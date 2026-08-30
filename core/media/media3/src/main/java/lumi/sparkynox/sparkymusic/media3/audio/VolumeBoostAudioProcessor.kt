package lumi.sparkynox.sparkymusic.media3.audio

import androidx.media3.common.C
import androidx.media3.common.audio.AudioProcessor
import androidx.media3.common.audio.BaseAudioProcessor
import androidx.media3.common.util.UnstableApi
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.tanh

/** Highest boost the slider offers. Past this it's just distortion with extra steps. */
const val VOLUME_BOOST_MAX_DB = 12f

private const val PCM16_MAX = 32_767.0

/**
 * Sample level, as a fraction of full scale, past which the soft limiter starts rolling gain off
 * instead of letting the boosted signal ride straight into the ceiling. Boosted peaks land in the
 * limiter's curve well before they'd clip outright, so a hot boost turns into gentle compression on
 * the loudest peaks rather than a hard, crackling clip.
 */
private const val LIMITER_KNEE = 0.891 // -1 dBFS

/**
 * Media3 [AudioProcessor] that raises level by a flat dB amount, independent of the equalizer's
 * own preamp (which only ever cuts — see [EqualizerAudioProcessor]'s callers).
 *
 * Placed after the equalizer/crossfade/sleep-fade chain, so it boosts the fully mixed signal
 * those stages already produced rather than sitting ahead of them and changing how much headroom
 * they have to work with.
 *
 * A flat gain alone would clip on any track already close to full scale — most mastered music is.
 * `tanh` past [LIMITER_KNEE] gives the boosted signal a soft ceiling instead: peaks under the knee
 * pass at the requested gain unchanged, and peaks that would have clipped get compressed down to
 * just under full scale rather than clamped, which is what turns into audible crackle.
 */
@UnstableApi
class VolumeBoostAudioProcessor(
    private val boostDb: () -> Float,
) : BaseAudioProcessor() {
    private var appliedBoostDb: Float? = null
    private var gain = 1.0
    private var bypass = true

    override fun onConfigure(inputAudioFormat: AudioProcessor.AudioFormat): AudioProcessor.AudioFormat {
        if (inputAudioFormat.encoding != C.ENCODING_PCM_16BIT) {
            return AudioProcessor.AudioFormat.NOT_SET
        }
        appliedBoostDb = null
        return inputAudioFormat
    }

    override fun onFlush() {
        // Stateless sample-by-sample, nothing to clear.
    }

    override fun onReset() {
        appliedBoostDb = null
        gain = 1.0
        bypass = true
    }

    override fun queueInput(inputBuffer: ByteBuffer) {
        val remaining = inputBuffer.remaining()
        if (remaining == 0) return

        val output = replaceOutputBuffer(remaining)
        syncGain()

        if (bypass) {
            output.put(inputBuffer)
            output.flip()
            return
        }

        inputBuffer.order(ByteOrder.nativeOrder())
        while (inputBuffer.remaining() >= 2) {
            val boosted = inputBuffer.short.toDouble() * gain
            output.putShort(softLimit(boosted).toInt().toShort())
        }
        while (inputBuffer.hasRemaining()) {
            output.put(inputBuffer.get())
        }

        output.flip()
    }

    private fun syncGain() {
        val next = boostDb()
        if (next == appliedBoostDb) return
        appliedBoostDb = next
        bypass = next <= 0f
        if (!bypass) {
            gain = 10.0.pow(next.coerceIn(0f, VOLUME_BOOST_MAX_DB) / 20.0)
        }
    }

    /**
     * Identity below the knee, `tanh`-compressed above it, scaled so a sample that lands exactly
     * on the knee comes out unchanged — no seam between the two halves of the curve.
     */
    private fun softLimit(sample: Double): Double {
        val kneeSamples = LIMITER_KNEE * PCM16_MAX
        val magnitude = abs(sample)
        if (magnitude <= kneeSamples) return sample

        val headroom = PCM16_MAX - kneeSamples
        val over = (magnitude - kneeSamples) / headroom
        val compressed = kneeSamples + headroom * tanh(over)
        return if (sample < 0) -compressed else compressed
    }
}

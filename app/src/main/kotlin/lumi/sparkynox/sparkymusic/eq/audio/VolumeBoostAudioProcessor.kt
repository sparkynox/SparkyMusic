package lumi.sparkynox.sparkymusic.eq.audio

import androidx.media3.common.C
import androidx.media3.common.audio.AudioProcessor
import androidx.media3.common.util.UnstableApi
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.tanh

/** Highest boost the slider offers. Past this it's just distortion with extra steps. */
const val VOLUME_BOOST_MAX_DB = 12f

private const val PCM16_MAX = 32767.0

/**
 * Sample level, as a fraction of full scale, past which the soft limiter starts rolling gain off
 * instead of letting the boosted signal ride straight into the ceiling. Boosted peaks land in the
 * limiter's curve well before they'd clip outright, so a hot boost turns into gentle compression
 * on the loudest peaks rather than a hard, crackling clip.
 */
private const val LIMITER_KNEE = 0.891 // -1 dBFS

/**
 * Flat dB gain, independent of the parametric EQ's own preamp ([lumi.sparkynox.sparkymusic.eq.data.ParametricEQ.preamp]).
 * Placed after the equalizer/duck/silence-detector group in [lumi.sparkynox.sparkymusic.playback.MusicService]'s
 * processor chain, so it boosts the fully mixed signal those stages already produced.
 *
 * A flat gain alone would clip on any track already close to full scale — most mastered music
 * is. `tanh` past [LIMITER_KNEE] gives the boosted signal a soft ceiling instead: peaks under
 * the knee pass at the requested gain unchanged, and peaks that would have clipped get
 * compressed down to just under full scale rather than clamped, which is what turns into
 * audible crackle.
 */
@UnstableApi
class VolumeBoostAudioProcessor : AudioProcessor {

    private var channelCount = 0
    private var encoding = C.ENCODING_INVALID
    private var isActive = false

    private var inputBuffer: ByteBuffer = EMPTY_BUFFER
    private var outputBuffer: ByteBuffer = EMPTY_BUFFER
    private var inputEnded = false

    /** Requested boost in dB, 0..[VOLUME_BOOST_MAX_DB]. Set from settings. */
    @Volatile
    private var boostDb: Float = 0f

    private var gain = 1.0

    companion object {
        private val EMPTY_BUFFER: ByteBuffer = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder())
    }

    fun setBoost(db: Float) {
        boostDb = db.coerceIn(0f, VOLUME_BOOST_MAX_DB)
        gain = if (boostDb <= 0f) 1.0 else 10.0.pow(boostDb / 20.0)
    }

    override fun configure(inputAudioFormat: AudioProcessor.AudioFormat): AudioProcessor.AudioFormat {
        channelCount = inputAudioFormat.channelCount
        encoding = inputAudioFormat.encoding

        if (encoding != C.ENCODING_PCM_16BIT) {
            throw AudioProcessor.UnhandledAudioFormatException(inputAudioFormat)
        }

        isActive = true
        return inputAudioFormat
    }

    override fun isActive(): Boolean = isActive

    override fun queueInput(inputBuffer: ByteBuffer) {
        val inputSize = inputBuffer.remaining()
        if (inputSize == 0) return

        if (outputBuffer.capacity() < inputSize) {
            outputBuffer = ByteBuffer.allocateDirect(inputSize).order(ByteOrder.nativeOrder())
        } else {
            outputBuffer.clear()
        }

        if (gain == 1.0) {
            outputBuffer.put(inputBuffer)
        } else {
            val sampleCount = inputSize / 2
            repeat(sampleCount) {
                val boosted = inputBuffer.getShort().toDouble() * gain
                outputBuffer.putShort(softLimit(boosted).toInt().toShort())
            }
        }

        outputBuffer.flip()
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

    override fun getOutput(): ByteBuffer {
        val buffer = outputBuffer
        outputBuffer = EMPTY_BUFFER
        return buffer
    }

    override fun isEnded(): Boolean = inputEnded && outputBuffer.remaining() == 0

    @Deprecated("Deprecated in Java")
    override fun flush() {
        outputBuffer = EMPTY_BUFFER
        inputEnded = false
    }

    override fun reset() {
        @Suppress("DEPRECATION")
        flush()
        inputBuffer = EMPTY_BUFFER
        channelCount = 0
        encoding = C.ENCODING_INVALID
        isActive = false
    }

    override fun queueEndOfStream() {
        inputEnded = true
    }
}

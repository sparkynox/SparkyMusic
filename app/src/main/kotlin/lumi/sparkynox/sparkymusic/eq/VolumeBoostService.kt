package lumi.sparkynox.sparkymusic.eq

import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import lumi.sparkynox.sparkymusic.eq.audio.VolumeBoostAudioProcessor
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Holds the running [VolumeBoostAudioProcessor] instance(s) so Settings can push a new boost
 * value live, the same shape as [EqualizerService] does for the parametric EQ.
 */
@Singleton
class VolumeBoostService @Inject constructor() {

    @OptIn(UnstableApi::class)
    private val audioProcessors = mutableListOf<VolumeBoostAudioProcessor>()
    private var pendingBoostDb: Float? = null

    companion object {
        private const val TAG = "VolumeBoostService"
    }

    @OptIn(UnstableApi::class)
    fun addAudioProcessor(processor: VolumeBoostAudioProcessor) {
        audioProcessors.add(processor)
        pendingBoostDb?.let { processor.setBoost(it) }
        Timber.tag(TAG).d("Audio processor added. Total: ${audioProcessors.size}")
    }

    fun removeAudioProcessor(processor: VolumeBoostAudioProcessor) {
        audioProcessors.remove(processor)
    }

    @OptIn(UnstableApi::class)
    fun setBoost(boostDb: Float) {
        pendingBoostDb = boostDb
        if (audioProcessors.isEmpty()) {
            Timber.tag(TAG).w("No audio processors set yet. Storing boost as pending: $boostDb dB")
            return
        }
        audioProcessors.forEach { it.setBoost(boostDb) }
    }

    fun release() {
        audioProcessors.clear()
    }
}

package lumi.sparkynox.sparkymusic.ui.screens.equalizer

import lumi.sparkynox.sparkymusic.eq.data.SavedEQProfile


data class EQState(
    val profiles: List<SavedEQProfile> = emptyList(),
    val activeProfileId: String? = null,
    val importStatus: String? = null,
    val error: String? = null
)
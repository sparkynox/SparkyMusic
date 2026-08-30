package lumi.sparkynox.sparkymusic.lyrics.models.response

import kotlinx.serialization.Serializable

@Serializable
data class BetterLyricsResponse(
    val ttml: String,
)
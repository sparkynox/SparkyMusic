package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.body

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class GetTranscriptBody(
    val context: Context,
    val params: String,
)
package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.body

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class GetSearchSuggestionsBody(
    val context: Context,
    val input: String,
)
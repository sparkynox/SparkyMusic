package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.body

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class SearchBody(
    val context: Context,
    val query: String?,
    val params: String?,
)
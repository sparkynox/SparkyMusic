package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.body

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.Context
import kotlinx.serialization.Serializable

@Serializable
data class GetQueueBody(
    val context: Context,
    val videoIds: List<String>?,
    val playlistId: String?,
)
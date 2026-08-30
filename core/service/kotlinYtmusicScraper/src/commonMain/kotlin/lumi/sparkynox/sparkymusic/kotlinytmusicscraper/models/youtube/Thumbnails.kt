package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.youtube

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.Thumbnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnails(
    @SerialName("thumbnails")
    val thumbnails: List<Thumbnail>? = null,
)
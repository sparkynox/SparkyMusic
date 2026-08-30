package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.pages

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.AlbumItem
import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.VideoItem

data class ExplorePage(
    val released: List<AlbumItem>,
    val musicVideo: List<VideoItem>,
)
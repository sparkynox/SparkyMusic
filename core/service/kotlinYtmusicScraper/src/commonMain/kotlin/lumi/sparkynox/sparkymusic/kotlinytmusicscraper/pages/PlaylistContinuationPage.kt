package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.pages

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.SongItem

data class PlaylistContinuationPage(
    val songs: List<SongItem>,
    val continuation: String?,
)
package lumi.sparkynox.sparkymusic.domain.data.model.mood.genre

import lumi.sparkynox.sparkymusic.domain.data.model.searchResult.songs.Artist

data class ItemsSong(
    val title: String,
    val artist: List<Artist>?,
    val videoId: String,
)
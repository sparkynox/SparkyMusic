package lumi.sparkynox.sparkymusic.domain.data.model.home

import lumi.sparkynox.sparkymusic.domain.data.model.searchResult.songs.Thumbnail

data class HomeItem(
    val contents: List<Content?>,
    val title: String,
    val subtitle: String? = null,
    val thumbnail: List<Thumbnail>? = null,
    val channelId: String? = null,
)
package lumi.sparkynox.sparkymusic.domain.data.model.browse.artist

import lumi.sparkynox.sparkymusic.domain.data.model.searchResult.songs.Thumbnail

data class ResultRelated(
    val browseId: String,
    val subscribers: String,
    val thumbnails: List<Thumbnail>,
    val title: String,
)
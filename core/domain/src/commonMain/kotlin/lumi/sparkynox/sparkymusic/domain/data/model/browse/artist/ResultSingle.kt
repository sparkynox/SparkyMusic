package lumi.sparkynox.sparkymusic.domain.data.model.browse.artist

import lumi.sparkynox.sparkymusic.domain.data.model.searchResult.songs.Thumbnail
import lumi.sparkynox.sparkymusic.domain.data.type.HomeContentType

data class ResultSingle(
    val browseId: String,
    val thumbnails: List<Thumbnail>,
    val title: String,
    val year: String,
) : HomeContentType
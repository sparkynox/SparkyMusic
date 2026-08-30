package lumi.sparkynox.sparkymusic.domain.data.model.browse.artist

import lumi.sparkynox.sparkymusic.domain.data.model.searchResult.songs.Thumbnail
import lumi.sparkynox.sparkymusic.domain.data.type.HomeContentType

data class ResultAlbum(
    val browseId: String,
    val isExplicit: Boolean,
    val thumbnails: List<Thumbnail>,
    val title: String,
    val year: String,
) : HomeContentType
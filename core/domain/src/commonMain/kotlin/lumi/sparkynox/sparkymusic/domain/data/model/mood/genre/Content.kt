package lumi.sparkynox.sparkymusic.domain.data.model.mood.genre

import lumi.sparkynox.sparkymusic.domain.data.model.searchResult.songs.Thumbnail
import lumi.sparkynox.sparkymusic.domain.data.type.HomeContentType

data class Content(
    val playlistBrowseId: String,
    val thumbnail: List<Thumbnail>?,
    val title: Title,
) : HomeContentType
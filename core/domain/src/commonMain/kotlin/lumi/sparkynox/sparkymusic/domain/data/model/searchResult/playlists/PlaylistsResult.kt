package lumi.sparkynox.sparkymusic.domain.data.model.searchResult.playlists

import lumi.sparkynox.sparkymusic.domain.data.model.searchResult.songs.Thumbnail
import lumi.sparkynox.sparkymusic.domain.data.type.PlaylistType
import lumi.sparkynox.sparkymusic.domain.data.type.SearchResultType
import lumi.sparkynox.sparkymusic.domain.utils.isRadioPlaylistId

data class PlaylistsResult(
    val author: String,
    val browseId: String,
    val category: String,
    val itemCount: String,
    val resultType: String,
    val thumbnails: List<Thumbnail>,
    val title: String,
) : PlaylistType,
    SearchResultType {
    override fun playlistType(): PlaylistType.Type =
        if (resultType == "Podcast") {
            PlaylistType.Type.PODCAST
        } else if (browseId.isRadioPlaylistId()) {
            PlaylistType.Type.RADIO
        } else {
            PlaylistType.Type.YOUTUBE_PLAYLIST
        }

    override fun objectType(): SearchResultType.Type =
        if (resultType == "Podcast") {
            SearchResultType.Type.PODCAST
        } else {
            SearchResultType.Type.PLAYLIST
        }
}
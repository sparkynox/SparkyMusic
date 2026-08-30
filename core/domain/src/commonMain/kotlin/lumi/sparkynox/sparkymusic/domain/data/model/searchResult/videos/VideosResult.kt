package lumi.sparkynox.sparkymusic.domain.data.model.searchResult.videos

import lumi.sparkynox.sparkymusic.domain.data.model.searchResult.songs.Artist
import lumi.sparkynox.sparkymusic.domain.data.model.searchResult.songs.Thumbnail
import lumi.sparkynox.sparkymusic.domain.data.type.SearchResultType

data class VideosResult(
    val artists: List<Artist>?,
    val category: String?,
    val duration: String?,
    val durationSeconds: Int?,
    val resultType: String?,
    val thumbnails: List<Thumbnail>?,
    val title: String,
    val videoId: String,
    val videoType: String?,
    val views: String?,
    val year: Any,
) : SearchResultType {
    override fun objectType(): SearchResultType.Type = SearchResultType.Type.VIDEO
}
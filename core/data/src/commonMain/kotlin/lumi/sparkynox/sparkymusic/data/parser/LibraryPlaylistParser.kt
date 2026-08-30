package lumi.sparkynox.sparkymusic.data.parser

import lumi.sparkynox.sparkymusic.domain.data.model.searchResult.playlists.PlaylistsResult
import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.GridRenderer
import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.MusicTwoRowItemRenderer

internal fun parseLibraryPlaylist(input: List<GridRenderer.Item>): List<PlaylistsResult> {
    val list: MutableList<PlaylistsResult> = mutableListOf()
    if (input.isNotEmpty()) {
        for (i in input.indices) {
            input[i].musicTwoRowItemRenderer?.let {
                if (it.navigationEndpoint?.browseEndpoint?.browseId != null) {
                    list.add(
                        PlaylistsResult(
                            author =
                                it.subtitle
                                    ?.runs
                                    ?.get(0)
                                    ?.text ?: "",
                            browseId = it.navigationEndpoint?.browseEndpoint?.browseId ?: "",
                            category = "",
                            itemCount = "",
                            resultType = "",
                            thumbnails =
                                it.thumbnailRenderer
                                    ?.musicThumbnailRenderer
                                    ?.thumbnail
                                    ?.thumbnails
                                    ?.toListThumbnail() ?: listOf(),
                            title =
                                it.title
                                    ?.runs
                                    ?.get(0)
                                    ?.text ?: "",
                        ),
                    )
                }
            }
        }
    }
    return list
}

internal fun parseNextLibraryPlaylist(input: List<MusicTwoRowItemRenderer>): List<PlaylistsResult> =
    input.map {
        PlaylistsResult(
            author =
                it.subtitle
                    ?.runs
                    ?.get(0)
                    ?.text ?: "",
            browseId = it.navigationEndpoint?.browseEndpoint?.browseId ?: "",
            category = "",
            itemCount = "",
            resultType = "",
            thumbnails =
                it.thumbnailRenderer
                    ?.musicThumbnailRenderer
                    ?.thumbnail
                    ?.thumbnails
                    ?.toListThumbnail() ?: listOf(),
            title =
                it.title
                    ?.runs
                    ?.get(0)
                    ?.text ?: "",
        )
    }
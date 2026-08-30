package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.response

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.Continuation
import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.MusicResponsiveListItemRenderer
import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.MusicShelfRenderer
import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.Tabs
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val contents: Contents?,
    val continuationContents: ContinuationContents?,
) {
    @Serializable
    data class Contents(
        val tabbedSearchResultsRenderer: Tabs?,
    )

    @Serializable
    data class ContinuationContents(
        val musicShelfContinuation: MusicShelfContinuation,
    ) {
        @Serializable
        data class MusicShelfContinuation(
            val contents: List<Content>,
            val continuations: List<Continuation>?,
        ) {
            @Serializable
            data class Content(
                val musicResponsiveListItemRenderer: MusicResponsiveListItemRenderer?,
                val musicMultiRowListItemRenderer: MusicShelfRenderer.Content.MusicMultiRowListItemRenderer?,
            )
        }
    }
}
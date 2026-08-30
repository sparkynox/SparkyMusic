package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.pages

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.YTItem

data class BrowseResult(
    val title: String?,
    val items: List<Item>,
) {
    data class Item(
        val title: String?,
        val items: List<YTItem>,
    )
}
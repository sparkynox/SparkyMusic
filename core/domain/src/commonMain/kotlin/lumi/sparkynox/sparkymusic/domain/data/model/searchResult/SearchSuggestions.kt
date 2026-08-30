package lumi.sparkynox.sparkymusic.domain.data.model.searchResult

import lumi.sparkynox.sparkymusic.domain.data.type.SearchResultType

data class SearchSuggestions(
    val queries: List<String>,
    val recommendedItems: List<SearchResultType>,
)
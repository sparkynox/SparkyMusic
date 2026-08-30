package lumi.sparkynox.sparkymusic.domain.data.model.home.chart

import lumi.sparkynox.sparkymusic.domain.data.model.browse.artist.ResultPlaylist

data class ChartItemPlaylist(
    val title: String,
    val playlists: List<ResultPlaylist>,
)
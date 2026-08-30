package lumi.sparkynox.sparkymusic.domain.data.model.home

import lumi.sparkynox.sparkymusic.domain.data.model.home.chart.Chart
import lumi.sparkynox.sparkymusic.domain.data.model.mood.Mood
import lumi.sparkynox.sparkymusic.domain.utils.Resource

data class HomeDataCombine(
    val home: Resource<Pair<String?, List<HomeItem>>>,
    val mood: Resource<Mood>,
    val chart: Resource<Chart>,
    val newRelease: Resource<List<HomeItem>>,
)
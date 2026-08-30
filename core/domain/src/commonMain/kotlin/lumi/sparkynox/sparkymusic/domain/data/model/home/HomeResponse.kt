package lumi.sparkynox.sparkymusic.domain.data.model.home

import lumi.sparkynox.sparkymusic.domain.data.model.home.chart.Chart
import lumi.sparkynox.sparkymusic.domain.data.model.mood.Mood
import lumi.sparkynox.sparkymusic.domain.utils.Resource

data class HomeResponse(
    val homeItem: Resource<ArrayList<HomeItem>>,
    val exploreMood: Resource<Mood>,
    val exploreChart: Resource<Chart>,
)
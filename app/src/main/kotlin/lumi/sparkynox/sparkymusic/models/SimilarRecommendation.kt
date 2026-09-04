

package lumi.sparkynox.sparkymusic.models

import com.music.innertube.models.YTItem
import lumi.sparkynox.sparkymusic.db.entities.LocalItem

data class SimilarRecommendation(
    val title: LocalItem,
    val items: List<YTItem>,
)

package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.subscriptionButton.SubscribeButtonRenderer
import kotlinx.serialization.Serializable

@Serializable
data class SubscriptionButton(
    val subscribeButtonRenderer: SubscribeButtonRenderer,
)
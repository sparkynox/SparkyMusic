package lumi.sparkynox.sparkymusic.domain.data.model.network

import lumi.sparkynox.sparkymusic.domain.manager.DataStoreManager

data class ProxyConfiguration(
    val host: String,
    val port: Int,
    val type: DataStoreManager.ProxyType,
    val username: String? = null,
    val password: String? = null,
)
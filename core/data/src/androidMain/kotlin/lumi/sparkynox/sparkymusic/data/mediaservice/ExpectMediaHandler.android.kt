package lumi.sparkynox.sparkymusic.data.mediaservice

import lumi.sparkynox.sparkymusic.domain.repository.AnalyticsRepository

actual fun createMediaServiceHandler(
    dataStoreManager: lumi.sparkynox.sparkymusic.domain.manager.DataStoreManager,
    songRepository: lumi.sparkynox.sparkymusic.domain.repository.SongRepository,
    streamRepository: lumi.sparkynox.sparkymusic.domain.repository.StreamRepository,
    localPlaylistRepository: lumi.sparkynox.sparkymusic.domain.repository.LocalPlaylistRepository,
    analyticsRepository: AnalyticsRepository,
    coroutineScope: kotlinx.coroutines.CoroutineScope,
): lumi.sparkynox.sparkymusic.domain.mediaservice.handler.MediaPlayerHandler =
    MediaServiceHandlerImpl(
        dataStoreManager,
        songRepository,
        streamRepository,
        localPlaylistRepository,
        analyticsRepository,
        coroutineScope,
    )
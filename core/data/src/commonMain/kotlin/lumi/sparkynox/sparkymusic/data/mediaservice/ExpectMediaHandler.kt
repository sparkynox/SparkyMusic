package lumi.sparkynox.sparkymusic.data.mediaservice

import lumi.sparkynox.sparkymusic.domain.manager.DataStoreManager
import lumi.sparkynox.sparkymusic.domain.mediaservice.handler.MediaPlayerHandler
import lumi.sparkynox.sparkymusic.domain.repository.AnalyticsRepository
import lumi.sparkynox.sparkymusic.domain.repository.LocalPlaylistRepository
import lumi.sparkynox.sparkymusic.domain.repository.SongRepository
import lumi.sparkynox.sparkymusic.domain.repository.StreamRepository
import kotlinx.coroutines.CoroutineScope

expect fun createMediaServiceHandler(
    dataStoreManager: DataStoreManager,
    songRepository: SongRepository,
    streamRepository: StreamRepository,
    localPlaylistRepository: LocalPlaylistRepository,
    analyticsRepository: AnalyticsRepository,
    coroutineScope: CoroutineScope,
): MediaPlayerHandler
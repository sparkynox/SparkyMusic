package lumi.sparkynox.sparkymusic.data.di

import lumi.sparkynox.sparkymusic.common.Config
import lumi.sparkynox.sparkymusic.data.mediaservice.createMediaServiceHandler
import lumi.sparkynox.sparkymusic.domain.mediaservice.handler.MediaPlayerHandler
import kotlinx.coroutines.CoroutineScope
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mediaHandlerModule =
    module {
        single<MediaPlayerHandler>(createdAtStart = true) {
            createMediaServiceHandler(
                dataStoreManager = get(),
                songRepository = get(),
                streamRepository = get(),
                localPlaylistRepository = get(),
                analyticsRepository = get(),
                coroutineScope = get<CoroutineScope>(named(Config.SERVICE_SCOPE)),
            )
        }
    }
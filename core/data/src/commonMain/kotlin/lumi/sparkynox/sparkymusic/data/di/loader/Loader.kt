package lumi.sparkynox.sparkymusic.data.di.loader

import lumi.sparkynox.sparkymusic.data.di.databaseModule
import lumi.sparkynox.sparkymusic.data.di.mediaHandlerModule
import lumi.sparkynox.sparkymusic.data.di.repositoryModule
import org.koin.core.context.loadKoinModules

fun loadAllModules() {
    loadKoinModules(
        listOf(
            databaseModule,
            repositoryModule,
        ),
    )
    loadKoinModules(mediaHandlerModule)
    loadMediaService()
}

expect fun loadMediaService()
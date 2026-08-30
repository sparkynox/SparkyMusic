package lumi.sparkynox.sparkymusic.data.di

import DatabaseDao
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import lumi.sparkynox.sparkymusic.data.dataStore.DataStoreManagerImpl
import lumi.sparkynox.sparkymusic.data.dataStore.createDataStoreInstance
import lumi.sparkynox.sparkymusic.data.db.Converters
import lumi.sparkynox.sparkymusic.data.db.MusicDatabase
import lumi.sparkynox.sparkymusic.data.db.datasource.AnalyticsDatasource
import lumi.sparkynox.sparkymusic.data.db.datasource.LocalDataSource
import lumi.sparkynox.sparkymusic.data.db.getDatabaseBuilder
import lumi.sparkynox.sparkymusic.domain.manager.DataStoreManager
import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.YouTube
import lumi.sparkynox.sparkymusic.spotify.Spotify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import lumi.sparkynox.sparkymusic.aiservice.AiClient
import lumi.sparkynox.sparkymusic.lyrics.SimpMusicLyricsClient
import kotlin.time.ExperimentalTime
import lumi.sparkynox.sparkymusic.autoeq.AutoEq

@OptIn(ExperimentalTime::class)
val databaseModule =
    module {
        single(createdAtStart = true) {
            Converters()
        }
        // Database
        single(createdAtStart = true) {
            getDatabaseBuilder(
                get<Converters>()
            )
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
        // DatabaseDao
        single(createdAtStart = true) {
            get<MusicDatabase>().getDatabaseDao()
        }
        // LocalDataSource
        single(createdAtStart = true) {
            LocalDataSource(get<DatabaseDao>(), get<MusicDatabase>())
        }
        // AnalyticsDatasource
        single(createdAtStart = true) {
            AnalyticsDatasource(get<DatabaseDao>())
        }
        // Datastore
        single(createdAtStart = true) {
            createDataStoreInstance()
        }
        // DatastoreManager
        single<DataStoreManager>(createdAtStart = true) {
            DataStoreManagerImpl(get<DataStore<Preferences>>())
        }

        // Move YouTube from Singleton to Koin DI
        single(createdAtStart = true) {
            YouTube()
        }

        single(createdAtStart = true) {
            Spotify()
        }

        single(createdAtStart = true) {
            AiClient()
        }

        single(createdAtStart = true) {
            SimpMusicLyricsClient()
        }

        // Not created at start, unlike the rest: nothing needs it until someone opens the AutoEq
        // picker, and it holds an HTTP client the vast majority of sessions never use.
        single {
            AutoEq()
        }
    }
package lumi.sparkynox.sparkymusic.data.di

import lumi.sparkynox.sparkymusic.common.Config.SERVICE_SCOPE
import lumi.sparkynox.sparkymusic.data.io.fileDir
import lumi.sparkynox.sparkymusic.data.repository.AccountRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.AlbumRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.AnalyticsRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.ArtistRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.AutoEqRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.CommonRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.HomeRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.ImportRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.LocalPlaylistRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.LyricsCanvasRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.PlaylistRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.PodcastRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.SearchRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.SongRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.StreamRepositoryImpl
import lumi.sparkynox.sparkymusic.data.repository.UpdateRepositoryImpl
import lumi.sparkynox.sparkymusic.domain.repository.AccountRepository
import lumi.sparkynox.sparkymusic.domain.repository.AlbumRepository
import lumi.sparkynox.sparkymusic.domain.repository.AnalyticsRepository
import lumi.sparkynox.sparkymusic.domain.repository.ArtistRepository
import lumi.sparkynox.sparkymusic.domain.repository.AutoEqRepository
import lumi.sparkynox.sparkymusic.domain.repository.CommonRepository
import lumi.sparkynox.sparkymusic.domain.repository.HomeRepository
import lumi.sparkynox.sparkymusic.domain.repository.ImportRepository
import lumi.sparkynox.sparkymusic.domain.repository.LocalPlaylistRepository
import lumi.sparkynox.sparkymusic.domain.repository.LyricsCanvasRepository
import lumi.sparkynox.sparkymusic.domain.repository.PlaylistRepository
import lumi.sparkynox.sparkymusic.domain.repository.PodcastRepository
import lumi.sparkynox.sparkymusic.domain.repository.SearchRepository
import lumi.sparkynox.sparkymusic.domain.repository.SongRepository
import lumi.sparkynox.sparkymusic.domain.repository.StreamRepository
import lumi.sparkynox.sparkymusic.domain.repository.UpdateRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule =
    module {
        single<AccountRepository>(createdAtStart = true) {
            AccountRepositoryImpl(get(), get())
        }

        single<AlbumRepository>(createdAtStart = true) {
            AlbumRepositoryImpl(get(), get())
        }

        single<ArtistRepository>(createdAtStart = true) {
            ArtistRepositoryImpl(get(), get(), get())
        }

        single<CommonRepository>(createdAtStart = true) {
            CommonRepositoryImpl(get(named(SERVICE_SCOPE)), get(), get(), get(), get(), get()).apply {
                this.init("${fileDir()}/ytdlp-cookie.txt", get())
            }
        }

        // Lazy for the same reason its client is: the picker is the only thing that wants it.
        single<AutoEqRepository> {
            AutoEqRepositoryImpl(get(), get())
        }

        single<HomeRepository>(createdAtStart = true) {
            HomeRepositoryImpl(get(), get())
        }

        single<ImportRepository>(createdAtStart = true) {
            ImportRepositoryImpl(get())
        }

        single<LocalPlaylistRepository>(createdAtStart = true) {
            LocalPlaylistRepositoryImpl(get(), get())
        }

        single<LyricsCanvasRepository>(createdAtStart = true) {
            LyricsCanvasRepositoryImpl(get(), get(), get(), get(), get())
        }

        single<PlaylistRepository>(createdAtStart = true) {
            PlaylistRepositoryImpl(get(), get(), get())
        }

        single<PodcastRepository>(createdAtStart = true) {
            PodcastRepositoryImpl(get(), get())
        }

        single<SearchRepository>(createdAtStart = true) {
            SearchRepositoryImpl(get(), get())
        }

        single<SongRepository>(createdAtStart = true) {
            SongRepositoryImpl(get(), get(), get())
        }

        single<StreamRepository>(createdAtStart = true) {
            StreamRepositoryImpl(get(), get())
        }

        single<UpdateRepository>(createdAtStart = true) {
            UpdateRepositoryImpl(get())
        }

        single<AnalyticsRepository>(createdAtStart = true) {
            AnalyticsRepositoryImpl(get())
        }
    }
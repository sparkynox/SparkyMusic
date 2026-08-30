package lumi.sparkynox.sparkymusic.di

import lumi.sparkynox.sparkymusic.viewModel.AlbumViewModel
import lumi.sparkynox.sparkymusic.viewModel.AnalyticsViewModel
import lumi.sparkynox.sparkymusic.viewModel.ArtistViewModel
import lumi.sparkynox.sparkymusic.viewModel.HomeViewModel
import lumi.sparkynox.sparkymusic.viewModel.ImportViewModel
import lumi.sparkynox.sparkymusic.viewModel.LibraryDynamicPlaylistViewModel
import lumi.sparkynox.sparkymusic.viewModel.LibraryViewModel
import lumi.sparkynox.sparkymusic.viewModel.LocalPlaylistViewModel
import lumi.sparkynox.sparkymusic.viewModel.LogInViewModel
import lumi.sparkynox.sparkymusic.viewModel.MoodViewModel
import lumi.sparkynox.sparkymusic.viewModel.MoreAlbumsViewModel
import lumi.sparkynox.sparkymusic.viewModel.NotificationViewModel
import lumi.sparkynox.sparkymusic.viewModel.NowPlayingBottomSheetViewModel
import lumi.sparkynox.sparkymusic.viewModel.PlaylistViewModel
import lumi.sparkynox.sparkymusic.viewModel.PodcastViewModel
import lumi.sparkynox.sparkymusic.viewModel.RecentlySongsViewModel
import lumi.sparkynox.sparkymusic.viewModel.SearchViewModel
import lumi.sparkynox.sparkymusic.viewModel.AutoEqViewModel
import lumi.sparkynox.sparkymusic.viewModel.SettingsViewModel
import lumi.sparkynox.sparkymusic.viewModel.SharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val viewModelModule =
    module {
        single {
            SharedViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        single {
            SearchViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            NowPlayingBottomSheetViewModel(
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LibraryViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LibraryDynamicPlaylistViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            ImportViewModel(
                get(),
            )
        }
        viewModel {
            AlbumViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            HomeViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            AutoEqViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            SettingsViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            ArtistViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            PlaylistViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            LogInViewModel(
                get(),
            )
        }
        viewModel {
            PodcastViewModel(
                get(),
            )
        }
        viewModel {
            MoreAlbumsViewModel(
                get(),
            )
        }
        viewModel {
            RecentlySongsViewModel(
                get(),
            )
        }
        viewModel {
            LocalPlaylistViewModel(
                get(),
                get(),
                get(),
            )
        }
        viewModel {
            NotificationViewModel(
                get(),
            )
        }
        viewModel {
            MoodViewModel(
                get(),
                get(),
            )
        }
        viewModel {
            AnalyticsViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }
    }
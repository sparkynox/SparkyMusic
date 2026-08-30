package lumi.sparkynox.sparkymusic.ui.navigation.graph

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import lumi.sparkynox.sparkymusic.ui.navigation.destination.home.AnalyticsDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.home.HomeDestination
import lumi.sparkynox.sparkymusic.ui.theme.ForceDarkContent
import lumi.sparkynox.sparkymusic.ui.navigation.destination.library.LibraryDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.player.FullscreenDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.search.SearchDestination
import lumi.sparkynox.sparkymusic.ui.screen.home.HomeScreen
import lumi.sparkynox.sparkymusic.ui.screen.home.analytics.AnalyticsScreen
import lumi.sparkynox.sparkymusic.ui.screen.library.LibraryScreen
import lumi.sparkynox.sparkymusic.ui.screen.other.SearchScreen
import lumi.sparkynox.sparkymusic.ui.screen.player.FullscreenPlayer

@Composable
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
fun AppNavigationGraph(
    innerPadding: PaddingValues,
    navController: NavHostController,
    startDestination: Any = HomeDestination,
    performanceMode: Boolean = false,
    hideNavBar: () -> Unit = { },
    showNavBar: (shouldShowNowPlayingSheet: Boolean) -> Unit = { },
    showNowPlayingSheet: () -> Unit = {},
    onScrolling: (onTop: Boolean) -> Unit = {},
) {
    NavHost(
        navController,
        startDestination = startDestination,
        // Slide+fade compositing is the transition cost that actually shows up as jank on weak
        // GPUs — cutting straight to the destination in performance mode removes it outright
        // rather than trying to cheapen it.
        enterTransition = {
            if (performanceMode) EnterTransition.None else fadeIn() + slideInHorizontally { -it }
        },
        exitTransition = {
            if (performanceMode) ExitTransition.None else fadeOut() + slideOutHorizontally { it }
        },
        popEnterTransition = {
            if (performanceMode) EnterTransition.None else fadeIn() + slideInHorizontally { -it }
        },
        popExitTransition = {
            if (performanceMode) ExitTransition.None else fadeOut() + slideOutHorizontally { it }
        },
    ) {
        // Bottom bar destinations
        composable<HomeDestination> {
            HomeScreen(
                onScrolling = onScrolling,
                navController = navController,
            )
        }
        composable<SearchDestination> {
            SearchScreen(
                navController = navController,
            )
        }
        composable<LibraryDestination> {
            LibraryScreen(
                innerPadding = innerPadding,
                navController = navController,
                onScrolling = onScrolling,
            )
        }
        // Only reachable as a tab while local tracking is enabled
        composable<AnalyticsDestination> {
            AnalyticsScreen(
                navController = navController,
                innerPadding = innerPadding,
            )
        }
        composable<FullscreenDestination> {
            ForceDarkContent {
                FullscreenPlayer(
                    navController,
                    hideNavBar = hideNavBar,
                    showNavBar = {
                        showNavBar.invoke(true)
                        showNowPlayingSheet.invoke()
                    },
                )
            }
        }
        // Home screen graph
        homeScreenGraph(
            innerPadding = innerPadding,
            navController = navController,
        )
        // Library screen graph
        libraryScreenGraph(
            innerPadding = innerPadding,
            navController = navController,
        )
        // List screen graph
        listScreenGraph(
            innerPadding = innerPadding,
            navController = navController,
        )
        // Login screen graph
        loginScreenGraph(
            innerPadding = innerPadding,
            navController = navController,
            hideBottomBar = hideNavBar,
            showBottomBar = {
                showNavBar(false)
            },
        )
    }
}
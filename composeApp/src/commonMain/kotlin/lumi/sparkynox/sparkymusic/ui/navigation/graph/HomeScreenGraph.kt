package lumi.sparkynox.sparkymusic.ui.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import lumi.sparkynox.sparkymusic.ui.navigation.destination.home.MoodDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.home.RecentlySongsDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.home.SettingsDestination
import lumi.sparkynox.sparkymusic.ui.screen.home.MoodScreen
import lumi.sparkynox.sparkymusic.ui.screen.home.RecentlySongsScreen
import lumi.sparkynox.sparkymusic.ui.screen.home.SettingScreen


fun NavGraphBuilder.homeScreenGraph(
    innerPadding: PaddingValues,
    navController: NavController,
) {
    composable<MoodDestination> { entry ->
        val params = entry.toRoute<MoodDestination>().params
        MoodScreen(
            navController = navController,
            params = params,
        )
    }

    composable<RecentlySongsDestination> {
        RecentlySongsScreen(
            navController = navController,
            innerPadding = innerPadding,
        )
    }
    composable<SettingsDestination> {
        SettingScreen(
            navController = navController,
            innerPadding = innerPadding,
        )
    }
    composable<lumi.sparkynox.sparkymusic.ui.navigation.destination.home.EqualizerDestination> {
        lumi.sparkynox.sparkymusic.ui.screen.home.EqualizerScreen(
            navController = navController,
            innerPadding = innerPadding,
        )
    }

}
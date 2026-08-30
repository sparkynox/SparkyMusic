package lumi.sparkynox.sparkymusic.ui.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import lumi.sparkynox.sparkymusic.ui.navigation.destination.login.DiscordLoginDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.login.LastfmLoginDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.login.LoginDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.login.SpotifyLoginDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.login.WelcomeDestination
import lumi.sparkynox.sparkymusic.ui.screen.login.DiscordLoginScreen
import lumi.sparkynox.sparkymusic.ui.screen.login.LastfmLoginScreen
import lumi.sparkynox.sparkymusic.ui.screen.login.LoginScreen
import lumi.sparkynox.sparkymusic.ui.screen.login.SpotifyLoginScreen
import lumi.sparkynox.sparkymusic.ui.screen.login.WelcomeScreen

fun NavGraphBuilder.loginScreenGraph(
    innerPadding: PaddingValues,
    navController: NavController,
    hideBottomBar: () -> Unit,
    showBottomBar: () -> Unit,
) {
    composable<WelcomeDestination> {
        WelcomeScreen(
            innerPadding = innerPadding,
            navController = navController,
        )
    }

    composable<LoginDestination> {
        LoginScreen(
            innerPadding = innerPadding,
            navController = navController,
            hideBottomNavigation = hideBottomBar,
            showBottomNavigation = showBottomBar,
        )
    }

    composable<SpotifyLoginDestination> {
        SpotifyLoginScreen(
            innerPadding = innerPadding,
            navController = navController,
            hideBottomNavigation = hideBottomBar,
            showBottomNavigation = showBottomBar,
        )
    }

    composable<DiscordLoginDestination> {
        DiscordLoginScreen(
            innerPadding = innerPadding,
            navController = navController,
            hideBottomNavigation = hideBottomBar,
            showBottomNavigation = showBottomBar,
        )
    }

    composable<LastfmLoginDestination> {
        LastfmLoginScreen(
            innerPadding = innerPadding,
            navController = navController,
            hideBottomNavigation = hideBottomBar,
            showBottomNavigation = showBottomBar,
        )
    }
}
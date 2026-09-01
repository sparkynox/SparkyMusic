

package lumi.sparkynox.sparkymusic.ui.utils

import androidx.navigation.NavController
import lumi.sparkynox.sparkymusic.ui.screens.Screens

fun NavController.backToMain() {
    val mainRoutes = Screens.MainScreens.map { it.route }

    while (previousBackStackEntry != null &&
        currentBackStackEntry?.destination?.route !in mainRoutes
    ) {
        popBackStack()
    }
}

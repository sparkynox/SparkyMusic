package lumi.sparkynox.sparkymusic.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import lumi.sparkynox.sparkymusic.ui.navigation.destination.home.HomeDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.login.LoginDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.login.SpotifyLoginDestination
import lumi.sparkynox.sparkymusic.ui.navigation.destination.login.WelcomeDestination
import lumi.sparkynox.sparkymusic.ui.theme.seed
import lumi.sparkynox.sparkymusic.ui.theme.typo
import lumi.sparkynox.sparkymusic.viewModel.SettingsViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import sparkymusic.composeapp.generated.resources.Res
import sparkymusic.composeapp.generated.resources.app_icon
import sparkymusic.composeapp.generated.resources.welcome_login_google
import sparkymusic.composeapp.generated.resources.welcome_login_later_hint
import sparkymusic.composeapp.generated.resources.welcome_login_spotify
import sparkymusic.composeapp.generated.resources.welcome_not_now
import sparkymusic.composeapp.generated.resources.welcome_subtitle
import sparkymusic.composeapp.generated.resources.welcome_title

/**
 * Shown once, on first launch, ahead of [HomeDestination] — see
 * `DataStoreManager.hasSeenWelcomeScreen`. Every path off this screen (either login option, or
 * "Not Now") marks it seen and replaces it in the back stack with Home, so the user is never
 * routed back here by pressing back from wherever they land.
 *
 * The same login options stay reachable from Settings afterwards — this screen is a shortcut on
 * first run, not the only way in.
 */
@Composable
fun WelcomeScreen(
    innerPadding: PaddingValues,
    navController: NavController,
    settingsViewModel: SettingsViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        settingsViewModel.setHasSeenWelcomeScreen(true)
    }

    fun proceedToHome() {
        navController.navigate(HomeDestination) {
            popUpTo(WelcomeDestination) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(Res.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier.size(96.dp),
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(Res.string.welcome_title),
            style = typo().headlineSmall,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(Res.string.welcome_subtitle),
            style = typo().bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(40.dp))

        Button(
            onClick = {
                // The screen itself, not this button, is the point the flow is "seen" from —
                // marking it here would leave "Not Now" as the only path that actually records
                // having gone through the screen at all.
                navController.navigate(LoginDestination)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = seed,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
            Text(stringResource(Res.string.welcome_login_google))
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = {
                navController.navigate(SpotifyLoginDestination)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(Res.string.welcome_login_spotify))
        }

        Spacer(Modifier.height(20.dp))

        TextButton(onClick = { proceedToHome() }) {
            Text(stringResource(Res.string.welcome_not_now))
        }

        Text(
            text = stringResource(Res.string.welcome_login_later_hint),
            style = typo().bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

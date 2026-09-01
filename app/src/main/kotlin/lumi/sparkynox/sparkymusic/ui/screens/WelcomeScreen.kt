package lumi.sparkynox.sparkymusic.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import lumi.sparkynox.sparkymusic.R
import lumi.sparkynox.sparkymusic.ui.theme.DefaultThemeColor

/**
 * Shown once, on first launch, ahead of Home — gated by `IsFirstRunKey` in MainActivity's
 * NavHost `startDestination`. Every path off this screen (either login option, or "Not Now")
 * clears that flag and replaces this screen in the back stack with Home, so the user is never
 * routed back here by pressing back from wherever they land.
 *
 * The same login options stay reachable from Settings afterwards (Account settings, and
 * Settings > Import > Spotify) — this screen is a shortcut on first run, not the only way in.
 */
@Composable
fun WelcomeScreen(
    navController: NavController,
    onDismiss: () -> Unit,
) {
    fun proceedToHome() {
        onDismiss()
        navController.navigate(Screens.Home.route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_nobg),
            contentDescription = null,
            modifier = Modifier.size(96.dp),
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Welcome to SparkyMusic",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Sign in to sync your library, playlists and liked songs",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(40.dp))

        Button(
            onClick = {
                onDismiss()
                navController.navigate("login")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = DefaultThemeColor,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
            Text("Continue with Google / YouTube Music")
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = {
                onDismiss()
                navController.navigate("settings/spotify_import")
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Continue with Spotify")
        }

        Spacer(Modifier.height(20.dp))

        TextButton(onClick = { proceedToHome() }) {
            Text("Not Now")
        }

        Text(
            text = "You can always log in later from Settings",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

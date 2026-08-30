package lumi.sparkynox.sparkymusic.ui.navigation.destination.login

import kotlinx.serialization.Serializable

/**
 * First-launch screen offering to log in before landing on Home. Shown once — see
 * `DataStoreManager.hasSeenWelcomeScreen` — and never again after either a login attempt or
 * "Not Now", since the same options stay reachable from Settings afterwards.
 */
@Serializable
data object WelcomeDestination

@file:Suppress("ktlint:standard:filename")

package lumi.sparkynox.sparkymusic.expect.ui

import androidx.compose.runtime.Composable

interface PhotoPickerLauncher {
    fun launch()
}

@Composable
expect fun photoPickerResult(onResultUri: (String?) -> Unit): PhotoPickerLauncher
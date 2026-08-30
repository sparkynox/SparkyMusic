package lumi.sparkynox.sparkymusic.expect.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import lumi.sparkynox.sparkymusic.domain.data.model.metadata.Lyrics
import lumi.sparkynox.sparkymusic.domain.data.model.streams.TimeLine
import lumi.sparkynox.sparkymusic.media3.ui.MediaPlayerView
import lumi.sparkynox.sparkymusic.media3.ui.MediaPlayerViewWithSubtitle
import lumi.sparkynox.sparkymusic.extension.findActivity
import lumi.sparkynox.sparkymusic.extension.getScreenSizeInfo
import lumi.sparkynox.sparkymusic.ui.theme.typo

@Composable
actual fun MediaPlayerView(
    url: String,
    modifier: Modifier,
    cropToBounds: Boolean,
) {
    MediaPlayerView(
        modifier = modifier,
        context = LocalContext.current,
        density = LocalDensity.current,
        url = url,
        screenSize = getScreenSizeInfo(),
        cropToBounds = cropToBounds,
    )
}

@Composable
actual fun MediaPlayerViewWithSubtitle(
    modifier: Modifier,
    playerName: String,
    shouldPip: Boolean,
    shouldShowSubtitle: Boolean,
    shouldScaleDownSubtitle: Boolean,
    isInPipMode: Boolean,
    timelineState: TimeLine,
    lyricsData: Lyrics?,
    translatedLyricsData: Lyrics?,
    mainTextStyle: TextStyle,
    translatedTextStyle: TextStyle,
) {
    MediaPlayerViewWithSubtitle(
        playerName = playerName,
        modifier = modifier,
        shouldShowSubtitle = shouldShowSubtitle,
        shouldPip = shouldPip,
        shouldScaleDownSubtitle = shouldScaleDownSubtitle,
        timelineState = timelineState,
        lyricsData = lyricsData,
        translatedLyricsData = translatedLyricsData,
        context = LocalContext.current,
        activity = LocalActivity.current as? ComponentActivity ?: LocalContext.current.findActivity(),
        isInPipMode = isInPipMode,
        mainTextStyle = typo().bodyLarge,
        translatedTextStyle = typo().bodyMedium,
    )
}
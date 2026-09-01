

package lumi.sparkynox.sparkymusic.lyrics

import android.content.Context
import lumi.sparkynox.sparkymusic.betterlyrics.BetterLyrics
import lumi.sparkynox.sparkymusic.constants.EnableBetterLyricsKey
import lumi.sparkynox.sparkymusic.utils.dataStore
import lumi.sparkynox.sparkymusic.utils.get

object BetterLyricsProvider : LyricsProvider {
    override val name = "BetterLyrics"

    override fun isEnabled(context: Context): Boolean = context.dataStore[EnableBetterLyricsKey] ?: true

    override suspend fun getLyrics(
        id: String,
        title: String,
        artist: String,
        duration: Int,
        album: String?,
    ): Result<String> = BetterLyrics.getLyrics(title, artist, duration, album)

    override suspend fun getAllLyrics(
        id: String,
        title: String,
        artist: String,
        duration: Int,
        album: String?,
        callback: (String) -> Unit,
    ) {
        BetterLyrics.getAllLyrics(title, artist, duration, album, callback)
    }
}

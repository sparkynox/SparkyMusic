

package lumi.sparkynox.sparkymusic.lyrics

import android.content.Context
import com.music.lrclib.LrcLib
import lumi.sparkynox.sparkymusic.constants.EnableLrcLibKey
import lumi.sparkynox.sparkymusic.utils.dataStore
import lumi.sparkynox.sparkymusic.utils.get

object LrcLibLyricsProvider : LyricsProvider {
    override val name = "LrcLib"

    override fun isEnabled(context: Context): Boolean = context.dataStore[EnableLrcLibKey] ?: true

    override suspend fun getLyrics(
        id: String,
        title: String,
        artist: String,
        duration: Int,
        album: String?,
    ): Result<String> = LrcLib.getLyrics(title, artist, duration, album)

    override suspend fun getAllLyrics(
        id: String,
        title: String,
        artist: String,
        duration: Int,
        album: String?,
        callback: (String) -> Unit,
    ) {
        LrcLib.getAllLyrics(title, artist, duration, album, callback)
    }
}

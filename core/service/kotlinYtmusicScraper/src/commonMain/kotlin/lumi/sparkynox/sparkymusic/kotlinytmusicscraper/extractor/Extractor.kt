package lumi.sparkynox.sparkymusic.kotlinytmusicscraper.extractor

import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.SongItem
import lumi.sparkynox.sparkymusic.kotlinytmusicscraper.models.response.DownloadProgress

expect class Extractor() {
    fun init()

    fun logIn(cookie: String?)

    fun mergeAudioVideoDownload(filePath: String): DownloadProgress

    fun saveAudioWithThumbnail(
        filePath: String,
        track: SongItem,
    ): DownloadProgress

    fun newPipePlayer(videoId: String): List<Pair<Int, String>>
}
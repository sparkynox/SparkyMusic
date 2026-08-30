package lumi.sparkynox.sparkymusic.data.io

import okio.FileSystem

expect fun fileSystem(): FileSystem

expect fun fileDir(): String
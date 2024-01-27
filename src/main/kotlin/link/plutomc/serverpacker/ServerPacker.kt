package link.plutomc.serverpacker

import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

internal var logger: Logger = LoggerFactory.getLogger("serverpacker")
internal var version = "1.0.0"
internal val workDir = File(System.getProperty("user.dir"))
internal lateinit var cacheDir: File
internal lateinit var downloadCacheDir: File

fun main(args: Array<String>): Unit = runBlocking {
    logger.info("serverpacker v$version.")

    downloadCacheDir = File(workDir, "/cache/downloads/")

    if (!downloadCacheDir.exists()) {
        downloadCacheDir.mkdirs()
    }
}
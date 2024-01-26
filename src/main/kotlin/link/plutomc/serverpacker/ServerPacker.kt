package link.plutomc.serverpacker

import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

internal var logger: Logger = LoggerFactory.getLogger("serverpacker")
internal var version = "1.0.0"
internal lateinit var downloadCacheLocation: File

fun main(args: Array<String>): Unit = runBlocking {
    logger.info("serverpacker v$version.")

    val workDir = File(System.getProperty("user.dir"))
    downloadCacheLocation = File(workDir, "/cache/downloads/")

    if (!downloadCacheLocation.exists()) {
        downloadCacheLocation.mkdirs()
    }
}
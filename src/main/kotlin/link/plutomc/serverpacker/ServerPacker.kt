package link.plutomc.serverpacker

import kotlinx.coroutines.runBlocking
import link.plutomc.serverpacker.utils.OSType
import link.plutomc.serverpacker.utils.Timer
import link.plutomc.serverpacker.utils.dirCheckAndCreate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import kotlin.system.exitProcess

internal var logger: Logger = LoggerFactory.getLogger("serverpacker")
internal var version = "1.0.0"
internal val workDir = File(System.getProperty("user.dir"))
internal val cacheDir = File(workDir, "cache/").dirCheckAndCreate()
internal val profileCacheDir = File(cacheDir, "profiles/").dirCheckAndCreate()
internal val outputsDir = File(workDir, "outputs/").dirCheckAndCreate()
internal val downloadCacheDir = File(cacheDir, "downloads/").dirCheckAndCreate()
internal val startScriptCache = File(cacheDir, "startScripts/").dirCheckAndCreate()
internal val os = System.getProperty("os.name")
internal val osType = if (os.lowercase().contains("windows")) {
    OSType.Windows
} else (if (os.lowercase().contains("linux")) {
    OSType.Linux
} else if (os.lowercase().contains("mac")) {
    OSType.MacOS
} else {
    OSType.Unknown
    logger.error("You are running a unsupported operating system!")
    exitProcess(0)
})

fun main(args: Array<String>): Unit = runBlocking {
    logger.info("serverpacker v$version.")
    logger.info("Running on $os.")

    val timer = Timer()

    logger.info("Done! Used ${timer.endTimer()}ms to finish.")
}
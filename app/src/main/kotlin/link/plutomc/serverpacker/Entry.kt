package link.plutomc.serverpacker

import link.plutomc.serverpacker.impl.ServerProject
import java.io.File

fun main() {
    Constants.logger.info("serverpacker ${Constants.version()}.")

    val workDir = File(System.getProperty("user.dir"))
    val outputDir = File(workDir, "output")

    if (!outputDir.exists()) {
        outputDir.mkdirs()
    }

    val serverProject = ServerProject("test", "1.0.0", listOf("nostalfinals"), outputDir, workDir)
}

class Entry {
}
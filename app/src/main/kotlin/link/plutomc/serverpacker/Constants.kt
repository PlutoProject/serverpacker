package link.plutomc.serverpacker

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.jar.Manifest

object Constants {

    val logger: Logger = LoggerFactory.getLogger("link/plutomc/serverpacker")

    fun version(): String {
        val classLoader = Entry::class.java.classLoader
        val manifest = Manifest(classLoader.getResourceAsStream("META-INF/MANIFEST.MF"))
        val attributes = manifest.mainAttributes

        return attributes.getValue("Implementation-Version")
    }

}
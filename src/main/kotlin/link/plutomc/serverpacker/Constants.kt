package link.plutomc.serverpacker

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.jar.Manifest

object Constants {

    val logger: Logger = LoggerFactory.getLogger("serverpacker")
    val version = {
        val classLoader = Entry::class.java.classLoader
        val manifest = Manifest(classLoader.getResourceAsStream("META-INF/MANIFEST.MF"))
        val attributes = manifest.mainAttributes

        attributes.getValue("Implementation-Version")
    }

}
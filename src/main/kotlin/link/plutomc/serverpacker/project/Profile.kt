package link.plutomc.serverpacker.project

import link.plutomc.serverpacker.addition.Configs
import link.plutomc.serverpacker.addition.Mods
import link.plutomc.serverpacker.addition.Plugins
import link.plutomc.serverpacker.source.Source
import org.apache.commons.io.FileUtils
import java.io.File

data class Profile(
    val name: String,
    val version: String,
    val software: Source,
    val profileDir: File
) {

    var plugins = Plugins(this)
    var mods = Mods(this)
    var configs = Configs(this)
    var startScript = StartScript()

}
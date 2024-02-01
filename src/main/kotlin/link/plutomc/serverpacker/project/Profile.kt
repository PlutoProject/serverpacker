package link.plutomc.serverpacker.project

import link.plutomc.serverpacker.source.Source
import java.io.File

data class Profile(
    val name: String,
    val version: String,
    val software: Source,
    val profileDir: File
) {

    var plugins = Folder("plugins", this)
    var mods = Folder("mods", this)
    var configs = Folder("config", this)
    var startScript = StartScript()
    var eula = false
}
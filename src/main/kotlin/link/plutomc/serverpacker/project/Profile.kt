package link.plutomc.serverpacker.project

import link.plutomc.serverpacker.project.folder.Folder
import link.plutomc.serverpacker.project.folder.RootFolder
import link.plutomc.serverpacker.source.Source
import java.io.File

class Profile(
    val name: String,
    private var version: String,
    var software: Source,
    var profileDir: File,
) {

    var root = RootFolder(this)
    var plugins = Folder("plugins", this)
    var mods = Folder("mods", this)
    var configs = Folder("config", this)
    var startScript = StartScript()
    var eula = false

}
package ink.pmc.serverpacker.project

import ink.pmc.serverpacker.project.folder.Folder
import ink.pmc.serverpacker.project.folder.RootFolder
import ink.pmc.serverpacker.source.Source
import java.io.File

class Profile(
    val name: String,
    var version: String,
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
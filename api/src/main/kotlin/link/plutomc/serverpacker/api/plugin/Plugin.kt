package link.plutomc.serverpacker.api.plugin

import link.plutomc.serverpacker.api.file.LocalFile

interface Plugin: LocalFile {

    val name: String

}
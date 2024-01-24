package link.plutomc.serverpacker.api.plugin

import link.plutomc.serverpacker.api.File

interface Plugin: File {

    val name: String

}
package link.plutomc.serverpacker.impl.plugin

import link.plutomc.serverpacker.api.plugin.LocalPlugin
import java.io.File

class ServerLocalPlugin(
    override val name: String,
    override val actualFile: File
): LocalPlugin {


}
package link.plutomc.serverpacker.impl

import java.io.File

data class ServerLocalFile(
    override val actualFile: File
): link.plutomc.serverpacker.api.file.LocalFile
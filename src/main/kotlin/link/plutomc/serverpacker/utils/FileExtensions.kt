package link.plutomc.serverpacker.utils

import java.io.File

fun File.checkAndCreate(): File {
    if (!exists()) {
        mkdirs()
    }

    return this
}
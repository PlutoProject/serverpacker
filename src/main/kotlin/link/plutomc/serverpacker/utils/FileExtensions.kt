package link.plutomc.serverpacker.utils

import java.io.File

fun File.checkOrCreate(): File {
    if (!exists()) {
        mkdirs()
    }

    return this
}
package link.plutomc.serverpacker.utils

import java.io.File

fun File.dirCheckAndCreate(): File {
    if (!exists()) {
        mkdir()
    }

    return this
}

fun File.fileCheckOrCreate(): File {
    if (!exists()) {
        createNewFile()
    }

    return this
}
package link.plutomc.serverpacker.source

import java.io.File

data class LocalSource(override val file: File) : Source {

    override fun resolve(): Boolean {
        return file.exists()
    }

}

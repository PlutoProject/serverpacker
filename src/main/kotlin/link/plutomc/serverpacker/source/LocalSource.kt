package link.plutomc.serverpacker.source

import java.io.File

data class LocalSource(override val file: File) : Source {
    override val url: String
        get() = file.absolutePath

    override fun resolve(): Boolean {
        return file.exists()
    }

}

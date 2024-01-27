package link.plutomc.serverpacker.source

import java.io.File

data class LocalSource(override val file: File) : Source {

    override val isAbleToReach: Boolean
        get() = file.exists()

}

package link.plutomc.serverpacker.source

import java.io.File

interface Source {

    val file: File

    fun resolve(): Boolean

}
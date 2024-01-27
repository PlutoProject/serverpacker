package link.plutomc.serverpacker.project

interface Remote {

    fun download(): Pair<Boolean, String>

}
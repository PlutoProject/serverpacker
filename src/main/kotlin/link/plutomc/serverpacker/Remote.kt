package link.plutomc.serverpacker

interface Remote {

    fun download(): Pair<Boolean, String>

}
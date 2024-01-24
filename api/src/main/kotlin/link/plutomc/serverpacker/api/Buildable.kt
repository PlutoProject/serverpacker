package link.plutomc.serverpacker.api

interface Buildable {

    fun build() {
        buildZip()
        buildDocker()
        buildPterodactyl()
    }

    fun buildZip()

    fun buildDocker()

    fun buildPterodactyl()

}
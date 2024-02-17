package ink.pmc.serverpacker.project

class Project(
    private val name: String
) {

    val profiles = mutableListOf<Profile>()

    fun addProfile(profile: Profile) {
        profiles.add(profile)
    }

}
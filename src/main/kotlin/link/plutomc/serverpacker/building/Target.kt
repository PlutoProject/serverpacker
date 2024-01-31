package link.plutomc.serverpacker.building

import link.plutomc.serverpacker.project.Profile
import java.io.File

abstract class Target(profile: Profile) {

    abstract fun build(): File

}
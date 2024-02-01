package link.plutomc.serverpacker.tasks

import link.plutomc.serverpacker.project.Profile

interface Task {

    fun run(profile: Profile)

}
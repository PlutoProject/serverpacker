package ink.pmc.serverpacker.tasks

import ink.pmc.serverpacker.project.Profile

interface Task {

    fun run(profile: Profile)

}
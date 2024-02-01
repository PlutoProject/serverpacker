package link.plutomc.serverpacker.addition

import link.plutomc.serverpacker.project.Profile
import link.plutomc.serverpacker.source.Source
import link.plutomc.serverpacker.utils.checkAndCreate
import org.apache.commons.io.FileUtils
import java.io.File

class Folder(override val name: String, private val profile: Profile): IFolder {

    private val _contents = arrayListOf<Source>()

    override val actual: File
        get() = File(profile.profileDir, name).checkAndCreate()
    override val contents: List<Source>
        get() = _contents

    override fun copyContents() {
        _contents.forEach {
            FileUtils.copyFile(it.file, File(actual, it.file.name))
        }
    }

}
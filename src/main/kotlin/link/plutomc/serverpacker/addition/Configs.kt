package link.plutomc.serverpacker.addition

import org.apache.commons.io.FileUtils
import java.io.File

class Configs(private val profileDir: File) : Addition {

    private val _contents = hashMapOf<String, File>()

    override val folderName: String
        get() = "config"
    override val folder: File
        get() {
            val dir = File(profileDir, folderName)

            if (!dir.exists()) {
                dir.mkdirs()
            }

            return dir
        }
    override val contents: Map<String, File>
        get() = _contents

    override fun addContent(name: String, content: File) {
        _contents[name] = content
    }

    override fun removeContent(name: String) {
        _contents.remove(name)
    }

    override fun copyContents() {
        _contents.forEach {
            FileUtils.copyFile(it.value, File(folder, it.value.name))
        }
    }

}
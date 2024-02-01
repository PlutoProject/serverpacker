package link.plutomc.serverpacker.script

import link.plutomc.serverpacker.logger
import link.plutomc.serverpacker.project.Project
import java.io.File
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultValue
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate

object ScriptRunner {

    fun run(file: File): Project? {
        val result = evalFile(file)
        var project: Project? = null

        when (result) {
            is ResultWithDiagnostics.Success -> {
                when (val value = result.value.returnValue) {
                    is ResultValue.Value -> {
                        project = value.value as Project
                    }

                    else -> {
                        logger.error("Script has no return value!")
                    }
                }
            }

            else -> {
                logger.error("Script execution failed!")
            }
        }

        return project
    }

    private fun evalFile(scriptFile: File): ResultWithDiagnostics<EvaluationResult> {
        val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<ServerPackerScript>()
        return BasicJvmScriptingHost().eval(scriptFile.toScriptSource(), compilationConfiguration, null)
    }

}
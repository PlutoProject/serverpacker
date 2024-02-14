package ink.pmc.serverpacker.script

import kotlinx.coroutines.runBlocking
import kotlin.script.experimental.api.*
import kotlin.script.experimental.dependencies.*
import kotlin.script.experimental.dependencies.maven.MavenDependenciesResolver
import kotlin.script.experimental.jvm.JvmDependency
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm

// These codes are from Kotlin's document
object ScriptConfiguration : ScriptCompilationConfiguration({
    // Implicit imports for all scripts of this type
    defaultImports(DependsOn::class, Repository::class)
    jvm {
        // Extract the whole classpath from context classloader and use it as dependencies
        dependenciesFromCurrentContext(wholeClasspath = true)
    }
    // Callbacks
    refineConfiguration {
        // Process specified annotations with the provided handler
        onAnnotations(DependsOn::class, Repository::class, handler = ::configureMavenDepsOnAnnotations)
    }
})

fun configureMavenDepsOnAnnotations(context: ScriptConfigurationRefinementContext): ResultWithDiagnostics<ScriptCompilationConfiguration> {
    val annotations = context.collectedData?.get(ScriptCollectedData.collectedAnnotations)?.takeIf { it.isNotEmpty() }
        ?: return context.compilationConfiguration.asSuccess()
    return runBlocking {
        resolver.resolveFromScriptSourceAnnotations(annotations)
    }.onSuccess {
        context.compilationConfiguration.with {
            dependencies.append(JvmDependency(it))
        }.asSuccess()
    }
}

private val resolver = CompoundDependenciesResolver(FileSystemDependenciesResolver(), MavenDependenciesResolver())

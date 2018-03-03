/*
 * Copyright (C) 2018 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

@file:Suppress("unused")

package com.prestongarno.kotlinq.compiler

import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.compile.AbstractCompile
import java.io.File

open class CompilerRunner : DefaultTask() {

  private val schemaDefs: Set<SchemaDefinition> by lazy {
    @Suppress("UNNECESSARY_SAFE_CALL") // you never fucking know with gradle
    project.project.extensions
        .getByType(KotlinqCompilerConfiguration::class.java)
        ?.schemaDefinitions
        ?: throw GradleException("Kotlinq gradle plugin is not configured")
  }

  @Input
  val schemaFiles: Set<File> = schemaDefs
      .map(SchemaDefinition::target)
      .map(::File)
      .toSet()

  init {
    schemaDefs.forEach {
      outputs.file(it.outputDir.asFile())
      inputs.file(it.target.asFile())
    }
  }

  @TaskAction
  fun compileGraphQl() {
    val results = schemaDefs.filter {
      it.target.asFile().let { file -> file.exists() && file.canRead() }.apply {
        if (!this) logger.warn("Unable to read a valid GraphQL schema at ${it.target}")
      }
    }.map { schemaDef ->

      var exception: Throwable? = null

      val file = schemaDef.target.asFile()

      val compiler = GraphQLCompiler(FileSchema(file.absolutePath)) {
        kotlinFileName = schemaDef.kotlinFileName
            .let { if (it.isEmpty()) "GraphQLKotlin.kt" else it }
        packageName = schemaDef.packageName
      }

      var result: String? = null

      try {
        compiler.compile()
        result = compiler.toKotlinApi()
      } catch (ex: Exception) {
        exception = exception?.let { IllegalArgumentException(it) } ?: ex
      }

      schemaDef.outputDir.asFile().apply {

        mkdirs()

        val ktFile = this.child(schemaDef.packageName.replace(".", "/").prepend("/")).apply {
          if (!exists() && !mkdirs()) {
            val msg = "Could not make compile output directory $path"
            exception = exception?.let { IllegalArgumentException(msg, it) } ?: GradleException(msg)
          }
        }.child(schemaDef.kotlinFileName)

        if (ktFile.exists() && !ktFile.canWrite()) {
          exception = GradleException("Can't write to ${ktFile.path}")
        }

        try {
          if (result != null) {
            ktFile.writeText(result)
          } else throw IllegalArgumentException("Result was empty")
        } catch (ex: Exception) {
          exception = exception?.let { IllegalArgumentException(it) } ?: ex
        }
      }
      return@map Pair(schemaDef, exception)
    }

    results.partition { it.second == null }.let { (successes, failures) ->
      successes.map { it.first }.forEach {
        logger.info("Successfully generated schema from " +
            "${it.target} to ${it.outputDir} as ${it.kotlinFileName}")
      }
      failures.takeIf { it.isNotEmpty() }?.apply {
        forEach { (schema, exception) ->
          logger.info("Failed to compile schema at ${schema.target}. Reason: <${exception?.message}>")
          val details = "Failure stacktrace for compilation of ${schema.target}:\n" +
              exception?.recursiveStacktrace()
          logger.debug(details)
          if (logger.isTraceEnabled) System.err.println(details)
        }
        // TODO should probably clean up the build dirs
        throw GradleException("Failed to compile the following schema(s):" +
            failures.joinToString(separator = "; ") { it.first.target })
      }
    }

    if (schemaDefs.isEmpty()) {
      logger.log(LogLevel.INFO, "No schema definitions specified. Skipping...")
    } else {
      project.tasks.filter {
        it.group?.contains("kotlin") == true
      }.forEach {
        (it as AbstractCompile).source(schemaDefs.map {
          it.outputDir
        }.toList())
      }
    }
  }

}

class SchemaDefinition(project: Project) {
  @JvmField var target: String = ""
  @JvmField var kotlinFileName = "GraphqlKotlinq.kt"
  @JvmField var packageName = "org.graphql"
  @JvmField var outputDir = project.buildDir.absolutePath + "generated/kotlinq"
}

open class KotlinqCompilerConfiguration(private var project: Project) {

  internal val schemaDefinitions: MutableSet<SchemaDefinition> = mutableSetOf()

  fun schema(action: Action<SchemaDefinition>) {
    val schemaDef = SchemaDefinition(project)
    action.execute(schemaDef)
    schemaDefinitions += schemaDef

    /*project.logger.log(LogLevel.INFO, */
    project.logger.log(LogLevel.INFO, "Working directory: ${File("./").absolutePath}")
    project.logger.log(LogLevel.INFO, "Registered GraphQL schema:" + schemaDef.run {
      "\n\ttarget: " + target +
          "\n\tkotlinFileName: " + kotlinFileName +
          "\n\tpackageName: " + packageName +
          "\n\toutputDir: " + outputDir
    })
  }

}

open class KotlinqPlugin : Plugin<Project> {

  override fun apply(target: Project?) = target?.run {
    target.logger.log(LogLevel.INFO, "Applying compiler plugin to project: '${target.name}'")
    target.extensions.create("kotlinq", KotlinqCompilerConfiguration::class.java, target)
    val compilerTask = target.tasks.create("compileGraphQL", CompilerRunner::class.java)
    target.tasks
        .filter { it.name.contains("test") }
        .forEach { it.dependsOn.add(compilerTask) }
  }.ignore()
}


@Suppress("unused") private fun Any?.ignore() = Unit
private fun String.asFile() = File(this)
private fun File.child(relativePath: String): File = ((this.path ?: "./") + relativePath.prepend("/")).asFile()
private fun StackTraceElement.toLoggingFormat(): String =
    "[$className].$methodName\t$fileName($lineNumber)"

private fun Throwable.recursiveStacktrace(indent: Int = 1): String {
  val indentSpace = "  ".repeat(indent)
  return stackTrace.joinToString(
      separator = "\n$indentSpace",
      prefix = "\nCaused by: $message\n$indentSpace",
      transform = StackTraceElement::toLoggingFormat
  ) + cause?.recursiveStacktrace(indent + 1)
}

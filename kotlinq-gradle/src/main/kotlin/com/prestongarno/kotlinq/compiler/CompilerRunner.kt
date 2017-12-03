/*
 * Copyright (C) 2017 Preston Garno
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

package com.prestongarno.kotlinq.compiler

import groovy.lang.Closure
import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.compile.AbstractCompile
import org.gradle.api.tasks.compile.JavaCompile
import java.io.File

open class CompilerRunner : DefaultTask() {

  val schemaDefs: Set<SchemaDefinition> by lazy { project.rootProject.extensions
      .getByType(KotlinqCompilerConfiguration::class.java)
      ?.schemaDefinitions
      ?: throw GradleException("Kotlinq gradle plugin is not configured") }

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
    schemaDefs.filter {
      it.target.asFile().let { file -> file.exists() && file.canRead() }
    }.onEach { schemaDef ->

      val file = schemaDef.target.asFile()

      val compiler = GraphQLCompiler(FileSchema(file.absolutePath)) {
        kotlinFileName = schemaDef.kotlinFileName
            .let { if (it.isEmpty()) "GraphQLKotlin.kt" else it }
        packageName = schemaDef.packageName
      }
      compiler.compile()
      val result = compiler.toKotlinApi()

      schemaDef.outputDir.asFile().apply {
        mkdirs()

        val ktFile = this.child(schemaDef.packageName.replace(".", "/").prepend("/")).apply {
          if (!exists() && !mkdirs())
            throw GradleException("Could not make compile output directory $path")
        }.child(schemaDef.kotlinFileName)

        if (ktFile.exists() && !ktFile.canWrite())
          throw GradleException("Can't write to ${ktFile.path}")

        ktFile.writeText(text = result)
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

open class KotlinqCompilerConfiguration(var project: Project) {

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

  override fun apply(target: Project?): Unit = target?.run {
    target.logger.log(LogLevel.INFO, "Applying compiler plugin to project: '${target.name}'")
    target.extensions.create("kotlinq", KotlinqCompilerConfiguration::class.java, target)
    val compilerTask = target.tasks.create("compileGraphQL", CompilerRunner::class.java)
  }.ignore()
}


private fun Any?.ignore(): Unit = Unit
private fun String.asFile() = File(this)
private fun File.child(relativePath: String): File = ((this.path ?: "./") + relativePath.prepend("/")).asFile()

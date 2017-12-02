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
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.compile.AbstractCompile
import org.gradle.api.tasks.compile.JavaCompile
import java.io.File

open class CompilerRunner : DefaultTask() {

  val schemaDefs: Set<SchemaDefinition> = extensions
      .getByType(KotlinqCompilerConfiguration::class.java)
      ?.schemaDefinitions
      ?: throw GradleException("Kotlinq gradle plugin is not configured")

  @Input
  val schemaFiles: Set<File> = schemaDefs
      .map(SchemaDefinition::file)
      .map(::File)
      .toSet()

  @TaskAction
  fun compileGraphQl() = schemaDefs.filter {
    it.file.asFile().let { file -> file.exists() && file.canRead() }
  }.onEach { schemaDef ->

    val file = schemaDef.file.asFile()

    val result = GraphQLCompiler(FileSchema(file.absolutePath)) {
      kotlinFileName = schemaDef.kotlinFileName
          .let { if (it.isEmpty()) "GraphQLKotlin.kt" else it }
      packageName = schemaDef.packageName
    }.apply(GraphQLCompiler::compile)
        .let(GraphQLCompiler::toKotlinApi)

    schemaDef.outputDir.asFile().apply {
      if (!exists() || !mkdirs())
        throw GradleException("Could not make compile output directory $path")

      val ktFile = this.child(schemaDef.packageName.replace(".", "/")).apply {
        if (!exists() || !mkdirs())
          throw GradleException("Could not make compile output directory $path")
      }.child(schemaDef.kotlinFileName)

      if (!ktFile.canWrite())
        throw GradleException("Can't write to ${ktFile.path}")

      ktFile.writeText(text = result)
    }

  }

}

class SchemaDefinition(project: Project) {
  @JvmField var file: String = ""
  @JvmField var kotlinFileName = ""
  @JvmField var packageName = ""
  @JvmField var outputDir = project.buildDir.absolutePath + "generated/kotlinq"
}

open class KotlinqCompilerConfiguration(var project: Project) {

  internal val schemaDefinitions: MutableSet<SchemaDefinition> = mutableSetOf()

  fun schema(closure: Closure<SchemaDefinition>) {
    val schemaDef = SchemaDefinition(project)
    closure.call(schemaDef)
    schemaDefinitions += schemaDef
  }

}

class KotlinqPlugin : Plugin<Project> {

  override fun apply(target: Project?): Unit = target?.run{

    extensions.add("kotlinq", KotlinqCompilerConfiguration(project))

    tasks.create("compileGraphQL") {
      val kotlinqCompile = KotlinqCompilerConfiguration(project)
      tasks.withType(AbstractCompile::class.java).forEach {
        it.setDependsOn(listOf(kotlinqCompile))
      }
    }
  }.ignore()
}


private fun Any?.ignore(): Unit = Unit
private fun String.asFile() = File(this)
private fun File.child(relativePath: String): File = ((this.path?:"./") + relativePath).asFile()

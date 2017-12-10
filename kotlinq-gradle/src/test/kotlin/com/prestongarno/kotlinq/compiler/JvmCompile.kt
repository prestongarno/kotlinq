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

import com.prestongarno.kotlinq.core.QSchemaType
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import java.io.File
import java.io.PrintStream
import java.net.URLClassLoader
import kotlin.reflect.KClass

object JvmCompile {

  @JvmOverloads
  fun exe(input: File, buildDir: File, printStream: PrintStream? = null): KtqCompileWrapper {
    K2JVMCompiler().run {
      val args = K2JVMCompilerArguments().apply {
        freeArgs = mutableListOf(input.path)
        destination = buildDir.absolutePath
        loadBuiltInsFromDependencies = true
        includeRuntime = false
        noOptimize = true
        classpath = System.getProperty("java.class.path")
            .split(System.getProperty("path.separator"))
            .filter {
              it.asFile().exists() && it.asFile().canRead()
            }.joinToString(":")
        noStdlib = true
        noReflect = true
        noJdk = true
        skipRuntimeVersionCheck = true
        jvmTarget = "1.8"
        reportPerf = true
      }
      execImpl(printStream?.let {
        PrintingMessageCollector(it, MessageRenderer.PLAIN_RELATIVE_PATHS, true)
      } ?: MessageCollector.NONE, Services.EMPTY, args)
    }.code.also { exitCode ->
      require(exitCode == 0)
    }
    return KtqCompileWrapper(buildDir)
  }

}

class KtqCompileWrapper(root: File) {

  val classLoader = URLClassLoader(
      listOf(root.toURI().toURL()).toTypedArray(),
      this::class.java.classLoader)

  fun loadClass(name: String, block: KClass<*>.() -> Unit = emptyBlock()) =
      (classLoader.loadClass(name)).kotlin.apply(block)

  @Suppress("UNCHECKED_CAST") fun loadObject(name: String): QSchemaType =
      (classLoader.loadClass(name).kotlin as KClass<QSchemaType>).objectInstance!!

  @Suppress("UNCHECKED_CAST") fun loadInterface(name: String): KClass<QSchemaType> =
      (classLoader.loadClass(name).kotlin as KClass<QSchemaType>)


}

fun String.asFile() = File(this)

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

/*package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.adapters.TypeListAdapter
import com.prestongarno.ktq.adapters.TypeStubAdapter
import com.prestongarno.ktq.compiler.asFile
import com.prestongarno.ktq.internal.ModelProvider
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import java.io.File
import java.net.URLClassLoader
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

object JvmCompile {

  fun exe(input: File, output: File): Boolean = K2JVMCompiler().run {
    val args = K2JVMCompilerArguments().apply {
      freeArgs = mutableListOf(input.absolutePath!!)
      loadBuiltInsFromDependencies = true
      destination = output.absolutePath
      classpath = System.getProperty("java.class.path")
          .split(System.getProperty("path.separator"))
          .filter {
            it.asFile().exists() && it.asFile().canRead()
          }.joinToString(":")
      noStdlib = true
      jvmTarget = "1.8"
      noReflect = true
      skipRuntimeVersionCheck = true
      reportPerf = true
    }
    output.deleteOnExit()
    execImpl(
        PrintingMessageCollector(
            java.io.PrintStream(File("/dev/null")),
            MessageRenderer.WITHOUT_PATHS, true),
        Services.EMPTY,
        args)
  }.code == 0
}

fun File.getFileTree(): List<File> {
  return this.walkTopDown().asSequence().distinct()
      .filter { it.isFile }
      .toList()
}

class KtqCompileWrapper(private val root: File) {

  val loader = URLClassLoader(
      listOf(root.toURI().toURL()).toTypedArray(),
      this::class.java.classLoader)

  @Suppress("UNCHECKED_CAST") fun loadObject(name: String): QSchemaType =
      (loader.loadClass(name).kotlin as KClass<QSchemaType>).objectInstance!!

  @Suppress("UNCHECKED_CAST") fun loadInterface(name: String): KClass<QSchemaType> =
      (loader.loadClass(name).kotlin as KClass<QSchemaType>)
}

internalfun QModel<*>.allGraphQl(): String {
  model::class.declaredMemberProperties.map { it.call(model) as FieldConfig }
      .map { it.toAdapter() }
      .forEach { if (!fields.contains(it)) fields.add(it) }
  return toGraphql()
}

internalfun QModel<*>.mockGraphql(selectedFields: List<String>) =
    model::class.declaredMemberProperties
        .filter { selectedFields.contains(it.name) }
        .map { fields.add(it.call(model) as Adapter<*>) }
        .let { toGraphql() }

internalfun QModel<*>.mockInputGraphql(name: String, map: Map<String, Any>): QModel<*> = this.apply {
  model::class.declaredMemberProperties.find { it.name == name }!!
      .call(model)
      .also {
        (it as FieldConfig).args.putAll(map)
        this.fields.add(it.toAdapter())
      }
}

internalfun String.compactGraphql(): String = trimMargin("|")
    .replace("[\\s\\n]*".toRegex(), "")
    .replace("...on", "... on ")

internalfun QModel<*>.setDelegateProvidingValue(name: String, value: () -> QModel<*>) =
    model::class.declaredMemberProperties.find { it.name == name }!!.call(model)
        .also { (it as ModelProvider).setValue(value.invoke()); this.fields.add((it as FieldConfig).toAdapter()) }

internalfun ModelProvider.setValue(value: QModel<*>) {
  when (this) {
    is TypeStubAdapter<*, *, *> -> this.build { value }
    is TypeListAdapter<*, *, *> -> this.build { value }
    else -> throw IllegalStateException("Bad type")
  }
}*/

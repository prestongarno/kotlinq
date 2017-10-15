package com.prestongarno.ktq

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

internal fun QModel<*>.allGraphQl(): String {
  model::class.declaredMemberProperties.map { it.call(model) as Adapter }
      .forEach { if (!fields.contains(it)) fields.add(it) }
  return toGraphql()
}

internal fun QModel<*>.mockGraphql(selectedFields: List<String>) =
    model::class.declaredMemberProperties
        .filter { selectedFields.contains(it.name) }
        .map { fields.add(it.call(model) as Adapter) }
        .let { toGraphql() }

internal fun QModel<*>.mockInputGraphql(name: String, map: Map<String, Any>): QModel<*> = this.apply {
  model::class.declaredMemberProperties.find { it.name == name }!!
      .call(model)
      .also {
        ((it as Adapter).args as MutableMap<String, Any>).putAll(map)
        this.fields.add(it)
      }
}


internal fun QModel<*>.setDelegateProvidingValue(name: String, value: () -> QModel<*>) =
    model::class.declaredMemberProperties.find { it.name == name }!!.call(model)
        .also { (it as ModelProvider).setValue(value.invoke()); this.fields.add(it as Adapter) }

internal fun ModelProvider.setValue(value: QModel<*>) {
  when (this) {
    is TypeStubAdapter<*, *, *> -> this.build { value }
    is TypeListAdapter<*, *, *> -> this.build { value }
    else -> throw IllegalStateException("Bad type")
  }
}

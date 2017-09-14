package com.prestongarno.ktq

import com.prestongarno.ktq.compiler.asFile
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import java.io.File
import java.net.URLClassLoader
import kotlin.reflect.KClass

object JvmCompile {

  lateinit var defaultCompArgs: Map<String, String>

  init {
    defaultCompArgs = mutableMapOf<String, String>(
        Pair("", "-no-stdlib"),
        Pair("", "-no-reflect"),
        Pair("-Xskip-runtime-version-check=true", ""),
        Pair("-classpath", System.getProperty("java.class.path")
            .split(System.getProperty("path.separator"))
            .filter {
              it.asFile().exists() && it.asFile().canRead()
            }.joinToString(":"))
    )
  }

  fun exe(input: File, output: File): Boolean {
    return K2JVMCompiler().run {
      val args = K2JVMCompilerArguments().apply {
        this.freeArgs = listOf(input.absolutePath)
        loadBuiltInsFromDependencies = true
        destination = output.absolutePath
        classpath = defaultCompArgs["-classpath"]!!
        reportPerf = true
      }
      output.deleteOnExit()
      this.execImpl(PrintingMessageCollector(System.out,
          MessageRenderer.WITHOUT_PATHS, true),
          Services.EMPTY,
          args)
    }.code == 0
  }

  fun load(output: File): ClassLoader = URLClassLoader(
      listOf(output.toURI().toURL()).toTypedArray(),
      this::class.java.classLoader)
}

fun File.getFileTree(): List<File> {
  return this.walkTopDown().asSequence().distinct()
      .filter { it.isFile }
      .toList()
}

class KtqCompileWrapper(private val root: File) {

  private val loader = JvmCompile.load(root)

  @Suppress("UNCHECKED_CAST") fun loadObject(name: String): QSchemaType =
      (loader.loadClass(name).kotlin as KClass<QSchemaType>).objectInstance!!
}
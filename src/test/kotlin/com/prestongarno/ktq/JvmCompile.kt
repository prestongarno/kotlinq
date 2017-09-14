package com.prestongarno.ktq

import com.prestongarno.ktq.compiler.asFile
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import org.jetbrains.kotlin.preprocessor.mkdirsOrFail
import java.io.File

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

  fun exe(input: File, output: File) : File {
    require(K2JVMCompiler().run {
      val args = K2JVMCompilerArguments().apply {
        this.freeArgs = listOf(input.absolutePath)
        loadBuiltInsFromDependencies = true
        destination = output.absolutePath
        classpath = defaultCompArgs["-classpath"]!!
        reportPerf = true
      }
      this.execImpl(PrintingMessageCollector(System.out,
          MessageRenderer.WITHOUT_PATHS, true),
          Services.EMPTY,
          args)
    }.code == 0) { "Compilation failed for integration test" }
    return output
  }
}
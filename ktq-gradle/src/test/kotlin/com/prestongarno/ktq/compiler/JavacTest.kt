package com.prestongarno.ktq.compiler

import com.google.common.io.Files
import java.io.File
import java.io.PrintStream

open class JavacTest {

  protected fun jvmCompileAndLoad(
      schema: String,
      packageName: String = "",
      printer: PrintStream? = null,
      block: GraphQLCompiler.() -> Unit = { }
  ): KtqCompileWrapper {

    val tempDir = Files.createTempDir()

    val kotlinOut = File.createTempFile(
        "KotlinpoetOutGraphQL", ".kt"
    ).apply { deleteOnExit() }

    val compilation = GraphQLCompiler(schema = StringSchema(schema)) {

      this@GraphQLCompiler.packageName = packageName

      kotlinFileName = kotlinOut.name

    }.apply(GraphQLCompiler::compile)
        .apply(block)

    kotlinOut.writeText(compilation.toKotlinApi())

    return JvmCompile.exe(kotlinOut, tempDir, printer)
  }
}

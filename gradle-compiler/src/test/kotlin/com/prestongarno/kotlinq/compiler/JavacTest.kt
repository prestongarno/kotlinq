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

package com.prestongarno.kotlinq.compiler

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

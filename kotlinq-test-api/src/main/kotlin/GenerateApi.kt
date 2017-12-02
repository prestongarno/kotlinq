import com.prestongarno.kotlinq.compiler.FileSchema
import com.prestongarno.kotlinq.compiler.GraphQLCompiler
import java.io.File
import java.util.logging.Level

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

class GenerateApi {
  companion object {
    val logger = java.util.logging.Logger.getGlobal()

    @JvmStatic fun main(args: Array<String>) {
      val destination = args.firstOrNull()?.let { File(it) }?.apply {
        mkdirs()
      } ?: throw IllegalArgumentException("No output destination specified!")

      File("./").walkTopDown().filter {
        it.isFile && it.name.endsWith("graphqls")
      }.distinctBy { it.nameWithoutExtension }.forEach { file ->
        logger.log(Level.INFO, "Compiling schema at: $file")
        GraphQLCompiler(FileSchema(file.absolutePath)) {
          this.packageName = "com.prestongarno.kotlinq.generated." + file.name.split(".").firstOrNull()!!
          this.kotlinFileName = file.nameWithoutExtension + ".kt"
        }.apply(GraphQLCompiler::compile)
            .let(GraphQLCompiler::toKotlinApi)
            .let { result ->
              File((destination.absolutePath!! + "/" + file.nameWithoutExtension + ".kt")).apply {
                logger.log(Level.INFO, "Writing result to: $this")
                writeText(result)
              }
            }
      }
    }
  }
}

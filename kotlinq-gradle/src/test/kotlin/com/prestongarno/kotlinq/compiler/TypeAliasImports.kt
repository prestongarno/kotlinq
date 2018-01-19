package com.prestongarno.kotlinq.compiler

import org.junit.Test
import java.io.File

class TypeAliasImports {

  @Test fun importsAreGenerated() {

    File("/home/preston/IdeaProjects/kotlinq/kotlinq-test-api/src/main/resources/starwars.graphqls")
        .reader()
        .readLines()
        .joinToString("\n") { it }.let { schema ->

      println(generateSequence { "=".repeat(30) }.take(10).joinToString(separator = "\n"))
      GraphQLCompiler(StringSchema(schema))
          .apply(GraphQLCompiler::compile)
          .toKotlinApi()
          .let(::println)

    }
  }
}
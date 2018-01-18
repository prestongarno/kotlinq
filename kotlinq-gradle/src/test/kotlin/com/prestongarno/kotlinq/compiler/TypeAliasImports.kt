package com.prestongarno.kotlinq.compiler

import com.prestongarno.kotlinq.org.antlr4.definitions.GraphQLSchemaParser
import org.junit.Test

class TypeAliasImports {

  @Test fun importsAreGenerated() {

    this::class.java
        .classLoader
        .getResourceAsStream("yelp.graphqls")
        .reader()
        .readLines()
        .joinToString("\n") { it }.let { schema ->

      GraphQLCompiler(StringSchema(schema))
          .apply(GraphQLCompiler::compile)
          .toKotlinApi()
          //.let(::println)

    }
  }
}
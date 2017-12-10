package com.prestongarno.kotlinq.compiler

import com.prestongarno.kotlinq.core.org.antlr4.base.GraphQLBaseSchema
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.junit.Test


class KeywordsAsSymbols {

  @Test fun `single line schema with keywords as symbols compiles successfully`() {


    // Get schema
    val schema = "\n      scalar MyCustomScalar " +
        "type Foo implements BazBar, Baz { foo: String! bar: String! input: Int string: Foo type: Int d: Int } " +
        "type Input { enum: Int } " +
        "scalar FOO interface BazBar { d: Int } " +
        "union SampletypeUnion = Foo | Input " +
        "interface Baz { foo: String! }\n    "
    val input: CharStream = CharStreams.fromString(schema.trimIndent())!!

    val lexer = GraphQLBaseSchema(input)
    lexer.modeNames.toList().println()

    lexer.tokenTypeMap.entries.sortedBy { it.value }.forEach { println("${it.key}(${it.value}),") }

    lexer.allTokens.forEachIndexed { index, token ->
      println("T=${token.type} | '${token.text}'")
    }
    GraphQLCompiler(StringSchema(schema)).toKotlinApi()
  }
}
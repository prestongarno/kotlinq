package com.prestongarno.kotlinq.compiler

import com.prestongarno.kotlinq.core.org.antlr4.base.GraphQLBaseSchema
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Test


class KeywordsAsSymbols {

  @Test fun iLoveAntlr() {


    // Get schema
    val input: CharStream = CharStreams.fromString("""
      type Foo {
        foo: String!

        bar: String!

      }

      union Baz = Foo|Baz

      interface BazBar {
        d: Int
      }
    """.trimIndent())

    val lexer = GraphQLBaseSchema(input)
    lexer.ruleNames.forEachIndexed{index, s -> println("$index: $s") }
    lexer.allTokens.forEachIndexed { index, token ->
      println("Rule(${token.type})[$index]='${token.text}'")
    }
  }
}
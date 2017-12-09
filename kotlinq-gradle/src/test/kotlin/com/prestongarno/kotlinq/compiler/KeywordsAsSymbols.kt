package com.prestongarno.kotlinq.compiler

import com.prestongarno.kotlinq.core.org.antlr4.base.GraphQLBaseSchema
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.junit.Test
import java.io.File


class KeywordsAsSymbols {

  @Test fun iLoveAntlr() {


    // Get schema
    val input: CharStream = CharStreams.fromString("""

      scalar MyCustomScalar
      type Foo implements BazBar, Baz {
        foo: String!

        bar: String!
        #equpur8jf;lasmn;vlkjtype interface baz bar

      }

      scalar FOOMOO union Baz = input|type|interface interface BazBar {
        d: Int @FOO("B\"AR")
      }
      #equpur8jf;lasmn;vlkjtype interface baz bar
      type StarWarsQuery implements Boo Baz      , Buzz ,     Aldrin {
        foo: String! bar: String!
      }input Barz
    """.trimIndent())!!

    val lexer = GraphQLBaseSchema(input)
    lexer.modeNames.toList().println()

    lexer.tokenTypeMap.entries.sortedBy { it.value }.forEach { println("${it.key}(${it.value}),") }

    lexer.allTokens.forEachIndexed { index, token ->
      println("T=${token.type} | '${token.text}'")
    }
  }
}
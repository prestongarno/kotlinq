package com.prestongarno.kotlinq.compiler

import com.prestongarno.kotlinq.core.org.antlr4.base.GraphQLBaseSchema
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.Token
import java.io.File


class GraphQLsLexer(val schema: Schema) {

  fun parse(): Set<SchemaType> {

    val str = when (schema) {
      is StringSchema -> schema.source
      is FileSchema -> File(schema.path).let {
        require(it.exists() && it.isFile() && it.canRead()) {
          "Cannot read schema: '${it.path}'"
        }
        it.readText()
      }
    }
    val lexer = GraphQLBaseSchema(CharStreams.fromString(str))

    val ordered = lexer.allTokens.iterator()

    if (!ordered.hasNext()) throw IllegalArgumentException("Empty schema")

    var current = ordered.next()

    while (ordered.hasNext()) {
      // make sure it's one of the 6 types allowed by gql
      if (Rule.match(current) != Rule.TYPE_LIT || GraphQLType.match(current) == null) {
        throw err(current, " Expected one of { type, input, enum, interface, scalar, union }")
      }

    }
    TODO()
  }

  private fun err(token: Token, message: String = ""): IllegalArgumentException {
    return IllegalArgumentException(
        "Unexpected input '${token.text}' at ${token.humanReadableSourceCoordinates()}: $message")
  }
}

enum class GraphQLType {
  TYPE,
  ENUM,
  INPUT,
  SCALAR,
  INTERFACE,
  UNION;

  companion object {
    fun match(token: Token): GraphQLType? {
      return GraphQLType.values().find { it.name.toLowerCase() == token.text }
    }
  }
}

enum class Rule(number: Int) {
  EOF(-1),
  LCURLY(1),
  WS(2),
  NAME(3),
  TYPE_DEC(4),
  COMMENT(5),
  COLON(6),
  WORD(7),
  BLOCK(8),
  NEWLINE(9),
  TYPE_LIT(10),
  RCURLY(11),
  OTHER(12),
  UNION_WS(13),
  EQ(14),
  WS_UNION(15),
  WS_SCALAR(16),
  WS_TYPE(17),
  IMPL_(18),
  IMPLEMENTS(18),
  EXIT_TYPE(19),
  WS_TYPE_CTX(20),
  COMMA(21),
  BREAK(22),
  STR(23);

  companion object {

    fun match(token: Token): Rule {
      return Rule.values().find { it.ordinal == token.type }
          ?: throw IllegalStateException(
          "unexpected input ${token.text} at ${token.humanReadableSourceCoordinates()}"
      )
    }
  }
}

fun Token.humanReadableSourceCoordinates(): String {
  return "[$line, $charPositionInLine]"
}


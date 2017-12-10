package com.prestongarno.kotlinq.compiler

import com.prestongarno.kotlinq.core.org.antlr4.base.GraphQLBaseSchema
import com.prestongarno.kotlinq.org.antlr4.definitions.GraphQLSchemaLexer
import com.prestongarno.kotlinq.org.antlr4.definitions.GraphQLSchemaParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Token
import java.io.File


class GraphQLsLexer(val schema: Schema) {

  fun parse(): Set<SchemaType> {

    val str = when (schema) {
      is StringSchema -> schema.source
      is FileSchema -> File(schema.path).let {
        require(it.exists() && it.isFile && it.canRead()) {
          "Cannot read schema: '${it.path}'"
        }
        it.readText()
      }
    }
    val lexer = GraphQLBaseSchema(CharStreams.fromString(str))

    val ordered = lexer.allTokens.iterator()

    if (!ordered.hasNext()) throw IllegalArgumentException("Empty schema")

    val definitions = mutableSetOf<TypeTokenSet>()

    var typeToken = ordered.next()

    while (Rule.match(typeToken) != Rule.EOF && ordered.hasNext()) {
      val currentType = GraphQLType.match(typeToken)
      // make sure it's one of the 6 types allowed by gql
      if (Rule.match(typeToken) != Rule.TYPE_LIT || currentType == null) {
        throw err(typeToken, " Expected one of { type, input, enum, interface, scalar, union }")
      }

      val name = try {
        matchName(ordered.next())
      } catch (ex: NoSuchElementException) {
        throw err(typeToken, "No name for ${currentType.name.toLowerCase()} type at " +
            typeToken.humanReadableSourceCoordinates())
      }

      val superinterfaces = mutableListOf<Token>()

      val body = mutableListOf<Token>()

      // short curcuit on scalar declaration
      if (currentType == GraphQLType.SCALAR) {
        definitions += TypeTokenSet(currentType, name, "")
        if (ordered.hasNext()) {
          typeToken = ordered.next()
          continue
        } else break
      }

      var next: Token = try {
        ordered.next()
      } catch (ex: NoSuchElementException) {
        throw err(typeToken, "No definition for ${currentType.name.toLowerCase()} type $name")
      }

      if (GraphQLType.match(typeToken) == GraphQLType.TYPE) {

        if (Rule.match(next).let { it != Rule.LCURLY && it != Rule.NAME })
          throw err(next, "Expected a valid superinterface name or '{'")

        while (Rule.match(next) == Rule.NAME) {
          superinterfaces.add(next)
          next = ordered.next()
        }
      }
      if (currentType == GraphQLType.UNION) {
        if (Rule.match(next) != Rule.BLOCK)
          throw err(next, "Expected a set of type names separated by '|'")
        definitions += TypeTokenSet(currentType, name, next.text)
        next = ordered.next()
      } else { // default block body
        if (Rule.match(next) != Rule.LCURLY)
          throw err(next, "Expected '{'")
        while (Rule.match(next) != Rule.RCURLY) {
          next = ordered.next()
          body.add(next)
        }
        definitions += TypeTokenSet(
            currentType,
            name,
            body.joinToString(separator = "") { it.text },
            superinterfaces.map { it.text }
        )
        if (ordered.hasNext()) next = ordered.next()
      }
      typeToken = next
    }

    return definitions.map {
      it.type.createType(it)
    }.toSet()
  }

  class TypeTokenSet(
      val type: GraphQLType,
      val name: String,
      val body: String,
      val superInterfaces: List<String> = emptyList()
  )

  private fun matchName(token: Token): String {
    if (Rule.match(token) != Rule.TYPE_DEC) {
      throw err(token, "Expected a name, but got ${token.text}")
    }
    return token.text
  }

  private fun err(token: Token, message: String = ""): IllegalArgumentException {
    return IllegalArgumentException(
        "Unexpected input '${token.text}' at ${token.humanReadableSourceCoordinates()}: $message")
  }
}

enum class GraphQLType {
  TYPE {
    override fun createType(tokens: GraphQLsLexer.TypeTokenSet): SchemaType =
        TypeDef(tokens.name, tokens.superInterfaces, fieldDefinitionsFromBody(tokens.body))
  },
  ENUM {
    override fun createType(tokens: GraphQLsLexer.TypeTokenSet): SchemaType =
        EnumDef(tokens.name, enumFromBody(tokens.body))
  },
  INPUT {
    override fun createType(tokens: GraphQLsLexer.TypeTokenSet): SchemaType =
        InputDef(tokens.name, fieldDefinitionsFromBody(tokens.body))
  },
  SCALAR {
    override fun createType(tokens: GraphQLsLexer.TypeTokenSet): SchemaType = ScalarDef(tokens.name)
  },
  INTERFACE {
    override fun createType(tokens: GraphQLsLexer.TypeTokenSet): SchemaType =
        InterfaceDef(tokens.name, fieldDefinitionsFromBody(tokens.body))
  },
  UNION {
    override fun createType(tokens: GraphQLsLexer.TypeTokenSet): SchemaType =
        UnionDef(tokens.name, tokens.body.split("\\s*\\|\\s*".toRegex()).filter(String::isNotEmpty))
  };

  abstract fun createType(tokens: GraphQLsLexer.TypeTokenSet): SchemaType

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

fun fieldDefinitionsFromBody(body: String): Set<FieldDefinition> =
    GraphQLSchemaParser(CommonTokenStream(GraphQLSchemaLexer(CharStreams.fromString(body))))
        .blockDef()
        .fieldDef()
        .map(::FieldDefinition)
        .toSet()

fun enumFromBody(body: String): List<String> =
    GraphQLSchemaParser(CommonTokenStream(GraphQLSchemaLexer(CharStreams.fromString(body))))
        .enumDef()
        .scalarName()
        .map { it.Name().text }

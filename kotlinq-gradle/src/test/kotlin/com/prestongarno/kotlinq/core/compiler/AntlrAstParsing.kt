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

package com.prestongarno.kotlinq.core.compiler

import com.prestongarno.kotlinq.core.org.antlr4.gen.GraphQLSchemaLexer
import com.prestongarno.kotlinq.core.org.antlr4.gen.GraphQLSchemaParser
import org.antlr.v4.runtime.CharStreams
import org.junit.Test
import org.antlr.v4.runtime.CommonTokenStream

class AntlrAstParsing {

  @Test fun simpleImplements() {
    val input = CharStreams.fromString("""

      type RandomMutation {
        value: Int
      }

      interface Foo {
        name: String
      }

      union BarOrBaz = Bar | Baz

      type Bar implements Foo {
        name: String
      }

      scalar Hello

      enum FancyBoolean {
        TRUE,
        FALSE
      }

      input BazBar {
        value: Hello
      }

    """.trimIndent())

    val lexer = GraphQLSchemaLexer(input)
    val stream = CommonTokenStream(lexer)
    val parser = GraphQLSchemaParser(stream)
    val result = parser.graphqlSchema()

    result.typeDef().size eq 2
    result.scalarDef().size eq 1
    result.unionDef().size eq 1
    result.enumDef().size eq 1
    result.interfaceDef().size eq 1
    result.inputTypeDef().size eq 1

    result.unionDef().first().apply {
      typeName().value() eq "BarOrBaz"
      unionTypes().children.forEachIndexed { index, tree ->
        when (index) {
          0 -> tree.text eq "Bar"
          1 -> tree.text eq "|"
          2 -> tree.text eq "Baz"
        }
      }
    }

    result.inputTypeDef().first().apply {
      typeName().value() eq "BazBar"
      fieldDef().size eq 1
      fieldDef(0).apply {
        typeSpec().typeName().value() eq "Hello"
        fieldName().Name().text eq "value"
      }
    }

    result.interfaceDef().first().apply {
      typeName().value() eq "Foo"
      fieldDef().size eq 1
      fieldDef().first().apply {
        fieldName().Name().text eq "name"
        fieldArgs() eq null
        typeSpec().typeName().Name().text eq "String"
      }
    }

    result.typeDef(0).apply {
      typeName().value() eq "RandomMutation"
      fieldDef(0).apply {
        fieldName().Name().text eq "value"
        typeSpec().typeName().value() eq "Int"
        typeSpec().nullable() eq null
      }
    }

    result.typeDef(1).apply {
      typeName().value() eq "Bar"
      implementationDefs().children[1].text eq "Foo"
      fieldDef(0).apply {
        fieldName().Name().text eq "name"
        typeSpec().typeName().value() eq "String"
        typeSpec().nullable() eq null
      }
    }

    result.scalarDef(0).apply {
      typeName().value() eq "Hello"
    }
  }
}

fun GraphQLSchemaParser.TypeNameContext.value() = this.Name().text


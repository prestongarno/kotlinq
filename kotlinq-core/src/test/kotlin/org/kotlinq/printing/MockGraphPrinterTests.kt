package org.kotlinq.printing

import org.junit.Before
import org.junit.Test
import org.kotlinq.SpreadOperator.`,,,`
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.services.Configuration
import org.kotlinq.createGraph
import org.kotlinq.eq
import org.kotlinq.printer.VisitingPrinter


class MockGraphPrinterTests {

  @Before fun injectPrinter() {
    Configuration.configure {
      printer = VisitingPrinterFormatter
    }
  }

  object VisitingPrinterFormatter : GraphQlFormatter {

    override fun printGraphQl(
        instance: GraphQlInstance,
        pretty: Boolean,
        inlineFragments: Boolean): String =
        VisitingPrinter(instance).toString()
  }


  @Test fun simpleScalarQueryTest() {

    val context = createGraph {
      scalar("version")
    }.graphQlInstance

    "{version}" eq context.toGraphQl()
  }


  @Test fun nestedContextTest() {

    val graph = createGraph {

      "query" ofType "Query" definedAs {
        scalar("hello")

        "nested" ofType "Def" definedAs {
          arguments = mapOf(
              "limitTo" to 100,
              "first" to 10)
          scalar("world")
        }

      }
    }.graphQlInstance

    "{query{hello,nested(limitTo: 100,first: 10){world}}}" eq graph.toGraphQl()
  }

  @Test fun fragmentPropertyDefinitionTest() {

    val graph = createGraph {
      "query" ofType "Query" spread {

        arguments = mapOf("first" to 100)
        isNullable = false

        `,,,` on "Type1" def {
          scalar("hello")
        }

        `,,,` on "Type2" def {
          scalar("world")
        }

      }
    }.graphQlInstance

    "{query(first: 100){... on Type1{hello}... on Type2{world}}}" eq graph.toGraphQl()
  }


  @Test fun fragmentPropertyDefinitionTestWithNestedTypes() {

    val graph = createGraph {
      "query" ofType "Query" spread {

        `,,,` on "Type1" def {

          scalar("hello")

          "nestedType1" ofType "Def" definedAs {
            scalar("name")
            scalar("id")
          }

        }

        `,,,` on "Type2" def {

          scalar("world")

          "nestedType2" ofType "Def" definedAs {
            scalar("name")
            scalar("loginId")
            scalar("id")
          }
        }
      }
    }.graphQlInstance

    graph.toGraphQl() eq
        "{query{... on Type1{hello,nestedType1{name,id}}... on Type2{world,nestedType2{name,loginId,id}}}}"
  }
}


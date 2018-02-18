@file:Suppress("unused")

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.api.Context
import org.kotlinq.dsl.extensions.float
import org.kotlinq.dsl.extensions.integer
import org.kotlinq.dsl.extensions.string
import org.kotlinq.dsl.toGraphQl


fun greet(worldName: String = "Earth", message: Any = "Hello"): Context =

    query {
      "greet"("name" to worldName, "message" to message) def {
        !"population"::integer
        "countries"("first" to 100) def {
          -"name"::string
          !"coordinates" on coordinates()
          !"subEntities"..{
            on("State") {
              "mayor"() def {
                !"name"::string
              }
            }
            on("City") {
              !"name"::string
            }

          }
        }
      }
    }


fun coordinates(): TypeDefinition =
    typeDefinition("Coordinate") {
      !"xValue"::float
      !"yValue"::float
    }

enum class Measurement {
  MILES,
  KILOMETERS
}

class Scratch {

  @Test fun `simple primitive field dsl coordinate type prints correctly`() {
    assertThat(coordinates().invoke().toGraphQl(pretty = true, inlineFragments = false))
        .isEqualTo("""
          {
            xValue
            yValue
          }
        """.trimIndent())
  }

  @Test fun queryGraph() {
    println(greet().toGraphQl(pretty = true, inlineFragments = false))
    println(coordinates().invoke().toGraphQl(pretty = true, inlineFragments = false))
  }

  /**
   * Test taken from query at https://edgecoders.com/restful-apis-vs-graphql-apis-by-example-51cb3d64809a
   * ```
   *     {
   *       person(ID: ...) {
   *         name
   *         birthYear,
   *         planet {
   *           name
   *         }
   *         films {
   *           title
   *         }
   *     }
   * ```
   *
   */
  @Test fun simpleStarWars() {

    val expect = """
      |{
      |  search {
      |    ... on Human {
      |      name
      |      mass
      |      friendsConnection {
      |        totalCount
      |        friends {
      |          ... on Human {
      |            name
      |            id
      |          }
      |        }
      |      }
      |    }
      |  }
      |}
      """.trimMargin("|")

    val starWarsQuery = query {
      "search"("text" to "r2d2") .. {
        on("Human") {
          !"name"::string
          !"id"::string
          "friendsConnection"("first" to 10) def {
            !"totalCount"::integer
            "friends"() .. {
              on("Human") {
                !"name"::string
                !"id"::string
              }
            }
          }
        }
      }
    }

    println(starWarsQuery.toGraphQl(pretty = true, inlineFragments = false))
  }

}


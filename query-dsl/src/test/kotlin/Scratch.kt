@file:Suppress("unused")

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.api.Definition
import org.kotlinq.dsl.TypeDefinition
import org.kotlinq.dsl.extensions.float
import org.kotlinq.dsl.extensions.integer
import org.kotlinq.dsl.extensions.string
import org.kotlinq.dsl.toGraphQl


fun greet(worldName: String = "Earth", message: Any = "Hello"): Definition =

    query {
      !"greet"("name" to worldName, "message" to message) on "Response" {
        !"population"::integer
        "countries"("first" to 100) on "Country" {
          !"name"::string
          !"coordinates" on coordinates()
          !"subEntities"..{
            on("State") {
              "mayor"() on "Persion" {
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
    defineType("Coordinate") {
      !"xValue"::float
      !"yValue"::float
    }

enum class Measurement {
  MILES,
  KILOMETERS
}

class Scratch {

  @Test fun `simple primitive field dsl coordinate type prints correctly`() {
    assertThat(coordinates().definition().toGraphQl(pretty = true, inlineFragments = false))
        .isEqualTo("""
          {
            xValue
            yValue
          }
        """.trimIndent())
  }

  @Test fun queryGraph() {
    println(greet().toGraphQl(pretty = true, inlineFragments = false))
    println(coordinates().definition().toGraphQl(pretty = true, inlineFragments = false))
  }

  @Test fun simpleStarWars() {

    val expect = """
      |{
      |  search(text: "r2d2") {
      |    __typename
      |    ... on Human{
      |      name
      |      id
      |      friendsConnection(first: 10) {
      |        totalCount
      |        friends {
      |          __typename
      |          ... on Human{
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
          "friendsConnection"("first" to 10) on "FriendConnection" {
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
    }.toGraphQl(pretty = true,
        inlineFragments = false)

    assertThat(starWarsQuery)
        .isEqualTo(expect)
    println(starWarsQuery)
  }

}


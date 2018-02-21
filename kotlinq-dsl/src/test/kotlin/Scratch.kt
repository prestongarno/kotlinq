@file:Suppress("unused")

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.api.Fragment
import org.kotlinq.api.Kind
import org.kotlinq.api.PropertyInfo
import org.kotlinq.dsl.fragment
import org.kotlinq.dsl.query
import org.kotlinq.dsl.toGraphQl


fun greet(worldName: String = "Earth", message: Any = "Hello") =
    query {
      !"greet"("name" to worldName, "message" to message) on def("Response") {
        "population"(integer)
        "countries"("first" to 100) on def("Country") {
          "name"(string)
          !"coordinates" on coordinates()
          !"subEntities"..{
            on("State") {
              "mayor"() on def("Person") {
                "name"(string)
              }
            }
            on("City") {
              "name"(string)
            }
          }
        }
      }
    }

fun coordinates() = fragment("Coordinate") {
  "xValue"(float)
  "yValue"(float)
}

enum class Measurement {
  MILES,
  KILOMETERS
}

class Scratch {

  @Test fun `simple primitive field dsl coordinate type prints correctly`() {
    assertThat(coordinates().toGraphQl(pretty = true))
        .isEqualTo("""
          {
            xValue
            yValue
          }
        """.trimIndent())
    assertThat(coordinates().toGraphQl(pretty = false))
        .isEqualTo("{__typename,xValue,yValue}")
  }

  @Test fun simpleStarWars() {

    val expect = """
      |{
      |  search(text: "han solo") {
      |    __typename
      |    ... on Human {
      |      name
      |      id
      |      height(unit: "METER")
      |      friendsConnection(first: 10) {
      |        totalCount
      |        friends {
      |          __typename
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
      "search"("text" to "han solo")..{
        on("Human") {
          "name"(string)
          "id"(string)
          "height"(!float, "unit" to "METER")
          "friendsConnection"("first" to 10) on def("FriendConnection") {
            "totalCount"(integer)
            "friends"..{
              on("Human") {
                "name"(!string)
                "id"(string)
              }
            }
          }
        }
      }
    }.toGraphQl(pretty = true)

    assertThat(starWarsQuery)
        .isEqualTo(expect)
  }

  @Test fun listStarWarsScratch() {

    fun humanDef() = fragment("Human") {
      "name"(string)
      "nicknames" listOf !string
    }

    fun robotDef() = fragment("Robot") {
      "modelNumber"(string)
      "maker" on humanDef()
    }

    fun charactersQuery(fragments: List<Fragment>) = query {
      "characters"("first" to 100)..listOf {
        on..fragments
      }
    }

    humanDef().graphQlInstance.properties.let { props ->
      assertThat(props["nicknames"]?.propertyInfo)
          .isEqualTo(
              PropertyInfo.named("nicknames")
                  .arguments(emptyMap())
                  .typeKind(Kind.string
                      .asNullable()
                      .asList())
                  .build())
      assertThat(props["name"]?.propertyInfo)
          .isEqualTo(PropertyInfo.named("name")
              .typeKind(Kind.string)
              .build())
    }


    assertThat(charactersQuery(listOf(humanDef())).toGraphQl())
        .isEqualTo("""
          |{
          |  characters(first: 100) {
          |    __typename
          |    ... on Human {
          |      name
          |      nicknames
          |    }
          |  }
          |}
          """.trimMargin("|"))

    assertThat(charactersQuery(listOf(humanDef())).toGraphQl(pretty = false))
        .isEqualTo(
            "{__typename,characters(first: 100){... on Human{__typename,name,nicknames}}}")

    assertThat(charactersQuery(listOf(humanDef(), robotDef())).toGraphQl())
        .isEqualTo("""
          |{
          |  characters(first: 100) {
          |    __typename
          |    ... on Human {
          |      name
          |      nicknames
          |    }
          |    ... on Robot {
          |      modelNumber
          |      maker {
          |        name
          |        nicknames
          |      }
          |    }
          |  }
          |}
          """.trimMargin("|"))

    val charactersQuery = query {
      "characters"("first" to 100)..listOf {
        on..humanDef()
        on..robotDef()
      }
    }

    val expect = """
      |{
      |  characters(first: 100) {
      |    __typename
      |    ... on Human {
      |      name
      |      nicknames
      |    }
      |    ... on Robot {
      |      modelNumber
      |      maker {
      |        name
      |        nicknames
      |      }
      |    }
      |  }
      |}
      """.trimMargin("|")

    assertThat(charactersQuery.toGraphQl())
        .isEqualTo(expect)
  }
}


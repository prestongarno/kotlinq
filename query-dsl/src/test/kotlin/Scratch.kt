@file:Suppress("unused")

import org.junit.Test
import org.kotlinq.dsl.toGraphQl


fun greet(worldName: String = "Earth", message: String = "Hello") =

    query {

      "greet"("name" to worldName, "message" to message) {
        "population"::integer

        "countries"("first" to 100) {
          "name"(::string)
          "coordinates"(
              arguments = coordinateArguments(1, Measurement.KILOMETERS),
              definition = coordinateDefinition())

          "subEntities" spread {

            //on("State") { "mayor"() { } }

            on("City") {
              "mayor"(::string)
            }
          }
        }
      }
    }

fun coordinateArguments(unitAccuracy: Int, unitType: Measurement): Map<String, Any> {
  return mapOf("unitAccuracy" to unitAccuracy,
      "unitType" to unitType.name)
}

fun coordinateDefinition(): TypeDefinition =
    typeDefinition("Coordinate") {
      "xValue"(::float)
      "yValue"(::float)
    }

enum class Measurement {
  MILES,
  KILOMETERS
}

class Scratch {

  @Test fun queryGraph() {
    println(greet().toGraphQl(pretty = true))
  }
}


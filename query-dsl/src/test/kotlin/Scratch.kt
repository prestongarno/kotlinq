@file:Suppress("unused")

import org.junit.Test
import org.kotlinq.dsl.extensions.float
import org.kotlinq.dsl.extensions.integer
import org.kotlinq.dsl.extensions.string
import org.kotlinq.dsl.toGraphQl


fun greet(worldName: String = "Earth", message: String = "Hello") =

    query {
      (!"greet"(mapOf("name" to worldName, "message" to message))) {
        !"population"::integer
        (!"countries"(mapOf("first" to 100))) {
          -"name"::string
          (!"coordinates"()).invoke { coordinateDefinition() }
          !"subEntities"() spread {
            //on("State") { "mayor"() { } }
            on("City") {
              //"mayor"(::string)
            }
          }
        }
      }
    }


fun coordinateDefinition(): TypeDefinition =
    typeDefinition("Coordinate") {
      !"xValue"::float
      !"yValue"::float
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


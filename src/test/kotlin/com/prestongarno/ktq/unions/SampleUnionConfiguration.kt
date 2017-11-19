package com.prestongarno.ktq.unions

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.primitives.eq
import org.junit.Test


object Thing : QUnionType by QUnionType.create() {
  fun onCar(init: () -> QModel<Car>) = on(init)
  fun onHamburger(init: () -> QModel<Hamburger>) = on(init)
}

object Hamburger : QType {
  val ingredients by QScalarArray.String.stub()
}

object Car : QType {
  val make by QScalar.String.stub()
  val carModel by QScalar.String.stub()
}

object Query : QType {
  val thing by QUnion.stub(Thing)

  val things by QUnionList.stub(Thing)
}

class SampleUnionConfiguration {

  class CarModel : QModel<Car>(Car) {
    val make by model.make
    val carModel by model.carModel
  }
  class HamburgerModel : QModel<Hamburger>(Hamburger) {
    val ingredientsList by model.ingredients
  }

  @Test fun `union field and fragments is possible`() {

    val query = object : QModel<Query>(Query) {

      val thing by model.thing {
        fragment {
          onCar(::CarModel)
          onHamburger(::HamburgerModel)
        }
      }

    }
    query.toGraphql(false) eq "{thing{__typename,... on Car{make,carModel}, ... on Hamburger{ingredients}}}"
  }

  @Test fun `union list field and fragments is possible`() {

    val query = object : QModel<Query>(Query) {

      val thingList by model.things {
        fragment {
          onCar(::CarModel)
          onHamburger(::HamburgerModel)
        }
      }

    }
    query.toGraphql(false) eq "{things{__typename,... on Car{make,carModel}, ... on Hamburger{ingredients}}}"
  }
}

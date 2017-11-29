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
  val ingredients by QScalar.List.String.stub()
}

object Car : QType {
  val make by QScalar.String.stub()
  val carModel by QScalar.String.stub()
}

object Query : QType {
  val thing by QUnion.stub(Thing)

  val things by QUnion.List.stub(Thing)
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

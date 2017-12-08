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

package com.prestongarno.kotlinq.core.unions

import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType.*
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.QUnionType
import com.prestongarno.kotlinq.core.primitives.eq
import org.junit.Test


object Thing : QUnionType by QUnionType.new() {
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
    val make by Car.make
    val carModel by Car.carModel
  }
  class HamburgerModel : QModel<Hamburger>(Hamburger) {
    val ingredientsList by Hamburger.ingredients
  }

  @Test fun `union field and fragments is possible`() {

    val query = object : QModel<Query>(Query) {

      val thing by Query.thing {
        fragment {
          onCar(SampleUnionConfiguration::CarModel)
          onHamburger(SampleUnionConfiguration::HamburgerModel)
        }
      }

    }
    query.toGraphql() eq "{thing{__typename,...fragCar0...fragHamburger1}}" +
        "fragment fragCar0 on Car{make,carModel}," +
        "fragment fragHamburger1 on Hamburger{ingredients}"
  }

  @Test fun `union list field and fragments is possible`() {

    val query = object : QModel<Query>(Query) {

      val thingList by Query.things {
        fragment {
          onCar(SampleUnionConfiguration::CarModel)
          onHamburger(SampleUnionConfiguration::HamburgerModel)
        }
      }

    }
    query.toGraphql() eq "{things{__typename,...fragCar0...fragHamburger1}}" +
        "fragment fragCar0 on Car{make,carModel}," +
        "fragment fragHamburger1 on Hamburger{ingredients}"
  }
}

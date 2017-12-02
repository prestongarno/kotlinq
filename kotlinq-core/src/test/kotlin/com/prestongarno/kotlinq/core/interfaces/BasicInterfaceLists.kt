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

package com.prestongarno.kotlinq.core.interfaces

import com.google.common.truth.Truth.assertThat
import com.prestongarno.kotlinq.core.QInterface
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType.*
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.primitives.eq
import com.prestongarno.kotlinq.core.stubs.StringDelegate
import org.junit.Test

interface Thing : QType, QInterface {
  val name : StringDelegate.Query
}

object Concrete : Thing {
  override val name: StringDelegate.Query by QScalar.String.stub()
}

object Get : QType {
  val getThings by QInterfaces.stub<Thing>()
}

class BasicInterfaceLists {

  class ThingImpl : QModel<Concrete>(Concrete) {
    val name by model.name
  }

  @Test fun `interface list is possible`() {

    val query = object : QModel<Get>(Get) {
      val things by Get.getThings {
        on(BasicInterfaceLists::ThingImpl)
      }
    }

    query::things.returnType.arguments
        .firstOrNull()?.type?.classifier eq Thing::class
    query.toGraphql() eq "{getThings{__typename, ... on Concrete{name}}}"
  }
}
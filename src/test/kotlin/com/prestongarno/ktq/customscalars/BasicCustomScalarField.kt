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

package com.prestongarno.ktq.customscalars

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import com.prestongarno.ktq.primitives.eq
import org.junit.Test

object URL : CustomScalar

object Item : QType {
  val url by QCustomScalar.stub<URL>()
}

class BasicCustomScalarField {

  @Test fun `custom scalar field is possible`() {

    val query = object : QModel<Item>(Item) {
      val url by model.url.map(StringScalarMapper { it })
    }

    query::url.returnType.classifier eq String::class
    query::url.returnType.isMarkedNullable eq false
    query.toGraphql(false) eq "{url}"
  }

  @Test fun `custom scalar field is possible 2`() {

    val query = object : QModel<Item>(Item) {
      val url by model.url.map(StringScalarMapper { it.toIntOrNull() })
    }

    query::url.returnType.classifier eq Int::class
    query::url.returnType.isMarkedNullable eq true
    query.toGraphql(false) eq "{url}"
  }
}

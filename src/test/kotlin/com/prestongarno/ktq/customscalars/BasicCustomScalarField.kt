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

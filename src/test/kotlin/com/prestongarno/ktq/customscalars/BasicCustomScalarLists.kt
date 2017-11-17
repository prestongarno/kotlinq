package com.prestongarno.ktq.customscalars

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.adapters.custom.StringScalarListMapper
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import com.prestongarno.ktq.primitives.eq
import org.junit.Test

object ResourceBundle : QType {
  val urls by QSchemaType.QCustomScalarList.stub<URL>()
}

class BasicCustomScalarLists {

  @Test fun `custom scalar list is possible`() {

    val query = object : QModel<ResourceBundle>(ResourceBundle) {
      val urls by model.urls.map(StringScalarListMapper { it })
    }
    query::urls.returnType.arguments
        .firstOrNull()?.type?.classifier eq String::class
    query.toGraphql(false) eq "{urls}"
  }
}

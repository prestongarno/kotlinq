package com.prestongarno.ktq.interfaces

import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.primitives.eq
import com.prestongarno.ktq.stubs.StringDelegate
import org.junit.Test

interface Thing : QType, QInterface {
  val name : StringDelegate.Query
}

object Concrete : Thing {
  override val name: StringDelegate.Query by QScalar.String.stub()
}

object Get : QType {
  val getThings by QInterfaceLists.stub<Thing>()
}

class BasicInterfaceLists {

  class ThingImpl : QModel<Concrete>(Concrete) {
    val name by model.name
  }

  @Test fun `interface list is possible`() {

    val query = object : QModel<Get>(Get) {
      val things by model.getThings {
        on(::ThingImpl)
      }
    }

    query::things.returnType.arguments
        .firstOrNull()?.type?.classifier eq QModel::class
    query.toGraphql(false) eq "{getThings{__typename,... on Concrete{name}}}"
  }
}
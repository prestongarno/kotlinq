package com.prestongarno.ktq

import com.prestongarno.ktq.QSchemaType.QCustomScalar
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import org.intellij.lang.annotations.Language
import org.junit.Test
import java.time.Instant
import java.util.Calendar
import java.util.Date
import kotlin.test.assertTrue

class MockResponseCustomScalar {
  @Test fun singleCustomScalarField() {

    val myNote = object : QModel<Note>(Note) {
      val dateCreated by model.dateCreated.init(StringScalarMapper {
        Date.from(Instant.parse(it)?: Instant.EPOCH)
      })
    }

    @Language("JSON") val response = """{
      "dateCreated": "2007-12-03T10:15:30.00Z"
    }"""

    myNote.run {
      onResponse(response)
      assertTrue(Calendar.getInstance().time.after(dateCreated))
    }
  }
}

object DateTime : CustomScalar

object Note : QSchemaType {
  val dateCreated: CustomScalarInitStub<DateTime> by QCustomScalar.stub()
}

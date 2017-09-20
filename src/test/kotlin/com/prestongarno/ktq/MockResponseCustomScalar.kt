package com.prestongarno.ktq

import com.prestongarno.ktq.QSchemaType.QCustomScalar
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import com.google.common.truth.Truth.assertThat
import org.intellij.lang.annotations.Language
import org.junit.Test
import java.io.File
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

  @Test fun singleCustomScalarField2() {
    val myNote = object : QModel<Note>(Note) {
      val webUrl by model.webUrl.init(StringScalarMapper { File(it).toURI() })
    }

    @Language("JSON") val response = """{
        "webUrl": "/dev/null"
      }"""

    myNote.run {
      onResponse(response)
      assertThat(webUrl.path).isEqualTo("/dev/null")
    }
  }
}

object DateTime : CustomScalar

object URI : CustomScalar

object Note : QSchemaType {
  val dateCreated: CustomScalarInitStub<DateTime> by QCustomScalar.stub()

  val webUrl: CustomScalarInitStub<URI> by QCustomScalar.stub()
}

package com.prestongarno.ktq

import com.prestongarno.ktq.QSchemaType.QCustomScalar
import com.prestongarno.ktq.QSchemaType.QCustomScalarList
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.adapters.custom.StringScalarListMapper
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
        Date.from(Instant.parse(it) ?: Instant.EPOCH)
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

  @Test fun multipleFieldCustomScalarListedMappings() {
    val myNote = object : QModel<Note>(Note) {
      val webUrl by model.webUrl.init(StringScalarMapper { File(it).toURI() })
      val related by model.relatedLinks.init(StringScalarListMapper { File(it) })
    }
    @Language("JSON") val response = """{
        "webUrl": "/dev/null",
        "relatedLinks": ["stderr", "stdin"]
      }"""

    myNote.run {
      onResponse(response)
      assertThat(webUrl.path).isEqualTo("/dev/null")
      assertTrue(related.size == 2)
      assertThat(related[0].name).isEqualTo("stderr")
      assertThat(related[1].name).isEqualTo("stdin")
    }
  }

  @Test fun multipleCustomScalarLists() {
    val myNote = object : QModel<Note>(Note) {
      val webUrl by model.webUrl.init(StringScalarMapper { File(it).toURI() })
      val related by model.relatedLinks.init(StringScalarListMapper { File(it) })
      val refIds by model.refIds.init(StringScalarListMapper { it.toInt() })
    }
    @Language("JSON") val response = """{
        "webUrl": "/dev/null",
        "relatedLinks": ["stderr", "stdin"],
        "refIds": [1000, 1001, 1002]
      }"""

    myNote.run {
      onResponse(response)
      assertThat(webUrl.path).isEqualTo("/dev/null")
      assertTrue(related.size == 2)
      assertThat(related[0].name).isEqualTo("stderr")
      assertThat(related[1].name).isEqualTo("stdin")
      assertTrue(refIds.size == 3)
      assertThat(refIds[0]).isEqualTo(1000)
      assertThat(refIds[1]).isEqualTo(1001)
      assertThat(refIds[2]).isEqualTo(1002)
    }
  }

  @Test fun configurableStubMappedToCustomScalarList() {
    val myNote = object : QModel<Note>(Note) {
      val refIds by model.refIdsConfigurable.config {
        first(10)
      }.init(StringScalarListMapper { it.toInt() })
    }
    @Language("JSON") val response = """{
        "refIdsConfigurable": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
      }"""

    myNote.run {
      onResponse(response)
      assertThat(refIds.size == 10)
      refIds.forEachIndexed {
        id, index -> assertThat(id).isEqualTo(index)
      }
    }
  }
}

object DateTime : CustomScalar

object URI : CustomScalar

object Note : QSchemaType {
  val dateCreated: CustomScalarInitStub<DateTime> by QCustomScalar.stub()
  val webUrl: CustomScalarInitStub<URI> by QCustomScalar.stub()
  val relatedLinks: CustomScalarListInitStub<URI> by QCustomScalarList.stub()
  val refIds: CustomScalarListInitStub<ID> by QCustomScalarList.stub()
  val refIdsConfigurable: CustomScalarListConfigStub<ID, Note.RefIdsConfigurableArgs>
      by QCustomScalarList.configStub { RefIdsConfigurableArgs(it) }

  class RefIdsConfigurableArgs(args: CustomScalarListArgBuilder) : CustomScalarListArgBuilder by args {
    fun first(value: Int): RefIdsConfigurableArgs = apply { addArg("first", value) }
  }
}

object ID : CustomScalar
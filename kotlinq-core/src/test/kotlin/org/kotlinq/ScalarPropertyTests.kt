package org.kotlinq

import org.junit.Test
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.Kind


class ScalarPropertyTests {

  @Test fun simpleStringAdapterIOTest() {
    val expect = "Hello, World!"
    Kotlinq.adapterService.scalarAdapters
        .newAdapter(info("any"))
        .apply {
          setValue(expect)
          require(getValue() == expect)
        }
  }


  @Test fun simpleIntAdapterIOTest() {
    val expect = 1000
    Kotlinq.adapterService.scalarAdapters
        .newAdapter(info("intProperty", Kind.Scalar._Int))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }

  @Test fun simpleFloatAdapterIOTest() {
    val expect = 1000.1f
    Kotlinq.adapterService.scalarAdapters
        .newAdapter(info("floatProperty", Kind.Scalar._Float))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }

  @Test fun simpleBooleanAdapterIOTest() {
    val expect = false
    Kotlinq.adapterService.scalarAdapters
        .newAdapter(info("boolProperty", Kind.Scalar._Boolean))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }
}
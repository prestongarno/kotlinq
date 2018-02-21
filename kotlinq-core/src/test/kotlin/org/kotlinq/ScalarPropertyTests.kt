package org.kotlinq

import org.junit.Test
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.Kind


class ScalarPropertyTests {

  @Test fun simpleStringAdapterIOTest() {
    val expect = "Hello, World!"
    Kotlinq.adapterService.scalarAdapters
        .adapterFor(info("any"))
        .apply {
          setValue(expect)
          require(getValue() == expect)
        }
  }


  @Test fun simpleIntAdapterIOTest() {
    val expect = 1000
    Kotlinq.adapterService.scalarAdapters
        .adapterFor(info("intProperty", Kind._Int))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }

  @Test fun simpleFloatAdapterIOTest() {
    val expect = 1000.1f
    Kotlinq.adapterService.scalarAdapters
        .adapterFor(info("floatProperty", Kind._Float))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }

  @Test fun simpleBooleanAdapterIOTest() {
    val expect = false
    Kotlinq.adapterService.scalarAdapters
        .adapterFor(info("boolProperty", Kind._Boolean))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }
}
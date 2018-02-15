package org.kotlinq

import org.junit.Test
import org.kotlinq.api.Kotlinq


class ScalarPropertyTests {

  @Test fun simpleStringAdapterIOTest() {
    val expect = "Hello, World!"
    Kotlinq.adapterService.scalarAdapters
        .stringAdapter(info("any"))
        .apply {
          setValue(expect)
          require(getValue() == expect)
        }
  }


  @Test fun simpleIntAdapterIOTest() {
    val expect = 1000
    Kotlinq.adapterService.scalarAdapters
        .intAdapter(info("intProperty"))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }

  @Test fun simpleFloatAdapterIOTest() {
    val expect = 1000.1f
    Kotlinq.adapterService.scalarAdapters
        .floatAdapter(info("floatProperty"))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }

  @Test fun simpleBooleanAdapterIOTest() {
    val expect = false
    Kotlinq.adapterService.scalarAdapters
        .booleanAdapter(info("boolProperty"))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }
}
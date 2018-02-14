package org.kotlinq

import org.junit.Test
import org.kotlinq.api.Kotlinq


class ScalarPropertyTests {

  @Test fun simpleStringAdapterIOTest() {
    val expect = "Hello, World!"
    Kotlinq.adapterService.scalarAdapters
        .stringAdapter("nothing", mockType(String::class))
        .apply {
          setValue(expect)
          require(getValue() == expect)
        }
  }


  @Test fun simpleIntAdapterIOTest() {
    val expect = 1000
    Kotlinq.adapterService.scalarAdapters
        .intAdapter("nothing", mockType(Int::class))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }

  @Test fun simpleFloatAdapterIOTest() {
    val expect = 1000.1f
    Kotlinq.adapterService.scalarAdapters
        .floatAdapter("nothing", mockType(Int::class))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }

  @Test fun simpleBooleanAdapterIOTest() {
    val expect = false
    Kotlinq.adapterService.scalarAdapters
        .booleanAdapter("nothing", mockType(Int::class))
        .apply {
          setValue("$expect")
          require(getValue() == expect)
        }
  }
}
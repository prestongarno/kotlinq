package com.prestongarno.ktq.interfaceFragments

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.ArgBuilder
import org.junit.Ignore
import org.junit.Test

class GeneratedFieldArgBuilder(val requiredArgument: String) : ArgBuilder() {

  init {
    "requiredArgument" with requiredArgument
  }

  var property: String? by arguments

  fun foo() {
    property = "World"
  }
}

class ArgBuilders {
  @Ignore @Test fun fooBar() {
    val foo = GeneratedFieldArgBuilder("Hello").apply { foo() }
    assertThat(foo.run { "$requiredArgument, $property!" })
        .isEqualTo(foo.arguments().values.joinToString(postfix = "!") { it.toString() })
  }
}

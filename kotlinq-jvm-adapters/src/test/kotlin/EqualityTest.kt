package org.kotlinq.jvm

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.jvm.ClassFragment.Companion.fragment


class EqualityTest {

  @Test fun simpleEquals() {
    class Foo(res: GraphQlResult): Data by res.toData() {
      val str by result.string()
    }

    assertThat(fragment(::Foo).hashCode())
        .isEqualTo(fragment(::Foo).hashCode())
    assertThat(fragment(::Foo))
        .isEqualTo(fragment(::Foo))

  }
}
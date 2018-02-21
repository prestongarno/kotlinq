package org.kotlinq.entities

import com.google.common.truth.Subject
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.api.Fragment
import org.kotlinq.api.Kind
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.PropertyInfo

class SimpleFragmentEquality {


  val scalarService get() = Kotlinq.adapterService.scalarAdapters

  fun newContext() = Kotlinq.newContextBuilder()

  fun newScalar(info: PropertyInfo) =
      scalarService.newAdapter(info)

  fun testFragmentEqualityAndHashCode(shouldBeEqual: Boolean, fragments: Pair<Fragment, Fragment>) =
      fragments.let { (first, second) ->
        val assert: Subject<*, *>.(Any) -> Unit = if (shouldBeEqual) {
          { isEqualTo(it) }
        } else {
          { isNotEqualTo(it) }
        }
        assertThat(first).assert(second)
        assertThat(first.hashCode()).assert(second.hashCode())
        assertThat(first.typeName).assert(second.typeName)
        assertThat(first.graphQlInstance).assert(second.graphQlInstance)
      }

  @Test fun `fragment with single string property is equal`() {
    testFragmentEqualityAndHashCode(
        shouldBeEqual = true,
        fragments = createPairOfFragments(type = Kind._String))
  }

  @Test fun `single integer property fragment is equal`() {

    val rootName = "frag0"
    val info = PropertyInfo.named("property_first")
        .typeKind(Kind._Int)
        .build()

    val firstProperty = newScalar(info)

    val first = newContext()
        .register(firstProperty)
        .build(rootName)

    val second = newContext()
        .register(newScalar(info.copy()))
        .build(rootName)

    testFragmentEqualityAndHashCode(
        shouldBeEqual = true,
        fragments = first to second)
  }

  @Test fun `single boolean property fragment is equal`() {

    val rootName = "frag0"
    val info = PropertyInfo.named("property_first")
        .typeKind(Kind._Boolean)
        .build()

    val firstProperty = newScalar(info)

    val first = newContext()
        .register(firstProperty)
        .build(rootName)

    val second = newContext()
        .register(newScalar(info.copy()))
        .build(rootName)

    testFragmentEqualityAndHashCode(
        shouldBeEqual = true,
        fragments = first to second)

  }

  @Test fun `single float property fragment is equal`() {

    val rootName = "frag0"
    val info = PropertyInfo.named("property_first")
        .typeKind(Kind._Float)
        .build()

    val firstProperty = newScalar(info)

    val first = newContext()
        .register(firstProperty)
        .build(rootName)

    val second = newContext()
        .register(newScalar(info.copy()))
        .build(rootName)

    testFragmentEqualityAndHashCode(
        shouldBeEqual = true,
        fragments = first to second)
  }


  fun createPairOfFragments(
      fragmentTypeName: String = "frag0",
      propertyName: String = "adapter_property",
      type: Kind): Pair<Fragment, Fragment> {

    val info = PropertyInfo.named(propertyName)
        .typeKind(Kind._String)
        .build()

    val property = scalarService
        .newAdapter(info)

    val first = newContext()
        .register(property)
        .build(fragmentTypeName)

    val second = newContext()
        .register(scalarService.newAdapter(info.copy()))
        .build(fragmentTypeName)

    return first to second
  }

}


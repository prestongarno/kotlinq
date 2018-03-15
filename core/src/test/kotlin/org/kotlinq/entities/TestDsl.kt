package org.kotlinq.entities

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.Kind
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.PropertyInfo
import org.kotlinq.common.addLast

typealias TestFragment = TestFragmentBuilder.() -> Unit

class TestFragmentBuilder private constructor(
    private val callback: (Adapter) -> Unit,
    block: TestFragmentBuilder.() -> Unit) {

  init {
    this.block()
  }

  fun Adapter.bind() = callback(this)

  fun scalar(name: String, kind: Kind.Scalar = Kind.string, arguments: Map<String, Any> = emptyMap()): Adapter =
      Kotlinq.adapterService.scalarAdapters
          .newAdapter(PropertyInfo.propertyNamed(name)
              .typeKind(kind)
              .arguments(arguments)
              .build()).also(callback)

  infix fun String.on(block: TestFragmentBuilder.() -> Unit) =
      this(kind = Kind.typeNamed("Fragment0")).on(block)

  operator fun String.invoke(kind: Kind, arguments: Map<String, Any> = emptyMap())
      : Pair<Pair<String, Map<String, Any>>, Kind> = this to arguments to kind

  infix fun Pair<Pair<String, Map<String, Any>>, Kind>.on(block: TestFragmentBuilder.() -> Unit) {
    callback(Kotlinq.adapterService.instanceProperty(
        PropertyInfo.propertyNamed(this.first.first)
            .typeKind(this.second)
            .build(),
        fragment(second.rootKind().name, block)))
  }

  fun bindFragment(propertyName: String, fragment: Fragment) {
    this.callback.invoke(Kotlinq.adapterService.instanceProperty(PropertyInfo.propertyNamed(propertyName).build(), fragment))
  }

  companion object {

    fun fragment(type: String = "Fragment0", block: TestFragmentBuilder.() -> Unit): Fragment {
      val properties = mutableListOf<Adapter>()
      TestFragmentBuilder(properties::addLast, block)
      return Kotlinq.newContextBuilder().apply {
        properties.forEach { register(it) }
      }.build(type)
    }

    fun fragment(kind: Kind, block: TestFragmentBuilder.() -> Unit) =
        fragment(kind.rootKind().name, block)
  }
}


package org.kotlinq.jvm

import org.kotlinq.api.BindableContext
import org.kotlinq.api.Kotlinq
import kotlin.reflect.KClass
import kotlin.reflect.KProperty0


@Suppress("UNCHECKED_CAST")
class TypedFragmentScope internal constructor(private val bindableContext: BindableContext) {

  /**
   * For:
   *
   * typedFragment<FooType>() {
   *   ::someProperty("first" to 10)
   * }
   *
   *
   * Enables arguments on fields while using generic DSL
   */
  @Suppress("UNCHECKED_CAST")
  inline operator fun <reified T : Data?> KProperty0<T>.invoke(arguments: Map<String, Any>) {
    registerWithArguments(this, T::class as KClass<Data>, arguments)
  }

  @PublishedApi
  internal fun registerWithArguments(property: KProperty0<Data?>, clazz: KClass<Data>, arguments: Map<String, Any>) {
    Kotlinq.adapterService.instanceProperty(
        property.toPropertyInfo(clazz.simpleName!!, arguments),
        TypedFragment.thisClass(clazz))
        .let(bindableContext::register)

  }
}
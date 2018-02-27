package org.kotlinq.jvm

import org.kotlinq.api.BindableContext
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.PropertyInfo
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1


@Suppress("UNCHECKED_CAST")
class TypedFragmentScope<T : Data?> internal constructor(private val bindableContext: BindableContext) {

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
  operator fun KProperty1<T, *>.invoke(arguments: Map<String, Any>) {
    bindScalarPropertyIfApplicable(this, arguments)
  }

  inline operator fun <reified X : Data?> KProperty1<T, X>.rangeTo(
      noinline block: InterfaceFragmentSpreadScope<X>.() -> Unit) =
      buildFromFragmentScope(this, block)



  @PublishedApi internal
  fun bindScalarPropertyIfApplicable(prop: KProperty1<T, *>, arguments: Map<String, Any>) {
    val kind = prop.returnType.scalarKind() ?: return
    PropertyInfo.propertyNamed(prop.name)
        .typeKind(kind)
        .arguments(arguments)
        .build()
        .let(Kotlinq.adapterService.scalarAdapters::newAdapter)
        .let(bindableContext::register)
  }

  @PublishedApi internal
  fun <X : Data?> buildFromFragmentScope(
      property: KProperty1<T, X>,
      block: InterfaceFragmentSpreadScope<X>.() -> Unit
  ) {
    val kind = property.returnType.dataKind() ?: return
    PropertyInfo.propertyNamed(property.name)
        .typeKind(kind)
        .build()
        .let { InterfaceFragmentSpreadScope<X>(it).build(block) }
        .let(bindableContext::register)
  }

  @PublishedApi
  internal fun registerWithArguments(property: KProperty1<*, Data?>, clazz: KClass<Data>, arguments: Map<String, Any>) {
    Kotlinq.adapterService.instanceProperty(
        property.toPropertyInfo(clazz.simpleName!!, arguments),
        TypedFragment.reflectionFragment(clazz))
        .let(bindableContext::register)

  }
}
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
  operator fun <R> KProperty1<T, R>.invoke(arguments: Map<String, Any>)
      : FragmentField<T, R> =
      bindScalarPropertyIfApplicable(this, arguments) ?: FragmentField(this)

  infix fun KProperty1<T, Data?>.union(block: InterfaceFragmentSpreadScope<Data?>.() -> Unit) {
    buildFromFragmentScope(this, block)
  }

  operator fun <R : Data?> FragmentField<T, R>.rangeTo(block: InterfaceFragmentSpreadScope<R>.() -> Unit) {
    buildFromFragmentScope(property, block)
  }

  infix fun FragmentField<T, Data?>.union(block: InterfaceFragmentSpreadScope<Data?>.() -> Unit) {
    buildFromFragmentScope(property, block)
  }

  inline operator fun <reified X : Data?> KProperty1<T, X>.rangeTo(
      noinline block: InterfaceFragmentSpreadScope<X>.() -> Unit) =
      buildFromFragmentScope(this, block)


  @PublishedApi internal
  fun <R> bindScalarPropertyIfApplicable(prop: KProperty1<T, *>, arguments: Map<String, Any>): FragmentField<T, R>? {
    val kind = prop.returnType.scalarKind() ?: return null
    PropertyInfo.propertyNamed(prop.name)
        .typeKind(kind)
        .arguments(arguments)
        .build()
        .let(Kotlinq.adapterService.scalarAdapters::newAdapter)
        .let(bindableContext::register)
    return null
  }

  @PublishedApi internal
  fun <X : Data?> buildFromFragmentScope(property: KProperty1<T, X>, block: InterfaceFragmentSpreadScope<X>.() -> Unit) {
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
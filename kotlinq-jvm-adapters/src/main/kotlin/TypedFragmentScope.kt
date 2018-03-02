package org.kotlinq.jvm

import org.kotlinq.api.BindableContext
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.PropertyInfo
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.reflect


@GraphQlDsl
@Suppress("UNCHECKED_CAST")
class TypedFragmentScope<T : Data?> internal constructor(private val bindableContext: BindableContext) {

  /**
   * For:
   *
   * fragment<FooType>() {
   *   ::someProperty("first" to 10)
   * }
   *
   *
   * Enables arguments on fields while using generic DSL
   */
  @Suppress("UNCHECKED_CAST")
  operator fun <R> KProperty1<T, R>.invoke(arguments: Map<String, *>)
      : FragmentField<T, R> =
      bindScalarPropertyIfApplicable(this, arguments) ?: FragmentField(this)

  operator fun <R : Data?> FragmentField<T, R>.rangeTo(block: InterfaceFragmentSpreadScope<R>.() -> Unit) {
    buildFromFragmentScope(property, block)
  }

  infix fun FragmentField<T, Any?>.union(block: InterfaceFragmentSpreadScope<Data?>.() -> Unit) {
    buildFromFragmentScope(property, block)
  }

  infix fun KProperty1<T, Any?>.union(block: InterfaceFragmentSpreadScope<Data?>.() -> Unit) {
    buildFromFragmentScope(this, block)
  }

  operator fun <X : Data?> KProperty1<T, X>.invoke(
      block: InterfaceFragmentSpreadScope<X>.() -> Unit) =
      buildFromFragmentScope(this, block)

  operator fun <X : Data?> KProperty1<T, List<X>>.rangeTo(
      block: InterfaceFragmentSpreadScope<X>.() -> Unit) =
      buildFromFragmentScope(this, block)

  infix fun <X : Data?> KProperty1<T, X>.on(fragment: ClassFragment<X>) = Kotlinq.adapterService
      .instanceProperty(toPropertyInfo(fragment.typeName), fragment)
      .let(bindableContext::register)
      .ignore()


  infix fun <X : Data?> KProperty1<T, X>.on(init: (GraphQlResult) -> X) {
    init.reflect()?.returnType?.clazz?.let { clazz ->
      val fragment = ClassFragment.fragment(clazz, init)
      Kotlinq.adapterService
          .instanceProperty(toPropertyInfo(fragment.typeName), fragment)
          .let(bindableContext::register)
    }
  }


  @PublishedApi internal
  fun <R> bindScalarPropertyIfApplicable(prop: KProperty1<T, *>, arguments: Map<String, *>): FragmentField<T, R>? {
    val kind = prop.returnType.scalarKind() ?: return null
    PropertyInfo.propertyNamed(prop.name)
        .typeKind(kind)
        .arguments(arguments
            .mapNotNull { it.key with it.value }
            .toMap())
        .build()
        .let(Kotlinq.adapterService.scalarAdapters::newAdapter)
        .let(bindableContext::register)
    return null
  }

  @PublishedApi internal
  fun <X : Data?> buildFromFragmentScope(property: KProperty1<T, *>, block: InterfaceFragmentSpreadScope<X>.() -> Unit) {
    val kind = property.returnType.rootType.dataKind() ?: return
    PropertyInfo.propertyNamed(property.name)
        .typeKind(kind)
        .build()
        .let { InterfaceFragmentSpreadScope<X>(it).build(block) }
        .let(bindableContext::register)
  }

  @PublishedApi internal
  fun registerWithArguments(property: KProperty1<*, Data?>, clazz: KClass<Data>, arguments: Map<String, Any>) {
    Kotlinq.adapterService.instanceProperty(
        property.toPropertyInfo(clazz.simpleName!!, arguments),
        reflectionFragment<Data?>(clazz))
        .let(bindableContext::register)

  }
}
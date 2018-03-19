package org.kotlinq.jvm

import org.kotlinq.api.BindableContext
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.Kotlinq.Companion.adapterService
import org.kotlinq.api.PropertyInfo
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.reflect


// TODO superinterfaces for different extension types, scopes, etc. needed
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
    buildFromFragmentScope(property, block, arguments)
  }

  infix fun FragmentField<T, Any?>.union(block: InterfaceFragmentSpreadScope<Data?>.() -> Unit) {
    buildFromFragmentScope(property, block, arguments)
  }

  infix fun KProperty1<T, Any?>.union(block: InterfaceFragmentSpreadScope<Data?>.() -> Unit) {
    buildFromFragmentScope(this, block)
  }

  infix fun <X : Data> FragmentField<T, X?>.on(fragment: ClassFragment<X>) {
    adapterService.instanceProperty(
        property.toPropertyInfo(args = arguments),
        fragment)
        .let(bindableContext::register)
  }

  infix fun <X> KProperty1<T, X>.withArgs(arguments: Map<String, *>)
      : FragmentField<T, X> = FragmentField(this, arguments)

  fun <X> KProperty1<T, X>.withArgs(vararg arguments: Pair<String, Any?>)
      : FragmentField<T, X> = FragmentField(this, arguments.toMap())

  operator fun <X : Data?> KProperty1<T, X>.invoke(
      block: InterfaceFragmentSpreadScope<X>.() -> Unit) =
      buildFromFragmentScope(this, block)

  operator fun <X : Data?> KProperty1<T, List<X>>.rangeTo(
      block: InterfaceFragmentSpreadScope<X>.() -> Unit) =
      buildFromFragmentScope(this, block)

  operator fun <X : Data?> KProperty1<T, List<X>>.rangeTo(fragment: ClassFragment<X>) {
    adapterService.instanceProperty(
        toPropertyInfo(fragment.typeName),
        fragment)
        .let(bindableContext::register)
  }

  infix fun <X : Data?> KProperty1<T, X>.on(fragment: ClassFragment<X>) =
      adapterService.instanceProperty(toPropertyInfo(fragment.typeName), fragment)
          .let(bindableContext::register)
          .ignore()

  infix fun <X : Data?> KProperty1<T, List<X>>.spread(fragment: ClassFragment<X>) =
      adapterService.instanceProperty(toPropertyInfo(fragment.typeName), fragment)
          .let(bindableContext::register)
          .ignore()

  inline infix fun <reified X : Data> KProperty1<T, List<X>>.spread(noinline init: (GraphQlResult) -> X) =
      bindFromInit(this, init, X::class)

  infix fun <X : Data?> KProperty1<T, X>.on(init: (GraphQlResult) -> X) {
    init.reflect()?.returnType?.clazz?.let { clazz ->
      val fragment = ClassFragment.fragment(clazz, init)
      Kotlinq.adapterService
          .instanceProperty(toPropertyInfo(fragment.typeName), fragment)
          .let(bindableContext::register)
    }
  }

  @PublishedApi internal
  fun <X : Data> bindFromInit(
      property: KProperty1<*, *>,
      init: (GraphQlResult) -> Data,
      clazz: KClass<X>
  ) {

    val kind = property.returnType.dataKind() ?: return

    PropertyInfo.propertyNamed(property.name)
        .typeKind(kind)
        .build()
        .let { info ->
          adapterService.instanceProperty(info, ClassFragment(clazz, init))
        }
        .let(bindableContext::register)
  }

  @PublishedApi internal
  fun <R> bindScalarPropertyIfApplicable(prop: KProperty1<T, *>, arguments: Map<String, *>): FragmentField<T, R>? {
    prop.returnType.scalarKind() ?: return null
    prop.toPropertyInfo(args = arguments)
        .let(Kotlinq.adapterService.scalarAdapters::newAdapter)
        .let(bindableContext::register)
    return null
  }

  @PublishedApi internal
  fun <X : Data?> buildFromFragmentScope(
      property: KProperty1<T, *>,
      block: InterfaceFragmentSpreadScope<X>.() -> Unit,
      arguments: Map<String, *> = emptyMap<String, Any?>()
  ) {
    property.returnType.dataKind() ?: return
    property.toPropertyInfo(args = arguments)
        .let { InterfaceFragmentSpreadScope<X>(it).build(block) }
        .let(bindableContext::register)
  }

  @PublishedApi internal
  fun registerWithArguments(property: KProperty1<*, Data?>, clazz: KClass<Data>, arguments: Map<String, Any>) {
    Kotlinq.adapterService.instanceProperty(
        property.toPropertyInfo(args = arguments),
        reflectionFragment<Data?>(clazz))
        .let(bindableContext::register)

  }
}


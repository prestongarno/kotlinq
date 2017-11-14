package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.InterfaceAdapterImpl
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.adapters.applyNotNull
import com.prestongarno.ktq.hooks.ConfigurableQuery
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfigQuery
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

/**
 * Remember -> compile generate all interface types to be *both* [QType] ***and*** [QInterface]
 */
interface InterfaceStub<I, out A : ArgBuilder> : FragmentStub<I>, DelegateProvider<QModel<I>?> where I : QInterface, I : QType {

  fun config(argumentScope: A.() -> Unit)

  companion object {
    @PublishedApi internal fun <I> noArgStub(
        qproperty: GraphQlProperty
    ): Query<I> where I : QInterface, I : QType =
        QueryImpl(qproperty)

    @PublishedApi internal fun <I, A> optionalArgStub(
        qproperty: GraphQlProperty
    ): OptionalConfigQueryQuery<I, A>
        where I : QInterface, I : QType, A : ArgBuilder =
        OptionalConfigQueryQueryImpl(qproperty)

    @PublishedApi internal fun <I, A> argStub(
        qproperty: GraphQlProperty
    ): ConfigurableQueryQuery<I, A>
        where I : QInterface, I : QType, A : ArgBuilder =
        ConfigurableQueryQueryImpl(qproperty)
  }

  interface Query<I> : NoArgConfig<InterfaceStub<I, ArgBuilder>, QModel<I>?> where I : QInterface, I : QType

  interface OptionalConfigQueryQuery<I, A> : OptionalConfigQuery<InterfaceStub<I, A>, QModel<I>?, A>
      where I : QInterface, I : QType, A : ArgBuilder

  interface ConfigurableQueryQuery<I, A> : ConfigurableQuery<InterfaceStub<I, A>, A>
      where I : QInterface, I : QType, A : ArgBuilder


  private class QueryImpl<I>(val qproperty: GraphQlProperty) : Query<I> where I : QInterface, I : QType {
    override fun invoke(arguments: ArgBuilder?, scope: (InterfaceStub<I, ArgBuilder>.() -> Unit)?): InterfaceStub<I, ArgBuilder> =
        InterfaceAdapterImpl<I, ArgBuilder>(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalConfigQueryQueryImpl<I, A>(val qproperty: GraphQlProperty) : OptionalConfigQueryQuery<I, A>
      where I : QInterface, I : QType, A : ArgBuilder {

    // TODO factory method on delegate straight to the QField instance to reduce object overhead
    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<QModel<I>?> =
        InterfaceAdapterImpl<I, A>(qproperty, null).provideDelegate(inst, property)

    override fun invoke(arguments: A, scope: (InterfaceStub<I, A>.() -> Unit)?): InterfaceStub<I, A> =
        InterfaceAdapterImpl<I, A>(qproperty, arguments).applyNotNull(scope)
  }

  private class ConfigurableQueryQueryImpl<I, A>(val qproperty: GraphQlProperty) : ConfigurableQueryQuery<I, A>
      where I : QInterface, I : QType, A : ArgBuilder {
    override fun invoke(arguments: A, scope: (InterfaceStub<I, A>.() -> Unit)?): InterfaceStub<I, A> =
        InterfaceAdapterImpl<I, A>(qproperty, arguments).applyNotNull(scope)
  }
}
package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.FragmentStub
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.adapters.InterfaceAdapterImpl
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.adapters.applyNotNull
import com.prestongarno.ktq.hooks.ConfiguredQuery
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfiguration
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
    ): OptionalConfigQuery<I, A>
        where I : QInterface, I : QType, A : ArgBuilder =
        OptionalConfigQueryImpl(qproperty)

    @PublishedApi internal fun <I, A> argStub(
        qproperty: GraphQlProperty
    ): ConfigurableQuery<I, A>
        where I : QInterface, I : QType, A : ArgBuilder =
        ConfigurableQueryImpl(qproperty)
  }

  interface Query<I> : NoArgConfig<InterfaceStub<I, ArgBuilder>, QModel<I>?> where I : QInterface, I : QType

  interface OptionalConfigQuery<I, A> : OptionalConfiguration<InterfaceStub<I, A>, QModel<I>?, A>
      where I : QInterface, I : QType, A : ArgBuilder

  interface ConfigurableQuery<I, A> : ConfiguredQuery<InterfaceStub<I, A>, A>
      where I : QInterface, I : QType, A : ArgBuilder


  private class QueryImpl<I>(val qproperty: GraphQlProperty) : Query<I> where I : QInterface, I : QType {
    override fun invoke(arguments: ArgBuilder?, scope: (InterfaceStub<I, ArgBuilder>.() -> Unit)?): InterfaceStub<I, ArgBuilder> =
        InterfaceAdapterImpl<I, ArgBuilder>(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalConfigQueryImpl<I, A>(val qproperty: GraphQlProperty) : OptionalConfigQuery<I, A>
      where I : QInterface, I : QType, A : ArgBuilder {

    // TODO factory method on delegate straight to the QField instance to reduce object overhead
    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<QModel<I>?> =
        InterfaceAdapterImpl<I, A>(qproperty, null).provideDelegate(inst, property)

    override fun invoke(arguments: A, scope: (InterfaceStub<I, A>.() -> Unit)?): InterfaceStub<I, A> =
        InterfaceAdapterImpl<I, A>(qproperty, arguments).applyNotNull(scope)
  }

  private class ConfigurableQueryImpl<I, A>(val qproperty: GraphQlProperty) : ConfigurableQuery<I, A>
      where I : QInterface, I : QType, A : ArgBuilder {
    override fun invoke(arguments: A, scope: (InterfaceStub<I, A>.() -> Unit)?): InterfaceStub<I, A> =
        InterfaceAdapterImpl<I, A>(qproperty, arguments).applyNotNull(scope)
  }
}
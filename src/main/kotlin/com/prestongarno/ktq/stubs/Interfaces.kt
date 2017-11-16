package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.InterfaceAdapterImpl
import com.prestongarno.ktq.properties.GraphQlProperty

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

  interface Query<I> : SchemaStub where I : QInterface, I : QType {
    operator fun invoke(
        arguments: ArgBuilder? = ArgBuilder(),
        scope: InterfaceStub<I, ArgBuilder>.() -> Unit
    ): InterfaceStub<I, ArgBuilder>
  }

  interface OptionalConfigQuery<I, A> : SchemaStub where I : QInterface, I : QType, A : ArgBuilder {

    operator fun invoke(
        scope: FragmentStub<I>.() -> Unit
    ): FragmentStub<I>

    operator fun invoke(
        arguments: A,
        scope: InterfaceStub<I, A>.() -> Unit
    ): InterfaceStub<I, A>

  }

  interface ConfigurableQuery<I, A> : SchemaStub where I : QInterface, I : QType, A : ArgBuilder {

    operator fun invoke(
        arguments: A,
        scope: InterfaceStub<I, A>.() -> Unit
    ): InterfaceStub<I, A>

  }


  private class QueryImpl<I>(val qproperty: GraphQlProperty) : Query<I> where I : QInterface, I : QType {

    override fun invoke(arguments: ArgBuilder?, scope: InterfaceStub<I, ArgBuilder>.() -> Unit): InterfaceStub<I, ArgBuilder> =
        InterfaceAdapterImpl<I, ArgBuilder>(qproperty, arguments ?: ArgBuilder()).apply(scope)

  }

  private class OptionalConfigQueryImpl<I, A>(val qproperty: GraphQlProperty) : OptionalConfigQuery<I, A>
      where I : QInterface, I : QType, A : ArgBuilder {

    override fun invoke(scope: FragmentStub<I>.() -> Unit): FragmentStub<I> =
        InterfaceAdapterImpl<I, A>(qproperty, null).apply(scope)

    override fun invoke(arguments: A, scope: InterfaceStub<I, A>.() -> Unit): InterfaceStub<I, A> =
        InterfaceAdapterImpl<I, A>(qproperty, arguments).apply(scope)

  }

  private class ConfigurableQueryImpl<I, A>(val qproperty: GraphQlProperty) : ConfigurableQuery<I, A>
      where I : QInterface, I : QType, A : ArgBuilder {

    override fun invoke(arguments: A, scope: InterfaceStub<I, A>.() -> Unit): InterfaceStub<I, A> =
        InterfaceAdapterImpl<I, A>(qproperty, arguments).apply(scope)

  }

}
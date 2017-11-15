package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.newInterfaceListStub
import com.prestongarno.ktq.properties.GraphQlProperty

interface InterfaceListStub<I, out A> :
    FragmentStub<I>,
    DelegateProvider<List<QModel<I>>>
    where I : QType,
          I : QInterface,
          A : ArgBuilder {

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
        arguments: ArgBuilder?,
        scope: InterfaceListStub<I, ArgBuilder>.() -> Unit
    ) : InterfaceListStub<I, ArgBuilder>

  }

  interface OptionalConfigQuery<I, A> : ConfigurableQuery<I, A>
      where I : QInterface,
            I : QType,
            A : ArgBuilder {

    /** Create stub for field without any arguments */
    operator fun invoke(
        scope: FragmentStub<I>.() -> Unit
    ) : InterfaceListStub<I, A>

  }

  interface ConfigurableQuery<I, A> : SchemaStub
      where I : QInterface,
            I : QType,
            A : ArgBuilder {

    operator fun invoke(
        arguments: A,
        scope: InterfaceListStub<I, A>.() -> Unit
    ) : InterfaceListStub<I, A>

  }

  private class QueryImpl<I>(val qproperty: GraphQlProperty) : Query<I> where I : QInterface, I : QType {
    override fun invoke(arguments: ArgBuilder?, scope: InterfaceListStub<I, ArgBuilder>.() -> Unit)
        : InterfaceListStub<I, ArgBuilder> =
        newInterfaceListStub<I, ArgBuilder>(qproperty, arguments ?: ArgBuilder()).apply(scope)
  }

  private class OptionalConfigQueryImpl<I, A>(
      val qproperty: GraphQlProperty
  ) : OptionalConfigQuery<I, A>
      where I : QInterface,
            I : QType,
            A : ArgBuilder {

    override fun invoke(arguments: A, scope: InterfaceListStub<I, A>.() -> Unit): InterfaceListStub<I, A> =
        newInterfaceListStub<I, A>(qproperty, arguments).apply(scope)

    override fun invoke(scope: FragmentStub<I>.() -> Unit): InterfaceListStub<I, A> =
      newInterfaceListStub<I, A>(qproperty, null).apply(scope)
  }

  private class ConfigurableQueryImpl<I, A>(
      val qproperty: GraphQlProperty
  ): ConfigurableQuery<I, A>
      where I : QInterface,
            I : QType,
            A : ArgBuilder {

    override fun invoke(
        arguments: A, scope: InterfaceListStub<I, A>.() -> Unit
    ): InterfaceListStub<I, A> =
        newInterfaceListStub<I, A>(qproperty, arguments).apply(scope)
  }
}
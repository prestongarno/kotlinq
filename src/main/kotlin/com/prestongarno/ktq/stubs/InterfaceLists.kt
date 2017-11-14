package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.newInterfaceListStub
import com.prestongarno.ktq.properties.GraphQlProperty

interface InterfaceListStub<I, out A> :
    FragmentStub<I>,
    DelegateProvider<List<QModel<I>>>
    where I : QType,
          I : QInterface,
          A : ArgBuilder {

  fun config(argumentScope: A.() -> Unit)

  interface Query<I> : SchemaStub where I : QInterface, I : QType {
    operator fun invoke(arguments: ArgBuilder?, scope: InterfaceListStub<I, ArgBuilder>.() -> Unit) : InterfaceListStub<I, ArgBuilder>
  }

  interface OptionalConfigQuery<I, A> : ConfigurableQuery<I, A> where I : QInterface, I : QType, A : ArgBuilder {
    /** Create stub for field without any arguments */
    operator fun invoke(scope: FragmentStub<I>.() -> Unit) : InterfaceListStub<I, A>
  }

  interface ConfigurableQuery<I, A> : SchemaStub where I : QInterface, I : QType, A : ArgBuilder {
    operator fun invoke(arguments: A, scope: InterfaceListStub<I, A>.() -> Unit) : InterfaceListStub<I, A>
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
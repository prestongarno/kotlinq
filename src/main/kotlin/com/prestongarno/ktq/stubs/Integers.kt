package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.applyNotNull
import com.prestongarno.ktq.adapters.bind
import com.prestongarno.ktq.adapters.toMap
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface IntDelegate<out A : ArgBuilder> : ScalarDelegate<IntStub> {

  var default : Int

  fun config(scope: A.() -> Unit)

  interface Query {

    operator fun invoke(
        arguments: ArgBuilder? = null,
        scope: (IntDelegate<ArgBuilder>.() -> Unit)? = null
    ): IntDelegate<ArgBuilder>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): IntStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfig<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (IntDelegate<A>.() -> Unit)?
    ): IntDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): IntStub
  }

  interface ConfigurableQuery<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (IntDelegate<A>.() -> Unit)? = null
    ): IntDelegate<A>
  }

  private class QueryImpl(val qproperty: GraphQlProperty) : IntDelegate.Query {
    override fun invoke(
        arguments: ArgBuilder?, scope: (IntDelegate<ArgBuilder>.() -> Unit)?
    ) = IntDelegateImpl(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : IntDelegate.OptionalConfig<A> {

    override fun invoke(arguments: A, scope: (IntDelegate<A>.() -> Unit)?): IntDelegate<A> =
        IntDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntStub = IntStub(qproperty).bind(inst)
  }

  private class ConfigurableQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : IntDelegate.ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (IntDelegate<A>.() -> Unit)?): IntDelegate<A> =
        IntDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private class IntDelegateImpl<out A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    val argBuilder: A? = null
) : IntDelegate<A> {

  override var default: Int = 0

  override fun config(scope: A.() -> Unit) {
    argBuilder?.scope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntStub =
      IntStub(qproperty, argBuilder.toMap(), default).bind(inst)
}


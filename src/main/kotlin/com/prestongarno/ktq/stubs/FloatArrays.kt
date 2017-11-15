package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.applyNotNull
import com.prestongarno.ktq.adapters.bind
import com.prestongarno.ktq.adapters.toMap
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface FloatArrayDelegate<out A : ArgBuilder> : ScalarArrayDelegate<FloatArrayStub> {

  var default: FloatArray?

  fun config(scope: A.() -> Unit)

  interface Query {
    operator fun invoke(
        arguments: ArgBuilder? = null,
        scope: (FloatArrayDelegate<ArgBuilder>.() -> Unit)? = null
    ): FloatArrayDelegate<ArgBuilder>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): FloatArrayStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfigQuery<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (FloatArrayDelegate<A>.() -> Unit)?
    ): FloatArrayDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): FloatArrayStub

  }

  interface ConfigurableQuery<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (FloatArrayDelegate<A>.() -> Unit)? = null
    ): FloatArrayDelegate<A>
  }

  private class QueryImpl(val qproperty: GraphQlProperty) : Query {
    override fun invoke(arguments: ArgBuilder?, scope: (FloatArrayDelegate<ArgBuilder>.() -> Unit)?
    ) = FloatArrayDelegateImpl<ArgBuilder>(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalConfigQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : OptionalConfigQuery<A> {

    override fun invoke(arguments: A, scope: (FloatArrayDelegate<A>.() -> Unit)?): FloatArrayDelegate<A> =
        FloatArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): FloatArrayStub = FloatArrayStub(qproperty).bind(inst)
  }


  private class ConfigurableQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (FloatArrayDelegate<A>.() -> Unit)?): FloatArrayDelegate<A> =
        FloatArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private class FloatArrayDelegateImpl<out A : ArgBuilder>(
    private val qproperty: GraphQlProperty,
    private val argBuilder: A?
) : FloatArrayDelegate<A>  {

  override var default: FloatArray? = null

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): FloatArrayStub =
      FloatArrayStub(qproperty, default, argBuilder.toMap()).bind(inst)

  override fun config(scope: A.() -> Unit) { argBuilder?.scope() }
}


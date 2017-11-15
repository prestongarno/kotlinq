package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.applyNotNull
import com.prestongarno.ktq.adapters.bind
import com.prestongarno.ktq.adapters.toMap
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface IntArrayDelegate<out A : ArgBuilder> : ScalarArrayDelegate<IntArrayStub> {

  var default: IntArray?

  fun config(scope: A.() -> Unit)

  interface Query {
    operator fun invoke(
        arguments: ArgBuilder? = null,
        scope: (IntArrayDelegate<ArgBuilder>.() -> Unit)? = null
    ): IntArrayDelegate<ArgBuilder>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): IntArrayStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfigQuery<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (IntArrayDelegate<A>.() -> Unit)?
    ): IntArrayDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): IntArrayStub

  }

  interface ConfigurableQuery<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (IntArrayDelegate<A>.() -> Unit)? = null
    ): IntArrayDelegate<A>
  }

  private class QueryImpl(val qproperty: GraphQlProperty) : Query {
    override fun invoke(arguments: ArgBuilder?, scope: (IntArrayDelegate<ArgBuilder>.() -> Unit)?
    ) = IntArrayDelegateImpl<ArgBuilder>(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalConfigQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : OptionalConfigQuery<A> {

    override fun invoke(arguments: A, scope: (IntArrayDelegate<A>.() -> Unit)?): IntArrayDelegate<A> =
        IntArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntArrayStub = IntArrayStub(qproperty).bind(inst)
  }


  private class ConfigurableQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (IntArrayDelegate<A>.() -> Unit)?): IntArrayDelegate<A> =
        IntArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private class IntArrayDelegateImpl<out A : ArgBuilder>(
    private val qproperty: GraphQlProperty,
    private val argBuilder: A?
) : IntArrayDelegate<A>  {

  override var default: IntArray? = null

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntArrayStub =
      IntArrayStub(qproperty, default, argBuilder.toMap()).bind(inst)

  override fun config(scope: A.() -> Unit) { argBuilder?.scope() }
}


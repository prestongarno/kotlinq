package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.applyNotNull
import com.prestongarno.ktq.adapters.bind
import com.prestongarno.ktq.adapters.toMap
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface BooleanArrayDelegate<out A : ArgBuilder> : ScalarArrayDelegate<BooleanArrayStub> {

  var default: BooleanArray?

  fun config(scope: A.() -> Unit)

  interface Query {
    operator fun invoke(
        arguments: ArgBuilder? = null,
        scope: (BooleanArrayDelegate<ArgBuilder>.() -> Unit)? = null
    ): BooleanArrayDelegate<ArgBuilder>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): BooleanArrayStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfigQuery<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (BooleanArrayDelegate<A>.() -> Unit)?
    ): BooleanArrayDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): BooleanArrayStub

  }

  interface ConfigurableQuery<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (BooleanArrayDelegate<A>.() -> Unit)? = null
    ): BooleanArrayDelegate<A>
  }

  private class QueryImpl(val qproperty: GraphQlProperty) : Query {
    override fun invoke(arguments: ArgBuilder?, scope: (BooleanArrayDelegate<ArgBuilder>.() -> Unit)?
    ) = BooleanArrayDelegateImpl<ArgBuilder>(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalConfigQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : OptionalConfigQuery<A> {

    override fun invoke(arguments: A, scope: (BooleanArrayDelegate<A>.() -> Unit)?): BooleanArrayDelegate<A> =
        BooleanArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): BooleanArrayStub = BooleanArrayStub(qproperty).bind(inst)
  }


  private class ConfigurableQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (BooleanArrayDelegate<A>.() -> Unit)?): BooleanArrayDelegate<A> =
        BooleanArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private class BooleanArrayDelegateImpl<out A : ArgBuilder>(
    private val qproperty: GraphQlProperty,
    private val argBuilder: A?
) : BooleanArrayDelegate<A>  {

  override var default: BooleanArray? = null

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): BooleanArrayStub =
      BooleanArrayStub(qproperty, default, argBuilder.toMap()).bind(inst)

  override fun config(scope: A.() -> Unit) { argBuilder?.scope() }
}


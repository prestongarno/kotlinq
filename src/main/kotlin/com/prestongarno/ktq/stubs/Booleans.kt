package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.applyNotNull
import com.prestongarno.ktq.adapters.bind
import com.prestongarno.ktq.adapters.toMap
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface BooleanDelegate<out A : ArgBuilder> : ScalarDelegate<BooleanStub> {

  var default : Boolean

  fun config(scope: A.() -> Unit)

  interface Query {

    operator fun invoke(
        arguments: ArgBuilder? = null,
        scope: (BooleanDelegate<ArgBuilder>.() -> Unit)? = null
    ): BooleanDelegate<ArgBuilder>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): BooleanStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfigQuery<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (BooleanDelegate<A>.() -> Unit)?
    ): BooleanDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): BooleanStub
  }

  interface ConfigurableQuery<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (BooleanDelegate<A>.() -> Unit)? = null
    ): BooleanDelegate<A>
  }

  private class QueryImpl(val qproperty: GraphQlProperty) : BooleanDelegate.Query {
    override fun invoke(
        arguments: ArgBuilder?, scope: (BooleanDelegate<ArgBuilder>.() -> Unit)?
    ) = BooleanDelegateImpl(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalConfigQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : BooleanDelegate.OptionalConfigQuery<A> {

    override fun invoke(arguments: A, scope: (BooleanDelegate<A>.() -> Unit)?): BooleanDelegate<A> =
        BooleanDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): BooleanStub = BooleanStub(qproperty).bind(inst)
  }

  private class ConfigurableQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : BooleanDelegate.ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (BooleanDelegate<A>.() -> Unit)?): BooleanDelegate<A> =
        BooleanDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private class BooleanDelegateImpl<out A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    val argBuilder: A? = null
) : BooleanDelegate<A> {

  override var default: Boolean = false

  override fun config(scope: A.() -> Unit) {
    argBuilder?.scope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): BooleanStub =
      BooleanStub(qproperty, argBuilder.toMap(), default).bind(inst)
}

package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.applyNotNull
import com.prestongarno.ktq.adapters.bind
import com.prestongarno.ktq.adapters.toMap
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface StringDelegate<out A : ArgBuilder> : ScalarDelegate<StringStub> {

  var default : String?

  fun config(scope: A.() -> Unit)

  interface Query {

    operator fun invoke(
        arguments: ArgBuilder? = null,
        scope: (StringDelegate<ArgBuilder>.() -> Unit)? = null
    ): StringDelegate<ArgBuilder>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): StringStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfig<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (StringDelegate<A>.() -> Unit)?
    ): StringDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): StringStub
  }

  interface ConfigurableQuery<A : ArgBuilder> {

    operator fun invoke(
        arguments: A,
        scope: (StringDelegate<A>.() -> Unit)? = null
    ): StringDelegate<A>
  }

  private class QueryImpl(val qproperty: GraphQlProperty) : StringDelegate.Query {
    override fun invoke(arguments: ArgBuilder?, scope: (StringDelegate<ArgBuilder>.() -> Unit)?
    ) = StringDelegateImpl(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : OptionalConfig<A> {

    override fun invoke(arguments: A, scope: (StringDelegate<A>.() -> Unit)?): StringDelegate<A> =
        StringDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): StringStub = StringStub(qproperty).bind(inst)
  }

  private class ConfigurableQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (StringDelegate<A>.() -> Unit)?): StringDelegate<A> =
        StringDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private class StringDelegateImpl<out A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    val argBuilder: A? = null
) : StringDelegate<A> {

  override var default: String? = null

  override fun config(scope: A.() -> Unit) {
    argBuilder?.scope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): StringStub =
      StringStub(qproperty, argBuilder.toMap(), default).bind(inst)
}



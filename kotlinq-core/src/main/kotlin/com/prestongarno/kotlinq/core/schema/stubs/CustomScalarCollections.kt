package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.adapters.CustomScalarStubImpl
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlPropertyContext
import com.prestongarno.kotlinq.core.properties.contextBuilder
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.schema.CustomScalar

typealias CBlock<T, V, A> = CustomScalarStub<T, V, A>.() -> Unit

interface CustomScalarListStub {

  interface NoArg<T : CustomScalar> {
    operator fun <V : Any> invoke(
        mapper: CustomScalarStub.Mapper<V>,
        arguments: ArgBuilder? = ArgBuilder(),
        block: CBlock<T, V, ArgBuilder> = empty()
    ): DelegateProvider<List<V>>
  }

  interface OptionallyConfigured<T : CustomScalar, A : ArgumentSpec> : Configured<T, A> {
    operator fun <V : Any> invoke(
        mapper: CustomScalarStub.Mapper<V>,
        arguments: ArgBuilder = ArgBuilder(),
        block: CBlock<T, V, ArgBuilder> = empty()
    ): DelegateProvider<List<V>>
  }

  interface Configured<T : CustomScalar, A : ArgumentSpec> {
    operator fun <V : Any> invoke(
        mapper: CustomScalarStub.Mapper<V>,
        arguments: A,
        block: CBlock<T, V, A> = empty()
    ): DelegateProvider<List<V>>
  }

  companion object {

    internal
    fun <T : CustomScalar> noArg()
        : GraphQlPropertyContext.Companion.Builder<CustomScalarListStub.NoArg<T>> =
        contextBuilder { CustomNoArgImpl<T>(it) }

    internal
    fun <T : CustomScalar, A : ArgumentSpec> optionallyConfigured()
        : GraphQlPropertyContext.Companion.Builder<CustomScalarListStub.OptionallyConfigured<T, A>> =
        contextBuilder { ConfiguredImpl<T, A>(it) }

    internal
    fun <T : CustomScalar, A : ArgumentSpec> configured()
        : GraphQlPropertyContext.Companion.Builder<CustomScalarListStub.Configured<T, A>> =
        contextBuilder { ConfiguredImpl<T, A>(it) }
  }
}

private
class ConfiguredImpl<T : CustomScalar, A : ArgumentSpec>(val property: GraphQlProperty)
  : CustomScalarListStub.OptionallyConfigured<T, A> {


  override fun <V : Any> invoke(
      mapper: CustomScalarStub.Mapper<V>,
      arguments: ArgBuilder,
      block: CBlock<T, V, ArgBuilder>
  ): DelegateProvider<List<V>> = delegateProvider { inst, _ ->
    CustomScalarStubImpl<T, V, ArgBuilder>(mapper, arguments)
        .apply(block)
        .toDelegate(property)
        .asList()
        .bindToContext(inst)
  }

  override fun <V : Any> invoke(
      mapper: CustomScalarStub.Mapper<V>,
      arguments: A,
      block: CBlock<T, V, A>
  ): DelegateProvider<List<V>> = delegateProvider { inst, _ ->
    CustomScalarStubImpl<T, V, A>(mapper, arguments)
        .apply(block)
        .toDelegate(property)
        .asList()
        .bindToContext(inst)
  }
}

private
class CustomNoArgImpl<T : CustomScalar>(val property: GraphQlProperty) : CustomScalarListStub.NoArg<T> {

  override fun <V : Any> invoke(
      mapper: CustomScalarStub.Mapper<V>,
      arguments: ArgBuilder?,
      block: CBlock<T, V, ArgBuilder>
  ): DelegateProvider<List<V>> = delegateProvider { inst, _ ->
    CustomScalarStubImpl<T, V, ArgBuilder>(mapper, arguments)
        .apply(block)
        .toDelegate(property)
        .asList()
        .bindToContext(inst)
  }
}

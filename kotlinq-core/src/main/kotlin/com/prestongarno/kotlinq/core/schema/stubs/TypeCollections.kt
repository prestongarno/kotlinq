package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.TypeStubAdapter
import com.prestongarno.kotlinq.core.adapters.toMap
import com.prestongarno.kotlinq.core.adapters.typeAdapter
import com.prestongarno.kotlinq.core.api.DefaultBuilderImpl
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Always
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Never
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Sometimes
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Companion.newBuilder
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.providingInstead
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlProperty.Companion.from
import com.prestongarno.kotlinq.core.properties.PropertyType
import com.prestongarno.kotlinq.core.schema.QType
import kotlin.reflect.KClass

interface TypeListStub<in E : QType> {

  operator fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.Context<List<U>>


  interface NoArg<in E : QType> : TypeListStub<E> {
    override fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.NoArgContext<List<U>>
  }


  interface OptionallyConfigured<in E : QType, A : ArgumentSpec> : TypeListStub<E> {
    override fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.OptionallyConfiguredContext<List<U>, A>
  }


  interface Configured<in E : QType, A : ArgumentSpec> : TypeListStub<E> {
    override fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.ConfiguredContext<List<U>, A>
  }
}


internal
sealed class TypeListStubImpl<in E : QType>(val typeName: String, propertyName: String) {

  abstract fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.Context<List<U>>

  val qproperty: GraphQlProperty = from(typeName, false, propertyName, PropertyType.OBJECT)

  class Configured<in E : QType, A : ArgumentSpec>(
      clazz: KClass<E>,
      propertyName: String
  ) : TypeListStubImpl<E>(clazz.graphQlName(), propertyName), TypeListStub.Configured<E, A> {

    override fun <U : QModel<E>> invoke(init: () -> U) = build(init)

    override fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.ConfiguredContext<List<U>, A> =
        newBuilder<U>().withArgs<A>() takingArguments ::Always resultingIn adapter(init) providingInstead List::class
  }


  class OptionallyConfigured<in E : QType, A : ArgumentSpec>(
      clazz: KClass<E>,
      propertyName: String
  ) : TypeListStubImpl<E>(clazz.graphQlName(), propertyName), TypeListStub.OptionallyConfigured<E, A> {

    override fun <U : QModel<E>> invoke(init: () -> U) = build(init)

    override fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.OptionallyConfiguredContext<List<U>, A> {
      return newBuilder<U>().withArgs<A>() takingArguments ::Sometimes resultingIn adapter(init) providingInstead List::class
    }

  }


  class NoArg<in E : QType>(
      clazz: KClass<E>,
      propertyName: String
  ) : TypeListStubImpl<E>(clazz.graphQlName(), propertyName), TypeListStub.NoArg<E> {

    override fun <U : QModel<E>> invoke(init: () -> U) = build(init)

    override fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.NoArgContext<List<U>> =
        newBuilder<U>() takingArguments ::Never resultingIn adapter(init) providingInstead List::class
  }
}


private
fun <U : QModel<E>, E : QType> TypeListStubImpl<E>.adapter(init: () -> U)
    : (Pair<ArgumentSpec?, DefaultBuilderImpl<U, ArgumentSpec>>) -> GraphqlPropertyDelegate<U> =
    { typeAdapter(qproperty, init, it) }



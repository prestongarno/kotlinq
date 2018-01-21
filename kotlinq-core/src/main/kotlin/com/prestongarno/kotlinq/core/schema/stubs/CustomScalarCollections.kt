package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.Model
import com.prestongarno.kotlinq.core.adapters.CustomScalarField
import com.prestongarno.kotlinq.core.adapters.toMap
import com.prestongarno.kotlinq.core.api.DslEvaluationResult
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Always
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Never
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Sometimes
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Companion.newBuilder
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.providingInstead
import com.prestongarno.kotlinq.core.api.schemaProvider
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.PropertyType
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.schema.CustomScalar
import java.io.InputStream
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


interface CustomScalarListStub<X : CustomScalar> {

  fun <T : Any> fromString(deserializer: (String) -> T?): GraphqlDslBuilder.Context<List<T>>

  fun <T : Any> fromStream(deserializer: (InputStream) -> T?): GraphqlDslBuilder.Context<List<T>>


  interface NoArg<X : CustomScalar> : CustomScalarListStub<X>, DelegateProvider<List<String>> {
    override fun <T : Any> fromString(deserializer: (String) -> T?): GraphqlDslBuilder.NoArgContext<List<T>>
    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?): GraphqlDslBuilder.NoArgContext<List<T>>
  }


  interface Configured<X : CustomScalar, A : ArgumentSpec> : CustomScalarListStub<X> {
    override fun <T : Any> fromString(deserializer: (String) -> T?): GraphqlDslBuilder.ConfiguredContext<List<T>, A>
    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?): GraphqlDslBuilder.ConfiguredContext<List<T>, A>
  }


  interface OptionallyConfigured<X : CustomScalar, A : ArgumentSpec> : CustomScalarListStub<X>, DelegateProvider<List<String>> {
    override fun <T : Any> fromString(deserializer: (String) -> T?): GraphqlDslBuilder.OptionallyConfiguredContext<List<T>, A>
    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?): GraphqlDslBuilder.OptionallyConfiguredContext<List<T>, A>
  }

}

internal
sealed class CustomScalarListPreDelegate<X, T : Any>(
    val graphqlProperty: GraphQlProperty,
    val deserializer: Mapper<*, T>
) where X : GraphqlDslBuilder.Context<List<T>>,
        X : GraphQlDelegateContext<List<T>, *> {

  fun toDelegate(result: DslEvaluationResult<T>) = CustomScalarField(
      graphqlProperty,
      result.first.toMap(),
      deserializer,
      result.second.default
  )

  abstract fun newContext(): X


  class ConfiguredDelegate<T : Any, A : ArgumentSpec>(qproperty: GraphQlProperty, deserializer: Mapper<*, T>)
    : CustomScalarListPreDelegate<GraphQlDelegateContext.Configured<List<T>, A>, T>(qproperty, deserializer) {

    override fun newContext() =
        newBuilder<T>().withArgs<A>() takingArguments ::Always resultingIn this::toDelegate providingInstead List::class
  }


  class OptionallyConfiguredDelegate<T : Any, A : ArgumentSpec>(qproperty: GraphQlProperty, deserializer: Mapper<*, T>)
    : CustomScalarListPreDelegate<GraphQlDelegateContext.OptionallyConfigured<List<T>, A>, T>(qproperty, deserializer) {

    override fun newContext() =
        newBuilder<T>().withArgs<A>() takingArguments ::Sometimes resultingIn this::toDelegate providingInstead List::class
  }


  class NoArgDelegate<T : Any>(qproperty: GraphQlProperty, deserializer: Mapper<*, T>)
    : CustomScalarListPreDelegate<GraphQlDelegateContext.NoArg<List<T>>, T>(qproperty, deserializer) {

    override fun newContext() =
        newBuilder<T>() takingArguments ::Never resultingIn this::toDelegate providingInstead List::class

  }


}


internal
sealed class CustomListStubHandle<out E : CustomScalar>(qpropertyName: String, typeName: String) {

  val qproperty = GraphQlProperty.from(typeName, false, qpropertyName, PropertyType.CUSTOM_SCALAR)


  class NoArgImpl<E : CustomScalar>(qpropertyName: String, typeName: String)
    : CustomListStubHandle<E>(qpropertyName, typeName), CustomScalarListStub.NoArg<E> {

    override fun provideDelegate(inst: Model<*>, property: KProperty<*>) = CustomScalarListPreDelegate
        .NoArgDelegate(qproperty, Mapper.StringMapper { it })
        .newContext()
        .provideDelegate(inst, property)
        .bindToContext(inst)

    override fun <T : Any> fromString(deserializer: (String) -> T?) = CustomScalarListPreDelegate
        .NoArgDelegate(qproperty, Mapper.StringMapper(deserializer))
        .newContext()

    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?) = CustomScalarListPreDelegate
        .NoArgDelegate(qproperty, Mapper.StreamMapper(deserializer))
        .newContext()
  }


  class ConfiguredImpl<E : CustomScalar, A : ArgumentSpec>(qpropertyName: String, typeName: String)
    : CustomListStubHandle<E>(qpropertyName, typeName), CustomScalarListStub.Configured<E, A> {

    override fun <T : Any> fromString(deserializer: (String) -> T?) = CustomScalarListPreDelegate
        .ConfiguredDelegate<T, A>(qproperty, Mapper.StringMapper(deserializer))
        .newContext()

    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?) = CustomScalarListPreDelegate
        .ConfiguredDelegate<T, A>(qproperty, Mapper.StreamMapper(deserializer))
        .newContext()
  }


  class OptionallyConfiguredImpl<E : CustomScalar, A : ArgumentSpec>(qpropertyName: String, typeName: String)
    : CustomListStubHandle<E>(qpropertyName, typeName),
      CustomScalarListStub.OptionallyConfigured<E, A> {

    override fun provideDelegate(inst: Model<*>, property: KProperty<*>) =
        CustomScalarListPreDelegate
            .OptionallyConfiguredDelegate<String, A>(qproperty, Mapper.StringMapper { it })
            .newContext()
            .provideDelegate(inst, property)
            .bindToContext(inst)

    override fun <T : Any> fromString(deserializer: (String) -> T?) = CustomScalarListPreDelegate
        .OptionallyConfiguredDelegate<T, A>(qproperty, Mapper.StringMapper(deserializer))
        .newContext()

    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?) = CustomScalarListPreDelegate
        .OptionallyConfiguredDelegate<T, A>(qproperty, Mapper.StreamMapper(deserializer))
        .newContext()
  }

  companion object {

    internal
    fun <E : CustomScalar> stub(clazz: KClass<E>) = schemaProvider {
      create<CustomListStubHandle.NoArgImpl<E>>("${clazz.simpleName}", it.second.name)
    }

    internal
    fun <E : CustomScalar, A : ArgumentSpec> optionallyConfiguredStub(clazz: KClass<E>) = schemaProvider {
      create<CustomListStubHandle.OptionallyConfiguredImpl<E, A>>("${clazz.simpleName}", it.second.name)
    }

    internal
    fun <E : CustomScalar, A : ArgumentSpec> configuredStub(clazz: KClass<E>) = schemaProvider {
      create<CustomListStubHandle.ConfiguredImpl<E, A>>("${clazz.simpleName}", it.second.name)
    }

    private
    inline fun <reified T : CustomListStubHandle<CustomScalar>> create(typeName: String, propertyName: String): T = when (T::class) {
      CustomListStubHandle.NoArgImpl::class -> CustomListStubHandle.NoArgImpl<CustomScalar>(propertyName, typeName) as T
      CustomListStubHandle.OptionallyConfiguredImpl::class -> CustomListStubHandle.OptionallyConfiguredImpl<CustomScalar, ArgumentSpec>(propertyName, typeName) as T
      CustomListStubHandle.ConfiguredImpl::class -> CustomListStubHandle.ConfiguredImpl<CustomScalar, ArgumentSpec>(propertyName, typeName) as T
      else -> TODO("learn me some algebraic data types in kotlin")
    }

  }

}

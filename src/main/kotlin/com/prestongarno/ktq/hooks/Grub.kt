package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarArgBuilder
import com.prestongarno.ktq.CustomScalarConfigStub
import com.prestongarno.ktq.CustomScalarInitStub
import com.prestongarno.ktq.CustomScalarListArgBuilder
import com.prestongarno.ktq.CustomScalarListConfigStub
import com.prestongarno.ktq.CustomScalarListInitStub
import com.prestongarno.ktq.EnumStub
import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.PropertyImpl
import com.prestongarno.ktq.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaEnum
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.UnionConfigAdapter
import com.prestongarno.ktq.UnionInitStub
import com.prestongarno.ktq.adapters.BooleanArrayDelegate
import com.prestongarno.ktq.adapters.BooleanDelegate
import com.prestongarno.ktq.adapters.CustomScalarAdapter
import com.prestongarno.ktq.adapters.CustomScalarListAdapter
import com.prestongarno.ktq.adapters.EnumAdapter
import com.prestongarno.ktq.adapters.FloatArrayDelegate
import com.prestongarno.ktq.adapters.FloatDelegate
import com.prestongarno.ktq.adapters.IntegerArrayDelegate
import com.prestongarno.ktq.adapters.IntegerDelegate
import com.prestongarno.ktq.adapters.StringArrayDelegate
import com.prestongarno.ktq.adapters.StringDelegate
import com.prestongarno.ktq.adapters.TypeListAdapter
import com.prestongarno.ktq.adapters.TypeStubAdapter
import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * Grand Unified Bootloader for SchemaType definitions/stubs.
 * solves the problem of not knowing the field's GraphQL prop fragment the generated createTypeStub hierarchy
 * when generating queries/payloads
 *
 * Delegation inception : this graphqlName delegate is delegated to in order to get the prop of the
 * schema field, then passes it off to the backing field, which is another delegate in order for the
 * actual delegation to happen fragment a schema model implementation
 *
 * This class does minimal work in order to reduce added complexity - it simply gets passed a function
 * which, when applied a string, produces the correct schemastub.  When delegated to by schemastub types,
 * fragment `getValue` for the schemastub it simply invokes the function with the prop of the graphqlName that it's
 * delegating to. This way, the graphqlName prop can be passed to the delegate/schemastub createTypeStub without having
 * to resort to hard-wired  &/or needlessly complex metadata methods such as (god forbid) annotations */
interface StubLoader<out T : SchemaStub> {
  operator fun getValue(inst: QSchemaType, property: KProperty<*>): T
}

interface StubProvider<out T : SchemaStub> {
  operator fun provideDelegate(inst: QSchemaType, property: KProperty<*>): StubLoader<T>
}

private class StubLoaderImpl<out T : SchemaStub>(val value: T) : StubLoader<T> {
  override operator fun getValue(inst: QSchemaType, property: KProperty<*>): T = value
}

class Grub<out T : SchemaStub>(
    private val typeName: String,
    private val isList: Boolean = false,
    private val toInit: (property: GraphQlProperty) -> T
) : StubProvider<T> {

  override operator fun provideDelegate(inst: QSchemaType, property: KProperty<*>): StubLoader<T> {
    val prop = PropertyImpl(typeName, property, isList, property.name)
    val value = toInit.invoke(prop)
    return StubLoaderImpl(value)
  }

  companion object {
    fun createStringDelegate(): StubProvider<StringDelegate<ArgBuilder>> =
        Grub("String") { StringDelegate(it, standardGenerator) }

    fun createIntDelegate(): StubProvider<IntegerDelegate<ArgBuilder>> =
        Grub("Int") { IntegerDelegate(it, standardGenerator) }

    fun createFloatDelegate(): StubProvider<FloatDelegate<ArgBuilder>> =
        Grub("Float") { FloatDelegate(it, standardGenerator) }

    fun createBooleanDelegate(): StubProvider<BooleanDelegate<ArgBuilder>> =
        Grub("Boolean") { BooleanDelegate(it, standardGenerator) }

    fun <A : ArgBuilder> createStringDelegate(arginit: (ArgBuilder) -> A) =
        Grub("String") { StringDelegate(it, arginit) }

    fun <A : ArgBuilder> createIntDelegate(arginit: (ArgBuilder) -> A) =
        Grub("Int") { IntegerDelegate(it, arginit) }

    fun <A : ArgBuilder> createFloatDelegate(arginit: (ArgBuilder) -> A)
        = Grub("Float") { FloatDelegate(it, arginit) }

    fun <A : ArgBuilder> createBooleanDelegate(arginit: (ArgBuilder) -> A)
        = Grub("Boolean") { BooleanDelegate(it, arginit) }


    fun createStringArrayDelegate() = Grub("String") { StringArrayDelegate(it, standardGenerator) }

    fun createIntArrayDelegate() = Grub("Int") { IntegerArrayDelegate(it, standardGenerator) }

    fun createFloatArrayDelegate() = Grub("Float") { FloatArrayDelegate(it, standardGenerator) }

    fun createBooleanArrayDelegate() = Grub("Boolean") { BooleanArrayDelegate(it, standardGenerator) }

    fun <A : ArgBuilder> createStringArrayDelegate(arginit: (ArgBuilder) -> A) =
        Grub("String") { StringArrayDelegate(it, arginit) }

    fun <A : ArgBuilder> createIntArrayDelegate(arginit: (ArgBuilder) -> A) =
        Grub("Int") { IntegerArrayDelegate(it, arginit) }

    fun <A : ArgBuilder> createFloatArrayDelegate(arginit: (ArgBuilder) -> A) =
        Grub("Float") { FloatArrayDelegate(it, arginit) }

    fun <A : ArgBuilder> createBooleanArrayDelegate(arginit: (ArgBuilder) -> A) =
        Grub("Boolean") { BooleanArrayDelegate(it, arginit) }


    fun <T : QSchemaType> createTypeStub(name: String): StubProvider<InitStub<T>>
        = Grub(name) { TypeStubAdapter<T, QModel<T>, ArgBuilder>(it, standardGenerator) }

    fun <T : QSchemaType, A : ArgBuilder> createTypeStub(
        name: String, arginit: (ArgBuilder) -> A
    ): StubProvider<TypeConfig<T, A>> = Grub(name) {
      TypeStubAdapter<T, QModel<T>, A>(it, arginit)
    }

    fun <T : QSchemaUnion> createUnionStub(objectModel: T, typeName: String): StubProvider<UnionInitStub<T>>
        = Grub(typeName) {
      UnionConfigAdapter.create(it, objectModel)
    }

    fun <T : QSchemaUnion, A : ArgBuilder> createUnionStub(
        objectModel: T,
        typeName: String,
        arginit: (ArgBuilder) -> A
    ): StubProvider<UnionInitStub<T>> = Grub(typeName) {
      UnionConfigAdapter.create(it, objectModel, arginit)
    }

    fun <T : CustomScalar> createCustomScalarStub(typeName: String): StubProvider<CustomScalarInitStub<T>> =
        Grub(typeName) { CustomScalarAdapter<T, QScalarMapper<T>, T, CustomScalarArgBuilder>(it, { it }) }

    fun <T : CustomScalar, A : CustomScalarArgBuilder> createCustomScalarConfig(
        typeName: String,
        arginit: (CustomScalarArgBuilder) -> A
    ): StubProvider<CustomScalarConfigStub<T, A>> =
        Grub(typeName) { CustomScalarAdapter<T, QScalarMapper<T>, T, A>(it, arginit) }

    fun <A : ArgBuilder, T : QSchemaType> createTypeConfigStub(
        typeName: String,
        arginit: (ArgBuilder) -> A
    ): StubProvider<TypeConfig<T, A>>
        = Grub(typeName) { TypeStubAdapter<T, QModel<T>, A>(it, arginit) }

    fun <T : QSchemaType> createTypeListStub(typeName: String): StubProvider<ListInitStub<T>> =
        Grub(typeName, true) { TypeListAdapter<T, QModel<T>, ArgBuilder>(it, standardGenerator) }

    fun <A : ArgBuilder, T : QSchemaType> createTypeListConfigStub(
        typeName: String,
        arginit: (ArgBuilder) -> A
    ): StubProvider<ListConfigType<T, A>> =
        Grub(typeName, true) { TypeListAdapter<T, QModel<T>, A>(it, arginit) }

    fun <T : CustomScalar> createCustomScalarListStub(typeName: String): StubProvider<CustomScalarListInitStub<T>>
        = Grub(typeName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, CustomScalarListArgBuilder>(it, { it }) }

    fun <T : CustomScalar, A : CustomScalarListArgBuilder> createCustomScalarListConfig(
        typeName: String,
        arginit: (CustomScalarListArgBuilder) -> A
    ): StubProvider<CustomScalarListConfigStub<T, A>> =
        Grub(typeName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, A>(it, arginit) }

    fun <T> createEnumStub(typeName: String, clazz: KClass<T>): StubProvider<EnumStub<T>> where T : QSchemaEnum, T : Enum<*> =
        Grub(typeName) { EnumAdapter(it, clazz, standardGenerator) }

    fun <T, A : ArgBuilder> createEnumConfigStub(
        typeName: String,
        clazz: KClass<T>,
        arginit: (ArgBuilder) -> A
    ): StubProvider<EnumStub<T>> where T : QSchemaEnum, T : Enum<*> =
        Grub(typeName, true) { EnumAdapter(it, clazz, arginit) }

    private val standardGenerator: (ArgBuilder) -> ArgBuilder = { it }
  }
}

package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarArgBuilder
import com.prestongarno.ktq.CustomScalarConfigStub
import com.prestongarno.ktq.CustomScalarInitStub
import com.prestongarno.ktq.CustomScalarListArgBuilder
import com.prestongarno.ktq.CustomScalarListConfigStub
import com.prestongarno.ktq.CustomScalarListInitStub
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.PropertyImpl
import com.prestongarno.ktq.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.UnionAdapter
import com.prestongarno.ktq.UnionInitStub
import com.prestongarno.ktq.adapters.BooleanArrayDelegate
import com.prestongarno.ktq.adapters.BooleanDelegate
import com.prestongarno.ktq.adapters.CustomScalarAdapter
import com.prestongarno.ktq.adapters.CustomScalarListAdapter
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

private class StubLoaderImpl<out T : SchemaStub>(
    val value: T
) : StubLoader<T> {
  override operator fun getValue(inst: QSchemaType, property: KProperty<*>): T = value
}

class Grub<out T : SchemaStub>(
    val typeName: String,
    val isList: Boolean = false,
    val toInit: (property: GraphQlProperty) -> T
) : StubProvider<T> {

  override operator fun provideDelegate(inst: QSchemaType, property: KProperty<*>): StubLoader<T> {
    val prop = PropertyImpl(typeName, property, isList, property.name)
    val value = toInit.invoke(prop)
    return StubLoaderImpl(value)
  }

  companion object {
    fun createStringDelegate(): StubProvider<StringDelegate<ArgBuilder>> =
        Grub("String", false) { StringDelegate(it, { it }) }

    fun createIntDelegate(): StubProvider<IntegerDelegate<ArgBuilder>> =
        Grub("Int", false) { IntegerDelegate(it, { it }) }

    fun createFloatDelegate(): StubProvider<FloatDelegate<ArgBuilder>> =
        Grub("Float", false) { FloatDelegate(it, { it }) }

    fun createBooleanDelegate(): StubProvider<BooleanDelegate<ArgBuilder>> =
        Grub("Boolean", false) { BooleanDelegate(it, { it }) }

    fun <A: ArgBuilder> createStringDelegate(arginit: (ArgBuilder) -> A): StubProvider<StringDelegate<A>> =
        Grub("String", false) { StringDelegate(it, arginit) }

    fun <A: ArgBuilder> createIntDelegate(arginit: (ArgBuilder) -> A): StubProvider<IntegerDelegate<A>> =
        Grub("Int", false) { IntegerDelegate(it, arginit) }

    fun <A: ArgBuilder> createFloatDelegate(arginit: (ArgBuilder) -> A): StubProvider<FloatDelegate<A>> =
        Grub("Float", false) { FloatDelegate(it, arginit) }

    fun <A: ArgBuilder> createBooleanDelegate(arginit: (ArgBuilder) -> A): StubProvider<BooleanDelegate<A>> =
        Grub("Boolean", false) { BooleanDelegate(it, arginit) }


    fun createStringArrayDelegate(): StubProvider<StringArrayDelegate<ArgBuilder>> =
        Grub("String", false) { StringArrayDelegate(it, { it }) }

    fun createIntArrayDelegate(): StubProvider<IntegerArrayDelegate<ArgBuilder>> =
        Grub("Int", false) { IntegerArrayDelegate(it, { it }) }

    fun createFloatArrayDelegate(): StubProvider<FloatArrayDelegate<ArgBuilder>> =
        Grub("Float", false) { FloatArrayDelegate(it, { it }) }

    fun createBooleanArrayDelegate(): StubProvider<BooleanArrayDelegate<ArgBuilder>> =
        Grub("Boolean", false) { BooleanArrayDelegate(it, { it }) }

    fun <A: ArgBuilder> createStringArrayDelegate(arginit: (ArgBuilder) -> A): StubProvider<StringArrayDelegate<A>> =
        Grub("String", false) { StringArrayDelegate(it, arginit) }

    fun <A: ArgBuilder> createIntArrayDelegate(arginit: (ArgBuilder) -> A): StubProvider<IntegerArrayDelegate<A>> =
        Grub("Int", false) { IntegerArrayDelegate(it, arginit) }

    fun <A: ArgBuilder> createFloatArrayDelegate(arginit: (ArgBuilder) -> A): StubProvider<FloatArrayDelegate<A>> =
        Grub("Float", false) { FloatArrayDelegate(it, arginit) }

    fun <A: ArgBuilder> createBooleanArrayDelegate(arginit: (ArgBuilder) -> A): StubProvider<BooleanArrayDelegate<A>> =
        Grub("Boolean", false) { BooleanArrayDelegate(it, arginit) }


    fun <T : QSchemaType> createTypeStub(name: String): StubProvider<InitStub<T>>
        = Grub(name, false) { TypeStubAdapter<T, QModel<T>, ArgBuilder>(it, { it }) }

    fun <T : QSchemaUnion> createUnionStub(objectModel: T, typeName: String): StubProvider<UnionInitStub<T>>
        = Grub(typeName, false) { UnionAdapter.create(it, objectModel) }

    //TODO write with new API/DSL hierarchy
    //fun <T : Any, A : ArgBuilder> createConfigStub(typeName: String, arginit: (ArgBuilder) -> A): StubProvider<QConfigStub<T, A>> = Grub(typeName, false) { ScalarStubAdapter<T, A>(it, arginit) }

    fun <T : CustomScalar> createCustomScalarStub(typeName: String): StubProvider<CustomScalarInitStub<T>> =
        Grub(typeName, false) { CustomScalarAdapter<T, QScalarMapper<T>, T, CustomScalarArgBuilder>(it, { it }) }

    fun <T : CustomScalar, A : CustomScalarArgBuilder> createCustomScalarConfig(
        typeName: String,
        arginit: (CustomScalarArgBuilder) -> A
    ): StubProvider<CustomScalarConfigStub<T, A>> =
        Grub(typeName, false) { CustomScalarAdapter<T, QScalarMapper<T>, T, A>(it, arginit) }

    fun <A : ArgBuilder, T : QSchemaType> createTypeConfigStub(
        simpleName: String,
        arginit: (ArgBuilder) -> A
    ): StubProvider<TypeConfiguration<T, A>>
        = Grub(simpleName, false) { TypeStubAdapter<T, QModel<T>, A>(it, arginit) }

    //TODO write with new API/DSL hierarchy
    //fun <T> createScalarListStub(simpleName: String): StubProvider<ListStub<T>> = Grub(simpleName, true) { ScalarListAdapter<T, ListArgBuilder>(it, { it }) }

    //TODO write with new API/DSL hierarchy
    //fun <A : ArgBuilder, T> createListConfigStub(simpleName: String, arginit: (ArgBuilder) -> A): StubProvider<ListConfig<T, A>> = Grub(simpleName, true) { ScalarListAdapter<T, A>(it, arginit) }

    fun <T : QSchemaType> createTypeListStub(simpleName: String): StubProvider<ListInitStub<T>> =
        Grub(simpleName, true) { TypeListAdapter<T, QModel<T>, ArgBuilder>(it, { it }) }

    fun <A : ArgBuilder, T : QSchemaType> createTypeListConfigStub(
        simpleName: String,
        arginit: (ArgBuilder) -> A
    ): StubProvider<ListConfigType<T, A>> =
        Grub(simpleName, true) { TypeListAdapter<T, QModel<T>, A>(it, arginit) }

    fun <T : CustomScalar> createCustomScalarListStub(simpleName: String): StubProvider<CustomScalarListInitStub<T>>
        = Grub(simpleName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, CustomScalarListArgBuilder>(it, { it }) }

    fun <T : CustomScalar, A : CustomScalarListArgBuilder> createCustomScalarListConfig(
        simpleName: String,
        arginit: (CustomScalarListArgBuilder) -> A
    ): StubProvider<CustomScalarListConfigStub<T, A>> =
        Grub(simpleName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, A>(it, arginit) }
  }
}

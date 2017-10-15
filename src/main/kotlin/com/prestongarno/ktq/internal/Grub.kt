package com.prestongarno.ktq.internal

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarArgBuilder
import com.prestongarno.ktq.CustomScalarConfigStub
import com.prestongarno.ktq.CustomScalarInitStub
import com.prestongarno.ktq.CustomScalarListArgBuilder
import com.prestongarno.ktq.CustomScalarListConfigStub
import com.prestongarno.ktq.CustomScalarListInitStub
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.ListArgBuilder
import com.prestongarno.ktq.ListConfig
import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.ListStub
import com.prestongarno.ktq.PropertyImpl
import com.prestongarno.ktq.QProperty
import com.prestongarno.ktq.QConfigStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.QTypeConfigStub
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.TypeArgBuilder
import com.prestongarno.ktq.TypeListArgBuilder
import com.prestongarno.ktq.UnionAdapter
import com.prestongarno.ktq.UnionInitStub
import com.prestongarno.ktq.adapters.CustomScalarAdapter
import com.prestongarno.ktq.adapters.CustomScalarListAdapter
import com.prestongarno.ktq.adapters.ScalarListAdapter
import com.prestongarno.ktq.adapters.ScalarStubAdapter
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
    val toInit: (property: QProperty) -> T
) : StubProvider<T> {

  override operator fun provideDelegate(inst: QSchemaType, property: KProperty<*>): StubLoader<T> {
    val value = toInit(PropertyImpl(typeName, property, isList, property.name))
    return StubLoaderImpl(value)
  }

  companion object {
    fun <T> createStub(typeName: String): StubProvider<Stub<T>> =
        Grub(typeName, false) { ScalarStubAdapter<T, ArgBuilder>(it, { it }) }

    fun <T : QSchemaType> createTypeStub(name: String): StubProvider<InitStub<T>>
        = Grub(name, false) { TypeStubAdapter<T, QModel<T>, TypeArgBuilder>(it, { it }) }

    fun <T : QSchemaUnion> createUnionStub(objectModel: T, typeName: String): StubProvider<UnionInitStub<T>>
        = Grub(typeName, false) { UnionAdapter.create(it, objectModel) }

    fun <T : Any, A : ArgBuilder> createConfigStub(typeName: String, arginit: (ArgBuilder) -> A): StubProvider<QConfigStub<T, A>>
        = Grub(typeName, false) { ScalarStubAdapter<T, A>(it, arginit) }

    fun <T : CustomScalar> createCustomScalarStub(typeName: String): StubProvider<CustomScalarInitStub<T>> =
        Grub(typeName, false) { CustomScalarAdapter<T, QScalarMapper<T>, T, CustomScalarArgBuilder>(it, { it }) }

    fun <T : CustomScalar, A : CustomScalarArgBuilder> createCustomScalarConfig(
        typeName: String,
        arginit: (CustomScalarArgBuilder) -> A
    ): StubProvider<CustomScalarConfigStub<T, A>> =
        Grub(typeName, false) { CustomScalarAdapter<T, QScalarMapper<T>, T, A>(it, arginit) }

    fun <A : TypeArgBuilder, T : QSchemaType> createTypeConfigStub(
        simpleName: String,
        arginit: (TypeArgBuilder) -> A
    ): StubProvider<QTypeConfigStub<T, A>>
        = Grub(simpleName, false) { TypeStubAdapter<T, QModel<T>, A>(it, arginit) }

    fun <T> createScalarListStub(simpleName: String): StubProvider<ListStub<T>>
        = Grub(simpleName, true) { ScalarListAdapter<T, ListArgBuilder>(it, { it }) }

    fun <A : ListArgBuilder, T> createListConfigStub(simpleName: String, arginit: (ListArgBuilder) -> A): StubProvider<ListConfig<T, A>>
        = Grub(simpleName, true) { ScalarListAdapter<T, A>(it, arginit) }

    fun <T : QSchemaType> createTypeListStub(simpleName: String): StubProvider<ListInitStub<T>> =
        Grub(simpleName, true) { TypeListAdapter<T, QModel<T>, TypeListArgBuilder>(it, { it }) }

    fun <A : TypeListArgBuilder, T : QSchemaType> createTypeListConfigStub(
        simpleName: String,
        arginit: (TypeListArgBuilder) -> A
    ): StubProvider<ListConfigType<T, A>> =
        Grub(simpleName, true) { TypeListAdapter<T, QModel<T>, A>(it, arginit) }

    fun <T : CustomScalar> createCustimScalarStub(simpleName: String): StubProvider<CustomScalarListInitStub<T>>
        = Grub(simpleName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, CustomScalarListArgBuilder>(it, { it }) }

    fun <T : CustomScalar, A : CustomScalarListArgBuilder> createCustomScalarListConfig(
        simpleName: String,
        arginit: (CustomScalarListArgBuilder) -> A
    ): StubProvider<CustomScalarListConfigStub<T, A>> =
        Grub(simpleName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, A>(it, arginit) }
  }
}

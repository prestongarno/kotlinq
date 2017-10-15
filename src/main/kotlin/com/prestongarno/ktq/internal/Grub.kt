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
 * solves the problem of not knowing the field's GraphQL prop fragment the generated type hierarchy
 * when generating queries/payloads
 *
 * Delegation inception : this graphqlName delegate is delegated to in order to get the prop of the
 * schema field, then passes it off to the backing field, which is another delegate in order for the
 * actual delegation to happen fragment a schema model implementation
 *
 * This class does minimal work in order to reduce added complexity - it simply gets passed a function
 * which, when applied a string, produces the correct schemastub.  When delegated to by schemastub types,
 * fragment `getValue` for the schemastub it simply invokes the function with the prop of the graphqlName that it's
 * delegating to. This way, the graphqlName prop can be passed to the delegate/schemastub type without having
 * to resort to hard-wired  &/or needlessly complex metadata methods such as (god forbid) annotations */
class Grub<out T : SchemaStub>(private val typeName: String, private val isList: Boolean = false, private val toInit: (property: QProperty) -> T) {

  constructor(inheritedProperty: QProperty, toInit: (property: QProperty) -> T) :
      this(inheritedProperty.graphqlType, inheritedProperty.isList, toInit) {
    this.prop = inheritedProperty
  }

  private var prop: QProperty? = null

  private val value: T by lazy { toInit(prop!!) }

  operator fun getValue(inst: QSchemaType, property: KProperty<*>): T {
    if (prop == null)
      prop = QProperty.from(property, typeName, isList)
    return value
  }

  companion object {
    fun <T> stub(typeName: String): Grub<Stub<T>> =
        Grub(typeName) { ScalarStubAdapter<T, ArgBuilder>(it, { it }) }

    fun <T : QSchemaType> type(name: String): Grub<InitStub<T>>
        = Grub(name) { TypeStubAdapter<T, QModel<T>, TypeArgBuilder>(it, { it }) }

    fun <T : QSchemaUnion> union(objectModel: T, typeName: String): Grub<UnionInitStub<T>>
        = Grub(typeName) { UnionAdapter.new(it, objectModel) }

    fun <T : Any, A : ArgBuilder> configStub(typeName: String, arginit: (ArgBuilder) -> A): Grub<QConfigStub<T, A>>
        = Grub(typeName) { ScalarStubAdapter<T, A>(it, arginit) }

    fun <T : CustomScalar> customScalar(typeName: String): Grub<CustomScalarInitStub<T>> =
        Grub(typeName) { CustomScalarAdapter<T, QScalarMapper<T>, T, CustomScalarArgBuilder>(it, { it }) }

    fun <T : CustomScalar, A : CustomScalarArgBuilder> customScalarConfig(
        typeName: String,
        arginit: (CustomScalarArgBuilder) -> A
    ): Grub<CustomScalarConfigStub<T, A>> =
        Grub(typeName) { CustomScalarAdapter<T, QScalarMapper<T>, T, A>(it, arginit) }

    fun <A : TypeArgBuilder, T : QSchemaType> typeConfig(
        simpleName: String,
        arginit: (TypeArgBuilder) -> A
    ): Grub<QTypeConfigStub<T, A>>
        = Grub(simpleName) { TypeStubAdapter<T, QModel<T>, A>(it, arginit) }

    fun <T> scalarList(simpleName: String): Grub<ListStub<T>>
        = Grub(simpleName, true) { ScalarListAdapter<T, ListArgBuilder>(it, { it }) }

    fun <A : ListArgBuilder, T> scalarListConfig(simpleName: String, arginit: (ListArgBuilder) -> A): Grub<ListConfig<T, A>>
        = Grub(simpleName, true) { ScalarListAdapter<T, A>(it, arginit) }

    fun <T : QSchemaType> typeList(simpleName: String): Grub<ListInitStub<T>> =
        Grub(simpleName, true) { TypeListAdapter<T, QModel<T>, TypeListArgBuilder>(it, { it }) }

    fun <A : TypeListArgBuilder, T : QSchemaType> typeListConfig(
        simpleName: String,
        arginit: (TypeListArgBuilder) -> A
    ): Grub<ListConfigType<T, A>> =
        Grub(simpleName, true) { TypeListAdapter<T, QModel<T>, A>(it, arginit) }

    fun <T : CustomScalar> customScalarList(simpleName: String): Grub<CustomScalarListInitStub<T>>
        = Grub(simpleName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, CustomScalarListArgBuilder>(it, { it }) }

    fun <T : CustomScalar, A : CustomScalarListArgBuilder> customScalarListConfig(
        simpleName: String,
        arginit: (CustomScalarListArgBuilder) -> A
    ): Grub<CustomScalarListConfigStub<T, A>> =
        Grub(simpleName, true) { CustomScalarListAdapter<T, QScalarListMapper<T>, T, A>(it, arginit) }
  }
}

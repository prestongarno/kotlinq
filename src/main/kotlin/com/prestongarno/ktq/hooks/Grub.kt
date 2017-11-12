package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.SchemaStub
import kotlin.reflect.KProperty

interface StubLoader<out T : SchemaStub> {
  operator fun getValue(inst: QSchemaType, property: KProperty<*>): T
}

interface StubProvider<out T : SchemaStub> {
  operator fun provideDelegate(inst: QSchemaType, property: KProperty<*>): StubLoader<T>
}

private class StubLoaderImpl<out T : SchemaStub>(val value: T) : StubLoader<T> {
  override operator fun getValue(inst: QSchemaType, property: KProperty<*>): T = value
}

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
@PublishedApi internal class Grub<out T : SchemaStub>(
    private val typeName: String,
    private val isList: Boolean = false,
    private val toInit: (property: GraphQlProperty) -> T
) : StubProvider<T> {

  override operator fun provideDelegate(inst: QSchemaType, property: KProperty<*>): StubLoader<T> =
      StubLoaderImpl(toInit(GraphQlProperty.from(property, typeName, isList, property.name)))
}

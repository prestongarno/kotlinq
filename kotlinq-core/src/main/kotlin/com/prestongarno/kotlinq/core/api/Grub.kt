/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.prestongarno.kotlinq.core.api

import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.properties.ConfigurableDelegateProvider
import com.prestongarno.kotlinq.core.properties.DelegateProvider
import com.prestongarno.kotlinq.core.properties.GraphQLPropertyContext
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface StubProvider<
    out T : GraphQLPropertyContext<D, RET>,
    out D : GraphqlDslBuilder<*>,
    out RET : Any?
    > {

  operator fun provideDelegate(inst: QSchemaType, property: KProperty<*>): Stub<T>

  fun asNullable(): StubProvider<T, D, RET?>

  companion object {

    internal
    @PublishedApi val delegationContext: DelegationContext = DefaultDelegationContext()
  }
}


interface Stub<out T : GraphQLPropertyContext<*, *>> {
  operator fun getValue(inst: QSchemaType, property: KProperty<*>): T
}

private
class StubLoaderImpl<out T : GraphQLPropertyContext<*, *>>(private val value: T) : Stub<T> {
  override operator fun getValue(inst: QSchemaType, property: KProperty<*>): T = value
}

/**
 * Grand Unified Bootloader for SchemaType definitions/stubs.
 * solves the problem of not knowing the field's GraphQL property name from the generated type hierarchy
 * when generating queries/payloads
 *
 * Delegation inception : this property delegate is delegated to in order to get the property name and type of the
 * schema field, then passes it off to the backing field, which is another delegate in order for the
 * actual delegation to happen fragment a schema model implementation
 *
 * This class does minimal work in order to reduce added complexity - it simply gets passed a function
 * which, when applied a string, produces the correct schemastub.  When delegated to by schemastub types,
 * fragment `getValue` for the schemastub it simply invokes the function with the prop of the graphqlName that it's
 * delegating to. This way, the delegate property can be passed to the delegate/schemastub type without having
 * to resort to hard-wired  &/or needlessly complex metadata methods such as (god forbid) annotations */
internal
class Grub<out T : GraphQLPropertyContext<D, RET>, out D : GraphqlDslBuilder<*>, out RET: Any?>(
    private val typeName: String,
    private val isList: Boolean = false,
    private val toInit: (property: GraphQlProperty) -> T
) : StubProvider<T, D, RET> {

  override fun asNullable(): StubProvider<T, D, RET?> {
    return Grub<T, D, RET?>(typeName, isList) { toInit(it).asNullable() }
    TODO("not implemented")
  }

  override operator fun provideDelegate(inst: QSchemaType, property: KProperty<*>): Stub<T> =
      StubLoaderImpl(toInit(GraphQlProperty.from(typeName, isList, property.name)))
}

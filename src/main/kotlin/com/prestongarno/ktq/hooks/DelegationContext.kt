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

package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.graphQlName
import com.prestongarno.ktq.stubs.InterfaceStub
import com.prestongarno.ktq.stubs.TypeStub
import kotlin.reflect.KClass

/**
 * The interface structure is a bit bloated with 3 different delegation interfaces
 * to support the 3 scenarios a graphql field can require: zero arguments, all nullable arguments,
 * or 1 or more non-null arguments. This isn't easily put into an interface and abstracted, since
 * each type has different requirements for the parameters and/or the DSL flow.
 *
 * However, the GraphQL type hierarchy is finite, so a sealed class hierarchy will make it easy
 * to cut down on the repetitiveness of the $TYPE$.$ARGUMENT_STRATEGY$ way to currently get a delegate
 *
 * DelegationContext is a single entrypoint for the API now, and takes a single argument for the schema
 * stub delegate provider type. This is restricted by the sealed class [GraphQLDelegate]
 */

class DelegationContext {

  val type: GraphQLDelegate.Type = TODO()
}


/**
 * Call to create a new delegate will be something like:
 *
 *    val property by StubProvider.delegationContext.type.query<T>()
 *
 * This is better because it's no longer inlining internal code into client class
 * files and also should be easier to mock up delegate providers for testing
 */
sealed class GraphQLDelegate private constructor() {


  // TODO make typealias for ctors and allow passing in of functions
  // which make the StubProvider for the
  class Type : GraphQLDelegate() {

    fun <T : QType> query(typeClazz: KClass<T>): StubProvider<TypeStub.Query<T>> {
      return Grub(typeClazz.graphQlName()) { TypeStub.noArgStub<T>(it) }
    }

    fun <T: QType, A : ArgBuilder> optionalConfigQuery(
        typeClazz: KClass<T>
    ): StubProvider<TypeStub.OptionalConfigQuery<T, A>> =
        Grub(typeClazz.graphQlName()) { TypeStub.optionalArgStub<T, A>(it) }

    fun <T : QType, A : ArgBuilder> configStub(
        typeClazz: KClass<T>
    ): StubProvider<TypeStub.ConfigurableQuery<T, A>> =
        Grub(typeClazz.graphQlName()) { TypeStub.argStub<T, A>(it) }

  }

  class Interface : GraphQLDelegate() {

  }

  //class Enum : GraphQLDelegate()

  //class Union : GraphQLDelegate()

  //class Scalar : GraphQLDelegate()

  //class CustomScalar : GraphQLDelegate()

}
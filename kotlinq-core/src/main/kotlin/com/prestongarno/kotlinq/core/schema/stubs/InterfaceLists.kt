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

package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.properties.GraphQLPropertyContext
import com.prestongarno.kotlinq.core.adapters.newInterfaceListStub
import com.prestongarno.kotlinq.core.api.GraphQLDelegate
import com.prestongarno.kotlinq.core.properties.GraphQlProperty

interface InterfaceListStub<I, out A> :
    FragmentStub<I>
    where I : QType,
          I : QInterface,
          A : ArgumentSpec {

  companion object {
    internal object Delegates : GraphQLDelegate.Lists.GraphQLListDelegate() {
      //TODO
    }

  }
}

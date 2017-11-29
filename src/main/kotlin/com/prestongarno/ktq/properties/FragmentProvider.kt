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

package com.prestongarno.ktq.properties

import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.hooks.Fragment

/**
 * This is simply a hook into the generated API interface to
 * be able to call a 'SomeUnionObjectType.() -> Unit' on the correct object
 * and get the fragments from the context for the DSL
 */
class FragmentProvider {

  private var collector: MutableList<Fragment>? = null

  /**
   * This is used for unions to invoke the dsl block passed from a union model.
   * Synchronized to handle state of [FragmentProvider.collector] during execution
   * of the code block (generated union fragment methods should normally add to collector)
   * @param target the target Union schema definition
   * @param dispatch the DSL code block to execute
   * @param callback a way for the fragments to be fetched (normally just call [FragmentProvider.reset] in closure)
   */
  @Synchronized operator fun <I : QUnionType> invoke(
      target: I,
      dispatch: I.() -> Unit,
      callback: FragmentProvider.() -> Unit
  ) {
    collector = mutableListOf()
    dispatch(target)
    callback(this)
    collector = null
  }

  /**
   * Reset the state of this instance. Call this from the
   * 3rd parameter in this context from [FragmentProvider.invoke]
   */
  internal fun reset(): Set<Fragment>
      = collector?.toSet()?.also { collector = null } ?: emptySet()

  internal fun addFragment(init: Fragment) {
    collector?.add(init)
  }
}
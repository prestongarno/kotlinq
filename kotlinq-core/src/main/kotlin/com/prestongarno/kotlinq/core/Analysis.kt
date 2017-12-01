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

package com.prestongarno.kotlinq.core

import com.prestongarno.kotlinq.core.hooks.Fragment
import com.prestongarno.kotlinq.core.hooks.FragmentContext
import com.prestongarno.kotlinq.core.hooks.ModelProvider

internal
fun QModel<*>.getFragments(): Set<Fragment> {
  return getFragments(this, hashSetOf(this))
}

private
fun getFragments(root: QModel<*>, collector: Set<QModel<*>>): Set<Fragment> {
  val fragmentEdges = root.getFields().filterIsInstance<FragmentContext>()
      .flatMap { it.fragments.asSequence() }
      .filterNot { collector.contains(it.model) }
      .toSet()
  val models = root.getFields().filterIsInstance<ModelProvider>()
      .filterNot { collector.contains(it.value) }
      .map(ModelProvider::value)
      .plus(fragmentEdges
          .filterNot { collector.contains(it.model) }
          .map(Fragment::model))

  return fragmentEdges + models.map {
    getFragments(it, collector + models + fragmentEdges.map(Fragment::model))
  }.flatten().toSet()

}
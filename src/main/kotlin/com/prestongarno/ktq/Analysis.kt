package com.prestongarno.ktq

import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.hooks.ModelProvider
import com.prestongarno.ktq.stubs.FragmentContext

internal fun QModel<*>.getFragments(): Set<Fragment> {
  return getFragments(this, hashSetOf(this))
}

private fun getFragments(root: QModel<*>, cache: Set<QModel<*>>): Set<Fragment> {
  val fragmentEdges = root.fields.filterIsInstance<FragmentContext>()
      .map { it.fragments }
      .flatMap { it }
      .filterNot { cache.contains(it.model) }
      .toSet()
  val models = root.fields.filterIsInstance<ModelProvider>()
      .map { it.value }
      .filterNot { cache.contains(it) }
      .plus(fragmentEdges
          .map { it.model }
          .filterNot { cache.contains(it) })
  return fragmentEdges +
      models.map {
        getFragments(it, cache + fragmentEdges.map { it.model } + models)
      }.flatten()
          .toSet()
}
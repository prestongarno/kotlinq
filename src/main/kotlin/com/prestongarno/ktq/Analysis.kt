package com.prestongarno.ktq

import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.hooks.FragmentContext
import com.prestongarno.ktq.hooks.ModelProvider

internal fun QModel<*>.getFragments(): Set<Fragment> {
  return getFragments(this, hashSetOf(this))
}

private fun getFragments(root: QModel<*>, cache: Set<QModel<*>>): Set<Fragment> {
  val fragmentEdges = root.getFields().filterIsInstance<FragmentContext>()
      .flatMap { it.fragments.asSequence() }
      .filterNot { cache.contains(it.model) }
      .toSet()
  val models = root.getFields().filterIsInstance<ModelProvider>()
      .filterNot { cache.contains(it.value) }
      .map { it.value }
      .plus(fragmentEdges
          .filterNot { cache.contains(it.model) }
          .map { it.model })
  return fragmentEdges +
      models.map {
        getFragments(it, cache + fragmentEdges.map { it.model } + models)
      }.flatten()
          .toSet()
}
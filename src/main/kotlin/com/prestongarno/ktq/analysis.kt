package com.prestongarno.ktq

import com.prestongarno.ktq.hooks.FragmentGenerator
import com.prestongarno.ktq.hooks.FragmentProvider
import com.prestongarno.ktq.hooks.ModelProvider

internal fun QModel<*>.getFragments(): Set<FragmentGenerator> {
  return getFragments(this, hashSetOf(this))
}

private fun getFragments(root: QModel<*>, cache: Set<QModel<*>>): Set<FragmentGenerator> {
  val fragmentEdges = root.fields.filterIsInstance<FragmentProvider>()
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
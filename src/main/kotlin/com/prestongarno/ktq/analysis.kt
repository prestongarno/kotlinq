package com.prestongarno.ktq

import com.prestongarno.ktq.internal.FragmentGenerator
import com.prestongarno.ktq.internal.FragmentProvider
import com.prestongarno.ktq.internal.ModelProvider

internal fun QModel<*>.getFragments(): Set<FragmentGenerator> {
  return getFragments(this, hashSetOf(this))
}

private fun getFragments(root: QModel<*>, cache: Set<QModel<*>>): Set<FragmentGenerator> {
  val fragmentEdges = root.fields.filterIsInstance<FragmentProvider>()
      .map { it.fragments }
      .flatMap { it }
      .map { it.model }
      .filterNot { cache.contains(it) }
  val models = root.fields.filterIsInstance<ModelProvider>()
      .map { it.value }
      .filterNot { cache.contains(it) }
  return fragmentEdges.map { getFragments(it, cache + fragmentEdges + models) }.flatten().toSet()
}
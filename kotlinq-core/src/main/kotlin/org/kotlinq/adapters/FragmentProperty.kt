package org.kotlinq.adapters

import org.kotlinq.api.Adapter
import org.kotlinq.api.Context
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.FragmentImpl
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.Resolver


internal
class FragmentProperty(
    override val propertyInfo: GraphQlPropertyInfo,
    fragments: Set<() -> Context>
) : FragmentAdapter {

  private var value: Context? = null

  override val fragments: Map<String, Fragment> by lazy {
    fragments.map { FragmentImpl(it) }.map { it.typeName to it }.toMap()
  }

  override fun getValue(): Context? = value

  override fun setValue(typeName: String, values: Map<String, Any?>, resolver: Resolver): Boolean {
    this.value = fragments[typeName]?.initializer?.invoke()?.apply {
      resolver.resolve(values, this)
    }
    return isResolved()
  }

  override fun accept(resolver: GraphVisitor) {
    resolver.visitFragmentContext(this)
  }

  override fun isResolved() =
      value?.graphQlInstance?.isResolved() == true
          || propertyInfo.isNullable


  override fun equals(other: Any?) = (other as? FragmentAdapter)
      ?.let { Adapter.adapterEquals(this, it) } == true
      && other.fragments.count { fragments[it.key] != it.value } == 0

  override fun hashCode(): Int =
      Adapter.adapterHashcode(this) * 31 + fragments.asSequence().fold(0) { acc, curr ->
        acc.times(31) + curr.value.prototype.graphQlInstance.hashCode()
      }
}
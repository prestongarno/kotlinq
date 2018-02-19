package org.kotlinq.properties

import org.kotlinq.api.Adapter
import org.kotlinq.api.Definition
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.FragmentImpl
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.Resolver


internal
class FragmentProperty(
    override val propertyInfo: GraphQlPropertyInfo,
    fragments: Set<() -> Definition>
) : FragmentAdapter {

  private var value: Definition? = null

  override val fragments: Map<String, Fragment> by lazy {
    fragments.map { FragmentImpl(it) }.map { it.typeName to it }.toMap()
  }

  override fun getValue(): Definition? = value

  override fun setValue(typeName: String, values: Map<String, Any?>, resolver: Resolver): Boolean {
    this.value = fragments[typeName]?.initializer?.invoke()?.apply {
      resolver.resolve(values, this)
    }
    return isResolved()
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
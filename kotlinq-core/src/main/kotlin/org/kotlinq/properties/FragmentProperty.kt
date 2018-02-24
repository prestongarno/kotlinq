package org.kotlinq.properties

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.PropertyInfo
import org.kotlinq.api.Resolver


internal
class FragmentProperty(
    override val propertyInfo: PropertyInfo,
    fragments: Set<Fragment>
) : FragmentAdapter {

  override val fragments: Map<String, Fragment> by lazy {
    fragments.map { it.typeName to it }.toMap()
  }

  private var value: Fragment? = null

  override fun getValue() = value

  override fun setValue(typeName: String, values: Map<String, Any?>, resolver: Resolver): Boolean {
    this.value = fragments[typeName]?.apply { resolver.resolve(values, this) }
    return isResolved()
  }

  override fun isResolved() =
      value?.graphQlInstance?.isResolved() == true
          || propertyInfo.isNullable


  override fun equals(other: Any?) = (other as? FragmentAdapter)
      ?.let { Adapter.adapterEquals(this, it) } == true
      && other.fragments.size == this.fragments.size
      && other.fragments.count { fragments[it.key] != it.value } == 0

  override fun hashCode(): Int =
      Adapter.adapterHashcode(this) * 31 + fragments.asSequence().fold(0) { acc, curr ->
        acc.times(31) + curr.value.graphQlInstance.hashCode()
      }
}
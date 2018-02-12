package org.kotlinq.adapters

import org.kotlinq.api.Context
import org.kotlinq.api.Fragment


internal
class FragmentImpl(override val initializer: () -> Context) : Fragment {
  override val prototype: Context by lazy { initializer() }

  override fun hashCode(): Int {
    return prototype.graphQlInstance.hashCode()
  }

  override fun equals(other: Any?): Boolean {
    other as? Fragment ?: return false

    if (typeName != other.typeName)
      return false

    return prototype.graphQlInstance.properties.all {
      other.prototype.graphQlInstance.properties[it.key] == it.value
    }

  }
}
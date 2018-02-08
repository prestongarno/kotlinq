package org.kotlinq.adapters

import org.kotlinq.api.Context
import org.kotlinq.api.Fragment


internal
class FragmentImpl(override val initializer: () -> Context) : Fragment {
  override val prototype: Context by lazy { initializer() }
}
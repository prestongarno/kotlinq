package org.kotlinq.adapters

import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.Resolver
import org.kotlinq.api.Context
import kotlin.reflect.KType


class FragmentProperty(
    override val name: String,
    override val type: KType,
    override val fragments: Map<String, Fragment>,
    override val arguments: Map<String, Any>
) : FragmentAdapter {

  private var value: Context? = null

  override fun getValue(): Context? = value

  override fun setValue(typeName: String, values: Map<String, Any?>, resolver: Resolver): Boolean {
    this.value = fragments[typeName]?.initializer?.invoke()?.apply {
      resolver.resolve(values, this)
    }
    return isResolved()
  }

  override fun accept(resolver: Resolver) {
    resolver.visitFragment(name, this)
  }

  override fun isResolved() =
      value?.graphQlInstance?.isResolved() == true
          || type.isMarkedNullable
}
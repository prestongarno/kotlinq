package org.kotlinq.adapters

import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.Resolver
import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlType
import org.kotlinq.api.GraphVisitor
import kotlin.reflect.KType


internal
class FragmentProperty(
    override val name: String,
    override val type: GraphQlType,
    fragments: Set<() -> Context>,
    override val arguments: Map<String, Any>
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
    resolver.visitFragment(this)
  }

  override fun isResolved() =
      value?.graphQlInstance?.isResolved() == true
          || type.isNullable
}
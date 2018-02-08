package org.kotlinq.adapters

import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.Context
import org.kotlinq.api.Resolver
import kotlin.reflect.KType

internal
class ModelPropertyImpl(
    override val name: String,
    override val type: KType,
    override val arguments: Map<String, String>,
    override val initializer: () -> Context
) : ModelAdapter {

  private var instance: Context? = null

  override val prototype: Context by lazy { initializer() }

  override fun isResolved(): Boolean {
    return instance?.graphQlInstance?.isResolved() == true
  }

  override fun setValue(result: Map<String, Any?>): Boolean {
    this.instance = initializer().apply {
      Resolver.resolve(result, this)
    }
    return isResolved()
  }

  override fun accept(resolver: Resolver) {
    resolver.visitModel(name, this)
  }

  override fun getValue(): Context? = instance
}

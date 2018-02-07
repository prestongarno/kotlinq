package org.kotlinq.adapters

import org.kotlinq.api.Fragment
import org.kotlinq.api.JsonParser
import org.kotlinq.api.TypeContext
import kotlin.reflect.KType


class FragmentProperty(
    override val fragments: Map<String, Fragment>,
    override val name: String,
    override val type: KType
) : FragmentAdapter {

  private var value: TypeContext? = null

  override fun resolve(result: Pair<String, String>): Boolean {
    value = fragments[result.first]?.initializer?.invoke()?.also { context ->

      JsonParser.parseToObject(result.second).forEach { (name, value) ->
        context.graphQlInstance.properties[name]?.take(value)
      }

    }
    return this.value?.graphQlInstance?.isResolved() == true
  }

  override fun getValue(): Any? = value

  override fun take(value: String): Boolean = resolve(JsonParser.parseFragment(value))

  override fun isResolved() = value != null || type.isMarkedNullable
}
package org.kotlinq.api

import kotlin.coroutines.experimental.buildSequence
import kotlin.reflect.KClass
import kotlin.reflect.KType

class FragmentCollectionAdapter(
    override val propertyInfo: GraphQlPropertyInfo,
    override val fragments: Map<String, Fragment>
) : Adapter, FragmentContext {

  private val dimensionality =
      propertyInfo.platformType.listDepth()

  private var _value: List<Any>? =
      if (propertyInfo.isNullable) null else emptyList()

  override fun getValue(): Any? = this._value

  override fun accept(resolver: GraphVisitor) {
    _value?.filterIsInstance<Definition>()?.forEach {
      it.graphQlInstance.accept(resolver)
    }
  }

  override fun isResolved(): Boolean =
      _value != null || propertyInfo.isNullable

  // TODO arbitrary nesting resolution
  fun setValue(values: List<Any>) {

    _value = values.mapNotNull { it as? Map<String, *> }
        .filter {
          it["__typename"] is String && it.entries.count { it.key !is String } == 0
        }
        .mapNotNull { values ->

          fragments[values["__typename"]]?.initializer?.invoke()?.also {
            Resolver.resolve(values, it)
          }

        }
        .filter { it.graphQlInstance.isResolved() }
        .toList()
  }

  // Needed for list coercion
  // as defined by <insert specification citation here>
  @Suppress("UNCHECKED_CAST")
  private
  fun KType.listDepth(): Int = buildSequence {

    arguments.firstOrNull()?.type?.let {
      it.classifier as? KClass<List<*>>
    }?.also { yield(it) }

  }.count()

  override fun equals(other: Any?): Boolean {
    return other is Adapter
        && other is FragmentContext
        && other.propertyInfo == propertyInfo
        && other.fragments.size == fragments.size
        && other.fragments.all { fragments[it.key] == it.value }
  }

  override fun hashCode() =
      propertyInfo.hashCode() * 31 + fragments.hashCode() * 31
}


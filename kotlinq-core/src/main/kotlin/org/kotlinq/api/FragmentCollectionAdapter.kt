package org.kotlinq.api

import kotlin.coroutines.experimental.buildSequence
import kotlin.reflect.KClass
import kotlin.reflect.KType

class FragmentCollectionAdapter(
    override val propertyInfo: GraphQlPropertyInfo,
    fragments: Set<Fragment>
) : Adapter, FragmentContext {

  private val dimensionality =
      propertyInfo.platformType.listDepth()

  override val fragments: Map<String, Fragment> =
      fragments.map { it.typeName to it }.toMap()

  private var _value: List<Any>? =
      if (propertyInfo.isNullable) null else emptyList()

  override fun getValue(): Any? = this._value

  override fun accept(resolver: GraphVisitor) {
    _value?.filterIsInstance<Fragment>()?.forEach {
      it.graphQlInstance.accept(resolver)
    }
  }

  override fun isResolved(): Boolean =
      _value != null || propertyInfo.isNullable

  // TODO arbitrary nesting resolution
  @Suppress("UNCHECKED_CAST", "USELESS_IS_CHECK")
  fun setValue(values: List<Any>) {

    _value = values.mapNotNull {
      it as? Map<String, *>
    }
        .filter {
          it["__typename"] is String && it.entries.count { it.key !is String } == 0
        }
        .mapNotNull { valueMap ->
          fragments[valueMap["__typename"]]?.also { Resolver.resolve(valueMap, it) }
        }.filter { it.graphQlInstance.isResolved() }
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


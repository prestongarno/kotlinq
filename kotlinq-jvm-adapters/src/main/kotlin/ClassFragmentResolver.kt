package org.kotlinq.jvm

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.Kind


internal
class ClassFragmentResolver<out T : Data?>(
    private val map: Map<String, Any?>,
    private val fragment: ClassFragment<T>
) {

  val isValid: Boolean by lazy { Validator.validate(map, fragment) }

  fun resolve(): T? =
      if (!isValid) null else fragment.init(map.toResult())
}

/**
 * Most readable code you'll ever see below
 */
internal
object Validator {

  fun validate(map: Map<String, Any?>, fragment: ClassFragment<*>): Boolean = fragment.graphQlInstance.properties.entries
      .firstOrNull { !isValidValue(it.value.propertyInfo.kind, map[it.key]) } == null

  private operator fun Fragment.get(property: String): Adapter? =
      graphQlInstance.properties[property]


  fun isValidValue(kind: Kind, value: Any?): Boolean {

    value ?: return kind is Kind.NullableKind

    // can't call Kind#isScalar since that counts "List<Int>" as a scalar
    if (value.isScalar()) return kind is Kind.Scalar
        && value.scalarKind()?.isTypeCompatible(kind) == true

    if (kind is Kind.ListKind) return (value as? List<*>)
        ?.let { it.all { isValidValue(kind.wrapped, it) } } == true
// best we can do here, without reflection on the back end
    return (kind as? Kind.NullableKind)?.wrapped?.let { isValidValue(it, value) } ?: value.asMapWithStringKey() != null
  }


  private
  fun Any.isScalar(): Boolean = this.let {
    it is String
        || it is Int
        || it is Boolean
        || it is Float
  }

  private fun Any?.scalarKind() = when (this) {
    is Boolean -> Kind.bool
    is Int -> Kind.integer
    is String -> Kind.string
    is Float -> Kind.float
    else -> null
  }

  @Suppress("UNCHECKED_CAST")
  internal
  fun Any?.asMapWithStringKey(): Map<String, Any?>? =
      if ((this as? Map<*, *>)?.entries?.all { it.key is String } == true) this as? Map<String, Any?> else null

  private fun String.getFrom(map: Map<String, Any?>): Any? = map[this]

}
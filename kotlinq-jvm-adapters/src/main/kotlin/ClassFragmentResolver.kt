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

  fun validate(map: Map<String, Any?>, fragment: ClassFragment<*>): Boolean {
    return map.entries
        .filter { (it.value as? String)?.startsWith("__") == false && it.value != "id" }
        .count { (key, value) -> fragment[key]?.let { !isValidValue(it.propertyInfo.kind, value) } == false } == 0
  }

  private operator fun Fragment.get(property: String): Adapter? =
      graphQlInstance.properties[property]


  fun isValidValue(kind: Kind, value: Any?): Boolean {

    value ?: return kind is Kind.NullableKind

    // can't call Kind#isScalar since that counts "List<Int>" as a scalar
    if (value.isScalar()) return kind is Kind.Scalar

    if (kind is Kind.ListKind) return (value as? List<*>)
        ?.let {
          if (it.isEmpty())
            true
          else
            it.firstOrNull { isValidValue(kind.wrapped, it) == false } == null
        } == true

    return (kind as? Kind.NullableKind)?.wrapped?.let {
      isValidValue(it, value)
    } // best we can do here, without reflection on the back end
        ?: (kind is Kind.Definition) && value.asMapWithStringKey() != null
  }


  private
  fun Any.isScalar(): Boolean = this.let {
    it is String
        || it is Int
        || it is Boolean
        || it is Float
  }

  @Suppress("UNCHECKED_CAST")
  internal
  fun Any?.asMapWithStringKey(): Map<String, Any?>? {
    return if ((this as? Map<*, *>)?.entries?.firstOrNull { it.key !is String } != null)
      null
    else this as? Map<String, Any?>
  }

  private fun String.getFrom(map: Map<String, Any?>): Any? = map[this]

}
@file:Suppress("ClassName")

package org.kotlinq.api

/**
 *
 * Named "Kind" and not "TypeKind" because it clashes with [javax.lang.model.type.TypeKind]
 * and makes intellij buggy and kotlin compiler fail
 *
 *
 * Somewhat the same idea as:
 *
 * ```
 * export const TypeKind = {
 *  SCALAR: 'SCALAR',
 *  OBJECT: 'OBJECT',
 *  INTERFACE: 'INTERFACE',
 *  UNION: 'UNION',
 *  ENUM: 'ENUM',
 *  INPUT_OBJECT: 'INPUT_OBJECT',
 *  LIST: 'LIST',
 *  NON_NULL: 'NON_NULL',
 *};
 *
 * ```
 *
 * (from https://github.com/graphql/graphql-js/blob/master/src/type/introspection.js)
 */

sealed
class Kind(internal val name: kotlin.String) {

  open val classifier: String get() = name

  abstract fun isTypeCompatible(instance: Kind): kotlin.Boolean

  /** Workaround for strance exception in initializer errors with this */
  open val isScalar: Boolean get() = false

  fun asList() = ListKind(this)
  fun asNullable() = this as? NullableKind ?: NullableKind(this)

  fun rootKind(): Kind = when {
    this is ListKind -> wrapped.rootKind()
    this is NullableKind -> wrapped.rootKind()
    else -> this
  }

  override fun equals(other: Any?) =
      ((other as? Kind)?.isTypeCompatible(this) == true
          && name == other.name)

  override fun hashCode(): Int =
      name.hashCode()

  override fun toString(): String = "TypeKind($name)"


  object _String : Kind("String") {
    override val isScalar get() = true
    override fun isTypeCompatible(instance: Kind) = instance === this
  }


  object _Int : Kind("Int") {
    override val isScalar get() = true
    override fun isTypeCompatible(instance: Kind) = instance === this
  }


  object _Boolean : Kind("Boolean") {
    override val isScalar get() = true
    override fun isTypeCompatible(instance: Kind) = instance === this
  }


  object _Float : Kind("Float") {
    override val isScalar get() = true
    override fun isTypeCompatible(instance: Kind) = instance === this
  }


  class ListKind internal constructor(val wrapped: Kind)
    : Kind("List") {

    override val classifier get() = wrapped.classifier
    override val isScalar get() = wrapped.isScalar

    override fun isTypeCompatible(instance: Kind): kotlin.Boolean =
        (instance as? ListKind)
            ?.let { wrapped.isTypeCompatible(it.wrapped) }
            ?: false
  }


  class NullableKind internal constructor(val wrapped: Kind) : Kind("!") {

    override val classifier get() = wrapped.classifier
    override val isScalar get() = wrapped.isScalar

    override fun isTypeCompatible(instance: Kind): kotlin.Boolean =
        (instance as? NullableKind)
            ?.let { wrapped.isTypeCompatible(it.wrapped) }
            ?: false
  }


  class Definition internal constructor(name: kotlin.String) : Kind(name) {

    override val isScalar = false

    override fun isTypeCompatible(instance: Kind): kotlin.Boolean =
        instance is Definition

    override fun toString(): kotlin.String = name
  }

  companion object {

    fun named(name: String): Kind {
      for (scalar in ScalarHolder.scalars) {
        println("$scalar")
        if (scalar.name == name)
          throw IllegalArgumentException("Illegal name $name")
      }
      return Definition(name)
    }
  }
}


private object ScalarHolder {
  val scalars = listOf(
      org.kotlinq.api.Kind._Int,
      org.kotlinq.api.Kind._Boolean,
      org.kotlinq.api.Kind._String,
      org.kotlinq.api.Kind._Float)
}


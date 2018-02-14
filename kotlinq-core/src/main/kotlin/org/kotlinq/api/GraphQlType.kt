package org.kotlinq.api

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmErasure


class GraphQlType(val ktype: KType) {

  val name: String get() = ktype.jvmErasure.simpleName!!
  val isNullable: Boolean get() = ktype.isMarkedNullable

  /**
   * todo should nullability be taken into account?
   */
  override fun equals(other: Any?): Boolean =
      other is GraphQlType
          && other.name == name
          && (other.ktype.classifier as? KClass<*>)?.qualifiedName ==
          (ktype.classifier as? KClass<*>)?.qualifiedName

  override fun hashCode() =
      name.hashCode().times(31) + isNullable.hashCode().times(31)


  override fun toString(): String =
      ktype.toString().let { if (isNullable) it else "!$it" }

  companion object {

    fun fromKtype(ktype: KType) = GraphQlType(ktype)
  }
}
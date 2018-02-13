package org.kotlinq.api

import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmErasure


interface GraphQlType {
  val name: String get() = ktype.jvmErasure.simpleName!!
  val isNullable: Boolean get() = ktype.isMarkedNullable
  val ktype: KType

  companion object {
    fun fromKtype(ktype: KType) =

        object : GraphQlType {

          override val ktype: KType get() = ktype

          override fun equals(other: Any?): Boolean {
            other as? GraphQlType ?: return false
            return other.name == name
          }

          override fun hashCode() =
              name.hashCode().times(31) + isNullable.hashCode().times(31)

        }

  }
}
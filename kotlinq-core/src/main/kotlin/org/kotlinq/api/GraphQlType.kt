package org.kotlinq.api

import kotlin.reflect.KClass
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

        }

  }
}
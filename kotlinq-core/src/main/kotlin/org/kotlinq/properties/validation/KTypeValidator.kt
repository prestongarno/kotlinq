package org.kotlinq.properties.validation

import kotlin.reflect.KType
import kotlin.reflect.full.isSubtypeOf


internal
fun KType.isCollection(): Boolean {
  return isSubtypeOf(Prototypes.apexListType)
}

private object Prototypes {
  val listProperty: Collection<*>? = emptyList<Any?>()
  val apexListType = Prototypes::listProperty.returnType
}


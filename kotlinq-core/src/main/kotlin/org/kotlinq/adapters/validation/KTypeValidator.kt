package org.kotlinq.adapters.validation

import kotlin.reflect.KType
import kotlin.reflect.full.isSubtypeOf


internal
fun KType.isCollection(): Boolean {
  return isSubtypeOf(PrototypeContainer.apexListType)
}

private object PrototypeContainer {
  val listProperty: Collection<*>? = emptyList<Any?>()
  val apexListType = PrototypeContainer::listProperty.returnType
}


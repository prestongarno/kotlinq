package org.kotlinq.jvm

import org.kotlinq.api.PropertyInfo
import org.kotlinq.introspection.Kind
import org.kotlinq.jvm.annotations.Ignore
import kotlin.coroutines.experimental.buildSequence
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.starProjectedType

// Internal extension functions for kotlin core reflection & kotlinq api

internal
object Reflekt {

  val dataKtype = Data::class.createType(nullable = true)

  val anyProperties: Set<KProperty1<*, *>> =
      Any::class.memberProperties.toSet()

  fun shouldBeIgnored(property: KProperty1<*, *>): Boolean {
    return property.findAnnotation<Ignore>() != null || property in anyProperties
  }
}

internal
fun KType.isCompatibleWith(value: Any?): Boolean {

  value ?: return isMarkedNullable

  if (this.isList) {
    val valueAsList = value as? List<*> ?: return false
    return listIsCompatible(valueAsList,
        arguments.first().type ?: Any::class.createType())
  } else {
    return value::class.starProjectedType.isSubtypeOf(this)
  }
}

private fun listIsCompatible(value: List<*>, type: KType): Boolean {
  if (value.isEmpty()) return true
  return value.all(type::isCompatibleWith)
}

@PublishedApi internal
fun KProperty1<*, *>.toPropertyInfo(
    typeName: String = this.returnType.rootType.clazz?.simpleName!!,
    args: Map<String, Any?> = emptyMap()
): PropertyInfo {

  if (returnType.rootType.scalarKind() != null)
    throw IllegalArgumentException(this.toString())

  return PropertyInfo
      .propertyNamed(name)
      .typeKind(wrap(
          Kind.typeNamed(typeName),
          returnType))
      .arguments(args)
      .build()
}


internal
fun KType.scalarKind(): Kind? {
  val name = (this.rootType.classifier as? KClass<*>)?.simpleName ?: return null
  return Kind.scalars.firstOrNull { it.classifier == name }?.let {
    wrap(it, this)
  }
}

internal
fun wrap(kind: Kind, type: KType): Kind = buildSequence {
  var current = type
  while (current.isList) {
    if (current.isMarkedNullable)
      yield(Kind::asNullable)
    yield(Kind::asList)
    current.arguments.firstOrNull()?.type?.let {
      current = it
    }
  }
}.toList()
    .reversed()
    .fold(kind) { acc, curr -> curr(acc) }

internal
fun KType.dataKind(): Kind? =
    if (!rootType.isSubtypeOf(Reflekt.dataKtype))
      null
    else rootType.clazz?.simpleName
        ?.let(Kind.Companion::typeNamed)
        ?.let { wrap(it, this) }

internal
val KType.clazz: KClass<*>?
  get() = this.classifier as? KClass<*>

internal
val KType.rootType: KType
  get() {
    return if (isList) {
      var current: KType = this
      while (current.isList)
        current.arguments.firstOrNull()?.type?.let { current = it }
      current
    } else this
  }

internal
val KType.isList get() = this.clazz?.isList ?: false

internal
val KClass<*>.isList get() = isSubclassOf(List::class)

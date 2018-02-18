package org.kotlinq.dsl

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection

fun mockType(
    clazz: KClass<*>,
    isMarkedNullable: Boolean = false,
    arguments: List<KTypeProjection> = emptyList()
): KType = FakeKType(clazz, isMarkedNullable, arguments)

private
class FakeKType(
    override val classifier: KClass<*>,
    override val isMarkedNullable: Boolean = true,
    override val arguments: List<KTypeProjection> = emptyList()
) : KType {

  init { require(!isScalar()) }

  override fun equals(other: Any?): Boolean =
      other is KType
          && other.classifier == classifier
          && other.isMarkedNullable == isMarkedNullable
          && other.arguments.containsAll(arguments)

  override fun hashCode(): Int =
      (classifier.hashCode() * 31 + isMarkedNullable.hashCode() * 31) * 31 +
          arguments.fold(0) { acc, curr -> acc * 31 + curr.hashCode() }

  override fun toString(): String {
    return classifier.toString().let { if (isMarkedNullable) it else "$it!" }
  }

  private
  fun List<KTypeProjection>.pretty() =
      joinToString(prefix = "<", separator = ", ", postfix = ">") { it.toString() }
}

fun KType.isScalar() =
    classifier == String::class
        || classifier == Boolean::class
        || classifier == Int::class
        || classifier == Float::class
        || classifier == Enum::class


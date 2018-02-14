package org.kotlinq

import org.junit.Test
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection
import kotlin.reflect.KVariance
import kotlin.reflect.full.withNullability


fun mockType(clazz: KClass<*>, arguments: List<KClass<*>> = emptyList(), isMarkedNullable: Boolean = true): KType {
  return MockedKTypeImpl(
      arguments.map { KTypeProjection(KVariance.INVARIANT, MockedKTypeImpl(emptyList(), it, true)) },
      clazz,
      isMarkedNullable)
}

private
class MockedKTypeImpl(
    override val arguments: List<KTypeProjection>,
    override val classifier: KClassifier?,
    override val isMarkedNullable: Boolean
) : KType {

  override fun equals(other: Any?): Boolean =
      other is KType
          && other.classifier == classifier
          && other.isMarkedNullable == isMarkedNullable
          && other.arguments.containsAll(arguments)

  /**
   * [org.kotlinq.api.GraphQlType] impl hashcode doesn't take
   * platform [KType.hashCode] into account, so not very important
   */
  override fun hashCode(): Int =
      (classifier?.hashCode() ?: 0 * 31+
      isMarkedNullable.hashCode() * 31) * 31 +
          arguments.fold(0) { acc, curr -> acc * 31 + curr.hashCode() }

  override fun toString(): String {
    return classifier.toString().let { if (isMarkedNullable) it else "$it!" }
  }
}


class MockKTypeTestIntegrityTest {

  val expect: String? = ""

  @Test fun mockStringEqualsBuiltinType() {

    val expectType = this::expect.returnType

    mockType(String::class) eq expectType
    mockType(String::class, isMarkedNullable = false).let { type ->
      type notEq this::expect.returnType
      type eq expectType.withNullability(false)
    }
  }

}

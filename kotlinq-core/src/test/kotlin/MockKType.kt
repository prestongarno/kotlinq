import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection
import kotlin.reflect.KVariance


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
) : KType


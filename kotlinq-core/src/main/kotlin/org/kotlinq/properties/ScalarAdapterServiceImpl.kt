package org.kotlinq.properties

import org.kotlinq.api.BooleanAdapter
import org.kotlinq.api.FloatAdapter
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.IntAdapter
import org.kotlinq.api.ParsingAdapter
import org.kotlinq.api.ScalarAdapterService
import org.kotlinq.api.StringAdapter
import kotlin.reflect.KType


class ScalarAdapterServiceImpl(
    override val mappers: ScalarAdapterService.TypeMappers = BuiltInTypeMapper()
) : ScalarAdapterService {

  override fun adapterFor(info: GraphQlPropertyInfo): ParsingAdapter {
    require(info.platformType.isScalar()) {
      "Type $info is not a scalar type"
    }
    return when (info.platformType.classifier) {
      Int::class -> IntAdapterImpl(info, mappers.intMapper)
      String::class -> StringAdapterImpl(info, mappers.stringMapper)
      Float::class -> FloatAdapterImpl(info, mappers.floatMapper)
      Boolean::class -> BooleanAdapterImpl(info, mappers.booleanMapper)
      else -> throw IllegalArgumentException()
    }
  }


  companion object {
    fun KType.isScalar() =
        classifier == String::class
            || classifier == Boolean::class
            || classifier == Int::class
            || classifier == Float::class
            || classifier == Enum::class
  }
}
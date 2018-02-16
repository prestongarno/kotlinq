package org.kotlinq.properties

import org.kotlinq.api.BooleanAdapter
import org.kotlinq.api.FloatAdapter
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.IntAdapter
import org.kotlinq.api.ScalarAdapterService
import org.kotlinq.api.StringAdapter


class ScalarAdapterServiceImpl(
    override val mappers: ScalarAdapterService.TypeMappers = BuiltInTypeMapper()
) : ScalarAdapterService {

  override fun intAdapter(info: GraphQlPropertyInfo, mapper: (String) -> Int): IntAdapter =
      IntAdapterImpl(info, mapper)

  override fun stringAdapter(info: GraphQlPropertyInfo, mapper: (String) -> String): StringAdapter =
      StringAdapterImpl(info, mapper)

  override fun floatAdapter(info: GraphQlPropertyInfo, mapper: (String) -> Float): FloatAdapter =
      FloatAdapterImpl(info, mapper)

  override fun booleanAdapter(info: GraphQlPropertyInfo, mapper: (String) -> Boolean): BooleanAdapter =
      BooleanAdapterImpl(info, mapper)
}
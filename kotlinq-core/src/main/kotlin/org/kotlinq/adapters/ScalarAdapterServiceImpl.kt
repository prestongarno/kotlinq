package org.kotlinq.adapters

import org.kotlinq.api.BooleanAdapter
import org.kotlinq.api.FloatAdapter
import org.kotlinq.api.IntAdapter
import org.kotlinq.api.ScalarAdapterService
import org.kotlinq.api.StringAdapter
import kotlin.reflect.KType


class ScalarAdapterServiceImpl(
    override val mappers: ScalarAdapterService.TypeMappers = BuiltInTypeMapper()
) : ScalarAdapterService {

  override fun intAdapter(
      name: String,
      type: KType,
      mapper: (String) -> Int,
      arguments: Map<String, Any>
  ): IntAdapter = IntAdapterImpl(name, type, mapper, arguments)

  override fun stringAdapter(
      name: String,
      type: KType,
      mapper: (String) -> String,
      arguments: Map<String, Any>
  ): StringAdapter = StringAdapterImpl(name, type, mapper, arguments)

  override fun floatAdapter(
      name: String,
      type: KType,
      mapper: (String) -> Float,
      arguments: Map<String, Any>
  ): FloatAdapter = FloatAdapterImpl(name, type, mapper, arguments)

  override fun booleanAdapter(
      name: String,
      type: KType,
      mapper: (String) -> Boolean,
      arguments: Map<String, Any>
  ): BooleanAdapter = BooleanAdapterImpl(name, type, mapper, arguments)

}
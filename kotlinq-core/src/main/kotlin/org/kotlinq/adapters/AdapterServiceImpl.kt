package org.kotlinq.adapters

import org.kotlinq.api.Adapter
import org.kotlinq.api.AdapterService
import org.kotlinq.api.Context
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.ScalarAdapterService
import java.io.InputStream
import kotlin.reflect.jvm.jvmErasure


internal
class AdapterServiceImpl(
    override val scalarAdapters: ScalarAdapterService = ScalarAdapterServiceImpl()
) : AdapterService {

  override fun deserializer(info: GraphQlPropertyInfo, init: (InputStream) -> Any?): Adapter =
      DeserializingProperty(info, init)

  override fun parser(info: GraphQlPropertyInfo, init: (String) -> Any?): Adapter =
      ParsedProperty(info, init)

  override fun enumDeserializer(info: GraphQlPropertyInfo): Adapter = ParsedProperty(info, { value ->
    info.platformType.jvmErasure.java.enumConstants.find { value == it?.toString() }
  })

  override fun instanceProperty(info: GraphQlPropertyInfo, init: () -> Context): ModelAdapter =
      InstanceProperty(info, init)

  override fun fragmentProperty(info: GraphQlPropertyInfo, fragments: Set<() -> Context>): FragmentAdapter =
      FragmentProperty(info, fragments)
}

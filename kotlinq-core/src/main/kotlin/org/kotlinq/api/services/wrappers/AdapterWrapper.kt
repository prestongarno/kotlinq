package org.kotlinq.api.services.wrappers

import org.kotlinq.api.Adapter
import org.kotlinq.api.AdapterService
import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.ScalarAdapterService
import org.kotlinq.api.services.Wrapper
import java.io.InputStream

internal
class AdapterWrapper(instance: AdapterService)
  : Wrapper<AdapterService>(instance, AdapterService::class),
    AdapterService {

  override val scalarAdapters: ScalarAdapterService
    get() = instance().scalarAdapters

  override fun deserializer(info: GraphQlPropertyInfo, init: (InputStream) -> Any?): Adapter =
      instance().deserializer(info, init)

  override fun parser(info: GraphQlPropertyInfo, init: (String) -> Any?): Adapter =
      instance().parser(info, init)

  override fun enumDeserializer(info: GraphQlPropertyInfo): Adapter =
      instance().enumDeserializer(info)

  override fun instanceProperty(info: GraphQlPropertyInfo, init: () -> Context): Adapter =
      instance().instanceProperty(info, init)

  override fun fragmentProperty(info: GraphQlPropertyInfo, fragments: Set<() -> Context>): Adapter =
      instance().fragmentProperty(info, fragments)
}
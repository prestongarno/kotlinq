package org.kotlinq.api.services.wrappers

import org.kotlinq.api.AdapterService
import org.kotlinq.api.Fragment
import org.kotlinq.api.PropertyInfo
import org.kotlinq.api.ScalarAdapterService
import org.kotlinq.api.services.Wrapper
import java.io.InputStream

internal
class AdapterWrapper(instance: AdapterService)
  : Wrapper<AdapterService>(instance, AdapterService::class),
    AdapterService {

  override fun instanceProperty(info: PropertyInfo, fragment: Fragment) =
      instance().instanceProperty(info, fragment)

  override fun fragmentProperty(info: PropertyInfo, fragments: Set<Fragment>) =
      instance().fragmentProperty(info, fragments)

  override val scalarAdapters: ScalarAdapterService
    get() = instance().scalarAdapters

  override fun deserializer(info: PropertyInfo, init: (InputStream) -> Any?) =
      instance().deserializer(info, init)

  override fun parser(info: PropertyInfo, init: (String) -> Any?) =
      instance().parser(info, init)
}
package org.kotlinq.properties

import org.kotlinq.api.AdapterService
import org.kotlinq.api.Fragment
import org.kotlinq.api.PropertyInfo
import org.kotlinq.api.ScalarAdapterService
import java.io.InputStream


internal
class AdapterServiceImpl(
    override val scalarAdapters: ScalarAdapterService = ScalarAdapterServiceImpl()
) : AdapterService {

  override fun deserializer(info: PropertyInfo, init: (InputStream) -> Any?) =
      DeserializingProperty(info, init)

  override fun parser(info: PropertyInfo, init: (String) -> Any?) =
      ParsedProperty(info, init)

  override fun instanceProperty(info: PropertyInfo, fragment: Fragment) =
      InstanceProperty(info, fragment)

  override fun fragmentProperty(info: PropertyInfo, fragments: Set<Fragment>) =
      FragmentProperty(info, fragments)
}

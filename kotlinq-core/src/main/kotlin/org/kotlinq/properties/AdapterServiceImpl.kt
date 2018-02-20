package org.kotlinq.properties

import org.kotlinq.api.Adapter
import org.kotlinq.api.AdapterService
import org.kotlinq.api.FragmentCollectionAdapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.GraphQlPropertyInfo
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

  override fun instanceProperty(info: GraphQlPropertyInfo, fragment: Fragment): Adapter =
      InstanceProperty(info, fragment)

  override fun fragmentProperty(info: GraphQlPropertyInfo, fragments: Set<Fragment>): Adapter {
    return if (info.platformType.classifier == List::class)
       FragmentCollectionAdapter(info, fragments)
    else
      FragmentProperty(info, fragments)
  }
}

package org.kotlinq.adapters

import org.kotlinq.api.Adapter
import org.kotlinq.api.AdapterService
import org.kotlinq.api.Context
import java.io.InputStream
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmErasure


internal
class AdapterServiceImpl : AdapterService {

  override fun deserializer(name: String, type: KType, init: (InputStream) -> Any?): Adapter {
    return DeserializingProperty(name, type, init, emptyMap())
  }

  override fun parser(name: String, type: KType, init: (String) -> Any?): Adapter {
    return ParsedProperty(name, type, init, emptyMap())
  }

  override fun enumDeserializer(name: String, type: KType): Adapter {
    return ParsedProperty(name, type, { value ->
      type.jvmErasure.java.enumConstants.find { value == it?.toString() }
    }, emptyMap())
  }

  override fun instanceProperty(name: String, type: KType, init: () -> Context): Adapter {
    return ModelPropertyImpl(name, type, emptyMap(), init)
  }

  override fun fragmentProperty(name: String, type: KType, fragments: Set<() -> Context>): Adapter {
    return FragmentProperty(
        name, type,
        fragments.map(::FragmentImpl).map {
          it.prototype.graphQlInstance.graphQlTypeName to it
        }.toMap(),
        emptyMap())
  }

}
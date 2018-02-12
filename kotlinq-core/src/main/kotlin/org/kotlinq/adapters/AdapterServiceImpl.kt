package org.kotlinq.adapters

import org.kotlinq.api.Adapter
import org.kotlinq.api.AdapterService
import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlType
import org.kotlinq.api.ScalarAdapterService
import java.io.InputStream
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmErasure


internal
class AdapterServiceImpl(
    override val scalarAdapters: ScalarAdapterService = ScalarAdapterServiceImpl()
) : AdapterService {

  override fun deserializer(
      name: String,
      type: KType,
      init: (InputStream) -> Any?,
      arguments: Map<String, Any>
  ): Adapter = DeserializingProperty(name, GraphQlType.fromKtype(type), init, arguments)

  override fun parser(
      name: String,
      type: KType,
      init: (String) -> Any?,
      arguments: Map<String, Any>
  ): Adapter = ParsedProperty(name, GraphQlType.fromKtype(type), init, arguments)

  override fun enumDeserializer(
      name: String,
      type: KType,
      arguments: Map<String, Any>
  ): Adapter = ParsedProperty(name, GraphQlType.fromKtype(type), { value ->
    type.jvmErasure.java.enumConstants.find { value == it?.toString() }
  }, arguments)

  override fun instanceProperty(
      name: String,
      type: KType,
      init: () -> Context,
      arguments: Map<String, Any>
  ): Adapter = ModelPropertyImpl(name, GraphQlType.fromKtype(type), arguments, init)

  override fun fragmentProperty(
      name: String,
      type: KType,
      fragments: Set<() -> Context>,
      arguments: Map<String, Any>
  ): Adapter = FragmentProperty(name, GraphQlType.fromKtype(type), fragments, arguments)

}
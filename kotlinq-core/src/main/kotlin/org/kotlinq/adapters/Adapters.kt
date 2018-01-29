package org.kotlinq.adapters

import org.kotlinq.Model
import org.kotlinq.api.GraphQlProperty
import kotlin.reflect.KClass
import kotlin.reflect.KType


// TODO module
interface AdapterService {


  fun <Z> deserializer(type: KType, init: (java.io.InputStream) -> Z)
      : GraphQlProperty<Z> = TODO()

  fun <Z> parser(type: KType, init: (String) -> Z)
      : GraphQlProperty<Z> = TODO()

  fun <Z : Model<*>> initializer(type: KType, init: () -> Z)
      : GraphQlProperty<Z> = TODO()

  fun <Z : Enum<Z>> enumDeserializer(clazz: KClass<Z>, type: KType)
      : GraphQlProperty<Z> = TODO()

}


interface Adapter {
  val type: KType
  fun getValue(): Any?
}

interface ModelAdapter: Adapter {
  fun resolve(value: Map<String, Any?>): Boolean
}

interface ModelCollectionAdapter : Adapter {
  fun resolve(value: List<Map<String, Any?>>): Boolean
}

interface DeserializingAdapter : Adapter {
  fun resolve(stream: java.io.InputStream): Boolean
}

interface ParsingAdapter : Adapter {
  val initializer: (String) -> Any?
}


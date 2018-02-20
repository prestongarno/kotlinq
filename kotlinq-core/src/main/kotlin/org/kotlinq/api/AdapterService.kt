package org.kotlinq.api


/**
 * Provides adapters (entities) for properties with different return types (use cases)
 *
 * @author prestongarno
 */
interface AdapterService {

  val scalarAdapters: ScalarAdapterService

  fun deserializer(info: GraphQlPropertyInfo, init: (java.io.InputStream) -> Any?): Adapter

  fun parser(info: GraphQlPropertyInfo, init: (String) -> Any?): Adapter

  fun enumDeserializer(info: GraphQlPropertyInfo): Adapter

  fun instanceProperty(info: GraphQlPropertyInfo, fragment: Fragment): Adapter

  fun fragmentProperty(info: GraphQlPropertyInfo, fragments: Set<Fragment>): Adapter

}



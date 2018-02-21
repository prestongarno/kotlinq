package org.kotlinq.api


/**
 * Provides adapters (entities) for properties with different return types (use cases)
 *
 * @author prestongarno
 */
interface AdapterService {

  val scalarAdapters: ScalarAdapterService

  fun deserializer(info: PropertyInfo, init: (java.io.InputStream) -> Any?): Adapter

  fun parser(info: PropertyInfo, init: (String) -> Any?): Adapter

  fun instanceProperty(info: PropertyInfo, fragment: Fragment): Adapter

  fun fragmentProperty(info: PropertyInfo, fragments: Set<Fragment>): Adapter

}



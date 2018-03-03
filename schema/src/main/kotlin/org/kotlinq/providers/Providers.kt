package org.kotlinq.providers

import org.kotlinq.Model
import org.kotlinq.delegates.GraphQlProperty


interface PropertyProviders {


  fun <Z> deserializingProvider(name: String, init: (java.io.InputStream) -> Z)
      : DslBuilderProvider<Z>

  fun <Z> parsingProvider(name: String, init: (String) -> Z?)
      : DslBuilderProvider<Z>

  fun <Z : Model<*>> initializingProvider(name: String, init: () -> Z)
      : DslBuilderProvider<Z>

}


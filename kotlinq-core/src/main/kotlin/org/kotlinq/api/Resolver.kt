package org.kotlinq.api

import com.github.salomonbrys.kodein.instance


interface Resolver {

  fun resolve(value: Map<String, Any?>, target: Context): Boolean

  fun visitModel(name: String, target: ModelAdapter)

  fun visitFragment(name: String, target: FragmentAdapter)

  fun visitScalar(name: String, target: ParsingAdapter)

  fun visitDeserializer(name: String, target: DeserializingAdapter)

  companion object : Resolver by Configuration.kodein.instance()
}

package org.kotlinq.api

import com.github.salomonbrys.kodein.instance


interface Resolver {

  fun resolve(value: Map<String, Any?>, target: Context): Boolean

  fun visitModel(target: ModelAdapter)

  fun visitFragment(target: FragmentAdapter)

  fun visitScalar(target: ParsingAdapter)

  fun visitDeserializer(target: DeserializingAdapter)

  companion object : Resolver by Configuration.kodein.instance()
}

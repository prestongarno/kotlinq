package org.kotlinq.api

import com.github.salomonbrys.kodein.instance


/**
 * Encapsulation of visitor-pattern algorithm for resolving GraphQL queries
 *
 * Currently no limit on nesting depth or size of response
 */
interface Resolver {

  fun resolve(value: Map<String, Any?>, target: Context): Boolean

  fun visitModel(target: ModelAdapter)

  fun visitFragment(target: FragmentAdapter)

  fun visitScalar(target: ParsingAdapter)

  fun visitDeserializer(target: DeserializingAdapter)

  companion object : Resolver by Configuration.kodein.instance()
}

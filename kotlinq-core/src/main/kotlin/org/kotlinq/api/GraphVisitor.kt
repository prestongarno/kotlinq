package org.kotlinq.api


interface GraphVisitor {

  fun visitDeserializer(target: DeserializingAdapter) = Unit

  fun visitFragment(target: Fragment) = Unit

  fun visitFragmentContext(target: FragmentAdapter) = Unit

  fun visitModel(target: ModelAdapter) = Unit

  fun visitScalar(target: ParsingAdapter) = Unit

}
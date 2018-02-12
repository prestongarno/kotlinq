package org.kotlinq.api


interface GraphVisitor {

  fun visitModel(target: ModelAdapter)

  fun visitFragment(target: FragmentAdapter)

  fun visitScalar(target: ParsingAdapter)

  fun visitDeserializer(target: DeserializingAdapter)

}
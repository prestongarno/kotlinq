package org.kotlinq.api


interface IntAdapter : ParsingAdapter {
  override fun getValue(): Int
}

interface FloatAdapter : ParsingAdapter {
  override fun getValue(): Float
}

interface StringAdapter : ParsingAdapter {
  override fun getValue(): String
}

interface BooleanAdapter : ParsingAdapter {
  override fun getValue(): Boolean
}


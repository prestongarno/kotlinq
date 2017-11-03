package com.prestongarno.ktq.input

// TODO generate this with the types, make it a sealed class
// to restrict to input types as data classes
interface QInput {
  fun toPayloadString() : String = TODO()
}
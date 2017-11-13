package com.prestongarno.ktq.interfaceFragments

import com.prestongarno.ktq.ArgBuilder

class GeneratedFieldArgBuilder(val requiredArgument: String) : ArgBuilder() {

  var property: String? by arguments

  fun foo() {
    property = "Hello world"
  }
}

fun main(args: Array<String>) {
  val foo = GeneratedFieldArgBuilder("Hello")
  println(foo.property)
  foo.foo()
  println(foo.property)
  println(foo.requiredArgument)
  //foo.apply { argBuilder.argBuilder().forEach { println("${it.first}==${it.second}") }}
}
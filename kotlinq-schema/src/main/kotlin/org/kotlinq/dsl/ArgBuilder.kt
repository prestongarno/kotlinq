package org.kotlinq.dsl


interface ArgumentSpec {
  fun take(argument: Pair<String, Any>)
}

open
class ArgBuilder : ArgumentSpec {
  private val args = mutableMapOf<String, Any>()

  override fun take(argument: Pair<String, Any>) {
    args[argument.first] = argument.second
  }
}
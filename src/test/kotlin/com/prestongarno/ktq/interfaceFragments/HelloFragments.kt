package com.prestongarno.ktq.interfaceFragments

import com.prestongarno.ktq.QInterfaceType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*


object Object : QInterfaceType {
  val value by QScalar.intStub()
}

class MyObject : QModel<Object>(Object) {
  val result by model.value.withDefault(100)
}

fun main(args: Array<String>) {
  require(MyObject().result == 100)
  require(MyObject().apply {
    onResponse("{\"value\": 69}")
  }.result == 69)
}
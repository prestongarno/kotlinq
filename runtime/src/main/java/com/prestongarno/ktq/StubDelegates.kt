package com.prestongarno.ktq

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.Stub
import kotlin.reflect.KProperty

object StubMapper {
  inline operator fun <reified T> getValue(inst: QModel<*>, property: KProperty<*>): T = TODO()
}
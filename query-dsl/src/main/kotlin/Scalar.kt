package org.kotlinq.dsl

import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.Kotlinq
import kotlin.reflect.KClass
import kotlin.reflect.KType
import org.kotlinq.dsl.Scalar.*
import kotlin.reflect.full.createType

sealed class Scalar(
    internal val clazz: KClass<*>,
    internal val type: KType = clazz.createType()) {

  internal fun bindToName(name: String): Adapter =
      name.fromScalar(this)

  object StringScalar : Scalar(String::class)
  object IntScalar : Scalar(Int::class)
  object BooleanScalar : Scalar(Boolean::class)
  object FloatScalar : Scalar(Float::class)
}

private
fun String.fromScalar(scalar: Scalar) = Kotlinq.adapterService.scalarAdapters.run {
  val info = GraphQlPropertyInfo(this@fromScalar, scalar.clazz.simpleName!!, scalar.type)
  when (scalar) {
    StringScalar -> stringAdapter(info)
    IntScalar -> intAdapter(info)
    BooleanScalar -> booleanAdapter(info)
    FloatScalar -> floatAdapter(info)
  }
}

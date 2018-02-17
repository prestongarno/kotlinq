@file:Suppress("unused")

import org.kotlinq.dsl.Scalar
import org.kotlinq.dsl.PrimitiveScope
import org.kotlinq.dsl.query
import kotlin.reflect.KProperty1


fun foo() = query {

  val bar: KProperty1<PrimitiveScope, Scalar> = PrimitiveScope::outerProp

  "name"(float)
}

val PrimitiveScope.outerProp get() = Scalar.StringScalar

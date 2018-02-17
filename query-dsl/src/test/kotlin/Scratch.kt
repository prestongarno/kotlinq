@file:Suppress("unused")

import org.kotlinq.dsl.Scalar
import org.kotlinq.dsl.PrimitiveScope
import org.kotlinq.dsl.ofType
import org.kotlinq.dsl.query
import org.kotlinq.dsl.queryGraph
import kotlin.reflect.KProperty1


fun foo() = queryGraph({listOf(
    "Hello"::string
)})


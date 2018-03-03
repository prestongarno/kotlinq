package org.kotlinq.delegates

import org.kotlinq.dsl.ArgumentSpec
import kotlin.reflect.KClass

class PredicateStub<in A : ArgumentSpec, out T : GraphQlPropertyStub>(
    private val name: String,
    private val clazz: KClass<T>
) {

  fun withArguments(arguments: A): T = GraphQlPropertyStub.create(clazz, name, arguments)

}
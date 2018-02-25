package org.kotlinq.jvm

import org.kotlinq.api.BindableContext
import org.kotlinq.api.Kotlinq
import kotlin.reflect.KProperty0


class TypedFragmentScope internal constructor(private val bindableContext: BindableContext) {
  inline operator fun <reified T : Data?> KProperty0<T>.invoke(arguments: Map<String, Any>) {
    Kotlinq.adapterService
        .instanceProperty(
            toPropertyInfo(T::class.simpleName!!, arguments),
            TypedFragment.thisClass(T::class)
        ).let(bindableContext::register)
  }
}
package org.kotlinq.jvm

import kotlin.reflect.KProperty1


class FragmentField<T, out R> internal constructor(
    internal val property: KProperty1<T, R>,
    internal val arguments: Map<String, Any?> = emptyMap())

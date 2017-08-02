package com.prestongarno.ktq.runtime.delegates

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class fragment {

	public inline operator fun <T, V: T> Map<in String, T>.getValue(thisRef: Any?, property: KProperty<*>): V
			= @Suppress("UNCHECKED_CAST") (get(property.name) as V)

	public inline operator fun <V> MutableMap<in String, in V>.setValue(thisRef: Any?, property: KProperty<*>, value: V) {
		this.put(property.name, value)
	}
}


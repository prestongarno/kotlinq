package com.prestongarno.ktq.runtime

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class ScalarMapper<out T : Any>(val converter: (String) -> T) : PropertyMapper<T>(FieldType.STRING) {
	override fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, T> {
		val mapper = ScalarProperty<T>(inst, property, converter)
		inst.scalarFields.add(mapper)
		return mapper
	}
}

internal class ScalarListMapper<out T : Any>(val converter: (String) -> T) : ListMapper<T>(FieldType.STRING) {
	override fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, List<T>> {
		val mapper = ScalarList<T>(inst, property, converter)
		inst.scalarListFields.add(mapper)
		return mapper
	}
}

class ScalarProperty<T>(val inst: GraphType, val property: KProperty<*>, val converter: (String) -> T) : ReadOnlyProperty<GraphType, T> {
	internal val raw: String by lazy { inst.values?.get(property.name) as String }
	internal val value: T by lazy { converter.invoke(raw) }
	override fun getValue(thisRef: GraphType, property: KProperty<*>): T = value
	internal val name: String = property.name

	override fun toString(): String {
		return "ScalarProperty(inst=${inst::class}, property=${property.name}, converter=$converter)"
	}
}

class ScalarList<T>(val inst: GraphType, val property: KProperty<*>, val converter: (String) -> T) : ReadOnlyProperty<GraphType, List<T>> {
	val raw: List<String> by lazy {
		@Suppress("UNCHECKED_CAST")
		inst.values?.get(property.name) as List<String>
	}
	internal val value by lazy { raw.map { converter.invoke(it) } }
	override fun getValue(thisRef: GraphType, property: KProperty<*>): List<T> = value
	val  name: String = property.name
}

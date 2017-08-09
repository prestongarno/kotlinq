package com.prestongarno.ktq.runtime.delegates

import com.prestongarno.ktq.runtime.ArgBuilder
import com.prestongarno.ktq.runtime.GraphType
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

interface QDelegate<T> {
	val fieldName: String
	val schemaType: String
	val payload: ArgBuilder
	val thisRef: GraphType

	@Suppress("UNCHECKED_CAST")
	fun getValue(thisRef: GraphType, property: KProperty<*>): T =
		thisRef.values?.get(property.name) as T ?: throw Error()

	companion object {
		internal val intMapper: QDelegate<Int> = QDummyDelegate<Int>()
		internal val stringMapper: QDelegate<String> = QDummyDelegate<String>()
		internal val boolMapper: QDelegate<Boolean> = QDummyDelegate<Boolean>()
		internal val floatMapper: QDelegate<Float> = QDummyDelegate<Float>()
	}

}

object GraphProvider {
	fun <T : GraphType> bind(init: () -> T): QDelegate<T> = object : QDelegate<T> {
		override var fieldName: String by Delegates.notNull()
		override var schemaType: String by Delegates.notNull()
		override var payload: ArgBuilder by Delegates.notNull()
		override val thisRef: GraphType by Delegates.notNull()

		private val value by lazy { init.invoke() }

		override fun getValue(thisRef: GraphType, property: KProperty<*>): T = value
	}
}


class QScalarDelegate<T> internal constructor(var mapper: (String) -> T,
                                              override var fieldName: String,
                                              override var schemaType: String,
                                              override var payload: ArgBuilder,
                                              override var thisRef: GraphType) : QDelegate<T> {

	override operator fun getValue(thisRef: GraphType, property: KProperty<*>): T {
		val get = thisRef.values?.get(fieldName) ?:
				throw NullPointerException("either property was accessed before it was supposed to of there was an error on the inside")
		return mapper.invoke(get as String)
	}

}

internal fun checkArgsBuilder(): ArgBuilder {
	val bundle: ArgBuilder
	if (ArgBuilder.last != null) {
		bundle = ArgBuilder.last!!
		ArgBuilder.last = null
	} else bundle = ArgBuilder.empty
	return bundle
}

/**
 * Dummy value only initialized once for the GraphType.Companion mapper values, QDelegate#provideDelegate extension
 * method returns the correct instance once it has the property/type/instance information for the field/object
 * todo how to get rid of this?
 */
class QDummyDelegate<T> internal constructor() : QDelegate<T> {
	override val fieldName: String by Delegates.notNull()
	override val schemaType: String by Delegates.notNull()
	override val payload: ArgBuilder by Delegates.notNull()
	override val thisRef: GraphType by Delegates.notNull()
}

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
		internal val intMapper: QDelegate<Int> = QDummyDelegate<Int>({ it.toInt() })
		internal val stringMapper: QDelegate<String> = QDummyDelegate<String>({ it })
		internal val boolMapper: QDelegate<Boolean> = QDummyDelegate<Boolean>({ it.toBoolean() })
		internal val floatMapper: QDelegate<Float> = QDummyDelegate<Float>({ it.toFloat() })
	}

}

open class GraphProvider<T : GraphType>(val init: () -> T,
                                        override val fieldName: String,
                                        override val schemaType: String,
                                        override val payload: ArgBuilder,
                                        override val thisRef: GraphType) : QDelegate<T> {

	val value by lazy { init.invoke() }

	override operator fun getValue(thisRef: GraphType, property: KProperty<*>): T = value
}


class QScalarDelegate<T> internal constructor(var mapper: (String) -> T,
                                              override var fieldName: String,
                                              override var schemaType: String,
                                              override var payload: ArgBuilder,
                                              override var thisRef: GraphType) : QDelegate<T> {

	override operator fun getValue(thisRef: GraphType, property: KProperty<*>): T {
		println("Accessing Property -> ${property.name} ::")
		val get = thisRef.values?.get(fieldName) ?:
				throw NullPointerException("either property '${property.name}' was accessed before it was supposed to of there was an error on the inside")
		return mapper.invoke(get.toString())
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
internal class QDummyDelegate<T>(val adapter: (String) -> T) : QDelegate<T> {
	override val fieldName: String by Delegates.notNull()
	override val schemaType: String by Delegates.notNull()
	override val payload: ArgBuilder by Delegates.notNull()
	override val thisRef: GraphType by Delegates.notNull()
}


/**
 * TODO how to stop using dummy instances to save type information at compile time
 * even though they don't really do anything
 */
internal class DummyGraphProvider<T : GraphType>(var of: () -> T, thisRef: GraphType)
	: GraphProvider<T>({ of.invoke() }, "", "", ArgBuilder.empty, thisRef) {
	override var fieldName: String = ""
		get() = throw GraphType.SchemaStub()
	override var schemaType: String = ""
		get() = throw GraphType.SchemaStub()
	override var payload: ArgBuilder = ArgBuilder.empty
		get() = throw GraphType.SchemaStub()
	override var thisRef: GraphType = thisRef
		get() = throw GraphType.SchemaStub()
}

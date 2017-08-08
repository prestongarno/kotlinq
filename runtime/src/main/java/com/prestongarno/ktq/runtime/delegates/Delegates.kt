package com.prestongarno.ktq.runtime.delegates

import com.prestongarno.ktq.runtime.ArgBuilder
import com.prestongarno.ktq.runtime.GraphType
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

//interface ListMapper<T> : ReadOnlyProperty<GraphType, List<T>> TODO do lists later

interface FieldMapper<T> : ReadOnlyProperty<GraphType, T>

interface DelegateProvider<T, K: QDelegate<T>> {
	operator fun <T> provideDelegate(inst: GraphType, property: KProperty<*>): QDelegate<T>

	operator fun getValue(inst: GraphType, property: KProperty<*>): K {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}

interface QDelegate<T> : FieldMapper<T> {
	val name: String
	val schemaType: String
	val payload: ArgBuilder

	override fun getValue(thisRef: GraphType, property: KProperty<*>): T {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	@Suppress("UNCHECKED_CAST")
	companion object {
		@Suppress("UNCHECKED_CAST")
		operator fun <T> provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, T> {
			val bundle: ArgBuilder = checkArgsBuilder()

			val type = property.returnType.arguments[0].type

			return when (type) {
				Int::class -> QScalarDelegate({ it.toInt() }, property.name, inst.SchemaTypeName, bundle) as QScalarDelegate<T>
				String::class -> QScalarDelegate({ it }, property.name, inst.SchemaTypeName, bundle) as QScalarDelegate<T>
				Float::class -> QScalarDelegate({ it.toFloat() }, property.name, inst.SchemaTypeName, bundle) as QScalarDelegate<T>
				Boolean::class -> QScalarDelegate({ it.toBoolean() }, property.name, inst.SchemaTypeName, bundle) as QScalarDelegate<T>
				else -> throw IllegalArgumentException("Expected a type scalar but got a $type")
			}
		}

		private val genericDelegate: Any = object : DelegateProvider<Any, QDelegate<Any>> {
			override operator fun <T> provideDelegate(inst: GraphType, property: KProperty<*>): QDelegate<T> {
				return QDelegate.provideDelegate<T>(inst, property) as QDelegate<T>
			}
		}

		internal fun <T: Any, K: QDelegate<T>> getGeneric(clazz: KClass<T>): DelegateProvider<T, K> = genericDelegate as DelegateProvider<T, K>

		fun <K: QDelegate<Int>> int(clazz: KClass<Int>): DelegateProvider<Int, K> = getGeneric<Int, K>(clazz)
		//fun string(): DelegateProvider = this
		//fun bool(): DelegateProvider = this
		//fun float(): DelegateProvider = this
	}

}

object GraphProvider {
	fun <T : GraphType> bind(init: () -> T): QDelegate<T> = object : DelegateProvider<T>, QDelegate<T> {

		override var name: String by Delegates.notNull()
		override var schemaType: String by Delegates.notNull()
		override var payload: ArgBuilder by Delegates.notNull()

		private val value by lazy { init.invoke() }

		override fun getValue(thisRef: GraphType, property: KProperty<*>): T = value

		@Suppress("UNCHECKED_CAST") override fun <T> provideDelegate(inst: GraphType, property: KProperty<*>):
				ReadOnlyProperty<GraphType, T> {
			name = property.name
			schemaType = inst.SchemaTypeName
			payload = checkArgsBuilder()
			return this as ReadOnlyProperty<GraphType, T>
		}
	}
}

fun checkArgsBuilder(): ArgBuilder {
	val bundle: ArgBuilder
	if (ArgBuilder.last != null) {
		bundle = ArgBuilder.last!!
		ArgBuilder.last = null
	} else bundle = ArgBuilder.empty
	return bundle
}

class QScalarDelegate<T> internal constructor(val mapper: (String) -> T,
                                              override val name: String,
                                              override val schemaType: String,
                                              override val payload: ArgBuilder) : QDelegate<T> {

	override fun getValue(thisRef: GraphType, property: KProperty<*>): T {
		val get = thisRef.values?.get(name) as String?: throw UnsupportedOperationException()
		return mapper.invoke(get)
	}
}


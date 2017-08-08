package com.prestongarno.ktq.runtime

import com.prestongarno.ktq.runtime.delegates.DelegateProvider
import com.prestongarno.ktq.runtime.delegates.GraphProvider
import com.prestongarno.ktq.runtime.delegates.QDelegate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.superclasses

/**
 * The root type of all GraphQL objects
 *
 * Each field should be delegated to the appropriate method in this class.
 * Primitive or custom scalar fields have their own methods, while nested GraphType objects
 * should use the method GraphType#field for a single field, or GraphType#list for a list of objects: * providing as a parameter a function which returns an instance of that type or subtype
 *
 * Example BasicUser model of type `User`
 *
 *
 * ```
 *     class BasicUser : User() {
 *       public override val username by string()
 *     }
 * ```
 *
 * where User is a GraphQL type defined in the schema i.e. an auto-generated subclass GraphType.
 *
 * TODO: Probably should abstract a bit from having separate logic for scalars, raw primitives, and nested objects
 */
open class GraphType {

	open val SchemaTypeName: String by lazy { throw SchemaStub() }

	/** This is the result of the query : Holds the values */
	internal var values: Map<String, Any?>? = null

	val foo: QDelegate<Int> by int<Int>(clazz = Int::class)

	internal val fields : MutableList<QDelegate<*>> = ArrayList(5)

	internal fun setValues(values: Map<String, Any?>): GraphType {
		this.values = values; return this; }

	/** @return a string representation of this object in GraphQL format able to be sent as a query/mutation
	 */
	internal fun toPayload(): String = TODO()

	override fun toString(): String = TODO()

	/** Throwable set as default delegate value for all fields on
	 * auto-generated GraphQL schema types, ensures that models explicitly declare their fields & types
	 */
	class SchemaStub : Throwable("not declared")

	companion object {
		/** Maps this field to a nested GraphType object
		 * @param of : A function which returns an instance of type of which this field is
		 */
		fun <T : GraphType> field(of: () -> T): QDelegate<T> = GraphProvider.bind<T>(of)

		/** Maps this field to a list of nested GraphType object
		 * @param of : A function which returns an instance of type of which this field is
		 */
		/** Maps this field to an integer value */
		//fun <T : GraphType> list(of: () -> T): ListMapper<T> = GraphListMapper<T>(of)

		/** Maps this field to an integer value */
		fun <T: Int> int(clazz: KClass<T>): DelegateProvider<Int, QDelegate<Int>> = QDelegate.int<QDelegate<Int>>(Int::class) as DelegateProvider<Int, QDelegate<Int>>

		/** Maps this field to an float value */
		//fun float() = QDelegate.float()

		/** Maps this field to an boolean value */
		//fun bool() = QDelegate.bool()

		/** Maps this field to an string value */
		//fun string(): DelegateProvider = QDelegate.string()

		/** Maps this field to an ID type
		 * This cooresponds to the `ID` Scalar type defined in the GraphQL specification
		 */
		//fun ID() = PropertyMapper.idMapper

		/** Maps this scalar field to a String type
		 */
		//fun scalar() = QDelegate.string()

		/** Maps this scalar field to a type T,
		 * @param the function which maps the raw data value (represented as a String) to type T
		 */
		//fun <T : Any> scalarMapper(converter: (String) -> T): <T> = ScalarMapper<T>(converter)

		/** Maps this scalar field to a List of items of type T,
		 * @param the function which maps the raw data value (represented as a String) to type T
		 */
		//fun <T : Any> scalarListMapper(converter: (String) -> T): ListMapper<T> = ScalarListMapper<T>(converter)

	}

}

internal class Payload() : ArgBuilder {

	constructor(vararg arguments: Pair<String, Any>) : this() {
		arguments.map { values.put(it.first, it.second) }
	}

	val values: MutableMap<in String, Any> = HashMap()

	override fun addArg(name: String, value: Any): ArgBuilder {
		values.put(name, value)
		return this
	}

	override fun build() = GraphType.Companion

	override fun toString(): String = TODO()

	private fun formatAs(value: Any): String {
		return when (value) {
			is Int, is Boolean, Float -> "$value"
			is String -> "\"$value\""
			is GraphType -> { TODO() }
			is Enum<*> -> value.name
			is List<*> -> value
					.map { formatAs(it ?: "") }
					.filter { it.isNotBlank() }
					.joinToString(", ", "[ ", " ]")
			else -> throw UnsupportedOperationException()
		}
	}
}

interface ArgBuilder {

	fun addArg(name: String, value: Any): ArgBuilder

	fun build(): GraphType.Companion

	companion object {
		fun create(): ArgBuilder {
			val payload = Payload()
			last = payload
			return payload
		}

		/** Nullable field needed for delegates to link a payload with a property*/
		internal var last: Payload? = null
		val empty: ArgBuilder by lazy { Payload() }
	}
}



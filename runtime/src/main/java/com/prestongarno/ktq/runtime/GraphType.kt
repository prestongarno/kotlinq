package com.prestongarno.ktq.runtime

import java.util.*
import kotlin.collections.HashMap
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
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

	internal fun setValues(values: Map<String, Any?>): GraphType {
		this.values = values; return this; }

	/** This map holds information about payloads (arguments) which are mapped to a specific field */
	internal var payLoads: MutableMap<String, Payload> = HashMap()

	/** This list holds info about which fields to include in the GraphQL query */
	internal val primitiveFields: MutableList<GraphFieldProperty<*>> = LinkedList()

	/** This list holds info about which fields to include in the GraphQL query */
	internal val primitiveListFields: MutableList<GraphListProperty<*>> = LinkedList()

	/** This list holds info about which fields to include in the GraphQL query */
	internal val scalarFields: MutableList<ScalarProperty<*>> = LinkedList()

	/** This list holds info about which fields to include in the GraphQL query */
	internal val scalarListFields: MutableList<ScalarList<*>> = LinkedList()

	/** This list holds info about which GraphTypes (i.e. nested objects) to include in the GraphQL query */
	internal val objectFields: MutableList<GraphMapper<GraphType>> = LinkedList()

	internal val provided: MutableList<ExactImpl<Any>> = LinkedList()

	/** @return a string representation of this object in GraphQL format able to be sent as a query/mutation
	 */
	internal fun toPayload(indentLevel: Int = GraphType.TAB_COUNT): String {
		GraphType.TAB_COUNT = indentLevel
		return Jsonify.createPayload(this)
	}

	override fun toString(): String =
			if (values == null) Jsonify.createPayload(this) else Jsonify.toJson(this)

	/** Throwable set as default delegate value for all fields on
	 * auto-generated GraphQL schema types, ensures that models explicitly declare their fields & types
	 */
	class SchemaStub : Throwable("not declared")

	companion object {
		/** Maps this field to a nested GraphType object
		 * @param of : A function which returns an instance of type of which this field is
		 */
		fun <T : GraphType> field(of: () -> T): PropertyMapper<T> = GraphPropertyMapper<T>(of)

		/** Maps this field to a list of nested GraphType object
		 * @param of : A function which returns an instance of type of which this field is
		 */
		/** Maps this field to an integer value */
		fun <T : GraphType> list(of: () -> T): ListMapper<T> = GraphListMapper<T>(of)

		/** Maps this field to an integer value */
		fun int() = PropertyMapper.intMapper

		/** Maps this field to an float value */
		fun float() = PropertyMapper.floatMapper

		/** Maps this field to an boolean value */
		fun bool() = PropertyMapper.boolMapper

		/** Maps this field to an string value */
		fun string() = PropertyMapper.stringMapper

		/** Maps this field to an ID type
		 * This cooresponds to the `ID` Scalar type defined in the GraphQL specification
		 */
		//fun ID() = PropertyMapper.idMapper

		/** Maps this scalar field to a String type
		 */
		fun scalar() = PropertyMapper.stringMapper

		/** Maps this scalar field to a type T,
		 * @param the function which maps the raw data value (represented as a String) to type T
		 */
		fun <T : Any> scalarMapper(converter: (String) -> T): PropertyMapper<T> = ScalarMapper<T>(converter)

		/** Maps this scalar field to a List of items of type T,
		 * @param the function which maps the raw data value (represented as a String) to type T
		 */
		fun <T : Any> scalarListMapper(converter: (String) -> T): ListMapper<T> = ScalarListMapper<T>(converter)

		/** Maps this field to a list of integer values */
		fun intList() = ListMapper.intListMapper

		/** Maps this field to a list of float values */
		fun floatList() = ListMapper.floatListMapper

		/** Maps this field to a list of boolean values */
		fun boolList() = ListMapper.boolListMapper

		/** Maps this field to a list of string values */
		fun stringList() = ListMapper.stringListMapper

		fun <T : QEnum> exact(value: T): Mapper<T> = ExactImpl(value)
		var TAB_COUNT: Int = 1

	}

}

interface Mapper<out T> {
	operator fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, T>
}

internal class ExactImpl<out T : Any>(val value: T) : Mapper<T> {

	var name by Delegates.notNull<String>()

	val mapper: ReadOnlyProperty<GraphType, T> by lazy {
		object : ReadOnlyProperty<GraphType, T> {
			override operator fun getValue(thisRef: GraphType, property: KProperty<*>): T = value
		}
	}

	override operator fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, T> {
		name = property.name
		inst.provided.add(0, this)
		return mapper
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

	override fun toString(): String = if (values.isNotEmpty())
		"(\n${"\t".repeat(GraphType.TAB_COUNT)}${values.entries.map { arg ->
			"${arg.key}: ${formatAs(arg.value)}"
		}.joinToString(",\n${"\t".repeat(GraphType.TAB_COUNT)}")}\n${"\t".repeat(GraphType.TAB_COUNT - 1)})" else ""

	private fun formatAs(value: Any): String {
		return when (value) {
			is Int, is Boolean, Float -> "$value"
			is String -> "\"$value\""
			is GraphType -> {
				val indent = GraphType.TAB_COUNT
				GraphType.TAB_COUNT += 1
				val res = value.toString()
				GraphType.TAB_COUNT -= 1; res
			}
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
	}
}

internal interface GraphMapper<out T : GraphType> {
	fun create(): T
	fun isList(): Boolean
	fun getName(): String
}

internal fun jsonToPairMap(data: String): Map<String, Any?> = TODO()

internal class GraphListMapper<T : GraphType>(val of: () -> T) : ListMapper<T>(FieldType.OBJECT),
		GraphMapper<T>,
		ReadOnlyProperty<GraphType, List<T>> {

	override fun create(): T = of.invoke()
	override fun isList(): Boolean = true
	override fun getName(): String = fieldName!! // delegate will be created before property access
	private var fieldName: String? = null
	private var thisRef: GraphType? = null // Enclosing object

	@Suppress("UNCHECKED_CAST")
	val value: List<T> by lazy {
		(thisRef?.values?.get(fieldName) as List<String>)
				.map { create().setValues(jsonToPairMap(it)) as T }
	}

	override operator fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, List<T>> {
		inst.objectFields.add(this)
		thisRef = inst
		fieldName = property.name
		if (ArgBuilder.last != null) {
			inst.payLoads.put(property.name, ArgBuilder.last!!)
			ArgBuilder.last = null
		}
		return this
	}

	override fun getValue(thisRef: GraphType, property: KProperty<*>): List<T> = value

	override fun toString(): String {
		return "GraphListMapper(of=$of, name=$fieldName)"
	}
}

internal open class GraphPropertyMapper<out T : GraphType>(val of: () -> T) :
		PropertyMapper<T>(FieldType.OBJECT),
		GraphMapper<T>,
		ReadOnlyProperty<GraphType, T> {

	override fun create(): T = of.invoke()
	override fun isList(): Boolean = false
	override fun getName(): String = fieldName!! // delegate will be created before property access
	private var thisRef: GraphType? = null // Enclosing object
	private var fieldName: String? = null

	@Suppress("UNCHECKED_CAST")
	val value: T by lazy { create().setValues(jsonToPairMap((thisRef?.values?.get(fieldName) as String))) as T }

	override operator fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, T> {
		fieldName = property.name
		thisRef = inst
		inst.objectFields.add(this)
		if (ArgBuilder.last != null) {
			inst.payLoads.put(property.name, ArgBuilder.last!!)
			ArgBuilder.last = null
		}
		return this
	}

	override fun getValue(thisRef: GraphType, property: KProperty<*>): T = value

	override fun toString(): String {
		return "GraphPropertyMapper(of=$of, name=$fieldName)"
	}
}


internal object Jsonify {
	fun createPayload(obj: GraphType): String {
		val indentLevel = GraphType.TAB_COUNT
		GraphType.TAB_COUNT += 1
		val primFields = obj.primitiveFields.map { printPayload(it.name, obj) }.joinToString(indentLevel).trim()
		val primListFields = obj.primitiveListFields.map { printPayload(it.name, obj) }.joinToString(indentLevel).trim()
		val scalarFields = obj.scalarFields.map { printPayload(it.name, obj) }.joinToString(indentLevel).trim()
		val scalarList = obj.scalarListFields.map { printPayload(it.name, obj) }.joinToString(indentLevel).trim()
		val nested = obj.objectFields.map { printPayload(it.getName(), obj) }.joinToString(indentLevel).trim()
		val provided = obj.provided.map { printPayload(it.name, obj) + "= ${it.value.toString()}" }.joinToString(indentLevel).trim()

		val clazzName = obj::class.simpleName ?: "$"
		val pretty = listOf(primFields, primListFields, scalarFields, scalarList, nested, provided).reversed()
				.reduce { next, acc -> if (next.trim().isNotBlank()) "$acc\n${"\t".repeat(indentLevel)}${next.trim()}" else acc } +
				"\n" + "\t".repeat(indentLevel - 1) + "}"

		GraphType.TAB_COUNT = indentLevel
		return ("${if (clazzName.contains('$')) obj::class::superclasses.get()[0].simpleName else clazzName} " +
				"{\n${"\t".repeat(indentLevel)}" + pretty)
				.replace("(\n\\s*?\n)+".toRegex(), "\n")
	}

	private fun List<String>.joinToString(indentLevel: Int): String = filter { it.trim().isNotBlank() }
			.map { it.trim() }
			.joinToString("\n" + ("\t".repeat(indentLevel)))

	private fun printPayload(name: String, obj: GraphType, value: GraphType? = null): String {
		val indent = GraphType.TAB_COUNT
		val payload = obj.payLoads.get(name)
		val res = if (payload != null) payload.toString() else ""
		GraphType.TAB_COUNT = indent
		val toPayload = value?.toPayload()
		return "$name$res ${toPayload?.padStart(1, ':') ?: ""}"
	}

	fun toJson(obj: GraphType): String = TODO()
}





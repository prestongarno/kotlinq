package com.prestongarno.ktq.runtime

import com.prestongarno.ktq.runtime.delegates.*
import kotlin.collections.ArrayList
import kotlin.reflect.KProperty

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

	open val SchemaTypeName: String by lazy {
		this::class.qualifiedName ?: this::class.supertypes.get(0).javaClass.name
	} //TODO :: Need to generate the type name in code generation

	/** This is the result of the query : Holds the values */
	internal var values: Map<String, Any?>? = null

	internal val fields: MutableList<QDelegate<*>> = ArrayList(5)

	/** @return a string representation of this object in GraphQL format able to be sent as a query/mutation
	 */
	internal fun toPayload(): String = TODO()

	override fun toString(): String = "${this::class.qualifiedName} :: \n\tvalues::\t$values\n\tfields::\t$fields"

	/** Throwable set as default delegate value for all fields on
	 * auto-generated GraphQL schema types, ensures that models explicitly declare their fields & types
	 */
	class SchemaStub : Throwable("not declared")

	companion object {
		/** Maps this field to a nested GraphType object
		 * @param of : A function which returns an instance of the type
		 */
		fun <T : GraphType> field(of: () -> T): GraphProvider<T> = DummyGraphProvider(of, DumbDummy())

		internal class DumbDummy : GraphType()

		/** Maps this field to a list of nested GraphType object
		 * @param of : A function which returns an instance of type of which this field is
		 */
		/** Maps this field to an integer value */
		//fun <T : GraphType> list(of: () -> T): QDelegate<List<T>> = GraphProvider.bindList(of)

		/** Maps this field to an integer value */
		fun int() = QDelegate.intMapper

		/** Maps this field to an float value */
		fun float() = QDelegate.floatMapper

		/** Maps this field to an boolean value */
		fun bool() = QDelegate.boolMapper

		/** Maps this field to an string value */
		fun string(): QDelegate<String> = QDelegate.stringMapper

		/** Maps this scalar field to a raw String type
		 */
		fun scalar() = QDelegate.stringMapper

		/** Maps this scalar field to a type T,
		 * TODO figure out a flexible way to support mapping to custom types
		 * @param the function which maps the raw data value (represented as a String) to type T
		 */
		fun <T : Any> scalarMapper(adapter: (String) -> T):
				QDelegate<T> = QDummyDelegate<T>(adapter)

		/** Maps this scalar field to a List of items of type T,
		 * @param the function which maps the raw data value (represented as a String) to type T
		 */
		//fun <T : Any> scalarListMapper(converter: (String) -> T): ListMapper<T> = ScalarListMapper<T>(converter)
		@Suppress("UNCHECKED_CAST")
		operator fun <T : GraphType> GraphProvider<T>.provideDelegate(thisRef: GraphType, property: KProperty<*>): GraphProvider<T> {

			println("Property ::>> ${property.name}")

			val bundle: ArgBuilder = checkArgsBuilder()
			val result = GraphProvider<T>(this.init, property.name, thisRef.SchemaTypeName, bundle, thisRef)
			thisRef.fields.add(result)
			return result
		}

		@Suppress("UNCHECKED_CAST")
		operator fun <T> QDelegate<T>.provideDelegate(thisRef: GraphType, property: KProperty<*>): QScalarDelegate<T> {

			println("Property :: ${property.name}")

			val bundle: ArgBuilder = checkArgsBuilder()
			val returnType = property.returnType.classifier ?:
					throw IllegalArgumentException("property '${property.name}'.returnType.classifier? was null")

			val result = when (returnType) { // TODO reuse these objects to avoid creating new instances for each GraphType
				Int::class -> QScalarDelegate({ it.toInt() }, property.name, thisRef.SchemaTypeName, bundle, thisRef) as QScalarDelegate<T>
				String::class -> QScalarDelegate({ it }, property.name, thisRef.SchemaTypeName, bundle, thisRef) as QScalarDelegate<T>
				Float::class -> QScalarDelegate({ it.toFloat() }, property.name, thisRef.SchemaTypeName, bundle, thisRef) as QScalarDelegate<T>
				Boolean::class -> QScalarDelegate({ it.toBoolean() }, property.name, thisRef.SchemaTypeName, bundle, thisRef) as QScalarDelegate<T>
				else  -> QScalarDelegate((this as QDummyDelegate<T>).adapter, property.name, thisRef.SchemaTypeName, bundle, thisRef)
				//else -> throw IllegalArgumentException("Expected a type scalar but got a $returnType")
			}
			thisRef.fields.add(result)
			return result
		}

	}

}

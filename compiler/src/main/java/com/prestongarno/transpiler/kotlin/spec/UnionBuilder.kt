package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.ktq.runtime.GraphType
import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.*

// TODO :: may want to make this an interface in order to reduce runtime overhead( i.e. single instance,
// TODO :: passing around pointers to lists `onSuccess`, etc.
class UnionBuilder {

	/**
	 * Creates Union types
	 * This is probably easier than I initially thought: just make the union a regular graphtype with each
	 * possible type as a list of that type. Adding a field "all" as a ordered list if needed
	 * I don't know if you can input these types as args/mutations or whatever though, it might be tricky
	 */
	fun create(union: QUnionTypeDef, builder: QTypeBuilder): TypeSpec = builder.createType(
		 QInterfaceDef(union.name,
				union.possibleTypes.map{ QField(it.name.toLowerCase(),
						it,
						emptyList(),
						QDirectiveSymbol.default, true, false) }))
}

package com.prestongarno.transpiler.kotlin.spec

import com.prestongarno.transpiler.QCompilationUnit
import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.*

// TODO :: may want to make this an interface in order to reduce runtime overhead( i.e. single instance,
// TODO :: passing around pointers to lists `onSuccess`, etc.
class UnionBuilder {

	fun create(union: QUnionTypeDef, builder: QTypeBuilder, comp: QCompilationUnit): TypeSpec = builder.createType(
		 QTypeDef(union.name,
				 emptyList(),
				union.possibleTypes.map{
					QField(it.name.toLowerCase(), it, emptyList(), QDirectiveSymbol.default, true, false)
				}), comp)
}

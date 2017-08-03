package com.prestongarno.transpiler

import com.prestongarno.transpiler.kotlin.spec.QTypeBuilder
import com.prestongarno.transpiler.kotlin.spec.createEnums
import com.prestongarno.transpiler.qlang.spec.QDefinedType
import com.prestongarno.transpiler.qlang.spec.Scalar
import com.squareup.kotlinpoet.KotlinFile
import java.io.File
import kotlin.streams.toList

class QCompiler {
	fun compile(file: File) : QCompilationUnit {
		val result = Attr.attributeCompilationUnit(QLParser().parse(file))
		return result
	}

	fun generateKotlinTypes(comp: QCompilationUnit, rootPackageName: String = "com.prestongarno.ktq") : List<String> {
		val ktBuilder = KotlinFile.builder("com.prestongarno.ktq", "QTypes")
		createEnums(comp.enums).map { ktBuilder.addType(it) }

		val typeBuilder = QTypeBuilder(rootPackageName)
		comp.types.forEach { t -> ktBuilder.addType(typeBuilder.createType(t)) }
		val result = ktBuilder.build().toString()
		println(result)
		return listOf()
	}

}
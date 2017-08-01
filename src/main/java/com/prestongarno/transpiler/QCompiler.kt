package com.prestongarno.transpiler

import java.io.File

class QCompiler {
	fun compile(file: File) : QCompilationUnit {
		val result = Attr.attributeCompilationUnit(QLParser().parse(file))
		return result
	}

	fun generateKotlinTypes(comp: QCompilationUnit) : List<String> {
		TODO()
	}
}
package com.prestongarno.transpiler

import com.prestongarno.transpiler.kotlin.spec.*
import com.squareup.kotlinpoet.KotlinFile
import java.io.File

class QCompiler {
  fun compile(file: File): QCompilationUnit {
    val result = Attr.attributeCompilationUnit(QLParser().parse(file))
    return result
  }

  fun generateKotlinTypes(comp: QCompilationUnit,
      rootPackageName: String = "com.prestongarno.ktq",
      outputPath: String? = null) {
    val ktBuilder = KotlinFile.builder(rootPackageName, "QTypes")
    createEnums(comp.enums).map { ktBuilder.addType(it) }
    val typeBuilder = QTypeBuilder(rootPackageName)
    comp.types.map { ktBuilder.addType(typeBuilder.createType(it)) }
    comp.ifaces.map { ktBuilder.addType(typeBuilder.createType(it)) }
    val unionBuilder = UnionBuilder()
    comp.unions.map { ktBuilder.addType(unionBuilder.create(it, typeBuilder)) }
    comp.scalar.map { ktBuilder.addType(typeBuilder.createType(ScalarBuilder.toType(it))) }
    comp.inputs.map { ktBuilder.addType(InputTypeBuilder.createInputSpec(it, rootPackageName)) }

    val suppressedWarnings = listOf(
        "@file:Suppress(\"unused\")"
    )

    val result = suppressedWarnings.joinToString("\n") +
        "\n\n" +
        (ktBuilder.build().toString().replace("ArgBuilder_by_builder", "ArgBuilder by builder")
            .replace("> \\{\n.*stub\\((.*)\\)\n.*}".toRegex(), "> = stub($1)")
            .replace(" = null", "? = null"))
    if (outputPath != null)
      File("$outputPath/${rootPackageName.replace(".", "/")}/QTypes.kt").printWriter().use { out ->
        out.write(result)
      }
  }

}
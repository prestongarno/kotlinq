package com.prestongarno.transpiler

import com.prestongarno.transpiler.kotlin.spec.*
import com.squareup.kotlinpoet.KotlinFile
import java.io.File

class QCompiler {

  companion object {
    val LESS_THAN = "LESS_THAN"
    val GREATER_THAN = "GREATER_THAN"
    val COMMA = "_COMMA_"
  }

  fun compile(file: File): QCompilationUnit {
    val result = Attr.attributeCompilationUnit(QLParser().parse(file))
    return result
  }

  fun generateKotlinTypes(comp: QCompilationUnit,
      outputPath: String = "",
      rootPackageName: String = "com.prestongarno.ktq",
      fileName: String = "GraphQlModel") {

    val ktBuilder = KotlinFile.builder(rootPackageName, fileName)

    val ifaceTypes = QInterfaceBuilder(comp.ifaces).buildAll()
    ifaceTypes.forEach {
      ktBuilder.addType(it.second)
    }

    val typeBuilder = QTypeBuilder()
    comp.types.map { ktBuilder.addType(typeBuilder.createType(it)) }

    val unionBuilder = UnionBuilder()
    comp.unions.map { ktBuilder.addType(unionBuilder.create(it, typeBuilder)) }

    comp.scalar.map { ktBuilder.addType(typeBuilder.createType(ScalarBuilder.toType(it))) }
    comp.inputs.map { ktBuilder.addType(InputTypeBuilder.createInputSpec(it, rootPackageName)) }
    createEnums(comp.enums).map { ktBuilder.addType(it) }

    val suppressedWarnings = listOf(
        "@file:Suppress(\"unused\")"
    )

    val result = suppressedWarnings.joinToString("\n") +
        "\n\n" +
        (ktBuilder.build().toString().replace("ArgBuilder(.*)_by_args".toRegex(), "ArgBuilder$1 by args")
            .replace(LESS_THAN, "<")
            .replace(GREATER_THAN, ">")
            .replace(COMMA, ", ")
            .replace("> \\{\n.*stub\\((.*)\\)\n.*}".toRegex(), "> = stub($1)"))
            .replace(" = null\n".toRegex(), "? = null")
    if (outputPath.trim().isNotEmpty())
      File("$outputPath/${rootPackageName.replace(".", "/")}/$fileName.kt").printWriter().use { out ->
        out.write(result)
      }
  }

}
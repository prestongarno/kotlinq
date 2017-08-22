package com.prestongarno.transpiler

import com.prestongarno.transpiler.kotlin.spec.*
import com.prestongarno.transpiler.qlang.spec.QInterfaceDef
import com.prestongarno.transpiler.qlang.spec.QSymbol
import com.squareup.kotlinpoet.KotlinFile
import java.io.File
import kotlin.streams.toList

class QCompiler {

  companion object {
    val LESS_THAN = "LESS_THAN"
    val GREATER_THAN = "GREATER_THAN"
    val COMMA = "_COMMA_"
  }

  fun compile(file: File): QCompilationUnit = Attr.attributeCompilationUnit(QLParser().parse(file))

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

    val duplicates = comp.types.parallelStream().map { type ->
      type.fields.filter { it.inheritedType != null }
          .map { checkDuplicateCount(it, type.interfaces.map { it as QInterfaceDef })
          }.filter { it.isNotEmpty() }
          .toList()
    }.toList()

    println(duplicates.joinToString { "\n" })


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

fun checkDuplicateCount(field: QSymbol, ifaces: List<QInterfaceDef>): List<Pair<QInterfaceDef, QSymbol>> {
  val linked = mutableListOf<Pair<QInterfaceDef, QSymbol>>()
  ifaces.forEach { iface ->
    iface.fields.forEach { linked.add(Pair(iface, it)) }}
  val filter = linked.filter { it.second.name == field.name }
  return if(filter.size > 1) filter else emptyList()
}








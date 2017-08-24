package com.prestongarno.transpiler

import com.squareup.kotlinpoet.*
import java.io.File

class QCompiler {

  companion object {
    // String literals and replacement because of missing kotlinpoet features
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
    comp.getAllTypes().forEach { ktBuilder.addType(it) }

    // Probably should inherit input arg classes to prevent chance of concrete-type adding arguments failing
    val suppressedWarnings = listOf(
        "@file:Suppress(\"unused\")"
    )

    val rawFile = ktBuilder.build().toString()
    val result = suppressedWarnings.joinToString("\n") +
        "\n\n" +
        (rawFile.replace("ArgBuilder(.*)_by_args".toRegex(), "ArgBuilder$1 by args")
            .replace(LESS_THAN, "<")
            .replace(GREATER_THAN, ">")
            .replace(COMMA, ", ")
            .replace("> \\{\n.*stub\\((.*)\\)\n.*}".toRegex(), "> = stub($1)"))
            .replace(" = null\n".toRegex(), "? = null")
            .replace("\\nimport class(.*)by args".toRegex(), "\nimport$1")
    if (outputPath.trim().isNotEmpty())
      File("$outputPath/${rootPackageName.replace(".", "/")}/$fileName.kt").printWriter().use { out ->
        out.write(result)
      }
  }
}


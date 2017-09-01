package com.prestongarno.transpiler

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.ListConfig
import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.ListStub
import com.prestongarno.ktq.QConfigStub
import com.prestongarno.ktq.QInput
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QTypeConfigStub
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.TypeArgBuilder
import com.prestongarno.ktq.TypeListArgBuilder
import com.squareup.kotlinpoet.*
import java.io.File
import kotlin.reflect.KClass

class QCompiler internal constructor(val source: File, builder: QCompiler.Builder) {

  val packageName = builder.packageName
  val outputName = builder.outputName
  var compilation: QCompilationUnit? = null
  var rawResult: String = ""

  companion object {
    fun initialize(destination: String = "GraphTypes") = Builder(destination)
    // String literals and replacement because of missing kotlinpoet features
    val LESS_THAN = "LESS_THAN"
    val GREATER_THAN = "GREATER_THAN"
    val COMMA = "_COMMA_"
  }

  class Builder internal constructor(internal var outputName: String) {
    internal var packageName: String = "com.prestongarno.ktq"

    fun packageName(name: String) = apply { this.packageName = name }

    fun compile(file: File, result: (QCompilationUnit) -> Unit = {}): QCompiler {
      val qCompiler = QCompiler(file, this)
      qCompiler.compile()
      result(qCompiler.compile());
      return qCompiler
    }
  }

  fun result(consumer: (String) -> Unit) = apply {
    val ktBuilder = KotlinFile.builder(packageName, outputName)
    getResolvedImports().mapNotNull {
      it.simpleName
    }.let {
      ktBuilder.addStaticImport("com.prestongarno.ktq", *it.toTypedArray()) }
    getSchemaTypeHelpers().mapNotNull {
      it.simpleName
    }.let {
      ktBuilder.addStaticImport(com.prestongarno.ktq.QSchemaType::class, *it.toTypedArray())
    }
    compilation?.getAllTypes()?.forEach {
      ktBuilder.addType(it)
    }

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
    this.rawResult = result
    consumer(result)
  }

  fun writeToFile(destination: String) = apply {
    val shouldWrite = System.getProperty("-Dcom.prestongarno.ktq.compiler.writeTestFiles") ?: "false"
    if (shouldWrite == "true" && destination.trim().isNotEmpty())
      File("$destination/${packageName.replace(".", "/")}/$outputName.kt").printWriter().use { out ->
        out.write(rawResult)
      }
  }

  fun compile(): QCompilationUnit {
    this.compilation = Attr.attributeCompilationUnit(QLParser().parse(this.source))
    return compilation!!
  }
}

private fun getResolvedImports(): List<KClass<*>> {
  return listOf(
      ArgBuilder::class,
      InitStub::class,
      ListConfig::class,
      ListConfigType::class,
      ListInitStub::class,
      ListStub::class,
      QConfigStub::class,
      QInput::class,
      QSchemaType::class,
      QTypeConfigStub::class,
      Stub::class,
      TypeArgBuilder::class,
      TypeListArgBuilder::class
  )
}

private fun getSchemaTypeHelpers(): List<KClass<*>> =
    listOf(
        QSchemaType.QScalar::class,
        QSchemaType.QScalarList::class,
        QSchemaType.QType::class,
        QSchemaType.QTypeList::class
    )


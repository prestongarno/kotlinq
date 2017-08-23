package com.prestongarno.transpiler

import com.prestongarno.transpiler.kotlin.spec.*
import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.KotlinFile
import java.io.File
import java.util.*

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

    val ifaceTypes = QInterfaceBuilder(comp.ifaces).buildAll()
    ifaceTypes.forEach {
      ktBuilder.addType(it.second)
    }

    val typeBuilder = QTypeBuilder()
    comp.types.forEach {
      it.kotlinSpec = typeBuilder.createType(it, comp)
      ktBuilder.addType(it.kotlinSpec!!)
    }

    val unionBuilder = UnionBuilder()
    comp.unions.map { it.kotlinSpec = unionBuilder.create(it, typeBuilder, comp)
      ktBuilder.addType(it.kotlinSpec!!) }

    comp.scalar.map { it.kotlinSpec = typeBuilder.createType(ScalarBuilder.toType(it), comp)
      ktBuilder.addType(it.kotlinSpec!!) }
    comp.inputs.forEach { input ->
      ktBuilder.addType(InputTypeBuilder.createInputSpec(input, rootPackageName)
          .also { input.kotlinSpec = it }) }
    createEnums(comp.enums).forEach { spec -> ktBuilder.addType(spec) }

    // Probably should inherit input arg classes to prevent chance of concrete-type adding arguments failing
    val conflicts = comp.confictOverrides.toList()
    conflicts.forEach { (symbol, typeAndIfaces) ->
      verifyOverridingFields(symbol, typeAndIfaces.first, typeAndIfaces.second)
          .ifPresent { throw it }
    }

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

  private fun verifyOverridingFields(symbol: QField,
      declaring: QTypeDef,
      overriding: List<QInterfaceDef>): Optional<Throwable> {
    overriding.fold(overriding[0], { curr, next ->
      val first = curr.fields.find { it.type.name == symbol.type.name }!! // would have failed in attr stage if null
      val verifi = verifyInputArguments(symbol, first)

      if (verifi.isPresent)
        return Optional.of(Throwable("Input argument mismatch on type ${declaring.name}: ", verifi.get()))

      val comparingTo = next.fields.find { it.type.name == symbol.type.name }!!

      if (first.type != comparingTo.type)
        return Optional.of(IllegalStateException("Conflicting overriding property declarations on [ ${declaring.name}.${symbol.name} ] " +
            "from interfaces ${curr.name} ( ${symbol.name} declares type ${first.type.name} )" +
            " and ${next.name} (declares type ${comparingTo.type.name} )"))

      next
    })
    return Optional.empty()
  }

  /**
   * If conflicting input declarations, returns a throwable (in the optional)
   */
  private fun verifyInputArguments(symbol: QField, comparing: QField): Optional<Throwable> {
    comparing.args.forEach { arg ->
      symbol.args.find { it.name == arg.name }?.also {
        if (it.nullable != arg.nullable)
          return Optional.of(Throwable(conflictMessage("nullable", it.nullable, it.name, arg.name)))
        else if (it.isList != arg.isList)
          return Optional.of(Throwable(conflictMessage("list", it.isList, it.name, arg.name)))
        else if (it.type.name != arg.type.name)
          return Optional.of(Throwable(conflictMessage(arg.type.name, false, it.name, arg.name)))
      }
    }
    return Optional.empty()
  }

  private fun conflictMessage(property: String, concreteFieldCondition: Boolean, propName: String, argName: String)
      = "Conflicting input argument declaration, ${boolToStr(concreteFieldCondition, property)} " +
      "$ arg ${propName} overriding ${boolToStr(!concreteFieldCondition, property)} ${propName} arg $argName"

  private fun boolToStr(ifIs: Boolean, name: String) = if (ifIs) "" else "non-$name"

}


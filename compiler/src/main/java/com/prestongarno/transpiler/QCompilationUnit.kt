package com.prestongarno.transpiler

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.TypeArgBuilder
import com.prestongarno.transpiler.qlang.spec.*
import com.squareup.kotlinpoet.*
import java.util.*

/**
 * Contains instance variables of Lists of different kinds of GraphQL schema declarations, each one with a field
 * list of Strings for which each entry represents a declaration within a type declaration (primitive model, field
 * type, or field list of types
 */
class QCompilationUnit(val types: List<QTypeDef>,
                       val ifaces: List<QInterfaceDef>,
                       val inputs: List<QInputType>,
                       val scalar: List<QScalarType>,
                       val enums: List<QEnumDef>,
                       val unions: List<QUnionTypeDef>) {

  private fun <T : QDefinedType> addAllStuff(vararg foo: List<T>): List<QDefinedType> {
    val whyThis = LinkedList<QDefinedType>()
    for (f in foo)
      whyThis.addAll(f)
    Collections.sort(whyThis, { o1, o2 -> o1.name.compareTo(o2.name) })
    return whyThis
  }

  val all: Array<QDefinedType> = addAllStuff(types, ifaces, unions, inputs, scalar, enums).toTypedArray()

  fun getAllTypes(): List<TypeSpec> =
      resolveConflicts().toMutableList()
          .also { lizt ->
            lizt.addAll(
                all.map {
                  if (it.description.isNotEmpty())
                    it.toKotlin()
                        .toBuilder()
                        .addKdoc(CodeBlock.of(it.description, "%W"))
                        .build()
                  else it.toKotlin()
                })
          }

  private val conflictOverrides = mutableMapOf<QField, Pair<QTypeDef, List<QInterfaceDef>>>()

  fun addConflicts(conflicts: List<Pair<QField, Pair<QTypeDef, List<QInterfaceDef>>>>) {
    conflictOverrides.putAll(conflicts)
  }

  val stateful: List<QStatefulType> by lazy {
    LinkedList<QStatefulType>()
        .addAllAnd(types)
        .addAllAnd(ifaces)
        .addAllAnd(inputs)
  }

  private fun <T : Any> MutableList<T>.addAllAnd(values: Collection<T>): MutableList<T> {
    this.addAll(0, values)
    return this
  }

  fun find(key: String) = all.find(key)

  /** Extension method for binary searching all types for attributing all fields
   */
  private fun Array<QDefinedType>.find(key: String): QDefinedType? {
    // if scalar type getFromMap the predefined ones in Scalar companion object
    val match = Scalar.match(key)
    if (match != Scalar.UNKNOWN) return Scalar.getType(match)

    var low = 0
    var high = size - 1
    var mid: Int
    while (low <= high) {
      mid = (low + high).ushr(1)
      val cmp = this[mid].name.compareTo(key);
      if (cmp < 0)
        low = mid + 1;
      else if (cmp > 0)
        high = mid - 1;
      else
        return this[mid]; // key found
    }
    return null
  }

  private fun resolveConflicts(): MutableList<TypeSpec> {
    return this.conflictOverrides.toList().mapNotNull { (symbol, pair: Pair<QTypeDef, List<QInterfaceDef>>) ->
      val baseInputClazzName = inputBuilderClassName(symbol.name)
      val superclazzType: TypeName = ClassName.bestGuess("Base" + baseInputClazzName)
      // 1) check that all multi-inherited fields are:
      //      i ) same type
      //      ii) concrete field declares all required args as the iface
      //      iii)extending #i, but checking `isList`, `nullable`, etc.
      verifyOverridingFields(symbol, pair.first, pair.second)
          .ifPresent { throw it }
      // need to build subclass for fields that have input argument(s)
      if (symbol.args.isNotEmpty()) {
        // 2) Add an argument builder class inside of the type/class for that particular field, extends #3
        // 3) Create top-level abstract builder class which unifies multi-override difference
        val dummy = QField(
            symbol.name,
            symbol.type,
            emptyList(), // TODO filter all base arguments and declare in superclass for muh polymorphism
            symbol.directive,
            symbol.isList,
            symbol.nullable).also { it.flag(QField.BuilderStatus.TOP_LEVEL); it.abstract(true) }
        buildArgBuilder(dummy,
            if (dummy.type is QScalarType || dummy.type is QEnumDef)
              ArgBuilder::class
            else TypeArgBuilder::class,
            superclazzType)
            .addModifiers(KModifier.ABSTRACT)
            .build()
      } else null
    }.distinctBy { it.name }
        .toMutableList()
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
      "$ arg $propName overriding ${boolToStr(!concreteFieldCondition, property)} $propName arg $argName"

  private fun boolToStr(ifIs: Boolean, name: String) = if (ifIs) "" else "non-$name"

}

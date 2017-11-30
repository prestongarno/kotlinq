package com.prestongarno.ktq.compiler

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

infix fun KClass<*>.directlyImplements(superinterface: KClass<*>) = apply {
  supertypes.find { it.classifier == superinterface }
      ?: throw  IllegalArgumentException(
      "${this.qualifiedName} does not implent ${superinterface.qualifiedName}")
}

infix fun KClass<*>.implements(superinterface: KClass<*>) {
  allSuperclasses.find { it == superinterface }
      ?: throw  IllegalArgumentException(
      "${this.qualifiedName} does not implent ${superinterface.qualifiedName}")
}

fun KClass<*>.func(name: String, block: (KFunction<*>) -> Unit) {
  functions.find { it.name == name }?.apply(block)
      ?: throw IllegalArgumentException("No such function '$name' on class $simpleName")
}

fun KClass<*>.kprop(name: String, block: (KProperty<*>) -> Unit = { }): KProperty<*> {
  return memberProperties.find { it.name == name }?.apply(block)
      ?: throw IllegalArgumentException("No such property '$name' on class $simpleName")
}

infix fun KProperty<*>.requireReturns(match: KClass<*>) {
  returnType.classifier eq match
}

infix fun KFunction<*>.requireReturns(match: KClass<*>) {
  returnType.classifier eq match
}

fun KProperty<*>.typeArgumentsMatch(match: List<String>) {
  returnType.arguments.mapNotNull(KTypeProjection::type)
      .mapNotNull(KType::classifier)
      .map { it as KClass<*> }
      .mapNotNull(KClass<*>::simpleName)
      .forEachIndexed { index, name ->
        require(match[index] == name)
      }
}

// for making assertions about an attribute's state
sealed class Qualification(val attributeName: String)

// [$clazz with $qualification] -> ClassReflectionAssertion -> [$it {assertion} $parameter]
class ParameterAssertion(
    val clazz: KClass<*>,
    val qualification: ParameterQualification,
    val positivity: Boolean = true
)

infix fun ParameterAssertion.constructorParametersContain(match: String): KParameter =
    clazz.primaryConstructor?.parameters?.find { it.name == match }?.apply {
      when (qualification) {
        ParameterQualification.nullability -> require(type.isMarkedNullable == positivity) {
          qualification.errorMessage("constructor parameter", match, positivity)
        }
      }
    } ?: throw IllegalArgumentException("No such constructor parameter '$match'")

infix fun KClass<*>.constructorParametersMatchExactly(arguments: Map<String, KClass<*>>) {
  ParameterAssertion(this, ParameterQualification.nothingInParticular)
      .constructorParametersMatchExactly(arguments)
}

infix fun ParameterAssertion.constructorParametersMatchExactly(arguments: Map<String, KClass<*>>) {
  val actualParams = clazz.primaryConstructor?.parameters
      ?.map { it to (it.type.classifier as KClass<*>) }
      ?: if (arguments.isEmpty())
          return
            else
          throw IllegalArgumentException("Constructor parameters were empty, expected: $arguments")
  arguments.entries.forEachIndexed { index, entry ->
    actualParams.getOrNull(index)?.let { (param, actualClazz) ->
      param.name eq entry.key
      actualClazz eq entry.value
      when (qualification) {
        ParameterQualification.nullability -> require(param.type.isMarkedNullable == positivity)
      }
    } ?: throw IllegalArgumentException("No such parameter at index [$index] ${entry.key}: ${entry.value}")
  }
}

infix fun KClass<*>.mustHave(qualification: ParameterQualification) =
    ParameterAssertion(this, qualification)

infix fun KClass<*>.without(qualification: ParameterQualification) =
    ParameterAssertion(clazz = this, qualification = qualification, positivity = false)
infix fun KClass<*>.with(qualification: ParameterQualification) =
    ParameterAssertion(clazz = this, qualification = qualification, positivity = true)

class ParameterQualification private constructor(
    attributeName: String
) : Qualification(attributeName) {

  fun errorMessage(target: String, targetName: String, positivity: Boolean): String =
      "Not true that $target '$targetName' ${
      if (positivity) "is" else "is not"
      } $attributeName"

  companion object {
    val nullability = ParameterQualification("nullable")
    val nothingInParticular = ParameterQualification("nothingInParticular")
  }
}

infix fun KClass<*>.hasPropertyNamed(match: String) = apply {
  memberProperties.find { it.name == match }?.ignore()
      ?: throw IllegalArgumentException("No such property $match on class $simpleName")
}

class KTypeSubject(val type: KType) {

  open class Attribute(val verb: (KType) -> Unit)

  companion object {
    fun argumentsMatching(vararg qualifiedNames: String): Attribute =
        typeArgumentMatch(qualifiedNames)

    fun reifiedArgumentsMatching(vararg classes: KClass<*>) = Attribute { type ->
      require(classes.size == type.arguments.size && type.arguments.zip(classes).all { (type, clazz) ->
        (type.type?.classifier as? KClass<*>)?.let { it == clazz } == true
      })
    }

    private val typeArgumentMatch: (Array<out String>) -> Attribute = {
      Attribute { type ->
        require(it.size == type.arguments.size && type.arguments.zip(it).all { (type, name) ->
          type.type?.toString() == name
        })
      }
    }
  }

}

infix fun KType.mustHave(attribute: KTypeSubject.Attribute) = apply {
  attribute.verb.invoke(this)
}


package com.prestongarno.ktq.unions

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.adapters.StringDelegate
import org.junit.Test

interface MexicanFood : QSchemaType {
  val ingredients: ListInitStub<FoodIngredient>
}

interface FoodIngredient : QSchemaType {
  val name: StringDelegate<ArgBuilder>
  val description: StringDelegate<ArgBuilder>
}

object Query : QSchemaType {
  val searchForThing by QUnion.stub(Thing)

  class SearchForThingArgs(args: ArgBuilder) : ArgBuilder by args {
    fun term(value: String) = addArg("term", value)
  }
}

object Thing : QSchemaUnion by QSchemaUnion.create(Thing) {
  fun onCar(init: () -> QModel<Car>) = on(init)
  fun onTaco(init: () -> QModel<Taco>) = on(init)
}

object Car : QSchemaType {
  val make by QScalar.stringStub()
  //val carType by QScalar.stubPrimitive<CarType>()
}

enum class CarType { COUPE, SEDAN, MINIVAN, OTHER }

object Taco : MexicanFood {
  override val ingredients by QTypeList.stub<FoodIngredient>()
  val weight by QScalar.intStub()
}

object Burrito : MexicanFood {
  override val ingredients by QTypeList.stub<FoodIngredient>()
}

object Hamburger : FoodIngredient {
  override val name: StringDelegate<ArgBuilder> by QScalar.stringStub()
  override val description by QScalar.stringStub()
}

object Lettuce : FoodIngredient {
  override val name by QScalar.stringStub()
  override val description by QScalar.stringStub()
  //val lettuceKind: Stub<LettuceType> by QScalar.stubPrimitive()
}

enum class LettuceType : QSchemaType {
  ICEBERG,
  ROMAINE,
  SPINACH,
  MIXED
}

class MyHamburger : QModel<FoodIngredient>(Hamburger)

class MyTaco : QModel<Taco>(Taco) {
  val foo by model.ingredients.init { MyHamburger() }
}

class Sample {
  @Test fun testClassEvenLoads() {

    val myCarModel = {
      object : QModel<Car>(Car) {
        val make by model.make
      }
    }
    val myTacoModel = {
      object : QModel<Taco>(Taco) {
        val weightInKg by model.weight
            .withDefault(100000000)
            .config {
              this.addArg("Hello", "World")
              println("Hello world")
            }
      }
    }

    val myQuery = object : QModel<Query>(Query) {
      val thingSearch by model.searchForThing.fragment {
        onCar(myCarModel)
        onTaco(myTacoModel)
      }
    }
    println(MyTaco().toGraphql(false))
    println(myQuery.toGraphql(false))
  }
}

package com.prestongarno.ktq.unions

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.stubs.ListInitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaEnum
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.stubs.UnionInitStub
import com.prestongarno.ktq.adapters.IntegerArrayDelegate
import com.prestongarno.ktq.adapters.StringDelegate
import com.prestongarno.ktq.getFragments
import org.junit.Test

interface Food : QSchemaType {
  val ingredients: ListInitStub<FoodIngredient>
}

interface FoodIngredient : QSchemaUnion {
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
  fun onHamburger(init: () -> QModel<Hamburger>) = on(init)
}

object Car : QSchemaType {
  val make by QScalar.stringStub()
  //val carType by QScalar.stubPrimitive<CarType>()
}

enum class CarType { COUPE, SEDAN, MINIVAN, OTHER }

object Taco : Food {
  override val ingredients by QUnion.stub<FoodIngredient>()
  val weight by QScalar.intStub()
}

object Burrito : Food {
  override val ingredients by QTypeList.stub<FoodIngredient>()
}

object Hamburger : Food {
  val numberOfPatties: IntegerArrayDelegate<ArgBuilder> by QScalarArray.intArrayStub()
  val whatsOnThisBurger: UnionInitStub<FoodIngredient> by QUnion.stub()
}

object Lettuce : FoodIngredient {
  override val name by QScalar.stringStub()
  override val description by QScalar.stringStub()
  val lettuceKind by QEnum.stub<LettuceType>()
}

enum class LettuceType : QSchemaEnum {
  ICEBERG,
  ROMAINE,
  SPINACH,
  MIXED
}

class MyHamburger : QModel<Hamburger>(Hamburger) {
  val numberOfPatties by model.numberOfPatties
}

class MyTaco : QModel<Taco>(Taco) {
  val foo by model.ingredients.querying { MyHamburger() }
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

    val myHamburger = {
      object : QModel<Hamburger>(Hamburger) {
        val numberOfPatties by model.numberOfPatties
      }
    }

    val myQuery = object : QModel<Query>(Query) {
      val thingSearch by model.searchForThing.fragment {
        onCar(myCarModel)
        onTaco(myTacoModel)
        onHamburger(myHamburger)
      }
    }
    println(MyTaco().toGraphql(false))
    println(myQuery.toGraphql(false))
    myQuery.getFragments().forEachIndexed { i, x -> println("#$i = " + x.model.toGraphql(false)) }
  }
}

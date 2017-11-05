package com.prestongarno.ktq.unions

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaEnum
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaType.QEnum
import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.QSchemaType.QScalarArray
import com.prestongarno.ktq.QSchemaType.QUnionList
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.adapters.IntegerArrayDelegate
import com.prestongarno.ktq.adapters.StringDelegate
import com.prestongarno.ktq.hooks.FragmentProvider
import com.prestongarno.ktq.stubs.UnionListInitStub
import org.junit.Test
import kotlin.reflect.jvm.isAccessible

//#############################################################
// Example Stub/Generated API
//#############################################################
interface Food : QSchemaType {
  val ingredients: UnionListInitStub<IngredientType>
}

interface FoodIngredient : QSchemaType {
  val name: StringDelegate<ArgBuilder>
  val description: StringDelegate<ArgBuilder>
}

object IngredientType : QSchemaUnion by QSchemaUnion.create(IngredientType) {
  fun onLettuce(init: () -> QModel<Lettuce>) = on(init)
}

object Query : QSchemaType {
  val searchForThing by QUnionList.stub<Thing>()

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
  override val ingredients by QUnionList.stub<IngredientType>()
  val weight by QScalar.intStub()
}

object Burrito : Food {
  override val ingredients by QUnionList.stub<IngredientType>()
}

object Hamburger : Food {
  val numberOfPatties: IntegerArrayDelegate<ArgBuilder> by QScalarArray.intArrayStub()
  override val ingredients by QUnionList.stub<IngredientType>()
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


//#############################################################
// Example API Usage
//#############################################################
class MyHamburger : QModel<Hamburger>(Hamburger) {
  val numberOfPatties by model.numberOfPatties
}

class MyLettuceModel : QModel<Lettuce>(Lettuce) {

}

class MyTaco : QModel<Taco>(Taco) {
  val foo by model.ingredients.fragment {
    println("$this + ${super.fields}")
    onLettuce { MyLettuceModel() }
    println("$this + ${(this.queue.reset().also {
      for (fragmentGenerator in it) {
        queue.addFragment(fragmentGenerator)
      }
    })}")
  }
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
              addArg("Hello", "World") // can arbitrarily add arguments to graphql queries
            }
      }
    }

    val myHamburger = {
      object : QModel<Hamburger>(Hamburger) {
        val numberOfPatties by model.numberOfPatties {
          println("Hello DSLs")
        }
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
    println(myTacoModel().toGraphql(false))
    println(myQuery.toGraphql(false))
    println(myHamburger().toGraphql(false))
    myQuery::thingSearch.apply { isAccessible = true }.getDelegate().let {
      (it as? FragmentProvider)?.run {
        fragments.forEachIndexed { i, x ->
          println("#$i = " + x.model.toGraphql(true))
        }
      }
    }
  }
}

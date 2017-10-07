package com.prestongarno.ktq.unions.experimental

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.Stub
import org.intellij.lang.annotations.Language
import org.junit.Test
import kotlin.test.assertTrue

/**================================================================*/
/**  Example                                                       */
/**================================================================*/

class MyUserModel : QModel<User>(User) {
  val name by model.name
}

object Actor : QSchemaUnion by QSchemaUnion.create(Actor) {
  fun user(init: Actor.() -> QModel<User>) = on<Actor> { init() }
  fun bot(init: Actor.() -> QModel<Bot>) = on<Actor> { init() }
}

class MyBotModel : QModel<Bot>(Bot) {
  val owner by model.owner.fragment {
    user { MyUserModel() }
    bot { MyBotModel() }
  }
  val name by model.name

}


object Bot : QSchemaType {
  val name: Stub<String> by QScalar.stub()

  val owner by QUnion.stub(Actor)
}

object User : QSchemaType {
  val name: Stub<String> by QScalar.stub()
}

object Query : QSchemaType {
  val me: InitStub<Actor> by QType.stub()
}

class ExperimentalUnionModel {

  @Test fun testModelStructure() {
    val botModel2 = MyBotModel()
    val botModel = MyBotModel()

    //val botModel2 = MyBotModel()

    val input = JsonObject(mapOf(
        Pair("__typename", "User"),
        Pair("name", "Preston")
    ))
    val input2 = JsonObject(mapOf(
        Pair("__typename", "User"),
        Pair("name", "Garno")
    ))

    @Language("JSON") val response = """
      {
        "name": "some bot or whatever",
        "owner": {
          "__typename": "User",
          "name": "preston"
        }
      }
      """
    println(botModel.toGraphql(false))
    botModel.onResponse(response)
    println(botModel.fields)
    require(botModel.owner !== QModel.NONE)
    require(botModel.owner is MyUserModel)

    assertTrue(botModel.accept(input))

    assertTrue((botModel.owner as MyUserModel).name == "Preston")

    println(botModel.owner)
    println(botModel.toGraphql(false))
    println(Actor.user { MyUserModel() }.toGraphql(false))
    println(MyUserModel().toGraphql(false))
    //assertTrue(botModel.foobar.fragments.size == 2)
    //assertTrue((MyBotModel().foobar as UnionStubImpl<*>).fragments.size == 2)
    assertTrue(botModel.owner !== MyBotModel().owner)


  }
}


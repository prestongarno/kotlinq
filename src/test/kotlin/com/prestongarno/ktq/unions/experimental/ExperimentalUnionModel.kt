package com.prestongarno.ktq.unions.experimental

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.UnionAdapter
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
  fun user(init: () -> QModel<User>) = on(init)
  fun bot(init: () -> QModel<Bot>) = on(init)
}

class MyBotModel : QModel<Bot>(Bot) {

  val name by model.name

  val owner by model.owner.fragment {
    user { MyUserModel() }
    bot { MyBotModel() }
  }

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
    val botModel = MyBotModel()

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

    //println(botModel.toGraphql(false))
    botModel.onResponse(response)
    require(botModel.owner != null)
    require(botModel.owner is MyUserModel)

    assertTrue(botModel.accept(input))

    assertTrue((botModel.owner as MyUserModel).name == "Preston")

    println(botModel.owner)
    println(botModel.toGraphql(false))
    println(MyUserModel().toGraphql(false))
    assertTrue(botModel.owner !== MyBotModel().owner)


  }
}


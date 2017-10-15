package com.prestongarno.ktq.unions.experimental

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.FieldAdapter
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.getFragments
import org.intellij.lang.annotations.Language
import org.junit.Test
import kotlin.test.assertTrue

/**================================================================*/
/**  Example                                                       */
/**================================================================*/

class MyUserModel : QModel<User>(User) {
  val username by model.name
  val user_email by model.email
}

object Actor : QSchemaUnion by QSchemaUnion.create(Actor) {
  fun user(init: () -> QModel<User>) = on(init)
  fun bot(init: () -> QModel<Bot>) = on(init)
}

class MyBotModel : QModel<Bot>(Bot) {

  val name by model.name

  val ownerModel by model.owner.fragment {
    user { MyUserModel() }
    bot { MyBotModel() }
  }

  val creatorModel by model.creator.init { MyUserModel() }

}


object Bot : QSchemaType {
  val name: Stub<String> by QScalar.stub()

  val owner by QUnion.stub(Actor)

  val creator by QType.stub<User>()
}

object User : QSchemaType {
  val name: Stub<String> by QScalar.stub()
  val email by QScalar.stub<String>()
}

object Query : QSchemaType {
  val me: InitStub<Actor> by QType.stub()
}

class ExperimentalUnionModel {

  @Test fun testModelStructure() {

    val botModel = MyBotModel()
    @Language("JSON") val response = """
      {
        "name": "some bot or whatever",
        "owner": {
          "__typename": "User",
          "name": "preston"
        }
      }
      """
    val thread2 = {
      object : Thread() {
        override fun start() {
          for (i in 1..1000) MyBotModel().run {
            require(this.onResponse(response));
            require(resolved)
          }
        }
      }
    }
    require(botModel.onResponse(response))
    require(botModel.ownerModel is MyUserModel)
    require(botModel.resolved)
    assertTrue((botModel.ownerModel as MyUserModel).username == "preston")

    for (i in 1..100) {
      thread2().start()
    }
    for (i in 1..1000) assertTrue(Bot.owner === Bot.owner)
  }

  @Test fun testFragmentGraphTraversal() {
    val bor = object : QModel<Bot>(Bot) {
      val borName by model.name
    }
    val bot = MyBotModel()
    @Language("JSON") val response = """
      {
        "name": "My Bot",
        "foo": "foo"
      }
      """
    @Language("JSON") val botresponse = """
      {
        "name": "some bot or whatever",
        "owner": {
          "__typename": "User",
          "name": "preston",
          "email": "prestongarno@gmail.com"
        },
        "creator": {
          "name": "PRESTON_GARNO",
          "email": "preston.garno35@gmail.com"
        }
      }
      """
    bor.accept(Parser().parse(response.byteInputStream()) as JsonObject)
    // TODO -> this is because `onProvideDelegate` doesn't instantiate a new instance
    require(bor.borName == "My Bot")
    bot.accept(Parser().parse(botresponse.byteInputStream()) as JsonObject)
  }
}


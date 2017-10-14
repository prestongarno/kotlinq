package com.prestongarno.ktq.unions.experimental

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
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
  val name by model.name
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
    println(botModel.toGraphql(false))
    require(botModel.onResponse(response))
    require(botModel.ownerModel is MyUserModel)
    require(botModel.resolved)
    assertTrue((botModel.ownerModel as MyUserModel).name == "preston")

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
    bor.accept(Parser().parse(response.byteInputStream()).also { println(it) } as JsonObject)
    println(bor.borName)
    bor.fields.forEach {
      println("prop : ${it.property.fieldName} (${it.property.typeName})")
    }
    bot.getFragments().forEach { println(it) }
    require(bot.fields.size == 2)
    bot.fields.forEach { println("${it.property.fieldName} (${it.property.typeName})") }
  }
}


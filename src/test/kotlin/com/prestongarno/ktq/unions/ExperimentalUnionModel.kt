package com.prestongarno.ktq.unions

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.adapters.TypeStubAdapter
import org.intellij.lang.annotations.Language
import org.junit.Test
import kotlin.properties.Delegates
import kotlin.reflect.jvm.isAccessible
import kotlin.test.assertTrue

class MyUserModel : QModel<UserObject>(UserObject) {
  val username by UserObject.name
  val user_email by UserObject.email
}

object Actor : QSchemaUnion by QSchemaUnion.create(Actor) {
  fun user(init: () -> QModel<UserObject>) = on(init)
  fun bot(init: () -> QModel<BotObject>) = on(init)
}

class MyBotModel : QModel<BotObject>(BotObject) {

  val name by BotObject.name

  val ownerModel by BotObject.owner.fragment {
    user { MyUserModel() }
    bot { MyBotModel() }
  }

  val creatorModel by BotObject.creator.init { MyUserModel() }

}


object BotObject : QSchemaType {
  val name: Stub<String> by QScalar.stub()

  val owner by QUnion.stub(Actor)

  val creator by QType.stub<UserObject>()
}

object UserObject : QSchemaType {
  val name: Stub<String> by QScalar.stub()
  val email by QScalar.stub<String>()
}

object Query : QSchemaType {
  val me: InitStub<Actor> by QType.stub()
}

class ExperimentalUnionModel {

  @Test fun testModelStructure() {
    @Language("JSON") val response = """
      {
        "name": "some bot or whatever",
        "owner": {
          "__typename": "User",
          "name": "preston"
        }
      }
      """
    var botModel: MyBotModel by Delegates.notNull()
    try {
      botModel = MyBotModel()
    } catch (ex: Exception) {
      ex.printStackTrace()
    }
    require(botModel.onResponse(response))
    require(botModel.ownerModel is MyUserModel)
    require(botModel.resolved)
    assertTrue((botModel.ownerModel as MyUserModel).username == "preston")
  }

  @Test fun testFragmentGraphTraversal() {
/*    val bor = object : QModel<Bot>(Bot) {
      val borName by model.name
    }*/
    //val bot = MyBotModel()
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
    println(botresponse)
    println(QModel<BotObject>(BotObject).toGraphql())
/*    println(bot.toGraphql())
    //bor.accept(Parser().parse(response.byteInputStream()) as JsonObject)
    // TODO -> this is because `onProvideDelegate` doesn't instantiate a create instance
    bot.accept(Parser().parse(botresponse.byteInputStream()) as JsonObject)
    require(bot::creatorModel.let { it.isAccessible = true; it.getDelegate()!! }::class != TypeStubAdapter::class)
    bot.fields.forEach { println(it.graphqlProperty) }
    //require(bor.borName == "My Bot")
    require(bot.ownerModel is MyUserModel)
    (bot.ownerModel as? MyUserModel)?.user_email.also { email ->
      require(email == "prestongarno@gmail.com")
    }*/
  }
}


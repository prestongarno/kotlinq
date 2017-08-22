package com.prestongarno.ktq

import org.junit.Test
import java.util.function.Consumer
import kotlin.reflect.jvm.reflect

/**
 * This needs to be fixed because of GraphQL's ability to have types implementing interfaces with the same names
 * After generating types, need to check all types for conflicting overriding properties
 *    1) Check for a type with 2+ ifaces with same field names
 *    2) check that the field on both is the same type
 *    3) check that the input arguments are the same for both
 *    4) fail for now if requires input arguments for different type, because technically it's invalid GraphQL also
 *    5) move the input arg/builder class to top level
 *    6) remove the inner class classifier on the type declaration in the interface
 *    7) ???
 *    8) Profit
 */
class InputArguments {
  @Test
  fun tesetInputArgumentCreation() {
    println(UserImpl::avatarUrl.reflect())
    Guac("", Consumer {  }, Runnable {  })
  }
}

object UserImpl : QType, RepoOwner, Actor2 {
  override val avatarUrl: Config<AvatarArgsImpl, URI> by lazy { configStub(AvatarArgsImpl()) }
}

interface Actor2 : QType {

  val avatarUrl: Config<AvatarArgsImpl, URI>

}

class AvatarArgsImpl(args: ArgBuilder<URI> = ArgBuilder.create()) : ArgBuilder<URI> by args {
  fun size(value: Int): AvatarArgsImpl = apply { addArg("size", value) }
}

interface RepoOwner : QType {
  val avatarUrl: Config<AvatarArgsImpl, URI>

}

class Guac(somethingElse: String, cheese: java.util.function.Consumer<Byte>, bean: Runnable) :
    java.util.function.Consumer<kotlin.Byte> by cheese, java.lang.Runnable by bean

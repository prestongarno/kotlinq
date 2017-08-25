@file:Suppress("unused")

package com.prestongarno.ktq

abstract class BaseAvatarArgs(args: ArgBuilder = ArgBuilder.create<URLY, BaseAvatarArgs>()) : ArgBuilder by args

interface ActorFoo : QType {
  val avatar: Config<URLY, BaseAvatarArgs>

  val login: Stub<String>

  val url: Stub<URLY>
}

object OrganizationFoo : QType, SomeConflict, ActorFoo {
  val owner: InitStub<UserFoo> by lazy { typeStub<UserFoo>() }

  val name: Stub<String> by lazy { stub<String>() }

  val members: InitStub<UserFoo> by lazy { typeStub<UserFoo>() }

  override val avatar: Config<URLY, AvatarArgs> by lazy { configStub<URLY, AvatarArgs>(AvatarArgs()) }

  override val login: Stub<String> by lazy { stub<String>() }

  override val url: Stub<URLY> by lazy { stub<URLY>() }

  override val foobar: Stub<String> by lazy { stub<String>() }

  class AvatarArgs(args: ArgBuilder = ArgBuilder.create<URLY, AvatarArgs>()) : BaseAvatarArgs(args) {
    fun size(value: Int): AvatarArgs = apply { addArg("size", value) }

  }
}

interface SomeConflict : QType {
  val avatar: Config<URLY, BaseAvatarArgs>

  val foobar: Stub<String>
}

object URLY : QType {
  val value: Stub<String> by lazy { stub<String>() }
}

object UserFoo : QType, ActorFoo {
  override val avatar: Config<URLY, AvatarArgs> by lazy { configStub<URLY, AvatarArgs>(AvatarArgs()) }

  override val login: Stub<String> by lazy { stub<String>() }

  override val url: Stub<URLY> by lazy { stub<URLY>() }

  class AvatarArgs(args: ArgBuilder = ArgBuilder.create<URLY, AvatarArgs>()) : BaseAvatarArgs(args) {
    fun size(value: Int): AvatarArgs = apply { addArg("size", value) }

  }
}

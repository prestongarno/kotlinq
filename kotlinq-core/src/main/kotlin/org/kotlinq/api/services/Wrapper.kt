package org.kotlinq.api.services

import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject


internal
abstract class Wrapper<T : Any>(private val default: T, val clazz: KClass<T>)
  : ConfigurableDependency<T> {

  @Volatile
  private var _instance: T = default

  final
  override fun use(instance: T) = synchronized(this) {
    this._instance = if (instance !== this
            && clazz.companionObject != instance::class) instance else default
  }

  final
  override fun instance(): T = _instance

  final override fun useDefault() =
      use(this.default)
}



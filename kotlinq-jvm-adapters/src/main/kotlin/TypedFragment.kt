package org.kotlinq.jvm

import org.kotlinq.api.Fragment
import org.kotlinq.api.Kotlinq
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties


class TypedFragment<T : Data> @PublishedApi internal constructor(
    val clazz: KClass<T>
) : Fragment by clazz() {


  companion object {

    inline fun <reified T : Data> typedFragment() = TypedFragment(T::class)

    operator fun KClass<*>.invoke(): Fragment = thisClass(this)

    @PublishedApi
    internal fun thisClass(clazz: KClass<*>): Fragment {
      val typeName = clazz.simpleName!!
      val builder = Kotlinq.newContextBuilder()

      clazz.memberProperties.forEach {

        println("$it (${it::class}")
      }
      println("=".repeat(100))

      clazz.members.forEach {
        println("$it (${it::class}")
      }
      TODO("...")
    }
  }
}
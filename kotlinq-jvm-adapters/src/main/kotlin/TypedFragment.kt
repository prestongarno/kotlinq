package org.kotlinq.jvm

import org.kotlinq.api.Fragment
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.PropertyInfo
import org.kotlinq.jvm.TypedFragment.Companion.thisClass
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties


class TypedFragment<T : Data> @PublishedApi internal constructor(
    val clazz: KClass<T>
) : Fragment by clazz() {


  companion object {

    inline fun <reified T : Data> typedFragment() = TypedFragment(T::class)


    @PublishedApi
    internal fun thisClass(clazz: KClass<*>): Fragment =
        clazz.declaredMemberProperties.mapNotNull {
          it with (it.returnType.scalarKind() ?: it.returnType.dataKind())
        }.map { (prop, kind) ->
          PropertyInfo.propertyNamed(prop.name)
              .typeKind(kind)
              .build().let {
                @Suppress("UNCHECKED_CAST")
                if (kind.isScalar)
                  Kotlinq.adapterService.scalarAdapters.newAdapter(it)
                else Kotlinq.adapterService.instanceProperty(
                    PropertyInfo.propertyNamed(prop.name).typeKind(kind).build(),
                    (prop.returnType.rootType.clazz as KClass<Data>).invoke())
              }
        }.let {

          use(Kotlinq.newContextBuilder()) {
            it.forEach { register(it) }
          }

        }.build(clazz.simpleName!!)
  }
}

internal
operator fun KClass<*>.invoke(): Fragment = thisClass(this)


infix fun <T : Any, U : Any> T.with(other: U?): Pair<T, U>? =
    other?.let { this to it }

fun <T> use(value: T, block: T.() -> Unit) = value.apply(block)

package org.kotlinq.jvm

import org.kotlinq.api.Fragment
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.PropertyInfo
import org.kotlinq.jvm.TypedFragment.Companion.reflectionFragment
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties


class TypedFragment<T : Data> @PublishedApi internal constructor(
    val clazz: KClass<T>,
    block: TypedFragmentScope<T>.() -> Unit = { }
) : Fragment by clazz(block) {

  companion object {

    inline fun <reified T : Data> typedFragment(
        noinline block: TypedFragmentScope<T>.() -> Unit = { /* nothing */ }
    ) = TypedFragment(T::class, block)


    @PublishedApi
    internal fun <T : Data> reflectionFragment(clazz: KClass<T>, block: TypedFragmentScope<T>.() -> Unit = { /* nothing */ }): Fragment =
        clazz.declaredMemberProperties.mapNotNull {
          it with (it.returnType.scalarKind() ?: it.returnType.dataKind())
        }.mapNotNull { (prop, kind) ->
          PropertyInfo.propertyNamed(prop.name)
              .typeKind(kind)
              .build().let {
                @Suppress("UNCHECKED_CAST")
                if (kind.isScalar)
                  Kotlinq.adapterService.scalarAdapters.newAdapter(it)
                else
                  (prop.returnType.rootType.clazz as? KClass<T>)?.let {
                    Kotlinq.adapterService.instanceProperty(PropertyInfo
                        .propertyNamed(prop.name)
                        .typeKind(kind)
                        .build(),
                        it(block))
                  }
              }
        }.useWith(Kotlinq.newContextBuilder()) { builder ->
          this.forEach { builder.register(it) }
          TypedFragmentScope<T>(builder).apply(block)
          builder.build(clazz.simpleName!!)
        }
  }
}

internal
operator fun <T : Data> KClass<T>.invoke(block: TypedFragmentScope<T>.() -> Unit): Fragment = reflectionFragment(this, block)



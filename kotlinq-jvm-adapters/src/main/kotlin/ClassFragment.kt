package org.kotlinq.jvm

import org.kotlinq.api.Fragment
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.PropertyInfo
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberProperties


class ClassFragment<out T : Data?> @PublishedApi internal constructor(
    private val clazz: KClass<*>,
    internal val init: (GraphQlResult) -> T,
    block: TypedFragmentScope<T>.() -> Unit = { },
    private val fragment: Fragment = reflectionFragment(clazz, block)
) : Fragment by fragment {

  override fun equals(other: Any?) = fragment == other
      || (other as? ClassFragment<*>)?.let { it.fragment == fragment } == true

  override fun hashCode(): Int =
      clazz.hashCode() * 31 + fragment.hashCode()

  companion object {

    inline fun <reified T : Data?> fragment(
        noinline init: (GraphQlResult) -> T,
        noinline block: TypedFragmentScope<T>.() -> Unit = { /* nothing */ }
    ) = ClassFragment(T::class, init, block)


    internal
    fun <T : Data?> fragment(
        clazz: KClass<*>,
        init: (GraphQlResult) -> T,
        block: TypedFragmentScope<T>.() -> Unit = { /* nothing */ }
    ) = ClassFragment(clazz, init, block)

  }
}


@Suppress("UNCHECKED_CAST")
internal fun <T : Data?> reflectionFragment(clazz: KClass<*>,
    block: TypedFragmentScope<T>.() -> Unit = { /* nothing */ }): Fragment =

    clazz.memberProperties.filter {
      it !in ProtoTypes.anyProperties
    }.mapNotNull {
      it with (it.returnType.scalarKind() ?: it.returnType.dataKind())
    }.mapNotNull { (prop, kind) ->
      PropertyInfo.propertyNamed(prop.name)
          .typeKind(kind)
          .build().let { info ->
            if (kind.isScalar)
              Kotlinq.adapterService.scalarAdapters.newAdapter(info)
            else prop.returnType.rootType.clazz?.let {
              if (it.isSubclassOf(Data::class)) Kotlinq.adapterService
                  .instanceProperty(info, reflectionFragment<Data?>(it))
              else null
            }
          }
    }.useWith(Kotlinq.newContextBuilder()) { builder ->
      this.forEach { builder.register(it) }
      TypedFragmentScope<T>(builder).apply(block)
      builder.build(clazz.simpleName!!)
    }

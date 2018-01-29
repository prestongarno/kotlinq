package providers

import org.kotlinq.Model
import org.kotlinq.dsl.ArgBuilder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class InitializingProviderImpl<Z : Model<*>>(
    val name: String,
    val init: () -> Z
) : DslBuilderProvider<Z> {

  private val args: ArgBuilder = ArgBuilder()

  override fun config(block: ArgBuilder.() -> Unit) = args.block()

  override operator fun provideDelegate(inst: Model<*>, property: KProperty<*>)
      : ReadOnlyProperty<Model<*>, Z> = TODO()
}

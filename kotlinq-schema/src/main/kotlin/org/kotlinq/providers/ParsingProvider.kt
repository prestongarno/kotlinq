package providers

import org.kotlinq.Model
import org.kotlinq.dsl.ArgBuilder
import kotlin.reflect.KProperty


class ParsingGraphQlPropertyProvider<Z>(
    val name: String,
    val init: (String) -> Z?
) : DslBuilderProvider<Z> {

  val args: ArgBuilder = ArgBuilder()

  override fun config(block: ArgBuilder.() -> Unit) = args.block()

  override fun provideDelegate(inst: Model<*>, property: KProperty<*>) = TODO()
}

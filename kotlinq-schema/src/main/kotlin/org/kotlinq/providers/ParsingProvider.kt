package org.kotlinq.providers

import org.kotlinq.Model
import org.kotlinq.api.Kotlinq
import org.kotlinq.delegates.bind
import org.kotlinq.dsl.ArgBuilder
import kotlin.reflect.KProperty


class ParsingGraphQlPropertyProvider<Z>(
    override val name: String,
    val init: (String) -> Z?
) : DslBuilderProvider<Z> {

  val args: ArgBuilder = ArgBuilder()

  override fun config(block: ArgBuilder.() -> Unit) = args.block()

  override fun provideDelegate(inst: Model<*>, property: KProperty<*>) =
      Kotlinq.adapterService.parser(name, property.returnType, init).bind<Z>(inst)
}

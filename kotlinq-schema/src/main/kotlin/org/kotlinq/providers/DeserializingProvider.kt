package org.kotlinq.providers

import org.kotlinq.Model
import org.kotlinq.dsl.ArgBuilder
import kotlin.reflect.KProperty

class DeserializingProviderImpl<Z>(
    override val name: String,
    val init: (java.io.InputStream) -> Z
) : DslBuilderProvider<Z> {

  private val args: ArgBuilder = ArgBuilder()

  override fun config(block: ArgBuilder.() -> Unit) = args.block()

  override operator fun provideDelegate(inst: Model<*>, property: KProperty<*>) = TODO()
}

package org.kotlinq.delegates

import org.kotlinq.Model
import org.kotlinq.api.GraphQlProperty
import org.kotlinq.adapters.graphQlProperty
import org.kotlinq.api.GraphQlProperty.Companion.graphQlProperty
import org.kotlinq.dsl.ArgBuilder
import org.kotlinq.dsl.ArgumentSpec
import org.kotlinq.dsl.DslBuilder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


interface GraphQlPropertyProvider<out Z> {
  operator fun provideDelegate(inst: Model<*>, property: KProperty<*>)
      : ReadOnlyProperty<Model<*>, Z>
}

fun <T : GraphQlProperty<*>> T.bindTo(model: Model<*>) = apply {
  model.bind(this)
}

internal
fun <Z> deserializingProvider(name: String, init: (java.io.InputStream) -> Z)
    : DslBuilderProvider<Z> =
    DeserializingGraphQlPropertyProviderImpl<Z>(name, init)

internal
fun <Z> parsingProvider(name: String, init: (String) -> Z?)
    : DslBuilderProvider<Z> = ParsingGraphQlPropertyProvider(name, init)

internal
fun <Z : Model<*>> initializingProvider(name: String, init: () -> Z)
    : DslBuilderProvider<Z> = GraphQlPropertyProviderImpl(name, init)

@Suppress("UNCHECKED_CAST")
internal
fun <U : List<*>> initializingProvider(
    name: String,
    init: () -> Model<*>,
    block: DslBuilder<Model<*>, ArgumentSpec>.() -> Unit
): GraphQlPropertyProvider<U> =
    GraphQlPropertyProviderImpl(name, init)
        .apply(block) as GraphQlPropertyProvider<U>

interface DslBuilderProvider<Z>
  : DslBuilder<Z, ArgBuilder>,
    GraphQlPropertyProvider<Z>

private
class DeserializingGraphQlPropertyProviderImpl<Z>(
    val name: String,
    val init: (java.io.InputStream) -> Z
) : DslBuilderProvider<Z> {

  private val args: ArgBuilder = ArgBuilder()

  override var default: Z? = null

  override fun config(block: ArgBuilder.() -> Unit) = args.block()

  override operator fun provideDelegate(inst: Model<*>, property: KProperty<*>) =
      graphQlProperty<Z>(name, property)
}

private
class ParsingGraphQlPropertyProvider<Z>(
    val name: String,
    val init: (String) -> Z?,
    override var default: Z? = null
) : DslBuilderProvider<Z> {

  val args: ArgBuilder = ArgBuilder()

  override fun config(block: ArgBuilder.() -> Unit) = args.block()

  override fun provideDelegate(inst: Model<*>, property: KProperty<*>) =
      graphQlProperty<Z>(property)
}

private
class GraphQlPropertyProviderImpl<Z : Model<*>>(
    val name: String,
    val init: () -> Z
) : DslBuilderProvider<Z> {

  private val args: ArgBuilder = ArgBuilder()

  override var default: Z? = null

  override fun config(block: ArgBuilder.() -> Unit) = args.block()

  override operator fun provideDelegate(inst: Model<*>, property: KProperty<*>)
      : ReadOnlyProperty<Model<*>, Z> = graphQlProperty<Z>(property).bindTo(inst)
}
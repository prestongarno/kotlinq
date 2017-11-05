package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.stubs.ListConfigType
import com.prestongarno.ktq.stubs.ListInitStub
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.stubs.TypeListStub
import com.prestongarno.ktq.hooks.ModelProvider
import kotlin.reflect.KProperty

internal class TypeListAdapter<I : QSchemaType, out P : QModel<I>, B : ArgBuilder>(
    qproperty: GraphQlProperty,
    private val builderInit: (ArgBuilder) -> B,
    val init: (() -> P)? = null,
    val config: (B.() -> Unit)? = null
) : PreDelegate(qproperty),
    ListInitStub<I>,
    TypeListStub<P, I>,
    ListConfigType<I, B>,
    ArgBuilder {

  override fun config(provider: B.() -> Unit): ListInitStub<I> =
      TypeListAdapter(qproperty, builderInit, this.init, provider)

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value) }

  override fun <U : QModel<I>> querying(of: () -> U): TypeListStub<U, I> =
      TypeListAdapter(qproperty, builderInit, of, this.config)

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<P>> {

    // This won't be null, the interface flow requires `querying(of: () -> P) to be called
    // in order to be exposed to an object which has the `operator function provideDelegate(...): QField<List<P>>`
    val initializer: () -> P = this.init!!

    return TypeListStubImpl(qproperty, initializer, apply {
      config?.invoke(builderInit(this))
    }.args.toMap()).also {
      inst.fields.add(it)
    }
  }

}

private data class TypeListStubImpl<P : QModel<*>>(
    override val qproperty: GraphQlProperty,
    val init: () -> P,
    override val args: Map<String, Any> = emptyMap()
) : QField<List<P>>,
    Adapter,
    ModelProvider {

  val results: MutableList<P> = mutableListOf()

  override val value: QModel<*> by lazy { init() }

  override fun toRawPayload(): String = qproperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else "" + value.toGraphql(false)

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<P> = results

  override fun accept(result: Any?): Boolean {
    return if (result is JsonArray<*>) {
      result.filterIsInstance<JsonObject>().filterNot { element ->
        init().let {
          this.results.add(it)
          it.accept(element)
        }
      }.isEmpty()
    } else false
  }

}
package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.FieldConfig
import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.TypeListStub
import com.prestongarno.ktq.internal.ModelProvider
import com.prestongarno.ktq.internal.nullPointer
import kotlin.reflect.KProperty

internal class TypeListAdapter<I : QSchemaType, P : QModel<I>, B : ArgBuilder>(
    property: GraphQlProperty,
    val builderInit: (ArgBuilder) -> B,
    val init: (() -> P) = nullPointer(),
    val config: (B.() -> Unit)? = null
) : FieldConfig(property),
    ListInitStub<I>,
    TypeListStub<P, I>,
    ListConfigType<I, B>,
    ArgBuilder {

  override fun config(provider: B.() -> Unit): ListInitStub<I> =
      TypeListAdapter(graphqlProperty, builderInit, this.init, provider)

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value) }

  override fun <U : QModel<I>> init(of: () -> U): TypeListStub<U, I> =
      TypeListAdapter(graphqlProperty, builderInit, of, this.config)

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<P>> =
      TypeListStubImpl(GraphQlProperty.from(property,
          this.graphqlProperty.graphqlType,
          this.graphqlProperty.isList,
          this.graphqlProperty.graphqlName),
          init,
          let {
            config?.invoke(builderInit(it))
            it.args.toMap()
          })
          .also { inst.fields.add(it) }


  override fun toAdapter(): Adapter = TypeListStubImpl(this.graphqlProperty, init, args)
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
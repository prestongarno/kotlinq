package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.FieldConfig
import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.QProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.TypeListArgBuilder
import com.prestongarno.ktq.TypeListStub
import com.prestongarno.ktq.internal.ModelProvider
import com.prestongarno.ktq.internal.nullPointer
import kotlin.reflect.KProperty

internal class TypeListAdapter<I : QSchemaType, P : QModel<I>, B : TypeListArgBuilder>(
    property: QProperty,
    val builderInit: (TypeListArgBuilder) -> B,
    val init: (() -> P) = nullPointer(),
    val config: (B.() -> Unit)? = null
) : FieldConfig(property),
    ListInitStub<I>,
    TypeListStub<P, I>,
    ListConfigType<I, B>,
    TypeListArgBuilder {

  override fun config(provider: B.() -> Unit): ListInitStub<I> =
      TypeListAdapter(graphqlProperty, builderInit, this.init, provider)

  override fun <U : QModel<T>, T : QSchemaType> build(init: () -> U): TypeListStub<U, T> =
      TypeListAdapter(graphqlProperty, builderInit, init, this.config)

  override fun addArg(name: String, value: Any): TypeListArgBuilder = apply { args.put(name, value) }

  override fun <U : QModel<I>> init(of: () -> U): TypeListStub<U, I> =
      TypeListAdapter(graphqlProperty, builderInit, of, this.config)

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Adapter<List<P>> =
      TypeListStubImpl(QProperty.from(property,
          this.graphqlProperty.graphqlType,
          this.graphqlProperty.isList,
          this.graphqlProperty.graphqlName),
          init,
          let {
            config?.invoke(builderInit(it))
            it.args.toMap()
          })
          .also { inst.fields.add(it) }


  override fun toAdapter(): Adapter<*> = TypeListStubImpl(this.graphqlProperty, init, args)
}

private data class TypeListStubImpl<P : QModel<*>>(
    override val graphqlProperty: QProperty,
    val init: () -> P,
    override val args: Map<String, Any> = emptyMap()
) : Adapter<List<P>>,
    ModelProvider {

  val results: MutableList<P> = mutableListOf()

  override val value: QModel<*> by lazy { init() }

  override fun toRawPayload(): String = graphqlProperty.graphqlName +
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
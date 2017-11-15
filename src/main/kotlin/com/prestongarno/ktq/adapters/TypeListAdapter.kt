package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.hooks.ModelProvider
import com.prestongarno.ktq.internal.CollectionDelegate
import com.prestongarno.ktq.stubs.TypeListStub
import kotlin.reflect.KProperty

internal class TypeListAdapter<out P : QModel<I>, I : QType, out A : ArgBuilder>(
    qproperty: GraphQlProperty,
    val init: () -> P,
    val argBuilder: A? = null
) : PreDelegate(qproperty),
    TypeListStub<P, I, A> {

  override fun config(scope: A.() -> Unit) {
    TODO("Not Implemented")
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<P>> =
      TypeListStubImpl(qproperty, init, argBuilder.toMap()).bind(inst)
}

@CollectionDelegate(QModel::class)
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

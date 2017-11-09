package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.hooks.TypeConfig
import com.prestongarno.ktq.TypeStub
import com.prestongarno.ktq.hooks.InitStub
import com.prestongarno.ktq.hooks.ModelProvider
import com.prestongarno.ktq.internal.ValueDelegate
import kotlin.reflect.KProperty

/**
 * This class represents a createStub for a non-leaf createTypeStub (aka an object) fragment a graph */
internal class TypeStubAdapter<I : QType, P : QModel<I>, B : ArgBuilder>(
    qproperty: GraphQlProperty,
    private val builderInit: (ArgBuilder) -> B,
    val init: (() -> P)? = null,
    val config: (B.() -> Unit)? = null
) : PreDelegate(qproperty),
    TypeStub<P, I>,
    InitStub<I>,
    TypeConfig<I, B> {

  override fun config(provider: B.() -> Unit): InitStub<I> =
      TypeStubAdapter(qproperty,builderInit, this.init, provider)

  /**
   * For the possible possibly nullable initializer ->
   * see [com.prestongarno.ktq.adapters.TypeListAdapter.provideDelegate]
   */
  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<P> {
    val initializer: () -> P = this.init!!
    return TypeStubImpl(qproperty, init, apply {
      config?.invoke(builderInit(this)) }.args.toMap()
    ).also { inst.fields.add(it) }
  }

  override fun <U : QModel<I>> querying(init: () -> U): TypeStub<U, I> =
      TypeStubAdapter(qproperty, builderInit, init, config)

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value) }

}

@ValueDelegate(QModel::class)
private data class TypeStubImpl<I : QType, out P : QModel<I>>(
    override val qproperty: GraphQlProperty,
    val init: () -> P,
    override val args: Map<String, Any> = emptyMap()
) : QField<P>,
    Adapter,
    ModelProvider {

  override val value by lazy(init)

  override fun getValue(inst: QModel<*>, property: KProperty<*>): P = value

  override fun accept(result: Any?): Boolean {
    value.resolved = true
    return result is JsonObject
        && value.fields.filterNot { f ->
      f.accept(result[f.qproperty.graphqlName])
    }.isEmpty() && value.resolved
  }

  override fun toRawPayload(): String = qproperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else "" + value.toGraphql(false)

}
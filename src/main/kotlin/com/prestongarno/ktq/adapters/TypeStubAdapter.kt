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
import com.prestongarno.ktq.toArgumentMap
import kotlin.reflect.KProperty

internal class TypeConfigStub<T : QType, A : ArgBuilder>(
    private val qproperty: GraphQlProperty
) : TypeConfig<T, A> {

  override fun invoke(arguments: A, scope: (A.() -> Unit)?): InitStub<T, A> {
    return TypeStubAdapter(qproperty, arguments, scope)
  }
}

/**
 * This class represents a createStub for a non-leaf createTypeStub (aka an object) fragment a graph */
internal class TypeStubAdapter<I : QType, P : QModel<I>, A : ArgBuilder>(
    qproperty: GraphQlProperty,
    val argBuilder: A? = null,
    val config: (A.() -> Unit)? = null,
    val init: (() -> P)? = null
) : PreDelegate(qproperty),
    InitStub<I, A>,
    TypeStub<P, I> {

  override fun invoke(arguments: A, scope: (A.() -> Unit)?): InitStub<I, A> =
      TypeStubAdapter(qproperty, arguments, scope ?: config, init)

  /**
   * For the possible possibly nullable initializer ->
   * see [com.prestongarno.ktq.adapters.TypeListAdapter.provideDelegate]
   */
  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<P> =
      TypeStubImpl(qproperty, init!!, toArgumentMap(argBuilder, config)).bind(inst)

  override fun <U : QModel<I>> querying(init: () -> U): TypeStub<U, I> =
      TypeStubAdapter(qproperty, argBuilder, config, init)

}

@ValueDelegate(QModel::class)
private data class TypeStubImpl<out I : QType, out P : QModel<I>>(
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
      f.value.accept(result[f.value.qproperty.graphqlName])
    }.isEmpty() && value.resolved
  }

  override fun toRawPayload(): String = qproperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else "" + value.toGraphql(false)

}
package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.stubs.CustomScalarListConfigStub
import com.prestongarno.ktq.stubs.CustomScalarListInitStub
import com.prestongarno.ktq.stubs.CustomScalarListStub
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.custom.InputStreamScalarListMapper
import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.adapters.custom.StringScalarListMapper
import com.prestongarno.ktq.internal.CollectionDelegate
import kotlin.reflect.KProperty

internal class CustomScalarListAdapter<E : CustomScalar, P : QScalarListMapper<Q>, out Q, B : ArgBuilder>(
    property: GraphQlProperty,
    val adapter: P? = null,
    val argBuilder: B? = null,
    private val default: Q? = null,
    val config: (B.() -> Unit)? = null
) : PreDelegate(property),
    CustomScalarListConfigStub<E, B>,
    CustomScalarListInitStub<E>,
    CustomScalarListStub<P, Q> {

  override fun config(provider: B.() -> Unit): CustomScalarListInitStub<E> =
      CustomScalarListAdapter(qproperty, adapter, argBuilder, default, provider)

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<Q>> =
      CustomScalarListStubImpl(GraphQlProperty.from(property,
          this.qproperty.graphqlType,
          this.qproperty.isList,
          this.qproperty.graphqlName),
          argBuilder.toMap(),
          adapter!!
      ).bind(inst)

  @Suppress("UNCHECKED_CAST") override fun <U : QScalarListMapper<A>, A> querying(of: U): CustomScalarListStub<U, A> =
      CustomScalarListAdapter<E, U, A, B>(qproperty, of, argBuilder, default as A?, config)

  //@Suppress("UNCHECKED_CAST") override fun <U : QScalarListMapper<T>, T> build(init: U): CustomScalarListStub<U, T> = CustomScalarListAdapter<E, U, T, B>(qproperty, init, default as T?, scope)

}

@CollectionDelegate(Any::class)
private data class CustomScalarListStubImpl<out Q>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any> = emptyMap(),
    val adapter: QScalarListMapper<Q>
) : QField<List<Q>>,
    Adapter {

  override fun toRawPayload(): String = qproperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else ""

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<Q> {
    return adapter.value
  }

  override fun accept(result: Any?): Boolean {
    val values = (result as? List<*> ?: listOf(result)).filterNotNull()
    when (adapter) {
      is InputStreamScalarListMapper<*> -> {
        (adapter as InputStreamScalarListMapper<*>).rawValue =
            values.map { "$it".byteInputStream() }
      }
      is StringScalarListMapper<*> -> {
        (adapter as StringScalarListMapper<*>).rawValue =
            values.map { "$it" }
      }
    }
    return true
  }
}

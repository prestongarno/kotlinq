package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.custom.InputStreamScalarMapper
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import com.prestongarno.ktq.internal.CollectionDelegate
import com.prestongarno.ktq.stubs.CustomScalarStub
import kotlin.reflect.KProperty

fun <E : CustomScalar, P : QScalarMapper<Q>, Q, A : ArgBuilder> newScalarDelegate(
    qproperty: GraphQlProperty,
    mapper: P,
    arguments: A?
): CustomScalarStub<E, Q, A> = CustomScalarAdapter<E, P, Q, A>(qproperty, mapper, arguments)

private class CustomScalarAdapter<E : CustomScalar, out P : QScalarMapper<Q>, Q, out B : ArgBuilder>(
    qproperty: GraphQlProperty,
    val mapper: P,
    val arguments: B? = null
) : PreDelegate(qproperty),
    CustomScalarStub<E, Q, B> {

  override var default: Q? = null

  override fun config(scope: B.() -> Unit) {
    arguments?.scope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<Q> =
      CustomScalarFieldImpl(qproperty, arguments.toMap(), mapper, default).bind(inst)

}

@CollectionDelegate(Any::class)
private data class CustomScalarFieldImpl<out Q>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any> = emptyMap(),
    val adapter: QScalarMapper<Q>,
    val default: Q?
) : Adapter,
    QField<Q> {

  override fun getValue(inst: QModel<*>, property: KProperty<*>): Q = adapter.value ?: default!!

  override fun accept(result: Any?): Boolean {
    when (adapter) {
      is InputStreamScalarMapper<*> -> adapter.rawValue = "${result ?: ""}".byteInputStream()
      is StringScalarMapper<*> -> adapter.rawValue = "${result ?: ""}"
      else -> throw IllegalArgumentException("No such adapter supported")
    }
    return true
  }

  override fun toRawPayload(): String = qproperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else ""
}

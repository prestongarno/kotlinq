package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.stubs.CustomScalarListStub
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.custom.InputStreamScalarListMapper
import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.adapters.custom.StringScalarListMapper
import com.prestongarno.ktq.internal.CollectionDelegate
import kotlin.reflect.KProperty

internal fun <E : CustomScalar, P : QScalarListMapper<Q>, Q, A : ArgBuilder> newCustomScalarListField(
    qproperty: GraphQlProperty,
    mapper: P,
    arguments: A?
): CustomScalarListStub<E, Q, A> = CustomScalarListAdapter(qproperty, mapper, arguments)

private class CustomScalarListAdapter<E : CustomScalar, out P : QScalarListMapper<Q>, Q, out A : ArgBuilder>(
    qproperty: GraphQlProperty,
    val mapper: P,
    val arguments: A?
) : PreDelegate(qproperty),
    CustomScalarListStub<E, Q, A> {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<Q>> =
      CustomScalarListStubImpl(qproperty, mapper, arguments.toMap())

  override fun config(scope: A.() -> Unit) { arguments?.scope() }

}

@CollectionDelegate(Any::class)
private data class CustomScalarListStubImpl<out Q>(
    override val qproperty: GraphQlProperty,
    val adapter: QScalarListMapper<Q>,
    override val args: Map<String, Any>
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

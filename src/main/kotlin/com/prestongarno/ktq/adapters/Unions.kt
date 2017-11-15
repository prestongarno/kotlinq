package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.stubs.UnionStub
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.hooks.FragmentContext
import com.prestongarno.ktq.internal.ValueDelegate
import kotlin.reflect.KProperty

fun newUnionField(
    qproperty: GraphQlProperty, fragments: Set<Fragment>, args: Map<String, Any>?
) : QField<QModel<*>?> = UnionStubImpl(qproperty, fragments, args ?: emptyMap())

@PublishedApi internal class UnionAdapterImpl<I : QUnionType, out A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    private val fragments: Set<Fragment>,
    val arguments: A? = null
) : UnionStub<I, A> {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<QModel<*>?> =
      newUnionField(qproperty, fragments, arguments.toMap()).apply {
        (this as? Adapter)?.bind(inst)
      }

  override fun config(scope: A.() -> Unit) {
    arguments?.apply(scope)
  }

}

@ValueDelegate(QModel::class) private class UnionStubImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>,
    override val args: Map<String, Any> = emptyMap()
) : Adapter,
    QField<QModel<*>?>,
    FragmentContext {

  var value: QModel<QType>? = null


  override fun accept(result: Any?): Boolean {
    return if (result is JsonObject) {
      value = result["__typename"]?.let { resultType ->
        fragments.find {
          it.model.graphqlType == resultType
        }?.initializer?.invoke()
      }
      return value?.accept(result) == true
    } else false
  }

  override fun toRawPayload(): String =
      fragments.joinToString(prefix = "{__typename,", postfix = "}") {
        it.model.run {
          "... on " + graphqlType + toGraphql(false)
        }
      }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): QModel<QType>? = value
}


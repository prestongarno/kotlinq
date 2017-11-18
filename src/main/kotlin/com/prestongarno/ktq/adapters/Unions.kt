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

fun <T : QUnionType, A : ArgBuilder> newUnionField(
    qproperty: GraphQlProperty,
    unionObject: T,
    arguments: A?
): UnionStub<T, A> = UnionAdapterImpl(qproperty, unionObject, arguments)

private class UnionAdapterImpl<T : QUnionType, out A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    val unionObject: T,
    val arguments: A? = null
) : UnionStub<T, A> {

  private var mutableFragments: Set<Fragment>? = null

  override fun fragment(scope: T.() -> Unit) = unionObject.queue(unionObject, scope) {
    mutableFragments = reset()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<QModel<*>?> =
      UnionStubImpl(qproperty, mutableFragments ?: emptySet(), arguments.toMap()).bind(inst)

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

  override fun toRawPayload(): String = qproperty.graphqlName +
      (if (args.isEmpty()) "" else args.entries.joinToString(
          prefix = "(", postfix = ")", separator = ","
      ) { (key, value) -> "$key: ${formatAs(value)}" }) +
      fragments.joinToString(prefix = "{__typename,", postfix = "}") {
        it.model.run {
          "... on " + graphqlType + toGraphql(false)
        }
      }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): QModel<QType>? = value
}


package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InterfaceStub
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.hooks.FragmentContext
import com.prestongarno.ktq.internal.ValueDelegate
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

/**
 * Base type of a R.H.S. delegate provider
 */
internal class InterfaceAdapterImpl<I, out A : ArgBuilder>(
    qproperty: GraphQlProperty,
    val arguments: A? = null
) : PreDelegate(qproperty),
    InterfaceStub<I, A>

    where I : QType,
          I : QInterface {


  private val fragments = mutableSetOf<Fragment>()

  override fun <T : I> on(initializer: () -> QModel<T>) {
    fragments += Fragment(initializer)
  }

  override fun config(scope: A.() -> Unit) {
    arguments?.scope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<QModel<I>?> =
      InterfaceDelegateImpl<I>(qproperty, arguments?.arguments?.invoke()?: emptyMap(), fragments.toSet())
          .bind(inst)
}

@ValueDelegate(QModel::class)
private class InterfaceDelegateImpl<I : QType>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>,
    override val fragments: Set<Fragment>
) : Adapter,
    FragmentContext,
    QField<QModel<I>?> {

  var value: QModel<I>? = null

  override fun accept(result: Any?): Boolean {
    if (result !is JsonObject) return false
    value = fragments.find { it.model.graphqlType == result["__typename"] }
        ?.initializer?.invoke()?.let {
      it.accept(result)
      @Suppress("UNCHECKED_CAST")
      it as? QModel<I>
    }
    return value?.isResolved() == true
  }

  override fun toRawPayload(): String =
      qproperty.graphqlName + (if (args.isEmpty()) "" else args.entries.joinToString(
          prefix = "(", postfix = ")") { (key, value) ->
        "$key: " + formatAs(value)
      }) + fragments.joinToString(
          prefix = "{__typename, ",
          postfix = "}",
          transform = Fragment::toString)

  override operator fun getValue(
      inst: QModel<*>,
      property: KProperty<*>
  ): QModel<I>? = value

}

package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InterfaceStub
import com.prestongarno.ktq.QInterfaceType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.hooks.FragmentContext
import com.prestongarno.ktq.internal.ValueDelegate
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.stubs.InterfaceFragment
import kotlin.reflect.KProperty

/**
 * Base type of a R.H.S. delegate provider
 */
internal class InterfaceFragmentAdapter<out T : QInterfaceType, A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    val arginit: (ArgBuilder) -> A,
    val config: (A.() -> Unit)? = null
) : InterfaceFragment<T>,
    InterfaceStub<T> {

  override fun fragment(on: T.() -> Unit): InterfaceStub<T> {
    TODO("not implemented")
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<QModel<T>?> {
    TODO("preston")
  }

}

@ValueDelegate(QModel::class)
private class InterfaceStub<T : QInterfaceType>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>,
    override val fragments: Set<Fragment>
) : QField<QModel<T>>,
    Adapter,
    FragmentContext {

  lateinit var value: QModel<T>

  override fun accept(result: Any?): Boolean {
    TODO("not implemented")
  }

  override fun toRawPayload(): String =
      fragments.joinToString(prefix = "{__typename,", postfix = "}") {
        it.model.run {
          "... on " + graphqlType + toGraphql(false)
        }
      }

  override operator fun getValue(
      inst: QModel<*>,
      property: KProperty<*>
  ): QModel<T> = value
}

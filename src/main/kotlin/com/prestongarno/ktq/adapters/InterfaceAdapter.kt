package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InterfaceStub
import com.prestongarno.ktq.QInterfaceType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.internal.ValueDelegate
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.stubs.FragmentContext
import com.prestongarno.ktq.stubs.FragmentScope
import com.prestongarno.ktq.stubs.InterfaceFragment
import kotlin.reflect.KProperty

/**
 * Base type of a R.H.S. delegate provider
 */
internal class InterfaceFragmentAdapter<I : QInterfaceType, out A : ArgBuilder>(
    qproperty: GraphQlProperty,
    private val arginit: (ArgBuilder) -> A
    // TODO -> Separate required arguments as data class here from inner optional config block
) : PreDelegate(qproperty),
    InterfaceFragment<I, A>,
    FragmentScope<I, A>,
    InterfaceStub<I>,
    ArgBuilder {

  private val fragments = mutableSetOf<Fragment>()

  override fun <T : I> on(initializer: () -> QModel<T>) {
    fragments += Fragment(initializer)
  }

  override fun config(scope: A.() -> Unit) {
    arginit(this).scope()
  }

  override fun invoke(context: FragmentScope<I, A>.() -> Unit): InterfaceStub<I> =
      InterfaceFragmentAdapter<I, A>(qproperty, arginit).apply(context)

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args[name] = value }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<QModel<I>?> =
      InterfaceDelegateImpl(qproperty, args, fragments.toSet())

}

@ValueDelegate(QModel::class)
private class InterfaceDelegateImpl<T : QInterfaceType>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>,
    override val fragments: Set<Fragment>
) : Adapter,
    FragmentContext<T> {

  var value: QModel<T>? = null

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
  ): QModel<T>? = value
}

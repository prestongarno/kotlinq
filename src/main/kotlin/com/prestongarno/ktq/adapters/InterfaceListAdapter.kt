package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.CollectionStub
import com.prestongarno.ktq.QInterfaceType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.stubs.FragmentCollectionContext
import com.prestongarno.ktq.stubs.FragmentScope
import kotlin.reflect.KProperty

internal class InterfaceFragmentListAdapter<I : QInterfaceType, A : ArgBuilder>(
    qproperty: GraphQlProperty,
    private val arginit: (ArgBuilder) -> A
) : PreDelegate(qproperty),
    CollectionStub<I>,
    FragmentScope<I, A> {

  private val fragments = mutableSetOf<Fragment>()

  override fun <T : I> on(initializer: () -> QModel<T>) {
    fragments += Fragment(initializer)
  }

  override fun config(scope: A.() -> Unit) {
    arginit(this).scope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<QModel<I>>> {
    return CollectionDelegateImpl(qproperty, args.toMap(), fragments.toSet())
  }

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args[name] = value }
}

private class CollectionDelegateImpl<I : QInterfaceType>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>,
    override val fragments: Set<Fragment>
) : Adapter,
    FragmentCollectionContext<I> {

  private val value = mutableListOf<QModel<I>>()

  override fun accept(result: Any?): Boolean {
    TODO("not implemented")
  }

  override fun toRawPayload(): String {
    return qproperty.graphqlName +
        (if (args.isEmpty())
          ""
        else
          args.entries.joinToString(prefix = "(", postfix = ")") { (key, value) ->
            "\\\"$key\\\": " + formatAs(value)
          }) +
        fragments.joinToString(prefix = "{__typename,", postfix = "}") {
          "... on " + it.model.graphqlType + it.model.toGraphql(false)
        }
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<QModel<I>> = value

}

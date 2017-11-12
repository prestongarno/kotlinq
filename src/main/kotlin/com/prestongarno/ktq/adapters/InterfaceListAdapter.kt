package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.AbstractCollectionStub
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.stubs.CollectionConfigFragment
import com.prestongarno.ktq.stubs.CollectionFragment
import com.prestongarno.ktq.stubs.FragmentContext
import com.prestongarno.ktq.stubs.FragmentScope
import com.prestongarno.ktq.stubs.TypeListStub
import com.prestongarno.ktq.toArgumentMap
import kotlin.reflect.KProperty

internal data class InterfaceListStub<I>(
    private val qproperty: GraphQlProperty
) : CollectionFragment<I>
    where I : QType,
          I : QInterface {
  override fun invoke(context: FragmentScope<I, ArgBuilder>.() -> Unit): AbstractCollectionStub<I> =
      InterfaceFragmentListAdapter<I, ArgBuilder>(qproperty).apply(context)
}

internal data class InterfaceListConfigStub<I, A : ArgBuilder>(
    private val qproperty: GraphQlProperty
) : CollectionConfigFragment<I, A>
    where I : QType,
          I : QInterface {

  override fun invoke(
      arguments: A,
      context: FragmentScope<I, A>.() -> Unit
  ): AbstractCollectionStub<I> =
      InterfaceFragmentListAdapter<I, A>(qproperty, arguments).apply(context)

}

internal class InterfaceFragmentListAdapter<I, out A : ArgBuilder>(
    qproperty: GraphQlProperty,
    val argBuilder: A? = null
) : PreDelegate(qproperty),
    TypeListStub<QModel<I>, I>,
    AbstractCollectionStub<I>,
    FragmentScope<I, A>

    where I : QType,
          I : QInterface {

  private val fragments = mutableSetOf<Fragment>()

  private var config: (A.() -> Unit)? = null

  override fun <T : I> on(initializer: () -> QModel<T>) {
    fragments += Fragment(initializer)
  }

  override fun config(scope: A.() -> Unit) {
    this.config = config
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<QModel<I>>> =
      CollectionDelegateImpl<I>(qproperty,
          toArgumentMap(argBuilder, config),
          fragments.toSet()
      ).apply {
        inst.fields.add(this)
      }
}

private class CollectionDelegateImpl<out I : QType>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>,
    override val fragments: Set<Fragment>
) : Adapter,
    QField<List<QModel<I>>>,
    FragmentContext {

  private var value = emptyList<QModel<I>>()

  override fun accept(result: Any?): Boolean {
    return if (result is Collection<*>) {
      result.filterIsInstance<JsonObject>()
          .mapNotNull {
            it["__typename"]?.let { resultType ->
              fragments.find {
                it.model.graphqlType == resultType
              }
            }?.to(it)
          }.let {
        value = it.mapNotNull { (gen, json) ->
          @Suppress("UNCHECKED_CAST")
          gen.initializer().apply {
            accept(json)
          } as? QModel<I>
        }
      }
      return true
    } else false
  }

  override fun toRawPayload(): String {
    return qproperty.graphqlName + (if (args.isEmpty()) "" else args.entries.joinToString(
        prefix = "(", postfix = ")") { (key, value) ->
      "\\\"$key\\\": " + formatAs(value)
    }) +
        fragments.joinToString(prefix = "{__typename,", postfix = "}") {
          "... on " + it.model.graphqlType + it.model.toGraphql(false)
        }
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<QModel<I>> = value

}

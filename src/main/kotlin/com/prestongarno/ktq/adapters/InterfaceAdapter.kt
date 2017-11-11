package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InterfaceStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
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
internal class InterfaceFragmentAdapter<I : QType, out A : ArgBuilder>(
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

  override fun provideDelegate(
      inst: QModel<*>,
      property: KProperty<*>
  ): QField<QModel<I>?> =
      InterfaceDelegateImpl<I>(qproperty, args, fragments.toSet()).apply { inst.fields.add(this) }

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args[name] = value }
}

@ValueDelegate(QModel::class)
private class InterfaceDelegateImpl<I : QType>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>,
    override val fragments: Set<Fragment>
) : Adapter,
    FragmentContext<I> {

  var value: QModel<I>? = null

  override fun accept(result: Any?): Boolean {
    println(result!!::class)
    if (result !is JsonObject) return false
    value = fragments.find { it.model.graphqlType == result[it.model.graphqlType] }
        ?.initializer?.invoke()?.run {
      accept(result)
      result as? QModel<I>
    }
    return result is QModel<*>
  }

  override fun toRawPayload(): String {
    return StringBuilder(fragments.size * 10).apply {
      this add qproperty.graphqlName add
          (if (args.isEmpty())
            ""
          else
            args.entries.joinToString(prefix = "(", postfix = ")") { (key, value) ->
              "\\\"$key\\\": " + formatAs(value)
            }) add
          fragments.joinToString(prefix = "{__typename,", postfix = "}") {
            "... on " + it.model.graphqlType + it.model.toGraphql(false)
          }
    }.toString()
  }

  override operator fun getValue(
      inst: QModel<*>,
      property: KProperty<*>
  ): QModel<I>? = value

  private infix fun StringBuilder.add(value: String) = this.append(value)

}

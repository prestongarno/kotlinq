package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InterfaceListStub
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.hooks.FragmentContext
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

/**
 * Factory method for an Interface List GraphQL field delegate provider
 * @param I the type of interface
 * @param A the type of [ArgBuilder] */
fun <I, A> newInterfaceListStub(
    qproperty: GraphQlProperty, argBuilder: A?
): InterfaceListStub<I, A>
    where I : QType, I : QInterface, A : ArgBuilder =
    InterfaceListStubImpl(qproperty, argBuilder)

private data class InterfaceListStubImpl<I, out A>(
    private val qproperty: GraphQlProperty,
    private val argBuilder: A?
) : InterfaceListStub<I, A>
    where I : QType,
          I : QInterface,
          A : ArgBuilder {

  private val fragments = mutableSetOf<Fragment>()

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<QModel<I>>> =
      InterfaceListField<I>(qproperty, fragments, argBuilder.toMap()).bind(inst)

  override fun <T : I> on(initializer: () -> QModel<T>) {
    fragments += Fragment(initializer)
  }

  override fun config(argumentScope: A.() -> Unit) {
    argBuilder?.argumentScope()
  }
}

private data class InterfaceListField<out I>(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>,
    override val args: Map<String, Any>
) : QField<List<QModel<I>>>,
    FragmentContext,
    Adapter
    where I : QType,
          I : QInterface {

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
      "$key: " + formatAs(value)
    }) +
        fragments.joinToString(prefix = "{__typename,", postfix = "}") {
          "... on " + it.model.graphqlType + it.model.toGraphql(false)
        }
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<QModel<I>> = value
}

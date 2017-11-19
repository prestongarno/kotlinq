package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.stubs.UnionListStub
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.hooks.FragmentContext
import com.prestongarno.ktq.internal.CollectionDelegate
import kotlin.reflect.KProperty

internal fun <T : QUnionType, A : ArgBuilder> newUnionListStub(
    qproperty: GraphQlProperty,
    objectModel: T,
    arguments: A?
): UnionListStub<T, A> =
    UnionListAdapter(qproperty, objectModel, arguments)

private class UnionListAdapter<I : QUnionType, out A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    val objectModel: I,
    val arguments: A?
) : UnionListStub<I, A> {

  private var fragments: Set<Fragment>? = null

  override fun config(scope: A.() -> Unit) {
    arguments?.scope()
  }

  override fun fragment(scope: I.() -> Unit) = objectModel.queue(objectModel, scope) {
    fragments = reset()
  }

  override fun provideDelegate(
      inst: QModel<*>,
      property: KProperty<*>
  ): QField<List<QModel<*>>> =
      UnionListStubImpl(
          qproperty,
          fragments ?: emptySet(),
          arguments.toMap()
      ).bind(inst)

}


@CollectionDelegate(QModel::class)
private class UnionListStubImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>,
    override val args: Map<String, Any>
) : Adapter,
    QField<List<QModel<*>>>,
    FragmentContext {

  private var value: List<QModel<QType>> = mutableListOf()

  override fun accept(result: Any?): Boolean {
    return if (result is Collection<*>) {
      result.filterIsInstance<JsonObject>()
          .mapNotNull {
            it["__typename"]?.let { resultType ->
              fragments.find {
                it.model.graphqlType == resultType && (it.model as? QModel<*>) != null
              }
            }?.to(it)
          }.let {
        value = it.mapNotNull { (gen, json) ->
          gen.initializer().apply {
            accept(json)
          } as? QModel<QType>
        }
      }
      return true
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

  override fun getValue(inst: QModel<*>, property: KProperty<*>) = value

}


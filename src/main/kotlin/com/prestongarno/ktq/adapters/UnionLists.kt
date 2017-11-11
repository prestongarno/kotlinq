package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.stubs.UnionListInitStub
import com.prestongarno.ktq.stubs.UnionListStub
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.internal.CollectionDelegate
import com.prestongarno.ktq.stubs.FragmentCollectionContext
import kotlin.reflect.KProperty

internal sealed class UnionListConfigAdapter<I : QUnionType>(
    val qproperty: GraphQlProperty,
    objectModel: I,
    val dispatcher: (I.() -> Unit)? = null
) : QModel<I>(objectModel),
    UnionListInitStub<I>,
    UnionListStub,
    QUnionType {

  private val fragments = mutableListOf<Fragment>()

  /**
   * Recurse to the base model of the graph */
  override val queue: com.prestongarno.ktq.properties.FragmentProvider get() = model.queue

  val args: Map<String, Any> by lazy { mapOf<String, Any>() }

  internal var value: List<QModel<*>> = mutableListOf()

  override fun fragment(what: I.() -> Unit): UnionListStub = MutableUnionListAdapter(qproperty, model, what)

  override fun on(init: () -> QModel<QType>) {
    queue.addFragment(Fragment(init))
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<QModel<*>>> =
      queue(model, dispatcher?: { /* nothing */}, {
        val stub = UnionListStubImpl(qproperty, queue.reset().toSet())
        inst.fields.add(stub)
        stub
      })

  companion object {

    /**
     * TODO(preston) add generic argument for the type of ArgBuilder on a field like this
     * also TODO => get rid of the property parameter, this is only for creating type defs*/
    fun <I : QUnionType> create(property: GraphQlProperty, objectModel: I)
        : UnionListConfigAdapter<I> = MutableUnionListAdapter(property, objectModel)
  }
}

/**
 * any configuration is done here on a delegate */
private class MutableUnionListAdapter<I : QUnionType>(
    qproperty: GraphQlProperty,
    objectModel: I,
    dispatcher: (I.() -> Unit)? = null
) : UnionListConfigAdapter<I>(qproperty, objectModel, dispatcher),
    DelegateProvider<List<QModel<*>>>

@CollectionDelegate(QModel::class)
private class UnionListStubImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>
) : Adapter,
    FragmentCollectionContext<QType> {

  private var value: List<QModel<QType>> = mutableListOf()

  override val args = emptyMap<String, Any>()

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

  override fun toRawPayload(): String = fragments.joinToString(
      prefix = "{__typename,", postfix = "}") {
    it.model.run { "... on $graphqlType${toGraphql(false)}" }
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>) = value

}


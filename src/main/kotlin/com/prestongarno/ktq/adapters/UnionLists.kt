package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.hooks.DelegateProvider
import com.prestongarno.ktq.stubs.UnionListInitStub
import com.prestongarno.ktq.stubs.UnionListStub
import com.prestongarno.ktq.hooks.Fragment
import com.prestongarno.ktq.hooks.FragmentContext
import com.prestongarno.ktq.internal.CollectionDelegate
import kotlin.reflect.KProperty

internal sealed class UnionListConfigAdapter<out I : QSchemaUnion>(
    val qproperty: GraphQlProperty,
    objectModel: I
) : QModel<I>(objectModel),
    UnionListInitStub<I>,
    UnionListStub,
    QSchemaUnion {

  private val fragments = mutableListOf<Fragment>()

  /**
   * Recurse to the base model of the graph */
  override val queue: com.prestongarno.ktq.properties.FragmentProvider get() = model.queue

  private var dispatcher: (I.() -> Unit)? = null

  val args: Map<String, Any> by lazy { mapOf<String, Any>() }

  internal var value: List<QModel<*>> = mutableListOf()

  override fun fragment(what: I.() -> Unit): UnionListStub = apply { dispatcher = what }

  override fun on(init: () -> QModel<*>) {
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
    fun <I : QSchemaUnion> create(property: GraphQlProperty, objectModel: I)
        : UnionListConfigAdapter<I> = MutableUnionListAdapter(property, objectModel)
  }
}

/**
 * any configuration is done here on a delegate */
private class MutableUnionListAdapter<out I : QSchemaUnion>(
    qproperty: GraphQlProperty,
    objectModel: I
) : UnionListConfigAdapter<I>(qproperty, objectModel),
    DelegateProvider<List<QModel<*>>>

@CollectionDelegate(QModel::class)
private class UnionListStubImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>
) : Adapter,
    QField<List<QModel<*>>>,
    FragmentContext {

  private var value: List<QModel<*>> = mutableListOf()

  override val args = emptyMap<String, Any>()

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
        value = it.map { (gen, json) ->
          gen.initializer().apply {
            accept(json)
          }
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


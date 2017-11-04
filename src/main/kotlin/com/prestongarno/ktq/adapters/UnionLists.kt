package com.prestongarno.ktq.adapters

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.properties.DispatchQueue
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.stubs.UnionListInitStub
import com.prestongarno.ktq.stubs.UnionListStub
import com.prestongarno.ktq.hooks.FragmentGenerator
import com.prestongarno.ktq.hooks.FragmentProvider
import kotlin.reflect.KProperty

internal sealed class UnionListConfigAdapter<out I : QSchemaUnion>(
    val qproperty: GraphQlProperty,
    objectModel: I
) : QModel<I>(objectModel),
    UnionListInitStub<I>,
    UnionListStub,
    QSchemaUnion {

  open val fragments = mutableSetOf<FragmentGenerator>()

  /**
   * Recurse to the base model of the graph */
  override val queue by lazy { model.queue }

  private var dispatcher: (I.() -> Unit)? = null

  val args: Map<String, Any> by lazy { mapOf<String, Any>() }

  internal var value: List<QModel<*>> = mutableListOf()

  override fun fragment(what: I.() -> Unit): UnionListStub = apply { dispatcher = what }

  override fun on(init: () -> QModel<*>) {
    fragments += FragmentGenerator(init)
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<QModel<*>>> {
    synchronized(queue) {
      queue.put(this)
      dispatcher?.invoke(model)
      queue.pop()
    }
    UnionListStubImpl(qproperty, fragments)
    TODO(" Make the immutable field class ")
  }
}

private class UnionListStubImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<FragmentGenerator>
) : Adapter,
    QField<List<QModel<*>>>,
    FragmentProvider {

  var value: List<QModel<*>> = mutableListOf()

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
    it.model.run { "... on $graphqlType${toGraphql()}(false)" }
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>) = value
}

internal class BaseUnionListAdapter<out I : QSchemaUnion>(model: I)
  : UnionListConfigAdapter<I>(GraphQlProperty.ROOT, model) {

  override val queue: DispatchQueue by lazy { DispatchQueue() }

  override fun on(init: () -> QModel<*>) {
    queue.get()?.on(init)
  }
}

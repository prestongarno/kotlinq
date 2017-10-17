package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.internal.FragmentGenerator
import com.prestongarno.ktq.internal.FragmentProvider
import kotlin.reflect.KProperty

interface UnionInitStub<out T : QSchemaUnion> : SchemaStub {
  fun fragment(what: T.() -> Unit): UnionStub
}

internal sealed class UnionAdapter<I : QSchemaUnion>(
    val qproperty: GraphQlProperty,
    objectModel: I
) : QModel<I>(objectModel),
    UnionInitStub<I>,
    UnionStub,
    QSchemaUnion {

  open val fragments = mutableSetOf<FragmentGenerator>()

  /**
   * Recurse to the base model of the graph */
  override val queue by lazy { model.queue }

  private var dispatcher: (I.() -> Unit)? = null

  val args: Map<String, Any> by lazy { mapOf<String, Any>() }

  internal var value: QModel<*>? = null

  override fun fragment(what: I.() -> Unit): UnionStub {
    dispatcher = what
    return this
  }

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): QField<QModel<*>?> {

    synchronized(queue) {
      queue.put(this)
      dispatcher?.invoke(model)
      queue.pop()
    }

    val next = UnionStubImpl(
        GraphQlProperty.from(
            property,
            this.qproperty.graphqlType,
            false,
            this.qproperty.graphqlName
        ), fragments)
    inst.fields.add(next)
    return next
  }

  override fun on(init: () -> QModel<*>) {
    fragments += FragmentGenerator(init)
  }

  companion object {
    fun <I : QSchemaUnion> create(property: GraphQlProperty, objectModel: I): UnionAdapter<I> = UnionAdapterImpl(property, objectModel)
  }
}

internal class UnionAdapterImpl<I : QSchemaUnion>(
    graphqlProperty: GraphQlProperty,
    objectModel: I
) : UnionAdapter<I>(graphqlProperty, objectModel)


internal class BaseUnionAdapter<I : QSchemaUnion>(model: I) : UnionAdapter<I>(GraphQlProperty.ROOT, model) {
  override val queue: DispatchQueue by lazy { DispatchQueue() }

  override fun on(init: () -> QModel<*>) {
    queue.get()?.on(init)
  }
}

private class UnionStubImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<FragmentGenerator>
) : Adapter,
    QField<QModel<*>?>,
    FragmentProvider {

  var value: QModel<*>? = null

  override val args = emptyMap<String, Any>()

  override fun accept(result: Any?): Boolean {
    return if (result is JsonObject) {
      value = result["__typename"]?.let { resultType ->
        fragments.find { it.model.graphqlType == resultType }?.model
      }
      return value?.accept(result) == true
    } else false
  }

  override fun toRawPayload(): String = fragments.joinToString(prefix = "{__typename,", postfix = "}") {
    "... on ${it.model.graphqlType}${it.model.toGraphql(false)}"
  }


  override fun getValue(inst: QModel<*>, property: KProperty<*>): QModel<*>? {
    return value
  }
}

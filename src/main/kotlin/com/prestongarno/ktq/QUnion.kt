package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.internal.FragmentProvider
import kotlin.reflect.KProperty

interface UnionInitStub<out T : QSchemaUnion> : SchemaStub {
  fun fragment(what: T.() -> Unit): UnionStub
}

internal class UnionAdapter<out I: QSchemaUnion>(
    override val graphqlName: String,
    val objectModel: I,
    val fragmentProvided: List<() -> QModel<*>> = emptyList()
) : QModel<I>(objectModel),
    Adapter,
    FragmentProvider,
    UnionInitStub<I>,
    UnionStub,
    QSchemaUnion {

  override val args by lazy { mutableMapOf<String, Any>() }

  private val fragmentAccumulator by lazy { mutableListOf<() -> QModel<*>>().also { it.addAll(fragmentProvided)} }

  override val fragments by lazy { fragmentAccumulator.map { it() } }

  internal var value: QModel<*>? = null

  override fun accept(result: Any?): Boolean {
    return if (result is JsonObject) {
      value = result["__typename"]?.let { resultType ->
        fragments.find { it.graphqlType == resultType }
      }
      return value?.accept(result)?: false
    } else false
  }

  override fun fragment(what: I.() -> Unit): UnionStub {
    objectModel.what()
    return (objectModel.toImmutableStub() as? UnionStub)?: UnionAdapter(graphqlName, objectModel, fragmentAccumulator)
  }

  override fun toRawPayload(): String = fragmentProvided.joinToString(prefix = "__typename,") {
    "... on ${it().graphqlType}${it().toGraphql(false)}"
  }

  override fun getValue(
      inst: QModel<*>,
      property: KProperty<*>
  ): QModel<QSchemaType> { return value?: throw IllegalStateException("null") }

  override fun toImmutableStub(): FragmentProvider {
    return UnionAdapter(graphqlName, objectModel, fragmentAccumulator)
  }

  override fun <R : QModel<*>> provideDelegate(
      inst: R,
      property: KProperty<*>
  ): UnionStub {
    val generated = UnionAdapter(property.name, objectModel)
    generated.onProvideDelegate(inst)
    return generated
  }

  override fun on(init: () -> QModel<*>) {
    fragmentAccumulator.add(init)
  }
}

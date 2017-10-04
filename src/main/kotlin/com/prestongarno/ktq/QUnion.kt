package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.internal.FragmentProvider
import kotlin.reflect.KProperty

interface UnionInitStub<out T : QSchemaUnion> : SchemaStub {
  fun fragment(what: T.() -> QModel<*>): UnionStub
}

internal class UnionAdapter<I: QSchemaUnion>(
    override val graphqlName: String,
    val objectModel: QSchemaUnion
) : QModel<QSchemaUnion>(objectModel),
    Adapter,
    FragmentProvider,
    UnionInitStub<I>,
    UnionStub,
    QSchemaUnion {

  override val args by lazy { mutableMapOf<String, Any>() }

  internal val fragmentAccumulator by lazy { mutableListOf<() -> QModel<*>>() }
  override val fragments by lazy { fragmentAccumulator.map { it() } }
  internal var value: QModel<*> = NONE

  override fun accept(result: Any?): Boolean {
    return if (result is JsonObject) {
      value = result["__typename"]?.let { resultType ->
        fragments.find { it.graphqlType == resultType }?: NONE
      }?: NONE
      value != NONE && value.accept(result)
    } else false
  }

  @Suppress("UNCHECKED_CAST")
  override fun fragment(
      what: I.() -> QModel<*>
  ): UnionStub = what(objectModel as I) as? UnionStub?: throw IllegalStateException()

  override fun toRawPayload(): String = fragments.joinToString(prefix = "__typename,") {
    "... on ${it.graphqlType}${it.toGraphql(false)}"
  }

  override fun getValue(
      inst: QModel<*>,
      property: KProperty<*>
  ): QModel<QSchemaType> = value

  override fun <R : QModel<*>> provideDelegate(
      inst: R,
      property: KProperty<*>
  ): UnionStub = UnionAdapter<I>(graphqlName, objectModel)

  override fun <T : QSchemaUnion> on(init: T.() -> QModel<*>): QModel<*> = apply {
    @Suppress("UNCHECKED_CAST") (objectModel as? T)?.also { fragmentAccumulator.add { init(it) } }
  }
}

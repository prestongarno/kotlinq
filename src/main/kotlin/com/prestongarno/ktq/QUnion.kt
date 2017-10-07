package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.internal.FragmentProvider
import kotlin.reflect.KProperty

interface UnionInitStub<out T : QSchemaUnion> : SchemaStub {
  fun fragment(what: T.() -> QModel<*>): UnionStub
}

internal class UnionAdapter<out I: QSchemaUnion>(
    override val graphqlName: String,
    val objectModel: I
) : QModel<QSchemaUnion>(objectModel),
    Adapter,
    FragmentProvider,
    UnionInitStub<I>,
    UnionStub,
    QSchemaUnion {

  init {
    println("Hello " + this.hashCode())
  }

  override val args by lazy { mutableMapOf<String, Any>() }

  internal val fragmentAccumulator by lazy { mutableListOf<() -> QModel<*>>() }

  override var fragments = emptyList<QModel<*>>()
    get() = fragmentAccumulator.map { it() }

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
  ): UnionStub = apply { this.fragmentAccumulator.add { what.invoke(objectModel) }}.also { println(this.fragmentAccumulator) }

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
  ): UnionStub = UnionAdapter(graphqlName, objectModel).also { inst.fields.add(it) }

  override fun <T : QSchemaUnion> on(init: T.() -> QModel<*>): QModel<*> {
    @Suppress("UNCHECKED_CAST") return apply { fragmentAccumulator.add { init(this as T) } }
  }
}

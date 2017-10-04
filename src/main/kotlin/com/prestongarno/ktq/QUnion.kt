package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.prestongarno.ktq.adapters.FieldAdapter
import com.prestongarno.ktq.internal.FragmentProvider
import kotlin.reflect.KProperty

interface UnionInitStub<out T : QSchemaUnion> : SchemaStub {
  fun on(what: T.() -> QModel<*>): UnionStub
}

internal class UnionStubImpl<out R : QSchemaUnion>(
    val objectModel: R
) : QModel<QSchemaType>(objectModel),
    FragmentProvider<QSchemaType>,
    UnionInitStub<R>,
    UnionStub,
    QSchemaUnion {

  private val __fragments = mutableListOf<() -> QModel<*>>()
  /**
   * Why is there a concurrent modification exception thrown if I don't call __fragments.toList() before map...
   */
  override val fragments by lazy { __fragments.toList().map { it() } }
  internal var value: QModel<*> = NONE

  override fun on(what: R.() -> QModel<*>): UnionStub = what(objectModel) as? UnionStub?: throw IllegalStateException()

  override fun accept(input: JsonObject): Boolean {
    value = input["__typename"]?.let { returned ->
      fragments.map {
        if (it.model::class.simpleName == returned) it else null
      }.filterNotNull().first()
    }?: NONE
    return value != NONE && value.accept(input)
  }

  override fun <T : QSchemaUnion> fragment(init: T.() -> QModel<*>): QModel<*> = apply {
    try {
      __fragments.add { init(objectModel as T) }
    } catch(ex: RuntimeException) { throw ex }
  }

  override fun toPayload(): String {
    return fragments.joinToString(" ") { "... on ${it.model::class.simpleName}${it.toGraphql(false)}" }
  }

  override fun getValue(
      inst: QModel<*>,
      property: KProperty<*>
  ): QModel<QSchemaType> = value

  override fun <R : QModel<*>> provideDelegate(
      inst: R,
      property: KProperty<*>
  ): UnionStub = UnionStubImpl(objectModel)

  companion object {
    val UNKNOWN = UnionStubImpl<QSchemaUnion>(object : QSchemaUnion {
      override fun toPayload(): String = ""
    })
  }
}



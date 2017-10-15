package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.FieldConfig
import com.prestongarno.ktq.QProperty
import com.prestongarno.ktq.QConfigStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.typedValueFrom
import kotlin.reflect.KProperty

/**
 * Adapter for scalar fields */
internal class ScalarStubAdapter<T, out B : ArgBuilder>(
    property: QProperty,
    val builderInit: (ArgBuilder) -> B
) : FieldConfig(property),
    Stub<T>,
    QConfigStub<T, B>,
    ArgBuilder {

  var default: T? = null

  override fun withDefault(value: T) = apply { default = value }

  override fun config(): B = builderInit(ScalarStubAdapter<T, B>(graphqlProperty, builderInit))

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Adapter<T> =
      ScalarStubImpl(QProperty.from(property,
          this.graphqlProperty.graphqlType,
          this.graphqlProperty.isList,
          this.graphqlProperty.graphqlName),
          default
      ).also {
        inst.fields.add(it)
      }

  @Suppress("UNCHECKED_CAST") override fun <T> build(): Stub<T> = this as Stub<T>

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value) }

  override fun toAdapter(): Adapter<*> = ScalarStubImpl(graphqlProperty, default, args.toMap())
}

private data class ScalarStubImpl<T>(
    override val graphqlProperty: QProperty,
    var default: T? = null,
    override val args: Map<String, Any> = emptyMap()
) : Adapter<T> {

  var value: T? = null

  override fun toRawPayload(): String = graphqlProperty.graphqlName +
      if (args.isNotEmpty()) this.args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          } else ""

  // TODO: withDefault(... ) for primitive types
  //override fun withDefault(value: T): Stub<T> = apply { default = value }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T = value ?: default!!

  override fun accept(result: Any?): Boolean {
    @Suppress("UNCHECKED_CAST")
    value = if (result != null) graphqlProperty.kproperty.typedValueFrom(result) as? T ?: default else default
    return value != null
  }

}

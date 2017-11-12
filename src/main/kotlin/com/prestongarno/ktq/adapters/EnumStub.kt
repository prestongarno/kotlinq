package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.EnumStub
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.internal.ValueDelegate
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

@PublishedApi internal class EnumAdapter<T, out A>(
    qproperty: GraphQlProperty,
    private val enumClass: KClass<T>,
    private val argBuilder: A? = null,
    private val argumentScope: (A.() -> Unit)? = null
) : PreDelegate(qproperty),
    EnumStub<T>

    where T : Enum<*>,
          T : QEnumType,
          A : ArgBuilder {


  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> = TODO()
/*      EnumFieldImpl(enumClass, qproperty, argumentScope?.let {
        argBuilder?.it(); argBuilder
      }?.arguments?.getAll()?.toMap() ?: emptyMap()).also {
        inst.fields.add(it)
      }*/

  fun config(config: A.() -> Unit) = EnumAdapter(qproperty, enumClass, argBuilder, config)

}

@ValueDelegate(Enum::class)
private data class EnumFieldImpl<T>(
    private val enumClass: KClass<T>,
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any> = emptyMap()
) : QField<T>, Adapter where T : Enum<*>, T : QEnumType {

  var value: T? = null

  override fun getValue(inst: QModel<*>, property: KProperty<*>) = value!!

  override fun accept(result: Any?): Boolean {
    // TODO don't call the java reflection type - use kotlin enums only
    value = enumClass.java.enumConstants?.find { it.name == "$result" }
    return value != null
  }

  override fun toRawPayload(): String {
    return qproperty.graphqlName +
        if (args.isNotEmpty())
          args.entries.joinToString(",", "(", ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          }
        else ""
  }
}

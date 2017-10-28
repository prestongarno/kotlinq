package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.BaseFieldAdapter
import com.prestongarno.ktq.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaEnum
import com.prestongarno.ktq.hooks.DelegateProvider
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal class EnumAdapter<out T, out A>(
    private val enumClass: KClass<T>,
    private val config: (A.() -> Unit)?,
    val builderInit: (ArgBuilder) -> A,
    graphQlProperty: GraphQlProperty
) : BaseFieldAdapter(graphQlProperty),
    DelegateProvider<T>

    where T : Enum<*>,
          T : QSchemaEnum,
          A : ArgBuilder {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      toField(enumClass,
          graphqlProperty,
          apply {
            config?.invoke(builderInit(this))
          }.args.toMap())

  fun config(config: A.() -> Unit) = EnumAdapter(enumClass, config, builderInit, graphqlProperty)

  override fun toAdapter(): Adapter = from(enumClass, graphqlProperty, args.toMap())

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args[name] = value }

  companion object {
    fun <T> from(
        clazz: KClass<T>,
        property: GraphQlProperty,
        args: Map<String, Any>
    ): Adapter where T : Enum<*>, T : QSchemaEnum = EnumStubImpl(clazz, property, args)

    fun <T> toField(
        clazz: KClass<T>,
        property: GraphQlProperty,
        args: Map<String, Any>
    ): QField<T> where T : Enum<*>, T : QSchemaEnum = EnumStubImpl(clazz, property, args)
  }
}

private data class EnumStubImpl<T>(
    private val enumClass: KClass<T>,
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any> = emptyMap()
) : QField<T>, Adapter where T : Enum<*>, T : QSchemaEnum {

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

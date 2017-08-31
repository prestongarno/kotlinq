package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KProperty

internal class ScalarStubAdapter<T, out B: ArgBuilder>(val builderInit: ( (ArgBuilder) -> B )?) :
    Stub<T>,
    QConfigStub<T, B>,
    ArgBuilder {

  val value : T? = null
  val args: MutableMap<in String, Any> = HashMap()
  lateinit var property: KProperty<*>

  @Suppress("UNCHECKED_CAST")
  override fun config(): B = builderInit?.invoke(this)?: this as B

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T> {
    println(property)
    this.property = property
    return this
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T = value!!

  @Suppress("UNCHECKED_CAST") override fun <T> build() : Stub<T> = this as Stub<T>

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value)  }

  override fun toString(): String = args.map { "${it.key} : ${formatAs(it.value)}" }.joinToString { "\n" }

}

private fun formatAs(value: Any): String {
  return when (value) {
    is Int, is Boolean, Float -> "$value"
    is String -> "\"$value\""
    is QInput -> value.toPayloadString()
    is Enum<*> -> value.name
    is List<*> -> value
        .map { formatAs(it ?: "") }
        .filter { it.isNotBlank() }
        .joinToString(", ", "[ ", " ]")
    else -> throw UnsupportedOperationException()
  }
}

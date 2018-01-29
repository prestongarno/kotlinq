package org.kotlinq.adapters

import java.lang.ref.WeakReference
import kotlin.reflect.KType


internal
class ParsedProperty(
    override val name: String,
    override val type: KType,
    override val initializer: (String) -> Any?,
    override val context: WeakReference<ModelAdapter>
) : ParsingAdapter {

  lateinit var textResult: String
  // TODO best way to handle this
  private val result: Any? by lazy {
    if (!this::textResult.isInitialized || context.get()?.parseField(this) == false)
      initializer("")
    else initializer(textResult)
  }

  override fun getValue(): Any? = result

  override fun take(value: String): Boolean {
    textResult = value
    return true
  }

}
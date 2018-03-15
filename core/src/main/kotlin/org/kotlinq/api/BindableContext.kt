package org.kotlinq.api


/**
 * Usually this would be done with a lambda,
 * but it's an important concept so an interface is defined
 */
interface BindableContext {

  fun register(adapter: Adapter): BindableContext

  fun build(typeName: String): Fragment


  companion object {

    internal
    fun newBindableContext(
        call: (Adapter) -> Unit,
        onBuild: (typeName: String) -> Fragment
    ): BindableContext = BindableContextImpl(call, onBuild)

    private
    class BindableContextImpl(
        private val call: (Adapter) -> Unit,
        private val onBuild: (typeName: String) -> Fragment
    ) : BindableContext {

      override fun build(typeName: String) = onBuild(typeName)

      override fun register(adapter: Adapter) = apply { call(adapter) }
    }
  }
}
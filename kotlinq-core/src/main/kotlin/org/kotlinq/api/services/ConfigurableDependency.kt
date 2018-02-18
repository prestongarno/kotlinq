package org.kotlinq.api.services

/**
 * Internal interface for re-configuring services (these dependencies are stateless,
 * so synchronizing on [ConfigurableDependency.use] will be totally fine)
 */
internal interface ConfigurableDependency<T> {

  val default: T

  fun instance(): T

  fun use(instance: T)


  companion object {

    fun <T : Any> configurableDependencyWithDefault(default: T)
        : ConfigurableDependency<T> =
        ConfigurableDependencyImpl(default)


    private
    class ConfigurableDependencyImpl<T : Any>(override val default: T) : ConfigurableDependency<T> {

      private var privateInstance: T = default

      override fun instance(): T = privateInstance

      override fun use(instance: T) {
        synchronized(this.privateInstance) {
          privateInstance = instance
        }
      }

    }
  }

}
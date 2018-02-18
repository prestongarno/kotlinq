package org.kotlinq.api.services

/**
 * Internal interface for re-configuring services (these dependencies are stateless,
 * so synchronizing on [ConfigurableDependency.use] will be totally fine)
 */
internal interface ConfigurableDependency<T> {

  fun instance(): T

  fun use(instance: T)

  fun useDefault()
}
package org.kotlinq.providers

import org.kotlinq.Model
import org.kotlinq.dsl.ArgBuilder
import org.kotlinq.dsl.DslBuilder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface GraphQlPropertyProvider<out Z> {
  // TODO get state container in the [Model] instance and pass to back-end
  operator fun provideDelegate(inst: Model<*>, property: KProperty<*>)
      : ReadOnlyProperty<Model<*>, Z>
}

//internal
interface DslBuilderProvider<Z> : DslBuilder<Z, ArgBuilder>, GraphQlPropertyProvider<Z>


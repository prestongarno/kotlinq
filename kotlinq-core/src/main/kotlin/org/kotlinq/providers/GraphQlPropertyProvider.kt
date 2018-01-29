package org.kotlinq.providers

import org.kotlinq.Model
import org.kotlinq.dsl.ArgBuilder
import org.kotlinq.dsl.DslBuilder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface GraphQlPropertyProvider<out Z> {
  operator fun provideDelegate(inst: Model<*>, property: KProperty<*>)
      : ReadOnlyProperty<Model<*>, Z>
}

//internal
interface DslBuilderProvider<Z> : DslBuilder<Z, ArgBuilder>, GraphQlPropertyProvider<Z>


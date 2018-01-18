package com.prestongarno.kotlinq.core.schema.properties.iface

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.properties.delegates.ConfiguredBlock
import com.prestongarno.kotlinq.core.properties.delegates.NoArgBlock
import com.prestongarno.kotlinq.core.schema.stubs.InterfaceListStub
import com.prestongarno.kotlinq.core.schema.stubs.InterfaceStub

typealias InterfaceProperty<T> = NoArgBlock<InterfaceStub<T, ArgBuilder>, QModel<T>?>
typealias OptionallyConfiguredInterfaceProperty<T, A> = InterfaceStub.OptionallyConfigured<T, A>
typealias ConfiguredInterfaceProperty<T, A> = ConfiguredBlock<InterfaceStub<T, A>, A, QModel<T>?>

typealias InterfaceListProperty<T> = InterfaceListStub.NoArg.NotNull<T>
typealias OptionallyConfiguredInterfaceListProperty<T, A> = InterfaceListStub.OptionallyConfigured.NotNull<T, A>
typealias ConfiguredInterfaceListProperty<T, A> = InterfaceListStub.Configured.NotNull<T, A>



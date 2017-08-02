package com.prestongarno.ktq.runtime.annotations

import com.prestongarno.ktq.runtime.Payload
import kotlin.reflect.KClass

annotation class WithArgs(val ofType: KClass<out Payload>)

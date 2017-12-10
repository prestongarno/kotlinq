package com.prestongarno.kotlinq.core

import com.google.common.truth.Truth.assertThat

infix fun Any?.eq(other: Any?): Boolean =
    if (other === Nothing::class) true else assertThat(this).isEqualTo(other) == Unit

infix fun Any?.neq(other: Any?): Boolean =
    if (other === Nothing::class) true else assertThat(this).isNotEqualTo(other) == Unit


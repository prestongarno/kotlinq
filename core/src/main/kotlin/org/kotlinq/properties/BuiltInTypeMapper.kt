package org.kotlinq.properties

import org.kotlinq.api.ScalarAdapterService


class BuiltInTypeMapper(
    override val booleanMapper: (String) -> Boolean = String::toBoolean,
    override val floatMapper: (String) -> Float = { it.toFloatOrNull() ?: 0f },
    override val intMapper: (String) -> Int = { it.toIntOrNull() ?: 0 },
    override val stringMapper: (String) -> String = { it }
) : ScalarAdapterService.TypeMappers
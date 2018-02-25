package org.kotlinq.jvm

import org.kotlinq.api.Kind
import org.kotlinq.api.PropertyInfo
import kotlin.reflect.KProperty0


@PublishedApi internal
fun KProperty0<Data?>.toPropertyInfo(
    typeName: String,
    args: Map<String, Any> = emptyMap()
) = PropertyInfo.propertyNamed("")
    .typeKind(Kind.typeNamed(typeName))
    .arguments(args)
    .build()


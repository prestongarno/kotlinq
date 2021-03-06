package org.kotlinq.properties

import org.kotlinq.api.ParsingAdapter
import org.kotlinq.api.PropertyInfo
import org.kotlinq.api.ScalarAdapterService
import org.kotlinq.api.Kind


class ScalarAdapterServiceImpl(
    override val mappers: ScalarAdapterService.TypeMappers = BuiltInTypeMapper()
) : ScalarAdapterService {

  override fun newAdapter(info: PropertyInfo): ParsingAdapter {
    require(info.kind.isScalar) { "Type $info is not a scalar type" }
    return when (info.kind.rootKind()) {
      Kind.Scalar._Int -> IntAdapterImpl(info, mappers.intMapper)
      Kind.Scalar._String -> StringAdapterImpl(info, mappers.stringMapper)
      Kind.Scalar._Float -> FloatAdapterImpl(info, mappers.floatMapper)
      Kind.Scalar._Boolean -> BooleanAdapterImpl(info, mappers.booleanMapper)
      else -> {
        throw IllegalArgumentException("Illegal info '$info'")
      }
    }
  }
}
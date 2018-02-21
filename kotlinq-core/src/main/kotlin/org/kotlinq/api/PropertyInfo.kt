package org.kotlinq.api

data
class PropertyInfo(
    val graphQlName: String,
    val kind: Kind,
    val arguments: Map<String, Any> = emptyMap()) {

  val graphQlType: String
    get() = kind.classifier

  val isNullable: Boolean get() =
    kind is Kind.NullableKind



  class Builder internal constructor(private var name: String) {
    private var arguments: Map<String, Any> = emptyMap()
    private var kind: Kind = Kind._String

    fun named(it: String) = apply { this.name = it }
    fun arguments(it: Map<String, Any>) = apply { arguments = it }
    fun typeKind(it: Kind) = apply { kind = it }

    fun build() = PropertyInfo(name, kind, arguments)
  }


  companion object {

    fun named(it: String) = Builder(it)
  }
}


import org.kotlinq.adapters.FragmentImpl
import org.kotlinq.api.Adapter
import org.kotlinq.api.Context
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.GraphQlType
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.ParsingAdapter
import org.kotlinq.api.Resolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

fun createGraph(definition: GraphBuilder.TypeBuilder.() -> Unit) =
    GraphBuilder("Query", definition).build()

class GraphBuilder(override val graphQlTypeName: String, private val definition: TypeBuilder.() -> Unit)
  : GraphQlInstance by GraphQlInstanceProvider.createNewInstance(graphQlTypeName) {

  fun build(): Context {
    TypeBuilder(this).apply(definition)
    return MockContext(this)
  }

  class TypeBuilder(private val graph: GraphBuilder) {

    var arguments: Map<String, Any> = emptyMap()
    var isNullable: Boolean = true

    fun scalar(
        name: String,
        type: GraphQlType = MockScalarType(String::class),
        arguments: Map<String, Any> = emptyMap()
    ) = graph.bindProperty(MockScalarAdapter(name, type, arguments))

    infix fun String.ofType(name: String): TypeFieldBuilder {
      return TypeFieldBuilder(this, name)
    }

    infix fun TypeFieldBuilder.definedAs(block: TypeBuilder.() -> Unit) {
      val def = TypeBuilder(GraphBuilder(typeName, block)).apply(block)
      graph.bindProperty(MockTypeField(fieldName, typeName, { GraphBuilder(typeName, block).build() }, def.isNullable, def.arguments))
    }

    infix fun TypeFieldBuilder.spread(block: FragmentBuilder.() -> Unit) {
      val builder = FragmentBuilder().apply(block)
      MockFragmentField(
          fieldName,
          builder.fragments.map { it.value }.toSet(),
          typeName,
          builder.isNullable,
          arguments = builder.arguments)
          .also(this@TypeBuilder.graph::bindProperty)
    }

    class TypeFieldBuilder(val fieldName: String, val typeName: String)
  }

  class FragmentBuilder {

    val fragments = mutableMapOf<String, () -> Context>()

    var arguments: Map<String, Any> = emptyMap()
    var isNullable: Boolean = true

    infix fun String.fragmentDef(block: TypeBuilder.() -> Unit) {
      fragments[this] = { GraphBuilder(this, block).build() }
    }
  }

}

private
class MockFragmentField(
    override val name: String,
    fragments: Set<() -> Context>,
    typeName: String = "Any",
    isNullable: Boolean = false,
    override val type: GraphQlType = MockScalarType(name = typeName, isNullable = isNullable),
    override val arguments: Map<String, Any> = emptyMap()
) : FragmentAdapter, Adapter by Kotlinq.adapterService.fragmentProperty(name, type.ktype, fragments, arguments) {

  private var value: Context? = null

  override val fragments: Map<String, Fragment> = fragments
      .map { FragmentImpl(it) }
      .map { it.typeName to it }.toMap()

  override fun setValue(typeName: String, values: Map<String, Any?>, resolver: Resolver): Boolean {
    this.value = fragments[typeName]?.initializer?.invoke()?.apply {
      resolver.resolve(values, this)
    }
    return value?.graphQlInstance?.isResolved() ?: type.isNullable
  }
}


private
class MockTypeField(
    override val name: String,
    typeName: String,
    override val initializer: () -> Context,
    isNullable: Boolean = true,
    override val arguments: Map<String, Any> = emptyMap()
) : ModelAdapter {

  override val prototype: Context by lazy(initializer)

  override val type = MockScalarType(name = typeName, isNullable = isNullable)

  private var value: Context? = null

  override fun setValue(result: Map<String, Any?>, resolver: Resolver): Boolean {
    value = initializer().apply { resolver.resolve(result, this) }
    return value?.graphQlInstance?.isResolved() == true || type.isNullable
  }

  override fun getValue() = value

  override fun accept(resolver: GraphVisitor) {
    resolver.visitModel(this)
  }

  override fun isResolved() = true
}


private
class MockContext(override val graphQlInstance: GraphQlInstance) : Context


private class MockScalarAdapter(
    override val name: String,
    override val type: GraphQlType = MockScalarType(String::class),
    override val arguments: Map<String, Any> = emptyMap()
) : ParsingAdapter {

  override val initializer: (String) -> Any? = { it }

  private var value = ""

  override fun setValue(value: String?): Boolean {
    this.value = "${initializer("$value")}"
    return true
  }

  override fun getValue(): Any? = value

  override fun accept(resolver: GraphVisitor) {
    resolver.visitScalar(this)
  }

  override fun isResolved(): Boolean = true
}


class MockScalarType(
    val clazz: KClass<*> = Any::class,
    override val name: String = clazz.simpleName!!,
    override val isNullable: Boolean = true,
    override val ktype: KType = mockType(clazz, isMarkedNullable = isNullable)
) : GraphQlType

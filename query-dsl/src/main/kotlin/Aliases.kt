import org.kotlinq.api.GraphQlInstance
import org.kotlinq.dsl.TypeBuilder


typealias LeafGetter = (() -> LeafBinding)

typealias LeafBinding = (GraphQlInstance) -> Unit

typealias TypeBuilderBlock = TypeBuilder.() -> Unit
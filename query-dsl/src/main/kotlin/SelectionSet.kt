import org.kotlinq.dsl.GraphBuilder
import org.kotlinq.dsl.TypeBuilder


typealias SelectionSet = TypeBuilder.() -> Unit


internal fun SelectionSet.asInitializer(typeName: String) =
    GraphBuilder(typeName, this)::build
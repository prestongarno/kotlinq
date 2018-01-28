import org.kotlinq.Model
import org.kotlinq.api.DaggerKotlinq
import org.kotlinq.delegates.CollectionStubN
import org.kotlinq.dsl.ArgBuilder
import org.kotlinq.static.ContextBuilder.Companion.schema
import org.kotlinq.static.enumMapper

/*******************
 * Generated schema
 */

enum class Response {
  YES, NO, MAYBE
}

object ContextDao {

  val response by enumMapper<Response>()

  val modelQuery by schema<BasicModel>().requiringArguments<ArgBuilder>().build()

  val model2D by schema<BasicModel>()
      .asList()
      .asList()
      .build()

  val model2DWithArgs by schema<BasicModel>()
      .asList()
      .asList()
      .requiringArguments<BazArgs>()
      .build()

  val modelNDimensions: CollectionStubN<Model<BasicModel>, List<List<List<List<Model<BasicModel>>>>>> by schema<BasicModel>()
      .asList()
      .asList()
      .asList()
      .asList()
      .build()

  val modelList by schema<BasicModel>()
      .asList()
      .build()

  class BazArgs : ArgBuilder() {
    var stringArgumentOptional: String? = null
  }
}

object BasicModel {
  val response by enumMapper<Response>()
}

/*******************
 * Graph query implementations
 */
class BasicModelQuery : Model<BasicModel>(model = BasicModel) {
  val parsedResponse by model.response
}

class ContextDaoQuery : Model<ContextDao>(model = ContextDao) {

  val enumMapped by model.response

  val modelImpl by (model.modelQuery.withArguments(ArgBuilder()))(::BasicModelQuery)

  val model2DImpl by model.model2D(::BasicModelQuery)

  val model2DArgsImpl by model.model2DWithArgs
      .withArguments(ContextDao.BazArgs())(::BasicModelQuery)

  // erased actual type after >2 nested lists
  val modelNDimenstion by model.modelNDimensions { BasicModelQuery() }

  val singleList by model.modelList(::BasicModelQuery)
}

fun main(args: Array<String>) {

  DaggerKotlinq.create()



  val foo = ContextDaoQuery()

  require(foo.properties.values.find {

    !it.adapter.accept(
        when (it.propertyName) {
          "response" -> "NO"
          "modelQuery" -> "{NO}"
          "model2D" -> "[[{\"response\": \"YES\"]]"
          else -> ""
        }

    )
  } == null)

  foo.apply {
    require(enumMapped == Response.NO)
    println(modelImpl)
    require(model2DImpl.firstOrNull()?.firstOrNull()?.parsedResponse == Response.NO)
    require(model2DArgsImpl.firstOrNull()?.firstOrNull()?.parsedResponse == Response.YES)
    require(singleList.firstOrNull()?.parsedResponse == Response.YES)

    val passesNDimensionTest = modelNDimenstion.firstOrNull()
        ?.firstOrNull()
        ?.firstOrNull()
        ?.firstOrNull()
        ?.let { it as? BasicModelQuery }
        ?.let {
          it.parsedResponse == Response.NO
        } ?: false

    require(passesNDimensionTest) // !!!
  }
}

internal
fun <T> List<T>.nested() = listOf(this)

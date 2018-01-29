import org.kotlinq.Model
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


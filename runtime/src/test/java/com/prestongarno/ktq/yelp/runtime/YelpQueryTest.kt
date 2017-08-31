package com.prestongarno.ktq.yelp.runtime

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.yelp.Business
import com.prestongarno.ktq.yelp.Businesses
import com.prestongarno.ktq.yelp.Query
import java.util.function.Consumer

class BusinessQuery(searchTerm: String) : QModel<Query>(Query::class) {
  val result by model.search.config()
      .term(searchTerm)
      .limit(10)
      .build { BusinessesNodesModel() }
}

class BusinessesNodesModel : QModel<Businesses>(Businesses::class) {
  val resultCount by model.total
  val resultsNodes by model.business
      .init { BusinessBasic() }
}

class BusinessBasic : QModel<Business>(Business::class) {
  val name by model.name
  val phoneNumber by model.display_phone
  val directUrl by model.url
}
class Guac(somethingElse: String) : Consumer<String> by Consumer({ println(it) })

package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.indent
import com.prestongarno.ktq.yelp.Business
import com.prestongarno.ktq.yelp.Businesses
import com.prestongarno.ktq.yelp.Query
import com.prestongarno.ktq.yelp.Reviews
import com.prestongarno.ktq.yelp.Review
import org.junit.Test

class BusinessQuery(searchTerm: String) : QModel<Query>(Query::class) {
  val result by model.search.config()
      .term(searchTerm)
      .limit(10)
      .build { BusinessesNodesModel() }
  val reviews by model.reviews.config()
      .locale("ENGLISH")
      .business("Wal-Mart")
      .build { ReviewsHolder() }
}

class ReviewsHolder : QModel<Reviews>(Reviews::class) {
  val all by model.review
      .init { ReviewModel() }
}

class ReviewModel : QModel<Review>(Review::class) {
  val content by model.text
  val rating by model.rating
  val timePosted by model.time_created
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

class TestCorrectStructure {
  @Test
  fun testBusinessBasic() {
    val one = BusinessQuery("asuh dude")
    val two = BusinessQuery("foobar")
    // make sure that a new config & new Model instance is created per invocation
    require(one.fields != two.fields)
    require(one.result != two.result)
    require(one.reviews != two.reviews)
    println(one.toJson())
    println(one.toJson().replace("\n", " ").replace("\\s*".toRegex(), ""))
  }
}

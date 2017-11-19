/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

@file:Suppress("unused")
/*
package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.stubs.CustomStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.URL
import com.prestongarno.ktq.yelp.Business
import com.prestongarno.ktq.yelp.Businesses
import com.prestongarno.ktq.yelp.Query
import com.prestongarno.ktq.yelp.Reviews
import com.prestongarno.ktq.yelp.Review
import org.junit.Ignore
import org.junit.Test

class BusinessQuery(searchTerm: String) : QModel<Query>(Query) {
  val result by model.search.scope {
    term(searchTerm)
    limit(10)
  }.querying { BusinessesNodesModel() }

  val reviews by model.reviews.scope {
    locale("ENGLISH")
    business("Wal-Mart")
  }.querying { ReviewsHolder() }
}

class ReviewsHolder : QModel<Reviews>(Reviews) {
  val all by model.review
      .querying { ReviewModel() }
}

class ReviewModel : QModel<Review>(Review) {
  val content by model.text
  val rating by model.rating
  val timePosted by model.time_created
}

class BusinessesNodesModel : QModel<Businesses>(Businesses) {
  val resultCount by model.total
  val resultsNodes by model.business
      .querying { BusinessBasic() }
}

class BusinessBasic : QModel<Business>(Business) {
  val name by model.name
  val phoneNumber by model.display_phone
  val directUrl by model.url
}

class TestCorrectStructure {
  @Ignore @Test
  fun testBusinessBasic() {
    // make sure that a create scope & create Model instance is created per invocation
    val one = BusinessQuery("bazfoo")
    val two = BusinessQuery("foobar")
    require(one.fields != two.fields)
    require(one.result != two.result)
    require(one.reviews != two.reviews)
  }
}*/

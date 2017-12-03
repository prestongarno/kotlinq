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

package com.prestongarno.kotlinq.core.yelp

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.QSchemaType.*
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.stubs.BooleanDelegate
import com.prestongarno.kotlinq.core.stubs.FloatDelegate
import com.prestongarno.kotlinq.core.stubs.IntDelegate
import com.prestongarno.kotlinq.core.stubs.StringArrayDelegate
import com.prestongarno.kotlinq.core.stubs.StringDelegate
import com.prestongarno.kotlinq.core.stubs.TypeListStub
import com.prestongarno.kotlinq.core.stubs.TypeStub

/**
 * TODO make a "generation" task and compile + jar all schemas for testing runtime API
 */
object Business : QType {
  val name: StringDelegate.Query by QScalar.String.stub()

  val id: StringDelegate.Query by QScalar.String.stub()

  val is_claimed: BooleanDelegate.Query by QScalar.Boolean.stub()

  val is_closed: BooleanDelegate.Query by QScalar.Boolean.stub()

  val url: StringDelegate.Query by QScalar.String.stub()

  val phone: StringDelegate.Query by QScalar.String.stub()

  val display_phone: StringDelegate.Query by QScalar.String.stub()

  val review_count: IntDelegate.Query by QScalar.Int.stub()

  val categories: TypeListStub.Query<Category> by QTypes.List.stub<Category>()

  val rating: FloatDelegate.Query by QScalar.Float.stub()

  val price: StringDelegate.Query by QScalar.String.stub()

  val location: TypeStub.Query<Location> by QTypes.stub<Location>()

  val coordinates: TypeStub.Query<Coordinates> by QTypes.stub<Coordinates>()

  val photos: StringArrayDelegate.Query by QScalar.List.String.stub()

  val hours: TypeListStub.Query<Hours> by QTypes.List.stub<Hours>()

  val reviews: TypeListStub.Query<Review> by QTypes.List.stub<Review>()
}


object Businesses : QType {
  val business: TypeListStub.Query<Business> by QTypes.List.stub<Business>()

  val total: IntDelegate.Query by QScalar.Int.stub()
}


object Category : QType {
  val title: StringDelegate.Query by QScalar.String.stub()

  val alias: StringDelegate.Query by QScalar.String.stub()
}


object Coordinates : QType {
  val latitude: FloatDelegate.Query by QScalar.Float.stub()

  val longitude: FloatDelegate.Query by QScalar.Float.stub()
}


object Hours : QType {
  val hours_type: StringDelegate.Query by QScalar.String.stub()

  val open: TypeListStub.Query<OpenHours> by QTypes.List.stub<OpenHours>()

  val is_open_now: BooleanDelegate.Query by QScalar.Boolean.stub()
}


object Location : QType {
  val address1: StringDelegate.Query by QScalar.String.stub()

  val address2: StringDelegate.Query by QScalar.String.stub()

  val address3: StringDelegate.Query by QScalar.String.stub()

  val city: StringDelegate.Query by QScalar.String.stub()

  val state: StringDelegate.Query by QScalar.String.stub()

  val zip_code: StringDelegate.Query by QScalar.String.stub()

  val country: StringDelegate.Query by QScalar.String.stub()

  val formatted_address: StringDelegate.Query by QScalar.String.stub()
}


object OpenHours : QType {
  val is_overnight: BooleanDelegate.Query by QScalar.Boolean.stub()

  val end: StringDelegate.Query by QScalar.String.stub()

  val start: StringDelegate.Query by QScalar.String.stub()

  val day: IntDelegate.Query by QScalar.Int.stub()
}


object Query : QType {
  val business: TypeStub.ConfigurableQuery<Business, BusinessArgs> by QTypes.configStub<Business, BusinessArgs>()

  val business_match_best: TypeStub.ConfigurableQuery<Business, Business_match_bestArgs> by QTypes.configStub<Business, Business_match_bestArgs>()

  val business_match_lookup: TypeStub.ConfigurableQuery<Businesses, Business_match_lookupArgs> by QTypes.configStub<Businesses, Business_match_lookupArgs>()

  val reviews: TypeStub.OptionalConfigQuery<Reviews, ReviewsArgs> by QTypes.optionalConfigStub<Reviews, ReviewsArgs>()

  val phone_search: TypeStub.OptionalConfigQuery<Businesses, Phone_searchArgs> by QTypes.optionalConfigStub<Businesses, Phone_searchArgs>()

  val search: TypeStub.OptionalConfigQuery<Businesses, SearchArgs> by QTypes.optionalConfigStub<Businesses, SearchArgs>()

  class BusinessArgs(id: String) : ArgBuilder() {
    init {
      "id" with id}
  }

  class Business_match_bestArgs(
      name: String,
      city: String,
      state: String,
      country: String
  ) : ArgBuilder() {
    var address1: String? by arguments

    var address2: String? by arguments

    var address3: String? by arguments

    var latitude: Float? by arguments

    var longitude: Float? by arguments

    var phone: String? by arguments

    var postal_code: String? by arguments
    init {
      "name" with name
      "city" with city
      "state" with state
      "country" with country}
  }

  class Business_match_lookupArgs(
      name: String,
      city: String,
      state: String,
      country: String
  ) : ArgBuilder() {
    var address1: String? by arguments

    var address2: String? by arguments

    var address3: String? by arguments

    var latitude: Float? by arguments

    var longitude: Float? by arguments

    var phone: String? by arguments

    var postal_code: String? by arguments
    init {
      "name" with name
      "city" with city
      "state" with state
      "country" with country}
  }

  class ReviewsArgs : ArgBuilder() {
    var business: String? by arguments

    var locale: String? by arguments
  }

  class Phone_searchArgs : ArgBuilder() {
    var phone: String? by arguments
  }

  class SearchArgs : ArgBuilder() {
    var term: String? by arguments

    var location: String? by arguments

    var country: String? by arguments

    var offset: Int? by arguments

    var limit: Int? by arguments

    var sort_by: String? by arguments

    var locale: String? by arguments

    var longitude: Float? by arguments

    var latitude: Float? by arguments

    var categories: String? by arguments

    var open_now: Boolean? by arguments

    var open_at: Int? by arguments

    var price: String? by arguments

    var attributes: String? by arguments

    var radius: Float? by arguments
  }
}


object Review : QType {
  val rating: IntDelegate.Query by QScalar.Int.stub()

  val user: TypeStub.Query<User> by QTypes.stub<User>()

  val text: StringDelegate.Query by QScalar.String.stub()

  val time_created: StringDelegate.Query by QScalar.String.stub()

  val url: StringDelegate.Query by QScalar.String.stub()
}


object Reviews : QType {
  val review: TypeListStub.Query<Review> by QTypes.List.stub<Review>()

  val total: IntDelegate.Query by QScalar.Int.stub()
}


object User : QType {
  val image_url: StringDelegate.Query by QScalar.String.stub()

  val name: StringDelegate.Query by QScalar.String.stub()
}
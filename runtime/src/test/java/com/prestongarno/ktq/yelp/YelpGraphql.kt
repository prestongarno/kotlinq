@file:Suppress("unused")

package com.prestongarno.ktq.yelp

import com.prestongarno.ktq.*

object Business : QType {
  val name: Stub<String> by lazy { stub<String>() }

  val id: Stub<String> by lazy { stub<String>() }

  val is_claimed: Stub<Boolean> by lazy { stub<Boolean>() }

  val is_closed: Stub<Boolean> by lazy { stub<Boolean>() }

  val url: Stub<String> by lazy { stub<String>() }

  val phone: Stub<String> by lazy { stub<String>() }

  val display_phone: Stub<String> by lazy { stub<String>() }

  val review_count: Stub<Int> by lazy { stub<Int>() }

  val categories: InitStub<Category> by lazy { typeStub<Category>() }

  val rating: Stub<Float> by lazy { stub<Float>() }

  val price: Stub<String> by lazy { stub<String>() }

  val location: InitStub<Location> by lazy { typeStub<Location>() }

  val coordinates: InitStub<Coordinates> by lazy { typeStub<Coordinates>() }

  val photos: Stub<String> by lazy { stub<String>() }

  val hours: InitStub<Hours> by lazy { typeStub<Hours>() }

  val reviews: InitStub<Review> by lazy { typeStub<Review>() }
}

object Businesses : QType {
  val business: InitStub<Business> by lazy { typeStub<Business>() }

  val total: Stub<Int> by lazy { stub<Int>() }
}

object Category : QType {
  val title: Stub<String> by lazy { stub<String>() }

  val alias: Stub<String> by lazy { stub<String>() }
}

object Coordinates : QType {
  val latitude: Stub<Float> by lazy { stub<Float>() }

  val longitude: Stub<Float> by lazy { stub<Float>() }
}

object Hours : QType {
  val hours_type: Stub<String> by lazy { stub<String>() }

  val open: InitStub<OpenHours> by lazy { typeStub<OpenHours>() }

  val is_open_now: Stub<Boolean> by lazy { stub<Boolean>() }
}

object Location : QType {
  val address1: Stub<String> by lazy { stub<String>() }

  val address2: Stub<String> by lazy { stub<String>() }

  val address3: Stub<String> by lazy { stub<String>() }

  val city: Stub<String> by lazy { stub<String>() }

  val state: Stub<String> by lazy { stub<String>() }

  val zip_code: Stub<String> by lazy { stub<String>() }

  val country: Stub<String> by lazy { stub<String>() }

  val formatted_address: Stub<String> by lazy { stub<String>() }
}

object OpenHours : QType {
  val is_overnight: Stub<Boolean> by lazy { stub<Boolean>() }

  val end: Stub<String> by lazy { stub<String>() }

  val start: Stub<String> by lazy { stub<String>() }

  val day: Stub<Int> by lazy { stub<Int>() }
}

object Query : QType {
  val business: ConfigType<Business, BusinessArgs> by lazy { typeConfigStub<Business, BusinessArgs>(BusinessArgs()) }

  val business_match_best: ConfigType<Business, Business_match_bestArgs> by lazy { typeConfigStub<Business, Business_match_bestArgs>(Business_match_bestArgs()) }

  val business_match_lookup: ConfigType<Businesses, Business_match_lookupArgs> by lazy { typeConfigStub<Businesses, Business_match_lookupArgs>(Business_match_lookupArgs()) }

  val reviews: ConfigType<Reviews, ReviewsArgs> by lazy { typeConfigStub<Reviews, ReviewsArgs>(ReviewsArgs()) }

  val phone_search: ConfigType<Businesses, Phone_searchArgs> by lazy { typeConfigStub<Businesses, Phone_searchArgs>(Phone_searchArgs()) }

  val search: ConfigType<Businesses, SearchArgs> by lazy { typeConfigStub<Businesses, SearchArgs>(SearchArgs()) }

  class BusinessArgs(args: TypeArgBuilder = TypeArgBuilder.create<Business, BusinessArgs>()) : TypeArgBuilder by args {
    fun id(value: String): BusinessArgs = apply { addArg("id", value) }
  }

  class Business_match_bestArgs(args: TypeArgBuilder = TypeArgBuilder.create<Business, Business_match_bestArgs>()) : TypeArgBuilder by args {
    fun name(value: String): Business_match_bestArgs = apply { addArg("name", value) }


    fun address1(value: String): Business_match_bestArgs = apply { addArg("address1", value) }


    fun address2(value: String): Business_match_bestArgs = apply { addArg("address2", value) }


    fun address3(value: String): Business_match_bestArgs = apply { addArg("address3", value) }


    fun city(value: String): Business_match_bestArgs = apply { addArg("city", value) }


    fun state(value: String): Business_match_bestArgs = apply { addArg("state", value) }


    fun country(value: String): Business_match_bestArgs = apply { addArg("country", value) }


    fun latitude(value: Float): Business_match_bestArgs = apply { addArg("latitude", value) }


    fun longitude(value: Float): Business_match_bestArgs = apply { addArg("longitude", value) }


    fun phone(value: String): Business_match_bestArgs = apply { addArg("phone", value) }


    fun postal_code(value: String): Business_match_bestArgs = apply { addArg("postal_code", value) }

  }

  class Business_match_lookupArgs(args: TypeArgBuilder = TypeArgBuilder.create<Businesses, Business_match_lookupArgs>()) : TypeArgBuilder by args {
    fun name(value: String): Business_match_lookupArgs = apply { addArg("name", value) }


    fun address1(value: String): Business_match_lookupArgs = apply { addArg("address1", value) }


    fun address2(value: String): Business_match_lookupArgs = apply { addArg("address2", value) }


    fun address3(value: String): Business_match_lookupArgs = apply { addArg("address3", value) }


    fun city(value: String): Business_match_lookupArgs = apply { addArg("city", value) }


    fun state(value: String): Business_match_lookupArgs = apply { addArg("state", value) }


    fun country(value: String): Business_match_lookupArgs = apply { addArg("country", value) }


    fun latitude(value: Float): Business_match_lookupArgs = apply { addArg("latitude", value) }


    fun longitude(value: Float): Business_match_lookupArgs = apply { addArg("longitude", value) }


    fun phone(value: String): Business_match_lookupArgs = apply { addArg("phone", value) }


    fun postal_code(value: String): Business_match_lookupArgs = apply { addArg("postal_code", value) }

  }

  class ReviewsArgs(args: TypeArgBuilder = TypeArgBuilder.create<Reviews, ReviewsArgs>()) : TypeArgBuilder by args {
    fun business(value: String): ReviewsArgs = apply { addArg("business", value) }


    fun locale(value: String): ReviewsArgs = apply { addArg("locale", value) }

  }

  class Phone_searchArgs(args: TypeArgBuilder = TypeArgBuilder.create<Businesses, Phone_searchArgs>()) : TypeArgBuilder by args {
    fun phone(value: String): Phone_searchArgs = apply { addArg("phone", value) }

  }

  class SearchArgs(args: TypeArgBuilder = TypeArgBuilder.create<Businesses, SearchArgs>()) : TypeArgBuilder by args {
    fun term(value: String): SearchArgs = apply { addArg("term", value) }


    fun location(value: String): SearchArgs = apply { addArg("location", value) }


    fun country(value: String): SearchArgs = apply { addArg("country", value) }


    fun offset(value: Int): SearchArgs = apply { addArg("offset", value) }


    fun limit(value: Int): SearchArgs = apply { addArg("limit", value) }


    fun sort_by(value: String): SearchArgs = apply { addArg("sort_by", value) }


    fun locale(value: String): SearchArgs = apply { addArg("locale", value) }


    fun longitude(value: Float): SearchArgs = apply { addArg("longitude", value) }


    fun latitude(value: Float): SearchArgs = apply { addArg("latitude", value) }


    fun categories(value: String): SearchArgs = apply { addArg("categories", value) }


    fun open_now(value: Boolean): SearchArgs = apply { addArg("open_now", value) }


    fun open_at(value: Int): SearchArgs = apply { addArg("open_at", value) }


    fun price(value: String): SearchArgs = apply { addArg("price", value) }


    fun attributes(value: String): SearchArgs = apply { addArg("attributes", value) }


    fun radius(value: Float): SearchArgs = apply { addArg("radius", value) }

  }
}

object Review : QType {
  val rating: Stub<Int> by lazy { stub<Int>() }

  val user: InitStub<User> by lazy { typeStub<User>() }

  val text: Stub<String> by lazy { stub<String>() }

  val time_created: Stub<String> by lazy { stub<String>() }

  val url: Stub<String> by lazy { stub<String>() }
}

object Reviews : QType {
  val review: InitStub<Review> by lazy { typeStub<Review>() }

  val total: Stub<Int> by lazy { stub<Int>() }
}

object User : QType {
  val image_url: Stub<String> by lazy { stub<String>() }

  val name: Stub<String> by lazy { stub<String>() }
}

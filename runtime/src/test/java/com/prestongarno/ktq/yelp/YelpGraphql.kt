@file:Suppress("unused")

package com.prestongarno.ktq.yelp

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.ListConfig
import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.ListStub
import com.prestongarno.ktq.QConfigStub
import com.prestongarno.ktq.QInput
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.QSchemaType.QScalarList
import com.prestongarno.ktq.QSchemaType.QType
import com.prestongarno.ktq.QSchemaType.QTypeList
import com.prestongarno.ktq.QTypeConfigStub
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.TypeArgBuilder
import com.prestongarno.ktq.TypeListArgBuilder

object Business : QSchemaType {
  val name: Stub<String> by QScalar.stub()

  val id: Stub<String> by QScalar.stub()

  val is_claimed: Stub<Boolean> by QScalar.stub()

  val is_closed: Stub<Boolean> by QScalar.stub()

  val url: Stub<String> by QScalar.stub()

  val phone: Stub<String> by QScalar.stub()

  val display_phone: Stub<String> by QScalar.stub()

  val review_count: Stub<Int> by QScalar.stub()

  val categories: ListInitStub<Category> by QTypeList.stub()

  val rating: Stub<Float> by QScalar.stub()

  val price: Stub<String> by QScalar.stub()

  val location: InitStub<Location> by QType.stub()

  val coordinates: InitStub<Coordinates> by QType.stub()

  val photos: ListStub<String> by QScalarList.stub()

  val hours: ListInitStub<Hours> by QTypeList.stub()

  val reviews: ListInitStub<Review> by QTypeList.stub()
}

object Businesses : QSchemaType {
  val business: ListInitStub<Business> by QTypeList.stub()

  val total: Stub<Int> by QScalar.stub()
}

object Category : QSchemaType {
  val title: Stub<String> by QScalar.stub()

  val alias: Stub<String> by QScalar.stub()
}

object Coordinates : QSchemaType {
  val latitude: Stub<Float> by QScalar.stub()

  val longitude: Stub<Float> by QScalar.stub()
}

object Hours : QSchemaType {
  val hours_type: Stub<String> by QScalar.stub()

  val open: ListInitStub<OpenHours> by QTypeList.stub()

  val is_open_now: Stub<Boolean> by QScalar.stub()
}

object Location : QSchemaType {
  val address1: Stub<String> by QScalar.stub()

  val address2: Stub<String> by QScalar.stub()

  val address3: Stub<String> by QScalar.stub()

  val city: Stub<String> by QScalar.stub()

  val state: Stub<String> by QScalar.stub()

  val zip_code: Stub<String> by QScalar.stub()

  val country: Stub<String> by QScalar.stub()

  val formatted_address: Stub<String> by QScalar.stub()
}

object OpenHours : QSchemaType {
  val is_overnight: Stub<Boolean> by QScalar.stub()

  val end: Stub<String> by QScalar.stub()

  val start: Stub<String> by QScalar.stub()

  val day: Stub<Int> by QScalar.stub()
}

/**
 * Represents the lack of understanding the developer team at yelp had when
 * they used a type system in order to spam strings by tens as args to fields... */
object Query : QSchemaType {
  val business: QTypeConfigStub<Business, BusinessArgs> by QType.configStub { BusinessArgs(it) }

  val business_match_best: QTypeConfigStub<Business, Business_match_bestArgs> by QType.configStub { Business_match_bestArgs(it) }

  val business_match_lookup: QTypeConfigStub<Businesses, Business_match_lookupArgs> by QType.configStub { Business_match_lookupArgs(it) }

  val reviews: QTypeConfigStub<Reviews, ReviewsArgs> by QType.configStub { ReviewsArgs(it) }

  val phone_search: QTypeConfigStub<Businesses, Phone_searchArgs> by QType.configStub { Phone_searchArgs(it) }

  val search: QTypeConfigStub<Businesses, SearchArgs> by QType.configStub { SearchArgs(it) }

  class BusinessArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun id(value: String): BusinessArgs = apply { addArg("id", value) }

  }

  class Business_match_bestArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
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

  class Business_match_lookupArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
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

  class ReviewsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun business(value: String): ReviewsArgs = apply { addArg("business", value) }


    fun locale(value: String): ReviewsArgs = apply { addArg("locale", value) }

  }

  class Phone_searchArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun phone(value: String): Phone_searchArgs = apply { addArg("phone", value) }

  }

  class SearchArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
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

/**
 * This type represents a review. You know, like in that one south park episode? */
object Review : QSchemaType {
  /**
   *  The rating that someone gave, ranging 1->5, with 5 being the greatest rating */
  val rating: Stub<Int> by QScalar.stub()

  /**
   * The user that left the rating */
  val user: InitStub<User> by QType.stub()

  /**
   * The body
   * of the review
   *  usually somewhat longer
   * than single comment lines ;) */
  val text: Stub<String> by QScalar.stub()

  /**
   * The time it was posted */
  val time_created: Stub<String> by QScalar.stub()

  /**
   * hard link to the review */
  val url: Stub<String> by QScalar.stub()
}

object Reviews : QSchemaType {
  val review: ListInitStub<Review> by QTypeList.stub()

  val total: Stub<Int> by QScalar.stub()
}

object User : QSchemaType {
  val image_url: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()
}

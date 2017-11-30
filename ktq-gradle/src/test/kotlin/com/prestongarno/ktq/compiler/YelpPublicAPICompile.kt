package com.prestongarno.ktq.compiler

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.compiler.ParameterQualification.Companion.nullability
import com.prestongarno.ktq.compiler.KTypeSubject.Companion.argumentsMatching
import com.prestongarno.ktq.compiler.KTypeSubject.Companion.reifiedArgumentsMatching
import com.prestongarno.ktq.stubs.BooleanDelegate
import com.prestongarno.ktq.stubs.FloatDelegate
import com.prestongarno.ktq.stubs.IntDelegate
import com.prestongarno.ktq.stubs.StringArrayDelegate
import com.prestongarno.ktq.stubs.StringDelegate
import com.prestongarno.ktq.stubs.TypeListStub
import com.prestongarno.ktq.stubs.TypeStub
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for compiled Yelp GraphQL schema
 *
 * Will primarily test this object from the Yelp GraphQL Schema:
 *
 * type Business {
 *    name: String
 *    id: String
 *    is_claimed: Boolean
 *    is_closed: Boolean
 *    url: String
 *    phone: String
 *    display_phone: String
 *    review_count: Int
 *    categories: [Category]
 *    rating: Float
 *    price: String
 *    location: Location
 *    coordinates: Coordinates
 *    photos: [String]
 *    hours: [Hours]
 *    reviews: [Review]
 * }
 */
class YelpPublicAPICompile {


  companion object : JavacTest() {
    /** classloader for objects & classes from the schema */
    val loader: KtqCompileWrapper = this::class.java
      .classLoader
      .getResourceAsStream("yelp.graphqls")
      .reader()
      .readLines()
      .joinToString("\n") { it }.apply {
        isNotEmpty() eq true
      }.let {
        jvmCompileAndLoad(
            schema = it,
            packageName = "com.yelp"
            , printer = System.out)
        }
      }

  @Test fun `yelp graphql schema compiles to ktq jvm objects`() =
      loader notEq null

  @Test fun `yelp graphql "Business" primitive fields match ktq type`() = loader.loadClass("com.yelp.Business") {

    kprop("name") { nameProp ->
      nameProp requireReturns StringDelegate.Query::class
    }

    kprop("is_claimed") {
      it requireReturns BooleanDelegate.Query::class
    }

    kprop("review_count") { intField ->
      intField requireReturns IntDelegate.Query::class
    }

    kprop("rating") { floatProp ->
      floatProp requireReturns FloatDelegate.Query::class
    }

  }.ignore()

  @Test fun `graphql string array field returns the correct type`() = loader.loadClass("com.yelp.Business") {
    kprop("photos") { stringArray ->
      stringArray requireReturns StringArrayDelegate.Query::class
    }
  }.ignore()

  @Test fun `graphql object type list field return type`() = loader.loadClass("com.yelp.Business") {

    val hoursClass = loader.loadClass("com.yelp.Hours")
    hoursClass directlyImplements QType::class
    val categoryClass = loader.loadClass("com.yelp.Category")
    categoryClass directlyImplements QType::class

    kprop("hours") {
      it requireReturns TypeListStub.Query::class
      it.returnType mustHave reifiedArgumentsMatching(hoursClass)
    }

    kprop("categories") {
      it requireReturns TypeListStub.Query::class
      it.returnType mustHave reifiedArgumentsMatching(categoryClass)
    }

  }.ignore()

  @Test fun `argument builder class with non-null parameter is non-nullable constructor parameter`() {
    val argClass = loader.loadClass("com.yelp.Query\$BusinessArgs")
    argClass directlyImplements ArgBuilder::class
    argClass without nullability constructorParametersContain "id"
  }

  /**
   * Yelp GraphQL schema declaration (yes it's ugly):
   * type Query {
   *
   *     business(id: String!): Business
   *
   *     business_match_best(
   *              name: String!,
   *              address1: String,
   *              address2: String,
   *              address3: String,
   *              city: String!,
   *              state: String!,
   *              country: String!,
   *              latitude: Float,
   *              longitude: Float,
   *              phone: String,
   *              postal_code: String
   *     ): Business
   *
   *     business_match_lookup(name: String!, address1: String, address2: String, address3: String, city: String!, state: String!, country: String!, latitude: Float, longitude: Float, phone: String, postal_code: String): Businesses
   *     reviews(business: String, locale: String): Reviews
   *     phone_search(phone: String): Businesses
   *     search(term: String, location: String, country: String, offset: Int, limit: Int, sort_by: String, locale: String, longitude: Float, latitude: Float, categories: String, open_now: Boolean, open_at: Int, price: String, attributes: String, radius: Float): Businesses
   * }
   */
  @Test fun `graphql field with arguments correct return type`() = loader.loadClass("com.yelp.Query") {

    val businessArgsClass = loader.loadClass("com.yelp.Query\$BusinessArgs")
    businessArgsClass directlyImplements ArgBuilder::class

    val businessClass = loader.loadClass("com.yelp.Business")
    businessClass directlyImplements QType::class

    kprop("business") {
      it requireReturns TypeStub.ConfigurableQuery::class
      it.returnType mustHave argumentsMatching("com.yelp.Business", "com.yelp.Query.BusinessArgs")
      it.returnType mustHave reifiedArgumentsMatching(businessClass, businessArgsClass)
    }

  }.ignore()

  /**
   * Query field spec:
   *     business_match_best(
   *              name: String!,
   *              address1: String,
   *              address2: String,
   *              address3: String,
   *              city: String!,
   *              state: String!,
   *              country: String!,
   *              latitude: Float,
   *              longitude: Float,
   *              phone: String,
   *              postal_code: String
   *     ): Business
   */
  @Test fun `graphql arguments on field is enforced in argbuilder`() = loader.loadClass("com.yelp.Query") {

    val businessBestMatchArgs = loader.loadClass("com.yelp.Query\$Business_match_bestArgs")
    businessBestMatchArgs directlyImplements ArgBuilder::class
    businessBestMatchArgs without nullability constructorParametersMatchExactly mapOf(
        "name" to String::class,
        "city" to String::class,
        "state" to String::class,
        "country" to String::class
    )

    businessBestMatchArgs.apply {
      kprop("latitude") requireReturns Float::class
      kprop("longitude") requireReturns Float::class
      kprop("phone") requireReturns String::class
      kprop("postal_code") requireReturns String::class
      kprop("address1") requireReturns String::class
      kprop("address2") requireReturns String::class
      kprop("address3") requireReturns String::class
    }

    val businessClass = loader.loadClass("com.yelp.Business")
    businessClass directlyImplements QType::class

    kprop("business_match_best") {
      it requireReturns TypeStub.ConfigurableQuery::class
      it.returnType mustHave reifiedArgumentsMatching(businessClass, businessBestMatchArgs)
    }

  }.ignore()
}


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

package com.prestongarno.kotlinq.core.formatting

import com.google.common.truth.Truth.assertThat
import com.prestongarno.kotlinq.core.QInterface
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType.*
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.primitives.eq
import com.prestongarno.kotlinq.core.stubs.IntDelegate
import com.prestongarno.kotlinq.core.stubs.StringDelegate
import com.prestongarno.kotlinq.core.stubs.TypeListStub
import com.prestongarno.kotlinq.core.stubs.TypeStub
import com.yelp.graphql.Business
import com.yelp.graphql.Location
import com.yelp.graphql.Review
import com.yelp.graphql.User
import org.junit.Test

class QmodelToGraphQl {

  open class BusinessModel : QModel<Business>(Business) {

    val name by model.name

    val location by model.location.query(::LocationModel)

  }

  open class LocationModel : QModel<Location>(Location) {

    val address1 by model.address1

    val city by model.city

    val state by model.state

    val country by model.country

  }

  open class ReviewModel : QModel<Review>(Review) {
    val rating by model.rating

    val text by model.text

    val timeCreated by model.time_created

    val user by model.user.query(::UserModel)
  }

  open class UserModel : QModel<User>(User) {
    val name by model.name
    val image_url by model.image_url
  }


  @Test fun `graphql query tographql formatted correctly`() {

    val expected = """
      |{
      |  name
      |  location {
      |    address1
      |    city
      |    state
      |    country
      |  }
      |}
      """.trimMargin("|")

    val actual = BusinessModel().toGraphql(pretty = true)

    actual eq expected

  }

  @Test fun `graphql query nested type queries`() {

    class TestModel : BusinessModel() {

      val reviews by model.reviews.query(::ReviewModel)

    }

    val expected = """
      |{
      |  name
      |  location {
      |    address1
      |    city
      |    state
      |    country
      |  }
      |  reviews {
      |    rating
      |    text
      |    time_created
      |    user {
      |      name
      |      image_url
      |    }
      |  }
      |}
      """.trimMargin("|")

    val testModel = TestModel()
    val actual = testModel.toGraphql(pretty = true)

    actual eq expected
  }

  // mock schema interface type (no local interfaces)
  interface IMock : QInterface, QType {

    val refId: StringDelegate.Query

    val ttl: IntDelegate.Query

    val businessList: TypeListStub.Query<Business>

  }

  interface MockContext : QInterface, QType {
    val mockReference: TypeStub.Query<Mock>
  }


  // schema concrete subclass of IMock
  object Mock : IMock {

    override val refId: StringDelegate.Query by QScalar.String.stub()

    override val ttl: IntDelegate.Query by QScalar.Int.stub()

    override val businessList: TypeListStub.Query<Business>  by QTypes.List.stub()

  }

  object Dock : IMock, MockContext {

    val length by QScalar.Int.stub()

    override val refId: StringDelegate.Query by QScalar.String.stub()

    override val ttl: IntDelegate.Query by QScalar.Int.stub()

    override val businessList: TypeListStub.Query<Business>  by QTypes.List.stub()

    override val mockReference: TypeStub.Query<Mock> by QTypes.stub()

  }

  // provide context for the interface query
  object MockQuery : QType {
    val queryImock by QInterfaces.stub<IMock>()
  }

  @Test fun interfaceFragmentToGraphQLFormatting() {

    class MockModel : QModel<Mock>(Mock) {
      val referenceId by model.refId
      val ttl by model.ttl
      val businesses by model.businessList.query(::BusinessModel)
    }

    val mockQueryImpl = object : QModel<MockQuery>(MockQuery) {
      val queryResult by model.queryImock {
        on(::MockModel)
      }
    }

    assertThat(mockQueryImpl.toGraphql(pretty = true)).isEqualTo("""
      |{
      |  queryImock {
      |    ... on Mock {
      |      refId
      |      ttl
      |      businessList {
      |        name
      |        location {
      |          address1
      |          city
      |          state
      |          country
      |        }
      |      }
      |    }
      |  }
      |}
      """.trimMargin("|"))
  }

  @Test fun fragmentingMultipleImplementationsToGraphQL() {

    class MockModel : QModel<Mock>(Mock) {

      val referenceId by model.refId

      val ttl by model.ttl

      val businesses by model.businessList.query(::BusinessModel)

    }

    class DockModel : QModel<Dock>(Dock) {

      val length by model.length

      val referenceId by model.refId

      val ttl by model.ttl

      val businesses by model.businessList.query(::BusinessModel)

      val mockRef by model.mockReference.query(::MockModel)

    }

    val mockQueryImpl = object : QModel<MockQuery>(MockQuery) {
      val queryResult by model.queryImock {
        on(::MockModel)
        on(::DockModel)
        on(::DockModel)
        on(::MockModel)
        on(::DockModel)
        on(::MockModel)
        on(::DockModel)
        on(::MockModel)
        on(::DockModel)
      }
    }

    assertThat(mockQueryImpl.toGraphql(pretty = true)).isEqualTo("""
      |{
      |  queryImock {
      |    ... on Mock {
      |      refId
      |      ttl
      |      businessList {
      |        name
      |        location {
      |          address1
      |          city
      |          state
      |          country
      |        }
      |      }
      |    }
      |    ... on Dock {
      |      length
      |      refId
      |      ttl
      |      businessList {
      |        name
      |        location {
      |          address1
      |          city
      |          state
      |          country
      |        }
      |      }
      |      mockReference {
      |        refId
      |        ttl
      |        businessList {
      |          name
      |          location {
      |            address1
      |            city
      |            state
      |            country
      |          }
      |        }
      |      }
      |    }
      |  }
      |}
      """.trimMargin("|"))
  }
}


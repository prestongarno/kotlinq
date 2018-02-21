***a Kotlin GraphQL client: type-safe DSL generation & runtime library***
-----------------------------

[ ![Download](https://api.bintray.com/packages/prestongarno/kotlinq/kotlinq-gradle/images/download.svg?version=0.3.0) ](https://bintray.com/prestongarno/kotlinq/kotlinq-gradle/0.3.0/link)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-client)
[![Build Status](https://travis-ci.org/prestongarno/kotlinq.svg?branch=master)](https://travis-ci.org/prestongarno/kotlinq)


## About

* **type-safe DSLs** for querying and mutating your data
* **dynamic queries/mutations** evaluated at runtime
* **custom scalar deserialization** to any native type
* **100% native** code - zero config files, zero old-school DSLs

The [ gradle plugin ](kotlinq-gradle/README.md) generates an equivalent kotlin type hierarchy which 
lets you auto-complete your way to safe, reliable queries and mutations

## Un-typed GraphQL query DSL (version 0.4.0) (new)

If you would like to quickly fetch from a GraphQL server without the build plugin and defining classes, 
version 0.4.0 will fully support [**ad-hoc, untyped**](https://github.com/prestongarno/kotlinq/blob/query-dsl/query-dsl/src/main/kotlin/org/kotlinq/dsl/extensions/FreePropertyExtensionScope.kt) but natively expressed queries and mutations!

Example:

```
    val starWarsQuery = query {
      "search"("text" to "r2d2")..{
        on("Human") {
          "name"(string)
          "id"(string)
          "height"(!float, "unit" to LengthUnit.METER)
          "friendsConnection"("first" to 10) on def("FriendConnection") {
            "totalCount"(integer)
            "friends"..{
              on("Human") {
                "name"(string)
                "id"(string)
              }
            }
          }
        }
      }
    }

    println(starWarsQuery.toGraphQl(
        pretty = true,
        inlineFragments = true))

```

will print:


```
{
  search(text: "han solo") {
    __typename
    ... on Human{
      name
      id
      height(unit: "METER")
      friendsConnection(first: 10) {
        totalCount
        friends {
          __typename
          ... on Human{
            name
            id
          }
        }
      }
    }
  }
}
```

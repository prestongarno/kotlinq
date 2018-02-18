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

### Known Issues

Basically everything. This was neat idea at first but requires a complete overhaul to be even remotely maintainable going forward. Do not use

## Un-typed GraphQL query DSL (version 0.4.0) (new)

If you would like to run a query in GraphQL without the complexity of the build plugin and defining classes, 
version 0.4.0 will fully support **ad-hoc, untyped** but natively expressed queries and mutations!

Current working example:

```
fun greet(worldName: String = "Earth", message: String = "Hello") =

    query {
      -"greet"("name" to worldName, "message" to message) {
        !"population"::integer
        "countries"("first" to 100) listOf {
          !"name"::string
          "subRegions" spread {
            on("State") { 
              "capitol"(cityDefinition())
            }
            on(cityDefinition())
          }
        }
      }
    }


fun cityDefinition() = typeDefinition("City") {
  !"name"::string
  -"mayor" { 
    /* "Person" type selection-set here etc... */ 
  } 
  !"population"::int
}

```


The DSL design has not been finalized, but an ideal solution will:

1. Provide ***readable*** GraphQL queries with minimal change to a standard text query
2. Provide JSON response ***verification*** based on the query structure, including *null safety* for kotlin compatibility
3. Support some form of mapping between JSON and native objects
4. Sacrifice conventional native syntax for more explicit queries (e.g. the operator overloading "Not" symbol in the example for query nullability expression)

## Github query example

```
class ViewerQuery : Model<Query>(Query) {

  val me by model.viewer.querying(::UserModel)

}

class UserModel : Model<User>(User) {
  val name by model.name
}

val myFirstQuery = GraphQL.initialize("https://api.github.com/graphql").apply {
  authorization = TokenAuth(System.getenv("GITHUB_OAUTH_TOKEN"))
}.query(::ViewerQuery)
    .send()

println("Hello ${myFirstQuery.me.name}")
```

The last code block will print "Hello, \<your name here\>"


***a Kotlin GraphQL client: type-safe DSL generation & runtime library***
-----------------------------

[ ![Download](https://api.bintray.com/packages/prestongarno/kotlinq/kotlinq-gradle/images/download.svg?version=0.3.0-RC1) ](https://bintray.com/prestongarno/kotlinq/kotlinq-gradle/0.3.0-RC1/link)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-client)
[![Build Status](https://travis-ci.org/prestongarno/ktq.svg?branch=master)](https://travis-ci.org/prestongarno/ktq)


## About

* 
* **dynamic queries/mutations** evaluated at runtime
* **custom scalar deserialization** to any native type
* **100% native** code - zero config files, zero old-school DSLs
* **No boilerplate** adapter classes or intermediate objects

## Documentation

The documentation is moving (slowly) to a dedicated site. [Check it out](https://prestongarno.github.io/kotlinq/)

## Modules

* `kotlinq-core`: Runtime API
* `kotlinq-gradle`: Gradle code-generating compiler
* `kotlinq-http`: HTTP Utilities using [http4k](http://http4k.org) as a dependency


The [ gradle plugin ](kotlinq-gradle/README.md) generates an equivalent kotlin type hierarchy which 
lets you auto-complete your way to safe, reliable queries and mutations

## Example

An example below queries the Yelp Graphql API. 
Note that while field types are specified, they are not necessary and can be inferred:

    class BusinessNodeQuery(searchTerm: String) : QModel(Query) {

      val result: List<BusinessNodes> by model.search(::BusinessesNodes) {
          config {
            term = searchTerm
            limit = 10
          }
      }
    }

    class BusinessesNodes : QModel(Businesses) {
      val resultCount: Int by model.total
      val resultsNodes: List<BusinessQuery> by model.business.query(::BusinessQuery)
    }

    class BusinessQuery : QModel(Business) {
    
      val name: String by model.name {
        default = "Unknown"
      }
      
      val phoneNumber: Int by model.display_phone
      
      val directUrl: String by model.url
    }


When initializing a `BusinessQuery` calling the `.toGraphql()` results in a valid graphql query as a String:

E.g. `BusinessQuery("foobar").toGraphql()` returns:

    {
     search(limit: 10,
        term: "foobar"){
       total,
       business {
         name,
         display_phone,
         url 
        }
      }
    }

For a complete guide on how to use all other graphql types such as Unions and Nullable fields, see the docs.

### How to execute a query or mutation:

This is an example for getting your github name:

    class ViewerQuery : QModel<Query>(Query) {
      val me by model.viewer.querying { UserModel() }
    }
    
    class UserModel : QModel<User>(User) {
      val name by model.name
    }

    val myFirstQuery = GraphQL.initialize("https://api.github.com/graphql").apply {
      authorization = TokenAuth(System.getenv("GITHUB_KEY"))
    }.query { ViewerQuery() }
        .send()
    println("Hello ${myFirstQuery.me.name}")


The last code block will print "Hello, \<your name here\>"

### Adding dependency from JCenter

__\<\< Note: version 0.3 is backwards-incompatible. It isn't a final release \\\>\>__


To use in a project, add the core & http libraries to a gradle buildscript:

      api 'com.prestongarno.kotlinq:kotlinq-core:0.3.0-RC1'
      api 'com.prestongarno.kotlinq:kotlinq-http:0.3.0-RC1'

Make sure to include the [ gradle plugin ](ktq-gradle) and read
 the gradle syntax for configuring compilation of graphql SDL as kotlin classes. 
 Add this to project buildscript dependencies block:

      classpath 'com.prestongarno.kotlinq:kotlinq-gradle:0.3.0-RC1'

And apply the plugin:

      plugins {
        id 'com.prestongarno.kotlin1' version '0.3.0-RC1'
      }



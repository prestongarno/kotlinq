*__a kotlin client for type-safe interaction with GraphQL servers__*
-----------------------------

[![Build Status](https://travis-ci.org/prestongarno/ktq.svg?branch=master)](https://travis-ci.org/prestongarno/ktq)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-client)
 [ ![jcenter](https://api.bintray.com/packages/prestongarno/ktq/ktq-client/images/download.svg?version=0.2) ](https://bintray.com/prestongarno/ktq/ktq-client/0.2/link)




### Adding dependency from Central or JCenter

To use in a project, add the dependency to a gradle buildscript:

      compile 'com.prestongarno.ktq:ktq-client:0.2'
      
Make sure to include the [ gradle plugin ](https://github.com/prestongarno/ktq-gradle) and read
 the gradle syntax for configuring compilation of graphql schema IDL as kotlin classes. 
 Add this to project buildscript dependencies block:

      classpath 'com.prestongarno.ktq:ktq-gradle:0.2'

And apply the plugin:

      plugins {
        id 'com.prestongarno.ktq' version 0.2
      }
      
### About

Stands for KoTlin Query (language). This is a library which supports concise, type-safe models for 
queries and mutations against a GraphQl schema. 

The [ gradle plugin ](https://github.com/prestongarno/ktq-gradle) generates an equivalent kotlin type hierarchy which is used to create and execute queries
and mutations without ever leaving native code.

For an example of how to build models, see the example below created for the Yelp Graphql API. 
Note that while field types are specified, they are not necessary and can be inferred by the properties
in the `model` instance which a concrete query/mutation class delegates its properties to.

    class BusinessQuery(searchTerm: String) : QModel(Query) {
    
      val result: List<BusinessNodesModel> by model.search
          .config()
              .term(searchTerm)
              .limit(10)
              .build { BusinessesNodesModel() }
          
    }

    class BusinessesNodesModel : QModel(Businesses) {
      val resultCount:   Int                    by model.total
      val resultsNodes:  List<SimpleBusiness>   by model.business.init { SimpleBusiness() }
    }

    class SimpleBusiness : QModel(Business) {
      val name:         String  by model.name
      val phoneNumber:  Int     by model.display_phone
      val directUrl:    String  by model.url
    }
    

When initializing a `BusinessQuery` calling the `.toGraphql()` results in a valid graphql query as a String:

E.g. `BusinessQuery("foobar").toGraphql()` returns (formatted by default):

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
    
For a complete guide on how to use all other graphql types such as Unions and Nullable fields,
check out the wiki.

### How to execute a query or mutation:

This isn't supported in the current release, but the package `com.prestongarno.ktq.http` package 
adds a dependency on [http4k](www.http4k.org) and supports end-to-end mutations and queries out of the box. Just 
describe your model, and execute. This is an example for getting your github name (not shown: compiled github IDL schema):

    class ViewerQuery : QModel<Query>(Query) {
      val me by model.viewer.init { UserModel() }
    }
    
    class UserModel : QModel<User>(User) {
      val name by model.name
    }

    GraphQL.initialize("https://api.github.com/graphql").apply {
      authorization = TokenAuth(System.getenv("GITHUB_KEY"))
    }.query { ViewerQuery() }
        .onSuccess { println("Hello, ${it.me.name}") }
        .execute()
        
        
The last code block will print "Hello, \<your name here\>"

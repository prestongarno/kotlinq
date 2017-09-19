*__a kotlin client for GraphQL schema definitions.__*
-----------------------------

[![Build Status](https://travis-ci.org/prestongarno/ktq.svg?branch=master)](https://travis-ci.org/prestongarno/ktq)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.prestongarno.ktq/ktq-client)
 [ ![jcenter](https://api.bintray.com/packages/prestongarno/ktq/ktq-client/images/download.svg?version=0.1) ](https://bintray.com/prestongarno/ktq/ktq-client/0.1/link)




### Add typedValueFrom Central & JCenter

To use in a project, add the dependency to a gradle buildscript:

      compile 'com.prestongarno.ktq:ktq-client:0.1'
      
Make sure to include the [ gradle plugin ](https://github.com/prestongarno/ktq-gradle) and read
 the gradle syntax for configuring compilation of graphql schema IDL as kotlin classes. 
 Add this to project buildscript dependencies block:

      classpath 'com.prestongarno.ktq:ktq-gradle:0.1'

And apply the plugin:

      plugins {
        id 'com.prestongarno.ktq' version 0.1
      }
      
### About

Stands for KoTlin Query (language). This is a library which supports concise, type-safe models for 
queries and mutations against a GraphQl schema. 

The [ gradle plugin ](https://github.com/prestongarno/ktq-gradle) generates an equivalent kotlin type hierarchy which is used to create and execute queries
and mutations without ever leaving native code.

For an example of how to build models, see the example below created for the Yelp Graphql API. 
Note that while field types are specified, they are not necessary and can be inferred by the properties
in the `model` instance which a concrete query/mutation class delegates its properties to.

    class BusinessQuery(searchTerm: String) : QModel(Query::class) {
    
      val result: List<BusinessNodesModel> by model.search
          .config()
              .term(searchTerm)
              .limit(10)
              .build { BusinessesNodesModel() }
          
    }

    class BusinessesNodesModel : QModel(Businesses::class) {
      val resultCount:   Int                    by model.total
      val resultsNodes:  List<SimpleBusiness>   by model.business.init { SimpleBusiness() }
    }

    class SimpleBusiness : QModel(Business::class) {
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
    
What's unique about this API is that the delegates serve to both configure queries/inputs as a contract for the 
resulting value of the delegated property, while also ensuring strong typing across the board. The accompanying
configuration (i.e. input arguments for a graphql field) class enforces any required arguments and supports the 
builder pattern for configuring your query for the delegated property in one line.

For a complete guide on how to use all other graphql types such as Unions and Nullable fields,
check out the wiki.

### How to execute a query or mutation:

This isn't currently supported, but pluggable HTTP modules to assist in executing queries 
using existing tools such as OkHTTP and Fuel and mapping to your objects are currently under development for a 1.0 release


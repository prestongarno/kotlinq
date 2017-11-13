## <span style="color:#f442c2"> How to objectValue **ktq** </span>

To use in a project, add the dependency to a gradle buildscript:

      compile 'com.prestongarno.ktq:ktq-client:0.2'
      
Make sure to include the [ gradle plugin ](https://github.com/prestongarno/ktq-gradle) and read
the gradle syntax for configuring compilation of graphql schema SDL as kotlin classes. 
 Add this to project buildscript dependencies block:

      classpath 'com.prestongarno.ktq:ktq-gradle:0.2'

And apply the plugin:

      plugins {
        id 'com.prestongarno.ktq' version 0.2
      }
      
## <span style="color:#f442c2">About</span>

Stands for KoTlin Query (language). This is a library which supports concise, type-safe models for 
queries and mutations against a GraphQl schema. 

The [ gradle plugin ](https://github.com/prestongarno/ktq-gradle) generates an equivalent kotlin type hierarchy which is used to create and execute queries
and mutations without ever leaving native code.

For an example of how to build models, see the example below created for the Yelp Graphql API. 
Note that while field types are specified, they are not necessary and can be inferred by the properties
in the `model` instance which a concrete query/mutation class delegates its properties to.

    class BusinessQuery(searchTerm: String) : QModel(Query) {
    
      val result: List<BusinessNodesModel> by model.search
          .config {
            term = searchTerm
            limit = 10
          }.querying { BusinessesNodesModel() }
          
    }

    class BusinessesNodesModel : QModel(Businesses) {
      val resultCount:   Int                    by model.total
      val resultsNodes:  List<SimpleBusiness>   by model.business.querying { SimpleBusiness() }
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

## <span style="color:#f442c2">How it works</span>

The above is only an example of the syntax *ktq* supports for describing a GraphQL type system and using it. 
For a more detailed explanation of the abstractions which make up the design of this library, go on to the next section.

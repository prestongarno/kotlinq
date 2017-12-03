## <span style="color:#f442c2"> How to objectValue **kotlinq** </span>

To use in a project, add the dependency to a gradle buildscript:

      compile 'com.prestongarno.kotlinq:kotlinq-client:0.2'
      
Make sure to include the [ gradle plugin ](https://github.com/prestongarno/kotlinq/kotlinq-gradle) and read
the gradle syntax for configuring compilation of graphql schema SDL as kotlin classes. 
 Add this to project buildscript dependencies block:

      classpath 'com.prestongarno.kotlinq:kotlinq-gradle:0.2'

And apply the plugin:

      plugins {
        id 'com.prestongarno.kotlinq' version 0.2
      }
      
## <span style="color:#f442c2">About</span>

Stands for KoTlin Query (language). This is a library which supports concise, type-safe models for 
queries and mutations against a GraphQl schema. 

The [ gradle plugin ](https://github.com/prestongarno/kotlinq/kotlinq-gradle) generates an equivalent kotlin type hierarchy which is used to create and execute queries
and mutations without ever leaving native code.

For an example of how to build models, see the example below created for the Yelp Graphql API. 

    class BusinessQuery(searchTerm: String) : QModel(Query) {
    
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
      val name: String by model.name
      val phoneNumber: Int by model.display_phone
      val directUrl: String by model.url
    }
    

Note that while field types are specified, they are not necessary and can be inferred by the properties
in the `model` instance which a concrete query/mutation class delegates its properties to.

When initializing a `BusinessQuery` calling the `.toGraphql()` results in a valid graphql query as a String:

E.g. `BusinessQuery("foobar").toGraphql()` returns (formatted by default):

    {
     search(limit: 10, term: "foobar"){
       total,
       business {
         name,
         display_phone,
         url 
        }
      }
    }

!!! warning
    <font size="+0.4">Delegated properties should be considered *uninitialized* until resolved!
    Call `model.resolved` (`Boolean` property) to figure this out. 
    Otherwise, all primitive/scalar types will be set to their default value, and all nullable fields will be null.</font>

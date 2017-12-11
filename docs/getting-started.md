## <span style="color:#f442c2">How to get started</span>

To use in a project, add the dependency to a gradle buildscript:

      compile 'com.prestongarno.kotlinq:kotlinq-client:0.3.0'

Make sure to include the [ gradle plugin ](https://github.com/prestongarno/kotlinq/kotlinq-gradle) and read
the gradle syntax for configuring compilation of graphql schema SDL as kotlin classes. 
 Add this to project buildscript dependencies block:

      classpath 'com.prestongarno.kotlinq:kotlinq-gradle:0.3.0'

And apply the plugin:

      plugins {
        id 'com.prestongarno.kotlinq' version 0.3.0
      }

## <span style="color:#f442c2">Patterns used by kotlinq</span>

The three key concepts which must be kept in mind when using kotlinq are:

1. ****Static Schema***: The Schema is represented as static objects.
 These are all [delegate providers][3],
 which hold information about the schema field it represents for the next point
2. ***Composition of Models through delegates***: When you want to model a type from the schema, it does not matter what you name your properties.
 **All you need to do is delegate to a property of a schema singleton/object***.
3. ***Nullability of fields***: Fields are not-null for convenience, but you should never access an object without first checking if the property `QModel.isResolved` returns true.
 Otherwise, accessing a property most likely ***will*** throw an exception (primitive/scalar ones, at least)


## <span style="color:#f442c2">Example nested query</span>

For an example of how to build models, see the example below created for the Yelp Graphql API. 

    class BusinessQuery(searchTerm: String) : QModel(Query) {
    
      val result: List<BusinessModel> by model.search.querying(::BusinessesModel) {
          config {
            term = searchTerm
            limit = 10
          }
      }
    }

    class BusinessModel : QModel(Business) {
      val name: String by model.name
      val phoneNumber: Int by model.display_phone
      val directUrl: String by model.url
    }


Note that while field types are specified, they are not necessary and can be inferred by the properties
in the `model` instance which a concrete query/mutation class delegates its properties to.

When initializing a `BusinessQuery("foobar").toGraphql(pretty = true)` results in a valid graphql query as a String:

    {
     search(limit: 10, term: "foobar"){
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


  [2]: https://kotlinlang.org/docs/reference/delegation.html
  [3]: https://kotlinlang.org/docs/reference/delegated-properties.html#providing-a-delegate-since-11


# ktq<sup>*</sup>

 [ ![Download](https://api.bintray.com/packages/prestongarno/ktq/ktq-client/images/download.svg?version=0.1) ](https://bintray.com/prestongarno/ktq/ktq-client/0.1/link)

An experimental kotlin type generator for GraphQL schema definitions and runtime library for interacting with endpoints

Supports concise, type-safe queries and models. Compiled and tested against open API endpoints such as Github and Yelp. An example model written from the yelp test package (`com.prestongarno.ktq.yelp`):

```
  class BusinessQuery(searchTerm: String) : QModel<Query>() {
    val result: List<BusinessNodesModel> by model.search.config()
        .term(searchTerm)
        .limit(10)
        .build { BusinessesNodesModel() }
  }

  class BusinessesNodesModel : QModel<Businesses>() {
    val resultCount: Int by model.total
    val resultsNodes: List<BusinessBasic> by model.business
        .init { BusinessBasic() }
  }

  class BusinessBasic : QModel<Business>() {
    val name: String by model.name
    val phoneNumber: Int by model.display_phone
    val directUrl: String by model.url
  }
  
```

When initializing a `BusinessQuery` calling the `.toGraphql()` results in a valid graphql query as a String:

E.g. `BusinessQuery("foobar").toGraphql()` returns (formatted by default):

```
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
```

What's even cooler is that the delegates serve to both configure queries/inputs, and also ensure strong typing across the board. More to come soon:)

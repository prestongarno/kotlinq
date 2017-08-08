
# ktq

A kotlin type generator and runtime library for GraphQL schema definitions

The idea behind this is the concept that if a project extensively uses a graphql API, why not forget client-side ORM/model errors by essentially wrapping the entire graphql API and including it in the project? Kotlin's delegates and other constructs are used to allow for expression of a graphql "selection" of fields from a database type by exposing the generated supertype property in a class.

Any input arguments declared in the schema are represented as builder classes, and allow for a flexible combination for querying/mutation. A field with arguments can be expressed like so:

```
    class QueryFooBar : User() {
    
        public override val name by string()
        
        public override val bio by string()
        
        public override val createdAt by field { DateTimeConvert() }
        
        public override val id by scalarMapper { it }
        

        public override val repositories by RepositoriesArgs()
                .affiliations(listOf( OWNER, COLLABORATOR ))
                .orderBy(object : RepositoryOrder() {
                    override val field by exact(CREATED_AT)
                    override val direction by exact(ASC)
                })
                .first(100)
                .privacy(PUBLIC)
                .build()
                .field { RepoConnection() }
    }


    class RepoConnection : RepositoryConnection() {
        public override val nodes by list { SelectFromRepository() }
        public override val totalCount by int()
    }
    

    class SelectFromRepository : Repository() {
        public override val name by string()
        public override val description by string()
        public override val id by scalar()
        public override val isFork by bool()
    }
    
    
    class DateTimeConvert : DateTime() {
        override val value by scalarMapper { it }
    }
    
```

The queries from classes like shown above are generated on-the-fly and submitted, providing a `Query<E : Result>` callback handle in order to be notified of the results. Example query generated from a test case:

```
    QueryFooBar {
        name
        bio
        id
        updatedAt
        createdAt
        repositories(
            affiliations: [ OWNER, COLLABORATOR ],
            orderBy: RepositoryOrder {
                direction = ASC
                field = CREATED_AT
            },
            privacy: PUBLIC,
            first: 100
        )
    }
```
Delegates are very useful for a use case like dynamic queries/objects, the goal is to be able to allow for concise expression of queries, and also to maintain the null safety which kotlin provides. It's possible with the use of metadata on the QL schema, otherwise there will most likely need to be a 1:1 relationship between queries -> concrete classes.
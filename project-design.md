# Kotlinq Project Design

### Summary

This project has the goal of enabling clients applications 
to easily use a GraphQL service to query, manipulate, and subscribe to data 
provided by the service in a generic, reusable, declarative and safe manner.
Specifically, it targets client applications running 
on the Java Virtual Machine or Android Operating System 

### High-level project context

GraphQL is used as a lightweight layer in the middle of an application model;
it stands as a mediator between **clients** (e.g. mobile apps) and **database systems**
and the single authoritative means of communication between both.

![alt text](https://i.imgur.com/yq4Hc0G.png "GraphQL Service Diagram")

Both clients and data sources (after this simply referred to as the 'server'\*) 
must be aware of GraphQL to communicate. This is exposed by the server as a
**GraphQL** ***schema*** which defines, in a universally-defined syntax, one or more 
**data models** and their corresponding relationships.

In order for a client to maximize efficiency when using a GraphQL service,
the service's **GraphQL schema** should be used to generate code to represent
the schema's structure on the client's native platform.

Code generation must use a pre-defined structure in the native platform
in order for the custom client application code to access the generated code when the application is running.
Therefore, a pre-defined application component must be designed which provides all 
client applications with a flexible and reliable ***application programming interface*** 
(API) for the client's native platform which can be used with any GraphQL service.

// TODO -> overall context map (clients -> generation tool -> native graphql API -> server)

# Technical Specification & Documentation

### Goals

* Support type-safe structuring of graphql schema with java classes
* Support printing of queries to a string
* Support the declarative & readable expression of graphql queries and mutations using kotlin DSL syntax
* Enforce required arguments to queries
* Enable optional arguments to be included in mutations/queries
* Support custom deserialization of GraphQL scalars to native types
* Support nullable and non-nullable query/mutation return values as defined by a graphql schema
* Do all of the above without intermediate builder-type classes or other objects


### Non Goals

* Authentication with graphql services
* GraphQL reflection (is already available through native core library reflection)
* Client caching
* Client data persistence
* HTTP protocol support in general
* Adapters to 3rd party platforms such as Android framework/libraries

### Metrics

A successful kotlin GraphQL library would support the direct mapping of a GraphQL query
to a Java object-graph and the printing of the corresponding query as a 
specification-compatible string for every possible native query declaration.

# Technical Implementation

### Summary
Typically, the builder pattern is used to accomplish dynamic composition of an object's attributes/properties.
This is not ideal when considering project scope: because dynamic composition of objects by 
method-chaining builder syntax necessarily gives the control of domain objects to the backing framework.

Historically this was the de-facto standard design pattern, 
but kotlin-exclusive language features necessitate reconsideration of such premises.

Three kotlin features can be combined to define a technical model which supports the project requirements.

1. Generics and declaration-site variance
2. DSL builders, a.k.a. a function with a receiver
3. Custom property delegation

The implementation is divided accordingly:

1. Static representation of a GraphQL schema
2. Interfaces to build queries from the schema

### Static Schema Representation

* Interfaces for creating classes representing GraphQL types
* Interfaces for creating properties composing a GraphQL type

### Interfaces for GraphQL types

Type annotations are often used to mark a user-defined class as a use case 
in a 3rd-party library (especially libraries utilizing build-time annotation processing).
This is simply meta-data


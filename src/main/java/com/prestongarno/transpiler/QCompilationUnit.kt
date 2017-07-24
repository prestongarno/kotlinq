package com.prestongarno.transpiler

/**
 * Contains instance variables of Lists of different kinds of GraphQL schema declarations, each one with a nested
 * list of Strings for which each entry represents a declaration within a type declaration (primitive value, nested
 * type, or nested list of types
 */
class QCompilationUnit(val types: List<Pair<String, List<String>>>,
					   val interfaces: List<Pair<String, List<String>>>,
					   val enums: List<Pair<String, List<String>>>,
					   val unions: List<Pair<String, List<String>>>,
					   val scalars: List<Pair<String, List<String>>>)

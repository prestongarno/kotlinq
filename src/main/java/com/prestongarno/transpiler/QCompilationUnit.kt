package com.prestongarno.transpiler

import com.prestongarno.transpiler.qlang.specc.QType

/**
 * Contains instance variables of Lists of different kinds of GraphQL schema declarations, each one with a nested
 * list of Strings for which each entry represents a declaration within a type declaration (primitive value, nested
 * type, or nested list of types
 */
class QCompilationUnit(val content: List<QType>)

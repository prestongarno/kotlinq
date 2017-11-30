package com.prestongarno.ktq.compiler

sealed class Schema

class StringSchema(val source: String) : Schema()

class FileSchema(val path: String) : Schema()


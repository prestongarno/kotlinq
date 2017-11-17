package com.prestongarno.ktq.http

sealed class Authorization(private val value: String) {
  override fun toString(): String = value
}

//class BasicAuth(user: String, pass: String = ""): Authorization("$user${if (pass.isNotBlank()) ":$pass" else ""}")

class TokenAuth(token: String, type: String = "Bearer") : Authorization("$type $token")

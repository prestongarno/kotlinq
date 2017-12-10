/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

@file:Suppress("unused")

package com.prestongarno.kotlinq.http

sealed class Authorization(private val value: String) {
  override fun toString(): String = value
}

//class BasicAuth(user: String, pass: String = ""): Authorization("$user${if (pass.isNotBlank()) ":$pass" else ""}")

class TokenAuth(token: String, type: String = "Bearer") : Authorization("$type $token")

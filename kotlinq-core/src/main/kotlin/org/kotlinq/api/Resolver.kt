package org.kotlinq.api

import dagger.Module
import javax.inject.Singleton


@Module
@Singleton
interface GraphQlJsonParser {
  fun parseToObject(string: String): Map<String, Any?>
  fun parseToArray(string: String): List<Map<String, Any?>>
}

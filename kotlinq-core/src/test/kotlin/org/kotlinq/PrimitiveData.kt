package org.kotlinq

import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.ThreadLocalRandom.current
import kotlin.coroutines.experimental.buildSequence

enum class PrimitiveData(val generator: () -> Any) {
  STRING(UUID.randomUUID()::toString),
  INT({ randomInt(Int.MIN_VALUE, Int.MAX_VALUE) }),
  FLOAT({ ThreadLocalRandom.current().nextDouble().toFloat() }),
  BOOLEAN({ (randomInt(0, 2) == 1) }),
  ENUM({ randomArgumentType() }), // so meta...
  LIST({
    val type = randomArgumentType()
    buildSequence {
      for (i in 1..randomInt(1, 10)) yield(type.generator())
    }.toList()
  });

  companion object Generator {

    fun randomArgumentType() =
        PrimitiveData.values()[ThreadLocalRandom.current().nextInt(0, PrimitiveData.values().size)]

    fun randomArgument() = STRING.generator().toString() to randomArgumentType().generator()

    fun randomGraphQlArgumentMap(size: Int = randomInt(0, 1000)) = buildSequence {
      for (i in 1..size) yield(randomArgument())
    }.toMap()
  }
}

private
fun randomInt(min: Int, max: Int) = current().nextInt(min, max)

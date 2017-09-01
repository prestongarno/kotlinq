package com.prestongarno.transpiler

import java.util.*

object QLexer {

  var NAME = Regex("([a-zA-Z0-9_]+)(\\((.*?)\\))?\\s*:")
  var TYPE = Regex("([a-zA-Z0-9_]+)")
  var INPUT = Regex("$TYPE\\s*:\\s*(\\[?($TYPE!?)\\]?!?)(\\s*?=\\s*?(.*?))?")
  var LIST = Regex("\\[$TYPE!?]")
  var NON_NULL = Regex("\\[?$TYPE((!\\])|(\\]!)|!)")

  /** Regex to match entire field (input) **/
  val DIRECTIVE = Regex("@([a-zA-Z0-9_]*?)\\((.*?)\\)")
  var INPUT_FIELD = Regex("$NAME\\s*(\\[?($TYPE!?)\\]?)(?:\\s*)?($DIRECTIVE)?")
  var PAR = Regex("\\((.*?)\\)", RegexOption.DOT_MATCHES_ALL)
  val NEWLINESPACES = Regex("[\\s\\t]*\\n[\\s\\t]*", RegexOption.MULTILINE)
  val INPUT_SPLIT = ",+".toRegex()
  val COMMENT = Regex("#.*")


  fun baseFields(block: String): List<Field> {
    var input = block

    PAR.findAll(block)
        .forEach { result -> input = input.replace(result.value, result.value.replace(NEWLINESPACES, ",")) }
    input = input.replace(NEWLINESPACES, "\n").trim()

    val comments = COMMENT.findAll(input)
        .mapNotNull { it }
        .filter { it.value.isNotBlank() }
        .map { Pair(it.range, it.value.trim().substring(1)) }
        .toMutableSet()

    return INPUT_FIELD.findAll(input)
        .mapNotNull { result -> result.groups }
        .filter { it.isNotEmpty() }
        .map { group ->
          val name = group[1]!!.value // TODO need more syntax checking?
          val type = group[6]!!.value
          val args = if (group[2] != null) {
            group[3]!!.value
                .trim()
                .split(INPUT_SPLIT)
                .filter { s -> s.isNotBlank() }
                .map { str -> inputField(str.trim()) }
          } else Collections.emptyList()
          val directive = if (group[7] == null) Pair("", "") else Pair(group[8]!!.value, group[9]!!.value)
          val isList = LIST.matches(group[4]!!.value)
          val nullable = !NON_NULL.containsMatchIn(group[5]!!.value)
          if (isList)
            checkBracketsForList(group[4]!!.value)
          Field(name,
              args,
              type,
              directive,
              isList,
              nullable,
              comments.filter { (range, value) ->
                range.last < group[0]!!.range.start
              }.sortedBy { it.first.first }
                  .toList()
                  .also { comments -= it }
                  .joinToString("\n") { it.second })
        }.toList()
  }


  private fun inputField(input: String): FieldInputArg {
    val match = INPUT.matchEntire(input)?.groupValues ?: throw IllegalArgumentException("Bad input field declaration: $input")
    if (match.size != 7) throw Error("Bad regex parsing input field -> expected capture count was 6 but was ${match.size}")
    val name = match[1]
    val type = match[4]
    var defaultValue = match[5].trim()
    if (defaultValue.isNotBlank() && defaultValue.startsWith("="))
      defaultValue = defaultValue.substring(1).trim() // why .*? captures '=' behind it?
    val isList = LIST.matches(match[2])
    val isNullable = !NON_NULL.matches(match[3])
    if (isList)
      checkBracketsForList(match[2])
    return FieldInputArg(name, type, defaultValue, isList, isNullable)
  }

  private inline fun checkBracketsForList(type: String) =
      if (!type.startsWith("[") || !type.endsWith("]")) throw IllegalArgumentException("Unclosed bracket: '$type'") else 1


  fun enumFields(block: String): List<String> = TYPE.findAll(block).map { result -> result.groups }
      .map { col -> col[0]!!.value }.toList()

  fun unionFields(block: String): List<String> = block.split("|").map { s -> s.trim() }.filter { s -> s.isNotEmpty() }

}

data class Field(val symbol: String,
                 val inputArgs: List<FieldInputArg>,
                 val type: String,
                 val directive: Pair<String, String>,
                 val isList: Boolean,
                 val isNullable: Boolean,
                 val comment: String) {
  override fun toString(): String {
    return "Field: $symbol  || type:\t$type || isList=$isList || isNullable=$isNullable || directive=$directive " +
        if (inputArgs.isNotEmpty()) inputArgs.joinToString("", "\n\t arg -> \t", ",") else ""
  }
}

data class FieldInputArg(val symbol: String, val type: String, val defaultValue: String, val isList: Boolean, val isNullable: Boolean) {
  override fun toString(): String = "arg: '$symbol' type='$type' isList? $isList -- Nullable? $isNullable" +
      if (defaultValue.isNotBlank()) " -- default model = '$defaultValue'" else ""
}


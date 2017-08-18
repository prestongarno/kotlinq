package com.prestongarno.ktq

import org.junit.Test

class RepoSelection() : Repository {
  val repoName: String by name()
  val nested : IssueConnection by Repository.IssuesArgs()
      .first(20)
      .orderBy(IssueOrder(IssueOrderField.CREATED_AT, OrderDirection.ASC))
      .build(this)
      .issues()
}

class InputArguments {
  @Test
  fun tesetInputArgumentCreation() {
    RepoSelection()
  }
}
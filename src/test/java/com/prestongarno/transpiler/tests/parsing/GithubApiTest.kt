package com.prestongarno.transpiler.tests.parsing

import com.prestongarno.transpiler.Field
import com.prestongarno.transpiler.Lexer
import org.junit.Test
import java.util.*

class GithubApiTest {
	@Test
	fun schemaTest() {
		val file = this::class.java.classLoader.getResource("graphql.schema.graphqls")

		val input = Scanner(file.openStream()).useDelimiter("\\A").next()
		val regex = Regex("\\{(.*?)}", RegexOption.DOT_MATCHES_ALL)
		regex.findAll(input).forEach { result -> Lexer.readFields(result.value).forEach{ t -> println(t) }  }
		Lexer.readFields("acceptTopicSuggestion(input: AcceptTopicSuggestionInput!): AcceptTopicSuggestionPayload\n" +
				"\n" +
				"    addComment(input: AddCommentInput!): AddCommentPayload\n" +
				"\n" +
				"    addProjectCard(input: AddProjectCardInput!): AddProjectCardPayload\n" +
				"\n" +
				"    addProjectColumn(input: AddProjectColumnInput!): AddProjectColumnPayload\n" +
				"\n" +
				"    addPullRequestReview(input: AddPullRequestReviewInput!): AddPullRequestReviewPayload\n" +
				"\n" +
				"    addPullRequestReviewComment(input: AddPullRequestReviewCommentInput!): AddPullRequestReviewCommentPayload\n" +
				"\n" +
				"    addReaction(input: AddReactionInput!): AddReactionPayload\n" +
				"\n" +
				"    addStar(input: AddStarInput!): AddStarPayload\n" +
				"\n" +
				"    createProject(input: CreateProjectInput!): CreateProjectPayload\n" +
				"\n" +
				"    declineTopicSuggestion(input: DeclineTopicSuggestionInput!): DeclineTopicSuggestionPayload\n" +
				"\n" +
				"    deleteProject(input: DeleteProjectInput!): DeleteProjectPayload\n" +
				"\n" +
				"    deleteProjectCard(input: DeleteProjectCardInput!): DeleteProjectCardPayload\n" +
				"\n" +
				"    deleteProjectColumn(input: DeleteProjectColumnInput!): DeleteProjectColumnPayload\n" +
				"\n" +
				"    deletePullRequestReview(input: DeletePullRequestReviewInput!): DeletePullRequestReviewPayload\n" +
				"\n" +
				"    dismissPullRequestReview(input: DismissPullRequestReviewInput!): DismissPullRequestReviewPayload\n" +
				"\n" +
				"    moveProjectCard(input: MoveProjectCardInput!): MoveProjectCardPayload\n" +
				"\n" +
				"    moveProjectColumn(input: MoveProjectColumnInput!): MoveProjectColumnPayload\n" +
				"\n" +
				"    removeOutsideCollaborator(input: RemoveOutsideCollaboratorInput!): RemoveOutsideCollaboratorPayload\n" +
				"\n" +
				"    removeReaction(input: RemoveReactionInput!): RemoveReactionPayload\n" +
				"\n" +
				"    removeStar(input: RemoveStarInput!): RemoveStarPayload\n" +
				"\n" +
				"    requestReviews(input: RequestReviewsInput!): RequestReviewsPayload\n" +
				"\n" +
				"    submitPullRequestReview(input: SubmitPullRequestReviewInput!): SubmitPullRequestReviewPayload\n" +
				"\n" +
				"    updateProject(input: UpdateProjectInput!): UpdateProjectPayload\n" +
				"\n" +
				"    updateProjectCard(input: UpdateProjectCardInput!): UpdateProjectCardPayload\n" +
				"\n" +
				"    updateProjectColumn(input: UpdateProjectColumnInput!): UpdateProjectColumnPayload\n" +
				"\n" +
				"    updatePullRequestReview(input: UpdatePullRequestReviewInput!): UpdatePullRequestReviewPayload\n" +
				"\n" +
				"    updatePullRequestReviewComment(input: UpdatePullRequestReviewCommentInput!): UpdatePullRequestReviewCommentPayload\n" +
				"\n" +
				"    updateSubscription(input: UpdateSubscriptionInput!): UpdateSubscriptionPayload\n" +
				"\n" +
				"    updateTopics(input: UpdateTopicsInput!): UpdateTopicsPayload" +
				"").forEachIndexed { index, field -> println("$field   $index") }
	}
}


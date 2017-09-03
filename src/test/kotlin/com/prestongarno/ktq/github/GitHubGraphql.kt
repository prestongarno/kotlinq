@file:Suppress("unused")

package com.prestongarno.ktq.github

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.ListConfig
import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.ListStub
import com.prestongarno.ktq.QConfigStub
import com.prestongarno.ktq.QInput
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.QSchemaType.QScalarList
import com.prestongarno.ktq.QSchemaType.QType
import com.prestongarno.ktq.QSchemaType.QTypeList
import com.prestongarno.ktq.QTypeConfigStub
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.TypeArgBuilder
import com.prestongarno.ktq.TypeListArgBuilder

abstract class BaseAvatarUrlArgs(args: ArgBuilder) : ArgBuilder by args

data class AcceptTopicSuggestionInput(private val repositoryId: String,
    private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AcceptTopicSuggestionPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val topic: InitStub<Topic> by QType.stub()
}

interface Actor : QSchemaType {
  val avatarUrl: QConfigStub<URI, BaseAvatarUrlArgs>

  val login: Stub<String>

  val resourcePath: Stub<URI>

  val url: Stub<URI>
}

data class AddCommentInput(private val subjectId: String, private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddCommentPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val commentEdge: InitStub<IssueCommentEdge> by QType.stub()

  val subject: InitStub<Node> by QType.stub()

  val timelineEdge: InitStub<IssueTimelineItemEdge> by QType.stub()
}

data class AddProjectCardInput(private val projectColumnId: String) : QInput {
  private var clientMutationId: String? = null
  private var contentId: String? = null
  private var note: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun contentId(model: String) = apply { contentId = model }

  fun note(model: String) = apply { note = model }
}

object AddProjectCardPayload : QSchemaType {
  val cardEdge: InitStub<ProjectCardEdge> by QType.stub()

  val clientMutationId: Stub<String> by QScalar.stub()

  val projectColumn: InitStub<Project> by QType.stub()
}

data class AddProjectColumnInput(private val projectId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddProjectColumnPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val columnEdge: InitStub<ProjectColumnEdge> by QType.stub()

  val project: InitStub<Project> by QType.stub()
}

data class AddPullRequestReviewCommentInput(private val pullRequestReviewId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  private var commitOID: GitObjectID? = null
  private var path: String? = null
  private var position: Int? = null
  private var inReplyTo: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun commitOID(model: GitObjectID) = apply { commitOID = model }

  fun path(model: String) = apply { path = model }

  fun position(model: Int) = apply { position = model }

  fun inReplyTo(model: String) = apply { inReplyTo = model }
}

object AddPullRequestReviewCommentPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val comment: InitStub<PullRequestReviewComment> by QType.stub()

  val commentEdge: InitStub<PullRequestReviewCommentEdge> by QType.stub()
}

data class AddPullRequestReviewInput(private val pullRequestId: String) : QInput {
  private var clientMutationId: String? = null
  private var commitOID: GitObjectID? = null
  private var body: String? = null
  private var event: PullRequestReviewEvent? = null
  private var comments: DraftPullRequestReviewComment? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun commitOID(model: GitObjectID) = apply { commitOID = model }

  fun body(model: String) = apply { body = model }

  fun event(model: PullRequestReviewEvent) = apply { event = model }

  fun comments(model: DraftPullRequestReviewComment) = apply { comments = model }
}

object AddPullRequestReviewPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val pullRequestReview: InitStub<PullRequestReview> by QType.stub()

  val reviewEdge: InitStub<PullRequestReviewEdge> by QType.stub()
}

data class AddReactionInput(private val subjectId: String,
    private val content: ReactionContent) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddReactionPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val reaction: InitStub<Reaction> by QType.stub()

  val subject: InitStub<Reactable> by QType.stub()
}

data class AddStarInput(private val starrableId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddStarPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val starrable: InitStub<Starrable> by QType.stub()
}

object AddedToProjectEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()
}

interface Assignable : QSchemaType {
  val assignees: QTypeConfigStub<UserConnection, AssigneesArgs>

  class AssigneesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): AssigneesArgs = apply { addArg("first", value) }


    fun after(value: String): AssigneesArgs = apply { addArg("after", value) }


    fun last(value: Int): AssigneesArgs = apply { addArg("last", value) }


    fun before(value: String): AssigneesArgs = apply { addArg("before", value) }

  }
}

object AssignedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val assignable: InitStub<Assignable> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val user: InitStub<User> by QType.stub()
}

object BaseRefChangedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()
}

object BaseRefForcePushedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val afterCommit: InitStub<Commit> by QType.stub()

  val beforeCommit: InitStub<Commit> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  val ref: InitStub<Ref> by QType.stub()
}

object Blame : QSchemaType {
  val ranges: ListInitStub<BlameRange> by QTypeList.stub()
}

object BlameRange : QSchemaType {
  val age: Stub<Int> by QScalar.stub()

  val commit: InitStub<Commit> by QType.stub()

  val endingLine: Stub<Int> by QScalar.stub()

  val startingLine: Stub<Int> by QScalar.stub()
}

object Blob : QSchemaType, GitObject, Node {
  override val abbreviatedOid: Stub<String> by QScalar.stub()

  val byteSize: Stub<Int> by QScalar.stub()

  override val commitResourcePath: Stub<URI> by QScalar.stub()

  override val commitUrl: Stub<URI> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val isBinary: Stub<Boolean> by QScalar.stub()

  val isTruncated: Stub<Boolean> by QScalar.stub()

  override val oid: Stub<GitObjectID> by QScalar.stub()

  override val repository: InitStub<Repository> by QType.stub()

  val text: Stub<String> by QScalar.stub()
}

object Bot : QSchemaType, UniformResourceLocatable, Actor, Node {
  override val avatarUrl: QConfigStub<URI, AvatarUrlArgs> by QScalar.configStub { AvatarUrlArgs(it) }

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  override val login: Stub<String> by QScalar.stub()

  override val resourcePath: Stub<URI> by QScalar.stub()

  override val url: Stub<URI> by QScalar.stub()

  class AvatarUrlArgs(args: ArgBuilder) : BaseAvatarUrlArgs(args) {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }
}

interface Closable : QSchemaType {
  val closed: Stub<Boolean>
}

object ClosedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val closable: InitStub<Closable> by QType.stub()

  val commit: InitStub<Commit> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()
}

object CodeOfConduct : QSchemaType {
  val body: Stub<String> by QScalar.stub()

  val key: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val url: Stub<URI> by QScalar.stub()
}

interface Comment : QSchemaType {
  val author: InitStub<Actor>

  val authorAssociation: Stub<CommentAuthorAssociation>

  val body: Stub<String>

  val bodyHTML: Stub<HTML>

  val createdAt: Stub<DateTime>

  val createdViaEmail: Stub<Boolean>

  val editor: InitStub<Actor>

  val id: Stub<String>

  val lastEditedAt: Stub<DateTime>

  val publishedAt: Stub<DateTime>

  val updatedAt: Stub<DateTime>

  val viewerDidAuthor: Stub<Boolean>
}

enum class CommentAuthorAssociation : QSchemaType {
  MEMBER,

  OWNER,

  COLLABORATOR,

  CONTRIBUTOR,

  FIRST_TIME_CONTRIBUTOR,

  NONE
}

enum class CommentCannotUpdateReason : QSchemaType {
  INSUFFICIENT_ACCESS,

  LOCKED,

  LOGIN_REQUIRED,

  MAINTENANCE,

  VERIFIED_EMAIL_REQUIRED
}

object CommentDeletedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()
}

object Commit : QSchemaType, Subscribable, GitObject, Node {
  override val abbreviatedOid: Stub<String> by QScalar.stub()

  val author: InitStub<GitActor> by QType.stub()

  val authoredByCommitter: Stub<Boolean> by QScalar.stub()

  val blame: QTypeConfigStub<Blame, BlameArgs> by QType.configStub { BlameArgs(it) }

  val comments: QTypeConfigStub<CommitCommentConnection, CommentsArgs> by QType.configStub { CommentsArgs(it) }

  override val commitResourcePath: Stub<URI> by QScalar.stub()

  override val commitUrl: Stub<URI> by QScalar.stub()

  val committedDate: Stub<DateTime> by QScalar.stub()

  val committedViaWeb: Stub<Boolean> by QScalar.stub()

  val committer: InitStub<GitActor> by QType.stub()

  val history: QTypeConfigStub<CommitHistoryConnection, HistoryArgs> by QType.configStub { HistoryArgs(it) }

  override val id: Stub<String> by QScalar.stub()

  val message: Stub<String> by QScalar.stub()

  val messageBody: Stub<String> by QScalar.stub()

  val messageBodyHTML: Stub<HTML> by QScalar.stub()

  val messageHeadline: Stub<String> by QScalar.stub()

  val messageHeadlineHTML: Stub<HTML> by QScalar.stub()

  override val oid: Stub<GitObjectID> by QScalar.stub()

  override val repository: InitStub<Repository> by QType.stub()

  val resourcePath: Stub<URI> by QScalar.stub()

  val signature: InitStub<GitSignature> by QType.stub()

  val status: InitStub<Status> by QType.stub()

  val tree: InitStub<Tree> by QType.stub()

  val treeResourcePath: Stub<URI> by QScalar.stub()

  val treeUrl: Stub<URI> by QScalar.stub()

  val url: Stub<URI> by QScalar.stub()

  override val viewerCanSubscribe: Stub<Boolean> by QScalar.stub()

  override val viewerSubscription: Stub<SubscriptionState> by QScalar.stub()

  class BlameArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun path(value: String): BlameArgs = apply { addArg("path", value) }

  }

  class CommentsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class HistoryArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): HistoryArgs = apply { addArg("first", value) }


    fun after(value: String): HistoryArgs = apply { addArg("after", value) }


    fun last(value: Int): HistoryArgs = apply { addArg("last", value) }


    fun before(value: String): HistoryArgs = apply { addArg("before", value) }


    fun path(value: String): HistoryArgs = apply { addArg("path", value) }


    fun author(value: CommitAuthor): HistoryArgs = apply { addArg("author", value) }


    fun since(value: GitTimestamp): HistoryArgs = apply { addArg("since", value) }


    fun until(value: GitTimestamp): HistoryArgs = apply { addArg("until", value) }

  }
}

data class CommitAuthor(private val emails: String) : QInput {
  private var id: String? = null
  fun id(model: String) = apply { id = model }
}

object CommitComment : QSchemaType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> by QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> by QScalar.stub()

  override val body: Stub<String> by QScalar.stub()

  override val bodyHTML: Stub<HTML> by QScalar.stub()

  val commit: InitStub<Commit> by QType.stub()

  override val createdAt: Stub<DateTime> by QScalar.stub()

  override val createdViaEmail: Stub<Boolean> by QScalar.stub()

  override val databaseId: Stub<Int> by QScalar.stub()

  override val editor: InitStub<Actor> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  override val lastEditedAt: Stub<DateTime> by QScalar.stub()

  val path: Stub<String> by QScalar.stub()

  val position: Stub<Int> by QScalar.stub()

  override val publishedAt: Stub<DateTime> by QScalar.stub()

  override val reactionGroups: ListInitStub<ReactionGroup> by QTypeList.stub()

  override val reactions: QTypeConfigStub<ReactionConnection, Reactable.ReactionsArgs> by QType.configStub { Reactable.ReactionsArgs(it) }

  override val repository: InitStub<Repository> by QType.stub()

  override val updatedAt: Stub<DateTime> by QScalar.stub()

  override val viewerCanDelete: Stub<Boolean> by QScalar.stub()

  override val viewerCanReact: Stub<Boolean> by QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> by QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> by QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> by QScalar.stub()
}

object CommitCommentConnection : QSchemaType {
  val edges: ListInitStub<CommitCommentEdge> by QTypeList.stub()

  val nodes: ListInitStub<CommitComment> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object CommitCommentEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<CommitComment> by QType.stub()
}

object CommitCommentThread : QSchemaType, RepositoryNode, Node {
  val comments: QTypeConfigStub<CommitCommentConnection, CommentsArgs> by QType.configStub { CommentsArgs(it) }

  val commit: InitStub<Commit> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  val path: Stub<String> by QScalar.stub()

  val position: Stub<Int> by QScalar.stub()

  override val repository: InitStub<Repository> by QType.stub()

  class CommentsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

object CommitEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Commit> by QType.stub()
}

object CommitHistoryConnection : QSchemaType {
  val edges: ListInitStub<CommitEdge> by QTypeList.stub()

  val nodes: ListInitStub<Commit> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()
}

object ConvertedNoteToIssueEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()
}

data class CreateProjectInput(private val ownerId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun body(model: String) = apply { body = model }
}

object CreateProjectPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val project: InitStub<Project> by QType.stub()
}

object DateTime : QSchemaType {
  val value: Stub<String> by QScalar.stub()
}

data class DeclineTopicSuggestionInput(private val repositoryId: String, private val name: String,
    private val reason: TopicSuggestionDeclineReason) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeclineTopicSuggestionPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val topic: InitStub<Topic> by QType.stub()
}

enum class DefaultRepositoryPermissionField : QSchemaType {
  READ,

  WRITE,

  ADMIN
}

interface Deletable : QSchemaType {
  val viewerCanDelete: Stub<Boolean>
}

data class DeleteProjectCardInput(private val cardId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeleteProjectCardPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val column: InitStub<ProjectColumn> by QType.stub()

  val deletedCardId: Stub<String> by QScalar.stub()
}

data class DeleteProjectColumnInput(private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeleteProjectColumnPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val deletedColumnId: Stub<String> by QScalar.stub()

  val project: InitStub<Project> by QType.stub()
}

data class DeleteProjectInput(private val projectId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeleteProjectPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val owner: InitStub<ProjectOwner> by QType.stub()
}

data class DeletePullRequestReviewInput(private val pullRequestReviewId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeletePullRequestReviewPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val pullRequestReview: InitStub<PullRequestReview> by QType.stub()
}

object DemilestonedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val milestoneTitle: Stub<String> by QScalar.stub()

  val subject: InitStub<MilestoneItem> by QType.stub()
}

object DeployedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  val deployment: InitStub<Deployment> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  val ref: InitStub<Ref> by QType.stub()
}

object Deployment : QSchemaType, Node {
  val commit: InitStub<Commit> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val creator: InitStub<Actor> by QType.stub()

  val environment: Stub<String> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val latestStatus: InitStub<DeploymentStatus> by QType.stub()

  val repository: InitStub<Repository> by QType.stub()

  val state: Stub<DeploymentState> by QScalar.stub()

  val statuses: QTypeConfigStub<DeploymentStatusConnection, StatusesArgs> by QType.configStub { StatusesArgs(it) }

  class StatusesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): StatusesArgs = apply { addArg("first", value) }


    fun after(value: String): StatusesArgs = apply { addArg("after", value) }


    fun last(value: Int): StatusesArgs = apply { addArg("last", value) }


    fun before(value: String): StatusesArgs = apply { addArg("before", value) }

  }
}

object DeploymentConnection : QSchemaType {
  val edges: ListInitStub<DeploymentEdge> by QTypeList.stub()

  val nodes: ListInitStub<Deployment> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object DeploymentEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Deployment> by QType.stub()
}

enum class DeploymentState : QSchemaType {
  ABANDONED,

  ACTIVE,

  DESTROYED,

  ERROR,

  FAILURE,

  INACTIVE,

  PENDING
}

object DeploymentStatus : QSchemaType, Node {
  val creator: InitStub<Actor> by QType.stub()

  val deployment: InitStub<Deployment> by QType.stub()

  val description: Stub<String> by QScalar.stub()

  val environmentUrl: Stub<URI> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val logUrl: Stub<URI> by QScalar.stub()

  val state: Stub<DeploymentStatusState> by QScalar.stub()
}

object DeploymentStatusConnection : QSchemaType {
  val edges: ListInitStub<DeploymentStatusEdge> by QTypeList.stub()

  val nodes: ListInitStub<DeploymentStatus> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object DeploymentStatusEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<DeploymentStatus> by QType.stub()
}

enum class DeploymentStatusState : QSchemaType {
  PENDING,

  SUCCESS,

  FAILURE,

  INACTIVE,

  ERROR
}

data class DismissPullRequestReviewInput(private val pullRequestReviewId: String,
    private val message: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DismissPullRequestReviewPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val pullRequestReview: InitStub<PullRequestReview> by QType.stub()
}

data class DraftPullRequestReviewComment(private val path: String, private val position: Int,
    private val body: String) : QInput

object ExternalIdentity : QSchemaType, Node {
  val guid: Stub<String> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val organizationInvitation: InitStub<OrganizationInvitation> by QType.stub()

  val samlIdentity: InitStub<ExternalIdentitySamlAttributes> by QType.stub()

  val scimIdentity: InitStub<ExternalIdentityScimAttributes> by QType.stub()

  val user: InitStub<User> by QType.stub()
}

object ExternalIdentityConnection : QSchemaType {
  val edges: ListInitStub<ExternalIdentityEdge> by QTypeList.stub()

  val nodes: ListInitStub<ExternalIdentity> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object ExternalIdentityEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<ExternalIdentity> by QType.stub()
}

object ExternalIdentitySamlAttributes : QSchemaType {
  val nameId: Stub<String> by QScalar.stub()
}

object ExternalIdentityScimAttributes : QSchemaType {
  val username: Stub<String> by QScalar.stub()
}

object FollowerConnection : QSchemaType {
  val edges: ListInitStub<UserEdge> by QTypeList.stub()

  val nodes: ListInitStub<User> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object FollowingConnection : QSchemaType {
  val edges: ListInitStub<UserEdge> by QTypeList.stub()

  val nodes: ListInitStub<User> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object Gist : QSchemaType, Starrable, Node {
  val comments: QTypeConfigStub<GistCommentConnection, CommentsArgs> by QType.configStub { CommentsArgs(it) }

  val createdAt: Stub<DateTime> by QScalar.stub()

  val description: Stub<String> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val isPublic: Stub<Boolean> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val owner: InitStub<RepositoryOwner> by QType.stub()

  override val stargazers: QTypeConfigStub<StargazerConnection, Starrable.StargazersArgs> by QType.configStub { Starrable.StargazersArgs(it) }

  val updatedAt: Stub<DateTime> by QScalar.stub()

  override val viewerHasStarred: Stub<Boolean> by QScalar.stub()

  class CommentsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

object GistComment : QSchemaType, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> by QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> by QScalar.stub()

  override val body: Stub<String> by QScalar.stub()

  override val bodyHTML: Stub<HTML> by QScalar.stub()

  override val createdAt: Stub<DateTime> by QScalar.stub()

  override val createdViaEmail: Stub<Boolean> by QScalar.stub()

  override val editor: InitStub<Actor> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  override val lastEditedAt: Stub<DateTime> by QScalar.stub()

  override val publishedAt: Stub<DateTime> by QScalar.stub()

  override val updatedAt: Stub<DateTime> by QScalar.stub()

  override val viewerCanDelete: Stub<Boolean> by QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> by QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> by QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> by QScalar.stub()
}

object GistCommentConnection : QSchemaType {
  val edges: ListInitStub<GistCommentEdge> by QTypeList.stub()

  val nodes: ListInitStub<GistComment> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object GistCommentEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<GistComment> by QType.stub()
}

object GistConnection : QSchemaType {
  val edges: ListInitStub<GistEdge> by QTypeList.stub()

  val nodes: ListInitStub<Gist> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object GistEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Gist> by QType.stub()
}

enum class GistPrivacy : QSchemaType {
  PUBLIC,

  SECRET,

  ALL
}

object GitActor : QSchemaType {
  val avatarUrl: QConfigStub<URI, AvatarUrlArgs> by QScalar.configStub { AvatarUrlArgs(it) }

  val date: Stub<GitTimestamp> by QScalar.stub()

  val email: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val user: InitStub<User> by QType.stub()

  class AvatarUrlArgs(args: ArgBuilder) : ArgBuilder by args {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }
}

interface GitObject : QSchemaType {
  val abbreviatedOid: Stub<String>

  val commitResourcePath: Stub<URI>

  val commitUrl: Stub<URI>

  val id: Stub<String>

  val oid: Stub<GitObjectID>

  val repository: InitStub<Repository>
}

object GitObjectID : QSchemaType {
  val value: Stub<String> by QScalar.stub()
}

interface GitSignature : QSchemaType {
  val email: Stub<String>

  val isValid: Stub<Boolean>

  val payload: Stub<String>

  val signature: Stub<String>

  val signer: InitStub<User>

  val state: Stub<GitSignatureState>
}

enum class GitSignatureState : QSchemaType {
  VALID,

  INVALID,

  MALFORMED_SIG,

  UNKNOWN_KEY,

  BAD_EMAIL,

  UNVERIFIED_EMAIL,

  NO_USER,

  UNKNOWN_SIG_TYPE,

  UNSIGNED,

  GPGVERIFY_UNAVAILABLE,

  GPGVERIFY_ERROR,

  NOT_SIGNING_KEY,

  EXPIRED_KEY
}

object GitTimestamp : QSchemaType {
  val value: Stub<String> by QScalar.stub()
}

object GpgSignature : QSchemaType, GitSignature {
  override val email: Stub<String> by QScalar.stub()

  override val isValid: Stub<Boolean> by QScalar.stub()

  val keyId: Stub<String> by QScalar.stub()

  override val payload: Stub<String> by QScalar.stub()

  override val signature: Stub<String> by QScalar.stub()

  override val signer: InitStub<User> by QType.stub()

  override val state: Stub<GitSignatureState> by QScalar.stub()
}

object HTML : QSchemaType {
  val value: Stub<String> by QScalar.stub()
}

object HeadRefDeletedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val headRef: InitStub<Ref> by QType.stub()

  val headRefName: Stub<String> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()
}

object HeadRefForcePushedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val afterCommit: InitStub<Commit> by QType.stub()

  val beforeCommit: InitStub<Commit> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  val ref: InitStub<Ref> by QType.stub()
}

object HeadRefRestoredEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()
}

object Issue : QSchemaType, UniformResourceLocatable, Subscribable, RepositoryNode, Reactable, Lockable, Labelable, UpdatableComment, Updatable, Comment, Closable, Assignable, Node {
  override val assignees: QTypeConfigStub<UserConnection, Assignable.AssigneesArgs> by QType.configStub { Assignable.AssigneesArgs(it) }

  override val author: InitStub<Actor> by QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> by QScalar.stub()

  override val body: Stub<String> by QScalar.stub()

  override val bodyHTML: Stub<HTML> by QScalar.stub()

  val bodyText: Stub<String> by QScalar.stub()

  override val closed: Stub<Boolean> by QScalar.stub()

  val comments: QTypeConfigStub<IssueCommentConnection, CommentsArgs> by QType.configStub { CommentsArgs(it) }

  override val createdAt: Stub<DateTime> by QScalar.stub()

  override val createdViaEmail: Stub<Boolean> by QScalar.stub()

  override val databaseId: Stub<Int> by QScalar.stub()

  override val editor: InitStub<Actor> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  override val labels: QTypeConfigStub<LabelConnection, Labelable.LabelsArgs> by QType.configStub { Labelable.LabelsArgs(it) }

  override val lastEditedAt: Stub<DateTime> by QScalar.stub()

  override val locked: Stub<Boolean> by QScalar.stub()

  val milestone: InitStub<Milestone> by QType.stub()

  val number: Stub<Int> by QScalar.stub()

  val participants: QTypeConfigStub<UserConnection, ParticipantsArgs> by QType.configStub { ParticipantsArgs(it) }

  override val publishedAt: Stub<DateTime> by QScalar.stub()

  override val reactionGroups: ListInitStub<ReactionGroup> by QTypeList.stub()

  override val reactions: QTypeConfigStub<ReactionConnection, Reactable.ReactionsArgs> by QType.configStub { Reactable.ReactionsArgs(it) }

  override val repository: InitStub<Repository> by QType.stub()

  override val resourcePath: Stub<URI> by QScalar.stub()

  val state: Stub<IssueState> by QScalar.stub()

  val timeline: QTypeConfigStub<IssueTimelineConnection, TimelineArgs> by QType.configStub { TimelineArgs(it) }

  val title: Stub<String> by QScalar.stub()

  override val updatedAt: Stub<DateTime> by QScalar.stub()

  override val url: Stub<URI> by QScalar.stub()

  override val viewerCanReact: Stub<Boolean> by QScalar.stub()

  override val viewerCanSubscribe: Stub<Boolean> by QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> by QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> by QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> by QScalar.stub()

  override val viewerSubscription: Stub<SubscriptionState> by QScalar.stub()

  class CommentsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class ParticipantsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ParticipantsArgs = apply { addArg("first", value) }


    fun after(value: String): ParticipantsArgs = apply { addArg("after", value) }


    fun last(value: Int): ParticipantsArgs = apply { addArg("last", value) }


    fun before(value: String): ParticipantsArgs = apply { addArg("before", value) }

  }

  class TimelineArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): TimelineArgs = apply { addArg("first", value) }


    fun after(value: String): TimelineArgs = apply { addArg("after", value) }


    fun last(value: Int): TimelineArgs = apply { addArg("last", value) }


    fun before(value: String): TimelineArgs = apply { addArg("before", value) }


    fun since(value: DateTime): TimelineArgs = apply { addArg("since", value) }

  }
}

object IssueComment : QSchemaType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> by QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> by QScalar.stub()

  override val body: Stub<String> by QScalar.stub()

  override val bodyHTML: Stub<HTML> by QScalar.stub()

  val bodyText: Stub<String> by QScalar.stub()

  override val createdAt: Stub<DateTime> by QScalar.stub()

  override val createdViaEmail: Stub<Boolean> by QScalar.stub()

  override val databaseId: Stub<Int> by QScalar.stub()

  override val editor: InitStub<Actor> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  val issue: InitStub<Issue> by QType.stub()

  override val lastEditedAt: Stub<DateTime> by QScalar.stub()

  override val publishedAt: Stub<DateTime> by QScalar.stub()

  override val reactionGroups: ListInitStub<ReactionGroup> by QTypeList.stub()

  override val reactions: QTypeConfigStub<ReactionConnection, Reactable.ReactionsArgs> by QType.configStub { Reactable.ReactionsArgs(it) }

  override val repository: InitStub<Repository> by QType.stub()

  override val updatedAt: Stub<DateTime> by QScalar.stub()

  override val viewerCanDelete: Stub<Boolean> by QScalar.stub()

  override val viewerCanReact: Stub<Boolean> by QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> by QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> by QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> by QScalar.stub()
}

object IssueCommentConnection : QSchemaType {
  val edges: ListInitStub<IssueCommentEdge> by QTypeList.stub()

  val nodes: ListInitStub<IssueComment> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object IssueCommentEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<IssueComment> by QType.stub()
}

object IssueConnection : QSchemaType {
  val edges: ListInitStub<IssueEdge> by QTypeList.stub()

  val nodes: ListInitStub<Issue> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object IssueEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Issue> by QType.stub()
}

object IssueOrPullRequest : QSchemaType {
  val Issue: ListInitStub<Issue> by QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> by QTypeList.stub()
}

data class IssueOrder(private val field: IssueOrderField,
    private val direction: OrderDirection) : QInput

enum class IssueOrderField : QSchemaType {
  CREATED_AT,

  UPDATED_AT,

  COMMENTS
}

enum class IssuePubSubTopic : QSchemaType {
  UPDATED,

  MARKASREAD
}

enum class IssueState : QSchemaType {
  OPEN,

  CLOSED
}

object IssueTimelineConnection : QSchemaType {
  val edges: ListInitStub<IssueTimelineItemEdge> by QTypeList.stub()

  val nodes: ListInitStub<IssueTimelineItem> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object IssueTimelineItem : QSchemaType {
  val Commit: ListInitStub<Commit> by QTypeList.stub()

  val IssueComment: ListInitStub<IssueComment> by QTypeList.stub()

  val ClosedEvent: ListInitStub<ClosedEvent> by QTypeList.stub()

  val ReopenedEvent: ListInitStub<ReopenedEvent> by QTypeList.stub()

  val SubscribedEvent: ListInitStub<SubscribedEvent> by QTypeList.stub()

  val UnsubscribedEvent: ListInitStub<UnsubscribedEvent> by QTypeList.stub()

  val ReferencedEvent: ListInitStub<ReferencedEvent> by QTypeList.stub()

  val AssignedEvent: ListInitStub<AssignedEvent> by QTypeList.stub()

  val UnassignedEvent: ListInitStub<UnassignedEvent> by QTypeList.stub()

  val LabeledEvent: ListInitStub<LabeledEvent> by QTypeList.stub()

  val UnlabeledEvent: ListInitStub<UnlabeledEvent> by QTypeList.stub()

  val MilestonedEvent: ListInitStub<MilestonedEvent> by QTypeList.stub()

  val DemilestonedEvent: ListInitStub<DemilestonedEvent> by QTypeList.stub()

  val RenamedTitleEvent: ListInitStub<RenamedTitleEvent> by QTypeList.stub()

  val LockedEvent: ListInitStub<LockedEvent> by QTypeList.stub()

  val UnlockedEvent: ListInitStub<UnlockedEvent> by QTypeList.stub()
}

object IssueTimelineItemEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<IssueTimelineItem> by QType.stub()
}

object Label : QSchemaType, Node {
  val color: Stub<String> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val issues: QTypeConfigStub<IssueConnection, IssuesArgs> by QType.configStub { IssuesArgs(it) }

  val name: Stub<String> by QScalar.stub()

  val pullRequests: QTypeConfigStub<PullRequestConnection, PullRequestsArgs> by QType.configStub { PullRequestsArgs(it) }

  val repository: InitStub<Repository> by QType.stub()

  class IssuesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class PullRequestsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): PullRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): PullRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): PullRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): PullRequestsArgs = apply { addArg("before", value) }

  }
}

object LabelConnection : QSchemaType {
  val edges: ListInitStub<LabelEdge> by QTypeList.stub()

  val nodes: ListInitStub<Label> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object LabelEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Label> by QType.stub()
}

interface Labelable : QSchemaType {
  val labels: QTypeConfigStub<LabelConnection, LabelsArgs>

  class LabelsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }
}

object LabeledEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val label: InitStub<Label> by QType.stub()

  val labelable: InitStub<Labelable> by QType.stub()
}

object Language : QSchemaType, Node {
  val color: Stub<String> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()
}

object LanguageConnection : QSchemaType {
  val edges: ListInitStub<LanguageEdge> by QTypeList.stub()

  val nodes: ListInitStub<Language> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()

  val totalSize: Stub<Int> by QScalar.stub()
}

object LanguageEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Language> by QType.stub()

  val size: Stub<Int> by QScalar.stub()
}

data class LanguageOrder(private val field: LanguageOrderField,
    private val direction: OrderDirection) : QInput

enum class LanguageOrderField : QSchemaType {
  SIZE
}

interface Lockable : QSchemaType {
  val locked: Stub<Boolean>
}

object LockedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val lockable: InitStub<Lockable> by QType.stub()
}

object MentionedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()
}

enum class MergeableState : QSchemaType {
  MERGEABLE,

  CONFLICTING,

  UNKNOWN
}

object MergedEvent : QSchemaType, UniformResourceLocatable, Node {
  val actor: InitStub<Actor> by QType.stub()

  val commit: InitStub<Commit> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val mergeRef: InitStub<Ref> by QType.stub()

  val mergeRefName: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  override val resourcePath: Stub<URI> by QScalar.stub()

  override val url: Stub<URI> by QScalar.stub()
}

object Milestone : QSchemaType, UniformResourceLocatable, Node {
  val creator: InitStub<Actor> by QType.stub()

  val description: Stub<String> by QScalar.stub()

  val dueOn: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val number: Stub<Int> by QScalar.stub()

  val repository: InitStub<Repository> by QType.stub()

  override val resourcePath: Stub<URI> by QScalar.stub()

  val state: Stub<MilestoneState> by QScalar.stub()

  val title: Stub<String> by QScalar.stub()

  override val url: Stub<URI> by QScalar.stub()
}

object MilestoneConnection : QSchemaType {
  val edges: ListInitStub<MilestoneEdge> by QTypeList.stub()

  val nodes: ListInitStub<Milestone> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object MilestoneEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Milestone> by QType.stub()
}

object MilestoneItem : QSchemaType {
  val Issue: ListInitStub<Issue> by QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> by QTypeList.stub()
}

enum class MilestoneState : QSchemaType {
  OPEN,

  CLOSED
}

object MilestonedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val milestoneTitle: Stub<String> by QScalar.stub()

  val subject: InitStub<MilestoneItem> by QType.stub()
}

data class MoveProjectCardInput(private val cardId: String, private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  private var afterCardId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun afterCardId(model: String) = apply { afterCardId = model }
}

object MoveProjectCardPayload : QSchemaType {
  val cardEdge: InitStub<ProjectCardEdge> by QType.stub()

  val clientMutationId: Stub<String> by QScalar.stub()
}

data class MoveProjectColumnInput(private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  private var afterColumnId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun afterColumnId(model: String) = apply { afterColumnId = model }
}

object MoveProjectColumnPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val columnEdge: InitStub<ProjectColumnEdge> by QType.stub()
}

object MovedColumnsInProjectEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()
}

object Mutation : QSchemaType {
  val acceptTopicSuggestion: QTypeConfigStub<AcceptTopicSuggestionPayload, AcceptTopicSuggestionArgs> by QType.configStub { AcceptTopicSuggestionArgs(it) }

  val addComment: QTypeConfigStub<AddCommentPayload, AddCommentArgs> by QType.configStub { AddCommentArgs(it) }

  val addProjectCard: QTypeConfigStub<AddProjectCardPayload, AddProjectCardArgs> by QType.configStub { AddProjectCardArgs(it) }

  val addProjectColumn: QTypeConfigStub<AddProjectColumnPayload, AddProjectColumnArgs> by QType.configStub { AddProjectColumnArgs(it) }

  val addPullRequestReview: QTypeConfigStub<AddPullRequestReviewPayload, AddPullRequestReviewArgs> by QType.configStub { AddPullRequestReviewArgs(it) }

  val addPullRequestReviewComment: QTypeConfigStub<AddPullRequestReviewCommentPayload, AddPullRequestReviewCommentArgs> by QType.configStub { AddPullRequestReviewCommentArgs(it) }

  val addReaction: QTypeConfigStub<AddReactionPayload, AddReactionArgs> by QType.configStub { AddReactionArgs(it) }

  val addStar: QTypeConfigStub<AddStarPayload, AddStarArgs> by QType.configStub { AddStarArgs(it) }

  val createProject: QTypeConfigStub<CreateProjectPayload, CreateProjectArgs> by QType.configStub { CreateProjectArgs(it) }

  val declineTopicSuggestion: QTypeConfigStub<DeclineTopicSuggestionPayload, DeclineTopicSuggestionArgs> by QType.configStub { DeclineTopicSuggestionArgs(it) }

  val deleteProject: QTypeConfigStub<DeleteProjectPayload, DeleteProjectArgs> by QType.configStub { DeleteProjectArgs(it) }

  val deleteProjectCard: QTypeConfigStub<DeleteProjectCardPayload, DeleteProjectCardArgs> by QType.configStub { DeleteProjectCardArgs(it) }

  val deleteProjectColumn: QTypeConfigStub<DeleteProjectColumnPayload, DeleteProjectColumnArgs> by QType.configStub { DeleteProjectColumnArgs(it) }

  val deletePullRequestReview: QTypeConfigStub<DeletePullRequestReviewPayload, DeletePullRequestReviewArgs> by QType.configStub { DeletePullRequestReviewArgs(it) }

  val dismissPullRequestReview: QTypeConfigStub<DismissPullRequestReviewPayload, DismissPullRequestReviewArgs> by QType.configStub { DismissPullRequestReviewArgs(it) }

  val moveProjectCard: QTypeConfigStub<MoveProjectCardPayload, MoveProjectCardArgs> by QType.configStub { MoveProjectCardArgs(it) }

  val moveProjectColumn: QTypeConfigStub<MoveProjectColumnPayload, MoveProjectColumnArgs> by QType.configStub { MoveProjectColumnArgs(it) }

  val removeOutsideCollaborator: QTypeConfigStub<RemoveOutsideCollaboratorPayload, RemoveOutsideCollaboratorArgs> by QType.configStub { RemoveOutsideCollaboratorArgs(it) }

  val removeReaction: QTypeConfigStub<RemoveReactionPayload, RemoveReactionArgs> by QType.configStub { RemoveReactionArgs(it) }

  val removeStar: QTypeConfigStub<RemoveStarPayload, RemoveStarArgs> by QType.configStub { RemoveStarArgs(it) }

  val requestReviews: QTypeConfigStub<RequestReviewsPayload, RequestReviewsArgs> by QType.configStub { RequestReviewsArgs(it) }

  val submitPullRequestReview: QTypeConfigStub<SubmitPullRequestReviewPayload, SubmitPullRequestReviewArgs> by QType.configStub { SubmitPullRequestReviewArgs(it) }

  val updateProject: QTypeConfigStub<UpdateProjectPayload, UpdateProjectArgs> by QType.configStub { UpdateProjectArgs(it) }

  val updateProjectCard: QTypeConfigStub<UpdateProjectCardPayload, UpdateProjectCardArgs> by QType.configStub { UpdateProjectCardArgs(it) }

  val updateProjectColumn: QTypeConfigStub<UpdateProjectColumnPayload, UpdateProjectColumnArgs> by QType.configStub { UpdateProjectColumnArgs(it) }

  val updatePullRequestReview: QTypeConfigStub<UpdatePullRequestReviewPayload, UpdatePullRequestReviewArgs> by QType.configStub { UpdatePullRequestReviewArgs(it) }

  val updatePullRequestReviewComment: QTypeConfigStub<UpdatePullRequestReviewCommentPayload, UpdatePullRequestReviewCommentArgs> by QType.configStub { UpdatePullRequestReviewCommentArgs(it) }

  val updateSubscription: QTypeConfigStub<UpdateSubscriptionPayload, UpdateSubscriptionArgs> by QType.configStub { UpdateSubscriptionArgs(it) }

  val updateTopics: QTypeConfigStub<UpdateTopicsPayload, UpdateTopicsArgs> by QType.configStub { UpdateTopicsArgs(it) }

  class AcceptTopicSuggestionArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: AcceptTopicSuggestionInput): AcceptTopicSuggestionArgs = apply { addArg("input", value) }

  }

  class AddCommentArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: AddCommentInput): AddCommentArgs = apply { addArg("input", value) }

  }

  class AddProjectCardArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: AddProjectCardInput): AddProjectCardArgs = apply { addArg("input", value) }

  }

  class AddProjectColumnArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: AddProjectColumnInput): AddProjectColumnArgs = apply { addArg("input", value) }

  }

  class AddPullRequestReviewArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: AddPullRequestReviewInput): AddPullRequestReviewArgs = apply { addArg("input", value) }

  }

  class AddPullRequestReviewCommentArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: AddPullRequestReviewCommentInput): AddPullRequestReviewCommentArgs = apply { addArg("input", value) }

  }

  class AddReactionArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: AddReactionInput): AddReactionArgs = apply { addArg("input", value) }

  }

  class AddStarArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: AddStarInput): AddStarArgs = apply { addArg("input", value) }

  }

  class CreateProjectArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: CreateProjectInput): CreateProjectArgs = apply { addArg("input", value) }

  }

  class DeclineTopicSuggestionArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: DeclineTopicSuggestionInput): DeclineTopicSuggestionArgs = apply { addArg("input", value) }

  }

  class DeleteProjectArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: DeleteProjectInput): DeleteProjectArgs = apply { addArg("input", value) }

  }

  class DeleteProjectCardArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: DeleteProjectCardInput): DeleteProjectCardArgs = apply { addArg("input", value) }

  }

  class DeleteProjectColumnArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: DeleteProjectColumnInput): DeleteProjectColumnArgs = apply { addArg("input", value) }

  }

  class DeletePullRequestReviewArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: DeletePullRequestReviewInput): DeletePullRequestReviewArgs = apply { addArg("input", value) }

  }

  class DismissPullRequestReviewArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: DismissPullRequestReviewInput): DismissPullRequestReviewArgs = apply { addArg("input", value) }

  }

  class MoveProjectCardArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: MoveProjectCardInput): MoveProjectCardArgs = apply { addArg("input", value) }

  }

  class MoveProjectColumnArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: MoveProjectColumnInput): MoveProjectColumnArgs = apply { addArg("input", value) }

  }

  class RemoveOutsideCollaboratorArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: RemoveOutsideCollaboratorInput): RemoveOutsideCollaboratorArgs = apply { addArg("input", value) }

  }

  class RemoveReactionArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: RemoveReactionInput): RemoveReactionArgs = apply { addArg("input", value) }

  }

  class RemoveStarArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: RemoveStarInput): RemoveStarArgs = apply { addArg("input", value) }

  }

  class RequestReviewsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: RequestReviewsInput): RequestReviewsArgs = apply { addArg("input", value) }

  }

  class SubmitPullRequestReviewArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: SubmitPullRequestReviewInput): SubmitPullRequestReviewArgs = apply { addArg("input", value) }

  }

  class UpdateProjectArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: UpdateProjectInput): UpdateProjectArgs = apply { addArg("input", value) }

  }

  class UpdateProjectCardArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: UpdateProjectCardInput): UpdateProjectCardArgs = apply { addArg("input", value) }

  }

  class UpdateProjectColumnArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: UpdateProjectColumnInput): UpdateProjectColumnArgs = apply { addArg("input", value) }

  }

  class UpdatePullRequestReviewArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: UpdatePullRequestReviewInput): UpdatePullRequestReviewArgs = apply { addArg("input", value) }

  }

  class UpdatePullRequestReviewCommentArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: UpdatePullRequestReviewCommentInput): UpdatePullRequestReviewCommentArgs = apply { addArg("input", value) }

  }

  class UpdateSubscriptionArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: UpdateSubscriptionInput): UpdateSubscriptionArgs = apply { addArg("input", value) }

  }

  class UpdateTopicsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun input(value: UpdateTopicsInput): UpdateTopicsArgs = apply { addArg("input", value) }

  }
}

interface Node : QSchemaType {
  val id: Stub<String>
}

enum class OrderDirection : QSchemaType {
  ASC,

  DESC
}

object Organization : QSchemaType, UniformResourceLocatable, RepositoryOwner, ProjectOwner, Actor, Node {
  override val avatarUrl: QConfigStub<URI, AvatarUrlArgs> by QScalar.configStub { AvatarUrlArgs(it) }

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val isInvoiced: Stub<Boolean> by QScalar.stub()

  override val login: Stub<String> by QScalar.stub()

  val members: QTypeConfigStub<UserConnection, MembersArgs> by QType.configStub { MembersArgs(it) }

  val name: Stub<String> by QScalar.stub()

  val newTeamResourcePath: Stub<URI> by QScalar.stub()

  val newTeamUrl: Stub<URI> by QScalar.stub()

  val organizationBillingEmail: Stub<String> by QScalar.stub()

  override val pinnedRepositories: QTypeConfigStub<RepositoryConnection, RepositoryOwner.PinnedRepositoriesArgs> by QType.configStub { RepositoryOwner.PinnedRepositoriesArgs(it) }

  override val project: QTypeConfigStub<Project, ProjectOwner.ProjectArgs> by QType.configStub { ProjectOwner.ProjectArgs(it) }

  override val projects: QTypeConfigStub<ProjectConnection, ProjectOwner.ProjectsArgs> by QType.configStub { ProjectOwner.ProjectsArgs(it) }

  override val projectsResourcePath: Stub<URI> by QScalar.stub()

  override val projectsUrl: Stub<URI> by QScalar.stub()

  override val repositories: QTypeConfigStub<RepositoryConnection, RepositoryOwner.RepositoriesArgs> by QType.configStub { RepositoryOwner.RepositoriesArgs(it) }

  override val repository: QTypeConfigStub<Repository, RepositoryOwner.RepositoryArgs> by QType.configStub { RepositoryOwner.RepositoryArgs(it) }

  override val resourcePath: Stub<URI> by QScalar.stub()

  val samlIdentityProvider: InitStub<OrganizationIdentityProvider> by QType.stub()

  val team: QTypeConfigStub<Team, TeamArgs> by QType.configStub { TeamArgs(it) }

  val teams: QTypeConfigStub<TeamConnection, TeamsArgs> by QType.configStub { TeamsArgs(it) }

  val teamsResourcePath: Stub<URI> by QScalar.stub()

  val teamsUrl: Stub<URI> by QScalar.stub()

  override val url: Stub<URI> by QScalar.stub()

  val viewerCanAdminister: Stub<Boolean> by QScalar.stub()

  override val viewerCanCreateProjects: Stub<Boolean> by QScalar.stub()

  val viewerCanCreateRepositories: Stub<Boolean> by QScalar.stub()

  val viewerCanCreateTeams: Stub<Boolean> by QScalar.stub()

  val viewerIsAMember: Stub<Boolean> by QScalar.stub()

  class AvatarUrlArgs(args: ArgBuilder) : BaseAvatarUrlArgs(args) {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }

  class MembersArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): MembersArgs = apply { addArg("first", value) }


    fun after(value: String): MembersArgs = apply { addArg("after", value) }


    fun last(value: Int): MembersArgs = apply { addArg("last", value) }


    fun before(value: String): MembersArgs = apply { addArg("before", value) }

  }

  class TeamArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun slug(value: String): TeamArgs = apply { addArg("slug", value) }

  }

  class TeamsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): TeamsArgs = apply { addArg("first", value) }


    fun after(value: String): TeamsArgs = apply { addArg("after", value) }


    fun last(value: Int): TeamsArgs = apply { addArg("last", value) }


    fun before(value: String): TeamsArgs = apply { addArg("before", value) }


    fun privacy(value: TeamPrivacy): TeamsArgs = apply { addArg("privacy", value) }


    fun role(value: TeamRole): TeamsArgs = apply { addArg("role", value) }


    fun query(value: String): TeamsArgs = apply { addArg("query", value) }


    fun userLogins(value: String): TeamsArgs = apply { addArg("userLogins", value) }


    fun orderBy(value: TeamOrder): TeamsArgs = apply { addArg("orderBy", value) }


    fun ldapMapped(value: Boolean): TeamsArgs = apply { addArg("ldapMapped", value) }

  }
}

object OrganizationConnection : QSchemaType {
  val edges: ListInitStub<OrganizationEdge> by QTypeList.stub()

  val nodes: ListInitStub<Organization> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object OrganizationEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Organization> by QType.stub()
}

object OrganizationIdentityProvider : QSchemaType, Node {
  val digestMethod: Stub<URI> by QScalar.stub()

  val externalIdentities: QTypeConfigStub<ExternalIdentityConnection, ExternalIdentitiesArgs> by QType.configStub { ExternalIdentitiesArgs(it) }

  override val id: Stub<String> by QScalar.stub()

  val idpCertificate: Stub<X509Certificate> by QScalar.stub()

  val issuer: Stub<String> by QScalar.stub()

  val organization: InitStub<Organization> by QType.stub()

  val signatureMethod: Stub<URI> by QScalar.stub()

  val ssoUrl: Stub<URI> by QScalar.stub()

  class ExternalIdentitiesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ExternalIdentitiesArgs = apply { addArg("first", value) }


    fun after(value: String): ExternalIdentitiesArgs = apply { addArg("after", value) }


    fun last(value: Int): ExternalIdentitiesArgs = apply { addArg("last", value) }


    fun before(value: String): ExternalIdentitiesArgs = apply { addArg("before", value) }

  }
}

object OrganizationInvitation : QSchemaType {
  val email: Stub<String> by QScalar.stub()

  val id: Stub<String> by QScalar.stub()

  val invitee: InitStub<User> by QType.stub()

  val inviter: InitStub<User> by QType.stub()

  val role: Stub<OrganizationInvitationRole> by QScalar.stub()
}

object OrganizationInvitationConnection : QSchemaType {
  val edges: ListInitStub<OrganizationInvitationEdge> by QTypeList.stub()

  val nodes: ListInitStub<OrganizationInvitation> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object OrganizationInvitationEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<OrganizationInvitation> by QType.stub()
}

enum class OrganizationInvitationRole : QSchemaType {
  DIRECT_MEMBER,

  ADMIN,

  BILLING_MANAGER,

  REINSTATE
}

object PageInfo : QSchemaType {
  val endCursor: Stub<String> by QScalar.stub()

  val hasNextPage: Stub<Boolean> by QScalar.stub()

  val hasPreviousPage: Stub<Boolean> by QScalar.stub()

  val startCursor: Stub<String> by QScalar.stub()
}

object Project : QSchemaType, Updatable, Node {
  val body: Stub<String> by QScalar.stub()

  val bodyHTML: Stub<HTML> by QScalar.stub()

  val closedAt: Stub<DateTime> by QScalar.stub()

  val columns: QTypeConfigStub<ProjectColumnConnection, ColumnsArgs> by QType.configStub { ColumnsArgs(it) }

  val createdAt: Stub<DateTime> by QScalar.stub()

  val creator: InitStub<Actor> by QType.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val number: Stub<Int> by QScalar.stub()

  val owner: InitStub<ProjectOwner> by QType.stub()

  val resourcePath: Stub<URI> by QScalar.stub()

  val state: Stub<ProjectState> by QScalar.stub()

  val updatedAt: Stub<DateTime> by QScalar.stub()

  val url: Stub<URI> by QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> by QScalar.stub()

  class ColumnsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ColumnsArgs = apply { addArg("first", value) }


    fun after(value: String): ColumnsArgs = apply { addArg("after", value) }


    fun last(value: Int): ColumnsArgs = apply { addArg("last", value) }


    fun before(value: String): ColumnsArgs = apply { addArg("before", value) }

  }
}

object ProjectCard : QSchemaType, Node {
  val column: InitStub<ProjectColumn> by QType.stub()

  val content: InitStub<ProjectCardItem> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val creator: InitStub<Actor> by QType.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val note: Stub<String> by QScalar.stub()

  val project: InitStub<Project> by QType.stub()

  val projectColumn: InitStub<ProjectColumn> by QType.stub()

  val resourcePath: Stub<URI> by QScalar.stub()

  val state: Stub<ProjectCardState> by QScalar.stub()

  val updatedAt: Stub<DateTime> by QScalar.stub()

  val url: Stub<URI> by QScalar.stub()
}

object ProjectCardConnection : QSchemaType {
  val edges: ListInitStub<ProjectCardEdge> by QTypeList.stub()

  val nodes: ListInitStub<ProjectCard> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object ProjectCardEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<ProjectCard> by QType.stub()
}

object ProjectCardItem : QSchemaType {
  val Issue: ListInitStub<Issue> by QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> by QTypeList.stub()
}

enum class ProjectCardState : QSchemaType {
  CONTENT_ONLY,

  NOTE_ONLY,

  REDACTED
}

object ProjectColumn : QSchemaType, Node {
  val cards: QTypeConfigStub<ProjectCardConnection, CardsArgs> by QType.configStub { CardsArgs(it) }

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val project: InitStub<Project> by QType.stub()

  val updatedAt: Stub<DateTime> by QScalar.stub()

  class CardsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): CardsArgs = apply { addArg("first", value) }


    fun after(value: String): CardsArgs = apply { addArg("after", value) }


    fun last(value: Int): CardsArgs = apply { addArg("last", value) }


    fun before(value: String): CardsArgs = apply { addArg("before", value) }

  }
}

object ProjectColumnConnection : QSchemaType {
  val edges: ListInitStub<ProjectColumnEdge> by QTypeList.stub()

  val nodes: ListInitStub<ProjectColumn> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object ProjectColumnEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<ProjectColumn> by QType.stub()
}

object ProjectConnection : QSchemaType {
  val edges: ListInitStub<ProjectEdge> by QTypeList.stub()

  val nodes: ListInitStub<Project> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object ProjectEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Project> by QType.stub()
}

data class ProjectOrder(private val field: ProjectOrderField,
    private val direction: OrderDirection) : QInput

enum class ProjectOrderField : QSchemaType {
  CREATED_AT,

  UPDATED_AT,

  NAME
}

interface ProjectOwner : QSchemaType {
  val id: Stub<String>

  val project: QTypeConfigStub<Project, ProjectArgs>

  val projects: QTypeConfigStub<ProjectConnection, ProjectsArgs>

  val projectsResourcePath: Stub<URI>

  val projectsUrl: Stub<URI>

  val viewerCanCreateProjects: Stub<Boolean>

  class ProjectArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun number(value: Int): ProjectArgs = apply { addArg("number", value) }

  }

  class ProjectsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ProjectsArgs = apply { addArg("first", value) }


    fun after(value: String): ProjectsArgs = apply { addArg("after", value) }


    fun last(value: Int): ProjectsArgs = apply { addArg("last", value) }


    fun before(value: String): ProjectsArgs = apply { addArg("before", value) }


    fun orderBy(value: ProjectOrder): ProjectsArgs = apply { addArg("orderBy", value) }


    fun search(value: String): ProjectsArgs = apply { addArg("search", value) }


    fun states(value: ProjectState): ProjectsArgs = apply { addArg("states", value) }

  }
}

enum class ProjectState : QSchemaType {
  OPEN,

  CLOSED
}

object ProtectedBranch : QSchemaType, Node {
  val creator: InitStub<Actor> by QType.stub()

  val hasDismissableStaleReviews: Stub<Boolean> by QScalar.stub()

  val hasRequiredReviews: Stub<Boolean> by QScalar.stub()

  val hasRequiredStatusChecks: Stub<Boolean> by QScalar.stub()

  val hasRestrictedPushes: Stub<Boolean> by QScalar.stub()

  val hasRestrictedReviewDismissals: Stub<Boolean> by QScalar.stub()

  val hasStrictRequiredStatusChecks: Stub<Boolean> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val isAdminEnforced: Stub<Boolean> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val pushAllowances: QTypeConfigStub<PushAllowanceConnection, PushAllowancesArgs> by QType.configStub { PushAllowancesArgs(it) }

  val repository: InitStub<Repository> by QType.stub()

  val requiredStatusCheckContexts: ListStub<String> by QScalarList.stub()

  val reviewDismissalAllowances: QTypeConfigStub<ReviewDismissalAllowanceConnection, ReviewDismissalAllowancesArgs> by QType.configStub { ReviewDismissalAllowancesArgs(it) }

  class PushAllowancesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): PushAllowancesArgs = apply { addArg("first", value) }


    fun after(value: String): PushAllowancesArgs = apply { addArg("after", value) }


    fun last(value: Int): PushAllowancesArgs = apply { addArg("last", value) }


    fun before(value: String): PushAllowancesArgs = apply { addArg("before", value) }

  }

  class ReviewDismissalAllowancesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ReviewDismissalAllowancesArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewDismissalAllowancesArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewDismissalAllowancesArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewDismissalAllowancesArgs = apply { addArg("before", value) }

  }
}

object ProtectedBranchConnection : QSchemaType {
  val edges: ListInitStub<ProtectedBranchEdge> by QTypeList.stub()

  val nodes: ListInitStub<ProtectedBranch> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object ProtectedBranchEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<ProtectedBranch> by QType.stub()
}

object PullRequest : QSchemaType, UniformResourceLocatable, Subscribable, RepositoryNode, Reactable, Lockable, Labelable, UpdatableComment, Updatable, Comment, Closable, Assignable, Node {
  override val assignees: QTypeConfigStub<UserConnection, Assignable.AssigneesArgs> by QType.configStub { Assignable.AssigneesArgs(it) }

  override val author: InitStub<Actor> by QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> by QScalar.stub()

  val baseRef: InitStub<Ref> by QType.stub()

  val baseRefName: Stub<String> by QScalar.stub()

  override val body: Stub<String> by QScalar.stub()

  override val bodyHTML: Stub<HTML> by QScalar.stub()

  val bodyText: Stub<String> by QScalar.stub()

  override val closed: Stub<Boolean> by QScalar.stub()

  val comments: QTypeConfigStub<IssueCommentConnection, CommentsArgs> by QType.configStub { CommentsArgs(it) }

  val commits: QTypeConfigStub<PullRequestCommitConnection, CommitsArgs> by QType.configStub { CommitsArgs(it) }

  override val createdAt: Stub<DateTime> by QScalar.stub()

  override val createdViaEmail: Stub<Boolean> by QScalar.stub()

  override val databaseId: Stub<Int> by QScalar.stub()

  override val editor: InitStub<Actor> by QType.stub()

  val headRef: InitStub<Ref> by QType.stub()

  val headRefName: Stub<String> by QScalar.stub()

  val headRepository: InitStub<Repository> by QType.stub()

  val headRepositoryOwner: InitStub<RepositoryOwner> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  val isCrossRepository: Stub<Boolean> by QScalar.stub()

  override val labels: QTypeConfigStub<LabelConnection, Labelable.LabelsArgs> by QType.configStub { Labelable.LabelsArgs(it) }

  override val lastEditedAt: Stub<DateTime> by QScalar.stub()

  override val locked: Stub<Boolean> by QScalar.stub()

  val mergeCommit: InitStub<Commit> by QType.stub()

  val mergeable: Stub<MergeableState> by QScalar.stub()

  val merged: Stub<Boolean> by QScalar.stub()

  val mergedAt: Stub<DateTime> by QScalar.stub()

  val number: Stub<Int> by QScalar.stub()

  val participants: QTypeConfigStub<UserConnection, ParticipantsArgs> by QType.configStub { ParticipantsArgs(it) }

  val potentialMergeCommit: InitStub<Commit> by QType.stub()

  override val publishedAt: Stub<DateTime> by QScalar.stub()

  override val reactionGroups: ListInitStub<ReactionGroup> by QTypeList.stub()

  override val reactions: QTypeConfigStub<ReactionConnection, Reactable.ReactionsArgs> by QType.configStub { Reactable.ReactionsArgs(it) }

  override val repository: InitStub<Repository> by QType.stub()

  override val resourcePath: Stub<URI> by QScalar.stub()

  val revertResourcePath: Stub<URI> by QScalar.stub()

  val revertUrl: Stub<URI> by QScalar.stub()

  val reviewRequests: QTypeConfigStub<ReviewRequestConnection, ReviewRequestsArgs> by QType.configStub { ReviewRequestsArgs(it) }

  val reviews: QTypeConfigStub<PullRequestReviewConnection, ReviewsArgs> by QType.configStub { ReviewsArgs(it) }

  val state: Stub<PullRequestState> by QScalar.stub()

  val suggestedReviewers: ListInitStub<SuggestedReviewer> by QTypeList.stub()

  val timeline: QTypeConfigStub<PullRequestTimelineConnection, TimelineArgs> by QType.configStub { TimelineArgs(it) }

  val title: Stub<String> by QScalar.stub()

  override val updatedAt: Stub<DateTime> by QScalar.stub()

  override val url: Stub<URI> by QScalar.stub()

  override val viewerCanReact: Stub<Boolean> by QScalar.stub()

  override val viewerCanSubscribe: Stub<Boolean> by QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> by QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> by QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> by QScalar.stub()

  override val viewerSubscription: Stub<SubscriptionState> by QScalar.stub()

  class CommentsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class CommitsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): CommitsArgs = apply { addArg("first", value) }


    fun after(value: String): CommitsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommitsArgs = apply { addArg("last", value) }


    fun before(value: String): CommitsArgs = apply { addArg("before", value) }

  }

  class ParticipantsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ParticipantsArgs = apply { addArg("first", value) }


    fun after(value: String): ParticipantsArgs = apply { addArg("after", value) }


    fun last(value: Int): ParticipantsArgs = apply { addArg("last", value) }


    fun before(value: String): ParticipantsArgs = apply { addArg("before", value) }

  }

  class ReviewRequestsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ReviewRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewRequestsArgs = apply { addArg("before", value) }

  }

  class ReviewsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ReviewsArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewsArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewsArgs = apply { addArg("before", value) }


    fun states(value: PullRequestReviewState): ReviewsArgs = apply { addArg("states", value) }

  }

  class TimelineArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): TimelineArgs = apply { addArg("first", value) }


    fun after(value: String): TimelineArgs = apply { addArg("after", value) }


    fun last(value: Int): TimelineArgs = apply { addArg("last", value) }


    fun before(value: String): TimelineArgs = apply { addArg("before", value) }


    fun since(value: DateTime): TimelineArgs = apply { addArg("since", value) }

  }
}

object PullRequestCommit : QSchemaType, UniformResourceLocatable, Node {
  val commit: InitStub<Commit> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  override val resourcePath: Stub<URI> by QScalar.stub()

  override val url: Stub<URI> by QScalar.stub()
}

object PullRequestCommitConnection : QSchemaType {
  val edges: ListInitStub<PullRequestCommitEdge> by QTypeList.stub()

  val nodes: ListInitStub<PullRequestCommit> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object PullRequestCommitEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<PullRequestCommit> by QType.stub()
}

object PullRequestConnection : QSchemaType {
  val edges: ListInitStub<PullRequestEdge> by QTypeList.stub()

  val nodes: ListInitStub<PullRequest> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object PullRequestEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<PullRequest> by QType.stub()
}

enum class PullRequestPubSubTopic : QSchemaType {
  UPDATED,

  MARKASREAD,

  HEAD_REF
}

object PullRequestReview : QSchemaType, RepositoryNode, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> by QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> by QScalar.stub()

  override val body: Stub<String> by QScalar.stub()

  override val bodyHTML: Stub<HTML> by QScalar.stub()

  val bodyText: Stub<String> by QScalar.stub()

  val comments: QTypeConfigStub<PullRequestReviewCommentConnection, CommentsArgs> by QType.configStub { CommentsArgs(it) }

  val commit: InitStub<Commit> by QType.stub()

  override val createdAt: Stub<DateTime> by QScalar.stub()

  override val createdViaEmail: Stub<Boolean> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val editor: InitStub<Actor> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  override val lastEditedAt: Stub<DateTime> by QScalar.stub()

  override val publishedAt: Stub<DateTime> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  override val repository: InitStub<Repository> by QType.stub()

  val resourcePath: Stub<URI> by QScalar.stub()

  val state: Stub<PullRequestReviewState> by QScalar.stub()

  val submittedAt: Stub<DateTime> by QScalar.stub()

  override val updatedAt: Stub<DateTime> by QScalar.stub()

  val url: Stub<URI> by QScalar.stub()

  override val viewerCanDelete: Stub<Boolean> by QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> by QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> by QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> by QScalar.stub()

  class CommentsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

object PullRequestReviewComment : QSchemaType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> by QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> by QScalar.stub()

  override val body: Stub<String> by QScalar.stub()

  override val bodyHTML: Stub<HTML> by QScalar.stub()

  val bodyText: Stub<String> by QScalar.stub()

  val commit: InitStub<Commit> by QType.stub()

  override val createdAt: Stub<DateTime> by QScalar.stub()

  override val createdViaEmail: Stub<Boolean> by QScalar.stub()

  override val databaseId: Stub<Int> by QScalar.stub()

  val diffHunk: Stub<String> by QScalar.stub()

  val draftedAt: Stub<DateTime> by QScalar.stub()

  override val editor: InitStub<Actor> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  override val lastEditedAt: Stub<DateTime> by QScalar.stub()

  val originalCommit: InitStub<Commit> by QType.stub()

  val originalPosition: Stub<Int> by QScalar.stub()

  val path: Stub<String> by QScalar.stub()

  val position: Stub<Int> by QScalar.stub()

  override val publishedAt: Stub<DateTime> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  val pullRequestReview: InitStub<PullRequestReview> by QType.stub()

  override val reactionGroups: ListInitStub<ReactionGroup> by QTypeList.stub()

  override val reactions: QTypeConfigStub<ReactionConnection, Reactable.ReactionsArgs> by QType.configStub { Reactable.ReactionsArgs(it) }

  override val repository: InitStub<Repository> by QType.stub()

  val resourcePath: Stub<URI> by QScalar.stub()

  override val updatedAt: Stub<DateTime> by QScalar.stub()

  val url: Stub<URI> by QScalar.stub()

  override val viewerCanDelete: Stub<Boolean> by QScalar.stub()

  override val viewerCanReact: Stub<Boolean> by QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> by QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> by QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> by QScalar.stub()
}

object PullRequestReviewCommentConnection : QSchemaType {
  val edges: ListInitStub<PullRequestReviewCommentEdge> by QTypeList.stub()

  val nodes: ListInitStub<PullRequestReviewComment> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object PullRequestReviewCommentEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<PullRequestReviewComment> by QType.stub()
}

object PullRequestReviewConnection : QSchemaType {
  val edges: ListInitStub<PullRequestReviewEdge> by QTypeList.stub()

  val nodes: ListInitStub<PullRequestReview> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object PullRequestReviewEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<PullRequestReview> by QType.stub()
}

enum class PullRequestReviewEvent : QSchemaType {
  COMMENT,

  APPROVE,

  REQUEST_CHANGES,

  DISMISS
}

enum class PullRequestReviewState : QSchemaType {
  PENDING,

  COMMENTED,

  APPROVED,

  CHANGES_REQUESTED,

  DISMISSED
}

object PullRequestReviewThread : QSchemaType, Node {
  val comments: QTypeConfigStub<PullRequestReviewCommentConnection, CommentsArgs> by QType.configStub { CommentsArgs(it) }

  override val id: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  class CommentsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

enum class PullRequestState : QSchemaType {
  OPEN,

  CLOSED,

  MERGED
}

object PullRequestTimelineConnection : QSchemaType {
  val edges: ListInitStub<PullRequestTimelineItemEdge> by QTypeList.stub()

  val nodes: ListInitStub<PullRequestTimelineItem> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object PullRequestTimelineItem : QSchemaType {
  val Commit: ListInitStub<Commit> by QTypeList.stub()

  val CommitCommentThread: ListInitStub<CommitCommentThread> by QTypeList.stub()

  val PullRequestReview: ListInitStub<PullRequestReview> by QTypeList.stub()

  val PullRequestReviewThread: ListInitStub<PullRequestReviewThread> by QTypeList.stub()

  val PullRequestReviewComment: ListInitStub<PullRequestReviewComment> by QTypeList.stub()

  val IssueComment: ListInitStub<IssueComment> by QTypeList.stub()

  val ClosedEvent: ListInitStub<ClosedEvent> by QTypeList.stub()

  val ReopenedEvent: ListInitStub<ReopenedEvent> by QTypeList.stub()

  val SubscribedEvent: ListInitStub<SubscribedEvent> by QTypeList.stub()

  val UnsubscribedEvent: ListInitStub<UnsubscribedEvent> by QTypeList.stub()

  val MergedEvent: ListInitStub<MergedEvent> by QTypeList.stub()

  val ReferencedEvent: ListInitStub<ReferencedEvent> by QTypeList.stub()

  val AssignedEvent: ListInitStub<AssignedEvent> by QTypeList.stub()

  val UnassignedEvent: ListInitStub<UnassignedEvent> by QTypeList.stub()

  val LabeledEvent: ListInitStub<LabeledEvent> by QTypeList.stub()

  val UnlabeledEvent: ListInitStub<UnlabeledEvent> by QTypeList.stub()

  val MilestonedEvent: ListInitStub<MilestonedEvent> by QTypeList.stub()

  val DemilestonedEvent: ListInitStub<DemilestonedEvent> by QTypeList.stub()

  val RenamedTitleEvent: ListInitStub<RenamedTitleEvent> by QTypeList.stub()

  val LockedEvent: ListInitStub<LockedEvent> by QTypeList.stub()

  val UnlockedEvent: ListInitStub<UnlockedEvent> by QTypeList.stub()

  val DeployedEvent: ListInitStub<DeployedEvent> by QTypeList.stub()

  val HeadRefDeletedEvent: ListInitStub<HeadRefDeletedEvent> by QTypeList.stub()

  val HeadRefRestoredEvent: ListInitStub<HeadRefRestoredEvent> by QTypeList.stub()

  val HeadRefForcePushedEvent: ListInitStub<HeadRefForcePushedEvent> by QTypeList.stub()

  val BaseRefForcePushedEvent: ListInitStub<BaseRefForcePushedEvent> by QTypeList.stub()

  val ReviewRequestedEvent: ListInitStub<ReviewRequestedEvent> by QTypeList.stub()

  val ReviewRequestRemovedEvent: ListInitStub<ReviewRequestRemovedEvent> by QTypeList.stub()

  val ReviewDismissedEvent: ListInitStub<ReviewDismissedEvent> by QTypeList.stub()
}

object PullRequestTimelineItemEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<PullRequestTimelineItem> by QType.stub()
}

object PushAllowance : QSchemaType, Node {
  val actor: InitStub<PushAllowanceActor> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  val protectedBranch: InitStub<ProtectedBranch> by QType.stub()
}

object PushAllowanceActor : QSchemaType {
  val User: ListInitStub<User> by QTypeList.stub()

  val Team: ListInitStub<Team> by QTypeList.stub()
}

object PushAllowanceConnection : QSchemaType {
  val edges: ListInitStub<PushAllowanceEdge> by QTypeList.stub()

  val nodes: ListInitStub<PushAllowance> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object PushAllowanceEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<PushAllowance> by QType.stub()
}

object Query : QSchemaType {
  val codeOfConduct: QTypeConfigStub<CodeOfConduct, CodeOfConductArgs> by QType.configStub { CodeOfConductArgs(it) }

  val codesOfConduct: ListInitStub<CodeOfConduct> by QTypeList.stub()

  val node: QTypeConfigStub<Node, NodeArgs> by QType.configStub { NodeArgs(it) }

  val nodes: ListConfigType<Node, NodesArgs> by QTypeList.configStub { NodesArgs(it) }

  val organization: QTypeConfigStub<Organization, OrganizationArgs> by QType.configStub { OrganizationArgs(it) }

  val rateLimit: InitStub<RateLimit> by QType.stub()

  val relay: InitStub<Query> by QType.stub()

  val repository: QTypeConfigStub<Repository, RepositoryArgs> by QType.configStub { RepositoryArgs(it) }

  val repositoryOwner: QTypeConfigStub<RepositoryOwner, RepositoryOwnerArgs> by QType.configStub { RepositoryOwnerArgs(it) }

  val resource: QTypeConfigStub<UniformResourceLocatable, ResourceArgs> by QType.configStub { ResourceArgs(it) }

  val search: QTypeConfigStub<SearchResultItemConnection, SearchArgs> by QType.configStub { SearchArgs(it) }

  val topic: QTypeConfigStub<Topic, TopicArgs> by QType.configStub { TopicArgs(it) }

  val user: QTypeConfigStub<User, UserArgs> by QType.configStub { UserArgs(it) }

  val viewer: InitStub<User> by QType.stub()

  class CodeOfConductArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun key(value: String): CodeOfConductArgs = apply { addArg("key", value) }

  }

  class NodeArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun id(value: String): NodeArgs = apply { addArg("id", value) }

  }

  class NodesArgs(args: TypeListArgBuilder) : TypeListArgBuilder by args {
    fun ids(value: String): NodesArgs = apply { addArg("ids", value) }

  }

  class OrganizationArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun login(value: String): OrganizationArgs = apply { addArg("login", value) }

  }

  class RepositoryArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun owner(value: String): RepositoryArgs = apply { addArg("owner", value) }


    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }

  class RepositoryOwnerArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun login(value: String): RepositoryOwnerArgs = apply { addArg("login", value) }

  }

  class ResourceArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun url(value: URI): ResourceArgs = apply { addArg("url", value) }

  }

  class SearchArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): SearchArgs = apply { addArg("first", value) }


    fun after(value: String): SearchArgs = apply { addArg("after", value) }


    fun last(value: Int): SearchArgs = apply { addArg("last", value) }


    fun before(value: String): SearchArgs = apply { addArg("before", value) }


    fun query(value: String): SearchArgs = apply { addArg("query", value) }


    fun type(value: SearchType): SearchArgs = apply { addArg("type", value) }

  }

  class TopicArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun name(value: String): TopicArgs = apply { addArg("name", value) }

  }

  class UserArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun login(value: String): UserArgs = apply { addArg("login", value) }

  }
}

object RateLimit : QSchemaType {
  val cost: Stub<Int> by QScalar.stub()

  val limit: Stub<Int> by QScalar.stub()

  val remaining: Stub<Int> by QScalar.stub()

  val resetAt: Stub<DateTime> by QScalar.stub()
}

interface Reactable : QSchemaType {
  val databaseId: Stub<Int>

  val id: Stub<String>

  val reactionGroups: ListInitStub<ReactionGroup>

  val reactions: QTypeConfigStub<ReactionConnection, ReactionsArgs>

  val viewerCanReact: Stub<Boolean>

  class ReactionsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

object ReactingUserConnection : QSchemaType {
  val edges: ListInitStub<ReactingUserEdge> by QTypeList.stub()

  val nodes: ListInitStub<User> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object ReactingUserEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<User> by QType.stub()

  val reactedAt: Stub<DateTime> by QScalar.stub()
}

object Reaction : QSchemaType, Node {
  val content: Stub<ReactionContent> by QScalar.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val user: InitStub<User> by QType.stub()
}

object ReactionConnection : QSchemaType {
  val edges: ListInitStub<ReactionEdge> by QTypeList.stub()

  val nodes: ListInitStub<Reaction> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()

  val viewerHasReacted: Stub<Boolean> by QScalar.stub()
}

enum class ReactionContent : QSchemaType {
  THUMBS_UP,

  THUMBS_DOWN,

  LAUGH,

  HOORAY,

  CONFUSED,

  HEART
}

object ReactionEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Reaction> by QType.stub()
}

object ReactionGroup : QSchemaType {
  val content: Stub<ReactionContent> by QScalar.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val subject: InitStub<Reactable> by QType.stub()

  val users: QTypeConfigStub<ReactingUserConnection, UsersArgs> by QType.configStub { UsersArgs(it) }

  val viewerHasReacted: Stub<Boolean> by QScalar.stub()

  class UsersArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): UsersArgs = apply { addArg("first", value) }


    fun after(value: String): UsersArgs = apply { addArg("after", value) }


    fun last(value: Int): UsersArgs = apply { addArg("last", value) }


    fun before(value: String): UsersArgs = apply { addArg("before", value) }

  }
}

data class ReactionOrder(private val field: ReactionOrderField,
    private val direction: OrderDirection) : QInput

enum class ReactionOrderField : QSchemaType {
  CREATED_AT
}

object Ref : QSchemaType, Node {
  val associatedPullRequests: QTypeConfigStub<PullRequestConnection, AssociatedPullRequestsArgs> by QType.configStub { AssociatedPullRequestsArgs(it) }

  override val id: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val prefix: Stub<String> by QScalar.stub()

  val repository: InitStub<Repository> by QType.stub()

  val target: InitStub<GitObject> by QType.stub()

  class AssociatedPullRequestsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): AssociatedPullRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): AssociatedPullRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): AssociatedPullRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): AssociatedPullRequestsArgs = apply { addArg("before", value) }


    fun states(value: PullRequestState): AssociatedPullRequestsArgs = apply { addArg("states", value) }

  }
}

object RefConnection : QSchemaType {
  val edges: ListInitStub<RefEdge> by QTypeList.stub()

  val nodes: ListInitStub<Ref> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object RefEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Ref> by QType.stub()
}

object ReferencedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val commit: InitStub<Commit> by QType.stub()

  val commitRepository: InitStub<Repository> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val isCrossReference: Stub<Boolean> by QScalar.stub()

  val isCrossRepository: Stub<Boolean> by QScalar.stub()

  val isDirectReference: Stub<Boolean> by QScalar.stub()

  val subject: InitStub<ReferencedSubject> by QType.stub()
}

object ReferencedSubject : QSchemaType {
  val Issue: ListInitStub<Issue> by QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> by QTypeList.stub()
}

object Release : QSchemaType, UniformResourceLocatable, Node {
  val description: Stub<String> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val publishedAt: Stub<DateTime> by QScalar.stub()

  val releaseAsset: QTypeConfigStub<ReleaseAssetConnection, ReleaseAssetArgs> by QType.configStub { ReleaseAssetArgs(it) }

  val releaseAssets: QTypeConfigStub<ReleaseAssetConnection, ReleaseAssetsArgs> by QType.configStub { ReleaseAssetsArgs(it) }

  override val resourcePath: Stub<URI> by QScalar.stub()

  val tag: InitStub<Ref> by QType.stub()

  override val url: Stub<URI> by QScalar.stub()

  class ReleaseAssetArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ReleaseAssetArgs = apply { addArg("first", value) }


    fun after(value: String): ReleaseAssetArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleaseAssetArgs = apply { addArg("last", value) }


    fun before(value: String): ReleaseAssetArgs = apply { addArg("before", value) }


    fun name(value: String): ReleaseAssetArgs = apply { addArg("name", value) }

  }

  class ReleaseAssetsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ReleaseAssetsArgs = apply { addArg("first", value) }


    fun after(value: String): ReleaseAssetsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleaseAssetsArgs = apply { addArg("last", value) }


    fun before(value: String): ReleaseAssetsArgs = apply { addArg("before", value) }

  }
}

object ReleaseAsset : QSchemaType, Node {
  override val id: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val release: InitStub<Release> by QType.stub()

  val url: Stub<URI> by QScalar.stub()
}

object ReleaseAssetConnection : QSchemaType {
  val edges: ListInitStub<ReleaseAssetEdge> by QTypeList.stub()

  val nodes: ListInitStub<ReleaseAsset> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object ReleaseAssetEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<ReleaseAsset> by QType.stub()
}

object ReleaseConnection : QSchemaType {
  val edges: ListInitStub<ReleaseEdge> by QTypeList.stub()

  val nodes: ListInitStub<Release> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object ReleaseEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Release> by QType.stub()
}

data class RemoveOutsideCollaboratorInput(private val userId: String,
    private val organizationId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object RemoveOutsideCollaboratorPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val removedUser: InitStub<User> by QType.stub()
}

data class RemoveReactionInput(private val subjectId: String,
    private val content: ReactionContent) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object RemoveReactionPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val reaction: InitStub<Reaction> by QType.stub()

  val subject: InitStub<Reactable> by QType.stub()
}

data class RemoveStarInput(private val starrableId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object RemoveStarPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val starrable: InitStub<Starrable> by QType.stub()
}

object RemovedFromProjectEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()
}

object RenamedTitleEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val currentTitle: Stub<String> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val previousTitle: Stub<String> by QScalar.stub()

  val subject: InitStub<RenamedTitleSubject> by QType.stub()
}

object RenamedTitleSubject : QSchemaType {
  val Issue: ListInitStub<Issue> by QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> by QTypeList.stub()
}

object ReopenedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val closable: InitStub<Closable> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()
}

object Repository : QSchemaType, RepositoryInfo, UniformResourceLocatable, Starrable, Subscribable, ProjectOwner, Node {
  val codeOfConduct: InitStub<CodeOfConduct> by QType.stub()

  val commitComments: QTypeConfigStub<CommitCommentConnection, CommitCommentsArgs> by QType.configStub { CommitCommentsArgs(it) }

  override val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  val defaultBranchRef: InitStub<Ref> by QType.stub()

  val deployments: QTypeConfigStub<DeploymentConnection, DeploymentsArgs> by QType.configStub { DeploymentsArgs(it) }

  override val description: Stub<String> by QScalar.stub()

  override val descriptionHTML: Stub<HTML> by QScalar.stub()

  val diskUsage: Stub<Int> by QScalar.stub()

  val forks: QTypeConfigStub<RepositoryConnection, ForksArgs> by QType.configStub { ForksArgs(it) }

  override val hasIssuesEnabled: Stub<Boolean> by QScalar.stub()

  override val hasWikiEnabled: Stub<Boolean> by QScalar.stub()

  override val homepageUrl: Stub<URI> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  override val isFork: Stub<Boolean> by QScalar.stub()

  override val isLocked: Stub<Boolean> by QScalar.stub()

  override val isMirror: Stub<Boolean> by QScalar.stub()

  override val isPrivate: Stub<Boolean> by QScalar.stub()

  val issue: QTypeConfigStub<Issue, IssueArgs> by QType.configStub { IssueArgs(it) }

  val issueOrPullRequest: QTypeConfigStub<IssueOrPullRequest, IssueOrPullRequestArgs> by QType.configStub { IssueOrPullRequestArgs(it) }

  val issues: QTypeConfigStub<IssueConnection, IssuesArgs> by QType.configStub { IssuesArgs(it) }

  val label: QTypeConfigStub<Label, LabelArgs> by QType.configStub { LabelArgs(it) }

  val labels: QTypeConfigStub<LabelConnection, LabelsArgs> by QType.configStub { LabelsArgs(it) }

  val languages: QTypeConfigStub<LanguageConnection, LanguagesArgs> by QType.configStub { LanguagesArgs(it) }

  override val license: Stub<String> by QScalar.stub()

  override val lockReason: Stub<RepositoryLockReason> by QScalar.stub()

  val mentionableUsers: QTypeConfigStub<UserConnection, MentionableUsersArgs> by QType.configStub { MentionableUsersArgs(it) }

  val milestone: QTypeConfigStub<Milestone, MilestoneArgs> by QType.configStub { MilestoneArgs(it) }

  val milestones: QTypeConfigStub<MilestoneConnection, MilestonesArgs> by QType.configStub { MilestonesArgs(it) }

  override val mirrorUrl: Stub<URI> by QScalar.stub()

  override val name: Stub<String> by QScalar.stub()

  override val nameWithOwner: Stub<String> by QScalar.stub()

  val objectVal: QTypeConfigStub<GitObject, ObjectValArgs> by QType.configStub { ObjectValArgs(it) }

  override val owner: InitStub<RepositoryOwner> by QType.stub()

  val parent: InitStub<Repository> by QType.stub()

  val primaryLanguage: InitStub<Language> by QType.stub()

  override val project: QTypeConfigStub<Project, ProjectOwner.ProjectArgs> by QType.configStub { ProjectOwner.ProjectArgs(it) }

  override val projects: QTypeConfigStub<ProjectConnection, ProjectOwner.ProjectsArgs> by QType.configStub { ProjectOwner.ProjectsArgs(it) }

  override val projectsResourcePath: Stub<URI> by QScalar.stub()

  override val projectsUrl: Stub<URI> by QScalar.stub()

  val protectedBranches: QTypeConfigStub<ProtectedBranchConnection, ProtectedBranchesArgs> by QType.configStub { ProtectedBranchesArgs(it) }

  val pullRequest: QTypeConfigStub<PullRequest, PullRequestArgs> by QType.configStub { PullRequestArgs(it) }

  val pullRequests: QTypeConfigStub<PullRequestConnection, PullRequestsArgs> by QType.configStub { PullRequestsArgs(it) }

  override val pushedAt: Stub<DateTime> by QScalar.stub()

  val ref: QTypeConfigStub<Ref, RefArgs> by QType.configStub { RefArgs(it) }

  val refs: QTypeConfigStub<RefConnection, RefsArgs> by QType.configStub { RefsArgs(it) }

  val releases: QTypeConfigStub<ReleaseConnection, ReleasesArgs> by QType.configStub { ReleasesArgs(it) }

  val repositoryTopics: QTypeConfigStub<RepositoryTopicConnection, RepositoryTopicsArgs> by QType.configStub { RepositoryTopicsArgs(it) }

  override val resourcePath: Stub<URI> by QScalar.stub()

  override val stargazers: QTypeConfigStub<StargazerConnection, Starrable.StargazersArgs> by QType.configStub { Starrable.StargazersArgs(it) }

  override val updatedAt: Stub<DateTime> by QScalar.stub()

  override val url: Stub<URI> by QScalar.stub()

  val viewerCanAdminister: Stub<Boolean> by QScalar.stub()

  override val viewerCanCreateProjects: Stub<Boolean> by QScalar.stub()

  override val viewerCanSubscribe: Stub<Boolean> by QScalar.stub()

  val viewerCanUpdateTopics: Stub<Boolean> by QScalar.stub()

  override val viewerHasStarred: Stub<Boolean> by QScalar.stub()

  override val viewerSubscription: Stub<SubscriptionState> by QScalar.stub()

  val watchers: QTypeConfigStub<UserConnection, WatchersArgs> by QType.configStub { WatchersArgs(it) }

  class CommitCommentsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): CommitCommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommitCommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommitCommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommitCommentsArgs = apply { addArg("before", value) }

  }

  class DeploymentsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): DeploymentsArgs = apply { addArg("first", value) }


    fun after(value: String): DeploymentsArgs = apply { addArg("after", value) }


    fun last(value: Int): DeploymentsArgs = apply { addArg("last", value) }


    fun before(value: String): DeploymentsArgs = apply { addArg("before", value) }

  }

  class ForksArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ForksArgs = apply { addArg("first", value) }


    fun after(value: String): ForksArgs = apply { addArg("after", value) }


    fun last(value: Int): ForksArgs = apply { addArg("last", value) }


    fun before(value: String): ForksArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): ForksArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): ForksArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): ForksArgs = apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): ForksArgs = apply { addArg("isLocked", value) }

  }

  class IssueArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun number(value: Int): IssueArgs = apply { addArg("number", value) }

  }

  class IssueOrPullRequestArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun number(value: Int): IssueOrPullRequestArgs = apply { addArg("number", value) }

  }

  class IssuesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class LabelArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun name(value: String): LabelArgs = apply { addArg("name", value) }

  }

  class LabelsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }

  class LanguagesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): LanguagesArgs = apply { addArg("first", value) }


    fun after(value: String): LanguagesArgs = apply { addArg("after", value) }


    fun last(value: Int): LanguagesArgs = apply { addArg("last", value) }


    fun before(value: String): LanguagesArgs = apply { addArg("before", value) }


    fun orderBy(value: LanguageOrder): LanguagesArgs = apply { addArg("orderBy", value) }

  }

  class MentionableUsersArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): MentionableUsersArgs = apply { addArg("first", value) }


    fun after(value: String): MentionableUsersArgs = apply { addArg("after", value) }


    fun last(value: Int): MentionableUsersArgs = apply { addArg("last", value) }


    fun before(value: String): MentionableUsersArgs = apply { addArg("before", value) }

  }

  class MilestoneArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun number(value: Int): MilestoneArgs = apply { addArg("number", value) }

  }

  class MilestonesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): MilestonesArgs = apply { addArg("first", value) }


    fun after(value: String): MilestonesArgs = apply { addArg("after", value) }


    fun last(value: Int): MilestonesArgs = apply { addArg("last", value) }


    fun before(value: String): MilestonesArgs = apply { addArg("before", value) }

  }

  class ObjectValArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun oid(value: GitObjectID): ObjectValArgs = apply { addArg("oid", value) }


    fun expression(value: String): ObjectValArgs = apply { addArg("expression", value) }

  }

  class ProtectedBranchesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ProtectedBranchesArgs = apply { addArg("first", value) }


    fun after(value: String): ProtectedBranchesArgs = apply { addArg("after", value) }


    fun last(value: Int): ProtectedBranchesArgs = apply { addArg("last", value) }


    fun before(value: String): ProtectedBranchesArgs = apply { addArg("before", value) }

  }

  class PullRequestArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun number(value: Int): PullRequestArgs = apply { addArg("number", value) }

  }

  class PullRequestsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): PullRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): PullRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): PullRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): PullRequestsArgs = apply { addArg("before", value) }


    fun states(value: PullRequestState): PullRequestsArgs = apply { addArg("states", value) }


    fun labels(value: String): PullRequestsArgs = apply { addArg("labels", value) }


    fun headRefName(value: String): PullRequestsArgs = apply { addArg("headRefName", value) }


    fun baseRefName(value: String): PullRequestsArgs = apply { addArg("baseRefName", value) }


    fun orderBy(value: IssueOrder): PullRequestsArgs = apply { addArg("orderBy", value) }

  }

  class RefArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun qualifiedName(value: String): RefArgs = apply { addArg("qualifiedName", value) }

  }

  class RefsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): RefsArgs = apply { addArg("first", value) }


    fun after(value: String): RefsArgs = apply { addArg("after", value) }


    fun last(value: Int): RefsArgs = apply { addArg("last", value) }


    fun before(value: String): RefsArgs = apply { addArg("before", value) }


    fun refPrefix(value: String): RefsArgs = apply { addArg("refPrefix", value) }


    fun direction(value: OrderDirection): RefsArgs = apply { addArg("direction", value) }

  }

  class ReleasesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ReleasesArgs = apply { addArg("first", value) }


    fun after(value: String): ReleasesArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleasesArgs = apply { addArg("last", value) }


    fun before(value: String): ReleasesArgs = apply { addArg("before", value) }

  }

  class RepositoryTopicsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): RepositoryTopicsArgs = apply { addArg("first", value) }


    fun after(value: String): RepositoryTopicsArgs = apply { addArg("after", value) }


    fun last(value: Int): RepositoryTopicsArgs = apply { addArg("last", value) }


    fun before(value: String): RepositoryTopicsArgs = apply { addArg("before", value) }

  }

  class WatchersArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): WatchersArgs = apply { addArg("first", value) }


    fun after(value: String): WatchersArgs = apply { addArg("after", value) }


    fun last(value: Int): WatchersArgs = apply { addArg("last", value) }


    fun before(value: String): WatchersArgs = apply { addArg("before", value) }

  }
}

enum class RepositoryAffiliation : QSchemaType {
  OWNER,

  COLLABORATOR,

  ORGANIZATION_MEMBER
}

enum class RepositoryCollaboratorAffiliation : QSchemaType {
  ALL,

  OUTSIDE
}

object RepositoryConnection : QSchemaType {
  val edges: ListInitStub<RepositoryEdge> by QTypeList.stub()

  val nodes: ListInitStub<Repository> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()

  val totalDiskUsage: Stub<Int> by QScalar.stub()
}

object RepositoryEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Repository> by QType.stub()
}

interface RepositoryInfo : QSchemaType {
  val createdAt: Stub<DateTime>

  val description: Stub<String>

  val descriptionHTML: Stub<HTML>

  val hasIssuesEnabled: Stub<Boolean>

  val hasWikiEnabled: Stub<Boolean>

  val homepageUrl: Stub<URI>

  val isFork: Stub<Boolean>

  val isLocked: Stub<Boolean>

  val isMirror: Stub<Boolean>

  val isPrivate: Stub<Boolean>

  val license: Stub<String>

  val lockReason: Stub<RepositoryLockReason>

  val mirrorUrl: Stub<URI>

  val name: Stub<String>

  val nameWithOwner: Stub<String>

  val owner: InitStub<RepositoryOwner>

  val pushedAt: Stub<DateTime>

  val resourcePath: Stub<URI>

  val updatedAt: Stub<DateTime>

  val url: Stub<URI>
}

object RepositoryInvitation : QSchemaType, Node {
  override val id: Stub<String> by QScalar.stub()

  val invitee: InitStub<User> by QType.stub()

  val inviter: InitStub<User> by QType.stub()

  val repository: InitStub<RepositoryInvitationRepository> by QType.stub()
}

object RepositoryInvitationRepository : QSchemaType, RepositoryInfo {
  override val createdAt: Stub<DateTime> by QScalar.stub()

  override val description: Stub<String> by QScalar.stub()

  override val descriptionHTML: Stub<HTML> by QScalar.stub()

  override val hasIssuesEnabled: Stub<Boolean> by QScalar.stub()

  override val hasWikiEnabled: Stub<Boolean> by QScalar.stub()

  override val homepageUrl: Stub<URI> by QScalar.stub()

  override val isFork: Stub<Boolean> by QScalar.stub()

  override val isLocked: Stub<Boolean> by QScalar.stub()

  override val isMirror: Stub<Boolean> by QScalar.stub()

  override val isPrivate: Stub<Boolean> by QScalar.stub()

  override val license: Stub<String> by QScalar.stub()

  override val lockReason: Stub<RepositoryLockReason> by QScalar.stub()

  override val mirrorUrl: Stub<URI> by QScalar.stub()

  override val name: Stub<String> by QScalar.stub()

  override val nameWithOwner: Stub<String> by QScalar.stub()

  override val owner: InitStub<RepositoryOwner> by QType.stub()

  override val pushedAt: Stub<DateTime> by QScalar.stub()

  override val resourcePath: Stub<URI> by QScalar.stub()

  override val updatedAt: Stub<DateTime> by QScalar.stub()

  override val url: Stub<URI> by QScalar.stub()
}

enum class RepositoryLockReason : QSchemaType {
  MOVING,

  BILLING,

  RENAME,

  MIGRATING
}

interface RepositoryNode : QSchemaType {
  val repository: InitStub<Repository>
}

data class RepositoryOrder(private val field: RepositoryOrderField,
    private val direction: OrderDirection) : QInput

enum class RepositoryOrderField : QSchemaType {
  CREATED_AT,

  UPDATED_AT,

  PUSHED_AT,

  NAME,

  STARGAZERS
}

interface RepositoryOwner : QSchemaType {
  val avatarUrl: QConfigStub<URI, BaseAvatarUrlArgs>

  val id: Stub<String>

  val login: Stub<String>

  val pinnedRepositories: QTypeConfigStub<RepositoryConnection, PinnedRepositoriesArgs>

  val repositories: QTypeConfigStub<RepositoryConnection, RepositoriesArgs>

  val repository: QTypeConfigStub<Repository, RepositoryArgs>

  val resourcePath: Stub<URI>

  val url: Stub<URI>

  class PinnedRepositoriesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): PinnedRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): PinnedRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): PinnedRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): PinnedRepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs = apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): PinnedRepositoriesArgs = apply { addArg("isLocked", value) }

  }

  class RepositoriesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): RepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): RepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): RepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): RepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): RepositoriesArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): RepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): RepositoriesArgs = apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): RepositoriesArgs = apply { addArg("isLocked", value) }


    fun isFork(value: Boolean): RepositoriesArgs = apply { addArg("isFork", value) }

  }

  class RepositoryArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }
}

enum class RepositoryPrivacy : QSchemaType {
  PUBLIC,

  PRIVATE
}

object RepositoryTopic : QSchemaType, UniformResourceLocatable, Node {
  override val id: Stub<String> by QScalar.stub()

  override val resourcePath: Stub<URI> by QScalar.stub()

  val topic: InitStub<Topic> by QType.stub()

  override val url: Stub<URI> by QScalar.stub()
}

object RepositoryTopicConnection : QSchemaType {
  val edges: ListInitStub<RepositoryTopicEdge> by QTypeList.stub()

  val nodes: ListInitStub<RepositoryTopic> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object RepositoryTopicEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<RepositoryTopic> by QType.stub()
}

data class RequestReviewsInput(private val pullRequestId: String, private val userIds: String,
    private val teamIds: String) : QInput {
  private var clientMutationId: String? = null
  private var union: Boolean? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun union(model: Boolean) = apply { union = model }
}

object RequestReviewsPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  val requestedReviewersEdge: InitStub<UserEdge> by QType.stub()
}

object ReviewDismissalAllowance : QSchemaType, Node {
  val actor: InitStub<ReviewDismissalAllowanceActor> by QType.stub()

  override val id: Stub<String> by QScalar.stub()

  val protectedBranch: InitStub<ProtectedBranch> by QType.stub()
}

object ReviewDismissalAllowanceActor : QSchemaType {
  val User: ListInitStub<User> by QTypeList.stub()

  val Team: ListInitStub<Team> by QTypeList.stub()
}

object ReviewDismissalAllowanceConnection : QSchemaType {
  val edges: ListInitStub<ReviewDismissalAllowanceEdge> by QTypeList.stub()

  val nodes: ListInitStub<ReviewDismissalAllowance> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object ReviewDismissalAllowanceEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<ReviewDismissalAllowance> by QType.stub()
}

object ReviewDismissedEvent : QSchemaType, UniformResourceLocatable, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val message: Stub<String> by QScalar.stub()

  val messageHtml: Stub<HTML> by QScalar.stub()

  val previousReviewState: Stub<PullRequestReviewState> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  val pullRequestCommit: InitStub<PullRequestCommit> by QType.stub()

  override val resourcePath: Stub<URI> by QScalar.stub()

  val review: InitStub<PullRequestReview> by QType.stub()

  override val url: Stub<URI> by QScalar.stub()
}

object ReviewRequest : QSchemaType, Node {
  val databaseId: Stub<Int> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  val reviewer: InitStub<User> by QType.stub()
}

object ReviewRequestConnection : QSchemaType {
  val edges: ListInitStub<ReviewRequestEdge> by QTypeList.stub()

  val nodes: ListInitStub<ReviewRequest> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object ReviewRequestEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<ReviewRequest> by QType.stub()
}

object ReviewRequestRemovedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  val subject: InitStub<User> by QType.stub()
}

object ReviewRequestedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val pullRequest: InitStub<PullRequest> by QType.stub()

  val subject: InitStub<User> by QType.stub()
}

object SearchResultItem : QSchemaType {
  val Issue: ListInitStub<Issue> by QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> by QTypeList.stub()

  val Repository: ListInitStub<Repository> by QTypeList.stub()

  val User: ListInitStub<User> by QTypeList.stub()

  val Organization: ListInitStub<Organization> by QTypeList.stub()
}

object SearchResultItemConnection : QSchemaType {
  val codeCount: Stub<Int> by QScalar.stub()

  val edges: ListInitStub<SearchResultItemEdge> by QTypeList.stub()

  val issueCount: Stub<Int> by QScalar.stub()

  val nodes: ListInitStub<SearchResultItem> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val repositoryCount: Stub<Int> by QScalar.stub()

  val userCount: Stub<Int> by QScalar.stub()

  val wikiCount: Stub<Int> by QScalar.stub()
}

object SearchResultItemEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<SearchResultItem> by QType.stub()
}

enum class SearchType : QSchemaType {
  ISSUE,

  REPOSITORY,

  USER
}

object SmimeSignature : QSchemaType, GitSignature {
  override val email: Stub<String> by QScalar.stub()

  override val isValid: Stub<Boolean> by QScalar.stub()

  override val payload: Stub<String> by QScalar.stub()

  override val signature: Stub<String> by QScalar.stub()

  override val signer: InitStub<User> by QType.stub()

  override val state: Stub<GitSignatureState> by QScalar.stub()
}

data class StarOrder(private val field: StarOrderField,
    private val direction: OrderDirection) : QInput

enum class StarOrderField : QSchemaType {
  STARRED_AT
}

object StargazerConnection : QSchemaType {
  val edges: ListInitStub<StargazerEdge> by QTypeList.stub()

  val nodes: ListInitStub<User> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object StargazerEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<User> by QType.stub()

  val starredAt: Stub<DateTime> by QScalar.stub()
}

interface Starrable : QSchemaType {
  val id: Stub<String>

  val stargazers: QTypeConfigStub<StargazerConnection, StargazersArgs>

  val viewerHasStarred: Stub<Boolean>

  class StargazersArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): StargazersArgs = apply { addArg("first", value) }


    fun after(value: String): StargazersArgs = apply { addArg("after", value) }


    fun last(value: Int): StargazersArgs = apply { addArg("last", value) }


    fun before(value: String): StargazersArgs = apply { addArg("before", value) }


    fun orderBy(value: StarOrder): StargazersArgs = apply { addArg("orderBy", value) }

  }
}

object StarredRepositoryConnection : QSchemaType {
  val edges: ListInitStub<StarredRepositoryEdge> by QTypeList.stub()

  val nodes: ListInitStub<Repository> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object StarredRepositoryEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Repository> by QType.stub()

  val starredAt: Stub<DateTime> by QScalar.stub()
}

object Status : QSchemaType, Node {
  val commit: InitStub<Commit> by QType.stub()

  val context: QTypeConfigStub<StatusContext, ContextArgs> by QType.configStub { ContextArgs(it) }

  val contexts: ListInitStub<StatusContext> by QTypeList.stub()

  override val id: Stub<String> by QScalar.stub()

  val state: Stub<StatusState> by QScalar.stub()

  class ContextArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun name(value: String): ContextArgs = apply { addArg("name", value) }

  }
}

object StatusContext : QSchemaType, Node {
  val commit: InitStub<Commit> by QType.stub()

  val context: Stub<String> by QScalar.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  val creator: InitStub<Actor> by QType.stub()

  val description: Stub<String> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val state: Stub<StatusState> by QScalar.stub()

  val targetUrl: Stub<URI> by QScalar.stub()
}

enum class StatusState : QSchemaType {
  EXPECTED,

  ERROR,

  FAILURE,

  PENDING,

  SUCCESS
}

data class SubmitPullRequestReviewInput(private val pullRequestReviewId: String,
    private val event: PullRequestReviewEvent) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun body(model: String) = apply { body = model }
}

object SubmitPullRequestReviewPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val pullRequestReview: InitStub<PullRequestReview> by QType.stub()
}

interface Subscribable : QSchemaType {
  val viewerCanSubscribe: Stub<Boolean>

  val viewerSubscription: Stub<SubscriptionState>
}

object SubscribedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val subscribable: InitStub<Subscribable> by QType.stub()
}

enum class SubscriptionState : QSchemaType {
  UNSUBSCRIBED,

  SUBSCRIBED,

  IGNORED
}

object SuggestedReviewer : QSchemaType {
  val isAuthor: Stub<Boolean> by QScalar.stub()

  val isCommenter: Stub<Boolean> by QScalar.stub()

  val reviewer: InitStub<User> by QType.stub()
}

object Tag : QSchemaType, GitObject, Node {
  override val abbreviatedOid: Stub<String> by QScalar.stub()

  override val commitResourcePath: Stub<URI> by QScalar.stub()

  override val commitUrl: Stub<URI> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val message: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  override val oid: Stub<GitObjectID> by QScalar.stub()

  override val repository: InitStub<Repository> by QType.stub()

  val tagger: InitStub<GitActor> by QType.stub()

  val target: InitStub<GitObject> by QType.stub()
}

object Team : QSchemaType, Node {
  val description: Stub<String> by QScalar.stub()

  val editTeamResourcePath: Stub<URI> by QScalar.stub()

  val editTeamUrl: Stub<URI> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val invitations: QTypeConfigStub<OrganizationInvitationConnection, InvitationsArgs> by QType.configStub { InvitationsArgs(it) }

  val name: Stub<String> by QScalar.stub()

  val organization: InitStub<Organization> by QType.stub()

  val privacy: Stub<TeamPrivacy> by QScalar.stub()

  val resourcePath: Stub<URI> by QScalar.stub()

  val slug: Stub<String> by QScalar.stub()

  val url: Stub<URI> by QScalar.stub()

  class InvitationsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): InvitationsArgs = apply { addArg("first", value) }


    fun after(value: String): InvitationsArgs = apply { addArg("after", value) }


    fun last(value: Int): InvitationsArgs = apply { addArg("last", value) }


    fun before(value: String): InvitationsArgs = apply { addArg("before", value) }

  }
}

object TeamConnection : QSchemaType {
  val edges: ListInitStub<TeamEdge> by QTypeList.stub()

  val nodes: ListInitStub<Team> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object TeamEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<Team> by QType.stub()
}

data class TeamOrder(private val field: TeamOrderField,
    private val direction: OrderDirection) : QInput

enum class TeamOrderField : QSchemaType {
  NAME
}

enum class TeamPrivacy : QSchemaType {
  SECRET,

  VISIBLE
}

enum class TeamRole : QSchemaType {
  ADMIN,

  MEMBER
}

object Topic : QSchemaType, Node {
  override val id: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val relatedTopics: ListInitStub<Topic> by QTypeList.stub()
}

enum class TopicSuggestionDeclineReason : QSchemaType {
  NOT_RELEVANT,

  TOO_SPECIFIC,

  PERSONAL_PREFERENCE,

  TOO_GENERAL
}

object Tree : QSchemaType, GitObject, Node {
  override val abbreviatedOid: Stub<String> by QScalar.stub()

  override val commitResourcePath: Stub<URI> by QScalar.stub()

  override val commitUrl: Stub<URI> by QScalar.stub()

  val entries: ListInitStub<TreeEntry> by QTypeList.stub()

  override val id: Stub<String> by QScalar.stub()

  override val oid: Stub<GitObjectID> by QScalar.stub()

  override val repository: InitStub<Repository> by QType.stub()
}

object TreeEntry : QSchemaType {
  val mode: Stub<Int> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val objectVal: InitStub<GitObject> by QType.stub()

  val oid: Stub<GitObjectID> by QScalar.stub()

  val repository: InitStub<Repository> by QType.stub()

  val type: Stub<String> by QScalar.stub()
}

object URI : QSchemaType {
  val value: Stub<String> by QScalar.stub()
}

object UnassignedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val assignable: InitStub<Assignable> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val user: InitStub<User> by QType.stub()
}

interface UniformResourceLocatable : QSchemaType {
  val resourcePath: Stub<URI>

  val url: Stub<URI>
}

object UnknownSignature : QSchemaType, GitSignature {
  override val email: Stub<String> by QScalar.stub()

  override val isValid: Stub<Boolean> by QScalar.stub()

  override val payload: Stub<String> by QScalar.stub()

  override val signature: Stub<String> by QScalar.stub()

  override val signer: InitStub<User> by QType.stub()

  override val state: Stub<GitSignatureState> by QScalar.stub()
}

object UnlabeledEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val label: InitStub<Label> by QType.stub()

  val labelable: InitStub<Labelable> by QType.stub()
}

object UnlockedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val lockable: InitStub<Lockable> by QType.stub()
}

object UnsubscribedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> by QType.stub()

  val createdAt: Stub<DateTime> by QScalar.stub()

  override val id: Stub<String> by QScalar.stub()

  val subscribable: InitStub<Subscribable> by QType.stub()
}

interface Updatable : QSchemaType {
  val viewerCanUpdate: Stub<Boolean>
}

interface UpdatableComment : QSchemaType {
  val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason>
}

data class UpdateProjectCardInput(private val projectCardId: String,
    private val note: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateProjectCardPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val projectCard: InitStub<ProjectCard> by QType.stub()
}

data class UpdateProjectColumnInput(private val projectColumnId: String,
    private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateProjectColumnPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val projectColumn: InitStub<ProjectColumn> by QType.stub()
}

data class UpdateProjectInput(private val projectId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  private var state: ProjectState? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun body(model: String) = apply { body = model }

  fun state(model: ProjectState) = apply { state = model }
}

object UpdateProjectPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val project: InitStub<Project> by QType.stub()
}

data class UpdatePullRequestReviewCommentInput(private val pullRequestReviewCommentId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdatePullRequestReviewCommentPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val pullRequestReviewComment: InitStub<PullRequestReviewComment> by QType.stub()
}

data class UpdatePullRequestReviewInput(private val pullRequestReviewId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdatePullRequestReviewPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val pullRequestReview: InitStub<PullRequestReview> by QType.stub()
}

data class UpdateSubscriptionInput(private val subscribableId: String,
    private val state: SubscriptionState) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateSubscriptionPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val subscribable: InitStub<Subscribable> by QType.stub()
}

data class UpdateTopicsInput(private val repositoryId: String,
    private val topicNames: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateTopicsPayload : QSchemaType {
  val clientMutationId: Stub<String> by QScalar.stub()

  val invalidTopicNames: ListStub<String> by QScalarList.stub()

  val repository: InitStub<Repository> by QType.stub()
}

object User : QSchemaType, UniformResourceLocatable, RepositoryOwner, Actor, Node {
  override val avatarUrl: QConfigStub<URI, AvatarUrlArgs> by QScalar.configStub { AvatarUrlArgs(it) }

  val bio: Stub<String> by QScalar.stub()

  val bioHTML: Stub<HTML> by QScalar.stub()

  val company: Stub<String> by QScalar.stub()

  val companyHTML: Stub<HTML> by QScalar.stub()

  val contributedRepositories: QTypeConfigStub<RepositoryConnection, ContributedRepositoriesArgs> by QType.configStub { ContributedRepositoriesArgs(it) }

  val createdAt: Stub<DateTime> by QScalar.stub()

  val databaseId: Stub<Int> by QScalar.stub()

  val email: Stub<String> by QScalar.stub()

  val followers: QTypeConfigStub<FollowerConnection, FollowersArgs> by QType.configStub { FollowersArgs(it) }

  val following: QTypeConfigStub<FollowingConnection, FollowingArgs> by QType.configStub { FollowingArgs(it) }

  val gist: QTypeConfigStub<Gist, GistArgs> by QType.configStub { GistArgs(it) }

  val gists: QTypeConfigStub<GistConnection, GistsArgs> by QType.configStub { GistsArgs(it) }

  override val id: Stub<String> by QScalar.stub()

  val isBountyHunter: Stub<Boolean> by QScalar.stub()

  val isCampusExpert: Stub<Boolean> by QScalar.stub()

  val isDeveloperProgramMember: Stub<Boolean> by QScalar.stub()

  val isEmployee: Stub<Boolean> by QScalar.stub()

  val isHireable: Stub<Boolean> by QScalar.stub()

  val isInvoiced: Stub<Boolean> by QScalar.stub()

  val isSiteAdmin: Stub<Boolean> by QScalar.stub()

  val isViewer: Stub<Boolean> by QScalar.stub()

  val issues: QTypeConfigStub<IssueConnection, IssuesArgs> by QType.configStub { IssuesArgs(it) }

  val location: Stub<String> by QScalar.stub()

  override val login: Stub<String> by QScalar.stub()

  val name: Stub<String> by QScalar.stub()

  val organization: QTypeConfigStub<Organization, OrganizationArgs> by QType.configStub { OrganizationArgs(it) }

  val organizations: QTypeConfigStub<OrganizationConnection, OrganizationsArgs> by QType.configStub { OrganizationsArgs(it) }

  override val pinnedRepositories: QTypeConfigStub<RepositoryConnection, RepositoryOwner.PinnedRepositoriesArgs> by QType.configStub { RepositoryOwner.PinnedRepositoriesArgs(it) }

  val pullRequests: QTypeConfigStub<PullRequestConnection, PullRequestsArgs> by QType.configStub { PullRequestsArgs(it) }

  override val repositories: QTypeConfigStub<RepositoryConnection, RepositoryOwner.RepositoriesArgs> by QType.configStub { RepositoryOwner.RepositoriesArgs(it) }

  override val repository: QTypeConfigStub<Repository, RepositoryOwner.RepositoryArgs> by QType.configStub { RepositoryOwner.RepositoryArgs(it) }

  override val resourcePath: Stub<URI> by QScalar.stub()

  val starredRepositories: QTypeConfigStub<StarredRepositoryConnection, StarredRepositoriesArgs> by QType.configStub { StarredRepositoriesArgs(it) }

  val updatedAt: Stub<DateTime> by QScalar.stub()

  override val url: Stub<URI> by QScalar.stub()

  val viewerCanFollow: Stub<Boolean> by QScalar.stub()

  val viewerIsFollowing: Stub<Boolean> by QScalar.stub()

  val watching: QTypeConfigStub<RepositoryConnection, WatchingArgs> by QType.configStub { WatchingArgs(it) }

  val websiteUrl: Stub<URI> by QScalar.stub()

  class AvatarUrlArgs(args: ArgBuilder) : BaseAvatarUrlArgs(args) {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }

  class ContributedRepositoriesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): ContributedRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): ContributedRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): ContributedRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): ContributedRepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): ContributedRepositoriesArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): ContributedRepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): ContributedRepositoriesArgs = apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): ContributedRepositoriesArgs = apply { addArg("isLocked", value) }

  }

  class FollowersArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): FollowersArgs = apply { addArg("first", value) }


    fun after(value: String): FollowersArgs = apply { addArg("after", value) }


    fun last(value: Int): FollowersArgs = apply { addArg("last", value) }


    fun before(value: String): FollowersArgs = apply { addArg("before", value) }

  }

  class FollowingArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): FollowingArgs = apply { addArg("first", value) }


    fun after(value: String): FollowingArgs = apply { addArg("after", value) }


    fun last(value: Int): FollowingArgs = apply { addArg("last", value) }


    fun before(value: String): FollowingArgs = apply { addArg("before", value) }

  }

  class GistArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun name(value: String): GistArgs = apply { addArg("name", value) }

  }

  class GistsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): GistsArgs = apply { addArg("first", value) }


    fun after(value: String): GistsArgs = apply { addArg("after", value) }


    fun last(value: Int): GistsArgs = apply { addArg("last", value) }


    fun before(value: String): GistsArgs = apply { addArg("before", value) }


    fun privacy(value: GistPrivacy): GistsArgs = apply { addArg("privacy", value) }

  }

  class IssuesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class OrganizationArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun login(value: String): OrganizationArgs = apply { addArg("login", value) }

  }

  class OrganizationsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): OrganizationsArgs = apply { addArg("first", value) }


    fun after(value: String): OrganizationsArgs = apply { addArg("after", value) }


    fun last(value: Int): OrganizationsArgs = apply { addArg("last", value) }


    fun before(value: String): OrganizationsArgs = apply { addArg("before", value) }

  }

  class PullRequestsArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): PullRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): PullRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): PullRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): PullRequestsArgs = apply { addArg("before", value) }


    fun states(value: PullRequestState): PullRequestsArgs = apply { addArg("states", value) }


    fun labels(value: String): PullRequestsArgs = apply { addArg("labels", value) }


    fun headRefName(value: String): PullRequestsArgs = apply { addArg("headRefName", value) }


    fun baseRefName(value: String): PullRequestsArgs = apply { addArg("baseRefName", value) }


    fun orderBy(value: IssueOrder): PullRequestsArgs = apply { addArg("orderBy", value) }

  }

  class StarredRepositoriesArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): StarredRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): StarredRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): StarredRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): StarredRepositoriesArgs = apply { addArg("before", value) }


    fun ownedByViewer(value: Boolean): StarredRepositoriesArgs = apply { addArg("ownedByViewer", value) }


    fun orderBy(value: StarOrder): StarredRepositoriesArgs = apply { addArg("orderBy", value) }

  }

  class WatchingArgs(args: TypeArgBuilder) : TypeArgBuilder by args {
    fun first(value: Int): WatchingArgs = apply { addArg("first", value) }


    fun after(value: String): WatchingArgs = apply { addArg("after", value) }


    fun last(value: Int): WatchingArgs = apply { addArg("last", value) }


    fun before(value: String): WatchingArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): WatchingArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): WatchingArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): WatchingArgs = apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): WatchingArgs = apply { addArg("isLocked", value) }

  }
}

object UserConnection : QSchemaType {
  val edges: ListInitStub<UserEdge> by QTypeList.stub()

  val nodes: ListInitStub<User> by QTypeList.stub()

  val pageInfo: InitStub<PageInfo> by QType.stub()

  val totalCount: Stub<Int> by QScalar.stub()
}

object UserEdge : QSchemaType {
  val cursor: Stub<String> by QScalar.stub()

  val node: InitStub<User> by QType.stub()
}

object X509Certificate : QSchemaType {
  val value: Stub<String> by QScalar.stub()
}

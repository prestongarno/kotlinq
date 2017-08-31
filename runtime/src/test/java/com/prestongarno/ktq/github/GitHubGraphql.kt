@file:Suppress("unused")

package com.prestongarno.ktq.github

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.InitStub
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

abstract class BaseAvatarUrlArgs(args: ArgBuilder = ArgBuilder.create<URI, BaseAvatarUrlArgs>()) : ArgBuilder by args

data class AcceptTopicSuggestionInput(private val repositoryId: String,
    private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AcceptTopicSuggestionPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val topic: InitStub<Topic> = QType.stub()
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
  val clientMutationId: Stub<String> = QScalar.stub()

  val commentEdge: InitStub<IssueCommentEdge> = QType.stub()

  val subject: InitStub<Node> = QType.stub()

  val timelineEdge: InitStub<IssueTimelineItemEdge> = QType.stub()
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
  val cardEdge: InitStub<ProjectCardEdge> = QType.stub()

  val clientMutationId: Stub<String> = QScalar.stub()

  val projectColumn: InitStub<Project> = QType.stub()
}

data class AddProjectColumnInput(private val projectId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddProjectColumnPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val columnEdge: InitStub<ProjectColumnEdge> = QType.stub()

  val project: InitStub<Project> = QType.stub()
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
  val clientMutationId: Stub<String> = QScalar.stub()

  val comment: InitStub<PullRequestReviewComment> = QType.stub()

  val commentEdge: InitStub<PullRequestReviewCommentEdge> = QType.stub()
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
  val clientMutationId: Stub<String> = QScalar.stub()

  val pullRequestReview: InitStub<PullRequestReview> = QType.stub()

  val reviewEdge: InitStub<PullRequestReviewEdge> = QType.stub()
}

data class AddReactionInput(private val subjectId: String,
    private val content: ReactionContent) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddReactionPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val reaction: InitStub<Reaction> = QType.stub()

  val subject: InitStub<Reactable> = QType.stub()
}

data class AddStarInput(private val starrableId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddStarPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val starrable: InitStub<Starrable> = QType.stub()
}

object AddedToProjectEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()
}

interface Assignable : QSchemaType {
  val assignees: QTypeConfigStub<UserConnection, AssigneesArgs>

  class AssigneesArgs(args: TypeArgBuilder = TypeArgBuilder.create<UserConnection, AssigneesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): AssigneesArgs = apply { addArg("first", value) }


    fun after(value: String): AssigneesArgs = apply { addArg("after", value) }


    fun last(value: Int): AssigneesArgs = apply { addArg("last", value) }


    fun before(value: String): AssigneesArgs = apply { addArg("before", value) }

  }
}

object AssignedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val assignable: InitStub<Assignable> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val user: InitStub<User> = QType.stub()
}

object BaseRefChangedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()
}

object BaseRefForcePushedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val afterCommit: InitStub<Commit> = QType.stub()

  val beforeCommit: InitStub<Commit> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  val ref: InitStub<Ref> = QType.stub()
}

object Blame : QSchemaType {
  val ranges: ListInitStub<BlameRange> = QTypeList.stub()
}

object BlameRange : QSchemaType {
  val age: Stub<Int> = QScalar.stub()

  val commit: InitStub<Commit> = QType.stub()

  val endingLine: Stub<Int> = QScalar.stub()

  val startingLine: Stub<Int> = QScalar.stub()
}

object Blob : QSchemaType, GitObject, Node {
  override val abbreviatedOid: Stub<String> = QScalar.stub()

  val byteSize: Stub<Int> = QScalar.stub()

  override val commitResourcePath: Stub<URI> = QScalar.stub()

  override val commitUrl: Stub<URI> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val isBinary: Stub<Boolean> = QScalar.stub()

  val isTruncated: Stub<Boolean> = QScalar.stub()

  override val oid: Stub<GitObjectID> = QScalar.stub()

  override val repository: InitStub<Repository> = QType.stub()

  val text: Stub<String> = QScalar.stub()
}

object Bot : QSchemaType, UniformResourceLocatable, Actor, Node {
  override val avatarUrl: QConfigStub<URI, AvatarUrlArgs> = QScalar.configStub(AvatarUrlArgs())

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  override val login: Stub<String> = QScalar.stub()

  override val resourcePath: Stub<URI> = QScalar.stub()

  override val url: Stub<URI> = QScalar.stub()

  class AvatarUrlArgs(args: ArgBuilder = ArgBuilder.create<URI, AvatarUrlArgs>()) : BaseAvatarUrlArgs(args) {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }
}

interface Closable : QSchemaType {
  val closed: Stub<Boolean>
}

object ClosedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val closable: InitStub<Closable> = QType.stub()

  val commit: InitStub<Commit> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()
}

object CodeOfConduct : QSchemaType {
  val body: Stub<String> = QScalar.stub()

  val key: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val url: Stub<URI> = QScalar.stub()
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
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()
}

object Commit : QSchemaType, Subscribable, GitObject, Node {
  override val abbreviatedOid: Stub<String> = QScalar.stub()

  val author: InitStub<GitActor> = QType.stub()

  val authoredByCommitter: Stub<Boolean> = QScalar.stub()

  val blame: QTypeConfigStub<Blame, BlameArgs> = QType.configStub(BlameArgs())

  val comments: QTypeConfigStub<CommitCommentConnection, CommentsArgs> = QType.configStub(CommentsArgs())

  override val commitResourcePath: Stub<URI> = QScalar.stub()

  override val commitUrl: Stub<URI> = QScalar.stub()

  val committedDate: Stub<DateTime> = QScalar.stub()

  val committedViaWeb: Stub<Boolean> = QScalar.stub()

  val committer: InitStub<GitActor> = QType.stub()

  val history: QTypeConfigStub<CommitHistoryConnection, HistoryArgs> = QType.configStub(HistoryArgs())

  override val id: Stub<String> = QScalar.stub()

  val message: Stub<String> = QScalar.stub()

  val messageBody: Stub<String> = QScalar.stub()

  val messageBodyHTML: Stub<HTML> = QScalar.stub()

  val messageHeadline: Stub<String> = QScalar.stub()

  val messageHeadlineHTML: Stub<HTML> = QScalar.stub()

  override val oid: Stub<GitObjectID> = QScalar.stub()

  override val repository: InitStub<Repository> = QType.stub()

  val resourcePath: Stub<URI> = QScalar.stub()

  val signature: InitStub<GitSignature> = QType.stub()

  val status: InitStub<Status> = QType.stub()

  val tree: InitStub<Tree> = QType.stub()

  val treeResourcePath: Stub<URI> = QScalar.stub()

  val treeUrl: Stub<URI> = QScalar.stub()

  val url: Stub<URI> = QScalar.stub()

  override val viewerCanSubscribe: Stub<Boolean> = QScalar.stub()

  override val viewerSubscription: Stub<SubscriptionState> = QScalar.stub()

  class BlameArgs(args: TypeArgBuilder = TypeArgBuilder.create<Blame, BlameArgs>()) : TypeArgBuilder by args {
    fun path(value: String): BlameArgs = apply { addArg("path", value) }

  }

  class CommentsArgs(args: TypeArgBuilder = TypeArgBuilder.create<CommitCommentConnection, CommentsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class HistoryArgs(args: TypeArgBuilder = TypeArgBuilder.create<CommitHistoryConnection, HistoryArgs>()) : TypeArgBuilder by args {
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
  override val author: InitStub<Actor> = QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> = QScalar.stub()

  override val body: Stub<String> = QScalar.stub()

  override val bodyHTML: Stub<HTML> = QScalar.stub()

  val commit: InitStub<Commit> = QType.stub()

  override val createdAt: Stub<DateTime> = QScalar.stub()

  override val createdViaEmail: Stub<Boolean> = QScalar.stub()

  override val databaseId: Stub<Int> = QScalar.stub()

  override val editor: InitStub<Actor> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  override val lastEditedAt: Stub<DateTime> = QScalar.stub()

  val path: Stub<String> = QScalar.stub()

  val position: Stub<Int> = QScalar.stub()

  override val publishedAt: Stub<DateTime> = QScalar.stub()

  override val reactionGroups: ListInitStub<ReactionGroup> = QTypeList.stub()

  override val reactions: QTypeConfigStub<ReactionConnection, Reactable.ReactionsArgs> = QType.configStub(Reactable.ReactionsArgs())

  override val repository: InitStub<Repository> = QType.stub()

  override val updatedAt: Stub<DateTime> = QScalar.stub()

  override val viewerCanDelete: Stub<Boolean> = QScalar.stub()

  override val viewerCanReact: Stub<Boolean> = QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> = QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> = QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> = QScalar.stub()
}

object CommitCommentConnection : QSchemaType {
  val edges: ListInitStub<CommitCommentEdge> = QTypeList.stub()

  val nodes: ListInitStub<CommitComment> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object CommitCommentEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<CommitComment> = QType.stub()
}

object CommitCommentThread : QSchemaType, RepositoryNode, Node {
  val comments: QTypeConfigStub<CommitCommentConnection, CommentsArgs> = QType.configStub(CommentsArgs())

  val commit: InitStub<Commit> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  val path: Stub<String> = QScalar.stub()

  val position: Stub<Int> = QScalar.stub()

  override val repository: InitStub<Repository> = QType.stub()

  class CommentsArgs(args: TypeArgBuilder = TypeArgBuilder.create<CommitCommentConnection, CommentsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

object CommitEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Commit> = QType.stub()
}

object CommitHistoryConnection : QSchemaType {
  val edges: ListInitStub<CommitEdge> = QTypeList.stub()

  val nodes: ListInitStub<Commit> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()
}

object ConvertedNoteToIssueEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()
}

data class CreateProjectInput(private val ownerId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun body(model: String) = apply { body = model }
}

object CreateProjectPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val project: InitStub<Project> = QType.stub()
}

object DateTime : QSchemaType {
  val value: Stub<String> = QScalar.stub()
}

data class DeclineTopicSuggestionInput(private val repositoryId: String, private val name: String,
    private val reason: TopicSuggestionDeclineReason) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeclineTopicSuggestionPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val topic: InitStub<Topic> = QType.stub()
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
  val clientMutationId: Stub<String> = QScalar.stub()

  val column: InitStub<ProjectColumn> = QType.stub()

  val deletedCardId: Stub<String> = QScalar.stub()
}

data class DeleteProjectColumnInput(private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeleteProjectColumnPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val deletedColumnId: Stub<String> = QScalar.stub()

  val project: InitStub<Project> = QType.stub()
}

data class DeleteProjectInput(private val projectId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeleteProjectPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val owner: InitStub<ProjectOwner> = QType.stub()
}

data class DeletePullRequestReviewInput(private val pullRequestReviewId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeletePullRequestReviewPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val pullRequestReview: InitStub<PullRequestReview> = QType.stub()
}

object DemilestonedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val milestoneTitle: Stub<String> = QScalar.stub()

  val subject: InitStub<MilestoneItem> = QType.stub()
}

object DeployedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  val deployment: InitStub<Deployment> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  val ref: InitStub<Ref> = QType.stub()
}

object Deployment : QSchemaType, Node {
  val commit: InitStub<Commit> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val creator: InitStub<Actor> = QType.stub()

  val environment: Stub<String> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val latestStatus: InitStub<DeploymentStatus> = QType.stub()

  val repository: InitStub<Repository> = QType.stub()

  val state: Stub<DeploymentState> = QScalar.stub()

  val statuses: QTypeConfigStub<DeploymentStatusConnection, StatusesArgs> = QType.configStub(StatusesArgs())

  class StatusesArgs(args: TypeArgBuilder = TypeArgBuilder.create<DeploymentStatusConnection, StatusesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): StatusesArgs = apply { addArg("first", value) }


    fun after(value: String): StatusesArgs = apply { addArg("after", value) }


    fun last(value: Int): StatusesArgs = apply { addArg("last", value) }


    fun before(value: String): StatusesArgs = apply { addArg("before", value) }

  }
}

object DeploymentConnection : QSchemaType {
  val edges: ListInitStub<DeploymentEdge> = QTypeList.stub()

  val nodes: ListInitStub<Deployment> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object DeploymentEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Deployment> = QType.stub()
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
  val creator: InitStub<Actor> = QType.stub()

  val deployment: InitStub<Deployment> = QType.stub()

  val description: Stub<String> = QScalar.stub()

  val environmentUrl: Stub<URI> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val logUrl: Stub<URI> = QScalar.stub()

  val state: Stub<DeploymentStatusState> = QScalar.stub()
}

object DeploymentStatusConnection : QSchemaType {
  val edges: ListInitStub<DeploymentStatusEdge> = QTypeList.stub()

  val nodes: ListInitStub<DeploymentStatus> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object DeploymentStatusEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<DeploymentStatus> = QType.stub()
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
  val clientMutationId: Stub<String> = QScalar.stub()

  val pullRequestReview: InitStub<PullRequestReview> = QType.stub()
}

data class DraftPullRequestReviewComment(private val path: String, private val position: Int,
    private val body: String) : QInput

object ExternalIdentity : QSchemaType, Node {
  val guid: Stub<String> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val organizationInvitation: InitStub<OrganizationInvitation> = QType.stub()

  val samlIdentity: InitStub<ExternalIdentitySamlAttributes> = QType.stub()

  val scimIdentity: InitStub<ExternalIdentityScimAttributes> = QType.stub()

  val user: InitStub<User> = QType.stub()
}

object ExternalIdentityConnection : QSchemaType {
  val edges: ListInitStub<ExternalIdentityEdge> = QTypeList.stub()

  val nodes: ListInitStub<ExternalIdentity> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object ExternalIdentityEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<ExternalIdentity> = QType.stub()
}

object ExternalIdentitySamlAttributes : QSchemaType {
  val nameId: Stub<String> = QScalar.stub()
}

object ExternalIdentityScimAttributes : QSchemaType {
  val username: Stub<String> = QScalar.stub()
}

object FollowerConnection : QSchemaType {
  val edges: ListInitStub<UserEdge> = QTypeList.stub()

  val nodes: ListInitStub<User> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object FollowingConnection : QSchemaType {
  val edges: ListInitStub<UserEdge> = QTypeList.stub()

  val nodes: ListInitStub<User> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object Gist : QSchemaType, Starrable, Node {
  val comments: QTypeConfigStub<GistCommentConnection, CommentsArgs> = QType.configStub(CommentsArgs())

  val createdAt: Stub<DateTime> = QScalar.stub()

  val description: Stub<String> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val isPublic: Stub<Boolean> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val owner: InitStub<RepositoryOwner> = QType.stub()

  override val stargazers: QTypeConfigStub<StargazerConnection, Starrable.StargazersArgs> = QType.configStub(Starrable.StargazersArgs())

  val updatedAt: Stub<DateTime> = QScalar.stub()

  override val viewerHasStarred: Stub<Boolean> = QScalar.stub()

  class CommentsArgs(args: TypeArgBuilder = TypeArgBuilder.create<GistCommentConnection, CommentsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

object GistComment : QSchemaType, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> = QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> = QScalar.stub()

  override val body: Stub<String> = QScalar.stub()

  override val bodyHTML: Stub<HTML> = QScalar.stub()

  override val createdAt: Stub<DateTime> = QScalar.stub()

  override val createdViaEmail: Stub<Boolean> = QScalar.stub()

  override val editor: InitStub<Actor> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  override val lastEditedAt: Stub<DateTime> = QScalar.stub()

  override val publishedAt: Stub<DateTime> = QScalar.stub()

  override val updatedAt: Stub<DateTime> = QScalar.stub()

  override val viewerCanDelete: Stub<Boolean> = QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> = QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> = QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> = QScalar.stub()
}

object GistCommentConnection : QSchemaType {
  val edges: ListInitStub<GistCommentEdge> = QTypeList.stub()

  val nodes: ListInitStub<GistComment> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object GistCommentEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<GistComment> = QType.stub()
}

object GistConnection : QSchemaType {
  val edges: ListInitStub<GistEdge> = QTypeList.stub()

  val nodes: ListInitStub<Gist> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object GistEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Gist> = QType.stub()
}

enum class GistPrivacy : QSchemaType {
  PUBLIC,

  SECRET,

  ALL
}

object GitActor : QSchemaType {
  val avatarUrl: QConfigStub<URI, AvatarUrlArgs> = QScalar.configStub(AvatarUrlArgs())

  val date: Stub<GitTimestamp> = QScalar.stub()

  val email: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val user: InitStub<User> = QType.stub()

  class AvatarUrlArgs(args: ArgBuilder = ArgBuilder.create<URI, AvatarUrlArgs>()) : ArgBuilder by args {
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
  val value: Stub<String> = QScalar.stub()
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
  val value: Stub<String> = QScalar.stub()
}

object GpgSignature : QSchemaType, GitSignature {
  override val email: Stub<String> = QScalar.stub()

  override val isValid: Stub<Boolean> = QScalar.stub()

  val keyId: Stub<String> = QScalar.stub()

  override val payload: Stub<String> = QScalar.stub()

  override val signature: Stub<String> = QScalar.stub()

  override val signer: InitStub<User> = QType.stub()

  override val state: Stub<GitSignatureState> = QScalar.stub()
}

object HTML : QSchemaType {
  val value: Stub<String> = QScalar.stub()
}

object HeadRefDeletedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val headRef: InitStub<Ref> = QType.stub()

  val headRefName: Stub<String> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()
}

object HeadRefForcePushedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val afterCommit: InitStub<Commit> = QType.stub()

  val beforeCommit: InitStub<Commit> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  val ref: InitStub<Ref> = QType.stub()
}

object HeadRefRestoredEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()
}

object Issue : QSchemaType, UniformResourceLocatable, Subscribable, RepositoryNode, Reactable, Lockable, Labelable, UpdatableComment, Updatable, Comment, Closable, Assignable, Node {
  override val assignees: QTypeConfigStub<UserConnection, Assignable.AssigneesArgs> = QType.configStub(Assignable.AssigneesArgs())

  override val author: InitStub<Actor> = QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> = QScalar.stub()

  override val body: Stub<String> = QScalar.stub()

  override val bodyHTML: Stub<HTML> = QScalar.stub()

  val bodyText: Stub<String> = QScalar.stub()

  override val closed: Stub<Boolean> = QScalar.stub()

  val comments: QTypeConfigStub<IssueCommentConnection, CommentsArgs> = QType.configStub(CommentsArgs())

  override val createdAt: Stub<DateTime> = QScalar.stub()

  override val createdViaEmail: Stub<Boolean> = QScalar.stub()

  override val databaseId: Stub<Int> = QScalar.stub()

  override val editor: InitStub<Actor> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  override val labels: QTypeConfigStub<LabelConnection, Labelable.LabelsArgs> = QType.configStub(Labelable.LabelsArgs())

  override val lastEditedAt: Stub<DateTime> = QScalar.stub()

  override val locked: Stub<Boolean> = QScalar.stub()

  val milestone: InitStub<Milestone> = QType.stub()

  val number: Stub<Int> = QScalar.stub()

  val participants: QTypeConfigStub<UserConnection, ParticipantsArgs> = QType.configStub(ParticipantsArgs())

  override val publishedAt: Stub<DateTime> = QScalar.stub()

  override val reactionGroups: ListInitStub<ReactionGroup> = QTypeList.stub()

  override val reactions: QTypeConfigStub<ReactionConnection, Reactable.ReactionsArgs> = QType.configStub(Reactable.ReactionsArgs())

  override val repository: InitStub<Repository> = QType.stub()

  override val resourcePath: Stub<URI> = QScalar.stub()

  val state: Stub<IssueState> = QScalar.stub()

  val timeline: QTypeConfigStub<IssueTimelineConnection, TimelineArgs> = QType.configStub(TimelineArgs())

  val title: Stub<String> = QScalar.stub()

  override val updatedAt: Stub<DateTime> = QScalar.stub()

  override val url: Stub<URI> = QScalar.stub()

  override val viewerCanReact: Stub<Boolean> = QScalar.stub()

  override val viewerCanSubscribe: Stub<Boolean> = QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> = QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> = QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> = QScalar.stub()

  override val viewerSubscription: Stub<SubscriptionState> = QScalar.stub()

  class CommentsArgs(args: TypeArgBuilder = TypeArgBuilder.create<IssueCommentConnection, CommentsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class ParticipantsArgs(args: TypeArgBuilder = TypeArgBuilder.create<UserConnection, ParticipantsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ParticipantsArgs = apply { addArg("first", value) }


    fun after(value: String): ParticipantsArgs = apply { addArg("after", value) }


    fun last(value: Int): ParticipantsArgs = apply { addArg("last", value) }


    fun before(value: String): ParticipantsArgs = apply { addArg("before", value) }

  }

  class TimelineArgs(args: TypeArgBuilder = TypeArgBuilder.create<IssueTimelineConnection, TimelineArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): TimelineArgs = apply { addArg("first", value) }


    fun after(value: String): TimelineArgs = apply { addArg("after", value) }


    fun last(value: Int): TimelineArgs = apply { addArg("last", value) }


    fun before(value: String): TimelineArgs = apply { addArg("before", value) }


    fun since(value: DateTime): TimelineArgs = apply { addArg("since", value) }

  }
}

object IssueComment : QSchemaType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> = QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> = QScalar.stub()

  override val body: Stub<String> = QScalar.stub()

  override val bodyHTML: Stub<HTML> = QScalar.stub()

  val bodyText: Stub<String> = QScalar.stub()

  override val createdAt: Stub<DateTime> = QScalar.stub()

  override val createdViaEmail: Stub<Boolean> = QScalar.stub()

  override val databaseId: Stub<Int> = QScalar.stub()

  override val editor: InitStub<Actor> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  val issue: InitStub<Issue> = QType.stub()

  override val lastEditedAt: Stub<DateTime> = QScalar.stub()

  override val publishedAt: Stub<DateTime> = QScalar.stub()

  override val reactionGroups: ListInitStub<ReactionGroup> = QTypeList.stub()

  override val reactions: QTypeConfigStub<ReactionConnection, Reactable.ReactionsArgs> = QType.configStub(Reactable.ReactionsArgs())

  override val repository: InitStub<Repository> = QType.stub()

  override val updatedAt: Stub<DateTime> = QScalar.stub()

  override val viewerCanDelete: Stub<Boolean> = QScalar.stub()

  override val viewerCanReact: Stub<Boolean> = QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> = QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> = QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> = QScalar.stub()
}

object IssueCommentConnection : QSchemaType {
  val edges: ListInitStub<IssueCommentEdge> = QTypeList.stub()

  val nodes: ListInitStub<IssueComment> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object IssueCommentEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<IssueComment> = QType.stub()
}

object IssueConnection : QSchemaType {
  val edges: ListInitStub<IssueEdge> = QTypeList.stub()

  val nodes: ListInitStub<Issue> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object IssueEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Issue> = QType.stub()
}

object IssueOrPullRequest : QSchemaType {
  val Issue: ListInitStub<Issue> = QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> = QTypeList.stub()
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
  val edges: ListInitStub<IssueTimelineItemEdge> = QTypeList.stub()

  val nodes: ListInitStub<IssueTimelineItem> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object IssueTimelineItem : QSchemaType {
  val Commit: ListInitStub<Commit> = QTypeList.stub()

  val IssueComment: ListInitStub<IssueComment> = QTypeList.stub()

  val ClosedEvent: ListInitStub<ClosedEvent> = QTypeList.stub()

  val ReopenedEvent: ListInitStub<ReopenedEvent> = QTypeList.stub()

  val SubscribedEvent: ListInitStub<SubscribedEvent> = QTypeList.stub()

  val UnsubscribedEvent: ListInitStub<UnsubscribedEvent> = QTypeList.stub()

  val ReferencedEvent: ListInitStub<ReferencedEvent> = QTypeList.stub()

  val AssignedEvent: ListInitStub<AssignedEvent> = QTypeList.stub()

  val UnassignedEvent: ListInitStub<UnassignedEvent> = QTypeList.stub()

  val LabeledEvent: ListInitStub<LabeledEvent> = QTypeList.stub()

  val UnlabeledEvent: ListInitStub<UnlabeledEvent> = QTypeList.stub()

  val MilestonedEvent: ListInitStub<MilestonedEvent> = QTypeList.stub()

  val DemilestonedEvent: ListInitStub<DemilestonedEvent> = QTypeList.stub()

  val RenamedTitleEvent: ListInitStub<RenamedTitleEvent> = QTypeList.stub()

  val LockedEvent: ListInitStub<LockedEvent> = QTypeList.stub()

  val UnlockedEvent: ListInitStub<UnlockedEvent> = QTypeList.stub()
}

object IssueTimelineItemEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<IssueTimelineItem> = QType.stub()
}

object Label : QSchemaType, Node {
  val color: Stub<String> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val issues: QTypeConfigStub<IssueConnection, IssuesArgs> = QType.configStub(IssuesArgs())

  val name: Stub<String> = QScalar.stub()

  val pullRequests: QTypeConfigStub<PullRequestConnection, PullRequestsArgs> = QType.configStub(PullRequestsArgs())

  val repository: InitStub<Repository> = QType.stub()

  class IssuesArgs(args: TypeArgBuilder = TypeArgBuilder.create<IssueConnection, IssuesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class PullRequestsArgs(args: TypeArgBuilder = TypeArgBuilder.create<PullRequestConnection, PullRequestsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): PullRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): PullRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): PullRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): PullRequestsArgs = apply { addArg("before", value) }

  }
}

object LabelConnection : QSchemaType {
  val edges: ListInitStub<LabelEdge> = QTypeList.stub()

  val nodes: ListInitStub<Label> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object LabelEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Label> = QType.stub()
}

interface Labelable : QSchemaType {
  val labels: QTypeConfigStub<LabelConnection, LabelsArgs>

  class LabelsArgs(args: TypeArgBuilder = TypeArgBuilder.create<LabelConnection, LabelsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }
}

object LabeledEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val label: InitStub<Label> = QType.stub()

  val labelable: InitStub<Labelable> = QType.stub()
}

object Language : QSchemaType, Node {
  val color: Stub<String> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()
}

object LanguageConnection : QSchemaType {
  val edges: ListInitStub<LanguageEdge> = QTypeList.stub()

  val nodes: ListInitStub<Language> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()

  val totalSize: Stub<Int> = QScalar.stub()
}

object LanguageEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Language> = QType.stub()

  val size: Stub<Int> = QScalar.stub()
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
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val lockable: InitStub<Lockable> = QType.stub()
}

object MentionedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()
}

enum class MergeableState : QSchemaType {
  MERGEABLE,

  CONFLICTING,

  UNKNOWN
}

object MergedEvent : QSchemaType, UniformResourceLocatable, Node {
  val actor: InitStub<Actor> = QType.stub()

  val commit: InitStub<Commit> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val mergeRef: InitStub<Ref> = QType.stub()

  val mergeRefName: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  override val resourcePath: Stub<URI> = QScalar.stub()

  override val url: Stub<URI> = QScalar.stub()
}

object Milestone : QSchemaType, UniformResourceLocatable, Node {
  val creator: InitStub<Actor> = QType.stub()

  val description: Stub<String> = QScalar.stub()

  val dueOn: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val number: Stub<Int> = QScalar.stub()

  val repository: InitStub<Repository> = QType.stub()

  override val resourcePath: Stub<URI> = QScalar.stub()

  val state: Stub<MilestoneState> = QScalar.stub()

  val title: Stub<String> = QScalar.stub()

  override val url: Stub<URI> = QScalar.stub()
}

object MilestoneConnection : QSchemaType {
  val edges: ListInitStub<MilestoneEdge> = QTypeList.stub()

  val nodes: ListInitStub<Milestone> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object MilestoneEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Milestone> = QType.stub()
}

object MilestoneItem : QSchemaType {
  val Issue: ListInitStub<Issue> = QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> = QTypeList.stub()
}

enum class MilestoneState : QSchemaType {
  OPEN,

  CLOSED
}

object MilestonedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val milestoneTitle: Stub<String> = QScalar.stub()

  val subject: InitStub<MilestoneItem> = QType.stub()
}

data class MoveProjectCardInput(private val cardId: String, private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  private var afterCardId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun afterCardId(model: String) = apply { afterCardId = model }
}

object MoveProjectCardPayload : QSchemaType {
  val cardEdge: InitStub<ProjectCardEdge> = QType.stub()

  val clientMutationId: Stub<String> = QScalar.stub()
}

data class MoveProjectColumnInput(private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  private var afterColumnId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun afterColumnId(model: String) = apply { afterColumnId = model }
}

object MoveProjectColumnPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val columnEdge: InitStub<ProjectColumnEdge> = QType.stub()
}

object MovedColumnsInProjectEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()
}

object Mutation : QSchemaType {
  val acceptTopicSuggestion: QTypeConfigStub<AcceptTopicSuggestionPayload, AcceptTopicSuggestionArgs> = QType.configStub(AcceptTopicSuggestionArgs())

  val addComment: QTypeConfigStub<AddCommentPayload, AddCommentArgs> = QType.configStub(AddCommentArgs())

  val addProjectCard: QTypeConfigStub<AddProjectCardPayload, AddProjectCardArgs> = QType.configStub(AddProjectCardArgs())

  val addProjectColumn: QTypeConfigStub<AddProjectColumnPayload, AddProjectColumnArgs> = QType.configStub(AddProjectColumnArgs())

  val addPullRequestReview: QTypeConfigStub<AddPullRequestReviewPayload, AddPullRequestReviewArgs> = QType.configStub(AddPullRequestReviewArgs())

  val addPullRequestReviewComment: QTypeConfigStub<AddPullRequestReviewCommentPayload, AddPullRequestReviewCommentArgs> = QType.configStub(AddPullRequestReviewCommentArgs())

  val addReaction: QTypeConfigStub<AddReactionPayload, AddReactionArgs> = QType.configStub(AddReactionArgs())

  val addStar: QTypeConfigStub<AddStarPayload, AddStarArgs> = QType.configStub(AddStarArgs())

  val createProject: QTypeConfigStub<CreateProjectPayload, CreateProjectArgs> = QType.configStub(CreateProjectArgs())

  val declineTopicSuggestion: QTypeConfigStub<DeclineTopicSuggestionPayload, DeclineTopicSuggestionArgs> = QType.configStub(DeclineTopicSuggestionArgs())

  val deleteProject: QTypeConfigStub<DeleteProjectPayload, DeleteProjectArgs> = QType.configStub(DeleteProjectArgs())

  val deleteProjectCard: QTypeConfigStub<DeleteProjectCardPayload, DeleteProjectCardArgs> = QType.configStub(DeleteProjectCardArgs())

  val deleteProjectColumn: QTypeConfigStub<DeleteProjectColumnPayload, DeleteProjectColumnArgs> = QType.configStub(DeleteProjectColumnArgs())

  val deletePullRequestReview: QTypeConfigStub<DeletePullRequestReviewPayload, DeletePullRequestReviewArgs> = QType.configStub(DeletePullRequestReviewArgs())

  val dismissPullRequestReview: QTypeConfigStub<DismissPullRequestReviewPayload, DismissPullRequestReviewArgs> = QType.configStub(DismissPullRequestReviewArgs())

  val moveProjectCard: QTypeConfigStub<MoveProjectCardPayload, MoveProjectCardArgs> = QType.configStub(MoveProjectCardArgs())

  val moveProjectColumn: QTypeConfigStub<MoveProjectColumnPayload, MoveProjectColumnArgs> = QType.configStub(MoveProjectColumnArgs())

  val removeOutsideCollaborator: QTypeConfigStub<RemoveOutsideCollaboratorPayload, RemoveOutsideCollaboratorArgs> = QType.configStub(RemoveOutsideCollaboratorArgs())

  val removeReaction: QTypeConfigStub<RemoveReactionPayload, RemoveReactionArgs> = QType.configStub(RemoveReactionArgs())

  val removeStar: QTypeConfigStub<RemoveStarPayload, RemoveStarArgs> = QType.configStub(RemoveStarArgs())

  val requestReviews: QTypeConfigStub<RequestReviewsPayload, RequestReviewsArgs> = QType.configStub(RequestReviewsArgs())

  val submitPullRequestReview: QTypeConfigStub<SubmitPullRequestReviewPayload, SubmitPullRequestReviewArgs> = QType.configStub(SubmitPullRequestReviewArgs())

  val updateProject: QTypeConfigStub<UpdateProjectPayload, UpdateProjectArgs> = QType.configStub(UpdateProjectArgs())

  val updateProjectCard: QTypeConfigStub<UpdateProjectCardPayload, UpdateProjectCardArgs> = QType.configStub(UpdateProjectCardArgs())

  val updateProjectColumn: QTypeConfigStub<UpdateProjectColumnPayload, UpdateProjectColumnArgs> = QType.configStub(UpdateProjectColumnArgs())

  val updatePullRequestReview: QTypeConfigStub<UpdatePullRequestReviewPayload, UpdatePullRequestReviewArgs> = QType.configStub(UpdatePullRequestReviewArgs())

  val updatePullRequestReviewComment: QTypeConfigStub<UpdatePullRequestReviewCommentPayload, UpdatePullRequestReviewCommentArgs> = QType.configStub(UpdatePullRequestReviewCommentArgs())

  val updateSubscription: QTypeConfigStub<UpdateSubscriptionPayload, UpdateSubscriptionArgs> = QType.configStub(UpdateSubscriptionArgs())

  val updateTopics: QTypeConfigStub<UpdateTopicsPayload, UpdateTopicsArgs> = QType.configStub(UpdateTopicsArgs())

  class AcceptTopicSuggestionArgs(args: TypeArgBuilder = TypeArgBuilder.create<AcceptTopicSuggestionPayload, AcceptTopicSuggestionArgs>()) : TypeArgBuilder by args {
    fun input(value: AcceptTopicSuggestionInput): AcceptTopicSuggestionArgs = apply { addArg("input", value) }

  }

  class AddCommentArgs(args: TypeArgBuilder = TypeArgBuilder.create<AddCommentPayload, AddCommentArgs>()) : TypeArgBuilder by args {
    fun input(value: AddCommentInput): AddCommentArgs = apply { addArg("input", value) }

  }

  class AddProjectCardArgs(args: TypeArgBuilder = TypeArgBuilder.create<AddProjectCardPayload, AddProjectCardArgs>()) : TypeArgBuilder by args {
    fun input(value: AddProjectCardInput): AddProjectCardArgs = apply { addArg("input", value) }

  }

  class AddProjectColumnArgs(args: TypeArgBuilder = TypeArgBuilder.create<AddProjectColumnPayload, AddProjectColumnArgs>()) : TypeArgBuilder by args {
    fun input(value: AddProjectColumnInput): AddProjectColumnArgs = apply { addArg("input", value) }

  }

  class AddPullRequestReviewArgs(args: TypeArgBuilder = TypeArgBuilder.create<AddPullRequestReviewPayload, AddPullRequestReviewArgs>()) : TypeArgBuilder by args {
    fun input(value: AddPullRequestReviewInput): AddPullRequestReviewArgs = apply { addArg("input", value) }

  }

  class AddPullRequestReviewCommentArgs(args: TypeArgBuilder = TypeArgBuilder.create<AddPullRequestReviewCommentPayload, AddPullRequestReviewCommentArgs>()) : TypeArgBuilder by args {
    fun input(value: AddPullRequestReviewCommentInput): AddPullRequestReviewCommentArgs = apply { addArg("input", value) }

  }

  class AddReactionArgs(args: TypeArgBuilder = TypeArgBuilder.create<AddReactionPayload, AddReactionArgs>()) : TypeArgBuilder by args {
    fun input(value: AddReactionInput): AddReactionArgs = apply { addArg("input", value) }

  }

  class AddStarArgs(args: TypeArgBuilder = TypeArgBuilder.create<AddStarPayload, AddStarArgs>()) : TypeArgBuilder by args {
    fun input(value: AddStarInput): AddStarArgs = apply { addArg("input", value) }

  }

  class CreateProjectArgs(args: TypeArgBuilder = TypeArgBuilder.create<CreateProjectPayload, CreateProjectArgs>()) : TypeArgBuilder by args {
    fun input(value: CreateProjectInput): CreateProjectArgs = apply { addArg("input", value) }

  }

  class DeclineTopicSuggestionArgs(args: TypeArgBuilder = TypeArgBuilder.create<DeclineTopicSuggestionPayload, DeclineTopicSuggestionArgs>()) : TypeArgBuilder by args {
    fun input(value: DeclineTopicSuggestionInput): DeclineTopicSuggestionArgs = apply { addArg("input", value) }

  }

  class DeleteProjectArgs(args: TypeArgBuilder = TypeArgBuilder.create<DeleteProjectPayload, DeleteProjectArgs>()) : TypeArgBuilder by args {
    fun input(value: DeleteProjectInput): DeleteProjectArgs = apply { addArg("input", value) }

  }

  class DeleteProjectCardArgs(args: TypeArgBuilder = TypeArgBuilder.create<DeleteProjectCardPayload, DeleteProjectCardArgs>()) : TypeArgBuilder by args {
    fun input(value: DeleteProjectCardInput): DeleteProjectCardArgs = apply { addArg("input", value) }

  }

  class DeleteProjectColumnArgs(args: TypeArgBuilder = TypeArgBuilder.create<DeleteProjectColumnPayload, DeleteProjectColumnArgs>()) : TypeArgBuilder by args {
    fun input(value: DeleteProjectColumnInput): DeleteProjectColumnArgs = apply { addArg("input", value) }

  }

  class DeletePullRequestReviewArgs(args: TypeArgBuilder = TypeArgBuilder.create<DeletePullRequestReviewPayload, DeletePullRequestReviewArgs>()) : TypeArgBuilder by args {
    fun input(value: DeletePullRequestReviewInput): DeletePullRequestReviewArgs = apply { addArg("input", value) }

  }

  class DismissPullRequestReviewArgs(args: TypeArgBuilder = TypeArgBuilder.create<DismissPullRequestReviewPayload, DismissPullRequestReviewArgs>()) : TypeArgBuilder by args {
    fun input(value: DismissPullRequestReviewInput): DismissPullRequestReviewArgs = apply { addArg("input", value) }

  }

  class MoveProjectCardArgs(args: TypeArgBuilder = TypeArgBuilder.create<MoveProjectCardPayload, MoveProjectCardArgs>()) : TypeArgBuilder by args {
    fun input(value: MoveProjectCardInput): MoveProjectCardArgs = apply { addArg("input", value) }

  }

  class MoveProjectColumnArgs(args: TypeArgBuilder = TypeArgBuilder.create<MoveProjectColumnPayload, MoveProjectColumnArgs>()) : TypeArgBuilder by args {
    fun input(value: MoveProjectColumnInput): MoveProjectColumnArgs = apply { addArg("input", value) }

  }

  class RemoveOutsideCollaboratorArgs(args: TypeArgBuilder = TypeArgBuilder.create<RemoveOutsideCollaboratorPayload, RemoveOutsideCollaboratorArgs>()) : TypeArgBuilder by args {
    fun input(value: RemoveOutsideCollaboratorInput): RemoveOutsideCollaboratorArgs = apply { addArg("input", value) }

  }

  class RemoveReactionArgs(args: TypeArgBuilder = TypeArgBuilder.create<RemoveReactionPayload, RemoveReactionArgs>()) : TypeArgBuilder by args {
    fun input(value: RemoveReactionInput): RemoveReactionArgs = apply { addArg("input", value) }

  }

  class RemoveStarArgs(args: TypeArgBuilder = TypeArgBuilder.create<RemoveStarPayload, RemoveStarArgs>()) : TypeArgBuilder by args {
    fun input(value: RemoveStarInput): RemoveStarArgs = apply { addArg("input", value) }

  }

  class RequestReviewsArgs(args: TypeArgBuilder = TypeArgBuilder.create<RequestReviewsPayload, RequestReviewsArgs>()) : TypeArgBuilder by args {
    fun input(value: RequestReviewsInput): RequestReviewsArgs = apply { addArg("input", value) }

  }

  class SubmitPullRequestReviewArgs(args: TypeArgBuilder = TypeArgBuilder.create<SubmitPullRequestReviewPayload, SubmitPullRequestReviewArgs>()) : TypeArgBuilder by args {
    fun input(value: SubmitPullRequestReviewInput): SubmitPullRequestReviewArgs = apply { addArg("input", value) }

  }

  class UpdateProjectArgs(args: TypeArgBuilder = TypeArgBuilder.create<UpdateProjectPayload, UpdateProjectArgs>()) : TypeArgBuilder by args {
    fun input(value: UpdateProjectInput): UpdateProjectArgs = apply { addArg("input", value) }

  }

  class UpdateProjectCardArgs(args: TypeArgBuilder = TypeArgBuilder.create<UpdateProjectCardPayload, UpdateProjectCardArgs>()) : TypeArgBuilder by args {
    fun input(value: UpdateProjectCardInput): UpdateProjectCardArgs = apply { addArg("input", value) }

  }

  class UpdateProjectColumnArgs(args: TypeArgBuilder = TypeArgBuilder.create<UpdateProjectColumnPayload, UpdateProjectColumnArgs>()) : TypeArgBuilder by args {
    fun input(value: UpdateProjectColumnInput): UpdateProjectColumnArgs = apply { addArg("input", value) }

  }

  class UpdatePullRequestReviewArgs(args: TypeArgBuilder = TypeArgBuilder.create<UpdatePullRequestReviewPayload, UpdatePullRequestReviewArgs>()) : TypeArgBuilder by args {
    fun input(value: UpdatePullRequestReviewInput): UpdatePullRequestReviewArgs = apply { addArg("input", value) }

  }

  class UpdatePullRequestReviewCommentArgs(args: TypeArgBuilder = TypeArgBuilder.create<UpdatePullRequestReviewCommentPayload, UpdatePullRequestReviewCommentArgs>()) : TypeArgBuilder by args {
    fun input(value: UpdatePullRequestReviewCommentInput): UpdatePullRequestReviewCommentArgs = apply { addArg("input", value) }

  }

  class UpdateSubscriptionArgs(args: TypeArgBuilder = TypeArgBuilder.create<UpdateSubscriptionPayload, UpdateSubscriptionArgs>()) : TypeArgBuilder by args {
    fun input(value: UpdateSubscriptionInput): UpdateSubscriptionArgs = apply { addArg("input", value) }

  }

  class UpdateTopicsArgs(args: TypeArgBuilder = TypeArgBuilder.create<UpdateTopicsPayload, UpdateTopicsArgs>()) : TypeArgBuilder by args {
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
  override val avatarUrl: QConfigStub<URI, AvatarUrlArgs> = QScalar.configStub(AvatarUrlArgs())

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val isInvoiced: Stub<Boolean> = QScalar.stub()

  override val login: Stub<String> = QScalar.stub()

  val members: QTypeConfigStub<UserConnection, MembersArgs> = QType.configStub(MembersArgs())

  val name: Stub<String> = QScalar.stub()

  val newTeamResourcePath: Stub<URI> = QScalar.stub()

  val newTeamUrl: Stub<URI> = QScalar.stub()

  val organizationBillingEmail: Stub<String> = QScalar.stub()

  override val pinnedRepositories: QTypeConfigStub<RepositoryConnection, RepositoryOwner.PinnedRepositoriesArgs> = QType.configStub(RepositoryOwner.PinnedRepositoriesArgs())

  override val project: QTypeConfigStub<Project, ProjectOwner.ProjectArgs> = QType.configStub(ProjectOwner.ProjectArgs())

  override val projects: QTypeConfigStub<ProjectConnection, ProjectOwner.ProjectsArgs> = QType.configStub(ProjectOwner.ProjectsArgs())

  override val projectsResourcePath: Stub<URI> = QScalar.stub()

  override val projectsUrl: Stub<URI> = QScalar.stub()

  override val repositories: QTypeConfigStub<RepositoryConnection, RepositoryOwner.RepositoriesArgs> = QType.configStub(RepositoryOwner.RepositoriesArgs())

  override val repository: QTypeConfigStub<Repository, RepositoryOwner.RepositoryArgs> = QType.configStub(RepositoryOwner.RepositoryArgs())

  override val resourcePath: Stub<URI> = QScalar.stub()

  val samlIdentityProvider: InitStub<OrganizationIdentityProvider> = QType.stub()

  val team: QTypeConfigStub<Team, TeamArgs> = QType.configStub(TeamArgs())

  val teams: QTypeConfigStub<TeamConnection, TeamsArgs> = QType.configStub(TeamsArgs())

  val teamsResourcePath: Stub<URI> = QScalar.stub()

  val teamsUrl: Stub<URI> = QScalar.stub()

  override val url: Stub<URI> = QScalar.stub()

  val viewerCanAdminister: Stub<Boolean> = QScalar.stub()

  override val viewerCanCreateProjects: Stub<Boolean> = QScalar.stub()

  val viewerCanCreateRepositories: Stub<Boolean> = QScalar.stub()

  val viewerCanCreateTeams: Stub<Boolean> = QScalar.stub()

  val viewerIsAMember: Stub<Boolean> = QScalar.stub()

  class AvatarUrlArgs(args: ArgBuilder = ArgBuilder.create<URI, AvatarUrlArgs>()) : BaseAvatarUrlArgs(args) {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }

  class MembersArgs(args: TypeArgBuilder = TypeArgBuilder.create<UserConnection, MembersArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): MembersArgs = apply { addArg("first", value) }


    fun after(value: String): MembersArgs = apply { addArg("after", value) }


    fun last(value: Int): MembersArgs = apply { addArg("last", value) }


    fun before(value: String): MembersArgs = apply { addArg("before", value) }

  }

  class TeamArgs(args: TypeArgBuilder = TypeArgBuilder.create<Team, TeamArgs>()) : TypeArgBuilder by args {
    fun slug(value: String): TeamArgs = apply { addArg("slug", value) }

  }

  class TeamsArgs(args: TypeArgBuilder = TypeArgBuilder.create<TeamConnection, TeamsArgs>()) : TypeArgBuilder by args {
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
  val edges: ListInitStub<OrganizationEdge> = QTypeList.stub()

  val nodes: ListInitStub<Organization> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object OrganizationEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Organization> = QType.stub()
}

object OrganizationIdentityProvider : QSchemaType, Node {
  val digestMethod: Stub<URI> = QScalar.stub()

  val externalIdentities: QTypeConfigStub<ExternalIdentityConnection, ExternalIdentitiesArgs> = QType.configStub(ExternalIdentitiesArgs())

  override val id: Stub<String> = QScalar.stub()

  val idpCertificate: Stub<X509Certificate> = QScalar.stub()

  val issuer: Stub<String> = QScalar.stub()

  val organization: InitStub<Organization> = QType.stub()

  val signatureMethod: Stub<URI> = QScalar.stub()

  val ssoUrl: Stub<URI> = QScalar.stub()

  class ExternalIdentitiesArgs(args: TypeArgBuilder = TypeArgBuilder.create<ExternalIdentityConnection, ExternalIdentitiesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ExternalIdentitiesArgs = apply { addArg("first", value) }


    fun after(value: String): ExternalIdentitiesArgs = apply { addArg("after", value) }


    fun last(value: Int): ExternalIdentitiesArgs = apply { addArg("last", value) }


    fun before(value: String): ExternalIdentitiesArgs = apply { addArg("before", value) }

  }
}

object OrganizationInvitation : QSchemaType {
  val email: Stub<String> = QScalar.stub()

  val id: Stub<String> = QScalar.stub()

  val invitee: InitStub<User> = QType.stub()

  val inviter: InitStub<User> = QType.stub()

  val role: Stub<OrganizationInvitationRole> = QScalar.stub()
}

object OrganizationInvitationConnection : QSchemaType {
  val edges: ListInitStub<OrganizationInvitationEdge> = QTypeList.stub()

  val nodes: ListInitStub<OrganizationInvitation> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object OrganizationInvitationEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<OrganizationInvitation> = QType.stub()
}

enum class OrganizationInvitationRole : QSchemaType {
  DIRECT_MEMBER,

  ADMIN,

  BILLING_MANAGER,

  REINSTATE
}

object PageInfo : QSchemaType {
  val endCursor: Stub<String> = QScalar.stub()

  val hasNextPage: Stub<Boolean> = QScalar.stub()

  val hasPreviousPage: Stub<Boolean> = QScalar.stub()

  val startCursor: Stub<String> = QScalar.stub()
}

object Project : QSchemaType, Updatable, Node {
  val body: Stub<String> = QScalar.stub()

  val bodyHTML: Stub<HTML> = QScalar.stub()

  val closedAt: Stub<DateTime> = QScalar.stub()

  val columns: QTypeConfigStub<ProjectColumnConnection, ColumnsArgs> = QType.configStub(ColumnsArgs())

  val createdAt: Stub<DateTime> = QScalar.stub()

  val creator: InitStub<Actor> = QType.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val number: Stub<Int> = QScalar.stub()

  val owner: InitStub<ProjectOwner> = QType.stub()

  val resourcePath: Stub<URI> = QScalar.stub()

  val state: Stub<ProjectState> = QScalar.stub()

  val updatedAt: Stub<DateTime> = QScalar.stub()

  val url: Stub<URI> = QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> = QScalar.stub()

  class ColumnsArgs(args: TypeArgBuilder = TypeArgBuilder.create<ProjectColumnConnection, ColumnsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ColumnsArgs = apply { addArg("first", value) }


    fun after(value: String): ColumnsArgs = apply { addArg("after", value) }


    fun last(value: Int): ColumnsArgs = apply { addArg("last", value) }


    fun before(value: String): ColumnsArgs = apply { addArg("before", value) }

  }
}

object ProjectCard : QSchemaType, Node {
  val column: InitStub<ProjectColumn> = QType.stub()

  val content: InitStub<ProjectCardItem> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val creator: InitStub<Actor> = QType.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val note: Stub<String> = QScalar.stub()

  val project: InitStub<Project> = QType.stub()

  val projectColumn: InitStub<ProjectColumn> = QType.stub()

  val resourcePath: Stub<URI> = QScalar.stub()

  val state: Stub<ProjectCardState> = QScalar.stub()

  val updatedAt: Stub<DateTime> = QScalar.stub()

  val url: Stub<URI> = QScalar.stub()
}

object ProjectCardConnection : QSchemaType {
  val edges: ListInitStub<ProjectCardEdge> = QTypeList.stub()

  val nodes: ListInitStub<ProjectCard> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object ProjectCardEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<ProjectCard> = QType.stub()
}

object ProjectCardItem : QSchemaType {
  val Issue: ListInitStub<Issue> = QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> = QTypeList.stub()
}

enum class ProjectCardState : QSchemaType {
  CONTENT_ONLY,

  NOTE_ONLY,

  REDACTED
}

object ProjectColumn : QSchemaType, Node {
  val cards: QTypeConfigStub<ProjectCardConnection, CardsArgs> = QType.configStub(CardsArgs())

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val project: InitStub<Project> = QType.stub()

  val updatedAt: Stub<DateTime> = QScalar.stub()

  class CardsArgs(args: TypeArgBuilder = TypeArgBuilder.create<ProjectCardConnection, CardsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): CardsArgs = apply { addArg("first", value) }


    fun after(value: String): CardsArgs = apply { addArg("after", value) }


    fun last(value: Int): CardsArgs = apply { addArg("last", value) }


    fun before(value: String): CardsArgs = apply { addArg("before", value) }

  }
}

object ProjectColumnConnection : QSchemaType {
  val edges: ListInitStub<ProjectColumnEdge> = QTypeList.stub()

  val nodes: ListInitStub<ProjectColumn> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object ProjectColumnEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<ProjectColumn> = QType.stub()
}

object ProjectConnection : QSchemaType {
  val edges: ListInitStub<ProjectEdge> = QTypeList.stub()

  val nodes: ListInitStub<Project> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object ProjectEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Project> = QType.stub()
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

  class ProjectArgs(args: TypeArgBuilder = TypeArgBuilder.create<Project, ProjectArgs>()) : TypeArgBuilder by args {
    fun number(value: Int): ProjectArgs = apply { addArg("number", value) }

  }

  class ProjectsArgs(args: TypeArgBuilder = TypeArgBuilder.create<ProjectConnection, ProjectsArgs>()) : TypeArgBuilder by args {
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
  val creator: InitStub<Actor> = QType.stub()

  val hasDismissableStaleReviews: Stub<Boolean> = QScalar.stub()

  val hasRequiredReviews: Stub<Boolean> = QScalar.stub()

  val hasRequiredStatusChecks: Stub<Boolean> = QScalar.stub()

  val hasRestrictedPushes: Stub<Boolean> = QScalar.stub()

  val hasRestrictedReviewDismissals: Stub<Boolean> = QScalar.stub()

  val hasStrictRequiredStatusChecks: Stub<Boolean> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val isAdminEnforced: Stub<Boolean> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val pushAllowances: QTypeConfigStub<PushAllowanceConnection, PushAllowancesArgs> = QType.configStub(PushAllowancesArgs())

  val repository: InitStub<Repository> = QType.stub()

  val requiredStatusCheckContexts: ListStub<String> = QScalarList.stub()

  val reviewDismissalAllowances: QTypeConfigStub<ReviewDismissalAllowanceConnection, ReviewDismissalAllowancesArgs> = QType.configStub(ReviewDismissalAllowancesArgs())

  class PushAllowancesArgs(args: TypeArgBuilder = TypeArgBuilder.create<PushAllowanceConnection, PushAllowancesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): PushAllowancesArgs = apply { addArg("first", value) }


    fun after(value: String): PushAllowancesArgs = apply { addArg("after", value) }


    fun last(value: Int): PushAllowancesArgs = apply { addArg("last", value) }


    fun before(value: String): PushAllowancesArgs = apply { addArg("before", value) }

  }

  class ReviewDismissalAllowancesArgs(args: TypeArgBuilder = TypeArgBuilder.create<ReviewDismissalAllowanceConnection, ReviewDismissalAllowancesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ReviewDismissalAllowancesArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewDismissalAllowancesArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewDismissalAllowancesArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewDismissalAllowancesArgs = apply { addArg("before", value) }

  }
}

object ProtectedBranchConnection : QSchemaType {
  val edges: ListInitStub<ProtectedBranchEdge> = QTypeList.stub()

  val nodes: ListInitStub<ProtectedBranch> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object ProtectedBranchEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<ProtectedBranch> = QType.stub()
}

object PullRequest : QSchemaType, UniformResourceLocatable, Subscribable, RepositoryNode, Reactable, Lockable, Labelable, UpdatableComment, Updatable, Comment, Closable, Assignable, Node {
  override val assignees: QTypeConfigStub<UserConnection, Assignable.AssigneesArgs> = QType.configStub(Assignable.AssigneesArgs())

  override val author: InitStub<Actor> = QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> = QScalar.stub()

  val baseRef: InitStub<Ref> = QType.stub()

  val baseRefName: Stub<String> = QScalar.stub()

  override val body: Stub<String> = QScalar.stub()

  override val bodyHTML: Stub<HTML> = QScalar.stub()

  val bodyText: Stub<String> = QScalar.stub()

  override val closed: Stub<Boolean> = QScalar.stub()

  val comments: QTypeConfigStub<IssueCommentConnection, CommentsArgs> = QType.configStub(CommentsArgs())

  val commits: QTypeConfigStub<PullRequestCommitConnection, CommitsArgs> = QType.configStub(CommitsArgs())

  override val createdAt: Stub<DateTime> = QScalar.stub()

  override val createdViaEmail: Stub<Boolean> = QScalar.stub()

  override val databaseId: Stub<Int> = QScalar.stub()

  override val editor: InitStub<Actor> = QType.stub()

  val headRef: InitStub<Ref> = QType.stub()

  val headRefName: Stub<String> = QScalar.stub()

  val headRepository: InitStub<Repository> = QType.stub()

  val headRepositoryOwner: InitStub<RepositoryOwner> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  val isCrossRepository: Stub<Boolean> = QScalar.stub()

  override val labels: QTypeConfigStub<LabelConnection, Labelable.LabelsArgs> = QType.configStub(Labelable.LabelsArgs())

  override val lastEditedAt: Stub<DateTime> = QScalar.stub()

  override val locked: Stub<Boolean> = QScalar.stub()

  val mergeCommit: InitStub<Commit> = QType.stub()

  val mergeable: Stub<MergeableState> = QScalar.stub()

  val merged: Stub<Boolean> = QScalar.stub()

  val mergedAt: Stub<DateTime> = QScalar.stub()

  val number: Stub<Int> = QScalar.stub()

  val participants: QTypeConfigStub<UserConnection, ParticipantsArgs> = QType.configStub(ParticipantsArgs())

  val potentialMergeCommit: InitStub<Commit> = QType.stub()

  override val publishedAt: Stub<DateTime> = QScalar.stub()

  override val reactionGroups: ListInitStub<ReactionGroup> = QTypeList.stub()

  override val reactions: QTypeConfigStub<ReactionConnection, Reactable.ReactionsArgs> = QType.configStub(Reactable.ReactionsArgs())

  override val repository: InitStub<Repository> = QType.stub()

  override val resourcePath: Stub<URI> = QScalar.stub()

  val revertResourcePath: Stub<URI> = QScalar.stub()

  val revertUrl: Stub<URI> = QScalar.stub()

  val reviewRequests: QTypeConfigStub<ReviewRequestConnection, ReviewRequestsArgs> = QType.configStub(ReviewRequestsArgs())

  val reviews: QTypeConfigStub<PullRequestReviewConnection, ReviewsArgs> = QType.configStub(ReviewsArgs())

  val state: Stub<PullRequestState> = QScalar.stub()

  val suggestedReviewers: ListInitStub<SuggestedReviewer> = QTypeList.stub()

  val timeline: QTypeConfigStub<PullRequestTimelineConnection, TimelineArgs> = QType.configStub(TimelineArgs())

  val title: Stub<String> = QScalar.stub()

  override val updatedAt: Stub<DateTime> = QScalar.stub()

  override val url: Stub<URI> = QScalar.stub()

  override val viewerCanReact: Stub<Boolean> = QScalar.stub()

  override val viewerCanSubscribe: Stub<Boolean> = QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> = QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> = QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> = QScalar.stub()

  override val viewerSubscription: Stub<SubscriptionState> = QScalar.stub()

  class CommentsArgs(args: TypeArgBuilder = TypeArgBuilder.create<IssueCommentConnection, CommentsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class CommitsArgs(args: TypeArgBuilder = TypeArgBuilder.create<PullRequestCommitConnection, CommitsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): CommitsArgs = apply { addArg("first", value) }


    fun after(value: String): CommitsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommitsArgs = apply { addArg("last", value) }


    fun before(value: String): CommitsArgs = apply { addArg("before", value) }

  }

  class ParticipantsArgs(args: TypeArgBuilder = TypeArgBuilder.create<UserConnection, ParticipantsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ParticipantsArgs = apply { addArg("first", value) }


    fun after(value: String): ParticipantsArgs = apply { addArg("after", value) }


    fun last(value: Int): ParticipantsArgs = apply { addArg("last", value) }


    fun before(value: String): ParticipantsArgs = apply { addArg("before", value) }

  }

  class ReviewRequestsArgs(args: TypeArgBuilder = TypeArgBuilder.create<ReviewRequestConnection, ReviewRequestsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ReviewRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewRequestsArgs = apply { addArg("before", value) }

  }

  class ReviewsArgs(args: TypeArgBuilder = TypeArgBuilder.create<PullRequestReviewConnection, ReviewsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ReviewsArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewsArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewsArgs = apply { addArg("before", value) }


    fun states(value: PullRequestReviewState): ReviewsArgs = apply { addArg("states", value) }

  }

  class TimelineArgs(args: TypeArgBuilder = TypeArgBuilder.create<PullRequestTimelineConnection, TimelineArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): TimelineArgs = apply { addArg("first", value) }


    fun after(value: String): TimelineArgs = apply { addArg("after", value) }


    fun last(value: Int): TimelineArgs = apply { addArg("last", value) }


    fun before(value: String): TimelineArgs = apply { addArg("before", value) }


    fun since(value: DateTime): TimelineArgs = apply { addArg("since", value) }

  }
}

object PullRequestCommit : QSchemaType, UniformResourceLocatable, Node {
  val commit: InitStub<Commit> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  override val resourcePath: Stub<URI> = QScalar.stub()

  override val url: Stub<URI> = QScalar.stub()
}

object PullRequestCommitConnection : QSchemaType {
  val edges: ListInitStub<PullRequestCommitEdge> = QTypeList.stub()

  val nodes: ListInitStub<PullRequestCommit> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object PullRequestCommitEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<PullRequestCommit> = QType.stub()
}

object PullRequestConnection : QSchemaType {
  val edges: ListInitStub<PullRequestEdge> = QTypeList.stub()

  val nodes: ListInitStub<PullRequest> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object PullRequestEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<PullRequest> = QType.stub()
}

enum class PullRequestPubSubTopic : QSchemaType {
  UPDATED,

  MARKASREAD,

  HEAD_REF
}

object PullRequestReview : QSchemaType, RepositoryNode, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> = QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> = QScalar.stub()

  override val body: Stub<String> = QScalar.stub()

  override val bodyHTML: Stub<HTML> = QScalar.stub()

  val bodyText: Stub<String> = QScalar.stub()

  val comments: QTypeConfigStub<PullRequestReviewCommentConnection, CommentsArgs> = QType.configStub(CommentsArgs())

  val commit: InitStub<Commit> = QType.stub()

  override val createdAt: Stub<DateTime> = QScalar.stub()

  override val createdViaEmail: Stub<Boolean> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val editor: InitStub<Actor> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  override val lastEditedAt: Stub<DateTime> = QScalar.stub()

  override val publishedAt: Stub<DateTime> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  override val repository: InitStub<Repository> = QType.stub()

  val resourcePath: Stub<URI> = QScalar.stub()

  val state: Stub<PullRequestReviewState> = QScalar.stub()

  val submittedAt: Stub<DateTime> = QScalar.stub()

  override val updatedAt: Stub<DateTime> = QScalar.stub()

  val url: Stub<URI> = QScalar.stub()

  override val viewerCanDelete: Stub<Boolean> = QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> = QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> = QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> = QScalar.stub()

  class CommentsArgs(args: TypeArgBuilder = TypeArgBuilder.create<PullRequestReviewCommentConnection, CommentsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

object PullRequestReviewComment : QSchemaType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> = QType.stub()

  override val authorAssociation: Stub<CommentAuthorAssociation> = QScalar.stub()

  override val body: Stub<String> = QScalar.stub()

  override val bodyHTML: Stub<HTML> = QScalar.stub()

  val bodyText: Stub<String> = QScalar.stub()

  val commit: InitStub<Commit> = QType.stub()

  override val createdAt: Stub<DateTime> = QScalar.stub()

  override val createdViaEmail: Stub<Boolean> = QScalar.stub()

  override val databaseId: Stub<Int> = QScalar.stub()

  val diffHunk: Stub<String> = QScalar.stub()

  val draftedAt: Stub<DateTime> = QScalar.stub()

  override val editor: InitStub<Actor> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  override val lastEditedAt: Stub<DateTime> = QScalar.stub()

  val originalCommit: InitStub<Commit> = QType.stub()

  val originalPosition: Stub<Int> = QScalar.stub()

  val path: Stub<String> = QScalar.stub()

  val position: Stub<Int> = QScalar.stub()

  override val publishedAt: Stub<DateTime> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  val pullRequestReview: InitStub<PullRequestReview> = QType.stub()

  override val reactionGroups: ListInitStub<ReactionGroup> = QTypeList.stub()

  override val reactions: QTypeConfigStub<ReactionConnection, Reactable.ReactionsArgs> = QType.configStub(Reactable.ReactionsArgs())

  override val repository: InitStub<Repository> = QType.stub()

  val resourcePath: Stub<URI> = QScalar.stub()

  override val updatedAt: Stub<DateTime> = QScalar.stub()

  val url: Stub<URI> = QScalar.stub()

  override val viewerCanDelete: Stub<Boolean> = QScalar.stub()

  override val viewerCanReact: Stub<Boolean> = QScalar.stub()

  override val viewerCanUpdate: Stub<Boolean> = QScalar.stub()

  override val viewerCannotUpdateReasons: ListStub<CommentCannotUpdateReason> = QScalarList.stub()

  override val viewerDidAuthor: Stub<Boolean> = QScalar.stub()
}

object PullRequestReviewCommentConnection : QSchemaType {
  val edges: ListInitStub<PullRequestReviewCommentEdge> = QTypeList.stub()

  val nodes: ListInitStub<PullRequestReviewComment> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object PullRequestReviewCommentEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<PullRequestReviewComment> = QType.stub()
}

object PullRequestReviewConnection : QSchemaType {
  val edges: ListInitStub<PullRequestReviewEdge> = QTypeList.stub()

  val nodes: ListInitStub<PullRequestReview> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object PullRequestReviewEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<PullRequestReview> = QType.stub()
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
  val comments: QTypeConfigStub<PullRequestReviewCommentConnection, CommentsArgs> = QType.configStub(CommentsArgs())

  override val id: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  class CommentsArgs(args: TypeArgBuilder = TypeArgBuilder.create<PullRequestReviewCommentConnection, CommentsArgs>()) : TypeArgBuilder by args {
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
  val edges: ListInitStub<PullRequestTimelineItemEdge> = QTypeList.stub()

  val nodes: ListInitStub<PullRequestTimelineItem> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object PullRequestTimelineItem : QSchemaType {
  val Commit: ListInitStub<Commit> = QTypeList.stub()

  val CommitCommentThread: ListInitStub<CommitCommentThread> = QTypeList.stub()

  val PullRequestReview: ListInitStub<PullRequestReview> = QTypeList.stub()

  val PullRequestReviewThread: ListInitStub<PullRequestReviewThread> = QTypeList.stub()

  val PullRequestReviewComment: ListInitStub<PullRequestReviewComment> = QTypeList.stub()

  val IssueComment: ListInitStub<IssueComment> = QTypeList.stub()

  val ClosedEvent: ListInitStub<ClosedEvent> = QTypeList.stub()

  val ReopenedEvent: ListInitStub<ReopenedEvent> = QTypeList.stub()

  val SubscribedEvent: ListInitStub<SubscribedEvent> = QTypeList.stub()

  val UnsubscribedEvent: ListInitStub<UnsubscribedEvent> = QTypeList.stub()

  val MergedEvent: ListInitStub<MergedEvent> = QTypeList.stub()

  val ReferencedEvent: ListInitStub<ReferencedEvent> = QTypeList.stub()

  val AssignedEvent: ListInitStub<AssignedEvent> = QTypeList.stub()

  val UnassignedEvent: ListInitStub<UnassignedEvent> = QTypeList.stub()

  val LabeledEvent: ListInitStub<LabeledEvent> = QTypeList.stub()

  val UnlabeledEvent: ListInitStub<UnlabeledEvent> = QTypeList.stub()

  val MilestonedEvent: ListInitStub<MilestonedEvent> = QTypeList.stub()

  val DemilestonedEvent: ListInitStub<DemilestonedEvent> = QTypeList.stub()

  val RenamedTitleEvent: ListInitStub<RenamedTitleEvent> = QTypeList.stub()

  val LockedEvent: ListInitStub<LockedEvent> = QTypeList.stub()

  val UnlockedEvent: ListInitStub<UnlockedEvent> = QTypeList.stub()

  val DeployedEvent: ListInitStub<DeployedEvent> = QTypeList.stub()

  val HeadRefDeletedEvent: ListInitStub<HeadRefDeletedEvent> = QTypeList.stub()

  val HeadRefRestoredEvent: ListInitStub<HeadRefRestoredEvent> = QTypeList.stub()

  val HeadRefForcePushedEvent: ListInitStub<HeadRefForcePushedEvent> = QTypeList.stub()

  val BaseRefForcePushedEvent: ListInitStub<BaseRefForcePushedEvent> = QTypeList.stub()

  val ReviewRequestedEvent: ListInitStub<ReviewRequestedEvent> = QTypeList.stub()

  val ReviewRequestRemovedEvent: ListInitStub<ReviewRequestRemovedEvent> = QTypeList.stub()

  val ReviewDismissedEvent: ListInitStub<ReviewDismissedEvent> = QTypeList.stub()
}

object PullRequestTimelineItemEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<PullRequestTimelineItem> = QType.stub()
}

object PushAllowance : QSchemaType, Node {
  val actor: InitStub<PushAllowanceActor> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  val protectedBranch: InitStub<ProtectedBranch> = QType.stub()
}

object PushAllowanceActor : QSchemaType {
  val User: ListInitStub<User> = QTypeList.stub()

  val Team: ListInitStub<Team> = QTypeList.stub()
}

object PushAllowanceConnection : QSchemaType {
  val edges: ListInitStub<PushAllowanceEdge> = QTypeList.stub()

  val nodes: ListInitStub<PushAllowance> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object PushAllowanceEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<PushAllowance> = QType.stub()
}

object Query : QSchemaType {
  val codeOfConduct: QTypeConfigStub<CodeOfConduct, CodeOfConductArgs> = QType.configStub(CodeOfConductArgs())

  val codesOfConduct: ListInitStub<CodeOfConduct> = QTypeList.stub()

  val node: QTypeConfigStub<Node, NodeArgs> = QType.configStub(NodeArgs())

  val nodes: ListConfigType<Node, NodesArgs> = QTypeList.configStub(NodesArgs())

  val organization: QTypeConfigStub<Organization, OrganizationArgs> = QType.configStub(OrganizationArgs())

  val rateLimit: InitStub<RateLimit> = QType.stub()

  val relay: InitStub<Query> = QType.stub()

  val repository: QTypeConfigStub<Repository, RepositoryArgs> = QType.configStub(RepositoryArgs())

  val repositoryOwner: QTypeConfigStub<RepositoryOwner, RepositoryOwnerArgs> = QType.configStub(RepositoryOwnerArgs())

  val resource: QTypeConfigStub<UniformResourceLocatable, ResourceArgs> = QType.configStub(ResourceArgs())

  val search: QTypeConfigStub<SearchResultItemConnection, SearchArgs> = QType.configStub(SearchArgs())

  val topic: QTypeConfigStub<Topic, TopicArgs> = QType.configStub(TopicArgs())

  val user: QTypeConfigStub<User, UserArgs> = QType.configStub(UserArgs())

  val viewer: InitStub<User> = QType.stub()

  class CodeOfConductArgs(args: TypeArgBuilder = TypeArgBuilder.create<CodeOfConduct, CodeOfConductArgs>()) : TypeArgBuilder by args {
    fun key(value: String): CodeOfConductArgs = apply { addArg("key", value) }

  }

  class NodeArgs(args: TypeArgBuilder = TypeArgBuilder.create<Node, NodeArgs>()) : TypeArgBuilder by args {
    fun id(value: String): NodeArgs = apply { addArg("id", value) }

  }

  class NodesArgs(args: TypeListArgBuilder = TypeListArgBuilder.create<Node, NodesArgs>()) : TypeListArgBuilder by args {
    fun ids(value: String): NodesArgs = apply { addArg("ids", value) }

  }

  class OrganizationArgs(args: TypeArgBuilder = TypeArgBuilder.create<Organization, OrganizationArgs>()) : TypeArgBuilder by args {
    fun login(value: String): OrganizationArgs = apply { addArg("login", value) }

  }

  class RepositoryArgs(args: TypeArgBuilder = TypeArgBuilder.create<Repository, RepositoryArgs>()) : TypeArgBuilder by args {
    fun owner(value: String): RepositoryArgs = apply { addArg("owner", value) }


    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }

  class RepositoryOwnerArgs(args: TypeArgBuilder = TypeArgBuilder.create<RepositoryOwner, RepositoryOwnerArgs>()) : TypeArgBuilder by args {
    fun login(value: String): RepositoryOwnerArgs = apply { addArg("login", value) }

  }

  class ResourceArgs(args: TypeArgBuilder = TypeArgBuilder.create<UniformResourceLocatable, ResourceArgs>()) : TypeArgBuilder by args {
    fun url(value: URI): ResourceArgs = apply { addArg("url", value) }

  }

  class SearchArgs(args: TypeArgBuilder = TypeArgBuilder.create<SearchResultItemConnection, SearchArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): SearchArgs = apply { addArg("first", value) }


    fun after(value: String): SearchArgs = apply { addArg("after", value) }


    fun last(value: Int): SearchArgs = apply { addArg("last", value) }


    fun before(value: String): SearchArgs = apply { addArg("before", value) }


    fun query(value: String): SearchArgs = apply { addArg("query", value) }


    fun type(value: SearchType): SearchArgs = apply { addArg("type", value) }

  }

  class TopicArgs(args: TypeArgBuilder = TypeArgBuilder.create<Topic, TopicArgs>()) : TypeArgBuilder by args {
    fun name(value: String): TopicArgs = apply { addArg("name", value) }

  }

  class UserArgs(args: TypeArgBuilder = TypeArgBuilder.create<User, UserArgs>()) : TypeArgBuilder by args {
    fun login(value: String): UserArgs = apply { addArg("login", value) }

  }
}

object RateLimit : QSchemaType {
  val cost: Stub<Int> = QScalar.stub()

  val limit: Stub<Int> = QScalar.stub()

  val remaining: Stub<Int> = QScalar.stub()

  val resetAt: Stub<DateTime> = QScalar.stub()
}

interface Reactable : QSchemaType {
  val databaseId: Stub<Int>

  val id: Stub<String>

  val reactionGroups: ListInitStub<ReactionGroup>

  val reactions: QTypeConfigStub<ReactionConnection, ReactionsArgs>

  val viewerCanReact: Stub<Boolean>

  class ReactionsArgs(args: TypeArgBuilder = TypeArgBuilder.create<ReactionConnection, ReactionsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

object ReactingUserConnection : QSchemaType {
  val edges: ListInitStub<ReactingUserEdge> = QTypeList.stub()

  val nodes: ListInitStub<User> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object ReactingUserEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<User> = QType.stub()

  val reactedAt: Stub<DateTime> = QScalar.stub()
}

object Reaction : QSchemaType, Node {
  val content: Stub<ReactionContent> = QScalar.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val user: InitStub<User> = QType.stub()
}

object ReactionConnection : QSchemaType {
  val edges: ListInitStub<ReactionEdge> = QTypeList.stub()

  val nodes: ListInitStub<Reaction> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()

  val viewerHasReacted: Stub<Boolean> = QScalar.stub()
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
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Reaction> = QType.stub()
}

object ReactionGroup : QSchemaType {
  val content: Stub<ReactionContent> = QScalar.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val subject: InitStub<Reactable> = QType.stub()

  val users: QTypeConfigStub<ReactingUserConnection, UsersArgs> = QType.configStub(UsersArgs())

  val viewerHasReacted: Stub<Boolean> = QScalar.stub()

  class UsersArgs(args: TypeArgBuilder = TypeArgBuilder.create<ReactingUserConnection, UsersArgs>()) : TypeArgBuilder by args {
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
  val associatedPullRequests: QTypeConfigStub<PullRequestConnection, AssociatedPullRequestsArgs> = QType.configStub(AssociatedPullRequestsArgs())

  override val id: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val prefix: Stub<String> = QScalar.stub()

  val repository: InitStub<Repository> = QType.stub()

  val target: InitStub<GitObject> = QType.stub()

  class AssociatedPullRequestsArgs(args: TypeArgBuilder = TypeArgBuilder.create<PullRequestConnection, AssociatedPullRequestsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): AssociatedPullRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): AssociatedPullRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): AssociatedPullRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): AssociatedPullRequestsArgs = apply { addArg("before", value) }


    fun states(value: PullRequestState): AssociatedPullRequestsArgs = apply { addArg("states", value) }

  }
}

object RefConnection : QSchemaType {
  val edges: ListInitStub<RefEdge> = QTypeList.stub()

  val nodes: ListInitStub<Ref> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object RefEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Ref> = QType.stub()
}

object ReferencedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val commit: InitStub<Commit> = QType.stub()

  val commitRepository: InitStub<Repository> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val isCrossReference: Stub<Boolean> = QScalar.stub()

  val isCrossRepository: Stub<Boolean> = QScalar.stub()

  val isDirectReference: Stub<Boolean> = QScalar.stub()

  val subject: InitStub<ReferencedSubject> = QType.stub()
}

object ReferencedSubject : QSchemaType {
  val Issue: ListInitStub<Issue> = QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> = QTypeList.stub()
}

object Release : QSchemaType, UniformResourceLocatable, Node {
  val description: Stub<String> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val publishedAt: Stub<DateTime> = QScalar.stub()

  val releaseAsset: QTypeConfigStub<ReleaseAssetConnection, ReleaseAssetArgs> = QType.configStub(ReleaseAssetArgs())

  val releaseAssets: QTypeConfigStub<ReleaseAssetConnection, ReleaseAssetsArgs> = QType.configStub(ReleaseAssetsArgs())

  override val resourcePath: Stub<URI> = QScalar.stub()

  val tag: InitStub<Ref> = QType.stub()

  override val url: Stub<URI> = QScalar.stub()

  class ReleaseAssetArgs(args: TypeArgBuilder = TypeArgBuilder.create<ReleaseAssetConnection, ReleaseAssetArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ReleaseAssetArgs = apply { addArg("first", value) }


    fun after(value: String): ReleaseAssetArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleaseAssetArgs = apply { addArg("last", value) }


    fun before(value: String): ReleaseAssetArgs = apply { addArg("before", value) }


    fun name(value: String): ReleaseAssetArgs = apply { addArg("name", value) }

  }

  class ReleaseAssetsArgs(args: TypeArgBuilder = TypeArgBuilder.create<ReleaseAssetConnection, ReleaseAssetsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ReleaseAssetsArgs = apply { addArg("first", value) }


    fun after(value: String): ReleaseAssetsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleaseAssetsArgs = apply { addArg("last", value) }


    fun before(value: String): ReleaseAssetsArgs = apply { addArg("before", value) }

  }
}

object ReleaseAsset : QSchemaType, Node {
  override val id: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val release: InitStub<Release> = QType.stub()

  val url: Stub<URI> = QScalar.stub()
}

object ReleaseAssetConnection : QSchemaType {
  val edges: ListInitStub<ReleaseAssetEdge> = QTypeList.stub()

  val nodes: ListInitStub<ReleaseAsset> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object ReleaseAssetEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<ReleaseAsset> = QType.stub()
}

object ReleaseConnection : QSchemaType {
  val edges: ListInitStub<ReleaseEdge> = QTypeList.stub()

  val nodes: ListInitStub<Release> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object ReleaseEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Release> = QType.stub()
}

data class RemoveOutsideCollaboratorInput(private val userId: String,
    private val organizationId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object RemoveOutsideCollaboratorPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val removedUser: InitStub<User> = QType.stub()
}

data class RemoveReactionInput(private val subjectId: String,
    private val content: ReactionContent) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object RemoveReactionPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val reaction: InitStub<Reaction> = QType.stub()

  val subject: InitStub<Reactable> = QType.stub()
}

data class RemoveStarInput(private val starrableId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object RemoveStarPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val starrable: InitStub<Starrable> = QType.stub()
}

object RemovedFromProjectEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()
}

object RenamedTitleEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val currentTitle: Stub<String> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val previousTitle: Stub<String> = QScalar.stub()

  val subject: InitStub<RenamedTitleSubject> = QType.stub()
}

object RenamedTitleSubject : QSchemaType {
  val Issue: ListInitStub<Issue> = QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> = QTypeList.stub()
}

object ReopenedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val closable: InitStub<Closable> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()
}

object Repository : QSchemaType, RepositoryInfo, UniformResourceLocatable, Starrable, Subscribable, ProjectOwner, Node {
  val codeOfConduct: InitStub<CodeOfConduct> = QType.stub()

  val commitComments: QTypeConfigStub<CommitCommentConnection, CommitCommentsArgs> = QType.configStub(CommitCommentsArgs())

  override val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  val defaultBranchRef: InitStub<Ref> = QType.stub()

  val deployments: QTypeConfigStub<DeploymentConnection, DeploymentsArgs> = QType.configStub(DeploymentsArgs())

  override val description: Stub<String> = QScalar.stub()

  override val descriptionHTML: Stub<HTML> = QScalar.stub()

  val diskUsage: Stub<Int> = QScalar.stub()

  val forks: QTypeConfigStub<RepositoryConnection, ForksArgs> = QType.configStub(ForksArgs())

  override val hasIssuesEnabled: Stub<Boolean> = QScalar.stub()

  override val hasWikiEnabled: Stub<Boolean> = QScalar.stub()

  override val homepageUrl: Stub<URI> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  override val isFork: Stub<Boolean> = QScalar.stub()

  override val isLocked: Stub<Boolean> = QScalar.stub()

  override val isMirror: Stub<Boolean> = QScalar.stub()

  override val isPrivate: Stub<Boolean> = QScalar.stub()

  val issue: QTypeConfigStub<Issue, IssueArgs> = QType.configStub(IssueArgs())

  val issueOrPullRequest: QTypeConfigStub<IssueOrPullRequest, IssueOrPullRequestArgs> = QType.configStub(IssueOrPullRequestArgs())

  val issues: QTypeConfigStub<IssueConnection, IssuesArgs> = QType.configStub(IssuesArgs())

  val label: QTypeConfigStub<Label, LabelArgs> = QType.configStub(LabelArgs())

  val labels: QTypeConfigStub<LabelConnection, LabelsArgs> = QType.configStub(LabelsArgs())

  val languages: QTypeConfigStub<LanguageConnection, LanguagesArgs> = QType.configStub(LanguagesArgs())

  override val license: Stub<String> = QScalar.stub()

  override val lockReason: Stub<RepositoryLockReason> = QScalar.stub()

  val mentionableUsers: QTypeConfigStub<UserConnection, MentionableUsersArgs> = QType.configStub(MentionableUsersArgs())

  val milestone: QTypeConfigStub<Milestone, MilestoneArgs> = QType.configStub(MilestoneArgs())

  val milestones: QTypeConfigStub<MilestoneConnection, MilestonesArgs> = QType.configStub(MilestonesArgs())

  override val mirrorUrl: Stub<URI> = QScalar.stub()

  override val name: Stub<String> = QScalar.stub()

  override val nameWithOwner: Stub<String> = QScalar.stub()

  val objectVal: QTypeConfigStub<GitObject, ObjectValArgs> = QType.configStub(ObjectValArgs())

  override val owner: InitStub<RepositoryOwner> = QType.stub()

  val parent: InitStub<Repository> = QType.stub()

  val primaryLanguage: InitStub<Language> = QType.stub()

  override val project: QTypeConfigStub<Project, ProjectOwner.ProjectArgs> = QType.configStub(ProjectOwner.ProjectArgs())

  override val projects: QTypeConfigStub<ProjectConnection, ProjectOwner.ProjectsArgs> = QType.configStub(ProjectOwner.ProjectsArgs())

  override val projectsResourcePath: Stub<URI> = QScalar.stub()

  override val projectsUrl: Stub<URI> = QScalar.stub()

  val protectedBranches: QTypeConfigStub<ProtectedBranchConnection, ProtectedBranchesArgs> = QType.configStub(ProtectedBranchesArgs())

  val pullRequest: QTypeConfigStub<PullRequest, PullRequestArgs> = QType.configStub(PullRequestArgs())

  val pullRequests: QTypeConfigStub<PullRequestConnection, PullRequestsArgs> = QType.configStub(PullRequestsArgs())

  override val pushedAt: Stub<DateTime> = QScalar.stub()

  val ref: QTypeConfigStub<Ref, RefArgs> = QType.configStub(RefArgs())

  val refs: QTypeConfigStub<RefConnection, RefsArgs> = QType.configStub(RefsArgs())

  val releases: QTypeConfigStub<ReleaseConnection, ReleasesArgs> = QType.configStub(ReleasesArgs())

  val repositoryTopics: QTypeConfigStub<RepositoryTopicConnection, RepositoryTopicsArgs> = QType.configStub(RepositoryTopicsArgs())

  override val resourcePath: Stub<URI> = QScalar.stub()

  override val stargazers: QTypeConfigStub<StargazerConnection, Starrable.StargazersArgs> = QType.configStub(Starrable.StargazersArgs())

  override val updatedAt: Stub<DateTime> = QScalar.stub()

  override val url: Stub<URI> = QScalar.stub()

  val viewerCanAdminister: Stub<Boolean> = QScalar.stub()

  override val viewerCanCreateProjects: Stub<Boolean> = QScalar.stub()

  override val viewerCanSubscribe: Stub<Boolean> = QScalar.stub()

  val viewerCanUpdateTopics: Stub<Boolean> = QScalar.stub()

  override val viewerHasStarred: Stub<Boolean> = QScalar.stub()

  override val viewerSubscription: Stub<SubscriptionState> = QScalar.stub()

  val watchers: QTypeConfigStub<UserConnection, WatchersArgs> = QType.configStub(WatchersArgs())

  class CommitCommentsArgs(args: TypeArgBuilder = TypeArgBuilder.create<CommitCommentConnection, CommitCommentsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): CommitCommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommitCommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommitCommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommitCommentsArgs = apply { addArg("before", value) }

  }

  class DeploymentsArgs(args: TypeArgBuilder = TypeArgBuilder.create<DeploymentConnection, DeploymentsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): DeploymentsArgs = apply { addArg("first", value) }


    fun after(value: String): DeploymentsArgs = apply { addArg("after", value) }


    fun last(value: Int): DeploymentsArgs = apply { addArg("last", value) }


    fun before(value: String): DeploymentsArgs = apply { addArg("before", value) }

  }

  class ForksArgs(args: TypeArgBuilder = TypeArgBuilder.create<RepositoryConnection, ForksArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ForksArgs = apply { addArg("first", value) }


    fun after(value: String): ForksArgs = apply { addArg("after", value) }


    fun last(value: Int): ForksArgs = apply { addArg("last", value) }


    fun before(value: String): ForksArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): ForksArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): ForksArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): ForksArgs = apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): ForksArgs = apply { addArg("isLocked", value) }

  }

  class IssueArgs(args: TypeArgBuilder = TypeArgBuilder.create<Issue, IssueArgs>()) : TypeArgBuilder by args {
    fun number(value: Int): IssueArgs = apply { addArg("number", value) }

  }

  class IssueOrPullRequestArgs(args: TypeArgBuilder = TypeArgBuilder.create<IssueOrPullRequest, IssueOrPullRequestArgs>()) : TypeArgBuilder by args {
    fun number(value: Int): IssueOrPullRequestArgs = apply { addArg("number", value) }

  }

  class IssuesArgs(args: TypeArgBuilder = TypeArgBuilder.create<IssueConnection, IssuesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class LabelArgs(args: TypeArgBuilder = TypeArgBuilder.create<Label, LabelArgs>()) : TypeArgBuilder by args {
    fun name(value: String): LabelArgs = apply { addArg("name", value) }

  }

  class LabelsArgs(args: TypeArgBuilder = TypeArgBuilder.create<LabelConnection, LabelsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }

  class LanguagesArgs(args: TypeArgBuilder = TypeArgBuilder.create<LanguageConnection, LanguagesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): LanguagesArgs = apply { addArg("first", value) }


    fun after(value: String): LanguagesArgs = apply { addArg("after", value) }


    fun last(value: Int): LanguagesArgs = apply { addArg("last", value) }


    fun before(value: String): LanguagesArgs = apply { addArg("before", value) }


    fun orderBy(value: LanguageOrder): LanguagesArgs = apply { addArg("orderBy", value) }

  }

  class MentionableUsersArgs(args: TypeArgBuilder = TypeArgBuilder.create<UserConnection, MentionableUsersArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): MentionableUsersArgs = apply { addArg("first", value) }


    fun after(value: String): MentionableUsersArgs = apply { addArg("after", value) }


    fun last(value: Int): MentionableUsersArgs = apply { addArg("last", value) }


    fun before(value: String): MentionableUsersArgs = apply { addArg("before", value) }

  }

  class MilestoneArgs(args: TypeArgBuilder = TypeArgBuilder.create<Milestone, MilestoneArgs>()) : TypeArgBuilder by args {
    fun number(value: Int): MilestoneArgs = apply { addArg("number", value) }

  }

  class MilestonesArgs(args: TypeArgBuilder = TypeArgBuilder.create<MilestoneConnection, MilestonesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): MilestonesArgs = apply { addArg("first", value) }


    fun after(value: String): MilestonesArgs = apply { addArg("after", value) }


    fun last(value: Int): MilestonesArgs = apply { addArg("last", value) }


    fun before(value: String): MilestonesArgs = apply { addArg("before", value) }

  }

  class ObjectValArgs(args: TypeArgBuilder = TypeArgBuilder.create<GitObject, ObjectValArgs>()) : TypeArgBuilder by args {
    fun oid(value: GitObjectID): ObjectValArgs = apply { addArg("oid", value) }


    fun expression(value: String): ObjectValArgs = apply { addArg("expression", value) }

  }

  class ProtectedBranchesArgs(args: TypeArgBuilder = TypeArgBuilder.create<ProtectedBranchConnection, ProtectedBranchesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ProtectedBranchesArgs = apply { addArg("first", value) }


    fun after(value: String): ProtectedBranchesArgs = apply { addArg("after", value) }


    fun last(value: Int): ProtectedBranchesArgs = apply { addArg("last", value) }


    fun before(value: String): ProtectedBranchesArgs = apply { addArg("before", value) }

  }

  class PullRequestArgs(args: TypeArgBuilder = TypeArgBuilder.create<PullRequest, PullRequestArgs>()) : TypeArgBuilder by args {
    fun number(value: Int): PullRequestArgs = apply { addArg("number", value) }

  }

  class PullRequestsArgs(args: TypeArgBuilder = TypeArgBuilder.create<PullRequestConnection, PullRequestsArgs>()) : TypeArgBuilder by args {
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

  class RefArgs(args: TypeArgBuilder = TypeArgBuilder.create<Ref, RefArgs>()) : TypeArgBuilder by args {
    fun qualifiedName(value: String): RefArgs = apply { addArg("qualifiedName", value) }

  }

  class RefsArgs(args: TypeArgBuilder = TypeArgBuilder.create<RefConnection, RefsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): RefsArgs = apply { addArg("first", value) }


    fun after(value: String): RefsArgs = apply { addArg("after", value) }


    fun last(value: Int): RefsArgs = apply { addArg("last", value) }


    fun before(value: String): RefsArgs = apply { addArg("before", value) }


    fun refPrefix(value: String): RefsArgs = apply { addArg("refPrefix", value) }


    fun direction(value: OrderDirection): RefsArgs = apply { addArg("direction", value) }

  }

  class ReleasesArgs(args: TypeArgBuilder = TypeArgBuilder.create<ReleaseConnection, ReleasesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ReleasesArgs = apply { addArg("first", value) }


    fun after(value: String): ReleasesArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleasesArgs = apply { addArg("last", value) }


    fun before(value: String): ReleasesArgs = apply { addArg("before", value) }

  }

  class RepositoryTopicsArgs(args: TypeArgBuilder = TypeArgBuilder.create<RepositoryTopicConnection, RepositoryTopicsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): RepositoryTopicsArgs = apply { addArg("first", value) }


    fun after(value: String): RepositoryTopicsArgs = apply { addArg("after", value) }


    fun last(value: Int): RepositoryTopicsArgs = apply { addArg("last", value) }


    fun before(value: String): RepositoryTopicsArgs = apply { addArg("before", value) }

  }

  class WatchersArgs(args: TypeArgBuilder = TypeArgBuilder.create<UserConnection, WatchersArgs>()) : TypeArgBuilder by args {
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
  val edges: ListInitStub<RepositoryEdge> = QTypeList.stub()

  val nodes: ListInitStub<Repository> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()

  val totalDiskUsage: Stub<Int> = QScalar.stub()
}

object RepositoryEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Repository> = QType.stub()
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
  override val id: Stub<String> = QScalar.stub()

  val invitee: InitStub<User> = QType.stub()

  val inviter: InitStub<User> = QType.stub()

  val repository: InitStub<RepositoryInvitationRepository> = QType.stub()
}

object RepositoryInvitationRepository : QSchemaType, RepositoryInfo {
  override val createdAt: Stub<DateTime> = QScalar.stub()

  override val description: Stub<String> = QScalar.stub()

  override val descriptionHTML: Stub<HTML> = QScalar.stub()

  override val hasIssuesEnabled: Stub<Boolean> = QScalar.stub()

  override val hasWikiEnabled: Stub<Boolean> = QScalar.stub()

  override val homepageUrl: Stub<URI> = QScalar.stub()

  override val isFork: Stub<Boolean> = QScalar.stub()

  override val isLocked: Stub<Boolean> = QScalar.stub()

  override val isMirror: Stub<Boolean> = QScalar.stub()

  override val isPrivate: Stub<Boolean> = QScalar.stub()

  override val license: Stub<String> = QScalar.stub()

  override val lockReason: Stub<RepositoryLockReason> = QScalar.stub()

  override val mirrorUrl: Stub<URI> = QScalar.stub()

  override val name: Stub<String> = QScalar.stub()

  override val nameWithOwner: Stub<String> = QScalar.stub()

  override val owner: InitStub<RepositoryOwner> = QType.stub()

  override val pushedAt: Stub<DateTime> = QScalar.stub()

  override val resourcePath: Stub<URI> = QScalar.stub()

  override val updatedAt: Stub<DateTime> = QScalar.stub()

  override val url: Stub<URI> = QScalar.stub()
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

  class PinnedRepositoriesArgs(args: TypeArgBuilder = TypeArgBuilder.create<RepositoryConnection, PinnedRepositoriesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): PinnedRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): PinnedRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): PinnedRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): PinnedRepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs = apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): PinnedRepositoriesArgs = apply { addArg("isLocked", value) }

  }

  class RepositoriesArgs(args: TypeArgBuilder = TypeArgBuilder.create<RepositoryConnection, RepositoriesArgs>()) : TypeArgBuilder by args {
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

  class RepositoryArgs(args: TypeArgBuilder = TypeArgBuilder.create<Repository, RepositoryArgs>()) : TypeArgBuilder by args {
    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }
}

enum class RepositoryPrivacy : QSchemaType {
  PUBLIC,

  PRIVATE
}

object RepositoryTopic : QSchemaType, UniformResourceLocatable, Node {
  override val id: Stub<String> = QScalar.stub()

  override val resourcePath: Stub<URI> = QScalar.stub()

  val topic: InitStub<Topic> = QType.stub()

  override val url: Stub<URI> = QScalar.stub()
}

object RepositoryTopicConnection : QSchemaType {
  val edges: ListInitStub<RepositoryTopicEdge> = QTypeList.stub()

  val nodes: ListInitStub<RepositoryTopic> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object RepositoryTopicEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<RepositoryTopic> = QType.stub()
}

data class RequestReviewsInput(private val pullRequestId: String, private val userIds: String,
    private val teamIds: String) : QInput {
  private var clientMutationId: String? = null
  private var union: Boolean? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun union(model: Boolean) = apply { union = model }
}

object RequestReviewsPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  val requestedReviewersEdge: InitStub<UserEdge> = QType.stub()
}

object ReviewDismissalAllowance : QSchemaType, Node {
  val actor: InitStub<ReviewDismissalAllowanceActor> = QType.stub()

  override val id: Stub<String> = QScalar.stub()

  val protectedBranch: InitStub<ProtectedBranch> = QType.stub()
}

object ReviewDismissalAllowanceActor : QSchemaType {
  val User: ListInitStub<User> = QTypeList.stub()

  val Team: ListInitStub<Team> = QTypeList.stub()
}

object ReviewDismissalAllowanceConnection : QSchemaType {
  val edges: ListInitStub<ReviewDismissalAllowanceEdge> = QTypeList.stub()

  val nodes: ListInitStub<ReviewDismissalAllowance> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object ReviewDismissalAllowanceEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<ReviewDismissalAllowance> = QType.stub()
}

object ReviewDismissedEvent : QSchemaType, UniformResourceLocatable, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val message: Stub<String> = QScalar.stub()

  val messageHtml: Stub<HTML> = QScalar.stub()

  val previousReviewState: Stub<PullRequestReviewState> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  val pullRequestCommit: InitStub<PullRequestCommit> = QType.stub()

  override val resourcePath: Stub<URI> = QScalar.stub()

  val review: InitStub<PullRequestReview> = QType.stub()

  override val url: Stub<URI> = QScalar.stub()
}

object ReviewRequest : QSchemaType, Node {
  val databaseId: Stub<Int> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  val reviewer: InitStub<User> = QType.stub()
}

object ReviewRequestConnection : QSchemaType {
  val edges: ListInitStub<ReviewRequestEdge> = QTypeList.stub()

  val nodes: ListInitStub<ReviewRequest> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object ReviewRequestEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<ReviewRequest> = QType.stub()
}

object ReviewRequestRemovedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  val subject: InitStub<User> = QType.stub()
}

object ReviewRequestedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val pullRequest: InitStub<PullRequest> = QType.stub()

  val subject: InitStub<User> = QType.stub()
}

object SearchResultItem : QSchemaType {
  val Issue: ListInitStub<Issue> = QTypeList.stub()

  val PullRequest: ListInitStub<PullRequest> = QTypeList.stub()

  val Repository: ListInitStub<Repository> = QTypeList.stub()

  val User: ListInitStub<User> = QTypeList.stub()

  val Organization: ListInitStub<Organization> = QTypeList.stub()
}

object SearchResultItemConnection : QSchemaType {
  val codeCount: Stub<Int> = QScalar.stub()

  val edges: ListInitStub<SearchResultItemEdge> = QTypeList.stub()

  val issueCount: Stub<Int> = QScalar.stub()

  val nodes: ListInitStub<SearchResultItem> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val repositoryCount: Stub<Int> = QScalar.stub()

  val userCount: Stub<Int> = QScalar.stub()

  val wikiCount: Stub<Int> = QScalar.stub()
}

object SearchResultItemEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<SearchResultItem> = QType.stub()
}

enum class SearchType : QSchemaType {
  ISSUE,

  REPOSITORY,

  USER
}

object SmimeSignature : QSchemaType, GitSignature {
  override val email: Stub<String> = QScalar.stub()

  override val isValid: Stub<Boolean> = QScalar.stub()

  override val payload: Stub<String> = QScalar.stub()

  override val signature: Stub<String> = QScalar.stub()

  override val signer: InitStub<User> = QType.stub()

  override val state: Stub<GitSignatureState> = QScalar.stub()
}

data class StarOrder(private val field: StarOrderField,
    private val direction: OrderDirection) : QInput

enum class StarOrderField : QSchemaType {
  STARRED_AT
}

object StargazerConnection : QSchemaType {
  val edges: ListInitStub<StargazerEdge> = QTypeList.stub()

  val nodes: ListInitStub<User> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object StargazerEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<User> = QType.stub()

  val starredAt: Stub<DateTime> = QScalar.stub()
}

interface Starrable : QSchemaType {
  val id: Stub<String>

  val stargazers: QTypeConfigStub<StargazerConnection, StargazersArgs>

  val viewerHasStarred: Stub<Boolean>

  class StargazersArgs(args: TypeArgBuilder = TypeArgBuilder.create<StargazerConnection, StargazersArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): StargazersArgs = apply { addArg("first", value) }


    fun after(value: String): StargazersArgs = apply { addArg("after", value) }


    fun last(value: Int): StargazersArgs = apply { addArg("last", value) }


    fun before(value: String): StargazersArgs = apply { addArg("before", value) }


    fun orderBy(value: StarOrder): StargazersArgs = apply { addArg("orderBy", value) }

  }
}

object StarredRepositoryConnection : QSchemaType {
  val edges: ListInitStub<StarredRepositoryEdge> = QTypeList.stub()

  val nodes: ListInitStub<Repository> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object StarredRepositoryEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Repository> = QType.stub()

  val starredAt: Stub<DateTime> = QScalar.stub()
}

object Status : QSchemaType, Node {
  val commit: InitStub<Commit> = QType.stub()

  val context: QTypeConfigStub<StatusContext, ContextArgs> = QType.configStub(ContextArgs())

  val contexts: ListInitStub<StatusContext> = QTypeList.stub()

  override val id: Stub<String> = QScalar.stub()

  val state: Stub<StatusState> = QScalar.stub()

  class ContextArgs(args: TypeArgBuilder = TypeArgBuilder.create<StatusContext, ContextArgs>()) : TypeArgBuilder by args {
    fun name(value: String): ContextArgs = apply { addArg("name", value) }

  }
}

object StatusContext : QSchemaType, Node {
  val commit: InitStub<Commit> = QType.stub()

  val context: Stub<String> = QScalar.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  val creator: InitStub<Actor> = QType.stub()

  val description: Stub<String> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val state: Stub<StatusState> = QScalar.stub()

  val targetUrl: Stub<URI> = QScalar.stub()
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
  val clientMutationId: Stub<String> = QScalar.stub()

  val pullRequestReview: InitStub<PullRequestReview> = QType.stub()
}

interface Subscribable : QSchemaType {
  val viewerCanSubscribe: Stub<Boolean>

  val viewerSubscription: Stub<SubscriptionState>
}

object SubscribedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val subscribable: InitStub<Subscribable> = QType.stub()
}

enum class SubscriptionState : QSchemaType {
  UNSUBSCRIBED,

  SUBSCRIBED,

  IGNORED
}

object SuggestedReviewer : QSchemaType {
  val isAuthor: Stub<Boolean> = QScalar.stub()

  val isCommenter: Stub<Boolean> = QScalar.stub()

  val reviewer: InitStub<User> = QType.stub()
}

object Tag : QSchemaType, GitObject, Node {
  override val abbreviatedOid: Stub<String> = QScalar.stub()

  override val commitResourcePath: Stub<URI> = QScalar.stub()

  override val commitUrl: Stub<URI> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val message: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  override val oid: Stub<GitObjectID> = QScalar.stub()

  override val repository: InitStub<Repository> = QType.stub()

  val tagger: InitStub<GitActor> = QType.stub()

  val target: InitStub<GitObject> = QType.stub()
}

object Team : QSchemaType, Node {
  val description: Stub<String> = QScalar.stub()

  val editTeamResourcePath: Stub<URI> = QScalar.stub()

  val editTeamUrl: Stub<URI> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val invitations: QTypeConfigStub<OrganizationInvitationConnection, InvitationsArgs> = QType.configStub(InvitationsArgs())

  val name: Stub<String> = QScalar.stub()

  val organization: InitStub<Organization> = QType.stub()

  val privacy: Stub<TeamPrivacy> = QScalar.stub()

  val resourcePath: Stub<URI> = QScalar.stub()

  val slug: Stub<String> = QScalar.stub()

  val url: Stub<URI> = QScalar.stub()

  class InvitationsArgs(args: TypeArgBuilder = TypeArgBuilder.create<OrganizationInvitationConnection, InvitationsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): InvitationsArgs = apply { addArg("first", value) }


    fun after(value: String): InvitationsArgs = apply { addArg("after", value) }


    fun last(value: Int): InvitationsArgs = apply { addArg("last", value) }


    fun before(value: String): InvitationsArgs = apply { addArg("before", value) }

  }
}

object TeamConnection : QSchemaType {
  val edges: ListInitStub<TeamEdge> = QTypeList.stub()

  val nodes: ListInitStub<Team> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object TeamEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<Team> = QType.stub()
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
  override val id: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val relatedTopics: ListInitStub<Topic> = QTypeList.stub()
}

enum class TopicSuggestionDeclineReason : QSchemaType {
  NOT_RELEVANT,

  TOO_SPECIFIC,

  PERSONAL_PREFERENCE,

  TOO_GENERAL
}

object Tree : QSchemaType, GitObject, Node {
  override val abbreviatedOid: Stub<String> = QScalar.stub()

  override val commitResourcePath: Stub<URI> = QScalar.stub()

  override val commitUrl: Stub<URI> = QScalar.stub()

  val entries: ListInitStub<TreeEntry> = QTypeList.stub()

  override val id: Stub<String> = QScalar.stub()

  override val oid: Stub<GitObjectID> = QScalar.stub()

  override val repository: InitStub<Repository> = QType.stub()
}

object TreeEntry : QSchemaType {
  val mode: Stub<Int> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val objectVal: InitStub<GitObject> = QType.stub()

  val oid: Stub<GitObjectID> = QScalar.stub()

  val repository: InitStub<Repository> = QType.stub()

  val type: Stub<String> = QScalar.stub()
}

object URI : QSchemaType {
  val value: Stub<String> = QScalar.stub()
}

object UnassignedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val assignable: InitStub<Assignable> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val user: InitStub<User> = QType.stub()
}

interface UniformResourceLocatable : QSchemaType {
  val resourcePath: Stub<URI>

  val url: Stub<URI>
}

object UnknownSignature : QSchemaType, GitSignature {
  override val email: Stub<String> = QScalar.stub()

  override val isValid: Stub<Boolean> = QScalar.stub()

  override val payload: Stub<String> = QScalar.stub()

  override val signature: Stub<String> = QScalar.stub()

  override val signer: InitStub<User> = QType.stub()

  override val state: Stub<GitSignatureState> = QScalar.stub()
}

object UnlabeledEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val label: InitStub<Label> = QType.stub()

  val labelable: InitStub<Labelable> = QType.stub()
}

object UnlockedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val lockable: InitStub<Lockable> = QType.stub()
}

object UnsubscribedEvent : QSchemaType, Node {
  val actor: InitStub<Actor> = QType.stub()

  val createdAt: Stub<DateTime> = QScalar.stub()

  override val id: Stub<String> = QScalar.stub()

  val subscribable: InitStub<Subscribable> = QType.stub()
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
  val clientMutationId: Stub<String> = QScalar.stub()

  val projectCard: InitStub<ProjectCard> = QType.stub()
}

data class UpdateProjectColumnInput(private val projectColumnId: String,
    private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateProjectColumnPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val projectColumn: InitStub<ProjectColumn> = QType.stub()
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
  val clientMutationId: Stub<String> = QScalar.stub()

  val project: InitStub<Project> = QType.stub()
}

data class UpdatePullRequestReviewCommentInput(private val pullRequestReviewCommentId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdatePullRequestReviewCommentPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val pullRequestReviewComment: InitStub<PullRequestReviewComment> = QType.stub()
}

data class UpdatePullRequestReviewInput(private val pullRequestReviewId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdatePullRequestReviewPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val pullRequestReview: InitStub<PullRequestReview> = QType.stub()
}

data class UpdateSubscriptionInput(private val subscribableId: String,
    private val state: SubscriptionState) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateSubscriptionPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val subscribable: InitStub<Subscribable> = QType.stub()
}

data class UpdateTopicsInput(private val repositoryId: String,
    private val topicNames: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateTopicsPayload : QSchemaType {
  val clientMutationId: Stub<String> = QScalar.stub()

  val invalidTopicNames: ListStub<String> = QScalarList.stub()

  val repository: InitStub<Repository> = QType.stub()
}

object User : QSchemaType, UniformResourceLocatable, RepositoryOwner, Actor, Node {
  override val avatarUrl: QConfigStub<URI, AvatarUrlArgs> = QScalar.configStub(AvatarUrlArgs())

  val bio: Stub<String> = QScalar.stub()

  val bioHTML: Stub<HTML> = QScalar.stub()

  val company: Stub<String> = QScalar.stub()

  val companyHTML: Stub<HTML> = QScalar.stub()

  val contributedRepositories: QTypeConfigStub<RepositoryConnection, ContributedRepositoriesArgs> = QType.configStub(ContributedRepositoriesArgs())

  val createdAt: Stub<DateTime> = QScalar.stub()

  val databaseId: Stub<Int> = QScalar.stub()

  val email: Stub<String> = QScalar.stub()

  val followers: QTypeConfigStub<FollowerConnection, FollowersArgs> = QType.configStub(FollowersArgs())

  val following: QTypeConfigStub<FollowingConnection, FollowingArgs> = QType.configStub(FollowingArgs())

  val gist: QTypeConfigStub<Gist, GistArgs> = QType.configStub(GistArgs())

  val gists: QTypeConfigStub<GistConnection, GistsArgs> = QType.configStub(GistsArgs())

  override val id: Stub<String> = QScalar.stub()

  val isBountyHunter: Stub<Boolean> = QScalar.stub()

  val isCampusExpert: Stub<Boolean> = QScalar.stub()

  val isDeveloperProgramMember: Stub<Boolean> = QScalar.stub()

  val isEmployee: Stub<Boolean> = QScalar.stub()

  val isHireable: Stub<Boolean> = QScalar.stub()

  val isInvoiced: Stub<Boolean> = QScalar.stub()

  val isSiteAdmin: Stub<Boolean> = QScalar.stub()

  val isViewer: Stub<Boolean> = QScalar.stub()

  val issues: QTypeConfigStub<IssueConnection, IssuesArgs> = QType.configStub(IssuesArgs())

  val location: Stub<String> = QScalar.stub()

  override val login: Stub<String> = QScalar.stub()

  val name: Stub<String> = QScalar.stub()

  val organization: QTypeConfigStub<Organization, OrganizationArgs> = QType.configStub(OrganizationArgs())

  val organizations: QTypeConfigStub<OrganizationConnection, OrganizationsArgs> = QType.configStub(OrganizationsArgs())

  override val pinnedRepositories: QTypeConfigStub<RepositoryConnection, RepositoryOwner.PinnedRepositoriesArgs> = QType.configStub(RepositoryOwner.PinnedRepositoriesArgs())

  val pullRequests: QTypeConfigStub<PullRequestConnection, PullRequestsArgs> = QType.configStub(PullRequestsArgs())

  override val repositories: QTypeConfigStub<RepositoryConnection, RepositoryOwner.RepositoriesArgs> = QType.configStub(RepositoryOwner.RepositoriesArgs())

  override val repository: QTypeConfigStub<Repository, RepositoryOwner.RepositoryArgs> = QType.configStub(RepositoryOwner.RepositoryArgs())

  override val resourcePath: Stub<URI> = QScalar.stub()

  val starredRepositories: QTypeConfigStub<StarredRepositoryConnection, StarredRepositoriesArgs> = QType.configStub(StarredRepositoriesArgs())

  val updatedAt: Stub<DateTime> = QScalar.stub()

  override val url: Stub<URI> = QScalar.stub()

  val viewerCanFollow: Stub<Boolean> = QScalar.stub()

  val viewerIsFollowing: Stub<Boolean> = QScalar.stub()

  val watching: QTypeConfigStub<RepositoryConnection, WatchingArgs> = QType.configStub(WatchingArgs())

  val websiteUrl: Stub<URI> = QScalar.stub()

  class AvatarUrlArgs(args: ArgBuilder = ArgBuilder.create<URI, AvatarUrlArgs>()) : BaseAvatarUrlArgs(args) {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }

  class ContributedRepositoriesArgs(args: TypeArgBuilder = TypeArgBuilder.create<RepositoryConnection, ContributedRepositoriesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): ContributedRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): ContributedRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): ContributedRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): ContributedRepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): ContributedRepositoriesArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): ContributedRepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): ContributedRepositoriesArgs = apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): ContributedRepositoriesArgs = apply { addArg("isLocked", value) }

  }

  class FollowersArgs(args: TypeArgBuilder = TypeArgBuilder.create<FollowerConnection, FollowersArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): FollowersArgs = apply { addArg("first", value) }


    fun after(value: String): FollowersArgs = apply { addArg("after", value) }


    fun last(value: Int): FollowersArgs = apply { addArg("last", value) }


    fun before(value: String): FollowersArgs = apply { addArg("before", value) }

  }

  class FollowingArgs(args: TypeArgBuilder = TypeArgBuilder.create<FollowingConnection, FollowingArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): FollowingArgs = apply { addArg("first", value) }


    fun after(value: String): FollowingArgs = apply { addArg("after", value) }


    fun last(value: Int): FollowingArgs = apply { addArg("last", value) }


    fun before(value: String): FollowingArgs = apply { addArg("before", value) }

  }

  class GistArgs(args: TypeArgBuilder = TypeArgBuilder.create<Gist, GistArgs>()) : TypeArgBuilder by args {
    fun name(value: String): GistArgs = apply { addArg("name", value) }

  }

  class GistsArgs(args: TypeArgBuilder = TypeArgBuilder.create<GistConnection, GistsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): GistsArgs = apply { addArg("first", value) }


    fun after(value: String): GistsArgs = apply { addArg("after", value) }


    fun last(value: Int): GistsArgs = apply { addArg("last", value) }


    fun before(value: String): GistsArgs = apply { addArg("before", value) }


    fun privacy(value: GistPrivacy): GistsArgs = apply { addArg("privacy", value) }

  }

  class IssuesArgs(args: TypeArgBuilder = TypeArgBuilder.create<IssueConnection, IssuesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class OrganizationArgs(args: TypeArgBuilder = TypeArgBuilder.create<Organization, OrganizationArgs>()) : TypeArgBuilder by args {
    fun login(value: String): OrganizationArgs = apply { addArg("login", value) }

  }

  class OrganizationsArgs(args: TypeArgBuilder = TypeArgBuilder.create<OrganizationConnection, OrganizationsArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): OrganizationsArgs = apply { addArg("first", value) }


    fun after(value: String): OrganizationsArgs = apply { addArg("after", value) }


    fun last(value: Int): OrganizationsArgs = apply { addArg("last", value) }


    fun before(value: String): OrganizationsArgs = apply { addArg("before", value) }

  }

  class PullRequestsArgs(args: TypeArgBuilder = TypeArgBuilder.create<PullRequestConnection, PullRequestsArgs>()) : TypeArgBuilder by args {
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

  class StarredRepositoriesArgs(args: TypeArgBuilder = TypeArgBuilder.create<StarredRepositoryConnection, StarredRepositoriesArgs>()) : TypeArgBuilder by args {
    fun first(value: Int): StarredRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): StarredRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): StarredRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): StarredRepositoriesArgs = apply { addArg("before", value) }


    fun ownedByViewer(value: Boolean): StarredRepositoriesArgs = apply { addArg("ownedByViewer", value) }


    fun orderBy(value: StarOrder): StarredRepositoriesArgs = apply { addArg("orderBy", value) }

  }

  class WatchingArgs(args: TypeArgBuilder = TypeArgBuilder.create<RepositoryConnection, WatchingArgs>()) : TypeArgBuilder by args {
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
  val edges: ListInitStub<UserEdge> = QTypeList.stub()

  val nodes: ListInitStub<User> = QTypeList.stub()

  val pageInfo: InitStub<PageInfo> = QType.stub()

  val totalCount: Stub<Int> = QScalar.stub()
}

object UserEdge : QSchemaType {
  val cursor: Stub<String> = QScalar.stub()

  val node: InitStub<User> = QType.stub()
}

object X509Certificate : QSchemaType {
  val value: Stub<String> = QScalar.stub()
}

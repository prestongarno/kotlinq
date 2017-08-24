@file:Suppress("unused")

package com.prestongarno.ktq

class BaseAvatarUrlArgs(args: AvatarUrlArgs = ArgBuilder.create<String, BaseAvatarUrlArgs>()) : AvatarUrlArgs by args

data class AcceptTopicSuggestionInput(private val repositoryId: String,
    private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AcceptTopicSuggestionPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val topic: InitStub<Topic>
}

interface Actor : QType {
  abstract val avatarUrl: Config<String, BaseAvatarUrlArgs>

  abstract val login: Stub<String>

  abstract val resourcePath: Stub<URI>

  abstract val url: Stub<URI>
}

data class AddCommentInput(private val subjectId: String, private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddCommentPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val commentEdge: InitStub<IssueCommentEdge>

  abstract val subject: InitStub<Node>

  abstract val timelineEdge: InitStub<IssueTimelineItemEdge>
}

data class AddProjectCardInput(private val projectColumnId: String) : QInput {
  private var clientMutationId: String? = null
  private var contentId: String? = null
  private var note: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun contentId(model: String) = apply { contentId = model }

  fun note(model: String) = apply { note = model }
}

object AddProjectCardPayload : QType {
  abstract val cardEdge: InitStub<ProjectCardEdge>

  abstract val clientMutationId: Stub<String>

  abstract val projectColumn: InitStub<Project>
}

data class AddProjectColumnInput(private val projectId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddProjectColumnPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val columnEdge: InitStub<ProjectColumnEdge>

  abstract val project: InitStub<Project>
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

object AddPullRequestReviewCommentPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val comment: InitStub<PullRequestReviewComment>

  abstract val commentEdge: InitStub<PullRequestReviewCommentEdge>
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

object AddPullRequestReviewPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val pullRequestReview: InitStub<PullRequestReview>

  abstract val reviewEdge: InitStub<PullRequestReviewEdge>
}

data class AddReactionInput(private val subjectId: String,
    private val content: ReactionContent) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddReactionPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val reaction: InitStub<Reaction>

  abstract val subject: InitStub<Reactable>
}

data class AddStarInput(private val starrableId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object AddStarPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val starrable: InitStub<Starrable>
}

object AddedToProjectEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>
}

interface Assignable : QType {
  abstract val assignees: ConfigType<UserConnection, AssigneesArgs>
}

object AssignedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val assignable: InitStub<Assignable>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val user: InitStub<User>
}

object BaseRefChangedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>
}

object BaseRefForcePushedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val afterCommit: InitStub<Commit>

  abstract val beforeCommit: InitStub<Commit>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>

  abstract val ref: InitStub<Ref>
}

object Blame : QType {
  abstract val ranges: InitStub<BlameRange>
}

object BlameRange : QType {
  abstract val age: Stub<Int>

  abstract val commit: InitStub<Commit>

  abstract val endingLine: Stub<Int>

  abstract val startingLine: Stub<Int>
}

object Blob : QType, GitObject, Node {
  val abbreviatedOid: Stub<String>

  abstract val byteSize: Stub<Int>

  val commitResourcePath: Stub<URI>

  val commitUrl: Stub<URI>

  val id: Stub<String>

  abstract val isBinary: Stub<Boolean>

  abstract val isTruncated: Stub<Boolean>

  val oid: Stub<GitObjectID>

  val repository: InitStub<Repository>

  abstract val text: Stub<String>
}

object Bot : QType, UniformResourceLocatable, Actor, Node {
  val avatarUrl: Config<String, Base>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>

  val login: Stub<String>

  val resourcePath: Stub<URI>

  val url: Stub<URI>

  class Base(args: AvatarUrlArgs = ArgBuilder.create<String, Base>()) : Base(args) {
    fun size(value: Int): Base = apply { addArg("size", value) }

  }
}

interface Closable : QType {
  abstract val closed: Stub<Boolean>
}

object ClosedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val closable: InitStub<Closable>

  abstract val commit: InitStub<Commit>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>
}

object CodeOfConduct : QType {
  abstract val body: Stub<String>

  abstract val key: Stub<String>

  abstract val name: Stub<String>

  abstract val url: Stub<URI>
}

interface Comment : QType {
  abstract val author: InitStub<Actor>

  abstract val authorAssociation: Stub<CommentAuthorAssociation>

  abstract val body: Stub<String>

  abstract val bodyHTML: Stub<HTML>

  abstract val createdAt: Stub<DateTime>

  abstract val createdViaEmail: Stub<Boolean>

  abstract val editor: InitStub<Actor>

  abstract val id: Stub<String>

  abstract val lastEditedAt: Stub<DateTime>

  abstract val publishedAt: Stub<DateTime>

  abstract val updatedAt: Stub<DateTime>

  abstract val viewerDidAuthor: Stub<Boolean>
}

enum class CommentAuthorAssociation : QType {
  MEMBER,

  OWNER,

  COLLABORATOR,

  CONTRIBUTOR,

  FIRST_TIME_CONTRIBUTOR,

  NONE
}

enum class CommentCannotUpdateReason : QType {
  INSUFFICIENT_ACCESS,

  LOCKED,

  LOGIN_REQUIRED,

  MAINTENANCE,

  VERIFIED_EMAIL_REQUIRED
}

object CommentDeletedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>
}

object Commit : QType, Subscribable, GitObject, Node {
  val abbreviatedOid: Stub<String>

  abstract val author: InitStub<GitActor>

  abstract val authoredByCommitter: Stub<Boolean>

  abstract val blame: ConfigType<Blame, BlameArgs>

  abstract val comments: ConfigType<CommitCommentConnection, CommentsArgs>

  val commitResourcePath: Stub<URI>

  val commitUrl: Stub<URI>

  abstract val committedDate: Stub<DateTime>

  abstract val committedViaWeb: Stub<Boolean>

  abstract val committer: InitStub<GitActor>

  abstract val history: ConfigType<CommitHistoryConnection, HistoryArgs>

  val id: Stub<String>

  abstract val message: Stub<String>

  abstract val messageBody: Stub<String>

  abstract val messageBodyHTML: Stub<HTML>

  abstract val messageHeadline: Stub<String>

  abstract val messageHeadlineHTML: Stub<HTML>

  val oid: Stub<GitObjectID>

  val repository: InitStub<Repository>

  abstract val resourcePath: Stub<URI>

  abstract val signature: InitStub<GitSignature>

  abstract val status: InitStub<Status>

  abstract val tree: InitStub<Tree>

  abstract val treeResourcePath: Stub<URI>

  abstract val treeUrl: Stub<URI>

  abstract val url: Stub<URI>

  val viewerCanSubscribe: Stub<Boolean>

  val viewerSubscription: Stub<SubscriptionState>
}

data class CommitAuthor(private val emails: String) : QInput {
  private var id: String? = null
  fun id(model: String) = apply { id = model }
}

object CommitComment : QType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  val author: InitStub<Actor>

  val authorAssociation: Stub<CommentAuthorAssociation>

  val body: Stub<String>

  val bodyHTML: Stub<HTML>

  abstract val commit: InitStub<Commit>

  val createdAt: Stub<DateTime>

  val createdViaEmail: Stub<Boolean>

  val databaseId: Stub<Int>

  val editor: InitStub<Actor>

  val id: Stub<String>

  val lastEditedAt: Stub<DateTime>

  abstract val path: Stub<String>

  abstract val position: Stub<Int>

  val publishedAt: Stub<DateTime>

  val reactionGroups: InitStub<ReactionGroup>

  val reactions: ConfigType<ReactionConnection, Base>

  val repository: InitStub<Repository>

  val updatedAt: Stub<DateTime>

  val viewerCanDelete: Stub<Boolean>

  val viewerCanReact: Stub<Boolean>

  val viewerCanUpdate: Stub<Boolean>

  val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason>

  val viewerDidAuthor: Stub<Boolean>

  class ReactionsArgs(args: ReactionsArgs = TypeArgBuilder.create<ReactionConnection, ReactionsArgs>()) : ReactionsArgs by args {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

object CommitCommentConnection : QType {
  abstract val edges: InitStub<CommitCommentEdge>

  abstract val nodes: InitStub<CommitComment>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object CommitCommentEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<CommitComment>
}

object CommitCommentThread : QType, RepositoryNode, Node {
  abstract val comments: ConfigType<CommitCommentConnection, CommentsArgs>

  abstract val commit: InitStub<Commit>

  val id: Stub<String>

  abstract val path: Stub<String>

  abstract val position: Stub<Int>

  val repository: InitStub<Repository>
}

object CommitEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Commit>
}

object CommitHistoryConnection : QType {
  abstract val edges: InitStub<CommitEdge>

  abstract val nodes: InitStub<Commit>

  abstract val pageInfo: InitStub<PageInfo>
}

object ConvertedNoteToIssueEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>
}

data class CreateProjectInput(private val ownerId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun body(model: String) = apply { body = model }
}

object CreateProjectPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val project: InitStub<Project>
}

object DateTime : QType {
  abstract val model: Stub<String>
}

data class DeclineTopicSuggestionInput(private val repositoryId: String, private val name: String,
    private val reason: TopicSuggestionDeclineReason) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeclineTopicSuggestionPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val topic: InitStub<Topic>
}

enum class DefaultRepositoryPermissionField : QType {
  READ,

  WRITE,

  ADMIN
}

interface Deletable : QType {
  abstract val viewerCanDelete: Stub<Boolean>
}

data class DeleteProjectCardInput(private val cardId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeleteProjectCardPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val column: InitStub<ProjectColumn>

  abstract val deletedCardId: Stub<String>
}

data class DeleteProjectColumnInput(private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeleteProjectColumnPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val deletedColumnId: Stub<String>

  abstract val project: InitStub<Project>
}

data class DeleteProjectInput(private val projectId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeleteProjectPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val owner: InitStub<ProjectOwner>
}

data class DeletePullRequestReviewInput(private val pullRequestReviewId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object DeletePullRequestReviewPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val pullRequestReview: InitStub<PullRequestReview>
}

object DemilestonedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val milestoneTitle: Stub<String>

  abstract val subject: InitStub<MilestoneItem>
}

object DeployedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  abstract val deployment: InitStub<Deployment>

  val id: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>

  abstract val ref: InitStub<Ref>
}

object Deployment : QType, Node {
  abstract val commit: InitStub<Commit>

  abstract val createdAt: Stub<DateTime>

  abstract val creator: InitStub<Actor>

  abstract val environment: Stub<String>

  val id: Stub<String>

  abstract val latestStatus: InitStub<DeploymentStatus>

  abstract val repository: InitStub<Repository>

  abstract val state: Stub<DeploymentState>

  abstract val statuses: ConfigType<DeploymentStatusConnection, StatusesArgs>
}

object DeploymentConnection : QType {
  abstract val edges: InitStub<DeploymentEdge>

  abstract val nodes: InitStub<Deployment>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object DeploymentEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Deployment>
}

enum class DeploymentState : QType {
  ABANDONED,

  ACTIVE,

  DESTROYED,

  ERROR,

  FAILURE,

  INACTIVE,

  PENDING
}

object DeploymentStatus : QType, Node {
  abstract val creator: InitStub<Actor>

  abstract val deployment: InitStub<Deployment>

  abstract val description: Stub<String>

  abstract val environmentUrl: Stub<URI>

  val id: Stub<String>

  abstract val logUrl: Stub<URI>

  abstract val state: Stub<DeploymentStatusState>
}

object DeploymentStatusConnection : QType {
  abstract val edges: InitStub<DeploymentStatusEdge>

  abstract val nodes: InitStub<DeploymentStatus>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object DeploymentStatusEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<DeploymentStatus>
}

enum class DeploymentStatusState : QType {
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

object DismissPullRequestReviewPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val pullRequestReview: InitStub<PullRequestReview>
}

data class DraftPullRequestReviewComment(private val path: String, private val position: Int,
    private val body: String) : QInput

object ExternalIdentity : QType, Node {
  abstract val guid: Stub<String>

  val id: Stub<String>

  abstract val organizationInvitation: InitStub<OrganizationInvitation>

  abstract val samlIdentity: InitStub<ExternalIdentitySamlAttributes>

  abstract val scimIdentity: InitStub<ExternalIdentityScimAttributes>

  abstract val user: InitStub<User>
}

object ExternalIdentityConnection : QType {
  abstract val edges: InitStub<ExternalIdentityEdge>

  abstract val nodes: InitStub<ExternalIdentity>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object ExternalIdentityEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<ExternalIdentity>
}

object ExternalIdentitySamlAttributes : QType {
  abstract val nameId: Stub<String>
}

object ExternalIdentityScimAttributes : QType {
  abstract val username: Stub<String>
}

object FollowerConnection : QType {
  abstract val edges: InitStub<UserEdge>

  abstract val nodes: InitStub<User>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object FollowingConnection : QType {
  abstract val edges: InitStub<UserEdge>

  abstract val nodes: InitStub<User>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object Gist : QType, Starrable, Node {
  abstract val comments: ConfigType<GistCommentConnection, CommentsArgs>

  abstract val createdAt: Stub<DateTime>

  abstract val description: Stub<String>

  val id: Stub<String>

  abstract val isPublic: Stub<Boolean>

  abstract val name: Stub<String>

  abstract val owner: InitStub<RepositoryOwner>

  val stargazers: ConfigType<StargazerConnection, Base>

  abstract val updatedAt: Stub<DateTime>

  val viewerHasStarred: Stub<Boolean>

  class StargazersArgs(args: StargazersArgs = TypeArgBuilder.create<StargazerConnection, StargazersArgs>()) : StargazersArgs by args {
    fun first(value: Int): StargazersArgs = apply { addArg("first", value) }


    fun after(value: String): StargazersArgs = apply { addArg("after", value) }


    fun last(value: Int): StargazersArgs = apply { addArg("last", value) }


    fun before(value: String): StargazersArgs = apply { addArg("before", value) }


    fun orderBy(value: StarOrder): StargazersArgs = apply { addArg("orderBy", value) }

  }
}

object GistComment : QType, UpdatableComment, Updatable, Deletable, Comment, Node {
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

  val viewerCanDelete: Stub<Boolean>

  val viewerCanUpdate: Stub<Boolean>

  val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason>

  val viewerDidAuthor: Stub<Boolean>
}

object GistCommentConnection : QType {
  abstract val edges: InitStub<GistCommentEdge>

  abstract val nodes: InitStub<GistComment>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object GistCommentEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<GistComment>
}

object GistConnection : QType {
  abstract val edges: InitStub<GistEdge>

  abstract val nodes: InitStub<Gist>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object GistEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Gist>
}

enum class GistPrivacy : QType {
  PUBLIC,

  SECRET,

  ALL
}

object GitActor : QType {
  abstract val avatarUrl: Config<String, AvatarUrlArgs>

  abstract val date: Stub<GitTimestamp>

  abstract val email: Stub<String>

  abstract val name: Stub<String>

  abstract val user: InitStub<User>
}

interface GitObject : QType {
  abstract val abbreviatedOid: Stub<String>

  abstract val commitResourcePath: Stub<URI>

  abstract val commitUrl: Stub<URI>

  abstract val id: Stub<String>

  abstract val oid: Stub<GitObjectID>

  abstract val repository: InitStub<Repository>
}

object GitObjectID : QType {
  abstract val model: Stub<String>
}

interface GitSignature : QType {
  abstract val email: Stub<String>

  abstract val isValid: Stub<Boolean>

  abstract val payload: Stub<String>

  abstract val signature: Stub<String>

  abstract val signer: InitStub<User>

  abstract val state: Stub<GitSignatureState>
}

enum class GitSignatureState : QType {
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

object GitTimestamp : QType {
  abstract val model: Stub<String>
}

object GpgSignature : QType, GitSignature {
  val email: Stub<String>

  val isValid: Stub<Boolean>

  abstract val keyId: Stub<String>

  val payload: Stub<String>

  val signature: Stub<String>

  val signer: InitStub<User>

  val state: Stub<GitSignatureState>
}

object HTML : QType {
  abstract val model: Stub<String>
}

object HeadRefDeletedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val headRef: InitStub<Ref>

  abstract val headRefName: Stub<String>

  val id: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>
}

object HeadRefForcePushedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val afterCommit: InitStub<Commit>

  abstract val beforeCommit: InitStub<Commit>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>

  abstract val ref: InitStub<Ref>
}

object HeadRefRestoredEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>
}

object Issue : QType, UniformResourceLocatable, Subscribable, RepositoryNode, Reactable, Lockable, Labelable, UpdatableComment, Updatable, Comment, Closable, Assignable, Node {
  val assignees: ConfigType<UserConnection, Base>

  val author: InitStub<Actor>

  val authorAssociation: Stub<CommentAuthorAssociation>

  val body: Stub<String>

  val bodyHTML: Stub<HTML>

  abstract val bodyText: Stub<String>

  val closed: Stub<Boolean>

  abstract val comments: ConfigType<IssueCommentConnection, CommentsArgs>

  val createdAt: Stub<DateTime>

  val createdViaEmail: Stub<Boolean>

  val databaseId: Stub<Int>

  val editor: InitStub<Actor>

  val id: Stub<String>

  val labels: ConfigType<LabelConnection, Base>

  val lastEditedAt: Stub<DateTime>

  val locked: Stub<Boolean>

  abstract val milestone: InitStub<Milestone>

  abstract val number: Stub<Int>

  abstract val participants: ConfigType<UserConnection, ParticipantsArgs>

  val publishedAt: Stub<DateTime>

  val reactionGroups: InitStub<ReactionGroup>

  val reactions: ConfigType<ReactionConnection, Base>

  val repository: InitStub<Repository>

  val resourcePath: Stub<URI>

  abstract val state: Stub<IssueState>

  abstract val timeline: ConfigType<IssueTimelineConnection, TimelineArgs>

  abstract val title: Stub<String>

  val updatedAt: Stub<DateTime>

  val url: Stub<URI>

  val viewerCanReact: Stub<Boolean>

  val viewerCanSubscribe: Stub<Boolean>

  val viewerCanUpdate: Stub<Boolean>

  val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason>

  val viewerDidAuthor: Stub<Boolean>

  val viewerSubscription: Stub<SubscriptionState>

  class AssigneesArgs(args: AssigneesArgs = TypeArgBuilder.create<UserConnection, AssigneesArgs>()) : AssigneesArgs by args {
    fun first(value: Int): AssigneesArgs = apply { addArg("first", value) }


    fun after(value: String): AssigneesArgs = apply { addArg("after", value) }


    fun last(value: Int): AssigneesArgs = apply { addArg("last", value) }


    fun before(value: String): AssigneesArgs = apply { addArg("before", value) }

  }

  class LabelsArgs(args: LabelsArgs = TypeArgBuilder.create<LabelConnection, LabelsArgs>()) : LabelsArgs by args {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }

  class ReactionsArgs(args: ReactionsArgs = TypeArgBuilder.create<ReactionConnection, ReactionsArgs>()) : ReactionsArgs by args {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

object IssueComment : QType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  val author: InitStub<Actor>

  val authorAssociation: Stub<CommentAuthorAssociation>

  val body: Stub<String>

  val bodyHTML: Stub<HTML>

  abstract val bodyText: Stub<String>

  val createdAt: Stub<DateTime>

  val createdViaEmail: Stub<Boolean>

  val databaseId: Stub<Int>

  val editor: InitStub<Actor>

  val id: Stub<String>

  abstract val issue: InitStub<Issue>

  val lastEditedAt: Stub<DateTime>

  val publishedAt: Stub<DateTime>

  val reactionGroups: InitStub<ReactionGroup>

  val reactions: ConfigType<ReactionConnection, Base>

  val repository: InitStub<Repository>

  val updatedAt: Stub<DateTime>

  val viewerCanDelete: Stub<Boolean>

  val viewerCanReact: Stub<Boolean>

  val viewerCanUpdate: Stub<Boolean>

  val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason>

  val viewerDidAuthor: Stub<Boolean>

  class ReactionsArgs(args: ReactionsArgs = TypeArgBuilder.create<ReactionConnection, ReactionsArgs>()) : ReactionsArgs by args {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

object IssueCommentConnection : QType {
  abstract val edges: InitStub<IssueCommentEdge>

  abstract val nodes: InitStub<IssueComment>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object IssueCommentEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<IssueComment>
}

object IssueConnection : QType {
  abstract val edges: InitStub<IssueEdge>

  abstract val nodes: InitStub<Issue>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object IssueEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Issue>
}

object IssueOrPullRequest : QType {
  abstract val Issue: InitStub<Issue>

  abstract val PullRequest: InitStub<PullRequest>
}

data class IssueOrder(private val field: IssueOrderField,
    private val direction: OrderDirection) : QInput

enum class IssueOrderField : QType {
  CREATED_AT,

  UPDATED_AT,

  COMMENTS
}

enum class IssuePubSubTopic : QType {
  UPDATED,

  MARKASREAD
}

enum class IssueState : QType {
  OPEN,

  CLOSED
}

object IssueTimelineConnection : QType {
  abstract val edges: InitStub<IssueTimelineItemEdge>

  abstract val nodes: InitStub<IssueTimelineItem>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object IssueTimelineItem : QType {
  abstract val Commit: InitStub<Commit>

  abstract val IssueComment: InitStub<IssueComment>

  abstract val ClosedEvent: InitStub<ClosedEvent>

  abstract val ReopenedEvent: InitStub<ReopenedEvent>

  abstract val SubscribedEvent: InitStub<SubscribedEvent>

  abstract val UnsubscribedEvent: InitStub<UnsubscribedEvent>

  abstract val ReferencedEvent: InitStub<ReferencedEvent>

  abstract val AssignedEvent: InitStub<AssignedEvent>

  abstract val UnassignedEvent: InitStub<UnassignedEvent>

  abstract val LabeledEvent: InitStub<LabeledEvent>

  abstract val UnlabeledEvent: InitStub<UnlabeledEvent>

  abstract val MilestonedEvent: InitStub<MilestonedEvent>

  abstract val DemilestonedEvent: InitStub<DemilestonedEvent>

  abstract val RenamedTitleEvent: InitStub<RenamedTitleEvent>

  abstract val LockedEvent: InitStub<LockedEvent>

  abstract val UnlockedEvent: InitStub<UnlockedEvent>
}

object IssueTimelineItemEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<IssueTimelineItem>
}

object Label : QType, Node {
  abstract val color: Stub<String>

  val id: Stub<String>

  abstract val issues: ConfigType<IssueConnection, IssuesArgs>

  abstract val name: Stub<String>

  abstract val pullRequests: ConfigType<PullRequestConnection, PullRequestsArgs>

  abstract val repository: InitStub<Repository>
}

object LabelConnection : QType {
  abstract val edges: InitStub<LabelEdge>

  abstract val nodes: InitStub<Label>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object LabelEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Label>
}

interface Labelable : QType {
  abstract val labels: ConfigType<LabelConnection, LabelsArgs>
}

object LabeledEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val label: InitStub<Label>

  abstract val labelable: InitStub<Labelable>
}

object Language : QType, Node {
  abstract val color: Stub<String>

  val id: Stub<String>

  abstract val name: Stub<String>
}

object LanguageConnection : QType {
  abstract val edges: InitStub<LanguageEdge>

  abstract val nodes: InitStub<Language>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>

  abstract val totalSize: Stub<Int>
}

object LanguageEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Language>

  abstract val size: Stub<Int>
}

data class LanguageOrder(private val field: LanguageOrderField,
    private val direction: OrderDirection) : QInput

enum class LanguageOrderField : QType {
  SIZE
}

interface Lockable : QType {
  abstract val locked: Stub<Boolean>
}

object LockedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val lockable: InitStub<Lockable>
}

object MentionedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>
}

enum class MergeableState : QType {
  MERGEABLE,

  CONFLICTING,

  UNKNOWN
}

object MergedEvent : QType, UniformResourceLocatable, Node {
  abstract val actor: InitStub<Actor>

  abstract val commit: InitStub<Commit>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val mergeRef: InitStub<Ref>

  abstract val mergeRefName: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>

  val resourcePath: Stub<URI>

  val url: Stub<URI>
}

object Milestone : QType, UniformResourceLocatable, Node {
  abstract val creator: InitStub<Actor>

  abstract val description: Stub<String>

  abstract val dueOn: Stub<DateTime>

  val id: Stub<String>

  abstract val number: Stub<Int>

  abstract val repository: InitStub<Repository>

  val resourcePath: Stub<URI>

  abstract val state: Stub<MilestoneState>

  abstract val title: Stub<String>

  val url: Stub<URI>
}

object MilestoneConnection : QType {
  abstract val edges: InitStub<MilestoneEdge>

  abstract val nodes: InitStub<Milestone>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object MilestoneEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Milestone>
}

object MilestoneItem : QType {
  abstract val Issue: InitStub<Issue>

  abstract val PullRequest: InitStub<PullRequest>
}

enum class MilestoneState : QType {
  OPEN,

  CLOSED
}

object MilestonedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val milestoneTitle: Stub<String>

  abstract val subject: InitStub<MilestoneItem>
}

data class MoveProjectCardInput(private val cardId: String, private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  private var afterCardId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun afterCardId(model: String) = apply { afterCardId = model }
}

object MoveProjectCardPayload : QType {
  abstract val cardEdge: InitStub<ProjectCardEdge>

  abstract val clientMutationId: Stub<String>
}

data class MoveProjectColumnInput(private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  private var afterColumnId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun afterColumnId(model: String) = apply { afterColumnId = model }
}

object MoveProjectColumnPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val columnEdge: InitStub<ProjectColumnEdge>
}

object MovedColumnsInProjectEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>
}

object Mutation : QType {
  abstract val acceptTopicSuggestion: ConfigType<AcceptTopicSuggestionPayload, AcceptTopicSuggestionArgs>

  abstract val addComment: ConfigType<AddCommentPayload, AddCommentArgs>

  abstract val addProjectCard: ConfigType<AddProjectCardPayload, AddProjectCardArgs>

  abstract val addProjectColumn: ConfigType<AddProjectColumnPayload, AddProjectColumnArgs>

  abstract val addPullRequestReview: ConfigType<AddPullRequestReviewPayload, AddPullRequestReviewArgs>

  abstract val addPullRequestReviewComment: ConfigType<AddPullRequestReviewCommentPayload, AddPullRequestReviewCommentArgs>

  abstract val addReaction: ConfigType<AddReactionPayload, AddReactionArgs>

  abstract val addStar: ConfigType<AddStarPayload, AddStarArgs>

  abstract val createProject: ConfigType<CreateProjectPayload, CreateProjectArgs>

  abstract val declineTopicSuggestion: ConfigType<DeclineTopicSuggestionPayload, DeclineTopicSuggestionArgs>

  abstract val deleteProject: ConfigType<DeleteProjectPayload, DeleteProjectArgs>

  abstract val deleteProjectCard: ConfigType<DeleteProjectCardPayload, DeleteProjectCardArgs>

  abstract val deleteProjectColumn: ConfigType<DeleteProjectColumnPayload, DeleteProjectColumnArgs>

  abstract val deletePullRequestReview: ConfigType<DeletePullRequestReviewPayload, DeletePullRequestReviewArgs>

  abstract val dismissPullRequestReview: ConfigType<DismissPullRequestReviewPayload, DismissPullRequestReviewArgs>

  abstract val moveProjectCard: ConfigType<MoveProjectCardPayload, MoveProjectCardArgs>

  abstract val moveProjectColumn: ConfigType<MoveProjectColumnPayload, MoveProjectColumnArgs>

  abstract val removeOutsideCollaborator: ConfigType<RemoveOutsideCollaboratorPayload, RemoveOutsideCollaboratorArgs>

  abstract val removeReaction: ConfigType<RemoveReactionPayload, RemoveReactionArgs>

  abstract val removeStar: ConfigType<RemoveStarPayload, RemoveStarArgs>

  abstract val requestReviews: ConfigType<RequestReviewsPayload, RequestReviewsArgs>

  abstract val submitPullRequestReview: ConfigType<SubmitPullRequestReviewPayload, SubmitPullRequestReviewArgs>

  abstract val updateProject: ConfigType<UpdateProjectPayload, UpdateProjectArgs>

  abstract val updateProjectCard: ConfigType<UpdateProjectCardPayload, UpdateProjectCardArgs>

  abstract val updateProjectColumn: ConfigType<UpdateProjectColumnPayload, UpdateProjectColumnArgs>

  abstract val updatePullRequestReview: ConfigType<UpdatePullRequestReviewPayload, UpdatePullRequestReviewArgs>

  abstract val updatePullRequestReviewComment: ConfigType<UpdatePullRequestReviewCommentPayload, UpdatePullRequestReviewCommentArgs>

  abstract val updateSubscription: ConfigType<UpdateSubscriptionPayload, UpdateSubscriptionArgs>

  abstract val updateTopics: ConfigType<UpdateTopicsPayload, UpdateTopicsArgs>
}

interface Node : QType {
  abstract val id: Stub<String>
}

enum class OrderDirection : QType {
  ASC,

  DESC
}

object Organization : QType, UniformResourceLocatable, RepositoryOwner, ProjectOwner, Actor, Node {
  val avatarUrl: Config<String, AvatarUrlArgs>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>

  abstract val isInvoiced: Stub<Boolean>

  val login: Stub<String>

  abstract val members: ConfigType<UserConnection, MembersArgs>

  abstract val name: Stub<String>

  abstract val newTeamResourcePath: Stub<URI>

  abstract val newTeamUrl: Stub<URI>

  abstract val organizationBillingEmail: Stub<String>

  val pinnedRepositories: ConfigType<RepositoryConnection, Base>

  val project: ConfigType<Project, Base>

  val projects: ConfigType<ProjectConnection, Base>

  val projectsResourcePath: Stub<URI>

  val projectsUrl: Stub<URI>

  val repositories: ConfigType<RepositoryConnection, Base>

  val repository: ConfigType<Repository, Base>

  val resourcePath: Stub<URI>

  abstract val samlIdentityProvider: InitStub<OrganizationIdentityProvider>

  abstract val team: ConfigType<Team, TeamArgs>

  abstract val teams: ConfigType<TeamConnection, TeamsArgs>

  abstract val teamsResourcePath: Stub<URI>

  abstract val teamsUrl: Stub<URI>

  val url: Stub<URI>

  abstract val viewerCanAdminister: Stub<Boolean>

  val viewerCanCreateProjects: Stub<Boolean>

  abstract val viewerCanCreateRepositories: Stub<Boolean>

  abstract val viewerCanCreateTeams: Stub<Boolean>

  abstract val viewerIsAMember: Stub<Boolean>

  class AvatarUrlArgs(args: AvatarUrlArgs = ArgBuilder.create<String, AvatarUrlArgs>()) : AvatarUrlArgs(args) {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }

  class PinnedRepositoriesArgs(args: PinnedRepositoriesArgs = TypeArgBuilder.create<RepositoryConnection, PinnedRepositoriesArgs>()) : PinnedRepositoriesArgs by args {
    fun first(value: Int): PinnedRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): PinnedRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): PinnedRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): PinnedRepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs = apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): PinnedRepositoriesArgs = apply { addArg("isLocked", value) }

  }

  class ProjectArgs(args: ProjectArgs = TypeArgBuilder.create<Project, ProjectArgs>()) : ProjectArgs by args {
    fun number(value: Int): ProjectArgs = apply { addArg("number", value) }

  }

  class ProjectsArgs(args: ProjectsArgs = TypeArgBuilder.create<ProjectConnection, ProjectsArgs>()) : ProjectsArgs by args {
    fun first(value: Int): ProjectsArgs = apply { addArg("first", value) }


    fun after(value: String): ProjectsArgs = apply { addArg("after", value) }


    fun last(value: Int): ProjectsArgs = apply { addArg("last", value) }


    fun before(value: String): ProjectsArgs = apply { addArg("before", value) }


    fun orderBy(value: ProjectOrder): ProjectsArgs = apply { addArg("orderBy", value) }


    fun search(value: String): ProjectsArgs = apply { addArg("search", value) }


    fun states(value: ProjectState): ProjectsArgs = apply { addArg("states", value) }

  }

  class RepositoriesArgs(args: RepositoriesArgs = TypeArgBuilder.create<RepositoryConnection, RepositoriesArgs>()) : RepositoriesArgs by args {
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

  class RepositoryArgs(args: RepositoryArgs = TypeArgBuilder.create<Repository, RepositoryArgs>()) : RepositoryArgs by args {
    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }
}

object OrganizationConnection : QType {
  abstract val edges: InitStub<OrganizationEdge>

  abstract val nodes: InitStub<Organization>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object OrganizationEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Organization>
}

object OrganizationIdentityProvider : QType, Node {
  abstract val digestMethod: Stub<URI>

  abstract val externalIdentities: ConfigType<ExternalIdentityConnection, ExternalIdentitiesArgs>

  val id: Stub<String>

  abstract val idpCertificate: Stub<X509Certificate>

  abstract val issuer: Stub<String>

  abstract val organization: InitStub<Organization>

  abstract val signatureMethod: Stub<URI>

  abstract val ssoUrl: Stub<URI>
}

object OrganizationInvitation : QType {
  abstract val email: Stub<String>

  abstract val id: Stub<String>

  abstract val invitee: InitStub<User>

  abstract val inviter: InitStub<User>

  abstract val role: Stub<OrganizationInvitationRole>
}

object OrganizationInvitationConnection : QType {
  abstract val edges: InitStub<OrganizationInvitationEdge>

  abstract val nodes: InitStub<OrganizationInvitation>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object OrganizationInvitationEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<OrganizationInvitation>
}

enum class OrganizationInvitationRole : QType {
  DIRECT_MEMBER,

  ADMIN,

  BILLING_MANAGER,

  REINSTATE
}

object PageInfo : QType {
  abstract val endCursor: Stub<String>

  abstract val hasNextPage: Stub<Boolean>

  abstract val hasPreviousPage: Stub<Boolean>

  abstract val startCursor: Stub<String>
}

object Project : QType, Updatable, Node {
  abstract val body: Stub<String>

  abstract val bodyHTML: Stub<HTML>

  abstract val closedAt: Stub<DateTime>

  abstract val columns: ConfigType<ProjectColumnConnection, ColumnsArgs>

  abstract val createdAt: Stub<DateTime>

  abstract val creator: InitStub<Actor>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>

  abstract val name: Stub<String>

  abstract val number: Stub<Int>

  abstract val owner: InitStub<ProjectOwner>

  abstract val resourcePath: Stub<URI>

  abstract val state: Stub<ProjectState>

  abstract val updatedAt: Stub<DateTime>

  abstract val url: Stub<URI>

  val viewerCanUpdate: Stub<Boolean>
}

object ProjectCard : QType, Node {
  abstract val column: InitStub<ProjectColumn>

  abstract val content: InitStub<ProjectCardItem>

  abstract val createdAt: Stub<DateTime>

  abstract val creator: InitStub<Actor>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>

  abstract val note: Stub<String>

  abstract val project: InitStub<Project>

  abstract val projectColumn: InitStub<ProjectColumn>

  abstract val resourcePath: Stub<URI>

  abstract val state: Stub<ProjectCardState>

  abstract val updatedAt: Stub<DateTime>

  abstract val url: Stub<URI>
}

object ProjectCardConnection : QType {
  abstract val edges: InitStub<ProjectCardEdge>

  abstract val nodes: InitStub<ProjectCard>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object ProjectCardEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<ProjectCard>
}

object ProjectCardItem : QType {
  abstract val Issue: InitStub<Issue>

  abstract val PullRequest: InitStub<PullRequest>
}

enum class ProjectCardState : QType {
  CONTENT_ONLY,

  NOTE_ONLY,

  REDACTED
}

object ProjectColumn : QType, Node {
  abstract val cards: ConfigType<ProjectCardConnection, CardsArgs>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>

  abstract val name: Stub<String>

  abstract val project: InitStub<Project>

  abstract val updatedAt: Stub<DateTime>
}

object ProjectColumnConnection : QType {
  abstract val edges: InitStub<ProjectColumnEdge>

  abstract val nodes: InitStub<ProjectColumn>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object ProjectColumnEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<ProjectColumn>
}

object ProjectConnection : QType {
  abstract val edges: InitStub<ProjectEdge>

  abstract val nodes: InitStub<Project>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object ProjectEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Project>
}

data class ProjectOrder(private val field: ProjectOrderField,
    private val direction: OrderDirection) : QInput

enum class ProjectOrderField : QType {
  CREATED_AT,

  UPDATED_AT,

  NAME
}

interface ProjectOwner : QType {
  abstract val id: Stub<String>

  abstract val project: ConfigType<Project, ProjectArgs>

  abstract val projects: ConfigType<ProjectConnection, ProjectsArgs>

  abstract val projectsResourcePath: Stub<URI>

  abstract val projectsUrl: Stub<URI>

  abstract val viewerCanCreateProjects: Stub<Boolean>
}

enum class ProjectState : QType {
  OPEN,

  CLOSED
}

object ProtectedBranch : QType, Node {
  abstract val creator: InitStub<Actor>

  abstract val hasDismissableStaleReviews: Stub<Boolean>

  abstract val hasRequiredReviews: Stub<Boolean>

  abstract val hasRequiredStatusChecks: Stub<Boolean>

  abstract val hasRestrictedPushes: Stub<Boolean>

  abstract val hasRestrictedReviewDismissals: Stub<Boolean>

  abstract val hasStrictRequiredStatusChecks: Stub<Boolean>

  val id: Stub<String>

  abstract val isAdminEnforced: Stub<Boolean>

  abstract val name: Stub<String>

  abstract val pushAllowances: ConfigType<PushAllowanceConnection, PushAllowancesArgs>

  abstract val repository: InitStub<Repository>

  abstract val requiredStatusCheckContexts: Stub<String>

  abstract val reviewDismissalAllowances: ConfigType<ReviewDismissalAllowanceConnection, ReviewDismissalAllowancesArgs>
}

object ProtectedBranchConnection : QType {
  abstract val edges: InitStub<ProtectedBranchEdge>

  abstract val nodes: InitStub<ProtectedBranch>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object ProtectedBranchEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<ProtectedBranch>
}

object PullRequest : QType, UniformResourceLocatable, Subscribable, RepositoryNode, Reactable, Lockable, Labelable, UpdatableComment, Updatable, Comment, Closable, Assignable, Node {
  val assignees: ConfigType<UserConnection, Base>

  val author: InitStub<Actor>

  val authorAssociation: Stub<CommentAuthorAssociation>

  abstract val baseRef: InitStub<Ref>

  abstract val baseRefName: Stub<String>

  val body: Stub<String>

  val bodyHTML: Stub<HTML>

  abstract val bodyText: Stub<String>

  val closed: Stub<Boolean>

  abstract val comments: ConfigType<IssueCommentConnection, CommentsArgs>

  abstract val commits: ConfigType<PullRequestCommitConnection, CommitsArgs>

  val createdAt: Stub<DateTime>

  val createdViaEmail: Stub<Boolean>

  val databaseId: Stub<Int>

  val editor: InitStub<Actor>

  abstract val headRef: InitStub<Ref>

  abstract val headRefName: Stub<String>

  abstract val headRepository: InitStub<Repository>

  abstract val headRepositoryOwner: InitStub<RepositoryOwner>

  val id: Stub<String>

  abstract val isCrossRepository: Stub<Boolean>

  val labels: ConfigType<LabelConnection, Base>

  val lastEditedAt: Stub<DateTime>

  val locked: Stub<Boolean>

  abstract val mergeCommit: InitStub<Commit>

  abstract val mergeable: Stub<MergeableState>

  abstract val merged: Stub<Boolean>

  abstract val mergedAt: Stub<DateTime>

  abstract val number: Stub<Int>

  abstract val participants: ConfigType<UserConnection, ParticipantsArgs>

  abstract val potentialMergeCommit: InitStub<Commit>

  val publishedAt: Stub<DateTime>

  val reactionGroups: InitStub<ReactionGroup>

  val reactions: ConfigType<ReactionConnection, Base>

  val repository: InitStub<Repository>

  val resourcePath: Stub<URI>

  abstract val revertResourcePath: Stub<URI>

  abstract val revertUrl: Stub<URI>

  abstract val reviewRequests: ConfigType<ReviewRequestConnection, ReviewRequestsArgs>

  abstract val reviews: ConfigType<PullRequestReviewConnection, ReviewsArgs>

  abstract val state: Stub<PullRequestState>

  abstract val suggestedReviewers: InitStub<SuggestedReviewer>

  abstract val timeline: ConfigType<PullRequestTimelineConnection, TimelineArgs>

  abstract val title: Stub<String>

  val updatedAt: Stub<DateTime>

  val url: Stub<URI>

  val viewerCanReact: Stub<Boolean>

  val viewerCanSubscribe: Stub<Boolean>

  val viewerCanUpdate: Stub<Boolean>

  val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason>

  val viewerDidAuthor: Stub<Boolean>

  val viewerSubscription: Stub<SubscriptionState>

  class AssigneesArgs(args: AssigneesArgs = TypeArgBuilder.create<UserConnection, AssigneesArgs>()) : AssigneesArgs by args {
    fun first(value: Int): AssigneesArgs = apply { addArg("first", value) }


    fun after(value: String): AssigneesArgs = apply { addArg("after", value) }


    fun last(value: Int): AssigneesArgs = apply { addArg("last", value) }


    fun before(value: String): AssigneesArgs = apply { addArg("before", value) }

  }

  class LabelsArgs(args: LabelsArgs = TypeArgBuilder.create<LabelConnection, LabelsArgs>()) : LabelsArgs by args {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }

  class ReactionsArgs(args: ReactionsArgs = TypeArgBuilder.create<ReactionConnection, ReactionsArgs>()) : ReactionsArgs by args {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

object PullRequestCommit : QType, UniformResourceLocatable, Node {
  abstract val commit: InitStub<Commit>

  val id: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>

  val resourcePath: Stub<URI>

  val url: Stub<URI>
}

object PullRequestCommitConnection : QType {
  abstract val edges: InitStub<PullRequestCommitEdge>

  abstract val nodes: InitStub<PullRequestCommit>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object PullRequestCommitEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<PullRequestCommit>
}

object PullRequestConnection : QType {
  abstract val edges: InitStub<PullRequestEdge>

  abstract val nodes: InitStub<PullRequest>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object PullRequestEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<PullRequest>
}

enum class PullRequestPubSubTopic : QType {
  UPDATED,

  MARKASREAD,

  HEAD_REF
}

object PullRequestReview : QType, RepositoryNode, UpdatableComment, Updatable, Deletable, Comment, Node {
  val author: InitStub<Actor>

  val authorAssociation: Stub<CommentAuthorAssociation>

  val body: Stub<String>

  val bodyHTML: Stub<HTML>

  abstract val bodyText: Stub<String>

  abstract val comments: ConfigType<PullRequestReviewCommentConnection, CommentsArgs>

  abstract val commit: InitStub<Commit>

  val createdAt: Stub<DateTime>

  val createdViaEmail: Stub<Boolean>

  abstract val databaseId: Stub<Int>

  val editor: InitStub<Actor>

  val id: Stub<String>

  val lastEditedAt: Stub<DateTime>

  val publishedAt: Stub<DateTime>

  abstract val pullRequest: InitStub<PullRequest>

  val repository: InitStub<Repository>

  abstract val resourcePath: Stub<URI>

  abstract val state: Stub<PullRequestReviewState>

  abstract val submittedAt: Stub<DateTime>

  val updatedAt: Stub<DateTime>

  abstract val url: Stub<URI>

  val viewerCanDelete: Stub<Boolean>

  val viewerCanUpdate: Stub<Boolean>

  val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason>

  val viewerDidAuthor: Stub<Boolean>
}

object PullRequestReviewComment : QType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  val author: InitStub<Actor>

  val authorAssociation: Stub<CommentAuthorAssociation>

  val body: Stub<String>

  val bodyHTML: Stub<HTML>

  abstract val bodyText: Stub<String>

  abstract val commit: InitStub<Commit>

  val createdAt: Stub<DateTime>

  val createdViaEmail: Stub<Boolean>

  val databaseId: Stub<Int>

  abstract val diffHunk: Stub<String>

  abstract val draftedAt: Stub<DateTime>

  val editor: InitStub<Actor>

  val id: Stub<String>

  val lastEditedAt: Stub<DateTime>

  abstract val originalCommit: InitStub<Commit>

  abstract val originalPosition: Stub<Int>

  abstract val path: Stub<String>

  abstract val position: Stub<Int>

  val publishedAt: Stub<DateTime>

  abstract val pullRequest: InitStub<PullRequest>

  abstract val pullRequestReview: InitStub<PullRequestReview>

  val reactionGroups: InitStub<ReactionGroup>

  val reactions: ConfigType<ReactionConnection, Base>

  val repository: InitStub<Repository>

  abstract val resourcePath: Stub<URI>

  val updatedAt: Stub<DateTime>

  abstract val url: Stub<URI>

  val viewerCanDelete: Stub<Boolean>

  val viewerCanReact: Stub<Boolean>

  val viewerCanUpdate: Stub<Boolean>

  val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason>

  val viewerDidAuthor: Stub<Boolean>

  class ReactionsArgs(args: ReactionsArgs = TypeArgBuilder.create<ReactionConnection, ReactionsArgs>()) : ReactionsArgs by args {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

object PullRequestReviewCommentConnection : QType {
  abstract val edges: InitStub<PullRequestReviewCommentEdge>

  abstract val nodes: InitStub<PullRequestReviewComment>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object PullRequestReviewCommentEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<PullRequestReviewComment>
}

object PullRequestReviewConnection : QType {
  abstract val edges: InitStub<PullRequestReviewEdge>

  abstract val nodes: InitStub<PullRequestReview>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object PullRequestReviewEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<PullRequestReview>
}

enum class PullRequestReviewEvent : QType {
  COMMENT,

  APPROVE,

  REQUEST_CHANGES,

  DISMISS
}

enum class PullRequestReviewState : QType {
  PENDING,

  COMMENTED,

  APPROVED,

  CHANGES_REQUESTED,

  DISMISSED
}

object PullRequestReviewThread : QType, Node {
  abstract val comments: ConfigType<PullRequestReviewCommentConnection, CommentsArgs>

  val id: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>
}

enum class PullRequestState : QType {
  OPEN,

  CLOSED,

  MERGED
}

object PullRequestTimelineConnection : QType {
  abstract val edges: InitStub<PullRequestTimelineItemEdge>

  abstract val nodes: InitStub<PullRequestTimelineItem>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object PullRequestTimelineItem : QType {
  abstract val Commit: InitStub<Commit>

  abstract val CommitCommentThread: InitStub<CommitCommentThread>

  abstract val PullRequestReview: InitStub<PullRequestReview>

  abstract val PullRequestReviewThread: InitStub<PullRequestReviewThread>

  abstract val PullRequestReviewComment: InitStub<PullRequestReviewComment>

  abstract val IssueComment: InitStub<IssueComment>

  abstract val ClosedEvent: InitStub<ClosedEvent>

  abstract val ReopenedEvent: InitStub<ReopenedEvent>

  abstract val SubscribedEvent: InitStub<SubscribedEvent>

  abstract val UnsubscribedEvent: InitStub<UnsubscribedEvent>

  abstract val MergedEvent: InitStub<MergedEvent>

  abstract val ReferencedEvent: InitStub<ReferencedEvent>

  abstract val AssignedEvent: InitStub<AssignedEvent>

  abstract val UnassignedEvent: InitStub<UnassignedEvent>

  abstract val LabeledEvent: InitStub<LabeledEvent>

  abstract val UnlabeledEvent: InitStub<UnlabeledEvent>

  abstract val MilestonedEvent: InitStub<MilestonedEvent>

  abstract val DemilestonedEvent: InitStub<DemilestonedEvent>

  abstract val RenamedTitleEvent: InitStub<RenamedTitleEvent>

  abstract val LockedEvent: InitStub<LockedEvent>

  abstract val UnlockedEvent: InitStub<UnlockedEvent>

  abstract val DeployedEvent: InitStub<DeployedEvent>

  abstract val HeadRefDeletedEvent: InitStub<HeadRefDeletedEvent>

  abstract val HeadRefRestoredEvent: InitStub<HeadRefRestoredEvent>

  abstract val HeadRefForcePushedEvent: InitStub<HeadRefForcePushedEvent>

  abstract val BaseRefForcePushedEvent: InitStub<BaseRefForcePushedEvent>

  abstract val ReviewRequestedEvent: InitStub<ReviewRequestedEvent>

  abstract val ReviewRequestRemovedEvent: InitStub<ReviewRequestRemovedEvent>

  abstract val ReviewDismissedEvent: InitStub<ReviewDismissedEvent>
}

object PullRequestTimelineItemEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<PullRequestTimelineItem>
}

object PushAllowance : QType, Node {
  abstract val actor: InitStub<PushAllowanceActor>

  val id: Stub<String>

  abstract val protectedBranch: InitStub<ProtectedBranch>
}

object PushAllowanceActor : QType {
  abstract val User: InitStub<User>

  abstract val Team: InitStub<Team>
}

object PushAllowanceConnection : QType {
  abstract val edges: InitStub<PushAllowanceEdge>

  abstract val nodes: InitStub<PushAllowance>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object PushAllowanceEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<PushAllowance>
}

object Query : QType {
  abstract val codeOfConduct: ConfigType<CodeOfConduct, CodeOfConductArgs>

  abstract val codesOfConduct: InitStub<CodeOfConduct>

  abstract val node: ConfigType<Node, NodeArgs>

  abstract val nodes: ConfigType<Node, NodesArgs>

  abstract val organization: ConfigType<Organization, OrganizationArgs>

  abstract val rateLimit: InitStub<RateLimit>

  abstract val relay: InitStub<Query>

  abstract val repository: ConfigType<Repository, RepositoryArgs>

  abstract val repositoryOwner: ConfigType<RepositoryOwner, RepositoryOwnerArgs>

  abstract val resource: ConfigType<UniformResourceLocatable, ResourceArgs>

  abstract val search: ConfigType<SearchResultItemConnection, SearchArgs>

  abstract val topic: ConfigType<Topic, TopicArgs>

  abstract val user: ConfigType<User, UserArgs>

  abstract val viewer: InitStub<User>
}

object RateLimit : QType {
  abstract val cost: Stub<Int>

  abstract val limit: Stub<Int>

  abstract val remaining: Stub<Int>

  abstract val resetAt: Stub<DateTime>
}

interface Reactable : QType {
  abstract val databaseId: Stub<Int>

  abstract val id: Stub<String>

  abstract val reactionGroups: InitStub<ReactionGroup>

  abstract val reactions: ConfigType<ReactionConnection, ReactionsArgs>

  abstract val viewerCanReact: Stub<Boolean>
}

object ReactingUserConnection : QType {
  abstract val edges: InitStub<ReactingUserEdge>

  abstract val nodes: InitStub<User>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object ReactingUserEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<User>

  abstract val reactedAt: Stub<DateTime>
}

object Reaction : QType, Node {
  abstract val content: Stub<ReactionContent>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>

  abstract val user: InitStub<User>
}

object ReactionConnection : QType {
  abstract val edges: InitStub<ReactionEdge>

  abstract val nodes: InitStub<Reaction>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>

  abstract val viewerHasReacted: Stub<Boolean>
}

enum class ReactionContent : QType {
  THUMBS_UP,

  THUMBS_DOWN,

  LAUGH,

  HOORAY,

  CONFUSED,

  HEART
}

object ReactionEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Reaction>
}

object ReactionGroup : QType {
  abstract val content: Stub<ReactionContent>

  abstract val createdAt: Stub<DateTime>

  abstract val subject: InitStub<Reactable>

  abstract val users: ConfigType<ReactingUserConnection, UsersArgs>

  abstract val viewerHasReacted: Stub<Boolean>
}

data class ReactionOrder(private val field: ReactionOrderField,
    private val direction: OrderDirection) : QInput

enum class ReactionOrderField : QType {
  CREATED_AT
}

object Ref : QType, Node {
  abstract val associatedPullRequests: ConfigType<PullRequestConnection, AssociatedPullRequestsArgs>

  val id: Stub<String>

  abstract val name: Stub<String>

  abstract val prefix: Stub<String>

  abstract val repository: InitStub<Repository>

  abstract val target: InitStub<GitObject>
}

object RefConnection : QType {
  abstract val edges: InitStub<RefEdge>

  abstract val nodes: InitStub<Ref>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object RefEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Ref>
}

object ReferencedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val commit: InitStub<Commit>

  abstract val commitRepository: InitStub<Repository>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val isCrossReference: Stub<Boolean>

  abstract val isCrossRepository: Stub<Boolean>

  abstract val isDirectReference: Stub<Boolean>

  abstract val subject: InitStub<ReferencedSubject>
}

object ReferencedSubject : QType {
  abstract val Issue: InitStub<Issue>

  abstract val PullRequest: InitStub<PullRequest>
}

object Release : QType, UniformResourceLocatable, Node {
  abstract val description: Stub<String>

  val id: Stub<String>

  abstract val name: Stub<String>

  abstract val publishedAt: Stub<DateTime>

  abstract val releaseAsset: ConfigType<ReleaseAssetConnection, ReleaseAssetArgs>

  abstract val releaseAssets: ConfigType<ReleaseAssetConnection, ReleaseAssetsArgs>

  val resourcePath: Stub<URI>

  abstract val tag: InitStub<Ref>

  val url: Stub<URI>
}

object ReleaseAsset : QType, Node {
  val id: Stub<String>

  abstract val name: Stub<String>

  abstract val release: InitStub<Release>

  abstract val url: Stub<URI>
}

object ReleaseAssetConnection : QType {
  abstract val edges: InitStub<ReleaseAssetEdge>

  abstract val nodes: InitStub<ReleaseAsset>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object ReleaseAssetEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<ReleaseAsset>
}

object ReleaseConnection : QType {
  abstract val edges: InitStub<ReleaseEdge>

  abstract val nodes: InitStub<Release>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object ReleaseEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Release>
}

data class RemoveOutsideCollaboratorInput(private val userId: String,
    private val organizationId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object RemoveOutsideCollaboratorPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val removedUser: InitStub<User>
}

data class RemoveReactionInput(private val subjectId: String,
    private val content: ReactionContent) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object RemoveReactionPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val reaction: InitStub<Reaction>

  abstract val subject: InitStub<Reactable>
}

data class RemoveStarInput(private val starrableId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object RemoveStarPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val starrable: InitStub<Starrable>
}

object RemovedFromProjectEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>
}

object RenamedTitleEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val currentTitle: Stub<String>

  val id: Stub<String>

  abstract val previousTitle: Stub<String>

  abstract val subject: InitStub<RenamedTitleSubject>
}

object RenamedTitleSubject : QType {
  abstract val Issue: InitStub<Issue>

  abstract val PullRequest: InitStub<PullRequest>
}

object ReopenedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val closable: InitStub<Closable>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>
}

object Repository : QType, RepositoryInfo, UniformResourceLocatable, Starrable, Subscribable, ProjectOwner, Node {
  abstract val codeOfConduct: InitStub<CodeOfConduct>

  abstract val commitComments: ConfigType<CommitCommentConnection, CommitCommentsArgs>

  val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  abstract val defaultBranchRef: InitStub<Ref>

  abstract val deployments: ConfigType<DeploymentConnection, DeploymentsArgs>

  val description: Stub<String>

  val descriptionHTML: Stub<HTML>

  abstract val diskUsage: Stub<Int>

  abstract val forks: ConfigType<RepositoryConnection, ForksArgs>

  val hasIssuesEnabled: Stub<Boolean>

  val hasWikiEnabled: Stub<Boolean>

  val homepageUrl: Stub<URI>

  val id: Stub<String>

  val isFork: Stub<Boolean>

  val isLocked: Stub<Boolean>

  val isMirror: Stub<Boolean>

  val isPrivate: Stub<Boolean>

  abstract val issue: ConfigType<Issue, IssueArgs>

  abstract val issueOrPullRequest: ConfigType<IssueOrPullRequest, IssueOrPullRequestArgs>

  abstract val issues: ConfigType<IssueConnection, IssuesArgs>

  abstract val label: ConfigType<Label, LabelArgs>

  abstract val labels: ConfigType<LabelConnection, LabelsArgs>

  abstract val languages: ConfigType<LanguageConnection, LanguagesArgs>

  val license: Stub<String>

  val lockReason: Stub<RepositoryLockReason>

  abstract val mentionableUsers: ConfigType<UserConnection, MentionableUsersArgs>

  abstract val milestone: ConfigType<Milestone, MilestoneArgs>

  abstract val milestones: ConfigType<MilestoneConnection, MilestonesArgs>

  val mirrorUrl: Stub<URI>

  val name: Stub<String>

  val nameWithOwner: Stub<String>

  abstract val objectVal: ConfigType<GitObject, ObjectValArgs>

  val owner: InitStub<RepositoryOwner>

  abstract val parent: InitStub<Repository>

  abstract val primaryLanguage: InitStub<Language>

  val project: ConfigType<Project, Base>

  val projects: ConfigType<ProjectConnection, Base>

  val projectsResourcePath: Stub<URI>

  val projectsUrl: Stub<URI>

  abstract val protectedBranches: ConfigType<ProtectedBranchConnection, ProtectedBranchesArgs>

  abstract val pullRequest: ConfigType<PullRequest, PullRequestArgs>

  abstract val pullRequests: ConfigType<PullRequestConnection, PullRequestsArgs>

  val pushedAt: Stub<DateTime>

  abstract val ref: ConfigType<Ref, RefArgs>

  abstract val refs: ConfigType<RefConnection, RefsArgs>

  abstract val releases: ConfigType<ReleaseConnection, ReleasesArgs>

  abstract val repositoryTopics: ConfigType<RepositoryTopicConnection, RepositoryTopicsArgs>

  val resourcePath: Stub<URI>

  val stargazers: ConfigType<StargazerConnection, Base>

  val updatedAt: Stub<DateTime>

  val url: Stub<URI>

  abstract val viewerCanAdminister: Stub<Boolean>

  val viewerCanCreateProjects: Stub<Boolean>

  val viewerCanSubscribe: Stub<Boolean>

  abstract val viewerCanUpdateTopics: Stub<Boolean>

  val viewerHasStarred: Stub<Boolean>

  val viewerSubscription: Stub<SubscriptionState>

  abstract val watchers: ConfigType<UserConnection, WatchersArgs>

  class ProjectArgs(args: ProjectArgs = TypeArgBuilder.create<Project, ProjectArgs>()) : ProjectArgs by args {
    fun number(value: Int): ProjectArgs = apply { addArg("number", value) }

  }

  class ProjectsArgs(args: ProjectsArgs = TypeArgBuilder.create<ProjectConnection, ProjectsArgs>()) : ProjectsArgs by args {
    fun first(value: Int): ProjectsArgs = apply { addArg("first", value) }


    fun after(value: String): ProjectsArgs = apply { addArg("after", value) }


    fun last(value: Int): ProjectsArgs = apply { addArg("last", value) }


    fun before(value: String): ProjectsArgs = apply { addArg("before", value) }


    fun orderBy(value: ProjectOrder): ProjectsArgs = apply { addArg("orderBy", value) }


    fun search(value: String): ProjectsArgs = apply { addArg("search", value) }


    fun states(value: ProjectState): ProjectsArgs = apply { addArg("states", value) }

  }

  class StargazersArgs(args: StargazersArgs = TypeArgBuilder.create<StargazerConnection, StargazersArgs>()) : StargazersArgs by args {
    fun first(value: Int): StargazersArgs = apply { addArg("first", value) }


    fun after(value: String): StargazersArgs = apply { addArg("after", value) }


    fun last(value: Int): StargazersArgs = apply { addArg("last", value) }


    fun before(value: String): StargazersArgs = apply { addArg("before", value) }


    fun orderBy(value: StarOrder): StargazersArgs = apply { addArg("orderBy", value) }

  }
}

enum class RepositoryAffiliation : QType {
  OWNER,

  COLLABORATOR,

  ORGANIZATION_MEMBER
}

enum class RepositoryCollaboratorAffiliation : QType {
  ALL,

  OUTSIDE
}

object RepositoryConnection : QType {
  abstract val edges: InitStub<RepositoryEdge>

  abstract val nodes: InitStub<Repository>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>

  abstract val totalDiskUsage: Stub<Int>
}

object RepositoryEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Repository>
}

interface RepositoryInfo : QType {
  abstract val createdAt: Stub<DateTime>

  abstract val description: Stub<String>

  abstract val descriptionHTML: Stub<HTML>

  abstract val hasIssuesEnabled: Stub<Boolean>

  abstract val hasWikiEnabled: Stub<Boolean>

  abstract val homepageUrl: Stub<URI>

  abstract val isFork: Stub<Boolean>

  abstract val isLocked: Stub<Boolean>

  abstract val isMirror: Stub<Boolean>

  abstract val isPrivate: Stub<Boolean>

  abstract val license: Stub<String>

  abstract val lockReason: Stub<RepositoryLockReason>

  abstract val mirrorUrl: Stub<URI>

  abstract val name: Stub<String>

  abstract val nameWithOwner: Stub<String>

  abstract val owner: InitStub<RepositoryOwner>

  abstract val pushedAt: Stub<DateTime>

  abstract val resourcePath: Stub<URI>

  abstract val updatedAt: Stub<DateTime>

  abstract val url: Stub<URI>
}

object RepositoryInvitation : QType, Node {
  val id: Stub<String>

  abstract val invitee: InitStub<User>

  abstract val inviter: InitStub<User>

  abstract val repository: InitStub<RepositoryInvitationRepository>
}

object RepositoryInvitationRepository : QType, RepositoryInfo {
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

enum class RepositoryLockReason : QType {
  MOVING,

  BILLING,

  RENAME,

  MIGRATING
}

interface RepositoryNode : QType {
  abstract val repository: InitStub<Repository>
}

data class RepositoryOrder(private val field: RepositoryOrderField,
    private val direction: OrderDirection) : QInput

enum class RepositoryOrderField : QType {
  CREATED_AT,

  UPDATED_AT,

  PUSHED_AT,

  NAME,

  STARGAZERS
}

interface RepositoryOwner : QType {
  abstract val avatarUrl: Config<String, BaseAvatarUrlArgs>

  abstract val id: Stub<String>

  abstract val login: Stub<String>

  abstract val pinnedRepositories: ConfigType<RepositoryConnection, PinnedRepositoriesArgs>

  abstract val repositories: ConfigType<RepositoryConnection, RepositoriesArgs>

  abstract val repository: ConfigType<Repository, RepositoryArgs>

  abstract val resourcePath: Stub<URI>

  abstract val url: Stub<URI>
}

enum class RepositoryPrivacy : QType {
  PUBLIC,

  PRIVATE
}

object RepositoryTopic : QType, UniformResourceLocatable, Node {
  val id: Stub<String>

  val resourcePath: Stub<URI>

  abstract val topic: InitStub<Topic>

  val url: Stub<URI>
}

object RepositoryTopicConnection : QType {
  abstract val edges: InitStub<RepositoryTopicEdge>

  abstract val nodes: InitStub<RepositoryTopic>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object RepositoryTopicEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<RepositoryTopic>
}

data class RequestReviewsInput(private val pullRequestId: String, private val userIds: String,
    private val teamIds: String) : QInput {
  private var clientMutationId: String? = null
  private var union: Boolean? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun union(model: Boolean) = apply { union = model }
}

object RequestReviewsPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>

  abstract val requestedReviewersEdge: InitStub<UserEdge>
}

object ReviewDismissalAllowance : QType, Node {
  abstract val actor: InitStub<ReviewDismissalAllowanceActor>

  val id: Stub<String>

  abstract val protectedBranch: InitStub<ProtectedBranch>
}

object ReviewDismissalAllowanceActor : QType {
  abstract val User: InitStub<User>

  abstract val Team: InitStub<Team>
}

object ReviewDismissalAllowanceConnection : QType {
  abstract val edges: InitStub<ReviewDismissalAllowanceEdge>

  abstract val nodes: InitStub<ReviewDismissalAllowance>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object ReviewDismissalAllowanceEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<ReviewDismissalAllowance>
}

object ReviewDismissedEvent : QType, UniformResourceLocatable, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  val id: Stub<String>

  abstract val message: Stub<String>

  abstract val messageHtml: Stub<HTML>

  abstract val previousReviewState: Stub<PullRequestReviewState>

  abstract val pullRequest: InitStub<PullRequest>

  abstract val pullRequestCommit: InitStub<PullRequestCommit>

  val resourcePath: Stub<URI>

  abstract val review: InitStub<PullRequestReview>

  val url: Stub<URI>
}

object ReviewRequest : QType, Node {
  abstract val databaseId: Stub<Int>

  val id: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>

  abstract val reviewer: InitStub<User>
}

object ReviewRequestConnection : QType {
  abstract val edges: InitStub<ReviewRequestEdge>

  abstract val nodes: InitStub<ReviewRequest>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object ReviewRequestEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<ReviewRequest>
}

object ReviewRequestRemovedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>

  abstract val subject: InitStub<User>
}

object ReviewRequestedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val pullRequest: InitStub<PullRequest>

  abstract val subject: InitStub<User>
}

object SearchResultItem : QType {
  abstract val Issue: InitStub<Issue>

  abstract val PullRequest: InitStub<PullRequest>

  abstract val Repository: InitStub<Repository>

  abstract val User: InitStub<User>

  abstract val Organization: InitStub<Organization>
}

object SearchResultItemConnection : QType {
  abstract val codeCount: Stub<Int>

  abstract val edges: InitStub<SearchResultItemEdge>

  abstract val issueCount: Stub<Int>

  abstract val nodes: InitStub<SearchResultItem>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val repositoryCount: Stub<Int>

  abstract val userCount: Stub<Int>

  abstract val wikiCount: Stub<Int>
}

object SearchResultItemEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<SearchResultItem>
}

enum class SearchType : QType {
  ISSUE,

  REPOSITORY,

  USER
}

object SmimeSignature : QType, GitSignature {
  val email: Stub<String>

  val isValid: Stub<Boolean>

  val payload: Stub<String>

  val signature: Stub<String>

  val signer: InitStub<User>

  val state: Stub<GitSignatureState>
}

data class StarOrder(private val field: StarOrderField,
    private val direction: OrderDirection) : QInput

enum class StarOrderField : QType {
  STARRED_AT
}

object StargazerConnection : QType {
  abstract val edges: InitStub<StargazerEdge>

  abstract val nodes: InitStub<User>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object StargazerEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<User>

  abstract val starredAt: Stub<DateTime>
}

interface Starrable : QType {
  abstract val id: Stub<String>

  abstract val stargazers: ConfigType<StargazerConnection, StargazersArgs>

  abstract val viewerHasStarred: Stub<Boolean>
}

object StarredRepositoryConnection : QType {
  abstract val edges: InitStub<StarredRepositoryEdge>

  abstract val nodes: InitStub<Repository>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object StarredRepositoryEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Repository>

  abstract val starredAt: Stub<DateTime>
}

object Status : QType, Node {
  abstract val commit: InitStub<Commit>

  abstract val context: ConfigType<StatusContext, ContextArgs>

  abstract val contexts: InitStub<StatusContext>

  val id: Stub<String>

  abstract val state: Stub<StatusState>
}

object StatusContext : QType, Node {
  abstract val commit: InitStub<Commit>

  abstract val context: Stub<String>

  abstract val createdAt: Stub<DateTime>

  abstract val creator: InitStub<Actor>

  abstract val description: Stub<String>

  val id: Stub<String>

  abstract val state: Stub<StatusState>

  abstract val targetUrl: Stub<URI>
}

enum class StatusState : QType {
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

object SubmitPullRequestReviewPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val pullRequestReview: InitStub<PullRequestReview>
}

interface Subscribable : QType {
  abstract val viewerCanSubscribe: Stub<Boolean>

  abstract val viewerSubscription: Stub<SubscriptionState>
}

object SubscribedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val subscribable: InitStub<Subscribable>
}

enum class SubscriptionState : QType {
  UNSUBSCRIBED,

  SUBSCRIBED,

  IGNORED
}

object SuggestedReviewer : QType {
  abstract val isAuthor: Stub<Boolean>

  abstract val isCommenter: Stub<Boolean>

  abstract val reviewer: InitStub<User>
}

object Tag : QType, GitObject, Node {
  val abbreviatedOid: Stub<String>

  val commitResourcePath: Stub<URI>

  val commitUrl: Stub<URI>

  val id: Stub<String>

  abstract val message: Stub<String>

  abstract val name: Stub<String>

  val oid: Stub<GitObjectID>

  val repository: InitStub<Repository>

  abstract val tagger: InitStub<GitActor>

  abstract val target: InitStub<GitObject>
}

object Team : QType, Node {
  abstract val description: Stub<String>

  abstract val editTeamResourcePath: Stub<URI>

  abstract val editTeamUrl: Stub<URI>

  val id: Stub<String>

  abstract val invitations: ConfigType<OrganizationInvitationConnection, InvitationsArgs>

  abstract val name: Stub<String>

  abstract val organization: InitStub<Organization>

  abstract val privacy: Stub<TeamPrivacy>

  abstract val resourcePath: Stub<URI>

  abstract val slug: Stub<String>

  abstract val url: Stub<URI>
}

object TeamConnection : QType {
  abstract val edges: InitStub<TeamEdge>

  abstract val nodes: InitStub<Team>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object TeamEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<Team>
}

data class TeamOrder(private val field: TeamOrderField,
    private val direction: OrderDirection) : QInput

enum class TeamOrderField : QType {
  NAME
}

enum class TeamPrivacy : QType {
  SECRET,

  VISIBLE
}

enum class TeamRole : QType {
  ADMIN,

  MEMBER
}

object Topic : QType, Node {
  val id: Stub<String>

  abstract val name: Stub<String>

  abstract val relatedTopics: InitStub<Topic>
}

enum class TopicSuggestionDeclineReason : QType {
  NOT_RELEVANT,

  TOO_SPECIFIC,

  PERSONAL_PREFERENCE,

  TOO_GENERAL
}

object Tree : QType, GitObject, Node {
  val abbreviatedOid: Stub<String>

  val commitResourcePath: Stub<URI>

  val commitUrl: Stub<URI>

  abstract val entries: InitStub<TreeEntry>

  val id: Stub<String>

  val oid: Stub<GitObjectID>

  val repository: InitStub<Repository>
}

object TreeEntry : QType {
  abstract val mode: Stub<Int>

  abstract val name: Stub<String>

  abstract val objectVal: InitStub<GitObject>

  abstract val oid: Stub<GitObjectID>

  abstract val repository: InitStub<Repository>

  abstract val type: Stub<String>
}

object URI : QType {
  abstract val model: Stub<String>
}

object UnassignedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val assignable: InitStub<Assignable>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val user: InitStub<User>
}

interface UniformResourceLocatable : QType {
  abstract val resourcePath: Stub<URI>

  abstract val url: Stub<URI>
}

object UnknownSignature : QType, GitSignature {
  val email: Stub<String>

  val isValid: Stub<Boolean>

  val payload: Stub<String>

  val signature: Stub<String>

  val signer: InitStub<User>

  val state: Stub<GitSignatureState>
}

object UnlabeledEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val label: InitStub<Label>

  abstract val labelable: InitStub<Labelable>
}

object UnlockedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val lockable: InitStub<Lockable>
}

object UnsubscribedEvent : QType, Node {
  abstract val actor: InitStub<Actor>

  abstract val createdAt: Stub<DateTime>

  val id: Stub<String>

  abstract val subscribable: InitStub<Subscribable>
}

interface Updatable : QType {
  abstract val viewerCanUpdate: Stub<Boolean>
}

interface UpdatableComment : QType {
  abstract val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason>
}

data class UpdateProjectCardInput(private val projectCardId: String,
    private val note: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateProjectCardPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val projectCard: InitStub<ProjectCard>
}

data class UpdateProjectColumnInput(private val projectColumnId: String,
    private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateProjectColumnPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val projectColumn: InitStub<ProjectColumn>
}

data class UpdateProjectInput(private val projectId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  private var state: ProjectState? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun body(model: String) = apply { body = model }

  fun state(model: ProjectState) = apply { state = model }
}

object UpdateProjectPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val project: InitStub<Project>
}

data class UpdatePullRequestReviewCommentInput(private val pullRequestReviewCommentId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdatePullRequestReviewCommentPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val pullRequestReviewComment: InitStub<PullRequestReviewComment>
}

data class UpdatePullRequestReviewInput(private val pullRequestReviewId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdatePullRequestReviewPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val pullRequestReview: InitStub<PullRequestReview>
}

data class UpdateSubscriptionInput(private val subscribableId: String,
    private val state: SubscriptionState) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateSubscriptionPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val subscribable: InitStub<Subscribable>
}

data class UpdateTopicsInput(private val repositoryId: String,
    private val topicNames: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

object UpdateTopicsPayload : QType {
  abstract val clientMutationId: Stub<String>

  abstract val invalidTopicNames: Stub<String>

  abstract val repository: InitStub<Repository>
}

object User : QType, UniformResourceLocatable, RepositoryOwner, Actor, Node {
  val avatarUrl: Config<String, AvatarUrlArgs>

  abstract val bio: Stub<String>

  abstract val bioHTML: Stub<HTML>

  abstract val company: Stub<String>

  abstract val companyHTML: Stub<HTML>

  abstract val contributedRepositories: ConfigType<RepositoryConnection, ContributedRepositoriesArgs>

  abstract val createdAt: Stub<DateTime>

  abstract val databaseId: Stub<Int>

  abstract val email: Stub<String>

  abstract val followers: ConfigType<FollowerConnection, FollowersArgs>

  abstract val following: ConfigType<FollowingConnection, FollowingArgs>

  abstract val gist: ConfigType<Gist, GistArgs>

  abstract val gists: ConfigType<GistConnection, GistsArgs>

  val id: Stub<String>

  abstract val isBountyHunter: Stub<Boolean>

  abstract val isCampusExpert: Stub<Boolean>

  abstract val isDeveloperProgramMember: Stub<Boolean>

  abstract val isEmployee: Stub<Boolean>

  abstract val isHireable: Stub<Boolean>

  abstract val isInvoiced: Stub<Boolean>

  abstract val isSiteAdmin: Stub<Boolean>

  abstract val isViewer: Stub<Boolean>

  abstract val issues: ConfigType<IssueConnection, IssuesArgs>

  abstract val location: Stub<String>

  val login: Stub<String>

  abstract val name: Stub<String>

  abstract val organization: ConfigType<Organization, OrganizationArgs>

  abstract val organizations: ConfigType<OrganizationConnection, OrganizationsArgs>

  val pinnedRepositories: ConfigType<RepositoryConnection, Base>

  abstract val pullRequests: ConfigType<PullRequestConnection, PullRequestsArgs>

  val repositories: ConfigType<RepositoryConnection, Base>

  val repository: ConfigType<Repository, Base>

  val resourcePath: Stub<URI>

  abstract val starredRepositories: ConfigType<StarredRepositoryConnection, StarredRepositoriesArgs>

  abstract val updatedAt: Stub<DateTime>

  val url: Stub<URI>

  abstract val viewerCanFollow: Stub<Boolean>

  abstract val viewerIsFollowing: Stub<Boolean>

  abstract val watching: ConfigType<RepositoryConnection, WatchingArgs>

  abstract val websiteUrl: Stub<URI>

  class AvatarUrlArgs(args: AvatarUrlArgs = ArgBuilder.create<String, AvatarUrlArgs>()) : AvatarUrlArgs(args) {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }

  class PinnedRepositoriesArgs(args: PinnedRepositoriesArgs = TypeArgBuilder.create<RepositoryConnection, PinnedRepositoriesArgs>()) : PinnedRepositoriesArgs by args {
    fun first(value: Int): PinnedRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): PinnedRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): PinnedRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): PinnedRepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs = apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): PinnedRepositoriesArgs = apply { addArg("isLocked", value) }

  }

  class RepositoriesArgs(args: RepositoriesArgs = TypeArgBuilder.create<RepositoryConnection, RepositoriesArgs>()) : RepositoriesArgs by args {
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

  class RepositoryArgs(args: RepositoryArgs = TypeArgBuilder.create<Repository, RepositoryArgs>()) : RepositoryArgs by args {
    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }
}

object UserConnection : QType {
  abstract val edges: InitStub<UserEdge>

  abstract val nodes: InitStub<User>

  abstract val pageInfo: InitStub<PageInfo>

  abstract val totalCount: Stub<Int>
}

object UserEdge : QType {
  abstract val cursor: Stub<String>

  abstract val node: InitStub<User>
}

object X509Certificate : QType {
  abstract val model: Stub<String>
}

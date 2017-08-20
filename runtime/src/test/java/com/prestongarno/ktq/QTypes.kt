@file:Suppress("unused")

package com.prestongarno.ktq

import kotlin.reflect.KFunction1

enum class TopicSuggestionDeclineReason {
  NOT_RELEVANT,

  TOO_SPECIFIC,

  PERSONAL_PREFERENCE,

  TOO_GENERAL
}

enum class TeamRole {
  ADMIN,

  MEMBER
}

enum class TeamPrivacy {
  SECRET,

  VISIBLE
}

enum class TeamOrderField {
  NAME
}

enum class SubscriptionState {
  UNSUBSCRIBED,

  SUBSCRIBED,

  IGNORED
}

enum class StatusState {
  EXPECTED,

  ERROR,

  FAILURE,

  PENDING,

  SUCCESS
}

enum class StarOrderField {
  STARRED_AT
}

enum class SearchType {
  ISSUE,

  REPOSITORY,

  USER
}

enum class RepositoryPrivacy {
  PUBLIC,

  PRIVATE
}

enum class RepositoryOrderField {
  CREATED_AT,

  UPDATED_AT,

  PUSHED_AT,

  NAME,

  STARGAZERS
}

enum class RepositoryLockReason {
  MOVING,

  BILLING,

  RENAME,

  MIGRATING
}

enum class RepositoryCollaboratorAffiliation {
  ALL,

  OUTSIDE
}

enum class RepositoryAffiliation {
  OWNER,

  COLLABORATOR,

  ORGANIZATION_MEMBER
}

enum class ReactionOrderField {
  CREATED_AT
}

enum class ReactionContent {
  THUMBS_UP,

  THUMBS_DOWN,

  LAUGH,

  HOORAY,

  CONFUSED,

  HEART
}

enum class PullRequestState {
  OPEN,

  CLOSED,

  MERGED
}

enum class PullRequestReviewState {
  PENDING,

  COMMENTED,

  APPROVED,

  CHANGES_REQUESTED,

  DISMISSED
}

enum class PullRequestReviewEvent {
  COMMENT,

  APPROVE,

  REQUEST_CHANGES,

  DISMISS
}

enum class PullRequestPubSubTopic {
  UPDATED,

  MARKASREAD,

  HEAD_REF
}

enum class ProjectState {
  OPEN,

  CLOSED
}

enum class ProjectOrderField {
  CREATED_AT,

  UPDATED_AT,

  NAME
}

enum class ProjectCardState {
  CONTENT_ONLY,

  NOTE_ONLY,

  REDACTED
}

enum class OrganizationInvitationRole {
  DIRECT_MEMBER,

  ADMIN,

  BILLING_MANAGER,

  REINSTATE
}

enum class OrderDirection {
  ASC,

  DESC
}

enum class MilestoneState {
  OPEN,

  CLOSED
}

enum class MergeableState {
  MERGEABLE,

  CONFLICTING,

  UNKNOWN
}

enum class LanguageOrderField {
  SIZE
}

enum class IssueState {
  OPEN,

  CLOSED
}

enum class IssuePubSubTopic {
  UPDATED,

  MARKASREAD
}

enum class IssueOrderField {
  CREATED_AT,

  UPDATED_AT,

  COMMENTS
}

enum class GitSignatureState {
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

enum class GistPrivacy {
  PUBLIC,

  SECRET,

  ALL
}

enum class DeploymentStatusState {
  PENDING,

  SUCCESS,

  FAILURE,

  INACTIVE,

  ERROR
}

enum class DeploymentState {
  ABANDONED,

  ACTIVE,

  DESTROYED,

  ERROR,

  FAILURE,

  INACTIVE,

  PENDING
}

enum class DefaultRepositoryPermissionField {
  READ,

  WRITE,

  ADMIN
}

enum class CommentCannotUpdateReason {
  INSUFFICIENT_ACCESS,

  LOCKED,

  LOGIN_REQUIRED,

  MAINTENANCE,

  VERIFIED_EMAIL_REQUIRED
}

enum class CommentAuthorAssociation {
  MEMBER,

  OWNER,

  COLLABORATOR,

  CONTRIBUTOR,

  FIRST_TIME_CONTRIBUTOR,

  NONE
}

interface UserEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : User> node(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("node", of)
}

interface UserConnection : QType {
  fun <T : UserEdge> edges(init: () -> T): NullableStub<UserEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<UserEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<UserEdge, T>("edges", of)

  fun <T : User> nodes(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface User : QType {
  fun bio(): NullableStub<String, ArgBuilder> = nullableStub()

  fun bioHTML(): Stub<HTML, ArgBuilder> = stub()

  fun company(): NullableStub<String, ArgBuilder> = nullableStub()

  fun companyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun email(): Stub<String, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun isBountyHunter(): Stub<Boolean, ArgBuilder> = stub()

  fun isCampusExpert(): Stub<Boolean, ArgBuilder> = stub()

  fun isDeveloperProgramMember(): Stub<Boolean, ArgBuilder> = stub()

  fun isEmployee(): Stub<Boolean, ArgBuilder> = stub()

  fun isHireable(): Stub<Boolean, ArgBuilder> = stub()

  fun isInvoiced(): Stub<Boolean, ArgBuilder> = stub()

  fun isSiteAdmin(): Stub<Boolean, ArgBuilder> = stub()

  fun isViewer(): Stub<Boolean, ArgBuilder> = stub()

  fun location(): NullableStub<String, ArgBuilder> = nullableStub()

  fun login(): Stub<String, ArgBuilder> = stub()

  fun name(): NullableStub<String, ArgBuilder> = nullableStub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun viewerCanFollow(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerIsFollowing(): Stub<Boolean, ArgBuilder> = stub()

  fun websiteUrl(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun <T : URI> avatarUrl(init: () -> T,
      argBuilder: AvatarUrlArgs = AvatarUrlArgs()): Stub<T, AvatarUrlArgs> =
        configStub(init, argBuilder)

  fun <T : RepositoryConnection> contributedRepositories(init: () -> T,
      argBuilder: ContributedRepositoriesArgs = ContributedRepositoriesArgs()): Stub<T, ContributedRepositoriesArgs> =
        configStub(init, argBuilder)

  fun <T : FollowerConnection> followers(init: () -> T,
      argBuilder: FollowersArgs = FollowersArgs()): Stub<T, FollowersArgs> =
        configStub(init, argBuilder)

  fun <T : FollowingConnection> following(init: () -> T,
      argBuilder: FollowingArgs = FollowingArgs()): Stub<T, FollowingArgs> =
        configStub(init, argBuilder)

  fun <T : Gist> gist(init: () -> T, argBuilder: GistArgs = GistArgs()): NullableStub<T, GistArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : GistConnection> gists(init: () -> T,
      argBuilder: GistsArgs = GistsArgs()): Stub<T, GistsArgs> = configStub(init, argBuilder)

  fun <T : IssueConnection> issues(init: () -> T,
      argBuilder: IssuesArgs = IssuesArgs()): Stub<T, IssuesArgs> = configStub(init, argBuilder)

  fun <T : Organization> organization(init: () -> T,
      argBuilder: OrganizationArgs = OrganizationArgs()): NullableStub<T, OrganizationArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : OrganizationConnection> organizations(init: () -> T,
      argBuilder: OrganizationsArgs = OrganizationsArgs()): Stub<T, OrganizationsArgs> =
        configStub(init, argBuilder)

  fun <T : RepositoryConnection> pinnedRepositories(init: () -> T,
      argBuilder: PinnedRepositoriesArgs = PinnedRepositoriesArgs()): Stub<T, PinnedRepositoriesArgs> =
        configStub(init, argBuilder)

  fun <T : PullRequestConnection> pullRequests(init: () -> T,
      argBuilder: PullRequestsArgs = PullRequestsArgs()): Stub<T, PullRequestsArgs> =
        configStub(init, argBuilder)

  fun <T : RepositoryConnection> repositories(init: () -> T,
      argBuilder: RepositoriesArgs = RepositoriesArgs()): Stub<T, RepositoriesArgs> =
        configStub(init, argBuilder)

  fun <T : Repository> repository(init: () -> T,
      argBuilder: RepositoryArgs = RepositoryArgs()): NullableStub<T, RepositoryArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : StarredRepositoryConnection> starredRepositories(init: () -> T,
      argBuilder: StarredRepositoriesArgs = StarredRepositoriesArgs()): Stub<T, StarredRepositoriesArgs> =
        configStub(init, argBuilder)

  fun <T : RepositoryConnection> watching(init: () -> T,
      argBuilder: WatchingArgs = WatchingArgs()): Stub<T, WatchingArgs> =
        configStub(init, argBuilder)

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }

  class ContributedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ContributedRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): ContributedRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): ContributedRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): ContributedRepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): ContributedRepositoriesArgs =
          apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): ContributedRepositoriesArgs =
          apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): ContributedRepositoriesArgs =
          apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): ContributedRepositoriesArgs = apply { addArg("isLocked", value) }

  }

  class FollowersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): FollowersArgs = apply { addArg("first", value) }


    fun after(value: String): FollowersArgs = apply { addArg("after", value) }


    fun last(value: Int): FollowersArgs = apply { addArg("last", value) }


    fun before(value: String): FollowersArgs = apply { addArg("before", value) }

  }

  class FollowingArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): FollowingArgs = apply { addArg("first", value) }


    fun after(value: String): FollowingArgs = apply { addArg("after", value) }


    fun last(value: Int): FollowingArgs = apply { addArg("last", value) }


    fun before(value: String): FollowingArgs = apply { addArg("before", value) }

  }

  class GistArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): GistArgs = apply { addArg("name", value) }

  }

  class GistsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): GistsArgs = apply { addArg("first", value) }


    fun after(value: String): GistsArgs = apply { addArg("after", value) }


    fun last(value: Int): GistsArgs = apply { addArg("last", value) }


    fun before(value: String): GistsArgs = apply { addArg("before", value) }


    fun privacy(value: GistPrivacy): GistsArgs = apply { addArg("privacy", value) }

  }

  class IssuesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class OrganizationArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): OrganizationArgs = apply { addArg("login", value) }

  }

  class OrganizationsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): OrganizationsArgs = apply { addArg("first", value) }


    fun after(value: String): OrganizationsArgs = apply { addArg("after", value) }


    fun last(value: Int): OrganizationsArgs = apply { addArg("last", value) }


    fun before(value: String): OrganizationsArgs = apply { addArg("before", value) }

  }

  class PinnedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PinnedRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): PinnedRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): PinnedRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): PinnedRepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs =
          apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs =
          apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): PinnedRepositoriesArgs = apply { addArg("isLocked", value) }

  }

  class PullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
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

  class RepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): RepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): RepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): RepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): RepositoriesArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): RepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): RepositoriesArgs =
          apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): RepositoriesArgs = apply { addArg("isLocked", value) }


    fun isFork(value: Boolean): RepositoriesArgs = apply { addArg("isFork", value) }

  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }

  class StarredRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StarredRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): StarredRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): StarredRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): StarredRepositoriesArgs = apply { addArg("before", value) }


    fun ownedByViewer(value: Boolean): StarredRepositoriesArgs =
          apply { addArg("ownedByViewer", value) }


    fun orderBy(value: StarOrder): StarredRepositoriesArgs = apply { addArg("orderBy", value) }

  }

  class WatchingArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): WatchingArgs = apply { addArg("first", value) }


    fun after(value: String): WatchingArgs = apply { addArg("after", value) }


    fun last(value: Int): WatchingArgs = apply { addArg("last", value) }


    fun before(value: String): WatchingArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): WatchingArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): WatchingArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): WatchingArgs =
          apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): WatchingArgs = apply { addArg("isLocked", value) }

  }
}

interface UpdateTopicsPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun invalidTopicNames(): Stub<String, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)
}

interface UpdateSubscriptionPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Subscribable> subscribable(init: () -> T): Stub<Subscribable, ArgBuilder> = stub()

  fun <T> subscribable(of: KFunction1<Subscribable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Subscribable, T>("subscribable", of)
}

interface UpdatePullRequestReviewPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview, ArgBuilder> =
        stub()

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReview, T>("pullRequestReview", of)
}

interface UpdatePullRequestReviewCommentPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : PullRequestReviewComment> pullRequestReviewComment(init: () -> T): Stub<PullRequestReviewComment, ArgBuilder> =
        stub()

  fun <T> pullRequestReviewComment(of: KFunction1<PullRequestReviewComment, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReviewComment, T>("pullRequestReviewComment", of)
}

interface UpdateProjectPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Project> project(init: () -> T): Stub<Project, ArgBuilder> = stub()

  fun <T> project(of: KFunction1<Project, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Project, T>("project", of)
}

interface UpdateProjectColumnPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : ProjectColumn> projectColumn(init: () -> T): Stub<ProjectColumn, ArgBuilder> = stub()

  fun <T> projectColumn(of: KFunction1<ProjectColumn, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProjectColumn, T>("projectColumn", of)
}

interface UpdateProjectCardPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : ProjectCard> projectCard(init: () -> T): Stub<ProjectCard, ArgBuilder> = stub()

  fun <T> projectCard(of: KFunction1<ProjectCard, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProjectCard, T>("projectCard", of)
}

interface UnsubscribedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : Subscribable> subscribable(init: () -> T): Stub<Subscribable, ArgBuilder> = stub()

  fun <T> subscribable(of: KFunction1<Subscribable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Subscribable, T>("subscribable", of)
}

interface UnlockedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : Lockable> lockable(init: () -> T): Stub<Lockable, ArgBuilder> = stub()

  fun <T> lockable(of: KFunction1<Lockable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Lockable, T>("lockable", of)
}

interface UnlabeledEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : Label> label(init: () -> T): Stub<Label, ArgBuilder> = stub()

  fun <T> label(of: KFunction1<Label, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Label, T>("label", of)

  fun <T : Labelable> labelable(init: () -> T): Stub<Labelable, ArgBuilder> = stub()

  fun <T> labelable(of: KFunction1<Labelable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Labelable, T>("labelable", of)
}

interface UnknownSignature : QType {
  fun email(): Stub<String, ArgBuilder> = stub()

  fun isValid(): Stub<Boolean, ArgBuilder> = stub()

  fun payload(): Stub<String, ArgBuilder> = stub()

  fun signature(): Stub<String, ArgBuilder> = stub()

  fun <T : User> signer(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> signer(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("signer", of)

  fun state(): Stub<GitSignatureState, ArgBuilder> = stub()
}

interface UnassignedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun <T : Assignable> assignable(init: () -> T): Stub<Assignable, ArgBuilder> = stub()

  fun <T> assignable(of: KFunction1<Assignable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Assignable, T>("assignable", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : User> user(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> user(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("user", of)
}

interface TreeEntry : QType {
  fun mode(): Stub<Int, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun <T : GitObject> objectVal(init: () -> T): NullableStub<GitObject, ArgBuilder> = nullableStub()

  fun <T> objectVal(of: KFunction1<GitObject, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<GitObject, T>("objectVal", of)

  fun oid(): Stub<GitObjectID, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun type(): Stub<String, ArgBuilder> = stub()
}

interface Tree : QType {
  fun abbreviatedOid(): Stub<String, ArgBuilder> = stub()

  fun commitResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun commitUrl(): Stub<URI, ArgBuilder> = stub()

  fun <T : TreeEntry> entries(init: () -> T): Stub<TreeEntry, ArgBuilder> = stub()

  fun <T> entries(of: KFunction1<TreeEntry, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<TreeEntry, T>("entries", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun oid(): Stub<GitObjectID, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)
}

interface Topic : QType {
  fun id(): Stub<String, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun <T : Topic> relatedTopics(init: () -> T): Stub<Topic, ArgBuilder> = stub()

  fun <T> relatedTopics(of: KFunction1<Topic, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Topic, T>("relatedTopics", of)
}

interface TeamEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Team> node(init: () -> T): NullableStub<Team, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Team, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Team, T>("node", of)
}

interface TeamConnection : QType {
  fun <T : TeamEdge> edges(init: () -> T): NullableStub<TeamEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<TeamEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<TeamEdge, T>("edges", of)

  fun <T : Team> nodes(init: () -> T): NullableStub<Team, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Team, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Team, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface Team : QType {
  fun description(): NullableStub<String, ArgBuilder> = nullableStub()

  fun editTeamResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun editTeamUrl(): Stub<URI, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun <T : Organization> organization(init: () -> T): Stub<Organization, ArgBuilder> = stub()

  fun <T> organization(of: KFunction1<Organization, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Organization, T>("organization", of)

  fun privacy(): Stub<TeamPrivacy, ArgBuilder> = stub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun slug(): Stub<String, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun <T : OrganizationInvitationConnection> invitations(init: () -> T,
      argBuilder: InvitationsArgs = InvitationsArgs()): NullableStub<T, InvitationsArgs> {
     return configNullableStub(init, argBuilder)
  }

  class InvitationsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): InvitationsArgs = apply { addArg("first", value) }


    fun after(value: String): InvitationsArgs = apply { addArg("after", value) }


    fun last(value: Int): InvitationsArgs = apply { addArg("last", value) }


    fun before(value: String): InvitationsArgs = apply { addArg("before", value) }

  }
}

interface Tag : QType {
  fun abbreviatedOid(): Stub<String, ArgBuilder> = stub()

  fun commitResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun commitUrl(): Stub<URI, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun message(): NullableStub<String, ArgBuilder> = nullableStub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun oid(): Stub<GitObjectID, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun <T : GitActor> tagger(init: () -> T): NullableStub<GitActor, ArgBuilder> = nullableStub()

  fun <T> tagger(of: KFunction1<GitActor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<GitActor, T>("tagger", of)

  fun <T : GitObject> target(init: () -> T): Stub<GitObject, ArgBuilder> = stub()

  fun <T> target(of: KFunction1<GitObject, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<GitObject, T>("target", of)
}

interface SuggestedReviewer : QType {
  fun isAuthor(): Stub<Boolean, ArgBuilder> = stub()

  fun isCommenter(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : User> reviewer(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> reviewer(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("reviewer", of)
}

interface SubscribedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : Subscribable> subscribable(init: () -> T): Stub<Subscribable, ArgBuilder> = stub()

  fun <T> subscribable(of: KFunction1<Subscribable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Subscribable, T>("subscribable", of)
}

interface SubmitPullRequestReviewPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview, ArgBuilder> =
        stub()

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReview, T>("pullRequestReview", of)
}

interface StatusContext : QType {
  fun <T : Commit> commit(init: () -> T): NullableStub<Commit, ArgBuilder> = nullableStub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("commit", of)

  fun context(): Stub<String, ArgBuilder> = stub()

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun <T : Actor> creator(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> creator(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("creator", of)

  fun description(): NullableStub<String, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun state(): Stub<StatusState, ArgBuilder> = stub()

  fun targetUrl(): NullableStub<URI, ArgBuilder> = nullableStub()
}

interface Status : QType {
  fun <T : Commit> commit(init: () -> T): NullableStub<Commit, ArgBuilder> = nullableStub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("commit", of)

  fun <T : StatusContext> contexts(init: () -> T): Stub<StatusContext, ArgBuilder> = stub()

  fun <T> contexts(of: KFunction1<StatusContext, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<StatusContext, T>("contexts", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun state(): Stub<StatusState, ArgBuilder> = stub()

  fun <T : StatusContext> context(init: () -> T,
      argBuilder: ContextArgs = ContextArgs()): NullableStub<T, ContextArgs> {
     return configNullableStub(init, argBuilder)
  }

  class ContextArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): ContextArgs = apply { addArg("name", value) }

  }
}

interface StarredRepositoryEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Repository> node(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> node(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("node", of)

  fun starredAt(): Stub<DateTime, ArgBuilder> = stub()
}

interface StarredRepositoryConnection : QType {
  fun <T : StarredRepositoryEdge> edges(init: () -> T): NullableStub<StarredRepositoryEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<StarredRepositoryEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<StarredRepositoryEdge, T>("edges", of)

  fun <T : Repository> nodes(init: () -> T): NullableStub<Repository, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Repository, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Repository, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface StargazerEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : User> node(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> node(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("node", of)

  fun starredAt(): Stub<DateTime, ArgBuilder> = stub()
}

interface StargazerConnection : QType {
  fun <T : StargazerEdge> edges(init: () -> T): NullableStub<StargazerEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<StargazerEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<StargazerEdge, T>("edges", of)

  fun <T : User> nodes(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface SmimeSignature : QType {
  fun email(): Stub<String, ArgBuilder> = stub()

  fun isValid(): Stub<Boolean, ArgBuilder> = stub()

  fun payload(): Stub<String, ArgBuilder> = stub()

  fun signature(): Stub<String, ArgBuilder> = stub()

  fun <T : User> signer(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> signer(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("signer", of)

  fun state(): Stub<GitSignatureState, ArgBuilder> = stub()
}

interface SearchResultItemEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : SearchResultItem> node(init: () -> T): NullableStub<SearchResultItem, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<SearchResultItem, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<SearchResultItem, T>("node", of)
}

interface SearchResultItemConnection : QType {
  fun codeCount(): Stub<Int, ArgBuilder> = stub()

  fun <T : SearchResultItemEdge> edges(init: () -> T): NullableStub<SearchResultItemEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<SearchResultItemEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<SearchResultItemEdge, T>("edges", of)

  fun issueCount(): Stub<Int, ArgBuilder> = stub()

  fun <T : SearchResultItem> nodes(init: () -> T): NullableStub<SearchResultItem, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<SearchResultItem, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<SearchResultItem, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun repositoryCount(): Stub<Int, ArgBuilder> = stub()

  fun userCount(): Stub<Int, ArgBuilder> = stub()

  fun wikiCount(): Stub<Int, ArgBuilder> = stub()
}

interface ReviewRequestedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : User> subject(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> subject(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("subject", of)
}

interface ReviewRequestRemovedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : User> subject(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> subject(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("subject", of)
}

interface ReviewRequestEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : ReviewRequest> node(init: () -> T): NullableStub<ReviewRequest, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<ReviewRequest, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReviewRequest, T>("node", of)
}

interface ReviewRequestConnection : QType {
  fun <T : ReviewRequestEdge> edges(init: () -> T): NullableStub<ReviewRequestEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<ReviewRequestEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReviewRequestEdge, T>("edges", of)

  fun <T : ReviewRequest> nodes(init: () -> T): NullableStub<ReviewRequest, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<ReviewRequest, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReviewRequest, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface ReviewRequest : QType {
  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : User> reviewer(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> reviewer(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("reviewer", of)
}

interface ReviewDismissedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun message(): Stub<String, ArgBuilder> = stub()

  fun messageHtml(): Stub<HTML, ArgBuilder> = stub()

  fun previousReviewState(): Stub<PullRequestReviewState, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : PullRequestCommit> pullRequestCommit(init: () -> T): NullableStub<PullRequestCommit, ArgBuilder> =
        nullableStub()

  fun <T> pullRequestCommit(of: KFunction1<PullRequestCommit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestCommit, T>("pullRequestCommit", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun <T : PullRequestReview> review(init: () -> T): NullableStub<PullRequestReview, ArgBuilder> =
        nullableStub()

  fun <T> review(of: KFunction1<PullRequestReview, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestReview, T>("review", of)

  fun url(): Stub<URI, ArgBuilder> = stub()
}

interface ReviewDismissalAllowanceEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : ReviewDismissalAllowance> node(init: () -> T): NullableStub<ReviewDismissalAllowance, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<ReviewDismissalAllowance, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReviewDismissalAllowance, T>("node", of)
}

interface ReviewDismissalAllowanceConnection : QType {
  fun <T : ReviewDismissalAllowanceEdge> edges(init: () -> T): NullableStub<ReviewDismissalAllowanceEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<ReviewDismissalAllowanceEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReviewDismissalAllowanceEdge, T>("edges", of)

  fun <T : ReviewDismissalAllowance> nodes(init: () -> T): NullableStub<ReviewDismissalAllowance, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<ReviewDismissalAllowance, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReviewDismissalAllowance, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface ReviewDismissalAllowance : QType {
  fun <T : ReviewDismissalAllowanceActor> actor(init: () -> T): NullableStub<ReviewDismissalAllowanceActor, ArgBuilder> =
        nullableStub()

  fun <T> actor(of: KFunction1<ReviewDismissalAllowanceActor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReviewDismissalAllowanceActor, T>("actor", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : ProtectedBranch> protectedBranch(init: () -> T): Stub<ProtectedBranch, ArgBuilder> =
        stub()

  fun <T> protectedBranch(of: KFunction1<ProtectedBranch, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProtectedBranch, T>("protectedBranch", of)
}

interface RequestReviewsPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : UserEdge> requestedReviewersEdge(init: () -> T): Stub<UserEdge, ArgBuilder> = stub()

  fun <T> requestedReviewersEdge(of: KFunction1<UserEdge, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<UserEdge, T>("requestedReviewersEdge", of)
}

interface RepositoryTopicEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : RepositoryTopic> node(init: () -> T): NullableStub<RepositoryTopic, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<RepositoryTopic, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<RepositoryTopic, T>("node", of)
}

interface RepositoryTopicConnection : QType {
  fun <T : RepositoryTopicEdge> edges(init: () -> T): NullableStub<RepositoryTopicEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<RepositoryTopicEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<RepositoryTopicEdge, T>("edges", of)

  fun <T : RepositoryTopic> nodes(init: () -> T): NullableStub<RepositoryTopic, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<RepositoryTopic, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<RepositoryTopic, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface RepositoryTopic : QType {
  fun id(): Stub<String, ArgBuilder> = stub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun <T : Topic> topic(init: () -> T): Stub<Topic, ArgBuilder> = stub()

  fun <T> topic(of: KFunction1<Topic, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Topic, T>("topic", of)

  fun url(): Stub<URI, ArgBuilder> = stub()
}

interface RepositoryInvitationRepository : QType {
  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun description(): NullableStub<String, ArgBuilder> = nullableStub()

  fun descriptionHTML(): Stub<HTML, ArgBuilder> = stub()

  fun hasIssuesEnabled(): Stub<Boolean, ArgBuilder> = stub()

  fun hasWikiEnabled(): Stub<Boolean, ArgBuilder> = stub()

  fun homepageUrl(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun isFork(): Stub<Boolean, ArgBuilder> = stub()

  fun isLocked(): Stub<Boolean, ArgBuilder> = stub()

  fun isMirror(): Stub<Boolean, ArgBuilder> = stub()

  fun isPrivate(): Stub<Boolean, ArgBuilder> = stub()

  fun license(): NullableStub<String, ArgBuilder> = nullableStub()

  fun lockReason(): NullableStub<RepositoryLockReason, ArgBuilder> = nullableStub()

  fun mirrorUrl(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun nameWithOwner(): Stub<String, ArgBuilder> = stub()

  fun <T : RepositoryOwner> owner(init: () -> T): Stub<RepositoryOwner, ArgBuilder> = stub()

  fun <T> owner(of: KFunction1<RepositoryOwner, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<RepositoryOwner, T>("owner", of)

  fun pushedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()
}

interface RepositoryInvitation : QType {
  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : User> invitee(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> invitee(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("invitee", of)

  fun <T : User> inviter(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> inviter(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("inviter", of)

  fun <T : RepositoryInvitationRepository> repository(init: () -> T): NullableStub<RepositoryInvitationRepository, ArgBuilder> =
        nullableStub()

  fun <T> repository(of: KFunction1<RepositoryInvitationRepository, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<RepositoryInvitationRepository, T>("repository", of)
}

interface RepositoryEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Repository> node(init: () -> T): NullableStub<Repository, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Repository, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Repository, T>("node", of)
}

interface RepositoryConnection : QType {
  fun <T : RepositoryEdge> edges(init: () -> T): NullableStub<RepositoryEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<RepositoryEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<RepositoryEdge, T>("edges", of)

  fun <T : Repository> nodes(init: () -> T): NullableStub<Repository, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Repository, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Repository, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()

  fun totalDiskUsage(): Stub<Int, ArgBuilder> = stub()
}

interface Repository : QType {
  fun <T : CodeOfConduct> codeOfConduct(init: () -> T): NullableStub<CodeOfConduct, ArgBuilder> =
        nullableStub()

  fun <T> codeOfConduct(of: KFunction1<CodeOfConduct, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<CodeOfConduct, T>("codeOfConduct", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun <T : Ref> defaultBranchRef(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> defaultBranchRef(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("defaultBranchRef", of)

  fun description(): NullableStub<String, ArgBuilder> = nullableStub()

  fun descriptionHTML(): Stub<HTML, ArgBuilder> = stub()

  fun diskUsage(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun hasIssuesEnabled(): Stub<Boolean, ArgBuilder> = stub()

  fun hasWikiEnabled(): Stub<Boolean, ArgBuilder> = stub()

  fun homepageUrl(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun isFork(): Stub<Boolean, ArgBuilder> = stub()

  fun isLocked(): Stub<Boolean, ArgBuilder> = stub()

  fun isMirror(): Stub<Boolean, ArgBuilder> = stub()

  fun isPrivate(): Stub<Boolean, ArgBuilder> = stub()

  fun license(): NullableStub<String, ArgBuilder> = nullableStub()

  fun lockReason(): NullableStub<RepositoryLockReason, ArgBuilder> = nullableStub()

  fun mirrorUrl(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun nameWithOwner(): Stub<String, ArgBuilder> = stub()

  fun <T : RepositoryOwner> owner(init: () -> T): Stub<RepositoryOwner, ArgBuilder> = stub()

  fun <T> owner(of: KFunction1<RepositoryOwner, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<RepositoryOwner, T>("owner", of)

  fun <T : Repository> parent(init: () -> T): NullableStub<Repository, ArgBuilder> = nullableStub()

  fun <T> parent(of: KFunction1<Repository, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Repository, T>("parent", of)

  fun <T : Language> primaryLanguage(init: () -> T): NullableStub<Language, ArgBuilder> =
        nullableStub()

  fun <T> primaryLanguage(of: KFunction1<Language, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Language, T>("primaryLanguage", of)

  fun projectsResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun projectsUrl(): Stub<URI, ArgBuilder> = stub()

  fun pushedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun viewerCanAdminister(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanCreateProjects(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanSubscribe(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanUpdateTopics(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerHasStarred(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerSubscription(): Stub<SubscriptionState, ArgBuilder> = stub()

  fun <T : CommitCommentConnection> commitComments(init: () -> T,
      argBuilder: CommitCommentsArgs = CommitCommentsArgs()): Stub<T, CommitCommentsArgs> =
        configStub(init, argBuilder)

  fun <T : DeploymentConnection> deployments(init: () -> T,
      argBuilder: DeploymentsArgs = DeploymentsArgs()): Stub<T, DeploymentsArgs> =
        configStub(init, argBuilder)

  fun <T : RepositoryConnection> forks(init: () -> T,
      argBuilder: ForksArgs = ForksArgs()): Stub<T, ForksArgs> = configStub(init, argBuilder)

  fun <T : Issue> issue(init: () -> T,
      argBuilder: IssueArgs = IssueArgs()): NullableStub<T, IssueArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : IssueOrPullRequest> issueOrPullRequest(init: () -> T,
      argBuilder: IssueOrPullRequestArgs = IssueOrPullRequestArgs()): NullableStub<T, IssueOrPullRequestArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : IssueConnection> issues(init: () -> T,
      argBuilder: IssuesArgs = IssuesArgs()): Stub<T, IssuesArgs> = configStub(init, argBuilder)

  fun <T : Label> label(init: () -> T,
      argBuilder: LabelArgs = LabelArgs()): NullableStub<T, LabelArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : LabelConnection> labels(init: () -> T,
      argBuilder: LabelsArgs = LabelsArgs()): NullableStub<T, LabelsArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : LanguageConnection> languages(init: () -> T,
      argBuilder: LanguagesArgs = LanguagesArgs()): NullableStub<T, LanguagesArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UserConnection> mentionableUsers(init: () -> T,
      argBuilder: MentionableUsersArgs = MentionableUsersArgs()): Stub<T, MentionableUsersArgs> =
        configStub(init, argBuilder)

  fun <T : Milestone> milestone(init: () -> T,
      argBuilder: MilestoneArgs = MilestoneArgs()): NullableStub<T, MilestoneArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : MilestoneConnection> milestones(init: () -> T,
      argBuilder: MilestonesArgs = MilestonesArgs()): NullableStub<T, MilestonesArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : GitObject> objectVal(init: () -> T,
      argBuilder: ObjectValArgs = ObjectValArgs()): NullableStub<T, ObjectValArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : Project> project(init: () -> T,
      argBuilder: ProjectArgs = ProjectArgs()): NullableStub<T, ProjectArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : ProjectConnection> projects(init: () -> T,
      argBuilder: ProjectsArgs = ProjectsArgs()): Stub<T, ProjectsArgs> =
        configStub(init, argBuilder)

  fun <T : ProtectedBranchConnection> protectedBranches(init: () -> T,
      argBuilder: ProtectedBranchesArgs = ProtectedBranchesArgs()): Stub<T, ProtectedBranchesArgs> =
        configStub(init, argBuilder)

  fun <T : PullRequest> pullRequest(init: () -> T,
      argBuilder: PullRequestArgs = PullRequestArgs()): NullableStub<T, PullRequestArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : PullRequestConnection> pullRequests(init: () -> T,
      argBuilder: PullRequestsArgs = PullRequestsArgs()): Stub<T, PullRequestsArgs> =
        configStub(init, argBuilder)

  fun <T : Ref> ref(init: () -> T, argBuilder: RefArgs = RefArgs()): NullableStub<T, RefArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : RefConnection> refs(init: () -> T,
      argBuilder: RefsArgs = RefsArgs()): NullableStub<T, RefsArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : ReleaseConnection> releases(init: () -> T,
      argBuilder: ReleasesArgs = ReleasesArgs()): Stub<T, ReleasesArgs> =
        configStub(init, argBuilder)

  fun <T : RepositoryTopicConnection> repositoryTopics(init: () -> T,
      argBuilder: RepositoryTopicsArgs = RepositoryTopicsArgs()): Stub<T, RepositoryTopicsArgs> =
        configStub(init, argBuilder)

  fun <T : StargazerConnection> stargazers(init: () -> T,
      argBuilder: StargazersArgs = StargazersArgs()): Stub<T, StargazersArgs> =
        configStub(init, argBuilder)

  fun <T : UserConnection> watchers(init: () -> T,
      argBuilder: WatchersArgs = WatchersArgs()): Stub<T, WatchersArgs> =
        configStub(init, argBuilder)

  class CommitCommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommitCommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommitCommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommitCommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommitCommentsArgs = apply { addArg("before", value) }

  }

  class DeploymentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): DeploymentsArgs = apply { addArg("first", value) }


    fun after(value: String): DeploymentsArgs = apply { addArg("after", value) }


    fun last(value: Int): DeploymentsArgs = apply { addArg("last", value) }


    fun before(value: String): DeploymentsArgs = apply { addArg("before", value) }

  }

  class ForksArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ForksArgs = apply { addArg("first", value) }


    fun after(value: String): ForksArgs = apply { addArg("after", value) }


    fun last(value: Int): ForksArgs = apply { addArg("last", value) }


    fun before(value: String): ForksArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): ForksArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): ForksArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): ForksArgs =
          apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): ForksArgs = apply { addArg("isLocked", value) }

  }

  class IssueArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): IssueArgs = apply { addArg("number", value) }

  }

  class IssueOrPullRequestArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): IssueOrPullRequestArgs = apply { addArg("number", value) }

  }

  class IssuesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class LabelArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): LabelArgs = apply { addArg("name", value) }

  }

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }

  class LanguagesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LanguagesArgs = apply { addArg("first", value) }


    fun after(value: String): LanguagesArgs = apply { addArg("after", value) }


    fun last(value: Int): LanguagesArgs = apply { addArg("last", value) }


    fun before(value: String): LanguagesArgs = apply { addArg("before", value) }


    fun orderBy(value: LanguageOrder): LanguagesArgs = apply { addArg("orderBy", value) }

  }

  class MentionableUsersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): MentionableUsersArgs = apply { addArg("first", value) }


    fun after(value: String): MentionableUsersArgs = apply { addArg("after", value) }


    fun last(value: Int): MentionableUsersArgs = apply { addArg("last", value) }


    fun before(value: String): MentionableUsersArgs = apply { addArg("before", value) }

  }

  class MilestoneArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): MilestoneArgs = apply { addArg("number", value) }

  }

  class MilestonesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): MilestonesArgs = apply { addArg("first", value) }


    fun after(value: String): MilestonesArgs = apply { addArg("after", value) }


    fun last(value: Int): MilestonesArgs = apply { addArg("last", value) }


    fun before(value: String): MilestonesArgs = apply { addArg("before", value) }

  }

  class ObjectValArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun oid(value: GitObjectID): ObjectValArgs = apply { addArg("oid", value) }


    fun expression(value: String): ObjectValArgs = apply { addArg("expression", value) }

  }

  class ProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): ProjectArgs = apply { addArg("number", value) }

  }

  class ProjectsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProjectsArgs = apply { addArg("first", value) }


    fun after(value: String): ProjectsArgs = apply { addArg("after", value) }


    fun last(value: Int): ProjectsArgs = apply { addArg("last", value) }


    fun before(value: String): ProjectsArgs = apply { addArg("before", value) }


    fun orderBy(value: ProjectOrder): ProjectsArgs = apply { addArg("orderBy", value) }


    fun search(value: String): ProjectsArgs = apply { addArg("search", value) }


    fun states(value: ProjectState): ProjectsArgs = apply { addArg("states", value) }

  }

  class ProtectedBranchesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProtectedBranchesArgs = apply { addArg("first", value) }


    fun after(value: String): ProtectedBranchesArgs = apply { addArg("after", value) }


    fun last(value: Int): ProtectedBranchesArgs = apply { addArg("last", value) }


    fun before(value: String): ProtectedBranchesArgs = apply { addArg("before", value) }

  }

  class PullRequestArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): PullRequestArgs = apply { addArg("number", value) }

  }

  class PullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
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

  class RefArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun qualifiedName(value: String): RefArgs = apply { addArg("qualifiedName", value) }

  }

  class RefsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RefsArgs = apply { addArg("first", value) }


    fun after(value: String): RefsArgs = apply { addArg("after", value) }


    fun last(value: Int): RefsArgs = apply { addArg("last", value) }


    fun before(value: String): RefsArgs = apply { addArg("before", value) }


    fun refPrefix(value: String): RefsArgs = apply { addArg("refPrefix", value) }


    fun direction(value: OrderDirection): RefsArgs = apply { addArg("direction", value) }

  }

  class ReleasesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReleasesArgs = apply { addArg("first", value) }


    fun after(value: String): ReleasesArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleasesArgs = apply { addArg("last", value) }


    fun before(value: String): ReleasesArgs = apply { addArg("before", value) }

  }

  class RepositoryTopicsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoryTopicsArgs = apply { addArg("first", value) }


    fun after(value: String): RepositoryTopicsArgs = apply { addArg("after", value) }


    fun last(value: Int): RepositoryTopicsArgs = apply { addArg("last", value) }


    fun before(value: String): RepositoryTopicsArgs = apply { addArg("before", value) }

  }

  class StargazersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StargazersArgs = apply { addArg("first", value) }


    fun after(value: String): StargazersArgs = apply { addArg("after", value) }


    fun last(value: Int): StargazersArgs = apply { addArg("last", value) }


    fun before(value: String): StargazersArgs = apply { addArg("before", value) }


    fun orderBy(value: StarOrder): StargazersArgs = apply { addArg("orderBy", value) }

  }

  class WatchersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): WatchersArgs = apply { addArg("first", value) }


    fun after(value: String): WatchersArgs = apply { addArg("after", value) }


    fun last(value: Int): WatchersArgs = apply { addArg("last", value) }


    fun before(value: String): WatchersArgs = apply { addArg("before", value) }

  }
}

interface ReopenedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun <T : Closable> closable(init: () -> T): Stub<Closable, ArgBuilder> = stub()

  fun <T> closable(of: KFunction1<Closable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Closable, T>("closable", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()
}

interface RenamedTitleEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun currentTitle(): Stub<String, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun previousTitle(): Stub<String, ArgBuilder> = stub()

  fun <T : RenamedTitleSubject> subject(init: () -> T): Stub<RenamedTitleSubject, ArgBuilder> =
        stub()

  fun <T> subject(of: KFunction1<RenamedTitleSubject, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<RenamedTitleSubject, T>("subject", of)
}

interface RemovedFromProjectEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()
}

interface RemoveStarPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Starrable> starrable(init: () -> T): Stub<Starrable, ArgBuilder> = stub()

  fun <T> starrable(of: KFunction1<Starrable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Starrable, T>("starrable", of)
}

interface RemoveReactionPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Reaction> reaction(init: () -> T): Stub<Reaction, ArgBuilder> = stub()

  fun <T> reaction(of: KFunction1<Reaction, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Reaction, T>("reaction", of)

  fun <T : Reactable> subject(init: () -> T): Stub<Reactable, ArgBuilder> = stub()

  fun <T> subject(of: KFunction1<Reactable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Reactable, T>("subject", of)
}

interface RemoveOutsideCollaboratorPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : User> removedUser(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> removedUser(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("removedUser", of)
}

interface ReleaseEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Release> node(init: () -> T): NullableStub<Release, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Release, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Release, T>("node", of)
}

interface ReleaseConnection : QType {
  fun <T : ReleaseEdge> edges(init: () -> T): NullableStub<ReleaseEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<ReleaseEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReleaseEdge, T>("edges", of)

  fun <T : Release> nodes(init: () -> T): NullableStub<Release, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Release, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Release, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface ReleaseAssetEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : ReleaseAsset> node(init: () -> T): NullableStub<ReleaseAsset, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<ReleaseAsset, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReleaseAsset, T>("node", of)
}

interface ReleaseAssetConnection : QType {
  fun <T : ReleaseAssetEdge> edges(init: () -> T): NullableStub<ReleaseAssetEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<ReleaseAssetEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReleaseAssetEdge, T>("edges", of)

  fun <T : ReleaseAsset> nodes(init: () -> T): NullableStub<ReleaseAsset, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<ReleaseAsset, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReleaseAsset, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface ReleaseAsset : QType {
  fun id(): Stub<String, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun <T : Release> release(init: () -> T): NullableStub<Release, ArgBuilder> = nullableStub()

  fun <T> release(of: KFunction1<Release, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Release, T>("release", of)

  fun url(): Stub<URI, ArgBuilder> = stub()
}

interface Release : QType {
  fun description(): NullableStub<String, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun name(): NullableStub<String, ArgBuilder> = nullableStub()

  fun publishedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun <T : Ref> tag(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> tag(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("tag", of)

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun <T : ReleaseAssetConnection> releaseAsset(init: () -> T,
      argBuilder: ReleaseAssetArgs = ReleaseAssetArgs()): Stub<T, ReleaseAssetArgs> =
        configStub(init, argBuilder)

  fun <T : ReleaseAssetConnection> releaseAssets(init: () -> T,
      argBuilder: ReleaseAssetsArgs = ReleaseAssetsArgs()): Stub<T, ReleaseAssetsArgs> =
        configStub(init, argBuilder)

  class ReleaseAssetArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReleaseAssetArgs = apply { addArg("first", value) }


    fun after(value: String): ReleaseAssetArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleaseAssetArgs = apply { addArg("last", value) }


    fun before(value: String): ReleaseAssetArgs = apply { addArg("before", value) }


    fun name(value: String): ReleaseAssetArgs = apply { addArg("name", value) }

  }

  class ReleaseAssetsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReleaseAssetsArgs = apply { addArg("first", value) }


    fun after(value: String): ReleaseAssetsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleaseAssetsArgs = apply { addArg("last", value) }


    fun before(value: String): ReleaseAssetsArgs = apply { addArg("before", value) }

  }
}

interface ReferencedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun <T : Commit> commit(init: () -> T): NullableStub<Commit, ArgBuilder> = nullableStub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("commit", of)

  fun <T : Repository> commitRepository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> commitRepository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("commitRepository", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun isCrossReference(): Stub<Boolean, ArgBuilder> = stub()

  fun isCrossRepository(): Stub<Boolean, ArgBuilder> = stub()

  fun isDirectReference(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : ReferencedSubject> subject(init: () -> T): Stub<ReferencedSubject, ArgBuilder> = stub()

  fun <T> subject(of: KFunction1<ReferencedSubject, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReferencedSubject, T>("subject", of)
}

interface RefEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Ref> node(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("node", of)
}

interface RefConnection : QType {
  fun <T : RefEdge> edges(init: () -> T): NullableStub<RefEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<RefEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<RefEdge, T>("edges", of)

  fun <T : Ref> nodes(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface Ref : QType {
  fun id(): Stub<String, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun prefix(): Stub<String, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun <T : GitObject> target(init: () -> T): Stub<GitObject, ArgBuilder> = stub()

  fun <T> target(of: KFunction1<GitObject, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<GitObject, T>("target", of)

  fun <T : PullRequestConnection> associatedPullRequests(init: () -> T,
      argBuilder: AssociatedPullRequestsArgs = AssociatedPullRequestsArgs()): Stub<T, AssociatedPullRequestsArgs> =
        configStub(init, argBuilder)

  class AssociatedPullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssociatedPullRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): AssociatedPullRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): AssociatedPullRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): AssociatedPullRequestsArgs = apply { addArg("before", value) }


    fun states(value: PullRequestState): AssociatedPullRequestsArgs =
          apply { addArg("states", value) }

  }
}

interface ReactionGroup : QType {
  fun content(): Stub<ReactionContent, ArgBuilder> = stub()

  fun createdAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun <T : Reactable> subject(init: () -> T): Stub<Reactable, ArgBuilder> = stub()

  fun <T> subject(of: KFunction1<Reactable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Reactable, T>("subject", of)

  fun viewerHasReacted(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : ReactingUserConnection> users(init: () -> T,
      argBuilder: UsersArgs = UsersArgs()): Stub<T, UsersArgs> = configStub(init, argBuilder)

  class UsersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): UsersArgs = apply { addArg("first", value) }


    fun after(value: String): UsersArgs = apply { addArg("after", value) }


    fun last(value: Int): UsersArgs = apply { addArg("last", value) }


    fun before(value: String): UsersArgs = apply { addArg("before", value) }

  }
}

interface ReactionEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Reaction> node(init: () -> T): NullableStub<Reaction, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Reaction, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Reaction, T>("node", of)
}

interface ReactionConnection : QType {
  fun <T : ReactionEdge> edges(init: () -> T): NullableStub<ReactionEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<ReactionEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReactionEdge, T>("edges", of)

  fun <T : Reaction> nodes(init: () -> T): NullableStub<Reaction, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Reaction, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Reaction, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()

  fun viewerHasReacted(): Stub<Boolean, ArgBuilder> = stub()
}

interface Reaction : QType {
  fun content(): Stub<ReactionContent, ArgBuilder> = stub()

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : User> user(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> user(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("user", of)
}

interface ReactingUserEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : User> node(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> node(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("node", of)

  fun reactedAt(): Stub<DateTime, ArgBuilder> = stub()
}

interface ReactingUserConnection : QType {
  fun <T : ReactingUserEdge> edges(init: () -> T): NullableStub<ReactingUserEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<ReactingUserEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ReactingUserEdge, T>("edges", of)

  fun <T : User> nodes(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface RateLimit : QType {
  fun cost(): Stub<Int, ArgBuilder> = stub()

  fun limit(): Stub<Int, ArgBuilder> = stub()

  fun remaining(): Stub<Int, ArgBuilder> = stub()

  fun resetAt(): Stub<DateTime, ArgBuilder> = stub()
}

interface Query : QType {
  fun <T : CodeOfConduct> codesOfConduct(init: () -> T): NullableStub<CodeOfConduct, ArgBuilder> =
        nullableStub()

  fun <T> codesOfConduct(of: KFunction1<CodeOfConduct, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<CodeOfConduct, T>("codesOfConduct", of)

  fun <T : RateLimit> rateLimit(init: () -> T): NullableStub<RateLimit, ArgBuilder> = nullableStub()

  fun <T> rateLimit(of: KFunction1<RateLimit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<RateLimit, T>("rateLimit", of)

  fun <T : Query> relay(init: () -> T): Stub<Query, ArgBuilder> = stub()

  fun <T> relay(of: KFunction1<Query, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Query, T>("relay", of)

  fun <T : User> viewer(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> viewer(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("viewer", of)

  fun <T : CodeOfConduct> codeOfConduct(init: () -> T,
      argBuilder: CodeOfConductArgs = CodeOfConductArgs()): NullableStub<T, CodeOfConductArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : Node> node(init: () -> T, argBuilder: NodeArgs = NodeArgs()): NullableStub<T, NodeArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : Node> nodes(init: () -> T,
      argBuilder: NodesArgs = NodesArgs()): NullableStub<T, NodesArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : Organization> organization(init: () -> T,
      argBuilder: OrganizationArgs = OrganizationArgs()): NullableStub<T, OrganizationArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : Repository> repository(init: () -> T,
      argBuilder: RepositoryArgs = RepositoryArgs()): NullableStub<T, RepositoryArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : RepositoryOwner> repositoryOwner(init: () -> T,
      argBuilder: RepositoryOwnerArgs = RepositoryOwnerArgs()): NullableStub<T, RepositoryOwnerArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UniformResourceLocatable> resource(init: () -> T,
      argBuilder: ResourceArgs = ResourceArgs()): NullableStub<T, ResourceArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : SearchResultItemConnection> search(init: () -> T,
      argBuilder: SearchArgs = SearchArgs()): Stub<T, SearchArgs> = configStub(init, argBuilder)

  fun <T : Topic> topic(init: () -> T,
      argBuilder: TopicArgs = TopicArgs()): NullableStub<T, TopicArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : User> user(init: () -> T, argBuilder: UserArgs = UserArgs()): NullableStub<T, UserArgs> {
     return configNullableStub(init, argBuilder)
  }

  class CodeOfConductArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun key(value: String): CodeOfConductArgs = apply { addArg("key", value) }

  }

  class NodeArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun id(value: String): NodeArgs = apply { addArg("id", value) }

  }

  class NodesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun ids(value: String): NodesArgs = apply { addArg("ids", value) }

  }

  class OrganizationArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): OrganizationArgs = apply { addArg("login", value) }

  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun owner(value: String): RepositoryArgs = apply { addArg("owner", value) }


    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }

  class RepositoryOwnerArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): RepositoryOwnerArgs = apply { addArg("login", value) }

  }

  class ResourceArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun url(value: URI): ResourceArgs = apply { addArg("url", value) }

  }

  class SearchArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): SearchArgs = apply { addArg("first", value) }


    fun after(value: String): SearchArgs = apply { addArg("after", value) }


    fun last(value: Int): SearchArgs = apply { addArg("last", value) }


    fun before(value: String): SearchArgs = apply { addArg("before", value) }


    fun query(value: String): SearchArgs = apply { addArg("query", value) }


    fun type(value: SearchType): SearchArgs = apply { addArg("type", value) }

  }

  class TopicArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): TopicArgs = apply { addArg("name", value) }

  }

  class UserArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): UserArgs = apply { addArg("login", value) }

  }
}

interface PushAllowanceEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : PushAllowance> node(init: () -> T): NullableStub<PushAllowance, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<PushAllowance, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PushAllowance, T>("node", of)
}

interface PushAllowanceConnection : QType {
  fun <T : PushAllowanceEdge> edges(init: () -> T): NullableStub<PushAllowanceEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<PushAllowanceEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PushAllowanceEdge, T>("edges", of)

  fun <T : PushAllowance> nodes(init: () -> T): NullableStub<PushAllowance, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<PushAllowance, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PushAllowance, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface PushAllowance : QType {
  fun <T : PushAllowanceActor> actor(init: () -> T): NullableStub<PushAllowanceActor, ArgBuilder> =
        nullableStub()

  fun <T> actor(of: KFunction1<PushAllowanceActor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PushAllowanceActor, T>("actor", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : ProtectedBranch> protectedBranch(init: () -> T): Stub<ProtectedBranch, ArgBuilder> =
        stub()

  fun <T> protectedBranch(of: KFunction1<ProtectedBranch, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProtectedBranch, T>("protectedBranch", of)
}

interface PullRequestTimelineItemEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequestTimelineItem> node(init: () -> T): NullableStub<PullRequestTimelineItem, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<PullRequestTimelineItem, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestTimelineItem, T>("node", of)
}

interface PullRequestTimelineConnection : QType {
  fun <T : PullRequestTimelineItemEdge> edges(init: () -> T): NullableStub<PullRequestTimelineItemEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<PullRequestTimelineItemEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestTimelineItemEdge, T>("edges", of)

  fun <T : PullRequestTimelineItem> nodes(init: () -> T): NullableStub<PullRequestTimelineItem, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<PullRequestTimelineItem, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestTimelineItem, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface PullRequestReviewThread : QType {
  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : PullRequestReviewCommentConnection> comments(init: () -> T,
      argBuilder: CommentsArgs = CommentsArgs()): Stub<T, CommentsArgs> =
        configStub(init, argBuilder)

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

interface PullRequestReviewEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequestReview> node(init: () -> T): NullableStub<PullRequestReview, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<PullRequestReview, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestReview, T>("node", of)
}

interface PullRequestReviewConnection : QType {
  fun <T : PullRequestReviewEdge> edges(init: () -> T): NullableStub<PullRequestReviewEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<PullRequestReviewEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestReviewEdge, T>("edges", of)

  fun <T : PullRequestReview> nodes(init: () -> T): NullableStub<PullRequestReview, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<PullRequestReview, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestReview, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface PullRequestReviewCommentEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequestReviewComment> node(init: () -> T): NullableStub<PullRequestReviewComment, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<PullRequestReviewComment, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestReviewComment, T>("node", of)
}

interface PullRequestReviewCommentConnection : QType {
  fun <T : PullRequestReviewCommentEdge> edges(init: () -> T): NullableStub<PullRequestReviewCommentEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<PullRequestReviewCommentEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestReviewCommentEdge, T>("edges", of)

  fun <T : PullRequestReviewComment> nodes(init: () -> T): NullableStub<PullRequestReviewComment, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<PullRequestReviewComment, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestReviewComment, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface PullRequestReviewComment : QType {
  fun <T : Actor> author(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> author(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation, ArgBuilder> = stub()

  fun body(): Stub<String, ArgBuilder> = stub()

  fun bodyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun bodyText(): Stub<String, ArgBuilder> = stub()

  fun <T : Commit> commit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun createdViaEmail(): Stub<Boolean, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun diffHunk(): Stub<String, ArgBuilder> = stub()

  fun draftedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun <T : Actor> editor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> editor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("editor", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun lastEditedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun <T : Commit> originalCommit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> originalCommit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("originalCommit", of)

  fun originalPosition(): Stub<Int, ArgBuilder> = stub()

  fun path(): Stub<String, ArgBuilder> = stub()

  fun position(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun publishedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): NullableStub<PullRequestReview, ArgBuilder> =
        nullableStub()

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestReview, T>("pullRequestReview", of)

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup, ArgBuilder> = stub()

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun viewerCanDelete(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanReact(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanUpdate(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason, ArgBuilder> = stub()

  fun viewerDidAuthor(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : ReactionConnection> reactions(init: () -> T,
      argBuilder: ReactionsArgs = ReactionsArgs()): Stub<T, ReactionsArgs> =
        configStub(init, argBuilder)

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

interface PullRequestReview : QType {
  fun <T : Actor> author(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> author(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation, ArgBuilder> = stub()

  fun body(): Stub<String, ArgBuilder> = stub()

  fun bodyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun bodyText(): Stub<String, ArgBuilder> = stub()

  fun <T : Commit> commit(init: () -> T): NullableStub<Commit, ArgBuilder> = nullableStub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun createdViaEmail(): Stub<Boolean, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun <T : Actor> editor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> editor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("editor", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun lastEditedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun publishedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun state(): Stub<PullRequestReviewState, ArgBuilder> = stub()

  fun submittedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun viewerCanDelete(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanUpdate(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason, ArgBuilder> = stub()

  fun viewerDidAuthor(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : PullRequestReviewCommentConnection> comments(init: () -> T,
      argBuilder: CommentsArgs = CommentsArgs()): Stub<T, CommentsArgs> =
        configStub(init, argBuilder)

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

interface PullRequestEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> node(init: () -> T): NullableStub<PullRequest, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequest, T>("node", of)
}

interface PullRequestConnection : QType {
  fun <T : PullRequestEdge> edges(init: () -> T): NullableStub<PullRequestEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<PullRequestEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestEdge, T>("edges", of)

  fun <T : PullRequest> nodes(init: () -> T): NullableStub<PullRequest, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequest, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface PullRequestCommitEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequestCommit> node(init: () -> T): NullableStub<PullRequestCommit, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<PullRequestCommit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestCommit, T>("node", of)
}

interface PullRequestCommitConnection : QType {
  fun <T : PullRequestCommitEdge> edges(init: () -> T): NullableStub<PullRequestCommitEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<PullRequestCommitEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestCommitEdge, T>("edges", of)

  fun <T : PullRequestCommit> nodes(init: () -> T): NullableStub<PullRequestCommit, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<PullRequestCommit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<PullRequestCommit, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface PullRequestCommit : QType {
  fun <T : Commit> commit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("commit", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()
}

interface PullRequest : QType {
  fun <T : Actor> author(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> author(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation, ArgBuilder> = stub()

  fun <T : Ref> baseRef(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> baseRef(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("baseRef", of)

  fun baseRefName(): Stub<String, ArgBuilder> = stub()

  fun body(): Stub<String, ArgBuilder> = stub()

  fun bodyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun bodyText(): Stub<String, ArgBuilder> = stub()

  fun closed(): Stub<Boolean, ArgBuilder> = stub()

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun createdViaEmail(): Stub<Boolean, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun <T : Actor> editor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> editor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("editor", of)

  fun <T : Ref> headRef(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> headRef(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("headRef", of)

  fun headRefName(): Stub<String, ArgBuilder> = stub()

  fun <T : Repository> headRepository(init: () -> T): NullableStub<Repository, ArgBuilder> =
        nullableStub()

  fun <T> headRepository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Repository, T>("headRepository", of)

  fun <T : RepositoryOwner> headRepositoryOwner(init: () -> T): NullableStub<RepositoryOwner, ArgBuilder> =
        nullableStub()

  fun <T> headRepositoryOwner(of: KFunction1<RepositoryOwner, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<RepositoryOwner, T>("headRepositoryOwner", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun isCrossRepository(): Stub<Boolean, ArgBuilder> = stub()

  fun lastEditedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun locked(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : Commit> mergeCommit(init: () -> T): NullableStub<Commit, ArgBuilder> = nullableStub()

  fun <T> mergeCommit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("mergeCommit", of)

  fun mergeable(): Stub<MergeableState, ArgBuilder> = stub()

  fun merged(): Stub<Boolean, ArgBuilder> = stub()

  fun mergedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun number(): Stub<Int, ArgBuilder> = stub()

  fun <T : Commit> potentialMergeCommit(init: () -> T): NullableStub<Commit, ArgBuilder> =
        nullableStub()

  fun <T> potentialMergeCommit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("potentialMergeCommit", of)

  fun publishedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup, ArgBuilder> = stub()

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun revertResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun revertUrl(): Stub<URI, ArgBuilder> = stub()

  fun state(): Stub<PullRequestState, ArgBuilder> = stub()

  fun <T : SuggestedReviewer> suggestedReviewers(init: () -> T): NullableStub<SuggestedReviewer, ArgBuilder> =
        nullableStub()

  fun <T> suggestedReviewers(of: KFunction1<SuggestedReviewer, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<SuggestedReviewer, T>("suggestedReviewers", of)

  fun title(): Stub<String, ArgBuilder> = stub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun viewerCanReact(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanSubscribe(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanUpdate(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason, ArgBuilder> = stub()

  fun viewerDidAuthor(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerSubscription(): Stub<SubscriptionState, ArgBuilder> = stub()

  fun <T : UserConnection> assignees(init: () -> T,
      argBuilder: AssigneesArgs = AssigneesArgs()): Stub<T, AssigneesArgs> =
        configStub(init, argBuilder)

  fun <T : IssueCommentConnection> comments(init: () -> T,
      argBuilder: CommentsArgs = CommentsArgs()): Stub<T, CommentsArgs> =
        configStub(init, argBuilder)

  fun <T : PullRequestCommitConnection> commits(init: () -> T,
      argBuilder: CommitsArgs = CommitsArgs()): Stub<T, CommitsArgs> = configStub(init, argBuilder)

  fun <T : LabelConnection> labels(init: () -> T,
      argBuilder: LabelsArgs = LabelsArgs()): NullableStub<T, LabelsArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UserConnection> participants(init: () -> T,
      argBuilder: ParticipantsArgs = ParticipantsArgs()): Stub<T, ParticipantsArgs> =
        configStub(init, argBuilder)

  fun <T : ReactionConnection> reactions(init: () -> T,
      argBuilder: ReactionsArgs = ReactionsArgs()): Stub<T, ReactionsArgs> =
        configStub(init, argBuilder)

  fun <T : ReviewRequestConnection> reviewRequests(init: () -> T,
      argBuilder: ReviewRequestsArgs = ReviewRequestsArgs()): NullableStub<T, ReviewRequestsArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : PullRequestReviewConnection> reviews(init: () -> T,
      argBuilder: ReviewsArgs = ReviewsArgs()): NullableStub<T, ReviewsArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : PullRequestTimelineConnection> timeline(init: () -> T,
      argBuilder: TimelineArgs = TimelineArgs()): Stub<T, TimelineArgs> =
        configStub(init, argBuilder)

  class AssigneesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssigneesArgs = apply { addArg("first", value) }


    fun after(value: String): AssigneesArgs = apply { addArg("after", value) }


    fun last(value: Int): AssigneesArgs = apply { addArg("last", value) }


    fun before(value: String): AssigneesArgs = apply { addArg("before", value) }

  }

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class CommitsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommitsArgs = apply { addArg("first", value) }


    fun after(value: String): CommitsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommitsArgs = apply { addArg("last", value) }


    fun before(value: String): CommitsArgs = apply { addArg("before", value) }

  }

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }

  class ParticipantsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ParticipantsArgs = apply { addArg("first", value) }


    fun after(value: String): ParticipantsArgs = apply { addArg("after", value) }


    fun last(value: Int): ParticipantsArgs = apply { addArg("last", value) }


    fun before(value: String): ParticipantsArgs = apply { addArg("before", value) }

  }

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }

  class ReviewRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReviewRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewRequestsArgs = apply { addArg("before", value) }

  }

  class ReviewsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReviewsArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewsArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewsArgs = apply { addArg("before", value) }


    fun states(value: PullRequestReviewState): ReviewsArgs = apply { addArg("states", value) }

  }

  class TimelineArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): TimelineArgs = apply { addArg("first", value) }


    fun after(value: String): TimelineArgs = apply { addArg("after", value) }


    fun last(value: Int): TimelineArgs = apply { addArg("last", value) }


    fun before(value: String): TimelineArgs = apply { addArg("before", value) }


    fun since(value: DateTime): TimelineArgs = apply { addArg("since", value) }

  }
}

interface ProtectedBranchEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : ProtectedBranch> node(init: () -> T): NullableStub<ProtectedBranch, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<ProtectedBranch, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProtectedBranch, T>("node", of)
}

interface ProtectedBranchConnection : QType {
  fun <T : ProtectedBranchEdge> edges(init: () -> T): NullableStub<ProtectedBranchEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<ProtectedBranchEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProtectedBranchEdge, T>("edges", of)

  fun <T : ProtectedBranch> nodes(init: () -> T): NullableStub<ProtectedBranch, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<ProtectedBranch, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProtectedBranch, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface ProtectedBranch : QType {
  fun <T : Actor> creator(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> creator(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("creator", of)

  fun hasDismissableStaleReviews(): Stub<Boolean, ArgBuilder> = stub()

  fun hasRequiredReviews(): Stub<Boolean, ArgBuilder> = stub()

  fun hasRequiredStatusChecks(): Stub<Boolean, ArgBuilder> = stub()

  fun hasRestrictedPushes(): Stub<Boolean, ArgBuilder> = stub()

  fun hasRestrictedReviewDismissals(): Stub<Boolean, ArgBuilder> = stub()

  fun hasStrictRequiredStatusChecks(): Stub<Boolean, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun isAdminEnforced(): Stub<Boolean, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun requiredStatusCheckContexts(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : PushAllowanceConnection> pushAllowances(init: () -> T,
      argBuilder: PushAllowancesArgs = PushAllowancesArgs()): Stub<T, PushAllowancesArgs> =
        configStub(init, argBuilder)

  fun <T : ReviewDismissalAllowanceConnection> reviewDismissalAllowances(init: () -> T,
      argBuilder: ReviewDismissalAllowancesArgs = ReviewDismissalAllowancesArgs()): Stub<T, ReviewDismissalAllowancesArgs> =
        configStub(init, argBuilder)

  class PushAllowancesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PushAllowancesArgs = apply { addArg("first", value) }


    fun after(value: String): PushAllowancesArgs = apply { addArg("after", value) }


    fun last(value: Int): PushAllowancesArgs = apply { addArg("last", value) }


    fun before(value: String): PushAllowancesArgs = apply { addArg("before", value) }

  }

  class ReviewDismissalAllowancesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReviewDismissalAllowancesArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewDismissalAllowancesArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewDismissalAllowancesArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewDismissalAllowancesArgs = apply { addArg("before", value) }

  }
}

interface ProjectEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Project> node(init: () -> T): NullableStub<Project, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Project, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Project, T>("node", of)
}

interface ProjectConnection : QType {
  fun <T : ProjectEdge> edges(init: () -> T): NullableStub<ProjectEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<ProjectEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProjectEdge, T>("edges", of)

  fun <T : Project> nodes(init: () -> T): NullableStub<Project, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Project, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Project, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface ProjectColumnEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : ProjectColumn> node(init: () -> T): NullableStub<ProjectColumn, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<ProjectColumn, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProjectColumn, T>("node", of)
}

interface ProjectColumnConnection : QType {
  fun <T : ProjectColumnEdge> edges(init: () -> T): NullableStub<ProjectColumnEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<ProjectColumnEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProjectColumnEdge, T>("edges", of)

  fun <T : ProjectColumn> nodes(init: () -> T): NullableStub<ProjectColumn, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<ProjectColumn, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProjectColumn, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface ProjectColumn : QType {
  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun <T : Project> project(init: () -> T): Stub<Project, ArgBuilder> = stub()

  fun <T> project(of: KFunction1<Project, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Project, T>("project", of)

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun <T : ProjectCardConnection> cards(init: () -> T,
      argBuilder: CardsArgs = CardsArgs()): Stub<T, CardsArgs> = configStub(init, argBuilder)

  class CardsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CardsArgs = apply { addArg("first", value) }


    fun after(value: String): CardsArgs = apply { addArg("after", value) }


    fun last(value: Int): CardsArgs = apply { addArg("last", value) }


    fun before(value: String): CardsArgs = apply { addArg("before", value) }

  }
}

interface ProjectCardEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : ProjectCard> node(init: () -> T): NullableStub<ProjectCard, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<ProjectCard, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProjectCard, T>("node", of)
}

interface ProjectCardConnection : QType {
  fun <T : ProjectCardEdge> edges(init: () -> T): NullableStub<ProjectCardEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<ProjectCardEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProjectCardEdge, T>("edges", of)

  fun <T : ProjectCard> nodes(init: () -> T): NullableStub<ProjectCard, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<ProjectCard, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProjectCard, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface ProjectCard : QType {
  fun <T : ProjectColumn> column(init: () -> T): NullableStub<ProjectColumn, ArgBuilder> =
        nullableStub()

  fun <T> column(of: KFunction1<ProjectColumn, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProjectColumn, T>("column", of)

  fun <T : ProjectCardItem> content(init: () -> T): NullableStub<ProjectCardItem, ArgBuilder> =
        nullableStub()

  fun <T> content(of: KFunction1<ProjectCardItem, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ProjectCardItem, T>("content", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun <T : Actor> creator(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> creator(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("creator", of)

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun note(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Project> project(init: () -> T): Stub<Project, ArgBuilder> = stub()

  fun <T> project(of: KFunction1<Project, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Project, T>("project", of)

  fun <T : ProjectColumn> projectColumn(init: () -> T): Stub<ProjectColumn, ArgBuilder> = stub()

  fun <T> projectColumn(of: KFunction1<ProjectColumn, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProjectColumn, T>("projectColumn", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun state(): NullableStub<ProjectCardState, ArgBuilder> = nullableStub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()
}

interface Project : QType {
  fun body(): NullableStub<String, ArgBuilder> = nullableStub()

  fun bodyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun closedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun <T : Actor> creator(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> creator(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("creator", of)

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun number(): Stub<Int, ArgBuilder> = stub()

  fun <T : ProjectOwner> owner(init: () -> T): Stub<ProjectOwner, ArgBuilder> = stub()

  fun <T> owner(of: KFunction1<ProjectOwner, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProjectOwner, T>("owner", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun state(): Stub<ProjectState, ArgBuilder> = stub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun viewerCanUpdate(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : ProjectColumnConnection> columns(init: () -> T,
      argBuilder: ColumnsArgs = ColumnsArgs()): Stub<T, ColumnsArgs> = configStub(init, argBuilder)

  class ColumnsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ColumnsArgs = apply { addArg("first", value) }


    fun after(value: String): ColumnsArgs = apply { addArg("after", value) }


    fun last(value: Int): ColumnsArgs = apply { addArg("last", value) }


    fun before(value: String): ColumnsArgs = apply { addArg("before", value) }

  }
}

interface PageInfo : QType {
  fun endCursor(): NullableStub<String, ArgBuilder> = nullableStub()

  fun hasNextPage(): Stub<Boolean, ArgBuilder> = stub()

  fun hasPreviousPage(): Stub<Boolean, ArgBuilder> = stub()

  fun startCursor(): NullableStub<String, ArgBuilder> = nullableStub()
}

interface OrganizationInvitationEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : OrganizationInvitation> node(init: () -> T): NullableStub<OrganizationInvitation, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<OrganizationInvitation, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<OrganizationInvitation, T>("node", of)
}

interface OrganizationInvitationConnection : QType {
  fun <T : OrganizationInvitationEdge> edges(init: () -> T): NullableStub<OrganizationInvitationEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<OrganizationInvitationEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<OrganizationInvitationEdge, T>("edges", of)

  fun <T : OrganizationInvitation> nodes(init: () -> T): NullableStub<OrganizationInvitation, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<OrganizationInvitation, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<OrganizationInvitation, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface OrganizationInvitation : QType {
  fun email(): NullableStub<String, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : User> invitee(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> invitee(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("invitee", of)

  fun <T : User> inviter(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> inviter(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("inviter", of)

  fun role(): Stub<OrganizationInvitationRole, ArgBuilder> = stub()
}

interface OrganizationIdentityProvider : QType {
  fun digestMethod(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun idpCertificate(): NullableStub<X509Certificate, ArgBuilder> = nullableStub()

  fun issuer(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Organization> organization(init: () -> T): NullableStub<Organization, ArgBuilder> =
        nullableStub()

  fun <T> organization(of: KFunction1<Organization, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Organization, T>("organization", of)

  fun signatureMethod(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun ssoUrl(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun <T : ExternalIdentityConnection> externalIdentities(init: () -> T,
      argBuilder: ExternalIdentitiesArgs = ExternalIdentitiesArgs()): Stub<T, ExternalIdentitiesArgs> =
        configStub(init, argBuilder)

  class ExternalIdentitiesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ExternalIdentitiesArgs = apply { addArg("first", value) }


    fun after(value: String): ExternalIdentitiesArgs = apply { addArg("after", value) }


    fun last(value: Int): ExternalIdentitiesArgs = apply { addArg("last", value) }


    fun before(value: String): ExternalIdentitiesArgs = apply { addArg("before", value) }

  }
}

interface OrganizationEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Organization> node(init: () -> T): NullableStub<Organization, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<Organization, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Organization, T>("node", of)
}

interface OrganizationConnection : QType {
  fun <T : OrganizationEdge> edges(init: () -> T): NullableStub<OrganizationEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<OrganizationEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<OrganizationEdge, T>("edges", of)

  fun <T : Organization> nodes(init: () -> T): NullableStub<Organization, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<Organization, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Organization, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface Organization : QType {
  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun isInvoiced(): Stub<Boolean, ArgBuilder> = stub()

  fun login(): Stub<String, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun newTeamResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun newTeamUrl(): Stub<URI, ArgBuilder> = stub()

  fun organizationBillingEmail(): NullableStub<String, ArgBuilder> = nullableStub()

  fun projectsResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun projectsUrl(): Stub<URI, ArgBuilder> = stub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun <T : OrganizationIdentityProvider> samlIdentityProvider(init: () -> T): NullableStub<OrganizationIdentityProvider, ArgBuilder> =
        nullableStub()

  fun <T> samlIdentityProvider(of: KFunction1<OrganizationIdentityProvider, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<OrganizationIdentityProvider, T>("samlIdentityProvider", of)

  fun teamsResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun teamsUrl(): Stub<URI, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun viewerCanAdminister(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanCreateProjects(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanCreateRepositories(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanCreateTeams(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerIsAMember(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : URI> avatarUrl(init: () -> T,
      argBuilder: AvatarUrlArgs = AvatarUrlArgs()): Stub<T, AvatarUrlArgs> =
        configStub(init, argBuilder)

  fun <T : UserConnection> members(init: () -> T,
      argBuilder: MembersArgs = MembersArgs()): Stub<T, MembersArgs> = configStub(init, argBuilder)

  fun <T : RepositoryConnection> pinnedRepositories(init: () -> T,
      argBuilder: PinnedRepositoriesArgs = PinnedRepositoriesArgs()): Stub<T, PinnedRepositoriesArgs> =
        configStub(init, argBuilder)

  fun <T : Project> project(init: () -> T,
      argBuilder: ProjectArgs = ProjectArgs()): NullableStub<T, ProjectArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : ProjectConnection> projects(init: () -> T,
      argBuilder: ProjectsArgs = ProjectsArgs()): Stub<T, ProjectsArgs> =
        configStub(init, argBuilder)

  fun <T : RepositoryConnection> repositories(init: () -> T,
      argBuilder: RepositoriesArgs = RepositoriesArgs()): Stub<T, RepositoriesArgs> =
        configStub(init, argBuilder)

  fun <T : Repository> repository(init: () -> T,
      argBuilder: RepositoryArgs = RepositoryArgs()): NullableStub<T, RepositoryArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : Team> team(init: () -> T, argBuilder: TeamArgs = TeamArgs()): NullableStub<T, TeamArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : TeamConnection> teams(init: () -> T,
      argBuilder: TeamsArgs = TeamsArgs()): Stub<T, TeamsArgs> = configStub(init, argBuilder)

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }

  class MembersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): MembersArgs = apply { addArg("first", value) }


    fun after(value: String): MembersArgs = apply { addArg("after", value) }


    fun last(value: Int): MembersArgs = apply { addArg("last", value) }


    fun before(value: String): MembersArgs = apply { addArg("before", value) }

  }

  class PinnedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PinnedRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): PinnedRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): PinnedRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): PinnedRepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs =
          apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs =
          apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): PinnedRepositoriesArgs = apply { addArg("isLocked", value) }

  }

  class ProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): ProjectArgs = apply { addArg("number", value) }

  }

  class ProjectsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProjectsArgs = apply { addArg("first", value) }


    fun after(value: String): ProjectsArgs = apply { addArg("after", value) }


    fun last(value: Int): ProjectsArgs = apply { addArg("last", value) }


    fun before(value: String): ProjectsArgs = apply { addArg("before", value) }


    fun orderBy(value: ProjectOrder): ProjectsArgs = apply { addArg("orderBy", value) }


    fun search(value: String): ProjectsArgs = apply { addArg("search", value) }


    fun states(value: ProjectState): ProjectsArgs = apply { addArg("states", value) }

  }

  class RepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): RepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): RepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): RepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): RepositoriesArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): RepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): RepositoriesArgs =
          apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): RepositoriesArgs = apply { addArg("isLocked", value) }


    fun isFork(value: Boolean): RepositoriesArgs = apply { addArg("isFork", value) }

  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }

  class TeamArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun slug(value: String): TeamArgs = apply { addArg("slug", value) }

  }

  class TeamsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
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

interface Mutation : QType {
  fun <T : AcceptTopicSuggestionPayload> acceptTopicSuggestion(init: () -> T,
      argBuilder: AcceptTopicSuggestionArgs = AcceptTopicSuggestionArgs()): NullableStub<T, AcceptTopicSuggestionArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : AddCommentPayload> addComment(init: () -> T,
      argBuilder: AddCommentArgs = AddCommentArgs()): NullableStub<T, AddCommentArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : AddProjectCardPayload> addProjectCard(init: () -> T,
      argBuilder: AddProjectCardArgs = AddProjectCardArgs()): NullableStub<T, AddProjectCardArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : AddProjectColumnPayload> addProjectColumn(init: () -> T,
      argBuilder: AddProjectColumnArgs = AddProjectColumnArgs()): NullableStub<T, AddProjectColumnArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : AddPullRequestReviewPayload> addPullRequestReview(init: () -> T,
      argBuilder: AddPullRequestReviewArgs = AddPullRequestReviewArgs()): NullableStub<T, AddPullRequestReviewArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : AddPullRequestReviewCommentPayload> addPullRequestReviewComment(init: () -> T,
      argBuilder: AddPullRequestReviewCommentArgs = AddPullRequestReviewCommentArgs()): NullableStub<T, AddPullRequestReviewCommentArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : AddReactionPayload> addReaction(init: () -> T,
      argBuilder: AddReactionArgs = AddReactionArgs()): NullableStub<T, AddReactionArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : AddStarPayload> addStar(init: () -> T,
      argBuilder: AddStarArgs = AddStarArgs()): NullableStub<T, AddStarArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : CreateProjectPayload> createProject(init: () -> T,
      argBuilder: CreateProjectArgs = CreateProjectArgs()): NullableStub<T, CreateProjectArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : DeclineTopicSuggestionPayload> declineTopicSuggestion(init: () -> T,
      argBuilder: DeclineTopicSuggestionArgs = DeclineTopicSuggestionArgs()): NullableStub<T, DeclineTopicSuggestionArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : DeleteProjectPayload> deleteProject(init: () -> T,
      argBuilder: DeleteProjectArgs = DeleteProjectArgs()): NullableStub<T, DeleteProjectArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : DeleteProjectCardPayload> deleteProjectCard(init: () -> T,
      argBuilder: DeleteProjectCardArgs = DeleteProjectCardArgs()): NullableStub<T, DeleteProjectCardArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : DeleteProjectColumnPayload> deleteProjectColumn(init: () -> T,
      argBuilder: DeleteProjectColumnArgs = DeleteProjectColumnArgs()): NullableStub<T, DeleteProjectColumnArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : DeletePullRequestReviewPayload> deletePullRequestReview(init: () -> T,
      argBuilder: DeletePullRequestReviewArgs = DeletePullRequestReviewArgs()): NullableStub<T, DeletePullRequestReviewArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : DismissPullRequestReviewPayload> dismissPullRequestReview(init: () -> T,
      argBuilder: DismissPullRequestReviewArgs = DismissPullRequestReviewArgs()): NullableStub<T, DismissPullRequestReviewArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : MoveProjectCardPayload> moveProjectCard(init: () -> T,
      argBuilder: MoveProjectCardArgs = MoveProjectCardArgs()): NullableStub<T, MoveProjectCardArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : MoveProjectColumnPayload> moveProjectColumn(init: () -> T,
      argBuilder: MoveProjectColumnArgs = MoveProjectColumnArgs()): NullableStub<T, MoveProjectColumnArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : RemoveOutsideCollaboratorPayload> removeOutsideCollaborator(init: () -> T,
      argBuilder: RemoveOutsideCollaboratorArgs = RemoveOutsideCollaboratorArgs()): NullableStub<T, RemoveOutsideCollaboratorArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : RemoveReactionPayload> removeReaction(init: () -> T,
      argBuilder: RemoveReactionArgs = RemoveReactionArgs()): NullableStub<T, RemoveReactionArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : RemoveStarPayload> removeStar(init: () -> T,
      argBuilder: RemoveStarArgs = RemoveStarArgs()): NullableStub<T, RemoveStarArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : RequestReviewsPayload> requestReviews(init: () -> T,
      argBuilder: RequestReviewsArgs = RequestReviewsArgs()): NullableStub<T, RequestReviewsArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : SubmitPullRequestReviewPayload> submitPullRequestReview(init: () -> T,
      argBuilder: SubmitPullRequestReviewArgs = SubmitPullRequestReviewArgs()): NullableStub<T, SubmitPullRequestReviewArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UpdateProjectPayload> updateProject(init: () -> T,
      argBuilder: UpdateProjectArgs = UpdateProjectArgs()): NullableStub<T, UpdateProjectArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UpdateProjectCardPayload> updateProjectCard(init: () -> T,
      argBuilder: UpdateProjectCardArgs = UpdateProjectCardArgs()): NullableStub<T, UpdateProjectCardArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UpdateProjectColumnPayload> updateProjectColumn(init: () -> T,
      argBuilder: UpdateProjectColumnArgs = UpdateProjectColumnArgs()): NullableStub<T, UpdateProjectColumnArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UpdatePullRequestReviewPayload> updatePullRequestReview(init: () -> T,
      argBuilder: UpdatePullRequestReviewArgs = UpdatePullRequestReviewArgs()): NullableStub<T, UpdatePullRequestReviewArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UpdatePullRequestReviewCommentPayload> updatePullRequestReviewComment(init: () -> T,
      argBuilder: UpdatePullRequestReviewCommentArgs = UpdatePullRequestReviewCommentArgs()): NullableStub<T, UpdatePullRequestReviewCommentArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UpdateSubscriptionPayload> updateSubscription(init: () -> T,
      argBuilder: UpdateSubscriptionArgs = UpdateSubscriptionArgs()): NullableStub<T, UpdateSubscriptionArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UpdateTopicsPayload> updateTopics(init: () -> T,
      argBuilder: UpdateTopicsArgs = UpdateTopicsArgs()): NullableStub<T, UpdateTopicsArgs> {
     return configNullableStub(init, argBuilder)
  }

  class AcceptTopicSuggestionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AcceptTopicSuggestionInput): AcceptTopicSuggestionArgs =
          apply { addArg("input", value) }

  }

  class AddCommentArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddCommentInput): AddCommentArgs = apply { addArg("input", value) }

  }

  class AddProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddProjectCardInput): AddProjectCardArgs = apply { addArg("input", value) }

  }

  class AddProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddProjectColumnInput): AddProjectColumnArgs = apply { addArg("input", value) }

  }

  class AddPullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddPullRequestReviewInput): AddPullRequestReviewArgs =
          apply { addArg("input", value) }

  }

  class AddPullRequestReviewCommentArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddPullRequestReviewCommentInput): AddPullRequestReviewCommentArgs =
          apply { addArg("input", value) }

  }

  class AddReactionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddReactionInput): AddReactionArgs = apply { addArg("input", value) }

  }

  class AddStarArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddStarInput): AddStarArgs = apply { addArg("input", value) }

  }

  class CreateProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: CreateProjectInput): CreateProjectArgs = apply { addArg("input", value) }

  }

  class DeclineTopicSuggestionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeclineTopicSuggestionInput): DeclineTopicSuggestionArgs =
          apply { addArg("input", value) }

  }

  class DeleteProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeleteProjectInput): DeleteProjectArgs = apply { addArg("input", value) }

  }

  class DeleteProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeleteProjectCardInput): DeleteProjectCardArgs =
          apply { addArg("input", value) }

  }

  class DeleteProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeleteProjectColumnInput): DeleteProjectColumnArgs =
          apply { addArg("input", value) }

  }

  class DeletePullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeletePullRequestReviewInput): DeletePullRequestReviewArgs =
          apply { addArg("input", value) }

  }

  class DismissPullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DismissPullRequestReviewInput): DismissPullRequestReviewArgs =
          apply { addArg("input", value) }

  }

  class MoveProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: MoveProjectCardInput): MoveProjectCardArgs = apply { addArg("input", value) }

  }

  class MoveProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: MoveProjectColumnInput): MoveProjectColumnArgs =
          apply { addArg("input", value) }

  }

  class RemoveOutsideCollaboratorArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RemoveOutsideCollaboratorInput): RemoveOutsideCollaboratorArgs =
          apply { addArg("input", value) }

  }

  class RemoveReactionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RemoveReactionInput): RemoveReactionArgs = apply { addArg("input", value) }

  }

  class RemoveStarArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RemoveStarInput): RemoveStarArgs = apply { addArg("input", value) }

  }

  class RequestReviewsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RequestReviewsInput): RequestReviewsArgs = apply { addArg("input", value) }

  }

  class SubmitPullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: SubmitPullRequestReviewInput): SubmitPullRequestReviewArgs =
          apply { addArg("input", value) }

  }

  class UpdateProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateProjectInput): UpdateProjectArgs = apply { addArg("input", value) }

  }

  class UpdateProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateProjectCardInput): UpdateProjectCardArgs =
          apply { addArg("input", value) }

  }

  class UpdateProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateProjectColumnInput): UpdateProjectColumnArgs =
          apply { addArg("input", value) }

  }

  class UpdatePullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdatePullRequestReviewInput): UpdatePullRequestReviewArgs =
          apply { addArg("input", value) }

  }

  class UpdatePullRequestReviewCommentArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdatePullRequestReviewCommentInput): UpdatePullRequestReviewCommentArgs =
          apply { addArg("input", value) }

  }

  class UpdateSubscriptionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateSubscriptionInput): UpdateSubscriptionArgs =
          apply { addArg("input", value) }

  }

  class UpdateTopicsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateTopicsInput): UpdateTopicsArgs = apply { addArg("input", value) }

  }
}

interface MovedColumnsInProjectEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()
}

interface MoveProjectColumnPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : ProjectColumnEdge> columnEdge(init: () -> T): Stub<ProjectColumnEdge, ArgBuilder> =
        stub()

  fun <T> columnEdge(of: KFunction1<ProjectColumnEdge, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProjectColumnEdge, T>("columnEdge", of)
}

interface MoveProjectCardPayload : QType {
  fun <T : ProjectCardEdge> cardEdge(init: () -> T): Stub<ProjectCardEdge, ArgBuilder> = stub()

  fun <T> cardEdge(of: KFunction1<ProjectCardEdge, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProjectCardEdge, T>("cardEdge", of)

  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()
}

interface MilestonedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun milestoneTitle(): Stub<String, ArgBuilder> = stub()

  fun <T : MilestoneItem> subject(init: () -> T): Stub<MilestoneItem, ArgBuilder> = stub()

  fun <T> subject(of: KFunction1<MilestoneItem, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<MilestoneItem, T>("subject", of)
}

interface MilestoneEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Milestone> node(init: () -> T): NullableStub<Milestone, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Milestone, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Milestone, T>("node", of)
}

interface MilestoneConnection : QType {
  fun <T : MilestoneEdge> edges(init: () -> T): NullableStub<MilestoneEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<MilestoneEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<MilestoneEdge, T>("edges", of)

  fun <T : Milestone> nodes(init: () -> T): NullableStub<Milestone, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Milestone, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Milestone, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface Milestone : QType {
  fun <T : Actor> creator(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> creator(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("creator", of)

  fun description(): NullableStub<String, ArgBuilder> = nullableStub()

  fun dueOn(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun number(): Stub<Int, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun state(): Stub<MilestoneState, ArgBuilder> = stub()

  fun title(): Stub<String, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()
}

interface MergedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun <T : Commit> commit(init: () -> T): NullableStub<Commit, ArgBuilder> = nullableStub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : Ref> mergeRef(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> mergeRef(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("mergeRef", of)

  fun mergeRefName(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()
}

interface MentionedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()
}

interface LockedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : Lockable> lockable(init: () -> T): Stub<Lockable, ArgBuilder> = stub()

  fun <T> lockable(of: KFunction1<Lockable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Lockable, T>("lockable", of)
}

interface LanguageEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Language> node(init: () -> T): Stub<Language, ArgBuilder> = stub()

  fun <T> node(of: KFunction1<Language, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Language, T>("node", of)

  fun size(): Stub<Int, ArgBuilder> = stub()
}

interface LanguageConnection : QType {
  fun <T : LanguageEdge> edges(init: () -> T): NullableStub<LanguageEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<LanguageEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<LanguageEdge, T>("edges", of)

  fun <T : Language> nodes(init: () -> T): NullableStub<Language, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Language, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Language, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()

  fun totalSize(): Stub<Int, ArgBuilder> = stub()
}

interface Language : QType {
  fun color(): NullableStub<String, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()
}

interface LabeledEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : Label> label(init: () -> T): Stub<Label, ArgBuilder> = stub()

  fun <T> label(of: KFunction1<Label, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Label, T>("label", of)

  fun <T : Labelable> labelable(init: () -> T): Stub<Labelable, ArgBuilder> = stub()

  fun <T> labelable(of: KFunction1<Labelable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Labelable, T>("labelable", of)
}

interface LabelEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Label> node(init: () -> T): NullableStub<Label, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Label, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Label, T>("node", of)
}

interface LabelConnection : QType {
  fun <T : LabelEdge> edges(init: () -> T): NullableStub<LabelEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<LabelEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<LabelEdge, T>("edges", of)

  fun <T : Label> nodes(init: () -> T): NullableStub<Label, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Label, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Label, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface Label : QType {
  fun color(): Stub<String, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun <T : IssueConnection> issues(init: () -> T,
      argBuilder: IssuesArgs = IssuesArgs()): Stub<T, IssuesArgs> = configStub(init, argBuilder)

  fun <T : PullRequestConnection> pullRequests(init: () -> T,
      argBuilder: PullRequestsArgs = PullRequestsArgs()): NullableStub<T, PullRequestsArgs> {
     return configNullableStub(init, argBuilder)
  }

  class IssuesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class PullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PullRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): PullRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): PullRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): PullRequestsArgs = apply { addArg("before", value) }

  }
}

interface IssueTimelineItemEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : IssueTimelineItem> node(init: () -> T): NullableStub<IssueTimelineItem, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<IssueTimelineItem, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<IssueTimelineItem, T>("node", of)
}

interface IssueTimelineConnection : QType {
  fun <T : IssueTimelineItemEdge> edges(init: () -> T): NullableStub<IssueTimelineItemEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<IssueTimelineItemEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<IssueTimelineItemEdge, T>("edges", of)

  fun <T : IssueTimelineItem> nodes(init: () -> T): NullableStub<IssueTimelineItem, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<IssueTimelineItem, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<IssueTimelineItem, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface IssueEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Issue> node(init: () -> T): NullableStub<Issue, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Issue, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Issue, T>("node", of)
}

interface IssueConnection : QType {
  fun <T : IssueEdge> edges(init: () -> T): NullableStub<IssueEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<IssueEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<IssueEdge, T>("edges", of)

  fun <T : Issue> nodes(init: () -> T): NullableStub<Issue, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Issue, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Issue, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface IssueCommentEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : IssueComment> node(init: () -> T): NullableStub<IssueComment, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<IssueComment, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<IssueComment, T>("node", of)
}

interface IssueCommentConnection : QType {
  fun <T : IssueCommentEdge> edges(init: () -> T): NullableStub<IssueCommentEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<IssueCommentEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<IssueCommentEdge, T>("edges", of)

  fun <T : IssueComment> nodes(init: () -> T): NullableStub<IssueComment, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<IssueComment, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<IssueComment, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface IssueComment : QType {
  fun <T : Actor> author(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> author(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation, ArgBuilder> = stub()

  fun body(): Stub<String, ArgBuilder> = stub()

  fun bodyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun bodyText(): Stub<String, ArgBuilder> = stub()

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun createdViaEmail(): Stub<Boolean, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun <T : Actor> editor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> editor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("editor", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : Issue> issue(init: () -> T): Stub<Issue, ArgBuilder> = stub()

  fun <T> issue(of: KFunction1<Issue, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Issue, T>("issue", of)

  fun lastEditedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun publishedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup, ArgBuilder> = stub()

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun viewerCanDelete(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanReact(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanUpdate(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason, ArgBuilder> = stub()

  fun viewerDidAuthor(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : ReactionConnection> reactions(init: () -> T,
      argBuilder: ReactionsArgs = ReactionsArgs()): Stub<T, ReactionsArgs> =
        configStub(init, argBuilder)

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

interface Issue : QType {
  fun <T : Actor> author(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> author(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation, ArgBuilder> = stub()

  fun body(): Stub<String, ArgBuilder> = stub()

  fun bodyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun bodyText(): Stub<String, ArgBuilder> = stub()

  fun closed(): Stub<Boolean, ArgBuilder> = stub()

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun createdViaEmail(): Stub<Boolean, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun <T : Actor> editor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> editor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("editor", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun lastEditedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun locked(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : Milestone> milestone(init: () -> T): NullableStub<Milestone, ArgBuilder> = nullableStub()

  fun <T> milestone(of: KFunction1<Milestone, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Milestone, T>("milestone", of)

  fun number(): Stub<Int, ArgBuilder> = stub()

  fun publishedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup, ArgBuilder> = stub()

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun state(): Stub<IssueState, ArgBuilder> = stub()

  fun title(): Stub<String, ArgBuilder> = stub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun viewerCanReact(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanSubscribe(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanUpdate(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason, ArgBuilder> = stub()

  fun viewerDidAuthor(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerSubscription(): Stub<SubscriptionState, ArgBuilder> = stub()

  fun <T : UserConnection> assignees(init: () -> T,
      argBuilder: AssigneesArgs = AssigneesArgs()): Stub<T, AssigneesArgs> =
        configStub(init, argBuilder)

  fun <T : IssueCommentConnection> comments(init: () -> T,
      argBuilder: CommentsArgs = CommentsArgs()): Stub<T, CommentsArgs> =
        configStub(init, argBuilder)

  fun <T : LabelConnection> labels(init: () -> T,
      argBuilder: LabelsArgs = LabelsArgs()): NullableStub<T, LabelsArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : UserConnection> participants(init: () -> T,
      argBuilder: ParticipantsArgs = ParticipantsArgs()): Stub<T, ParticipantsArgs> =
        configStub(init, argBuilder)

  fun <T : ReactionConnection> reactions(init: () -> T,
      argBuilder: ReactionsArgs = ReactionsArgs()): Stub<T, ReactionsArgs> =
        configStub(init, argBuilder)

  fun <T : IssueTimelineConnection> timeline(init: () -> T,
      argBuilder: TimelineArgs = TimelineArgs()): Stub<T, TimelineArgs> =
        configStub(init, argBuilder)

  class AssigneesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssigneesArgs = apply { addArg("first", value) }


    fun after(value: String): AssigneesArgs = apply { addArg("after", value) }


    fun last(value: Int): AssigneesArgs = apply { addArg("last", value) }


    fun before(value: String): AssigneesArgs = apply { addArg("before", value) }

  }

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }

  class ParticipantsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ParticipantsArgs = apply { addArg("first", value) }


    fun after(value: String): ParticipantsArgs = apply { addArg("after", value) }


    fun last(value: Int): ParticipantsArgs = apply { addArg("last", value) }


    fun before(value: String): ParticipantsArgs = apply { addArg("before", value) }

  }

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }

  class TimelineArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): TimelineArgs = apply { addArg("first", value) }


    fun after(value: String): TimelineArgs = apply { addArg("after", value) }


    fun last(value: Int): TimelineArgs = apply { addArg("last", value) }


    fun before(value: String): TimelineArgs = apply { addArg("before", value) }


    fun since(value: DateTime): TimelineArgs = apply { addArg("since", value) }

  }
}

interface HeadRefRestoredEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)
}

interface HeadRefForcePushedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun <T : Commit> afterCommit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> afterCommit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("afterCommit", of)

  fun <T : Commit> beforeCommit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> beforeCommit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("beforeCommit", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : Ref> ref(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> ref(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("ref", of)
}

interface HeadRefDeletedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun <T : Ref> headRef(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> headRef(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("headRef", of)

  fun headRefName(): Stub<String, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)
}

interface GpgSignature : QType {
  fun email(): Stub<String, ArgBuilder> = stub()

  fun isValid(): Stub<Boolean, ArgBuilder> = stub()

  fun keyId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun payload(): Stub<String, ArgBuilder> = stub()

  fun signature(): Stub<String, ArgBuilder> = stub()

  fun <T : User> signer(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> signer(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("signer", of)

  fun state(): Stub<GitSignatureState, ArgBuilder> = stub()
}

interface GitActor : QType {
  fun date(): NullableStub<GitTimestamp, ArgBuilder> = nullableStub()

  fun email(): NullableStub<String, ArgBuilder> = nullableStub()

  fun name(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : User> user(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> user(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("user", of)

  fun <T : URI> avatarUrl(init: () -> T,
      argBuilder: AvatarUrlArgs = AvatarUrlArgs()): Stub<T, AvatarUrlArgs> =
        configStub(init, argBuilder)

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }
}

interface GistEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Gist> node(init: () -> T): NullableStub<Gist, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Gist, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Gist, T>("node", of)
}

interface GistConnection : QType {
  fun <T : GistEdge> edges(init: () -> T): NullableStub<GistEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<GistEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<GistEdge, T>("edges", of)

  fun <T : Gist> nodes(init: () -> T): NullableStub<Gist, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Gist, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Gist, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface GistCommentEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : GistComment> node(init: () -> T): NullableStub<GistComment, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<GistComment, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<GistComment, T>("node", of)
}

interface GistCommentConnection : QType {
  fun <T : GistCommentEdge> edges(init: () -> T): NullableStub<GistCommentEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<GistCommentEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<GistCommentEdge, T>("edges", of)

  fun <T : GistComment> nodes(init: () -> T): NullableStub<GistComment, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<GistComment, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<GistComment, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface GistComment : QType {
  fun <T : Actor> author(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> author(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation, ArgBuilder> = stub()

  fun body(): Stub<String, ArgBuilder> = stub()

  fun bodyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun createdViaEmail(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : Actor> editor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> editor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("editor", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun lastEditedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun publishedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun viewerCanDelete(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanUpdate(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason, ArgBuilder> = stub()

  fun viewerDidAuthor(): Stub<Boolean, ArgBuilder> = stub()
}

interface Gist : QType {
  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun description(): NullableStub<String, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun isPublic(): Stub<Boolean, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun <T : RepositoryOwner> owner(init: () -> T): NullableStub<RepositoryOwner, ArgBuilder> =
        nullableStub()

  fun <T> owner(of: KFunction1<RepositoryOwner, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<RepositoryOwner, T>("owner", of)

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun viewerHasStarred(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : GistCommentConnection> comments(init: () -> T,
      argBuilder: CommentsArgs = CommentsArgs()): Stub<T, CommentsArgs> =
        configStub(init, argBuilder)

  fun <T : StargazerConnection> stargazers(init: () -> T,
      argBuilder: StargazersArgs = StargazersArgs()): Stub<T, StargazersArgs> =
        configStub(init, argBuilder)

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class StargazersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StargazersArgs = apply { addArg("first", value) }


    fun after(value: String): StargazersArgs = apply { addArg("after", value) }


    fun last(value: Int): StargazersArgs = apply { addArg("last", value) }


    fun before(value: String): StargazersArgs = apply { addArg("before", value) }


    fun orderBy(value: StarOrder): StargazersArgs = apply { addArg("orderBy", value) }

  }
}

interface FollowingConnection : QType {
  fun <T : UserEdge> edges(init: () -> T): NullableStub<UserEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<UserEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<UserEdge, T>("edges", of)

  fun <T : User> nodes(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface FollowerConnection : QType {
  fun <T : UserEdge> edges(init: () -> T): NullableStub<UserEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<UserEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<UserEdge, T>("edges", of)

  fun <T : User> nodes(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface ExternalIdentityScimAttributes : QType {
  fun username(): NullableStub<String, ArgBuilder> = nullableStub()
}

interface ExternalIdentitySamlAttributes : QType {
  fun nameId(): NullableStub<String, ArgBuilder> = nullableStub()
}

interface ExternalIdentityEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : ExternalIdentity> node(init: () -> T): NullableStub<ExternalIdentity, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<ExternalIdentity, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ExternalIdentity, T>("node", of)
}

interface ExternalIdentityConnection : QType {
  fun <T : ExternalIdentityEdge> edges(init: () -> T): NullableStub<ExternalIdentityEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<ExternalIdentityEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ExternalIdentityEdge, T>("edges", of)

  fun <T : ExternalIdentity> nodes(init: () -> T): NullableStub<ExternalIdentity, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<ExternalIdentity, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ExternalIdentity, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface ExternalIdentity : QType {
  fun guid(): Stub<String, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : OrganizationInvitation> organizationInvitation(init: () -> T): NullableStub<OrganizationInvitation, ArgBuilder> =
        nullableStub()

  fun <T> organizationInvitation(of: KFunction1<OrganizationInvitation, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<OrganizationInvitation, T>("organizationInvitation", of)

  fun <T : ExternalIdentitySamlAttributes> samlIdentity(init: () -> T): NullableStub<ExternalIdentitySamlAttributes, ArgBuilder> =
        nullableStub()

  fun <T> samlIdentity(of: KFunction1<ExternalIdentitySamlAttributes, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ExternalIdentitySamlAttributes, T>("samlIdentity", of)

  fun <T : ExternalIdentityScimAttributes> scimIdentity(init: () -> T): NullableStub<ExternalIdentityScimAttributes, ArgBuilder> =
        nullableStub()

  fun <T> scimIdentity(of: KFunction1<ExternalIdentityScimAttributes, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<ExternalIdentityScimAttributes, T>("scimIdentity", of)

  fun <T : User> user(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> user(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("user", of)
}

interface DismissPullRequestReviewPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview, ArgBuilder> =
        stub()

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReview, T>("pullRequestReview", of)
}

interface DeploymentStatusEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : DeploymentStatus> node(init: () -> T): NullableStub<DeploymentStatus, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<DeploymentStatus, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<DeploymentStatus, T>("node", of)
}

interface DeploymentStatusConnection : QType {
  fun <T : DeploymentStatusEdge> edges(init: () -> T): NullableStub<DeploymentStatusEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<DeploymentStatusEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<DeploymentStatusEdge, T>("edges", of)

  fun <T : DeploymentStatus> nodes(init: () -> T): NullableStub<DeploymentStatus, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<DeploymentStatus, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<DeploymentStatus, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface DeploymentStatus : QType {
  fun <T : Actor> creator(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> creator(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("creator", of)

  fun <T : Deployment> deployment(init: () -> T): Stub<Deployment, ArgBuilder> = stub()

  fun <T> deployment(of: KFunction1<Deployment, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Deployment, T>("deployment", of)

  fun description(): NullableStub<String, ArgBuilder> = nullableStub()

  fun environmentUrl(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun logUrl(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun state(): Stub<DeploymentStatusState, ArgBuilder> = stub()
}

interface DeploymentEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Deployment> node(init: () -> T): NullableStub<Deployment, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Deployment, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Deployment, T>("node", of)
}

interface DeploymentConnection : QType {
  fun <T : DeploymentEdge> edges(init: () -> T): NullableStub<DeploymentEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<DeploymentEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<DeploymentEdge, T>("edges", of)

  fun <T : Deployment> nodes(init: () -> T): NullableStub<Deployment, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Deployment, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Deployment, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface Deployment : QType {
  fun <T : Commit> commit(init: () -> T): NullableStub<Commit, ArgBuilder> = nullableStub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun <T : Actor> creator(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> creator(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("creator", of)

  fun environment(): NullableStub<String, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : DeploymentStatus> latestStatus(init: () -> T): NullableStub<DeploymentStatus, ArgBuilder> =
        nullableStub()

  fun <T> latestStatus(of: KFunction1<DeploymentStatus, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<DeploymentStatus, T>("latestStatus", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun state(): NullableStub<DeploymentState, ArgBuilder> = nullableStub()

  fun <T : DeploymentStatusConnection> statuses(init: () -> T,
      argBuilder: StatusesArgs = StatusesArgs()): NullableStub<T, StatusesArgs> {
     return configNullableStub(init, argBuilder)
  }

  class StatusesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StatusesArgs = apply { addArg("first", value) }


    fun after(value: String): StatusesArgs = apply { addArg("after", value) }


    fun last(value: Int): StatusesArgs = apply { addArg("last", value) }


    fun before(value: String): StatusesArgs = apply { addArg("before", value) }

  }
}

interface DeployedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun <T : Deployment> deployment(init: () -> T): Stub<Deployment, ArgBuilder> = stub()

  fun <T> deployment(of: KFunction1<Deployment, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Deployment, T>("deployment", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : Ref> ref(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> ref(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("ref", of)
}

interface DemilestonedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun milestoneTitle(): Stub<String, ArgBuilder> = stub()

  fun <T : MilestoneItem> subject(init: () -> T): Stub<MilestoneItem, ArgBuilder> = stub()

  fun <T> subject(of: KFunction1<MilestoneItem, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<MilestoneItem, T>("subject", of)
}

interface DeletePullRequestReviewPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview, ArgBuilder> =
        stub()

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReview, T>("pullRequestReview", of)
}

interface DeleteProjectPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : ProjectOwner> owner(init: () -> T): Stub<ProjectOwner, ArgBuilder> = stub()

  fun <T> owner(of: KFunction1<ProjectOwner, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProjectOwner, T>("owner", of)
}

interface DeleteProjectColumnPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun deletedColumnId(): Stub<String, ArgBuilder> = stub()

  fun <T : Project> project(init: () -> T): Stub<Project, ArgBuilder> = stub()

  fun <T> project(of: KFunction1<Project, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Project, T>("project", of)
}

interface DeleteProjectCardPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : ProjectColumn> column(init: () -> T): Stub<ProjectColumn, ArgBuilder> = stub()

  fun <T> column(of: KFunction1<ProjectColumn, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProjectColumn, T>("column", of)

  fun deletedCardId(): Stub<String, ArgBuilder> = stub()
}

interface DeclineTopicSuggestionPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Topic> topic(init: () -> T): Stub<Topic, ArgBuilder> = stub()

  fun <T> topic(of: KFunction1<Topic, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Topic, T>("topic", of)
}

interface CreateProjectPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Project> project(init: () -> T): Stub<Project, ArgBuilder> = stub()

  fun <T> project(of: KFunction1<Project, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Project, T>("project", of)
}

interface ConvertedNoteToIssueEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()
}

interface CommitHistoryConnection : QType {
  fun <T : CommitEdge> edges(init: () -> T): NullableStub<CommitEdge, ArgBuilder> = nullableStub()

  fun <T> edges(of: KFunction1<CommitEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<CommitEdge, T>("edges", of)

  fun <T : Commit> nodes(init: () -> T): NullableStub<Commit, ArgBuilder> = nullableStub()

  fun <T> nodes(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)
}

interface CommitEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : Commit> node(init: () -> T): NullableStub<Commit, ArgBuilder> = nullableStub()

  fun <T> node(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("node", of)
}

interface CommitCommentThread : QType {
  fun <T : Commit> commit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("commit", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun path(): NullableStub<String, ArgBuilder> = nullableStub()

  fun position(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun <T : CommitCommentConnection> comments(init: () -> T,
      argBuilder: CommentsArgs = CommentsArgs()): Stub<T, CommentsArgs> =
        configStub(init, argBuilder)

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

interface CommitCommentEdge : QType {
  fun cursor(): Stub<String, ArgBuilder> = stub()

  fun <T : CommitComment> node(init: () -> T): NullableStub<CommitComment, ArgBuilder> =
        nullableStub()

  fun <T> node(of: KFunction1<CommitComment, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<CommitComment, T>("node", of)
}

interface CommitCommentConnection : QType {
  fun <T : CommitCommentEdge> edges(init: () -> T): NullableStub<CommitCommentEdge, ArgBuilder> =
        nullableStub()

  fun <T> edges(of: KFunction1<CommitCommentEdge, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<CommitCommentEdge, T>("edges", of)

  fun <T : CommitComment> nodes(init: () -> T): NullableStub<CommitComment, ArgBuilder> =
        nullableStub()

  fun <T> nodes(of: KFunction1<CommitComment, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<CommitComment, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo, ArgBuilder> = stub()

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int, ArgBuilder> = stub()
}

interface CommitComment : QType {
  fun <T : Actor> author(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> author(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation, ArgBuilder> = stub()

  fun body(): Stub<String, ArgBuilder> = stub()

  fun bodyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun <T : Commit> commit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun createdViaEmail(): Stub<Boolean, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun <T : Actor> editor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> editor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("editor", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun lastEditedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun path(): NullableStub<String, ArgBuilder> = nullableStub()

  fun position(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun publishedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup, ArgBuilder> = stub()

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun viewerCanDelete(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanReact(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCanUpdate(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason, ArgBuilder> = stub()

  fun viewerDidAuthor(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : ReactionConnection> reactions(init: () -> T,
      argBuilder: ReactionsArgs = ReactionsArgs()): Stub<T, ReactionsArgs> =
        configStub(init, argBuilder)

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

interface Commit : QType {
  fun abbreviatedOid(): Stub<String, ArgBuilder> = stub()

  fun <T : GitActor> author(init: () -> T): NullableStub<GitActor, ArgBuilder> = nullableStub()

  fun <T> author(of: KFunction1<GitActor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<GitActor, T>("author", of)

  fun authoredByCommitter(): Stub<Boolean, ArgBuilder> = stub()

  fun commitResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun commitUrl(): Stub<URI, ArgBuilder> = stub()

  fun committedDate(): Stub<DateTime, ArgBuilder> = stub()

  fun committedViaWeb(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : GitActor> committer(init: () -> T): NullableStub<GitActor, ArgBuilder> = nullableStub()

  fun <T> committer(of: KFunction1<GitActor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<GitActor, T>("committer", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun message(): Stub<String, ArgBuilder> = stub()

  fun messageBody(): Stub<String, ArgBuilder> = stub()

  fun messageBodyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun messageHeadline(): Stub<String, ArgBuilder> = stub()

  fun messageHeadlineHTML(): Stub<HTML, ArgBuilder> = stub()

  fun oid(): Stub<GitObjectID, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun <T : GitSignature> signature(init: () -> T): NullableStub<GitSignature, ArgBuilder> =
        nullableStub()

  fun <T> signature(of: KFunction1<GitSignature, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<GitSignature, T>("signature", of)

  fun <T : Status> status(init: () -> T): NullableStub<Status, ArgBuilder> = nullableStub()

  fun <T> status(of: KFunction1<Status, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Status, T>("status", of)

  fun <T : Tree> tree(init: () -> T): Stub<Tree, ArgBuilder> = stub()

  fun <T> tree(of: KFunction1<Tree, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Tree, T>("tree", of)

  fun treeResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun treeUrl(): Stub<URI, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun viewerCanSubscribe(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerSubscription(): Stub<SubscriptionState, ArgBuilder> = stub()

  fun <T : Blame> blame(init: () -> T, argBuilder: BlameArgs = BlameArgs()): Stub<T, BlameArgs> =
        configStub(init, argBuilder)

  fun <T : CommitCommentConnection> comments(init: () -> T,
      argBuilder: CommentsArgs = CommentsArgs()): Stub<T, CommentsArgs> =
        configStub(init, argBuilder)

  fun <T : CommitHistoryConnection> history(init: () -> T,
      argBuilder: HistoryArgs = HistoryArgs()): Stub<T, HistoryArgs> = configStub(init, argBuilder)

  class BlameArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun path(value: String): BlameArgs = apply { addArg("path", value) }

  }

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class HistoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
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

interface CommentDeletedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()
}

interface CodeOfConduct : QType {
  fun body(): NullableStub<String, ArgBuilder> = nullableStub()

  fun key(): Stub<String, ArgBuilder> = stub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun url(): NullableStub<URI, ArgBuilder> = nullableStub()
}

interface ClosedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun <T : Closable> closable(init: () -> T): Stub<Closable, ArgBuilder> = stub()

  fun <T> closable(of: KFunction1<Closable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Closable, T>("closable", of)

  fun <T : Commit> commit(init: () -> T): NullableStub<Commit, ArgBuilder> = nullableStub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()
}

interface Bot : QType {
  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun login(): Stub<String, ArgBuilder> = stub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun <T : URI> avatarUrl(init: () -> T,
      argBuilder: AvatarUrlArgs = AvatarUrlArgs()): Stub<T, AvatarUrlArgs> =
        configStub(init, argBuilder)

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }
}

interface Blob : QType {
  fun abbreviatedOid(): Stub<String, ArgBuilder> = stub()

  fun byteSize(): Stub<Int, ArgBuilder> = stub()

  fun commitResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun commitUrl(): Stub<URI, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun isBinary(): Stub<Boolean, ArgBuilder> = stub()

  fun isTruncated(): Stub<Boolean, ArgBuilder> = stub()

  fun oid(): Stub<GitObjectID, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun text(): NullableStub<String, ArgBuilder> = nullableStub()
}

interface BlameRange : QType {
  fun age(): Stub<Int, ArgBuilder> = stub()

  fun <T : Commit> commit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("commit", of)

  fun endingLine(): Stub<Int, ArgBuilder> = stub()

  fun startingLine(): Stub<Int, ArgBuilder> = stub()
}

interface Blame : QType {
  fun <T : BlameRange> ranges(init: () -> T): Stub<BlameRange, ArgBuilder> = stub()

  fun <T> ranges(of: KFunction1<BlameRange, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<BlameRange, T>("ranges", of)
}

interface BaseRefForcePushedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun <T : Commit> afterCommit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> afterCommit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("afterCommit", of)

  fun <T : Commit> beforeCommit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> beforeCommit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("beforeCommit", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : Ref> ref(init: () -> T): NullableStub<Ref, ArgBuilder> = nullableStub()

  fun <T> ref(of: KFunction1<Ref, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Ref, T>("ref", of)
}

interface BaseRefChangedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()
}

interface AssignedEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun <T : Assignable> assignable(init: () -> T): Stub<Assignable, ArgBuilder> = stub()

  fun <T> assignable(of: KFunction1<Assignable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Assignable, T>("assignable", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : User> user(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> user(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("user", of)
}

interface AddedToProjectEvent : QType {
  fun <T : Actor> actor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> actor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()
}

interface AddStarPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Starrable> starrable(init: () -> T): Stub<Starrable, ArgBuilder> = stub()

  fun <T> starrable(of: KFunction1<Starrable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Starrable, T>("starrable", of)
}

interface AddReactionPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Reaction> reaction(init: () -> T): Stub<Reaction, ArgBuilder> = stub()

  fun <T> reaction(of: KFunction1<Reaction, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Reaction, T>("reaction", of)

  fun <T : Reactable> subject(init: () -> T): Stub<Reactable, ArgBuilder> = stub()

  fun <T> subject(of: KFunction1<Reactable, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Reactable, T>("subject", of)
}

interface AddPullRequestReviewPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview, ArgBuilder> =
        stub()

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReview, T>("pullRequestReview", of)

  fun <T : PullRequestReviewEdge> reviewEdge(init: () -> T): Stub<PullRequestReviewEdge, ArgBuilder> =
        stub()

  fun <T> reviewEdge(of: KFunction1<PullRequestReviewEdge, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReviewEdge, T>("reviewEdge", of)
}

interface AddPullRequestReviewCommentPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : PullRequestReviewComment> comment(init: () -> T): Stub<PullRequestReviewComment, ArgBuilder> =
        stub()

  fun <T> comment(of: KFunction1<PullRequestReviewComment, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReviewComment, T>("comment", of)

  fun <T : PullRequestReviewCommentEdge> commentEdge(init: () -> T): Stub<PullRequestReviewCommentEdge, ArgBuilder> =
        stub()

  fun <T> commentEdge(of: KFunction1<PullRequestReviewCommentEdge, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReviewCommentEdge, T>("commentEdge", of)
}

interface AddProjectColumnPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : ProjectColumnEdge> columnEdge(init: () -> T): Stub<ProjectColumnEdge, ArgBuilder> =
        stub()

  fun <T> columnEdge(of: KFunction1<ProjectColumnEdge, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProjectColumnEdge, T>("columnEdge", of)

  fun <T : Project> project(init: () -> T): Stub<Project, ArgBuilder> = stub()

  fun <T> project(of: KFunction1<Project, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Project, T>("project", of)
}

interface AddProjectCardPayload : QType {
  fun <T : ProjectCardEdge> cardEdge(init: () -> T): Stub<ProjectCardEdge, ArgBuilder> = stub()

  fun <T> cardEdge(of: KFunction1<ProjectCardEdge, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ProjectCardEdge, T>("cardEdge", of)

  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Project> projectColumn(init: () -> T): Stub<Project, ArgBuilder> = stub()

  fun <T> projectColumn(of: KFunction1<Project, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Project, T>("projectColumn", of)
}

interface AddCommentPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : IssueCommentEdge> commentEdge(init: () -> T): Stub<IssueCommentEdge, ArgBuilder> = stub()

  fun <T> commentEdge(of: KFunction1<IssueCommentEdge, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<IssueCommentEdge, T>("commentEdge", of)

  fun <T : Node> subject(init: () -> T): Stub<Node, ArgBuilder> = stub()

  fun <T> subject(of: KFunction1<Node, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Node, T>("subject", of)

  fun <T : IssueTimelineItemEdge> timelineEdge(init: () -> T): Stub<IssueTimelineItemEdge, ArgBuilder> =
        stub()

  fun <T> timelineEdge(of: KFunction1<IssueTimelineItemEdge, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<IssueTimelineItemEdge, T>("timelineEdge", of)
}

interface AcceptTopicSuggestionPayload : QType {
  fun clientMutationId(): NullableStub<String, ArgBuilder> = nullableStub()

  fun <T : Topic> topic(init: () -> T): Stub<Topic, ArgBuilder> = stub()

  fun <T> topic(of: KFunction1<Topic, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Topic, T>("topic", of)
}

interface UpdatableComment : QType {
  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason, ArgBuilder> = stub()
}

interface Updatable : QType {
  fun viewerCanUpdate(): Stub<Boolean, ArgBuilder> = stub()
}

interface UniformResourceLocatable : QType {
  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()
}

interface Subscribable : QType {
  fun viewerCanSubscribe(): Stub<Boolean, ArgBuilder> = stub()

  fun viewerSubscription(): Stub<SubscriptionState, ArgBuilder> = stub()
}

interface Starrable : QType {
  fun id(): Stub<String, ArgBuilder> = stub()

  fun viewerHasStarred(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : StargazerConnection> stargazers(init: () -> T,
      argBuilder: StargazersArgs = StargazersArgs()): Stub<T, StargazersArgs> =
        configStub(init, argBuilder)

  class StargazersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StargazersArgs = apply { addArg("first", value) }


    fun after(value: String): StargazersArgs = apply { addArg("after", value) }


    fun last(value: Int): StargazersArgs = apply { addArg("last", value) }


    fun before(value: String): StargazersArgs = apply { addArg("before", value) }


    fun orderBy(value: StarOrder): StargazersArgs = apply { addArg("orderBy", value) }

  }
}

interface RepositoryOwner : QType {
  fun id(): Stub<String, ArgBuilder> = stub()

  fun login(): Stub<String, ArgBuilder> = stub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun <T : URI> avatarUrl(init: () -> T,
      argBuilder: AvatarUrlArgs = AvatarUrlArgs()): Stub<T, AvatarUrlArgs> =
        configStub(init, argBuilder)

  fun <T : RepositoryConnection> pinnedRepositories(init: () -> T,
      argBuilder: PinnedRepositoriesArgs = PinnedRepositoriesArgs()): Stub<T, PinnedRepositoriesArgs> =
        configStub(init, argBuilder)

  fun <T : RepositoryConnection> repositories(init: () -> T,
      argBuilder: RepositoriesArgs = RepositoriesArgs()): Stub<T, RepositoriesArgs> =
        configStub(init, argBuilder)

  fun <T : Repository> repository(init: () -> T,
      argBuilder: RepositoryArgs = RepositoryArgs()): NullableStub<T, RepositoryArgs> {
     return configNullableStub(init, argBuilder)
  }

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }

  class PinnedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PinnedRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): PinnedRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): PinnedRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): PinnedRepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs =
          apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs =
          apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): PinnedRepositoriesArgs = apply { addArg("isLocked", value) }

  }

  class RepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): RepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): RepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): RepositoriesArgs = apply { addArg("before", value) }


    fun privacy(value: RepositoryPrivacy): RepositoriesArgs = apply { addArg("privacy", value) }


    fun orderBy(value: RepositoryOrder): RepositoriesArgs = apply { addArg("orderBy", value) }


    fun affiliations(value: RepositoryAffiliation): RepositoriesArgs =
          apply { addArg("affiliations", value) }


    fun isLocked(value: Boolean): RepositoriesArgs = apply { addArg("isLocked", value) }


    fun isFork(value: Boolean): RepositoriesArgs = apply { addArg("isFork", value) }

  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }
}

interface RepositoryNode : QType {
  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)
}

interface RepositoryInfo : QType {
  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun description(): NullableStub<String, ArgBuilder> = nullableStub()

  fun descriptionHTML(): Stub<HTML, ArgBuilder> = stub()

  fun hasIssuesEnabled(): Stub<Boolean, ArgBuilder> = stub()

  fun hasWikiEnabled(): Stub<Boolean, ArgBuilder> = stub()

  fun homepageUrl(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun isFork(): Stub<Boolean, ArgBuilder> = stub()

  fun isLocked(): Stub<Boolean, ArgBuilder> = stub()

  fun isMirror(): Stub<Boolean, ArgBuilder> = stub()

  fun isPrivate(): Stub<Boolean, ArgBuilder> = stub()

  fun license(): NullableStub<String, ArgBuilder> = nullableStub()

  fun lockReason(): NullableStub<RepositoryLockReason, ArgBuilder> = nullableStub()

  fun mirrorUrl(): NullableStub<URI, ArgBuilder> = nullableStub()

  fun name(): Stub<String, ArgBuilder> = stub()

  fun nameWithOwner(): Stub<String, ArgBuilder> = stub()

  fun <T : RepositoryOwner> owner(init: () -> T): Stub<RepositoryOwner, ArgBuilder> = stub()

  fun <T> owner(of: KFunction1<RepositoryOwner, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<RepositoryOwner, T>("owner", of)

  fun pushedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()
}

interface Reactable : QType {
  fun databaseId(): NullableStub<Int, ArgBuilder> = nullableStub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup, ArgBuilder> = stub()

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun viewerCanReact(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : ReactionConnection> reactions(init: () -> T,
      argBuilder: ReactionsArgs = ReactionsArgs()): Stub<T, ReactionsArgs> =
        configStub(init, argBuilder)

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

interface ProjectOwner : QType {
  fun id(): Stub<String, ArgBuilder> = stub()

  fun projectsResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun projectsUrl(): Stub<URI, ArgBuilder> = stub()

  fun viewerCanCreateProjects(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : Project> project(init: () -> T,
      argBuilder: ProjectArgs = ProjectArgs()): NullableStub<T, ProjectArgs> {
     return configNullableStub(init, argBuilder)
  }

  fun <T : ProjectConnection> projects(init: () -> T,
      argBuilder: ProjectsArgs = ProjectsArgs()): Stub<T, ProjectsArgs> =
        configStub(init, argBuilder)

  class ProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): ProjectArgs = apply { addArg("number", value) }

  }

  class ProjectsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProjectsArgs = apply { addArg("first", value) }


    fun after(value: String): ProjectsArgs = apply { addArg("after", value) }


    fun last(value: Int): ProjectsArgs = apply { addArg("last", value) }


    fun before(value: String): ProjectsArgs = apply { addArg("before", value) }


    fun orderBy(value: ProjectOrder): ProjectsArgs = apply { addArg("orderBy", value) }


    fun search(value: String): ProjectsArgs = apply { addArg("search", value) }


    fun states(value: ProjectState): ProjectsArgs = apply { addArg("states", value) }

  }
}

interface Node : QType {
  fun id(): Stub<String, ArgBuilder> = stub()
}

interface Lockable : QType {
  fun locked(): Stub<Boolean, ArgBuilder> = stub()
}

interface Labelable : QType {
  fun <T : LabelConnection> labels(init: () -> T,
      argBuilder: LabelsArgs = LabelsArgs()): NullableStub<T, LabelsArgs> {
     return configNullableStub(init, argBuilder)
  }

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }
}

interface GitSignature : QType {
  fun email(): Stub<String, ArgBuilder> = stub()

  fun isValid(): Stub<Boolean, ArgBuilder> = stub()

  fun payload(): Stub<String, ArgBuilder> = stub()

  fun signature(): Stub<String, ArgBuilder> = stub()

  fun <T : User> signer(init: () -> T): NullableStub<User, ArgBuilder> = nullableStub()

  fun <T> signer(of: KFunction1<User, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<User, T>("signer", of)

  fun state(): Stub<GitSignatureState, ArgBuilder> = stub()
}

interface GitObject : QType {
  fun abbreviatedOid(): Stub<String, ArgBuilder> = stub()

  fun commitResourcePath(): Stub<URI, ArgBuilder> = stub()

  fun commitUrl(): Stub<URI, ArgBuilder> = stub()

  fun id(): Stub<String, ArgBuilder> = stub()

  fun oid(): Stub<GitObjectID, ArgBuilder> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)
}

interface Deletable : QType {
  fun viewerCanDelete(): Stub<Boolean, ArgBuilder> = stub()
}

interface Comment : QType {
  fun <T : Actor> author(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> author(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation, ArgBuilder> = stub()

  fun body(): Stub<String, ArgBuilder> = stub()

  fun bodyHTML(): Stub<HTML, ArgBuilder> = stub()

  fun createdAt(): Stub<DateTime, ArgBuilder> = stub()

  fun createdViaEmail(): Stub<Boolean, ArgBuilder> = stub()

  fun <T : Actor> editor(init: () -> T): NullableStub<Actor, ArgBuilder> = nullableStub()

  fun <T> editor(of: KFunction1<Actor, Stub<T, ArgBuilder>>): NullableStub<T, ArgBuilder> =
        nullableStub<Actor, T>("editor", of)

  fun id(): Stub<String, ArgBuilder> = stub()

  fun lastEditedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun publishedAt(): NullableStub<DateTime, ArgBuilder> = nullableStub()

  fun updatedAt(): Stub<DateTime, ArgBuilder> = stub()

  fun viewerDidAuthor(): Stub<Boolean, ArgBuilder> = stub()
}

interface Closable : QType {
  fun closed(): Stub<Boolean, ArgBuilder> = stub()
}

interface Assignable : QType {
  fun <T : UserConnection> assignees(init: () -> T,
      argBuilder: AssigneesArgs = AssigneesArgs()): Stub<T, AssigneesArgs> =
        configStub(init, argBuilder)

  class AssigneesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssigneesArgs = apply { addArg("first", value) }


    fun after(value: String): AssigneesArgs = apply { addArg("after", value) }


    fun last(value: Int): AssigneesArgs = apply { addArg("last", value) }


    fun before(value: String): AssigneesArgs = apply { addArg("before", value) }

  }
}

interface Actor : QType {
  fun login(): Stub<String, ArgBuilder> = stub()

  fun resourcePath(): Stub<URI, ArgBuilder> = stub()

  fun url(): Stub<URI, ArgBuilder> = stub()

  fun <T : URI> avatarUrl(init: () -> T,
      argBuilder: AvatarUrlArgs = AvatarUrlArgs()): Stub<T, AvatarUrlArgs> =
        configStub(init, argBuilder)

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }
}

interface SearchResultItem : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue, ArgBuilder> = stub()

  fun <T> issue(of: KFunction1<Issue, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullrequest", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository, ArgBuilder> = stub()

  fun <T> repository(of: KFunction1<Repository, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Repository, T>("repository", of)

  fun <T : User> user(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> user(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("user", of)

  fun <T : Organization> organization(init: () -> T): Stub<Organization, ArgBuilder> = stub()

  fun <T> organization(of: KFunction1<Organization, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Organization, T>("organization", of)
}

interface ReviewDismissalAllowanceActor : QType {
  fun <T : User> user(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> user(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("user", of)

  fun <T : Team> team(init: () -> T): Stub<Team, ArgBuilder> = stub()

  fun <T> team(of: KFunction1<Team, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Team, T>("team", of)
}

interface RenamedTitleSubject : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue, ArgBuilder> = stub()

  fun <T> issue(of: KFunction1<Issue, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullrequest", of)
}

interface ReferencedSubject : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue, ArgBuilder> = stub()

  fun <T> issue(of: KFunction1<Issue, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullrequest", of)
}

interface PushAllowanceActor : QType {
  fun <T : User> user(init: () -> T): Stub<User, ArgBuilder> = stub()

  fun <T> user(of: KFunction1<User, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<User, T>("user", of)

  fun <T : Team> team(init: () -> T): Stub<Team, ArgBuilder> = stub()

  fun <T> team(of: KFunction1<Team, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Team, T>("team", of)
}

interface PullRequestTimelineItem : QType {
  fun <T : Commit> commit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("commit", of)

  fun <T : CommitCommentThread> commitcommentthread(init: () -> T): Stub<CommitCommentThread, ArgBuilder> =
        stub()

  fun <T> commitcommentthread(of: KFunction1<CommitCommentThread, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<CommitCommentThread, T>("commitcommentthread", of)

  fun <T : PullRequestReview> pullrequestreview(init: () -> T): Stub<PullRequestReview, ArgBuilder> =
        stub()

  fun <T> pullrequestreview(of: KFunction1<PullRequestReview, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReview, T>("pullrequestreview", of)

  fun <T : PullRequestReviewThread> pullrequestreviewthread(init: () -> T): Stub<PullRequestReviewThread, ArgBuilder> =
        stub()

  fun <T> pullrequestreviewthread(of: KFunction1<PullRequestReviewThread, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReviewThread, T>("pullrequestreviewthread", of)

  fun <T : PullRequestReviewComment> pullrequestreviewcomment(init: () -> T): Stub<PullRequestReviewComment, ArgBuilder> =
        stub()

  fun <T> pullrequestreviewcomment(of: KFunction1<PullRequestReviewComment, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequestReviewComment, T>("pullrequestreviewcomment", of)

  fun <T : IssueComment> issuecomment(init: () -> T): Stub<IssueComment, ArgBuilder> = stub()

  fun <T> issuecomment(of: KFunction1<IssueComment, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<IssueComment, T>("issuecomment", of)

  fun <T : ClosedEvent> closedevent(init: () -> T): Stub<ClosedEvent, ArgBuilder> = stub()

  fun <T> closedevent(of: KFunction1<ClosedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ClosedEvent, T>("closedevent", of)

  fun <T : ReopenedEvent> reopenedevent(init: () -> T): Stub<ReopenedEvent, ArgBuilder> = stub()

  fun <T> reopenedevent(of: KFunction1<ReopenedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReopenedEvent, T>("reopenedevent", of)

  fun <T : SubscribedEvent> subscribedevent(init: () -> T): Stub<SubscribedEvent, ArgBuilder> =
        stub()

  fun <T> subscribedevent(of: KFunction1<SubscribedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<SubscribedEvent, T>("subscribedevent", of)

  fun <T : UnsubscribedEvent> unsubscribedevent(init: () -> T): Stub<UnsubscribedEvent, ArgBuilder> =
        stub()

  fun <T> unsubscribedevent(of: KFunction1<UnsubscribedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<UnsubscribedEvent, T>("unsubscribedevent", of)

  fun <T : MergedEvent> mergedevent(init: () -> T): Stub<MergedEvent, ArgBuilder> = stub()

  fun <T> mergedevent(of: KFunction1<MergedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<MergedEvent, T>("mergedevent", of)

  fun <T : ReferencedEvent> referencedevent(init: () -> T): Stub<ReferencedEvent, ArgBuilder> =
        stub()

  fun <T> referencedevent(of: KFunction1<ReferencedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReferencedEvent, T>("referencedevent", of)

  fun <T : AssignedEvent> assignedevent(init: () -> T): Stub<AssignedEvent, ArgBuilder> = stub()

  fun <T> assignedevent(of: KFunction1<AssignedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<AssignedEvent, T>("assignedevent", of)

  fun <T : UnassignedEvent> unassignedevent(init: () -> T): Stub<UnassignedEvent, ArgBuilder> =
        stub()

  fun <T> unassignedevent(of: KFunction1<UnassignedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<UnassignedEvent, T>("unassignedevent", of)

  fun <T : LabeledEvent> labeledevent(init: () -> T): Stub<LabeledEvent, ArgBuilder> = stub()

  fun <T> labeledevent(of: KFunction1<LabeledEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<LabeledEvent, T>("labeledevent", of)

  fun <T : UnlabeledEvent> unlabeledevent(init: () -> T): Stub<UnlabeledEvent, ArgBuilder> = stub()

  fun <T> unlabeledevent(of: KFunction1<UnlabeledEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<UnlabeledEvent, T>("unlabeledevent", of)

  fun <T : MilestonedEvent> milestonedevent(init: () -> T): Stub<MilestonedEvent, ArgBuilder> =
        stub()

  fun <T> milestonedevent(of: KFunction1<MilestonedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<MilestonedEvent, T>("milestonedevent", of)

  fun <T : DemilestonedEvent> demilestonedevent(init: () -> T): Stub<DemilestonedEvent, ArgBuilder> =
        stub()

  fun <T> demilestonedevent(of: KFunction1<DemilestonedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<DemilestonedEvent, T>("demilestonedevent", of)

  fun <T : RenamedTitleEvent> renamedtitleevent(init: () -> T): Stub<RenamedTitleEvent, ArgBuilder> =
        stub()

  fun <T> renamedtitleevent(of: KFunction1<RenamedTitleEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<RenamedTitleEvent, T>("renamedtitleevent", of)

  fun <T : LockedEvent> lockedevent(init: () -> T): Stub<LockedEvent, ArgBuilder> = stub()

  fun <T> lockedevent(of: KFunction1<LockedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<LockedEvent, T>("lockedevent", of)

  fun <T : UnlockedEvent> unlockedevent(init: () -> T): Stub<UnlockedEvent, ArgBuilder> = stub()

  fun <T> unlockedevent(of: KFunction1<UnlockedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<UnlockedEvent, T>("unlockedevent", of)

  fun <T : DeployedEvent> deployedevent(init: () -> T): Stub<DeployedEvent, ArgBuilder> = stub()

  fun <T> deployedevent(of: KFunction1<DeployedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<DeployedEvent, T>("deployedevent", of)

  fun <T : HeadRefDeletedEvent> headrefdeletedevent(init: () -> T): Stub<HeadRefDeletedEvent, ArgBuilder> =
        stub()

  fun <T> headrefdeletedevent(of: KFunction1<HeadRefDeletedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<HeadRefDeletedEvent, T>("headrefdeletedevent", of)

  fun <T : HeadRefRestoredEvent> headrefrestoredevent(init: () -> T): Stub<HeadRefRestoredEvent, ArgBuilder> =
        stub()

  fun <T> headrefrestoredevent(of: KFunction1<HeadRefRestoredEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<HeadRefRestoredEvent, T>("headrefrestoredevent", of)

  fun <T : HeadRefForcePushedEvent> headrefforcepushedevent(init: () -> T): Stub<HeadRefForcePushedEvent, ArgBuilder> =
        stub()

  fun <T> headrefforcepushedevent(of: KFunction1<HeadRefForcePushedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<HeadRefForcePushedEvent, T>("headrefforcepushedevent", of)

  fun <T : BaseRefForcePushedEvent> baserefforcepushedevent(init: () -> T): Stub<BaseRefForcePushedEvent, ArgBuilder> =
        stub()

  fun <T> baserefforcepushedevent(of: KFunction1<BaseRefForcePushedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<BaseRefForcePushedEvent, T>("baserefforcepushedevent", of)

  fun <T : ReviewRequestedEvent> reviewrequestedevent(init: () -> T): Stub<ReviewRequestedEvent, ArgBuilder> =
        stub()

  fun <T> reviewrequestedevent(of: KFunction1<ReviewRequestedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReviewRequestedEvent, T>("reviewrequestedevent", of)

  fun <T : ReviewRequestRemovedEvent> reviewrequestremovedevent(init: () -> T): Stub<ReviewRequestRemovedEvent, ArgBuilder> =
        stub()

  fun <T> reviewrequestremovedevent(of: KFunction1<ReviewRequestRemovedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReviewRequestRemovedEvent, T>("reviewrequestremovedevent", of)

  fun <T : ReviewDismissedEvent> reviewdismissedevent(init: () -> T): Stub<ReviewDismissedEvent, ArgBuilder> =
        stub()

  fun <T> reviewdismissedevent(of: KFunction1<ReviewDismissedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReviewDismissedEvent, T>("reviewdismissedevent", of)
}

interface ProjectCardItem : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue, ArgBuilder> = stub()

  fun <T> issue(of: KFunction1<Issue, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullrequest", of)
}

interface MilestoneItem : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue, ArgBuilder> = stub()

  fun <T> issue(of: KFunction1<Issue, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullrequest", of)
}

interface IssueTimelineItem : QType {
  fun <T : Commit> commit(init: () -> T): Stub<Commit, ArgBuilder> = stub()

  fun <T> commit(of: KFunction1<Commit, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Commit, T>("commit", of)

  fun <T : IssueComment> issuecomment(init: () -> T): Stub<IssueComment, ArgBuilder> = stub()

  fun <T> issuecomment(of: KFunction1<IssueComment, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<IssueComment, T>("issuecomment", of)

  fun <T : ClosedEvent> closedevent(init: () -> T): Stub<ClosedEvent, ArgBuilder> = stub()

  fun <T> closedevent(of: KFunction1<ClosedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ClosedEvent, T>("closedevent", of)

  fun <T : ReopenedEvent> reopenedevent(init: () -> T): Stub<ReopenedEvent, ArgBuilder> = stub()

  fun <T> reopenedevent(of: KFunction1<ReopenedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReopenedEvent, T>("reopenedevent", of)

  fun <T : SubscribedEvent> subscribedevent(init: () -> T): Stub<SubscribedEvent, ArgBuilder> =
        stub()

  fun <T> subscribedevent(of: KFunction1<SubscribedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<SubscribedEvent, T>("subscribedevent", of)

  fun <T : UnsubscribedEvent> unsubscribedevent(init: () -> T): Stub<UnsubscribedEvent, ArgBuilder> =
        stub()

  fun <T> unsubscribedevent(of: KFunction1<UnsubscribedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<UnsubscribedEvent, T>("unsubscribedevent", of)

  fun <T : ReferencedEvent> referencedevent(init: () -> T): Stub<ReferencedEvent, ArgBuilder> =
        stub()

  fun <T> referencedevent(of: KFunction1<ReferencedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<ReferencedEvent, T>("referencedevent", of)

  fun <T : AssignedEvent> assignedevent(init: () -> T): Stub<AssignedEvent, ArgBuilder> = stub()

  fun <T> assignedevent(of: KFunction1<AssignedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<AssignedEvent, T>("assignedevent", of)

  fun <T : UnassignedEvent> unassignedevent(init: () -> T): Stub<UnassignedEvent, ArgBuilder> =
        stub()

  fun <T> unassignedevent(of: KFunction1<UnassignedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<UnassignedEvent, T>("unassignedevent", of)

  fun <T : LabeledEvent> labeledevent(init: () -> T): Stub<LabeledEvent, ArgBuilder> = stub()

  fun <T> labeledevent(of: KFunction1<LabeledEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<LabeledEvent, T>("labeledevent", of)

  fun <T : UnlabeledEvent> unlabeledevent(init: () -> T): Stub<UnlabeledEvent, ArgBuilder> = stub()

  fun <T> unlabeledevent(of: KFunction1<UnlabeledEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<UnlabeledEvent, T>("unlabeledevent", of)

  fun <T : MilestonedEvent> milestonedevent(init: () -> T): Stub<MilestonedEvent, ArgBuilder> =
        stub()

  fun <T> milestonedevent(of: KFunction1<MilestonedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<MilestonedEvent, T>("milestonedevent", of)

  fun <T : DemilestonedEvent> demilestonedevent(init: () -> T): Stub<DemilestonedEvent, ArgBuilder> =
        stub()

  fun <T> demilestonedevent(of: KFunction1<DemilestonedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<DemilestonedEvent, T>("demilestonedevent", of)

  fun <T : RenamedTitleEvent> renamedtitleevent(init: () -> T): Stub<RenamedTitleEvent, ArgBuilder> =
        stub()

  fun <T> renamedtitleevent(of: KFunction1<RenamedTitleEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<RenamedTitleEvent, T>("renamedtitleevent", of)

  fun <T : LockedEvent> lockedevent(init: () -> T): Stub<LockedEvent, ArgBuilder> = stub()

  fun <T> lockedevent(of: KFunction1<LockedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<LockedEvent, T>("lockedevent", of)

  fun <T : UnlockedEvent> unlockedevent(init: () -> T): Stub<UnlockedEvent, ArgBuilder> = stub()

  fun <T> unlockedevent(of: KFunction1<UnlockedEvent, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<UnlockedEvent, T>("unlockedevent", of)
}

interface IssueOrPullRequest : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue, ArgBuilder> = stub()

  fun <T> issue(of: KFunction1<Issue, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest, ArgBuilder> = stub()

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T, ArgBuilder>>): Stub<T, ArgBuilder> =
        stub<PullRequest, T>("pullrequest", of)
}

interface X509Certificate : QType {
  fun value(): Stub<String, ArgBuilder> = stub()
}

interface URI : QType {
  fun value(): Stub<String, ArgBuilder> = stub()
}

interface HTML : QType {
  fun value(): Stub<String, ArgBuilder> = stub()
}

interface GitTimestamp : QType {
  fun value(): Stub<String, ArgBuilder> = stub()
}

interface GitObjectID : QType {
  fun value(): Stub<String, ArgBuilder> = stub()
}

interface DateTime : QType {
  fun value(): Stub<String, ArgBuilder> = stub()
}

data class UpdateTopicsInput(private val repositoryId: String,
    private val topicNames: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class UpdateSubscriptionInput(private val subscribableId: String,
    private val state: SubscriptionState) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class UpdatePullRequestReviewInput(private val pullRequestReviewId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class UpdatePullRequestReviewCommentInput(private val pullRequestReviewCommentId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class UpdateProjectInput(private val projectId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  private var state: ProjectState? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }

  fun body(value: String) = apply { body = value }

  fun state(value: ProjectState) = apply { state = value }
}

data class UpdateProjectColumnInput(private val projectColumnId: String,
    private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class UpdateProjectCardInput(private val projectCardId: String,
    private val note: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class TeamOrder(private val field: TeamOrderField,
    private val direction: OrderDirection) : QInput {
}

data class SubmitPullRequestReviewInput(private val pullRequestReviewId: String,
    private val event: PullRequestReviewEvent) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }

  fun body(value: String) = apply { body = value }
}

data class StarOrder(private val field: StarOrderField,
    private val direction: OrderDirection) : QInput {
}

data class RequestReviewsInput(private val pullRequestId: String, private val userIds: String,
    private val teamIds: String) : QInput {
  private var clientMutationId: String? = null
  private var union: Boolean? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }

  fun union(value: Boolean) = apply { union = value }
}

data class RepositoryOrder(private val field: RepositoryOrderField,
    private val direction: OrderDirection) : QInput {
}

data class RemoveStarInput(private val starrableId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class RemoveReactionInput(private val subjectId: String,
    private val content: ReactionContent) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class RemoveOutsideCollaboratorInput(private val userId: String,
    private val organizationId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class ReactionOrder(private val field: ReactionOrderField,
    private val direction: OrderDirection) : QInput {
}

data class ProjectOrder(private val field: ProjectOrderField,
    private val direction: OrderDirection) : QInput {
}

data class MoveProjectColumnInput(private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  private var afterColumnId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }

  fun afterColumnId(value: String) = apply { afterColumnId = value }
}

data class MoveProjectCardInput(private val cardId: String, private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  private var afterCardId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }

  fun afterCardId(value: String) = apply { afterCardId = value }
}

data class LanguageOrder(private val field: LanguageOrderField,
    private val direction: OrderDirection) : QInput {
}

data class IssueOrder(private val field: IssueOrderField,
    private val direction: OrderDirection) : QInput {
}

data class DraftPullRequestReviewComment(private val path: String, private val position: Int,
    private val body: String) : QInput {
}

data class DismissPullRequestReviewInput(private val pullRequestReviewId: String,
    private val message: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class DeletePullRequestReviewInput(private val pullRequestReviewId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class DeleteProjectInput(private val projectId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class DeleteProjectColumnInput(private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class DeleteProjectCardInput(private val cardId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class DeclineTopicSuggestionInput(private val repositoryId: String, private val name: String,
    private val reason: TopicSuggestionDeclineReason) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class CreateProjectInput(private val ownerId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }

  fun body(value: String) = apply { body = value }
}

data class CommitAuthor(private val emails: String) : QInput {
  private var id: String? = null
  fun id(value: String) = apply { id = value }
}

data class AddStarInput(private val starrableId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class AddReactionInput(private val subjectId: String,
    private val content: ReactionContent) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class AddPullRequestReviewInput(private val pullRequestId: String) : QInput {
  private var clientMutationId: String? = null
  private var commitOID: GitObjectID? = null
  private var body: String? = null
  private var event: PullRequestReviewEvent? = null
  private var comments: DraftPullRequestReviewComment? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }

  fun commitOID(value: GitObjectID) = apply { commitOID = value }

  fun body(value: String) = apply { body = value }

  fun event(value: PullRequestReviewEvent) = apply { event = value }

  fun comments(value: DraftPullRequestReviewComment) = apply { comments = value }
}

data class AddPullRequestReviewCommentInput(private val pullRequestReviewId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  private var commitOID: GitObjectID? = null
  private var path: String? = null
  private var position: Int? = null
  private var inReplyTo: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }

  fun commitOID(value: GitObjectID) = apply { commitOID = value }

  fun path(value: String) = apply { path = value }

  fun position(value: Int) = apply { position = value }

  fun inReplyTo(value: String) = apply { inReplyTo = value }
}

data class AddProjectColumnInput(private val projectId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class AddProjectCardInput(private val projectColumnId: String) : QInput {
  private var clientMutationId: String? = null
  private var contentId: String? = null
  private var note: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }

  fun contentId(value: String) = apply { contentId = value }

  fun note(value: String) = apply { note = value }
}

data class AddCommentInput(private val subjectId: String, private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

data class AcceptTopicSuggestionInput(private val repositoryId: String,
    private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(value: String) = apply { clientMutationId = value }
}

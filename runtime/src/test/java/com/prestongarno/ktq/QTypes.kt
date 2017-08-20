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
  fun cursor(): Stub<String> = stub()

  fun <T : User> node(init: () -> T): Stub<User> = stub(init)

  fun <T> node(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("node", of)
}

interface UserConnection : QType {
  fun <T : UserEdge> edges(init: () -> T): Stub<UserEdge> = stub(init)

  fun <T> edges(of: KFunction1<UserEdge, Stub<T>>): Stub<T> = stub<UserEdge, T>("edges", of)

  fun <T : User> nodes(init: () -> T): Stub<User> = stub(init)

  fun <T> nodes(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface User : QType {
  fun avatarUrl(): Stub<URI> = stub()

  fun bio(): Stub<String> = stub()

  fun bioHTML(): Stub<HTML> = stub()

  fun company(): Stub<String> = stub()

  fun companyHTML(): Stub<HTML> = stub()

  fun <T : RepositoryConnection> contributedRepositories(init: () -> T): Stub<RepositoryConnection> = stub(init)

  fun <T> contributedRepositories(of: KFunction1<RepositoryConnection, Stub<T>>): Stub<T> =
        stub<RepositoryConnection, T>("contributedRepositories", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun email(): Stub<String> = stub()

  fun <T : FollowerConnection> followers(init: () -> T): Stub<FollowerConnection> = stub(init)

  fun <T> followers(of: KFunction1<FollowerConnection, Stub<T>>): Stub<T> =
        stub<FollowerConnection, T>("followers", of)

  fun <T : FollowingConnection> following(init: () -> T): Stub<FollowingConnection> = stub(init)

  fun <T> following(of: KFunction1<FollowingConnection, Stub<T>>): Stub<T> =
        stub<FollowingConnection, T>("following", of)

  fun <T : Gist> gist(init: () -> T): Stub<Gist> = stub(init)

  fun <T> gist(of: KFunction1<Gist, Stub<T>>): Stub<T> = stub<Gist, T>("gist", of)

  fun <T : GistConnection> gists(init: () -> T): Stub<GistConnection> = stub(init)

  fun <T> gists(of: KFunction1<GistConnection, Stub<T>>): Stub<T> =
        stub<GistConnection, T>("gists", of)

  fun id(): Stub<String> = stub()

  fun isBountyHunter(): Stub<Boolean> = stub()

  fun isCampusExpert(): Stub<Boolean> = stub()

  fun isDeveloperProgramMember(): Stub<Boolean> = stub()

  fun isEmployee(): Stub<Boolean> = stub()

  fun isHireable(): Stub<Boolean> = stub()

  fun isInvoiced(): Stub<Boolean> = stub()

  fun isSiteAdmin(): Stub<Boolean> = stub()

  fun isViewer(): Stub<Boolean> = stub()

  fun <T : IssueConnection> issues(init: () -> T): Stub<IssueConnection> = stub(init)

  fun <T> issues(of: KFunction1<IssueConnection, Stub<T>>): Stub<T> =
        stub<IssueConnection, T>("issues", of)

  fun location(): Stub<String> = stub()

  fun login(): Stub<String> = stub()

  fun name(): Stub<String> = stub()

  fun <T : Organization> organization(init: () -> T): Stub<Organization> = stub(init)

  fun <T> organization(of: KFunction1<Organization, Stub<T>>): Stub<T> =
        stub<Organization, T>("organization", of)

  fun <T : OrganizationConnection> organizations(init: () -> T): Stub<OrganizationConnection> = stub(init)

  fun <T> organizations(of: KFunction1<OrganizationConnection, Stub<T>>): Stub<T> =
        stub<OrganizationConnection, T>("organizations", of)

  fun <T : RepositoryConnection> pinnedRepositories(init: () -> T): Stub<RepositoryConnection> = stub(init)

  fun <T> pinnedRepositories(of: KFunction1<RepositoryConnection, Stub<T>>): Stub<T> =
        stub<RepositoryConnection, T>("pinnedRepositories", of)

  fun <T : PullRequestConnection> pullRequests(init: () -> T): Stub<PullRequestConnection> = stub(init)

  fun <T> pullRequests(of: KFunction1<PullRequestConnection, Stub<T>>): Stub<T> =
        stub<PullRequestConnection, T>("pullRequests", of)

  fun <T : RepositoryConnection> repositories(init: () -> T): Stub<RepositoryConnection> = stub(init)

  fun <T> repositories(of: KFunction1<RepositoryConnection, Stub<T>>): Stub<T> =
        stub<RepositoryConnection, T>("repositories", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI> = stub()

  fun <T : StarredRepositoryConnection> starredRepositories(init: () -> T): Stub<StarredRepositoryConnection> = stub(init)

  fun <T> starredRepositories(of: KFunction1<StarredRepositoryConnection, Stub<T>>): Stub<T> =
        stub<StarredRepositoryConnection, T>("starredRepositories", of)

  fun updatedAt(): Stub<DateTime> = stub()

  fun url(): Stub<URI> = stub()

  fun viewerCanFollow(): Stub<Boolean> = stub()

  fun viewerIsFollowing(): Stub<Boolean> = stub()

  fun <T : RepositoryConnection> watching(init: () -> T): Stub<RepositoryConnection> = stub(init)

  fun <T> watching(of: KFunction1<RepositoryConnection, Stub<T>>): Stub<T> =
        stub<RepositoryConnection, T>("watching", of)

  fun websiteUrl(): Stub<URI> = stub()

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
      return apply { addArg("size", value) }
    }
  }

  class ContributedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ContributedRepositoriesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ContributedRepositoriesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ContributedRepositoriesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ContributedRepositoriesArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: RepositoryPrivacy): ContributedRepositoriesArgs {
      return apply { addArg("privacy", value) }
    }

    fun orderBy(value: RepositoryOrder): ContributedRepositoriesArgs {
      return apply { addArg("orderBy", value) }
    }

    fun affiliations(value: RepositoryAffiliation): ContributedRepositoriesArgs {
      return apply { addArg("affiliations", value) }
    }

    fun isLocked(value: Boolean): ContributedRepositoriesArgs {
      return apply { addArg("isLocked", value) }
    }
  }

  class FollowersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): FollowersArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): FollowersArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): FollowersArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): FollowersArgs {
      return apply { addArg("before", value) }
    }
  }

  class FollowingArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): FollowingArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): FollowingArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): FollowingArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): FollowingArgs {
      return apply { addArg("before", value) }
    }
  }

  class GistArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): GistArgs {
      return apply { addArg("name", value) }
    }
  }

  class GistsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): GistsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): GistsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): GistsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): GistsArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: GistPrivacy): GistsArgs {
      return apply { addArg("privacy", value) }
    }
  }

  class IssuesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): IssuesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): IssuesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): IssuesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): IssuesArgs {
      return apply { addArg("before", value) }
    }

    fun labels(value: String): IssuesArgs {
      return apply { addArg("labels", value) }
    }

    fun orderBy(value: IssueOrder): IssuesArgs {
      return apply { addArg("orderBy", value) }
    }

    fun states(value: IssueState): IssuesArgs {
      return apply { addArg("states", value) }
    }
  }

  class OrganizationArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): OrganizationArgs {
      return apply { addArg("login", value) }
    }
  }

  class OrganizationsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): OrganizationsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): OrganizationsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): OrganizationsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): OrganizationsArgs {
      return apply { addArg("before", value) }
    }
  }

  class PinnedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PinnedRepositoriesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): PinnedRepositoriesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): PinnedRepositoriesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): PinnedRepositoriesArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs {
      return apply { addArg("privacy", value) }
    }

    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs {
      return apply { addArg("orderBy", value) }
    }

    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs {
      return apply { addArg("affiliations", value) }
    }

    fun isLocked(value: Boolean): PinnedRepositoriesArgs {
      return apply { addArg("isLocked", value) }
    }
  }

  class PullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PullRequestsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): PullRequestsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): PullRequestsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): PullRequestsArgs {
      return apply { addArg("before", value) }
    }

    fun states(value: PullRequestState): PullRequestsArgs {
      return apply { addArg("states", value) }
    }

    fun labels(value: String): PullRequestsArgs {
      return apply { addArg("labels", value) }
    }

    fun headRefName(value: String): PullRequestsArgs {
      return apply { addArg("headRefName", value) }
    }

    fun baseRefName(value: String): PullRequestsArgs {
      return apply { addArg("baseRefName", value) }
    }

    fun orderBy(value: IssueOrder): PullRequestsArgs {
      return apply { addArg("orderBy", value) }
    }
  }

  class RepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoriesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): RepositoriesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): RepositoriesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): RepositoriesArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: RepositoryPrivacy): RepositoriesArgs {
      return apply { addArg("privacy", value) }
    }

    fun orderBy(value: RepositoryOrder): RepositoriesArgs {
      return apply { addArg("orderBy", value) }
    }

    fun affiliations(value: RepositoryAffiliation): RepositoriesArgs {
      return apply { addArg("affiliations", value) }
    }

    fun isLocked(value: Boolean): RepositoriesArgs {
      return apply { addArg("isLocked", value) }
    }

    fun isFork(value: Boolean): RepositoriesArgs {
      return apply { addArg("isFork", value) }
    }
  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): RepositoryArgs {
      return apply { addArg("name", value) }
    }
  }

  class StarredRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StarredRepositoriesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): StarredRepositoriesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): StarredRepositoriesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): StarredRepositoriesArgs {
      return apply { addArg("before", value) }
    }

    fun ownedByViewer(value: Boolean): StarredRepositoriesArgs {
      return apply { addArg("ownedByViewer", value) }
    }

    fun orderBy(value: StarOrder): StarredRepositoriesArgs {
      return apply { addArg("orderBy", value) }
    }
  }

  class WatchingArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): WatchingArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): WatchingArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): WatchingArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): WatchingArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: RepositoryPrivacy): WatchingArgs {
      return apply { addArg("privacy", value) }
    }

    fun orderBy(value: RepositoryOrder): WatchingArgs {
      return apply { addArg("orderBy", value) }
    }

    fun affiliations(value: RepositoryAffiliation): WatchingArgs {
      return apply { addArg("affiliations", value) }
    }

    fun isLocked(value: Boolean): WatchingArgs {
      return apply { addArg("isLocked", value) }
    }
  }
}

interface UpdateTopicsPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun invalidTopicNames(): Stub<String> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)
}

interface UpdateSubscriptionPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : Subscribable> subscribable(init: () -> T): Stub<Subscribable> = stub(init)

  fun <T> subscribable(of: KFunction1<Subscribable, Stub<T>>): Stub<T> =
        stub<Subscribable, T>("subscribable", of)
}

interface UpdatePullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview> = stub(init)

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T>>): Stub<T> =
        stub<PullRequestReview, T>("pullRequestReview", of)
}

interface UpdatePullRequestReviewCommentPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : PullRequestReviewComment> pullRequestReviewComment(init: () -> T): Stub<PullRequestReviewComment> = stub(init)

  fun <T> pullRequestReviewComment(of: KFunction1<PullRequestReviewComment, Stub<T>>): Stub<T> =
        stub<PullRequestReviewComment, T>("pullRequestReviewComment", of)
}

interface UpdateProjectPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : Project> project(init: () -> T): Stub<Project> = stub(init)

  fun <T> project(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("project", of)
}

interface UpdateProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : ProjectColumn> projectColumn(init: () -> T): Stub<ProjectColumn> = stub(init)

  fun <T> projectColumn(of: KFunction1<ProjectColumn, Stub<T>>): Stub<T> =
        stub<ProjectColumn, T>("projectColumn", of)
}

interface UpdateProjectCardPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : ProjectCard> projectCard(init: () -> T): Stub<ProjectCard> = stub(init)

  fun <T> projectCard(of: KFunction1<ProjectCard, Stub<T>>): Stub<T> =
        stub<ProjectCard, T>("projectCard", of)
}

interface UnsubscribedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : Subscribable> subscribable(init: () -> T): Stub<Subscribable> = stub(init)

  fun <T> subscribable(of: KFunction1<Subscribable, Stub<T>>): Stub<T> =
        stub<Subscribable, T>("subscribable", of)
}

interface UnlockedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : Lockable> lockable(init: () -> T): Stub<Lockable> = stub(init)

  fun <T> lockable(of: KFunction1<Lockable, Stub<T>>): Stub<T> = stub<Lockable, T>("lockable", of)
}

interface UnlabeledEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : Label> label(init: () -> T): Stub<Label> = stub(init)

  fun <T> label(of: KFunction1<Label, Stub<T>>): Stub<T> = stub<Label, T>("label", of)

  fun <T : Labelable> labelable(init: () -> T): Stub<Labelable> = stub(init)

  fun <T> labelable(of: KFunction1<Labelable, Stub<T>>): Stub<T> =
        stub<Labelable, T>("labelable", of)
}

interface UnknownSignature : QType {
  fun email(): Stub<String> = stub()

  fun isValid(): Stub<Boolean> = stub()

  fun payload(): Stub<String> = stub()

  fun signature(): Stub<String> = stub()

  fun <T : User> signer(init: () -> T): Stub<User> = stub(init)

  fun <T> signer(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("signer", of)

  fun state(): Stub<GitSignatureState> = stub()
}

interface UnassignedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun <T : Assignable> assignable(init: () -> T): Stub<Assignable> = stub(init)

  fun <T> assignable(of: KFunction1<Assignable, Stub<T>>): Stub<T> =
        stub<Assignable, T>("assignable", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : User> user(init: () -> T): Stub<User> = stub(init)

  fun <T> user(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("user", of)
}

interface TreeEntry : QType {
  fun mode(): Stub<Int> = stub()

  fun name(): Stub<String> = stub()

  fun <T : GitObject> objectVal(init: () -> T): Stub<GitObject> = stub(init)

  fun <T> objectVal(of: KFunction1<GitObject, Stub<T>>): Stub<T> =
        stub<GitObject, T>("objectVal", of)

  fun oid(): Stub<GitObjectID> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun type(): Stub<String> = stub()
}

interface Tree : QType {
  fun abbreviatedOid(): Stub<String> = stub()

  fun commitResourcePath(): Stub<URI> = stub()

  fun commitUrl(): Stub<URI> = stub()

  fun <T : TreeEntry> entries(init: () -> T): Stub<TreeEntry> = stub(init)

  fun <T> entries(of: KFunction1<TreeEntry, Stub<T>>): Stub<T> = stub<TreeEntry, T>("entries", of)

  fun id(): Stub<String> = stub()

  fun oid(): Stub<GitObjectID> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)
}

interface Topic : QType {
  fun id(): Stub<String> = stub()

  fun name(): Stub<String> = stub()

  fun <T : Topic> relatedTopics(init: () -> T): Stub<Topic> = stub(init)

  fun <T> relatedTopics(of: KFunction1<Topic, Stub<T>>): Stub<T> =
        stub<Topic, T>("relatedTopics", of)
}

interface TeamEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Team> node(init: () -> T): Stub<Team> = stub(init)

  fun <T> node(of: KFunction1<Team, Stub<T>>): Stub<T> = stub<Team, T>("node", of)
}

interface TeamConnection : QType {
  fun <T : TeamEdge> edges(init: () -> T): Stub<TeamEdge> = stub(init)

  fun <T> edges(of: KFunction1<TeamEdge, Stub<T>>): Stub<T> = stub<TeamEdge, T>("edges", of)

  fun <T : Team> nodes(init: () -> T): Stub<Team> = stub(init)

  fun <T> nodes(of: KFunction1<Team, Stub<T>>): Stub<T> = stub<Team, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface Team : QType {
  fun description(): Stub<String> = stub()

  fun editTeamResourcePath(): Stub<URI> = stub()

  fun editTeamUrl(): Stub<URI> = stub()

  fun id(): Stub<String> = stub()

  fun <T : OrganizationInvitationConnection> invitations(init: () -> T): Stub<OrganizationInvitationConnection> = stub(init)

  fun <T> invitations(of: KFunction1<OrganizationInvitationConnection, Stub<T>>): Stub<T> =
        stub<OrganizationInvitationConnection, T>("invitations", of)

  fun name(): Stub<String> = stub()

  fun <T : Organization> organization(init: () -> T): Stub<Organization> = stub(init)

  fun <T> organization(of: KFunction1<Organization, Stub<T>>): Stub<T> =
        stub<Organization, T>("organization", of)

  fun privacy(): Stub<TeamPrivacy> = stub()

  fun resourcePath(): Stub<URI> = stub()

  fun slug(): Stub<String> = stub()

  fun url(): Stub<URI> = stub()

  class InvitationsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): InvitationsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): InvitationsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): InvitationsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): InvitationsArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface Tag : QType {
  fun abbreviatedOid(): Stub<String> = stub()

  fun commitResourcePath(): Stub<URI> = stub()

  fun commitUrl(): Stub<URI> = stub()

  fun id(): Stub<String> = stub()

  fun message(): Stub<String> = stub()

  fun name(): Stub<String> = stub()

  fun oid(): Stub<GitObjectID> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun <T : GitActor> tagger(init: () -> T): Stub<GitActor> = stub(init)

  fun <T> tagger(of: KFunction1<GitActor, Stub<T>>): Stub<T> = stub<GitActor, T>("tagger", of)

  fun <T : GitObject> target(init: () -> T): Stub<GitObject> = stub(init)

  fun <T> target(of: KFunction1<GitObject, Stub<T>>): Stub<T> = stub<GitObject, T>("target", of)
}

interface SuggestedReviewer : QType {
  fun isAuthor(): Stub<Boolean> = stub()

  fun isCommenter(): Stub<Boolean> = stub()

  fun <T : User> reviewer(init: () -> T): Stub<User> = stub(init)

  fun <T> reviewer(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("reviewer", of)
}

interface SubscribedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : Subscribable> subscribable(init: () -> T): Stub<Subscribable> = stub(init)

  fun <T> subscribable(of: KFunction1<Subscribable, Stub<T>>): Stub<T> =
        stub<Subscribable, T>("subscribable", of)
}

interface SubmitPullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview> = stub(init)

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T>>): Stub<T> =
        stub<PullRequestReview, T>("pullRequestReview", of)
}

interface StatusContext : QType {
  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun context(): Stub<String> = stub()

  fun createdAt(): Stub<DateTime> = stub()

  fun <T : Actor> creator(init: () -> T): Stub<Actor> = stub(init)

  fun <T> creator(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("creator", of)

  fun description(): Stub<String> = stub()

  fun id(): Stub<String> = stub()

  fun state(): Stub<StatusState> = stub()

  fun targetUrl(): Stub<URI> = stub()
}

interface Status : QType {
  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun <T : StatusContext> context(init: () -> T): Stub<StatusContext> = stub(init)

  fun <T> context(of: KFunction1<StatusContext, Stub<T>>): Stub<T> =
        stub<StatusContext, T>("context", of)

  fun <T : StatusContext> contexts(init: () -> T): Stub<StatusContext> = stub(init)

  fun <T> contexts(of: KFunction1<StatusContext, Stub<T>>): Stub<T> =
        stub<StatusContext, T>("contexts", of)

  fun id(): Stub<String> = stub()

  fun state(): Stub<StatusState> = stub()

  class ContextArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): ContextArgs {
      return apply { addArg("name", value) }
    }
  }
}

interface StarredRepositoryEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Repository> node(init: () -> T): Stub<Repository> = stub(init)

  fun <T> node(of: KFunction1<Repository, Stub<T>>): Stub<T> = stub<Repository, T>("node", of)

  fun starredAt(): Stub<DateTime> = stub()
}

interface StarredRepositoryConnection : QType {
  fun <T : StarredRepositoryEdge> edges(init: () -> T): Stub<StarredRepositoryEdge> = stub(init)

  fun <T> edges(of: KFunction1<StarredRepositoryEdge, Stub<T>>): Stub<T> =
        stub<StarredRepositoryEdge, T>("edges", of)

  fun <T : Repository> nodes(init: () -> T): Stub<Repository> = stub(init)

  fun <T> nodes(of: KFunction1<Repository, Stub<T>>): Stub<T> = stub<Repository, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface StargazerEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : User> node(init: () -> T): Stub<User> = stub(init)

  fun <T> node(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("node", of)

  fun starredAt(): Stub<DateTime> = stub()
}

interface StargazerConnection : QType {
  fun <T : StargazerEdge> edges(init: () -> T): Stub<StargazerEdge> = stub(init)

  fun <T> edges(of: KFunction1<StargazerEdge, Stub<T>>): Stub<T> =
        stub<StargazerEdge, T>("edges", of)

  fun <T : User> nodes(init: () -> T): Stub<User> = stub(init)

  fun <T> nodes(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface SmimeSignature : QType {
  fun email(): Stub<String> = stub()

  fun isValid(): Stub<Boolean> = stub()

  fun payload(): Stub<String> = stub()

  fun signature(): Stub<String> = stub()

  fun <T : User> signer(init: () -> T): Stub<User> = stub(init)

  fun <T> signer(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("signer", of)

  fun state(): Stub<GitSignatureState> = stub()
}

interface SearchResultItemEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : SearchResultItem> node(init: () -> T): Stub<SearchResultItem> = stub(init)

  fun <T> node(of: KFunction1<SearchResultItem, Stub<T>>): Stub<T> =
        stub<SearchResultItem, T>("node", of)
}

interface SearchResultItemConnection : QType {
  fun codeCount(): Stub<Int> = stub()

  fun <T : SearchResultItemEdge> edges(init: () -> T): Stub<SearchResultItemEdge> = stub(init)

  fun <T> edges(of: KFunction1<SearchResultItemEdge, Stub<T>>): Stub<T> =
        stub<SearchResultItemEdge, T>("edges", of)

  fun issueCount(): Stub<Int> = stub()

  fun <T : SearchResultItem> nodes(init: () -> T): Stub<SearchResultItem> = stub(init)

  fun <T> nodes(of: KFunction1<SearchResultItem, Stub<T>>): Stub<T> =
        stub<SearchResultItem, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun repositoryCount(): Stub<Int> = stub()

  fun userCount(): Stub<Int> = stub()

  fun wikiCount(): Stub<Int> = stub()
}

interface ReviewRequestedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : User> subject(init: () -> T): Stub<User> = stub(init)

  fun <T> subject(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("subject", of)
}

interface ReviewRequestRemovedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : User> subject(init: () -> T): Stub<User> = stub(init)

  fun <T> subject(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("subject", of)
}

interface ReviewRequestEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : ReviewRequest> node(init: () -> T): Stub<ReviewRequest> = stub(init)

  fun <T> node(of: KFunction1<ReviewRequest, Stub<T>>): Stub<T> = stub<ReviewRequest, T>("node", of)
}

interface ReviewRequestConnection : QType {
  fun <T : ReviewRequestEdge> edges(init: () -> T): Stub<ReviewRequestEdge> = stub(init)

  fun <T> edges(of: KFunction1<ReviewRequestEdge, Stub<T>>): Stub<T> =
        stub<ReviewRequestEdge, T>("edges", of)

  fun <T : ReviewRequest> nodes(init: () -> T): Stub<ReviewRequest> = stub(init)

  fun <T> nodes(of: KFunction1<ReviewRequest, Stub<T>>): Stub<T> =
        stub<ReviewRequest, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface ReviewRequest : QType {
  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : User> reviewer(init: () -> T): Stub<User> = stub(init)

  fun <T> reviewer(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("reviewer", of)
}

interface ReviewDismissedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()

  fun message(): Stub<String> = stub()

  fun messageHtml(): Stub<HTML> = stub()

  fun previousReviewState(): Stub<PullRequestReviewState> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : PullRequestCommit> pullRequestCommit(init: () -> T): Stub<PullRequestCommit> = stub(init)

  fun <T> pullRequestCommit(of: KFunction1<PullRequestCommit, Stub<T>>): Stub<T> =
        stub<PullRequestCommit, T>("pullRequestCommit", of)

  fun resourcePath(): Stub<URI> = stub()

  fun <T : PullRequestReview> review(init: () -> T): Stub<PullRequestReview> = stub(init)

  fun <T> review(of: KFunction1<PullRequestReview, Stub<T>>): Stub<T> =
        stub<PullRequestReview, T>("review", of)

  fun url(): Stub<URI> = stub()
}

interface ReviewDismissalAllowanceEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : ReviewDismissalAllowance> node(init: () -> T): Stub<ReviewDismissalAllowance> = stub(init)

  fun <T> node(of: KFunction1<ReviewDismissalAllowance, Stub<T>>): Stub<T> =
        stub<ReviewDismissalAllowance, T>("node", of)
}

interface ReviewDismissalAllowanceConnection : QType {
  fun <T : ReviewDismissalAllowanceEdge> edges(init: () -> T): Stub<ReviewDismissalAllowanceEdge> = stub(init)

  fun <T> edges(of: KFunction1<ReviewDismissalAllowanceEdge, Stub<T>>): Stub<T> =
        stub<ReviewDismissalAllowanceEdge, T>("edges", of)

  fun <T : ReviewDismissalAllowance> nodes(init: () -> T): Stub<ReviewDismissalAllowance> = stub(init)

  fun <T> nodes(of: KFunction1<ReviewDismissalAllowance, Stub<T>>): Stub<T> =
        stub<ReviewDismissalAllowance, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface ReviewDismissalAllowance : QType {
  fun <T : ReviewDismissalAllowanceActor> actor(init: () -> T): Stub<ReviewDismissalAllowanceActor> = stub(init)

  fun <T> actor(of: KFunction1<ReviewDismissalAllowanceActor, Stub<T>>): Stub<T> =
        stub<ReviewDismissalAllowanceActor, T>("actor", of)

  fun id(): Stub<String> = stub()

  fun <T : ProtectedBranch> protectedBranch(init: () -> T): Stub<ProtectedBranch> = stub(init)

  fun <T> protectedBranch(of: KFunction1<ProtectedBranch, Stub<T>>): Stub<T> =
        stub<ProtectedBranch, T>("protectedBranch", of)
}

interface RequestReviewsPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : UserEdge> requestedReviewersEdge(init: () -> T): Stub<UserEdge> = stub(init)

  fun <T> requestedReviewersEdge(of: KFunction1<UserEdge, Stub<T>>): Stub<T> =
        stub<UserEdge, T>("requestedReviewersEdge", of)
}

interface RepositoryTopicEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : RepositoryTopic> node(init: () -> T): Stub<RepositoryTopic> = stub(init)

  fun <T> node(of: KFunction1<RepositoryTopic, Stub<T>>): Stub<T> =
        stub<RepositoryTopic, T>("node", of)
}

interface RepositoryTopicConnection : QType {
  fun <T : RepositoryTopicEdge> edges(init: () -> T): Stub<RepositoryTopicEdge> = stub(init)

  fun <T> edges(of: KFunction1<RepositoryTopicEdge, Stub<T>>): Stub<T> =
        stub<RepositoryTopicEdge, T>("edges", of)

  fun <T : RepositoryTopic> nodes(init: () -> T): Stub<RepositoryTopic> = stub(init)

  fun <T> nodes(of: KFunction1<RepositoryTopic, Stub<T>>): Stub<T> =
        stub<RepositoryTopic, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface RepositoryTopic : QType {
  fun id(): Stub<String> = stub()

  fun resourcePath(): Stub<URI> = stub()

  fun <T : Topic> topic(init: () -> T): Stub<Topic> = stub(init)

  fun <T> topic(of: KFunction1<Topic, Stub<T>>): Stub<T> = stub<Topic, T>("topic", of)

  fun url(): Stub<URI> = stub()
}

interface RepositoryInvitationRepository : QType {
  fun createdAt(): Stub<DateTime> = stub()

  fun description(): Stub<String> = stub()

  fun descriptionHTML(): Stub<HTML> = stub()

  fun hasIssuesEnabled(): Stub<Boolean> = stub()

  fun hasWikiEnabled(): Stub<Boolean> = stub()

  fun homepageUrl(): Stub<URI> = stub()

  fun isFork(): Stub<Boolean> = stub()

  fun isLocked(): Stub<Boolean> = stub()

  fun isMirror(): Stub<Boolean> = stub()

  fun isPrivate(): Stub<Boolean> = stub()

  fun license(): Stub<String> = stub()

  fun lockReason(): Stub<RepositoryLockReason> = stub()

  fun mirrorUrl(): Stub<URI> = stub()

  fun name(): Stub<String> = stub()

  fun nameWithOwner(): Stub<String> = stub()

  fun <T : RepositoryOwner> owner(init: () -> T): Stub<RepositoryOwner> = stub(init)

  fun <T> owner(of: KFunction1<RepositoryOwner, Stub<T>>): Stub<T> =
        stub<RepositoryOwner, T>("owner", of)

  fun pushedAt(): Stub<DateTime> = stub()

  fun resourcePath(): Stub<URI> = stub()

  fun updatedAt(): Stub<DateTime> = stub()

  fun url(): Stub<URI> = stub()
}

interface RepositoryInvitation : QType {
  fun id(): Stub<String> = stub()

  fun <T : User> invitee(init: () -> T): Stub<User> = stub(init)

  fun <T> invitee(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("invitee", of)

  fun <T : User> inviter(init: () -> T): Stub<User> = stub(init)

  fun <T> inviter(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("inviter", of)

  fun <T : RepositoryInvitationRepository> repository(init: () -> T): Stub<RepositoryInvitationRepository> = stub(init)

  fun <T> repository(of: KFunction1<RepositoryInvitationRepository, Stub<T>>): Stub<T> =
        stub<RepositoryInvitationRepository, T>("repository", of)
}

interface RepositoryEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Repository> node(init: () -> T): Stub<Repository> = stub(init)

  fun <T> node(of: KFunction1<Repository, Stub<T>>): Stub<T> = stub<Repository, T>("node", of)
}

interface RepositoryConnection : QType {
  fun <T : RepositoryEdge> edges(init: () -> T): Stub<RepositoryEdge> = stub(init)

  fun <T> edges(of: KFunction1<RepositoryEdge, Stub<T>>): Stub<T> =
        stub<RepositoryEdge, T>("edges", of)

  fun <T : Repository> nodes(init: () -> T): Stub<Repository> = stub(init)

  fun <T> nodes(of: KFunction1<Repository, Stub<T>>): Stub<T> = stub<Repository, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()

  fun totalDiskUsage(): Stub<Int> = stub()
}

interface Repository : QType {
  fun <T : CodeOfConduct> codeOfConduct(init: () -> T): Stub<CodeOfConduct> = stub(init)

  fun <T> codeOfConduct(of: KFunction1<CodeOfConduct, Stub<T>>): Stub<T> =
        stub<CodeOfConduct, T>("codeOfConduct", of)

  fun <T : CommitCommentConnection> commitComments(init: () -> T): Stub<CommitCommentConnection> = stub(init)

  fun <T> commitComments(of: KFunction1<CommitCommentConnection, Stub<T>>): Stub<T> =
        stub<CommitCommentConnection, T>("commitComments", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun <T : Ref> defaultBranchRef(init: () -> T): Stub<Ref> = stub(init)

  fun <T> defaultBranchRef(of: KFunction1<Ref, Stub<T>>): Stub<T> =
        stub<Ref, T>("defaultBranchRef", of)

  fun <T : DeploymentConnection> deployments(init: () -> T): Stub<DeploymentConnection> = stub(init)

  fun <T> deployments(of: KFunction1<DeploymentConnection, Stub<T>>): Stub<T> =
        stub<DeploymentConnection, T>("deployments", of)

  fun description(): Stub<String> = stub()

  fun descriptionHTML(): Stub<HTML> = stub()

  fun diskUsage(): Stub<Int> = stub()

  fun <T : RepositoryConnection> forks(init: () -> T): Stub<RepositoryConnection> = stub(init)

  fun <T> forks(of: KFunction1<RepositoryConnection, Stub<T>>): Stub<T> =
        stub<RepositoryConnection, T>("forks", of)

  fun hasIssuesEnabled(): Stub<Boolean> = stub()

  fun hasWikiEnabled(): Stub<Boolean> = stub()

  fun homepageUrl(): Stub<URI> = stub()

  fun id(): Stub<String> = stub()

  fun isFork(): Stub<Boolean> = stub()

  fun isLocked(): Stub<Boolean> = stub()

  fun isMirror(): Stub<Boolean> = stub()

  fun isPrivate(): Stub<Boolean> = stub()

  fun <T : Issue> issue(init: () -> T): Stub<Issue> = stub(init)

  fun <T> issue(of: KFunction1<Issue, Stub<T>>): Stub<T> = stub<Issue, T>("issue", of)

  fun <T : IssueOrPullRequest> issueOrPullRequest(init: () -> T): Stub<IssueOrPullRequest> = stub(init)

  fun <T> issueOrPullRequest(of: KFunction1<IssueOrPullRequest, Stub<T>>): Stub<T> =
        stub<IssueOrPullRequest, T>("issueOrPullRequest", of)

  fun <T : IssueConnection> issues(init: () -> T): Stub<IssueConnection> = stub(init)

  fun <T> issues(of: KFunction1<IssueConnection, Stub<T>>): Stub<T> =
        stub<IssueConnection, T>("issues", of)

  fun <T : Label> label(init: () -> T): Stub<Label> = stub(init)

  fun <T> label(of: KFunction1<Label, Stub<T>>): Stub<T> = stub<Label, T>("label", of)

  fun <T : LabelConnection> labels(init: () -> T): Stub<LabelConnection> = stub(init)

  fun <T> labels(of: KFunction1<LabelConnection, Stub<T>>): Stub<T> =
        stub<LabelConnection, T>("labels", of)

  fun <T : LanguageConnection> languages(init: () -> T): Stub<LanguageConnection> = stub(init)

  fun <T> languages(of: KFunction1<LanguageConnection, Stub<T>>): Stub<T> =
        stub<LanguageConnection, T>("languages", of)

  fun license(): Stub<String> = stub()

  fun lockReason(): Stub<RepositoryLockReason> = stub()

  fun <T : UserConnection> mentionableUsers(init: () -> T): Stub<UserConnection> = stub(init)

  fun <T> mentionableUsers(of: KFunction1<UserConnection, Stub<T>>): Stub<T> =
        stub<UserConnection, T>("mentionableUsers", of)

  fun <T : Milestone> milestone(init: () -> T): Stub<Milestone> = stub(init)

  fun <T> milestone(of: KFunction1<Milestone, Stub<T>>): Stub<T> =
        stub<Milestone, T>("milestone", of)

  fun <T : MilestoneConnection> milestones(init: () -> T): Stub<MilestoneConnection> = stub(init)

  fun <T> milestones(of: KFunction1<MilestoneConnection, Stub<T>>): Stub<T> =
        stub<MilestoneConnection, T>("milestones", of)

  fun mirrorUrl(): Stub<URI> = stub()

  fun name(): Stub<String> = stub()

  fun nameWithOwner(): Stub<String> = stub()

  fun <T : GitObject> objectVal(init: () -> T): Stub<GitObject> = stub(init)

  fun <T> objectVal(of: KFunction1<GitObject, Stub<T>>): Stub<T> =
        stub<GitObject, T>("objectVal", of)

  fun <T : RepositoryOwner> owner(init: () -> T): Stub<RepositoryOwner> = stub(init)

  fun <T> owner(of: KFunction1<RepositoryOwner, Stub<T>>): Stub<T> =
        stub<RepositoryOwner, T>("owner", of)

  fun <T : Repository> parent(init: () -> T): Stub<Repository> = stub(init)

  fun <T> parent(of: KFunction1<Repository, Stub<T>>): Stub<T> = stub<Repository, T>("parent", of)

  fun <T : Language> primaryLanguage(init: () -> T): Stub<Language> = stub(init)

  fun <T> primaryLanguage(of: KFunction1<Language, Stub<T>>): Stub<T> =
        stub<Language, T>("primaryLanguage", of)

  fun <T : Project> project(init: () -> T): Stub<Project> = stub(init)

  fun <T> project(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("project", of)

  fun <T : ProjectConnection> projects(init: () -> T): Stub<ProjectConnection> = stub(init)

  fun <T> projects(of: KFunction1<ProjectConnection, Stub<T>>): Stub<T> =
        stub<ProjectConnection, T>("projects", of)

  fun projectsResourcePath(): Stub<URI> = stub()

  fun projectsUrl(): Stub<URI> = stub()

  fun <T : ProtectedBranchConnection> protectedBranches(init: () -> T): Stub<ProtectedBranchConnection> = stub(init)

  fun <T> protectedBranches(of: KFunction1<ProtectedBranchConnection, Stub<T>>): Stub<T> =
        stub<ProtectedBranchConnection, T>("protectedBranches", of)

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : PullRequestConnection> pullRequests(init: () -> T): Stub<PullRequestConnection> = stub(init)

  fun <T> pullRequests(of: KFunction1<PullRequestConnection, Stub<T>>): Stub<T> =
        stub<PullRequestConnection, T>("pullRequests", of)

  fun pushedAt(): Stub<DateTime> = stub()

  fun <T : Ref> ref(init: () -> T): Stub<Ref> = stub(init)

  fun <T> ref(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("ref", of)

  fun <T : RefConnection> refs(init: () -> T): Stub<RefConnection> = stub(init)

  fun <T> refs(of: KFunction1<RefConnection, Stub<T>>): Stub<T> = stub<RefConnection, T>("refs", of)

  fun <T : ReleaseConnection> releases(init: () -> T): Stub<ReleaseConnection> = stub(init)

  fun <T> releases(of: KFunction1<ReleaseConnection, Stub<T>>): Stub<T> =
        stub<ReleaseConnection, T>("releases", of)

  fun <T : RepositoryTopicConnection> repositoryTopics(init: () -> T): Stub<RepositoryTopicConnection> = stub(init)

  fun <T> repositoryTopics(of: KFunction1<RepositoryTopicConnection, Stub<T>>): Stub<T> =
        stub<RepositoryTopicConnection, T>("repositoryTopics", of)

  fun resourcePath(): Stub<URI> = stub()

  fun <T : StargazerConnection> stargazers(init: () -> T): Stub<StargazerConnection> = stub(init)

  fun <T> stargazers(of: KFunction1<StargazerConnection, Stub<T>>): Stub<T> =
        stub<StargazerConnection, T>("stargazers", of)

  fun updatedAt(): Stub<DateTime> = stub()

  fun url(): Stub<URI> = stub()

  fun viewerCanAdminister(): Stub<Boolean> = stub()

  fun viewerCanCreateProjects(): Stub<Boolean> = stub()

  fun viewerCanSubscribe(): Stub<Boolean> = stub()

  fun viewerCanUpdateTopics(): Stub<Boolean> = stub()

  fun viewerHasStarred(): Stub<Boolean> = stub()

  fun viewerSubscription(): Stub<SubscriptionState> = stub()

  fun <T : UserConnection> watchers(init: () -> T): Stub<UserConnection> = stub(init)

  fun <T> watchers(of: KFunction1<UserConnection, Stub<T>>): Stub<T> =
        stub<UserConnection, T>("watchers", of)

  class CommitCommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommitCommentsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): CommitCommentsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): CommitCommentsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): CommitCommentsArgs {
      return apply { addArg("before", value) }
    }
  }

  class DeploymentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): DeploymentsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): DeploymentsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): DeploymentsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): DeploymentsArgs {
      return apply { addArg("before", value) }
    }
  }

  class ForksArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ForksArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ForksArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ForksArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ForksArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: RepositoryPrivacy): ForksArgs {
      return apply { addArg("privacy", value) }
    }

    fun orderBy(value: RepositoryOrder): ForksArgs {
      return apply { addArg("orderBy", value) }
    }

    fun affiliations(value: RepositoryAffiliation): ForksArgs {
      return apply { addArg("affiliations", value) }
    }

    fun isLocked(value: Boolean): ForksArgs {
      return apply { addArg("isLocked", value) }
    }
  }

  class IssueArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): IssueArgs {
      return apply { addArg("number", value) }
    }
  }

  class IssueOrPullRequestArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): IssueOrPullRequestArgs {
      return apply { addArg("number", value) }
    }
  }

  class IssuesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): IssuesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): IssuesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): IssuesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): IssuesArgs {
      return apply { addArg("before", value) }
    }

    fun labels(value: String): IssuesArgs {
      return apply { addArg("labels", value) }
    }

    fun orderBy(value: IssueOrder): IssuesArgs {
      return apply { addArg("orderBy", value) }
    }

    fun states(value: IssueState): IssuesArgs {
      return apply { addArg("states", value) }
    }
  }

  class LabelArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): LabelArgs {
      return apply { addArg("name", value) }
    }
  }

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): LabelsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): LabelsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): LabelsArgs {
      return apply { addArg("before", value) }
    }
  }

  class LanguagesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LanguagesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): LanguagesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): LanguagesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): LanguagesArgs {
      return apply { addArg("before", value) }
    }

    fun orderBy(value: LanguageOrder): LanguagesArgs {
      return apply { addArg("orderBy", value) }
    }
  }

  class MentionableUsersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): MentionableUsersArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): MentionableUsersArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): MentionableUsersArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): MentionableUsersArgs {
      return apply { addArg("before", value) }
    }
  }

  class MilestoneArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): MilestoneArgs {
      return apply { addArg("number", value) }
    }
  }

  class MilestonesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): MilestonesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): MilestonesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): MilestonesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): MilestonesArgs {
      return apply { addArg("before", value) }
    }
  }

  class ObjectValArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun oid(value: GitObjectID): ObjectValArgs {
      return apply { addArg("oid", value) }
    }

    fun expression(value: String): ObjectValArgs {
      return apply { addArg("expression", value) }
    }
  }

  class ProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): ProjectArgs {
      return apply { addArg("number", value) }
    }
  }

  class ProjectsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProjectsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ProjectsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ProjectsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ProjectsArgs {
      return apply { addArg("before", value) }
    }

    fun orderBy(value: ProjectOrder): ProjectsArgs {
      return apply { addArg("orderBy", value) }
    }

    fun search(value: String): ProjectsArgs {
      return apply { addArg("search", value) }
    }

    fun states(value: ProjectState): ProjectsArgs {
      return apply { addArg("states", value) }
    }
  }

  class ProtectedBranchesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProtectedBranchesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ProtectedBranchesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ProtectedBranchesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ProtectedBranchesArgs {
      return apply { addArg("before", value) }
    }
  }

  class PullRequestArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): PullRequestArgs {
      return apply { addArg("number", value) }
    }
  }

  class PullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PullRequestsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): PullRequestsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): PullRequestsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): PullRequestsArgs {
      return apply { addArg("before", value) }
    }

    fun states(value: PullRequestState): PullRequestsArgs {
      return apply { addArg("states", value) }
    }

    fun labels(value: String): PullRequestsArgs {
      return apply { addArg("labels", value) }
    }

    fun headRefName(value: String): PullRequestsArgs {
      return apply { addArg("headRefName", value) }
    }

    fun baseRefName(value: String): PullRequestsArgs {
      return apply { addArg("baseRefName", value) }
    }

    fun orderBy(value: IssueOrder): PullRequestsArgs {
      return apply { addArg("orderBy", value) }
    }
  }

  class RefArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun qualifiedName(value: String): RefArgs {
      return apply { addArg("qualifiedName", value) }
    }
  }

  class RefsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RefsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): RefsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): RefsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): RefsArgs {
      return apply { addArg("before", value) }
    }

    fun refPrefix(value: String): RefsArgs {
      return apply { addArg("refPrefix", value) }
    }

    fun direction(value: OrderDirection): RefsArgs {
      return apply { addArg("direction", value) }
    }
  }

  class ReleasesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReleasesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReleasesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReleasesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReleasesArgs {
      return apply { addArg("before", value) }
    }
  }

  class RepositoryTopicsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoryTopicsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): RepositoryTopicsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): RepositoryTopicsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): RepositoryTopicsArgs {
      return apply { addArg("before", value) }
    }
  }

  class StargazersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StargazersArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): StargazersArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): StargazersArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): StargazersArgs {
      return apply { addArg("before", value) }
    }

    fun orderBy(value: StarOrder): StargazersArgs {
      return apply { addArg("orderBy", value) }
    }
  }

  class WatchersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): WatchersArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): WatchersArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): WatchersArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): WatchersArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface ReopenedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun <T : Closable> closable(init: () -> T): Stub<Closable> = stub(init)

  fun <T> closable(of: KFunction1<Closable, Stub<T>>): Stub<T> = stub<Closable, T>("closable", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()
}

interface RenamedTitleEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun currentTitle(): Stub<String> = stub()

  fun id(): Stub<String> = stub()

  fun previousTitle(): Stub<String> = stub()

  fun <T : RenamedTitleSubject> subject(init: () -> T): Stub<RenamedTitleSubject> = stub(init)

  fun <T> subject(of: KFunction1<RenamedTitleSubject, Stub<T>>): Stub<T> =
        stub<RenamedTitleSubject, T>("subject", of)
}

interface RemovedFromProjectEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()
}

interface RemoveStarPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : Starrable> starrable(init: () -> T): Stub<Starrable> = stub(init)

  fun <T> starrable(of: KFunction1<Starrable, Stub<T>>): Stub<T> =
        stub<Starrable, T>("starrable", of)
}

interface RemoveReactionPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : Reaction> reaction(init: () -> T): Stub<Reaction> = stub(init)

  fun <T> reaction(of: KFunction1<Reaction, Stub<T>>): Stub<T> = stub<Reaction, T>("reaction", of)

  fun <T : Reactable> subject(init: () -> T): Stub<Reactable> = stub(init)

  fun <T> subject(of: KFunction1<Reactable, Stub<T>>): Stub<T> = stub<Reactable, T>("subject", of)
}

interface RemoveOutsideCollaboratorPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : User> removedUser(init: () -> T): Stub<User> = stub(init)

  fun <T> removedUser(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("removedUser", of)
}

interface ReleaseEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Release> node(init: () -> T): Stub<Release> = stub(init)

  fun <T> node(of: KFunction1<Release, Stub<T>>): Stub<T> = stub<Release, T>("node", of)
}

interface ReleaseConnection : QType {
  fun <T : ReleaseEdge> edges(init: () -> T): Stub<ReleaseEdge> = stub(init)

  fun <T> edges(of: KFunction1<ReleaseEdge, Stub<T>>): Stub<T> = stub<ReleaseEdge, T>("edges", of)

  fun <T : Release> nodes(init: () -> T): Stub<Release> = stub(init)

  fun <T> nodes(of: KFunction1<Release, Stub<T>>): Stub<T> = stub<Release, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface ReleaseAssetEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : ReleaseAsset> node(init: () -> T): Stub<ReleaseAsset> = stub(init)

  fun <T> node(of: KFunction1<ReleaseAsset, Stub<T>>): Stub<T> = stub<ReleaseAsset, T>("node", of)
}

interface ReleaseAssetConnection : QType {
  fun <T : ReleaseAssetEdge> edges(init: () -> T): Stub<ReleaseAssetEdge> = stub(init)

  fun <T> edges(of: KFunction1<ReleaseAssetEdge, Stub<T>>): Stub<T> =
        stub<ReleaseAssetEdge, T>("edges", of)

  fun <T : ReleaseAsset> nodes(init: () -> T): Stub<ReleaseAsset> = stub(init)

  fun <T> nodes(of: KFunction1<ReleaseAsset, Stub<T>>): Stub<T> = stub<ReleaseAsset, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface ReleaseAsset : QType {
  fun id(): Stub<String> = stub()

  fun name(): Stub<String> = stub()

  fun <T : Release> release(init: () -> T): Stub<Release> = stub(init)

  fun <T> release(of: KFunction1<Release, Stub<T>>): Stub<T> = stub<Release, T>("release", of)

  fun url(): Stub<URI> = stub()
}

interface Release : QType {
  fun description(): Stub<String> = stub()

  fun id(): Stub<String> = stub()

  fun name(): Stub<String> = stub()

  fun publishedAt(): Stub<DateTime> = stub()

  fun <T : ReleaseAssetConnection> releaseAsset(init: () -> T): Stub<ReleaseAssetConnection> = stub(init)

  fun <T> releaseAsset(of: KFunction1<ReleaseAssetConnection, Stub<T>>): Stub<T> =
        stub<ReleaseAssetConnection, T>("releaseAsset", of)

  fun <T : ReleaseAssetConnection> releaseAssets(init: () -> T): Stub<ReleaseAssetConnection> = stub(init)

  fun <T> releaseAssets(of: KFunction1<ReleaseAssetConnection, Stub<T>>): Stub<T> =
        stub<ReleaseAssetConnection, T>("releaseAssets", of)

  fun resourcePath(): Stub<URI> = stub()

  fun <T : Ref> tag(init: () -> T): Stub<Ref> = stub(init)

  fun <T> tag(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("tag", of)

  fun url(): Stub<URI> = stub()

  class ReleaseAssetArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReleaseAssetArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReleaseAssetArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReleaseAssetArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReleaseAssetArgs {
      return apply { addArg("before", value) }
    }

    fun name(value: String): ReleaseAssetArgs {
      return apply { addArg("name", value) }
    }
  }

  class ReleaseAssetsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReleaseAssetsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReleaseAssetsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReleaseAssetsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReleaseAssetsArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface ReferencedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun <T : Repository> commitRepository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> commitRepository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("commitRepository", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun isCrossReference(): Stub<Boolean> = stub()

  fun isCrossRepository(): Stub<Boolean> = stub()

  fun isDirectReference(): Stub<Boolean> = stub()

  fun <T : ReferencedSubject> subject(init: () -> T): Stub<ReferencedSubject> = stub(init)

  fun <T> subject(of: KFunction1<ReferencedSubject, Stub<T>>): Stub<T> =
        stub<ReferencedSubject, T>("subject", of)
}

interface RefEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Ref> node(init: () -> T): Stub<Ref> = stub(init)

  fun <T> node(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("node", of)
}

interface RefConnection : QType {
  fun <T : RefEdge> edges(init: () -> T): Stub<RefEdge> = stub(init)

  fun <T> edges(of: KFunction1<RefEdge, Stub<T>>): Stub<T> = stub<RefEdge, T>("edges", of)

  fun <T : Ref> nodes(init: () -> T): Stub<Ref> = stub(init)

  fun <T> nodes(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface Ref : QType {
  fun <T : PullRequestConnection> associatedPullRequests(init: () -> T): Stub<PullRequestConnection> = stub(init)

  fun <T> associatedPullRequests(of: KFunction1<PullRequestConnection, Stub<T>>): Stub<T> =
        stub<PullRequestConnection, T>("associatedPullRequests", of)

  fun id(): Stub<String> = stub()

  fun name(): Stub<String> = stub()

  fun prefix(): Stub<String> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun <T : GitObject> target(init: () -> T): Stub<GitObject> = stub(init)

  fun <T> target(of: KFunction1<GitObject, Stub<T>>): Stub<T> = stub<GitObject, T>("target", of)

  class AssociatedPullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssociatedPullRequestsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): AssociatedPullRequestsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): AssociatedPullRequestsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): AssociatedPullRequestsArgs {
      return apply { addArg("before", value) }
    }

    fun states(value: PullRequestState): AssociatedPullRequestsArgs {
      return apply { addArg("states", value) }
    }
  }
}

interface ReactionGroup : QType {
  fun content(): Stub<ReactionContent> = stub()

  fun createdAt(): Stub<DateTime> = stub()

  fun <T : Reactable> subject(init: () -> T): Stub<Reactable> = stub(init)

  fun <T> subject(of: KFunction1<Reactable, Stub<T>>): Stub<T> = stub<Reactable, T>("subject", of)

  fun <T : ReactingUserConnection> users(init: () -> T): Stub<ReactingUserConnection> = stub(init)

  fun <T> users(of: KFunction1<ReactingUserConnection, Stub<T>>): Stub<T> =
        stub<ReactingUserConnection, T>("users", of)

  fun viewerHasReacted(): Stub<Boolean> = stub()

  class UsersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): UsersArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): UsersArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): UsersArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): UsersArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface ReactionEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Reaction> node(init: () -> T): Stub<Reaction> = stub(init)

  fun <T> node(of: KFunction1<Reaction, Stub<T>>): Stub<T> = stub<Reaction, T>("node", of)
}

interface ReactionConnection : QType {
  fun <T : ReactionEdge> edges(init: () -> T): Stub<ReactionEdge> = stub(init)

  fun <T> edges(of: KFunction1<ReactionEdge, Stub<T>>): Stub<T> = stub<ReactionEdge, T>("edges", of)

  fun <T : Reaction> nodes(init: () -> T): Stub<Reaction> = stub(init)

  fun <T> nodes(of: KFunction1<Reaction, Stub<T>>): Stub<T> = stub<Reaction, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()

  fun viewerHasReacted(): Stub<Boolean> = stub()
}

interface Reaction : QType {
  fun content(): Stub<ReactionContent> = stub()

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()

  fun <T : User> user(init: () -> T): Stub<User> = stub(init)

  fun <T> user(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("user", of)
}

interface ReactingUserEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : User> node(init: () -> T): Stub<User> = stub(init)

  fun <T> node(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("node", of)

  fun reactedAt(): Stub<DateTime> = stub()
}

interface ReactingUserConnection : QType {
  fun <T : ReactingUserEdge> edges(init: () -> T): Stub<ReactingUserEdge> = stub(init)

  fun <T> edges(of: KFunction1<ReactingUserEdge, Stub<T>>): Stub<T> =
        stub<ReactingUserEdge, T>("edges", of)

  fun <T : User> nodes(init: () -> T): Stub<User> = stub(init)

  fun <T> nodes(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface RateLimit : QType {
  fun cost(): Stub<Int> = stub()

  fun limit(): Stub<Int> = stub()

  fun remaining(): Stub<Int> = stub()

  fun resetAt(): Stub<DateTime> = stub()
}

interface Query : QType {
  fun <T : CodeOfConduct> codeOfConduct(init: () -> T): Stub<CodeOfConduct> = stub(init)

  fun <T> codeOfConduct(of: KFunction1<CodeOfConduct, Stub<T>>): Stub<T> =
        stub<CodeOfConduct, T>("codeOfConduct", of)

  fun <T : CodeOfConduct> codesOfConduct(init: () -> T): Stub<CodeOfConduct> = stub(init)

  fun <T> codesOfConduct(of: KFunction1<CodeOfConduct, Stub<T>>): Stub<T> =
        stub<CodeOfConduct, T>("codesOfConduct", of)

  fun <T : Node> node(init: () -> T): Stub<Node> = stub(init)

  fun <T> node(of: KFunction1<Node, Stub<T>>): Stub<T> = stub<Node, T>("node", of)

  fun <T : Node> nodes(init: () -> T): Stub<Node> = stub(init)

  fun <T> nodes(of: KFunction1<Node, Stub<T>>): Stub<T> = stub<Node, T>("nodes", of)

  fun <T : Organization> organization(init: () -> T): Stub<Organization> = stub(init)

  fun <T> organization(of: KFunction1<Organization, Stub<T>>): Stub<T> =
        stub<Organization, T>("organization", of)

  fun <T : RateLimit> rateLimit(init: () -> T): Stub<RateLimit> = stub(init)

  fun <T> rateLimit(of: KFunction1<RateLimit, Stub<T>>): Stub<T> =
        stub<RateLimit, T>("rateLimit", of)

  fun <T : Query> relay(init: () -> T): Stub<Query> = stub(init)

  fun <T> relay(of: KFunction1<Query, Stub<T>>): Stub<T> = stub<Query, T>("relay", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun <T : RepositoryOwner> repositoryOwner(init: () -> T): Stub<RepositoryOwner> = stub(init)

  fun <T> repositoryOwner(of: KFunction1<RepositoryOwner, Stub<T>>): Stub<T> =
        stub<RepositoryOwner, T>("repositoryOwner", of)

  fun <T : UniformResourceLocatable> resource(init: () -> T): Stub<UniformResourceLocatable> = stub(init)

  fun <T> resource(of: KFunction1<UniformResourceLocatable, Stub<T>>): Stub<T> =
        stub<UniformResourceLocatable, T>("resource", of)

  fun <T : SearchResultItemConnection> search(init: () -> T): Stub<SearchResultItemConnection> = stub(init)

  fun <T> search(of: KFunction1<SearchResultItemConnection, Stub<T>>): Stub<T> =
        stub<SearchResultItemConnection, T>("search", of)

  fun <T : Topic> topic(init: () -> T): Stub<Topic> = stub(init)

  fun <T> topic(of: KFunction1<Topic, Stub<T>>): Stub<T> = stub<Topic, T>("topic", of)

  fun <T : User> user(init: () -> T): Stub<User> = stub(init)

  fun <T> user(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("user", of)

  fun <T : User> viewer(init: () -> T): Stub<User> = stub(init)

  fun <T> viewer(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("viewer", of)

  class CodeOfConductArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun key(value: String): CodeOfConductArgs {
      return apply { addArg("key", value) }
    }
  }

  class NodeArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun id(value: String): NodeArgs {
      return apply { addArg("id", value) }
    }
  }

  class NodesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun ids(value: String): NodesArgs {
      return apply { addArg("ids", value) }
    }
  }

  class OrganizationArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): OrganizationArgs {
      return apply { addArg("login", value) }
    }
  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun owner(value: String): RepositoryArgs {
      return apply { addArg("owner", value) }
    }

    fun name(value: String): RepositoryArgs {
      return apply { addArg("name", value) }
    }
  }

  class RepositoryOwnerArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): RepositoryOwnerArgs {
      return apply { addArg("login", value) }
    }
  }

  class ResourceArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun url(value: URI): ResourceArgs {
      return apply { addArg("url", value) }
    }
  }

  class SearchArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): SearchArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): SearchArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): SearchArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): SearchArgs {
      return apply { addArg("before", value) }
    }

    fun query(value: String): SearchArgs {
      return apply { addArg("query", value) }
    }

    fun type(value: SearchType): SearchArgs {
      return apply { addArg("type", value) }
    }
  }

  class TopicArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): TopicArgs {
      return apply { addArg("name", value) }
    }
  }

  class UserArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): UserArgs {
      return apply { addArg("login", value) }
    }
  }
}

interface PushAllowanceEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : PushAllowance> node(init: () -> T): Stub<PushAllowance> = stub(init)

  fun <T> node(of: KFunction1<PushAllowance, Stub<T>>): Stub<T> = stub<PushAllowance, T>("node", of)
}

interface PushAllowanceConnection : QType {
  fun <T : PushAllowanceEdge> edges(init: () -> T): Stub<PushAllowanceEdge> = stub(init)

  fun <T> edges(of: KFunction1<PushAllowanceEdge, Stub<T>>): Stub<T> =
        stub<PushAllowanceEdge, T>("edges", of)

  fun <T : PushAllowance> nodes(init: () -> T): Stub<PushAllowance> = stub(init)

  fun <T> nodes(of: KFunction1<PushAllowance, Stub<T>>): Stub<T> =
        stub<PushAllowance, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface PushAllowance : QType {
  fun <T : PushAllowanceActor> actor(init: () -> T): Stub<PushAllowanceActor> = stub(init)

  fun <T> actor(of: KFunction1<PushAllowanceActor, Stub<T>>): Stub<T> =
        stub<PushAllowanceActor, T>("actor", of)

  fun id(): Stub<String> = stub()

  fun <T : ProtectedBranch> protectedBranch(init: () -> T): Stub<ProtectedBranch> = stub(init)

  fun <T> protectedBranch(of: KFunction1<ProtectedBranch, Stub<T>>): Stub<T> =
        stub<ProtectedBranch, T>("protectedBranch", of)
}

interface PullRequestTimelineItemEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : PullRequestTimelineItem> node(init: () -> T): Stub<PullRequestTimelineItem> = stub(init)

  fun <T> node(of: KFunction1<PullRequestTimelineItem, Stub<T>>): Stub<T> =
        stub<PullRequestTimelineItem, T>("node", of)
}

interface PullRequestTimelineConnection : QType {
  fun <T : PullRequestTimelineItemEdge> edges(init: () -> T): Stub<PullRequestTimelineItemEdge> = stub(init)

  fun <T> edges(of: KFunction1<PullRequestTimelineItemEdge, Stub<T>>): Stub<T> =
        stub<PullRequestTimelineItemEdge, T>("edges", of)

  fun <T : PullRequestTimelineItem> nodes(init: () -> T): Stub<PullRequestTimelineItem> = stub(init)

  fun <T> nodes(of: KFunction1<PullRequestTimelineItem, Stub<T>>): Stub<T> =
        stub<PullRequestTimelineItem, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface PullRequestReviewThread : QType {
  fun <T : PullRequestReviewCommentConnection> comments(init: () -> T): Stub<PullRequestReviewCommentConnection> = stub(init)

  fun <T> comments(of: KFunction1<PullRequestReviewCommentConnection, Stub<T>>): Stub<T> =
        stub<PullRequestReviewCommentConnection, T>("comments", of)

  fun id(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): CommentsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): CommentsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): CommentsArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface PullRequestReviewEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : PullRequestReview> node(init: () -> T): Stub<PullRequestReview> = stub(init)

  fun <T> node(of: KFunction1<PullRequestReview, Stub<T>>): Stub<T> =
        stub<PullRequestReview, T>("node", of)
}

interface PullRequestReviewConnection : QType {
  fun <T : PullRequestReviewEdge> edges(init: () -> T): Stub<PullRequestReviewEdge> = stub(init)

  fun <T> edges(of: KFunction1<PullRequestReviewEdge, Stub<T>>): Stub<T> =
        stub<PullRequestReviewEdge, T>("edges", of)

  fun <T : PullRequestReview> nodes(init: () -> T): Stub<PullRequestReview> = stub(init)

  fun <T> nodes(of: KFunction1<PullRequestReview, Stub<T>>): Stub<T> =
        stub<PullRequestReview, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface PullRequestReviewCommentEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : PullRequestReviewComment> node(init: () -> T): Stub<PullRequestReviewComment> = stub(init)

  fun <T> node(of: KFunction1<PullRequestReviewComment, Stub<T>>): Stub<T> =
        stub<PullRequestReviewComment, T>("node", of)
}

interface PullRequestReviewCommentConnection : QType {
  fun <T : PullRequestReviewCommentEdge> edges(init: () -> T): Stub<PullRequestReviewCommentEdge> = stub(init)

  fun <T> edges(of: KFunction1<PullRequestReviewCommentEdge, Stub<T>>): Stub<T> =
        stub<PullRequestReviewCommentEdge, T>("edges", of)

  fun <T : PullRequestReviewComment> nodes(init: () -> T): Stub<PullRequestReviewComment> = stub(init)

  fun <T> nodes(of: KFunction1<PullRequestReviewComment, Stub<T>>): Stub<T> =
        stub<PullRequestReviewComment, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface PullRequestReviewComment : QType {
  fun <T : Actor> author(init: () -> T): Stub<Actor> = stub(init)

  fun <T> author(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub()

  fun body(): Stub<String> = stub()

  fun bodyHTML(): Stub<HTML> = stub()

  fun bodyText(): Stub<String> = stub()

  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun createdViaEmail(): Stub<Boolean> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun diffHunk(): Stub<String> = stub()

  fun draftedAt(): Stub<DateTime> = stub()

  fun <T : Actor> editor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> editor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("editor", of)

  fun id(): Stub<String> = stub()

  fun lastEditedAt(): Stub<DateTime> = stub()

  fun <T : Commit> originalCommit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> originalCommit(of: KFunction1<Commit, Stub<T>>): Stub<T> =
        stub<Commit, T>("originalCommit", of)

  fun originalPosition(): Stub<Int> = stub()

  fun path(): Stub<String> = stub()

  fun position(): Stub<Int> = stub()

  fun publishedAt(): Stub<DateTime> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview> = stub(init)

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T>>): Stub<T> =
        stub<PullRequestReview, T>("pullRequestReview", of)

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup> = stub(init)

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T>>): Stub<T> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : ReactionConnection> reactions(init: () -> T): Stub<ReactionConnection> = stub(init)

  fun <T> reactions(of: KFunction1<ReactionConnection, Stub<T>>): Stub<T> =
        stub<ReactionConnection, T>("reactions", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI> = stub()

  fun updatedAt(): Stub<DateTime> = stub()

  fun url(): Stub<URI> = stub()

  fun viewerCanDelete(): Stub<Boolean> = stub()

  fun viewerCanReact(): Stub<Boolean> = stub()

  fun viewerCanUpdate(): Stub<Boolean> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub()

  fun viewerDidAuthor(): Stub<Boolean> = stub()

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReactionsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReactionsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReactionsArgs {
      return apply { addArg("before", value) }
    }

    fun content(value: ReactionContent): ReactionsArgs {
      return apply { addArg("content", value) }
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
      return apply { addArg("orderBy", value) }
    }
  }
}

interface PullRequestReview : QType {
  fun <T : Actor> author(init: () -> T): Stub<Actor> = stub(init)

  fun <T> author(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub()

  fun body(): Stub<String> = stub()

  fun bodyHTML(): Stub<HTML> = stub()

  fun bodyText(): Stub<String> = stub()

  fun <T : PullRequestReviewCommentConnection> comments(init: () -> T): Stub<PullRequestReviewCommentConnection> = stub(init)

  fun <T> comments(of: KFunction1<PullRequestReviewCommentConnection, Stub<T>>): Stub<T> =
        stub<PullRequestReviewCommentConnection, T>("comments", of)

  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun createdViaEmail(): Stub<Boolean> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun <T : Actor> editor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> editor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("editor", of)

  fun id(): Stub<String> = stub()

  fun lastEditedAt(): Stub<DateTime> = stub()

  fun publishedAt(): Stub<DateTime> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI> = stub()

  fun state(): Stub<PullRequestReviewState> = stub()

  fun submittedAt(): Stub<DateTime> = stub()

  fun updatedAt(): Stub<DateTime> = stub()

  fun url(): Stub<URI> = stub()

  fun viewerCanDelete(): Stub<Boolean> = stub()

  fun viewerCanUpdate(): Stub<Boolean> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub()

  fun viewerDidAuthor(): Stub<Boolean> = stub()

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): CommentsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): CommentsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): CommentsArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface PullRequestEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : PullRequest> node(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> node(of: KFunction1<PullRequest, Stub<T>>): Stub<T> = stub<PullRequest, T>("node", of)
}

interface PullRequestConnection : QType {
  fun <T : PullRequestEdge> edges(init: () -> T): Stub<PullRequestEdge> = stub(init)

  fun <T> edges(of: KFunction1<PullRequestEdge, Stub<T>>): Stub<T> =
        stub<PullRequestEdge, T>("edges", of)

  fun <T : PullRequest> nodes(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> nodes(of: KFunction1<PullRequest, Stub<T>>): Stub<T> = stub<PullRequest, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface PullRequestCommitEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : PullRequestCommit> node(init: () -> T): Stub<PullRequestCommit> = stub(init)

  fun <T> node(of: KFunction1<PullRequestCommit, Stub<T>>): Stub<T> =
        stub<PullRequestCommit, T>("node", of)
}

interface PullRequestCommitConnection : QType {
  fun <T : PullRequestCommitEdge> edges(init: () -> T): Stub<PullRequestCommitEdge> = stub(init)

  fun <T> edges(of: KFunction1<PullRequestCommitEdge, Stub<T>>): Stub<T> =
        stub<PullRequestCommitEdge, T>("edges", of)

  fun <T : PullRequestCommit> nodes(init: () -> T): Stub<PullRequestCommit> = stub(init)

  fun <T> nodes(of: KFunction1<PullRequestCommit, Stub<T>>): Stub<T> =
        stub<PullRequestCommit, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface PullRequestCommit : QType {
  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun id(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun resourcePath(): Stub<URI> = stub()

  fun url(): Stub<URI> = stub()
}

interface PullRequest : QType {
  fun <T : UserConnection> assignees(init: () -> T): Stub<UserConnection> = stub(init)

  fun <T> assignees(of: KFunction1<UserConnection, Stub<T>>): Stub<T> =
        stub<UserConnection, T>("assignees", of)

  fun <T : Actor> author(init: () -> T): Stub<Actor> = stub(init)

  fun <T> author(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub()

  fun <T : Ref> baseRef(init: () -> T): Stub<Ref> = stub(init)

  fun <T> baseRef(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("baseRef", of)

  fun baseRefName(): Stub<String> = stub()

  fun body(): Stub<String> = stub()

  fun bodyHTML(): Stub<HTML> = stub()

  fun bodyText(): Stub<String> = stub()

  fun closed(): Stub<Boolean> = stub()

  fun <T : IssueCommentConnection> comments(init: () -> T): Stub<IssueCommentConnection> = stub(init)

  fun <T> comments(of: KFunction1<IssueCommentConnection, Stub<T>>): Stub<T> =
        stub<IssueCommentConnection, T>("comments", of)

  fun <T : PullRequestCommitConnection> commits(init: () -> T): Stub<PullRequestCommitConnection> = stub(init)

  fun <T> commits(of: KFunction1<PullRequestCommitConnection, Stub<T>>): Stub<T> =
        stub<PullRequestCommitConnection, T>("commits", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun createdViaEmail(): Stub<Boolean> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun <T : Actor> editor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> editor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("editor", of)

  fun <T : Ref> headRef(init: () -> T): Stub<Ref> = stub(init)

  fun <T> headRef(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("headRef", of)

  fun headRefName(): Stub<String> = stub()

  fun <T : Repository> headRepository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> headRepository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("headRepository", of)

  fun <T : RepositoryOwner> headRepositoryOwner(init: () -> T): Stub<RepositoryOwner> = stub(init)

  fun <T> headRepositoryOwner(of: KFunction1<RepositoryOwner, Stub<T>>): Stub<T> =
        stub<RepositoryOwner, T>("headRepositoryOwner", of)

  fun id(): Stub<String> = stub()

  fun isCrossRepository(): Stub<Boolean> = stub()

  fun <T : LabelConnection> labels(init: () -> T): Stub<LabelConnection> = stub(init)

  fun <T> labels(of: KFunction1<LabelConnection, Stub<T>>): Stub<T> =
        stub<LabelConnection, T>("labels", of)

  fun lastEditedAt(): Stub<DateTime> = stub()

  fun locked(): Stub<Boolean> = stub()

  fun <T : Commit> mergeCommit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> mergeCommit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("mergeCommit", of)

  fun mergeable(): Stub<MergeableState> = stub()

  fun merged(): Stub<Boolean> = stub()

  fun mergedAt(): Stub<DateTime> = stub()

  fun number(): Stub<Int> = stub()

  fun <T : UserConnection> participants(init: () -> T): Stub<UserConnection> = stub(init)

  fun <T> participants(of: KFunction1<UserConnection, Stub<T>>): Stub<T> =
        stub<UserConnection, T>("participants", of)

  fun <T : Commit> potentialMergeCommit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> potentialMergeCommit(of: KFunction1<Commit, Stub<T>>): Stub<T> =
        stub<Commit, T>("potentialMergeCommit", of)

  fun publishedAt(): Stub<DateTime> = stub()

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup> = stub(init)

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T>>): Stub<T> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : ReactionConnection> reactions(init: () -> T): Stub<ReactionConnection> = stub(init)

  fun <T> reactions(of: KFunction1<ReactionConnection, Stub<T>>): Stub<T> =
        stub<ReactionConnection, T>("reactions", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI> = stub()

  fun revertResourcePath(): Stub<URI> = stub()

  fun revertUrl(): Stub<URI> = stub()

  fun <T : ReviewRequestConnection> reviewRequests(init: () -> T): Stub<ReviewRequestConnection> = stub(init)

  fun <T> reviewRequests(of: KFunction1<ReviewRequestConnection, Stub<T>>): Stub<T> =
        stub<ReviewRequestConnection, T>("reviewRequests", of)

  fun <T : PullRequestReviewConnection> reviews(init: () -> T): Stub<PullRequestReviewConnection> = stub(init)

  fun <T> reviews(of: KFunction1<PullRequestReviewConnection, Stub<T>>): Stub<T> =
        stub<PullRequestReviewConnection, T>("reviews", of)

  fun state(): Stub<PullRequestState> = stub()

  fun <T : SuggestedReviewer> suggestedReviewers(init: () -> T): Stub<SuggestedReviewer> = stub(init)

  fun <T> suggestedReviewers(of: KFunction1<SuggestedReviewer, Stub<T>>): Stub<T> =
        stub<SuggestedReviewer, T>("suggestedReviewers", of)

  fun <T : PullRequestTimelineConnection> timeline(init: () -> T): Stub<PullRequestTimelineConnection> = stub(init)

  fun <T> timeline(of: KFunction1<PullRequestTimelineConnection, Stub<T>>): Stub<T> =
        stub<PullRequestTimelineConnection, T>("timeline", of)

  fun title(): Stub<String> = stub()

  fun updatedAt(): Stub<DateTime> = stub()

  fun url(): Stub<URI> = stub()

  fun viewerCanReact(): Stub<Boolean> = stub()

  fun viewerCanSubscribe(): Stub<Boolean> = stub()

  fun viewerCanUpdate(): Stub<Boolean> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub()

  fun viewerDidAuthor(): Stub<Boolean> = stub()

  fun viewerSubscription(): Stub<SubscriptionState> = stub()

  class AssigneesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssigneesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): AssigneesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): AssigneesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): AssigneesArgs {
      return apply { addArg("before", value) }
    }
  }

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): CommentsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): CommentsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): CommentsArgs {
      return apply { addArg("before", value) }
    }
  }

  class CommitsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommitsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): CommitsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): CommitsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): CommitsArgs {
      return apply { addArg("before", value) }
    }
  }

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): LabelsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): LabelsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): LabelsArgs {
      return apply { addArg("before", value) }
    }
  }

  class ParticipantsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ParticipantsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ParticipantsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ParticipantsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ParticipantsArgs {
      return apply { addArg("before", value) }
    }
  }

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReactionsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReactionsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReactionsArgs {
      return apply { addArg("before", value) }
    }

    fun content(value: ReactionContent): ReactionsArgs {
      return apply { addArg("content", value) }
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
      return apply { addArg("orderBy", value) }
    }
  }

  class ReviewRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReviewRequestsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReviewRequestsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReviewRequestsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReviewRequestsArgs {
      return apply { addArg("before", value) }
    }
  }

  class ReviewsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReviewsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReviewsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReviewsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReviewsArgs {
      return apply { addArg("before", value) }
    }

    fun states(value: PullRequestReviewState): ReviewsArgs {
      return apply { addArg("states", value) }
    }
  }

  class TimelineArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): TimelineArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): TimelineArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): TimelineArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): TimelineArgs {
      return apply { addArg("before", value) }
    }

    fun since(value: DateTime): TimelineArgs {
      return apply { addArg("since", value) }
    }
  }
}

interface ProtectedBranchEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : ProtectedBranch> node(init: () -> T): Stub<ProtectedBranch> = stub(init)

  fun <T> node(of: KFunction1<ProtectedBranch, Stub<T>>): Stub<T> =
        stub<ProtectedBranch, T>("node", of)
}

interface ProtectedBranchConnection : QType {
  fun <T : ProtectedBranchEdge> edges(init: () -> T): Stub<ProtectedBranchEdge> = stub(init)

  fun <T> edges(of: KFunction1<ProtectedBranchEdge, Stub<T>>): Stub<T> =
        stub<ProtectedBranchEdge, T>("edges", of)

  fun <T : ProtectedBranch> nodes(init: () -> T): Stub<ProtectedBranch> = stub(init)

  fun <T> nodes(of: KFunction1<ProtectedBranch, Stub<T>>): Stub<T> =
        stub<ProtectedBranch, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface ProtectedBranch : QType {
  fun <T : Actor> creator(init: () -> T): Stub<Actor> = stub(init)

  fun <T> creator(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("creator", of)

  fun hasDismissableStaleReviews(): Stub<Boolean> = stub()

  fun hasRequiredReviews(): Stub<Boolean> = stub()

  fun hasRequiredStatusChecks(): Stub<Boolean> = stub()

  fun hasRestrictedPushes(): Stub<Boolean> = stub()

  fun hasRestrictedReviewDismissals(): Stub<Boolean> = stub()

  fun hasStrictRequiredStatusChecks(): Stub<Boolean> = stub()

  fun id(): Stub<String> = stub()

  fun isAdminEnforced(): Stub<Boolean> = stub()

  fun name(): Stub<String> = stub()

  fun <T : PushAllowanceConnection> pushAllowances(init: () -> T): Stub<PushAllowanceConnection> = stub(init)

  fun <T> pushAllowances(of: KFunction1<PushAllowanceConnection, Stub<T>>): Stub<T> =
        stub<PushAllowanceConnection, T>("pushAllowances", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun requiredStatusCheckContexts(): Stub<String> = stub()

  fun <T : ReviewDismissalAllowanceConnection> reviewDismissalAllowances(init: () -> T): Stub<ReviewDismissalAllowanceConnection> = stub(init)

  fun <T> reviewDismissalAllowances(of: KFunction1<ReviewDismissalAllowanceConnection, Stub<T>>): Stub<T> =
        stub<ReviewDismissalAllowanceConnection, T>("reviewDismissalAllowances", of)

  class PushAllowancesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PushAllowancesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): PushAllowancesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): PushAllowancesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): PushAllowancesArgs {
      return apply { addArg("before", value) }
    }
  }

  class ReviewDismissalAllowancesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReviewDismissalAllowancesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReviewDismissalAllowancesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReviewDismissalAllowancesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReviewDismissalAllowancesArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface ProjectEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Project> node(init: () -> T): Stub<Project> = stub(init)

  fun <T> node(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("node", of)
}

interface ProjectConnection : QType {
  fun <T : ProjectEdge> edges(init: () -> T): Stub<ProjectEdge> = stub(init)

  fun <T> edges(of: KFunction1<ProjectEdge, Stub<T>>): Stub<T> = stub<ProjectEdge, T>("edges", of)

  fun <T : Project> nodes(init: () -> T): Stub<Project> = stub(init)

  fun <T> nodes(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface ProjectColumnEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : ProjectColumn> node(init: () -> T): Stub<ProjectColumn> = stub(init)

  fun <T> node(of: KFunction1<ProjectColumn, Stub<T>>): Stub<T> = stub<ProjectColumn, T>("node", of)
}

interface ProjectColumnConnection : QType {
  fun <T : ProjectColumnEdge> edges(init: () -> T): Stub<ProjectColumnEdge> = stub(init)

  fun <T> edges(of: KFunction1<ProjectColumnEdge, Stub<T>>): Stub<T> =
        stub<ProjectColumnEdge, T>("edges", of)

  fun <T : ProjectColumn> nodes(init: () -> T): Stub<ProjectColumn> = stub(init)

  fun <T> nodes(of: KFunction1<ProjectColumn, Stub<T>>): Stub<T> =
        stub<ProjectColumn, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface ProjectColumn : QType {
  fun <T : ProjectCardConnection> cards(init: () -> T): Stub<ProjectCardConnection> = stub(init)

  fun <T> cards(of: KFunction1<ProjectCardConnection, Stub<T>>): Stub<T> =
        stub<ProjectCardConnection, T>("cards", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()

  fun name(): Stub<String> = stub()

  fun <T : Project> project(init: () -> T): Stub<Project> = stub(init)

  fun <T> project(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("project", of)

  fun updatedAt(): Stub<DateTime> = stub()

  class CardsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CardsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): CardsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): CardsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): CardsArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface ProjectCardEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : ProjectCard> node(init: () -> T): Stub<ProjectCard> = stub(init)

  fun <T> node(of: KFunction1<ProjectCard, Stub<T>>): Stub<T> = stub<ProjectCard, T>("node", of)
}

interface ProjectCardConnection : QType {
  fun <T : ProjectCardEdge> edges(init: () -> T): Stub<ProjectCardEdge> = stub(init)

  fun <T> edges(of: KFunction1<ProjectCardEdge, Stub<T>>): Stub<T> =
        stub<ProjectCardEdge, T>("edges", of)

  fun <T : ProjectCard> nodes(init: () -> T): Stub<ProjectCard> = stub(init)

  fun <T> nodes(of: KFunction1<ProjectCard, Stub<T>>): Stub<T> = stub<ProjectCard, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface ProjectCard : QType {
  fun <T : ProjectColumn> column(init: () -> T): Stub<ProjectColumn> = stub(init)

  fun <T> column(of: KFunction1<ProjectColumn, Stub<T>>): Stub<T> =
        stub<ProjectColumn, T>("column", of)

  fun <T : ProjectCardItem> content(init: () -> T): Stub<ProjectCardItem> = stub(init)

  fun <T> content(of: KFunction1<ProjectCardItem, Stub<T>>): Stub<T> =
        stub<ProjectCardItem, T>("content", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun <T : Actor> creator(init: () -> T): Stub<Actor> = stub(init)

  fun <T> creator(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("creator", of)

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()

  fun note(): Stub<String> = stub()

  fun <T : Project> project(init: () -> T): Stub<Project> = stub(init)

  fun <T> project(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("project", of)

  fun <T : ProjectColumn> projectColumn(init: () -> T): Stub<ProjectColumn> = stub(init)

  fun <T> projectColumn(of: KFunction1<ProjectColumn, Stub<T>>): Stub<T> =
        stub<ProjectColumn, T>("projectColumn", of)

  fun resourcePath(): Stub<URI> = stub()

  fun state(): Stub<ProjectCardState> = stub()

  fun updatedAt(): Stub<DateTime> = stub()

  fun url(): Stub<URI> = stub()
}

interface Project : QType {
  fun body(): Stub<String> = stub()

  fun bodyHTML(): Stub<HTML> = stub()

  fun closedAt(): Stub<DateTime> = stub()

  fun <T : ProjectColumnConnection> columns(init: () -> T): Stub<ProjectColumnConnection> = stub(init)

  fun <T> columns(of: KFunction1<ProjectColumnConnection, Stub<T>>): Stub<T> =
        stub<ProjectColumnConnection, T>("columns", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun <T : Actor> creator(init: () -> T): Stub<Actor> = stub(init)

  fun <T> creator(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("creator", of)

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()

  fun name(): Stub<String> = stub()

  fun number(): Stub<Int> = stub()

  fun <T : ProjectOwner> owner(init: () -> T): Stub<ProjectOwner> = stub(init)

  fun <T> owner(of: KFunction1<ProjectOwner, Stub<T>>): Stub<T> = stub<ProjectOwner, T>("owner", of)

  fun resourcePath(): Stub<URI> = stub()

  fun state(): Stub<ProjectState> = stub()

  fun updatedAt(): Stub<DateTime> = stub()

  fun url(): Stub<URI> = stub()

  fun viewerCanUpdate(): Stub<Boolean> = stub()

  class ColumnsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ColumnsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ColumnsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ColumnsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ColumnsArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface PageInfo : QType {
  fun endCursor(): Stub<String> = stub()

  fun hasNextPage(): Stub<Boolean> = stub()

  fun hasPreviousPage(): Stub<Boolean> = stub()

  fun startCursor(): Stub<String> = stub()
}

interface OrganizationInvitationEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : OrganizationInvitation> node(init: () -> T): Stub<OrganizationInvitation> = stub(init)

  fun <T> node(of: KFunction1<OrganizationInvitation, Stub<T>>): Stub<T> =
        stub<OrganizationInvitation, T>("node", of)
}

interface OrganizationInvitationConnection : QType {
  fun <T : OrganizationInvitationEdge> edges(init: () -> T): Stub<OrganizationInvitationEdge> = stub(init)

  fun <T> edges(of: KFunction1<OrganizationInvitationEdge, Stub<T>>): Stub<T> =
        stub<OrganizationInvitationEdge, T>("edges", of)

  fun <T : OrganizationInvitation> nodes(init: () -> T): Stub<OrganizationInvitation> = stub(init)

  fun <T> nodes(of: KFunction1<OrganizationInvitation, Stub<T>>): Stub<T> =
        stub<OrganizationInvitation, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface OrganizationInvitation : QType {
  fun email(): Stub<String> = stub()

  fun id(): Stub<String> = stub()

  fun <T : User> invitee(init: () -> T): Stub<User> = stub(init)

  fun <T> invitee(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("invitee", of)

  fun <T : User> inviter(init: () -> T): Stub<User> = stub(init)

  fun <T> inviter(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("inviter", of)

  fun role(): Stub<OrganizationInvitationRole> = stub()
}

interface OrganizationIdentityProvider : QType {
  fun digestMethod(): Stub<URI> = stub()

  fun <T : ExternalIdentityConnection> externalIdentities(init: () -> T): Stub<ExternalIdentityConnection> = stub(init)

  fun <T> externalIdentities(of: KFunction1<ExternalIdentityConnection, Stub<T>>): Stub<T> =
        stub<ExternalIdentityConnection, T>("externalIdentities", of)

  fun id(): Stub<String> = stub()

  fun idpCertificate(): Stub<X509Certificate> = stub()

  fun issuer(): Stub<String> = stub()

  fun <T : Organization> organization(init: () -> T): Stub<Organization> = stub(init)

  fun <T> organization(of: KFunction1<Organization, Stub<T>>): Stub<T> =
        stub<Organization, T>("organization", of)

  fun signatureMethod(): Stub<URI> = stub()

  fun ssoUrl(): Stub<URI> = stub()

  class ExternalIdentitiesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ExternalIdentitiesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ExternalIdentitiesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ExternalIdentitiesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ExternalIdentitiesArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface OrganizationEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Organization> node(init: () -> T): Stub<Organization> = stub(init)

  fun <T> node(of: KFunction1<Organization, Stub<T>>): Stub<T> = stub<Organization, T>("node", of)
}

interface OrganizationConnection : QType {
  fun <T : OrganizationEdge> edges(init: () -> T): Stub<OrganizationEdge> = stub(init)

  fun <T> edges(of: KFunction1<OrganizationEdge, Stub<T>>): Stub<T> =
        stub<OrganizationEdge, T>("edges", of)

  fun <T : Organization> nodes(init: () -> T): Stub<Organization> = stub(init)

  fun <T> nodes(of: KFunction1<Organization, Stub<T>>): Stub<T> = stub<Organization, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface Organization : QType {
  fun avatarUrl(): Stub<URI> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()

  fun isInvoiced(): Stub<Boolean> = stub()

  fun login(): Stub<String> = stub()

  fun <T : UserConnection> members(init: () -> T): Stub<UserConnection> = stub(init)

  fun <T> members(of: KFunction1<UserConnection, Stub<T>>): Stub<T> =
        stub<UserConnection, T>("members", of)

  fun name(): Stub<String> = stub()

  fun newTeamResourcePath(): Stub<URI> = stub()

  fun newTeamUrl(): Stub<URI> = stub()

  fun organizationBillingEmail(): Stub<String> = stub()

  fun <T : RepositoryConnection> pinnedRepositories(init: () -> T): Stub<RepositoryConnection> = stub(init)

  fun <T> pinnedRepositories(of: KFunction1<RepositoryConnection, Stub<T>>): Stub<T> =
        stub<RepositoryConnection, T>("pinnedRepositories", of)

  fun <T : Project> project(init: () -> T): Stub<Project> = stub(init)

  fun <T> project(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("project", of)

  fun <T : ProjectConnection> projects(init: () -> T): Stub<ProjectConnection> = stub(init)

  fun <T> projects(of: KFunction1<ProjectConnection, Stub<T>>): Stub<T> =
        stub<ProjectConnection, T>("projects", of)

  fun projectsResourcePath(): Stub<URI> = stub()

  fun projectsUrl(): Stub<URI> = stub()

  fun <T : RepositoryConnection> repositories(init: () -> T): Stub<RepositoryConnection> = stub(init)

  fun <T> repositories(of: KFunction1<RepositoryConnection, Stub<T>>): Stub<T> =
        stub<RepositoryConnection, T>("repositories", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI> = stub()

  fun <T : OrganizationIdentityProvider> samlIdentityProvider(init: () -> T): Stub<OrganizationIdentityProvider> = stub(init)

  fun <T> samlIdentityProvider(of: KFunction1<OrganizationIdentityProvider, Stub<T>>): Stub<T> =
        stub<OrganizationIdentityProvider, T>("samlIdentityProvider", of)

  fun <T : Team> team(init: () -> T): Stub<Team> = stub(init)

  fun <T> team(of: KFunction1<Team, Stub<T>>): Stub<T> = stub<Team, T>("team", of)

  fun <T : TeamConnection> teams(init: () -> T): Stub<TeamConnection> = stub(init)

  fun <T> teams(of: KFunction1<TeamConnection, Stub<T>>): Stub<T> =
        stub<TeamConnection, T>("teams", of)

  fun teamsResourcePath(): Stub<URI> = stub()

  fun teamsUrl(): Stub<URI> = stub()

  fun url(): Stub<URI> = stub()

  fun viewerCanAdminister(): Stub<Boolean> = stub()

  fun viewerCanCreateProjects(): Stub<Boolean> = stub()

  fun viewerCanCreateRepositories(): Stub<Boolean> = stub()

  fun viewerCanCreateTeams(): Stub<Boolean> = stub()

  fun viewerIsAMember(): Stub<Boolean> = stub()

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
      return apply { addArg("size", value) }
    }
  }

  class MembersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): MembersArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): MembersArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): MembersArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): MembersArgs {
      return apply { addArg("before", value) }
    }
  }

  class PinnedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PinnedRepositoriesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): PinnedRepositoriesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): PinnedRepositoriesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): PinnedRepositoriesArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs {
      return apply { addArg("privacy", value) }
    }

    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs {
      return apply { addArg("orderBy", value) }
    }

    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs {
      return apply { addArg("affiliations", value) }
    }

    fun isLocked(value: Boolean): PinnedRepositoriesArgs {
      return apply { addArg("isLocked", value) }
    }
  }

  class ProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): ProjectArgs {
      return apply { addArg("number", value) }
    }
  }

  class ProjectsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProjectsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ProjectsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ProjectsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ProjectsArgs {
      return apply { addArg("before", value) }
    }

    fun orderBy(value: ProjectOrder): ProjectsArgs {
      return apply { addArg("orderBy", value) }
    }

    fun search(value: String): ProjectsArgs {
      return apply { addArg("search", value) }
    }

    fun states(value: ProjectState): ProjectsArgs {
      return apply { addArg("states", value) }
    }
  }

  class RepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoriesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): RepositoriesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): RepositoriesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): RepositoriesArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: RepositoryPrivacy): RepositoriesArgs {
      return apply { addArg("privacy", value) }
    }

    fun orderBy(value: RepositoryOrder): RepositoriesArgs {
      return apply { addArg("orderBy", value) }
    }

    fun affiliations(value: RepositoryAffiliation): RepositoriesArgs {
      return apply { addArg("affiliations", value) }
    }

    fun isLocked(value: Boolean): RepositoriesArgs {
      return apply { addArg("isLocked", value) }
    }

    fun isFork(value: Boolean): RepositoriesArgs {
      return apply { addArg("isFork", value) }
    }
  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): RepositoryArgs {
      return apply { addArg("name", value) }
    }
  }

  class TeamArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun slug(value: String): TeamArgs {
      return apply { addArg("slug", value) }
    }
  }

  class TeamsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): TeamsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): TeamsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): TeamsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): TeamsArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: TeamPrivacy): TeamsArgs {
      return apply { addArg("privacy", value) }
    }

    fun role(value: TeamRole): TeamsArgs {
      return apply { addArg("role", value) }
    }

    fun query(value: String): TeamsArgs {
      return apply { addArg("query", value) }
    }

    fun userLogins(value: String): TeamsArgs {
      return apply { addArg("userLogins", value) }
    }

    fun orderBy(value: TeamOrder): TeamsArgs {
      return apply { addArg("orderBy", value) }
    }

    fun ldapMapped(value: Boolean): TeamsArgs {
      return apply { addArg("ldapMapped", value) }
    }
  }
}

interface Mutation : QType {
  fun <T : AcceptTopicSuggestionPayload> acceptTopicSuggestion(init: () -> T): Stub<AcceptTopicSuggestionPayload> = stub(init)

  fun <T> acceptTopicSuggestion(of: KFunction1<AcceptTopicSuggestionPayload, Stub<T>>): Stub<T> =
        stub<AcceptTopicSuggestionPayload, T>("acceptTopicSuggestion", of)

  fun <T : AddCommentPayload> addComment(init: () -> T): Stub<AddCommentPayload> = stub(init)

  fun <T> addComment(of: KFunction1<AddCommentPayload, Stub<T>>): Stub<T> =
        stub<AddCommentPayload, T>("addComment", of)

  fun <T : AddProjectCardPayload> addProjectCard(init: () -> T): Stub<AddProjectCardPayload> = stub(init)

  fun <T> addProjectCard(of: KFunction1<AddProjectCardPayload, Stub<T>>): Stub<T> =
        stub<AddProjectCardPayload, T>("addProjectCard", of)

  fun <T : AddProjectColumnPayload> addProjectColumn(init: () -> T): Stub<AddProjectColumnPayload> = stub(init)

  fun <T> addProjectColumn(of: KFunction1<AddProjectColumnPayload, Stub<T>>): Stub<T> =
        stub<AddProjectColumnPayload, T>("addProjectColumn", of)

  fun <T : AddPullRequestReviewPayload> addPullRequestReview(init: () -> T): Stub<AddPullRequestReviewPayload> = stub(init)

  fun <T> addPullRequestReview(of: KFunction1<AddPullRequestReviewPayload, Stub<T>>): Stub<T> =
        stub<AddPullRequestReviewPayload, T>("addPullRequestReview", of)

  fun <T : AddPullRequestReviewCommentPayload> addPullRequestReviewComment(init: () -> T): Stub<AddPullRequestReviewCommentPayload> = stub(init)

  fun <T> addPullRequestReviewComment(of: KFunction1<AddPullRequestReviewCommentPayload, Stub<T>>): Stub<T> =
        stub<AddPullRequestReviewCommentPayload, T>("addPullRequestReviewComment", of)

  fun <T : AddReactionPayload> addReaction(init: () -> T): Stub<AddReactionPayload> = stub(init)

  fun <T> addReaction(of: KFunction1<AddReactionPayload, Stub<T>>): Stub<T> =
        stub<AddReactionPayload, T>("addReaction", of)

  fun <T : AddStarPayload> addStar(init: () -> T): Stub<AddStarPayload> = stub(init)

  fun <T> addStar(of: KFunction1<AddStarPayload, Stub<T>>): Stub<T> =
        stub<AddStarPayload, T>("addStar", of)

  fun <T : CreateProjectPayload> createProject(init: () -> T): Stub<CreateProjectPayload> = stub(init)

  fun <T> createProject(of: KFunction1<CreateProjectPayload, Stub<T>>): Stub<T> =
        stub<CreateProjectPayload, T>("createProject", of)

  fun <T : DeclineTopicSuggestionPayload> declineTopicSuggestion(init: () -> T): Stub<DeclineTopicSuggestionPayload> = stub(init)

  fun <T> declineTopicSuggestion(of: KFunction1<DeclineTopicSuggestionPayload, Stub<T>>): Stub<T> =
        stub<DeclineTopicSuggestionPayload, T>("declineTopicSuggestion", of)

  fun <T : DeleteProjectPayload> deleteProject(init: () -> T): Stub<DeleteProjectPayload> = stub(init)

  fun <T> deleteProject(of: KFunction1<DeleteProjectPayload, Stub<T>>): Stub<T> =
        stub<DeleteProjectPayload, T>("deleteProject", of)

  fun <T : DeleteProjectCardPayload> deleteProjectCard(init: () -> T): Stub<DeleteProjectCardPayload> = stub(init)

  fun <T> deleteProjectCard(of: KFunction1<DeleteProjectCardPayload, Stub<T>>): Stub<T> =
        stub<DeleteProjectCardPayload, T>("deleteProjectCard", of)

  fun <T : DeleteProjectColumnPayload> deleteProjectColumn(init: () -> T): Stub<DeleteProjectColumnPayload> = stub(init)

  fun <T> deleteProjectColumn(of: KFunction1<DeleteProjectColumnPayload, Stub<T>>): Stub<T> =
        stub<DeleteProjectColumnPayload, T>("deleteProjectColumn", of)

  fun <T : DeletePullRequestReviewPayload> deletePullRequestReview(init: () -> T): Stub<DeletePullRequestReviewPayload> = stub(init)

  fun <T> deletePullRequestReview(of: KFunction1<DeletePullRequestReviewPayload, Stub<T>>): Stub<T> =
        stub<DeletePullRequestReviewPayload, T>("deletePullRequestReview", of)

  fun <T : DismissPullRequestReviewPayload> dismissPullRequestReview(init: () -> T): Stub<DismissPullRequestReviewPayload> = stub(init)

  fun <T> dismissPullRequestReview(of: KFunction1<DismissPullRequestReviewPayload, Stub<T>>): Stub<T> =
        stub<DismissPullRequestReviewPayload, T>("dismissPullRequestReview", of)

  fun <T : MoveProjectCardPayload> moveProjectCard(init: () -> T): Stub<MoveProjectCardPayload> = stub(init)

  fun <T> moveProjectCard(of: KFunction1<MoveProjectCardPayload, Stub<T>>): Stub<T> =
        stub<MoveProjectCardPayload, T>("moveProjectCard", of)

  fun <T : MoveProjectColumnPayload> moveProjectColumn(init: () -> T): Stub<MoveProjectColumnPayload> = stub(init)

  fun <T> moveProjectColumn(of: KFunction1<MoveProjectColumnPayload, Stub<T>>): Stub<T> =
        stub<MoveProjectColumnPayload, T>("moveProjectColumn", of)

  fun <T : RemoveOutsideCollaboratorPayload> removeOutsideCollaborator(init: () -> T): Stub<RemoveOutsideCollaboratorPayload> = stub(init)

  fun <T> removeOutsideCollaborator(of: KFunction1<RemoveOutsideCollaboratorPayload, Stub<T>>): Stub<T> =
        stub<RemoveOutsideCollaboratorPayload, T>("removeOutsideCollaborator", of)

  fun <T : RemoveReactionPayload> removeReaction(init: () -> T): Stub<RemoveReactionPayload> = stub(init)

  fun <T> removeReaction(of: KFunction1<RemoveReactionPayload, Stub<T>>): Stub<T> =
        stub<RemoveReactionPayload, T>("removeReaction", of)

  fun <T : RemoveStarPayload> removeStar(init: () -> T): Stub<RemoveStarPayload> = stub(init)

  fun <T> removeStar(of: KFunction1<RemoveStarPayload, Stub<T>>): Stub<T> =
        stub<RemoveStarPayload, T>("removeStar", of)

  fun <T : RequestReviewsPayload> requestReviews(init: () -> T): Stub<RequestReviewsPayload> = stub(init)

  fun <T> requestReviews(of: KFunction1<RequestReviewsPayload, Stub<T>>): Stub<T> =
        stub<RequestReviewsPayload, T>("requestReviews", of)

  fun <T : SubmitPullRequestReviewPayload> submitPullRequestReview(init: () -> T): Stub<SubmitPullRequestReviewPayload> = stub(init)

  fun <T> submitPullRequestReview(of: KFunction1<SubmitPullRequestReviewPayload, Stub<T>>): Stub<T> =
        stub<SubmitPullRequestReviewPayload, T>("submitPullRequestReview", of)

  fun <T : UpdateProjectPayload> updateProject(init: () -> T): Stub<UpdateProjectPayload> = stub(init)

  fun <T> updateProject(of: KFunction1<UpdateProjectPayload, Stub<T>>): Stub<T> =
        stub<UpdateProjectPayload, T>("updateProject", of)

  fun <T : UpdateProjectCardPayload> updateProjectCard(init: () -> T): Stub<UpdateProjectCardPayload> = stub(init)

  fun <T> updateProjectCard(of: KFunction1<UpdateProjectCardPayload, Stub<T>>): Stub<T> =
        stub<UpdateProjectCardPayload, T>("updateProjectCard", of)

  fun <T : UpdateProjectColumnPayload> updateProjectColumn(init: () -> T): Stub<UpdateProjectColumnPayload> = stub(init)

  fun <T> updateProjectColumn(of: KFunction1<UpdateProjectColumnPayload, Stub<T>>): Stub<T> =
        stub<UpdateProjectColumnPayload, T>("updateProjectColumn", of)

  fun <T : UpdatePullRequestReviewPayload> updatePullRequestReview(init: () -> T): Stub<UpdatePullRequestReviewPayload> = stub(init)

  fun <T> updatePullRequestReview(of: KFunction1<UpdatePullRequestReviewPayload, Stub<T>>): Stub<T> =
        stub<UpdatePullRequestReviewPayload, T>("updatePullRequestReview", of)

  fun <T : UpdatePullRequestReviewCommentPayload> updatePullRequestReviewComment(init: () -> T): Stub<UpdatePullRequestReviewCommentPayload> = stub(init)

  fun <T> updatePullRequestReviewComment(of: KFunction1<UpdatePullRequestReviewCommentPayload, Stub<T>>): Stub<T> =
        stub<UpdatePullRequestReviewCommentPayload, T>("updatePullRequestReviewComment", of)

  fun <T : UpdateSubscriptionPayload> updateSubscription(init: () -> T): Stub<UpdateSubscriptionPayload> = stub(init)

  fun <T> updateSubscription(of: KFunction1<UpdateSubscriptionPayload, Stub<T>>): Stub<T> =
        stub<UpdateSubscriptionPayload, T>("updateSubscription", of)

  fun <T : UpdateTopicsPayload> updateTopics(init: () -> T): Stub<UpdateTopicsPayload> = stub(init)

  fun <T> updateTopics(of: KFunction1<UpdateTopicsPayload, Stub<T>>): Stub<T> =
        stub<UpdateTopicsPayload, T>("updateTopics", of)

  class AcceptTopicSuggestionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AcceptTopicSuggestionInput): AcceptTopicSuggestionArgs {
      return apply { addArg("input", value) }
    }
  }

  class AddCommentArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddCommentInput): AddCommentArgs {
      return apply { addArg("input", value) }
    }
  }

  class AddProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddProjectCardInput): AddProjectCardArgs {
      return apply { addArg("input", value) }
    }
  }

  class AddProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddProjectColumnInput): AddProjectColumnArgs {
      return apply { addArg("input", value) }
    }
  }

  class AddPullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddPullRequestReviewInput): AddPullRequestReviewArgs {
      return apply { addArg("input", value) }
    }
  }

  class AddPullRequestReviewCommentArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddPullRequestReviewCommentInput): AddPullRequestReviewCommentArgs {
      return apply { addArg("input", value) }
    }
  }

  class AddReactionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddReactionInput): AddReactionArgs {
      return apply { addArg("input", value) }
    }
  }

  class AddStarArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddStarInput): AddStarArgs {
      return apply { addArg("input", value) }
    }
  }

  class CreateProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: CreateProjectInput): CreateProjectArgs {
      return apply { addArg("input", value) }
    }
  }

  class DeclineTopicSuggestionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeclineTopicSuggestionInput): DeclineTopicSuggestionArgs {
      return apply { addArg("input", value) }
    }
  }

  class DeleteProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeleteProjectInput): DeleteProjectArgs {
      return apply { addArg("input", value) }
    }
  }

  class DeleteProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeleteProjectCardInput): DeleteProjectCardArgs {
      return apply { addArg("input", value) }
    }
  }

  class DeleteProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeleteProjectColumnInput): DeleteProjectColumnArgs {
      return apply { addArg("input", value) }
    }
  }

  class DeletePullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeletePullRequestReviewInput): DeletePullRequestReviewArgs {
      return apply { addArg("input", value) }
    }
  }

  class DismissPullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DismissPullRequestReviewInput): DismissPullRequestReviewArgs {
      return apply { addArg("input", value) }
    }
  }

  class MoveProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: MoveProjectCardInput): MoveProjectCardArgs {
      return apply { addArg("input", value) }
    }
  }

  class MoveProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: MoveProjectColumnInput): MoveProjectColumnArgs {
      return apply { addArg("input", value) }
    }
  }

  class RemoveOutsideCollaboratorArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RemoveOutsideCollaboratorInput): RemoveOutsideCollaboratorArgs {
      return apply { addArg("input", value) }
    }
  }

  class RemoveReactionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RemoveReactionInput): RemoveReactionArgs {
      return apply { addArg("input", value) }
    }
  }

  class RemoveStarArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RemoveStarInput): RemoveStarArgs {
      return apply { addArg("input", value) }
    }
  }

  class RequestReviewsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RequestReviewsInput): RequestReviewsArgs {
      return apply { addArg("input", value) }
    }
  }

  class SubmitPullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: SubmitPullRequestReviewInput): SubmitPullRequestReviewArgs {
      return apply { addArg("input", value) }
    }
  }

  class UpdateProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateProjectInput): UpdateProjectArgs {
      return apply { addArg("input", value) }
    }
  }

  class UpdateProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateProjectCardInput): UpdateProjectCardArgs {
      return apply { addArg("input", value) }
    }
  }

  class UpdateProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateProjectColumnInput): UpdateProjectColumnArgs {
      return apply { addArg("input", value) }
    }
  }

  class UpdatePullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdatePullRequestReviewInput): UpdatePullRequestReviewArgs {
      return apply { addArg("input", value) }
    }
  }

  class UpdatePullRequestReviewCommentArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdatePullRequestReviewCommentInput): UpdatePullRequestReviewCommentArgs {
      return apply { addArg("input", value) }
    }
  }

  class UpdateSubscriptionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateSubscriptionInput): UpdateSubscriptionArgs {
      return apply { addArg("input", value) }
    }
  }

  class UpdateTopicsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateTopicsInput): UpdateTopicsArgs {
      return apply { addArg("input", value) }
    }
  }
}

interface MovedColumnsInProjectEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()
}

interface MoveProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : ProjectColumnEdge> columnEdge(init: () -> T): Stub<ProjectColumnEdge> = stub(init)

  fun <T> columnEdge(of: KFunction1<ProjectColumnEdge, Stub<T>>): Stub<T> =
        stub<ProjectColumnEdge, T>("columnEdge", of)
}

interface MoveProjectCardPayload : QType {
  fun <T : ProjectCardEdge> cardEdge(init: () -> T): Stub<ProjectCardEdge> = stub(init)

  fun <T> cardEdge(of: KFunction1<ProjectCardEdge, Stub<T>>): Stub<T> =
        stub<ProjectCardEdge, T>("cardEdge", of)

  fun clientMutationId(): Stub<String> = stub()
}

interface MilestonedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun milestoneTitle(): Stub<String> = stub()

  fun <T : MilestoneItem> subject(init: () -> T): Stub<MilestoneItem> = stub(init)

  fun <T> subject(of: KFunction1<MilestoneItem, Stub<T>>): Stub<T> =
        stub<MilestoneItem, T>("subject", of)
}

interface MilestoneEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Milestone> node(init: () -> T): Stub<Milestone> = stub(init)

  fun <T> node(of: KFunction1<Milestone, Stub<T>>): Stub<T> = stub<Milestone, T>("node", of)
}

interface MilestoneConnection : QType {
  fun <T : MilestoneEdge> edges(init: () -> T): Stub<MilestoneEdge> = stub(init)

  fun <T> edges(of: KFunction1<MilestoneEdge, Stub<T>>): Stub<T> =
        stub<MilestoneEdge, T>("edges", of)

  fun <T : Milestone> nodes(init: () -> T): Stub<Milestone> = stub(init)

  fun <T> nodes(of: KFunction1<Milestone, Stub<T>>): Stub<T> = stub<Milestone, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface Milestone : QType {
  fun <T : Actor> creator(init: () -> T): Stub<Actor> = stub(init)

  fun <T> creator(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("creator", of)

  fun description(): Stub<String> = stub()

  fun dueOn(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun number(): Stub<Int> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI> = stub()

  fun state(): Stub<MilestoneState> = stub()

  fun title(): Stub<String> = stub()

  fun url(): Stub<URI> = stub()
}

interface MergedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : Ref> mergeRef(init: () -> T): Stub<Ref> = stub(init)

  fun <T> mergeRef(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("mergeRef", of)

  fun mergeRefName(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun resourcePath(): Stub<URI> = stub()

  fun url(): Stub<URI> = stub()
}

interface MentionedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()
}

interface LockedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : Lockable> lockable(init: () -> T): Stub<Lockable> = stub(init)

  fun <T> lockable(of: KFunction1<Lockable, Stub<T>>): Stub<T> = stub<Lockable, T>("lockable", of)
}

interface LanguageEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Language> node(init: () -> T): Stub<Language> = stub(init)

  fun <T> node(of: KFunction1<Language, Stub<T>>): Stub<T> = stub<Language, T>("node", of)

  fun size(): Stub<Int> = stub()
}

interface LanguageConnection : QType {
  fun <T : LanguageEdge> edges(init: () -> T): Stub<LanguageEdge> = stub(init)

  fun <T> edges(of: KFunction1<LanguageEdge, Stub<T>>): Stub<T> = stub<LanguageEdge, T>("edges", of)

  fun <T : Language> nodes(init: () -> T): Stub<Language> = stub(init)

  fun <T> nodes(of: KFunction1<Language, Stub<T>>): Stub<T> = stub<Language, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()

  fun totalSize(): Stub<Int> = stub()
}

interface Language : QType {
  fun color(): Stub<String> = stub()

  fun id(): Stub<String> = stub()

  fun name(): Stub<String> = stub()
}

interface LabeledEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : Label> label(init: () -> T): Stub<Label> = stub(init)

  fun <T> label(of: KFunction1<Label, Stub<T>>): Stub<T> = stub<Label, T>("label", of)

  fun <T : Labelable> labelable(init: () -> T): Stub<Labelable> = stub(init)

  fun <T> labelable(of: KFunction1<Labelable, Stub<T>>): Stub<T> =
        stub<Labelable, T>("labelable", of)
}

interface LabelEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Label> node(init: () -> T): Stub<Label> = stub(init)

  fun <T> node(of: KFunction1<Label, Stub<T>>): Stub<T> = stub<Label, T>("node", of)
}

interface LabelConnection : QType {
  fun <T : LabelEdge> edges(init: () -> T): Stub<LabelEdge> = stub(init)

  fun <T> edges(of: KFunction1<LabelEdge, Stub<T>>): Stub<T> = stub<LabelEdge, T>("edges", of)

  fun <T : Label> nodes(init: () -> T): Stub<Label> = stub(init)

  fun <T> nodes(of: KFunction1<Label, Stub<T>>): Stub<T> = stub<Label, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface Label : QType {
  fun color(): Stub<String> = stub()

  fun id(): Stub<String> = stub()

  fun <T : IssueConnection> issues(init: () -> T): Stub<IssueConnection> = stub(init)

  fun <T> issues(of: KFunction1<IssueConnection, Stub<T>>): Stub<T> =
        stub<IssueConnection, T>("issues", of)

  fun name(): Stub<String> = stub()

  fun <T : PullRequestConnection> pullRequests(init: () -> T): Stub<PullRequestConnection> = stub(init)

  fun <T> pullRequests(of: KFunction1<PullRequestConnection, Stub<T>>): Stub<T> =
        stub<PullRequestConnection, T>("pullRequests", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  class IssuesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): IssuesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): IssuesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): IssuesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): IssuesArgs {
      return apply { addArg("before", value) }
    }

    fun labels(value: String): IssuesArgs {
      return apply { addArg("labels", value) }
    }

    fun orderBy(value: IssueOrder): IssuesArgs {
      return apply { addArg("orderBy", value) }
    }

    fun states(value: IssueState): IssuesArgs {
      return apply { addArg("states", value) }
    }
  }

  class PullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PullRequestsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): PullRequestsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): PullRequestsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): PullRequestsArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface IssueTimelineItemEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : IssueTimelineItem> node(init: () -> T): Stub<IssueTimelineItem> = stub(init)

  fun <T> node(of: KFunction1<IssueTimelineItem, Stub<T>>): Stub<T> =
        stub<IssueTimelineItem, T>("node", of)
}

interface IssueTimelineConnection : QType {
  fun <T : IssueTimelineItemEdge> edges(init: () -> T): Stub<IssueTimelineItemEdge> = stub(init)

  fun <T> edges(of: KFunction1<IssueTimelineItemEdge, Stub<T>>): Stub<T> =
        stub<IssueTimelineItemEdge, T>("edges", of)

  fun <T : IssueTimelineItem> nodes(init: () -> T): Stub<IssueTimelineItem> = stub(init)

  fun <T> nodes(of: KFunction1<IssueTimelineItem, Stub<T>>): Stub<T> =
        stub<IssueTimelineItem, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface IssueEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Issue> node(init: () -> T): Stub<Issue> = stub(init)

  fun <T> node(of: KFunction1<Issue, Stub<T>>): Stub<T> = stub<Issue, T>("node", of)
}

interface IssueConnection : QType {
  fun <T : IssueEdge> edges(init: () -> T): Stub<IssueEdge> = stub(init)

  fun <T> edges(of: KFunction1<IssueEdge, Stub<T>>): Stub<T> = stub<IssueEdge, T>("edges", of)

  fun <T : Issue> nodes(init: () -> T): Stub<Issue> = stub(init)

  fun <T> nodes(of: KFunction1<Issue, Stub<T>>): Stub<T> = stub<Issue, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface IssueCommentEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : IssueComment> node(init: () -> T): Stub<IssueComment> = stub(init)

  fun <T> node(of: KFunction1<IssueComment, Stub<T>>): Stub<T> = stub<IssueComment, T>("node", of)
}

interface IssueCommentConnection : QType {
  fun <T : IssueCommentEdge> edges(init: () -> T): Stub<IssueCommentEdge> = stub(init)

  fun <T> edges(of: KFunction1<IssueCommentEdge, Stub<T>>): Stub<T> =
        stub<IssueCommentEdge, T>("edges", of)

  fun <T : IssueComment> nodes(init: () -> T): Stub<IssueComment> = stub(init)

  fun <T> nodes(of: KFunction1<IssueComment, Stub<T>>): Stub<T> = stub<IssueComment, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface IssueComment : QType {
  fun <T : Actor> author(init: () -> T): Stub<Actor> = stub(init)

  fun <T> author(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub()

  fun body(): Stub<String> = stub()

  fun bodyHTML(): Stub<HTML> = stub()

  fun bodyText(): Stub<String> = stub()

  fun createdAt(): Stub<DateTime> = stub()

  fun createdViaEmail(): Stub<Boolean> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun <T : Actor> editor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> editor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("editor", of)

  fun id(): Stub<String> = stub()

  fun <T : Issue> issue(init: () -> T): Stub<Issue> = stub(init)

  fun <T> issue(of: KFunction1<Issue, Stub<T>>): Stub<T> = stub<Issue, T>("issue", of)

  fun lastEditedAt(): Stub<DateTime> = stub()

  fun publishedAt(): Stub<DateTime> = stub()

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup> = stub(init)

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T>>): Stub<T> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : ReactionConnection> reactions(init: () -> T): Stub<ReactionConnection> = stub(init)

  fun <T> reactions(of: KFunction1<ReactionConnection, Stub<T>>): Stub<T> =
        stub<ReactionConnection, T>("reactions", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun updatedAt(): Stub<DateTime> = stub()

  fun viewerCanDelete(): Stub<Boolean> = stub()

  fun viewerCanReact(): Stub<Boolean> = stub()

  fun viewerCanUpdate(): Stub<Boolean> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub()

  fun viewerDidAuthor(): Stub<Boolean> = stub()

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReactionsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReactionsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReactionsArgs {
      return apply { addArg("before", value) }
    }

    fun content(value: ReactionContent): ReactionsArgs {
      return apply { addArg("content", value) }
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
      return apply { addArg("orderBy", value) }
    }
  }
}

interface Issue : QType {
  fun <T : UserConnection> assignees(init: () -> T): Stub<UserConnection> = stub(init)

  fun <T> assignees(of: KFunction1<UserConnection, Stub<T>>): Stub<T> =
        stub<UserConnection, T>("assignees", of)

  fun <T : Actor> author(init: () -> T): Stub<Actor> = stub(init)

  fun <T> author(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub()

  fun body(): Stub<String> = stub()

  fun bodyHTML(): Stub<HTML> = stub()

  fun bodyText(): Stub<String> = stub()

  fun closed(): Stub<Boolean> = stub()

  fun <T : IssueCommentConnection> comments(init: () -> T): Stub<IssueCommentConnection> = stub(init)

  fun <T> comments(of: KFunction1<IssueCommentConnection, Stub<T>>): Stub<T> =
        stub<IssueCommentConnection, T>("comments", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun createdViaEmail(): Stub<Boolean> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun <T : Actor> editor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> editor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("editor", of)

  fun id(): Stub<String> = stub()

  fun <T : LabelConnection> labels(init: () -> T): Stub<LabelConnection> = stub(init)

  fun <T> labels(of: KFunction1<LabelConnection, Stub<T>>): Stub<T> =
        stub<LabelConnection, T>("labels", of)

  fun lastEditedAt(): Stub<DateTime> = stub()

  fun locked(): Stub<Boolean> = stub()

  fun <T : Milestone> milestone(init: () -> T): Stub<Milestone> = stub(init)

  fun <T> milestone(of: KFunction1<Milestone, Stub<T>>): Stub<T> =
        stub<Milestone, T>("milestone", of)

  fun number(): Stub<Int> = stub()

  fun <T : UserConnection> participants(init: () -> T): Stub<UserConnection> = stub(init)

  fun <T> participants(of: KFunction1<UserConnection, Stub<T>>): Stub<T> =
        stub<UserConnection, T>("participants", of)

  fun publishedAt(): Stub<DateTime> = stub()

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup> = stub(init)

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T>>): Stub<T> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : ReactionConnection> reactions(init: () -> T): Stub<ReactionConnection> = stub(init)

  fun <T> reactions(of: KFunction1<ReactionConnection, Stub<T>>): Stub<T> =
        stub<ReactionConnection, T>("reactions", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI> = stub()

  fun state(): Stub<IssueState> = stub()

  fun <T : IssueTimelineConnection> timeline(init: () -> T): Stub<IssueTimelineConnection> = stub(init)

  fun <T> timeline(of: KFunction1<IssueTimelineConnection, Stub<T>>): Stub<T> =
        stub<IssueTimelineConnection, T>("timeline", of)

  fun title(): Stub<String> = stub()

  fun updatedAt(): Stub<DateTime> = stub()

  fun url(): Stub<URI> = stub()

  fun viewerCanReact(): Stub<Boolean> = stub()

  fun viewerCanSubscribe(): Stub<Boolean> = stub()

  fun viewerCanUpdate(): Stub<Boolean> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub()

  fun viewerDidAuthor(): Stub<Boolean> = stub()

  fun viewerSubscription(): Stub<SubscriptionState> = stub()

  class AssigneesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssigneesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): AssigneesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): AssigneesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): AssigneesArgs {
      return apply { addArg("before", value) }
    }
  }

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): CommentsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): CommentsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): CommentsArgs {
      return apply { addArg("before", value) }
    }
  }

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): LabelsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): LabelsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): LabelsArgs {
      return apply { addArg("before", value) }
    }
  }

  class ParticipantsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ParticipantsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ParticipantsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ParticipantsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ParticipantsArgs {
      return apply { addArg("before", value) }
    }
  }

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReactionsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReactionsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReactionsArgs {
      return apply { addArg("before", value) }
    }

    fun content(value: ReactionContent): ReactionsArgs {
      return apply { addArg("content", value) }
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
      return apply { addArg("orderBy", value) }
    }
  }

  class TimelineArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): TimelineArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): TimelineArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): TimelineArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): TimelineArgs {
      return apply { addArg("before", value) }
    }

    fun since(value: DateTime): TimelineArgs {
      return apply { addArg("since", value) }
    }
  }
}

interface HeadRefRestoredEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)
}

interface HeadRefForcePushedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun <T : Commit> afterCommit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> afterCommit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("afterCommit", of)

  fun <T : Commit> beforeCommit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> beforeCommit(of: KFunction1<Commit, Stub<T>>): Stub<T> =
        stub<Commit, T>("beforeCommit", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : Ref> ref(init: () -> T): Stub<Ref> = stub(init)

  fun <T> ref(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("ref", of)
}

interface HeadRefDeletedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun <T : Ref> headRef(init: () -> T): Stub<Ref> = stub(init)

  fun <T> headRef(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("headRef", of)

  fun headRefName(): Stub<String> = stub()

  fun id(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)
}

interface GpgSignature : QType {
  fun email(): Stub<String> = stub()

  fun isValid(): Stub<Boolean> = stub()

  fun keyId(): Stub<String> = stub()

  fun payload(): Stub<String> = stub()

  fun signature(): Stub<String> = stub()

  fun <T : User> signer(init: () -> T): Stub<User> = stub(init)

  fun <T> signer(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("signer", of)

  fun state(): Stub<GitSignatureState> = stub()
}

interface GitActor : QType {
  fun avatarUrl(): Stub<URI> = stub()

  fun date(): Stub<GitTimestamp> = stub()

  fun email(): Stub<String> = stub()

  fun name(): Stub<String> = stub()

  fun <T : User> user(init: () -> T): Stub<User> = stub(init)

  fun <T> user(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("user", of)

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
      return apply { addArg("size", value) }
    }
  }
}

interface GistEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Gist> node(init: () -> T): Stub<Gist> = stub(init)

  fun <T> node(of: KFunction1<Gist, Stub<T>>): Stub<T> = stub<Gist, T>("node", of)
}

interface GistConnection : QType {
  fun <T : GistEdge> edges(init: () -> T): Stub<GistEdge> = stub(init)

  fun <T> edges(of: KFunction1<GistEdge, Stub<T>>): Stub<T> = stub<GistEdge, T>("edges", of)

  fun <T : Gist> nodes(init: () -> T): Stub<Gist> = stub(init)

  fun <T> nodes(of: KFunction1<Gist, Stub<T>>): Stub<T> = stub<Gist, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface GistCommentEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : GistComment> node(init: () -> T): Stub<GistComment> = stub(init)

  fun <T> node(of: KFunction1<GistComment, Stub<T>>): Stub<T> = stub<GistComment, T>("node", of)
}

interface GistCommentConnection : QType {
  fun <T : GistCommentEdge> edges(init: () -> T): Stub<GistCommentEdge> = stub(init)

  fun <T> edges(of: KFunction1<GistCommentEdge, Stub<T>>): Stub<T> =
        stub<GistCommentEdge, T>("edges", of)

  fun <T : GistComment> nodes(init: () -> T): Stub<GistComment> = stub(init)

  fun <T> nodes(of: KFunction1<GistComment, Stub<T>>): Stub<T> = stub<GistComment, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface GistComment : QType {
  fun <T : Actor> author(init: () -> T): Stub<Actor> = stub(init)

  fun <T> author(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub()

  fun body(): Stub<String> = stub()

  fun bodyHTML(): Stub<HTML> = stub()

  fun createdAt(): Stub<DateTime> = stub()

  fun createdViaEmail(): Stub<Boolean> = stub()

  fun <T : Actor> editor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> editor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("editor", of)

  fun id(): Stub<String> = stub()

  fun lastEditedAt(): Stub<DateTime> = stub()

  fun publishedAt(): Stub<DateTime> = stub()

  fun updatedAt(): Stub<DateTime> = stub()

  fun viewerCanDelete(): Stub<Boolean> = stub()

  fun viewerCanUpdate(): Stub<Boolean> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub()

  fun viewerDidAuthor(): Stub<Boolean> = stub()
}

interface Gist : QType {
  fun <T : GistCommentConnection> comments(init: () -> T): Stub<GistCommentConnection> = stub(init)

  fun <T> comments(of: KFunction1<GistCommentConnection, Stub<T>>): Stub<T> =
        stub<GistCommentConnection, T>("comments", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun description(): Stub<String> = stub()

  fun id(): Stub<String> = stub()

  fun isPublic(): Stub<Boolean> = stub()

  fun name(): Stub<String> = stub()

  fun <T : RepositoryOwner> owner(init: () -> T): Stub<RepositoryOwner> = stub(init)

  fun <T> owner(of: KFunction1<RepositoryOwner, Stub<T>>): Stub<T> =
        stub<RepositoryOwner, T>("owner", of)

  fun <T : StargazerConnection> stargazers(init: () -> T): Stub<StargazerConnection> = stub(init)

  fun <T> stargazers(of: KFunction1<StargazerConnection, Stub<T>>): Stub<T> =
        stub<StargazerConnection, T>("stargazers", of)

  fun updatedAt(): Stub<DateTime> = stub()

  fun viewerHasStarred(): Stub<Boolean> = stub()

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): CommentsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): CommentsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): CommentsArgs {
      return apply { addArg("before", value) }
    }
  }

  class StargazersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StargazersArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): StargazersArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): StargazersArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): StargazersArgs {
      return apply { addArg("before", value) }
    }

    fun orderBy(value: StarOrder): StargazersArgs {
      return apply { addArg("orderBy", value) }
    }
  }
}

interface FollowingConnection : QType {
  fun <T : UserEdge> edges(init: () -> T): Stub<UserEdge> = stub(init)

  fun <T> edges(of: KFunction1<UserEdge, Stub<T>>): Stub<T> = stub<UserEdge, T>("edges", of)

  fun <T : User> nodes(init: () -> T): Stub<User> = stub(init)

  fun <T> nodes(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface FollowerConnection : QType {
  fun <T : UserEdge> edges(init: () -> T): Stub<UserEdge> = stub(init)

  fun <T> edges(of: KFunction1<UserEdge, Stub<T>>): Stub<T> = stub<UserEdge, T>("edges", of)

  fun <T : User> nodes(init: () -> T): Stub<User> = stub(init)

  fun <T> nodes(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface ExternalIdentityScimAttributes : QType {
  fun username(): Stub<String> = stub()
}

interface ExternalIdentitySamlAttributes : QType {
  fun nameId(): Stub<String> = stub()
}

interface ExternalIdentityEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : ExternalIdentity> node(init: () -> T): Stub<ExternalIdentity> = stub(init)

  fun <T> node(of: KFunction1<ExternalIdentity, Stub<T>>): Stub<T> =
        stub<ExternalIdentity, T>("node", of)
}

interface ExternalIdentityConnection : QType {
  fun <T : ExternalIdentityEdge> edges(init: () -> T): Stub<ExternalIdentityEdge> = stub(init)

  fun <T> edges(of: KFunction1<ExternalIdentityEdge, Stub<T>>): Stub<T> =
        stub<ExternalIdentityEdge, T>("edges", of)

  fun <T : ExternalIdentity> nodes(init: () -> T): Stub<ExternalIdentity> = stub(init)

  fun <T> nodes(of: KFunction1<ExternalIdentity, Stub<T>>): Stub<T> =
        stub<ExternalIdentity, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface ExternalIdentity : QType {
  fun guid(): Stub<String> = stub()

  fun id(): Stub<String> = stub()

  fun <T : OrganizationInvitation> organizationInvitation(init: () -> T): Stub<OrganizationInvitation> = stub(init)

  fun <T> organizationInvitation(of: KFunction1<OrganizationInvitation, Stub<T>>): Stub<T> =
        stub<OrganizationInvitation, T>("organizationInvitation", of)

  fun <T : ExternalIdentitySamlAttributes> samlIdentity(init: () -> T): Stub<ExternalIdentitySamlAttributes> = stub(init)

  fun <T> samlIdentity(of: KFunction1<ExternalIdentitySamlAttributes, Stub<T>>): Stub<T> =
        stub<ExternalIdentitySamlAttributes, T>("samlIdentity", of)

  fun <T : ExternalIdentityScimAttributes> scimIdentity(init: () -> T): Stub<ExternalIdentityScimAttributes> = stub(init)

  fun <T> scimIdentity(of: KFunction1<ExternalIdentityScimAttributes, Stub<T>>): Stub<T> =
        stub<ExternalIdentityScimAttributes, T>("scimIdentity", of)

  fun <T : User> user(init: () -> T): Stub<User> = stub(init)

  fun <T> user(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("user", of)
}

interface DismissPullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview> = stub(init)

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T>>): Stub<T> =
        stub<PullRequestReview, T>("pullRequestReview", of)
}

interface DeploymentStatusEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : DeploymentStatus> node(init: () -> T): Stub<DeploymentStatus> = stub(init)

  fun <T> node(of: KFunction1<DeploymentStatus, Stub<T>>): Stub<T> =
        stub<DeploymentStatus, T>("node", of)
}

interface DeploymentStatusConnection : QType {
  fun <T : DeploymentStatusEdge> edges(init: () -> T): Stub<DeploymentStatusEdge> = stub(init)

  fun <T> edges(of: KFunction1<DeploymentStatusEdge, Stub<T>>): Stub<T> =
        stub<DeploymentStatusEdge, T>("edges", of)

  fun <T : DeploymentStatus> nodes(init: () -> T): Stub<DeploymentStatus> = stub(init)

  fun <T> nodes(of: KFunction1<DeploymentStatus, Stub<T>>): Stub<T> =
        stub<DeploymentStatus, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface DeploymentStatus : QType {
  fun <T : Actor> creator(init: () -> T): Stub<Actor> = stub(init)

  fun <T> creator(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("creator", of)

  fun <T : Deployment> deployment(init: () -> T): Stub<Deployment> = stub(init)

  fun <T> deployment(of: KFunction1<Deployment, Stub<T>>): Stub<T> =
        stub<Deployment, T>("deployment", of)

  fun description(): Stub<String> = stub()

  fun environmentUrl(): Stub<URI> = stub()

  fun id(): Stub<String> = stub()

  fun logUrl(): Stub<URI> = stub()

  fun state(): Stub<DeploymentStatusState> = stub()
}

interface DeploymentEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Deployment> node(init: () -> T): Stub<Deployment> = stub(init)

  fun <T> node(of: KFunction1<Deployment, Stub<T>>): Stub<T> = stub<Deployment, T>("node", of)
}

interface DeploymentConnection : QType {
  fun <T : DeploymentEdge> edges(init: () -> T): Stub<DeploymentEdge> = stub(init)

  fun <T> edges(of: KFunction1<DeploymentEdge, Stub<T>>): Stub<T> =
        stub<DeploymentEdge, T>("edges", of)

  fun <T : Deployment> nodes(init: () -> T): Stub<Deployment> = stub(init)

  fun <T> nodes(of: KFunction1<Deployment, Stub<T>>): Stub<T> = stub<Deployment, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface Deployment : QType {
  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun <T : Actor> creator(init: () -> T): Stub<Actor> = stub(init)

  fun <T> creator(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("creator", of)

  fun environment(): Stub<String> = stub()

  fun id(): Stub<String> = stub()

  fun <T : DeploymentStatus> latestStatus(init: () -> T): Stub<DeploymentStatus> = stub(init)

  fun <T> latestStatus(of: KFunction1<DeploymentStatus, Stub<T>>): Stub<T> =
        stub<DeploymentStatus, T>("latestStatus", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun state(): Stub<DeploymentState> = stub()

  fun <T : DeploymentStatusConnection> statuses(init: () -> T): Stub<DeploymentStatusConnection> = stub(init)

  fun <T> statuses(of: KFunction1<DeploymentStatusConnection, Stub<T>>): Stub<T> =
        stub<DeploymentStatusConnection, T>("statuses", of)

  class StatusesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StatusesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): StatusesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): StatusesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): StatusesArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface DeployedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun <T : Deployment> deployment(init: () -> T): Stub<Deployment> = stub(init)

  fun <T> deployment(of: KFunction1<Deployment, Stub<T>>): Stub<T> =
        stub<Deployment, T>("deployment", of)

  fun id(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : Ref> ref(init: () -> T): Stub<Ref> = stub(init)

  fun <T> ref(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("ref", of)
}

interface DemilestonedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun milestoneTitle(): Stub<String> = stub()

  fun <T : MilestoneItem> subject(init: () -> T): Stub<MilestoneItem> = stub(init)

  fun <T> subject(of: KFunction1<MilestoneItem, Stub<T>>): Stub<T> =
        stub<MilestoneItem, T>("subject", of)
}

interface DeletePullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview> = stub(init)

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T>>): Stub<T> =
        stub<PullRequestReview, T>("pullRequestReview", of)
}

interface DeleteProjectPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : ProjectOwner> owner(init: () -> T): Stub<ProjectOwner> = stub(init)

  fun <T> owner(of: KFunction1<ProjectOwner, Stub<T>>): Stub<T> = stub<ProjectOwner, T>("owner", of)
}

interface DeleteProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun deletedColumnId(): Stub<String> = stub()

  fun <T : Project> project(init: () -> T): Stub<Project> = stub(init)

  fun <T> project(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("project", of)
}

interface DeleteProjectCardPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : ProjectColumn> column(init: () -> T): Stub<ProjectColumn> = stub(init)

  fun <T> column(of: KFunction1<ProjectColumn, Stub<T>>): Stub<T> =
        stub<ProjectColumn, T>("column", of)

  fun deletedCardId(): Stub<String> = stub()
}

interface DeclineTopicSuggestionPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : Topic> topic(init: () -> T): Stub<Topic> = stub(init)

  fun <T> topic(of: KFunction1<Topic, Stub<T>>): Stub<T> = stub<Topic, T>("topic", of)
}

interface CreateProjectPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : Project> project(init: () -> T): Stub<Project> = stub(init)

  fun <T> project(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("project", of)
}

interface ConvertedNoteToIssueEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()
}

interface CommitHistoryConnection : QType {
  fun <T : CommitEdge> edges(init: () -> T): Stub<CommitEdge> = stub(init)

  fun <T> edges(of: KFunction1<CommitEdge, Stub<T>>): Stub<T> = stub<CommitEdge, T>("edges", of)

  fun <T : Commit> nodes(init: () -> T): Stub<Commit> = stub(init)

  fun <T> nodes(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)
}

interface CommitEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : Commit> node(init: () -> T): Stub<Commit> = stub(init)

  fun <T> node(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("node", of)
}

interface CommitCommentThread : QType {
  fun <T : CommitCommentConnection> comments(init: () -> T): Stub<CommitCommentConnection> = stub(init)

  fun <T> comments(of: KFunction1<CommitCommentConnection, Stub<T>>): Stub<T> =
        stub<CommitCommentConnection, T>("comments", of)

  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun id(): Stub<String> = stub()

  fun path(): Stub<String> = stub()

  fun position(): Stub<Int> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): CommentsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): CommentsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): CommentsArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface CommitCommentEdge : QType {
  fun cursor(): Stub<String> = stub()

  fun <T : CommitComment> node(init: () -> T): Stub<CommitComment> = stub(init)

  fun <T> node(of: KFunction1<CommitComment, Stub<T>>): Stub<T> = stub<CommitComment, T>("node", of)
}

interface CommitCommentConnection : QType {
  fun <T : CommitCommentEdge> edges(init: () -> T): Stub<CommitCommentEdge> = stub(init)

  fun <T> edges(of: KFunction1<CommitCommentEdge, Stub<T>>): Stub<T> =
        stub<CommitCommentEdge, T>("edges", of)

  fun <T : CommitComment> nodes(init: () -> T): Stub<CommitComment> = stub(init)

  fun <T> nodes(of: KFunction1<CommitComment, Stub<T>>): Stub<T> =
        stub<CommitComment, T>("nodes", of)

  fun <T : PageInfo> pageInfo(init: () -> T): Stub<PageInfo> = stub(init)

  fun <T> pageInfo(of: KFunction1<PageInfo, Stub<T>>): Stub<T> = stub<PageInfo, T>("pageInfo", of)

  fun totalCount(): Stub<Int> = stub()
}

interface CommitComment : QType {
  fun <T : Actor> author(init: () -> T): Stub<Actor> = stub(init)

  fun <T> author(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub()

  fun body(): Stub<String> = stub()

  fun bodyHTML(): Stub<HTML> = stub()

  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun createdViaEmail(): Stub<Boolean> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun <T : Actor> editor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> editor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("editor", of)

  fun id(): Stub<String> = stub()

  fun lastEditedAt(): Stub<DateTime> = stub()

  fun path(): Stub<String> = stub()

  fun position(): Stub<Int> = stub()

  fun publishedAt(): Stub<DateTime> = stub()

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup> = stub(init)

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T>>): Stub<T> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : ReactionConnection> reactions(init: () -> T): Stub<ReactionConnection> = stub(init)

  fun <T> reactions(of: KFunction1<ReactionConnection, Stub<T>>): Stub<T> =
        stub<ReactionConnection, T>("reactions", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun updatedAt(): Stub<DateTime> = stub()

  fun viewerCanDelete(): Stub<Boolean> = stub()

  fun viewerCanReact(): Stub<Boolean> = stub()

  fun viewerCanUpdate(): Stub<Boolean> = stub()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub()

  fun viewerDidAuthor(): Stub<Boolean> = stub()

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReactionsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReactionsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReactionsArgs {
      return apply { addArg("before", value) }
    }

    fun content(value: ReactionContent): ReactionsArgs {
      return apply { addArg("content", value) }
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
      return apply { addArg("orderBy", value) }
    }
  }
}

interface Commit : QType {
  fun abbreviatedOid(): Stub<String> = stub()

  fun <T : GitActor> author(init: () -> T): Stub<GitActor> = stub(init)

  fun <T> author(of: KFunction1<GitActor, Stub<T>>): Stub<T> = stub<GitActor, T>("author", of)

  fun authoredByCommitter(): Stub<Boolean> = stub()

  fun <T : Blame> blame(init: () -> T): Stub<Blame> = stub(init)

  fun <T> blame(of: KFunction1<Blame, Stub<T>>): Stub<T> = stub<Blame, T>("blame", of)

  fun <T : CommitCommentConnection> comments(init: () -> T): Stub<CommitCommentConnection> = stub(init)

  fun <T> comments(of: KFunction1<CommitCommentConnection, Stub<T>>): Stub<T> =
        stub<CommitCommentConnection, T>("comments", of)

  fun commitResourcePath(): Stub<URI> = stub()

  fun commitUrl(): Stub<URI> = stub()

  fun committedDate(): Stub<DateTime> = stub()

  fun committedViaWeb(): Stub<Boolean> = stub()

  fun <T : GitActor> committer(init: () -> T): Stub<GitActor> = stub(init)

  fun <T> committer(of: KFunction1<GitActor, Stub<T>>): Stub<T> = stub<GitActor, T>("committer", of)

  fun <T : CommitHistoryConnection> history(init: () -> T): Stub<CommitHistoryConnection> = stub(init)

  fun <T> history(of: KFunction1<CommitHistoryConnection, Stub<T>>): Stub<T> =
        stub<CommitHistoryConnection, T>("history", of)

  fun id(): Stub<String> = stub()

  fun message(): Stub<String> = stub()

  fun messageBody(): Stub<String> = stub()

  fun messageBodyHTML(): Stub<HTML> = stub()

  fun messageHeadline(): Stub<String> = stub()

  fun messageHeadlineHTML(): Stub<HTML> = stub()

  fun oid(): Stub<GitObjectID> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI> = stub()

  fun <T : GitSignature> signature(init: () -> T): Stub<GitSignature> = stub(init)

  fun <T> signature(of: KFunction1<GitSignature, Stub<T>>): Stub<T> =
        stub<GitSignature, T>("signature", of)

  fun <T : Status> status(init: () -> T): Stub<Status> = stub(init)

  fun <T> status(of: KFunction1<Status, Stub<T>>): Stub<T> = stub<Status, T>("status", of)

  fun <T : Tree> tree(init: () -> T): Stub<Tree> = stub(init)

  fun <T> tree(of: KFunction1<Tree, Stub<T>>): Stub<T> = stub<Tree, T>("tree", of)

  fun treeResourcePath(): Stub<URI> = stub()

  fun treeUrl(): Stub<URI> = stub()

  fun url(): Stub<URI> = stub()

  fun viewerCanSubscribe(): Stub<Boolean> = stub()

  fun viewerSubscription(): Stub<SubscriptionState> = stub()

  class BlameArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun path(value: String): BlameArgs {
      return apply { addArg("path", value) }
    }
  }

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): CommentsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): CommentsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): CommentsArgs {
      return apply { addArg("before", value) }
    }
  }

  class HistoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): HistoryArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): HistoryArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): HistoryArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): HistoryArgs {
      return apply { addArg("before", value) }
    }

    fun path(value: String): HistoryArgs {
      return apply { addArg("path", value) }
    }

    fun author(value: CommitAuthor): HistoryArgs {
      return apply { addArg("author", value) }
    }

    fun since(value: GitTimestamp): HistoryArgs {
      return apply { addArg("since", value) }
    }

    fun until(value: GitTimestamp): HistoryArgs {
      return apply { addArg("until", value) }
    }
  }
}

interface CommentDeletedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()
}

interface CodeOfConduct : QType {
  fun body(): Stub<String> = stub()

  fun key(): Stub<String> = stub()

  fun name(): Stub<String> = stub()

  fun url(): Stub<URI> = stub()
}

interface ClosedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun <T : Closable> closable(init: () -> T): Stub<Closable> = stub(init)

  fun <T> closable(of: KFunction1<Closable, Stub<T>>): Stub<T> = stub<Closable, T>("closable", of)

  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()
}

interface Bot : QType {
  fun avatarUrl(): Stub<URI> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()

  fun login(): Stub<String> = stub()

  fun resourcePath(): Stub<URI> = stub()

  fun url(): Stub<URI> = stub()

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
      return apply { addArg("size", value) }
    }
  }
}

interface Blob : QType {
  fun abbreviatedOid(): Stub<String> = stub()

  fun byteSize(): Stub<Int> = stub()

  fun commitResourcePath(): Stub<URI> = stub()

  fun commitUrl(): Stub<URI> = stub()

  fun id(): Stub<String> = stub()

  fun isBinary(): Stub<Boolean> = stub()

  fun isTruncated(): Stub<Boolean> = stub()

  fun oid(): Stub<GitObjectID> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun text(): Stub<String> = stub()
}

interface BlameRange : QType {
  fun age(): Stub<Int> = stub()

  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun endingLine(): Stub<Int> = stub()

  fun startingLine(): Stub<Int> = stub()
}

interface Blame : QType {
  fun <T : BlameRange> ranges(init: () -> T): Stub<BlameRange> = stub(init)

  fun <T> ranges(of: KFunction1<BlameRange, Stub<T>>): Stub<T> = stub<BlameRange, T>("ranges", of)
}

interface BaseRefForcePushedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun <T : Commit> afterCommit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> afterCommit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("afterCommit", of)

  fun <T : Commit> beforeCommit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> beforeCommit(of: KFunction1<Commit, Stub<T>>): Stub<T> =
        stub<Commit, T>("beforeCommit", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : PullRequest> pullRequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullRequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullRequest", of)

  fun <T : Ref> ref(init: () -> T): Stub<Ref> = stub(init)

  fun <T> ref(of: KFunction1<Ref, Stub<T>>): Stub<T> = stub<Ref, T>("ref", of)
}

interface BaseRefChangedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()
}

interface AssignedEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun <T : Assignable> assignable(init: () -> T): Stub<Assignable> = stub(init)

  fun <T> assignable(of: KFunction1<Assignable, Stub<T>>): Stub<T> =
        stub<Assignable, T>("assignable", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun id(): Stub<String> = stub()

  fun <T : User> user(init: () -> T): Stub<User> = stub(init)

  fun <T> user(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("user", of)
}

interface AddedToProjectEvent : QType {
  fun <T : Actor> actor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> actor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("actor", of)

  fun createdAt(): Stub<DateTime> = stub()

  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()
}

interface AddStarPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : Starrable> starrable(init: () -> T): Stub<Starrable> = stub(init)

  fun <T> starrable(of: KFunction1<Starrable, Stub<T>>): Stub<T> =
        stub<Starrable, T>("starrable", of)
}

interface AddReactionPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : Reaction> reaction(init: () -> T): Stub<Reaction> = stub(init)

  fun <T> reaction(of: KFunction1<Reaction, Stub<T>>): Stub<T> = stub<Reaction, T>("reaction", of)

  fun <T : Reactable> subject(init: () -> T): Stub<Reactable> = stub(init)

  fun <T> subject(of: KFunction1<Reactable, Stub<T>>): Stub<T> = stub<Reactable, T>("subject", of)
}

interface AddPullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : PullRequestReview> pullRequestReview(init: () -> T): Stub<PullRequestReview> = stub(init)

  fun <T> pullRequestReview(of: KFunction1<PullRequestReview, Stub<T>>): Stub<T> =
        stub<PullRequestReview, T>("pullRequestReview", of)

  fun <T : PullRequestReviewEdge> reviewEdge(init: () -> T): Stub<PullRequestReviewEdge> = stub(init)

  fun <T> reviewEdge(of: KFunction1<PullRequestReviewEdge, Stub<T>>): Stub<T> =
        stub<PullRequestReviewEdge, T>("reviewEdge", of)
}

interface AddPullRequestReviewCommentPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : PullRequestReviewComment> comment(init: () -> T): Stub<PullRequestReviewComment> = stub(init)

  fun <T> comment(of: KFunction1<PullRequestReviewComment, Stub<T>>): Stub<T> =
        stub<PullRequestReviewComment, T>("comment", of)

  fun <T : PullRequestReviewCommentEdge> commentEdge(init: () -> T): Stub<PullRequestReviewCommentEdge> = stub(init)

  fun <T> commentEdge(of: KFunction1<PullRequestReviewCommentEdge, Stub<T>>): Stub<T> =
        stub<PullRequestReviewCommentEdge, T>("commentEdge", of)
}

interface AddProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : ProjectColumnEdge> columnEdge(init: () -> T): Stub<ProjectColumnEdge> = stub(init)

  fun <T> columnEdge(of: KFunction1<ProjectColumnEdge, Stub<T>>): Stub<T> =
        stub<ProjectColumnEdge, T>("columnEdge", of)

  fun <T : Project> project(init: () -> T): Stub<Project> = stub(init)

  fun <T> project(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("project", of)
}

interface AddProjectCardPayload : QType {
  fun <T : ProjectCardEdge> cardEdge(init: () -> T): Stub<ProjectCardEdge> = stub(init)

  fun <T> cardEdge(of: KFunction1<ProjectCardEdge, Stub<T>>): Stub<T> =
        stub<ProjectCardEdge, T>("cardEdge", of)

  fun clientMutationId(): Stub<String> = stub()

  fun <T : Project> projectColumn(init: () -> T): Stub<Project> = stub(init)

  fun <T> projectColumn(of: KFunction1<Project, Stub<T>>): Stub<T> =
        stub<Project, T>("projectColumn", of)
}

interface AddCommentPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : IssueCommentEdge> commentEdge(init: () -> T): Stub<IssueCommentEdge> = stub(init)

  fun <T> commentEdge(of: KFunction1<IssueCommentEdge, Stub<T>>): Stub<T> =
        stub<IssueCommentEdge, T>("commentEdge", of)

  fun <T : Node> subject(init: () -> T): Stub<Node> = stub(init)

  fun <T> subject(of: KFunction1<Node, Stub<T>>): Stub<T> = stub<Node, T>("subject", of)

  fun <T : IssueTimelineItemEdge> timelineEdge(init: () -> T): Stub<IssueTimelineItemEdge> = stub(init)

  fun <T> timelineEdge(of: KFunction1<IssueTimelineItemEdge, Stub<T>>): Stub<T> =
        stub<IssueTimelineItemEdge, T>("timelineEdge", of)
}

interface AcceptTopicSuggestionPayload : QType {
  fun clientMutationId(): Stub<String> = stub()

  fun <T : Topic> topic(init: () -> T): Stub<Topic> = stub(init)

  fun <T> topic(of: KFunction1<Topic, Stub<T>>): Stub<T> = stub<Topic, T>("topic", of)
}

interface UpdatableComment : QType {
  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub()
}

interface Updatable : QType {
  fun viewerCanUpdate(): Stub<Boolean> = stub()
}

interface UniformResourceLocatable : QType {
  fun resourcePath(): Stub<URI> = stub()

  fun url(): Stub<URI> = stub()
}

interface Subscribable : QType {
  fun viewerCanSubscribe(): Stub<Boolean> = stub()

  fun viewerSubscription(): Stub<SubscriptionState> = stub()
}

interface Starrable : QType {
  fun id(): Stub<String> = stub()

  fun <T : StargazerConnection> stargazers(init: () -> T): Stub<StargazerConnection> = stub(init)

  fun <T> stargazers(of: KFunction1<StargazerConnection, Stub<T>>): Stub<T> =
        stub<StargazerConnection, T>("stargazers", of)

  fun viewerHasStarred(): Stub<Boolean> = stub()

  class StargazersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StargazersArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): StargazersArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): StargazersArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): StargazersArgs {
      return apply { addArg("before", value) }
    }

    fun orderBy(value: StarOrder): StargazersArgs {
      return apply { addArg("orderBy", value) }
    }
  }
}

interface RepositoryOwner : QType {
  fun avatarUrl(): Stub<URI> = stub()

  fun id(): Stub<String> = stub()

  fun login(): Stub<String> = stub()

  fun <T : RepositoryConnection> pinnedRepositories(init: () -> T): Stub<RepositoryConnection> = stub(init)

  fun <T> pinnedRepositories(of: KFunction1<RepositoryConnection, Stub<T>>): Stub<T> =
        stub<RepositoryConnection, T>("pinnedRepositories", of)

  fun <T : RepositoryConnection> repositories(init: () -> T): Stub<RepositoryConnection> = stub(init)

  fun <T> repositories(of: KFunction1<RepositoryConnection, Stub<T>>): Stub<T> =
        stub<RepositoryConnection, T>("repositories", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun resourcePath(): Stub<URI> = stub()

  fun url(): Stub<URI> = stub()

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
      return apply { addArg("size", value) }
    }
  }

  class PinnedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PinnedRepositoriesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): PinnedRepositoriesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): PinnedRepositoriesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): PinnedRepositoriesArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs {
      return apply { addArg("privacy", value) }
    }

    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs {
      return apply { addArg("orderBy", value) }
    }

    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs {
      return apply { addArg("affiliations", value) }
    }

    fun isLocked(value: Boolean): PinnedRepositoriesArgs {
      return apply { addArg("isLocked", value) }
    }
  }

  class RepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoriesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): RepositoriesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): RepositoriesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): RepositoriesArgs {
      return apply { addArg("before", value) }
    }

    fun privacy(value: RepositoryPrivacy): RepositoriesArgs {
      return apply { addArg("privacy", value) }
    }

    fun orderBy(value: RepositoryOrder): RepositoriesArgs {
      return apply { addArg("orderBy", value) }
    }

    fun affiliations(value: RepositoryAffiliation): RepositoriesArgs {
      return apply { addArg("affiliations", value) }
    }

    fun isLocked(value: Boolean): RepositoriesArgs {
      return apply { addArg("isLocked", value) }
    }

    fun isFork(value: Boolean): RepositoriesArgs {
      return apply { addArg("isFork", value) }
    }
  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): RepositoryArgs {
      return apply { addArg("name", value) }
    }
  }
}

interface RepositoryNode : QType {
  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)
}

interface RepositoryInfo : QType {
  fun createdAt(): Stub<DateTime> = stub()

  fun description(): Stub<String> = stub()

  fun descriptionHTML(): Stub<HTML> = stub()

  fun hasIssuesEnabled(): Stub<Boolean> = stub()

  fun hasWikiEnabled(): Stub<Boolean> = stub()

  fun homepageUrl(): Stub<URI> = stub()

  fun isFork(): Stub<Boolean> = stub()

  fun isLocked(): Stub<Boolean> = stub()

  fun isMirror(): Stub<Boolean> = stub()

  fun isPrivate(): Stub<Boolean> = stub()

  fun license(): Stub<String> = stub()

  fun lockReason(): Stub<RepositoryLockReason> = stub()

  fun mirrorUrl(): Stub<URI> = stub()

  fun name(): Stub<String> = stub()

  fun nameWithOwner(): Stub<String> = stub()

  fun <T : RepositoryOwner> owner(init: () -> T): Stub<RepositoryOwner> = stub(init)

  fun <T> owner(of: KFunction1<RepositoryOwner, Stub<T>>): Stub<T> =
        stub<RepositoryOwner, T>("owner", of)

  fun pushedAt(): Stub<DateTime> = stub()

  fun resourcePath(): Stub<URI> = stub()

  fun updatedAt(): Stub<DateTime> = stub()

  fun url(): Stub<URI> = stub()
}

interface Reactable : QType {
  fun databaseId(): Stub<Int> = stub()

  fun id(): Stub<String> = stub()

  fun <T : ReactionGroup> reactionGroups(init: () -> T): Stub<ReactionGroup> = stub(init)

  fun <T> reactionGroups(of: KFunction1<ReactionGroup, Stub<T>>): Stub<T> =
        stub<ReactionGroup, T>("reactionGroups", of)

  fun <T : ReactionConnection> reactions(init: () -> T): Stub<ReactionConnection> = stub(init)

  fun <T> reactions(of: KFunction1<ReactionConnection, Stub<T>>): Stub<T> =
        stub<ReactionConnection, T>("reactions", of)

  fun viewerCanReact(): Stub<Boolean> = stub()

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ReactionsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ReactionsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ReactionsArgs {
      return apply { addArg("before", value) }
    }

    fun content(value: ReactionContent): ReactionsArgs {
      return apply { addArg("content", value) }
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
      return apply { addArg("orderBy", value) }
    }
  }
}

interface ProjectOwner : QType {
  fun id(): Stub<String> = stub()

  fun <T : Project> project(init: () -> T): Stub<Project> = stub(init)

  fun <T> project(of: KFunction1<Project, Stub<T>>): Stub<T> = stub<Project, T>("project", of)

  fun <T : ProjectConnection> projects(init: () -> T): Stub<ProjectConnection> = stub(init)

  fun <T> projects(of: KFunction1<ProjectConnection, Stub<T>>): Stub<T> =
        stub<ProjectConnection, T>("projects", of)

  fun projectsResourcePath(): Stub<URI> = stub()

  fun projectsUrl(): Stub<URI> = stub()

  fun viewerCanCreateProjects(): Stub<Boolean> = stub()

  class ProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): ProjectArgs {
      return apply { addArg("number", value) }
    }
  }

  class ProjectsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProjectsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): ProjectsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): ProjectsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): ProjectsArgs {
      return apply { addArg("before", value) }
    }

    fun orderBy(value: ProjectOrder): ProjectsArgs {
      return apply { addArg("orderBy", value) }
    }

    fun search(value: String): ProjectsArgs {
      return apply { addArg("search", value) }
    }

    fun states(value: ProjectState): ProjectsArgs {
      return apply { addArg("states", value) }
    }
  }
}

interface Node : QType {
  fun id(): Stub<String> = stub()
}

interface Lockable : QType {
  fun locked(): Stub<Boolean> = stub()
}

interface Labelable : QType {
  fun <T : LabelConnection> labels(init: () -> T): Stub<LabelConnection> = stub(init)

  fun <T> labels(of: KFunction1<LabelConnection, Stub<T>>): Stub<T> =
        stub<LabelConnection, T>("labels", of)

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): LabelsArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): LabelsArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): LabelsArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface GitSignature : QType {
  fun email(): Stub<String> = stub()

  fun isValid(): Stub<Boolean> = stub()

  fun payload(): Stub<String> = stub()

  fun signature(): Stub<String> = stub()

  fun <T : User> signer(init: () -> T): Stub<User> = stub(init)

  fun <T> signer(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("signer", of)

  fun state(): Stub<GitSignatureState> = stub()
}

interface GitObject : QType {
  fun abbreviatedOid(): Stub<String> = stub()

  fun commitResourcePath(): Stub<URI> = stub()

  fun commitUrl(): Stub<URI> = stub()

  fun id(): Stub<String> = stub()

  fun oid(): Stub<GitObjectID> = stub()

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)
}

interface Deletable : QType {
  fun viewerCanDelete(): Stub<Boolean> = stub()
}

interface Comment : QType {
  fun <T : Actor> author(init: () -> T): Stub<Actor> = stub(init)

  fun <T> author(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("author", of)

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub()

  fun body(): Stub<String> = stub()

  fun bodyHTML(): Stub<HTML> = stub()

  fun createdAt(): Stub<DateTime> = stub()

  fun createdViaEmail(): Stub<Boolean> = stub()

  fun <T : Actor> editor(init: () -> T): Stub<Actor> = stub(init)

  fun <T> editor(of: KFunction1<Actor, Stub<T>>): Stub<T> = stub<Actor, T>("editor", of)

  fun id(): Stub<String> = stub()

  fun lastEditedAt(): Stub<DateTime> = stub()

  fun publishedAt(): Stub<DateTime> = stub()

  fun updatedAt(): Stub<DateTime> = stub()

  fun viewerDidAuthor(): Stub<Boolean> = stub()
}

interface Closable : QType {
  fun closed(): Stub<Boolean> = stub()
}

interface Assignable : QType {
  fun <T : UserConnection> assignees(init: () -> T): Stub<UserConnection> = stub(init)

  fun <T> assignees(of: KFunction1<UserConnection, Stub<T>>): Stub<T> =
        stub<UserConnection, T>("assignees", of)

  class AssigneesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssigneesArgs {
      return apply { addArg("first", value) }
    }

    fun after(value: String): AssigneesArgs {
      return apply { addArg("after", value) }
    }

    fun last(value: Int): AssigneesArgs {
      return apply { addArg("last", value) }
    }

    fun before(value: String): AssigneesArgs {
      return apply { addArg("before", value) }
    }
  }
}

interface Actor : QType {
  fun avatarUrl(): Stub<URI> = stub()

  fun login(): Stub<String> = stub()

  fun resourcePath(): Stub<URI> = stub()

  fun url(): Stub<URI> = stub()

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
      return apply { addArg("size", value) }
    }
  }
}

interface SearchResultItem : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue> = stub(init)

  fun <T> issue(of: KFunction1<Issue, Stub<T>>): Stub<T> = stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullrequest", of)

  fun <T : Repository> repository(init: () -> T): Stub<Repository> = stub(init)

  fun <T> repository(of: KFunction1<Repository, Stub<T>>): Stub<T> =
        stub<Repository, T>("repository", of)

  fun <T : User> user(init: () -> T): Stub<User> = stub(init)

  fun <T> user(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("user", of)

  fun <T : Organization> organization(init: () -> T): Stub<Organization> = stub(init)

  fun <T> organization(of: KFunction1<Organization, Stub<T>>): Stub<T> =
        stub<Organization, T>("organization", of)
}

interface ReviewDismissalAllowanceActor : QType {
  fun <T : User> user(init: () -> T): Stub<User> = stub(init)

  fun <T> user(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("user", of)

  fun <T : Team> team(init: () -> T): Stub<Team> = stub(init)

  fun <T> team(of: KFunction1<Team, Stub<T>>): Stub<T> = stub<Team, T>("team", of)
}

interface RenamedTitleSubject : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue> = stub(init)

  fun <T> issue(of: KFunction1<Issue, Stub<T>>): Stub<T> = stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullrequest", of)
}

interface ReferencedSubject : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue> = stub(init)

  fun <T> issue(of: KFunction1<Issue, Stub<T>>): Stub<T> = stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullrequest", of)
}

interface PushAllowanceActor : QType {
  fun <T : User> user(init: () -> T): Stub<User> = stub(init)

  fun <T> user(of: KFunction1<User, Stub<T>>): Stub<T> = stub<User, T>("user", of)

  fun <T : Team> team(init: () -> T): Stub<Team> = stub(init)

  fun <T> team(of: KFunction1<Team, Stub<T>>): Stub<T> = stub<Team, T>("team", of)
}

interface PullRequestTimelineItem : QType {
  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun <T : CommitCommentThread> commitcommentthread(init: () -> T): Stub<CommitCommentThread> = stub(init)

  fun <T> commitcommentthread(of: KFunction1<CommitCommentThread, Stub<T>>): Stub<T> =
        stub<CommitCommentThread, T>("commitcommentthread", of)

  fun <T : PullRequestReview> pullrequestreview(init: () -> T): Stub<PullRequestReview> = stub(init)

  fun <T> pullrequestreview(of: KFunction1<PullRequestReview, Stub<T>>): Stub<T> =
        stub<PullRequestReview, T>("pullrequestreview", of)

  fun <T : PullRequestReviewThread> pullrequestreviewthread(init: () -> T): Stub<PullRequestReviewThread> = stub(init)

  fun <T> pullrequestreviewthread(of: KFunction1<PullRequestReviewThread, Stub<T>>): Stub<T> =
        stub<PullRequestReviewThread, T>("pullrequestreviewthread", of)

  fun <T : PullRequestReviewComment> pullrequestreviewcomment(init: () -> T): Stub<PullRequestReviewComment> = stub(init)

  fun <T> pullrequestreviewcomment(of: KFunction1<PullRequestReviewComment, Stub<T>>): Stub<T> =
        stub<PullRequestReviewComment, T>("pullrequestreviewcomment", of)

  fun <T : IssueComment> issuecomment(init: () -> T): Stub<IssueComment> = stub(init)

  fun <T> issuecomment(of: KFunction1<IssueComment, Stub<T>>): Stub<T> =
        stub<IssueComment, T>("issuecomment", of)

  fun <T : ClosedEvent> closedevent(init: () -> T): Stub<ClosedEvent> = stub(init)

  fun <T> closedevent(of: KFunction1<ClosedEvent, Stub<T>>): Stub<T> =
        stub<ClosedEvent, T>("closedevent", of)

  fun <T : ReopenedEvent> reopenedevent(init: () -> T): Stub<ReopenedEvent> = stub(init)

  fun <T> reopenedevent(of: KFunction1<ReopenedEvent, Stub<T>>): Stub<T> =
        stub<ReopenedEvent, T>("reopenedevent", of)

  fun <T : SubscribedEvent> subscribedevent(init: () -> T): Stub<SubscribedEvent> = stub(init)

  fun <T> subscribedevent(of: KFunction1<SubscribedEvent, Stub<T>>): Stub<T> =
        stub<SubscribedEvent, T>("subscribedevent", of)

  fun <T : UnsubscribedEvent> unsubscribedevent(init: () -> T): Stub<UnsubscribedEvent> = stub(init)

  fun <T> unsubscribedevent(of: KFunction1<UnsubscribedEvent, Stub<T>>): Stub<T> =
        stub<UnsubscribedEvent, T>("unsubscribedevent", of)

  fun <T : MergedEvent> mergedevent(init: () -> T): Stub<MergedEvent> = stub(init)

  fun <T> mergedevent(of: KFunction1<MergedEvent, Stub<T>>): Stub<T> =
        stub<MergedEvent, T>("mergedevent", of)

  fun <T : ReferencedEvent> referencedevent(init: () -> T): Stub<ReferencedEvent> = stub(init)

  fun <T> referencedevent(of: KFunction1<ReferencedEvent, Stub<T>>): Stub<T> =
        stub<ReferencedEvent, T>("referencedevent", of)

  fun <T : AssignedEvent> assignedevent(init: () -> T): Stub<AssignedEvent> = stub(init)

  fun <T> assignedevent(of: KFunction1<AssignedEvent, Stub<T>>): Stub<T> =
        stub<AssignedEvent, T>("assignedevent", of)

  fun <T : UnassignedEvent> unassignedevent(init: () -> T): Stub<UnassignedEvent> = stub(init)

  fun <T> unassignedevent(of: KFunction1<UnassignedEvent, Stub<T>>): Stub<T> =
        stub<UnassignedEvent, T>("unassignedevent", of)

  fun <T : LabeledEvent> labeledevent(init: () -> T): Stub<LabeledEvent> = stub(init)

  fun <T> labeledevent(of: KFunction1<LabeledEvent, Stub<T>>): Stub<T> =
        stub<LabeledEvent, T>("labeledevent", of)

  fun <T : UnlabeledEvent> unlabeledevent(init: () -> T): Stub<UnlabeledEvent> = stub(init)

  fun <T> unlabeledevent(of: KFunction1<UnlabeledEvent, Stub<T>>): Stub<T> =
        stub<UnlabeledEvent, T>("unlabeledevent", of)

  fun <T : MilestonedEvent> milestonedevent(init: () -> T): Stub<MilestonedEvent> = stub(init)

  fun <T> milestonedevent(of: KFunction1<MilestonedEvent, Stub<T>>): Stub<T> =
        stub<MilestonedEvent, T>("milestonedevent", of)

  fun <T : DemilestonedEvent> demilestonedevent(init: () -> T): Stub<DemilestonedEvent> = stub(init)

  fun <T> demilestonedevent(of: KFunction1<DemilestonedEvent, Stub<T>>): Stub<T> =
        stub<DemilestonedEvent, T>("demilestonedevent", of)

  fun <T : RenamedTitleEvent> renamedtitleevent(init: () -> T): Stub<RenamedTitleEvent> = stub(init)

  fun <T> renamedtitleevent(of: KFunction1<RenamedTitleEvent, Stub<T>>): Stub<T> =
        stub<RenamedTitleEvent, T>("renamedtitleevent", of)

  fun <T : LockedEvent> lockedevent(init: () -> T): Stub<LockedEvent> = stub(init)

  fun <T> lockedevent(of: KFunction1<LockedEvent, Stub<T>>): Stub<T> =
        stub<LockedEvent, T>("lockedevent", of)

  fun <T : UnlockedEvent> unlockedevent(init: () -> T): Stub<UnlockedEvent> = stub(init)

  fun <T> unlockedevent(of: KFunction1<UnlockedEvent, Stub<T>>): Stub<T> =
        stub<UnlockedEvent, T>("unlockedevent", of)

  fun <T : DeployedEvent> deployedevent(init: () -> T): Stub<DeployedEvent> = stub(init)

  fun <T> deployedevent(of: KFunction1<DeployedEvent, Stub<T>>): Stub<T> =
        stub<DeployedEvent, T>("deployedevent", of)

  fun <T : HeadRefDeletedEvent> headrefdeletedevent(init: () -> T): Stub<HeadRefDeletedEvent> = stub(init)

  fun <T> headrefdeletedevent(of: KFunction1<HeadRefDeletedEvent, Stub<T>>): Stub<T> =
        stub<HeadRefDeletedEvent, T>("headrefdeletedevent", of)

  fun <T : HeadRefRestoredEvent> headrefrestoredevent(init: () -> T): Stub<HeadRefRestoredEvent> = stub(init)

  fun <T> headrefrestoredevent(of: KFunction1<HeadRefRestoredEvent, Stub<T>>): Stub<T> =
        stub<HeadRefRestoredEvent, T>("headrefrestoredevent", of)

  fun <T : HeadRefForcePushedEvent> headrefforcepushedevent(init: () -> T): Stub<HeadRefForcePushedEvent> = stub(init)

  fun <T> headrefforcepushedevent(of: KFunction1<HeadRefForcePushedEvent, Stub<T>>): Stub<T> =
        stub<HeadRefForcePushedEvent, T>("headrefforcepushedevent", of)

  fun <T : BaseRefForcePushedEvent> baserefforcepushedevent(init: () -> T): Stub<BaseRefForcePushedEvent> = stub(init)

  fun <T> baserefforcepushedevent(of: KFunction1<BaseRefForcePushedEvent, Stub<T>>): Stub<T> =
        stub<BaseRefForcePushedEvent, T>("baserefforcepushedevent", of)

  fun <T : ReviewRequestedEvent> reviewrequestedevent(init: () -> T): Stub<ReviewRequestedEvent> = stub(init)

  fun <T> reviewrequestedevent(of: KFunction1<ReviewRequestedEvent, Stub<T>>): Stub<T> =
        stub<ReviewRequestedEvent, T>("reviewrequestedevent", of)

  fun <T : ReviewRequestRemovedEvent> reviewrequestremovedevent(init: () -> T): Stub<ReviewRequestRemovedEvent> = stub(init)

  fun <T> reviewrequestremovedevent(of: KFunction1<ReviewRequestRemovedEvent, Stub<T>>): Stub<T> =
        stub<ReviewRequestRemovedEvent, T>("reviewrequestremovedevent", of)

  fun <T : ReviewDismissedEvent> reviewdismissedevent(init: () -> T): Stub<ReviewDismissedEvent> = stub(init)

  fun <T> reviewdismissedevent(of: KFunction1<ReviewDismissedEvent, Stub<T>>): Stub<T> =
        stub<ReviewDismissedEvent, T>("reviewdismissedevent", of)
}

interface ProjectCardItem : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue> = stub(init)

  fun <T> issue(of: KFunction1<Issue, Stub<T>>): Stub<T> = stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullrequest", of)
}

interface MilestoneItem : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue> = stub(init)

  fun <T> issue(of: KFunction1<Issue, Stub<T>>): Stub<T> = stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullrequest", of)
}

interface IssueTimelineItem : QType {
  fun <T : Commit> commit(init: () -> T): Stub<Commit> = stub(init)

  fun <T> commit(of: KFunction1<Commit, Stub<T>>): Stub<T> = stub<Commit, T>("commit", of)

  fun <T : IssueComment> issuecomment(init: () -> T): Stub<IssueComment> = stub(init)

  fun <T> issuecomment(of: KFunction1<IssueComment, Stub<T>>): Stub<T> =
        stub<IssueComment, T>("issuecomment", of)

  fun <T : ClosedEvent> closedevent(init: () -> T): Stub<ClosedEvent> = stub(init)

  fun <T> closedevent(of: KFunction1<ClosedEvent, Stub<T>>): Stub<T> =
        stub<ClosedEvent, T>("closedevent", of)

  fun <T : ReopenedEvent> reopenedevent(init: () -> T): Stub<ReopenedEvent> = stub(init)

  fun <T> reopenedevent(of: KFunction1<ReopenedEvent, Stub<T>>): Stub<T> =
        stub<ReopenedEvent, T>("reopenedevent", of)

  fun <T : SubscribedEvent> subscribedevent(init: () -> T): Stub<SubscribedEvent> = stub(init)

  fun <T> subscribedevent(of: KFunction1<SubscribedEvent, Stub<T>>): Stub<T> =
        stub<SubscribedEvent, T>("subscribedevent", of)

  fun <T : UnsubscribedEvent> unsubscribedevent(init: () -> T): Stub<UnsubscribedEvent> = stub(init)

  fun <T> unsubscribedevent(of: KFunction1<UnsubscribedEvent, Stub<T>>): Stub<T> =
        stub<UnsubscribedEvent, T>("unsubscribedevent", of)

  fun <T : ReferencedEvent> referencedevent(init: () -> T): Stub<ReferencedEvent> = stub(init)

  fun <T> referencedevent(of: KFunction1<ReferencedEvent, Stub<T>>): Stub<T> =
        stub<ReferencedEvent, T>("referencedevent", of)

  fun <T : AssignedEvent> assignedevent(init: () -> T): Stub<AssignedEvent> = stub(init)

  fun <T> assignedevent(of: KFunction1<AssignedEvent, Stub<T>>): Stub<T> =
        stub<AssignedEvent, T>("assignedevent", of)

  fun <T : UnassignedEvent> unassignedevent(init: () -> T): Stub<UnassignedEvent> = stub(init)

  fun <T> unassignedevent(of: KFunction1<UnassignedEvent, Stub<T>>): Stub<T> =
        stub<UnassignedEvent, T>("unassignedevent", of)

  fun <T : LabeledEvent> labeledevent(init: () -> T): Stub<LabeledEvent> = stub(init)

  fun <T> labeledevent(of: KFunction1<LabeledEvent, Stub<T>>): Stub<T> =
        stub<LabeledEvent, T>("labeledevent", of)

  fun <T : UnlabeledEvent> unlabeledevent(init: () -> T): Stub<UnlabeledEvent> = stub(init)

  fun <T> unlabeledevent(of: KFunction1<UnlabeledEvent, Stub<T>>): Stub<T> =
        stub<UnlabeledEvent, T>("unlabeledevent", of)

  fun <T : MilestonedEvent> milestonedevent(init: () -> T): Stub<MilestonedEvent> = stub(init)

  fun <T> milestonedevent(of: KFunction1<MilestonedEvent, Stub<T>>): Stub<T> =
        stub<MilestonedEvent, T>("milestonedevent", of)

  fun <T : DemilestonedEvent> demilestonedevent(init: () -> T): Stub<DemilestonedEvent> = stub(init)

  fun <T> demilestonedevent(of: KFunction1<DemilestonedEvent, Stub<T>>): Stub<T> =
        stub<DemilestonedEvent, T>("demilestonedevent", of)

  fun <T : RenamedTitleEvent> renamedtitleevent(init: () -> T): Stub<RenamedTitleEvent> = stub(init)

  fun <T> renamedtitleevent(of: KFunction1<RenamedTitleEvent, Stub<T>>): Stub<T> =
        stub<RenamedTitleEvent, T>("renamedtitleevent", of)

  fun <T : LockedEvent> lockedevent(init: () -> T): Stub<LockedEvent> = stub(init)

  fun <T> lockedevent(of: KFunction1<LockedEvent, Stub<T>>): Stub<T> =
        stub<LockedEvent, T>("lockedevent", of)

  fun <T : UnlockedEvent> unlockedevent(init: () -> T): Stub<UnlockedEvent> = stub(init)

  fun <T> unlockedevent(of: KFunction1<UnlockedEvent, Stub<T>>): Stub<T> =
        stub<UnlockedEvent, T>("unlockedevent", of)
}

interface IssueOrPullRequest : QType {
  fun <T : Issue> issue(init: () -> T): Stub<Issue> = stub(init)

  fun <T> issue(of: KFunction1<Issue, Stub<T>>): Stub<T> = stub<Issue, T>("issue", of)

  fun <T : PullRequest> pullrequest(init: () -> T): Stub<PullRequest> = stub(init)

  fun <T> pullrequest(of: KFunction1<PullRequest, Stub<T>>): Stub<T> =
        stub<PullRequest, T>("pullrequest", of)
}

interface X509Certificate : QType {
  fun value(): Stub<String> = stub()
}

interface URI : QType {
  fun value(): Stub<String> = stub()
}

interface HTML : QType {
  fun value(): Stub<String> = stub()
}

interface GitTimestamp : QType {
  fun value(): Stub<String> = stub()
}

interface GitObjectID : QType {
  fun value(): Stub<String> = stub()
}

interface DateTime : QType {
  fun value(): Stub<String> = stub()
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

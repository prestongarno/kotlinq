package com.prestongarno.ktq

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
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<User> = stub("node")
}

interface UserConnection : QType {
  fun edges(): Stub<UserEdge> = stub("edges")

  fun nodes(): Stub<User> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface User : QType {
  fun avatarUrl(): Stub<URI> = stub("avatarUrl")

  fun bio(): Stub<String> = stub("bio")

  fun bioHTML(): Stub<HTML> = stub("bioHTML")

  fun company(): Stub<String> = stub("company")

  fun companyHTML(): Stub<HTML> = stub("companyHTML")

  fun contributedRepositories(): Stub<RepositoryConnection> = stub("contributedRepositories")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun email(): Stub<String> = stub("email")

  fun followers(): Stub<FollowerConnection> = stub("followers")

  fun following(): Stub<FollowingConnection> = stub("following")

  fun gist(): Stub<Gist> = stub("gist")

  fun gists(): Stub<GistConnection> = stub("gists")

  fun id(): Stub<String> = stub("id")

  fun isBountyHunter(): Stub<Boolean> = stub("isBountyHunter")

  fun isCampusExpert(): Stub<Boolean> = stub("isCampusExpert")

  fun isDeveloperProgramMember(): Stub<Boolean> = stub("isDeveloperProgramMember")

  fun isEmployee(): Stub<Boolean> = stub("isEmployee")

  fun isHireable(): Stub<Boolean> = stub("isHireable")

  fun isInvoiced(): Stub<Boolean> = stub("isInvoiced")

  fun isSiteAdmin(): Stub<Boolean> = stub("isSiteAdmin")

  fun isViewer(): Stub<Boolean> = stub("isViewer")

  fun issues(): Stub<IssueConnection> = stub("issues")

  fun location(): Stub<String> = stub("location")

  fun login(): Stub<String> = stub("login")

  fun name(): Stub<String> = stub("name")

  fun organization(): Stub<Organization> = stub("organization")

  fun organizations(): Stub<OrganizationConnection> = stub("organizations")

  fun pinnedRepositories(): Stub<RepositoryConnection> = stub("pinnedRepositories")

  fun pullRequests(): Stub<PullRequestConnection> = stub("pullRequests")

  fun repositories(): Stub<RepositoryConnection> = stub("repositories")

  fun repository(): Stub<Repository> = stub("repository")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun starredRepositories(): Stub<StarredRepositoryConnection> = stub("starredRepositories")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun url(): Stub<URI> = stub("url")

  fun viewerCanFollow(): Stub<Boolean> = stub("viewerCanFollow")

  fun viewerIsFollowing(): Stub<Boolean> = stub("viewerIsFollowing")

  fun watching(): Stub<RepositoryConnection> = stub("watching")

  fun websiteUrl(): Stub<URI> = stub("websiteUrl")

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }

  class ContributedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ContributedRepositoriesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ContributedRepositoriesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ContributedRepositoriesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ContributedRepositoriesArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: RepositoryPrivacy): ContributedRepositoriesArgs {
       addArg("privacy", value); return this;
    }

    fun orderBy(value: RepositoryOrder): ContributedRepositoriesArgs {
       addArg("orderBy", value); return this;
    }

    fun affiliations(value: RepositoryAffiliation): ContributedRepositoriesArgs {
       addArg("affiliations", value); return this;
    }

    fun isLocked(value: Boolean): ContributedRepositoriesArgs {
       addArg("isLocked", value); return this;
    }
  }

  class FollowersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): FollowersArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): FollowersArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): FollowersArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): FollowersArgs {
       addArg("before", value); return this;
    }
  }

  class FollowingArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): FollowingArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): FollowingArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): FollowingArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): FollowingArgs {
       addArg("before", value); return this;
    }
  }

  class GistArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): GistArgs {
       addArg("name", value); return this;
    }
  }

  class GistsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): GistsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): GistsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): GistsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): GistsArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: GistPrivacy): GistsArgs {
       addArg("privacy", value); return this;
    }
  }

  class IssuesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): IssuesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): IssuesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): IssuesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): IssuesArgs {
       addArg("before", value); return this;
    }

    fun labels(value: String): IssuesArgs {
       addArg("labels", value); return this;
    }

    fun orderBy(value: IssueOrder): IssuesArgs {
       addArg("orderBy", value); return this;
    }

    fun states(value: IssueState): IssuesArgs {
       addArg("states", value); return this;
    }
  }

  class OrganizationArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): OrganizationArgs {
       addArg("login", value); return this;
    }
  }

  class OrganizationsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): OrganizationsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): OrganizationsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): OrganizationsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): OrganizationsArgs {
       addArg("before", value); return this;
    }
  }

  class PinnedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PinnedRepositoriesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): PinnedRepositoriesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): PinnedRepositoriesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): PinnedRepositoriesArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs {
       addArg("privacy", value); return this;
    }

    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs {
       addArg("orderBy", value); return this;
    }

    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs {
       addArg("affiliations", value); return this;
    }

    fun isLocked(value: Boolean): PinnedRepositoriesArgs {
       addArg("isLocked", value); return this;
    }
  }

  class PullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PullRequestsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): PullRequestsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): PullRequestsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): PullRequestsArgs {
       addArg("before", value); return this;
    }

    fun states(value: PullRequestState): PullRequestsArgs {
       addArg("states", value); return this;
    }

    fun labels(value: String): PullRequestsArgs {
       addArg("labels", value); return this;
    }

    fun headRefName(value: String): PullRequestsArgs {
       addArg("headRefName", value); return this;
    }

    fun baseRefName(value: String): PullRequestsArgs {
       addArg("baseRefName", value); return this;
    }

    fun orderBy(value: IssueOrder): PullRequestsArgs {
       addArg("orderBy", value); return this;
    }
  }

  class RepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoriesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): RepositoriesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): RepositoriesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): RepositoriesArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: RepositoryPrivacy): RepositoriesArgs {
       addArg("privacy", value); return this;
    }

    fun orderBy(value: RepositoryOrder): RepositoriesArgs {
       addArg("orderBy", value); return this;
    }

    fun affiliations(value: RepositoryAffiliation): RepositoriesArgs {
       addArg("affiliations", value); return this;
    }

    fun isLocked(value: Boolean): RepositoriesArgs {
       addArg("isLocked", value); return this;
    }

    fun isFork(value: Boolean): RepositoriesArgs {
       addArg("isFork", value); return this;
    }
  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): RepositoryArgs {
       addArg("name", value); return this;
    }
  }

  class StarredRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StarredRepositoriesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): StarredRepositoriesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): StarredRepositoriesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): StarredRepositoriesArgs {
       addArg("before", value); return this;
    }

    fun ownedByViewer(value: Boolean): StarredRepositoriesArgs {
       addArg("ownedByViewer", value); return this;
    }

    fun orderBy(value: StarOrder): StarredRepositoriesArgs {
       addArg("orderBy", value); return this;
    }
  }

  class WatchingArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): WatchingArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): WatchingArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): WatchingArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): WatchingArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: RepositoryPrivacy): WatchingArgs {
       addArg("privacy", value); return this;
    }

    fun orderBy(value: RepositoryOrder): WatchingArgs {
       addArg("orderBy", value); return this;
    }

    fun affiliations(value: RepositoryAffiliation): WatchingArgs {
       addArg("affiliations", value); return this;
    }

    fun isLocked(value: Boolean): WatchingArgs {
       addArg("isLocked", value); return this;
    }
  }
}

interface UpdateTopicsPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun invalidTopicNames(): Stub<String> = stub("invalidTopicNames")

  fun repository(): Stub<Repository> = stub("repository")
}

interface UpdateSubscriptionPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun subscribable(): Stub<Subscribable> = stub("subscribable")
}

interface UpdatePullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun pullRequestReview(): Stub<PullRequestReview> = stub("pullRequestReview")
}

interface UpdatePullRequestReviewCommentPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun pullRequestReviewComment(): Stub<PullRequestReviewComment> = stub("pullRequestReviewComment")
}

interface UpdateProjectPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun project(): Stub<Project> = stub("project")
}

interface UpdateProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun projectColumn(): Stub<ProjectColumn> = stub("projectColumn")
}

interface UpdateProjectCardPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun projectCard(): Stub<ProjectCard> = stub("projectCard")
}

interface UnsubscribedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun subscribable(): Stub<Subscribable> = stub("subscribable")
}

interface UnlockedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun lockable(): Stub<Lockable> = stub("lockable")
}

interface UnlabeledEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun label(): Stub<Label> = stub("label")

  fun labelable(): Stub<Labelable> = stub("labelable")
}

interface UnknownSignature : QType {
  fun email(): Stub<String> = stub("email")

  fun isValid(): Stub<Boolean> = stub("isValid")

  fun payload(): Stub<String> = stub("payload")

  fun signature(): Stub<String> = stub("signature")

  fun signer(): Stub<User> = stub("signer")

  fun state(): Stub<GitSignatureState> = stub("state")
}

interface UnassignedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun assignable(): Stub<Assignable> = stub("assignable")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun user(): Stub<User> = stub("user")
}

interface TreeEntry : QType {
  fun mode(): Stub<Int> = stub("mode")

  fun name(): Stub<String> = stub("name")

  fun objectVal(): Stub<GitObject> = stub("objectVal")

  fun oid(): Stub<GitObjectID> = stub("oid")

  fun repository(): Stub<Repository> = stub("repository")

  fun type(): Stub<String> = stub("type")
}

interface Tree : QType {
  fun abbreviatedOid(): Stub<String> = stub("abbreviatedOid")

  fun commitResourcePath(): Stub<URI> = stub("commitResourcePath")

  fun commitUrl(): Stub<URI> = stub("commitUrl")

  fun entries(): Stub<TreeEntry> = stub("entries")

  fun id(): Stub<String> = stub("id")

  fun oid(): Stub<GitObjectID> = stub("oid")

  fun repository(): Stub<Repository> = stub("repository")
}

interface Topic : QType {
  fun id(): Stub<String> = stub("id")

  fun name(): Stub<String> = stub("name")

  fun relatedTopics(): Stub<Topic> = stub("relatedTopics")
}

interface TeamEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Team> = stub("node")
}

interface TeamConnection : QType {
  fun edges(): Stub<TeamEdge> = stub("edges")

  fun nodes(): Stub<Team> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface Team : QType {
  fun description(): Stub<String> = stub("description")

  fun editTeamResourcePath(): Stub<URI> = stub("editTeamResourcePath")

  fun editTeamUrl(): Stub<URI> = stub("editTeamUrl")

  fun id(): Stub<String> = stub("id")

  fun invitations(): Stub<OrganizationInvitationConnection> = stub("invitations")

  fun name(): Stub<String> = stub("name")

  fun organization(): Stub<Organization> = stub("organization")

  fun privacy(): Stub<TeamPrivacy> = stub("privacy")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun slug(): Stub<String> = stub("slug")

  fun url(): Stub<URI> = stub("url")

  class InvitationsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): InvitationsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): InvitationsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): InvitationsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): InvitationsArgs {
       addArg("before", value); return this;
    }
  }
}

interface Tag : QType {
  fun abbreviatedOid(): Stub<String> = stub("abbreviatedOid")

  fun commitResourcePath(): Stub<URI> = stub("commitResourcePath")

  fun commitUrl(): Stub<URI> = stub("commitUrl")

  fun id(): Stub<String> = stub("id")

  fun message(): Stub<String> = stub("message")

  fun name(): Stub<String> = stub("name")

  fun oid(): Stub<GitObjectID> = stub("oid")

  fun repository(): Stub<Repository> = stub("repository")

  fun tagger(): Stub<GitActor> = stub("tagger")

  fun target(): Stub<GitObject> = stub("target")
}

interface SuggestedReviewer : QType {
  fun isAuthor(): Stub<Boolean> = stub("isAuthor")

  fun isCommenter(): Stub<Boolean> = stub("isCommenter")

  fun reviewer(): Stub<User> = stub("reviewer")
}

interface SubscribedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun subscribable(): Stub<Subscribable> = stub("subscribable")
}

interface SubmitPullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun pullRequestReview(): Stub<PullRequestReview> = stub("pullRequestReview")
}

interface StatusContext : QType {
  fun commit(): Stub<Commit> = stub("commit")

  fun context(): Stub<String> = stub("context")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun creator(): Stub<Actor> = stub("creator")

  fun description(): Stub<String> = stub("description")

  fun id(): Stub<String> = stub("id")

  fun state(): Stub<StatusState> = stub("state")

  fun targetUrl(): Stub<URI> = stub("targetUrl")
}

interface Status : QType {
  fun commit(): Stub<Commit> = stub("commit")

  fun context(): Stub<StatusContext> = stub("context")

  fun contexts(): Stub<StatusContext> = stub("contexts")

  fun id(): Stub<String> = stub("id")

  fun state(): Stub<StatusState> = stub("state")

  class ContextArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): ContextArgs {
       addArg("name", value); return this;
    }
  }
}

interface StarredRepositoryEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Repository> = stub("node")

  fun starredAt(): Stub<DateTime> = stub("starredAt")
}

interface StarredRepositoryConnection : QType {
  fun edges(): Stub<StarredRepositoryEdge> = stub("edges")

  fun nodes(): Stub<Repository> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface StargazerEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<User> = stub("node")

  fun starredAt(): Stub<DateTime> = stub("starredAt")
}

interface StargazerConnection : QType {
  fun edges(): Stub<StargazerEdge> = stub("edges")

  fun nodes(): Stub<User> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface SmimeSignature : QType {
  fun email(): Stub<String> = stub("email")

  fun isValid(): Stub<Boolean> = stub("isValid")

  fun payload(): Stub<String> = stub("payload")

  fun signature(): Stub<String> = stub("signature")

  fun signer(): Stub<User> = stub("signer")

  fun state(): Stub<GitSignatureState> = stub("state")
}

interface SearchResultItemEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<SearchResultItem> = stub("node")
}

interface SearchResultItemConnection : QType {
  fun codeCount(): Stub<Int> = stub("codeCount")

  fun edges(): Stub<SearchResultItemEdge> = stub("edges")

  fun issueCount(): Stub<Int> = stub("issueCount")

  fun nodes(): Stub<SearchResultItem> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun repositoryCount(): Stub<Int> = stub("repositoryCount")

  fun userCount(): Stub<Int> = stub("userCount")

  fun wikiCount(): Stub<Int> = stub("wikiCount")
}

interface ReviewRequestedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun subject(): Stub<User> = stub("subject")
}

interface ReviewRequestRemovedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun subject(): Stub<User> = stub("subject")
}

interface ReviewRequestEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<ReviewRequest> = stub("node")
}

interface ReviewRequestConnection : QType {
  fun edges(): Stub<ReviewRequestEdge> = stub("edges")

  fun nodes(): Stub<ReviewRequest> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface ReviewRequest : QType {
  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun reviewer(): Stub<User> = stub("reviewer")
}

interface ReviewDismissedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")

  fun message(): Stub<String> = stub("message")

  fun messageHtml(): Stub<HTML> = stub("messageHtml")

  fun previousReviewState(): Stub<PullRequestReviewState> = stub("previousReviewState")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun pullRequestCommit(): Stub<PullRequestCommit> = stub("pullRequestCommit")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun review(): Stub<PullRequestReview> = stub("review")

  fun url(): Stub<URI> = stub("url")
}

interface ReviewDismissalAllowanceEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<ReviewDismissalAllowance> = stub("node")
}

interface ReviewDismissalAllowanceConnection : QType {
  fun edges(): Stub<ReviewDismissalAllowanceEdge> = stub("edges")

  fun nodes(): Stub<ReviewDismissalAllowance> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface ReviewDismissalAllowance : QType {
  fun actor(): Stub<ReviewDismissalAllowanceActor> = stub("actor")

  fun id(): Stub<String> = stub("id")

  fun protectedBranch(): Stub<ProtectedBranch> = stub("protectedBranch")
}

interface RequestReviewsPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun requestedReviewersEdge(): Stub<UserEdge> = stub("requestedReviewersEdge")
}

interface RepositoryTopicEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<RepositoryTopic> = stub("node")
}

interface RepositoryTopicConnection : QType {
  fun edges(): Stub<RepositoryTopicEdge> = stub("edges")

  fun nodes(): Stub<RepositoryTopic> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface RepositoryTopic : QType {
  fun id(): Stub<String> = stub("id")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun topic(): Stub<Topic> = stub("topic")

  fun url(): Stub<URI> = stub("url")
}

interface RepositoryInvitationRepository : QType {
  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun description(): Stub<String> = stub("description")

  fun descriptionHTML(): Stub<HTML> = stub("descriptionHTML")

  fun hasIssuesEnabled(): Stub<Boolean> = stub("hasIssuesEnabled")

  fun hasWikiEnabled(): Stub<Boolean> = stub("hasWikiEnabled")

  fun homepageUrl(): Stub<URI> = stub("homepageUrl")

  fun isFork(): Stub<Boolean> = stub("isFork")

  fun isLocked(): Stub<Boolean> = stub("isLocked")

  fun isMirror(): Stub<Boolean> = stub("isMirror")

  fun isPrivate(): Stub<Boolean> = stub("isPrivate")

  fun license(): Stub<String> = stub("license")

  fun lockReason(): Stub<RepositoryLockReason> = stub("lockReason")

  fun mirrorUrl(): Stub<URI> = stub("mirrorUrl")

  fun name(): Stub<String> = stub("name")

  fun nameWithOwner(): Stub<String> = stub("nameWithOwner")

  fun owner(): Stub<RepositoryOwner> = stub("owner")

  fun pushedAt(): Stub<DateTime> = stub("pushedAt")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun url(): Stub<URI> = stub("url")
}

interface RepositoryInvitation : QType {
  fun id(): Stub<String> = stub("id")

  fun invitee(): Stub<User> = stub("invitee")

  fun inviter(): Stub<User> = stub("inviter")

  fun repository(): Stub<RepositoryInvitationRepository> = stub("repository")
}

interface RepositoryEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Repository> = stub("node")
}

interface RepositoryConnection : QType {
  fun edges(): Stub<RepositoryEdge> = stub("edges")

  fun nodes(): Stub<Repository> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")

  fun totalDiskUsage(): Stub<Int> = stub("totalDiskUsage")
}

interface Repository : QType {
  fun codeOfConduct(): Stub<CodeOfConduct> = stub("codeOfConduct")

  fun commitComments(): Stub<CommitCommentConnection> = stub("commitComments")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun defaultBranchRef(): Stub<Ref> = stub("defaultBranchRef")

  fun deployments(): Stub<DeploymentConnection> = stub("deployments")

  fun description(): Stub<String> = stub("description")

  fun descriptionHTML(): Stub<HTML> = stub("descriptionHTML")

  fun diskUsage(): Stub<Int> = stub("diskUsage")

  fun forks(): Stub<RepositoryConnection> = stub("forks")

  fun hasIssuesEnabled(): Stub<Boolean> = stub("hasIssuesEnabled")

  fun hasWikiEnabled(): Stub<Boolean> = stub("hasWikiEnabled")

  fun homepageUrl(): Stub<URI> = stub("homepageUrl")

  fun id(): Stub<String> = stub("id")

  fun isFork(): Stub<Boolean> = stub("isFork")

  fun isLocked(): Stub<Boolean> = stub("isLocked")

  fun isMirror(): Stub<Boolean> = stub("isMirror")

  fun isPrivate(): Stub<Boolean> = stub("isPrivate")

  fun issue(): Stub<Issue> = stub("issue")

  fun issueOrPullRequest(): Stub<IssueOrPullRequest> = stub("issueOrPullRequest")

  fun issues(): Stub<IssueConnection> = stub("issues")

  fun label(): Stub<Label> = stub("label")

  fun labels(): Stub<LabelConnection> = stub("labels")

  fun languages(): Stub<LanguageConnection> = stub("languages")

  fun license(): Stub<String> = stub("license")

  fun lockReason(): Stub<RepositoryLockReason> = stub("lockReason")

  fun mentionableUsers(): Stub<UserConnection> = stub("mentionableUsers")

  fun milestone(): Stub<Milestone> = stub("milestone")

  fun milestones(): Stub<MilestoneConnection> = stub("milestones")

  fun mirrorUrl(): Stub<URI> = stub("mirrorUrl")

  fun name(): Stub<String> = stub("name")

  fun nameWithOwner(): Stub<String> = stub("nameWithOwner")

  fun objectVal(): Stub<GitObject> = stub("objectVal")

  fun owner(): Stub<RepositoryOwner> = stub("owner")

  fun parent(): Stub<Repository> = stub("parent")

  fun primaryLanguage(): Stub<Language> = stub("primaryLanguage")

  fun project(): Stub<Project> = stub("project")

  fun projects(): Stub<ProjectConnection> = stub("projects")

  fun projectsResourcePath(): Stub<URI> = stub("projectsResourcePath")

  fun projectsUrl(): Stub<URI> = stub("projectsUrl")

  fun protectedBranches(): Stub<ProtectedBranchConnection> = stub("protectedBranches")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun pullRequests(): Stub<PullRequestConnection> = stub("pullRequests")

  fun pushedAt(): Stub<DateTime> = stub("pushedAt")

  fun ref(): Stub<Ref> = stub("ref")

  fun refs(): Stub<RefConnection> = stub("refs")

  fun releases(): Stub<ReleaseConnection> = stub("releases")

  fun repositoryTopics(): Stub<RepositoryTopicConnection> = stub("repositoryTopics")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun stargazers(): Stub<StargazerConnection> = stub("stargazers")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun url(): Stub<URI> = stub("url")

  fun viewerCanAdminister(): Stub<Boolean> = stub("viewerCanAdminister")

  fun viewerCanCreateProjects(): Stub<Boolean> = stub("viewerCanCreateProjects")

  fun viewerCanSubscribe(): Stub<Boolean> = stub("viewerCanSubscribe")

  fun viewerCanUpdateTopics(): Stub<Boolean> = stub("viewerCanUpdateTopics")

  fun viewerHasStarred(): Stub<Boolean> = stub("viewerHasStarred")

  fun viewerSubscription(): Stub<SubscriptionState> = stub("viewerSubscription")

  fun watchers(): Stub<UserConnection> = stub("watchers")

  class CommitCommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommitCommentsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): CommitCommentsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): CommitCommentsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): CommitCommentsArgs {
       addArg("before", value); return this;
    }
  }

  class DeploymentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): DeploymentsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): DeploymentsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): DeploymentsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): DeploymentsArgs {
       addArg("before", value); return this;
    }
  }

  class ForksArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ForksArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ForksArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ForksArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ForksArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: RepositoryPrivacy): ForksArgs {
       addArg("privacy", value); return this;
    }

    fun orderBy(value: RepositoryOrder): ForksArgs {
       addArg("orderBy", value); return this;
    }

    fun affiliations(value: RepositoryAffiliation): ForksArgs {
       addArg("affiliations", value); return this;
    }

    fun isLocked(value: Boolean): ForksArgs {
       addArg("isLocked", value); return this;
    }
  }

  class IssueArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): IssueArgs {
       addArg("number", value); return this;
    }
  }

  class IssueOrPullRequestArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): IssueOrPullRequestArgs {
       addArg("number", value); return this;
    }
  }

  class IssuesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): IssuesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): IssuesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): IssuesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): IssuesArgs {
       addArg("before", value); return this;
    }

    fun labels(value: String): IssuesArgs {
       addArg("labels", value); return this;
    }

    fun orderBy(value: IssueOrder): IssuesArgs {
       addArg("orderBy", value); return this;
    }

    fun states(value: IssueState): IssuesArgs {
       addArg("states", value); return this;
    }
  }

  class LabelArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): LabelArgs {
       addArg("name", value); return this;
    }
  }

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): LabelsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): LabelsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): LabelsArgs {
       addArg("before", value); return this;
    }
  }

  class LanguagesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LanguagesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): LanguagesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): LanguagesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): LanguagesArgs {
       addArg("before", value); return this;
    }

    fun orderBy(value: LanguageOrder): LanguagesArgs {
       addArg("orderBy", value); return this;
    }
  }

  class MentionableUsersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): MentionableUsersArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): MentionableUsersArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): MentionableUsersArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): MentionableUsersArgs {
       addArg("before", value); return this;
    }
  }

  class MilestoneArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): MilestoneArgs {
       addArg("number", value); return this;
    }
  }

  class MilestonesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): MilestonesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): MilestonesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): MilestonesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): MilestonesArgs {
       addArg("before", value); return this;
    }
  }

  class ObjectValArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun oid(value: GitObjectID): ObjectValArgs {
       addArg("oid", value); return this;
    }

    fun expression(value: String): ObjectValArgs {
       addArg("expression", value); return this;
    }
  }

  class ProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): ProjectArgs {
       addArg("number", value); return this;
    }
  }

  class ProjectsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProjectsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ProjectsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ProjectsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ProjectsArgs {
       addArg("before", value); return this;
    }

    fun orderBy(value: ProjectOrder): ProjectsArgs {
       addArg("orderBy", value); return this;
    }

    fun search(value: String): ProjectsArgs {
       addArg("search", value); return this;
    }

    fun states(value: ProjectState): ProjectsArgs {
       addArg("states", value); return this;
    }
  }

  class ProtectedBranchesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProtectedBranchesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ProtectedBranchesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ProtectedBranchesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ProtectedBranchesArgs {
       addArg("before", value); return this;
    }
  }

  class PullRequestArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): PullRequestArgs {
       addArg("number", value); return this;
    }
  }

  class PullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PullRequestsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): PullRequestsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): PullRequestsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): PullRequestsArgs {
       addArg("before", value); return this;
    }

    fun states(value: PullRequestState): PullRequestsArgs {
       addArg("states", value); return this;
    }

    fun labels(value: String): PullRequestsArgs {
       addArg("labels", value); return this;
    }

    fun headRefName(value: String): PullRequestsArgs {
       addArg("headRefName", value); return this;
    }

    fun baseRefName(value: String): PullRequestsArgs {
       addArg("baseRefName", value); return this;
    }

    fun orderBy(value: IssueOrder): PullRequestsArgs {
       addArg("orderBy", value); return this;
    }
  }

  class RefArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun qualifiedName(value: String): RefArgs {
       addArg("qualifiedName", value); return this;
    }
  }

  class RefsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RefsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): RefsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): RefsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): RefsArgs {
       addArg("before", value); return this;
    }

    fun refPrefix(value: String): RefsArgs {
       addArg("refPrefix", value); return this;
    }

    fun direction(value: OrderDirection): RefsArgs {
       addArg("direction", value); return this;
    }
  }

  class ReleasesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReleasesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReleasesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReleasesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReleasesArgs {
       addArg("before", value); return this;
    }
  }

  class RepositoryTopicsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoryTopicsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): RepositoryTopicsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): RepositoryTopicsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): RepositoryTopicsArgs {
       addArg("before", value); return this;
    }
  }

  class StargazersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StargazersArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): StargazersArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): StargazersArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): StargazersArgs {
       addArg("before", value); return this;
    }

    fun orderBy(value: StarOrder): StargazersArgs {
       addArg("orderBy", value); return this;
    }
  }

  class WatchersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): WatchersArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): WatchersArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): WatchersArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): WatchersArgs {
       addArg("before", value); return this;
    }
  }
}

interface ReopenedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun closable(): Stub<Closable> = stub("closable")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")
}

interface RenamedTitleEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun currentTitle(): Stub<String> = stub("currentTitle")

  fun id(): Stub<String> = stub("id")

  fun previousTitle(): Stub<String> = stub("previousTitle")

  fun subject(): Stub<RenamedTitleSubject> = stub("subject")
}

interface RemovedFromProjectEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")
}

interface RemoveStarPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun starrable(): Stub<Starrable> = stub("starrable")
}

interface RemoveReactionPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun reaction(): Stub<Reaction> = stub("reaction")

  fun subject(): Stub<Reactable> = stub("subject")
}

interface RemoveOutsideCollaboratorPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun removedUser(): Stub<User> = stub("removedUser")
}

interface ReleaseEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Release> = stub("node")
}

interface ReleaseConnection : QType {
  fun edges(): Stub<ReleaseEdge> = stub("edges")

  fun nodes(): Stub<Release> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface ReleaseAssetEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<ReleaseAsset> = stub("node")
}

interface ReleaseAssetConnection : QType {
  fun edges(): Stub<ReleaseAssetEdge> = stub("edges")

  fun nodes(): Stub<ReleaseAsset> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface ReleaseAsset : QType {
  fun id(): Stub<String> = stub("id")

  fun name(): Stub<String> = stub("name")

  fun release(): Stub<Release> = stub("release")

  fun url(): Stub<URI> = stub("url")
}

interface Release : QType {
  fun description(): Stub<String> = stub("description")

  fun id(): Stub<String> = stub("id")

  fun name(): Stub<String> = stub("name")

  fun publishedAt(): Stub<DateTime> = stub("publishedAt")

  fun releaseAsset(): Stub<ReleaseAssetConnection> = stub("releaseAsset")

  fun releaseAssets(): Stub<ReleaseAssetConnection> = stub("releaseAssets")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun tag(): Stub<Ref> = stub("tag")

  fun url(): Stub<URI> = stub("url")

  class ReleaseAssetArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReleaseAssetArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReleaseAssetArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReleaseAssetArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReleaseAssetArgs {
       addArg("before", value); return this;
    }

    fun name(value: String): ReleaseAssetArgs {
       addArg("name", value); return this;
    }
  }

  class ReleaseAssetsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReleaseAssetsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReleaseAssetsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReleaseAssetsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReleaseAssetsArgs {
       addArg("before", value); return this;
    }
  }
}

interface ReferencedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun commit(): Stub<Commit> = stub("commit")

  fun commitRepository(): Stub<Repository> = stub("commitRepository")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun isCrossReference(): Stub<Boolean> = stub("isCrossReference")

  fun isCrossRepository(): Stub<Boolean> = stub("isCrossRepository")

  fun isDirectReference(): Stub<Boolean> = stub("isDirectReference")

  fun subject(): Stub<ReferencedSubject> = stub("subject")
}

interface RefEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Ref> = stub("node")
}

interface RefConnection : QType {
  fun edges(): Stub<RefEdge> = stub("edges")

  fun nodes(): Stub<Ref> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface Ref : QType {
  fun associatedPullRequests(): Stub<PullRequestConnection> = stub("associatedPullRequests")

  fun id(): Stub<String> = stub("id")

  fun name(): Stub<String> = stub("name")

  fun prefix(): Stub<String> = stub("prefix")

  fun repository(): Stub<Repository> = stub("repository")

  fun target(): Stub<GitObject> = stub("target")

  class AssociatedPullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssociatedPullRequestsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): AssociatedPullRequestsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): AssociatedPullRequestsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): AssociatedPullRequestsArgs {
       addArg("before", value); return this;
    }

    fun states(value: PullRequestState): AssociatedPullRequestsArgs {
       addArg("states", value); return this;
    }
  }
}

interface ReactionGroup : QType {
  fun content(): Stub<ReactionContent> = stub("content")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun subject(): Stub<Reactable> = stub("subject")

  fun users(): Stub<ReactingUserConnection> = stub("users")

  fun viewerHasReacted(): Stub<Boolean> = stub("viewerHasReacted")

  class UsersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): UsersArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): UsersArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): UsersArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): UsersArgs {
       addArg("before", value); return this;
    }
  }
}

interface ReactionEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Reaction> = stub("node")
}

interface ReactionConnection : QType {
  fun edges(): Stub<ReactionEdge> = stub("edges")

  fun nodes(): Stub<Reaction> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")

  fun viewerHasReacted(): Stub<Boolean> = stub("viewerHasReacted")
}

interface Reaction : QType {
  fun content(): Stub<ReactionContent> = stub("content")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")

  fun user(): Stub<User> = stub("user")
}

interface ReactingUserEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<User> = stub("node")

  fun reactedAt(): Stub<DateTime> = stub("reactedAt")
}

interface ReactingUserConnection : QType {
  fun edges(): Stub<ReactingUserEdge> = stub("edges")

  fun nodes(): Stub<User> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface RateLimit : QType {
  fun cost(): Stub<Int> = stub("cost")

  fun limit(): Stub<Int> = stub("limit")

  fun remaining(): Stub<Int> = stub("remaining")

  fun resetAt(): Stub<DateTime> = stub("resetAt")
}

interface Query : QType {
  fun codeOfConduct(): Stub<CodeOfConduct> = stub("codeOfConduct")

  fun codesOfConduct(): Stub<CodeOfConduct> = stub("codesOfConduct")

  fun node(): Stub<Node> = stub("node")

  fun nodes(): Stub<Node> = stub("nodes")

  fun organization(): Stub<Organization> = stub("organization")

  fun rateLimit(): Stub<RateLimit> = stub("rateLimit")

  fun relay(): Stub<Query> = stub("relay")

  fun repository(): Stub<Repository> = stub("repository")

  fun repositoryOwner(): Stub<RepositoryOwner> = stub("repositoryOwner")

  fun resource(): Stub<UniformResourceLocatable> = stub("resource")

  fun search(): Stub<SearchResultItemConnection> = stub("search")

  fun topic(): Stub<Topic> = stub("topic")

  fun user(): Stub<User> = stub("user")

  fun viewer(): Stub<User> = stub("viewer")

  class CodeOfConductArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun key(value: String): CodeOfConductArgs {
       addArg("key", value); return this;
    }
  }

  class NodeArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun id(value: String): NodeArgs {
       addArg("id", value); return this;
    }
  }

  class NodesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun ids(value: String): NodesArgs {
       addArg("ids", value); return this;
    }
  }

  class OrganizationArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): OrganizationArgs {
       addArg("login", value); return this;
    }
  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun owner(value: String): RepositoryArgs {
       addArg("owner", value); return this;
    }

    fun name(value: String): RepositoryArgs {
       addArg("name", value); return this;
    }
  }

  class RepositoryOwnerArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): RepositoryOwnerArgs {
       addArg("login", value); return this;
    }
  }

  class ResourceArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun url(value: URI): ResourceArgs {
       addArg("url", value); return this;
    }
  }

  class SearchArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): SearchArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): SearchArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): SearchArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): SearchArgs {
       addArg("before", value); return this;
    }

    fun query(value: String): SearchArgs {
       addArg("query", value); return this;
    }

    fun type(value: SearchType): SearchArgs {
       addArg("type", value); return this;
    }
  }

  class TopicArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): TopicArgs {
       addArg("name", value); return this;
    }
  }

  class UserArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun login(value: String): UserArgs {
       addArg("login", value); return this;
    }
  }
}

interface PushAllowanceEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<PushAllowance> = stub("node")
}

interface PushAllowanceConnection : QType {
  fun edges(): Stub<PushAllowanceEdge> = stub("edges")

  fun nodes(): Stub<PushAllowance> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface PushAllowance : QType {
  fun actor(): Stub<PushAllowanceActor> = stub("actor")

  fun id(): Stub<String> = stub("id")

  fun protectedBranch(): Stub<ProtectedBranch> = stub("protectedBranch")
}

interface PullRequestTimelineItemEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<PullRequestTimelineItem> = stub("node")
}

interface PullRequestTimelineConnection : QType {
  fun edges(): Stub<PullRequestTimelineItemEdge> = stub("edges")

  fun nodes(): Stub<PullRequestTimelineItem> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface PullRequestReviewThread : QType {
  fun comments(): Stub<PullRequestReviewCommentConnection> = stub("comments")

  fun id(): Stub<String> = stub("id")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): CommentsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): CommentsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): CommentsArgs {
       addArg("before", value); return this;
    }
  }
}

interface PullRequestReviewEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<PullRequestReview> = stub("node")
}

interface PullRequestReviewConnection : QType {
  fun edges(): Stub<PullRequestReviewEdge> = stub("edges")

  fun nodes(): Stub<PullRequestReview> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface PullRequestReviewCommentEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<PullRequestReviewComment> = stub("node")
}

interface PullRequestReviewCommentConnection : QType {
  fun edges(): Stub<PullRequestReviewCommentEdge> = stub("edges")

  fun nodes(): Stub<PullRequestReviewComment> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface PullRequestReviewComment : QType {
  fun author(): Stub<Actor> = stub("author")

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub("authorAssociation")

  fun body(): Stub<String> = stub("body")

  fun bodyHTML(): Stub<HTML> = stub("bodyHTML")

  fun bodyText(): Stub<String> = stub("bodyText")

  fun commit(): Stub<Commit> = stub("commit")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun createdViaEmail(): Stub<Boolean> = stub("createdViaEmail")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun diffHunk(): Stub<String> = stub("diffHunk")

  fun draftedAt(): Stub<DateTime> = stub("draftedAt")

  fun editor(): Stub<Actor> = stub("editor")

  fun id(): Stub<String> = stub("id")

  fun lastEditedAt(): Stub<DateTime> = stub("lastEditedAt")

  fun originalCommit(): Stub<Commit> = stub("originalCommit")

  fun originalPosition(): Stub<Int> = stub("originalPosition")

  fun path(): Stub<String> = stub("path")

  fun position(): Stub<Int> = stub("position")

  fun publishedAt(): Stub<DateTime> = stub("publishedAt")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun pullRequestReview(): Stub<PullRequestReview> = stub("pullRequestReview")

  fun reactionGroups(): Stub<ReactionGroup> = stub("reactionGroups")

  fun reactions(): Stub<ReactionConnection> = stub("reactions")

  fun repository(): Stub<Repository> = stub("repository")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun url(): Stub<URI> = stub("url")

  fun viewerCanDelete(): Stub<Boolean> = stub("viewerCanDelete")

  fun viewerCanReact(): Stub<Boolean> = stub("viewerCanReact")

  fun viewerCanUpdate(): Stub<Boolean> = stub("viewerCanUpdate")

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub("viewerCannotUpdateReasons")

  fun viewerDidAuthor(): Stub<Boolean> = stub("viewerDidAuthor")

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReactionsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReactionsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReactionsArgs {
       addArg("before", value); return this;
    }

    fun content(value: ReactionContent): ReactionsArgs {
       addArg("content", value); return this;
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
       addArg("orderBy", value); return this;
    }
  }
}

interface PullRequestReview : QType {
  fun author(): Stub<Actor> = stub("author")

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub("authorAssociation")

  fun body(): Stub<String> = stub("body")

  fun bodyHTML(): Stub<HTML> = stub("bodyHTML")

  fun bodyText(): Stub<String> = stub("bodyText")

  fun comments(): Stub<PullRequestReviewCommentConnection> = stub("comments")

  fun commit(): Stub<Commit> = stub("commit")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun createdViaEmail(): Stub<Boolean> = stub("createdViaEmail")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun editor(): Stub<Actor> = stub("editor")

  fun id(): Stub<String> = stub("id")

  fun lastEditedAt(): Stub<DateTime> = stub("lastEditedAt")

  fun publishedAt(): Stub<DateTime> = stub("publishedAt")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun repository(): Stub<Repository> = stub("repository")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun state(): Stub<PullRequestReviewState> = stub("state")

  fun submittedAt(): Stub<DateTime> = stub("submittedAt")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun url(): Stub<URI> = stub("url")

  fun viewerCanDelete(): Stub<Boolean> = stub("viewerCanDelete")

  fun viewerCanUpdate(): Stub<Boolean> = stub("viewerCanUpdate")

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub("viewerCannotUpdateReasons")

  fun viewerDidAuthor(): Stub<Boolean> = stub("viewerDidAuthor")

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): CommentsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): CommentsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): CommentsArgs {
       addArg("before", value); return this;
    }
  }
}

interface PullRequestEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<PullRequest> = stub("node")
}

interface PullRequestConnection : QType {
  fun edges(): Stub<PullRequestEdge> = stub("edges")

  fun nodes(): Stub<PullRequest> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface PullRequestCommitEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<PullRequestCommit> = stub("node")
}

interface PullRequestCommitConnection : QType {
  fun edges(): Stub<PullRequestCommitEdge> = stub("edges")

  fun nodes(): Stub<PullRequestCommit> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface PullRequestCommit : QType {
  fun commit(): Stub<Commit> = stub("commit")

  fun id(): Stub<String> = stub("id")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun url(): Stub<URI> = stub("url")
}

interface PullRequest : QType {
  fun assignees(): Stub<UserConnection> = stub("assignees")

  fun author(): Stub<Actor> = stub("author")

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub("authorAssociation")

  fun baseRef(): Stub<Ref> = stub("baseRef")

  fun baseRefName(): Stub<String> = stub("baseRefName")

  fun body(): Stub<String> = stub("body")

  fun bodyHTML(): Stub<HTML> = stub("bodyHTML")

  fun bodyText(): Stub<String> = stub("bodyText")

  fun closed(): Stub<Boolean> = stub("closed")

  fun comments(): Stub<IssueCommentConnection> = stub("comments")

  fun commits(): Stub<PullRequestCommitConnection> = stub("commits")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun createdViaEmail(): Stub<Boolean> = stub("createdViaEmail")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun editor(): Stub<Actor> = stub("editor")

  fun headRef(): Stub<Ref> = stub("headRef")

  fun headRefName(): Stub<String> = stub("headRefName")

  fun headRepository(): Stub<Repository> = stub("headRepository")

  fun headRepositoryOwner(): Stub<RepositoryOwner> = stub("headRepositoryOwner")

  fun id(): Stub<String> = stub("id")

  fun isCrossRepository(): Stub<Boolean> = stub("isCrossRepository")

  fun labels(): Stub<LabelConnection> = stub("labels")

  fun lastEditedAt(): Stub<DateTime> = stub("lastEditedAt")

  fun locked(): Stub<Boolean> = stub("locked")

  fun mergeCommit(): Stub<Commit> = stub("mergeCommit")

  fun mergeable(): Stub<MergeableState> = stub("mergeable")

  fun merged(): Stub<Boolean> = stub("merged")

  fun mergedAt(): Stub<DateTime> = stub("mergedAt")

  fun number(): Stub<Int> = stub("number")

  fun participants(): Stub<UserConnection> = stub("participants")

  fun potentialMergeCommit(): Stub<Commit> = stub("potentialMergeCommit")

  fun publishedAt(): Stub<DateTime> = stub("publishedAt")

  fun reactionGroups(): Stub<ReactionGroup> = stub("reactionGroups")

  fun reactions(): Stub<ReactionConnection> = stub("reactions")

  fun repository(): Stub<Repository> = stub("repository")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun revertResourcePath(): Stub<URI> = stub("revertResourcePath")

  fun revertUrl(): Stub<URI> = stub("revertUrl")

  fun reviewRequests(): Stub<ReviewRequestConnection> = stub("reviewRequests")

  fun reviews(): Stub<PullRequestReviewConnection> = stub("reviews")

  fun state(): Stub<PullRequestState> = stub("state")

  fun suggestedReviewers(): Stub<SuggestedReviewer> = stub("suggestedReviewers")

  fun timeline(): Stub<PullRequestTimelineConnection> = stub("timeline")

  fun title(): Stub<String> = stub("title")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun url(): Stub<URI> = stub("url")

  fun viewerCanReact(): Stub<Boolean> = stub("viewerCanReact")

  fun viewerCanSubscribe(): Stub<Boolean> = stub("viewerCanSubscribe")

  fun viewerCanUpdate(): Stub<Boolean> = stub("viewerCanUpdate")

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub("viewerCannotUpdateReasons")

  fun viewerDidAuthor(): Stub<Boolean> = stub("viewerDidAuthor")

  fun viewerSubscription(): Stub<SubscriptionState> = stub("viewerSubscription")

  class AssigneesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssigneesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): AssigneesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): AssigneesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): AssigneesArgs {
       addArg("before", value); return this;
    }
  }

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): CommentsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): CommentsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): CommentsArgs {
       addArg("before", value); return this;
    }
  }

  class CommitsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommitsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): CommitsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): CommitsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): CommitsArgs {
       addArg("before", value); return this;
    }
  }

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): LabelsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): LabelsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): LabelsArgs {
       addArg("before", value); return this;
    }
  }

  class ParticipantsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ParticipantsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ParticipantsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ParticipantsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ParticipantsArgs {
       addArg("before", value); return this;
    }
  }

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReactionsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReactionsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReactionsArgs {
       addArg("before", value); return this;
    }

    fun content(value: ReactionContent): ReactionsArgs {
       addArg("content", value); return this;
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
       addArg("orderBy", value); return this;
    }
  }

  class ReviewRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReviewRequestsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReviewRequestsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReviewRequestsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReviewRequestsArgs {
       addArg("before", value); return this;
    }
  }

  class ReviewsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReviewsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReviewsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReviewsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReviewsArgs {
       addArg("before", value); return this;
    }

    fun states(value: PullRequestReviewState): ReviewsArgs {
       addArg("states", value); return this;
    }
  }

  class TimelineArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): TimelineArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): TimelineArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): TimelineArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): TimelineArgs {
       addArg("before", value); return this;
    }

    fun since(value: DateTime): TimelineArgs {
       addArg("since", value); return this;
    }
  }
}

interface ProtectedBranchEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<ProtectedBranch> = stub("node")
}

interface ProtectedBranchConnection : QType {
  fun edges(): Stub<ProtectedBranchEdge> = stub("edges")

  fun nodes(): Stub<ProtectedBranch> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface ProtectedBranch : QType {
  fun creator(): Stub<Actor> = stub("creator")

  fun hasDismissableStaleReviews(): Stub<Boolean> = stub("hasDismissableStaleReviews")

  fun hasRequiredReviews(): Stub<Boolean> = stub("hasRequiredReviews")

  fun hasRequiredStatusChecks(): Stub<Boolean> = stub("hasRequiredStatusChecks")

  fun hasRestrictedPushes(): Stub<Boolean> = stub("hasRestrictedPushes")

  fun hasRestrictedReviewDismissals(): Stub<Boolean> = stub("hasRestrictedReviewDismissals")

  fun hasStrictRequiredStatusChecks(): Stub<Boolean> = stub("hasStrictRequiredStatusChecks")

  fun id(): Stub<String> = stub("id")

  fun isAdminEnforced(): Stub<Boolean> = stub("isAdminEnforced")

  fun name(): Stub<String> = stub("name")

  fun pushAllowances(): Stub<PushAllowanceConnection> = stub("pushAllowances")

  fun repository(): Stub<Repository> = stub("repository")

  fun requiredStatusCheckContexts(): Stub<String> = stub("requiredStatusCheckContexts")

  fun reviewDismissalAllowances(): Stub<ReviewDismissalAllowanceConnection> = stub("reviewDismissalAllowances")

  class PushAllowancesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PushAllowancesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): PushAllowancesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): PushAllowancesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): PushAllowancesArgs {
       addArg("before", value); return this;
    }
  }

  class ReviewDismissalAllowancesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReviewDismissalAllowancesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReviewDismissalAllowancesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReviewDismissalAllowancesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReviewDismissalAllowancesArgs {
       addArg("before", value); return this;
    }
  }
}

interface ProjectEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Project> = stub("node")
}

interface ProjectConnection : QType {
  fun edges(): Stub<ProjectEdge> = stub("edges")

  fun nodes(): Stub<Project> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface ProjectColumnEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<ProjectColumn> = stub("node")
}

interface ProjectColumnConnection : QType {
  fun edges(): Stub<ProjectColumnEdge> = stub("edges")

  fun nodes(): Stub<ProjectColumn> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface ProjectColumn : QType {
  fun cards(): Stub<ProjectCardConnection> = stub("cards")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")

  fun name(): Stub<String> = stub("name")

  fun project(): Stub<Project> = stub("project")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  class CardsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CardsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): CardsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): CardsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): CardsArgs {
       addArg("before", value); return this;
    }
  }
}

interface ProjectCardEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<ProjectCard> = stub("node")
}

interface ProjectCardConnection : QType {
  fun edges(): Stub<ProjectCardEdge> = stub("edges")

  fun nodes(): Stub<ProjectCard> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface ProjectCard : QType {
  fun column(): Stub<ProjectColumn> = stub("column")

  fun content(): Stub<ProjectCardItem> = stub("content")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun creator(): Stub<Actor> = stub("creator")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")

  fun note(): Stub<String> = stub("note")

  fun project(): Stub<Project> = stub("project")

  fun projectColumn(): Stub<ProjectColumn> = stub("projectColumn")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun state(): Stub<ProjectCardState> = stub("state")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun url(): Stub<URI> = stub("url")
}

interface Project : QType {
  fun body(): Stub<String> = stub("body")

  fun bodyHTML(): Stub<HTML> = stub("bodyHTML")

  fun closedAt(): Stub<DateTime> = stub("closedAt")

  fun columns(): Stub<ProjectColumnConnection> = stub("columns")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun creator(): Stub<Actor> = stub("creator")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")

  fun name(): Stub<String> = stub("name")

  fun number(): Stub<Int> = stub("number")

  fun owner(): Stub<ProjectOwner> = stub("owner")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun state(): Stub<ProjectState> = stub("state")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun url(): Stub<URI> = stub("url")

  fun viewerCanUpdate(): Stub<Boolean> = stub("viewerCanUpdate")

  class ColumnsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ColumnsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ColumnsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ColumnsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ColumnsArgs {
       addArg("before", value); return this;
    }
  }
}

interface PageInfo : QType {
  fun endCursor(): Stub<String> = stub("endCursor")

  fun hasNextPage(): Stub<Boolean> = stub("hasNextPage")

  fun hasPreviousPage(): Stub<Boolean> = stub("hasPreviousPage")

  fun startCursor(): Stub<String> = stub("startCursor")
}

interface OrganizationInvitationEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<OrganizationInvitation> = stub("node")
}

interface OrganizationInvitationConnection : QType {
  fun edges(): Stub<OrganizationInvitationEdge> = stub("edges")

  fun nodes(): Stub<OrganizationInvitation> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface OrganizationInvitation : QType {
  fun email(): Stub<String> = stub("email")

  fun id(): Stub<String> = stub("id")

  fun invitee(): Stub<User> = stub("invitee")

  fun inviter(): Stub<User> = stub("inviter")

  fun role(): Stub<OrganizationInvitationRole> = stub("role")
}

interface OrganizationIdentityProvider : QType {
  fun digestMethod(): Stub<URI> = stub("digestMethod")

  fun externalIdentities(): Stub<ExternalIdentityConnection> = stub("externalIdentities")

  fun id(): Stub<String> = stub("id")

  fun idpCertificate(): Stub<X509Certificate> = stub("idpCertificate")

  fun issuer(): Stub<String> = stub("issuer")

  fun organization(): Stub<Organization> = stub("organization")

  fun signatureMethod(): Stub<URI> = stub("signatureMethod")

  fun ssoUrl(): Stub<URI> = stub("ssoUrl")

  class ExternalIdentitiesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ExternalIdentitiesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ExternalIdentitiesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ExternalIdentitiesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ExternalIdentitiesArgs {
       addArg("before", value); return this;
    }
  }
}

interface OrganizationEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Organization> = stub("node")
}

interface OrganizationConnection : QType {
  fun edges(): Stub<OrganizationEdge> = stub("edges")

  fun nodes(): Stub<Organization> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface Organization : QType {
  fun avatarUrl(): Stub<URI> = stub("avatarUrl")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")

  fun isInvoiced(): Stub<Boolean> = stub("isInvoiced")

  fun login(): Stub<String> = stub("login")

  fun members(): Stub<UserConnection> = stub("members")

  fun name(): Stub<String> = stub("name")

  fun newTeamResourcePath(): Stub<URI> = stub("newTeamResourcePath")

  fun newTeamUrl(): Stub<URI> = stub("newTeamUrl")

  fun organizationBillingEmail(): Stub<String> = stub("organizationBillingEmail")

  fun pinnedRepositories(): Stub<RepositoryConnection> = stub("pinnedRepositories")

  fun project(): Stub<Project> = stub("project")

  fun projects(): Stub<ProjectConnection> = stub("projects")

  fun projectsResourcePath(): Stub<URI> = stub("projectsResourcePath")

  fun projectsUrl(): Stub<URI> = stub("projectsUrl")

  fun repositories(): Stub<RepositoryConnection> = stub("repositories")

  fun repository(): Stub<Repository> = stub("repository")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun samlIdentityProvider(): Stub<OrganizationIdentityProvider> = stub("samlIdentityProvider")

  fun team(): Stub<Team> = stub("team")

  fun teams(): Stub<TeamConnection> = stub("teams")

  fun teamsResourcePath(): Stub<URI> = stub("teamsResourcePath")

  fun teamsUrl(): Stub<URI> = stub("teamsUrl")

  fun url(): Stub<URI> = stub("url")

  fun viewerCanAdminister(): Stub<Boolean> = stub("viewerCanAdminister")

  fun viewerCanCreateProjects(): Stub<Boolean> = stub("viewerCanCreateProjects")

  fun viewerCanCreateRepositories(): Stub<Boolean> = stub("viewerCanCreateRepositories")

  fun viewerCanCreateTeams(): Stub<Boolean> = stub("viewerCanCreateTeams")

  fun viewerIsAMember(): Stub<Boolean> = stub("viewerIsAMember")

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }

  class MembersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): MembersArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): MembersArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): MembersArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): MembersArgs {
       addArg("before", value); return this;
    }
  }

  class PinnedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PinnedRepositoriesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): PinnedRepositoriesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): PinnedRepositoriesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): PinnedRepositoriesArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs {
       addArg("privacy", value); return this;
    }

    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs {
       addArg("orderBy", value); return this;
    }

    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs {
       addArg("affiliations", value); return this;
    }

    fun isLocked(value: Boolean): PinnedRepositoriesArgs {
       addArg("isLocked", value); return this;
    }
  }

  class ProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): ProjectArgs {
       addArg("number", value); return this;
    }
  }

  class ProjectsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProjectsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ProjectsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ProjectsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ProjectsArgs {
       addArg("before", value); return this;
    }

    fun orderBy(value: ProjectOrder): ProjectsArgs {
       addArg("orderBy", value); return this;
    }

    fun search(value: String): ProjectsArgs {
       addArg("search", value); return this;
    }

    fun states(value: ProjectState): ProjectsArgs {
       addArg("states", value); return this;
    }
  }

  class RepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoriesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): RepositoriesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): RepositoriesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): RepositoriesArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: RepositoryPrivacy): RepositoriesArgs {
       addArg("privacy", value); return this;
    }

    fun orderBy(value: RepositoryOrder): RepositoriesArgs {
       addArg("orderBy", value); return this;
    }

    fun affiliations(value: RepositoryAffiliation): RepositoriesArgs {
       addArg("affiliations", value); return this;
    }

    fun isLocked(value: Boolean): RepositoriesArgs {
       addArg("isLocked", value); return this;
    }

    fun isFork(value: Boolean): RepositoriesArgs {
       addArg("isFork", value); return this;
    }
  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): RepositoryArgs {
       addArg("name", value); return this;
    }
  }

  class TeamArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun slug(value: String): TeamArgs {
       addArg("slug", value); return this;
    }
  }

  class TeamsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): TeamsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): TeamsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): TeamsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): TeamsArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: TeamPrivacy): TeamsArgs {
       addArg("privacy", value); return this;
    }

    fun role(value: TeamRole): TeamsArgs {
       addArg("role", value); return this;
    }

    fun query(value: String): TeamsArgs {
       addArg("query", value); return this;
    }

    fun userLogins(value: String): TeamsArgs {
       addArg("userLogins", value); return this;
    }

    fun orderBy(value: TeamOrder): TeamsArgs {
       addArg("orderBy", value); return this;
    }

    fun ldapMapped(value: Boolean): TeamsArgs {
       addArg("ldapMapped", value); return this;
    }
  }
}

interface Mutation : QType {
  fun acceptTopicSuggestion(): Stub<AcceptTopicSuggestionPayload> = stub("acceptTopicSuggestion")

  fun addComment(): Stub<AddCommentPayload> = stub("addComment")

  fun addProjectCard(): Stub<AddProjectCardPayload> = stub("addProjectCard")

  fun addProjectColumn(): Stub<AddProjectColumnPayload> = stub("addProjectColumn")

  fun addPullRequestReview(): Stub<AddPullRequestReviewPayload> = stub("addPullRequestReview")

  fun addPullRequestReviewComment(): Stub<AddPullRequestReviewCommentPayload> = stub("addPullRequestReviewComment")

  fun addReaction(): Stub<AddReactionPayload> = stub("addReaction")

  fun addStar(): Stub<AddStarPayload> = stub("addStar")

  fun createProject(): Stub<CreateProjectPayload> = stub("createProject")

  fun declineTopicSuggestion(): Stub<DeclineTopicSuggestionPayload> = stub("declineTopicSuggestion")

  fun deleteProject(): Stub<DeleteProjectPayload> = stub("deleteProject")

  fun deleteProjectCard(): Stub<DeleteProjectCardPayload> = stub("deleteProjectCard")

  fun deleteProjectColumn(): Stub<DeleteProjectColumnPayload> = stub("deleteProjectColumn")

  fun deletePullRequestReview(): Stub<DeletePullRequestReviewPayload> = stub("deletePullRequestReview")

  fun dismissPullRequestReview(): Stub<DismissPullRequestReviewPayload> = stub("dismissPullRequestReview")

  fun moveProjectCard(): Stub<MoveProjectCardPayload> = stub("moveProjectCard")

  fun moveProjectColumn(): Stub<MoveProjectColumnPayload> = stub("moveProjectColumn")

  fun removeOutsideCollaborator(): Stub<RemoveOutsideCollaboratorPayload> = stub("removeOutsideCollaborator")

  fun removeReaction(): Stub<RemoveReactionPayload> = stub("removeReaction")

  fun removeStar(): Stub<RemoveStarPayload> = stub("removeStar")

  fun requestReviews(): Stub<RequestReviewsPayload> = stub("requestReviews")

  fun submitPullRequestReview(): Stub<SubmitPullRequestReviewPayload> = stub("submitPullRequestReview")

  fun updateProject(): Stub<UpdateProjectPayload> = stub("updateProject")

  fun updateProjectCard(): Stub<UpdateProjectCardPayload> = stub("updateProjectCard")

  fun updateProjectColumn(): Stub<UpdateProjectColumnPayload> = stub("updateProjectColumn")

  fun updatePullRequestReview(): Stub<UpdatePullRequestReviewPayload> = stub("updatePullRequestReview")

  fun updatePullRequestReviewComment(): Stub<UpdatePullRequestReviewCommentPayload> = stub("updatePullRequestReviewComment")

  fun updateSubscription(): Stub<UpdateSubscriptionPayload> = stub("updateSubscription")

  fun updateTopics(): Stub<UpdateTopicsPayload> = stub("updateTopics")

  class AcceptTopicSuggestionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AcceptTopicSuggestionInput): AcceptTopicSuggestionArgs {
       addArg("input", value); return this;
    }
  }

  class AddCommentArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddCommentInput): AddCommentArgs {
       addArg("input", value); return this;
    }
  }

  class AddProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddProjectCardInput): AddProjectCardArgs {
       addArg("input", value); return this;
    }
  }

  class AddProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddProjectColumnInput): AddProjectColumnArgs {
       addArg("input", value); return this;
    }
  }

  class AddPullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddPullRequestReviewInput): AddPullRequestReviewArgs {
       addArg("input", value); return this;
    }
  }

  class AddPullRequestReviewCommentArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddPullRequestReviewCommentInput): AddPullRequestReviewCommentArgs {
       addArg("input", value); return this;
    }
  }

  class AddReactionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddReactionInput): AddReactionArgs {
       addArg("input", value); return this;
    }
  }

  class AddStarArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: AddStarInput): AddStarArgs {
       addArg("input", value); return this;
    }
  }

  class CreateProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: CreateProjectInput): CreateProjectArgs {
       addArg("input", value); return this;
    }
  }

  class DeclineTopicSuggestionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeclineTopicSuggestionInput): DeclineTopicSuggestionArgs {
       addArg("input", value); return this;
    }
  }

  class DeleteProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeleteProjectInput): DeleteProjectArgs {
       addArg("input", value); return this;
    }
  }

  class DeleteProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeleteProjectCardInput): DeleteProjectCardArgs {
       addArg("input", value); return this;
    }
  }

  class DeleteProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeleteProjectColumnInput): DeleteProjectColumnArgs {
       addArg("input", value); return this;
    }
  }

  class DeletePullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DeletePullRequestReviewInput): DeletePullRequestReviewArgs {
       addArg("input", value); return this;
    }
  }

  class DismissPullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: DismissPullRequestReviewInput): DismissPullRequestReviewArgs {
       addArg("input", value); return this;
    }
  }

  class MoveProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: MoveProjectCardInput): MoveProjectCardArgs {
       addArg("input", value); return this;
    }
  }

  class MoveProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: MoveProjectColumnInput): MoveProjectColumnArgs {
       addArg("input", value); return this;
    }
  }

  class RemoveOutsideCollaboratorArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RemoveOutsideCollaboratorInput): RemoveOutsideCollaboratorArgs {
       addArg("input", value); return this;
    }
  }

  class RemoveReactionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RemoveReactionInput): RemoveReactionArgs {
       addArg("input", value); return this;
    }
  }

  class RemoveStarArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RemoveStarInput): RemoveStarArgs {
       addArg("input", value); return this;
    }
  }

  class RequestReviewsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: RequestReviewsInput): RequestReviewsArgs {
       addArg("input", value); return this;
    }
  }

  class SubmitPullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: SubmitPullRequestReviewInput): SubmitPullRequestReviewArgs {
       addArg("input", value); return this;
    }
  }

  class UpdateProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateProjectInput): UpdateProjectArgs {
       addArg("input", value); return this;
    }
  }

  class UpdateProjectCardArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateProjectCardInput): UpdateProjectCardArgs {
       addArg("input", value); return this;
    }
  }

  class UpdateProjectColumnArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateProjectColumnInput): UpdateProjectColumnArgs {
       addArg("input", value); return this;
    }
  }

  class UpdatePullRequestReviewArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdatePullRequestReviewInput): UpdatePullRequestReviewArgs {
       addArg("input", value); return this;
    }
  }

  class UpdatePullRequestReviewCommentArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdatePullRequestReviewCommentInput): UpdatePullRequestReviewCommentArgs {
       addArg("input", value); return this;
    }
  }

  class UpdateSubscriptionArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateSubscriptionInput): UpdateSubscriptionArgs {
       addArg("input", value); return this;
    }
  }

  class UpdateTopicsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun input(value: UpdateTopicsInput): UpdateTopicsArgs {
       addArg("input", value); return this;
    }
  }
}

interface MovedColumnsInProjectEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")
}

interface MoveProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun columnEdge(): Stub<ProjectColumnEdge> = stub("columnEdge")
}

interface MoveProjectCardPayload : QType {
  fun cardEdge(): Stub<ProjectCardEdge> = stub("cardEdge")

  fun clientMutationId(): Stub<String> = stub("clientMutationId")
}

interface MilestonedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun milestoneTitle(): Stub<String> = stub("milestoneTitle")

  fun subject(): Stub<MilestoneItem> = stub("subject")
}

interface MilestoneEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Milestone> = stub("node")
}

interface MilestoneConnection : QType {
  fun edges(): Stub<MilestoneEdge> = stub("edges")

  fun nodes(): Stub<Milestone> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface Milestone : QType {
  fun creator(): Stub<Actor> = stub("creator")

  fun description(): Stub<String> = stub("description")

  fun dueOn(): Stub<DateTime> = stub("dueOn")

  fun id(): Stub<String> = stub("id")

  fun number(): Stub<Int> = stub("number")

  fun repository(): Stub<Repository> = stub("repository")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun state(): Stub<MilestoneState> = stub("state")

  fun title(): Stub<String> = stub("title")

  fun url(): Stub<URI> = stub("url")
}

interface MergedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun commit(): Stub<Commit> = stub("commit")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun mergeRef(): Stub<Ref> = stub("mergeRef")

  fun mergeRefName(): Stub<String> = stub("mergeRefName")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun url(): Stub<URI> = stub("url")
}

interface MentionedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")
}

interface LockedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun lockable(): Stub<Lockable> = stub("lockable")
}

interface LanguageEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Language> = stub("node")

  fun size(): Stub<Int> = stub("size")
}

interface LanguageConnection : QType {
  fun edges(): Stub<LanguageEdge> = stub("edges")

  fun nodes(): Stub<Language> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")

  fun totalSize(): Stub<Int> = stub("totalSize")
}

interface Language : QType {
  fun color(): Stub<String> = stub("color")

  fun id(): Stub<String> = stub("id")

  fun name(): Stub<String> = stub("name")
}

interface LabeledEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun label(): Stub<Label> = stub("label")

  fun labelable(): Stub<Labelable> = stub("labelable")
}

interface LabelEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Label> = stub("node")
}

interface LabelConnection : QType {
  fun edges(): Stub<LabelEdge> = stub("edges")

  fun nodes(): Stub<Label> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface Label : QType {
  fun color(): Stub<String> = stub("color")

  fun id(): Stub<String> = stub("id")

  fun issues(): Stub<IssueConnection> = stub("issues")

  fun name(): Stub<String> = stub("name")

  fun pullRequests(): Stub<PullRequestConnection> = stub("pullRequests")

  fun repository(): Stub<Repository> = stub("repository")

  class IssuesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): IssuesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): IssuesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): IssuesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): IssuesArgs {
       addArg("before", value); return this;
    }

    fun labels(value: String): IssuesArgs {
       addArg("labels", value); return this;
    }

    fun orderBy(value: IssueOrder): IssuesArgs {
       addArg("orderBy", value); return this;
    }

    fun states(value: IssueState): IssuesArgs {
       addArg("states", value); return this;
    }
  }

  class PullRequestsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PullRequestsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): PullRequestsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): PullRequestsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): PullRequestsArgs {
       addArg("before", value); return this;
    }
  }
}

interface IssueTimelineItemEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<IssueTimelineItem> = stub("node")
}

interface IssueTimelineConnection : QType {
  fun edges(): Stub<IssueTimelineItemEdge> = stub("edges")

  fun nodes(): Stub<IssueTimelineItem> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface IssueEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Issue> = stub("node")
}

interface IssueConnection : QType {
  fun edges(): Stub<IssueEdge> = stub("edges")

  fun nodes(): Stub<Issue> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface IssueCommentEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<IssueComment> = stub("node")
}

interface IssueCommentConnection : QType {
  fun edges(): Stub<IssueCommentEdge> = stub("edges")

  fun nodes(): Stub<IssueComment> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface IssueComment : QType {
  fun author(): Stub<Actor> = stub("author")

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub("authorAssociation")

  fun body(): Stub<String> = stub("body")

  fun bodyHTML(): Stub<HTML> = stub("bodyHTML")

  fun bodyText(): Stub<String> = stub("bodyText")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun createdViaEmail(): Stub<Boolean> = stub("createdViaEmail")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun editor(): Stub<Actor> = stub("editor")

  fun id(): Stub<String> = stub("id")

  fun issue(): Stub<Issue> = stub("issue")

  fun lastEditedAt(): Stub<DateTime> = stub("lastEditedAt")

  fun publishedAt(): Stub<DateTime> = stub("publishedAt")

  fun reactionGroups(): Stub<ReactionGroup> = stub("reactionGroups")

  fun reactions(): Stub<ReactionConnection> = stub("reactions")

  fun repository(): Stub<Repository> = stub("repository")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun viewerCanDelete(): Stub<Boolean> = stub("viewerCanDelete")

  fun viewerCanReact(): Stub<Boolean> = stub("viewerCanReact")

  fun viewerCanUpdate(): Stub<Boolean> = stub("viewerCanUpdate")

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub("viewerCannotUpdateReasons")

  fun viewerDidAuthor(): Stub<Boolean> = stub("viewerDidAuthor")

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReactionsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReactionsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReactionsArgs {
       addArg("before", value); return this;
    }

    fun content(value: ReactionContent): ReactionsArgs {
       addArg("content", value); return this;
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
       addArg("orderBy", value); return this;
    }
  }
}

interface Issue : QType {
  fun assignees(): Stub<UserConnection> = stub("assignees")

  fun author(): Stub<Actor> = stub("author")

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub("authorAssociation")

  fun body(): Stub<String> = stub("body")

  fun bodyHTML(): Stub<HTML> = stub("bodyHTML")

  fun bodyText(): Stub<String> = stub("bodyText")

  fun closed(): Stub<Boolean> = stub("closed")

  fun comments(): Stub<IssueCommentConnection> = stub("comments")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun createdViaEmail(): Stub<Boolean> = stub("createdViaEmail")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun editor(): Stub<Actor> = stub("editor")

  fun id(): Stub<String> = stub("id")

  fun labels(): Stub<LabelConnection> = stub("labels")

  fun lastEditedAt(): Stub<DateTime> = stub("lastEditedAt")

  fun locked(): Stub<Boolean> = stub("locked")

  fun milestone(): Stub<Milestone> = stub("milestone")

  fun number(): Stub<Int> = stub("number")

  fun participants(): Stub<UserConnection> = stub("participants")

  fun publishedAt(): Stub<DateTime> = stub("publishedAt")

  fun reactionGroups(): Stub<ReactionGroup> = stub("reactionGroups")

  fun reactions(): Stub<ReactionConnection> = stub("reactions")

  fun repository(): Stub<Repository> = stub("repository")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun state(): Stub<IssueState> = stub("state")

  fun timeline(): Stub<IssueTimelineConnection> = stub("timeline")

  fun title(): Stub<String> = stub("title")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun url(): Stub<URI> = stub("url")

  fun viewerCanReact(): Stub<Boolean> = stub("viewerCanReact")

  fun viewerCanSubscribe(): Stub<Boolean> = stub("viewerCanSubscribe")

  fun viewerCanUpdate(): Stub<Boolean> = stub("viewerCanUpdate")

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub("viewerCannotUpdateReasons")

  fun viewerDidAuthor(): Stub<Boolean> = stub("viewerDidAuthor")

  fun viewerSubscription(): Stub<SubscriptionState> = stub("viewerSubscription")

  class AssigneesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssigneesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): AssigneesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): AssigneesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): AssigneesArgs {
       addArg("before", value); return this;
    }
  }

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): CommentsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): CommentsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): CommentsArgs {
       addArg("before", value); return this;
    }
  }

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): LabelsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): LabelsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): LabelsArgs {
       addArg("before", value); return this;
    }
  }

  class ParticipantsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ParticipantsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ParticipantsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ParticipantsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ParticipantsArgs {
       addArg("before", value); return this;
    }
  }

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReactionsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReactionsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReactionsArgs {
       addArg("before", value); return this;
    }

    fun content(value: ReactionContent): ReactionsArgs {
       addArg("content", value); return this;
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
       addArg("orderBy", value); return this;
    }
  }

  class TimelineArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): TimelineArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): TimelineArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): TimelineArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): TimelineArgs {
       addArg("before", value); return this;
    }

    fun since(value: DateTime): TimelineArgs {
       addArg("since", value); return this;
    }
  }
}

interface HeadRefRestoredEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")
}

interface HeadRefForcePushedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun afterCommit(): Stub<Commit> = stub("afterCommit")

  fun beforeCommit(): Stub<Commit> = stub("beforeCommit")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun ref(): Stub<Ref> = stub("ref")
}

interface HeadRefDeletedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun headRef(): Stub<Ref> = stub("headRef")

  fun headRefName(): Stub<String> = stub("headRefName")

  fun id(): Stub<String> = stub("id")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")
}

interface GpgSignature : QType {
  fun email(): Stub<String> = stub("email")

  fun isValid(): Stub<Boolean> = stub("isValid")

  fun keyId(): Stub<String> = stub("keyId")

  fun payload(): Stub<String> = stub("payload")

  fun signature(): Stub<String> = stub("signature")

  fun signer(): Stub<User> = stub("signer")

  fun state(): Stub<GitSignatureState> = stub("state")
}

interface GitActor : QType {
  fun avatarUrl(): Stub<URI> = stub("avatarUrl")

  fun date(): Stub<GitTimestamp> = stub("date")

  fun email(): Stub<String> = stub("email")

  fun name(): Stub<String> = stub("name")

  fun user(): Stub<User> = stub("user")

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }
}

interface GistEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Gist> = stub("node")
}

interface GistConnection : QType {
  fun edges(): Stub<GistEdge> = stub("edges")

  fun nodes(): Stub<Gist> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface GistCommentEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<GistComment> = stub("node")
}

interface GistCommentConnection : QType {
  fun edges(): Stub<GistCommentEdge> = stub("edges")

  fun nodes(): Stub<GistComment> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface GistComment : QType {
  fun author(): Stub<Actor> = stub("author")

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub("authorAssociation")

  fun body(): Stub<String> = stub("body")

  fun bodyHTML(): Stub<HTML> = stub("bodyHTML")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun createdViaEmail(): Stub<Boolean> = stub("createdViaEmail")

  fun editor(): Stub<Actor> = stub("editor")

  fun id(): Stub<String> = stub("id")

  fun lastEditedAt(): Stub<DateTime> = stub("lastEditedAt")

  fun publishedAt(): Stub<DateTime> = stub("publishedAt")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun viewerCanDelete(): Stub<Boolean> = stub("viewerCanDelete")

  fun viewerCanUpdate(): Stub<Boolean> = stub("viewerCanUpdate")

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub("viewerCannotUpdateReasons")

  fun viewerDidAuthor(): Stub<Boolean> = stub("viewerDidAuthor")
}

interface Gist : QType {
  fun comments(): Stub<GistCommentConnection> = stub("comments")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun description(): Stub<String> = stub("description")

  fun id(): Stub<String> = stub("id")

  fun isPublic(): Stub<Boolean> = stub("isPublic")

  fun name(): Stub<String> = stub("name")

  fun owner(): Stub<RepositoryOwner> = stub("owner")

  fun stargazers(): Stub<StargazerConnection> = stub("stargazers")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun viewerHasStarred(): Stub<Boolean> = stub("viewerHasStarred")

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): CommentsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): CommentsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): CommentsArgs {
       addArg("before", value); return this;
    }
  }

  class StargazersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StargazersArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): StargazersArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): StargazersArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): StargazersArgs {
       addArg("before", value); return this;
    }

    fun orderBy(value: StarOrder): StargazersArgs {
       addArg("orderBy", value); return this;
    }
  }
}

interface FollowingConnection : QType {
  fun edges(): Stub<UserEdge> = stub("edges")

  fun nodes(): Stub<User> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface FollowerConnection : QType {
  fun edges(): Stub<UserEdge> = stub("edges")

  fun nodes(): Stub<User> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface ExternalIdentityScimAttributes : QType {
  fun username(): Stub<String> = stub("username")
}

interface ExternalIdentitySamlAttributes : QType {
  fun nameId(): Stub<String> = stub("nameId")
}

interface ExternalIdentityEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<ExternalIdentity> = stub("node")
}

interface ExternalIdentityConnection : QType {
  fun edges(): Stub<ExternalIdentityEdge> = stub("edges")

  fun nodes(): Stub<ExternalIdentity> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface ExternalIdentity : QType {
  fun guid(): Stub<String> = stub("guid")

  fun id(): Stub<String> = stub("id")

  fun organizationInvitation(): Stub<OrganizationInvitation> = stub("organizationInvitation")

  fun samlIdentity(): Stub<ExternalIdentitySamlAttributes> = stub("samlIdentity")

  fun scimIdentity(): Stub<ExternalIdentityScimAttributes> = stub("scimIdentity")

  fun user(): Stub<User> = stub("user")
}

interface DismissPullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun pullRequestReview(): Stub<PullRequestReview> = stub("pullRequestReview")
}

interface DeploymentStatusEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<DeploymentStatus> = stub("node")
}

interface DeploymentStatusConnection : QType {
  fun edges(): Stub<DeploymentStatusEdge> = stub("edges")

  fun nodes(): Stub<DeploymentStatus> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface DeploymentStatus : QType {
  fun creator(): Stub<Actor> = stub("creator")

  fun deployment(): Stub<Deployment> = stub("deployment")

  fun description(): Stub<String> = stub("description")

  fun environmentUrl(): Stub<URI> = stub("environmentUrl")

  fun id(): Stub<String> = stub("id")

  fun logUrl(): Stub<URI> = stub("logUrl")

  fun state(): Stub<DeploymentStatusState> = stub("state")
}

interface DeploymentEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Deployment> = stub("node")
}

interface DeploymentConnection : QType {
  fun edges(): Stub<DeploymentEdge> = stub("edges")

  fun nodes(): Stub<Deployment> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface Deployment : QType {
  fun commit(): Stub<Commit> = stub("commit")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun creator(): Stub<Actor> = stub("creator")

  fun environment(): Stub<String> = stub("environment")

  fun id(): Stub<String> = stub("id")

  fun latestStatus(): Stub<DeploymentStatus> = stub("latestStatus")

  fun repository(): Stub<Repository> = stub("repository")

  fun state(): Stub<DeploymentState> = stub("state")

  fun statuses(): Stub<DeploymentStatusConnection> = stub("statuses")

  class StatusesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StatusesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): StatusesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): StatusesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): StatusesArgs {
       addArg("before", value); return this;
    }
  }
}

interface DeployedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun deployment(): Stub<Deployment> = stub("deployment")

  fun id(): Stub<String> = stub("id")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun ref(): Stub<Ref> = stub("ref")
}

interface DemilestonedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun milestoneTitle(): Stub<String> = stub("milestoneTitle")

  fun subject(): Stub<MilestoneItem> = stub("subject")
}

interface DeletePullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun pullRequestReview(): Stub<PullRequestReview> = stub("pullRequestReview")
}

interface DeleteProjectPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun owner(): Stub<ProjectOwner> = stub("owner")
}

interface DeleteProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun deletedColumnId(): Stub<String> = stub("deletedColumnId")

  fun project(): Stub<Project> = stub("project")
}

interface DeleteProjectCardPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun column(): Stub<ProjectColumn> = stub("column")

  fun deletedCardId(): Stub<String> = stub("deletedCardId")
}

interface DeclineTopicSuggestionPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun topic(): Stub<Topic> = stub("topic")
}

interface CreateProjectPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun project(): Stub<Project> = stub("project")
}

interface ConvertedNoteToIssueEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")
}

interface CommitHistoryConnection : QType {
  fun edges(): Stub<CommitEdge> = stub("edges")

  fun nodes(): Stub<Commit> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")
}

interface CommitEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<Commit> = stub("node")
}

interface CommitCommentThread : QType {
  fun comments(): Stub<CommitCommentConnection> = stub("comments")

  fun commit(): Stub<Commit> = stub("commit")

  fun id(): Stub<String> = stub("id")

  fun path(): Stub<String> = stub("path")

  fun position(): Stub<Int> = stub("position")

  fun repository(): Stub<Repository> = stub("repository")

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): CommentsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): CommentsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): CommentsArgs {
       addArg("before", value); return this;
    }
  }
}

interface CommitCommentEdge : QType {
  fun cursor(): Stub<String> = stub("cursor")

  fun node(): Stub<CommitComment> = stub("node")
}

interface CommitCommentConnection : QType {
  fun edges(): Stub<CommitCommentEdge> = stub("edges")

  fun nodes(): Stub<CommitComment> = stub("nodes")

  fun pageInfo(): Stub<PageInfo> = stub("pageInfo")

  fun totalCount(): Stub<Int> = stub("totalCount")
}

interface CommitComment : QType {
  fun author(): Stub<Actor> = stub("author")

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub("authorAssociation")

  fun body(): Stub<String> = stub("body")

  fun bodyHTML(): Stub<HTML> = stub("bodyHTML")

  fun commit(): Stub<Commit> = stub("commit")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun createdViaEmail(): Stub<Boolean> = stub("createdViaEmail")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun editor(): Stub<Actor> = stub("editor")

  fun id(): Stub<String> = stub("id")

  fun lastEditedAt(): Stub<DateTime> = stub("lastEditedAt")

  fun path(): Stub<String> = stub("path")

  fun position(): Stub<Int> = stub("position")

  fun publishedAt(): Stub<DateTime> = stub("publishedAt")

  fun reactionGroups(): Stub<ReactionGroup> = stub("reactionGroups")

  fun reactions(): Stub<ReactionConnection> = stub("reactions")

  fun repository(): Stub<Repository> = stub("repository")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun viewerCanDelete(): Stub<Boolean> = stub("viewerCanDelete")

  fun viewerCanReact(): Stub<Boolean> = stub("viewerCanReact")

  fun viewerCanUpdate(): Stub<Boolean> = stub("viewerCanUpdate")

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub("viewerCannotUpdateReasons")

  fun viewerDidAuthor(): Stub<Boolean> = stub("viewerDidAuthor")

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReactionsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReactionsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReactionsArgs {
       addArg("before", value); return this;
    }

    fun content(value: ReactionContent): ReactionsArgs {
       addArg("content", value); return this;
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
       addArg("orderBy", value); return this;
    }
  }
}

interface Commit : QType {
  fun abbreviatedOid(): Stub<String> = stub("abbreviatedOid")

  fun author(): Stub<GitActor> = stub("author")

  fun authoredByCommitter(): Stub<Boolean> = stub("authoredByCommitter")

  fun blame(): Stub<Blame> = stub("blame")

  fun comments(): Stub<CommitCommentConnection> = stub("comments")

  fun commitResourcePath(): Stub<URI> = stub("commitResourcePath")

  fun commitUrl(): Stub<URI> = stub("commitUrl")

  fun committedDate(): Stub<DateTime> = stub("committedDate")

  fun committedViaWeb(): Stub<Boolean> = stub("committedViaWeb")

  fun committer(): Stub<GitActor> = stub("committer")

  fun history(): Stub<CommitHistoryConnection> = stub("history")

  fun id(): Stub<String> = stub("id")

  fun message(): Stub<String> = stub("message")

  fun messageBody(): Stub<String> = stub("messageBody")

  fun messageBodyHTML(): Stub<HTML> = stub("messageBodyHTML")

  fun messageHeadline(): Stub<String> = stub("messageHeadline")

  fun messageHeadlineHTML(): Stub<HTML> = stub("messageHeadlineHTML")

  fun oid(): Stub<GitObjectID> = stub("oid")

  fun repository(): Stub<Repository> = stub("repository")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun signature(): Stub<GitSignature> = stub("signature")

  fun status(): Stub<Status> = stub("status")

  fun tree(): Stub<Tree> = stub("tree")

  fun treeResourcePath(): Stub<URI> = stub("treeResourcePath")

  fun treeUrl(): Stub<URI> = stub("treeUrl")

  fun url(): Stub<URI> = stub("url")

  fun viewerCanSubscribe(): Stub<Boolean> = stub("viewerCanSubscribe")

  fun viewerSubscription(): Stub<SubscriptionState> = stub("viewerSubscription")

  class BlameArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun path(value: String): BlameArgs {
       addArg("path", value); return this;
    }
  }

  class CommentsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): CommentsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): CommentsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): CommentsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): CommentsArgs {
       addArg("before", value); return this;
    }
  }

  class HistoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): HistoryArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): HistoryArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): HistoryArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): HistoryArgs {
       addArg("before", value); return this;
    }

    fun path(value: String): HistoryArgs {
       addArg("path", value); return this;
    }

    fun author(value: CommitAuthor): HistoryArgs {
       addArg("author", value); return this;
    }

    fun since(value: GitTimestamp): HistoryArgs {
       addArg("since", value); return this;
    }

    fun until(value: GitTimestamp): HistoryArgs {
       addArg("until", value); return this;
    }
  }
}

interface CommentDeletedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")
}

interface CodeOfConduct : QType {
  fun body(): Stub<String> = stub("body")

  fun key(): Stub<String> = stub("key")

  fun name(): Stub<String> = stub("name")

  fun url(): Stub<URI> = stub("url")
}

interface ClosedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun closable(): Stub<Closable> = stub("closable")

  fun commit(): Stub<Commit> = stub("commit")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")
}

interface Bot : QType {
  fun avatarUrl(): Stub<URI> = stub("avatarUrl")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")

  fun login(): Stub<String> = stub("login")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun url(): Stub<URI> = stub("url")

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }
}

interface Blob : QType {
  fun abbreviatedOid(): Stub<String> = stub("abbreviatedOid")

  fun byteSize(): Stub<Int> = stub("byteSize")

  fun commitResourcePath(): Stub<URI> = stub("commitResourcePath")

  fun commitUrl(): Stub<URI> = stub("commitUrl")

  fun id(): Stub<String> = stub("id")

  fun isBinary(): Stub<Boolean> = stub("isBinary")

  fun isTruncated(): Stub<Boolean> = stub("isTruncated")

  fun oid(): Stub<GitObjectID> = stub("oid")

  fun repository(): Stub<Repository> = stub("repository")

  fun text(): Stub<String> = stub("text")
}

interface BlameRange : QType {
  fun age(): Stub<Int> = stub("age")

  fun commit(): Stub<Commit> = stub("commit")

  fun endingLine(): Stub<Int> = stub("endingLine")

  fun startingLine(): Stub<Int> = stub("startingLine")
}

interface Blame : QType {
  fun ranges(): Stub<BlameRange> = stub("ranges")
}

interface BaseRefForcePushedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun afterCommit(): Stub<Commit> = stub("afterCommit")

  fun beforeCommit(): Stub<Commit> = stub("beforeCommit")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun pullRequest(): Stub<PullRequest> = stub("pullRequest")

  fun ref(): Stub<Ref> = stub("ref")
}

interface BaseRefChangedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")
}

interface AssignedEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun assignable(): Stub<Assignable> = stub("assignable")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun id(): Stub<String> = stub("id")

  fun user(): Stub<User> = stub("user")
}

interface AddedToProjectEvent : QType {
  fun actor(): Stub<Actor> = stub("actor")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")
}

interface AddStarPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun starrable(): Stub<Starrable> = stub("starrable")
}

interface AddReactionPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun reaction(): Stub<Reaction> = stub("reaction")

  fun subject(): Stub<Reactable> = stub("subject")
}

interface AddPullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun pullRequestReview(): Stub<PullRequestReview> = stub("pullRequestReview")

  fun reviewEdge(): Stub<PullRequestReviewEdge> = stub("reviewEdge")
}

interface AddPullRequestReviewCommentPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun comment(): Stub<PullRequestReviewComment> = stub("comment")

  fun commentEdge(): Stub<PullRequestReviewCommentEdge> = stub("commentEdge")
}

interface AddProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun columnEdge(): Stub<ProjectColumnEdge> = stub("columnEdge")

  fun project(): Stub<Project> = stub("project")
}

interface AddProjectCardPayload : QType {
  fun cardEdge(): Stub<ProjectCardEdge> = stub("cardEdge")

  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun projectColumn(): Stub<Project> = stub("projectColumn")
}

interface AddCommentPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun commentEdge(): Stub<IssueCommentEdge> = stub("commentEdge")

  fun subject(): Stub<Node> = stub("subject")

  fun timelineEdge(): Stub<IssueTimelineItemEdge> = stub("timelineEdge")
}

interface AcceptTopicSuggestionPayload : QType {
  fun clientMutationId(): Stub<String> = stub("clientMutationId")

  fun topic(): Stub<Topic> = stub("topic")
}

interface UpdatableComment : QType {
  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub("viewerCannotUpdateReasons")
}

interface Updatable : QType {
  fun viewerCanUpdate(): Stub<Boolean> = stub("viewerCanUpdate")
}

interface UniformResourceLocatable : QType {
  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun url(): Stub<URI> = stub("url")
}

interface Subscribable : QType {
  fun viewerCanSubscribe(): Stub<Boolean> = stub("viewerCanSubscribe")

  fun viewerSubscription(): Stub<SubscriptionState> = stub("viewerSubscription")
}

interface Starrable : QType {
  fun id(): Stub<String> = stub("id")

  fun stargazers(): Stub<StargazerConnection> = stub("stargazers")

  fun viewerHasStarred(): Stub<Boolean> = stub("viewerHasStarred")

  class StargazersArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): StargazersArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): StargazersArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): StargazersArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): StargazersArgs {
       addArg("before", value); return this;
    }

    fun orderBy(value: StarOrder): StargazersArgs {
       addArg("orderBy", value); return this;
    }
  }
}

interface RepositoryOwner : QType {
  fun avatarUrl(): Stub<URI> = stub("avatarUrl")

  fun id(): Stub<String> = stub("id")

  fun login(): Stub<String> = stub("login")

  fun pinnedRepositories(): Stub<RepositoryConnection> = stub("pinnedRepositories")

  fun repositories(): Stub<RepositoryConnection> = stub("repositories")

  fun repository(): Stub<Repository> = stub("repository")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun url(): Stub<URI> = stub("url")

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }

  class PinnedRepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): PinnedRepositoriesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): PinnedRepositoriesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): PinnedRepositoriesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): PinnedRepositoriesArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: RepositoryPrivacy): PinnedRepositoriesArgs {
       addArg("privacy", value); return this;
    }

    fun orderBy(value: RepositoryOrder): PinnedRepositoriesArgs {
       addArg("orderBy", value); return this;
    }

    fun affiliations(value: RepositoryAffiliation): PinnedRepositoriesArgs {
       addArg("affiliations", value); return this;
    }

    fun isLocked(value: Boolean): PinnedRepositoriesArgs {
       addArg("isLocked", value); return this;
    }
  }

  class RepositoriesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): RepositoriesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): RepositoriesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): RepositoriesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): RepositoriesArgs {
       addArg("before", value); return this;
    }

    fun privacy(value: RepositoryPrivacy): RepositoriesArgs {
       addArg("privacy", value); return this;
    }

    fun orderBy(value: RepositoryOrder): RepositoriesArgs {
       addArg("orderBy", value); return this;
    }

    fun affiliations(value: RepositoryAffiliation): RepositoriesArgs {
       addArg("affiliations", value); return this;
    }

    fun isLocked(value: Boolean): RepositoriesArgs {
       addArg("isLocked", value); return this;
    }

    fun isFork(value: Boolean): RepositoriesArgs {
       addArg("isFork", value); return this;
    }
  }

  class RepositoryArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): RepositoryArgs {
       addArg("name", value); return this;
    }
  }
}

interface RepositoryNode : QType {
  fun repository(): Stub<Repository> = stub("repository")
}

interface RepositoryInfo : QType {
  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun description(): Stub<String> = stub("description")

  fun descriptionHTML(): Stub<HTML> = stub("descriptionHTML")

  fun hasIssuesEnabled(): Stub<Boolean> = stub("hasIssuesEnabled")

  fun hasWikiEnabled(): Stub<Boolean> = stub("hasWikiEnabled")

  fun homepageUrl(): Stub<URI> = stub("homepageUrl")

  fun isFork(): Stub<Boolean> = stub("isFork")

  fun isLocked(): Stub<Boolean> = stub("isLocked")

  fun isMirror(): Stub<Boolean> = stub("isMirror")

  fun isPrivate(): Stub<Boolean> = stub("isPrivate")

  fun license(): Stub<String> = stub("license")

  fun lockReason(): Stub<RepositoryLockReason> = stub("lockReason")

  fun mirrorUrl(): Stub<URI> = stub("mirrorUrl")

  fun name(): Stub<String> = stub("name")

  fun nameWithOwner(): Stub<String> = stub("nameWithOwner")

  fun owner(): Stub<RepositoryOwner> = stub("owner")

  fun pushedAt(): Stub<DateTime> = stub("pushedAt")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun url(): Stub<URI> = stub("url")
}

interface Reactable : QType {
  fun databaseId(): Stub<Int> = stub("databaseId")

  fun id(): Stub<String> = stub("id")

  fun reactionGroups(): Stub<ReactionGroup> = stub("reactionGroups")

  fun reactions(): Stub<ReactionConnection> = stub("reactions")

  fun viewerCanReact(): Stub<Boolean> = stub("viewerCanReact")

  class ReactionsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ReactionsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ReactionsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ReactionsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ReactionsArgs {
       addArg("before", value); return this;
    }

    fun content(value: ReactionContent): ReactionsArgs {
       addArg("content", value); return this;
    }

    fun orderBy(value: ReactionOrder): ReactionsArgs {
       addArg("orderBy", value); return this;
    }
  }
}

interface ProjectOwner : QType {
  fun id(): Stub<String> = stub("id")

  fun project(): Stub<Project> = stub("project")

  fun projects(): Stub<ProjectConnection> = stub("projects")

  fun projectsResourcePath(): Stub<URI> = stub("projectsResourcePath")

  fun projectsUrl(): Stub<URI> = stub("projectsUrl")

  fun viewerCanCreateProjects(): Stub<Boolean> = stub("viewerCanCreateProjects")

  class ProjectArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun number(value: Int): ProjectArgs {
       addArg("number", value); return this;
    }
  }

  class ProjectsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): ProjectsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): ProjectsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): ProjectsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): ProjectsArgs {
       addArg("before", value); return this;
    }

    fun orderBy(value: ProjectOrder): ProjectsArgs {
       addArg("orderBy", value); return this;
    }

    fun search(value: String): ProjectsArgs {
       addArg("search", value); return this;
    }

    fun states(value: ProjectState): ProjectsArgs {
       addArg("states", value); return this;
    }
  }
}

interface Node : QType {
  fun id(): Stub<String> = stub("id")
}

interface Lockable : QType {
  fun locked(): Stub<Boolean> = stub("locked")
}

interface Labelable : QType {
  fun labels(): Stub<LabelConnection> = stub("labels")

  class LabelsArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): LabelsArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): LabelsArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): LabelsArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): LabelsArgs {
       addArg("before", value); return this;
    }
  }
}

interface GitSignature : QType {
  fun email(): Stub<String> = stub("email")

  fun isValid(): Stub<Boolean> = stub("isValid")

  fun payload(): Stub<String> = stub("payload")

  fun signature(): Stub<String> = stub("signature")

  fun signer(): Stub<User> = stub("signer")

  fun state(): Stub<GitSignatureState> = stub("state")
}

interface GitObject : QType {
  fun abbreviatedOid(): Stub<String> = stub("abbreviatedOid")

  fun commitResourcePath(): Stub<URI> = stub("commitResourcePath")

  fun commitUrl(): Stub<URI> = stub("commitUrl")

  fun id(): Stub<String> = stub("id")

  fun oid(): Stub<GitObjectID> = stub("oid")

  fun repository(): Stub<Repository> = stub("repository")
}

interface Deletable : QType {
  fun viewerCanDelete(): Stub<Boolean> = stub("viewerCanDelete")
}

interface Comment : QType {
  fun author(): Stub<Actor> = stub("author")

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub("authorAssociation")

  fun body(): Stub<String> = stub("body")

  fun bodyHTML(): Stub<HTML> = stub("bodyHTML")

  fun createdAt(): Stub<DateTime> = stub("createdAt")

  fun createdViaEmail(): Stub<Boolean> = stub("createdViaEmail")

  fun editor(): Stub<Actor> = stub("editor")

  fun id(): Stub<String> = stub("id")

  fun lastEditedAt(): Stub<DateTime> = stub("lastEditedAt")

  fun publishedAt(): Stub<DateTime> = stub("publishedAt")

  fun updatedAt(): Stub<DateTime> = stub("updatedAt")

  fun viewerDidAuthor(): Stub<Boolean> = stub("viewerDidAuthor")
}

interface Closable : QType {
  fun closed(): Stub<Boolean> = stub("closed")
}

interface Assignable : QType {
  fun assignees(): Stub<UserConnection> = stub("assignees")

  class AssigneesArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun first(value: Int): AssigneesArgs {
       addArg("first", value); return this;
    }

    fun after(value: String): AssigneesArgs {
       addArg("after", value); return this;
    }

    fun last(value: Int): AssigneesArgs {
       addArg("last", value); return this;
    }

    fun before(value: String): AssigneesArgs {
       addArg("before", value); return this;
    }
  }
}

interface Actor : QType {
  fun avatarUrl(): Stub<URI> = stub("avatarUrl")

  fun login(): Stub<String> = stub("login")

  fun resourcePath(): Stub<URI> = stub("resourcePath")

  fun url(): Stub<URI> = stub("url")

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }
}

interface SearchResultItem : QType {
  fun issue(): Stub<Issue> = stub("issue")

  fun pullrequest(): Stub<PullRequest> = stub("pullrequest")

  fun repository(): Stub<Repository> = stub("repository")

  fun user(): Stub<User> = stub("user")

  fun organization(): Stub<Organization> = stub("organization")
}

interface ReviewDismissalAllowanceActor : QType {
  fun user(): Stub<User> = stub("user")

  fun team(): Stub<Team> = stub("team")
}

interface RenamedTitleSubject : QType {
  fun issue(): Stub<Issue> = stub("issue")

  fun pullrequest(): Stub<PullRequest> = stub("pullrequest")
}

interface ReferencedSubject : QType {
  fun issue(): Stub<Issue> = stub("issue")

  fun pullrequest(): Stub<PullRequest> = stub("pullrequest")
}

interface PushAllowanceActor : QType {
  fun user(): Stub<User> = stub("user")

  fun team(): Stub<Team> = stub("team")
}

interface PullRequestTimelineItem : QType {
  fun commit(): Stub<Commit> = stub("commit")

  fun commitcommentthread(): Stub<CommitCommentThread> = stub("commitcommentthread")

  fun pullrequestreview(): Stub<PullRequestReview> = stub("pullrequestreview")

  fun pullrequestreviewthread(): Stub<PullRequestReviewThread> = stub("pullrequestreviewthread")

  fun pullrequestreviewcomment(): Stub<PullRequestReviewComment> = stub("pullrequestreviewcomment")

  fun issuecomment(): Stub<IssueComment> = stub("issuecomment")

  fun closedevent(): Stub<ClosedEvent> = stub("closedevent")

  fun reopenedevent(): Stub<ReopenedEvent> = stub("reopenedevent")

  fun subscribedevent(): Stub<SubscribedEvent> = stub("subscribedevent")

  fun unsubscribedevent(): Stub<UnsubscribedEvent> = stub("unsubscribedevent")

  fun mergedevent(): Stub<MergedEvent> = stub("mergedevent")

  fun referencedevent(): Stub<ReferencedEvent> = stub("referencedevent")

  fun assignedevent(): Stub<AssignedEvent> = stub("assignedevent")

  fun unassignedevent(): Stub<UnassignedEvent> = stub("unassignedevent")

  fun labeledevent(): Stub<LabeledEvent> = stub("labeledevent")

  fun unlabeledevent(): Stub<UnlabeledEvent> = stub("unlabeledevent")

  fun milestonedevent(): Stub<MilestonedEvent> = stub("milestonedevent")

  fun demilestonedevent(): Stub<DemilestonedEvent> = stub("demilestonedevent")

  fun renamedtitleevent(): Stub<RenamedTitleEvent> = stub("renamedtitleevent")

  fun lockedevent(): Stub<LockedEvent> = stub("lockedevent")

  fun unlockedevent(): Stub<UnlockedEvent> = stub("unlockedevent")

  fun deployedevent(): Stub<DeployedEvent> = stub("deployedevent")

  fun headrefdeletedevent(): Stub<HeadRefDeletedEvent> = stub("headrefdeletedevent")

  fun headrefrestoredevent(): Stub<HeadRefRestoredEvent> = stub("headrefrestoredevent")

  fun headrefforcepushedevent(): Stub<HeadRefForcePushedEvent> = stub("headrefforcepushedevent")

  fun baserefforcepushedevent(): Stub<BaseRefForcePushedEvent> = stub("baserefforcepushedevent")

  fun reviewrequestedevent(): Stub<ReviewRequestedEvent> = stub("reviewrequestedevent")

  fun reviewrequestremovedevent(): Stub<ReviewRequestRemovedEvent> = stub("reviewrequestremovedevent")

  fun reviewdismissedevent(): Stub<ReviewDismissedEvent> = stub("reviewdismissedevent")
}

interface ProjectCardItem : QType {
  fun issue(): Stub<Issue> = stub("issue")

  fun pullrequest(): Stub<PullRequest> = stub("pullrequest")
}

interface MilestoneItem : QType {
  fun issue(): Stub<Issue> = stub("issue")

  fun pullrequest(): Stub<PullRequest> = stub("pullrequest")
}

interface IssueTimelineItem : QType {
  fun commit(): Stub<Commit> = stub("commit")

  fun issuecomment(): Stub<IssueComment> = stub("issuecomment")

  fun closedevent(): Stub<ClosedEvent> = stub("closedevent")

  fun reopenedevent(): Stub<ReopenedEvent> = stub("reopenedevent")

  fun subscribedevent(): Stub<SubscribedEvent> = stub("subscribedevent")

  fun unsubscribedevent(): Stub<UnsubscribedEvent> = stub("unsubscribedevent")

  fun referencedevent(): Stub<ReferencedEvent> = stub("referencedevent")

  fun assignedevent(): Stub<AssignedEvent> = stub("assignedevent")

  fun unassignedevent(): Stub<UnassignedEvent> = stub("unassignedevent")

  fun labeledevent(): Stub<LabeledEvent> = stub("labeledevent")

  fun unlabeledevent(): Stub<UnlabeledEvent> = stub("unlabeledevent")

  fun milestonedevent(): Stub<MilestonedEvent> = stub("milestonedevent")

  fun demilestonedevent(): Stub<DemilestonedEvent> = stub("demilestonedevent")

  fun renamedtitleevent(): Stub<RenamedTitleEvent> = stub("renamedtitleevent")

  fun lockedevent(): Stub<LockedEvent> = stub("lockedevent")

  fun unlockedevent(): Stub<UnlockedEvent> = stub("unlockedevent")
}

interface IssueOrPullRequest : QType {
  fun issue(): Stub<Issue> = stub("issue")

  fun pullrequest(): Stub<PullRequest> = stub("pullrequest")
}

interface X509Certificate : QType {
  fun value(): Stub<String> = stub("value")
}

interface URI : QType {
  fun value(): Stub<String> = stub("value")
}

interface HTML : QType {
  fun value(): Stub<String> = stub("value")
}

interface GitTimestamp : QType {
  fun value(): Stub<String> = stub("value")
}

interface GitObjectID : QType {
  fun value(): Stub<String> = stub("value")
}

interface DateTime : QType {
  fun value(): Stub<String> = stub("value")
}

data class UpdateTopicsInput(val repositoryId: String, val topicNames: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class UpdateSubscriptionInput(val subscribableId: String,
    val state: SubscriptionState) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class UpdatePullRequestReviewInput(val pullRequestReviewId: String,
    val body: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class UpdatePullRequestReviewCommentInput(val pullRequestReviewCommentId: String,
    val body: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class UpdateProjectInput(val projectId: String, val name: String) : QInput {
  var clientMutationId: String? = null

  var body: String? = null

  var state: ProjectState? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}

  fun body(value: String) {
    apply { body = value }}

  fun state(value: ProjectState) {
    apply { state = value }}
}

data class UpdateProjectColumnInput(val projectColumnId: String, val name: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class UpdateProjectCardInput(val projectCardId: String, val note: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class TeamOrder(val field: TeamOrderField, val direction: OrderDirection) : QInput {
}

data class SubmitPullRequestReviewInput(val pullRequestReviewId: String,
    val event: PullRequestReviewEvent) : QInput {
  var clientMutationId: String? = null

  var body: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}

  fun body(value: String) {
    apply { body = value }}
}

data class StarOrder(val field: StarOrderField, val direction: OrderDirection) : QInput {
}

data class RequestReviewsInput(val pullRequestId: String, val userIds: String,
    val teamIds: String) : QInput {
  var clientMutationId: String? = null

  var union: Boolean? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}

  fun union(value: Boolean) {
    apply { union = value }}
}

data class RepositoryOrder(val field: RepositoryOrderField,
    val direction: OrderDirection) : QInput {
}

data class RemoveStarInput(val starrableId: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class RemoveReactionInput(val subjectId: String, val content: ReactionContent) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class RemoveOutsideCollaboratorInput(val userId: String, val organizationId: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class ReactionOrder(val field: ReactionOrderField, val direction: OrderDirection) : QInput {
}

data class ProjectOrder(val field: ProjectOrderField, val direction: OrderDirection) : QInput {
}

data class MoveProjectColumnInput(val columnId: String) : QInput {
  var clientMutationId: String? = null

  var afterColumnId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}

  fun afterColumnId(value: String) {
    apply { afterColumnId = value }}
}

data class MoveProjectCardInput(val cardId: String, val columnId: String) : QInput {
  var clientMutationId: String? = null

  var afterCardId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}

  fun afterCardId(value: String) {
    apply { afterCardId = value }}
}

data class LanguageOrder(val field: LanguageOrderField, val direction: OrderDirection) : QInput {
}

data class IssueOrder(val field: IssueOrderField, val direction: OrderDirection) : QInput {
}

data class DraftPullRequestReviewComment(val path: String, val position: Int,
    val body: String) : QInput {
}

data class DismissPullRequestReviewInput(val pullRequestReviewId: String,
    val message: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class DeletePullRequestReviewInput(val pullRequestReviewId: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class DeleteProjectInput(val projectId: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class DeleteProjectColumnInput(val columnId: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class DeleteProjectCardInput(val cardId: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class DeclineTopicSuggestionInput(val repositoryId: String, val name: String,
    val reason: TopicSuggestionDeclineReason) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class CreateProjectInput(val ownerId: String, val name: String) : QInput {
  var clientMutationId: String? = null

  var body: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}

  fun body(value: String) {
    apply { body = value }}
}

data class CommitAuthor(val emails: String) : QInput {
  var id: String? = null

  fun id(value: String) {
    apply { id = value }}
}

data class AddStarInput(val starrableId: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class AddReactionInput(val subjectId: String, val content: ReactionContent) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class AddPullRequestReviewInput(val pullRequestId: String) : QInput {
  var clientMutationId: String? = null

  var commitOID: GitObjectID? = null

  var body: String? = null

  var event: PullRequestReviewEvent? = null

  var comments: DraftPullRequestReviewComment? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}

  fun commitOID(value: GitObjectID) {
    apply { commitOID = value }}

  fun body(value: String) {
    apply { body = value }}

  fun event(value: PullRequestReviewEvent) {
    apply { event = value }}

  fun comments(value: DraftPullRequestReviewComment) {
    apply { comments = value }}
}

data class AddPullRequestReviewCommentInput(val pullRequestReviewId: String,
    val body: String) : QInput {
  var clientMutationId: String? = null

  var commitOID: GitObjectID? = null

  var path: String? = null

  var position: Int? = null

  var inReplyTo: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}

  fun commitOID(value: GitObjectID) {
    apply { commitOID = value }}

  fun path(value: String) {
    apply { path = value }}

  fun position(value: Int) {
    apply { position = value }}

  fun inReplyTo(value: String) {
    apply { inReplyTo = value }}
}

data class AddProjectColumnInput(val projectId: String, val name: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class AddProjectCardInput(val projectColumnId: String) : QInput {
  var clientMutationId: String? = null

  var contentId: String? = null

  var note: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}

  fun contentId(value: String) {
    apply { contentId = value }}

  fun note(value: String) {
    apply { note = value }}
}

data class AddCommentInput(val subjectId: String, val body: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

data class AcceptTopicSuggestionInput(val repositoryId: String, val name: String) : QInput {
  var clientMutationId: String? = null

  fun clientMutationId(value: String) {
    apply { clientMutationId = value }}
}

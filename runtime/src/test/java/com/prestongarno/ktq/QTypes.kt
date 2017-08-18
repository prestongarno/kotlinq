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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<User> = stub<User>()
}

interface UserConnection : QType {
  fun edges(): Stub<UserEdge> = stub<UserEdge>()

  fun nodes(): Stub<User> = stub<User>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface User : QType {
  fun avatarUrl(): Stub<URI> = stub<URI>()

  fun bio(): Stub<String> = stub<String>()

  fun bioHTML(): Stub<HTML> = stub<HTML>()

  fun company(): Stub<String> = stub<String>()

  fun companyHTML(): Stub<HTML> = stub<HTML>()

  fun contributedRepositories(): Stub<RepositoryConnection> = stub<RepositoryConnection>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun email(): Stub<String> = stub<String>()

  fun followers(): Stub<FollowerConnection> = stub<FollowerConnection>()

  fun following(): Stub<FollowingConnection> = stub<FollowingConnection>()

  fun gist(): Stub<Gist> = stub<Gist>()

  fun gists(): Stub<GistConnection> = stub<GistConnection>()

  fun id(): Stub<String> = stub<String>()

  fun isBountyHunter(): Stub<Boolean> = stub<Boolean>()

  fun isCampusExpert(): Stub<Boolean> = stub<Boolean>()

  fun isDeveloperProgramMember(): Stub<Boolean> = stub<Boolean>()

  fun isEmployee(): Stub<Boolean> = stub<Boolean>()

  fun isHireable(): Stub<Boolean> = stub<Boolean>()

  fun isInvoiced(): Stub<Boolean> = stub<Boolean>()

  fun isSiteAdmin(): Stub<Boolean> = stub<Boolean>()

  fun isViewer(): Stub<Boolean> = stub<Boolean>()

  fun issues(): Stub<IssueConnection> = stub<IssueConnection>()

  fun location(): Stub<String> = stub<String>()

  fun login(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()

  fun organization(): Stub<Organization> = stub<Organization>()

  fun organizations(): Stub<OrganizationConnection> = stub<OrganizationConnection>()

  fun pinnedRepositories(): Stub<RepositoryConnection> = stub<RepositoryConnection>()

  fun pullRequests(): Stub<PullRequestConnection> = stub<PullRequestConnection>()

  fun repositories(): Stub<RepositoryConnection> = stub<RepositoryConnection>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun starredRepositories(): Stub<StarredRepositoryConnection> = stub<StarredRepositoryConnection>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun url(): Stub<URI> = stub<URI>()

  fun viewerCanFollow(): Stub<Boolean> = stub<Boolean>()

  fun viewerIsFollowing(): Stub<Boolean> = stub<Boolean>()

  fun watching(): Stub<RepositoryConnection> = stub<RepositoryConnection>()

  fun websiteUrl(): Stub<URI> = stub<URI>()

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
  fun clientMutationId(): Stub<String> = stub<String>()

  fun invalidTopicNames(): Stub<String> = stub<String>()

  fun repository(): Stub<Repository> = stub<Repository>()
}

interface UpdateSubscriptionPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun subscribable(): Stub<Subscribable> = stub<Subscribable>()
}

interface UpdatePullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun pullRequestReview(): Stub<PullRequestReview> = stub<PullRequestReview>()
}

interface UpdatePullRequestReviewCommentPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun pullRequestReviewComment(): Stub<PullRequestReviewComment> = stub<PullRequestReviewComment>()
}

interface UpdateProjectPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun project(): Stub<Project> = stub<Project>()
}

interface UpdateProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun projectColumn(): Stub<ProjectColumn> = stub<ProjectColumn>()
}

interface UpdateProjectCardPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun projectCard(): Stub<ProjectCard> = stub<ProjectCard>()
}

interface UnsubscribedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun subscribable(): Stub<Subscribable> = stub<Subscribable>()
}

interface UnlockedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun lockable(): Stub<Lockable> = stub<Lockable>()
}

interface UnlabeledEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun label(): Stub<Label> = stub<Label>()

  fun labelable(): Stub<Labelable> = stub<Labelable>()
}

interface UnknownSignature : QType {
  fun email(): Stub<String> = stub<String>()

  fun isValid(): Stub<Boolean> = stub<Boolean>()

  fun payload(): Stub<String> = stub<String>()

  fun signature(): Stub<String> = stub<String>()

  fun signer(): Stub<User> = stub<User>()

  fun state(): Stub<GitSignatureState> = stub<GitSignatureState>()
}

interface UnassignedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun assignable(): Stub<Assignable> = stub<Assignable>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun user(): Stub<User> = stub<User>()
}

interface TreeEntry : QType {
  fun mode(): Stub<Int> = stub<Int>()

  fun name(): Stub<String> = stub<String>()

  fun objectVal(): Stub<GitObject> = stub<GitObject>()

  fun oid(): Stub<GitObjectID> = stub<GitObjectID>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun type(): Stub<String> = stub<String>()
}

interface Tree : QType {
  fun abbreviatedOid(): Stub<String> = stub<String>()

  fun commitResourcePath(): Stub<URI> = stub<URI>()

  fun commitUrl(): Stub<URI> = stub<URI>()

  fun entries(): Stub<TreeEntry> = stub<TreeEntry>()

  fun id(): Stub<String> = stub<String>()

  fun oid(): Stub<GitObjectID> = stub<GitObjectID>()

  fun repository(): Stub<Repository> = stub<Repository>()
}

interface Topic : QType {
  fun id(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()

  fun relatedTopics(): Stub<Topic> = stub<Topic>()
}

interface TeamEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Team> = stub<Team>()
}

interface TeamConnection : QType {
  fun edges(): Stub<TeamEdge> = stub<TeamEdge>()

  fun nodes(): Stub<Team> = stub<Team>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface Team : QType {
  fun description(): Stub<String> = stub<String>()

  fun editTeamResourcePath(): Stub<URI> = stub<URI>()

  fun editTeamUrl(): Stub<URI> = stub<URI>()

  fun id(): Stub<String> = stub<String>()

  fun invitations(): Stub<OrganizationInvitationConnection> = stub<OrganizationInvitationConnection>()

  fun name(): Stub<String> = stub<String>()

  fun organization(): Stub<Organization> = stub<Organization>()

  fun privacy(): Stub<TeamPrivacy> = stub<TeamPrivacy>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun slug(): Stub<String> = stub<String>()

  fun url(): Stub<URI> = stub<URI>()

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
  fun abbreviatedOid(): Stub<String> = stub<String>()

  fun commitResourcePath(): Stub<URI> = stub<URI>()

  fun commitUrl(): Stub<URI> = stub<URI>()

  fun id(): Stub<String> = stub<String>()

  fun message(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()

  fun oid(): Stub<GitObjectID> = stub<GitObjectID>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun tagger(): Stub<GitActor> = stub<GitActor>()

  fun target(): Stub<GitObject> = stub<GitObject>()
}

interface SuggestedReviewer : QType {
  fun isAuthor(): Stub<Boolean> = stub<Boolean>()

  fun isCommenter(): Stub<Boolean> = stub<Boolean>()

  fun reviewer(): Stub<User> = stub<User>()
}

interface SubscribedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun subscribable(): Stub<Subscribable> = stub<Subscribable>()
}

interface SubmitPullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun pullRequestReview(): Stub<PullRequestReview> = stub<PullRequestReview>()
}

interface StatusContext : QType {
  fun commit(): Stub<Commit> = stub<Commit>()

  fun context(): Stub<String> = stub<String>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun creator(): Stub<Actor> = stub<Actor>()

  fun description(): Stub<String> = stub<String>()

  fun id(): Stub<String> = stub<String>()

  fun state(): Stub<StatusState> = stub<StatusState>()

  fun targetUrl(): Stub<URI> = stub<URI>()
}

interface Status : QType {
  fun commit(): Stub<Commit> = stub<Commit>()

  fun context(): Stub<StatusContext> = stub<StatusContext>()

  fun contexts(): Stub<StatusContext> = stub<StatusContext>()

  fun id(): Stub<String> = stub<String>()

  fun state(): Stub<StatusState> = stub<StatusState>()

  class ContextArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): ContextArgs {
       addArg("name", value); return this;
    }
  }
}

interface StarredRepositoryEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Repository> = stub<Repository>()

  fun starredAt(): Stub<DateTime> = stub<DateTime>()
}

interface StarredRepositoryConnection : QType {
  fun edges(): Stub<StarredRepositoryEdge> = stub<StarredRepositoryEdge>()

  fun nodes(): Stub<Repository> = stub<Repository>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface StargazerEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<User> = stub<User>()

  fun starredAt(): Stub<DateTime> = stub<DateTime>()
}

interface StargazerConnection : QType {
  fun edges(): Stub<StargazerEdge> = stub<StargazerEdge>()

  fun nodes(): Stub<User> = stub<User>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface SmimeSignature : QType {
  fun email(): Stub<String> = stub<String>()

  fun isValid(): Stub<Boolean> = stub<Boolean>()

  fun payload(): Stub<String> = stub<String>()

  fun signature(): Stub<String> = stub<String>()

  fun signer(): Stub<User> = stub<User>()

  fun state(): Stub<GitSignatureState> = stub<GitSignatureState>()
}

interface SearchResultItemEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<SearchResultItem> = stub<SearchResultItem>()
}

interface SearchResultItemConnection : QType {
  fun codeCount(): Stub<Int> = stub<Int>()

  fun edges(): Stub<SearchResultItemEdge> = stub<SearchResultItemEdge>()

  fun issueCount(): Stub<Int> = stub<Int>()

  fun nodes(): Stub<SearchResultItem> = stub<SearchResultItem>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun repositoryCount(): Stub<Int> = stub<Int>()

  fun userCount(): Stub<Int> = stub<Int>()

  fun wikiCount(): Stub<Int> = stub<Int>()
}

interface ReviewRequestedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun subject(): Stub<User> = stub<User>()
}

interface ReviewRequestRemovedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun subject(): Stub<User> = stub<User>()
}

interface ReviewRequestEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<ReviewRequest> = stub<ReviewRequest>()
}

interface ReviewRequestConnection : QType {
  fun edges(): Stub<ReviewRequestEdge> = stub<ReviewRequestEdge>()

  fun nodes(): Stub<ReviewRequest> = stub<ReviewRequest>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface ReviewRequest : QType {
  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun reviewer(): Stub<User> = stub<User>()
}

interface ReviewDismissedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()

  fun message(): Stub<String> = stub<String>()

  fun messageHtml(): Stub<HTML> = stub<HTML>()

  fun previousReviewState(): Stub<PullRequestReviewState> = stub<PullRequestReviewState>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun pullRequestCommit(): Stub<PullRequestCommit> = stub<PullRequestCommit>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun review(): Stub<PullRequestReview> = stub<PullRequestReview>()

  fun url(): Stub<URI> = stub<URI>()
}

interface ReviewDismissalAllowanceEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<ReviewDismissalAllowance> = stub<ReviewDismissalAllowance>()
}

interface ReviewDismissalAllowanceConnection : QType {
  fun edges(): Stub<ReviewDismissalAllowanceEdge> = stub<ReviewDismissalAllowanceEdge>()

  fun nodes(): Stub<ReviewDismissalAllowance> = stub<ReviewDismissalAllowance>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface ReviewDismissalAllowance : QType {
  fun actor(): Stub<ReviewDismissalAllowanceActor> = stub<ReviewDismissalAllowanceActor>()

  fun id(): Stub<String> = stub<String>()

  fun protectedBranch(): Stub<ProtectedBranch> = stub<ProtectedBranch>()
}

interface RequestReviewsPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun requestedReviewersEdge(): Stub<UserEdge> = stub<UserEdge>()
}

interface RepositoryTopicEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<RepositoryTopic> = stub<RepositoryTopic>()
}

interface RepositoryTopicConnection : QType {
  fun edges(): Stub<RepositoryTopicEdge> = stub<RepositoryTopicEdge>()

  fun nodes(): Stub<RepositoryTopic> = stub<RepositoryTopic>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface RepositoryTopic : QType {
  fun id(): Stub<String> = stub<String>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun topic(): Stub<Topic> = stub<Topic>()

  fun url(): Stub<URI> = stub<URI>()
}

interface RepositoryInvitationRepository : QType {
  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun description(): Stub<String> = stub<String>()

  fun descriptionHTML(): Stub<HTML> = stub<HTML>()

  fun hasIssuesEnabled(): Stub<Boolean> = stub<Boolean>()

  fun hasWikiEnabled(): Stub<Boolean> = stub<Boolean>()

  fun homepageUrl(): Stub<URI> = stub<URI>()

  fun isFork(): Stub<Boolean> = stub<Boolean>()

  fun isLocked(): Stub<Boolean> = stub<Boolean>()

  fun isMirror(): Stub<Boolean> = stub<Boolean>()

  fun isPrivate(): Stub<Boolean> = stub<Boolean>()

  fun license(): Stub<String> = stub<String>()

  fun lockReason(): Stub<RepositoryLockReason> = stub<RepositoryLockReason>()

  fun mirrorUrl(): Stub<URI> = stub<URI>()

  fun name(): Stub<String> = stub<String>()

  fun nameWithOwner(): Stub<String> = stub<String>()

  fun owner(): Stub<RepositoryOwner> = stub<RepositoryOwner>()

  fun pushedAt(): Stub<DateTime> = stub<DateTime>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun url(): Stub<URI> = stub<URI>()
}

interface RepositoryInvitation : QType {
  fun id(): Stub<String> = stub<String>()

  fun invitee(): Stub<User> = stub<User>()

  fun inviter(): Stub<User> = stub<User>()

  fun repository(): Stub<RepositoryInvitationRepository> = stub<RepositoryInvitationRepository>()
}

interface RepositoryEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Repository> = stub<Repository>()
}

interface RepositoryConnection : QType {
  fun edges(): Stub<RepositoryEdge> = stub<RepositoryEdge>()

  fun nodes(): Stub<Repository> = stub<Repository>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()

  fun totalDiskUsage(): Stub<Int> = stub<Int>()
}

interface Repository : QType {
  fun codeOfConduct(): Stub<CodeOfConduct> = stub<CodeOfConduct>()

  fun commitComments(): Stub<CommitCommentConnection> = stub<CommitCommentConnection>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun defaultBranchRef(): Stub<Ref> = stub<Ref>()

  fun deployments(): Stub<DeploymentConnection> = stub<DeploymentConnection>()

  fun description(): Stub<String> = stub<String>()

  fun descriptionHTML(): Stub<HTML> = stub<HTML>()

  fun diskUsage(): Stub<Int> = stub<Int>()

  fun forks(): Stub<RepositoryConnection> = stub<RepositoryConnection>()

  fun hasIssuesEnabled(): Stub<Boolean> = stub<Boolean>()

  fun hasWikiEnabled(): Stub<Boolean> = stub<Boolean>()

  fun homepageUrl(): Stub<URI> = stub<URI>()

  fun id(): Stub<String> = stub<String>()

  fun isFork(): Stub<Boolean> = stub<Boolean>()

  fun isLocked(): Stub<Boolean> = stub<Boolean>()

  fun isMirror(): Stub<Boolean> = stub<Boolean>()

  fun isPrivate(): Stub<Boolean> = stub<Boolean>()

  fun issue(): Stub<Issue> = stub<Issue>()

  fun issueOrPullRequest(): Stub<IssueOrPullRequest> = stub<IssueOrPullRequest>()

  fun issues(): Stub<IssueConnection> = stub<IssueConnection>()

  fun label(): Stub<Label> = stub<Label>()

  fun labels(): Stub<LabelConnection> = stub<LabelConnection>()

  fun languages(): Stub<LanguageConnection> = stub<LanguageConnection>()

  fun license(): Stub<String> = stub<String>()

  fun lockReason(): Stub<RepositoryLockReason> = stub<RepositoryLockReason>()

  fun mentionableUsers(): Stub<UserConnection> = stub<UserConnection>()

  fun milestone(): Stub<Milestone> = stub<Milestone>()

  fun milestones(): Stub<MilestoneConnection> = stub<MilestoneConnection>()

  fun mirrorUrl(): Stub<URI> = stub<URI>()

  fun name(): Stub<String> = stub<String>()

  fun nameWithOwner(): Stub<String> = stub<String>()

  fun objectVal(): Stub<GitObject> = stub<GitObject>()

  fun owner(): Stub<RepositoryOwner> = stub<RepositoryOwner>()

  fun parent(): Stub<Repository> = stub<Repository>()

  fun primaryLanguage(): Stub<Language> = stub<Language>()

  fun project(): Stub<Project> = stub<Project>()

  fun projects(): Stub<ProjectConnection> = stub<ProjectConnection>()

  fun projectsResourcePath(): Stub<URI> = stub<URI>()

  fun projectsUrl(): Stub<URI> = stub<URI>()

  fun protectedBranches(): Stub<ProtectedBranchConnection> = stub<ProtectedBranchConnection>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun pullRequests(): Stub<PullRequestConnection> = stub<PullRequestConnection>()

  fun pushedAt(): Stub<DateTime> = stub<DateTime>()

  fun ref(): Stub<Ref> = stub<Ref>()

  fun refs(): Stub<RefConnection> = stub<RefConnection>()

  fun releases(): Stub<ReleaseConnection> = stub<ReleaseConnection>()

  fun repositoryTopics(): Stub<RepositoryTopicConnection> = stub<RepositoryTopicConnection>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun stargazers(): Stub<StargazerConnection> = stub<StargazerConnection>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun url(): Stub<URI> = stub<URI>()

  fun viewerCanAdminister(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanCreateProjects(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanSubscribe(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanUpdateTopics(): Stub<Boolean> = stub<Boolean>()

  fun viewerHasStarred(): Stub<Boolean> = stub<Boolean>()

  fun viewerSubscription(): Stub<SubscriptionState> = stub<SubscriptionState>()

  fun watchers(): Stub<UserConnection> = stub<UserConnection>()

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
  fun actor(): Stub<Actor> = stub<Actor>()

  fun closable(): Stub<Closable> = stub<Closable>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()
}

interface RenamedTitleEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun currentTitle(): Stub<String> = stub<String>()

  fun id(): Stub<String> = stub<String>()

  fun previousTitle(): Stub<String> = stub<String>()

  fun subject(): Stub<RenamedTitleSubject> = stub<RenamedTitleSubject>()
}

interface RemovedFromProjectEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()
}

interface RemoveStarPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun starrable(): Stub<Starrable> = stub<Starrable>()
}

interface RemoveReactionPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun reaction(): Stub<Reaction> = stub<Reaction>()

  fun subject(): Stub<Reactable> = stub<Reactable>()
}

interface RemoveOutsideCollaboratorPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun removedUser(): Stub<User> = stub<User>()
}

interface ReleaseEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Release> = stub<Release>()
}

interface ReleaseConnection : QType {
  fun edges(): Stub<ReleaseEdge> = stub<ReleaseEdge>()

  fun nodes(): Stub<Release> = stub<Release>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface ReleaseAssetEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<ReleaseAsset> = stub<ReleaseAsset>()
}

interface ReleaseAssetConnection : QType {
  fun edges(): Stub<ReleaseAssetEdge> = stub<ReleaseAssetEdge>()

  fun nodes(): Stub<ReleaseAsset> = stub<ReleaseAsset>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface ReleaseAsset : QType {
  fun id(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()

  fun release(): Stub<Release> = stub<Release>()

  fun url(): Stub<URI> = stub<URI>()
}

interface Release : QType {
  fun description(): Stub<String> = stub<String>()

  fun id(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()

  fun publishedAt(): Stub<DateTime> = stub<DateTime>()

  fun releaseAsset(): Stub<ReleaseAssetConnection> = stub<ReleaseAssetConnection>()

  fun releaseAssets(): Stub<ReleaseAssetConnection> = stub<ReleaseAssetConnection>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun tag(): Stub<Ref> = stub<Ref>()

  fun url(): Stub<URI> = stub<URI>()

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
  fun actor(): Stub<Actor> = stub<Actor>()

  fun commit(): Stub<Commit> = stub<Commit>()

  fun commitRepository(): Stub<Repository> = stub<Repository>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun isCrossReference(): Stub<Boolean> = stub<Boolean>()

  fun isCrossRepository(): Stub<Boolean> = stub<Boolean>()

  fun isDirectReference(): Stub<Boolean> = stub<Boolean>()

  fun subject(): Stub<ReferencedSubject> = stub<ReferencedSubject>()
}

interface RefEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Ref> = stub<Ref>()
}

interface RefConnection : QType {
  fun edges(): Stub<RefEdge> = stub<RefEdge>()

  fun nodes(): Stub<Ref> = stub<Ref>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface Ref : QType {
  fun associatedPullRequests(): Stub<PullRequestConnection> = stub<PullRequestConnection>()

  fun id(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()

  fun prefix(): Stub<String> = stub<String>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun target(): Stub<GitObject> = stub<GitObject>()

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
  fun content(): Stub<ReactionContent> = stub<ReactionContent>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun subject(): Stub<Reactable> = stub<Reactable>()

  fun users(): Stub<ReactingUserConnection> = stub<ReactingUserConnection>()

  fun viewerHasReacted(): Stub<Boolean> = stub<Boolean>()

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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Reaction> = stub<Reaction>()
}

interface ReactionConnection : QType {
  fun edges(): Stub<ReactionEdge> = stub<ReactionEdge>()

  fun nodes(): Stub<Reaction> = stub<Reaction>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()

  fun viewerHasReacted(): Stub<Boolean> = stub<Boolean>()
}

interface Reaction : QType {
  fun content(): Stub<ReactionContent> = stub<ReactionContent>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()

  fun user(): Stub<User> = stub<User>()
}

interface ReactingUserEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<User> = stub<User>()

  fun reactedAt(): Stub<DateTime> = stub<DateTime>()
}

interface ReactingUserConnection : QType {
  fun edges(): Stub<ReactingUserEdge> = stub<ReactingUserEdge>()

  fun nodes(): Stub<User> = stub<User>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface RateLimit : QType {
  fun cost(): Stub<Int> = stub<Int>()

  fun limit(): Stub<Int> = stub<Int>()

  fun remaining(): Stub<Int> = stub<Int>()

  fun resetAt(): Stub<DateTime> = stub<DateTime>()
}

interface Query : QType {
  fun codeOfConduct(): Stub<CodeOfConduct> = stub<CodeOfConduct>()

  fun codesOfConduct(): Stub<CodeOfConduct> = stub<CodeOfConduct>()

  fun node(): Stub<Node> = stub<Node>()

  fun nodes(): Stub<Node> = stub<Node>()

  fun organization(): Stub<Organization> = stub<Organization>()

  fun rateLimit(): Stub<RateLimit> = stub<RateLimit>()

  fun relay(): Stub<Query> = stub<Query>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun repositoryOwner(): Stub<RepositoryOwner> = stub<RepositoryOwner>()

  fun resource(): Stub<UniformResourceLocatable> = stub<UniformResourceLocatable>()

  fun search(): Stub<SearchResultItemConnection> = stub<SearchResultItemConnection>()

  fun topic(): Stub<Topic> = stub<Topic>()

  fun user(): Stub<User> = stub<User>()

  fun viewer(): Stub<User> = stub<User>()

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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<PushAllowance> = stub<PushAllowance>()
}

interface PushAllowanceConnection : QType {
  fun edges(): Stub<PushAllowanceEdge> = stub<PushAllowanceEdge>()

  fun nodes(): Stub<PushAllowance> = stub<PushAllowance>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface PushAllowance : QType {
  fun actor(): Stub<PushAllowanceActor> = stub<PushAllowanceActor>()

  fun id(): Stub<String> = stub<String>()

  fun protectedBranch(): Stub<ProtectedBranch> = stub<ProtectedBranch>()
}

interface PullRequestTimelineItemEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<PullRequestTimelineItem> = stub<PullRequestTimelineItem>()
}

interface PullRequestTimelineConnection : QType {
  fun edges(): Stub<PullRequestTimelineItemEdge> = stub<PullRequestTimelineItemEdge>()

  fun nodes(): Stub<PullRequestTimelineItem> = stub<PullRequestTimelineItem>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface PullRequestReviewThread : QType {
  fun comments(): Stub<PullRequestReviewCommentConnection> = stub<PullRequestReviewCommentConnection>()

  fun id(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<PullRequestReview> = stub<PullRequestReview>()
}

interface PullRequestReviewConnection : QType {
  fun edges(): Stub<PullRequestReviewEdge> = stub<PullRequestReviewEdge>()

  fun nodes(): Stub<PullRequestReview> = stub<PullRequestReview>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface PullRequestReviewCommentEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<PullRequestReviewComment> = stub<PullRequestReviewComment>()
}

interface PullRequestReviewCommentConnection : QType {
  fun edges(): Stub<PullRequestReviewCommentEdge> = stub<PullRequestReviewCommentEdge>()

  fun nodes(): Stub<PullRequestReviewComment> = stub<PullRequestReviewComment>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface PullRequestReviewComment : QType {
  fun author(): Stub<Actor> = stub<Actor>()

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub<CommentAuthorAssociation>()

  fun body(): Stub<String> = stub<String>()

  fun bodyHTML(): Stub<HTML> = stub<HTML>()

  fun bodyText(): Stub<String> = stub<String>()

  fun commit(): Stub<Commit> = stub<Commit>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun createdViaEmail(): Stub<Boolean> = stub<Boolean>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun diffHunk(): Stub<String> = stub<String>()

  fun draftedAt(): Stub<DateTime> = stub<DateTime>()

  fun editor(): Stub<Actor> = stub<Actor>()

  fun id(): Stub<String> = stub<String>()

  fun lastEditedAt(): Stub<DateTime> = stub<DateTime>()

  fun originalCommit(): Stub<Commit> = stub<Commit>()

  fun originalPosition(): Stub<Int> = stub<Int>()

  fun path(): Stub<String> = stub<String>()

  fun position(): Stub<Int> = stub<Int>()

  fun publishedAt(): Stub<DateTime> = stub<DateTime>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun pullRequestReview(): Stub<PullRequestReview> = stub<PullRequestReview>()

  fun reactionGroups(): Stub<ReactionGroup> = stub<ReactionGroup>()

  fun reactions(): Stub<ReactionConnection> = stub<ReactionConnection>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun url(): Stub<URI> = stub<URI>()

  fun viewerCanDelete(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanReact(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanUpdate(): Stub<Boolean> = stub<Boolean>()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub<CommentCannotUpdateReason>()

  fun viewerDidAuthor(): Stub<Boolean> = stub<Boolean>()

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
  fun author(): Stub<Actor> = stub<Actor>()

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub<CommentAuthorAssociation>()

  fun body(): Stub<String> = stub<String>()

  fun bodyHTML(): Stub<HTML> = stub<HTML>()

  fun bodyText(): Stub<String> = stub<String>()

  fun comments(): Stub<PullRequestReviewCommentConnection> = stub<PullRequestReviewCommentConnection>()

  fun commit(): Stub<Commit> = stub<Commit>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun createdViaEmail(): Stub<Boolean> = stub<Boolean>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun editor(): Stub<Actor> = stub<Actor>()

  fun id(): Stub<String> = stub<String>()

  fun lastEditedAt(): Stub<DateTime> = stub<DateTime>()

  fun publishedAt(): Stub<DateTime> = stub<DateTime>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun state(): Stub<PullRequestReviewState> = stub<PullRequestReviewState>()

  fun submittedAt(): Stub<DateTime> = stub<DateTime>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun url(): Stub<URI> = stub<URI>()

  fun viewerCanDelete(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanUpdate(): Stub<Boolean> = stub<Boolean>()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub<CommentCannotUpdateReason>()

  fun viewerDidAuthor(): Stub<Boolean> = stub<Boolean>()

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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<PullRequest> = stub<PullRequest>()
}

interface PullRequestConnection : QType {
  fun edges(): Stub<PullRequestEdge> = stub<PullRequestEdge>()

  fun nodes(): Stub<PullRequest> = stub<PullRequest>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface PullRequestCommitEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<PullRequestCommit> = stub<PullRequestCommit>()
}

interface PullRequestCommitConnection : QType {
  fun edges(): Stub<PullRequestCommitEdge> = stub<PullRequestCommitEdge>()

  fun nodes(): Stub<PullRequestCommit> = stub<PullRequestCommit>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface PullRequestCommit : QType {
  fun commit(): Stub<Commit> = stub<Commit>()

  fun id(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun url(): Stub<URI> = stub<URI>()
}

interface PullRequest : QType {
  fun assignees(): Stub<UserConnection> = stub<UserConnection>()

  fun author(): Stub<Actor> = stub<Actor>()

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub<CommentAuthorAssociation>()

  fun baseRef(): Stub<Ref> = stub<Ref>()

  fun baseRefName(): Stub<String> = stub<String>()

  fun body(): Stub<String> = stub<String>()

  fun bodyHTML(): Stub<HTML> = stub<HTML>()

  fun bodyText(): Stub<String> = stub<String>()

  fun closed(): Stub<Boolean> = stub<Boolean>()

  fun comments(): Stub<IssueCommentConnection> = stub<IssueCommentConnection>()

  fun commits(): Stub<PullRequestCommitConnection> = stub<PullRequestCommitConnection>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun createdViaEmail(): Stub<Boolean> = stub<Boolean>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun editor(): Stub<Actor> = stub<Actor>()

  fun headRef(): Stub<Ref> = stub<Ref>()

  fun headRefName(): Stub<String> = stub<String>()

  fun headRepository(): Stub<Repository> = stub<Repository>()

  fun headRepositoryOwner(): Stub<RepositoryOwner> = stub<RepositoryOwner>()

  fun id(): Stub<String> = stub<String>()

  fun isCrossRepository(): Stub<Boolean> = stub<Boolean>()

  fun labels(): Stub<LabelConnection> = stub<LabelConnection>()

  fun lastEditedAt(): Stub<DateTime> = stub<DateTime>()

  fun locked(): Stub<Boolean> = stub<Boolean>()

  fun mergeCommit(): Stub<Commit> = stub<Commit>()

  fun mergeable(): Stub<MergeableState> = stub<MergeableState>()

  fun merged(): Stub<Boolean> = stub<Boolean>()

  fun mergedAt(): Stub<DateTime> = stub<DateTime>()

  fun number(): Stub<Int> = stub<Int>()

  fun participants(): Stub<UserConnection> = stub<UserConnection>()

  fun potentialMergeCommit(): Stub<Commit> = stub<Commit>()

  fun publishedAt(): Stub<DateTime> = stub<DateTime>()

  fun reactionGroups(): Stub<ReactionGroup> = stub<ReactionGroup>()

  fun reactions(): Stub<ReactionConnection> = stub<ReactionConnection>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun revertResourcePath(): Stub<URI> = stub<URI>()

  fun revertUrl(): Stub<URI> = stub<URI>()

  fun reviewRequests(): Stub<ReviewRequestConnection> = stub<ReviewRequestConnection>()

  fun reviews(): Stub<PullRequestReviewConnection> = stub<PullRequestReviewConnection>()

  fun state(): Stub<PullRequestState> = stub<PullRequestState>()

  fun suggestedReviewers(): Stub<SuggestedReviewer> = stub<SuggestedReviewer>()

  fun timeline(): Stub<PullRequestTimelineConnection> = stub<PullRequestTimelineConnection>()

  fun title(): Stub<String> = stub<String>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun url(): Stub<URI> = stub<URI>()

  fun viewerCanReact(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanSubscribe(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanUpdate(): Stub<Boolean> = stub<Boolean>()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub<CommentCannotUpdateReason>()

  fun viewerDidAuthor(): Stub<Boolean> = stub<Boolean>()

  fun viewerSubscription(): Stub<SubscriptionState> = stub<SubscriptionState>()

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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<ProtectedBranch> = stub<ProtectedBranch>()
}

interface ProtectedBranchConnection : QType {
  fun edges(): Stub<ProtectedBranchEdge> = stub<ProtectedBranchEdge>()

  fun nodes(): Stub<ProtectedBranch> = stub<ProtectedBranch>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface ProtectedBranch : QType {
  fun creator(): Stub<Actor> = stub<Actor>()

  fun hasDismissableStaleReviews(): Stub<Boolean> = stub<Boolean>()

  fun hasRequiredReviews(): Stub<Boolean> = stub<Boolean>()

  fun hasRequiredStatusChecks(): Stub<Boolean> = stub<Boolean>()

  fun hasRestrictedPushes(): Stub<Boolean> = stub<Boolean>()

  fun hasRestrictedReviewDismissals(): Stub<Boolean> = stub<Boolean>()

  fun hasStrictRequiredStatusChecks(): Stub<Boolean> = stub<Boolean>()

  fun id(): Stub<String> = stub<String>()

  fun isAdminEnforced(): Stub<Boolean> = stub<Boolean>()

  fun name(): Stub<String> = stub<String>()

  fun pushAllowances(): Stub<PushAllowanceConnection> = stub<PushAllowanceConnection>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun requiredStatusCheckContexts(): Stub<String> = stub<String>()

  fun reviewDismissalAllowances(): Stub<ReviewDismissalAllowanceConnection> = stub<ReviewDismissalAllowanceConnection>()

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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Project> = stub<Project>()
}

interface ProjectConnection : QType {
  fun edges(): Stub<ProjectEdge> = stub<ProjectEdge>()

  fun nodes(): Stub<Project> = stub<Project>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface ProjectColumnEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<ProjectColumn> = stub<ProjectColumn>()
}

interface ProjectColumnConnection : QType {
  fun edges(): Stub<ProjectColumnEdge> = stub<ProjectColumnEdge>()

  fun nodes(): Stub<ProjectColumn> = stub<ProjectColumn>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface ProjectColumn : QType {
  fun cards(): Stub<ProjectCardConnection> = stub<ProjectCardConnection>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()

  fun project(): Stub<Project> = stub<Project>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<ProjectCard> = stub<ProjectCard>()
}

interface ProjectCardConnection : QType {
  fun edges(): Stub<ProjectCardEdge> = stub<ProjectCardEdge>()

  fun nodes(): Stub<ProjectCard> = stub<ProjectCard>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface ProjectCard : QType {
  fun column(): Stub<ProjectColumn> = stub<ProjectColumn>()

  fun content(): Stub<ProjectCardItem> = stub<ProjectCardItem>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun creator(): Stub<Actor> = stub<Actor>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()

  fun note(): Stub<String> = stub<String>()

  fun project(): Stub<Project> = stub<Project>()

  fun projectColumn(): Stub<ProjectColumn> = stub<ProjectColumn>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun state(): Stub<ProjectCardState> = stub<ProjectCardState>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun url(): Stub<URI> = stub<URI>()
}

interface Project : QType {
  fun body(): Stub<String> = stub<String>()

  fun bodyHTML(): Stub<HTML> = stub<HTML>()

  fun closedAt(): Stub<DateTime> = stub<DateTime>()

  fun columns(): Stub<ProjectColumnConnection> = stub<ProjectColumnConnection>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun creator(): Stub<Actor> = stub<Actor>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()

  fun number(): Stub<Int> = stub<Int>()

  fun owner(): Stub<ProjectOwner> = stub<ProjectOwner>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun state(): Stub<ProjectState> = stub<ProjectState>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun url(): Stub<URI> = stub<URI>()

  fun viewerCanUpdate(): Stub<Boolean> = stub<Boolean>()

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
  fun endCursor(): Stub<String> = stub<String>()

  fun hasNextPage(): Stub<Boolean> = stub<Boolean>()

  fun hasPreviousPage(): Stub<Boolean> = stub<Boolean>()

  fun startCursor(): Stub<String> = stub<String>()
}

interface OrganizationInvitationEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<OrganizationInvitation> = stub<OrganizationInvitation>()
}

interface OrganizationInvitationConnection : QType {
  fun edges(): Stub<OrganizationInvitationEdge> = stub<OrganizationInvitationEdge>()

  fun nodes(): Stub<OrganizationInvitation> = stub<OrganizationInvitation>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface OrganizationInvitation : QType {
  fun email(): Stub<String> = stub<String>()

  fun id(): Stub<String> = stub<String>()

  fun invitee(): Stub<User> = stub<User>()

  fun inviter(): Stub<User> = stub<User>()

  fun role(): Stub<OrganizationInvitationRole> = stub<OrganizationInvitationRole>()
}

interface OrganizationIdentityProvider : QType {
  fun digestMethod(): Stub<URI> = stub<URI>()

  fun externalIdentities(): Stub<ExternalIdentityConnection> = stub<ExternalIdentityConnection>()

  fun id(): Stub<String> = stub<String>()

  fun idpCertificate(): Stub<X509Certificate> = stub<X509Certificate>()

  fun issuer(): Stub<String> = stub<String>()

  fun organization(): Stub<Organization> = stub<Organization>()

  fun signatureMethod(): Stub<URI> = stub<URI>()

  fun ssoUrl(): Stub<URI> = stub<URI>()

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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Organization> = stub<Organization>()
}

interface OrganizationConnection : QType {
  fun edges(): Stub<OrganizationEdge> = stub<OrganizationEdge>()

  fun nodes(): Stub<Organization> = stub<Organization>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface Organization : QType {
  fun avatarUrl(): Stub<URI> = stub<URI>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()

  fun isInvoiced(): Stub<Boolean> = stub<Boolean>()

  fun login(): Stub<String> = stub<String>()

  fun members(): Stub<UserConnection> = stub<UserConnection>()

  fun name(): Stub<String> = stub<String>()

  fun newTeamResourcePath(): Stub<URI> = stub<URI>()

  fun newTeamUrl(): Stub<URI> = stub<URI>()

  fun organizationBillingEmail(): Stub<String> = stub<String>()

  fun pinnedRepositories(): Stub<RepositoryConnection> = stub<RepositoryConnection>()

  fun project(): Stub<Project> = stub<Project>()

  fun projects(): Stub<ProjectConnection> = stub<ProjectConnection>()

  fun projectsResourcePath(): Stub<URI> = stub<URI>()

  fun projectsUrl(): Stub<URI> = stub<URI>()

  fun repositories(): Stub<RepositoryConnection> = stub<RepositoryConnection>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun samlIdentityProvider(): Stub<OrganizationIdentityProvider> = stub<OrganizationIdentityProvider>()

  fun team(): Stub<Team> = stub<Team>()

  fun teams(): Stub<TeamConnection> = stub<TeamConnection>()

  fun teamsResourcePath(): Stub<URI> = stub<URI>()

  fun teamsUrl(): Stub<URI> = stub<URI>()

  fun url(): Stub<URI> = stub<URI>()

  fun viewerCanAdminister(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanCreateProjects(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanCreateRepositories(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanCreateTeams(): Stub<Boolean> = stub<Boolean>()

  fun viewerIsAMember(): Stub<Boolean> = stub<Boolean>()

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
  fun acceptTopicSuggestion(): Stub<AcceptTopicSuggestionPayload> = stub<AcceptTopicSuggestionPayload>()

  fun addComment(): Stub<AddCommentPayload> = stub<AddCommentPayload>()

  fun addProjectCard(): Stub<AddProjectCardPayload> = stub<AddProjectCardPayload>()

  fun addProjectColumn(): Stub<AddProjectColumnPayload> = stub<AddProjectColumnPayload>()

  fun addPullRequestReview(): Stub<AddPullRequestReviewPayload> = stub<AddPullRequestReviewPayload>()

  fun addPullRequestReviewComment(): Stub<AddPullRequestReviewCommentPayload> = stub<AddPullRequestReviewCommentPayload>()

  fun addReaction(): Stub<AddReactionPayload> = stub<AddReactionPayload>()

  fun addStar(): Stub<AddStarPayload> = stub<AddStarPayload>()

  fun createProject(): Stub<CreateProjectPayload> = stub<CreateProjectPayload>()

  fun declineTopicSuggestion(): Stub<DeclineTopicSuggestionPayload> = stub<DeclineTopicSuggestionPayload>()

  fun deleteProject(): Stub<DeleteProjectPayload> = stub<DeleteProjectPayload>()

  fun deleteProjectCard(): Stub<DeleteProjectCardPayload> = stub<DeleteProjectCardPayload>()

  fun deleteProjectColumn(): Stub<DeleteProjectColumnPayload> = stub<DeleteProjectColumnPayload>()

  fun deletePullRequestReview(): Stub<DeletePullRequestReviewPayload> = stub<DeletePullRequestReviewPayload>()

  fun dismissPullRequestReview(): Stub<DismissPullRequestReviewPayload> = stub<DismissPullRequestReviewPayload>()

  fun moveProjectCard(): Stub<MoveProjectCardPayload> = stub<MoveProjectCardPayload>()

  fun moveProjectColumn(): Stub<MoveProjectColumnPayload> = stub<MoveProjectColumnPayload>()

  fun removeOutsideCollaborator(): Stub<RemoveOutsideCollaboratorPayload> = stub<RemoveOutsideCollaboratorPayload>()

  fun removeReaction(): Stub<RemoveReactionPayload> = stub<RemoveReactionPayload>()

  fun removeStar(): Stub<RemoveStarPayload> = stub<RemoveStarPayload>()

  fun requestReviews(): Stub<RequestReviewsPayload> = stub<RequestReviewsPayload>()

  fun submitPullRequestReview(): Stub<SubmitPullRequestReviewPayload> = stub<SubmitPullRequestReviewPayload>()

  fun updateProject(): Stub<UpdateProjectPayload> = stub<UpdateProjectPayload>()

  fun updateProjectCard(): Stub<UpdateProjectCardPayload> = stub<UpdateProjectCardPayload>()

  fun updateProjectColumn(): Stub<UpdateProjectColumnPayload> = stub<UpdateProjectColumnPayload>()

  fun updatePullRequestReview(): Stub<UpdatePullRequestReviewPayload> = stub<UpdatePullRequestReviewPayload>()

  fun updatePullRequestReviewComment(): Stub<UpdatePullRequestReviewCommentPayload> = stub<UpdatePullRequestReviewCommentPayload>()

  fun updateSubscription(): Stub<UpdateSubscriptionPayload> = stub<UpdateSubscriptionPayload>()

  fun updateTopics(): Stub<UpdateTopicsPayload> = stub<UpdateTopicsPayload>()

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
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()
}

interface MoveProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun columnEdge(): Stub<ProjectColumnEdge> = stub<ProjectColumnEdge>()
}

interface MoveProjectCardPayload : QType {
  fun cardEdge(): Stub<ProjectCardEdge> = stub<ProjectCardEdge>()

  fun clientMutationId(): Stub<String> = stub<String>()
}

interface MilestonedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun milestoneTitle(): Stub<String> = stub<String>()

  fun subject(): Stub<MilestoneItem> = stub<MilestoneItem>()
}

interface MilestoneEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Milestone> = stub<Milestone>()
}

interface MilestoneConnection : QType {
  fun edges(): Stub<MilestoneEdge> = stub<MilestoneEdge>()

  fun nodes(): Stub<Milestone> = stub<Milestone>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface Milestone : QType {
  fun creator(): Stub<Actor> = stub<Actor>()

  fun description(): Stub<String> = stub<String>()

  fun dueOn(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun number(): Stub<Int> = stub<Int>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun state(): Stub<MilestoneState> = stub<MilestoneState>()

  fun title(): Stub<String> = stub<String>()

  fun url(): Stub<URI> = stub<URI>()
}

interface MergedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun commit(): Stub<Commit> = stub<Commit>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun mergeRef(): Stub<Ref> = stub<Ref>()

  fun mergeRefName(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun url(): Stub<URI> = stub<URI>()
}

interface MentionedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()
}

interface LockedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun lockable(): Stub<Lockable> = stub<Lockable>()
}

interface LanguageEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Language> = stub<Language>()

  fun size(): Stub<Int> = stub<Int>()
}

interface LanguageConnection : QType {
  fun edges(): Stub<LanguageEdge> = stub<LanguageEdge>()

  fun nodes(): Stub<Language> = stub<Language>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()

  fun totalSize(): Stub<Int> = stub<Int>()
}

interface Language : QType {
  fun color(): Stub<String> = stub<String>()

  fun id(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()
}

interface LabeledEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun label(): Stub<Label> = stub<Label>()

  fun labelable(): Stub<Labelable> = stub<Labelable>()
}

interface LabelEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Label> = stub<Label>()
}

interface LabelConnection : QType {
  fun edges(): Stub<LabelEdge> = stub<LabelEdge>()

  fun nodes(): Stub<Label> = stub<Label>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface Label : QType {
  fun color(): Stub<String> = stub<String>()

  fun id(): Stub<String> = stub<String>()

  fun issues(): Stub<IssueConnection> = stub<IssueConnection>()

  fun name(): Stub<String> = stub<String>()

  fun pullRequests(): Stub<PullRequestConnection> = stub<PullRequestConnection>()

  fun repository(): Stub<Repository> = stub<Repository>()

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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<IssueTimelineItem> = stub<IssueTimelineItem>()
}

interface IssueTimelineConnection : QType {
  fun edges(): Stub<IssueTimelineItemEdge> = stub<IssueTimelineItemEdge>()

  fun nodes(): Stub<IssueTimelineItem> = stub<IssueTimelineItem>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface IssueEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Issue> = stub<Issue>()
}

interface IssueConnection : QType {
  fun edges(): Stub<IssueEdge> = stub<IssueEdge>()

  fun nodes(): Stub<Issue> = stub<Issue>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface IssueCommentEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<IssueComment> = stub<IssueComment>()
}

interface IssueCommentConnection : QType {
  fun edges(): Stub<IssueCommentEdge> = stub<IssueCommentEdge>()

  fun nodes(): Stub<IssueComment> = stub<IssueComment>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface IssueComment : QType {
  fun author(): Stub<Actor> = stub<Actor>()

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub<CommentAuthorAssociation>()

  fun body(): Stub<String> = stub<String>()

  fun bodyHTML(): Stub<HTML> = stub<HTML>()

  fun bodyText(): Stub<String> = stub<String>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun createdViaEmail(): Stub<Boolean> = stub<Boolean>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun editor(): Stub<Actor> = stub<Actor>()

  fun id(): Stub<String> = stub<String>()

  fun issue(): Stub<Issue> = stub<Issue>()

  fun lastEditedAt(): Stub<DateTime> = stub<DateTime>()

  fun publishedAt(): Stub<DateTime> = stub<DateTime>()

  fun reactionGroups(): Stub<ReactionGroup> = stub<ReactionGroup>()

  fun reactions(): Stub<ReactionConnection> = stub<ReactionConnection>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun viewerCanDelete(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanReact(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanUpdate(): Stub<Boolean> = stub<Boolean>()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub<CommentCannotUpdateReason>()

  fun viewerDidAuthor(): Stub<Boolean> = stub<Boolean>()

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
  fun assignees(): Stub<UserConnection> = stub<UserConnection>()

  fun author(): Stub<Actor> = stub<Actor>()

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub<CommentAuthorAssociation>()

  fun body(): Stub<String> = stub<String>()

  fun bodyHTML(): Stub<HTML> = stub<HTML>()

  fun bodyText(): Stub<String> = stub<String>()

  fun closed(): Stub<Boolean> = stub<Boolean>()

  fun comments(): Stub<IssueCommentConnection> = stub<IssueCommentConnection>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun createdViaEmail(): Stub<Boolean> = stub<Boolean>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun editor(): Stub<Actor> = stub<Actor>()

  fun id(): Stub<String> = stub<String>()

  fun labels(): Stub<LabelConnection> = stub<LabelConnection>()

  fun lastEditedAt(): Stub<DateTime> = stub<DateTime>()

  fun locked(): Stub<Boolean> = stub<Boolean>()

  fun milestone(): Stub<Milestone> = stub<Milestone>()

  fun number(): Stub<Int> = stub<Int>()

  fun participants(): Stub<UserConnection> = stub<UserConnection>()

  fun publishedAt(): Stub<DateTime> = stub<DateTime>()

  fun reactionGroups(): Stub<ReactionGroup> = stub<ReactionGroup>()

  fun reactions(): Stub<ReactionConnection> = stub<ReactionConnection>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun state(): Stub<IssueState> = stub<IssueState>()

  fun timeline(): Stub<IssueTimelineConnection> = stub<IssueTimelineConnection>()

  fun title(): Stub<String> = stub<String>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun url(): Stub<URI> = stub<URI>()

  fun viewerCanReact(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanSubscribe(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanUpdate(): Stub<Boolean> = stub<Boolean>()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub<CommentCannotUpdateReason>()

  fun viewerDidAuthor(): Stub<Boolean> = stub<Boolean>()

  fun viewerSubscription(): Stub<SubscriptionState> = stub<SubscriptionState>()

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
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()
}

interface HeadRefForcePushedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun afterCommit(): Stub<Commit> = stub<Commit>()

  fun beforeCommit(): Stub<Commit> = stub<Commit>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun ref(): Stub<Ref> = stub<Ref>()
}

interface HeadRefDeletedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun headRef(): Stub<Ref> = stub<Ref>()

  fun headRefName(): Stub<String> = stub<String>()

  fun id(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()
}

interface GpgSignature : QType {
  fun email(): Stub<String> = stub<String>()

  fun isValid(): Stub<Boolean> = stub<Boolean>()

  fun keyId(): Stub<String> = stub<String>()

  fun payload(): Stub<String> = stub<String>()

  fun signature(): Stub<String> = stub<String>()

  fun signer(): Stub<User> = stub<User>()

  fun state(): Stub<GitSignatureState> = stub<GitSignatureState>()
}

interface GitActor : QType {
  fun avatarUrl(): Stub<URI> = stub<URI>()

  fun date(): Stub<GitTimestamp> = stub<GitTimestamp>()

  fun email(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()

  fun user(): Stub<User> = stub<User>()

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }
}

interface GistEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Gist> = stub<Gist>()
}

interface GistConnection : QType {
  fun edges(): Stub<GistEdge> = stub<GistEdge>()

  fun nodes(): Stub<Gist> = stub<Gist>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface GistCommentEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<GistComment> = stub<GistComment>()
}

interface GistCommentConnection : QType {
  fun edges(): Stub<GistCommentEdge> = stub<GistCommentEdge>()

  fun nodes(): Stub<GistComment> = stub<GistComment>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface GistComment : QType {
  fun author(): Stub<Actor> = stub<Actor>()

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub<CommentAuthorAssociation>()

  fun body(): Stub<String> = stub<String>()

  fun bodyHTML(): Stub<HTML> = stub<HTML>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun createdViaEmail(): Stub<Boolean> = stub<Boolean>()

  fun editor(): Stub<Actor> = stub<Actor>()

  fun id(): Stub<String> = stub<String>()

  fun lastEditedAt(): Stub<DateTime> = stub<DateTime>()

  fun publishedAt(): Stub<DateTime> = stub<DateTime>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun viewerCanDelete(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanUpdate(): Stub<Boolean> = stub<Boolean>()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub<CommentCannotUpdateReason>()

  fun viewerDidAuthor(): Stub<Boolean> = stub<Boolean>()
}

interface Gist : QType {
  fun comments(): Stub<GistCommentConnection> = stub<GistCommentConnection>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun description(): Stub<String> = stub<String>()

  fun id(): Stub<String> = stub<String>()

  fun isPublic(): Stub<Boolean> = stub<Boolean>()

  fun name(): Stub<String> = stub<String>()

  fun owner(): Stub<RepositoryOwner> = stub<RepositoryOwner>()

  fun stargazers(): Stub<StargazerConnection> = stub<StargazerConnection>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun viewerHasStarred(): Stub<Boolean> = stub<Boolean>()

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
  fun edges(): Stub<UserEdge> = stub<UserEdge>()

  fun nodes(): Stub<User> = stub<User>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface FollowerConnection : QType {
  fun edges(): Stub<UserEdge> = stub<UserEdge>()

  fun nodes(): Stub<User> = stub<User>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface ExternalIdentityScimAttributes : QType {
  fun username(): Stub<String> = stub<String>()
}

interface ExternalIdentitySamlAttributes : QType {
  fun nameId(): Stub<String> = stub<String>()
}

interface ExternalIdentityEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<ExternalIdentity> = stub<ExternalIdentity>()
}

interface ExternalIdentityConnection : QType {
  fun edges(): Stub<ExternalIdentityEdge> = stub<ExternalIdentityEdge>()

  fun nodes(): Stub<ExternalIdentity> = stub<ExternalIdentity>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface ExternalIdentity : QType {
  fun guid(): Stub<String> = stub<String>()

  fun id(): Stub<String> = stub<String>()

  fun organizationInvitation(): Stub<OrganizationInvitation> = stub<OrganizationInvitation>()

  fun samlIdentity(): Stub<ExternalIdentitySamlAttributes> = stub<ExternalIdentitySamlAttributes>()

  fun scimIdentity(): Stub<ExternalIdentityScimAttributes> = stub<ExternalIdentityScimAttributes>()

  fun user(): Stub<User> = stub<User>()
}

interface DismissPullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun pullRequestReview(): Stub<PullRequestReview> = stub<PullRequestReview>()
}

interface DeploymentStatusEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<DeploymentStatus> = stub<DeploymentStatus>()
}

interface DeploymentStatusConnection : QType {
  fun edges(): Stub<DeploymentStatusEdge> = stub<DeploymentStatusEdge>()

  fun nodes(): Stub<DeploymentStatus> = stub<DeploymentStatus>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface DeploymentStatus : QType {
  fun creator(): Stub<Actor> = stub<Actor>()

  fun deployment(): Stub<Deployment> = stub<Deployment>()

  fun description(): Stub<String> = stub<String>()

  fun environmentUrl(): Stub<URI> = stub<URI>()

  fun id(): Stub<String> = stub<String>()

  fun logUrl(): Stub<URI> = stub<URI>()

  fun state(): Stub<DeploymentStatusState> = stub<DeploymentStatusState>()
}

interface DeploymentEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Deployment> = stub<Deployment>()
}

interface DeploymentConnection : QType {
  fun edges(): Stub<DeploymentEdge> = stub<DeploymentEdge>()

  fun nodes(): Stub<Deployment> = stub<Deployment>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface Deployment : QType {
  fun commit(): Stub<Commit> = stub<Commit>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun creator(): Stub<Actor> = stub<Actor>()

  fun environment(): Stub<String> = stub<String>()

  fun id(): Stub<String> = stub<String>()

  fun latestStatus(): Stub<DeploymentStatus> = stub<DeploymentStatus>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun state(): Stub<DeploymentState> = stub<DeploymentState>()

  fun statuses(): Stub<DeploymentStatusConnection> = stub<DeploymentStatusConnection>()

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
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun deployment(): Stub<Deployment> = stub<Deployment>()

  fun id(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun ref(): Stub<Ref> = stub<Ref>()
}

interface DemilestonedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun milestoneTitle(): Stub<String> = stub<String>()

  fun subject(): Stub<MilestoneItem> = stub<MilestoneItem>()
}

interface DeletePullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun pullRequestReview(): Stub<PullRequestReview> = stub<PullRequestReview>()
}

interface DeleteProjectPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun owner(): Stub<ProjectOwner> = stub<ProjectOwner>()
}

interface DeleteProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun deletedColumnId(): Stub<String> = stub<String>()

  fun project(): Stub<Project> = stub<Project>()
}

interface DeleteProjectCardPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun column(): Stub<ProjectColumn> = stub<ProjectColumn>()

  fun deletedCardId(): Stub<String> = stub<String>()
}

interface DeclineTopicSuggestionPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun topic(): Stub<Topic> = stub<Topic>()
}

interface CreateProjectPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun project(): Stub<Project> = stub<Project>()
}

interface ConvertedNoteToIssueEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()
}

interface CommitHistoryConnection : QType {
  fun edges(): Stub<CommitEdge> = stub<CommitEdge>()

  fun nodes(): Stub<Commit> = stub<Commit>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()
}

interface CommitEdge : QType {
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<Commit> = stub<Commit>()
}

interface CommitCommentThread : QType {
  fun comments(): Stub<CommitCommentConnection> = stub<CommitCommentConnection>()

  fun commit(): Stub<Commit> = stub<Commit>()

  fun id(): Stub<String> = stub<String>()

  fun path(): Stub<String> = stub<String>()

  fun position(): Stub<Int> = stub<Int>()

  fun repository(): Stub<Repository> = stub<Repository>()

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
  fun cursor(): Stub<String> = stub<String>()

  fun node(): Stub<CommitComment> = stub<CommitComment>()
}

interface CommitCommentConnection : QType {
  fun edges(): Stub<CommitCommentEdge> = stub<CommitCommentEdge>()

  fun nodes(): Stub<CommitComment> = stub<CommitComment>()

  fun pageInfo(): Stub<PageInfo> = stub<PageInfo>()

  fun totalCount(): Stub<Int> = stub<Int>()
}

interface CommitComment : QType {
  fun author(): Stub<Actor> = stub<Actor>()

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub<CommentAuthorAssociation>()

  fun body(): Stub<String> = stub<String>()

  fun bodyHTML(): Stub<HTML> = stub<HTML>()

  fun commit(): Stub<Commit> = stub<Commit>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun createdViaEmail(): Stub<Boolean> = stub<Boolean>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun editor(): Stub<Actor> = stub<Actor>()

  fun id(): Stub<String> = stub<String>()

  fun lastEditedAt(): Stub<DateTime> = stub<DateTime>()

  fun path(): Stub<String> = stub<String>()

  fun position(): Stub<Int> = stub<Int>()

  fun publishedAt(): Stub<DateTime> = stub<DateTime>()

  fun reactionGroups(): Stub<ReactionGroup> = stub<ReactionGroup>()

  fun reactions(): Stub<ReactionConnection> = stub<ReactionConnection>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun viewerCanDelete(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanReact(): Stub<Boolean> = stub<Boolean>()

  fun viewerCanUpdate(): Stub<Boolean> = stub<Boolean>()

  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub<CommentCannotUpdateReason>()

  fun viewerDidAuthor(): Stub<Boolean> = stub<Boolean>()

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
  fun abbreviatedOid(): Stub<String> = stub<String>()

  fun author(): Stub<GitActor> = stub<GitActor>()

  fun authoredByCommitter(): Stub<Boolean> = stub<Boolean>()

  fun blame(): Stub<Blame> = stub<Blame>()

  fun comments(): Stub<CommitCommentConnection> = stub<CommitCommentConnection>()

  fun commitResourcePath(): Stub<URI> = stub<URI>()

  fun commitUrl(): Stub<URI> = stub<URI>()

  fun committedDate(): Stub<DateTime> = stub<DateTime>()

  fun committedViaWeb(): Stub<Boolean> = stub<Boolean>()

  fun committer(): Stub<GitActor> = stub<GitActor>()

  fun history(): Stub<CommitHistoryConnection> = stub<CommitHistoryConnection>()

  fun id(): Stub<String> = stub<String>()

  fun message(): Stub<String> = stub<String>()

  fun messageBody(): Stub<String> = stub<String>()

  fun messageBodyHTML(): Stub<HTML> = stub<HTML>()

  fun messageHeadline(): Stub<String> = stub<String>()

  fun messageHeadlineHTML(): Stub<HTML> = stub<HTML>()

  fun oid(): Stub<GitObjectID> = stub<GitObjectID>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun signature(): Stub<GitSignature> = stub<GitSignature>()

  fun status(): Stub<Status> = stub<Status>()

  fun tree(): Stub<Tree> = stub<Tree>()

  fun treeResourcePath(): Stub<URI> = stub<URI>()

  fun treeUrl(): Stub<URI> = stub<URI>()

  fun url(): Stub<URI> = stub<URI>()

  fun viewerCanSubscribe(): Stub<Boolean> = stub<Boolean>()

  fun viewerSubscription(): Stub<SubscriptionState> = stub<SubscriptionState>()

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
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()
}

interface CodeOfConduct : QType {
  fun body(): Stub<String> = stub<String>()

  fun key(): Stub<String> = stub<String>()

  fun name(): Stub<String> = stub<String>()

  fun url(): Stub<URI> = stub<URI>()
}

interface ClosedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun closable(): Stub<Closable> = stub<Closable>()

  fun commit(): Stub<Commit> = stub<Commit>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()
}

interface Bot : QType {
  fun avatarUrl(): Stub<URI> = stub<URI>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()

  fun login(): Stub<String> = stub<String>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun url(): Stub<URI> = stub<URI>()

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }
}

interface Blob : QType {
  fun abbreviatedOid(): Stub<String> = stub<String>()

  fun byteSize(): Stub<Int> = stub<Int>()

  fun commitResourcePath(): Stub<URI> = stub<URI>()

  fun commitUrl(): Stub<URI> = stub<URI>()

  fun id(): Stub<String> = stub<String>()

  fun isBinary(): Stub<Boolean> = stub<Boolean>()

  fun isTruncated(): Stub<Boolean> = stub<Boolean>()

  fun oid(): Stub<GitObjectID> = stub<GitObjectID>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun text(): Stub<String> = stub<String>()
}

interface BlameRange : QType {
  fun age(): Stub<Int> = stub<Int>()

  fun commit(): Stub<Commit> = stub<Commit>()

  fun endingLine(): Stub<Int> = stub<Int>()

  fun startingLine(): Stub<Int> = stub<Int>()
}

interface Blame : QType {
  fun ranges(): Stub<BlameRange> = stub<BlameRange>()
}

interface BaseRefForcePushedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun afterCommit(): Stub<Commit> = stub<Commit>()

  fun beforeCommit(): Stub<Commit> = stub<Commit>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun pullRequest(): Stub<PullRequest> = stub<PullRequest>()

  fun ref(): Stub<Ref> = stub<Ref>()
}

interface BaseRefChangedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()
}

interface AssignedEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun assignable(): Stub<Assignable> = stub<Assignable>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun id(): Stub<String> = stub<String>()

  fun user(): Stub<User> = stub<User>()
}

interface AddedToProjectEvent : QType {
  fun actor(): Stub<Actor> = stub<Actor>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()
}

interface AddStarPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun starrable(): Stub<Starrable> = stub<Starrable>()
}

interface AddReactionPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun reaction(): Stub<Reaction> = stub<Reaction>()

  fun subject(): Stub<Reactable> = stub<Reactable>()
}

interface AddPullRequestReviewPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun pullRequestReview(): Stub<PullRequestReview> = stub<PullRequestReview>()

  fun reviewEdge(): Stub<PullRequestReviewEdge> = stub<PullRequestReviewEdge>()
}

interface AddPullRequestReviewCommentPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun comment(): Stub<PullRequestReviewComment> = stub<PullRequestReviewComment>()

  fun commentEdge(): Stub<PullRequestReviewCommentEdge> = stub<PullRequestReviewCommentEdge>()
}

interface AddProjectColumnPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun columnEdge(): Stub<ProjectColumnEdge> = stub<ProjectColumnEdge>()

  fun project(): Stub<Project> = stub<Project>()
}

interface AddProjectCardPayload : QType {
  fun cardEdge(): Stub<ProjectCardEdge> = stub<ProjectCardEdge>()

  fun clientMutationId(): Stub<String> = stub<String>()

  fun projectColumn(): Stub<Project> = stub<Project>()
}

interface AddCommentPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun commentEdge(): Stub<IssueCommentEdge> = stub<IssueCommentEdge>()

  fun subject(): Stub<Node> = stub<Node>()

  fun timelineEdge(): Stub<IssueTimelineItemEdge> = stub<IssueTimelineItemEdge>()
}

interface AcceptTopicSuggestionPayload : QType {
  fun clientMutationId(): Stub<String> = stub<String>()

  fun topic(): Stub<Topic> = stub<Topic>()
}

interface UpdatableComment : QType {
  fun viewerCannotUpdateReasons(): Stub<CommentCannotUpdateReason> = stub<CommentCannotUpdateReason>()
}

interface Updatable : QType {
  fun viewerCanUpdate(): Stub<Boolean> = stub<Boolean>()
}

interface UniformResourceLocatable : QType {
  fun resourcePath(): Stub<URI> = stub<URI>()

  fun url(): Stub<URI> = stub<URI>()
}

interface Subscribable : QType {
  fun viewerCanSubscribe(): Stub<Boolean> = stub<Boolean>()

  fun viewerSubscription(): Stub<SubscriptionState> = stub<SubscriptionState>()
}

interface Starrable : QType {
  fun id(): Stub<String> = stub<String>()

  fun stargazers(): Stub<StargazerConnection> = stub<StargazerConnection>()

  fun viewerHasStarred(): Stub<Boolean> = stub<Boolean>()

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
  fun avatarUrl(): Stub<URI> = stub<URI>()

  fun id(): Stub<String> = stub<String>()

  fun login(): Stub<String> = stub<String>()

  fun pinnedRepositories(): Stub<RepositoryConnection> = stub<RepositoryConnection>()

  fun repositories(): Stub<RepositoryConnection> = stub<RepositoryConnection>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun url(): Stub<URI> = stub<URI>()

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
  fun repository(): Stub<Repository> = stub<Repository>()
}

interface RepositoryInfo : QType {
  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun description(): Stub<String> = stub<String>()

  fun descriptionHTML(): Stub<HTML> = stub<HTML>()

  fun hasIssuesEnabled(): Stub<Boolean> = stub<Boolean>()

  fun hasWikiEnabled(): Stub<Boolean> = stub<Boolean>()

  fun homepageUrl(): Stub<URI> = stub<URI>()

  fun isFork(): Stub<Boolean> = stub<Boolean>()

  fun isLocked(): Stub<Boolean> = stub<Boolean>()

  fun isMirror(): Stub<Boolean> = stub<Boolean>()

  fun isPrivate(): Stub<Boolean> = stub<Boolean>()

  fun license(): Stub<String> = stub<String>()

  fun lockReason(): Stub<RepositoryLockReason> = stub<RepositoryLockReason>()

  fun mirrorUrl(): Stub<URI> = stub<URI>()

  fun name(): Stub<String> = stub<String>()

  fun nameWithOwner(): Stub<String> = stub<String>()

  fun owner(): Stub<RepositoryOwner> = stub<RepositoryOwner>()

  fun pushedAt(): Stub<DateTime> = stub<DateTime>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun url(): Stub<URI> = stub<URI>()
}

interface Reactable : QType {
  fun databaseId(): Stub<Int> = stub<Int>()

  fun id(): Stub<String> = stub<String>()

  fun reactionGroups(): Stub<ReactionGroup> = stub<ReactionGroup>()

  fun reactions(): Stub<ReactionConnection> = stub<ReactionConnection>()

  fun viewerCanReact(): Stub<Boolean> = stub<Boolean>()

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
  fun id(): Stub<String> = stub<String>()

  fun project(): Stub<Project> = stub<Project>()

  fun projects(): Stub<ProjectConnection> = stub<ProjectConnection>()

  fun projectsResourcePath(): Stub<URI> = stub<URI>()

  fun projectsUrl(): Stub<URI> = stub<URI>()

  fun viewerCanCreateProjects(): Stub<Boolean> = stub<Boolean>()

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
  fun id(): Stub<String> = stub<String>()
}

interface Lockable : QType {
  fun locked(): Stub<Boolean> = stub<Boolean>()
}

interface Labelable : QType {
  fun labels(): Stub<LabelConnection> = stub<LabelConnection>()

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
  fun email(): Stub<String> = stub<String>()

  fun isValid(): Stub<Boolean> = stub<Boolean>()

  fun payload(): Stub<String> = stub<String>()

  fun signature(): Stub<String> = stub<String>()

  fun signer(): Stub<User> = stub<User>()

  fun state(): Stub<GitSignatureState> = stub<GitSignatureState>()
}

interface GitObject : QType {
  fun abbreviatedOid(): Stub<String> = stub<String>()

  fun commitResourcePath(): Stub<URI> = stub<URI>()

  fun commitUrl(): Stub<URI> = stub<URI>()

  fun id(): Stub<String> = stub<String>()

  fun oid(): Stub<GitObjectID> = stub<GitObjectID>()

  fun repository(): Stub<Repository> = stub<Repository>()
}

interface Deletable : QType {
  fun viewerCanDelete(): Stub<Boolean> = stub<Boolean>()
}

interface Comment : QType {
  fun author(): Stub<Actor> = stub<Actor>()

  fun authorAssociation(): Stub<CommentAuthorAssociation> = stub<CommentAuthorAssociation>()

  fun body(): Stub<String> = stub<String>()

  fun bodyHTML(): Stub<HTML> = stub<HTML>()

  fun createdAt(): Stub<DateTime> = stub<DateTime>()

  fun createdViaEmail(): Stub<Boolean> = stub<Boolean>()

  fun editor(): Stub<Actor> = stub<Actor>()

  fun id(): Stub<String> = stub<String>()

  fun lastEditedAt(): Stub<DateTime> = stub<DateTime>()

  fun publishedAt(): Stub<DateTime> = stub<DateTime>()

  fun updatedAt(): Stub<DateTime> = stub<DateTime>()

  fun viewerDidAuthor(): Stub<Boolean> = stub<Boolean>()
}

interface Closable : QType {
  fun closed(): Stub<Boolean> = stub<Boolean>()
}

interface Assignable : QType {
  fun assignees(): Stub<UserConnection> = stub<UserConnection>()

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
  fun avatarUrl(): Stub<URI> = stub<URI>()

  fun login(): Stub<String> = stub<String>()

  fun resourcePath(): Stub<URI> = stub<URI>()

  fun url(): Stub<URI> = stub<URI>()

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }
}

interface SearchResultItem : QType {
  fun issue(): Stub<Issue> = stub<Issue>()

  fun pullrequest(): Stub<PullRequest> = stub<PullRequest>()

  fun repository(): Stub<Repository> = stub<Repository>()

  fun user(): Stub<User> = stub<User>()

  fun organization(): Stub<Organization> = stub<Organization>()
}

interface ReviewDismissalAllowanceActor : QType {
  fun user(): Stub<User> = stub<User>()

  fun team(): Stub<Team> = stub<Team>()
}

interface RenamedTitleSubject : QType {
  fun issue(): Stub<Issue> = stub<Issue>()

  fun pullrequest(): Stub<PullRequest> = stub<PullRequest>()
}

interface ReferencedSubject : QType {
  fun issue(): Stub<Issue> = stub<Issue>()

  fun pullrequest(): Stub<PullRequest> = stub<PullRequest>()
}

interface PushAllowanceActor : QType {
  fun user(): Stub<User> = stub<User>()

  fun team(): Stub<Team> = stub<Team>()
}

interface PullRequestTimelineItem : QType {
  fun commit(): Stub<Commit> = stub<Commit>()

  fun commitcommentthread(): Stub<CommitCommentThread> = stub<CommitCommentThread>()

  fun pullrequestreview(): Stub<PullRequestReview> = stub<PullRequestReview>()

  fun pullrequestreviewthread(): Stub<PullRequestReviewThread> = stub<PullRequestReviewThread>()

  fun pullrequestreviewcomment(): Stub<PullRequestReviewComment> = stub<PullRequestReviewComment>()

  fun issuecomment(): Stub<IssueComment> = stub<IssueComment>()

  fun closedevent(): Stub<ClosedEvent> = stub<ClosedEvent>()

  fun reopenedevent(): Stub<ReopenedEvent> = stub<ReopenedEvent>()

  fun subscribedevent(): Stub<SubscribedEvent> = stub<SubscribedEvent>()

  fun unsubscribedevent(): Stub<UnsubscribedEvent> = stub<UnsubscribedEvent>()

  fun mergedevent(): Stub<MergedEvent> = stub<MergedEvent>()

  fun referencedevent(): Stub<ReferencedEvent> = stub<ReferencedEvent>()

  fun assignedevent(): Stub<AssignedEvent> = stub<AssignedEvent>()

  fun unassignedevent(): Stub<UnassignedEvent> = stub<UnassignedEvent>()

  fun labeledevent(): Stub<LabeledEvent> = stub<LabeledEvent>()

  fun unlabeledevent(): Stub<UnlabeledEvent> = stub<UnlabeledEvent>()

  fun milestonedevent(): Stub<MilestonedEvent> = stub<MilestonedEvent>()

  fun demilestonedevent(): Stub<DemilestonedEvent> = stub<DemilestonedEvent>()

  fun renamedtitleevent(): Stub<RenamedTitleEvent> = stub<RenamedTitleEvent>()

  fun lockedevent(): Stub<LockedEvent> = stub<LockedEvent>()

  fun unlockedevent(): Stub<UnlockedEvent> = stub<UnlockedEvent>()

  fun deployedevent(): Stub<DeployedEvent> = stub<DeployedEvent>()

  fun headrefdeletedevent(): Stub<HeadRefDeletedEvent> = stub<HeadRefDeletedEvent>()

  fun headrefrestoredevent(): Stub<HeadRefRestoredEvent> = stub<HeadRefRestoredEvent>()

  fun headrefforcepushedevent(): Stub<HeadRefForcePushedEvent> = stub<HeadRefForcePushedEvent>()

  fun baserefforcepushedevent(): Stub<BaseRefForcePushedEvent> = stub<BaseRefForcePushedEvent>()

  fun reviewrequestedevent(): Stub<ReviewRequestedEvent> = stub<ReviewRequestedEvent>()

  fun reviewrequestremovedevent(): Stub<ReviewRequestRemovedEvent> = stub<ReviewRequestRemovedEvent>()

  fun reviewdismissedevent(): Stub<ReviewDismissedEvent> = stub<ReviewDismissedEvent>()
}

interface ProjectCardItem : QType {
  fun issue(): Stub<Issue> = stub<Issue>()

  fun pullrequest(): Stub<PullRequest> = stub<PullRequest>()
}

interface MilestoneItem : QType {
  fun issue(): Stub<Issue> = stub<Issue>()

  fun pullrequest(): Stub<PullRequest> = stub<PullRequest>()
}

interface IssueTimelineItem : QType {
  fun commit(): Stub<Commit> = stub<Commit>()

  fun issuecomment(): Stub<IssueComment> = stub<IssueComment>()

  fun closedevent(): Stub<ClosedEvent> = stub<ClosedEvent>()

  fun reopenedevent(): Stub<ReopenedEvent> = stub<ReopenedEvent>()

  fun subscribedevent(): Stub<SubscribedEvent> = stub<SubscribedEvent>()

  fun unsubscribedevent(): Stub<UnsubscribedEvent> = stub<UnsubscribedEvent>()

  fun referencedevent(): Stub<ReferencedEvent> = stub<ReferencedEvent>()

  fun assignedevent(): Stub<AssignedEvent> = stub<AssignedEvent>()

  fun unassignedevent(): Stub<UnassignedEvent> = stub<UnassignedEvent>()

  fun labeledevent(): Stub<LabeledEvent> = stub<LabeledEvent>()

  fun unlabeledevent(): Stub<UnlabeledEvent> = stub<UnlabeledEvent>()

  fun milestonedevent(): Stub<MilestonedEvent> = stub<MilestonedEvent>()

  fun demilestonedevent(): Stub<DemilestonedEvent> = stub<DemilestonedEvent>()

  fun renamedtitleevent(): Stub<RenamedTitleEvent> = stub<RenamedTitleEvent>()

  fun lockedevent(): Stub<LockedEvent> = stub<LockedEvent>()

  fun unlockedevent(): Stub<UnlockedEvent> = stub<UnlockedEvent>()
}

interface IssueOrPullRequest : QType {
  fun issue(): Stub<Issue> = stub<Issue>()

  fun pullrequest(): Stub<PullRequest> = stub<PullRequest>()
}

interface X509Certificate : QType {
  fun value(): Stub<String> = stub<String>()
}

interface URI : QType {
  fun value(): Stub<String> = stub<String>()
}

interface HTML : QType {
  fun value(): Stub<String> = stub<String>()
}

interface GitTimestamp : QType {
  fun value(): Stub<String> = stub<String>()
}

interface GitObjectID : QType {
  fun value(): Stub<String> = stub<String>()
}

interface DateTime : QType {
  fun value(): Stub<String> = stub<String>()
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

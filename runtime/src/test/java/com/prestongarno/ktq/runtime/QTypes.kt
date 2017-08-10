package com.prestongarno.ktq.runtime

import com.prestongarno.ktq.runtime.GraphType.SchemaStub
import kotlin.collections.List

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

abstract class UserEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: User by lazy { throw SchemaStub() }
}

abstract class UserConnection : GraphType() {
  protected open val edges: List<UserEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<User> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class User : GraphType() {
  protected open val avatarUrl: URI by lazy { throw SchemaStub() }

  protected open val bio: String by lazy { throw SchemaStub() }

  protected open val bioHTML: HTML by lazy { throw SchemaStub() }

  protected open val company: String by lazy { throw SchemaStub() }

  protected open val companyHTML: HTML by lazy { throw SchemaStub() }

  protected open val contributedRepositories: RepositoryConnection by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val email: String by lazy { throw SchemaStub() }

  protected open val followers: FollowerConnection by lazy { throw SchemaStub() }

  protected open val following: FollowingConnection by lazy { throw SchemaStub() }

  protected open val gist: Gist by lazy { throw SchemaStub() }

  protected open val gists: GistConnection by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val isBountyHunter: Boolean by lazy { throw SchemaStub() }

  protected open val isCampusExpert: Boolean by lazy { throw SchemaStub() }

  protected open val isDeveloperProgramMember: Boolean by lazy { throw SchemaStub() }

  protected open val isEmployee: Boolean by lazy { throw SchemaStub() }

  protected open val isHireable: Boolean by lazy { throw SchemaStub() }

  protected open val isInvoiced: Boolean by lazy { throw SchemaStub() }

  protected open val isSiteAdmin: Boolean by lazy { throw SchemaStub() }

  protected open val isViewer: Boolean by lazy { throw SchemaStub() }

  protected open val issues: IssueConnection by lazy { throw SchemaStub() }

  protected open val location: String by lazy { throw SchemaStub() }

  protected open val login: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val organization: Organization by lazy { throw SchemaStub() }

  protected open val organizations: OrganizationConnection by lazy { throw SchemaStub() }

  protected open val pinnedRepositories: RepositoryConnection by lazy { throw SchemaStub() }

  protected open val pullRequests: PullRequestConnection by lazy { throw SchemaStub() }

  protected open val repositories: RepositoryConnection by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val starredRepositories: StarredRepositoryConnection by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  protected open val viewerCanFollow: Boolean by lazy { throw SchemaStub() }

  protected open val viewerIsFollowing: Boolean by lazy { throw SchemaStub() }

  protected open val watching: RepositoryConnection by lazy { throw SchemaStub() }

  protected open val websiteUrl: URI by lazy { throw SchemaStub() }

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

    fun affiliations(value: List<RepositoryAffiliation>): ContributedRepositoriesArgs {
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

    fun labels(value: List<String>): IssuesArgs {
       addArg("labels", value); return this;
    }

    fun orderBy(value: IssueOrder): IssuesArgs {
       addArg("orderBy", value); return this;
    }

    fun states(value: List<IssueState>): IssuesArgs {
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

    fun affiliations(value: List<RepositoryAffiliation>): PinnedRepositoriesArgs {
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

    fun states(value: List<PullRequestState>): PullRequestsArgs {
       addArg("states", value); return this;
    }

    fun labels(value: List<String>): PullRequestsArgs {
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

    fun affiliations(value: List<RepositoryAffiliation>): RepositoriesArgs {
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

    fun affiliations(value: List<RepositoryAffiliation>): WatchingArgs {
       addArg("affiliations", value); return this;
    }

    fun isLocked(value: Boolean): WatchingArgs {
       addArg("isLocked", value); return this;
    }
  }
}

abstract class UpdateTopicsPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val invalidTopicNames: List<String> by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }
}

abstract class UpdateSubscriptionPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val subscribable: Subscribable by lazy { throw SchemaStub() }
}

abstract class UpdatePullRequestReviewPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val pullRequestReview: PullRequestReview by lazy { throw SchemaStub() }
}

abstract class UpdatePullRequestReviewCommentPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val pullRequestReviewComment: PullRequestReviewComment by lazy { throw SchemaStub() }
}

abstract class UpdateProjectPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val project: Project by lazy { throw SchemaStub() }
}

abstract class UpdateProjectColumnPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val projectColumn: ProjectColumn by lazy { throw SchemaStub() }
}

abstract class UpdateProjectCardPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val projectCard: ProjectCard by lazy { throw SchemaStub() }
}

abstract class UnsubscribedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val subscribable: Subscribable by lazy { throw SchemaStub() }
}

abstract class UnlockedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val lockable: Lockable by lazy { throw SchemaStub() }
}

abstract class UnlabeledEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val label: Label by lazy { throw SchemaStub() }

  protected open val labelable: Labelable by lazy { throw SchemaStub() }
}

abstract class UnknownSignature : GraphType() {
  protected open val email: String by lazy { throw SchemaStub() }

  protected open val isValid: Boolean by lazy { throw SchemaStub() }

  protected open val payload: String by lazy { throw SchemaStub() }

  protected open val signature: String by lazy { throw SchemaStub() }

  protected open val signer: User by lazy { throw SchemaStub() }

  protected open val state: GitSignatureState by lazy { throw SchemaStub() }
}

abstract class UnassignedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val assignable: Assignable by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val user: User by lazy { throw SchemaStub() }
}

abstract class TreeEntry : GraphType() {
  protected open val mode: Int by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val objectVal: GitObject by lazy { throw SchemaStub() }

  protected open val oid: GitObjectID by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val type: String by lazy { throw SchemaStub() }
}

abstract class Tree : GraphType() {
  protected open val abbreviatedOid: String by lazy { throw SchemaStub() }

  protected open val commitResourcePath: URI by lazy { throw SchemaStub() }

  protected open val commitUrl: URI by lazy { throw SchemaStub() }

  protected open val entries: List<TreeEntry> by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val oid: GitObjectID by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }
}

abstract class Topic : GraphType() {
  protected open val id: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val relatedTopics: List<Topic> by lazy { throw SchemaStub() }
}

abstract class TeamEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Team by lazy { throw SchemaStub() }
}

abstract class TeamConnection : GraphType() {
  protected open val edges: List<TeamEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Team> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class Team : GraphType() {
  protected open val description: String by lazy { throw SchemaStub() }

  protected open val editTeamResourcePath: URI by lazy { throw SchemaStub() }

  protected open val editTeamUrl: URI by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val invitations: OrganizationInvitationConnection by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val organization: Organization by lazy { throw SchemaStub() }

  protected open val privacy: TeamPrivacy by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val slug: String by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

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

abstract class Tag : GraphType() {
  protected open val abbreviatedOid: String by lazy { throw SchemaStub() }

  protected open val commitResourcePath: URI by lazy { throw SchemaStub() }

  protected open val commitUrl: URI by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val message: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val oid: GitObjectID by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val tagger: GitActor by lazy { throw SchemaStub() }

  protected open val target: GitObject by lazy { throw SchemaStub() }
}

abstract class SuggestedReviewer : GraphType() {
  protected open val isAuthor: Boolean by lazy { throw SchemaStub() }

  protected open val isCommenter: Boolean by lazy { throw SchemaStub() }

  protected open val reviewer: User by lazy { throw SchemaStub() }
}

abstract class SubscribedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val subscribable: Subscribable by lazy { throw SchemaStub() }
}

abstract class SubmitPullRequestReviewPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val pullRequestReview: PullRequestReview by lazy { throw SchemaStub() }
}

abstract class StatusContext : GraphType() {
  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val context: String by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val creator: Actor by lazy { throw SchemaStub() }

  protected open val description: String by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val state: StatusState by lazy { throw SchemaStub() }

  protected open val targetUrl: URI by lazy { throw SchemaStub() }
}

abstract class Status : GraphType() {
  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val context: StatusContext by lazy { throw SchemaStub() }

  protected open val contexts: List<StatusContext> by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val state: StatusState by lazy { throw SchemaStub() }

  class ContextArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun name(value: String): ContextArgs {
       addArg("name", value); return this;
    }
  }
}

abstract class StarredRepositoryEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Repository by lazy { throw SchemaStub() }

  protected open val starredAt: DateTime by lazy { throw SchemaStub() }
}

abstract class StarredRepositoryConnection : GraphType() {
  protected open val edges: List<StarredRepositoryEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Repository> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class StargazerEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: User by lazy { throw SchemaStub() }

  protected open val starredAt: DateTime by lazy { throw SchemaStub() }
}

abstract class StargazerConnection : GraphType() {
  protected open val edges: List<StargazerEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<User> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class SmimeSignature : GraphType() {
  protected open val email: String by lazy { throw SchemaStub() }

  protected open val isValid: Boolean by lazy { throw SchemaStub() }

  protected open val payload: String by lazy { throw SchemaStub() }

  protected open val signature: String by lazy { throw SchemaStub() }

  protected open val signer: User by lazy { throw SchemaStub() }

  protected open val state: GitSignatureState by lazy { throw SchemaStub() }
}

abstract class SearchResultItemEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: SearchResultItem by lazy { throw SchemaStub() }
}

abstract class SearchResultItemConnection : GraphType() {
  protected open val codeCount: Int by lazy { throw SchemaStub() }

  protected open val edges: List<SearchResultItemEdge> by lazy { throw SchemaStub() }

  protected open val issueCount: Int by lazy { throw SchemaStub() }

  protected open val nodes: List<SearchResultItem> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val repositoryCount: Int by lazy { throw SchemaStub() }

  protected open val userCount: Int by lazy { throw SchemaStub() }

  protected open val wikiCount: Int by lazy { throw SchemaStub() }
}

abstract class ReviewRequestedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val subject: User by lazy { throw SchemaStub() }
}

abstract class ReviewRequestRemovedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val subject: User by lazy { throw SchemaStub() }
}

abstract class ReviewRequestEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: ReviewRequest by lazy { throw SchemaStub() }
}

abstract class ReviewRequestConnection : GraphType() {
  protected open val edges: List<ReviewRequestEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<ReviewRequest> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class ReviewRequest : GraphType() {
  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val reviewer: User by lazy { throw SchemaStub() }
}

abstract class ReviewDismissedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val message: String by lazy { throw SchemaStub() }

  protected open val messageHtml: HTML by lazy { throw SchemaStub() }

  protected open val previousReviewState: PullRequestReviewState by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val pullRequestCommit: PullRequestCommit by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val review: PullRequestReview by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class ReviewDismissalAllowanceEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: ReviewDismissalAllowance by lazy { throw SchemaStub() }
}

abstract class ReviewDismissalAllowanceConnection : GraphType() {
  protected open val edges: List<ReviewDismissalAllowanceEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<ReviewDismissalAllowance> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class ReviewDismissalAllowance : GraphType() {
  protected open val actor: ReviewDismissalAllowanceActor by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val protectedBranch: ProtectedBranch by lazy { throw SchemaStub() }
}

abstract class RequestReviewsPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val requestedReviewersEdge: UserEdge by lazy { throw SchemaStub() }
}

abstract class RepositoryTopicEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: RepositoryTopic by lazy { throw SchemaStub() }
}

abstract class RepositoryTopicConnection : GraphType() {
  protected open val edges: List<RepositoryTopicEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<RepositoryTopic> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class RepositoryTopic : GraphType() {
  protected open val id: String by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val topic: Topic by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class RepositoryInvitationRepository : GraphType() {
  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val description: String by lazy { throw SchemaStub() }

  protected open val descriptionHTML: HTML by lazy { throw SchemaStub() }

  protected open val hasIssuesEnabled: Boolean by lazy { throw SchemaStub() }

  protected open val hasWikiEnabled: Boolean by lazy { throw SchemaStub() }

  protected open val homepageUrl: URI by lazy { throw SchemaStub() }

  protected open val isFork: Boolean by lazy { throw SchemaStub() }

  protected open val isLocked: Boolean by lazy { throw SchemaStub() }

  protected open val isMirror: Boolean by lazy { throw SchemaStub() }

  protected open val isPrivate: Boolean by lazy { throw SchemaStub() }

  protected open val license: String by lazy { throw SchemaStub() }

  protected open val lockReason: RepositoryLockReason by lazy { throw SchemaStub() }

  protected open val mirrorUrl: URI by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val nameWithOwner: String by lazy { throw SchemaStub() }

  protected open val owner: RepositoryOwner by lazy { throw SchemaStub() }

  protected open val pushedAt: DateTime by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class RepositoryInvitation : GraphType() {
  protected open val id: String by lazy { throw SchemaStub() }

  protected open val invitee: User by lazy { throw SchemaStub() }

  protected open val inviter: User by lazy { throw SchemaStub() }

  protected open val repository: RepositoryInvitationRepository by lazy { throw SchemaStub() }
}

abstract class RepositoryEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Repository by lazy { throw SchemaStub() }
}

abstract class RepositoryConnection : GraphType() {
  protected open val edges: List<RepositoryEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Repository> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }

  protected open val totalDiskUsage: Int by lazy { throw SchemaStub() }
}

abstract class Repository : GraphType() {
  protected open val codeOfConduct: CodeOfConduct by lazy { throw SchemaStub() }

  protected open val commitComments: CommitCommentConnection by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val defaultBranchRef: Ref by lazy { throw SchemaStub() }

  protected open val deployments: DeploymentConnection by lazy { throw SchemaStub() }

  protected open val description: String by lazy { throw SchemaStub() }

  protected open val descriptionHTML: HTML by lazy { throw SchemaStub() }

  protected open val diskUsage: Int by lazy { throw SchemaStub() }

  protected open val forks: RepositoryConnection by lazy { throw SchemaStub() }

  protected open val hasIssuesEnabled: Boolean by lazy { throw SchemaStub() }

  protected open val hasWikiEnabled: Boolean by lazy { throw SchemaStub() }

  protected open val homepageUrl: URI by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val isFork: Boolean by lazy { throw SchemaStub() }

  protected open val isLocked: Boolean by lazy { throw SchemaStub() }

  protected open val isMirror: Boolean by lazy { throw SchemaStub() }

  protected open val isPrivate: Boolean by lazy { throw SchemaStub() }

  protected open val issue: Issue by lazy { throw SchemaStub() }

  protected open val issueOrPullRequest: IssueOrPullRequest by lazy { throw SchemaStub() }

  protected open val issues: IssueConnection by lazy { throw SchemaStub() }

  protected open val label: Label by lazy { throw SchemaStub() }

  protected open val labels: LabelConnection by lazy { throw SchemaStub() }

  protected open val languages: LanguageConnection by lazy { throw SchemaStub() }

  protected open val license: String by lazy { throw SchemaStub() }

  protected open val lockReason: RepositoryLockReason by lazy { throw SchemaStub() }

  protected open val mentionableUsers: UserConnection by lazy { throw SchemaStub() }

  protected open val milestone: Milestone by lazy { throw SchemaStub() }

  protected open val milestones: MilestoneConnection by lazy { throw SchemaStub() }

  protected open val mirrorUrl: URI by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val nameWithOwner: String by lazy { throw SchemaStub() }

  protected open val objectVal: GitObject by lazy { throw SchemaStub() }

  protected open val owner: RepositoryOwner by lazy { throw SchemaStub() }

  protected open val parent: Repository by lazy { throw SchemaStub() }

  protected open val primaryLanguage: Language by lazy { throw SchemaStub() }

  protected open val project: Project by lazy { throw SchemaStub() }

  protected open val projects: ProjectConnection by lazy { throw SchemaStub() }

  protected open val projectsResourcePath: URI by lazy { throw SchemaStub() }

  protected open val projectsUrl: URI by lazy { throw SchemaStub() }

  protected open val protectedBranches: ProtectedBranchConnection by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val pullRequests: PullRequestConnection by lazy { throw SchemaStub() }

  protected open val pushedAt: DateTime by lazy { throw SchemaStub() }

  protected open val ref: Ref by lazy { throw SchemaStub() }

  protected open val refs: RefConnection by lazy { throw SchemaStub() }

  protected open val releases: ReleaseConnection by lazy { throw SchemaStub() }

  protected open val repositoryTopics: RepositoryTopicConnection by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val stargazers: StargazerConnection by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  protected open val viewerCanAdminister: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanCreateProjects: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanSubscribe: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanUpdateTopics: Boolean by lazy { throw SchemaStub() }

  protected open val viewerHasStarred: Boolean by lazy { throw SchemaStub() }

  protected open val viewerSubscription: SubscriptionState by lazy { throw SchemaStub() }

  protected open val watchers: UserConnection by lazy { throw SchemaStub() }

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

    fun affiliations(value: List<RepositoryAffiliation>): ForksArgs {
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

    fun labels(value: List<String>): IssuesArgs {
       addArg("labels", value); return this;
    }

    fun orderBy(value: IssueOrder): IssuesArgs {
       addArg("orderBy", value); return this;
    }

    fun states(value: List<IssueState>): IssuesArgs {
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

    fun states(value: List<ProjectState>): ProjectsArgs {
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

    fun states(value: List<PullRequestState>): PullRequestsArgs {
       addArg("states", value); return this;
    }

    fun labels(value: List<String>): PullRequestsArgs {
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

abstract class ReopenedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val closable: Closable by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }
}

abstract class RenamedTitleEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val currentTitle: String by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val previousTitle: String by lazy { throw SchemaStub() }

  protected open val subject: RenamedTitleSubject by lazy { throw SchemaStub() }
}

abstract class RemovedFromProjectEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }
}

abstract class RemoveStarPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val starrable: Starrable by lazy { throw SchemaStub() }
}

abstract class RemoveReactionPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val reaction: Reaction by lazy { throw SchemaStub() }

  protected open val subject: Reactable by lazy { throw SchemaStub() }
}

abstract class RemoveOutsideCollaboratorPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val removedUser: User by lazy { throw SchemaStub() }
}

abstract class ReleaseEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Release by lazy { throw SchemaStub() }
}

abstract class ReleaseConnection : GraphType() {
  protected open val edges: List<ReleaseEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Release> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class ReleaseAssetEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: ReleaseAsset by lazy { throw SchemaStub() }
}

abstract class ReleaseAssetConnection : GraphType() {
  protected open val edges: List<ReleaseAssetEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<ReleaseAsset> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class ReleaseAsset : GraphType() {
  protected open val id: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val release: Release by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class Release : GraphType() {
  protected open val description: String by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val publishedAt: DateTime by lazy { throw SchemaStub() }

  protected open val releaseAsset: ReleaseAssetConnection by lazy { throw SchemaStub() }

  protected open val releaseAssets: ReleaseAssetConnection by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val tag: Ref by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

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

abstract class ReferencedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val commitRepository: Repository by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val isCrossReference: Boolean by lazy { throw SchemaStub() }

  protected open val isCrossRepository: Boolean by lazy { throw SchemaStub() }

  protected open val isDirectReference: Boolean by lazy { throw SchemaStub() }

  protected open val subject: ReferencedSubject by lazy { throw SchemaStub() }
}

abstract class RefEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Ref by lazy { throw SchemaStub() }
}

abstract class RefConnection : GraphType() {
  protected open val edges: List<RefEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Ref> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class Ref : GraphType() {
  protected open val associatedPullRequests: PullRequestConnection by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val prefix: String by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val target: GitObject by lazy { throw SchemaStub() }

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

    fun states(value: List<PullRequestState>): AssociatedPullRequestsArgs {
       addArg("states", value); return this;
    }
  }
}

abstract class ReactionGroup : GraphType() {
  protected open val content: ReactionContent by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val subject: Reactable by lazy { throw SchemaStub() }

  protected open val users: ReactingUserConnection by lazy { throw SchemaStub() }

  protected open val viewerHasReacted: Boolean by lazy { throw SchemaStub() }

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

abstract class ReactionEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Reaction by lazy { throw SchemaStub() }
}

abstract class ReactionConnection : GraphType() {
  protected open val edges: List<ReactionEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Reaction> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }

  protected open val viewerHasReacted: Boolean by lazy { throw SchemaStub() }
}

abstract class Reaction : GraphType() {
  protected open val content: ReactionContent by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val user: User by lazy { throw SchemaStub() }
}

abstract class ReactingUserEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: User by lazy { throw SchemaStub() }

  protected open val reactedAt: DateTime by lazy { throw SchemaStub() }
}

abstract class ReactingUserConnection : GraphType() {
  protected open val edges: List<ReactingUserEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<User> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class RateLimit : GraphType() {
  protected open val cost: Int by lazy { throw SchemaStub() }

  protected open val limit: Int by lazy { throw SchemaStub() }

  protected open val remaining: Int by lazy { throw SchemaStub() }

  protected open val resetAt: DateTime by lazy { throw SchemaStub() }
}

abstract class Query : GraphType() {
  protected open val codeOfConduct: CodeOfConduct by lazy { throw SchemaStub() }

  protected open val codesOfConduct: List<CodeOfConduct> by lazy { throw SchemaStub() }

  protected open val node: Node by lazy { throw SchemaStub() }

  protected open val nodes: List<Node> by lazy { throw SchemaStub() }

  protected open val organization: Organization by lazy { throw SchemaStub() }

  protected open val rateLimit: RateLimit by lazy { throw SchemaStub() }

  protected open val relay: Query by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val repositoryOwner: RepositoryOwner by lazy { throw SchemaStub() }

  protected open val resource: UniformResourceLocatable by lazy { throw SchemaStub() }

  protected open val search: SearchResultItemConnection by lazy { throw SchemaStub() }

  protected open val topic: Topic by lazy { throw SchemaStub() }

  protected open val user: User by lazy { throw SchemaStub() }

  protected open val viewer: User by lazy { throw SchemaStub() }

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

abstract class PushAllowanceEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: PushAllowance by lazy { throw SchemaStub() }
}

abstract class PushAllowanceConnection : GraphType() {
  protected open val edges: List<PushAllowanceEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<PushAllowance> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class PushAllowance : GraphType() {
  protected open val actor: PushAllowanceActor by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val protectedBranch: ProtectedBranch by lazy { throw SchemaStub() }
}

abstract class PullRequestTimelineItemEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: PullRequestTimelineItem by lazy { throw SchemaStub() }
}

abstract class PullRequestTimelineConnection : GraphType() {
  protected open val edges: List<PullRequestTimelineItemEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<PullRequestTimelineItem> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class PullRequestReviewThread : GraphType() {
  protected open val comments: PullRequestReviewCommentConnection by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

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

abstract class PullRequestReviewEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: PullRequestReview by lazy { throw SchemaStub() }
}

abstract class PullRequestReviewConnection : GraphType() {
  protected open val edges: List<PullRequestReviewEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<PullRequestReview> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class PullRequestReviewCommentEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: PullRequestReviewComment by lazy { throw SchemaStub() }
}

abstract class PullRequestReviewCommentConnection : GraphType() {
  protected open val edges: List<PullRequestReviewCommentEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<PullRequestReviewComment> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class PullRequestReviewComment : GraphType() {
  protected open val author: Actor by lazy { throw SchemaStub() }

  protected open val authorAssociation: CommentAuthorAssociation by lazy { throw SchemaStub() }

  protected open val body: String by lazy { throw SchemaStub() }

  protected open val bodyHTML: HTML by lazy { throw SchemaStub() }

  protected open val bodyText: String by lazy { throw SchemaStub() }

  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val createdViaEmail: Boolean by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val diffHunk: String by lazy { throw SchemaStub() }

  protected open val draftedAt: DateTime by lazy { throw SchemaStub() }

  protected open val editor: Actor by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val lastEditedAt: DateTime by lazy { throw SchemaStub() }

  protected open val originalCommit: Commit by lazy { throw SchemaStub() }

  protected open val originalPosition: Int by lazy { throw SchemaStub() }

  protected open val path: String by lazy { throw SchemaStub() }

  protected open val position: Int by lazy { throw SchemaStub() }

  protected open val publishedAt: DateTime by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val pullRequestReview: PullRequestReview by lazy { throw SchemaStub() }

  protected open val reactionGroups: List<ReactionGroup> by lazy { throw SchemaStub() }

  protected open val reactions: ReactionConnection by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  protected open val viewerCanDelete: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanReact: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanUpdate: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCannotUpdateReasons: List<CommentCannotUpdateReason> by lazy { throw SchemaStub() }

  protected open val viewerDidAuthor: Boolean by lazy { throw SchemaStub() }

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

abstract class PullRequestReview : GraphType() {
  protected open val author: Actor by lazy { throw SchemaStub() }

  protected open val authorAssociation: CommentAuthorAssociation by lazy { throw SchemaStub() }

  protected open val body: String by lazy { throw SchemaStub() }

  protected open val bodyHTML: HTML by lazy { throw SchemaStub() }

  protected open val bodyText: String by lazy { throw SchemaStub() }

  protected open val comments: PullRequestReviewCommentConnection by lazy { throw SchemaStub() }

  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val createdViaEmail: Boolean by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val editor: Actor by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val lastEditedAt: DateTime by lazy { throw SchemaStub() }

  protected open val publishedAt: DateTime by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val state: PullRequestReviewState by lazy { throw SchemaStub() }

  protected open val submittedAt: DateTime by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  protected open val viewerCanDelete: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanUpdate: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCannotUpdateReasons: List<CommentCannotUpdateReason> by lazy { throw SchemaStub() }

  protected open val viewerDidAuthor: Boolean by lazy { throw SchemaStub() }

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

abstract class PullRequestEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: PullRequest by lazy { throw SchemaStub() }
}

abstract class PullRequestConnection : GraphType() {
  protected open val edges: List<PullRequestEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<PullRequest> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class PullRequestCommitEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: PullRequestCommit by lazy { throw SchemaStub() }
}

abstract class PullRequestCommitConnection : GraphType() {
  protected open val edges: List<PullRequestCommitEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<PullRequestCommit> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class PullRequestCommit : GraphType() {
  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class PullRequest : GraphType() {
  protected open val assignees: UserConnection by lazy { throw SchemaStub() }

  protected open val author: Actor by lazy { throw SchemaStub() }

  protected open val authorAssociation: CommentAuthorAssociation by lazy { throw SchemaStub() }

  protected open val baseRef: Ref by lazy { throw SchemaStub() }

  protected open val baseRefName: String by lazy { throw SchemaStub() }

  protected open val body: String by lazy { throw SchemaStub() }

  protected open val bodyHTML: HTML by lazy { throw SchemaStub() }

  protected open val bodyText: String by lazy { throw SchemaStub() }

  protected open val closed: Boolean by lazy { throw SchemaStub() }

  protected open val comments: IssueCommentConnection by lazy { throw SchemaStub() }

  protected open val commits: PullRequestCommitConnection by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val createdViaEmail: Boolean by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val editor: Actor by lazy { throw SchemaStub() }

  protected open val headRef: Ref by lazy { throw SchemaStub() }

  protected open val headRefName: String by lazy { throw SchemaStub() }

  protected open val headRepository: Repository by lazy { throw SchemaStub() }

  protected open val headRepositoryOwner: RepositoryOwner by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val isCrossRepository: Boolean by lazy { throw SchemaStub() }

  protected open val labels: LabelConnection by lazy { throw SchemaStub() }

  protected open val lastEditedAt: DateTime by lazy { throw SchemaStub() }

  protected open val locked: Boolean by lazy { throw SchemaStub() }

  protected open val mergeCommit: Commit by lazy { throw SchemaStub() }

  protected open val mergeable: MergeableState by lazy { throw SchemaStub() }

  protected open val merged: Boolean by lazy { throw SchemaStub() }

  protected open val mergedAt: DateTime by lazy { throw SchemaStub() }

  protected open val number: Int by lazy { throw SchemaStub() }

  protected open val participants: UserConnection by lazy { throw SchemaStub() }

  protected open val potentialMergeCommit: Commit by lazy { throw SchemaStub() }

  protected open val publishedAt: DateTime by lazy { throw SchemaStub() }

  protected open val reactionGroups: List<ReactionGroup> by lazy { throw SchemaStub() }

  protected open val reactions: ReactionConnection by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val revertResourcePath: URI by lazy { throw SchemaStub() }

  protected open val revertUrl: URI by lazy { throw SchemaStub() }

  protected open val reviewRequests: ReviewRequestConnection by lazy { throw SchemaStub() }

  protected open val reviews: PullRequestReviewConnection by lazy { throw SchemaStub() }

  protected open val state: PullRequestState by lazy { throw SchemaStub() }

  protected open val suggestedReviewers: List<SuggestedReviewer> by lazy { throw SchemaStub() }

  protected open val timeline: PullRequestTimelineConnection by lazy { throw SchemaStub() }

  protected open val title: String by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  protected open val viewerCanReact: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanSubscribe: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanUpdate: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCannotUpdateReasons: List<CommentCannotUpdateReason> by lazy { throw SchemaStub() }

  protected open val viewerDidAuthor: Boolean by lazy { throw SchemaStub() }

  protected open val viewerSubscription: SubscriptionState by lazy { throw SchemaStub() }

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

    fun states(value: List<PullRequestReviewState>): ReviewsArgs {
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

abstract class ProtectedBranchEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: ProtectedBranch by lazy { throw SchemaStub() }
}

abstract class ProtectedBranchConnection : GraphType() {
  protected open val edges: List<ProtectedBranchEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<ProtectedBranch> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class ProtectedBranch : GraphType() {
  protected open val creator: Actor by lazy { throw SchemaStub() }

  protected open val hasDismissableStaleReviews: Boolean by lazy { throw SchemaStub() }

  protected open val hasRequiredReviews: Boolean by lazy { throw SchemaStub() }

  protected open val hasRequiredStatusChecks: Boolean by lazy { throw SchemaStub() }

  protected open val hasRestrictedPushes: Boolean by lazy { throw SchemaStub() }

  protected open val hasRestrictedReviewDismissals: Boolean by lazy { throw SchemaStub() }

  protected open val hasStrictRequiredStatusChecks: Boolean by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val isAdminEnforced: Boolean by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val pushAllowances: PushAllowanceConnection by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val requiredStatusCheckContexts: List<String> by lazy { throw SchemaStub() }

  protected open val reviewDismissalAllowances: ReviewDismissalAllowanceConnection by lazy { throw SchemaStub() }

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

abstract class ProjectEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Project by lazy { throw SchemaStub() }
}

abstract class ProjectConnection : GraphType() {
  protected open val edges: List<ProjectEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Project> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class ProjectColumnEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: ProjectColumn by lazy { throw SchemaStub() }
}

abstract class ProjectColumnConnection : GraphType() {
  protected open val edges: List<ProjectColumnEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<ProjectColumn> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class ProjectColumn : GraphType() {
  protected open val cards: ProjectCardConnection by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val project: Project by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

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

abstract class ProjectCardEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: ProjectCard by lazy { throw SchemaStub() }
}

abstract class ProjectCardConnection : GraphType() {
  protected open val edges: List<ProjectCardEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<ProjectCard> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class ProjectCard : GraphType() {
  protected open val column: ProjectColumn by lazy { throw SchemaStub() }

  protected open val content: ProjectCardItem by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val creator: Actor by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val note: String by lazy { throw SchemaStub() }

  protected open val project: Project by lazy { throw SchemaStub() }

  protected open val projectColumn: ProjectColumn by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val state: ProjectCardState by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class Project : GraphType() {
  protected open val body: String by lazy { throw SchemaStub() }

  protected open val bodyHTML: HTML by lazy { throw SchemaStub() }

  protected open val closedAt: DateTime by lazy { throw SchemaStub() }

  protected open val columns: ProjectColumnConnection by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val creator: Actor by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val number: Int by lazy { throw SchemaStub() }

  protected open val owner: ProjectOwner by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val state: ProjectState by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  protected open val viewerCanUpdate: Boolean by lazy { throw SchemaStub() }

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

abstract class PageInfo : GraphType() {
  protected open val endCursor: String by lazy { throw SchemaStub() }

  protected open val hasNextPage: Boolean by lazy { throw SchemaStub() }

  protected open val hasPreviousPage: Boolean by lazy { throw SchemaStub() }

  protected open val startCursor: String by lazy { throw SchemaStub() }
}

abstract class OrganizationInvitationEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: OrganizationInvitation by lazy { throw SchemaStub() }
}

abstract class OrganizationInvitationConnection : GraphType() {
  protected open val edges: List<OrganizationInvitationEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<OrganizationInvitation> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class OrganizationInvitation : GraphType() {
  protected open val email: String by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val invitee: User by lazy { throw SchemaStub() }

  protected open val inviter: User by lazy { throw SchemaStub() }

  protected open val role: OrganizationInvitationRole by lazy { throw SchemaStub() }
}

abstract class OrganizationIdentityProvider : GraphType() {
  protected open val digestMethod: URI by lazy { throw SchemaStub() }

  protected open val externalIdentities: ExternalIdentityConnection by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val idpCertificate: X509Certificate by lazy { throw SchemaStub() }

  protected open val issuer: String by lazy { throw SchemaStub() }

  protected open val organization: Organization by lazy { throw SchemaStub() }

  protected open val signatureMethod: URI by lazy { throw SchemaStub() }

  protected open val ssoUrl: URI by lazy { throw SchemaStub() }

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

abstract class OrganizationEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Organization by lazy { throw SchemaStub() }
}

abstract class OrganizationConnection : GraphType() {
  protected open val edges: List<OrganizationEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Organization> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class Organization : GraphType() {
  protected open val avatarUrl: URI by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val isInvoiced: Boolean by lazy { throw SchemaStub() }

  protected open val login: String by lazy { throw SchemaStub() }

  protected open val members: UserConnection by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val newTeamResourcePath: URI by lazy { throw SchemaStub() }

  protected open val newTeamUrl: URI by lazy { throw SchemaStub() }

  protected open val organizationBillingEmail: String by lazy { throw SchemaStub() }

  protected open val pinnedRepositories: RepositoryConnection by lazy { throw SchemaStub() }

  protected open val project: Project by lazy { throw SchemaStub() }

  protected open val projects: ProjectConnection by lazy { throw SchemaStub() }

  protected open val projectsResourcePath: URI by lazy { throw SchemaStub() }

  protected open val projectsUrl: URI by lazy { throw SchemaStub() }

  protected open val repositories: RepositoryConnection by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val samlIdentityProvider: OrganizationIdentityProvider by lazy { throw SchemaStub() }

  protected open val team: Team by lazy { throw SchemaStub() }

  protected open val teams: TeamConnection by lazy { throw SchemaStub() }

  protected open val teamsResourcePath: URI by lazy { throw SchemaStub() }

  protected open val teamsUrl: URI by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  protected open val viewerCanAdminister: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanCreateProjects: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanCreateRepositories: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanCreateTeams: Boolean by lazy { throw SchemaStub() }

  protected open val viewerIsAMember: Boolean by lazy { throw SchemaStub() }

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

    fun affiliations(value: List<RepositoryAffiliation>): PinnedRepositoriesArgs {
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

    fun states(value: List<ProjectState>): ProjectsArgs {
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

    fun affiliations(value: List<RepositoryAffiliation>): RepositoriesArgs {
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

    fun userLogins(value: List<String>): TeamsArgs {
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

abstract class Mutation : GraphType() {
  protected open val acceptTopicSuggestion: AcceptTopicSuggestionPayload by lazy { throw SchemaStub() }

  protected open val addComment: AddCommentPayload by lazy { throw SchemaStub() }

  protected open val addProjectCard: AddProjectCardPayload by lazy { throw SchemaStub() }

  protected open val addProjectColumn: AddProjectColumnPayload by lazy { throw SchemaStub() }

  protected open val addPullRequestReview: AddPullRequestReviewPayload by lazy { throw SchemaStub() }

  protected open val addPullRequestReviewComment: AddPullRequestReviewCommentPayload by lazy { throw SchemaStub() }

  protected open val addReaction: AddReactionPayload by lazy { throw SchemaStub() }

  protected open val addStar: AddStarPayload by lazy { throw SchemaStub() }

  protected open val createProject: CreateProjectPayload by lazy { throw SchemaStub() }

  protected open val declineTopicSuggestion: DeclineTopicSuggestionPayload by lazy { throw SchemaStub() }

  protected open val deleteProject: DeleteProjectPayload by lazy { throw SchemaStub() }

  protected open val deleteProjectCard: DeleteProjectCardPayload by lazy { throw SchemaStub() }

  protected open val deleteProjectColumn: DeleteProjectColumnPayload by lazy { throw SchemaStub() }

  protected open val deletePullRequestReview: DeletePullRequestReviewPayload by lazy { throw SchemaStub() }

  protected open val dismissPullRequestReview: DismissPullRequestReviewPayload by lazy { throw SchemaStub() }

  protected open val moveProjectCard: MoveProjectCardPayload by lazy { throw SchemaStub() }

  protected open val moveProjectColumn: MoveProjectColumnPayload by lazy { throw SchemaStub() }

  protected open val removeOutsideCollaborator: RemoveOutsideCollaboratorPayload by lazy { throw SchemaStub() }

  protected open val removeReaction: RemoveReactionPayload by lazy { throw SchemaStub() }

  protected open val removeStar: RemoveStarPayload by lazy { throw SchemaStub() }

  protected open val requestReviews: RequestReviewsPayload by lazy { throw SchemaStub() }

  protected open val submitPullRequestReview: SubmitPullRequestReviewPayload by lazy { throw SchemaStub() }

  protected open val updateProject: UpdateProjectPayload by lazy { throw SchemaStub() }

  protected open val updateProjectCard: UpdateProjectCardPayload by lazy { throw SchemaStub() }

  protected open val updateProjectColumn: UpdateProjectColumnPayload by lazy { throw SchemaStub() }

  protected open val updatePullRequestReview: UpdatePullRequestReviewPayload by lazy { throw SchemaStub() }

  protected open val updatePullRequestReviewComment: UpdatePullRequestReviewCommentPayload by lazy { throw SchemaStub() }

  protected open val updateSubscription: UpdateSubscriptionPayload by lazy { throw SchemaStub() }

  protected open val updateTopics: UpdateTopicsPayload by lazy { throw SchemaStub() }

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

abstract class MovedColumnsInProjectEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }
}

abstract class MoveProjectColumnPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val columnEdge: ProjectColumnEdge by lazy { throw SchemaStub() }
}

abstract class MoveProjectCardPayload : GraphType() {
  protected open val cardEdge: ProjectCardEdge by lazy { throw SchemaStub() }

  protected open val clientMutationId: String by lazy { throw SchemaStub() }
}

abstract class MilestonedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val milestoneTitle: String by lazy { throw SchemaStub() }

  protected open val subject: MilestoneItem by lazy { throw SchemaStub() }
}

abstract class MilestoneEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Milestone by lazy { throw SchemaStub() }
}

abstract class MilestoneConnection : GraphType() {
  protected open val edges: List<MilestoneEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Milestone> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class Milestone : GraphType() {
  protected open val creator: Actor by lazy { throw SchemaStub() }

  protected open val description: String by lazy { throw SchemaStub() }

  protected open val dueOn: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val number: Int by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val state: MilestoneState by lazy { throw SchemaStub() }

  protected open val title: String by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class MergedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val mergeRef: Ref by lazy { throw SchemaStub() }

  protected open val mergeRefName: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class MentionedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }
}

abstract class LockedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val lockable: Lockable by lazy { throw SchemaStub() }
}

abstract class LanguageEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Language by lazy { throw SchemaStub() }

  protected open val size: Int by lazy { throw SchemaStub() }
}

abstract class LanguageConnection : GraphType() {
  protected open val edges: List<LanguageEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Language> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }

  protected open val totalSize: Int by lazy { throw SchemaStub() }
}

abstract class Language : GraphType() {
  protected open val color: String by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }
}

abstract class LabeledEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val label: Label by lazy { throw SchemaStub() }

  protected open val labelable: Labelable by lazy { throw SchemaStub() }
}

abstract class LabelEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Label by lazy { throw SchemaStub() }
}

abstract class LabelConnection : GraphType() {
  protected open val edges: List<LabelEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Label> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class Label : GraphType() {
  protected open val color: String by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val issues: IssueConnection by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val pullRequests: PullRequestConnection by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

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

    fun labels(value: List<String>): IssuesArgs {
       addArg("labels", value); return this;
    }

    fun orderBy(value: IssueOrder): IssuesArgs {
       addArg("orderBy", value); return this;
    }

    fun states(value: List<IssueState>): IssuesArgs {
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

abstract class IssueTimelineItemEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: IssueTimelineItem by lazy { throw SchemaStub() }
}

abstract class IssueTimelineConnection : GraphType() {
  protected open val edges: List<IssueTimelineItemEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<IssueTimelineItem> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class IssueEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Issue by lazy { throw SchemaStub() }
}

abstract class IssueConnection : GraphType() {
  protected open val edges: List<IssueEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Issue> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class IssueCommentEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: IssueComment by lazy { throw SchemaStub() }
}

abstract class IssueCommentConnection : GraphType() {
  protected open val edges: List<IssueCommentEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<IssueComment> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class IssueComment : GraphType() {
  protected open val author: Actor by lazy { throw SchemaStub() }

  protected open val authorAssociation: CommentAuthorAssociation by lazy { throw SchemaStub() }

  protected open val body: String by lazy { throw SchemaStub() }

  protected open val bodyHTML: HTML by lazy { throw SchemaStub() }

  protected open val bodyText: String by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val createdViaEmail: Boolean by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val editor: Actor by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val issue: Issue by lazy { throw SchemaStub() }

  protected open val lastEditedAt: DateTime by lazy { throw SchemaStub() }

  protected open val publishedAt: DateTime by lazy { throw SchemaStub() }

  protected open val reactionGroups: List<ReactionGroup> by lazy { throw SchemaStub() }

  protected open val reactions: ReactionConnection by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val viewerCanDelete: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanReact: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanUpdate: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCannotUpdateReasons: List<CommentCannotUpdateReason> by lazy { throw SchemaStub() }

  protected open val viewerDidAuthor: Boolean by lazy { throw SchemaStub() }

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

abstract class Issue : GraphType() {
  protected open val assignees: UserConnection by lazy { throw SchemaStub() }

  protected open val author: Actor by lazy { throw SchemaStub() }

  protected open val authorAssociation: CommentAuthorAssociation by lazy { throw SchemaStub() }

  protected open val body: String by lazy { throw SchemaStub() }

  protected open val bodyHTML: HTML by lazy { throw SchemaStub() }

  protected open val bodyText: String by lazy { throw SchemaStub() }

  protected open val closed: Boolean by lazy { throw SchemaStub() }

  protected open val comments: IssueCommentConnection by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val createdViaEmail: Boolean by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val editor: Actor by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val labels: LabelConnection by lazy { throw SchemaStub() }

  protected open val lastEditedAt: DateTime by lazy { throw SchemaStub() }

  protected open val locked: Boolean by lazy { throw SchemaStub() }

  protected open val milestone: Milestone by lazy { throw SchemaStub() }

  protected open val number: Int by lazy { throw SchemaStub() }

  protected open val participants: UserConnection by lazy { throw SchemaStub() }

  protected open val publishedAt: DateTime by lazy { throw SchemaStub() }

  protected open val reactionGroups: List<ReactionGroup> by lazy { throw SchemaStub() }

  protected open val reactions: ReactionConnection by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val state: IssueState by lazy { throw SchemaStub() }

  protected open val timeline: IssueTimelineConnection by lazy { throw SchemaStub() }

  protected open val title: String by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  protected open val viewerCanReact: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanSubscribe: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanUpdate: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCannotUpdateReasons: List<CommentCannotUpdateReason> by lazy { throw SchemaStub() }

  protected open val viewerDidAuthor: Boolean by lazy { throw SchemaStub() }

  protected open val viewerSubscription: SubscriptionState by lazy { throw SchemaStub() }

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

abstract class HeadRefRestoredEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }
}

abstract class HeadRefForcePushedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val afterCommit: Commit by lazy { throw SchemaStub() }

  protected open val beforeCommit: Commit by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val ref: Ref by lazy { throw SchemaStub() }
}

abstract class HeadRefDeletedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val headRef: Ref by lazy { throw SchemaStub() }

  protected open val headRefName: String by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }
}

abstract class GpgSignature : GraphType() {
  protected open val email: String by lazy { throw SchemaStub() }

  protected open val isValid: Boolean by lazy { throw SchemaStub() }

  protected open val keyId: String by lazy { throw SchemaStub() }

  protected open val payload: String by lazy { throw SchemaStub() }

  protected open val signature: String by lazy { throw SchemaStub() }

  protected open val signer: User by lazy { throw SchemaStub() }

  protected open val state: GitSignatureState by lazy { throw SchemaStub() }
}

abstract class GitActor : GraphType() {
  protected open val avatarUrl: URI by lazy { throw SchemaStub() }

  protected open val date: GitTimestamp by lazy { throw SchemaStub() }

  protected open val email: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val user: User by lazy { throw SchemaStub() }

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }
}

abstract class GistEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Gist by lazy { throw SchemaStub() }
}

abstract class GistConnection : GraphType() {
  protected open val edges: List<GistEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Gist> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class GistCommentEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: GistComment by lazy { throw SchemaStub() }
}

abstract class GistCommentConnection : GraphType() {
  protected open val edges: List<GistCommentEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<GistComment> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class GistComment : GraphType() {
  protected open val author: Actor by lazy { throw SchemaStub() }

  protected open val authorAssociation: CommentAuthorAssociation by lazy { throw SchemaStub() }

  protected open val body: String by lazy { throw SchemaStub() }

  protected open val bodyHTML: HTML by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val createdViaEmail: Boolean by lazy { throw SchemaStub() }

  protected open val editor: Actor by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val lastEditedAt: DateTime by lazy { throw SchemaStub() }

  protected open val publishedAt: DateTime by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val viewerCanDelete: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanUpdate: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCannotUpdateReasons: List<CommentCannotUpdateReason> by lazy { throw SchemaStub() }

  protected open val viewerDidAuthor: Boolean by lazy { throw SchemaStub() }
}

abstract class Gist : GraphType() {
  protected open val comments: GistCommentConnection by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val description: String by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val isPublic: Boolean by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val owner: RepositoryOwner by lazy { throw SchemaStub() }

  protected open val stargazers: StargazerConnection by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val viewerHasStarred: Boolean by lazy { throw SchemaStub() }

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

abstract class FollowingConnection : GraphType() {
  protected open val edges: List<UserEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<User> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class FollowerConnection : GraphType() {
  protected open val edges: List<UserEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<User> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class ExternalIdentityScimAttributes : GraphType() {
  protected open val username: String by lazy { throw SchemaStub() }
}

abstract class ExternalIdentitySamlAttributes : GraphType() {
  protected open val nameId: String by lazy { throw SchemaStub() }
}

abstract class ExternalIdentityEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: ExternalIdentity by lazy { throw SchemaStub() }
}

abstract class ExternalIdentityConnection : GraphType() {
  protected open val edges: List<ExternalIdentityEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<ExternalIdentity> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class ExternalIdentity : GraphType() {
  protected open val guid: String by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val organizationInvitation: OrganizationInvitation by lazy { throw SchemaStub() }

  protected open val samlIdentity: ExternalIdentitySamlAttributes by lazy { throw SchemaStub() }

  protected open val scimIdentity: ExternalIdentityScimAttributes by lazy { throw SchemaStub() }

  protected open val user: User by lazy { throw SchemaStub() }
}

abstract class DismissPullRequestReviewPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val pullRequestReview: PullRequestReview by lazy { throw SchemaStub() }
}

abstract class DeploymentStatusEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: DeploymentStatus by lazy { throw SchemaStub() }
}

abstract class DeploymentStatusConnection : GraphType() {
  protected open val edges: List<DeploymentStatusEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<DeploymentStatus> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class DeploymentStatus : GraphType() {
  protected open val creator: Actor by lazy { throw SchemaStub() }

  protected open val deployment: Deployment by lazy { throw SchemaStub() }

  protected open val description: String by lazy { throw SchemaStub() }

  protected open val environmentUrl: URI by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val logUrl: URI by lazy { throw SchemaStub() }

  protected open val state: DeploymentStatusState by lazy { throw SchemaStub() }
}

abstract class DeploymentEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Deployment by lazy { throw SchemaStub() }
}

abstract class DeploymentConnection : GraphType() {
  protected open val edges: List<DeploymentEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Deployment> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class Deployment : GraphType() {
  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val creator: Actor by lazy { throw SchemaStub() }

  protected open val environment: String by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val latestStatus: DeploymentStatus by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val state: DeploymentState by lazy { throw SchemaStub() }

  protected open val statuses: DeploymentStatusConnection by lazy { throw SchemaStub() }

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

abstract class DeployedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val deployment: Deployment by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val ref: Ref by lazy { throw SchemaStub() }
}

abstract class DemilestonedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val milestoneTitle: String by lazy { throw SchemaStub() }

  protected open val subject: MilestoneItem by lazy { throw SchemaStub() }
}

abstract class DeletePullRequestReviewPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val pullRequestReview: PullRequestReview by lazy { throw SchemaStub() }
}

abstract class DeleteProjectPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val owner: ProjectOwner by lazy { throw SchemaStub() }
}

abstract class DeleteProjectColumnPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val deletedColumnId: String by lazy { throw SchemaStub() }

  protected open val project: Project by lazy { throw SchemaStub() }
}

abstract class DeleteProjectCardPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val column: ProjectColumn by lazy { throw SchemaStub() }

  protected open val deletedCardId: String by lazy { throw SchemaStub() }
}

abstract class DeclineTopicSuggestionPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val topic: Topic by lazy { throw SchemaStub() }
}

abstract class CreateProjectPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val project: Project by lazy { throw SchemaStub() }
}

abstract class ConvertedNoteToIssueEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }
}

abstract class CommitHistoryConnection : GraphType() {
  protected open val edges: List<CommitEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<Commit> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }
}

abstract class CommitEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: Commit by lazy { throw SchemaStub() }
}

abstract class CommitCommentThread : GraphType() {
  protected open val comments: CommitCommentConnection by lazy { throw SchemaStub() }

  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val path: String by lazy { throw SchemaStub() }

  protected open val position: Int by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

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

abstract class CommitCommentEdge : GraphType() {
  protected open val cursor: String by lazy { throw SchemaStub() }

  protected open val node: CommitComment by lazy { throw SchemaStub() }
}

abstract class CommitCommentConnection : GraphType() {
  protected open val edges: List<CommitCommentEdge> by lazy { throw SchemaStub() }

  protected open val nodes: List<CommitComment> by lazy { throw SchemaStub() }

  protected open val pageInfo: PageInfo by lazy { throw SchemaStub() }

  protected open val totalCount: Int by lazy { throw SchemaStub() }
}

abstract class CommitComment : GraphType() {
  protected open val author: Actor by lazy { throw SchemaStub() }

  protected open val authorAssociation: CommentAuthorAssociation by lazy { throw SchemaStub() }

  protected open val body: String by lazy { throw SchemaStub() }

  protected open val bodyHTML: HTML by lazy { throw SchemaStub() }

  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val createdViaEmail: Boolean by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val editor: Actor by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val lastEditedAt: DateTime by lazy { throw SchemaStub() }

  protected open val path: String by lazy { throw SchemaStub() }

  protected open val position: Int by lazy { throw SchemaStub() }

  protected open val publishedAt: DateTime by lazy { throw SchemaStub() }

  protected open val reactionGroups: List<ReactionGroup> by lazy { throw SchemaStub() }

  protected open val reactions: ReactionConnection by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val viewerCanDelete: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanReact: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCanUpdate: Boolean by lazy { throw SchemaStub() }

  protected open val viewerCannotUpdateReasons: List<CommentCannotUpdateReason> by lazy { throw SchemaStub() }

  protected open val viewerDidAuthor: Boolean by lazy { throw SchemaStub() }

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

abstract class Commit : GraphType() {
  protected open val abbreviatedOid: String by lazy { throw SchemaStub() }

  protected open val author: GitActor by lazy { throw SchemaStub() }

  protected open val authoredByCommitter: Boolean by lazy { throw SchemaStub() }

  protected open val blame: Blame by lazy { throw SchemaStub() }

  protected open val comments: CommitCommentConnection by lazy { throw SchemaStub() }

  protected open val commitResourcePath: URI by lazy { throw SchemaStub() }

  protected open val commitUrl: URI by lazy { throw SchemaStub() }

  protected open val committedDate: DateTime by lazy { throw SchemaStub() }

  protected open val committedViaWeb: Boolean by lazy { throw SchemaStub() }

  protected open val committer: GitActor by lazy { throw SchemaStub() }

  protected open val history: CommitHistoryConnection by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val message: String by lazy { throw SchemaStub() }

  protected open val messageBody: String by lazy { throw SchemaStub() }

  protected open val messageBodyHTML: HTML by lazy { throw SchemaStub() }

  protected open val messageHeadline: String by lazy { throw SchemaStub() }

  protected open val messageHeadlineHTML: HTML by lazy { throw SchemaStub() }

  protected open val oid: GitObjectID by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val signature: GitSignature by lazy { throw SchemaStub() }

  protected open val status: Status by lazy { throw SchemaStub() }

  protected open val tree: Tree by lazy { throw SchemaStub() }

  protected open val treeResourcePath: URI by lazy { throw SchemaStub() }

  protected open val treeUrl: URI by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  protected open val viewerCanSubscribe: Boolean by lazy { throw SchemaStub() }

  protected open val viewerSubscription: SubscriptionState by lazy { throw SchemaStub() }

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

abstract class CommentDeletedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }
}

abstract class CodeOfConduct : GraphType() {
  protected open val body: String by lazy { throw SchemaStub() }

  protected open val key: String by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class ClosedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val closable: Closable by lazy { throw SchemaStub() }

  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }
}

abstract class Bot : GraphType() {
  protected open val avatarUrl: URI by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val login: String by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }
}

abstract class Blob : GraphType() {
  protected open val abbreviatedOid: String by lazy { throw SchemaStub() }

  protected open val byteSize: Int by lazy { throw SchemaStub() }

  protected open val commitResourcePath: URI by lazy { throw SchemaStub() }

  protected open val commitUrl: URI by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val isBinary: Boolean by lazy { throw SchemaStub() }

  protected open val isTruncated: Boolean by lazy { throw SchemaStub() }

  protected open val oid: GitObjectID by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val text: String by lazy { throw SchemaStub() }
}

abstract class BlameRange : GraphType() {
  protected open val age: Int by lazy { throw SchemaStub() }

  protected open val commit: Commit by lazy { throw SchemaStub() }

  protected open val endingLine: Int by lazy { throw SchemaStub() }

  protected open val startingLine: Int by lazy { throw SchemaStub() }
}

abstract class Blame : GraphType() {
  protected open val ranges: List<BlameRange> by lazy { throw SchemaStub() }
}

abstract class BaseRefForcePushedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val afterCommit: Commit by lazy { throw SchemaStub() }

  protected open val beforeCommit: Commit by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val pullRequest: PullRequest by lazy { throw SchemaStub() }

  protected open val ref: Ref by lazy { throw SchemaStub() }
}

abstract class BaseRefChangedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }
}

abstract class AssignedEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val assignable: Assignable by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val user: User by lazy { throw SchemaStub() }
}

abstract class AddedToProjectEvent : GraphType() {
  protected open val actor: Actor by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }
}

abstract class AddStarPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val starrable: Starrable by lazy { throw SchemaStub() }
}

abstract class AddReactionPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val reaction: Reaction by lazy { throw SchemaStub() }

  protected open val subject: Reactable by lazy { throw SchemaStub() }
}

abstract class AddPullRequestReviewPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val pullRequestReview: PullRequestReview by lazy { throw SchemaStub() }

  protected open val reviewEdge: PullRequestReviewEdge by lazy { throw SchemaStub() }
}

abstract class AddPullRequestReviewCommentPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val comment: PullRequestReviewComment by lazy { throw SchemaStub() }

  protected open val commentEdge: PullRequestReviewCommentEdge by lazy { throw SchemaStub() }
}

abstract class AddProjectColumnPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val columnEdge: ProjectColumnEdge by lazy { throw SchemaStub() }

  protected open val project: Project by lazy { throw SchemaStub() }
}

abstract class AddProjectCardPayload : GraphType() {
  protected open val cardEdge: ProjectCardEdge by lazy { throw SchemaStub() }

  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val projectColumn: Project by lazy { throw SchemaStub() }
}

abstract class AddCommentPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val commentEdge: IssueCommentEdge by lazy { throw SchemaStub() }

  protected open val subject: Node by lazy { throw SchemaStub() }

  protected open val timelineEdge: IssueTimelineItemEdge by lazy { throw SchemaStub() }
}

abstract class AcceptTopicSuggestionPayload : GraphType() {
  protected open val clientMutationId: String by lazy { throw SchemaStub() }

  protected open val topic: Topic by lazy { throw SchemaStub() }
}

abstract class UpdatableComment : GraphType() {
  protected open val viewerCannotUpdateReasons: List<CommentCannotUpdateReason> by lazy { throw SchemaStub() }
}

abstract class Updatable : GraphType() {
  protected open val viewerCanUpdate: Boolean by lazy { throw SchemaStub() }
}

abstract class UniformResourceLocatable : GraphType() {
  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class Subscribable : GraphType() {
  protected open val viewerCanSubscribe: Boolean by lazy { throw SchemaStub() }

  protected open val viewerSubscription: SubscriptionState by lazy { throw SchemaStub() }
}

abstract class Starrable : GraphType() {
  protected open val id: String by lazy { throw SchemaStub() }

  protected open val stargazers: StargazerConnection by lazy { throw SchemaStub() }

  protected open val viewerHasStarred: Boolean by lazy { throw SchemaStub() }

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

abstract class RepositoryOwner : GraphType() {
  protected open val avatarUrl: URI by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val login: String by lazy { throw SchemaStub() }

  protected open val pinnedRepositories: RepositoryConnection by lazy { throw SchemaStub() }

  protected open val repositories: RepositoryConnection by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

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

    fun affiliations(value: List<RepositoryAffiliation>): PinnedRepositoriesArgs {
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

    fun affiliations(value: List<RepositoryAffiliation>): RepositoriesArgs {
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

abstract class RepositoryNode : GraphType() {
  protected open val repository: Repository by lazy { throw SchemaStub() }
}

abstract class RepositoryInfo : GraphType() {
  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val description: String by lazy { throw SchemaStub() }

  protected open val descriptionHTML: HTML by lazy { throw SchemaStub() }

  protected open val hasIssuesEnabled: Boolean by lazy { throw SchemaStub() }

  protected open val hasWikiEnabled: Boolean by lazy { throw SchemaStub() }

  protected open val homepageUrl: URI by lazy { throw SchemaStub() }

  protected open val isFork: Boolean by lazy { throw SchemaStub() }

  protected open val isLocked: Boolean by lazy { throw SchemaStub() }

  protected open val isMirror: Boolean by lazy { throw SchemaStub() }

  protected open val isPrivate: Boolean by lazy { throw SchemaStub() }

  protected open val license: String by lazy { throw SchemaStub() }

  protected open val lockReason: RepositoryLockReason by lazy { throw SchemaStub() }

  protected open val mirrorUrl: URI by lazy { throw SchemaStub() }

  protected open val name: String by lazy { throw SchemaStub() }

  protected open val nameWithOwner: String by lazy { throw SchemaStub() }

  protected open val owner: RepositoryOwner by lazy { throw SchemaStub() }

  protected open val pushedAt: DateTime by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }
}

abstract class Reactable : GraphType() {
  protected open val databaseId: Int by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val reactionGroups: List<ReactionGroup> by lazy { throw SchemaStub() }

  protected open val reactions: ReactionConnection by lazy { throw SchemaStub() }

  protected open val viewerCanReact: Boolean by lazy { throw SchemaStub() }

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

abstract class ProjectOwner : GraphType() {
  protected open val id: String by lazy { throw SchemaStub() }

  protected open val project: Project by lazy { throw SchemaStub() }

  protected open val projects: ProjectConnection by lazy { throw SchemaStub() }

  protected open val projectsResourcePath: URI by lazy { throw SchemaStub() }

  protected open val projectsUrl: URI by lazy { throw SchemaStub() }

  protected open val viewerCanCreateProjects: Boolean by lazy { throw SchemaStub() }

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

    fun states(value: List<ProjectState>): ProjectsArgs {
       addArg("states", value); return this;
    }
  }
}

abstract class Node : GraphType() {
  protected open val id: String by lazy { throw SchemaStub() }
}

abstract class Lockable : GraphType() {
  protected open val locked: Boolean by lazy { throw SchemaStub() }
}

abstract class Labelable : GraphType() {
  protected open val labels: LabelConnection by lazy { throw SchemaStub() }

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

abstract class GitSignature : GraphType() {
  protected open val email: String by lazy { throw SchemaStub() }

  protected open val isValid: Boolean by lazy { throw SchemaStub() }

  protected open val payload: String by lazy { throw SchemaStub() }

  protected open val signature: String by lazy { throw SchemaStub() }

  protected open val signer: User by lazy { throw SchemaStub() }

  protected open val state: GitSignatureState by lazy { throw SchemaStub() }
}

abstract class GitObject : GraphType() {
  protected open val abbreviatedOid: String by lazy { throw SchemaStub() }

  protected open val commitResourcePath: URI by lazy { throw SchemaStub() }

  protected open val commitUrl: URI by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val oid: GitObjectID by lazy { throw SchemaStub() }

  protected open val repository: Repository by lazy { throw SchemaStub() }
}

abstract class Deletable : GraphType() {
  protected open val viewerCanDelete: Boolean by lazy { throw SchemaStub() }
}

abstract class Comment : GraphType() {
  protected open val author: Actor by lazy { throw SchemaStub() }

  protected open val authorAssociation: CommentAuthorAssociation by lazy { throw SchemaStub() }

  protected open val body: String by lazy { throw SchemaStub() }

  protected open val bodyHTML: HTML by lazy { throw SchemaStub() }

  protected open val createdAt: DateTime by lazy { throw SchemaStub() }

  protected open val createdViaEmail: Boolean by lazy { throw SchemaStub() }

  protected open val editor: Actor by lazy { throw SchemaStub() }

  protected open val id: String by lazy { throw SchemaStub() }

  protected open val lastEditedAt: DateTime by lazy { throw SchemaStub() }

  protected open val publishedAt: DateTime by lazy { throw SchemaStub() }

  protected open val updatedAt: DateTime by lazy { throw SchemaStub() }

  protected open val viewerDidAuthor: Boolean by lazy { throw SchemaStub() }
}

abstract class Closable : GraphType() {
  protected open val closed: Boolean by lazy { throw SchemaStub() }
}

abstract class Assignable : GraphType() {
  protected open val assignees: UserConnection by lazy { throw SchemaStub() }

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

abstract class Actor : GraphType() {
  protected open val avatarUrl: URI by lazy { throw SchemaStub() }

  protected open val login: String by lazy { throw SchemaStub() }

  protected open val resourcePath: URI by lazy { throw SchemaStub() }

  protected open val url: URI by lazy { throw SchemaStub() }

  class AvatarUrlArgs(builder: ArgBuilder = ArgBuilder.create()) : ArgBuilder by builder {
    fun size(value: Int): AvatarUrlArgs {
       addArg("size", value); return this;
    }
  }
}

abstract class SearchResultItem : GraphType() {
  protected open val issue: List<Issue> by lazy { throw SchemaStub() }

  protected open val pullrequest: List<PullRequest> by lazy { throw SchemaStub() }

  protected open val repository: List<Repository> by lazy { throw SchemaStub() }

  protected open val user: List<User> by lazy { throw SchemaStub() }

  protected open val organization: List<Organization> by lazy { throw SchemaStub() }
}

abstract class ReviewDismissalAllowanceActor : GraphType() {
  protected open val user: List<User> by lazy { throw SchemaStub() }

  protected open val team: List<Team> by lazy { throw SchemaStub() }
}

abstract class RenamedTitleSubject : GraphType() {
  protected open val issue: List<Issue> by lazy { throw SchemaStub() }

  protected open val pullrequest: List<PullRequest> by lazy { throw SchemaStub() }
}

abstract class ReferencedSubject : GraphType() {
  protected open val issue: List<Issue> by lazy { throw SchemaStub() }

  protected open val pullrequest: List<PullRequest> by lazy { throw SchemaStub() }
}

abstract class PushAllowanceActor : GraphType() {
  protected open val user: List<User> by lazy { throw SchemaStub() }

  protected open val team: List<Team> by lazy { throw SchemaStub() }
}

abstract class PullRequestTimelineItem : GraphType() {
  protected open val commit: List<Commit> by lazy { throw SchemaStub() }

  protected open val commitcommentthread: List<CommitCommentThread> by lazy { throw SchemaStub() }

  protected open val pullrequestreview: List<PullRequestReview> by lazy { throw SchemaStub() }

  protected open val pullrequestreviewthread: List<PullRequestReviewThread> by lazy { throw SchemaStub() }

  protected open val pullrequestreviewcomment: List<PullRequestReviewComment> by lazy { throw SchemaStub() }

  protected open val issuecomment: List<IssueComment> by lazy { throw SchemaStub() }

  protected open val closedevent: List<ClosedEvent> by lazy { throw SchemaStub() }

  protected open val reopenedevent: List<ReopenedEvent> by lazy { throw SchemaStub() }

  protected open val subscribedevent: List<SubscribedEvent> by lazy { throw SchemaStub() }

  protected open val unsubscribedevent: List<UnsubscribedEvent> by lazy { throw SchemaStub() }

  protected open val mergedevent: List<MergedEvent> by lazy { throw SchemaStub() }

  protected open val referencedevent: List<ReferencedEvent> by lazy { throw SchemaStub() }

  protected open val assignedevent: List<AssignedEvent> by lazy { throw SchemaStub() }

  protected open val unassignedevent: List<UnassignedEvent> by lazy { throw SchemaStub() }

  protected open val labeledevent: List<LabeledEvent> by lazy { throw SchemaStub() }

  protected open val unlabeledevent: List<UnlabeledEvent> by lazy { throw SchemaStub() }

  protected open val milestonedevent: List<MilestonedEvent> by lazy { throw SchemaStub() }

  protected open val demilestonedevent: List<DemilestonedEvent> by lazy { throw SchemaStub() }

  protected open val renamedtitleevent: List<RenamedTitleEvent> by lazy { throw SchemaStub() }

  protected open val lockedevent: List<LockedEvent> by lazy { throw SchemaStub() }

  protected open val unlockedevent: List<UnlockedEvent> by lazy { throw SchemaStub() }

  protected open val deployedevent: List<DeployedEvent> by lazy { throw SchemaStub() }

  protected open val headrefdeletedevent: List<HeadRefDeletedEvent> by lazy { throw SchemaStub() }

  protected open val headrefrestoredevent: List<HeadRefRestoredEvent> by lazy { throw SchemaStub() }

  protected open val headrefforcepushedevent: List<HeadRefForcePushedEvent> by lazy { throw SchemaStub() }

  protected open val baserefforcepushedevent: List<BaseRefForcePushedEvent> by lazy { throw SchemaStub() }

  protected open val reviewrequestedevent: List<ReviewRequestedEvent> by lazy { throw SchemaStub() }

  protected open val reviewrequestremovedevent: List<ReviewRequestRemovedEvent> by lazy { throw SchemaStub() }

  protected open val reviewdismissedevent: List<ReviewDismissedEvent> by lazy { throw SchemaStub() }
}

abstract class ProjectCardItem : GraphType() {
  protected open val issue: List<Issue> by lazy { throw SchemaStub() }

  protected open val pullrequest: List<PullRequest> by lazy { throw SchemaStub() }
}

abstract class MilestoneItem : GraphType() {
  protected open val issue: List<Issue> by lazy { throw SchemaStub() }

  protected open val pullrequest: List<PullRequest> by lazy { throw SchemaStub() }
}

abstract class IssueTimelineItem : GraphType() {
  protected open val commit: List<Commit> by lazy { throw SchemaStub() }

  protected open val issuecomment: List<IssueComment> by lazy { throw SchemaStub() }

  protected open val closedevent: List<ClosedEvent> by lazy { throw SchemaStub() }

  protected open val reopenedevent: List<ReopenedEvent> by lazy { throw SchemaStub() }

  protected open val subscribedevent: List<SubscribedEvent> by lazy { throw SchemaStub() }

  protected open val unsubscribedevent: List<UnsubscribedEvent> by lazy { throw SchemaStub() }

  protected open val referencedevent: List<ReferencedEvent> by lazy { throw SchemaStub() }

  protected open val assignedevent: List<AssignedEvent> by lazy { throw SchemaStub() }

  protected open val unassignedevent: List<UnassignedEvent> by lazy { throw SchemaStub() }

  protected open val labeledevent: List<LabeledEvent> by lazy { throw SchemaStub() }

  protected open val unlabeledevent: List<UnlabeledEvent> by lazy { throw SchemaStub() }

  protected open val milestonedevent: List<MilestonedEvent> by lazy { throw SchemaStub() }

  protected open val demilestonedevent: List<DemilestonedEvent> by lazy { throw SchemaStub() }

  protected open val renamedtitleevent: List<RenamedTitleEvent> by lazy { throw SchemaStub() }

  protected open val lockedevent: List<LockedEvent> by lazy { throw SchemaStub() }

  protected open val unlockedevent: List<UnlockedEvent> by lazy { throw SchemaStub() }
}

abstract class IssueOrPullRequest : GraphType() {
  protected open val issue: List<Issue> by lazy { throw SchemaStub() }

  protected open val pullrequest: List<PullRequest> by lazy { throw SchemaStub() }
}

abstract class X509Certificate : GraphType() {
  protected open val value: CustomScalar by lazy { throw SchemaStub() }
}

abstract class URI : GraphType() {
  protected open val value: CustomScalar by lazy { throw SchemaStub() }
}

abstract class HTML : GraphType() {
  protected open val value: CustomScalar by lazy { throw SchemaStub() }
}

abstract class GitTimestamp : GraphType() {
  protected open val value: CustomScalar by lazy { throw SchemaStub() }
}

abstract class GitObjectID : GraphType() {
  protected open val value: CustomScalar by lazy { throw SchemaStub() }
}

abstract class DateTime : GraphType() {
  protected open val value: CustomScalar by lazy { throw SchemaStub() }
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

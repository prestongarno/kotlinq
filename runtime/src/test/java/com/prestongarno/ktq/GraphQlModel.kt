@file:Suppress("unused")

package com.prestongarno.ktq

interface Actor : QType {
  val avatarUrl: Config<AvatarUrlArgs, URI>

  val login: Stub<String>

  val resourcePath: Stub<URI>

  val url: Stub<URI>

  class AvatarUrlArgs(args: ArgBuilder<URI> = ArgBuilder.create()) : ArgBuilder<URI> by args {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }
}

interface Assignable : QType {
  val assignees: ConfigType<AssigneesArgs, UserConnection>

  class AssigneesArgs(args: TypeArgBuilder<UserConnection, QModel<UserConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<UserConnection, QModel<UserConnection>> by args {
    fun first(value: Int): AssigneesArgs = apply { addArg("first", value) }


    fun after(value: String): AssigneesArgs = apply { addArg("after", value) }


    fun last(value: Int): AssigneesArgs = apply { addArg("last", value) }


    fun before(value: String): AssigneesArgs = apply { addArg("before", value) }

  }
}

interface Closable : QType {
  val closed: Stub<Boolean>
}

interface Comment : QType {
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

interface Deletable : QType {
  val viewerCanDelete: Stub<Boolean>
}

interface GitObject : QType {
  val abbreviatedOid: Stub<String>

  val commitResourcePath: Stub<URI>

  val commitUrl: Stub<URI>

  val id: Stub<String>

  val oid: Stub<GitObjectID>

  val repository: InitStub<Repository>
}

interface GitSignature : QType {
  val email: Stub<String>

  val isValid: Stub<Boolean>

  val payload: Stub<String>

  val signature: Stub<String>

  val signer: InitStub<User>

  val state: Stub<GitSignatureState>
}

interface Labelable : QType {
  val labels: ConfigType<LabelsArgs, LabelConnection>

  class LabelsArgs(args: TypeArgBuilder<LabelConnection, QModel<LabelConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<LabelConnection, QModel<LabelConnection>> by args {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }
}

interface Lockable : QType {
  val locked: Stub<Boolean>
}

interface Node : QType {
  val id: Stub<String>
}

interface ProjectOwner : QType {
  val id: Stub<String>

  val project: ConfigType<ProjectArgs, Project>

  val projects: ConfigType<ProjectsArgs, ProjectConnection>

  val projectsResourcePath: Stub<URI>

  val projectsUrl: Stub<URI>

  val viewerCanCreateProjects: Stub<Boolean>

  class ProjectArgs(args: TypeArgBuilder<Project, QModel<Project>> = TypeArgBuilder.create()) : TypeArgBuilder<Project, QModel<Project>> by args {
    fun number(value: Int): ProjectArgs = apply { addArg("number", value) }

  }

  class ProjectsArgs(args: TypeArgBuilder<ProjectConnection, QModel<ProjectConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ProjectConnection, QModel<ProjectConnection>> by args {
    fun first(value: Int): ProjectsArgs = apply { addArg("first", value) }


    fun after(value: String): ProjectsArgs = apply { addArg("after", value) }


    fun last(value: Int): ProjectsArgs = apply { addArg("last", value) }


    fun before(value: String): ProjectsArgs = apply { addArg("before", value) }


    fun orderBy(value: ProjectOrder): ProjectsArgs = apply { addArg("orderBy", value) }


    fun search(value: String): ProjectsArgs = apply { addArg("search", value) }


    fun states(value: ProjectState): ProjectsArgs = apply { addArg("states", value) }

  }
}

interface Reactable : QType {
  val databaseId: Stub<Int>

  val id: Stub<String>

  val reactionGroups: InitStub<ReactionGroup>

  val reactions: ConfigType<ReactionsArgs, ReactionConnection>

  val viewerCanReact: Stub<Boolean>

  class ReactionsArgs(args: TypeArgBuilder<ReactionConnection, QModel<ReactionConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ReactionConnection, QModel<ReactionConnection>> by args {
    fun first(value: Int): ReactionsArgs = apply { addArg("first", value) }


    fun after(value: String): ReactionsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReactionsArgs = apply { addArg("last", value) }


    fun before(value: String): ReactionsArgs = apply { addArg("before", value) }


    fun content(value: ReactionContent): ReactionsArgs = apply { addArg("content", value) }


    fun orderBy(value: ReactionOrder): ReactionsArgs = apply { addArg("orderBy", value) }

  }
}

interface RepositoryInfo : QType {
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

interface RepositoryNode : QType {
  val repository: InitStub<Repository>
}

interface RepositoryOwner : QType {
  //val avatarUrl: Config<AvatarUrlArgs, URI>

  val id: Stub<String>

  val login: Stub<String>

  val pinnedRepositories: ConfigType<PinnedRepositoriesArgs, RepositoryConnection>

  val repositories: ConfigType<RepositoriesArgs, RepositoryConnection>

  val repository: ConfigType<RepositoryArgs, Repository>

  val resourcePath: Stub<URI>

  val url: Stub<URI>

  class AvatarUrlArgs(args: ArgBuilder<URI> = ArgBuilder.create()) : ArgBuilder<URI> by args {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }

  class PinnedRepositoriesArgs(args: TypeArgBuilder<RepositoryConnection, QModel<RepositoryConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<RepositoryConnection, QModel<RepositoryConnection>> by args {
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

  class RepositoriesArgs(args: TypeArgBuilder<RepositoryConnection, QModel<RepositoryConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<RepositoryConnection, QModel<RepositoryConnection>> by args {
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

  class RepositoryArgs(args: TypeArgBuilder<Repository, QModel<Repository>> = TypeArgBuilder.create()) : TypeArgBuilder<Repository, QModel<Repository>> by args {
    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }
}

interface Starrable : QType {
  val id: Stub<String>

  val stargazers: ConfigType<StargazersArgs, StargazerConnection>

  val viewerHasStarred: Stub<Boolean>

  class StargazersArgs(args: TypeArgBuilder<StargazerConnection, QModel<StargazerConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<StargazerConnection, QModel<StargazerConnection>> by args {
    fun first(value: Int): StargazersArgs = apply { addArg("first", value) }


    fun after(value: String): StargazersArgs = apply { addArg("after", value) }


    fun last(value: Int): StargazersArgs = apply { addArg("last", value) }


    fun before(value: String): StargazersArgs = apply { addArg("before", value) }


    fun orderBy(value: StarOrder): StargazersArgs = apply { addArg("orderBy", value) }

  }
}

interface Subscribable : QType {
  val viewerCanSubscribe: Stub<Boolean>

  val viewerSubscription: Stub<SubscriptionState>
}

interface UniformResourceLocatable : QType {
  val resourcePath: Stub<URI>

  val url: Stub<URI>
}

interface Updatable : QType {
  val viewerCanUpdate: Stub<Boolean>
}

interface UpdatableComment : QType {
  val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason>
}

object UserEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<User> by  lazy { typeStub<User>() } 
}

object UserConnection : QType {
  val edges: InitStub<UserEdge> by  lazy { typeStub<UserEdge>() } 

  val nodes: InitStub<User> by  lazy { typeStub<User>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object User : QType, UniformResourceLocatable, RepositoryOwner, Actor, Node {
  override val avatarUrl: Config<Actor.AvatarUrlArgs, URI> by  lazy { configStub(Actor.AvatarUrlArgs()) }

  val bio: Stub<String> by  lazy { stub<String>() } 

  val bioHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val company: Stub<String> by  lazy { stub<String>() } 

  val companyHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val contributedRepositories: ConfigType<ContributedRepositoriesArgs, RepositoryConnection> by  lazy { typeConfigStub(ContributedRepositoriesArgs()) } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  val email: Stub<String> by  lazy { stub<String>() } 

  val followers: ConfigType<FollowersArgs, FollowerConnection> by  lazy { typeConfigStub(FollowersArgs()) } 

  val following: ConfigType<FollowingArgs, FollowingConnection> by  lazy { typeConfigStub(FollowingArgs()) } 

  val gist: ConfigType<GistArgs, Gist> by  lazy { typeConfigStub(GistArgs()) } 

  val gists: ConfigType<GistsArgs, GistConnection> by  lazy { typeConfigStub(GistsArgs()) } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val isBountyHunter: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isCampusExpert: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isDeveloperProgramMember: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isEmployee: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isHireable: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isInvoiced: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isSiteAdmin: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isViewer: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val issues: ConfigType<IssuesArgs, IssueConnection> by  lazy { typeConfigStub(IssuesArgs()) } 

  val location: Stub<String> by  lazy { stub<String>() } 

  override val login: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val organization: ConfigType<OrganizationArgs, Organization> by  lazy { typeConfigStub(OrganizationArgs()) } 

  val organizations: ConfigType<OrganizationsArgs, OrganizationConnection> by  lazy { typeConfigStub(OrganizationsArgs()) } 

  override val pinnedRepositories: ConfigType<RepositoryOwner.PinnedRepositoriesArgs, RepositoryConnection> by  lazy { typeConfigStub(RepositoryOwner.PinnedRepositoriesArgs()) } 

  val pullRequests: ConfigType<PullRequestsArgs, PullRequestConnection> by  lazy { typeConfigStub(PullRequestsArgs()) } 

  override val repositories: ConfigType<RepositoryOwner.RepositoriesArgs, RepositoryConnection> by  lazy { typeConfigStub(RepositoryOwner.RepositoriesArgs()) } 

  override val repository: ConfigType<RepositoryOwner.RepositoryArgs, Repository> by  lazy { typeConfigStub(RepositoryOwner.RepositoryArgs()) } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val starredRepositories: ConfigType<StarredRepositoriesArgs, StarredRepositoryConnection> by  lazy { typeConfigStub(StarredRepositoriesArgs()) } 

  val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 

  val viewerCanFollow: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val viewerIsFollowing: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val watching: ConfigType<WatchingArgs, RepositoryConnection> by  lazy { typeConfigStub(WatchingArgs()) } 

  val websiteUrl: Stub<URI> by  lazy { stub<URI>() } 

  class ContributedRepositoriesArgs(args: TypeArgBuilder<RepositoryConnection, QModel<RepositoryConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<RepositoryConnection, QModel<RepositoryConnection>> by args {
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

  class FollowersArgs(args: TypeArgBuilder<FollowerConnection, QModel<FollowerConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<FollowerConnection, QModel<FollowerConnection>> by args {
    fun first(value: Int): FollowersArgs = apply { addArg("first", value) }


    fun after(value: String): FollowersArgs = apply { addArg("after", value) }


    fun last(value: Int): FollowersArgs = apply { addArg("last", value) }


    fun before(value: String): FollowersArgs = apply { addArg("before", value) }

  }

  class FollowingArgs(args: TypeArgBuilder<FollowingConnection, QModel<FollowingConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<FollowingConnection, QModel<FollowingConnection>> by args {
    fun first(value: Int): FollowingArgs = apply { addArg("first", value) }


    fun after(value: String): FollowingArgs = apply { addArg("after", value) }


    fun last(value: Int): FollowingArgs = apply { addArg("last", value) }


    fun before(value: String): FollowingArgs = apply { addArg("before", value) }

  }

  class GistArgs(args: TypeArgBuilder<Gist, QModel<Gist>> = TypeArgBuilder.create()) : TypeArgBuilder<Gist, QModel<Gist>> by args {
    fun name(value: String): GistArgs = apply { addArg("name", value) }

  }

  class GistsArgs(args: TypeArgBuilder<GistConnection, QModel<GistConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<GistConnection, QModel<GistConnection>> by args {
    fun first(value: Int): GistsArgs = apply { addArg("first", value) }


    fun after(value: String): GistsArgs = apply { addArg("after", value) }


    fun last(value: Int): GistsArgs = apply { addArg("last", value) }


    fun before(value: String): GistsArgs = apply { addArg("before", value) }


    fun privacy(value: GistPrivacy): GistsArgs = apply { addArg("privacy", value) }

  }

  class IssuesArgs(args: TypeArgBuilder<IssueConnection, QModel<IssueConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<IssueConnection, QModel<IssueConnection>> by args {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class OrganizationArgs(args: TypeArgBuilder<Organization, QModel<Organization>> = TypeArgBuilder.create()) : TypeArgBuilder<Organization, QModel<Organization>> by args {
    fun login(value: String): OrganizationArgs = apply { addArg("login", value) }

  }

  class OrganizationsArgs(args: TypeArgBuilder<OrganizationConnection, QModel<OrganizationConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<OrganizationConnection, QModel<OrganizationConnection>> by args {
    fun first(value: Int): OrganizationsArgs = apply { addArg("first", value) }


    fun after(value: String): OrganizationsArgs = apply { addArg("after", value) }


    fun last(value: Int): OrganizationsArgs = apply { addArg("last", value) }


    fun before(value: String): OrganizationsArgs = apply { addArg("before", value) }

  }

  class PullRequestsArgs(args: TypeArgBuilder<PullRequestConnection, QModel<PullRequestConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<PullRequestConnection, QModel<PullRequestConnection>> by args {
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

  class StarredRepositoriesArgs(args: TypeArgBuilder<StarredRepositoryConnection, QModel<StarredRepositoryConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<StarredRepositoryConnection, QModel<StarredRepositoryConnection>> by args {
    fun first(value: Int): StarredRepositoriesArgs = apply { addArg("first", value) }


    fun after(value: String): StarredRepositoriesArgs = apply { addArg("after", value) }


    fun last(value: Int): StarredRepositoriesArgs = apply { addArg("last", value) }


    fun before(value: String): StarredRepositoriesArgs = apply { addArg("before", value) }


    fun ownedByViewer(value: Boolean): StarredRepositoriesArgs =
          apply { addArg("ownedByViewer", value) }


    fun orderBy(value: StarOrder): StarredRepositoriesArgs = apply { addArg("orderBy", value) }

  }

  class WatchingArgs(args: TypeArgBuilder<RepositoryConnection, QModel<RepositoryConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<RepositoryConnection, QModel<RepositoryConnection>> by args {
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

object UpdateTopicsPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val invalidTopicNames: Stub<String> by  lazy { stub<String>() } 

  val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 
}

object UpdateSubscriptionPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val subscribable: InitStub<Subscribable> by  lazy { typeStub<Subscribable>() } 
}

object UpdatePullRequestReviewPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val pullRequestReview: InitStub<PullRequestReview> by  lazy { typeStub<PullRequestReview>() } 
}

object UpdatePullRequestReviewCommentPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val pullRequestReviewComment: InitStub<PullRequestReviewComment> by  lazy { typeStub<PullRequestReviewComment>() } 
}

object UpdateProjectPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val project: InitStub<Project> by  lazy { typeStub<Project>() } 
}

object UpdateProjectColumnPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val projectColumn: InitStub<ProjectColumn> by  lazy { typeStub<ProjectColumn>() } 
}

object UpdateProjectCardPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val projectCard: InitStub<ProjectCard> by  lazy { typeStub<ProjectCard>() } 
}

object UnsubscribedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val subscribable: InitStub<Subscribable> by  lazy { typeStub<Subscribable>() } 
}

object UnlockedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val lockable: InitStub<Lockable> by  lazy { typeStub<Lockable>() } 
}

object UnlabeledEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val label: InitStub<Label> by  lazy { typeStub<Label>() } 

  val labelable: InitStub<Labelable> by  lazy { typeStub<Labelable>() } 
}

object UnknownSignature : QType, GitSignature {
  override val email: Stub<String> by  lazy { stub<String>() } 

  override val isValid: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val payload: Stub<String> by  lazy { stub<String>() } 

  override val signature: Stub<String> by  lazy { stub<String>() } 

  override val signer: InitStub<User> by  lazy { typeStub<User>() } 

  override val state: Stub<GitSignatureState> by  lazy { stub<GitSignatureState>() } 
}

object UnassignedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val assignable: InitStub<Assignable> by  lazy { typeStub<Assignable>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val user: InitStub<User> by  lazy { typeStub<User>() } 
}

object TreeEntry : QType {
  val mode: Stub<Int> by  lazy { stub<Int>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val objectVal: InitStub<GitObject> by  lazy { typeStub<GitObject>() } 

  val oid: Stub<GitObjectID> by  lazy { stub<GitObjectID>() } 

  val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val type: Stub<String> by  lazy { stub<String>() } 
}

object Tree : QType, GitObject, Node {
  override val abbreviatedOid: Stub<String> by  lazy { stub<String>() } 

  override val commitResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val commitUrl: Stub<URI> by  lazy { stub<URI>() } 

  val entries: InitStub<TreeEntry> by  lazy { typeStub<TreeEntry>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  override val oid: Stub<GitObjectID> by  lazy { stub<GitObjectID>() } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 
}

object Topic : QType, Node {
  override val id: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val relatedTopics: InitStub<Topic> by  lazy { typeStub<Topic>() } 
}

object TeamEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Team> by  lazy { typeStub<Team>() } 
}

object TeamConnection : QType {
  val edges: InitStub<TeamEdge> by  lazy { typeStub<TeamEdge>() } 

  val nodes: InitStub<Team> by  lazy { typeStub<Team>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object Team : QType, Node {
  val description: Stub<String> by  lazy { stub<String>() } 

  val editTeamResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val editTeamUrl: Stub<URI> by  lazy { stub<URI>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val invitations: ConfigType<InvitationsArgs, OrganizationInvitationConnection> by  lazy { typeConfigStub(InvitationsArgs()) } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val organization: InitStub<Organization> by  lazy { typeStub<Organization>() } 

  val privacy: Stub<TeamPrivacy> by  lazy { stub<TeamPrivacy>() } 

  val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val slug: Stub<String> by  lazy { stub<String>() } 

  val url: Stub<URI> by  lazy { stub<URI>() } 

  class InvitationsArgs(args: TypeArgBuilder<OrganizationInvitationConnection, QModel<OrganizationInvitationConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<OrganizationInvitationConnection, QModel<OrganizationInvitationConnection>> by args {
    fun first(value: Int): InvitationsArgs = apply { addArg("first", value) }


    fun after(value: String): InvitationsArgs = apply { addArg("after", value) }


    fun last(value: Int): InvitationsArgs = apply { addArg("last", value) }


    fun before(value: String): InvitationsArgs = apply { addArg("before", value) }

  }
}

object Tag : QType, GitObject, Node {
  override val abbreviatedOid: Stub<String> by  lazy { stub<String>() } 

  override val commitResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val commitUrl: Stub<URI> by  lazy { stub<URI>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val message: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  override val oid: Stub<GitObjectID> by  lazy { stub<GitObjectID>() } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val tagger: InitStub<GitActor> by  lazy { typeStub<GitActor>() } 

  val target: InitStub<GitObject> by  lazy { typeStub<GitObject>() } 
}

object SuggestedReviewer : QType {
  val isAuthor: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isCommenter: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val reviewer: InitStub<User> by  lazy { typeStub<User>() } 
}

object SubscribedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val subscribable: InitStub<Subscribable> by  lazy { typeStub<Subscribable>() } 
}

object SubmitPullRequestReviewPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val pullRequestReview: InitStub<PullRequestReview> by  lazy { typeStub<PullRequestReview>() } 
}

object StatusContext : QType, Node {
  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val context: Stub<String> by  lazy { stub<String>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val creator: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val description: Stub<String> by  lazy { stub<String>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val state: Stub<StatusState> by  lazy { stub<StatusState>() } 

  val targetUrl: Stub<URI> by  lazy { stub<URI>() } 
}

object Status : QType, Node {
  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val context: ConfigType<ContextArgs, StatusContext> by  lazy { typeConfigStub(ContextArgs()) } 

  val contexts: InitStub<StatusContext> by  lazy { typeStub<StatusContext>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val state: Stub<StatusState> by  lazy { stub<StatusState>() } 

  class ContextArgs(args: TypeArgBuilder<StatusContext, QModel<StatusContext>> = TypeArgBuilder.create()) : TypeArgBuilder<StatusContext, QModel<StatusContext>> by args {
    fun name(value: String): ContextArgs = apply { addArg("name", value) }

  }
}

object StarredRepositoryEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val starredAt: Stub<DateTime> by  lazy { stub<DateTime>() } 
}

object StarredRepositoryConnection : QType {
  val edges: InitStub<StarredRepositoryEdge> by  lazy { typeStub<StarredRepositoryEdge>() } 

  val nodes: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object StargazerEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<User> by  lazy { typeStub<User>() } 

  val starredAt: Stub<DateTime> by  lazy { stub<DateTime>() } 
}

object StargazerConnection : QType {
  val edges: InitStub<StargazerEdge> by  lazy { typeStub<StargazerEdge>() } 

  val nodes: InitStub<User> by  lazy { typeStub<User>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object SmimeSignature : QType, GitSignature {
  override val email: Stub<String> by  lazy { stub<String>() } 

  override val isValid: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val payload: Stub<String> by  lazy { stub<String>() } 

  override val signature: Stub<String> by  lazy { stub<String>() } 

  override val signer: InitStub<User> by  lazy { typeStub<User>() } 

  override val state: Stub<GitSignatureState> by  lazy { stub<GitSignatureState>() } 
}

object SearchResultItemEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<SearchResultItem> by  lazy { typeStub<SearchResultItem>() } 
}

object SearchResultItemConnection : QType {
  val codeCount: Stub<Int> by  lazy { stub<Int>() } 

  val edges: InitStub<SearchResultItemEdge> by  lazy { typeStub<SearchResultItemEdge>() } 

  val issueCount: Stub<Int> by  lazy { stub<Int>() } 

  val nodes: InitStub<SearchResultItem> by  lazy { typeStub<SearchResultItem>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val repositoryCount: Stub<Int> by  lazy { stub<Int>() } 

  val userCount: Stub<Int> by  lazy { stub<Int>() } 

  val wikiCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ReviewRequestedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val subject: InitStub<User> by  lazy { typeStub<User>() } 
}

object ReviewRequestRemovedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val subject: InitStub<User> by  lazy { typeStub<User>() } 
}

object ReviewRequestEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<ReviewRequest> by  lazy { typeStub<ReviewRequest>() } 
}

object ReviewRequestConnection : QType {
  val edges: InitStub<ReviewRequestEdge> by  lazy { typeStub<ReviewRequestEdge>() } 

  val nodes: InitStub<ReviewRequest> by  lazy { typeStub<ReviewRequest>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ReviewRequest : QType, Node {
  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val reviewer: InitStub<User> by  lazy { typeStub<User>() } 
}

object ReviewDismissedEvent : QType, UniformResourceLocatable, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val message: Stub<String> by  lazy { stub<String>() } 

  val messageHtml: Stub<HTML> by  lazy { stub<HTML>() } 

  val previousReviewState: Stub<PullRequestReviewState> by  lazy { stub<PullRequestReviewState>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val pullRequestCommit: InitStub<PullRequestCommit> by  lazy { typeStub<PullRequestCommit>() } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val review: InitStub<PullRequestReview> by  lazy { typeStub<PullRequestReview>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 
}

object ReviewDismissalAllowanceEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<ReviewDismissalAllowance> by  lazy { typeStub<ReviewDismissalAllowance>() } 
}

object ReviewDismissalAllowanceConnection : QType {
  val edges: InitStub<ReviewDismissalAllowanceEdge> by  lazy { typeStub<ReviewDismissalAllowanceEdge>() } 

  val nodes: InitStub<ReviewDismissalAllowance> by  lazy { typeStub<ReviewDismissalAllowance>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ReviewDismissalAllowance : QType, Node {
  val actor: InitStub<ReviewDismissalAllowanceActor> by  lazy { typeStub<ReviewDismissalAllowanceActor>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val protectedBranch: InitStub<ProtectedBranch> by  lazy { typeStub<ProtectedBranch>() } 
}

object RequestReviewsPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val requestedReviewersEdge: InitStub<UserEdge> by  lazy { typeStub<UserEdge>() } 
}

object RepositoryTopicEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<RepositoryTopic> by  lazy { typeStub<RepositoryTopic>() } 
}

object RepositoryTopicConnection : QType {
  val edges: InitStub<RepositoryTopicEdge> by  lazy { typeStub<RepositoryTopicEdge>() } 

  val nodes: InitStub<RepositoryTopic> by  lazy { typeStub<RepositoryTopic>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object RepositoryTopic : QType, UniformResourceLocatable, Node {
  override val id: Stub<String> by  lazy { stub<String>() } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val topic: InitStub<Topic> by  lazy { typeStub<Topic>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 
}

object RepositoryInvitationRepository : QType, RepositoryInfo {
  override val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val description: Stub<String> by  lazy { stub<String>() } 

  override val descriptionHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  override val hasIssuesEnabled: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val hasWikiEnabled: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val homepageUrl: Stub<URI> by  lazy { stub<URI>() } 

  override val isFork: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val isLocked: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val isMirror: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val isPrivate: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val license: Stub<String> by  lazy { stub<String>() } 

  override val lockReason: Stub<RepositoryLockReason> by  lazy { stub<RepositoryLockReason>() } 

  override val mirrorUrl: Stub<URI> by  lazy { stub<URI>() } 

  override val name: Stub<String> by  lazy { stub<String>() } 

  override val nameWithOwner: Stub<String> by  lazy { stub<String>() } 

  override val owner: InitStub<RepositoryOwner> by  lazy { typeStub<RepositoryOwner>() } 

  override val pushedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 
}

object RepositoryInvitation : QType, Node {
  override val id: Stub<String> by  lazy { stub<String>() } 

  val invitee: InitStub<User> by  lazy { typeStub<User>() } 

  val inviter: InitStub<User> by  lazy { typeStub<User>() } 

  val repository: InitStub<RepositoryInvitationRepository> by  lazy { typeStub<RepositoryInvitationRepository>() } 
}

object RepositoryEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Repository> by  lazy { typeStub<Repository>() } 
}

object RepositoryConnection : QType {
  val edges: InitStub<RepositoryEdge> by  lazy { typeStub<RepositoryEdge>() } 

  val nodes: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 

  val totalDiskUsage: Stub<Int> by  lazy { stub<Int>() } 
}

object Repository : QType, RepositoryInfo, UniformResourceLocatable, Starrable, Subscribable, ProjectOwner, Node {
  val codeOfConduct: InitStub<CodeOfConduct> by  lazy { typeStub<CodeOfConduct>() } 

  val commitComments: ConfigType<CommitCommentsArgs, CommitCommentConnection> by  lazy { typeConfigStub(CommitCommentsArgs()) } 

  override val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  val defaultBranchRef: InitStub<Ref> by  lazy { typeStub<Ref>() } 

  val deployments: ConfigType<DeploymentsArgs, DeploymentConnection> by  lazy { typeConfigStub(DeploymentsArgs()) } 

  override val description: Stub<String> by  lazy { stub<String>() } 

  override val descriptionHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val diskUsage: Stub<Int> by  lazy { stub<Int>() } 

  val forks: ConfigType<ForksArgs, RepositoryConnection> by  lazy { typeConfigStub(ForksArgs()) } 

  override val hasIssuesEnabled: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val hasWikiEnabled: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val homepageUrl: Stub<URI> by  lazy { stub<URI>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  override val isFork: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val isLocked: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val isMirror: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val isPrivate: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val issue: ConfigType<IssueArgs, Issue> by  lazy { typeConfigStub(IssueArgs()) } 

  val issueOrPullRequest: ConfigType<IssueOrPullRequestArgs, IssueOrPullRequest> by  lazy { typeConfigStub(IssueOrPullRequestArgs()) } 

  val issues: ConfigType<IssuesArgs, IssueConnection> by  lazy { typeConfigStub(IssuesArgs()) } 

  val label: ConfigType<LabelArgs, Label> by  lazy { typeConfigStub(LabelArgs()) } 

  val labels: ConfigType<LabelsArgs, LabelConnection> by  lazy { typeConfigStub(LabelsArgs()) } 

  val languages: ConfigType<LanguagesArgs, LanguageConnection> by  lazy { typeConfigStub(LanguagesArgs()) } 

  override val license: Stub<String> by  lazy { stub<String>() } 

  override val lockReason: Stub<RepositoryLockReason> by  lazy { stub<RepositoryLockReason>() } 

  val mentionableUsers: ConfigType<MentionableUsersArgs, UserConnection> by  lazy { typeConfigStub(MentionableUsersArgs()) } 

  val milestone: ConfigType<MilestoneArgs, Milestone> by  lazy { typeConfigStub(MilestoneArgs()) } 

  val milestones: ConfigType<MilestonesArgs, MilestoneConnection> by  lazy { typeConfigStub(MilestonesArgs()) } 

  override val mirrorUrl: Stub<URI> by  lazy { stub<URI>() } 

  override val name: Stub<String> by  lazy { stub<String>() } 

  override val nameWithOwner: Stub<String> by  lazy { stub<String>() } 

  val objectVal: ConfigType<ObjectValArgs, GitObject> by  lazy { typeConfigStub(ObjectValArgs()) } 

  override val owner: InitStub<RepositoryOwner> by  lazy { typeStub<RepositoryOwner>() } 

  val parent: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val primaryLanguage: InitStub<Language> by  lazy { typeStub<Language>() } 

  override val project: ConfigType<ProjectOwner.ProjectArgs, Project> by  lazy { typeConfigStub(ProjectOwner.ProjectArgs()) } 

  override val projects: ConfigType<ProjectOwner.ProjectsArgs, ProjectConnection> by  lazy { typeConfigStub(ProjectOwner.ProjectsArgs()) } 

  override val projectsResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val projectsUrl: Stub<URI> by  lazy { stub<URI>() } 

  val protectedBranches: ConfigType<ProtectedBranchesArgs, ProtectedBranchConnection> by  lazy { typeConfigStub(ProtectedBranchesArgs()) } 

  val pullRequest: ConfigType<PullRequestArgs, PullRequest> by  lazy { typeConfigStub(PullRequestArgs()) } 

  val pullRequests: ConfigType<PullRequestsArgs, PullRequestConnection> by  lazy { typeConfigStub(PullRequestsArgs()) } 

  override val pushedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val ref: ConfigType<RefArgs, Ref> by  lazy { typeConfigStub(RefArgs()) } 

  val refs: ConfigType<RefsArgs, RefConnection> by  lazy { typeConfigStub(RefsArgs()) } 

  val releases: ConfigType<ReleasesArgs, ReleaseConnection> by  lazy { typeConfigStub(ReleasesArgs()) } 

  val repositoryTopics: ConfigType<RepositoryTopicsArgs, RepositoryTopicConnection> by  lazy { typeConfigStub(RepositoryTopicsArgs()) } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val stargazers: ConfigType<Starrable.StargazersArgs, StargazerConnection> by  lazy { typeConfigStub(Starrable.StargazersArgs()) } 

  override val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 

  val viewerCanAdminister: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanCreateProjects: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanSubscribe: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val viewerCanUpdateTopics: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerHasStarred: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerSubscription: Stub<SubscriptionState> by  lazy { stub<SubscriptionState>() } 

  val watchers: ConfigType<WatchersArgs, UserConnection> by  lazy { typeConfigStub(WatchersArgs()) } 

  class CommitCommentsArgs(args: TypeArgBuilder<CommitCommentConnection, QModel<CommitCommentConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<CommitCommentConnection, QModel<CommitCommentConnection>> by args {
    fun first(value: Int): CommitCommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommitCommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommitCommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommitCommentsArgs = apply { addArg("before", value) }

  }

  class DeploymentsArgs(args: TypeArgBuilder<DeploymentConnection, QModel<DeploymentConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<DeploymentConnection, QModel<DeploymentConnection>> by args {
    fun first(value: Int): DeploymentsArgs = apply { addArg("first", value) }


    fun after(value: String): DeploymentsArgs = apply { addArg("after", value) }


    fun last(value: Int): DeploymentsArgs = apply { addArg("last", value) }


    fun before(value: String): DeploymentsArgs = apply { addArg("before", value) }

  }

  class ForksArgs(args: TypeArgBuilder<RepositoryConnection, QModel<RepositoryConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<RepositoryConnection, QModel<RepositoryConnection>> by args {
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

  class IssueArgs(args: TypeArgBuilder<Issue, QModel<Issue>> = TypeArgBuilder.create()) : TypeArgBuilder<Issue, QModel<Issue>> by args {
    fun number(value: Int): IssueArgs = apply { addArg("number", value) }

  }

  class IssueOrPullRequestArgs(args: TypeArgBuilder<IssueOrPullRequest, QModel<IssueOrPullRequest>> = TypeArgBuilder.create()) : TypeArgBuilder<IssueOrPullRequest, QModel<IssueOrPullRequest>> by args {
    fun number(value: Int): IssueOrPullRequestArgs = apply { addArg("number", value) }

  }

  class IssuesArgs(args: TypeArgBuilder<IssueConnection, QModel<IssueConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<IssueConnection, QModel<IssueConnection>> by args {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class LabelArgs(args: TypeArgBuilder<Label, QModel<Label>> = TypeArgBuilder.create()) : TypeArgBuilder<Label, QModel<Label>> by args {
    fun name(value: String): LabelArgs = apply { addArg("name", value) }

  }

  class LabelsArgs(args: TypeArgBuilder<LabelConnection, QModel<LabelConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<LabelConnection, QModel<LabelConnection>> by args {
    fun first(value: Int): LabelsArgs = apply { addArg("first", value) }


    fun after(value: String): LabelsArgs = apply { addArg("after", value) }


    fun last(value: Int): LabelsArgs = apply { addArg("last", value) }


    fun before(value: String): LabelsArgs = apply { addArg("before", value) }

  }

  class LanguagesArgs(args: TypeArgBuilder<LanguageConnection, QModel<LanguageConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<LanguageConnection, QModel<LanguageConnection>> by args {
    fun first(value: Int): LanguagesArgs = apply { addArg("first", value) }


    fun after(value: String): LanguagesArgs = apply { addArg("after", value) }


    fun last(value: Int): LanguagesArgs = apply { addArg("last", value) }


    fun before(value: String): LanguagesArgs = apply { addArg("before", value) }


    fun orderBy(value: LanguageOrder): LanguagesArgs = apply { addArg("orderBy", value) }

  }

  class MentionableUsersArgs(args: TypeArgBuilder<UserConnection, QModel<UserConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<UserConnection, QModel<UserConnection>> by args {
    fun first(value: Int): MentionableUsersArgs = apply { addArg("first", value) }


    fun after(value: String): MentionableUsersArgs = apply { addArg("after", value) }


    fun last(value: Int): MentionableUsersArgs = apply { addArg("last", value) }


    fun before(value: String): MentionableUsersArgs = apply { addArg("before", value) }

  }

  class MilestoneArgs(args: TypeArgBuilder<Milestone, QModel<Milestone>> = TypeArgBuilder.create()) : TypeArgBuilder<Milestone, QModel<Milestone>> by args {
    fun number(value: Int): MilestoneArgs = apply { addArg("number", value) }

  }

  class MilestonesArgs(args: TypeArgBuilder<MilestoneConnection, QModel<MilestoneConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<MilestoneConnection, QModel<MilestoneConnection>> by args {
    fun first(value: Int): MilestonesArgs = apply { addArg("first", value) }


    fun after(value: String): MilestonesArgs = apply { addArg("after", value) }


    fun last(value: Int): MilestonesArgs = apply { addArg("last", value) }


    fun before(value: String): MilestonesArgs = apply { addArg("before", value) }

  }

  class ObjectValArgs(args: TypeArgBuilder<GitObject, QModel<GitObject>> = TypeArgBuilder.create()) : TypeArgBuilder<GitObject, QModel<GitObject>> by args {
    fun oid(value: GitObjectID): ObjectValArgs = apply { addArg("oid", value) }


    fun expression(value: String): ObjectValArgs = apply { addArg("expression", value) }

  }

  class ProtectedBranchesArgs(args: TypeArgBuilder<ProtectedBranchConnection, QModel<ProtectedBranchConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ProtectedBranchConnection, QModel<ProtectedBranchConnection>> by args {
    fun first(value: Int): ProtectedBranchesArgs = apply { addArg("first", value) }


    fun after(value: String): ProtectedBranchesArgs = apply { addArg("after", value) }


    fun last(value: Int): ProtectedBranchesArgs = apply { addArg("last", value) }


    fun before(value: String): ProtectedBranchesArgs = apply { addArg("before", value) }

  }

  class PullRequestArgs(args: TypeArgBuilder<PullRequest, QModel<PullRequest>> = TypeArgBuilder.create()) : TypeArgBuilder<PullRequest, QModel<PullRequest>> by args {
    fun number(value: Int): PullRequestArgs = apply { addArg("number", value) }

  }

  class PullRequestsArgs(args: TypeArgBuilder<PullRequestConnection, QModel<PullRequestConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<PullRequestConnection, QModel<PullRequestConnection>> by args {
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

  class RefArgs(args: TypeArgBuilder<Ref, QModel<Ref>> = TypeArgBuilder.create()) : TypeArgBuilder<Ref, QModel<Ref>> by args {
    fun qualifiedName(value: String): RefArgs = apply { addArg("qualifiedName", value) }

  }

  class RefsArgs(args: TypeArgBuilder<RefConnection, QModel<RefConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<RefConnection, QModel<RefConnection>> by args {
    fun first(value: Int): RefsArgs = apply { addArg("first", value) }


    fun after(value: String): RefsArgs = apply { addArg("after", value) }


    fun last(value: Int): RefsArgs = apply { addArg("last", value) }


    fun before(value: String): RefsArgs = apply { addArg("before", value) }


    fun refPrefix(value: String): RefsArgs = apply { addArg("refPrefix", value) }


    fun direction(value: OrderDirection): RefsArgs = apply { addArg("direction", value) }

  }

  class ReleasesArgs(args: TypeArgBuilder<ReleaseConnection, QModel<ReleaseConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ReleaseConnection, QModel<ReleaseConnection>> by args {
    fun first(value: Int): ReleasesArgs = apply { addArg("first", value) }


    fun after(value: String): ReleasesArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleasesArgs = apply { addArg("last", value) }


    fun before(value: String): ReleasesArgs = apply { addArg("before", value) }

  }

  class RepositoryTopicsArgs(args: TypeArgBuilder<RepositoryTopicConnection, QModel<RepositoryTopicConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<RepositoryTopicConnection, QModel<RepositoryTopicConnection>> by args {
    fun first(value: Int): RepositoryTopicsArgs = apply { addArg("first", value) }


    fun after(value: String): RepositoryTopicsArgs = apply { addArg("after", value) }


    fun last(value: Int): RepositoryTopicsArgs = apply { addArg("last", value) }


    fun before(value: String): RepositoryTopicsArgs = apply { addArg("before", value) }

  }

  class WatchersArgs(args: TypeArgBuilder<UserConnection, QModel<UserConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<UserConnection, QModel<UserConnection>> by args {
    fun first(value: Int): WatchersArgs = apply { addArg("first", value) }


    fun after(value: String): WatchersArgs = apply { addArg("after", value) }


    fun last(value: Int): WatchersArgs = apply { addArg("last", value) }


    fun before(value: String): WatchersArgs = apply { addArg("before", value) }

  }
}

object ReopenedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val closable: InitStub<Closable> by  lazy { typeStub<Closable>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 
}

object RenamedTitleEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val currentTitle: Stub<String> by  lazy { stub<String>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val previousTitle: Stub<String> by  lazy { stub<String>() } 

  val subject: InitStub<RenamedTitleSubject> by  lazy { typeStub<RenamedTitleSubject>() } 
}

object RemovedFromProjectEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 
}

object RemoveStarPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val starrable: InitStub<Starrable> by  lazy { typeStub<Starrable>() } 
}

object RemoveReactionPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val reaction: InitStub<Reaction> by  lazy { typeStub<Reaction>() } 

  val subject: InitStub<Reactable> by  lazy { typeStub<Reactable>() } 
}

object RemoveOutsideCollaboratorPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val removedUser: InitStub<User> by  lazy { typeStub<User>() } 
}

object ReleaseEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Release> by  lazy { typeStub<Release>() } 
}

object ReleaseConnection : QType {
  val edges: InitStub<ReleaseEdge> by  lazy { typeStub<ReleaseEdge>() } 

  val nodes: InitStub<Release> by  lazy { typeStub<Release>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ReleaseAssetEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<ReleaseAsset> by  lazy { typeStub<ReleaseAsset>() } 
}

object ReleaseAssetConnection : QType {
  val edges: InitStub<ReleaseAssetEdge> by  lazy { typeStub<ReleaseAssetEdge>() } 

  val nodes: InitStub<ReleaseAsset> by  lazy { typeStub<ReleaseAsset>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ReleaseAsset : QType, Node {
  override val id: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val release: InitStub<Release> by  lazy { typeStub<Release>() } 

  val url: Stub<URI> by  lazy { stub<URI>() } 
}

object Release : QType, UniformResourceLocatable, Node {
  val description: Stub<String> by  lazy { stub<String>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val publishedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val releaseAsset: ConfigType<ReleaseAssetArgs, ReleaseAssetConnection> by  lazy { typeConfigStub(ReleaseAssetArgs()) } 

  val releaseAssets: ConfigType<ReleaseAssetsArgs, ReleaseAssetConnection> by  lazy { typeConfigStub(ReleaseAssetsArgs()) } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val tag: InitStub<Ref> by  lazy { typeStub<Ref>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 

  class ReleaseAssetArgs(args: TypeArgBuilder<ReleaseAssetConnection, QModel<ReleaseAssetConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ReleaseAssetConnection, QModel<ReleaseAssetConnection>> by args {
    fun first(value: Int): ReleaseAssetArgs = apply { addArg("first", value) }


    fun after(value: String): ReleaseAssetArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleaseAssetArgs = apply { addArg("last", value) }


    fun before(value: String): ReleaseAssetArgs = apply { addArg("before", value) }


    fun name(value: String): ReleaseAssetArgs = apply { addArg("name", value) }

  }

  class ReleaseAssetsArgs(args: TypeArgBuilder<ReleaseAssetConnection, QModel<ReleaseAssetConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ReleaseAssetConnection, QModel<ReleaseAssetConnection>> by args {
    fun first(value: Int): ReleaseAssetsArgs = apply { addArg("first", value) }


    fun after(value: String): ReleaseAssetsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReleaseAssetsArgs = apply { addArg("last", value) }


    fun before(value: String): ReleaseAssetsArgs = apply { addArg("before", value) }

  }
}

object ReferencedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val commitRepository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val isCrossReference: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isCrossRepository: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isDirectReference: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val subject: InitStub<ReferencedSubject> by  lazy { typeStub<ReferencedSubject>() } 
}

object RefEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Ref> by  lazy { typeStub<Ref>() } 
}

object RefConnection : QType {
  val edges: InitStub<RefEdge> by  lazy { typeStub<RefEdge>() } 

  val nodes: InitStub<Ref> by  lazy { typeStub<Ref>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object Ref : QType, Node {
  val associatedPullRequests: ConfigType<AssociatedPullRequestsArgs, PullRequestConnection> by  lazy { typeConfigStub(AssociatedPullRequestsArgs()) } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val prefix: Stub<String> by  lazy { stub<String>() } 

  val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val target: InitStub<GitObject> by  lazy { typeStub<GitObject>() } 

  class AssociatedPullRequestsArgs(args: TypeArgBuilder<PullRequestConnection, QModel<PullRequestConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<PullRequestConnection, QModel<PullRequestConnection>> by args {
    fun first(value: Int): AssociatedPullRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): AssociatedPullRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): AssociatedPullRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): AssociatedPullRequestsArgs = apply { addArg("before", value) }


    fun states(value: PullRequestState): AssociatedPullRequestsArgs =
          apply { addArg("states", value) }

  }
}

object ReactionGroup : QType {
  val content: Stub<ReactionContent> by  lazy { stub<ReactionContent>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val subject: InitStub<Reactable> by  lazy { typeStub<Reactable>() } 

  val users: ConfigType<UsersArgs, ReactingUserConnection> by  lazy { typeConfigStub(UsersArgs()) } 

  val viewerHasReacted: Stub<Boolean> by  lazy { stub<Boolean>() } 

  class UsersArgs(args: TypeArgBuilder<ReactingUserConnection, QModel<ReactingUserConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ReactingUserConnection, QModel<ReactingUserConnection>> by args {
    fun first(value: Int): UsersArgs = apply { addArg("first", value) }


    fun after(value: String): UsersArgs = apply { addArg("after", value) }


    fun last(value: Int): UsersArgs = apply { addArg("last", value) }


    fun before(value: String): UsersArgs = apply { addArg("before", value) }

  }
}

object ReactionEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Reaction> by  lazy { typeStub<Reaction>() } 
}

object ReactionConnection : QType {
  val edges: InitStub<ReactionEdge> by  lazy { typeStub<ReactionEdge>() } 

  val nodes: InitStub<Reaction> by  lazy { typeStub<Reaction>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 

  val viewerHasReacted: Stub<Boolean> by  lazy { stub<Boolean>() } 
}

object Reaction : QType, Node {
  val content: Stub<ReactionContent> by  lazy { stub<ReactionContent>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val user: InitStub<User> by  lazy { typeStub<User>() } 
}

object ReactingUserEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<User> by  lazy { typeStub<User>() } 

  val reactedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 
}

object ReactingUserConnection : QType {
  val edges: InitStub<ReactingUserEdge> by  lazy { typeStub<ReactingUserEdge>() } 

  val nodes: InitStub<User> by  lazy { typeStub<User>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object RateLimit : QType {
  val cost: Stub<Int> by  lazy { stub<Int>() } 

  val limit: Stub<Int> by  lazy { stub<Int>() } 

  val remaining: Stub<Int> by  lazy { stub<Int>() } 

  val resetAt: Stub<DateTime> by  lazy { stub<DateTime>() } 
}

object Query : QType {
  val codeOfConduct: ConfigType<CodeOfConductArgs, CodeOfConduct> by  lazy { typeConfigStub(CodeOfConductArgs()) } 

  val codesOfConduct: InitStub<CodeOfConduct> by  lazy { typeStub<CodeOfConduct>() } 

  val node: ConfigType<NodeArgs, Node> by  lazy { typeConfigStub(NodeArgs()) } 

  val nodes: ConfigType<NodesArgs, Node> by  lazy { typeConfigStub(NodesArgs()) } 

  val organization: ConfigType<OrganizationArgs, Organization> by  lazy { typeConfigStub(OrganizationArgs()) } 

  val rateLimit: InitStub<RateLimit> by  lazy { typeStub<RateLimit>() } 

  val relay: InitStub<Query> by  lazy { typeStub<Query>() } 

  val repository: ConfigType<RepositoryArgs, Repository> by  lazy { typeConfigStub(RepositoryArgs()) } 

  val repositoryOwner: ConfigType<RepositoryOwnerArgs, RepositoryOwner> by  lazy { typeConfigStub(RepositoryOwnerArgs()) } 

  val resource: ConfigType<ResourceArgs, UniformResourceLocatable> by  lazy { typeConfigStub(ResourceArgs()) } 

  val search: ConfigType<SearchArgs, SearchResultItemConnection> by  lazy { typeConfigStub(SearchArgs()) } 

  val topic: ConfigType<TopicArgs, Topic> by  lazy { typeConfigStub(TopicArgs()) } 

  val user: ConfigType<UserArgs, User> by  lazy { typeConfigStub(UserArgs()) } 

  val viewer: InitStub<User> by  lazy { typeStub<User>() } 

  class CodeOfConductArgs(args: TypeArgBuilder<CodeOfConduct, QModel<CodeOfConduct>> = TypeArgBuilder.create()) : TypeArgBuilder<CodeOfConduct, QModel<CodeOfConduct>> by args {
    fun key(value: String): CodeOfConductArgs = apply { addArg("key", value) }

  }

  class NodeArgs(args: TypeArgBuilder<Node, QModel<Node>> = TypeArgBuilder.create()) : TypeArgBuilder<Node, QModel<Node>> by args {
    fun id(value: String): NodeArgs = apply { addArg("id", value) }

  }

  class NodesArgs(args: TypeArgBuilder<Node, QModel<Node>> = TypeArgBuilder.create()) : TypeArgBuilder<Node, QModel<Node>> by args {
    fun ids(value: String): NodesArgs = apply { addArg("ids", value) }

  }

  class OrganizationArgs(args: TypeArgBuilder<Organization, QModel<Organization>> = TypeArgBuilder.create()) : TypeArgBuilder<Organization, QModel<Organization>> by args {
    fun login(value: String): OrganizationArgs = apply { addArg("login", value) }

  }

  class RepositoryArgs(args: TypeArgBuilder<Repository, QModel<Repository>> = TypeArgBuilder.create()) : TypeArgBuilder<Repository, QModel<Repository>> by args {
    fun owner(value: String): RepositoryArgs = apply { addArg("owner", value) }


    fun name(value: String): RepositoryArgs = apply { addArg("name", value) }

  }

  class RepositoryOwnerArgs(args: TypeArgBuilder<RepositoryOwner, QModel<RepositoryOwner>> = TypeArgBuilder.create()) : TypeArgBuilder<RepositoryOwner, QModel<RepositoryOwner>> by args {
    fun login(value: String): RepositoryOwnerArgs = apply { addArg("login", value) }

  }

  class ResourceArgs(args: TypeArgBuilder<UniformResourceLocatable, QModel<UniformResourceLocatable>> = TypeArgBuilder.create()) : TypeArgBuilder<UniformResourceLocatable, QModel<UniformResourceLocatable>> by args {
    fun url(value: URI): ResourceArgs = apply { addArg("url", value) }

  }

  class SearchArgs(args: TypeArgBuilder<SearchResultItemConnection, QModel<SearchResultItemConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<SearchResultItemConnection, QModel<SearchResultItemConnection>> by args {
    fun first(value: Int): SearchArgs = apply { addArg("first", value) }


    fun after(value: String): SearchArgs = apply { addArg("after", value) }


    fun last(value: Int): SearchArgs = apply { addArg("last", value) }


    fun before(value: String): SearchArgs = apply { addArg("before", value) }


    fun query(value: String): SearchArgs = apply { addArg("query", value) }


    fun type(value: SearchType): SearchArgs = apply { addArg("type", value) }

  }

  class TopicArgs(args: TypeArgBuilder<Topic, QModel<Topic>> = TypeArgBuilder.create()) : TypeArgBuilder<Topic, QModel<Topic>> by args {
    fun name(value: String): TopicArgs = apply { addArg("name", value) }

  }

  class UserArgs(args: TypeArgBuilder<User, QModel<User>> = TypeArgBuilder.create()) : TypeArgBuilder<User, QModel<User>> by args {
    fun login(value: String): UserArgs = apply { addArg("login", value) }

  }
}

object PushAllowanceEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<PushAllowance> by  lazy { typeStub<PushAllowance>() } 
}

object PushAllowanceConnection : QType {
  val edges: InitStub<PushAllowanceEdge> by  lazy { typeStub<PushAllowanceEdge>() } 

  val nodes: InitStub<PushAllowance> by  lazy { typeStub<PushAllowance>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object PushAllowance : QType, Node {
  val actor: InitStub<PushAllowanceActor> by  lazy { typeStub<PushAllowanceActor>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val protectedBranch: InitStub<ProtectedBranch> by  lazy { typeStub<ProtectedBranch>() } 
}

object PullRequestTimelineItemEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<PullRequestTimelineItem> by  lazy { typeStub<PullRequestTimelineItem>() } 
}

object PullRequestTimelineConnection : QType {
  val edges: InitStub<PullRequestTimelineItemEdge> by  lazy { typeStub<PullRequestTimelineItemEdge>() } 

  val nodes: InitStub<PullRequestTimelineItem> by  lazy { typeStub<PullRequestTimelineItem>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object PullRequestReviewThread : QType, Node {
  val comments: ConfigType<CommentsArgs, PullRequestReviewCommentConnection> by  lazy { typeConfigStub(CommentsArgs()) } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  class CommentsArgs(args: TypeArgBuilder<PullRequestReviewCommentConnection, QModel<PullRequestReviewCommentConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<PullRequestReviewCommentConnection, QModel<PullRequestReviewCommentConnection>> by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

object PullRequestReviewEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<PullRequestReview> by  lazy { typeStub<PullRequestReview>() } 
}

object PullRequestReviewConnection : QType {
  val edges: InitStub<PullRequestReviewEdge> by  lazy { typeStub<PullRequestReviewEdge>() } 

  val nodes: InitStub<PullRequestReview> by  lazy { typeStub<PullRequestReview>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object PullRequestReviewCommentEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<PullRequestReviewComment> by  lazy { typeStub<PullRequestReviewComment>() } 
}

object PullRequestReviewCommentConnection : QType {
  val edges: InitStub<PullRequestReviewCommentEdge> by  lazy { typeStub<PullRequestReviewCommentEdge>() } 

  val nodes: InitStub<PullRequestReviewComment> by  lazy { typeStub<PullRequestReviewComment>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object PullRequestReviewComment : QType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val authorAssociation: Stub<CommentAuthorAssociation> by  lazy { stub<CommentAuthorAssociation>() } 

  override val body: Stub<String> by  lazy { stub<String>() } 

  override val bodyHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val bodyText: Stub<String> by  lazy { stub<String>() } 

  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  override val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val createdViaEmail: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  val diffHunk: Stub<String> by  lazy { stub<String>() } 

  val draftedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val editor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  override val lastEditedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val originalCommit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val originalPosition: Stub<Int> by  lazy { stub<Int>() } 

  val path: Stub<String> by  lazy { stub<String>() } 

  val position: Stub<Int> by  lazy { stub<Int>() } 

  override val publishedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val pullRequestReview: InitStub<PullRequestReview> by  lazy { typeStub<PullRequestReview>() } 

  override val reactionGroups: InitStub<ReactionGroup> by  lazy { typeStub<ReactionGroup>() } 

  override val reactions: ConfigType<Reactable.ReactionsArgs, ReactionConnection> by  lazy { typeConfigStub(Reactable.ReactionsArgs()) } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val url: Stub<URI> by  lazy { stub<URI>() } 

  override val viewerCanDelete: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanReact: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanUpdate: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason> by  lazy { stub<CommentCannotUpdateReason>() } 

  override val viewerDidAuthor: Stub<Boolean> by  lazy { stub<Boolean>() } 
}

object PullRequestReview : QType, RepositoryNode, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val authorAssociation: Stub<CommentAuthorAssociation> by  lazy { stub<CommentAuthorAssociation>() } 

  override val body: Stub<String> by  lazy { stub<String>() } 

  override val bodyHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val bodyText: Stub<String> by  lazy { stub<String>() } 

  val comments: ConfigType<CommentsArgs, PullRequestReviewCommentConnection> by  lazy { typeConfigStub(CommentsArgs()) } 

  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  override val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val createdViaEmail: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val editor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  override val lastEditedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val publishedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val state: Stub<PullRequestReviewState> by  lazy { stub<PullRequestReviewState>() } 

  val submittedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val url: Stub<URI> by  lazy { stub<URI>() } 

  override val viewerCanDelete: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanUpdate: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason> by  lazy { stub<CommentCannotUpdateReason>() } 

  override val viewerDidAuthor: Stub<Boolean> by  lazy { stub<Boolean>() } 

  class CommentsArgs(args: TypeArgBuilder<PullRequestReviewCommentConnection, QModel<PullRequestReviewCommentConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<PullRequestReviewCommentConnection, QModel<PullRequestReviewCommentConnection>> by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

object PullRequestEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 
}

object PullRequestConnection : QType {
  val edges: InitStub<PullRequestEdge> by  lazy { typeStub<PullRequestEdge>() } 

  val nodes: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object PullRequestCommitEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<PullRequestCommit> by  lazy { typeStub<PullRequestCommit>() } 
}

object PullRequestCommitConnection : QType {
  val edges: InitStub<PullRequestCommitEdge> by  lazy { typeStub<PullRequestCommitEdge>() } 

  val nodes: InitStub<PullRequestCommit> by  lazy { typeStub<PullRequestCommit>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object PullRequestCommit : QType, UniformResourceLocatable, Node {
  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 
}

object PullRequest : QType, UniformResourceLocatable, Subscribable, RepositoryNode, Reactable, Lockable, Labelable, UpdatableComment, Updatable, Comment, Closable, Assignable, Node {
  override val assignees: ConfigType<Assignable.AssigneesArgs, UserConnection> by  lazy { typeConfigStub(Assignable.AssigneesArgs()) } 

  override val author: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val authorAssociation: Stub<CommentAuthorAssociation> by  lazy { stub<CommentAuthorAssociation>() } 

  val baseRef: InitStub<Ref> by  lazy { typeStub<Ref>() } 

  val baseRefName: Stub<String> by  lazy { stub<String>() } 

  override val body: Stub<String> by  lazy { stub<String>() } 

  override val bodyHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val bodyText: Stub<String> by  lazy { stub<String>() } 

  override val closed: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val comments: ConfigType<CommentsArgs, IssueCommentConnection> by  lazy { typeConfigStub(CommentsArgs()) } 

  val commits: ConfigType<CommitsArgs, PullRequestCommitConnection> by  lazy { typeConfigStub(CommitsArgs()) } 

  override val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val createdViaEmail: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val editor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val headRef: InitStub<Ref> by  lazy { typeStub<Ref>() } 

  val headRefName: Stub<String> by  lazy { stub<String>() } 

  val headRepository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val headRepositoryOwner: InitStub<RepositoryOwner> by  lazy { typeStub<RepositoryOwner>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val isCrossRepository: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val labels: ConfigType<Labelable.LabelsArgs, LabelConnection> by  lazy { typeConfigStub(Labelable.LabelsArgs()) } 

  override val lastEditedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val locked: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val mergeCommit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val mergeable: Stub<MergeableState> by  lazy { stub<MergeableState>() } 

  val merged: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val mergedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val number: Stub<Int> by  lazy { stub<Int>() } 

  val participants: ConfigType<ParticipantsArgs, UserConnection> by  lazy { typeConfigStub(ParticipantsArgs()) } 

  val potentialMergeCommit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  override val publishedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val reactionGroups: InitStub<ReactionGroup> by  lazy { typeStub<ReactionGroup>() } 

  override val reactions: ConfigType<Reactable.ReactionsArgs, ReactionConnection> by  lazy { typeConfigStub(Reactable.ReactionsArgs()) } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val revertResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val revertUrl: Stub<URI> by  lazy { stub<URI>() } 

  val reviewRequests: ConfigType<ReviewRequestsArgs, ReviewRequestConnection> by  lazy { typeConfigStub(ReviewRequestsArgs()) } 

  val reviews: ConfigType<ReviewsArgs, PullRequestReviewConnection> by  lazy { typeConfigStub(ReviewsArgs()) } 

  val state: Stub<PullRequestState> by  lazy { stub<PullRequestState>() } 

  val suggestedReviewers: InitStub<SuggestedReviewer> by  lazy { typeStub<SuggestedReviewer>() } 

  val timeline: ConfigType<TimelineArgs, PullRequestTimelineConnection> by  lazy { typeConfigStub(TimelineArgs()) } 

  val title: Stub<String> by  lazy { stub<String>() } 

  override val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 

  override val viewerCanReact: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanSubscribe: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanUpdate: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason> by  lazy { stub<CommentCannotUpdateReason>() } 

  override val viewerDidAuthor: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerSubscription: Stub<SubscriptionState> by  lazy { stub<SubscriptionState>() } 

  class CommentsArgs(args: TypeArgBuilder<IssueCommentConnection, QModel<IssueCommentConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<IssueCommentConnection, QModel<IssueCommentConnection>> by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class CommitsArgs(args: TypeArgBuilder<PullRequestCommitConnection, QModel<PullRequestCommitConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<PullRequestCommitConnection, QModel<PullRequestCommitConnection>> by args {
    fun first(value: Int): CommitsArgs = apply { addArg("first", value) }


    fun after(value: String): CommitsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommitsArgs = apply { addArg("last", value) }


    fun before(value: String): CommitsArgs = apply { addArg("before", value) }

  }

  class ParticipantsArgs(args: TypeArgBuilder<UserConnection, QModel<UserConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<UserConnection, QModel<UserConnection>> by args {
    fun first(value: Int): ParticipantsArgs = apply { addArg("first", value) }


    fun after(value: String): ParticipantsArgs = apply { addArg("after", value) }


    fun last(value: Int): ParticipantsArgs = apply { addArg("last", value) }


    fun before(value: String): ParticipantsArgs = apply { addArg("before", value) }

  }

  class ReviewRequestsArgs(args: TypeArgBuilder<ReviewRequestConnection, QModel<ReviewRequestConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ReviewRequestConnection, QModel<ReviewRequestConnection>> by args {
    fun first(value: Int): ReviewRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewRequestsArgs = apply { addArg("before", value) }

  }

  class ReviewsArgs(args: TypeArgBuilder<PullRequestReviewConnection, QModel<PullRequestReviewConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<PullRequestReviewConnection, QModel<PullRequestReviewConnection>> by args {
    fun first(value: Int): ReviewsArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewsArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewsArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewsArgs = apply { addArg("before", value) }


    fun states(value: PullRequestReviewState): ReviewsArgs = apply { addArg("states", value) }

  }

  class TimelineArgs(args: TypeArgBuilder<PullRequestTimelineConnection, QModel<PullRequestTimelineConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<PullRequestTimelineConnection, QModel<PullRequestTimelineConnection>> by args {
    fun first(value: Int): TimelineArgs = apply { addArg("first", value) }


    fun after(value: String): TimelineArgs = apply { addArg("after", value) }


    fun last(value: Int): TimelineArgs = apply { addArg("last", value) }


    fun before(value: String): TimelineArgs = apply { addArg("before", value) }


    fun since(value: DateTime): TimelineArgs = apply { addArg("since", value) }

  }
}

object ProtectedBranchEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<ProtectedBranch> by  lazy { typeStub<ProtectedBranch>() } 
}

object ProtectedBranchConnection : QType {
  val edges: InitStub<ProtectedBranchEdge> by  lazy { typeStub<ProtectedBranchEdge>() } 

  val nodes: InitStub<ProtectedBranch> by  lazy { typeStub<ProtectedBranch>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ProtectedBranch : QType, Node {
  val creator: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val hasDismissableStaleReviews: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val hasRequiredReviews: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val hasRequiredStatusChecks: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val hasRestrictedPushes: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val hasRestrictedReviewDismissals: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val hasStrictRequiredStatusChecks: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val isAdminEnforced: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val pushAllowances: ConfigType<PushAllowancesArgs, PushAllowanceConnection> by  lazy { typeConfigStub(PushAllowancesArgs()) } 

  val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val requiredStatusCheckContexts: Stub<String> by  lazy { stub<String>() } 

  val reviewDismissalAllowances: ConfigType<ReviewDismissalAllowancesArgs, ReviewDismissalAllowanceConnection> by  lazy { typeConfigStub(ReviewDismissalAllowancesArgs()) } 

  class PushAllowancesArgs(args: TypeArgBuilder<PushAllowanceConnection, QModel<PushAllowanceConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<PushAllowanceConnection, QModel<PushAllowanceConnection>> by args {
    fun first(value: Int): PushAllowancesArgs = apply { addArg("first", value) }


    fun after(value: String): PushAllowancesArgs = apply { addArg("after", value) }


    fun last(value: Int): PushAllowancesArgs = apply { addArg("last", value) }


    fun before(value: String): PushAllowancesArgs = apply { addArg("before", value) }

  }

  class ReviewDismissalAllowancesArgs(args: TypeArgBuilder<ReviewDismissalAllowanceConnection, QModel<ReviewDismissalAllowanceConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ReviewDismissalAllowanceConnection, QModel<ReviewDismissalAllowanceConnection>> by args {
    fun first(value: Int): ReviewDismissalAllowancesArgs = apply { addArg("first", value) }


    fun after(value: String): ReviewDismissalAllowancesArgs = apply { addArg("after", value) }


    fun last(value: Int): ReviewDismissalAllowancesArgs = apply { addArg("last", value) }


    fun before(value: String): ReviewDismissalAllowancesArgs = apply { addArg("before", value) }

  }
}

object ProjectEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Project> by  lazy { typeStub<Project>() } 
}

object ProjectConnection : QType {
  val edges: InitStub<ProjectEdge> by  lazy { typeStub<ProjectEdge>() } 

  val nodes: InitStub<Project> by  lazy { typeStub<Project>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ProjectColumnEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<ProjectColumn> by  lazy { typeStub<ProjectColumn>() } 
}

object ProjectColumnConnection : QType {
  val edges: InitStub<ProjectColumnEdge> by  lazy { typeStub<ProjectColumnEdge>() } 

  val nodes: InitStub<ProjectColumn> by  lazy { typeStub<ProjectColumn>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ProjectColumn : QType, Node {
  val cards: ConfigType<CardsArgs, ProjectCardConnection> by  lazy { typeConfigStub(CardsArgs()) } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val project: InitStub<Project> by  lazy { typeStub<Project>() } 

  val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  class CardsArgs(args: TypeArgBuilder<ProjectCardConnection, QModel<ProjectCardConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ProjectCardConnection, QModel<ProjectCardConnection>> by args {
    fun first(value: Int): CardsArgs = apply { addArg("first", value) }


    fun after(value: String): CardsArgs = apply { addArg("after", value) }


    fun last(value: Int): CardsArgs = apply { addArg("last", value) }


    fun before(value: String): CardsArgs = apply { addArg("before", value) }

  }
}

object ProjectCardEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<ProjectCard> by  lazy { typeStub<ProjectCard>() } 
}

object ProjectCardConnection : QType {
  val edges: InitStub<ProjectCardEdge> by  lazy { typeStub<ProjectCardEdge>() } 

  val nodes: InitStub<ProjectCard> by  lazy { typeStub<ProjectCard>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ProjectCard : QType, Node {
  val column: InitStub<ProjectColumn> by  lazy { typeStub<ProjectColumn>() } 

  val content: InitStub<ProjectCardItem> by  lazy { typeStub<ProjectCardItem>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val creator: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val note: Stub<String> by  lazy { stub<String>() } 

  val project: InitStub<Project> by  lazy { typeStub<Project>() } 

  val projectColumn: InitStub<ProjectColumn> by  lazy { typeStub<ProjectColumn>() } 

  val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val state: Stub<ProjectCardState> by  lazy { stub<ProjectCardState>() } 

  val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val url: Stub<URI> by  lazy { stub<URI>() } 
}

object Project : QType, Updatable, Node {
  val body: Stub<String> by  lazy { stub<String>() } 

  val bodyHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val closedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val columns: ConfigType<ColumnsArgs, ProjectColumnConnection> by  lazy { typeConfigStub(ColumnsArgs()) } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val creator: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val number: Stub<Int> by  lazy { stub<Int>() } 

  val owner: InitStub<ProjectOwner> by  lazy { typeStub<ProjectOwner>() } 

  val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val state: Stub<ProjectState> by  lazy { stub<ProjectState>() } 

  val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val url: Stub<URI> by  lazy { stub<URI>() } 

  override val viewerCanUpdate: Stub<Boolean> by  lazy { stub<Boolean>() } 

  class ColumnsArgs(args: TypeArgBuilder<ProjectColumnConnection, QModel<ProjectColumnConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ProjectColumnConnection, QModel<ProjectColumnConnection>> by args {
    fun first(value: Int): ColumnsArgs = apply { addArg("first", value) }


    fun after(value: String): ColumnsArgs = apply { addArg("after", value) }


    fun last(value: Int): ColumnsArgs = apply { addArg("last", value) }


    fun before(value: String): ColumnsArgs = apply { addArg("before", value) }

  }
}

object PageInfo : QType {
  val endCursor: Stub<String> by  lazy { stub<String>() } 

  val hasNextPage: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val hasPreviousPage: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val startCursor: Stub<String> by  lazy { stub<String>() } 
}

object OrganizationInvitationEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<OrganizationInvitation> by  lazy { typeStub<OrganizationInvitation>() } 
}

object OrganizationInvitationConnection : QType {
  val edges: InitStub<OrganizationInvitationEdge> by  lazy { typeStub<OrganizationInvitationEdge>() } 

  val nodes: InitStub<OrganizationInvitation> by  lazy { typeStub<OrganizationInvitation>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object OrganizationInvitation : QType {
  val email: Stub<String> by  lazy { stub<String>() } 

  val id: Stub<String> by  lazy { stub<String>() } 

  val invitee: InitStub<User> by  lazy { typeStub<User>() } 

  val inviter: InitStub<User> by  lazy { typeStub<User>() } 

  val role: Stub<OrganizationInvitationRole> by  lazy { stub<OrganizationInvitationRole>() } 
}

object OrganizationIdentityProvider : QType, Node {
  val digestMethod: Stub<URI> by  lazy { stub<URI>() } 

  val externalIdentities: ConfigType<ExternalIdentitiesArgs, ExternalIdentityConnection> by  lazy { typeConfigStub(ExternalIdentitiesArgs()) } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val idpCertificate: Stub<X509Certificate> by  lazy { stub<X509Certificate>() } 

  val issuer: Stub<String> by  lazy { stub<String>() } 

  val organization: InitStub<Organization> by  lazy { typeStub<Organization>() } 

  val signatureMethod: Stub<URI> by  lazy { stub<URI>() } 

  val ssoUrl: Stub<URI> by  lazy { stub<URI>() } 

  class ExternalIdentitiesArgs(args: TypeArgBuilder<ExternalIdentityConnection, QModel<ExternalIdentityConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<ExternalIdentityConnection, QModel<ExternalIdentityConnection>> by args {
    fun first(value: Int): ExternalIdentitiesArgs = apply { addArg("first", value) }


    fun after(value: String): ExternalIdentitiesArgs = apply { addArg("after", value) }


    fun last(value: Int): ExternalIdentitiesArgs = apply { addArg("last", value) }


    fun before(value: String): ExternalIdentitiesArgs = apply { addArg("before", value) }

  }
}

object OrganizationEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Organization> by  lazy { typeStub<Organization>() } 
}

object OrganizationConnection : QType {
  val edges: InitStub<OrganizationEdge> by  lazy { typeStub<OrganizationEdge>() } 

  val nodes: InitStub<Organization> by  lazy { typeStub<Organization>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object Organization : QType, UniformResourceLocatable, RepositoryOwner, ProjectOwner, Actor, Node {
  override val avatarUrl: Config<Actor.AvatarUrlArgs, URI> by  lazy { configStub(Actor.AvatarUrlArgs()) }

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val isInvoiced: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val login: Stub<String> by  lazy { stub<String>() } 

  val members: ConfigType<MembersArgs, UserConnection> by  lazy { typeConfigStub(MembersArgs()) } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val newTeamResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val newTeamUrl: Stub<URI> by  lazy { stub<URI>() } 

  val organizationBillingEmail: Stub<String> by  lazy { stub<String>() } 

  override val pinnedRepositories: ConfigType<RepositoryOwner.PinnedRepositoriesArgs, RepositoryConnection> by  lazy { typeConfigStub(RepositoryOwner.PinnedRepositoriesArgs()) } 

  override val project: ConfigType<ProjectOwner.ProjectArgs, Project> by  lazy { typeConfigStub(ProjectOwner.ProjectArgs()) } 

  override val projects: ConfigType<ProjectOwner.ProjectsArgs, ProjectConnection> by  lazy { typeConfigStub(ProjectOwner.ProjectsArgs()) } 

  override val projectsResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val projectsUrl: Stub<URI> by  lazy { stub<URI>() } 

  override val repositories: ConfigType<RepositoryOwner.RepositoriesArgs, RepositoryConnection> by  lazy { typeConfigStub(RepositoryOwner.RepositoriesArgs()) } 

  override val repository: ConfigType<RepositoryOwner.RepositoryArgs, Repository> by  lazy { typeConfigStub(RepositoryOwner.RepositoryArgs()) } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val samlIdentityProvider: InitStub<OrganizationIdentityProvider> by  lazy { typeStub<OrganizationIdentityProvider>() } 

  val team: ConfigType<TeamArgs, Team> by  lazy { typeConfigStub(TeamArgs()) } 

  val teams: ConfigType<TeamsArgs, TeamConnection> by  lazy { typeConfigStub(TeamsArgs()) } 

  val teamsResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val teamsUrl: Stub<URI> by  lazy { stub<URI>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 

  val viewerCanAdminister: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanCreateProjects: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val viewerCanCreateRepositories: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val viewerCanCreateTeams: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val viewerIsAMember: Stub<Boolean> by  lazy { stub<Boolean>() } 

  class MembersArgs(args: TypeArgBuilder<UserConnection, QModel<UserConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<UserConnection, QModel<UserConnection>> by args {
    fun first(value: Int): MembersArgs = apply { addArg("first", value) }


    fun after(value: String): MembersArgs = apply { addArg("after", value) }


    fun last(value: Int): MembersArgs = apply { addArg("last", value) }


    fun before(value: String): MembersArgs = apply { addArg("before", value) }

  }

  class TeamArgs(args: TypeArgBuilder<Team, QModel<Team>> = TypeArgBuilder.create()) : TypeArgBuilder<Team, QModel<Team>> by args {
    fun slug(value: String): TeamArgs = apply { addArg("slug", value) }

  }

  class TeamsArgs(args: TypeArgBuilder<TeamConnection, QModel<TeamConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<TeamConnection, QModel<TeamConnection>> by args {
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

object Mutation : QType {
  val acceptTopicSuggestion: ConfigType<AcceptTopicSuggestionArgs, AcceptTopicSuggestionPayload> by  lazy { typeConfigStub(AcceptTopicSuggestionArgs()) } 

  val addComment: ConfigType<AddCommentArgs, AddCommentPayload> by  lazy { typeConfigStub(AddCommentArgs()) } 

  val addProjectCard: ConfigType<AddProjectCardArgs, AddProjectCardPayload> by  lazy { typeConfigStub(AddProjectCardArgs()) } 

  val addProjectColumn: ConfigType<AddProjectColumnArgs, AddProjectColumnPayload> by  lazy { typeConfigStub(AddProjectColumnArgs()) } 

  val addPullRequestReview: ConfigType<AddPullRequestReviewArgs, AddPullRequestReviewPayload> by  lazy { typeConfigStub(AddPullRequestReviewArgs()) } 

  val addPullRequestReviewComment: ConfigType<AddPullRequestReviewCommentArgs, AddPullRequestReviewCommentPayload> by  lazy { typeConfigStub(AddPullRequestReviewCommentArgs()) } 

  val addReaction: ConfigType<AddReactionArgs, AddReactionPayload> by  lazy { typeConfigStub(AddReactionArgs()) } 

  val addStar: ConfigType<AddStarArgs, AddStarPayload> by  lazy { typeConfigStub(AddStarArgs()) } 

  val createProject: ConfigType<CreateProjectArgs, CreateProjectPayload> by  lazy { typeConfigStub(CreateProjectArgs()) } 

  val declineTopicSuggestion: ConfigType<DeclineTopicSuggestionArgs, DeclineTopicSuggestionPayload> by  lazy { typeConfigStub(DeclineTopicSuggestionArgs()) } 

  val deleteProject: ConfigType<DeleteProjectArgs, DeleteProjectPayload> by  lazy { typeConfigStub(DeleteProjectArgs()) } 

  val deleteProjectCard: ConfigType<DeleteProjectCardArgs, DeleteProjectCardPayload> by  lazy { typeConfigStub(DeleteProjectCardArgs()) } 

  val deleteProjectColumn: ConfigType<DeleteProjectColumnArgs, DeleteProjectColumnPayload> by  lazy { typeConfigStub(DeleteProjectColumnArgs()) } 

  val deletePullRequestReview: ConfigType<DeletePullRequestReviewArgs, DeletePullRequestReviewPayload> by  lazy { typeConfigStub(DeletePullRequestReviewArgs()) } 

  val dismissPullRequestReview: ConfigType<DismissPullRequestReviewArgs, DismissPullRequestReviewPayload> by  lazy { typeConfigStub(DismissPullRequestReviewArgs()) } 

  val moveProjectCard: ConfigType<MoveProjectCardArgs, MoveProjectCardPayload> by  lazy { typeConfigStub(MoveProjectCardArgs()) } 

  val moveProjectColumn: ConfigType<MoveProjectColumnArgs, MoveProjectColumnPayload> by  lazy { typeConfigStub(MoveProjectColumnArgs()) } 

  val removeOutsideCollaborator: ConfigType<RemoveOutsideCollaboratorArgs, RemoveOutsideCollaboratorPayload> by  lazy { typeConfigStub(RemoveOutsideCollaboratorArgs()) } 

  val removeReaction: ConfigType<RemoveReactionArgs, RemoveReactionPayload> by  lazy { typeConfigStub(RemoveReactionArgs()) } 

  val removeStar: ConfigType<RemoveStarArgs, RemoveStarPayload> by  lazy { typeConfigStub(RemoveStarArgs()) } 

  val requestReviews: ConfigType<RequestReviewsArgs, RequestReviewsPayload> by  lazy { typeConfigStub(RequestReviewsArgs()) } 

  val submitPullRequestReview: ConfigType<SubmitPullRequestReviewArgs, SubmitPullRequestReviewPayload> by  lazy { typeConfigStub(SubmitPullRequestReviewArgs()) } 

  val updateProject: ConfigType<UpdateProjectArgs, UpdateProjectPayload> by  lazy { typeConfigStub(UpdateProjectArgs()) } 

  val updateProjectCard: ConfigType<UpdateProjectCardArgs, UpdateProjectCardPayload> by  lazy { typeConfigStub(UpdateProjectCardArgs()) } 

  val updateProjectColumn: ConfigType<UpdateProjectColumnArgs, UpdateProjectColumnPayload> by  lazy { typeConfigStub(UpdateProjectColumnArgs()) } 

  val updatePullRequestReview: ConfigType<UpdatePullRequestReviewArgs, UpdatePullRequestReviewPayload> by  lazy { typeConfigStub(UpdatePullRequestReviewArgs()) } 

  val updatePullRequestReviewComment: ConfigType<UpdatePullRequestReviewCommentArgs, UpdatePullRequestReviewCommentPayload> by  lazy { typeConfigStub(UpdatePullRequestReviewCommentArgs()) } 

  val updateSubscription: ConfigType<UpdateSubscriptionArgs, UpdateSubscriptionPayload> by  lazy { typeConfigStub(UpdateSubscriptionArgs()) } 

  val updateTopics: ConfigType<UpdateTopicsArgs, UpdateTopicsPayload> by  lazy { typeConfigStub(UpdateTopicsArgs()) } 

  class AcceptTopicSuggestionArgs(args: TypeArgBuilder<AcceptTopicSuggestionPayload, QModel<AcceptTopicSuggestionPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<AcceptTopicSuggestionPayload, QModel<AcceptTopicSuggestionPayload>> by args {
    fun input(value: AcceptTopicSuggestionInput): AcceptTopicSuggestionArgs =
          apply { addArg("input", value) }

  }

  class AddCommentArgs(args: TypeArgBuilder<AddCommentPayload, QModel<AddCommentPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<AddCommentPayload, QModel<AddCommentPayload>> by args {
    fun input(value: AddCommentInput): AddCommentArgs = apply { addArg("input", value) }

  }

  class AddProjectCardArgs(args: TypeArgBuilder<AddProjectCardPayload, QModel<AddProjectCardPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<AddProjectCardPayload, QModel<AddProjectCardPayload>> by args {
    fun input(value: AddProjectCardInput): AddProjectCardArgs = apply { addArg("input", value) }

  }

  class AddProjectColumnArgs(args: TypeArgBuilder<AddProjectColumnPayload, QModel<AddProjectColumnPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<AddProjectColumnPayload, QModel<AddProjectColumnPayload>> by args {
    fun input(value: AddProjectColumnInput): AddProjectColumnArgs = apply { addArg("input", value) }

  }

  class AddPullRequestReviewArgs(args: TypeArgBuilder<AddPullRequestReviewPayload, QModel<AddPullRequestReviewPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<AddPullRequestReviewPayload, QModel<AddPullRequestReviewPayload>> by args {
    fun input(value: AddPullRequestReviewInput): AddPullRequestReviewArgs =
          apply { addArg("input", value) }

  }

  class AddPullRequestReviewCommentArgs(args: TypeArgBuilder<AddPullRequestReviewCommentPayload, QModel<AddPullRequestReviewCommentPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<AddPullRequestReviewCommentPayload, QModel<AddPullRequestReviewCommentPayload>> by args {
    fun input(value: AddPullRequestReviewCommentInput): AddPullRequestReviewCommentArgs =
          apply { addArg("input", value) }

  }

  class AddReactionArgs(args: TypeArgBuilder<AddReactionPayload, QModel<AddReactionPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<AddReactionPayload, QModel<AddReactionPayload>> by args {
    fun input(value: AddReactionInput): AddReactionArgs = apply { addArg("input", value) }

  }

  class AddStarArgs(args: TypeArgBuilder<AddStarPayload, QModel<AddStarPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<AddStarPayload, QModel<AddStarPayload>> by args {
    fun input(value: AddStarInput): AddStarArgs = apply { addArg("input", value) }

  }

  class CreateProjectArgs(args: TypeArgBuilder<CreateProjectPayload, QModel<CreateProjectPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<CreateProjectPayload, QModel<CreateProjectPayload>> by args {
    fun input(value: CreateProjectInput): CreateProjectArgs = apply { addArg("input", value) }

  }

  class DeclineTopicSuggestionArgs(args: TypeArgBuilder<DeclineTopicSuggestionPayload, QModel<DeclineTopicSuggestionPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<DeclineTopicSuggestionPayload, QModel<DeclineTopicSuggestionPayload>> by args {
    fun input(value: DeclineTopicSuggestionInput): DeclineTopicSuggestionArgs =
          apply { addArg("input", value) }

  }

  class DeleteProjectArgs(args: TypeArgBuilder<DeleteProjectPayload, QModel<DeleteProjectPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<DeleteProjectPayload, QModel<DeleteProjectPayload>> by args {
    fun input(value: DeleteProjectInput): DeleteProjectArgs = apply { addArg("input", value) }

  }

  class DeleteProjectCardArgs(args: TypeArgBuilder<DeleteProjectCardPayload, QModel<DeleteProjectCardPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<DeleteProjectCardPayload, QModel<DeleteProjectCardPayload>> by args {
    fun input(value: DeleteProjectCardInput): DeleteProjectCardArgs =
          apply { addArg("input", value) }

  }

  class DeleteProjectColumnArgs(args: TypeArgBuilder<DeleteProjectColumnPayload, QModel<DeleteProjectColumnPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<DeleteProjectColumnPayload, QModel<DeleteProjectColumnPayload>> by args {
    fun input(value: DeleteProjectColumnInput): DeleteProjectColumnArgs =
          apply { addArg("input", value) }

  }

  class DeletePullRequestReviewArgs(args: TypeArgBuilder<DeletePullRequestReviewPayload, QModel<DeletePullRequestReviewPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<DeletePullRequestReviewPayload, QModel<DeletePullRequestReviewPayload>> by args {
    fun input(value: DeletePullRequestReviewInput): DeletePullRequestReviewArgs =
          apply { addArg("input", value) }

  }

  class DismissPullRequestReviewArgs(args: TypeArgBuilder<DismissPullRequestReviewPayload, QModel<DismissPullRequestReviewPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<DismissPullRequestReviewPayload, QModel<DismissPullRequestReviewPayload>> by args {
    fun input(value: DismissPullRequestReviewInput): DismissPullRequestReviewArgs =
          apply { addArg("input", value) }

  }

  class MoveProjectCardArgs(args: TypeArgBuilder<MoveProjectCardPayload, QModel<MoveProjectCardPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<MoveProjectCardPayload, QModel<MoveProjectCardPayload>> by args {
    fun input(value: MoveProjectCardInput): MoveProjectCardArgs = apply { addArg("input", value) }

  }

  class MoveProjectColumnArgs(args: TypeArgBuilder<MoveProjectColumnPayload, QModel<MoveProjectColumnPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<MoveProjectColumnPayload, QModel<MoveProjectColumnPayload>> by args {
    fun input(value: MoveProjectColumnInput): MoveProjectColumnArgs =
          apply { addArg("input", value) }

  }

  class RemoveOutsideCollaboratorArgs(args: TypeArgBuilder<RemoveOutsideCollaboratorPayload, QModel<RemoveOutsideCollaboratorPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<RemoveOutsideCollaboratorPayload, QModel<RemoveOutsideCollaboratorPayload>> by args {
    fun input(value: RemoveOutsideCollaboratorInput): RemoveOutsideCollaboratorArgs =
          apply { addArg("input", value) }

  }

  class RemoveReactionArgs(args: TypeArgBuilder<RemoveReactionPayload, QModel<RemoveReactionPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<RemoveReactionPayload, QModel<RemoveReactionPayload>> by args {
    fun input(value: RemoveReactionInput): RemoveReactionArgs = apply { addArg("input", value) }

  }

  class RemoveStarArgs(args: TypeArgBuilder<RemoveStarPayload, QModel<RemoveStarPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<RemoveStarPayload, QModel<RemoveStarPayload>> by args {
    fun input(value: RemoveStarInput): RemoveStarArgs = apply { addArg("input", value) }

  }

  class RequestReviewsArgs(args: TypeArgBuilder<RequestReviewsPayload, QModel<RequestReviewsPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<RequestReviewsPayload, QModel<RequestReviewsPayload>> by args {
    fun input(value: RequestReviewsInput): RequestReviewsArgs = apply { addArg("input", value) }

  }

  class SubmitPullRequestReviewArgs(args: TypeArgBuilder<SubmitPullRequestReviewPayload, QModel<SubmitPullRequestReviewPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<SubmitPullRequestReviewPayload, QModel<SubmitPullRequestReviewPayload>> by args {
    fun input(value: SubmitPullRequestReviewInput): SubmitPullRequestReviewArgs =
          apply { addArg("input", value) }

  }

  class UpdateProjectArgs(args: TypeArgBuilder<UpdateProjectPayload, QModel<UpdateProjectPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<UpdateProjectPayload, QModel<UpdateProjectPayload>> by args {
    fun input(value: UpdateProjectInput): UpdateProjectArgs = apply { addArg("input", value) }

  }

  class UpdateProjectCardArgs(args: TypeArgBuilder<UpdateProjectCardPayload, QModel<UpdateProjectCardPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<UpdateProjectCardPayload, QModel<UpdateProjectCardPayload>> by args {
    fun input(value: UpdateProjectCardInput): UpdateProjectCardArgs =
          apply { addArg("input", value) }

  }

  class UpdateProjectColumnArgs(args: TypeArgBuilder<UpdateProjectColumnPayload, QModel<UpdateProjectColumnPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<UpdateProjectColumnPayload, QModel<UpdateProjectColumnPayload>> by args {
    fun input(value: UpdateProjectColumnInput): UpdateProjectColumnArgs =
          apply { addArg("input", value) }

  }

  class UpdatePullRequestReviewArgs(args: TypeArgBuilder<UpdatePullRequestReviewPayload, QModel<UpdatePullRequestReviewPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<UpdatePullRequestReviewPayload, QModel<UpdatePullRequestReviewPayload>> by args {
    fun input(value: UpdatePullRequestReviewInput): UpdatePullRequestReviewArgs =
          apply { addArg("input", value) }

  }

  class UpdatePullRequestReviewCommentArgs(args: TypeArgBuilder<UpdatePullRequestReviewCommentPayload, QModel<UpdatePullRequestReviewCommentPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<UpdatePullRequestReviewCommentPayload, QModel<UpdatePullRequestReviewCommentPayload>> by args {
    fun input(value: UpdatePullRequestReviewCommentInput): UpdatePullRequestReviewCommentArgs =
          apply { addArg("input", value) }

  }

  class UpdateSubscriptionArgs(args: TypeArgBuilder<UpdateSubscriptionPayload, QModel<UpdateSubscriptionPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<UpdateSubscriptionPayload, QModel<UpdateSubscriptionPayload>> by args {
    fun input(value: UpdateSubscriptionInput): UpdateSubscriptionArgs =
          apply { addArg("input", value) }

  }

  class UpdateTopicsArgs(args: TypeArgBuilder<UpdateTopicsPayload, QModel<UpdateTopicsPayload>> = TypeArgBuilder.create()) : TypeArgBuilder<UpdateTopicsPayload, QModel<UpdateTopicsPayload>> by args {
    fun input(value: UpdateTopicsInput): UpdateTopicsArgs = apply { addArg("input", value) }

  }
}

object MovedColumnsInProjectEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 
}

object MoveProjectColumnPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val columnEdge: InitStub<ProjectColumnEdge> by  lazy { typeStub<ProjectColumnEdge>() } 
}

object MoveProjectCardPayload : QType {
  val cardEdge: InitStub<ProjectCardEdge> by  lazy { typeStub<ProjectCardEdge>() } 

  val clientMutationId: Stub<String> by  lazy { stub<String>() } 
}

object MilestonedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val milestoneTitle: Stub<String> by  lazy { stub<String>() } 

  val subject: InitStub<MilestoneItem> by  lazy { typeStub<MilestoneItem>() } 
}

object MilestoneEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Milestone> by  lazy { typeStub<Milestone>() } 
}

object MilestoneConnection : QType {
  val edges: InitStub<MilestoneEdge> by  lazy { typeStub<MilestoneEdge>() } 

  val nodes: InitStub<Milestone> by  lazy { typeStub<Milestone>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object Milestone : QType, UniformResourceLocatable, Node {
  val creator: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val description: Stub<String> by  lazy { stub<String>() } 

  val dueOn: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val number: Stub<Int> by  lazy { stub<Int>() } 

  val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val state: Stub<MilestoneState> by  lazy { stub<MilestoneState>() } 

  val title: Stub<String> by  lazy { stub<String>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 
}

object MergedEvent : QType, UniformResourceLocatable, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val mergeRef: InitStub<Ref> by  lazy { typeStub<Ref>() } 

  val mergeRefName: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 
}

object MentionedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 
}

object LockedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val lockable: InitStub<Lockable> by  lazy { typeStub<Lockable>() } 
}

object LanguageEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Language> by  lazy { typeStub<Language>() } 

  val size: Stub<Int> by  lazy { stub<Int>() } 
}

object LanguageConnection : QType {
  val edges: InitStub<LanguageEdge> by  lazy { typeStub<LanguageEdge>() } 

  val nodes: InitStub<Language> by  lazy { typeStub<Language>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 

  val totalSize: Stub<Int> by  lazy { stub<Int>() } 
}

object Language : QType, Node {
  val color: Stub<String> by  lazy { stub<String>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 
}

object LabeledEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val label: InitStub<Label> by  lazy { typeStub<Label>() } 

  val labelable: InitStub<Labelable> by  lazy { typeStub<Labelable>() } 
}

object LabelEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Label> by  lazy { typeStub<Label>() } 
}

object LabelConnection : QType {
  val edges: InitStub<LabelEdge> by  lazy { typeStub<LabelEdge>() } 

  val nodes: InitStub<Label> by  lazy { typeStub<Label>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object Label : QType, Node {
  val color: Stub<String> by  lazy { stub<String>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val issues: ConfigType<IssuesArgs, IssueConnection> by  lazy { typeConfigStub(IssuesArgs()) } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val pullRequests: ConfigType<PullRequestsArgs, PullRequestConnection> by  lazy { typeConfigStub(PullRequestsArgs()) } 

  val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  class IssuesArgs(args: TypeArgBuilder<IssueConnection, QModel<IssueConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<IssueConnection, QModel<IssueConnection>> by args {
    fun first(value: Int): IssuesArgs = apply { addArg("first", value) }


    fun after(value: String): IssuesArgs = apply { addArg("after", value) }


    fun last(value: Int): IssuesArgs = apply { addArg("last", value) }


    fun before(value: String): IssuesArgs = apply { addArg("before", value) }


    fun labels(value: String): IssuesArgs = apply { addArg("labels", value) }


    fun orderBy(value: IssueOrder): IssuesArgs = apply { addArg("orderBy", value) }


    fun states(value: IssueState): IssuesArgs = apply { addArg("states", value) }

  }

  class PullRequestsArgs(args: TypeArgBuilder<PullRequestConnection, QModel<PullRequestConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<PullRequestConnection, QModel<PullRequestConnection>> by args {
    fun first(value: Int): PullRequestsArgs = apply { addArg("first", value) }


    fun after(value: String): PullRequestsArgs = apply { addArg("after", value) }


    fun last(value: Int): PullRequestsArgs = apply { addArg("last", value) }


    fun before(value: String): PullRequestsArgs = apply { addArg("before", value) }

  }
}

object IssueTimelineItemEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<IssueTimelineItem> by  lazy { typeStub<IssueTimelineItem>() } 
}

object IssueTimelineConnection : QType {
  val edges: InitStub<IssueTimelineItemEdge> by  lazy { typeStub<IssueTimelineItemEdge>() } 

  val nodes: InitStub<IssueTimelineItem> by  lazy { typeStub<IssueTimelineItem>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object IssueEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Issue> by  lazy { typeStub<Issue>() } 
}

object IssueConnection : QType {
  val edges: InitStub<IssueEdge> by  lazy { typeStub<IssueEdge>() } 

  val nodes: InitStub<Issue> by  lazy { typeStub<Issue>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object IssueCommentEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<IssueComment> by  lazy { typeStub<IssueComment>() } 
}

object IssueCommentConnection : QType {
  val edges: InitStub<IssueCommentEdge> by  lazy { typeStub<IssueCommentEdge>() } 

  val nodes: InitStub<IssueComment> by  lazy { typeStub<IssueComment>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object IssueComment : QType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val authorAssociation: Stub<CommentAuthorAssociation> by  lazy { stub<CommentAuthorAssociation>() } 

  override val body: Stub<String> by  lazy { stub<String>() } 

  override val bodyHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val bodyText: Stub<String> by  lazy { stub<String>() } 

  override val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val createdViaEmail: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val editor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val issue: InitStub<Issue> by  lazy { typeStub<Issue>() } 

  override val lastEditedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val publishedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val reactionGroups: InitStub<ReactionGroup> by  lazy { typeStub<ReactionGroup>() } 

  override val reactions: ConfigType<Reactable.ReactionsArgs, ReactionConnection> by  lazy { typeConfigStub(Reactable.ReactionsArgs()) } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  override val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val viewerCanDelete: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanReact: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanUpdate: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason> by  lazy { stub<CommentCannotUpdateReason>() } 

  override val viewerDidAuthor: Stub<Boolean> by  lazy { stub<Boolean>() } 
}

object Issue : QType, UniformResourceLocatable, Subscribable, RepositoryNode, Reactable, Lockable, Labelable, UpdatableComment, Updatable, Comment, Closable, Assignable, Node {
  override val assignees: ConfigType<Assignable.AssigneesArgs, UserConnection> by  lazy { typeConfigStub(Assignable.AssigneesArgs()) } 

  override val author: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val authorAssociation: Stub<CommentAuthorAssociation> by  lazy { stub<CommentAuthorAssociation>() } 

  override val body: Stub<String> by  lazy { stub<String>() } 

  override val bodyHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val bodyText: Stub<String> by  lazy { stub<String>() } 

  override val closed: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val comments: ConfigType<CommentsArgs, IssueCommentConnection> by  lazy { typeConfigStub(CommentsArgs()) } 

  override val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val createdViaEmail: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val editor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  override val labels: ConfigType<Labelable.LabelsArgs, LabelConnection> by  lazy { typeConfigStub(Labelable.LabelsArgs()) } 

  override val lastEditedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val locked: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val milestone: InitStub<Milestone> by  lazy { typeStub<Milestone>() } 

  val number: Stub<Int> by  lazy { stub<Int>() } 

  val participants: ConfigType<ParticipantsArgs, UserConnection> by  lazy { typeConfigStub(ParticipantsArgs()) } 

  override val publishedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val reactionGroups: InitStub<ReactionGroup> by  lazy { typeStub<ReactionGroup>() } 

  override val reactions: ConfigType<Reactable.ReactionsArgs, ReactionConnection> by  lazy { typeConfigStub(Reactable.ReactionsArgs()) } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val state: Stub<IssueState> by  lazy { stub<IssueState>() } 

  val timeline: ConfigType<TimelineArgs, IssueTimelineConnection> by  lazy { typeConfigStub(TimelineArgs()) } 

  val title: Stub<String> by  lazy { stub<String>() } 

  override val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 

  override val viewerCanReact: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanSubscribe: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanUpdate: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason> by  lazy { stub<CommentCannotUpdateReason>() } 

  override val viewerDidAuthor: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerSubscription: Stub<SubscriptionState> by  lazy { stub<SubscriptionState>() } 

  class CommentsArgs(args: TypeArgBuilder<IssueCommentConnection, QModel<IssueCommentConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<IssueCommentConnection, QModel<IssueCommentConnection>> by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class ParticipantsArgs(args: TypeArgBuilder<UserConnection, QModel<UserConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<UserConnection, QModel<UserConnection>> by args {
    fun first(value: Int): ParticipantsArgs = apply { addArg("first", value) }


    fun after(value: String): ParticipantsArgs = apply { addArg("after", value) }


    fun last(value: Int): ParticipantsArgs = apply { addArg("last", value) }


    fun before(value: String): ParticipantsArgs = apply { addArg("before", value) }

  }

  class TimelineArgs(args: TypeArgBuilder<IssueTimelineConnection, QModel<IssueTimelineConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<IssueTimelineConnection, QModel<IssueTimelineConnection>> by args {
    fun first(value: Int): TimelineArgs = apply { addArg("first", value) }


    fun after(value: String): TimelineArgs = apply { addArg("after", value) }


    fun last(value: Int): TimelineArgs = apply { addArg("last", value) }


    fun before(value: String): TimelineArgs = apply { addArg("before", value) }


    fun since(value: DateTime): TimelineArgs = apply { addArg("since", value) }

  }
}

object HeadRefRestoredEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 
}

object HeadRefForcePushedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val afterCommit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val beforeCommit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val ref: InitStub<Ref> by  lazy { typeStub<Ref>() } 
}

object HeadRefDeletedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val headRef: InitStub<Ref> by  lazy { typeStub<Ref>() } 

  val headRefName: Stub<String> by  lazy { stub<String>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 
}

object GpgSignature : QType, GitSignature {
  override val email: Stub<String> by  lazy { stub<String>() } 

  override val isValid: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val keyId: Stub<String> by  lazy { stub<String>() } 

  override val payload: Stub<String> by  lazy { stub<String>() } 

  override val signature: Stub<String> by  lazy { stub<String>() } 

  override val signer: InitStub<User> by  lazy { typeStub<User>() } 

  override val state: Stub<GitSignatureState> by  lazy { stub<GitSignatureState>() } 
}

object GitActor : QType {
  val avatarUrl: Config<AvatarUrlArgs, URI> by  lazy { configStub(AvatarUrlArgs()) } 

  val date: Stub<GitTimestamp> by  lazy { stub<GitTimestamp>() } 

  val email: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val user: InitStub<User> by  lazy { typeStub<User>() } 

  class AvatarUrlArgs(args: ArgBuilder<URI> = ArgBuilder.create()) : ArgBuilder<URI> by args {
    fun size(value: Int): AvatarUrlArgs = apply { addArg("size", value) }

  }
}

object GistEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Gist> by  lazy { typeStub<Gist>() } 
}

object GistConnection : QType {
  val edges: InitStub<GistEdge> by  lazy { typeStub<GistEdge>() } 

  val nodes: InitStub<Gist> by  lazy { typeStub<Gist>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object GistCommentEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<GistComment> by  lazy { typeStub<GistComment>() } 
}

object GistCommentConnection : QType {
  val edges: InitStub<GistCommentEdge> by  lazy { typeStub<GistCommentEdge>() } 

  val nodes: InitStub<GistComment> by  lazy { typeStub<GistComment>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object GistComment : QType, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val authorAssociation: Stub<CommentAuthorAssociation> by  lazy { stub<CommentAuthorAssociation>() } 

  override val body: Stub<String> by  lazy { stub<String>() } 

  override val bodyHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  override val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val createdViaEmail: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val editor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  override val lastEditedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val publishedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val viewerCanDelete: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanUpdate: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason> by  lazy { stub<CommentCannotUpdateReason>() } 

  override val viewerDidAuthor: Stub<Boolean> by  lazy { stub<Boolean>() } 
}

object Gist : QType, Starrable, Node {
  val comments: ConfigType<CommentsArgs, GistCommentConnection> by  lazy { typeConfigStub(CommentsArgs()) } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val description: Stub<String> by  lazy { stub<String>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val isPublic: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val owner: InitStub<RepositoryOwner> by  lazy { typeStub<RepositoryOwner>() } 

  override val stargazers: ConfigType<Starrable.StargazersArgs, StargazerConnection> by  lazy { typeConfigStub(Starrable.StargazersArgs()) } 

  val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val viewerHasStarred: Stub<Boolean> by  lazy { stub<Boolean>() } 

  class CommentsArgs(args: TypeArgBuilder<GistCommentConnection, QModel<GistCommentConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<GistCommentConnection, QModel<GistCommentConnection>> by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

object FollowingConnection : QType {
  val edges: InitStub<UserEdge> by  lazy { typeStub<UserEdge>() } 

  val nodes: InitStub<User> by  lazy { typeStub<User>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object FollowerConnection : QType {
  val edges: InitStub<UserEdge> by  lazy { typeStub<UserEdge>() } 

  val nodes: InitStub<User> by  lazy { typeStub<User>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ExternalIdentityScimAttributes : QType {
  val username: Stub<String> by  lazy { stub<String>() } 
}

object ExternalIdentitySamlAttributes : QType {
  val nameId: Stub<String> by  lazy { stub<String>() } 
}

object ExternalIdentityEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<ExternalIdentity> by  lazy { typeStub<ExternalIdentity>() } 
}

object ExternalIdentityConnection : QType {
  val edges: InitStub<ExternalIdentityEdge> by  lazy { typeStub<ExternalIdentityEdge>() } 

  val nodes: InitStub<ExternalIdentity> by  lazy { typeStub<ExternalIdentity>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object ExternalIdentity : QType, Node {
  val guid: Stub<String> by  lazy { stub<String>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val organizationInvitation: InitStub<OrganizationInvitation> by  lazy { typeStub<OrganizationInvitation>() } 

  val samlIdentity: InitStub<ExternalIdentitySamlAttributes> by  lazy { typeStub<ExternalIdentitySamlAttributes>() } 

  val scimIdentity: InitStub<ExternalIdentityScimAttributes> by  lazy { typeStub<ExternalIdentityScimAttributes>() } 

  val user: InitStub<User> by  lazy { typeStub<User>() } 
}

object DismissPullRequestReviewPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val pullRequestReview: InitStub<PullRequestReview> by  lazy { typeStub<PullRequestReview>() } 
}

object DeploymentStatusEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<DeploymentStatus> by  lazy { typeStub<DeploymentStatus>() } 
}

object DeploymentStatusConnection : QType {
  val edges: InitStub<DeploymentStatusEdge> by  lazy { typeStub<DeploymentStatusEdge>() } 

  val nodes: InitStub<DeploymentStatus> by  lazy { typeStub<DeploymentStatus>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object DeploymentStatus : QType, Node {
  val creator: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val deployment: InitStub<Deployment> by  lazy { typeStub<Deployment>() } 

  val description: Stub<String> by  lazy { stub<String>() } 

  val environmentUrl: Stub<URI> by  lazy { stub<URI>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val logUrl: Stub<URI> by  lazy { stub<URI>() } 

  val state: Stub<DeploymentStatusState> by  lazy { stub<DeploymentStatusState>() } 
}

object DeploymentEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Deployment> by  lazy { typeStub<Deployment>() } 
}

object DeploymentConnection : QType {
  val edges: InitStub<DeploymentEdge> by  lazy { typeStub<DeploymentEdge>() } 

  val nodes: InitStub<Deployment> by  lazy { typeStub<Deployment>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object Deployment : QType, Node {
  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val creator: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val environment: Stub<String> by  lazy { stub<String>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val latestStatus: InitStub<DeploymentStatus> by  lazy { typeStub<DeploymentStatus>() } 

  val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val state: Stub<DeploymentState> by  lazy { stub<DeploymentState>() } 

  val statuses: ConfigType<StatusesArgs, DeploymentStatusConnection> by  lazy { typeConfigStub(StatusesArgs()) } 

  class StatusesArgs(args: TypeArgBuilder<DeploymentStatusConnection, QModel<DeploymentStatusConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<DeploymentStatusConnection, QModel<DeploymentStatusConnection>> by args {
    fun first(value: Int): StatusesArgs = apply { addArg("first", value) }


    fun after(value: String): StatusesArgs = apply { addArg("after", value) }


    fun last(value: Int): StatusesArgs = apply { addArg("last", value) }


    fun before(value: String): StatusesArgs = apply { addArg("before", value) }

  }
}

object DeployedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  val deployment: InitStub<Deployment> by  lazy { typeStub<Deployment>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val ref: InitStub<Ref> by  lazy { typeStub<Ref>() } 
}

object DemilestonedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val milestoneTitle: Stub<String> by  lazy { stub<String>() } 

  val subject: InitStub<MilestoneItem> by  lazy { typeStub<MilestoneItem>() } 
}

object DeletePullRequestReviewPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val pullRequestReview: InitStub<PullRequestReview> by  lazy { typeStub<PullRequestReview>() } 
}

object DeleteProjectPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val owner: InitStub<ProjectOwner> by  lazy { typeStub<ProjectOwner>() } 
}

object DeleteProjectColumnPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val deletedColumnId: Stub<String> by  lazy { stub<String>() } 

  val project: InitStub<Project> by  lazy { typeStub<Project>() } 
}

object DeleteProjectCardPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val column: InitStub<ProjectColumn> by  lazy { typeStub<ProjectColumn>() } 

  val deletedCardId: Stub<String> by  lazy { stub<String>() } 
}

object DeclineTopicSuggestionPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val topic: InitStub<Topic> by  lazy { typeStub<Topic>() } 
}

object CreateProjectPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val project: InitStub<Project> by  lazy { typeStub<Project>() } 
}

object ConvertedNoteToIssueEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 
}

object CommitHistoryConnection : QType {
  val edges: InitStub<CommitEdge> by  lazy { typeStub<CommitEdge>() } 

  val nodes: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 
}

object CommitEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<Commit> by  lazy { typeStub<Commit>() } 
}

object CommitCommentThread : QType, RepositoryNode, Node {
  val comments: ConfigType<CommentsArgs, CommitCommentConnection> by  lazy { typeConfigStub(CommentsArgs()) } 

  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val path: Stub<String> by  lazy { stub<String>() } 

  val position: Stub<Int> by  lazy { stub<Int>() } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  class CommentsArgs(args: TypeArgBuilder<CommitCommentConnection, QModel<CommitCommentConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<CommitCommentConnection, QModel<CommitCommentConnection>> by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }
}

object CommitCommentEdge : QType {
  val cursor: Stub<String> by  lazy { stub<String>() } 

  val node: InitStub<CommitComment> by  lazy { typeStub<CommitComment>() } 
}

object CommitCommentConnection : QType {
  val edges: InitStub<CommitCommentEdge> by  lazy { typeStub<CommitCommentEdge>() } 

  val nodes: InitStub<CommitComment> by  lazy { typeStub<CommitComment>() } 

  val pageInfo: InitStub<PageInfo> by  lazy { typeStub<PageInfo>() } 

  val totalCount: Stub<Int> by  lazy { stub<Int>() } 
}

object CommitComment : QType, RepositoryNode, Reactable, UpdatableComment, Updatable, Deletable, Comment, Node {
  override val author: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val authorAssociation: Stub<CommentAuthorAssociation> by  lazy { stub<CommentAuthorAssociation>() } 

  override val body: Stub<String> by  lazy { stub<String>() } 

  override val bodyHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  override val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val createdViaEmail: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val editor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  override val lastEditedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val path: Stub<String> by  lazy { stub<String>() } 

  val position: Stub<Int> by  lazy { stub<Int>() } 

  override val publishedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val reactionGroups: InitStub<ReactionGroup> by  lazy { typeStub<ReactionGroup>() } 

  override val reactions: ConfigType<Reactable.ReactionsArgs, ReactionConnection> by  lazy { typeConfigStub(Reactable.ReactionsArgs()) } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  override val updatedAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val viewerCanDelete: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanReact: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCanUpdate: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerCannotUpdateReasons: Stub<CommentCannotUpdateReason> by  lazy { stub<CommentCannotUpdateReason>() } 

  override val viewerDidAuthor: Stub<Boolean> by  lazy { stub<Boolean>() } 
}

object Commit : QType, Subscribable, GitObject, Node {
  override val abbreviatedOid: Stub<String> by  lazy { stub<String>() } 

  val author: InitStub<GitActor> by  lazy { typeStub<GitActor>() } 

  val authoredByCommitter: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val blame: ConfigType<BlameArgs, Blame> by  lazy { typeConfigStub(BlameArgs()) } 

  val comments: ConfigType<CommentsArgs, CommitCommentConnection> by  lazy { typeConfigStub(CommentsArgs()) } 

  override val commitResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val commitUrl: Stub<URI> by  lazy { stub<URI>() } 

  val committedDate: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val committedViaWeb: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val committer: InitStub<GitActor> by  lazy { typeStub<GitActor>() } 

  val history: ConfigType<HistoryArgs, CommitHistoryConnection> by  lazy { typeConfigStub(HistoryArgs()) } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val message: Stub<String> by  lazy { stub<String>() } 

  val messageBody: Stub<String> by  lazy { stub<String>() } 

  val messageBodyHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  val messageHeadline: Stub<String> by  lazy { stub<String>() } 

  val messageHeadlineHTML: Stub<HTML> by  lazy { stub<HTML>() } 

  override val oid: Stub<GitObjectID> by  lazy { stub<GitObjectID>() } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val signature: InitStub<GitSignature> by  lazy { typeStub<GitSignature>() } 

  val status: InitStub<Status> by  lazy { typeStub<Status>() } 

  val tree: InitStub<Tree> by  lazy { typeStub<Tree>() } 

  val treeResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  val treeUrl: Stub<URI> by  lazy { stub<URI>() } 

  val url: Stub<URI> by  lazy { stub<URI>() } 

  override val viewerCanSubscribe: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val viewerSubscription: Stub<SubscriptionState> by  lazy { stub<SubscriptionState>() } 

  class BlameArgs(args: TypeArgBuilder<Blame, QModel<Blame>> = TypeArgBuilder.create()) : TypeArgBuilder<Blame, QModel<Blame>> by args {
    fun path(value: String): BlameArgs = apply { addArg("path", value) }

  }

  class CommentsArgs(args: TypeArgBuilder<CommitCommentConnection, QModel<CommitCommentConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<CommitCommentConnection, QModel<CommitCommentConnection>> by args {
    fun first(value: Int): CommentsArgs = apply { addArg("first", value) }


    fun after(value: String): CommentsArgs = apply { addArg("after", value) }


    fun last(value: Int): CommentsArgs = apply { addArg("last", value) }


    fun before(value: String): CommentsArgs = apply { addArg("before", value) }

  }

  class HistoryArgs(args: TypeArgBuilder<CommitHistoryConnection, QModel<CommitHistoryConnection>> = TypeArgBuilder.create()) : TypeArgBuilder<CommitHistoryConnection, QModel<CommitHistoryConnection>> by args {
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

object CommentDeletedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 
}

object CodeOfConduct : QType {
  val body: Stub<String> by  lazy { stub<String>() } 

  val key: Stub<String> by  lazy { stub<String>() } 

  val name: Stub<String> by  lazy { stub<String>() } 

  val url: Stub<URI> by  lazy { stub<URI>() } 
}

object ClosedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val closable: InitStub<Closable> by  lazy { typeStub<Closable>() } 

  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 
}

object Bot : QType, UniformResourceLocatable, Actor, Node {
  override val avatarUrl: Config<Actor.AvatarUrlArgs, URI> by  lazy { configStub(Actor.AvatarUrlArgs()) } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  override val login: Stub<String> by  lazy { stub<String>() } 

  override val resourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val url: Stub<URI> by  lazy { stub<URI>() } 
}

object Blob : QType, GitObject, Node {
  override val abbreviatedOid: Stub<String> by  lazy { stub<String>() } 

  val byteSize: Stub<Int> by  lazy { stub<Int>() } 

  override val commitResourcePath: Stub<URI> by  lazy { stub<URI>() } 

  override val commitUrl: Stub<URI> by  lazy { stub<URI>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val isBinary: Stub<Boolean> by  lazy { stub<Boolean>() } 

  val isTruncated: Stub<Boolean> by  lazy { stub<Boolean>() } 

  override val oid: Stub<GitObjectID> by  lazy { stub<GitObjectID>() } 

  override val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val text: Stub<String> by  lazy { stub<String>() } 
}

object BlameRange : QType {
  val age: Stub<Int> by  lazy { stub<Int>() } 

  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val endingLine: Stub<Int> by  lazy { stub<Int>() } 

  val startingLine: Stub<Int> by  lazy { stub<Int>() } 
}

object Blame : QType {
  val ranges: InitStub<BlameRange> by  lazy { typeStub<BlameRange>() } 
}

object BaseRefForcePushedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val afterCommit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val beforeCommit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val pullRequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val ref: InitStub<Ref> by  lazy { typeStub<Ref>() } 
}

object BaseRefChangedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 
}

object AssignedEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val assignable: InitStub<Assignable> by  lazy { typeStub<Assignable>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 

  val user: InitStub<User> by  lazy { typeStub<User>() } 
}

object AddedToProjectEvent : QType, Node {
  val actor: InitStub<Actor> by  lazy { typeStub<Actor>() } 

  val createdAt: Stub<DateTime> by  lazy { stub<DateTime>() } 

  val databaseId: Stub<Int> by  lazy { stub<Int>() } 

  override val id: Stub<String> by  lazy { stub<String>() } 
}

object AddStarPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val starrable: InitStub<Starrable> by  lazy { typeStub<Starrable>() } 
}

object AddReactionPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val reaction: InitStub<Reaction> by  lazy { typeStub<Reaction>() } 

  val subject: InitStub<Reactable> by  lazy { typeStub<Reactable>() } 
}

object AddPullRequestReviewPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val pullRequestReview: InitStub<PullRequestReview> by  lazy { typeStub<PullRequestReview>() } 

  val reviewEdge: InitStub<PullRequestReviewEdge> by  lazy { typeStub<PullRequestReviewEdge>() } 
}

object AddPullRequestReviewCommentPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val comment: InitStub<PullRequestReviewComment> by  lazy { typeStub<PullRequestReviewComment>() } 

  val commentEdge: InitStub<PullRequestReviewCommentEdge> by  lazy { typeStub<PullRequestReviewCommentEdge>() } 
}

object AddProjectColumnPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val columnEdge: InitStub<ProjectColumnEdge> by  lazy { typeStub<ProjectColumnEdge>() } 

  val project: InitStub<Project> by  lazy { typeStub<Project>() } 
}

object AddProjectCardPayload : QType {
  val cardEdge: InitStub<ProjectCardEdge> by  lazy { typeStub<ProjectCardEdge>() } 

  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val projectColumn: InitStub<Project> by  lazy { typeStub<Project>() } 
}

object AddCommentPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val commentEdge: InitStub<IssueCommentEdge> by  lazy { typeStub<IssueCommentEdge>() } 

  val subject: InitStub<Node> by  lazy { typeStub<Node>() } 

  val timelineEdge: InitStub<IssueTimelineItemEdge> by  lazy { typeStub<IssueTimelineItemEdge>() } 
}

object AcceptTopicSuggestionPayload : QType {
  val clientMutationId: Stub<String> by  lazy { stub<String>() } 

  val topic: InitStub<Topic> by  lazy { typeStub<Topic>() } 
}

object SearchResultItem : QType {
  val issue: InitStub<Issue> by  lazy { typeStub<Issue>() } 

  val pullrequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 

  val repository: InitStub<Repository> by  lazy { typeStub<Repository>() } 

  val user: InitStub<User> by  lazy { typeStub<User>() } 

  val organization: InitStub<Organization> by  lazy { typeStub<Organization>() } 
}

object ReviewDismissalAllowanceActor : QType {
  val user: InitStub<User> by  lazy { typeStub<User>() } 

  val team: InitStub<Team> by  lazy { typeStub<Team>() } 
}

object RenamedTitleSubject : QType {
  val issue: InitStub<Issue> by  lazy { typeStub<Issue>() } 

  val pullrequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 
}

object ReferencedSubject : QType {
  val issue: InitStub<Issue> by  lazy { typeStub<Issue>() } 

  val pullrequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 
}

object PushAllowanceActor : QType {
  val user: InitStub<User> by  lazy { typeStub<User>() } 

  val team: InitStub<Team> by  lazy { typeStub<Team>() } 
}

object PullRequestTimelineItem : QType {
  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val commitcommentthread: InitStub<CommitCommentThread> by  lazy { typeStub<CommitCommentThread>() } 

  val pullrequestreview: InitStub<PullRequestReview> by  lazy { typeStub<PullRequestReview>() } 

  val pullrequestreviewthread: InitStub<PullRequestReviewThread> by  lazy { typeStub<PullRequestReviewThread>() } 

  val pullrequestreviewcomment: InitStub<PullRequestReviewComment> by  lazy { typeStub<PullRequestReviewComment>() } 

  val issuecomment: InitStub<IssueComment> by  lazy { typeStub<IssueComment>() } 

  val closedevent: InitStub<ClosedEvent> by  lazy { typeStub<ClosedEvent>() } 

  val reopenedevent: InitStub<ReopenedEvent> by  lazy { typeStub<ReopenedEvent>() } 

  val subscribedevent: InitStub<SubscribedEvent> by  lazy { typeStub<SubscribedEvent>() } 

  val unsubscribedevent: InitStub<UnsubscribedEvent> by  lazy { typeStub<UnsubscribedEvent>() } 

  val mergedevent: InitStub<MergedEvent> by  lazy { typeStub<MergedEvent>() } 

  val referencedevent: InitStub<ReferencedEvent> by  lazy { typeStub<ReferencedEvent>() } 

  val assignedevent: InitStub<AssignedEvent> by  lazy { typeStub<AssignedEvent>() } 

  val unassignedevent: InitStub<UnassignedEvent> by  lazy { typeStub<UnassignedEvent>() } 

  val labeledevent: InitStub<LabeledEvent> by  lazy { typeStub<LabeledEvent>() } 

  val unlabeledevent: InitStub<UnlabeledEvent> by  lazy { typeStub<UnlabeledEvent>() } 

  val milestonedevent: InitStub<MilestonedEvent> by  lazy { typeStub<MilestonedEvent>() } 

  val demilestonedevent: InitStub<DemilestonedEvent> by  lazy { typeStub<DemilestonedEvent>() } 

  val renamedtitleevent: InitStub<RenamedTitleEvent> by  lazy { typeStub<RenamedTitleEvent>() } 

  val lockedevent: InitStub<LockedEvent> by  lazy { typeStub<LockedEvent>() } 

  val unlockedevent: InitStub<UnlockedEvent> by  lazy { typeStub<UnlockedEvent>() } 

  val deployedevent: InitStub<DeployedEvent> by  lazy { typeStub<DeployedEvent>() } 

  val headrefdeletedevent: InitStub<HeadRefDeletedEvent> by  lazy { typeStub<HeadRefDeletedEvent>() } 

  val headrefrestoredevent: InitStub<HeadRefRestoredEvent> by  lazy { typeStub<HeadRefRestoredEvent>() } 

  val headrefforcepushedevent: InitStub<HeadRefForcePushedEvent> by  lazy { typeStub<HeadRefForcePushedEvent>() } 

  val baserefforcepushedevent: InitStub<BaseRefForcePushedEvent> by  lazy { typeStub<BaseRefForcePushedEvent>() } 

  val reviewrequestedevent: InitStub<ReviewRequestedEvent> by  lazy { typeStub<ReviewRequestedEvent>() } 

  val reviewrequestremovedevent: InitStub<ReviewRequestRemovedEvent> by  lazy { typeStub<ReviewRequestRemovedEvent>() } 

  val reviewdismissedevent: InitStub<ReviewDismissedEvent> by  lazy { typeStub<ReviewDismissedEvent>() } 
}

object ProjectCardItem : QType {
  val issue: InitStub<Issue> by  lazy { typeStub<Issue>() } 

  val pullrequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 
}

object MilestoneItem : QType {
  val issue: InitStub<Issue> by  lazy { typeStub<Issue>() } 

  val pullrequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 
}

object IssueTimelineItem : QType {
  val commit: InitStub<Commit> by  lazy { typeStub<Commit>() } 

  val issuecomment: InitStub<IssueComment> by  lazy { typeStub<IssueComment>() } 

  val closedevent: InitStub<ClosedEvent> by  lazy { typeStub<ClosedEvent>() } 

  val reopenedevent: InitStub<ReopenedEvent> by  lazy { typeStub<ReopenedEvent>() } 

  val subscribedevent: InitStub<SubscribedEvent> by  lazy { typeStub<SubscribedEvent>() } 

  val unsubscribedevent: InitStub<UnsubscribedEvent> by  lazy { typeStub<UnsubscribedEvent>() } 

  val referencedevent: InitStub<ReferencedEvent> by  lazy { typeStub<ReferencedEvent>() } 

  val assignedevent: InitStub<AssignedEvent> by  lazy { typeStub<AssignedEvent>() } 

  val unassignedevent: InitStub<UnassignedEvent> by  lazy { typeStub<UnassignedEvent>() } 

  val labeledevent: InitStub<LabeledEvent> by  lazy { typeStub<LabeledEvent>() } 

  val unlabeledevent: InitStub<UnlabeledEvent> by  lazy { typeStub<UnlabeledEvent>() } 

  val milestonedevent: InitStub<MilestonedEvent> by  lazy { typeStub<MilestonedEvent>() } 

  val demilestonedevent: InitStub<DemilestonedEvent> by  lazy { typeStub<DemilestonedEvent>() } 

  val renamedtitleevent: InitStub<RenamedTitleEvent> by  lazy { typeStub<RenamedTitleEvent>() } 

  val lockedevent: InitStub<LockedEvent> by  lazy { typeStub<LockedEvent>() } 

  val unlockedevent: InitStub<UnlockedEvent> by  lazy { typeStub<UnlockedEvent>() } 
}

object IssueOrPullRequest : QType {
  val issue: InitStub<Issue> by  lazy { typeStub<Issue>() } 

  val pullrequest: InitStub<PullRequest> by  lazy { typeStub<PullRequest>() } 
}

object X509Certificate : QType {
  val model: Stub<String> by  lazy { stub<String>() } 
}

object URI : QType {
  val model: Stub<String> by  lazy { stub<String>() } 
}

object HTML : QType {
  val model: Stub<String> by  lazy { stub<String>() } 
}

object GitTimestamp : QType {
  val model: Stub<String> by  lazy { stub<String>() } 
}

object GitObjectID : QType {
  val model: Stub<String> by  lazy { stub<String>() } 
}

object DateTime : QType {
  val model: Stub<String> by  lazy { stub<String>() } 
}

data class UpdateTopicsInput(private val repositoryId: String,
    private val topicNames: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class UpdateSubscriptionInput(private val subscribableId: String,
    private val state: SubscriptionState) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class UpdatePullRequestReviewInput(private val pullRequestReviewId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class UpdatePullRequestReviewCommentInput(private val pullRequestReviewCommentId: String,
    private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class UpdateProjectInput(private val projectId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  private var state: ProjectState? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun body(model: String) = apply { body = model }

  fun state(model: ProjectState) = apply { state = model }
}

data class UpdateProjectColumnInput(private val projectColumnId: String,
    private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class UpdateProjectCardInput(private val projectCardId: String,
    private val note: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class TeamOrder(private val field: TeamOrderField,
    private val direction: OrderDirection) : QInput {
}

data class SubmitPullRequestReviewInput(private val pullRequestReviewId: String,
    private val event: PullRequestReviewEvent) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun body(model: String) = apply { body = model }
}

data class StarOrder(private val field: StarOrderField,
    private val direction: OrderDirection) : QInput {
}

data class RequestReviewsInput(private val pullRequestId: String, private val userIds: String,
    private val teamIds: String) : QInput {
  private var clientMutationId: String? = null
  private var union: Boolean? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun union(model: Boolean) = apply { union = model }
}

data class RepositoryOrder(private val field: RepositoryOrderField,
    private val direction: OrderDirection) : QInput {
}

data class RemoveStarInput(private val starrableId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class RemoveReactionInput(private val subjectId: String,
    private val content: ReactionContent) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class RemoveOutsideCollaboratorInput(private val userId: String,
    private val organizationId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
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
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun afterColumnId(model: String) = apply { afterColumnId = model }
}

data class MoveProjectCardInput(private val cardId: String, private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  private var afterCardId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun afterCardId(model: String) = apply { afterCardId = model }
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
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class DeletePullRequestReviewInput(private val pullRequestReviewId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class DeleteProjectInput(private val projectId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class DeleteProjectColumnInput(private val columnId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class DeleteProjectCardInput(private val cardId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class DeclineTopicSuggestionInput(private val repositoryId: String, private val name: String,
    private val reason: TopicSuggestionDeclineReason) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class CreateProjectInput(private val ownerId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  private var body: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun body(model: String) = apply { body = model }
}

data class CommitAuthor(private val emails: String) : QInput {
  private var id: String? = null
  fun id(model: String) = apply { id = model }
}

data class AddStarInput(private val starrableId: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class AddReactionInput(private val subjectId: String,
    private val content: ReactionContent) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
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

data class AddProjectColumnInput(private val projectId: String, private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class AddProjectCardInput(private val projectColumnId: String) : QInput {
  private var clientMutationId: String? = null
  private var contentId: String? = null
  private var note: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }

  fun contentId(model: String) = apply { contentId = model }

  fun note(model: String) = apply { note = model }
}

data class AddCommentInput(private val subjectId: String, private val body: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

data class AcceptTopicSuggestionInput(private val repositoryId: String,
    private val name: String) : QInput {
  private var clientMutationId: String? = null
  fun clientMutationId(model: String) = apply { clientMutationId = model }
}

enum class TopicSuggestionDeclineReason : QType {
  NOT_RELEVANT,

  TOO_SPECIFIC,

  PERSONAL_PREFERENCE,

  TOO_GENERAL
}

enum class TeamRole : QType {
  ADMIN,

  MEMBER
}

enum class TeamPrivacy : QType {
  SECRET,

  VISIBLE
}

enum class TeamOrderField : QType {
  NAME
}

enum class SubscriptionState : QType {
  UNSUBSCRIBED,

  SUBSCRIBED,

  IGNORED
}

enum class StatusState : QType {
  EXPECTED,

  ERROR,

  FAILURE,

  PENDING,

  SUCCESS
}

enum class StarOrderField : QType {
  STARRED_AT
}

enum class SearchType : QType {
  ISSUE,

  REPOSITORY,

  USER
}

enum class RepositoryPrivacy : QType {
  PUBLIC,

  PRIVATE
}

enum class RepositoryOrderField : QType {
  CREATED_AT,

  UPDATED_AT,

  PUSHED_AT,

  NAME,

  STARGAZERS
}

enum class RepositoryLockReason : QType {
  MOVING,

  BILLING,

  RENAME,

  MIGRATING
}

enum class RepositoryCollaboratorAffiliation : QType {
  ALL,

  OUTSIDE
}

enum class RepositoryAffiliation : QType {
  OWNER,

  COLLABORATOR,

  ORGANIZATION_MEMBER
}

enum class ReactionOrderField : QType {
  CREATED_AT
}

enum class ReactionContent : QType {
  THUMBS_UP,

  THUMBS_DOWN,

  LAUGH,

  HOORAY,

  CONFUSED,

  HEART
}

enum class PullRequestState : QType {
  OPEN,

  CLOSED,

  MERGED
}

enum class PullRequestReviewState : QType {
  PENDING,

  COMMENTED,

  APPROVED,

  CHANGES_REQUESTED,

  DISMISSED
}

enum class PullRequestReviewEvent : QType {
  COMMENT,

  APPROVE,

  REQUEST_CHANGES,

  DISMISS
}

enum class PullRequestPubSubTopic : QType {
  UPDATED,

  MARKASREAD,

  HEAD_REF
}

enum class ProjectState : QType {
  OPEN,

  CLOSED
}

enum class ProjectOrderField : QType {
  CREATED_AT,

  UPDATED_AT,

  NAME
}

enum class ProjectCardState : QType {
  CONTENT_ONLY,

  NOTE_ONLY,

  REDACTED
}

enum class OrganizationInvitationRole : QType {
  DIRECT_MEMBER,

  ADMIN,

  BILLING_MANAGER,

  REINSTATE
}

enum class OrderDirection : QType {
  ASC,

  DESC
}

enum class MilestoneState : QType {
  OPEN,

  CLOSED
}

enum class MergeableState : QType {
  MERGEABLE,

  CONFLICTING,

  UNKNOWN
}

enum class LanguageOrderField : QType {
  SIZE
}

enum class IssueState : QType {
  OPEN,

  CLOSED
}

enum class IssuePubSubTopic : QType {
  UPDATED,

  MARKASREAD
}

enum class IssueOrderField : QType {
  CREATED_AT,

  UPDATED_AT,

  COMMENTS
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

enum class GistPrivacy : QType {
  PUBLIC,

  SECRET,

  ALL
}

enum class DeploymentStatusState : QType {
  PENDING,

  SUCCESS,

  FAILURE,

  INACTIVE,

  ERROR
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

enum class DefaultRepositoryPermissionField : QType {
  READ,

  WRITE,

  ADMIN
}

enum class CommentCannotUpdateReason : QType {
  INSUFFICIENT_ACCESS,

  LOCKED,

  LOGIN_REQUIRED,

  MAINTENANCE,

  VERIFIED_EMAIL_REQUIRED
}

enum class CommentAuthorAssociation : QType {
  MEMBER,

  OWNER,

  COLLABORATOR,

  CONTRIBUTOR,

  FIRST_TIME_CONTRIBUTOR,

  NONE
}

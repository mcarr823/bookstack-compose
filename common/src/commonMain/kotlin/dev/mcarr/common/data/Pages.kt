package dev.mcarr.common.data

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pages(
    @SerialName("data")
    override val data: List<Page>,
    @SerialName("total")
    override val total: Int
) : BookstackResponse<Page>

@Serializable
data class Page(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("priority")
    val priority: Int = -1,
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("chapter_id")
    val chapterId: Int,
    @SerialName("draft")
    val draft: Boolean,
    @SerialName("template")
    val template: Boolean,
    @SerialName("created_at")
    val createdAt: Instant,
    @SerialName("updated_at")
    val updatedAt: Instant,
    @SerialName("url")
    val url: String = "",
    @SerialName("created_by")
    val createdBy: Int = -1,
    @SerialName("updated_by")
    val updatedBy: Int = -1,
    @SerialName("owned_by")
    val ownedBy: Int = -1,
)

@Serializable
data class FullPage(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("chapter_id")
    val chapterId: Int,
    @SerialName("draft")
    val draft: Boolean,
    @SerialName("template")
    val template: Boolean,
    @SerialName("created_at")
    val createdAt: Instant,
    @SerialName("updated_at")
    val updatedAt: Instant,
    @SerialName("created_by")
    val createdBy: Author,
    @SerialName("updated_by")
    val updatedBy: Author,
    @SerialName("owned_by")
    val ownedBy: Author,
    @SerialName("tags")
    val tags: List<Tag>,
    @SerialName("priority")
    val priority: Int,
    @SerialName("revision_count")
    val revisionCount: Int,
    @SerialName("html")
    val html: String,
    @SerialName("markdown")
    val markdown: String,
)

/**
 * Must specify bookId OR chapterId.
 * Also, must specify html OR markdown.
 *
 * For now we're using an interface with 4 different data classes.
 * TODO: look into alternatives like polymorphism in serialization
 **/
@Serializable
sealed interface CreatePageRequest: RequestBodyInterface
@Serializable
data class CreatePageRequestBookHtml(
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("name")
    val name: String, //max 255 chars
    @SerialName("html")
    val html: String,
    //TODO: tags
): CreatePageRequest
@Serializable
data class CreatePageRequestBookMarkdown(
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("name")
    val name: String, //max 255 chars
    @SerialName("markdown")
    val markdown: String,
    //TODO: tags
): CreatePageRequest
@Serializable
data class CreatePageRequestChapterHtml(
    @SerialName("chapter_id")
    val chapterId: Int,
    @SerialName("name")
    val name: String, //max 255 chars
    @SerialName("html")
    val html: String,
    //TODO: tags
): CreatePageRequest
@Serializable
data class CreatePageRequestChapterMarkdown(
    @SerialName("chapter_id")
    val chapterId: Int,
    @SerialName("name")
    val name: String, //max 255 chars
    @SerialName("markdown")
    val markdown: String,
    //TODO: tags
): CreatePageRequest

@Serializable
data class CreatePageResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("chapter_id")
    val chapterId: Int,
    @SerialName("priority")
    val priority: Int,
    @SerialName("name")
    val name: String,
    @SerialName("html")
    val html: String,
    @SerialName("editor")
    val editor: String,
    @SerialName("markdown")
    val markdown: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("created_by")
    val createdBy: Author,
    @SerialName("updated_by")
    val updatedBy: Author,
    @SerialName("owned_by")
    val ownedBy: Author,
    @SerialName("created_at")
    val createdAt: Instant,
    @SerialName("updated_at")
    val updatedAt: Instant,
    @SerialName("draft")
    val draft: Boolean,
    @SerialName("template")
    val template: Boolean,
    @SerialName("revision_count")
    val revisionCount: Int,
    //TODO: tags
)

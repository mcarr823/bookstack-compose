package dev.mcarr.common.data.classes

import dev.mcarr.common.data.interfaces.BookstackResponse
import dev.mcarr.common.data.interfaces.ParentPageInterface
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Pages(
    override val data: List<Page>,
    override val total: Int
) : BookstackResponse<Page>

@Serializable
data class Page(
    override val id: Int,
    override val name: String,
    override val slug: String,
    override val priority: Int = -1,
    override val book_id: Int,
    override val chapter_id: Int,
    override val draft: Boolean,
    override val template: Boolean,
    override val created_at: Instant,
    override val updated_at: Instant,
    val url: String = "",
    val created_by: Int = -1,
    val updated_by: Int = -1,
    val owned_by: Int = -1,
) : ParentPageInterface

@Serializable
data class FullPage(
    override val id: Int,
    override val name: String,
    override val slug: String,
    override val book_id: Int,
    override val chapter_id: Int,
    override val draft: Boolean,
    override val template: Boolean,
    override val created_at: Instant,
    override val updated_at: Instant,
    val created_by: Author,
    val updated_by: Author,
    val owned_by: Author,
    val tags: List<Tag>,
    override val priority: Int,
    val revision_count: Int,
    val html: String,
    val markdown: String,
) : ParentPageInterface

/**
 * Must specify bookId OR chapterId.
 * Also, must specify html OR markdown.
 **/
@Serializable
data class CreatePageRequest(
    val book_id: Int? = null,
    val chapter_id: Int? = null,
    val name: String, //max 255 chars
    val html: String? = null,
    val markdown: String? = null,
    //TODO: tags
)

@Serializable
data class CreatePageResponse(
    val id: Int,
    val book_id: Int,
    val chapter_id: Int,
    val priority: Int,
    val name: String,
    val html: String,
    val editor: String,
    val markdown: String,
    val slug: String,
    val created_by: Author,
    val updated_by: Author,
    val owned_by: Author,
    val created_at: Instant,
    val updated_at: Instant,
    val draft: Boolean,
    val template: Boolean,
    val revision_count: Int,
    //TODO: tags
)

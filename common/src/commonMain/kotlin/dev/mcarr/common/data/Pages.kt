package dev.mcarr.common.data

import dev.mcarr.common.data.interfaces.FullPageInterface
import dev.mcarr.common.data.interfaces.PageInterface
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
    override val url: String = "",
    override val created_by: Int = -1,
    override val updated_by: Int = -1,
    override val owned_by: Int = -1,
) : PageInterface

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
    override val created_by: Author,
    override val updated_by: Author,
    override val owned_by: Author,
    override val tags: List<Tag>,
    override val priority: Int,
    override val revision_count: Int,
    override val html: String,
    override val markdown: String,
) : FullPageInterface

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

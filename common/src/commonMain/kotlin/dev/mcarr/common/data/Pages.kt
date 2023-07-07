package dev.mcarr.common.data

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Pages(
    override val data: List<Page>,
    override val total: Int
) : BookstackResponse<Page>

@Serializable
data class Page(
    val id: Int,
    val name: String,
    val slug: String,
    val priority: Int = -1,
    val book_id: Int,
    val chapter_id: Int,
    val draft: Boolean,
    val template: Boolean,
    val created_at: Instant,
    val updated_at: Instant,
    val url: String = "",
    val created_by: Int = -1,
    val updated_by: Int = -1,
    val owned_by: Int = -1,
)

@Serializable
data class FullPage(
    val id: Int,
    val name: String,
    val slug: String,
    val book_id: Int,
    val chapter_id: Int,
    val draft: Boolean,
    val template: Boolean,
    val created_at: Instant,
    val updated_at: Instant,
    val created_by: Author,
    val updated_by: Author,
    val owned_by: Author,
    val tags: List<Tag>,
    val priority: Int,
    val revision_count: Int,
    val html: String,
    val markdown: String,
)

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

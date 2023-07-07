package dev.mcarr.common.data

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Chapters(
    override val data: List<Chapter>,
    override val total: Int
) : BookstackResponse<Chapter>

@Serializable
data class Chapter(
    val id: Int,
    val book_id: Int,
    val name: String,
    val slug: String,
    val description: String,
    val priority: Int,
    val created_at: Instant,
    val updated_at: Instant,
    val created_by: Int,
    val updated_by: Int
)

@Serializable
data class FullChapter(
    val id: Int,
    val book_id: Int,
    val name: String,
    val slug: String,
    val description: String,
    val priority: Int,
    val created_at: Instant,
    val updated_at: Instant,
    val created_by: Author,
    val updated_by: Author,
    val owned_by: Author,
    val tags: List<Tag>,
    val pages: List<Page>
)

@Serializable
data class CreateChapterRequest(
    val book_id: Int,
    val name: String, //max 255 chars
    val description: String, //max 1,000 chars
    //TODO: tags
)

@Serializable
data class CreateChapterResponse(
    val id: Int,
    val book_id: Int,
    val priority: Int,
    val name: String,
    val description: String,
    val slug: String,
    val created_by: Int,
    val updated_by: Int,
    val owned_by: Int,
    val created_at: Instant,
    val updated_at: Instant,
    //TODO: tags
)

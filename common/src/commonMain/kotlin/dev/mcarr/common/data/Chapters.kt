package dev.mcarr.common.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chapters(
    @SerialName("data")
    override val data: List<Chapter>,
    @SerialName("total")
    override val total: Int
) : BookstackResponse<Chapter>

@Serializable
data class Chapter(
    @SerialName("id")
    val id: Int,
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("description")
    val description: String,
    @SerialName("priority")
    val priority: Int,
    @SerialName("created_at")
    val createdAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("updated_at")
    val updatedAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("created_by")
    val createdBy: Int,
    @SerialName("updated_by")
    val updatedBy: Int
)

@Serializable
data class FullChapter(
    @SerialName("id")
    val id: Int,
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("description")
    val description: String,
    @SerialName("priority")
    val priority: Int,
    @SerialName("created_at")
    val createdAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("updated_at")
    val updatedAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("created_by")
    val createdBy: Author,
    @SerialName("updated_by")
    val updatedBy: Author,
    @SerialName("owned_by")
    val ownedBy: Author,
    @SerialName("tags")
    val tags: List<Tag>,
    @SerialName("pages")
    val pages: List<Page>
)

@Serializable
data class CreateChapterRequest(
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("name")
    val name: String, //max 255 chars
    @SerialName("description")
    val description: String, //max 1,000 chars
    //TODO: tags
): RequestBodyInterface

@Serializable
data class CreateChapterResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("priority")
    val priority: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("created_by")
    val createdBy: Int,
    @SerialName("updated_by")
    val updatedBy: Int,
    @SerialName("owned_by")
    val ownedBy: Int,
    @SerialName("created_at")
    val createdAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("updated_at")
    val updatedAt: String, //2023-06-25T10:29:40.000000Z
    //TODO: tags
)

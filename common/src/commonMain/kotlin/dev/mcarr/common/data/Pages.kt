package dev.mcarr.common.data

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
    val createdAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("updated_at")
    val updatedAt: String, //2023-06-25T10:29:40.000000Z
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
    @SerialName("priority")
    val priority: Int,
    @SerialName("revision_count")
    val revisionCount: Int,
    @SerialName("html")
    val html: String,
    @SerialName("markdown")
    val markdown: String,
)
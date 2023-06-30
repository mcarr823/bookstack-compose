package dev.mcarr.common.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Books(
    @SerialName("data")
    override val data: List<Book>,
    @SerialName("total")
    override val total: Int
) : BookstackResponse<Book>

@Serializable
data class Book(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("description")
    val description: String,
    @SerialName("created_at")
    val createdAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("updated_at")
    val updatedAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("created_by")
    val createdBy: Int,
    @SerialName("updated_by")
    val updatedBy: Int,
    @SerialName("owned_by")
    val ownedBy: Int
)

@Serializable
data class FullBook(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("description")
    val description: String,
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
    @SerialName("contents")
    val contents: List<BookContent>,
    @SerialName("tags")
    val tags: List<Tag>,
    @SerialName("cover")
    val cover: BookCover?
)

@Serializable
data class BookContent(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("book_id")
    val bookId: Int,
    @SerialName("created_at")
    val createdAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("updated_at")
    val updatedAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("url")
    val url: String,
    @SerialName("type")
    val type: String,
    @SerialName("pages")
    val pages: List<Page> = listOf()
)

@Serializable
data class BookCover(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
    @SerialName("created_at")
    val createdAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("updated_at")
    val updatedAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("created_by")
    val createdBy: Int,
    @SerialName("updated_by")
    val updatedBy: Int,
    @SerialName("path")
    val path: String,
    @SerialName("type")
    val type: String,
    @SerialName("uploaded_to")
    val uploadedTo: Int,
)
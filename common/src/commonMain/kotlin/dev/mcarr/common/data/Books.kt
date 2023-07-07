package dev.mcarr.common.data

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Books(
    override val data: List<Book>,
    override val total: Int
) : BookstackResponse<Book>

@Serializable
data class Book(
    val id: Int,
    val name: String,
    val slug: String,
    val description: String,
    val created_at: Instant,
    val updated_at: Instant,
    val created_by: Int,
    val updated_by: Int,
    val owned_by: Int
)

@Serializable
data class FullBook(
    val id: Int,
    val name: String,
    val slug: String,
    val description: String,
    val created_at: Instant,
    val updated_at: Instant,
    val created_by: Author,
    val updated_by: Author,
    val owned_by: Author,
    val contents: List<BookContent>,
    val tags: List<Tag>,
    val cover: BookCover?
)

@Serializable
data class BookContent(
    val id: Int,
    val name: String,
    val slug: String,
    val book_id: Int,
    val created_at: Instant,
    val updated_at: Instant,
    val url: String,
    val type: String,
    val pages: List<Page> = listOf()
)

@Serializable
data class BookCover(
    val id: Int,
    val name: String,
    val url: String,
    val created_at: Instant,
    val updated_at: Instant,
    val created_by: Int,
    val updated_by: Int,
    val path: String,
    val type: String,
    val uploaded_to: Int,
)

@Serializable
data class CreateBookRequest(
    val name: String, //max 255 chars
    val description: String, //max 1,000 chars
    //TODO: tags
    //TODO: image
)

@Serializable
data class CreateBookResponse(
    val id: Int,
    val name: String,
    val description: String,
    val slug: String,
    val created_by: Int,
    val updated_by: Int,
    val owned_by: Int,
    val created_at: Instant,
    val updated_at: Instant,
)
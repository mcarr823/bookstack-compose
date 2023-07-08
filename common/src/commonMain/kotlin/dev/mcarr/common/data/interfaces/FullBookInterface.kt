package dev.mcarr.common.data.interfaces

import dev.mcarr.common.data.Author
import dev.mcarr.common.data.BookContent
import dev.mcarr.common.data.BookCover
import dev.mcarr.common.data.Tag
import kotlinx.datetime.Instant

interface FullBookInterface {
    val id: Int
    val name: String
    val slug: String
    val description: String
    val created_at: Instant
    val updated_at: Instant
    val created_by: Author
    val updated_by: Author
    val owned_by: Author
    val contents: List<BookContent>
    val tags: List<Tag>
    val cover: BookCover?
}
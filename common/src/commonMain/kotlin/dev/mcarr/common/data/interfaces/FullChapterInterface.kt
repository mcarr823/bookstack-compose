package dev.mcarr.common.data.interfaces

import dev.mcarr.common.data.Author
import dev.mcarr.common.data.Page
import dev.mcarr.common.data.Tag
import kotlinx.datetime.Instant

interface FullChapterInterface {
    val id: Int
    val book_id: Int
    val name: String
    val slug: String
    val description: String
    val priority: Int
    val created_at: Instant
    val updated_at: Instant
    val created_by: Author
    val updated_by: Author
    val owned_by: Author
    val tags: List<Tag>
    val pages: List<Page>
}
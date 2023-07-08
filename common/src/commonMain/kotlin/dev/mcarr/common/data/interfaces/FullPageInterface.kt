package dev.mcarr.common.data.interfaces

import dev.mcarr.common.data.Author
import dev.mcarr.common.data.Tag
import kotlinx.datetime.Instant

interface FullPageInterface {
    val id: Int
    val name: String
    val slug: String
    val book_id: Int
    val chapter_id: Int
    val draft: Boolean
    val template: Boolean
    val created_at: Instant
    val updated_at: Instant
    val created_by: Author
    val updated_by: Author
    val owned_by: Author
    val tags: List<Tag>
    val priority: Int
    val revision_count: Int
    val html: String
    val markdown: String
}
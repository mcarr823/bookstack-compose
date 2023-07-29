package dev.mcarr.common.data.interfaces

import kotlinx.datetime.Instant

interface ParentPageInterface {
    val id: Int
    val name: String
    val slug: String
    val book_id: Int
    val chapter_id: Int
    val draft: Boolean
    val template: Boolean
    val created_at: Instant
    val updated_at: Instant
    val priority: Int
}
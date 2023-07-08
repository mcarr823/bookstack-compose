package dev.mcarr.common.data.interfaces

import kotlinx.datetime.Instant

interface PageInterface {
    val id: Int
    val name: String
    val slug: String
    val priority: Int
    val book_id: Int
    val chapter_id: Int
    val draft: Boolean
    val template: Boolean
    val created_at: Instant
    val updated_at: Instant
    val url: String
    val created_by: Int
    val updated_by: Int
    val owned_by: Int
}
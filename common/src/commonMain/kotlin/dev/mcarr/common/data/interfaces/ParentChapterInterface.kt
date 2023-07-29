package dev.mcarr.common.data.interfaces

import kotlinx.datetime.Instant

interface ParentChapterInterface {
    val id: Int
    val book_id: Int
    val name: String
    val slug: String
    val description: String
    val priority: Int
    val created_at: Instant
    val updated_at: Instant
}
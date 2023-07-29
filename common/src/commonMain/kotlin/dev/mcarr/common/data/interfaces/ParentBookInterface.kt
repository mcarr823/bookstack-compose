package dev.mcarr.common.data.interfaces

import kotlinx.datetime.Instant

interface ParentBookInterface {
    val id: Int
    val name: String
    val slug: String
    val description: String
    val created_at: Instant
    val updated_at: Instant
}
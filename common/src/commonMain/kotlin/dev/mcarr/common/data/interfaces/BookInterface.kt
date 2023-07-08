package dev.mcarr.common.data.interfaces

import kotlinx.datetime.Instant

interface BookInterface {
    val id: Int
    val name: String
    val slug: String
    val description: String
    val created_at: Instant
    val updated_at: Instant
    val created_by: Int
    val updated_by: Int
    val owned_by: Int
}
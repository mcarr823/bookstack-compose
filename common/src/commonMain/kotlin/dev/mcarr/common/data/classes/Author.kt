package dev.mcarr.common.data.classes

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val id: Int,
    val name: String,
    val slug: String = "",
)

enum class AuthorRole(val value: String) {
    CREATED("created"),
    UPDATED("updated"),
    OWNED("owned")
}

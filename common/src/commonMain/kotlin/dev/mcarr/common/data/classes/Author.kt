package dev.mcarr.common.data.classes

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val id: Int,
    val name: String,
    val slug: String = "",
)
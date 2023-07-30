package dev.mcarr.common.data.classes

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: Int = -1,
    val name: String,
    val value: String,
    val order: Int,
)
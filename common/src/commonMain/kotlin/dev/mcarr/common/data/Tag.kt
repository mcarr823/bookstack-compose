package dev.mcarr.common.data

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: Int = -1,
    val name: String,
    val value: String,
    val order: Int,
)
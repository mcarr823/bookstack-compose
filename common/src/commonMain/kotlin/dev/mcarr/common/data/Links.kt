package dev.mcarr.common.data

import kotlinx.serialization.Serializable

@Serializable
data class Links (
    val html: String,
    val markdown: String
)
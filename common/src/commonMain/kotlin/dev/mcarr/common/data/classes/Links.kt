package dev.mcarr.common.data.classes

import kotlinx.serialization.Serializable

@Serializable
data class Links (
    val html: String,
    val markdown: String
)
package dev.mcarr.common.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links (
    @SerialName("html")
    val html: String,
    @SerialName("markdown")
    val markdown: String
)
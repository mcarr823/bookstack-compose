package dev.mcarr.common.data.classes

import kotlinx.serialization.Serializable

@Serializable
data class BookstackException(
    val error: BookstackExceptionMessage
) : Exception()

@Serializable
data class BookstackExceptionMessage(
    val message: String,
    val code: Int
)
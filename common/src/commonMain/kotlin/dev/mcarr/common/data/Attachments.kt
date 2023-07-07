package dev.mcarr.common.data

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Attachments(
    override val data: List<Attachment>,
    override val total: Int
) : BookstackResponse<Attachment>

@Serializable
data class Attachment(
    val id: Int,
    val name: String,
    val extension: String,
    val uploaded_to: Int,
    val external: Boolean,
    val order: Int,
    val created_at: Instant,
    val updated_at: Instant,
    val created_by: Int,
    val updated_by: Int
)

@Serializable
data class FullAttachment(
    val id: Int,
    val name: String,
    val extension: String,
    val uploaded_to: Int,
    val external: Boolean,
    val order: Int,
    val created_at: Instant,
    val updated_at: Instant,
    val created_by: Author,
    val updated_by: Author,
    val links: Links,
    val content: String,
)


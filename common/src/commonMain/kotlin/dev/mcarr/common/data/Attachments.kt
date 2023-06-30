package dev.mcarr.common.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attachments(
    @SerialName("data")
    override val data: List<Attachment>,
    @SerialName("total")
    override val total: Int
) : BookstackResponse<Attachment>

@Serializable
data class Attachment(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("extension")
    val extension: String,
    @SerialName("uploaded_to")
    val uploadedTo: Int,
    @SerialName("external")
    val external: Boolean,
    @SerialName("order")
    val order: Int,
    @SerialName("created_at")
    val createdAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("updated_at")
    val updatedAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("created_by")
    val createdBy: Int,
    @SerialName("updated_by")
    val updatedBy: Int
)

@Serializable
data class FullAttachment(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("extension")
    val extension: String,
    @SerialName("uploaded_to")
    val uploadedTo: Int,
    @SerialName("external")
    val external: Boolean,
    @SerialName("order")
    val order: Int,
    @SerialName("created_at")
    val createdAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("updated_at")
    val updatedAt: String, //2023-06-25T10:29:40.000000Z
    @SerialName("created_by")
    val createdBy: Author,
    @SerialName("updated_by")
    val updatedBy: Author,
    @SerialName("links")
    val links: Links,
    @SerialName("content")
    val content: String,
)


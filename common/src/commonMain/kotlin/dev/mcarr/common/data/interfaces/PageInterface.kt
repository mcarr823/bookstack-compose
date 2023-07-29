package dev.mcarr.common.data.interfaces

interface PageInterface : ParentPageInterface {
    val created_by: Int
    val updated_by: Int
    val owned_by: Int
    val url: String
}
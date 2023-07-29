package dev.mcarr.common.data.interfaces

interface BookInterface : ParentBookInterface {
    val created_by: Int
    val updated_by: Int
    val owned_by: Int
}
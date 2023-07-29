package dev.mcarr.common.data.interfaces

interface ChapterInterface : ParentChapterInterface {
    val created_by: Int
    val updated_by: Int
}
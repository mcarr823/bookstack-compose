package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.Author

@Entity(
    tableName = ChapterAuthorEntity.TABLE_NAME,
    primaryKeys = [ ChapterAuthorEntity.CHAPTER_ID, ChapterAuthorEntity.ROLE ]
)
class ChapterAuthorEntity(
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = CHAPTER_ID)
    val chapter_id: Int,
    @ColumnInfo(name = ROLE)
    val role: String,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = SLUG)
    val slug: String
) {

    constructor(json: Author, chapterId: Int, role: String) : this(
        id = json.id,
        chapter_id = chapterId,
        role = role,
        name = json.name,
        slug = json.slug
    )

    fun toDataClass() = Author(
        id = id,
        name = name,
        slug = slug
    )

    companion object{
        const val TABLE_NAME = "ChapterAuthorEntity"
        const val ID = "cae_id"
        const val CHAPTER_ID = "cae_chapter_id"
        const val ROLE = "cae_role"
        const val NAME = "cae_name"
        const val SLUG = "cae_slug"
    }
}
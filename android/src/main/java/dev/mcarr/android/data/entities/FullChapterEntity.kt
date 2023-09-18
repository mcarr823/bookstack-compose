package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.FullChapter

@Entity(tableName = FullChapterEntity.TABLE_NAME)
data class FullChapterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = BOOK_ID)
    val book_id: Int,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = SLUG)
    val slug: String,
    @ColumnInfo(name = DESCRIPTION)
    val description: String,
    @ColumnInfo(name = PRIORITY)
    val priority: Int,
    @ColumnInfo(name = CREATED_AT)
    val created_at: String,
    @ColumnInfo(name = UPDATED_AT)
    val updated_at: String
) {

    constructor(json: FullChapter) : this(
        id = json.id,
        book_id = json.book_id,
        name = json.name,
        slug = json.slug,
        description = json.description,
        priority = json.priority,
        created_at = json.created_at.toString(),
        updated_at = json.updated_at.toString()
    )

    companion object{
        const val TABLE_NAME = "FullChapterEntity"
        const val ID = "fce_id"
        const val BOOK_ID = "fce_book_id"
        const val NAME = "fce_name"
        const val SLUG = "fce_slug"
        const val DESCRIPTION = "fce_description"
        const val PRIORITY = "fce_priority"
        const val CREATED_AT = "fce_created_at"
        const val UPDATED_AT = "fce_updated_at"
    }

}
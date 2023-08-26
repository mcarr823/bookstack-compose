package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.Chapter
import dev.mcarr.common.data.interfaces.ChapterInterface
import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant

@Entity(tableName = ChapterEntity.TABLE_NAME)
data class ChapterEntity(
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
    val updated_at: String,
    @ColumnInfo(name = CREATED_BY)
    val created_by: Int,
    @ColumnInfo(name = UPDATED_BY)
    val updated_by: Int
) {

    constructor(json: ChapterInterface) : this(
        id = json.id,
        book_id = json.book_id,
        name = json.name,
        slug = json.slug,
        description = json.description,
        priority = json.priority,
        created_at = json.created_at.toString(),
        updated_at = json.updated_at.toString(),
        created_by = json.created_by,
        updated_by = json.updated_by
    )

    fun toDataClass() = Chapter(
        id = id,
        book_id = book_id,
        name = name,
        slug = slug,
        description = description,
        priority = priority,
        created_at = created_at.toInstant(),
        updated_at = updated_at.toInstant(),
        created_by = created_by,
        updated_by = updated_by
    )

    companion object{
        const val TABLE_NAME = "ChapterEntity"
        const val ID = "ce_id"
        const val BOOK_ID = "ce_book_id"
        const val NAME = "ce_name"
        const val SLUG = "ce_slug"
        const val DESCRIPTION = "ce_description"
        const val PRIORITY = "ce_priority"
        const val CREATED_AT = "ce_created_at"
        const val UPDATED_AT = "ce_updated_at"
        const val CREATED_BY = "ce_created_by"
        const val UPDATED_BY = "ce_updated_by"
    }

}
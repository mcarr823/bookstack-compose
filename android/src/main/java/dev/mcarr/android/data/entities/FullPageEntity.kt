package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FullPageEntity.TABLE_NAME)
class FullPageEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = SLUG)
    val slug: String,
    @ColumnInfo(name = BOOK_ID)
    val book_id: Int,
    @ColumnInfo(name = CHAPTER_ID)
    val chapter_id: Int,
    @ColumnInfo(name = DRAFT)
    val draft: Boolean,
    @ColumnInfo(name = TEMPLATE)
    val template: Boolean,
    @ColumnInfo(name = CREATED_AT)
    val created_at: String,
    @ColumnInfo(name = UPDATED_AT)
    val updated_at: String,
    @ColumnInfo(name = PRIORITY)
    val priority: Int,
    @ColumnInfo(name = REVISION_COUNT)
    val revision_count: Int,
    @ColumnInfo(name = HTML)
    val html: String,
    @ColumnInfo(name = MARKDOWN)
    val markdown: String
) {

    companion object{
        const val TABLE_NAME = "FullPageEntity"
        const val ID = "fpe_id"
        const val NAME = "fpe_name"
        const val SLUG = "fpe_slug"
        const val BOOK_ID = "fpe_book_id"
        const val CHAPTER_ID = "fpe_chapter_id"
        const val DRAFT = "fpe_draft"
        const val TEMPLATE = "fpe_template"
        const val CREATED_AT = "fpe_created_at"
        const val UPDATED_AT = "fpe_updated_at"
        const val PRIORITY = "fpe_priority"
        const val REVISION_COUNT = "fpe_revision_count"
        const val HTML = "fpe_html"
        const val MARKDOWN = "fpe_markdown"
    }

}
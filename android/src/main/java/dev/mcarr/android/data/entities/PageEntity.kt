package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.Page
import kotlinx.datetime.toInstant

@Entity(tableName = PageEntity.TABLE_NAME)
class PageEntity(
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
    @ColumnInfo(name = CREATED_BY)
    val created_by: Int,
    @ColumnInfo(name = UPDATED_BY)
    val updated_by: Int,
    @ColumnInfo(name = OWNED_BY)
    val owned_by: Int,
    @ColumnInfo(name = URL)
    val url: String
) {

    constructor(json: Page) : this(
        id = json.id,
        name = json.name,
        slug = json.slug,
        book_id = json.book_id,
        chapter_id = json.chapter_id,
        draft = json.draft,
        template = json.template,
        created_at = json.created_at.toString(),
        updated_at = json.updated_at.toString(),
        priority = json.priority,
        created_by = json.created_by,
        updated_by = json.updated_by,
        owned_by = json.owned_by,
        url = json.url
    )

    fun toDataClass() = Page(
        id = id,
        name = name,
        slug = slug,
        book_id = book_id,
        chapter_id = chapter_id,
        draft = draft,
        template = template,
        created_at = created_at.toInstant(),
        updated_at = updated_at.toInstant(),
        priority = priority,
        created_by = created_by,
        updated_by = updated_by,
        owned_by = owned_by,
        url = url
    )

    companion object{
        const val TABLE_NAME = "PageEntity"
        const val ID = "pe_id"
        const val NAME = "pe_name"
        const val SLUG = "pe_slug"
        const val BOOK_ID = "pe_book_id"
        const val CHAPTER_ID = "pe_chapter_id"
        const val DRAFT = "pe_draft"
        const val TEMPLATE = "pe_template"
        const val CREATED_AT = "pe_created_at"
        const val UPDATED_AT = "pe_updated_at"
        const val PRIORITY = "pe_priority"
        const val CREATED_BY = "pe_created_by"
        const val UPDATED_BY = "pe_updated_by"
        const val OWNED_BY = "pe_owned_by"
        const val URL = "pe_url"
    }

}
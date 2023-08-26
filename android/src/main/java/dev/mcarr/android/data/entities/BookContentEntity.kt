package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.BookContent
import kotlinx.datetime.toInstant

@Entity(tableName = BookContentEntity.TABLE_NAME)
class BookContentEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = SLUG)
    val slug: String,
    @ColumnInfo(name = BOOK_ID)
    val book_id: Int,
    @ColumnInfo(name = CREATED_AT)
    val created_at: String,
    @ColumnInfo(name = UPDATED_AT)
    val updated_at: String,
    @ColumnInfo(name = URL)
    val url: String,
    @ColumnInfo(name = TYPE)
    val type: String
) {

    constructor(json: BookContent) : this(
        id = json.id,
        name = json.name,
        slug = json.slug,
        book_id = json.book_id,
        created_at = json.created_at.toString(),
        updated_at = json.updated_at.toString(),
        url = json.url,
        type = json.type
    )

    fun toDataClass() = BookContent(
        id = id,
        name = name,
        slug = slug,
        book_id = book_id,
        created_at = created_at.toInstant(),
        updated_at = updated_at.toInstant(),
        url = url,
        type = type
    )

    companion object{
        const val TABLE_NAME = "BookContentEntity"
        const val ID = "bce_id"
        const val NAME = "bce_name"
        const val SLUG = "bce_slug"
        const val BOOK_ID = "bce_book_id"
        const val CREATED_AT = "bce_created_at"
        const val UPDATED_AT = "bce_updated_at"
        const val URL = "bce_url"
        const val TYPE = "bce_type"
    }
}
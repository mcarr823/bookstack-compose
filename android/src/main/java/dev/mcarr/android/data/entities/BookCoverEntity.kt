package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.BookCover
import kotlinx.datetime.toInstant

@Entity(tableName = BookCoverEntity.TABLE_NAME)
class BookCoverEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = BOOK_ID)
    val book_id: Int,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = URL)
    val url: String,
    @ColumnInfo(name = CREATED_AT)
    val created_at: String,
    @ColumnInfo(name = UPDATED_AT)
    val updated_at: String,
    @ColumnInfo(name = CREATED_BY)
    val created_by: Int,
    @ColumnInfo(name = UPDATED_BY)
    val updated_by: Int,
    @ColumnInfo(name = PATH)
    val path: String,
    @ColumnInfo(name = TYPE)
    val type: String,
    @ColumnInfo(name = UPLOADED_TO)
    val uploaded_to: Int,
) {

    constructor(json: BookCover, bookId: Int) : this(
        id = json.id,
        book_id = bookId,
        name = json.name,
        url = json.url,
        created_at = json.created_at.toString(),
        updated_at = json.updated_at.toString(),
        created_by = json.created_by,
        updated_by = json.updated_by,
        path = json.path,
        type = json.type,
        uploaded_to = json.uploaded_to
    )

    fun toDataClass() = BookCover(
        id = id,
        name = name,
        url = url,
        created_at = created_at.toInstant(),
        updated_at = updated_at.toInstant(),
        created_by = created_by,
        updated_by = updated_by,
        path = path,
        type = type,
        uploaded_to = uploaded_to
    )

    companion object{
        const val TABLE_NAME = "BookCoverEntity"
        const val ID = "bce_id"
        const val BOOK_ID = "bce_book_id"
        const val NAME = "bce_name"
        const val URL = "bce_url"
        const val CREATED_AT = "bce_created_at"
        const val UPDATED_AT = "bce_updated_at"
        const val CREATED_BY = "bce_created_by"
        const val UPDATED_BY = "bce_updated_by"
        const val PATH = "bce_path"
        const val TYPE = "bce_type"
        const val UPLOADED_TO = "bce_uploaded_to"
    }
}
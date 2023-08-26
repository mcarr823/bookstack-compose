package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.Author

@Entity(
    tableName = BookAuthorEntity.TABLE_NAME,
    primaryKeys = [ BookAuthorEntity.BOOK_ID, BookAuthorEntity.ROLE ]
)
class BookAuthorEntity(
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = BOOK_ID)
    val book_id: Int,
    @ColumnInfo(name = ROLE)
    val role: String,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = SLUG)
    val slug: String
) {

    constructor(json: Author, bookId: Int, role: String) : this(
        id = json.id,
        book_id = bookId,
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
        const val TABLE_NAME = "BookAuthorEntity"
        const val ID = "bae_id"
        const val BOOK_ID = "bae_book_id"
        const val ROLE = "bae_role"
        const val NAME = "bae_name"
        const val SLUG = "bae_slug"
    }
}
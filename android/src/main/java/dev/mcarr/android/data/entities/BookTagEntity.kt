package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.Tag

@Entity(tableName = BookTagEntity.TABLE_NAME)
class BookTagEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = BOOK_ID)
    val book_id: Int,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = VALUE)
    val value: String,
    @ColumnInfo(name = ORDER)
    val order: Int,
) {

    constructor(json: Tag, bookId: Int) : this(
        id = json.id,
        book_id = bookId,
        name = json.name,
        value = json.value,
        order = json.order
    )

    fun toDataClass() = Tag(
        id = id,
        name = name,
        value = value,
        order = order
    )

    companion object{
        const val TABLE_NAME = "BookTagEntity"
        const val ID = "bte_id"
        const val BOOK_ID = "bte_book_id"
        const val NAME = "bte_name"
        const val VALUE = "bte_value"
        const val ORDER = "bte_order"
    }
}
package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.Tag

@Entity(tableName = PageTagEntity.TABLE_NAME)
class PageTagEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = PAGE_ID)
    val page_id: Int,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = VALUE)
    val value: String,
    @ColumnInfo(name = ORDER)
    val order: Int,
) {

    constructor(json: Tag, pageId: Int) : this(
        id = json.id,
        page_id = pageId,
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
        const val TABLE_NAME = "PageTagEntity"
        const val ID = "pte_id"
        const val PAGE_ID = "pte_page_id"
        const val NAME = "pte_name"
        const val VALUE = "pte_value"
        const val ORDER = "pte_order"
    }
}
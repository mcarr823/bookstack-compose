package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.Tag

@Entity(tableName = ChapterTagEntity.TABLE_NAME)
class ChapterTagEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = CHAPTER_ID)
    val chapter_id: Int,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = VALUE)
    val value: String,
    @ColumnInfo(name = ORDER)
    val order: Int,
) {

    constructor(json: Tag, chapterId: Int) : this(
        id = json.id,
        chapter_id = chapterId,
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
        const val TABLE_NAME = "ChapterTagEntity"
        const val ID = "cte_id"
        const val CHAPTER_ID = "cte_chapter_id"
        const val NAME = "cte_name"
        const val VALUE = "cte_value"
        const val ORDER = "cte_order"
    }
}
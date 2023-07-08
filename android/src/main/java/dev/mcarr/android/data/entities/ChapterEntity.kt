package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import dev.mcarr.common.data.interfaces.ChapterInterface
import kotlinx.datetime.Instant

@Entity(tableName = ChapterEntity.TABLE_NAME)
data class ChapterEntity(
    @ColumnInfo(name = ID)
    override val id: Int,
    override val book_id: Int,
    override val name: String,
    override val slug: String,
    override val description: String,
    override val priority: Int,
    override val created_at: Instant,
    override val updated_at: Instant,
    override val created_by: Int,
    override val updated_by: Int
) : ChapterInterface {

    constructor(json: ChapterInterface) : this(
        id = json.id,
        book_id = json.book_id,
        name = json.name,
        slug = json.slug,
        description = json.description,
        priority = json.priority,
        created_at = json.created_at,
        updated_at = json.updated_at,
        created_by = json.created_by,
        updated_by = json.updated_by
    )

    companion object{
        const val TABLE_NAME = "ChapterEntity"
        const val ID = "id"
    }

}
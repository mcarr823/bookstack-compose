package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import dev.mcarr.common.data.interfaces.BookInterface
import kotlinx.datetime.Instant

@Entity(tableName = BookEntity.TABLE_NAME)
data class BookEntity(
    @ColumnInfo(name = ID)
    override val id: Int,
    override val name: String,
    override val slug: String,
    override val description: String,
    override val created_at: Instant,
    override val updated_at: Instant,
    override val created_by: Int,
    override val updated_by: Int,
    override val owned_by: Int
) : BookInterface {

    constructor(json: BookInterface) : this(
        id = json.id,
        name = json.name,
        slug = json.slug,
        description = json.description,
        created_at = json.created_at,
        updated_at = json.updated_at,
        created_by = json.created_by,
        updated_by = json.updated_by,
        owned_by = json.owned_by
    )

    companion object{
        const val TABLE_NAME = "BookEntity"
        const val ID = "id"
    }

}
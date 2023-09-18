package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.FullBook

@Entity(tableName = FullBookEntity.TABLE_NAME)
class FullBookEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = SLUG)
    val slug: String,
    @ColumnInfo(name = DESCRIPTION)
    val description: String,
    @ColumnInfo(name = CREATED_AT)
    val created_at: String,
    @ColumnInfo(name = UPDATED_AT)
    val updated_at: String
) {

    constructor(json: FullBook) : this(
        id = json.id,
        name = json.name,
        slug = json.slug,
        description = json.description,
        created_at = json.created_at.toString(),
        updated_at = json.updated_at.toString()
    )

    companion object{
        const val TABLE_NAME = "FullBookEntity"
        const val ID = "fbe_id"
        const val NAME = "fbe_name"
        const val SLUG = "fbe_slug"
        const val DESCRIPTION = "fbe_description"
        const val CREATED_AT = "fbe_created_at"
        const val UPDATED_AT = "fbe_updated_at"
    }

}
package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mcarr.common.data.classes.Book
import kotlinx.datetime.toInstant

@Entity(tableName = BookEntity.TABLE_NAME)
data class BookEntity(
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
    val updated_at: String,
    @ColumnInfo(name = CREATED_BY)
    val created_by: Int,
    @ColumnInfo(name = UPDATED_BY)
    val updated_by: Int,
    @ColumnInfo(name = OWNED_BY)
    val owned_by: Int
) {

    constructor(json: Book) : this(
        id = json.id,
        name = json.name,
        slug = json.slug,
        description = json.description,
        created_at = json.created_at.toString(),
        updated_at = json.updated_at.toString(),
        created_by = json.created_by,
        updated_by = json.updated_by,
        owned_by = json.owned_by
    )

    fun toDataClass() = Book(
        id = id,
        name = name,
        slug = slug,
        description = description,
        created_at = created_at.toInstant(),
        updated_at = updated_at.toInstant(),
        created_by = created_by,
        updated_by = updated_by,
        owned_by = owned_by
    )

    companion object{
        const val TABLE_NAME = "BookEntity"
        const val ID = "be_id"
        const val NAME = "be_name"
        const val SLUG = "be_slug"
        const val DESCRIPTION = "be_description"
        const val CREATED_AT = "be_created_at"
        const val UPDATED_AT = "be_updated_at"
        const val CREATED_BY = "be_created_by"
        const val UPDATED_BY = "be_updated_by"
        const val OWNED_BY = "be_owned_by"
    }

}
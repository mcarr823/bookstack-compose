package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import dev.mcarr.common.data.classes.Author

@Entity(
    tableName = PageAuthorEntity.TABLE_NAME,
    primaryKeys = [ PageAuthorEntity.PAGE_ID, PageAuthorEntity.ROLE ]
)
class PageAuthorEntity(
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = PAGE_ID)
    val page_id: Int,
    @ColumnInfo(name = ROLE)
    val role: String,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = SLUG)
    val slug: String
) {

    fun toDataClass() = Author(
        id = id,
        name = name,
        slug = slug
    )

    companion object{
        const val TABLE_NAME = "PageAuthorEntity"
        const val ID = "pae_id"
        const val PAGE_ID = "pae_page_id"
        const val ROLE = "pae_role"
        const val NAME = "pae_name"
        const val SLUG = "pae_slug"
    }

}
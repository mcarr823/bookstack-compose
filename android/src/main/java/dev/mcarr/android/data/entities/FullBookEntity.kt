package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import dev.mcarr.common.data.classes.Author
import dev.mcarr.common.data.classes.BookContent
import dev.mcarr.common.data.classes.BookCover
import dev.mcarr.common.data.classes.Tag
import dev.mcarr.common.data.interfaces.FullBookInterface
import kotlinx.datetime.Instant

@Entity(tableName = FullBookEntity.TABLE_NAME)
class FullBookEntity(
    @ColumnInfo(name = ID)
    override val id: Int,
    override val name: String,
    override val slug: String,
    override val description: String,
    override val created_at: Instant,
    override val updated_at: Instant,
    override val created_by: Author,
    override val updated_by: Author,
    override val owned_by: Author,
    override val contents: List<BookContent>,
    override val tags: List<Tag>,
    override val cover: BookCover?
) : FullBookInterface {


    constructor(json: FullBookInterface) : this(
        id = json.id,
        name = json.name,
        slug = json.slug,
        description = json.description,
        created_at = json.created_at,
        updated_at = json.updated_at,
        created_by = json.created_by,
        updated_by = json.updated_by,
        owned_by = json.owned_by,
        contents = json.contents,
        tags = json.tags,
        cover = json.cover
    )

    companion object{
        const val TABLE_NAME = "FullBookEntity"
        const val ID = "id"
    }

}
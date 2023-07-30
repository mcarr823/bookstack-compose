package dev.mcarr.android.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import dev.mcarr.common.data.classes.Author
import dev.mcarr.common.data.classes.Page
import dev.mcarr.common.data.classes.Tag
import dev.mcarr.common.data.interfaces.FullChapterInterface
import kotlinx.datetime.Instant

@Entity(tableName = FullChapterEntity.TABLE_NAME)
data class FullChapterEntity(
    @ColumnInfo(name = ID)
    override val id: Int,
    override val book_id: Int,
    override val name: String,
    override val slug: String,
    override val description: String,
    override val priority: Int,
    override val created_at: Instant,
    override val updated_at: Instant,
    override val created_by: Author,
    override val updated_by: Author,
    override val owned_by: Author,
    override val tags: List<Tag>,
    override val pages: List<Page>
) : FullChapterInterface {

    companion object{
        const val TABLE_NAME = "FullChapterEntity"
        const val ID = "id"
    }

}
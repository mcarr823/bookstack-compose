package dev.mcarr.android.data.entities.joins

import androidx.room.Embedded
import androidx.room.Relation
import dev.mcarr.android.data.entities.BookContentEntity
import dev.mcarr.android.data.entities.BookEntity
import dev.mcarr.android.data.entities.PageEntity
import dev.mcarr.common.data.classes.BookContent
import kotlinx.datetime.toInstant

class BookContentJoin(
    @Embedded
    val entity: BookContentEntity
) {

    @Relation(entity = PageEntity::class, parentColumn = BookContentEntity.BOOK_ID, entityColumn = PageEntity.BOOK_ID)
    var pages: List<PageEntity> = listOf()

    fun toDataClass() = BookContent(
        id = entity.id,
        name = entity.name,
        slug = entity.slug,
        book_id = entity.book_id,
        created_at = entity.created_at.toInstant(),
        updated_at = entity.updated_at.toInstant(),
        url = entity.url,
        type = entity.type,
        pages = pages.map { it.toDataClass() }
    )

    companion object{
        fun fromJson(json: BookContent) : BookContentJoin {
            val content = BookContentEntity(json)
            return BookContentJoin(content).apply {
                pages = json.pages.map { PageEntity(it) }
            }
        }
    }

}
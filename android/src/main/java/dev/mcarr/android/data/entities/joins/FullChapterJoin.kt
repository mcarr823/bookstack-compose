package dev.mcarr.android.data.entities.joins

import androidx.room.Embedded
import androidx.room.Relation
import dev.mcarr.android.data.entities.ChapterAuthorEntity
import dev.mcarr.android.data.entities.ChapterTagEntity
import dev.mcarr.android.data.entities.FullChapterEntity
import dev.mcarr.android.data.entities.PageEntity
import dev.mcarr.common.data.classes.AuthorRole
import dev.mcarr.common.data.classes.FullChapter
import dev.mcarr.common.data.interfaces.FullChapterInterface
import kotlinx.datetime.toInstant

class FullChapterJoin(
    @Embedded
    val entity: FullChapterEntity
) {

    @Relation(entity = ChapterAuthorEntity::class, parentColumn = FullChapterEntity.ID, entityColumn = ChapterAuthorEntity.CHAPTER_ID)
    var authors: List<ChapterAuthorEntity> = listOf()

    @Relation(entity = ChapterTagEntity::class, parentColumn = FullChapterEntity.ID, entityColumn = ChapterTagEntity.CHAPTER_ID)
    var tags: List<ChapterTagEntity> = listOf()

    @Relation(entity = PageEntity::class, parentColumn = FullChapterEntity.ID, entityColumn = PageEntity.CHAPTER_ID)
    var pages: List<PageEntity> = listOf()

    fun toDataClass() = FullChapter(
        id = entity.id,
        book_id = entity.book_id,
        priority = entity.priority,
        name = entity.name,
        slug = entity.slug,
        description = entity.description,
        created_at = entity.created_at.toInstant(),
        updated_at = entity.updated_at.toInstant(),
        created_by = authors.first { it.role == AuthorRole.CREATED.value }.toDataClass(),
        updated_by = authors.first { it.role == AuthorRole.UPDATED.value }.toDataClass(),
        owned_by = authors.first { it.role == AuthorRole.OWNED.value }.toDataClass(),
        tags = tags.map { it.toDataClass() },
        pages = pages.map { it.toDataClass() }
    )

    companion object{
        fun fromJson(json: FullChapterInterface) =
            FullChapterJoin(
                entity = FullChapterEntity(json)
            ).apply {
                authors = listOf(
                    ChapterAuthorEntity(json.created_by, json.id, AuthorRole.CREATED.value),
                    ChapterAuthorEntity(json.owned_by, json.id, AuthorRole.OWNED.value),
                    ChapterAuthorEntity(json.updated_by, json.id, AuthorRole.UPDATED.value)
                )
                tags = json.tags.map { ChapterTagEntity(it, json.id) }
                pages = json.pages.map { PageEntity(it) }
            }
    }
}
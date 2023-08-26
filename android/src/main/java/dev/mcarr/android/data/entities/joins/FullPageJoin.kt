package dev.mcarr.android.data.entities.joins

import androidx.room.Embedded
import androidx.room.Relation
import dev.mcarr.android.data.entities.FullPageEntity
import dev.mcarr.android.data.entities.PageAuthorEntity
import dev.mcarr.android.data.entities.PageTagEntity
import dev.mcarr.common.data.classes.AuthorRole
import dev.mcarr.common.data.classes.FullPage
import kotlinx.datetime.toInstant

class FullPageJoin(
    @Embedded
    val entity: FullPageEntity
) {

    @Relation(entity = PageAuthorEntity::class, parentColumn = FullPageEntity.ID, entityColumn = PageAuthorEntity.PAGE_ID)
    var authors: List<PageAuthorEntity> = listOf()

    @Relation(entity = PageTagEntity::class, parentColumn = FullPageEntity.ID, entityColumn = PageTagEntity.PAGE_ID)
    var tags: List<PageTagEntity> = listOf()

    fun toDataClass() = FullPage(
        id = entity.id,
        name = entity.name,
        slug = entity.slug,
        book_id = entity.book_id,
        chapter_id = entity.chapter_id,
        draft = entity.draft,
        template = entity.template,
        created_at = entity.created_at.toInstant(),
        updated_at = entity.updated_at.toInstant(),
        created_by = authors.first { it.role == AuthorRole.CREATED.value }.toDataClass(),
        updated_by = authors.first { it.role == AuthorRole.UPDATED.value }.toDataClass(),
        owned_by = authors.first { it.role == AuthorRole.OWNED.value }.toDataClass(),
        priority = entity.priority,
        revision_count = entity.revision_count,
        html = entity.html,
        markdown = entity.markdown,
        tags = tags.map { it.toDataClass() },
    )

}
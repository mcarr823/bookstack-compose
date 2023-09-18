package dev.mcarr.android.data.entities.joins

import androidx.room.Embedded
import androidx.room.Relation
import dev.mcarr.android.data.entities.BookAuthorEntity
import dev.mcarr.android.data.entities.BookContentEntity
import dev.mcarr.android.data.entities.BookCoverEntity
import dev.mcarr.android.data.entities.BookTagEntity
import dev.mcarr.android.data.entities.FullBookEntity
import dev.mcarr.common.data.classes.AuthorRole
import dev.mcarr.common.data.classes.FullBook
import kotlinx.datetime.toInstant

class FullBookJoin(
    @Embedded
    val entity: FullBookEntity
) {
    @Relation(entity = BookAuthorEntity::class, parentColumn = FullBookEntity.ID, entityColumn = BookAuthorEntity.BOOK_ID)
    var authors: List<BookAuthorEntity> = listOf()
    @Relation(entity = BookContentEntity::class, parentColumn = FullBookEntity.ID, entityColumn = BookContentEntity.BOOK_ID)
    var contents: List<BookContentJoin> = listOf()
    @Relation(entity = BookTagEntity::class, parentColumn = FullBookEntity.ID, entityColumn = BookTagEntity.BOOK_ID)
    var tags: List<BookTagEntity> = listOf()
    @Relation(entity = BookCoverEntity::class, parentColumn = FullBookEntity.ID, entityColumn = BookCoverEntity.BOOK_ID)
    var covers: List<BookCoverEntity> = listOf()

    fun toDataClass() = FullBook(
        id = entity.id,
        name = entity.name,
        slug = entity.slug,
        description = entity.description,
        created_at = entity.created_at.toInstant(),
        updated_at = entity.updated_at.toInstant(),
        created_by = authors.first { it.role == AuthorRole.CREATED.value }.toDataClass(),
        updated_by = authors.first { it.role == AuthorRole.UPDATED.value }.toDataClass(),
        owned_by = authors.first { it.role == AuthorRole.OWNED.value }.toDataClass(),
        contents = contents.map { it.toDataClass() },
        tags = tags.map { it.toDataClass() },
        cover = covers.map { it.toDataClass() }.firstOrNull()
    )

    companion object{
        fun fromJson(json: FullBook) =
            FullBookJoin(
                entity = FullBookEntity(json)
            ).apply {
                authors = listOf(
                    BookAuthorEntity(json.created_by, json.id, AuthorRole.CREATED.value),
                    BookAuthorEntity(json.owned_by, json.id, AuthorRole.OWNED.value),
                    BookAuthorEntity(json.updated_by, json.id, AuthorRole.UPDATED.value)
                )
                contents = json.contents.map { BookContentJoin.fromJson(it) }
                tags = json.tags.map { BookTagEntity(it, json.id) }
                covers = listOfNotNull(json.cover?.let { BookCoverEntity(it, json.id) })
            }
    }
}
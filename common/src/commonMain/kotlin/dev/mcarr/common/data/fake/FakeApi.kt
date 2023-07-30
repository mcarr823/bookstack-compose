package dev.mcarr.common.data.fake

import dev.mcarr.common.data.classes.Attachments
import dev.mcarr.common.data.classes.Author
import dev.mcarr.common.data.classes.Book
import dev.mcarr.common.data.classes.Books
import dev.mcarr.common.data.classes.Chapter
import dev.mcarr.common.data.classes.Chapters
import dev.mcarr.common.data.classes.CreateBookRequest
import dev.mcarr.common.data.classes.CreateBookResponse
import dev.mcarr.common.data.classes.CreateChapterRequest
import dev.mcarr.common.data.classes.CreateChapterResponse
import dev.mcarr.common.data.classes.CreatePageRequest
import dev.mcarr.common.data.classes.CreatePageResponse
import dev.mcarr.common.data.classes.ExportFormat
import dev.mcarr.common.data.classes.FullAttachment
import dev.mcarr.common.data.classes.FullBook
import dev.mcarr.common.data.classes.FullChapter
import dev.mcarr.common.data.classes.FullPage
import dev.mcarr.common.data.classes.Pages
import dev.mcarr.common.network.ApiInterface
import kotlinx.datetime.Clock

class FakeApi : ApiInterface {


    /* Docs */

    override suspend fun getDocsHtml(): String = throw NotImplementedError()

    override suspend fun getDocsJson(): String = throw NotImplementedError()



    /* Attachments */

    override suspend fun getAttachments(): Attachments = throw NotImplementedError()

    //TODO: create attachment

    override suspend fun getAttachment(id: Int): FullAttachment = throw NotImplementedError()

    //TODO: update attachment

    override suspend fun deleteAttachment(id: Int): Boolean = true



    /* Books */

    override suspend fun getBooks(): Books {
        val books = (1 until 20).map { i ->
            Book(
                id = i,
                name = "Book $i",
                slug = "",
                description = "This is an example book description #$i",
                created_at = Clock.System.now(),
                created_by = 0,
                updated_at = Clock.System.now(),
                updated_by = 0,
                owned_by = 0
            )
        }
        return Books(
            data = books,
            total = books.size
        )
    }

    override suspend fun createBook(book: CreateBookRequest): CreateBookResponse = throw NotImplementedError()

    override suspend fun getBook(id: Int): FullBook {

        val author = Author(
            id = 0,
            name = "Mr Author",
            slug = "mr_author"
        )
        return FullBook(
            id = id,
            name = "Book Title",
            slug = "",
            description = "This is an example book description",
            created_at = Clock.System.now(),
            created_by = author,
            updated_at = Clock.System.now(),
            updated_by = author,
            owned_by = author,
            contents = listOf(),
            cover = null,
            tags = listOf()
        )

    }

    override suspend fun updateBook(id: Int, book: CreateBookRequest): CreateBookResponse = throw NotImplementedError()

    override suspend fun deleteBook(id: Int): Boolean = true

    override suspend fun exportBook(id: Int, format: ExportFormat): String = throw NotImplementedError()



    /* Chapters */

    override suspend fun getChapters(): Chapters {
        val chapters = (1 until 20).map { i ->
            Chapter(
                id = i,
                book_id = 0,
                name = "Chapter $i",
                slug = "",
                description = "This is an example chapter description #$i",
                priority = 0,
                created_at = Clock.System.now(),
                created_by = 0,
                updated_at = Clock.System.now(),
                updated_by = 0
            )
        }
        return Chapters(
            data = chapters,
            total = chapters.size
        )
    }

    override suspend fun createChapter(chapter: CreateChapterRequest): CreateChapterResponse = throw NotImplementedError()

    override suspend fun getChapter(id: Int): FullChapter {
        val author = Author(
            id = 0,
            name = "Mr Author",
            slug = "mr_author"
        )
        return FullChapter(
            id = id,
            book_id = 0,
            name = "Book Title",
            slug = "",
            description = "This is an example book description",
            priority = 0,
            created_at = Clock.System.now(),
            created_by = author,
            updated_at = Clock.System.now(),
            updated_by = author,
            owned_by = author,
            tags = listOf(),
            pages = listOf()
        )
    }

    override suspend fun updateChapter(id: Int, chapter: CreateChapterRequest): CreateChapterResponse = throw NotImplementedError()

    override suspend fun deleteChapter(id: Int): Boolean = true

    override suspend fun exportChapter(id: Int, format: ExportFormat): String = throw NotImplementedError()



    /* Pages */

    override suspend fun getPages(): Pages = throw NotImplementedError()

    override suspend fun createPage(page: CreatePageRequest): CreatePageResponse = throw NotImplementedError()

    override suspend fun getPage(id: Int): FullPage = throw NotImplementedError()

    override suspend fun updatePage(id: Int, page: CreatePageRequest): CreatePageResponse = throw NotImplementedError()

    override suspend fun deletePage(id: Int): Boolean = true

    override suspend fun exportPage(id: Int, format: ExportFormat): String = throw NotImplementedError()

}
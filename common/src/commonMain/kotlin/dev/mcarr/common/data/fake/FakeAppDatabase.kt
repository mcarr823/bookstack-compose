package dev.mcarr.common.data.fake

import dev.mcarr.common.data.Attachments
import dev.mcarr.common.data.Author
import dev.mcarr.common.data.Book
import dev.mcarr.common.data.Books
import dev.mcarr.common.data.Chapters
import dev.mcarr.common.data.FullAttachment
import dev.mcarr.common.data.FullBook
import dev.mcarr.common.data.FullPage
import dev.mcarr.common.data.Page
import dev.mcarr.common.data.Pages
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.interfaces.BookInterface
import dev.mcarr.common.data.interfaces.ChapterInterface
import dev.mcarr.common.data.interfaces.FullBookInterface
import dev.mcarr.common.data.interfaces.FullChapterInterface
import dev.mcarr.common.data.interfaces.FullPageInterface
import kotlinx.datetime.Clock

class FakeAppDatabase : AppDatabaseInterface {

    override suspend fun getDocsHtml(): String = ""
    override suspend fun setDocsHtml(data: String) = Unit
    override suspend fun getDocsJson(): String = ""
    override suspend fun setDocsJson(data: String) = Unit
    override suspend fun getAttachments(): Attachments = Attachments(listOf(), 0)
    override suspend fun setAttachments(data: Attachments) = Unit
    override suspend fun getAttachment(id: Int): FullAttachment? = null
    override suspend fun setAttachment(data: FullAttachment) = Unit
    override suspend fun deleteAttachment(id: Int) = Unit

    override suspend fun getBooks(): List<BookInterface> =
        (1 until 20).map { i ->
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
    override suspend fun setBooks(data: Books) = Unit
    override suspend fun getBook(id: Int) =
        Book(
            id = id,
            name = "Book Title",
            slug = "",
            description = "This is an example book description",
            created_at = Clock.System.now(),
            created_by = 0,
            updated_at = Clock.System.now(),
            updated_by = 0,
            owned_by = 0
        )
    override suspend fun setBook(data: BookInterface) = Unit
    override suspend fun deleteBook(id: Int) = Unit
    override suspend fun deleteBooks() = Unit

    override suspend fun getBookFull(id: Int): FullBookInterface {
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
    override suspend fun setBookFull(data: FullBookInterface) = Unit
    override suspend fun deleteBookFull(id: Int) = Unit

    override suspend fun getChapters(): List<ChapterInterface> = listOf()
    override suspend fun setChapters(data: Chapters) = Unit
    override suspend fun getChapter(id: Int): ChapterInterface? = null
    override suspend fun setChapter(data: ChapterInterface) = Unit
    override suspend fun deleteChapter(id: Int) = Unit

    override suspend fun getFullChapter(id: Int): FullChapterInterface? = null
    override suspend fun getFullChaptersByBookId(bookId: Int): List<FullChapterInterface> = listOf()
    override suspend fun setFullChapter(data: FullChapterInterface) = Unit
    override suspend fun deleteFullChapter(id: Int) = Unit

    override suspend fun getPages(): List<Page> = listOf()
    override suspend fun setPages(data: Pages) = Unit
    override suspend fun getPage(id: Int): Page? = null
    override suspend fun setPage(data: Page) = Unit
    override suspend fun deletePage(id: Int) = Unit

    override suspend fun getFullPage(id: Int): FullPage? = null
    override suspend fun getFullPagesByBookId(bookId: Int): List<FullPageInterface> = listOf()
    override suspend fun setFullPage(data: FullPage) = Unit
    override suspend fun deleteFullPage(id: Int) = Unit

}
package dev.mcarr.common.data

import dev.mcarr.common.data.classes.Attachments
import dev.mcarr.common.data.classes.Book
import dev.mcarr.common.data.classes.Books
import dev.mcarr.common.data.classes.Chapter
import dev.mcarr.common.data.classes.Chapters
import dev.mcarr.common.data.classes.FullAttachment
import dev.mcarr.common.data.classes.FullBook
import dev.mcarr.common.data.classes.FullChapter
import dev.mcarr.common.data.classes.FullPage
import dev.mcarr.common.data.classes.Page
import dev.mcarr.common.data.classes.Pages
import dev.mcarr.common.data.interfaces.AppDatabaseInterface

/**
 * Desktop version of AppDatabase.
 * Stores data in RAM instead of using an actual database, since desktop computers
 * have more RAM and store data differently than mobile devices.
 **/
class AppDatabase : AppDatabaseInterface {

    /* Docs */

    private var docsHtml = ""
    override suspend fun getDocsHtml(): String = docsHtml
    override suspend fun setDocsHtml(data: String) { docsHtml = data }

    private var docsJson = ""
    override suspend fun getDocsJson(): String = docsJson
    override suspend fun setDocsJson(data: String) { docsJson = data }



    /* Attachments */

    private var attachments = Attachments(data = listOf(), total = 0)
    override suspend fun getAttachments(): Attachments = attachments
    override suspend fun setAttachments(data: Attachments) { attachments = data }

    private val fullAttachments = HashMap<Int, FullAttachment>()
    override suspend fun getAttachment(id: Int): FullAttachment? = fullAttachments[id]
    override suspend fun setAttachment(data: FullAttachment) = fullAttachments.set(data.id, data)
    override suspend fun deleteAttachment(id: Int) { fullAttachments.remove(id) }



    /* Bpoks */

    private val books = HashMap<Int, Book>()
    override suspend fun getBooks() = books.entries.map { it.value }
    override suspend fun setBooks(data: Books) {
        books.clear()
        data.data.forEach { books[it.id] = it }
    }
    override suspend fun getBook(id: Int) = books[id]
    override suspend fun setBook(data: Book){ books[data.id] = data }
    override suspend fun deleteBook(id: Int) { books.remove(id) }
    override suspend fun deleteBooks() { books.clear() }

    private val fullBooks = HashMap<Int, FullBook>()
    override suspend fun getBookFull(id: Int) = fullBooks[id]
    override suspend fun setBookFull(data: FullBook) = fullBooks.set(data.id, data)
    override suspend fun deleteBookFull(id: Int) { fullBooks.remove(id) }



    /* Chapters */

    private var chapters = HashMap<Int, Chapter>()
    override suspend fun getChapters() = chapters.map { it.value }
    override suspend fun setChapters(data: Chapters) {
        chapters.clear()
        data.data.forEach { chapters[it.id] = it }
    }
    override suspend fun getChapter(id: Int) = chapters[id]
    override suspend fun setChapter(data: Chapter) = chapters.set(data.id, data)
    override suspend fun deleteChapter(id: Int) { chapters.remove(id) }

    private val fullChapters = HashMap<Int, FullChapter>()
    override suspend fun getFullChapter(id: Int) = fullChapters[id]
    override suspend fun getFullChaptersByBookId(bookId: Int) = fullChapters.map { it.value }.filter { it.book_id == bookId }
    override suspend fun setFullChapter(data: FullChapter) = fullChapters.set(data.id, data)
    override suspend fun deleteFullChapter(id: Int) { fullChapters.remove(id) }



    /* Pages */

    private var pages = HashMap<Int, Page>()
    override suspend fun getPages(): List<Page> = pages.entries.map { it.value }
    override suspend fun setPages(data: Pages) {
        pages.clear()
        data.data.forEach { pages[it.id] = it }
    }
    override suspend fun getPage(id: Int): Page? = pages[id]
    override suspend fun setPage(data: Page) = pages.set(data.id, data)
    override suspend fun deletePage(id: Int) { pages.remove(id) }

    private var fullPages = HashMap<Int, FullPage>()
    override suspend fun getFullPage(id: Int): FullPage? = fullPages[id]
    override suspend fun getFullPagesByBookId(bookId: Int) = fullPages.map { it.value }.filter { it.book_id == bookId }
    override suspend fun setFullPage(data: FullPage) = fullPages.set(data.id, data)
    override suspend fun deleteFullPage(id: Int) { fullPages.remove(id) }

}
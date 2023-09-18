package dev.mcarr.common.data.interfaces

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

interface AppDatabaseInterface {

    /* Docs */

    suspend fun getDocsHtml(): String

    suspend fun setDocsHtml(data: String)

    suspend fun getDocsJson(): String

    suspend fun setDocsJson(data: String)



    /* Attachments */

    suspend fun getAttachments(): Attachments

    suspend fun setAttachments(data: Attachments)

    //TODO: create attachment

    suspend fun getAttachment(id: Int): FullAttachment?

    suspend fun setAttachment(data: FullAttachment)

    //TODO: update attachment

    suspend fun deleteAttachment(id: Int)



    /* Books */

    suspend fun getBooks(): List<Book>
    suspend fun setBooks(data: Books)
    suspend fun getBook(id: Int): Book?
    suspend fun setBook(data: Book)
    suspend fun deleteBook(id: Int)
    suspend fun deleteBooks()

    suspend fun getBookFull(id: Int): FullBook?
    suspend fun setBookFull(data: FullBook)
    suspend fun deleteBookFull(id: Int)



    /* Chapters */

    suspend fun getChapters(): List<Chapter>
    suspend fun getFullChaptersByBookId(bookId: Int): List<FullChapter>
    suspend fun setChapters(data: Chapters)
    suspend fun getChapter(id: Int): Chapter?
    suspend fun setChapter(data: Chapter)
    suspend fun deleteChapter(id: Int)

    suspend fun getFullChapter(id: Int): FullChapter?
    suspend fun setFullChapter(data: FullChapter)
    suspend fun deleteFullChapter(id: Int)



    /* Pages */

    suspend fun getPages(): List<Page>
    suspend fun setPages(data: Pages)
    suspend fun getPage(id: Int): Page?
    suspend fun setPage(data: Page)
    suspend fun deletePage(id: Int)

    suspend fun getFullPage(id: Int): FullPage?
    suspend fun getFullPagesByBookId(bookId: Int): List<FullPage>
    suspend fun setFullPage(data: FullPage)
    suspend fun deleteFullPage(id: Int)

}
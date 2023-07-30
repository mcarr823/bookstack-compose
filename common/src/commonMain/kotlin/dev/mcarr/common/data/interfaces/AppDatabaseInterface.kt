package dev.mcarr.common.data.interfaces

import dev.mcarr.common.data.classes.Attachments
import dev.mcarr.common.data.classes.Books
import dev.mcarr.common.data.classes.Chapters
import dev.mcarr.common.data.classes.FullAttachment
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

    suspend fun getBooks(): List<BookInterface>
    suspend fun setBooks(data: Books)
    suspend fun getBook(id: Int): BookInterface?
    suspend fun setBook(data: BookInterface)
    suspend fun deleteBook(id: Int)
    suspend fun deleteBooks()

    suspend fun getBookFull(id: Int): FullBookInterface?
    suspend fun setBookFull(data: FullBookInterface)
    suspend fun deleteBookFull(id: Int)



    /* Chapters */

    suspend fun getChapters(): List<ChapterInterface>
    suspend fun getFullChaptersByBookId(bookId: Int): List<FullChapterInterface>
    suspend fun setChapters(data: Chapters)
    suspend fun getChapter(id: Int): ChapterInterface?
    suspend fun setChapter(data: ChapterInterface)
    suspend fun deleteChapter(id: Int)

    suspend fun getFullChapter(id: Int): FullChapterInterface?
    suspend fun setFullChapter(data: FullChapterInterface)
    suspend fun deleteFullChapter(id: Int)



    /* Pages */

    suspend fun getPages(): List<Page>
    suspend fun setPages(data: Pages)
    suspend fun getPage(id: Int): Page?
    suspend fun setPage(data: Page)
    suspend fun deletePage(id: Int)

    suspend fun getFullPage(id: Int): FullPage?
    suspend fun getFullPagesByBookId(bookId: Int): List<FullPageInterface>
    suspend fun setFullPage(data: FullPage)
    suspend fun deleteFullPage(id: Int)

}
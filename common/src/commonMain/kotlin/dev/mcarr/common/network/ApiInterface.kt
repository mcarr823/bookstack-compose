package dev.mcarr.common.network

import dev.mcarr.common.data.classes.Attachments
import dev.mcarr.common.data.classes.Books
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

interface ApiInterface {

    /* Docs */

    @Throws(Exception::class)
    suspend fun getDocsHtml(): String

    @Throws(Exception::class)
    suspend fun getDocsJson(): String



    /* Attachments */

    @Throws(Exception::class)
    suspend fun getAttachments(): Attachments

    //TODO: create attachment

    @Throws(Exception::class)
    suspend fun getAttachment(id: Int): FullAttachment

    //TODO: update attachment

    suspend fun deleteAttachment(id: Int): Boolean



    /* Books */

    @Throws(Exception::class)
    suspend fun getBooks(): Books

    @Throws(Exception::class)
    suspend fun createBook(book: CreateBookRequest): CreateBookResponse

    @Throws(Exception::class)
    suspend fun getBook(id: Int): FullBook

    @Throws(Exception::class)
    suspend fun updateBook(id: Int, book: CreateBookRequest): CreateBookResponse

    suspend fun deleteBook(id: Int): Boolean

    @Throws(Exception::class)
    suspend fun exportBook(id: Int, format: ExportFormat): String



    /* Chapters */

    @Throws(Exception::class)
    suspend fun getChapters(): Chapters

    @Throws(Exception::class)
    suspend fun createChapter(chapter: CreateChapterRequest): CreateChapterResponse

    @Throws(Exception::class)
    suspend fun getChapter(id: Int): FullChapter

    @Throws(Exception::class)
    suspend fun updateChapter(id: Int, chapter: CreateChapterRequest): CreateChapterResponse

    suspend fun deleteChapter(id: Int): Boolean

    @Throws(Exception::class)
    suspend fun exportChapter(id: Int, format: ExportFormat): String



    /* Pages */

    @Throws(Exception::class)
    suspend fun getPages(): Pages

    @Throws(Exception::class)
    suspend fun createPage(page: CreatePageRequest): CreatePageResponse

    @Throws(Exception::class)
    suspend fun getPage(id: Int): FullPage

    @Throws(Exception::class)
    suspend fun updatePage(id: Int, page: CreatePageRequest): CreatePageResponse

    suspend fun deletePage(id: Int): Boolean

    @Throws(Exception::class)
    suspend fun exportPage(id: Int, format: ExportFormat): String

}
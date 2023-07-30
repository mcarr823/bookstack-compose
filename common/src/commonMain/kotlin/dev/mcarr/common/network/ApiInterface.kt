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

    suspend fun getDocsHtml(): String

    suspend fun getDocsJson(): String



    /* Attachments */

    suspend fun getAttachments(): Attachments

    //TODO: create attachment

    suspend fun getAttachment(id: Int): FullAttachment

    //TODO: update attachment

    suspend fun deleteAttachment(id: Int): Boolean



    /* Books */

    suspend fun getBooks(): Books

    suspend fun createBook(book: CreateBookRequest): CreateBookResponse

    suspend fun getBook(id: Int): FullBook

    suspend fun updateBook(id: Int, book: CreateBookRequest): CreateBookResponse

    suspend fun deleteBook(id: Int): Boolean

    suspend fun exportBook(id: Int, format: ExportFormat): String



    /* Chapters */

    suspend fun getChapters(): Chapters

    suspend fun createChapter(chapter: CreateChapterRequest): CreateChapterResponse

    suspend fun getChapter(id: Int): FullChapter

    suspend fun updateChapter(id: Int, chapter: CreateChapterRequest): CreateChapterResponse

    suspend fun deleteChapter(id: Int): Boolean

    suspend fun exportChapter(id: Int, format: ExportFormat): String



    /* Pages */

    suspend fun getPages(): Pages

    suspend fun createPage(page: CreatePageRequest): CreatePageResponse

    suspend fun getPage(id: Int): FullPage

    suspend fun updatePage(id: Int, page: CreatePageRequest): CreatePageResponse

    suspend fun deletePage(id: Int): Boolean

    suspend fun exportPage(id: Int, format: ExportFormat): String

}
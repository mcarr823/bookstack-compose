package dev.mcarr.android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.mcarr.android.data.daos.BookDao
import dev.mcarr.android.data.daos.ChapterDao
import dev.mcarr.android.data.daos.FullBookDao
import dev.mcarr.android.data.daos.FullChapterDao
import dev.mcarr.android.data.daos.FullPageDao
import dev.mcarr.android.data.daos.PageDao
import dev.mcarr.android.data.entities.BookAuthorEntity
import dev.mcarr.android.data.entities.BookContentEntity
import dev.mcarr.android.data.entities.BookCoverEntity
import dev.mcarr.android.data.entities.BookEntity
import dev.mcarr.android.data.entities.BookTagEntity
import dev.mcarr.android.data.entities.ChapterAuthorEntity
import dev.mcarr.android.data.entities.ChapterEntity
import dev.mcarr.android.data.entities.ChapterTagEntity
import dev.mcarr.android.data.entities.FullBookEntity
import dev.mcarr.android.data.entities.FullChapterEntity
import dev.mcarr.android.data.entities.FullPageEntity
import dev.mcarr.android.data.entities.PageAuthorEntity
import dev.mcarr.android.data.entities.PageEntity
import dev.mcarr.android.data.entities.PageTagEntity
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
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

@Database(entities = [
    BookEntity::class, FullBookEntity::class,
    BookAuthorEntity::class, BookContentEntity::class,
    BookCoverEntity::class, BookTagEntity::class,
    ChapterEntity::class, FullChapterEntity::class,
    ChapterAuthorEntity::class, ChapterTagEntity::class,
    PageEntity::class, FullPageEntity::class,
    PageAuthorEntity::class, PageTagEntity::class

], version = AppDatabase.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase(), AppDatabaseInterface {

    abstract fun bookDao(): BookDao
    abstract fun fullBookDao(): FullBookDao
    abstract fun chapterDao(): ChapterDao
    abstract fun fullChapterDao(): FullChapterDao
    abstract fun pageDao(): PageDao
    abstract fun fullPageDao(): FullPageDao

    companion object{

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "bookstack-compose-android"

        var database: AppDatabase? = null

        fun getDatabase(c: Context): AppDatabase {
            return database ?: synchronized(this){
                Room.databaseBuilder(
                    c.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build().also {
                    database = it
                }

            }
        }

    }

    override suspend fun getDocsHtml(): String {
        TODO("Not yet implemented")
    }

    override suspend fun setDocsHtml(data: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getDocsJson(): String {
        TODO("Not yet implemented")
    }

    override suspend fun setDocsJson(data: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAttachments(): Attachments {
        TODO("Not yet implemented")
    }

    override suspend fun setAttachments(data: Attachments) {
        TODO("Not yet implemented")
    }

    override suspend fun getAttachment(id: Int): FullAttachment? {
        TODO("Not yet implemented")
    }

    override suspend fun setAttachment(data: FullAttachment) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAttachment(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getBooks(): List<Book> = bookDao().getBooks().map { it.toDataClass() }
    override suspend fun setBooks(data: Books) {
        val dao = bookDao()
        dao.delete()
        val list = data.data.map { BookEntity(it) }
        dao.insertBooks(list)
    }
    override suspend fun getBook(id: Int): Book? = bookDao().getBook(id)?.toDataClass()
    override suspend fun setBook(data: Book) = BookEntity(data).let(bookDao()::update)
    override suspend fun deleteBook(id: Int) = bookDao().delete(id)
    override suspend fun deleteBooks() = bookDao().delete()

    override suspend fun getBookFull(id: Int): FullBook? = fullBookDao().getFullBook(id)?.toDataClass()
    override suspend fun setBookFull(data: FullBook) = FullBookEntity(data).let(fullBookDao()::update)
    override suspend fun deleteBookFull(id: Int) = fullBookDao().delete(id)

    override suspend fun getChapters(): List<Chapter> = chapterDao().getChapters().map { it.toDataClass() }
    override suspend fun setChapters(data: Chapters) {
        val dao = chapterDao()
        dao.delete()
        val list = data.data.map { ChapterEntity(it) }
        dao.insertChapters(list)
    }
    override suspend fun getChapter(id: Int): Chapter? = chapterDao().getChapter(id)?.toDataClass()
    override suspend fun setChapter(data: Chapter) = ChapterEntity(data).let(chapterDao()::update)
    override suspend fun deleteChapter(id: Int) = chapterDao().delete(id)

    override suspend fun getFullChapter(id: Int): FullChapter? = fullChapterDao().getFullChapter(id)?.toDataClass()
    override suspend fun getFullChaptersByBookId(bookId: Int): List<FullChapter> = fullChapterDao().getFullChaptersByBookId(bookId).map { it.toDataClass() }
    override suspend fun setFullChapter(data: FullChapter) = FullChapterEntity(data).let(fullChapterDao()::update)
    override suspend fun deleteFullChapter(id: Int) = fullChapterDao().delete(id)

    override suspend fun getPages(): List<Page> {
        TODO("Not yet implemented")
    }

    override suspend fun setPages(data: Pages) {
        TODO("Not yet implemented")
    }

    override suspend fun getPage(id: Int): Page? {
        TODO("Not yet implemented")
    }

    override suspend fun setPage(data: Page) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePage(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getFullPage(id: Int): FullPage? {
        TODO("Not yet implemented")
    }

    override suspend fun getFullPagesByBookId(bookId: Int): List<FullPage> =
        fullPageDao().getFullPagesByBookId(bookId).map { it.toDataClass() }

    override suspend fun setFullPage(data: FullPage) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFullPage(id: Int) {
        TODO("Not yet implemented")
    }
}
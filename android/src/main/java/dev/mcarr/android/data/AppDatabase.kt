package dev.mcarr.android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.mcarr.android.data.daos.BookDao
import dev.mcarr.android.data.daos.ChapterDao
import dev.mcarr.android.data.daos.FullBookDao
import dev.mcarr.android.data.daos.FullChapterDao
import dev.mcarr.android.data.entities.BookEntity
import dev.mcarr.android.data.entities.ChapterEntity
import dev.mcarr.android.data.entities.FullBookEntity
import dev.mcarr.android.data.entities.FullChapterEntity
import dev.mcarr.common.data.interfaces.AppDatabaseInterface
import dev.mcarr.common.data.classes.Attachments
import dev.mcarr.common.data.classes.Books
import dev.mcarr.common.data.classes.Chapters
import dev.mcarr.common.data.classes.FullAttachment
import dev.mcarr.common.data.classes.FullPage
import dev.mcarr.common.data.classes.Page
import dev.mcarr.common.data.classes.Pages
import dev.mcarr.common.data.interfaces.BookInterface
import dev.mcarr.common.data.interfaces.ChapterInterface
import dev.mcarr.common.data.interfaces.FullBookInterface
import dev.mcarr.common.data.interfaces.FullChapterInterface

@Database(entities = [
    BookEntity::class, FullBookEntity::class,
    ChapterEntity::class, FullChapterEntity::class
], version = AppDatabase.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase(), AppDatabaseInterface {

    abstract fun bookDao(): BookDao
    abstract fun fullBookDao(): FullBookDao
    abstract fun chapterDao(): ChapterDao
    abstract fun fullChapterDao(): FullChapterDao

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

    override suspend fun getBooks() = bookDao().getBooks()
    override suspend fun setBooks(data: Books) {
        val dao = bookDao()
        dao.delete()
        val list = data.data.map { BookEntity(it) }
        dao.insertBooks(list)
    }
    override suspend fun getBook(id: Int) = bookDao().getBook(id)
    override suspend fun setBook(data: BookInterface) = bookDao().update(data as BookEntity)
    override suspend fun deleteBook(id: Int) = bookDao().delete(id)

    override suspend fun getBookFull(id: Int) = fullBookDao().getFullBook(id)
    override suspend fun setBookFull(data: FullBookInterface) = fullBookDao().update(data as FullBookEntity)
    override suspend fun deleteBookFull(id: Int) = fullBookDao().delete(id)

    override suspend fun getChapters() = chapterDao().getChapters()
    override suspend fun setChapters(data: Chapters) {
        val dao = chapterDao()
        dao.delete()
        val list = data.data.map { ChapterEntity(it) }
        dao.insertChapters(list)
    }
    override suspend fun getChapter(id: Int) = chapterDao().getChapter(id)
    override suspend fun setChapter(data: ChapterInterface) = chapterDao().update(data as ChapterEntity)
    override suspend fun deleteChapter(id: Int) = chapterDao().delete(id)

    override suspend fun getFullChapter(id: Int) = fullChapterDao().getFullChapter(id)
    override suspend fun setFullChapter(data: FullChapterInterface) = fullChapterDao().update(data as FullChapterEntity)
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

    override suspend fun setFullPage(data: FullPage) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFullPage(id: Int) {
        TODO("Not yet implemented")
    }
}
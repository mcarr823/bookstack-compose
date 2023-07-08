package dev.mcarr.android.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.mcarr.android.data.entities.BookEntity

@Dao
interface BookDao {

    @Query("SELECT * FROM `${BookEntity.TABLE_NAME}`")
    fun getBooks(): List<BookEntity>

    @Query("SELECT * FROM `${BookEntity.TABLE_NAME}` WHERE `${BookEntity.ID}` = :id")
    fun getBook(id: Int): BookEntity?

    @Insert
    fun insertBooks(books: List<BookEntity>)

    @Update
    fun update(book: BookEntity)

    @Query("DELETE FROM `${BookEntity.TABLE_NAME}`")
    fun delete()

    @Query("DELETE FROM `${BookEntity.TABLE_NAME}` WHERE `${BookEntity.ID}` = :id")
    fun delete(id: Int)

}
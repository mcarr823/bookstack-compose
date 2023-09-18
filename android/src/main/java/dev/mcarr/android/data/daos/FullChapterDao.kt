package dev.mcarr.android.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import dev.mcarr.android.data.entities.FullChapterEntity
import dev.mcarr.android.data.entities.joins.FullChapterJoin

@Dao
interface FullChapterDao {

    @Transaction
    @Query("SELECT * FROM `${FullChapterEntity.TABLE_NAME}` WHERE `${FullChapterEntity.ID}` = :id")
    fun getFullChapter(id: Int): FullChapterJoin?

    @Transaction
    @Query("SELECT * FROM `${FullChapterEntity.TABLE_NAME}` WHERE `${FullChapterEntity.BOOK_ID}` = :bookId")
    fun getFullChaptersByBookId(bookId: Int): List<FullChapterJoin>

    @Update
    fun update(row: FullChapterEntity)

    @Query("DELETE FROM `${FullChapterEntity.TABLE_NAME}` WHERE `${FullChapterEntity.ID}` = :id")
    fun delete(id: Int)

}
package dev.mcarr.android.data.daos

import androidx.room.Dao
import androidx.room.Query
import dev.mcarr.android.data.entities.FullPageEntity
import dev.mcarr.android.data.entities.joins.FullPageJoin

@Dao
interface FullPageDao {

    @Query("SELECT * FROM `${FullPageEntity.TABLE_NAME}` WHERE `${FullPageEntity.BOOK_ID}` = :bookId")
    fun getFullPagesByBookId(bookId: Int): List<FullPageJoin>

}
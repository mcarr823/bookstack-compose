package dev.mcarr.android.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import dev.mcarr.android.data.entities.FullBookEntity
import dev.mcarr.android.data.entities.joins.FullBookJoin

@Dao
interface FullBookDao {

    @Transaction
    @Query("SELECT * FROM `${FullBookEntity.TABLE_NAME}` WHERE `${FullBookEntity.ID}` = :id")
    fun getFullBook(id: Int): FullBookJoin?

    @Update
    fun update(row: FullBookEntity)

    @Query("DELETE FROM `${FullBookEntity.TABLE_NAME}` WHERE `${FullBookEntity.ID}` = :id")
    fun delete(id: Int)

}
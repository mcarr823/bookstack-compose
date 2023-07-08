package dev.mcarr.android.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import dev.mcarr.android.data.entities.FullBookEntity

@Dao
interface FullBookDao {

    @Query("SELECT * FROM `${FullBookEntity.TABLE_NAME}` WHERE `${FullBookEntity.ID}` = :id")
    fun getFullBook(id: Int): FullBookEntity?

    @Update
    fun update(row: FullBookEntity)

    @Query("DELETE FROM `${FullBookEntity.TABLE_NAME}` WHERE `${FullBookEntity.ID}` = :id")
    fun delete(id: Int)

}
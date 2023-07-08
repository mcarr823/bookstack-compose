package dev.mcarr.android.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import dev.mcarr.android.data.entities.FullChapterEntity

@Dao
interface FullChapterDao {

    @Query("SELECT * FROM `${FullChapterEntity.TABLE_NAME}` WHERE `${FullChapterEntity.ID}` = :id")
    fun getFullChapter(id: Int): FullChapterEntity?

    @Update
    fun update(row: FullChapterEntity)

    @Query("DELETE FROM `${FullChapterEntity.TABLE_NAME}` WHERE `${FullChapterEntity.ID}` = :id")
    fun delete(id: Int)

}
package dev.mcarr.android.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.mcarr.android.data.entities.ChapterEntity

@Dao
interface ChapterDao {

    @Query("SELECT * FROM `${ChapterEntity.TABLE_NAME}`")
    fun getChapters(): List<ChapterEntity>

    @Query("SELECT * FROM `${ChapterEntity.TABLE_NAME}` WHERE `${ChapterEntity.ID}` = :id")
    fun getChapter(id: Int): ChapterEntity?

    @Insert
    fun insertChapters(rows: List<ChapterEntity>)

    @Update
    fun update(row: ChapterEntity)

    @Query("DELETE FROM `${ChapterEntity.TABLE_NAME}`")
    fun delete()

    @Query("DELETE FROM `${ChapterEntity.TABLE_NAME}` WHERE `${ChapterEntity.ID}` = :id")
    fun delete(id: Int)

}
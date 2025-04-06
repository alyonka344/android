package ru.kholmogorova.myapplication.dal.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: DogHistory)

    @Query("SELECT * FROM history ORDER BY timestamp DESC LIMIT 10")
    suspend fun getAllHistory(): List<DogHistory>

    @Query("DELETE FROM history WHERE id = :id")
    suspend fun deleteHistoryById(id: Long)
}
package ru.kholmogorova.myapplication.infrastructure

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kholmogorova.myapplication.dal.model.DogHistory
import ru.kholmogorova.myapplication.dal.model.HistoryDao

@Database(entities = [DogHistory::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
package ru.kholmogorova.myapplication.dal.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class DogHistory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val imageUrl: String,
    val timestamp: Long = System.currentTimeMillis()
)
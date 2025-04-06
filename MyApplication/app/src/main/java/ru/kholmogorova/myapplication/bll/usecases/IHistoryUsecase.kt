package ru.kholmogorova.myapplication.bll.usecases

import ru.kholmogorova.myapplication.dal.model.DogHistory

interface IHistoryUsecase {
    suspend fun getAllHistory() : List<DogHistory>
    suspend fun deleteHistoryById(id: Long)
    suspend fun addToHistory(history: DogHistory)
}
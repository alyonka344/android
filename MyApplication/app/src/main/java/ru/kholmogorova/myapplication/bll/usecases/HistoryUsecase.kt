package ru.kholmogorova.myapplication.bll.usecases

import ru.kholmogorova.myapplication.dal.model.HistoryDao
import ru.kholmogorova.myapplication.dal.model.DogHistory
import java.sql.Timestamp

class HistoryUsecase(private val historyDao: HistoryDao) : IHistoryUsecase {

    override suspend fun getAllHistory(): List<DogHistory> {
        return historyDao.getAllHistory()
    }

    override suspend fun deleteHistoryById(id: Long) {
        historyDao.deleteHistoryById(id)
    }

    override suspend fun addToHistory(history: DogHistory) {
        historyDao.insert(history)
    }
}
package ru.kholmogorova.myapplication.dal.repository

import ru.kholmogorova.myapplication.bll.model.Currency

interface CurrencyRepository {
    suspend fun getLatestRates(baseCurrency: String): Currency
}
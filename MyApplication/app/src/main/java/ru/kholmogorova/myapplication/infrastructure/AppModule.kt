package ru.kholmogorova.myapplication.infrastructure

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.kholmogorova.myapplication.bll.usecases.HistoryUsecase

val appModule = module {
    single {
        Room.databaseBuilder(androidApplication(), MyDatabase::class.java, "my_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<MyDatabase>().historyDao() }
    single { HistoryUsecase(get()) }
}
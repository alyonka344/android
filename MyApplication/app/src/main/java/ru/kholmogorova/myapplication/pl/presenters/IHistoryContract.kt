package ru.kholmogorova.myapplication.pl.presenters

import ru.kholmogorova.myapplication.dal.model.DogHistory

interface IHistoryContract {
    interface View {
        fun showHistory(dogs: List<DogHistory>)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun refreshHistory()
        fun removeHistory(id: Long)
    }
}
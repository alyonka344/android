package ru.kholmogorova.myapplication.pl.presenters

import ru.kholmogorova.myapplication.dal.model.DogResponse

interface IMainContract {
    interface View {
        fun showDog(dog: DogResponse)
        fun showError(message: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadDog()
    }
}
package ru.kholmogorova.myapplication.pl.presenters

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.kholmogorova.myapplication.bll.usecases.HistoryUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryPresenter : IHistoryContract.Presenter, KoinComponent {
    private var view: IHistoryContract.View? = null
    private val dogHistoryUsecase: HistoryUsecase by inject()

    override fun attach(view: IHistoryContract.View) {
        this.view = view
        refreshHistory()
    }

    override fun detach() {
        view = null
    }

    override fun refreshHistory() {
        CoroutineScope(Dispatchers.Main).launch {
            val history = dogHistoryUsecase.getAllHistory()
            view?.showHistory(history)
        }
    }

    override fun removeHistory(id: Long) {
        CoroutineScope(Dispatchers.Main).launch {
            dogHistoryUsecase.deleteHistoryById(id)
            refreshHistory()
        }
    }
}
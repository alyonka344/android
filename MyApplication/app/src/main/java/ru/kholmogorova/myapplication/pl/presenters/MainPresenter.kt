package ru.kholmogorova.myapplication.pl.presenters

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.kholmogorova.myapplication.bll.usecases.HistoryUsecase
import ru.kholmogorova.myapplication.dal.api.RetrofitInstance
import ru.kholmogorova.myapplication.dal.model.DogHistory
import ru.kholmogorova.myapplication.dal.model.DogResponse

class MainPresenter : KoinComponent, IMainContract.Presenter {
    private var view: IMainContract.View? = null
    private val historyUsecase: HistoryUsecase by inject()

    override fun attach(view: IMainContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun loadDog() {
        view?.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                var dogResponse = DogResponse(url = "")
                val allowedFormats = listOf("jpg", "jpeg", "png", "gif")
                var fileExtension = ""
                while (dogResponse.url.isEmpty() or (fileExtension !in allowedFormats)) {
                    dogResponse = RetrofitInstance.api.getRandomDog()
                    fileExtension = dogResponse.url.substringAfterLast('.', "").lowercase()
                }
                view?.showDog(dogResponse)
                view?.hideLoading()

                val history = DogHistory(
                    imageUrl = dogResponse.url,
                    timestamp = System.currentTimeMillis()
                )
                historyUsecase.addToHistory(history)
            } catch (e: Exception) {
                view?.showError("Ошибка загрузки собаки: ${e.message}")
            } finally {
                view?.hideLoading()
            }
        }
    }
}
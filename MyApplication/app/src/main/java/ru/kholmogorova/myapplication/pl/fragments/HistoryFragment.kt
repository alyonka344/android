package ru.kholmogorova.myapplication.pl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.kholmogorova.myapplication.R
import ru.kholmogorova.myapplication.dal.model.DogHistory
import ru.kholmogorova.myapplication.pl.HistoryItemView
import ru.kholmogorova.myapplication.pl.presenters.HistoryPresenter
import ru.kholmogorova.myapplication.pl.presenters.IHistoryContract

class HistoryFragment : Fragment(), IHistoryContract.View {
    private lateinit var presenter: IHistoryContract.Presenter
    private lateinit var container: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container = view.findViewById(R.id.historyContainer)
        presenter = HistoryPresenter()
        presenter.attach(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun onResume() {
        super.onResume()
        presenter.refreshHistory()
    }

    override fun showHistory(dogs: List<DogHistory>) {
        container.removeAllViews()
        val inflater = LayoutInflater.from(context)

        dogs.forEach { history ->
            // Используем кастомную вьюшку
            val historyItemView = HistoryItemView(requireContext())
            historyItemView.bind(history.imageUrl) // Привязываем данные к вьюшке

            // Устанавливаем слушатель на кнопку удаления
            historyItemView.findViewById<Button>(R.id.removeHistoryButton).setOnClickListener {
                presenter.removeHistory(history.id)
            }

            // Добавляем кастомную вьюшку в контейнер
            container.addView(historyItemView)
        }
    }

}
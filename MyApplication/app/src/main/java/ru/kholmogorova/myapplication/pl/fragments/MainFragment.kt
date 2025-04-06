package ru.kholmogorova.myapplication.pl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.kholmogorova.myapplication.R
import ru.kholmogorova.myapplication.dal.model.DogResponse
import ru.kholmogorova.myapplication.pl.presenters.IMainContract
import ru.kholmogorova.myapplication.pl.presenters.MainPresenter

class MainFragment : Fragment(), IMainContract.View {
    private lateinit var presenter: IMainContract.Presenter
    private lateinit var imageViewDog: ImageView
    private lateinit var imageViewLoading: ImageView
    private lateinit var buttonGetDog: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageViewDog = view.findViewById(R.id.imageViewDog)
        imageViewLoading = view.findViewById(R.id.imageViewLoading)
        buttonGetDog = view.findViewById(R.id.buttonGetDog)

        presenter = MainPresenter()
        presenter.attach(this)

        buttonGetDog.setOnClickListener {
            presenter.loadDog()
        }

        Glide.with(this)
            .asGif()
            .load(R.drawable.loading_gif)
            .centerCrop()
            .into(imageViewLoading)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun showDog(dog: DogResponse) {
        Glide.with(requireContext())
            .load(dog.url)
            .centerCrop()
            .into(imageViewDog)

        imageViewLoading.visibility = View.INVISIBLE
        imageViewDog.visibility = View.VISIBLE
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), "Ошибка: $message", Toast.LENGTH_SHORT).show()
        hideLoading()
    }

    override fun showLoading() {
        imageViewDog.visibility = View.INVISIBLE
        imageViewLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        imageViewDog.visibility = View.VISIBLE
        imageViewLoading.visibility = View.INVISIBLE
    }
}
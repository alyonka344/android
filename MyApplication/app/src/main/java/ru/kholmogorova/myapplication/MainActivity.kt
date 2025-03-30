package ru.kholmogorova.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import ru.kholmogorova.myapplication.dal.api.RetrofitInstance
import ru.kholmogorova.myapplication.dal.model.DogResponse

class MainActivity : AppCompatActivity() {
    private lateinit var imageViewDog: ImageView
    private lateinit var buttonGetDog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        imageViewDog = findViewById(R.id.imageViewDog)
        buttonGetDog = findViewById(R.id.buttonGetDog)

        buttonGetDog.setOnClickListener {
            getRandomDogImage()
        }
    }

    private fun getRandomDogImage() {
        RetrofitInstance.api.getRandomDog().enqueue(object : Callback<DogResponse> {
            override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {
                if (response.isSuccessful) {
                    val dogImageUrl = response.body()?.url
                    if (!dogImageUrl.isNullOrEmpty()) {
                        Glide.with(this@MainActivity)
                            .load(dogImageUrl)
                            .centerCrop()
                            .into(imageViewDog)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Ошибка загрузки", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ошибка сети: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
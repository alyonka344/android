package ru.kholmogorova.myapplication.dal.api

import retrofit2.Call
import retrofit2.http.GET
import ru.kholmogorova.myapplication.dal.model.DogResponse

interface DogApiService {
    @GET("woof.json")
    fun getRandomDog(): Call<DogResponse>
}
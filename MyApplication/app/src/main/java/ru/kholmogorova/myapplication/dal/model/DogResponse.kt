package ru.kholmogorova.myapplication.dal.model

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("url") val url: String
)
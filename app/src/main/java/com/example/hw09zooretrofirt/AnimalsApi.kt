package com.example.hw09zooretrofirt

import retrofit2.Call
import retrofit2.http.GET

interface AnimalsApi {
    @GET("animals/rand/10")
    fun fetchAnimals(): Call<List<Animal>>
}
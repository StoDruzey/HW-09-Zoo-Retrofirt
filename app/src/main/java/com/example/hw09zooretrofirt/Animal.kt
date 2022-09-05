package com.example.hw09zooretrofirt

import com.google.gson.annotations.SerializedName

data class Animal(
    val id: Int,
    val name: String,
    @SerializedName("latin_name")
    val latinName: String,
    @SerializedName("animal_type")
    val animalType: String,
    @SerializedName("lifespan")
    val lifeSpan: String,
    val habitat: String,
    val diet: String,
    @SerializedName("geo_range")
    val geoRange: String,
    @SerializedName("image_link")
    val url: String
)
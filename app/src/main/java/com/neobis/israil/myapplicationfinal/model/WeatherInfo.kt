package com.neobis.israil.myapplicationfinal.model

data class WeatherInfo(
        val weather: List<Weather>,
        val base: String,
        val main: Main,
        val visibility: Int,
        val dt: Int,
        val id: Int,
        val name: String,
        val cod: Int
)



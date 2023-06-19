package com.example.avaliacao_03_andre_ads_pdm.data

import com.squareup.moshi.Json

data class Musica(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "seconds") val seconds: Int
)

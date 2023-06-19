package com.example.avaliacao_03_andre_ads_pdm.data

import com.squareup.moshi.Json

data class Aluno(
    @Json(name = "cpf") val cpf: String,
    @Json(name = "posicao") val posicao: String,
    @Json(name = "altura") val altura: String,
    @Json(name = "massa") val massa: String,
    @Json(name = "foto") val imagem: String
)

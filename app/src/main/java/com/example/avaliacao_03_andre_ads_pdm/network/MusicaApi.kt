package com.example.avaliacao_03_andre_ads_pdm.network

import com.example.avaliacao_03_andre_ads_pdm.data.Musica
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http://127.0.0.1:8000"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface MusicaApiService {
    @GET("/musics/")
    suspend fun getMusicas(): List<Musica>
}

object MusicaApi {
    val retrofitService: MusicaApiService by lazy {
        retrofit.create(MusicaApiService::class.java)
    }
}
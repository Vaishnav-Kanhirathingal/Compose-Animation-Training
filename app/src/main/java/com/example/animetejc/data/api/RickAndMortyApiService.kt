package com.example.animetejc.data.api

import com.example.animetejc.data.response.CharacterPageResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {
    /**
     * https://rickandmortyapi.com/api/character
     * https://rickandmortyapi.com/api/character?page=2
     * */
    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service by lazy { retrofitBuilder.create(RickAndMortyApiService::class.java) }
    }

    @GET("character?")
    suspend fun getCharacters(@Query("page") pageNo: Int): CharacterPageResponse
}
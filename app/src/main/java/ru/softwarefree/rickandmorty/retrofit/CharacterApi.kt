package ru.softwarefree.rickandmorty.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {
    @GET("/character/{id}")
    suspend fun getCharacterId(@Path("id") id: Int): Results

    @GET("character")
    suspend fun getCharacterApi(
        @Query("name") name: String="",
        @Query("status") status: String="",
        @Query("species") species: String="",
        @Query("type") type: String="",
        @Query("gender") gender: String="",
        @Query("image") image: String="",
    ): Results
}
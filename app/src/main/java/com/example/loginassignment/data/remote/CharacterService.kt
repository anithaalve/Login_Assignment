package com.example.loginassignment.data.remote

import com.example.loginassignment.data.entities.CharacterEntity
import com.example.loginassignment.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
  @GET("character")
  suspend fun getAllCharacters(): Response<CharacterList>
  
  @GET("character/{id}")
  suspend fun getCharacter(@Path("id") id: Int): Response<CharacterEntity>
}
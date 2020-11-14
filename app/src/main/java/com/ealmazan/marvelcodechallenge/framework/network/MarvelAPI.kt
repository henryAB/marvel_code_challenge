package com.ealmazan.marvelcodechallenge.framework.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface MarvelAPI {

    @GET("/v1/public/characters")
    fun getCharacters(): Deferred<>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterInfo(id: Int): Deferred<>
}
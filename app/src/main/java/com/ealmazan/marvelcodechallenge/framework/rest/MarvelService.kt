package com.ealmazan.marvelcodechallenge.framework.rest

import com.ealmazan.marvelcodechallenge.domain.CharacterWrapper
import com.ealmazan.marvelcodechallenge.domain.MarvelCharacter
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface MarvelService {

    @GET("/v1/public/characters")
    fun getCharacters(@Query("offset") offset: Int): Deferred<CharacterWrapper>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterInfo(id: Int): Deferred<MarvelCharacter>
}
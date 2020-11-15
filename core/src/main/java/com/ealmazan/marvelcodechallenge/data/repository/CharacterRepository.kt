package com.ealmazan.marvelcodechallenge.data.repository

import com.ealmazan.marvelcodechallenge.domain.MarvelCharacter

interface CharacterRepository {
    suspend fun getAllCharacters(offset: Int): List<MarvelCharacter>
    suspend fun getCharacter(id: Int): MarvelCharacter
}
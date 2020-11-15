package com.ealmazan.marvelcodechallenge.data

import com.ealmazan.marvelcodechallenge.domain.CharacterWrapper
import com.ealmazan.marvelcodechallenge.domain.MarvelCharacter

interface CharacterRemoteDataSource {
    suspend fun getCharacters(offset: Int): CharacterWrapper
    suspend fun getCharacterInfo(id: Int): MarvelCharacter
}
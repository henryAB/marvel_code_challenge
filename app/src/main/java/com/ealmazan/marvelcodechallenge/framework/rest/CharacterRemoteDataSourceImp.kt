package com.ealmazan.marvelcodechallenge.framework.rest

import com.ealmazan.marvelcodechallenge.data.CharacterRemoteDataSource
import com.ealmazan.marvelcodechallenge.domain.CharacterWrapper
import com.ealmazan.marvelcodechallenge.domain.MarvelCharacter

class CharacterRemoteDataSourceImp(private val marvelAPI: MarvelAPI): CharacterRemoteDataSource {
    override suspend fun getCharacters(offset: Int): CharacterWrapper = marvelAPI.api.getCharacters(offset).await()
    override suspend fun getCharacterInfo(id: Int): MarvelCharacter = marvelAPI.api.getCharacterInfo(id).await()
}
package com.ealmazan.marvelcodechallenge.data.repository

import com.ealmazan.marvelcodechallenge.data.CharacterDataSource
import com.ealmazan.marvelcodechallenge.data.CharacterRemoteDataSource
import com.ealmazan.marvelcodechallenge.domain.MarvelCharacter

class CharacterRepositoryImp(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val characterDataSource: CharacterDataSource): CharacterRepository {

    override suspend fun getAllCharacters(offset: Int): List<MarvelCharacter> {
        return characterRemoteDataSource.getCharacters(offset).data.results
    }
    override suspend fun getCharacter(id: Int): MarvelCharacter = characterRemoteDataSource.getCharacterInfo(id)
}
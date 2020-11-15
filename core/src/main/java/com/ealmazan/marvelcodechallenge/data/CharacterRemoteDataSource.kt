package com.ealmazan.marvelcodechallenge.data

import com.ealmazan.marvelcodechallenge.domain.CharacterWrapper

interface CharacterRemoteDataSource {
    suspend fun getCharacters(offset: Int): CharacterWrapper
}
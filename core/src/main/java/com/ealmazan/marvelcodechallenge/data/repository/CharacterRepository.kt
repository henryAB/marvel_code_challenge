package com.ealmazan.marvelcodechallenge.data.repository

import com.ealmazan.marvelcodechallenge.domain.CharacterWrapper
import com.ealmazan.marvelcodechallenge.usecases.Either
import com.ealmazan.marvelcodechallenge.usecases.ErrorType

interface CharacterRepository {
    suspend fun getAllCharacters(offset: Int): Either<ErrorType, CharacterWrapper>
}
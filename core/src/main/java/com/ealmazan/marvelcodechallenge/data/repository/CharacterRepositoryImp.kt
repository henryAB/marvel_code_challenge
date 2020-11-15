package com.ealmazan.marvelcodechallenge.data.repository

import com.ealmazan.marvelcodechallenge.data.CharacterDataSource
import com.ealmazan.marvelcodechallenge.data.CharacterRemoteDataSource
import com.ealmazan.marvelcodechallenge.domain.CharacterWrapper
import com.ealmazan.marvelcodechallenge.usecases.Either
import com.ealmazan.marvelcodechallenge.usecases.ErrorType

class CharacterRepositoryImp(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val characterDataSource: CharacterDataSource): CharacterRepository {

    override suspend fun getAllCharacters(offset: Int): Either<ErrorType, CharacterWrapper> {
        return try {
            Either.Right(characterRemoteDataSource.getCharacters(offset))
        } catch (exception: Exception) {
            Either.Left(ErrorType.NETWORK)
        }
    }
}
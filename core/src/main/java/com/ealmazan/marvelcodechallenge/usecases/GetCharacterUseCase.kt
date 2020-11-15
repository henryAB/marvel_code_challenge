package com.ealmazan.marvelcodechallenge.usecases

import com.ealmazan.marvelcodechallenge.data.repository.CharacterRepository

class GetCharacterUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(offset: Int) = repository.getAllCharacters(offset)
}
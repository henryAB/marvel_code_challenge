package com.ealmazan.marvelcodechallenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ealmazan.marvelcodechallenge.domain.MarvelCharacter
import com.ealmazan.marvelcodechallenge.usecases.GetCharacterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(private val getCharacterUseCases: GetCharacterUseCase) : ViewModel() {

    val characters = MutableLiveData<List<MarvelCharacter>>()

    fun requestCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            characters.postValue(getCharacterUseCases.invoke(0))
        }
    }

}
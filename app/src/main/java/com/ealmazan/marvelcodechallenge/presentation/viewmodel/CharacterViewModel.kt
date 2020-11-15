package com.ealmazan.marvelcodechallenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ealmazan.marvelcodechallenge.domain.CharacterWrapper
import com.ealmazan.marvelcodechallenge.usecases.Either
import com.ealmazan.marvelcodechallenge.usecases.ErrorType
import com.ealmazan.marvelcodechallenge.usecases.GetCharacterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val PAGE_SIZE = 20

class CharacterViewModel(private val getCharacterUseCases: GetCharacterUseCase) : ViewModel() {

    val wrapper = MutableLiveData<Either<ErrorType, CharacterWrapper>>()
    private var lastOffset = 0
    private var isLoading = false

    fun requestCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val newWrapper = getCharacterUseCases.invoke(lastOffset)
            if (newWrapper is Either.Right) {
                lastOffset = newWrapper.r.data.offset + PAGE_SIZE
            }
            wrapper.postValue(newWrapper)
            isLoading = false
        }
    }

    fun scroll(dx: Int, dy: Int, linearLayoutManager: LinearLayoutManager) {
        if (dy > 0) {
            val visibleItemCount = linearLayoutManager.childCount
            val totalItemCount = linearLayoutManager.itemCount
            val firstVisibleItemPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()
            if (!isLoading) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= lastOffset) {
                    isLoading = true
                    requestCharacters()
                }
            }
        }
    }

}
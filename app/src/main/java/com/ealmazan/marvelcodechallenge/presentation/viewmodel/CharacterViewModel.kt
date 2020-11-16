package com.ealmazan.marvelcodechallenge.presentation.viewmodel

import android.util.Log
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

class CharacterViewModel(private val getCharactersUseCases: GetCharacterUseCase) : ViewModel() {

    val wrapper = MutableLiveData<Either<ErrorType, CharacterWrapper>>()
    val loadMore = MutableLiveData<Boolean>()
    private var lastOffset = 0
    private var totalCount = 0
    private var isLoading = false

    fun requestCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val newWrapper = getCharactersUseCases.invoke(lastOffset)
            if (newWrapper is Either.Right) {
                totalCount = newWrapper.r.data.total
                lastOffset = newWrapper.r.data.offset + PAGE_SIZE
                Log.i(CharacterViewModel::class.java.canonicalName, "Offset: $lastOffset Total count: $totalCount")
                if (lastOffset > totalCount) {
                    loadMore.postValue(false)
                }
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
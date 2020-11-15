package com.ealmazan.marvelcodechallenge.domain

data class CharacterWrapper(
    val code: Int,
    val status: String,
    val data: CharacterDataContainer
)
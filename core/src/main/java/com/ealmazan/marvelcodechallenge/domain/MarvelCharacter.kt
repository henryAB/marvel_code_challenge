package com.ealmazan.marvelcodechallenge.domain

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Image)
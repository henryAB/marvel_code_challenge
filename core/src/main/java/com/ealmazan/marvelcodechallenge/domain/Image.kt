package com.ealmazan.marvelcodechallenge.domain

data class Image(
    val path: String,
    val extension: String
) {
    fun getFullUrl(): String = "$path.$extension"
}
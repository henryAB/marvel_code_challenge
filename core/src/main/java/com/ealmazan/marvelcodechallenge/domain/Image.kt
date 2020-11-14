package com.ealmazan.marvelcodechallenge.domain

/**
 * Tipo de dato devuelto por la API de marvel usado para tratar el tipo thumbnail
 *
 * @param path TODO
 * @param extension TODO
 */
data class Image(
    val path: String,
    val extension: String
)
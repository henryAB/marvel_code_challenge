package com.ealmazan.marvelcodechallenge.domain

/**
 * Modelo de personajes devueltos por la API de marvel
 *
 * @id Identificador único
 * @name Nombre del personaje
 */
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Image)
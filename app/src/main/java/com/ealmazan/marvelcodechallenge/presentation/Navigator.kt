package com.ealmazan.marvelcodechallenge.presentation

import android.content.Context
import android.content.Intent
import com.ealmazan.marvelcodechallenge.domain.MarvelCharacter

const val CHARACTER_NAME = "character_name"
const val CHARACTER_DESCRIPTION = "character_description"
const val CHARACTER_IMAGE_PATH = "character_image_path"

class Navigator {

    fun openDetailActivity(context: Context, marvelCharacter: MarvelCharacter) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(CHARACTER_NAME, marvelCharacter.name)
        intent.putExtra(CHARACTER_DESCRIPTION, marvelCharacter.description)
        intent.putExtra(
            CHARACTER_IMAGE_PATH,
            "${marvelCharacter.thumbnail.path}.${marvelCharacter.thumbnail.extension}"
        )
        context.startActivity(intent)
    }
}
package com.ealmazan.marvelcodechallenge.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ealmazan.marvelcodechallenge.domain.MarvelCharacter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(marvelCharacter: MarvelCharacter, clickListener: (MarvelCharacter) -> Unit) {
        itemView.tv_character_name.text = marvelCharacter.name
        Picasso.get()
            .load("${marvelCharacter.thumbnail.path}.${marvelCharacter.thumbnail.extension}")
            .into(itemView.iv_character_photo)
        itemView.setOnClickListener {
            clickListener(marvelCharacter)
        }
    }

}
package com.ealmazan.marvelcodechallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ealmazan.marvelcodechallenge.R
import com.ealmazan.marvelcodechallenge.domain.MarvelCharacter
import com.ealmazan.marvelcodechallenge.presentation.viewholder.CharacterViewHolder
import com.ealmazan.marvelcodechallenge.presentation.viewholder.ProgressViewHolder
import kotlin.properties.Delegates

const val CHARACTER_TYPE = 0
const val LOADING_TYPE = 1

class CharacterAdapter(private val clickListener: (MarvelCharacter) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var loadMore = true
    var data: MutableList<MarvelCharacter> by Delegates.observable(mutableListOf()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == CHARACTER_TYPE) {
            CharacterViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
            )
        } else {
            ProgressViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.load_item, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == data.size) {
            LOADING_TYPE
        } else {
            CHARACTER_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterViewHolder) {
            holder.bind(data[position], clickListener)
        }
    }

    override fun getItemCount(): Int {
        return if (loadMore) {
            data.size + 1
        } else {
            data.size
        }
    }

}


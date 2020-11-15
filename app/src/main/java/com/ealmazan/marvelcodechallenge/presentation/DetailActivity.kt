package com.ealmazan.marvelcodechallenge.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ealmazan.marvelcodechallenge.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        fillViews()
    }

    private fun fillViews() {
        tv_character_name.text = intent.getStringExtra(CHARACTER_NAME)
        Picasso.get().load(intent.getStringExtra(CHARACTER_IMAGE_PATH)).into(iv_character_photo)
        val description = intent.getStringExtra(CHARACTER_DESCRIPTION)
        tv_character_description.text = if (description.isNullOrEmpty()) {
            getString(R.string.default_empty_character_description)
        } else {
            description
        }
    }
}
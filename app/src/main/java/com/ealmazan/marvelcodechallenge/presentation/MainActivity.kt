package com.ealmazan.marvelcodechallenge.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ealmazan.marvelcodechallenge.R
import com.ealmazan.marvelcodechallenge.presentation.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val characterViewModel: CharacterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        characterViewModel.characters.observe(this, { marvelCharacterList ->
            marvelCharacterList.forEach {
                Log.i(MainActivity::class.java.canonicalName, "${it.name}")
            }
        })
        characterViewModel.requestCharacters()
    }
}
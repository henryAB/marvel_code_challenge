package com.ealmazan.marvelcodechallenge.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ealmazan.marvelcodechallenge.R
import com.ealmazan.marvelcodechallenge.presentation.adapter.CharacterAdapter
import com.ealmazan.marvelcodechallenge.presentation.viewmodel.CharacterViewModel
import com.ealmazan.marvelcodechallenge.usecases.Either
import com.ealmazan.marvelcodechallenge.usecases.ErrorType
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.load_item.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    private val characterViewModel: CharacterViewModel by viewModel()

    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        characterAdapter = CharacterAdapter { character ->
            navigator.openDetailActivity(this, character)
        }
        initRecyclerView()
        characterViewModel.wrapper.observe(this, { wrapper ->
            when (wrapper) {
                is Either.Right -> {
                    ll_load.visibility = View.GONE
                    rv_marvel_characters.visibility = View.VISIBLE
                    characterAdapter.data.addAll(wrapper.r.data.results)
                    characterAdapter.notifyDataSetChanged()
                }
                is Either.Left -> {
                    when (wrapper.l) {
                        ErrorType.NETWORK -> {
                            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        })
        characterViewModel.loadMore.observe(this, { loadMore -> characterAdapter.loadMore = loadMore })
        characterViewModel.requestCharacters()
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        rv_marvel_characters.apply {
            layoutManager = linearLayoutManager
            adapter = characterAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    characterViewModel.scroll(dx, dy, linearLayoutManager)
                }
            })
        }
    }
}
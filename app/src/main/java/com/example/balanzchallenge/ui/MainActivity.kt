package com.example.balanzchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.balanzchallenge.data.PairInfo
import com.example.balanzchallenge.data.PairInfoPesos
import com.example.balanzchallenge.databinding.ActivityMainBinding
import com.example.balanzchallenge.viewmodel.ParViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel by viewModels<ParViewModel>()
    private lateinit var adapter : PairsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PairsAdapter()
        binding.rvRatings.layoutManager = LinearLayoutManager(this)
        val recycler = binding.rvRatings
        recycler.adapter = adapter
        getPairs()
    }

    private fun getPairs() {
        viewModel.getPairs()
        viewModel.state.observe(this, Observer {
            when (it) {
                is ParViewModel.State.Failure -> displayError(it.cause)
                is ParViewModel.State.Loading -> showProgressBar()
                is ParViewModel.State.Success -> showPairs(it.pairs)
            }
        })
    }

    private fun displayError(cause: Throwable) {
        //TODO
    }

    private fun showPairs(pairs: MutableList<PairInfoPesos>) {
        binding.progressBar.isVisible = false
        adapter.setPairs(pairs)
    }

    private fun showProgressBar() {
        binding.progressBar.isVisible = true
    }

}
package com.example.balanzchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.RemoteMediator
import com.example.balanzchallenge.data.ParInfo
import com.example.balanzchallenge.databinding.ActivityMainBinding
import com.example.balanzchallenge.viewmodel.ParViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel by viewModels<ParViewModel>()
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPairs()
    }

    private fun getPairs() {
        viewModel.getPairs()
        viewModel.state.observe(this, Observer {
            when (it) {
                //is ParViewModel.State.Failure -> displayError(it.cause)
                //is ParViewModel.State.Loading -> showProgressBar()
                is ParViewModel.State.Success -> showMovie(it.pairs)
            }
        })
    }

    private fun showMovie(pairs: List<ParInfo>) {

        binding.priceChange.text = pairs.first().prevClosePrice
        Log.e("error", pairs.first().prevClosePrice)
    }

    private fun showProgressBar() {
        //binding.progressBar.isVisible = true
    }

}
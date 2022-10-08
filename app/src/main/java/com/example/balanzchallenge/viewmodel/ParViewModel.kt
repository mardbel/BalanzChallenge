package com.example.balanzchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balanzchallenge.data.ParInfo
import com.example.balanzchallenge.repository.ParRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParViewModel @Inject constructor(private val parRepository: ParRepository) : ViewModel() {

    suspend fun getPars() {
        _state.value = State.Loading()
        viewModelScope.launch {
            val pairDetails = parRepository.getPars()
            if (pairDetails == null) {
                throw  PairsNotFoundedException()
            } else _state.postValue(State.Success(pairDetails))
        }
    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    sealed class State {
        class Success(val pairs: List<ParInfo>) : State()
        class Failure(val cause: Throwable) : State()
        class Loading : State()
    }

    class PairsNotFoundedException : Exception("Error declaring pairs")

}
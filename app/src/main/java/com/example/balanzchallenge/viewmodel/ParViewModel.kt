package com.example.balanzchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balanzchallenge.data.PairInfo
import com.example.balanzchallenge.data.PairInfoPesos
import com.example.balanzchallenge.repository.ParRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class ParViewModel @Inject constructor(private val parRepository: ParRepository) : ViewModel() {

    private val CCL: Double = 200.00

    fun getPairs() {
        _state.value = State.Loading()
        viewModelScope.launch {
            val pairDetails = parRepository.getPairs()

            val pesifiedValue = transformToPesos(pairDetails)
            if (pairDetails == null) {
                _state.value = State.Failure(PairsNotFoundedException())
            } else _state.value = State.Success(pesifiedValue)
        }
    }

    private val _state = MutableLiveData<State>()

    val state: LiveData<State>
        get() = _state

    private fun transformToPesos(pairDetails : MutableList<PairInfo>?): MutableList<PairInfoPesos> {
        val pairList = mutableListOf<PairInfoPesos>()
        pairDetails?.forEach {
            val askPrice = it.askPrice.toDouble() * CCL
            val df = DecimalFormat("###,###,###.00")
            val twoDigitNum = df.format(askPrice)

            val priceChange = it.priceChangePercent.toDouble()
            val twoDigitChange = df.format(priceChange)

            val bidPrice = (1.00 / it.bidPrice.toDouble() * CCL)
            val bidFormated = df.format(bidPrice)

            val pesifiedValue =
                if (it.symbol == "BUSDUSDT") {
                    PairInfoPesos(
                        symbol = it.symbol,
                        askPrice = bidFormated,
                        priceChangePercent = twoDigitChange.toDouble()
                    )
                } else {
                    PairInfoPesos(
                        symbol = it.symbol,
                        askPrice = twoDigitNum,
                        priceChangePercent = twoDigitChange.toDouble()
                    )
                }

            pairList.add(pesifiedValue)
        }
        return pairList
    }


    sealed class State {
        class Success(val pairs: MutableList<PairInfoPesos>) : State()
        class Failure(val cause: Throwable) : State()
        class Loading : State()
    }

    class PairsNotFoundedException : Exception("Error declaring pairs")

}
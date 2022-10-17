package com.example.balanzchallenge.repository

import com.example.balanzchallenge.api.BinanceApiService
import com.example.balanzchallenge.data.PairInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParRepository @Inject constructor(
    private val binanceService: BinanceApiService
) {

    suspend fun getPairs(): MutableList<PairInfo>? {
        return binanceService.getPairsInfo(returnPairs())
    }

    private fun returnPairs() : String {
        return "[\"BTCBUSD\",\"ETHBUSD\",\"BNBBUSD\",\"LUNABUSD\",\"SOLBUSD\",\"LTCBUSD\",\"MATICBUSD\",\"AVAXBUSD\",\"XRPBUSD\",\"BUSDUSDT\"]"
    }

}
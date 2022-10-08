package com.example.balanzchallenge.repository

import com.example.balanzchallenge.api.BinanceApiService
import com.example.balanzchallenge.data.ParInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParRepository @Inject constructor(
    private val binanceService: BinanceApiService
) {

    suspend fun getPars(): List<ParInfo>? {
        return binanceService.getParsInfo(pars)
    }

    private val pars = "[\"BTCBUSD\"]"
}

//,%22ETHBUSD%22,%22BNBBUSD%22,%22LUNABUSD%22,%22SOLBUSD%22,%22LTCBUSD%22,%22MATICBUSD%22,%22AVAXBUSD%22,%22XRPBUSD%22,%22BUSDUSDT%22
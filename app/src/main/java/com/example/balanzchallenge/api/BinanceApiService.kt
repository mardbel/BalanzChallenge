package com.example.balanzchallenge.api

import com.example.balanzchallenge.data.PairInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface BinanceApiService {

    val PATH : String

    @GET("api/v3/ticker/24hr") // revisar esta configuracion
    suspend fun getPairsInfo(
        @Query("symbols") id: String
    ): MutableList<PairInfo>?
}
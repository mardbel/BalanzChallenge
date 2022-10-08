package com.example.balanzchallenge.api

import com.example.balanzchallenge.data.ParInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface BinanceApiService {

    @GET("api/v3/ticker/24hr?symbols={par_ids}") // revisar esta configuracion
    suspend fun getParsInfo(
        @Path("par_ids") id: String
    ): List<ParInfo>?
}
package com.example.balanzchallenge.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PairInfo(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("bidPrice") val bidPrice: String,
    @SerializedName("askPrice") val askPrice: String,
    @SerializedName("priceChangePercent") val priceChangePercent: String
) : Parcelable

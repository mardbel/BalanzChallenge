package com.example.balanzchallenge.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PairInfoPesos(
    val symbol: String,
    val askPrice: String,
    val priceChangePercent: Double
): Parcelable
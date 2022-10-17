package com.example.balanzchallenge.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.balanzchallenge.R
import com.example.balanzchallenge.data.PairInfoPesos
import com.example.balanzchallenge.databinding.ItemCoinBinding

class PairsAdapter : RecyclerView.Adapter<PairsAdapter.ViewHolder>() {

    private var mPairs: List<PairInfoPesos> = listOf()

    fun setPairs(pairs: MutableList<PairInfoPesos>) {
        mPairs = pairs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mPairs[position])
    }

    override fun getItemCount(): Int {
        return mPairs.size
    }

    class ViewHolder constructor(private val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pair: PairInfoPesos) {


            binding.tvCoinRate.text = "${pair.askPrice}"

            val pairChange = pair.priceChangePercent
            if (pairChange >= 0) {
                binding.tvCoinVariation.text = "+${pair.priceChangePercent}%"
                binding.tvCoinVariation.setTextColor(Color.GREEN)
            } else {
                binding.tvCoinVariation.text = "${pair.priceChangePercent}%"
                binding.tvCoinVariation.setTextColor(Color.RED)
            }

            val pairName = pair.symbol
            val pairNameFormatted = pairName.substring(0, pairName.length-4)
            binding.tvCoinShortName.text = pairNameFormatted

            binding.tvCoinName.text = when (pairName) {
                "BTCBUSD" -> "Bitcoin"
                "ETHBUSD" -> "Ethereum"
                "BNBBUSD" -> "Binance"
                "LUNABUSD" -> "Luna"
                "SOLBUSD" -> "Solana"
                "LTCBUSD" -> "LiteCoin"
                "MATICBUSD" -> "Matic"
                "AVAXBUSD" -> "Avalanche"
                "BUSDUSDT" -> "Dolar BUSD"
                else -> "Ripple"
            }

            binding.ivBtc.setImageResource(
            when (pairName) {
                "BTCBUSD" -> R.drawable.btc1
                "ETHBUSD" -> R.drawable.ethere
                "BNBBUSD" -> R.drawable.bnb
                "LUNABUSD" -> R.drawable.luna
                "SOLBUSD" -> R.drawable.solana
                "LTCBUSD" -> R.drawable.ltc1
                "MATICBUSD" -> R.drawable.matic
                "AVAXBUSD" -> R.drawable.avax
                "XRPBUSD" -> R.drawable.xrp
                else -> R.drawable.busd
            })
        }
    }
}
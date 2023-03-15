package com.brunorce.currencypick.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.brunorce.currencypick.databinding.ItemCurrencyBinding
import com.brunorce.currencypick.model.Currency

class CurrencyViewHolder(
    private val binding: ItemCurrencyBinding
) : RecyclerView.ViewHolder(binding.root) {

    /**
     *
     */
    fun bind(currency: Currency) {
        binding.apply {
            ivFlag.setImageResource(currency.flag)
            tvName.text = currency.name
            tvCode.text = currency.code
            tvSymbol.text = currency.symbol
        }
    }

    /**
     *
     */
    fun setTextColor(color: Int?) {
        binding.apply {
            color?.apply {
                tvName.setTextColor(color)
                tvCode.setTextColor(color)
                tvSymbol.setTextColor(color)
            }
        }
    }

    /**
     *
     */
    fun showFullMode(show: Boolean) {
        binding.apply {
            ivFlag.isVisible = show
            tvCode.isVisible = show
        }
    }

    /**
     *
     */
    companion object {
        fun from(parent: ViewGroup): CurrencyViewHolder {
            val layoutInflater = ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return CurrencyViewHolder(layoutInflater)
        }
    }
}
package com.brunoxapps.currencypick.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.brunoxapps.currencypick.databinding.ItemCurrencyBinding
import com.brunoxapps.currencypick.model.Currency

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
    fun showFullCurrencyData(show: Boolean) {
        binding.apply {
            tvCode.isVisible = show
            tvSymbol.isVisible = show
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
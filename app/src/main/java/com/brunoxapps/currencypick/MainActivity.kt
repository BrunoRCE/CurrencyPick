package com.brunoxapps.currencypick

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brunoxapps.currencypick.databinding.ActivityMainBinding
import com.brunoxapps.currencypick.model.Currency

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val currencyPick = CurrencyPick {
            setCurrencyView(it)
        }

        currencyPick.setQueryHint("Custom hint...")

        currencyPick.setTextColor("#2196F3")

        currencyPick.showMinimalData()

        binding.button.setOnClickListener {
            currencyPick.show(supportFragmentManager, "")
        }
    }

    private fun setCurrencyView(currency: Currency) {
        binding.apply {
            ivFlag.setImageResource(currency.flag)
            tvName.text = currency.name
            tvCode.text = currency.code
            tvSymbol.text = currency.symbol
        }
    }
}
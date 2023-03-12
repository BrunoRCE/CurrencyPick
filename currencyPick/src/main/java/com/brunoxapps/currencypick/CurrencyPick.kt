package com.brunoxapps.currencypick

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.brunoxapps.currencypick.adapter.CurrencyAdapter
import com.brunoxapps.currencypick.data.Currencies
import com.brunoxapps.currencypick.databinding.DialogCurrencyPickBinding
import com.brunoxapps.currencypick.model.Currency

class CurrencyPick(
    private val currency: (Currency) -> Unit
) : DialogFragment() {

    /** **/
    private var binding: DialogCurrencyPickBinding? = null

    /** **/
    private var adapter: CurrencyAdapter? = null

    /** **/
    private var queryHint: String? = null

    /** **/
    private var textColor: Int? = null

    /** **/
    private var isFullCurrencyData: Boolean = true

    /**
     *
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())

        binding = DialogCurrencyPickBinding.inflate(requireActivity().layoutInflater)

        builder.setView(binding?.root)

        setupSearchView()

        setupAdapter()

        setQueryHintView(queryHint)

        setTextColorView(textColor)

        showFullCurrencyDataView(isFullCurrencyData)

        return builder.create()
    }

    /**
     *
     */
    private fun setupSearchView() {
        binding?.searchBar?.apply {
            isIconifiedByDefault = false

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    adapter?.filter?.filter(query)
                    return false
                }
            })
        }
    }

    /**
     *
     */
    override fun show(manager: FragmentManager, tag: String?) {
        if (isAdded) {
            return
        }
        super.show(manager, tag)
    }

    /**
     *
     */
    private fun setupAdapter() {
        adapter = CurrencyAdapter(onItemClick)

        binding?.rvCurrency?.adapter = adapter

        adapter?.setData(Currencies.CURRENCIES)
    }

    /**
     *
     */
    private val onItemClick: (Currency) -> Unit = {
        currency(it)
        dismiss()
    }

    /**
     *
     */
    fun setQueryHint(queryHint: String) {
        this.queryHint = queryHint
    }

    /**
     *
     */
    fun showMinimalData() {
        isFullCurrencyData = false
    }

    /**
     *
     */
    fun setTextColor(color: String) {
        try {
            textColor = Color.parseColor(color)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    /**
     *
     */
    private fun setQueryHintView(queryHint: String?) {
        queryHint?.let {
            binding?.searchBar?.queryHint = it
        }
    }

    /**
     *
     */
    private fun showFullCurrencyDataView(show: Boolean) {
        adapter?.showFullCurrencyData(show)
    }

    /**
     *
     */
    private fun setTextColorView(color: Int?) {
        adapter?.setTextColor(color)
    }
}
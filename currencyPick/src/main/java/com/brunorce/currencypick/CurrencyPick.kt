package com.brunorce.currencypick

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.brunorce.currencypick.adapter.CurrencyAdapter
import com.brunorce.currencypick.data.Currencies
import com.brunorce.currencypick.databinding.DialogCurrencyPickBinding
import com.brunorce.currencypick.model.Currency

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
    private var isTextColorOnlyName: Boolean = false

    /** **/
    private var isMinimalMode: Boolean = false

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

        setTextColorOnlyNameView(isTextColorOnlyName)

        setMinimalModeView(isMinimalMode)

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
     * Set your custom query hint.
     * If it is not added, the text "Search..." hint is set by default
     */
    fun setQueryHint(queryHint: String) {
        this.queryHint = queryHint
    }

    /**
     * Sets the text color of all currency information in the view.
     * You should set only the color in hexadecimal format.
     * If it is not added or is the format wrong, the text color GRAY is set by default
     */
    fun setTextColor(colorHex: String) {
        try {
            textColor = Color.parseColor(colorHex)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    /**
     * Applies only the text color assigned to the currency name,
     * all other texts are set to color GRAY
     */
    fun setTextColorOnlyName() {
        isTextColorOnlyName = true
    }

    /**
     * Sets the minimum view format, hiding the flag and currency code
     */
    fun setMinimalMode() {
        isMinimalMode = true
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
    private fun setMinimalModeView(show: Boolean) {
        adapter?.setMinimalMode(show)
    }

    /**
     *
     */
    private fun setTextColorView(color: Int?) {
        adapter?.setTextColor(color)
    }

    /**
     *
     */
    private fun setTextColorOnlyNameView(isTextColorOnlyName: Boolean) {
        adapter?.setTextColorOnlyName(isTextColorOnlyName)
    }
}
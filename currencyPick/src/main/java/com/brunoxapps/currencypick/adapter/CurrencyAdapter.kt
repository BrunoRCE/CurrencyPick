package com.brunoxapps.currencypick.adapter

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.brunoxapps.currencypick.model.Currency
import java.util.Locale

class CurrencyAdapter(
    private val onItemClickListener: (Currency) -> Unit
) : ListAdapter<Currency, CurrencyViewHolder>(AdapterDiffUtil()), Filterable {

    /** **/
    private var itemsClone = mutableListOf<Currency>()

    /** **/
    private var isFullMode: Boolean = true

    /** **/
    private var textColor: Int? = null

    /**
     *
     */
    fun setData(list: List<Currency?>?) {
        itemsClone = list as MutableList<Currency>
        submitList(list)
    }

    /**
     *
     */
    fun showFullMode(show: Boolean) {
        isFullMode = show
    }

    /**
     *
     */
    fun setTextColor(color: Int?) {
        textColor = color
    }

    /**
     *
     */
    fun findItemsBySearch(items: List<Currency>, search: String): List<Currency> {
        val itemsFound = mutableListOf<Currency>()

        for (item in items) {
            if (item.name?.lowercase(Locale.getDefault())
                    ?.contains(search.lowercase(Locale.getDefault())) == true
            ) {
                itemsFound.add(item)
            }
        }

        return itemsFound
    }

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder.from(parent)
    }

    /**
     *
     */
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

        holder.setTextColor(textColor)

        holder.showFullMode(isFullMode)

        holder.itemView.setOnClickListener {
            onItemClickListener(item)
        }
    }

    /**
     *
     */
    override fun getFilter(): Filter {

        return object : Filter() {

            override fun performFiltering(charSequence: CharSequence?): FilterResults {

                val filteredList: List<Currency> = if (charSequence.isNullOrEmpty()) {
                    itemsClone
                } else {
                    findItemsBySearch(itemsClone, charSequence.toString())
                }

                val results = FilterResults()

                results.values = filteredList

                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                val list: List<Currency?>? = results?.values?.let { it as List<Currency?>? }

                submitList(list)
            }
        }
    }

    /**
     *
     */
    internal class AdapterDiffUtil : DiffUtil.ItemCallback<Currency>() {

        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
            oldItem == newItem
    }
}

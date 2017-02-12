package com.yairkukielka.ticker.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yairkukielka.ticker.R
import com.yairkukielka.ticker.data.CurrencyItem

/**
 * Created by Yair Kukielka on 2/5/17.
 */
class CurrencyItemAdapter(val items: List<CurrencyItem>) : RecyclerView.Adapter<CurrencyItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyItemAdapter.ViewHolder {
        val context = parent.getContext()
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.currency_item, parent, false)

        // Return a new holder instance
        val viewHolder = ViewHolder(contactView)
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: CurrencyItemAdapter.ViewHolder, position: Int) {

        // Get the data model based on position
        val item = items.get(position)

        // Set item views based on the data model
        viewHolder.currencyName.setText(item.currencyName)
        viewHolder.currencyValue.setText(item.currencyValue)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currencyName: TextView
        var currencyValue: TextView

        init {
            currencyName = itemView.findViewById(R.id.currency_name) as TextView
            currencyValue = itemView.findViewById(R.id.currency_value) as TextView
        }
    }

}
package com.yairkukielka.ticker.ui

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView
import com.yairkukielka.ticker.R
import com.yairkukielka.ticker.data.CurrencyItem
import com.yairkukielka.ticker.domain.STATE


/**
 * Created by Yair Kukielka on 2/5/17.
 */
class CurrencyItemAdapter() : RecyclerView.Adapter<CurrencyItemAdapter.ViewHolder>() {

    var items: MutableList<CurrencyItem> = mutableListOf()

    fun replaceWithNewItems(newItems: List<CurrencyItem>) {
        if (newItems.size > 5 && newItems.size == items.size) {
            for (i in newItems.indices) {
                val newItem = newItems[i]
                if (newItem.state != STATE.NOT_CHANGED) {
                    items[i] = newItem
                    notifyItemChanged(i)
                }
            }
        } else {
            items.clear()
            items.addAll(newItems)
            notifyDataSetChanged()
        }
    }

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

//        if (!viewHolder.currencyName.text.equals(item.currencyName)) {
        // Set item views based on the data model
        viewHolder.currencyName.setText(item.currencyName)
//        }
//        if (!viewHolder.lastValue.equals(item.currencyValue)) {
//        viewHolder.currencyValue.setText(item.currencyValue)
        viewHolder.tickerView.setText(item.currencyValue)
//            viewHolder.lastValue = item.currencyValue
        checkColor(item.state, viewHolder.currencyName, viewHolder.tickerView)
//        }
    }

    private fun checkColor(CHANGEDSTATE: STATE, itemName: TextView, itemValue: TickerView) {
        var color: Int
        if (CHANGEDSTATE.equals(STATE.INCREASED)) {
            color = Color.GREEN
        } else if (CHANGEDSTATE.equals(STATE.DECREASED)) {
            color = Color.RED
        } else {
            color = Color.BLACK
        }

//        if (color != Color.BLACK) {
        itemName.setTextColor(color)
//        }
        itemValue.textColor = color

    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var currencyName: TextView
//        var tickerView: TickerView
        @BindView(R.id.currency_name) lateinit var currencyName: TextView
        @BindView(R.id.tickerView) lateinit var tickerView: TickerView

        init {
            ButterKnife.bind(this, itemView)
//            currencyName = itemView.findViewById(R.id.currency_name) as TextView
//            tickerView = itemView.findViewById(R.id.tickerView) as TickerView
            tickerView.setCharacterList(TickerUtils.getDefaultListForUSCurrency());
//
        }

    }

}
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
import com.yairkukielka.ticker.data.STATE


/**
 * Created by Yair Kukielka on 2/5/17.
 */
class CurrencyItemAdapter : RecyclerView.Adapter<CurrencyItemAdapter.ViewHolder>() {

    private var items: MutableList<CurrencyItem> = mutableListOf()

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.getContext()
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.currency_item, parent, false)

        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

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
        checkColor(item.state, viewHolder.tickerView)
//        }
    }

    private fun checkColor(state: STATE, itemValue: TickerView) {
        val color: Int = when (state) {
            STATE.INCREASED -> Color.parseColor("#009900") // Dark green
            STATE.DECREASED -> Color.RED
            else -> Color.BLACK
        }
        itemValue.textColor = color

    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.currency_name)
        lateinit var currencyName: TextView
        @BindView(R.id.tickerView)
        lateinit var tickerView: TickerView

        init {
            ButterKnife.bind(this, itemView)
            tickerView.setCharacterList(TickerUtils.getDefaultListForUSCurrency())
        }

    }

}
package com.yairkukielka.ticker.data


import com.yairkukielka.ticker.domain.STATE

data class CurrencyItem(val currencyName: String, val currencyValue: String) {
    var state: STATE = STATE.NOT_CHANGED
}
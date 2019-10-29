package com.yairkukielka.ticker.data


data class CurrencyItem(val currencyName: String, val currencyValue: String) {
    var state: STATE = STATE.NOT_CHANGED
}
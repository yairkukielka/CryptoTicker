package com.yairkukielka.ticker.data


import com.yairkukielka.ticker.domain.CHANGED_STATE

data class CurrencyItem(val currencyName: String, val currencyValue: String) {
    var CHANGEDSTATE: CHANGED_STATE = CHANGED_STATE.UNKOWN
}
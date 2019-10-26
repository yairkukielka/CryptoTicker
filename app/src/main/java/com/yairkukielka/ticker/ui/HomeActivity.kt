package com.yairkukielka.ticker.ui

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.yairkukielka.ticker.R
import com.yairkukielka.ticker.TickerApplication
import com.yairkukielka.ticker.data.CurrencyItem
import com.yairkukielka.ticker.domain.HomePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    @Inject lateinit var presenter: HomePresenter

    @BindView(R.id.rvCurrencies) lateinit var rvCurrencies: RecyclerView
    @BindView(R.id.fab) lateinit var fab: FloatingActionButton
    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar

    lateinit var disposable: Disposable
    var items: MutableList<CurrencyItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this);

        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        TickerApplication.component.injectActivity(this)

        initList()
    }

    private fun initList() {
        val adapter = CurrencyItemAdapter()
//        rvCurrencies.setHasFixedSize(true)
        rvCurrencies.adapter = adapter
        rvCurrencies.layoutManager = LinearLayoutManager(this)

        val item1 = CurrencyItem("This", "is")
        val item2 = CurrencyItem("the", "CryptoTicker!")
        items.add(item1)
        items.add(item2)
        adapter.repalaceWithNewItems(items)
    }

    override fun onStart() {
        super.onStart()
        val adapter = rvCurrencies.adapter as CurrencyItemAdapter

        disposable = getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { adapter.repalaceWithNewItems(it) },
                        { Log.d("TAG", toString()) },
                        { Log.d("TAG", toString()) }
                )
    }

    override fun onStop() {
        disposable.dispose()
        super.onStop()
    }

    fun getObservable(): Observable<List<CurrencyItem>> {
        return presenter.tick()
    }

}

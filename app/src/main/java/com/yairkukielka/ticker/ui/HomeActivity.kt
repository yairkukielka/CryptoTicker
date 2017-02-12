package com.yairkukielka.ticker.ui

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

import com.yairkukielka.ticker.R
import com.yairkukielka.ticker.TickerApplication
import com.yairkukielka.ticker.data.*
import com.yairkukielka.ticker.domain.CurrencyInfo
import com.yairkukielka.ticker.domain.HomePresenter
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomeActivity : AppCompatActivity() {

    @Inject lateinit var presenter: HomePresenter

    @BindView(R.id.rvCurrencies) lateinit var rvCurrencies: RecyclerView
    @BindView(R.id.fab) lateinit var fab: FloatingActionButton
    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar

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
        presenter.tick()

        initList()
    }

    private fun initList() {
//        var items: Observable<List<CurrencyItem>> = getObservable()

        val adapter = CurrencyItemAdapter(items)
        rvCurrencies.adapter = adapter
        rvCurrencies.layoutManager = LinearLayoutManager(this)

        val item1 = CurrencyItem("Margarita", "guapa")
        val item2 = CurrencyItem("es", "impresionante!")
        items.add(item1)
        items.add(item2)
        getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            items.clear()
                            items.addAll(it)
                            adapter.notifyDataSetChanged()
                        },
                        { Log.d("TAG", toString()) },
                        { Log.d("TAG", toString()) }
                )
    }

    fun getObservable(): Observable<List<CurrencyItem>> {
        return presenter.tick()
    }

}

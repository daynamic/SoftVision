package com.akshat.softvision.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshat.softvision.R
import com.akshat.softvision.adapter.ListsAdapter
import com.akshat.softvision.viewmodel.MainViewModel
import com.akshat.softvision.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var factory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        progress_bar.visibility = View.VISIBLE
        itemsswipetorefresh.visibility = View.GONE
        viewModel.getServices()

        //** Set the colors of the Pull To Refresh View
        itemsswipetorefresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
        itemsswipetorefresh.setColorSchemeColors(Color.WHITE)

        itemsswipetorefresh.setOnRefreshListener {
            viewModel.getServices()
            itemsswipetorefresh.isRefreshing = false
        }

        viewModel.providedData.observe(this, Observer { providedData ->
            progress_bar.visibility = View.GONE
            itemsswipetorefresh.visibility = View.VISIBLE
            recycler_view.also {
                supportActionBar?.title = viewModel.heading.value.toString()
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = ListsAdapter(providedData)
            }
        }
        )


    }
}
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
        //Factory implementation
        factory = MainViewModelFactory()
        //assigning ViewModel
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        //Showing Progressbar
        progress_bar.visibility = View.VISIBLE
        //hiding the content
        itemsswipetorefresh.visibility = View.GONE
        //Network call via viewmodel
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

        //Observing LiveData and Injecting the data in ListView
        viewModel.providedData.observe(this, Observer { providedData ->
            progress_bar.visibility = View.GONE
            itemsswipetorefresh.visibility = View.VISIBLE
            recycler_view.also {
                //Setting title text obtained from the network Call
                supportActionBar?.title = viewModel.heading.value.toString()
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                //Injecting Values in Adapter
                it.adapter = ListsAdapter(providedData)
            }
        }
        )


    }
}
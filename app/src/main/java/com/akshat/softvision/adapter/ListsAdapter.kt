package com.akshat.softvision.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshat.softvision.model.Row

/**
 * Created by Akshat on 23/06/20.
 */
class ListsAdapter(
    private val reportsAvailable : List<Row>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  PostsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as PostsViewHolder).bind(reportsAvailable[position])
    }

    override fun getItemCount()=reportsAvailable.size

}
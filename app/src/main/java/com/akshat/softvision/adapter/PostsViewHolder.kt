package com.akshat.softvision.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshat.softvision.R
import com.akshat.softvision.model.Row
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.list_items.view.*
import kotlinx.android.synthetic.main.list_items.view.img_banner
import kotlinx.android.synthetic.main.list_items.view.txt_name

/**
 * Created by Akshat on 23/06/20.
 */
class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(responseItem: Row) {
        if (responseItem != null) {
            itemView.txt_name.text = responseItem.title
            itemView.txt_description.text = responseItem.description
            if (!responseItem.imageHref.isNullOrEmpty())
                Picasso.get().load(responseItem.imageHref).into(itemView.img_banner)
        }
    }

    companion object {
        fun create(parent: ViewGroup): PostsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
            return PostsViewHolder(view)
        }
    }


}
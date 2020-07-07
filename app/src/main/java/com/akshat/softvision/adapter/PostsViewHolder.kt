package com.akshat.softvision.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshat.softvision.R
import com.akshat.softvision.model.Row
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


/**
 * Created by Akshat on 23/06/20.
 */
class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(responseItem: Row) {
        //Setting text from Response
        itemView.txt_name.text = responseItem.title
        //Setting Description from Response
        itemView.txt_description.text = responseItem.description
        //Setting Image from Response
        if (!responseItem.imageHref.isNullOrEmpty())
            Picasso.get().load(responseItem.imageHref).into(itemView.img_banner)
        else
            itemView.img_banner.setImageResource(R.drawable.ic_launcher_background)
    }

    companion object {
        fun create(parent: ViewGroup): PostsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
            return PostsViewHolder(view)
        }
    }


}
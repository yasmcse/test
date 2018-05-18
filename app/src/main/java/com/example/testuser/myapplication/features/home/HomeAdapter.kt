package com.example.testuser.myapplication.features.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testuser.myapplication.R
import com.example.testuser.myapplication.model.Post

class HomeAdapter(val context: Context) : RecyclerView.Adapter<PostListViewHolder>() {
    var listItems: List<Post>? = null
        set(list) {
            field = list
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        return PostListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder?.setData(listItems?.get(position))
    }


    override fun getItemCount(): Int {
        return listItems?.size ?: 0
    }

}
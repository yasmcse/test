package com.example.testuser.myapplication.features.home

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.testuser.myapplication.model.Post
import kotlinx.android.synthetic.main.item_list.view.*

class PostListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var userId = view.userId
    private var title = view.title
    private var body = view.body
    private var post: Post? = null


    fun setData(post: Post?) {
        this.post = post
        userId.text = "user Id = " + post?.userId.toString() + "\n"
        title.text = "title \n\n" + post?.title + "\n"
        body.text = "body \n\n" + post?.body + "\n"

    }
}
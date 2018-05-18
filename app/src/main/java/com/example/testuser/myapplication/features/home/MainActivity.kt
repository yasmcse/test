package com.example.testuser.myapplication.features.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.*
import android.widget.ProgressBar
import butterknife.ButterKnife
import com.example.testuser.myapplication.R
import com.example.testuser.myapplication.model.Post
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), HomeActivityPresenter.View {
    private var homeAdapter: HomeAdapter? = null
    private var presenter: HomeActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        ButterKnife.bind(this)
        presenter = HomeActivityPresenter()
        presenter?.attach(this)

    }

    fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        homeRecyclerView.layoutManager = layoutManager
        homeRecyclerView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        homeAdapter = HomeAdapter(this)
        homeRecyclerView.adapter = homeAdapter
    }

    override fun displayPost(listOfPosts: List<Post>?) {
        homeRecyclerView.visibility = VISIBLE
        homeAdapter?.listItems = listOfPosts
    }

    override fun displayError(error: String?) {
        error_msg.visibility = VISIBLE
        error_msg.text = error
    }

    override fun displayLoading(show: Boolean) = if (show) {
        progressBar.visibility = VISIBLE
    } else {
        progressBar.visibility = GONE
    }
}

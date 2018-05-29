package com.example.testuser.myapplication.features.home


import com.example.testuser.myapplication.base.Presenter
import com.example.testuser.myapplication.model.Post
import com.example.testuser.myapplication.service.MyService
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class HomeActivityPresenter : Presenter<HomeActivityPresenter.View>() {

    var myService = MyService.Creator.newMyService()
    var backgroundThreadScheduler = Schedulers.newThread()
    var mainThreadScheduler = AndroidSchedulers.mainThread()


    override fun onViewAttached(view: View) {
        super.onViewAttached(view)

        val postsObservable = myService.posts
                .subscribeOn(backgroundThreadScheduler)
                .observeOn(mainThreadScheduler)

        postsObservable.subscribe(object : Observer<List<Post>> {
            override fun onNext(postList: List<Post>?) {
                view.displayLoading(true)
                view.displayPost(postList)
            }

            override fun onCompleted() {
                view.displayLoading(false)
            }

            override fun onError(e: Throwable) {
                view.displayError(e.message)
            }

        })

    }


    interface View : Presenter.View {

        fun displayPost(listOfPosts: List<Post>?)

        fun displayError(e:String?)

        fun displayLoading(show:Boolean)

    }
}
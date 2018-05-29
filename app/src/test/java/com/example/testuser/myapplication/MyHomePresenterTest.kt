package com.example.testuser.myapplication


import com.example.testuser.myapplication.features.home.HomeActivityPresenter
import com.example.testuser.myapplication.model.Post
import com.example.testuser.myapplication.service.MyService
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import rx.Observable
import rx.schedulers.Schedulers


/**
 * Tests to test the Presenter logic
 */
class MyHomePresenterTests {


    var networkService: MyService = mock(MyService::class.java)
    val view: HomeActivityPresenter.View = mock(HomeActivityPresenter.View::class.java)
    val presenter = HomeActivityPresenter()

    val post1 = Post(1, 1, "Post Title", "Post Body")
    val list = listOf<Post>(post1)


    @Before
    fun setUp() {


    }

    @Test
    fun whenListLoadsSuccessfully() {
        `when`(networkService.posts).thenReturn(Observable.just(list))
        presenter.myService = networkService
        presenter.backgroundThreadScheduler = Schedulers.immediate()
        presenter.mainThreadScheduler = Schedulers.immediate()
        presenter.attach(view)

        verify(view).displayLoading(true)
        verify(view).displayPost(list)
    }

    @Test
    fun whenListIsEmpty() {
        `when`(networkService.posts).thenReturn(Observable.just(kotlin.collections.emptyList()))
        presenter.myService = networkService
        presenter.backgroundThreadScheduler = Schedulers.immediate()
        presenter.mainThreadScheduler = Schedulers.immediate()
        presenter.attach(view)

        verify(view).displayLoading(true)
        verify(view).displayPost(emptyList())
    }

}
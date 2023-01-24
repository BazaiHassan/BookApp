package com.android.bookborrowapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseActivity : AppCompatActivity(),BaseView{
    override fun setProgressBar(progress: Boolean) {

    }
}
interface BaseView{
    fun setProgressBar(progress:Boolean)
}
fun <T>Single<T>.singleObserver(): Single<T>
{
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

abstract class BaseViewModel : ViewModel(){

  val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
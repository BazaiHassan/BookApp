package com.android.bookborrowapp.utils

import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BookSingleObserver<T>(val compositeDisposable: CompositeDisposable): SingleObserver<T> {

 override fun onSubscribe(p0: Disposable) {
  compositeDisposable.add(p0)
 }

 override fun onError(p0: Throwable) {
 }
}
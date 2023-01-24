package com.android.bookborrowapp.auth

import androidx.lifecycle.MutableLiveData
import com.android.bookborrowapp.auth.repo.AuthRepository
import com.android.bookborrowapp.base.BaseView
import com.android.bookborrowapp.base.BaseViewModel
import com.android.bookborrowapp.base.singleObserver
import com.android.bookborrowapp.data.ResponseCheckRegister
import com.android.bookborrowapp.utils.BookSingleObserver
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers.io

class AuthViewModel(val authRepository: AuthRepository):BaseViewModel(){

    val registerCheckLiveData = MutableLiveData<ResponseCheckRegister>()
    val checkLoginLivedata = MutableLiveData<Boolean>()

    fun checkRegister(phone:String){

        authRepository.checkRegister(phone).singleObserver()
            .subscribe(object : BookSingleObserver<ResponseCheckRegister>(compositeDisposable){
                override fun onSuccess(t: ResponseCheckRegister) {
                  registerCheckLiveData.value = t
                                    }

            })
    }

    fun checkLogin(){
        checkLoginLivedata.value = authRepository.checkLogin()
    }
}
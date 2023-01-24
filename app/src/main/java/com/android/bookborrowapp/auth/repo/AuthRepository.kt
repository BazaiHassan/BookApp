package com.android.bookborrowapp.auth.repo

import com.android.bookborrowapp.data.ResponseCheckRegister
import io.reactivex.Single

interface AuthRepository {
    fun checkRegister(phone:String):Single<ResponseCheckRegister>
    //
    fun loadToken()
    fun checkLogin():Boolean
}
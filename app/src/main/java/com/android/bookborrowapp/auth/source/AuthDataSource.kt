package com.android.bookborrowapp.auth.source

import android.text.BoringLayout
import com.android.bookborrowapp.data.ResponseCheckRegister
import io.reactivex.Single

interface AuthDataSource {
    fun checkRegister(phone:String): Single<ResponseCheckRegister>

    //
    fun saveToken(token:String)
    fun loadToken()
    fun checkLogin():Boolean

}
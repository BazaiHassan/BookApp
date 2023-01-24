package com.android.bookborrowapp.auth.source

import android.content.SharedPreferences
import com.android.bookborrowapp.auth.TokenContainer
import com.android.bookborrowapp.data.ResponseCheckRegister
import io.reactivex.Single

class LocalAuthDataSource(val sharedPreferences: SharedPreferences):AuthDataSource {
    override fun checkRegister(phone: String): Single<ResponseCheckRegister> {
        TODO("Not yet implemented")
    }

    override fun saveToken(token: String) {
      sharedPreferences.edit().apply {
          putString("token",token)
      }.apply()
    }

    override fun loadToken() {
        TokenContainer.updateToken(sharedPreferences.getString("token",null))
    }

    override fun checkLogin(): Boolean {
        if(sharedPreferences.getString("token","")!=""){
            return true
        }else{
            return false
        }
    }
}
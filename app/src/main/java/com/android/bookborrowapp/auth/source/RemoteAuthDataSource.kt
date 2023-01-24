package com.android.bookborrowapp.auth.source

import com.android.bookborrowapp.api.ApiService
import com.android.bookborrowapp.data.ResponseCheckRegister
import io.reactivex.Single

class RemoteAuthDataSource(val apiService: ApiService) : AuthDataSource {
    override fun checkRegister(phone: String): Single<ResponseCheckRegister> = apiService.checkUser(phone)

    override fun saveToken(token: String) {

    }

    override fun loadToken() {
    }

    override fun checkLogin(): Boolean {
        TODO("Not yet implemented")
    }
}
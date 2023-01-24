package com.android.bookborrowapp.auth.repo

import com.android.bookborrowapp.auth.TokenContainer
import com.android.bookborrowapp.auth.source.AuthDataSource
import com.android.bookborrowapp.auth.source.LocalAuthDataSource
import com.android.bookborrowapp.auth.source.RemoteAuthDataSource
import com.android.bookborrowapp.data.ResponseCheckRegister
import io.reactivex.Single

class AuthRepositoryImpl(val remoteAuthDataSource: AuthDataSource,val localAuthDataSource: LocalAuthDataSource):AuthRepository {

    override fun checkRegister(phone: String): Single<ResponseCheckRegister> {
        return remoteAuthDataSource.checkRegister(phone).doOnSuccess {
            TokenContainer.updateToken(it.token)
            localAuthDataSource.saveToken(it.token)
        }
    }

    override fun loadToken() {
      localAuthDataSource.loadToken()
    }

    override fun checkLogin(): Boolean {
     return localAuthDataSource.checkLogin()
    }
}
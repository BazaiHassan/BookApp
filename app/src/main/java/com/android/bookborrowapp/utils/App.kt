package com.android.bookborrowapp.utils

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.android.bookborrowapp.api.ApiService
import com.android.bookborrowapp.api.retrofitApi
import com.android.bookborrowapp.auth.AuthViewModel
import com.android.bookborrowapp.auth.repo.AuthRepository
import com.android.bookborrowapp.auth.repo.AuthRepositoryImpl
import com.android.bookborrowapp.auth.source.LocalAuthDataSource
import com.android.bookborrowapp.auth.source.RemoteAuthDataSource
import com.android.bookborrowapp.utils.AppSignatureHashHelper.AppSignatureHashHelper
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val appSignatureHashHelper = AppSignatureHashHelper(this)
        Log.i("RE","Hashkey:"+appSignatureHashHelper.appSignatures[0])
        val myModule = module {
            single<ApiService> { retrofitApi() }
            single<SharedPreferences> {this@App.getSharedPreferences("user", MODE_PRIVATE)}
            factory<AuthRepository> { AuthRepositoryImpl(RemoteAuthDataSource(get()), LocalAuthDataSource(get()))}

            viewModel { AuthViewModel(get()) }

        }

        startKoin {
            androidContext(this@App)
            modules(myModule)
        }

        val authRepository : AuthRepository = get()
        authRepository.loadToken()

    }

}
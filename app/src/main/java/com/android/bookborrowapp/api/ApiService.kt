package com.android.bookborrowapp.api

import com.android.bookborrowapp.data.ResponseCheckRegister
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register.php")
    fun checkUser(@Field("mobile_phone") phone:String):Single<ResponseCheckRegister>
}

fun retrofitApi() : ApiService{
    val retrofit=Retrofit.Builder()
        .baseUrl("https://bookstore.shopsepehraein.ir/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
  return retrofit.create(ApiService::class.java)
}
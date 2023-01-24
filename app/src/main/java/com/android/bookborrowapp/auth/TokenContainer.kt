package com.android.bookborrowapp.auth

object TokenContainer {
    var token : String ?=null
    private set
    fun updateToken(token:String?){
       this.token=token
    }
}
package com.android.bookborrowapp.data

import com.google.gson.annotations.SerializedName

data class ResponseCheckRegister(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: String
)

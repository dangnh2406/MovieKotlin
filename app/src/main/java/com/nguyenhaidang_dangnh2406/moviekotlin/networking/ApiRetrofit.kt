package com.nguyenhaidang_dangnh2406.moviekotlin.networking

import com.google.gson.Gson
import com.nguyenhaidang_dangnh2406.moviekotlin.interfaces.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRetrofit {
    var BASE_URL = "https://api.themoviedb.org/"
    val getApi : ApiInterface
    get() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }


}
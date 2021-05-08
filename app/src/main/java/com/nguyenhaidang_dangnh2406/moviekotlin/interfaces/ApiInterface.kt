package com.nguyenhaidang_dangnh2406.moviekotlin.interfaces

import com.nguyenhaidang_dangnh2406.moviekotlin.model.movies.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("3/movie/popular?api_key=414ffc7cfe79b04554b68edfa48428d3")
    fun getMovies(@Query("page") page :Int) : Call<Movies>


}
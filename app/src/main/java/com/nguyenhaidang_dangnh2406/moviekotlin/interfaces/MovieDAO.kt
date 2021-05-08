package com.nguyenhaidang_dangnh2406.moviekotlin.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nguyenhaidang_dangnh2406.moviekotlin.model.favorite.MovieDislike
import com.nguyenhaidang_dangnh2406.moviekotlin.model.favorite.MovieLike

@Dao
interface MovieDAO {
    @Insert
    fun insertMovieLike(movieLike: MovieLike)

    @Insert
    fun insertMovieDislike(movieDislike: MovieDislike)

    @Query("select * from movie_like")
    fun readAllMovieLike() : MutableList<MovieLike>

    @Query("select * from movie_dislike")
    fun  readAllMovieDislike() :MutableList<MovieDislike>
}
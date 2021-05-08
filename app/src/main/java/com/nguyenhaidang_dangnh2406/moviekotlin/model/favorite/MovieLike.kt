package com.nguyenhaidang_dangnh2406.moviekotlin.model.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_like")
data class MovieLike (
    @PrimaryKey

    var id_movie : String,
    var title :String,
    var poster_img :String,
    var rating : String,
    var release :String,
    var overview :String
)
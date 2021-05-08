package com.nguyenhaidang_dangnh2406.moviekotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nguyenhaidang_dangnh2406.moviekotlin.interfaces.MovieDAO
import com.nguyenhaidang_dangnh2406.moviekotlin.model.favorite.MovieDislike
import com.nguyenhaidang_dangnh2406.moviekotlin.model.favorite.MovieLike

@Database(entities = [MovieLike::class, MovieDislike::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDaabase (context: Context) : MovieDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie.db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
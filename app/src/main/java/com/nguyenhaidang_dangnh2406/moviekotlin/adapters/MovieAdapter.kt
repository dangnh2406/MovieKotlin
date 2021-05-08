package com.nguyenhaidang_dangnh2406.moviekotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nguyenhaidang_dangnh2406.moviekotlin.R
import com.nguyenhaidang_dangnh2406.moviekotlin.db.MovieDatabase
import com.nguyenhaidang_dangnh2406.moviekotlin.model.favorite.MovieDislike
import com.nguyenhaidang_dangnh2406.moviekotlin.model.favorite.MovieLike
import com.nguyenhaidang_dangnh2406.moviekotlin.model.movies.Movies
import com.nguyenhaidang_dangnh2406.moviekotlin.model.movies.Result
import com.nguyenhaidang_dangnh2406.moviekotlin.util.StringCommons
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_movie.view.*

class MovieAdapter(var context: Context,var list: MutableList<Result>) :
    RecyclerView.Adapter<MovieAdapter.ViewHoder>() , View.OnClickListener {
    class ViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.row_movie_title_txt
        var rating = itemView.row_movie_rating_txt
        var release = itemView.row_movie_release_txt
        var avt = itemView.row_movie_poster_img
        var overview = itemView.row_movie_overview_title
        var title_overview = itemView.row_movie_overview
        var icon_like = itemView.row_movie_like_img
        var icon_unlike = itemView.row_movie_unlike_img
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        var view = LayoutInflater.from(context).inflate(R.layout.row_movie,parent,false)
        return ViewHoder(view)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHoder, position: Int) {
        holder.title.text = list[position].title
        holder.rating.text = "Rating: ${list[position].voteAverage}"
        Picasso.get().load(StringCommons.domain+list[position].posterPath).into(holder.avt)
        holder.release.text = "Release: ${list[position].releaseDate}"
        holder.overview.text = list[position].overview
        holder.title_overview.text = "Overview"
        holder.icon_like.setTag(position)
        holder.icon_like.setOnClickListener(this)
        holder.icon_unlike.setTag(position)
        holder.icon_unlike.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var position = v?.getTag()
        var id_movie:String = list[position as Int].id.toString()
        var title :String = list[position].title
        var poster_img :String = list[position].posterPath
        var rating:String = list[position].voteAverage.toString()
        var release :String = list[position].releaseDate
        var overview :String = list[position].overview
        when(v?.id){
            R.id.row_movie_like_img->{
                var movieLike = MovieLike(id_movie,title,poster_img,rating,release,overview)
                MovieDatabase.getDaabase(context).movieDAO().insertMovieLike(movieLike)
                Toast.makeText(context, "Added to your favorite list!"+MovieDatabase.getDaabase(context).movieDAO().readAllMovieLike().size, Toast.LENGTH_SHORT).show()
            }

            R.id.row_movie_unlike_img->{
                var movieDislike = MovieDislike(id_movie,title,poster_img,rating,release,overview)
                MovieDatabase.getDaabase(context).movieDAO().insertMovieDislike(movieDislike)
                Toast.makeText(context, "Added to your dislike favorite!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
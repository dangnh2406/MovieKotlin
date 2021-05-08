package com.nguyenhaidang_dangnh2406.moviekotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nguyenhaidang_dangnh2406.moviekotlin.R
import com.nguyenhaidang_dangnh2406.moviekotlin.adapters.MovieAdapter
import com.nguyenhaidang_dangnh2406.moviekotlin.interfaces.ApiInterface
import com.nguyenhaidang_dangnh2406.moviekotlin.model.movies.Movies
import com.nguyenhaidang_dangnh2406.moviekotlin.model.movies.Result
import com.nguyenhaidang_dangnh2406.moviekotlin.networking.ApiRetrofit
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MovieFragment : Fragment() ,NestedScrollView.OnScrollChangeListener{
    var listMovie : MutableList<Result> = mutableListOf()
    lateinit var movieAdapter:MovieAdapter
    var page :Int = 1

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_movie, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getData(page)
    }

    override fun onScrollChange(
        v: NestedScrollView?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight){
            getData(++page)
            fragment_movie_progress_bar.visibility = View.VISIBLE
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MovieFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    fun getData(page : Int){
        val call : Call<Movies> = ApiRetrofit.getApi.getMovies(page)
        call.enqueue(object : Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                listMovie.addAll(response.body()!!.results.toMutableList())
                movie_recycleview.adapter = context?.let { MovieAdapter(it,listMovie) }
                movie_recycleview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                fragment_movie_progress_bar.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(context, "Loi", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun initView(){
        movie_recycleview.layoutManager = LinearLayoutManager(context)
        movieAdapter = MovieAdapter(context!!,listMovie)
        movie_recycleview.adapter = movieAdapter
        fragment_movie_nested_scroll_view.setOnScrollChangeListener(this)
    }

}
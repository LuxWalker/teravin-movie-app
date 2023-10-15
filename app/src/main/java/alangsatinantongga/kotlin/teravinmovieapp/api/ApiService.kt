package alangsatinantongga.kotlin.teravinmovieapp.api

import alangsatinantongga.kotlin.teravinmovieapp.model.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getMovies(@Query("page") page: Int): Call<MovieListResponse>
}